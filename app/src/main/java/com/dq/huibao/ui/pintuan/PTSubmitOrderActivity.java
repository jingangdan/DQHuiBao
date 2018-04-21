package com.dq.huibao.ui.pintuan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pintuan.PinTuanCheckOrder;
import com.dq.huibao.ui.PayActivity;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddrListActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：拼团确认提交
 * Created by jingang on 2017/10/30.
 */

public class PTSubmitOrderActivity extends BaseActivity {
    @Bind(R.id.pt_submitorder_address)
    LinearLayout linAddr;
    /*地址*/
    @Bind(R.id.pt_tv_submitorder_address)
    TextView tvAddr;
    /*总价*/
    @Bind(R.id.pt_tv_confirm_pay)
    TextView tvConfirmPay;
    /*确认订单*/
    @Bind(R.id.pt_but_confirm_pay)
    Button butConfirmPay;
    /*商店名称*/
    @Bind(R.id.pt_tv_checkorder_shopname)
    TextView ptTvCheckorderShopname;
    /*商品图片*/
    @Bind(R.id.iv_item_co_img)
    ImageView ivItemCoImg;
    /*商品名称*/
    @Bind(R.id.tv_item_co_goodsname)
    TextView tvItemCoGoodsname;
    /*商品规格*/
    @Bind(R.id.tv_item_co_option)
    TextView tvItemCoOption;
    /*商品价格*/
    @Bind(R.id.tv_item_co_marketprice)
    TextView tvItemCoMarketprice;
    /*商品数量*/
    @Bind(R.id.tv_item_co_buycount)
    TextView tvItemCoBuycount;
    /*留言*/
    @Bind(R.id.pt_et_checkorder_comment)
    EditText ptEtCheckorderComment;
    /*共几件*/
    @Bind(R.id.pt_tv_checkorder_num)
    TextView tvCheckorderNum;
    /*商品总价*/
    @Bind(R.id.pt_tv_checkorder_money)
    TextView ptTvCheckorderMoney;
    /*运费*/
    @Bind(R.id.pt_tv_checkorder_dispatch)
    TextView ptTvCheckorderDispatch;
    /*会员优惠*/
    @Bind(R.id.pt_tv_co_discount_all)
    TextView ptTvCoDiscountAll;
    /**/
    @Bind(R.id.pt_iv_youhuiquan_tight)
    ImageView ptIvYouhuiquanTight;
    /*商品小计价格*/
    @Bind(R.id.pt_tv_co_pay_all)
    TextView ptTvCoPayAll;

    private String remark = "";

    /*接收页面传值*/
    private Intent intent;
    private String cartids = "", goodsid = "", count = "", optionid = "", tag = "", pid = "", tuanid = "";
    Set<String> optionidSets;

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "", uid = "";

    /*收货地址*/
    private List<Addr.DataBean> addrList = new ArrayList<>();
    private String regionid = "";//市级id（省市二级id）
    private String addrid = "";//收货地址id

