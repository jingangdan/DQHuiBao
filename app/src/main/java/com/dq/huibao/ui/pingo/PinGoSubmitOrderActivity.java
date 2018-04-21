package com.dq.huibao.ui.pingo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoQuYuAdapter;
import com.dq.huibao.adapter.pingo.PinGoSubmitOrderAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.cart.CheckOrder;
import com.dq.huibao.bean.pingo.PinGoCartList;
import com.dq.huibao.bean.pingo.PinGoOrderConfirm;
import com.dq.huibao.bean.pingo.PinGoiQuSelect;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.PayActivity;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddrListActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：拼Go确认提交
 * Created by jingang on 2017/10/30.
 */

public class PinGoSubmitOrderActivity extends BaseActivity {
    @Bind(R.id.rv_sibmitorder)
    RecyclerView rvSibmitorder;
    @Bind(R.id.lin_submitorder_address)
    LinearLayout linAddr;
    @Bind(R.id.tv_submitorder_address)
    TextView tvAddr;
    @Bind(R.id.tv_confirm_pay)
    TextView tvConfirmPay;
    @Bind(R.id.but_confirm_pay)
    Button butConfirmPay;

    private PinGoSubmitOrderAdapter submitOrderAdapter;
    private LinearLayoutManager mManager;
    private List<PinGoOrderConfirm.DataBean> shopList = new ArrayList<>();
    private String remark = "";

    /*接收页面传值*/
    private Intent intent;
    private String cartids = "", goodsid = "", count = "", optionid = "", tag = "";

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "",uid = "",regid = "";

    /*收货地址*/
    private List<Addr.DataBean> addrList = new ArrayList<>();
    private String regionid = "";//市级id（省市二级id）
    private String addrid = "";//收货地址id

    /*支付价格*/
    private double pay_all = 0.00;
    //支付方式：1：货到付款    2：在线支付
    private String paytype = "2";