    /*支付价格*/
    private double pay_all = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_submitorder);
        ButterKnife.bind(this);

        intent = getIntent();
        cartids = intent.getStringExtra("cartids");
        goodsid = intent.getStringExtra("goodsid");
        count = intent.getStringExtra("count");
        optionid = intent.getStringExtra("optioned");
        tag = intent.getStringExtra("tag");
        pid = intent.getStringExtra("pid");
        tuanid = intent.getStringExtra("tuanid");
        optionid = intent.getStringExtra("optioned");
        optionidSets = new HashSet<>(Arrays.asList(intent.getStringExtra("optionSpecs").split("\\_")));

        isLogin();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("确认订单");
    }

    @OnClick({R.id.pt_submitorder_address, R.id.pt_but_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pt_submitorder_address:
                if (regionid.equals("")) {
                    //添加收货地址
                    intent = new Intent(this, AddAddressActivity.class);
                    intent.putExtra("tag", "0");
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                } else {
                    //选择收货地址
                    intent = new Intent(this, AddrListActivity.class);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                }
                break;

            case R.id.pt_but_confirm_pay:
                //提交订单
//                JSONObject object = new JSONObject();//创建一个总的对象，这个对象对整个json串

//                orderBuynow(phone, token, goodsid, addrid, count, optionid, URLEncoder.encode(shopList.get(0).getCommet(), "UTF-8"), shopList.get(0).getCommet());
                orderBuynow();

                break;
            default:
                break;
        }

    }

    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            uid = login.getData().getUid();
            getAddr(phone, token);
        } else {
            toast("请重新登录");
        }

    }

    /**
     * 获取收货地址
     *
     * @param phone
     * @param token
     */
    public void getAddr(final String phone, final String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpPath.MEMBER_GETADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");

        System.out.println("获取收货地址 = " + PATH);
        HttpxUtils.Get(this, PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("获取收货地址 = " + result);
                Addr addr = GsonUtil.gsonIntance().gsonToBean(result, Addr.class);
                addrList.clear();
                addrList = addr.getData();
                if (addr.getStatus() == 1) {
                    //确认订单
                    for (int i = 0; i < addrList.size(); i++) {
                        if (addrList.get(i).getIsdefault().equals("1")) {
                            regionid = addrList.get(i).getRegionid();
                            addrid = addrList.get(i).getId();
                            tvAddr.setText(addrList.get(i).getContact() + "(" + addrList.get(i).getMobile() + ")\n" +
                                    addrList.get(i).getProvince() + "." + addrList.get(i).getCity() + "." + addrList.get(i).getAddr());

                            getCheckorder();
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 确认订单（商品详情）
     *
     */
    public void getCheckorder() {
//        MD5_PATH = "addrid=" + addrid + "&count=" + count + "&goodsid=" + goodsid + "&optionid=" + optionid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

//        PATH = HttpPath.PINTUAN_TUAN_ADDORDER + MD5_PATH + "&sign=" +
//                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pid", pid);
        map.put("count", count);
        map.put("is_tuan", tag);//单独买0 拼团买1
        map.put("tuanid", tuanid);
        map.put("goodsid", goodsid);
        map.put("ids", optionidSets.toString());
        System.out.println("确认订单（商品详情） = " + map.toString());
        HttpxUtils.Post(this, HttpPath.PINTUAN_TUAN_CONFIRM, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("确认订单（商品详情） = " + result);
                PinTuanCheckOrder checkOrder = GsonUtil.gsonIntance().gsonToBean(result, PinTuanCheckOrder.class);

                updateUI(checkOrder);
                /*商品总价 + 运费 - 优惠*/
                pay_all = checkOrder.getData().getMoney_all()
                        + checkOrder.getData().getDispatch_all()
                        - checkOrder.getData().getDiscount_all();

                tvConfirmPay.setText("需付：¥" + pay_all);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("确认订单（商品详情） = 失败" + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 更新ui
     */
    public void updateUI(PinTuanCheckOrder checkOrder) {
        goodsid = checkOrder.getData().getGoodsid()+"";
        count = checkOrder.getData().getCount() + "";
        /*商店名称*/
//        ptTvCheckorderShopname;
    /*商品图片*/
        ImageView ivItemCoImg;
    /*商品名称*/
        tvItemCoGoodsname.setText(checkOrder.getData().getGoods().getGoodsname());
    /*商品规格*/
//        tvItemCoOption;
    /*商品价格*/
        tvItemCoMarketprice.setText("￥" + Float.intBitsToFloat(checkOrder.getData().getMoney_all())/Float.intBitsToFloat(checkOrder.getData().getCount()));
    /*商品数量*/
        tvItemCoBuycount.setText("数量 x" + count);
    /*留言*/
        EditText ptEtCheckorderComment;
    /*共几件*/
        tvCheckorderNum.setText(checkOrder.getData().getCount()+"");
    /*商品总价*/
        ptTvCheckorderMoney.setText("￥" + checkOrder.getData().getMoney_all());
    /*运费*/
        ptTvCheckorderDispatch.setText("￥" + checkOrder.getData().getDispatch_all());
    /*会员优惠*/
        ptTvCoDiscountAll.setText("￥" + checkOrder.getData().getDiscount_all());
    /**/
//        ImageView ptIvYouhuiquanTight;
    /*商品小计价格*/
        ptTvCoPayAll.setText("￥" + checkOrder.getData().getMoney_all());
    }

    /**
     * 提交订单（立即购买）
     */
    public void orderBuynow() {
        String s = "";
        try {
            s = URLEncoder.encode(ptEtCheckorderComment.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
        ss = ss.replaceAll("[\\s*\t\n\r]", "");
//        MD5_PATH = "addrid=" + addrid + "&count=" + count + "&goodsid=" + cartids + "&optionid=" + optionid + "&phone=" + phone + "&remark=" + remark + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
//
//        PATH = HttpPath.PINTUAN_TUAN_ADDORDER + MD5_PATH + "&sign=" +
//                MD5Util.getMD5String("addrid=" + addrid + "&count=" + count + "&goodsid=" + cartids + "&optionid=" + optionid + "&phone=" + phone + "&remark=" + remarks + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpPath.KEY);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pid", pid);
        map.put("count", count);
        map.put("is_tuan", tag);//单独买0 拼团买1
        map.put("tuanid", tuanid);
        map.put("goodsid", goodsid);
        map.put("addrid", addrid);
        map.put("remark", ss);
        System.out.println("提交订单（立即购买） = " + map.toString());
        HttpxUtils.Post(this, HttpPath.PINTUAN_TUAN_ADDORDER, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("提交订单（立即购买） = " + result);
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    intent = new Intent(PTSubmitOrderActivity.this, PayActivity.class);
                    intent.putExtra("ordersn", addrReturn.getData().toString());
                    intent.putExtra("price", "" + pay_all);
                    intent.putExtra("phone", phone);
                    intent.putExtra("token", token);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);

                    setResult();
                    PTSubmitOrderActivity.this.finish();

                } else {
                    toast("" + addrReturn.getData());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("提交订单（立即购买） 失败= " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void setResult() {
        intent = new Intent();
        setResult(CodeUtils.CONFIRM_ORDER, intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.CONFIRM_ORDER) {
            if (resultCode == CodeUtils.ADDR_ADD || resultCode == CodeUtils.ADDR_LIST || resultCode == CodeUtils.ADDR_LISTS) {
                pay_all = 0.0;
                isLogin();
            } else if (resultCode == CodeUtils.PAY) {
                intent = new Intent();
                setResult(CodeUtils.CONFIRM_ORDER, intent);
                PTSubmitOrderActivity.this.finish();
            }
        }
    }
}