    //区域数据
    PinGoiQuSelect pinGoiQuSelect = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitorder);
        ButterKnife.bind(this);

        intent = getIntent();
        cartids = intent.getStringExtra("cartids");
        goodsid = intent.getStringExtra("goodsid");
        count = intent.getStringExtra("count");
        optionid = intent.getStringExtra("optioned");
        tag = intent.getStringExtra("tag");

        mManager = new LinearLayoutManager(this);
        submitOrderAdapter = new PinGoSubmitOrderAdapter(this, shopList);

        rvSibmitorder.setLayoutManager(mManager);
        rvSibmitorder.setAdapter(submitOrderAdapter);
        isLogin();
        diquData();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("确认订单");
    }

    @OnClick({R.id.lin_submitorder_address, R.id.but_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_submitorder_address:
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

            case R.id.but_confirm_pay:
                if (pinGoiQuSelect == null){
                    toast("正在获取区域信息");
                    diquData();
                }else {
                    alertDialog.show();
                }
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

                            if (tag.equals("1")) {
                                getCheckorder(addrid, count);

                            } else if (tag.equals("0")) {
                                getCheckorderCart(cartids, addrid);
                            }


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
     * 提交订单前确认 (购物车)
     *
     * @param cartids 购物车id 集合 逗号隔开
     * @param addrid  配送地址的市级id
     */
    public void getCheckorderCart(String cartids, String addrid) {
        MD5_PATH = "?mid=" + uid + "&cartids=" + cartids;

        PATH = HttpPath.PINGO_CART_ISORDER + MD5_PATH;
        System.out.println("确认订单-购物车 = " + PATH);
        HttpxUtils.Post(this, PATH, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("确认订单-购物车 = " + result);
                PinGoOrderConfirm checkOrder = GsonUtil.gsonIntance().gsonToBean(result, PinGoOrderConfirm.class);

                shopList.clear();

                shopList.add(checkOrder.getData());

                submitOrderAdapter.notifyDataSetChanged();
                pay_all = checkOrder.getData().getAllprice();

                tvConfirmPay.setText("需付：¥" + pay_all);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("确认订单-购物车 = " + ex.toString());
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
     * @param addrid
     * @param count
     */
    public void getCheckorder(String addrid, String count) {
        Map<String,Object> map = new HashMap<>();
        map.put("mid",uid);
        map.put("goodsid",goodsid);
        map.put("optionid",optionid);
        map.put("count",count);
        System.out.println("确认订单（商品详情） = " + map.toString());
        HttpxUtils.Post(this, HttpPath.PINGO_GOOD_ISORDER, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("确认订单（商品详情） = " + result);
                try {
                    PinGoOrderConfirm checkOrder = GsonUtil.gsonIntance().gsonToBean(result, PinGoOrderConfirm.class);

                    shopList.clear();
                    shopList.add(checkOrder.getData());

                    submitOrderAdapter.notifyDataSetChanged();

                    pay_all = checkOrder.getData().getAllprice();

                    tvConfirmPay.setText("需付：¥" + pay_all);
                }catch (Exception e){
                    System.out.println("确认订单（商品详情） =失败 " + e.toString());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("确认订单（商品详情） =失败 " + ex.toString());
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
     * 提交订单(购物车)
     *
     * @param cartids 购物车id 集合 逗号隔开
     * @param addrid  收货地址的id
     * @param remark  备注[{shopid:remark}]备注
     */

    private String string_result = "";

    public void orderAdd(String cartids, String addrid, final String remark) {
        Map<String,Object> map = new HashMap<>();
        map.put("mid",uid);
        map.put("epxid",addrid);
        map.put("paytype",paytype);//1:货到付款，2：在线支付
        map.put("regid",regid);
        map.put("tips",remark);
        map.put("distype",shopList.get(0).getList().get(0).getDistype());
        map.put("cartids",cartids);
        System.out.println("提交订单-购物车 = " + map.toString());
        HttpxUtils.Post(this, HttpPath.PINGO_CART_SUBMITORDER, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("提交订单-购物车 = " + result);
                string_result = result;
                AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                if (addrReturn.getStatus() == 1) {
                    if (paytype.equals("1")){
                        //货到付款
                        toast("下单成功");
                    }else {
                        intent = new Intent(PinGoSubmitOrderActivity.this, PayActivity.class);
                        intent.putExtra("ordersn", addrReturn.getData().toString());
                        intent.putExtra("price", "" + pay_all);
                        intent.putExtra("phone", phone);
                        intent.putExtra("token", token);
                        startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                    }
                    setResult();
                    PinGoSubmitOrderActivity.this.finish();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("提交订单-购物车 =失败 " + ex.toString());
                if (!TextUtils.isEmpty(string_result)) {
                    AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(string_result, AddrReturn.class);
                    if (addrReturn.getStatus() == 1) {
                        toast("" + addrReturn.getData());
                    }
                }
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
     * 提交订单（立即购买）
     *
     * @param cartids
     * @param count
     * @param addrid
     * @param remark
     */
    public void orderBuynow(String cartids, String addrid, String count, String optionid, final String remark, String remarks) {
        Map<String,Object> map = new HashMap<>();
        map.put("mid",uid);
        map.put("epxid",addrid);
        map.put("paytype",paytype);//1:货到付款，2：在线支付
        map.put("regid",regid);
        map.put("tips",remark);
        map.put("distype",shopList.get(0).getList().get(0).getDistype());
        map.put("goodsid",cartids);
        map.put("optionid",optionid);
        map.put("count",count);
        map.put("allprice",pay_all);
        System.out.println("提交订单（立即购买） = " + map.toString());
        HttpxUtils.Post(this, HttpPath.PINGO_GOOD_SUBMITORDER, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("提交订单（立即购买） = " + result);
                try {
                    AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                    if (addrReturn.getStatus() == 1) {
                        if (paytype.equals("1")){
                            //货到付款
                            toast("下单成功");
                        }else {
                            intent = new Intent(PinGoSubmitOrderActivity.this, PayActivity.class);
                            intent.putExtra("ordersn", addrReturn.getData().toString());
                            intent.putExtra("price", "" + pay_all);
                            intent.putExtra("phone", phone);
                            intent.putExtra("token", token);
                            startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                        }
                        setResult();
                        PinGoSubmitOrderActivity.this.finish();

                    } else {
                        toast("" + addrReturn.getData());
                    }
                }catch (Exception ex){
                    System.out.println("提交订单（立即购买） =失败 " + ex.toString());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("提交订单（立即购买） =失败 " + ex.toString());
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
     * 提交订单
     */
    public void submitOrder(){
        alertDialog.dismiss();
        //提交订单
        pay_all = shopList.get(0).getAllprice();

        try {
            String s = URLEncoder.encode(shopList.get(0).getCommet().toString(), "UTF-8");
            String ss = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
            ss = ss.replaceAll("[\\s*\t\n\r]", "");

            if (tag.equals("1")) {
                orderBuynow(goodsid, addrid, count, optionid, URLEncoder.encode(shopList.get(0).getCommet(), "UTF-8"), shopList.get(0).getCommet());

            } else if (tag.equals("0")) {
                orderAdd(cartids, addrid, ss);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 地区
     */
    public void diquData(){
        HttpxUtils.Get(this,HttpPath.PINGO_REGION, null,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("地区地区 = " + result);
                        pinGoiQuSelect = GsonUtil.gsonIntance().gsonToBean(result, PinGoiQuSelect.class);
                        if (diQuAdapter != null){
                            diQuAdapter.notifyDataSetChanged();
                        }else {
                            createDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("地区地区= 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    PinGoQuYuAdapter diQuAdapter;
    AlertDialog alertDialog = null;
    View popview;
    public void createDialog(){
        popview = View.inflate(this, R.layout.pingo_pop_paytype,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popview);
        alertDialog = builder.create();
        RecyclerView recyclerView = popview.findViewById(R.id.pop_pingo_paytype_diqu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        diQuAdapter = new PinGoQuYuAdapter(this,pinGoiQuSelect.getData().getList());
        recyclerView.setAdapter(diQuAdapter);
        diQuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                toast(pinGoiQuSelect.getData().getList().get(position).getRegname());
                regid = pinGoiQuSelect.getData().getList().get(position).getId();
            }
        });
        //货到付款
        popview.findViewById(R.id.pop_pingo_paytype_hdfk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paytype = "1";
            }
        });
        //在线支付
        popview.findViewById(R.id.pop_pingo_paytype_zxzf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paytype = "2";
            }
        });
        //确认
        popview.findViewById(R.id.pop_pingo_paytype_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (regid.equals("")){
                    toast("请选择所在区域");
                }else {
                    submitOrder();
                }
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
                PinGoSubmitOrderActivity.this.finish();
            }
        }
    }
}
