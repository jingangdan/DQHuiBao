package com.dq.huibao.ui.jifen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoQuYuAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.jifen.JiFenGoodDetial;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.addr.AddAddressActivity;
import com.dq.huibao.ui.addr.AddrListActivity;
import com.dq.huibao.utils.AppUtil;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.CountDownUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 积分商品详情页
 * Created by d on 2018/4/20.
 */

public class JiFenGoodDetailActivity extends BaseActivity {


    JiFenGoodDetial.DataBean jiFenGoodDetial;

    @Bind(R.id.jifen_detail_header_image)
    ImageView jifenDetailHeaderImage;
    @Bind(R.id.jifen_detail_name)
    TextView jifenDetailName;
    @Bind(R.id.jifen_detail_time)
    TextView jifenDetailTime;
    @Bind(R.id.jifen_detail_price_and_score)
    TextView jifenDetailPriceAndScore;
    @Bind(R.id.jifen_detail_oldprice)
    TextView jifenDetailOldprice;
    @Bind(R.id.jifen_detail_yunfei)
    TextView jifenDetailYunfei;
    @Bind(R.id.jifen_detail_keduihuan)
    TextView jifenDetailKeduihuan;
    @Bind(R.id.jifen_detail_keduihuan_num)
    TextView jifenDetailKeduihuanNum;
    @Bind(R.id.jifen_detail_keduihuan_taday_num)
    TextView jifenDetailKeduihuanTadayNum;
    @Bind(R.id.jifen_detail_duihuan_shengyu)
    TextView jifenDetailDuihuanShengyu;
    @Bind(R.id.jifen_detail_duihuan_yidui)
    TextView jifenDetailDuihuanYidui;
    @Bind(R.id.jifen_detail_duihuan_all)
    TextView jifenDetailDuihuanAll;
    @Bind(R.id.jifen_detail_webview1)
    WebView webView1;
    @Bind(R.id.jifen_detail_webview2)
    WebView webView2;
    WebSettings webSettings;
    @Bind(R.id.jifen_detail_duihuan)
    Button jifenDetailDuihuan;
    private String goodid = "";

    Intent intent = new Intent();
    //
    private String phone = "", token = "", uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_detail);
        ButterKnife.bind(this);
        setTitleName("商品详情");
        goodid = getIntent().getStringExtra("goodid");
        phone = getIntent().getStringExtra("phone");
        token = getIntent().getStringExtra("token");
        uid = getIntent().getStringExtra("uid");

        createDialog();
        getAddr();
        getData();
    }

    /**
     * 获取积分商品详情
     */
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", goodid);
        map.put("mid", uid);
        HttpxUtils.Get(this, HttpPath.JIFEN_FULI_GOODDETAILS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取积分商品详情 = " + result);
                        jiFenGoodDetial = GsonUtil.gsonIntance().gsonToBean(result, JiFenGoodDetial.class).getData();
                        try {
                            updateUI();
                        } catch (Exception ex) {
                            System.out.println("获取积分商品详情 = 失败" + ex.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取积分商品详情 = 失败" + ex.getMessage());
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
    public void toSubmit() {
        Map<String, Object> map = new HashMap<>();
        map.put("gid", goodid);
        map.put("mid", uid);
        map.put("epxid", addrid);
        System.out.println("积分兑换提交 = " + map.toString());
        HttpxUtils.Post(this, HttpPath.JIFEN_SAVEORDER, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("积分兑换提交 = " + result);
                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        toast(aReturn.getData());
                        if (aReturn.getStatus() == 1) {
                            //刷新
                            getData();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("积分兑换提交 = 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /*收货地址*/
    private List<Addr.DataBean> addrList = new ArrayList<>();
    private String regionid = "";//市级id（省市二级id）
    private String addrid = "";//收货地址id

    /**
     * 获取收货地址
     */
    public void getAddr() {
        String MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        String PATH = HttpPath.MEMBER_GETADDR + MD5_PATH + "&sign=" +
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
                            addressTv.setText(addrList.get(i).getContact() + "(" + addrList.get(i).getMobile() + ")\n" +
                                    addrList.get(i).getProvince() + "." + addrList.get(i).getCity() + "." + addrList.get(i).getAddr());
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
    CountDownUtil countDownUtil;
    private void updateUI() {
        getWebHTML(webView1, jiFenGoodDetial.getIntro());
        getWebHTML(webView2, jiFenGoodDetial.getTips());

        ImageUtils.loadIntoUseFitWidths(JiFenGoodDetailActivity.this, jiFenGoodDetial.getThumb(), R.mipmap.icon_empty001, jifenDetailHeaderImage);

//        String start = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
//        String end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
//        jifenDetailTime.setText("0".equals(jiFenGoodDetial.getStarttime())?"":(start + "\n" + end));
        countDownUtil = new CountDownUtil(Long.parseLong(jiFenGoodDetial.getEndtime()) * 1000 - System.currentTimeMillis(), jifenDetailTime);
        countDownUtil.countdown();
        jifenDetailName.setText(jiFenGoodDetial.getGoodsname());
        jifenDetailPriceAndScore.setText("￥" + jiFenGoodDetial.getPrice() + "+" + jiFenGoodDetial.getScore() + "积分");
        jifenDetailOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        jifenDetailOldprice.setText("￥" + jiFenGoodDetial.getProductprice());
        jifenDetailYunfei.setText("运费:￥" + jiFenGoodDetial.getExpprice());
        jifenDetailKeduihuan.setText("1".equals(jiFenGoodDetial.getStatus()) ? "可兑换" : "不可兑换");
        jifenDetailKeduihuanTadayNum.setText("今日可兑换x" + jiFenGoodDetial.getOnedaycount());
        jifenDetailKeduihuanNum.setText("可兑换x" + jiFenGoodDetial.getOneallcount());
        jifenDetailDuihuanShengyu.setText("剩余x" + jiFenGoodDetial.getLastcount() + "");
        jifenDetailDuihuanYidui.setText("已兑换x" + jiFenGoodDetial.getSalecount());
        jifenDetailDuihuanAll.setText("总数量：" + jiFenGoodDetial.getAllcount());

        if (jiFenGoodDetial.getStatus().equals("0") || jiFenGoodDetial.getOnedaycount().equals("0")){
            jifenDetailDuihuan.setVisibility(View.GONE);
            jifenDetailKeduihuanTadayNum.setTextColor(getResources().getColor(R.color.red_normal));

            jifenDetailKeduihuanTadayNum.setText(jiFenGoodDetial.getMsg()
                    + AppUtil.getDateToString("MM-dd HH:mm:ss",Long.parseLong(jiFenGoodDetial.getStarttime()))
                    + AppUtil.getDateToString("MM-dd HH:mm:ss",Long.parseLong(jiFenGoodDetial.getEndtime())));

            countDownUtil.stopThread();
            jifenDetailTime.setText("00:00:00");
        }
    }

    PinGoQuYuAdapter diQuAdapter;
    AlertDialog alertDialog = null;
    View popview;
    TextView addressTv;

    public void createDialog() {
        popview = View.inflate(this, R.layout.alert_jifen_address,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popview);
        alertDialog = builder.create();
        addressTv = popview.findViewById(R.id.tv_jifen_submitorder_address);
        popview.findViewById(R.id.lin_jfien_submitorder_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (regionid.equals("")) {
                    //添加收货地址
                    intent = new Intent(JiFenGoodDetailActivity.this, AddAddressActivity.class);
                    intent.putExtra("tag", "0");
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                } else {
                    //选择收货地址
                    intent = new Intent(JiFenGoodDetailActivity.this, AddrListActivity.class);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                }
            }
        });
        popview.findViewById(R.id.btn_jifen_submitorder_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (addrList.size() != 0 && !jiFenGoodDetial.getOnedaycount().equals("0")){
                    toSubmit();
                }else {
                    toast("请添加收货地址");
                }
            }
        });
    }

    @OnClick({R.id.jifen_detail_duihuan,R.id.jifen_detail_duihuan_cant})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jifen_detail_duihuan:
                isLogin();
                break;
            case R.id.jifen_detail_duihuan_cant:
                toast(jiFenGoodDetial.getMsg());
                break;
        }
    }
    @SuppressLint("WrongConstant")
    public void isLogin() {
        SPUserInfo spUserInfo = new SPUserInfo(getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            uid = login.getData().getUid();
            if (addrList.size() == 0){
                getAddr();
            }
            alertDialog.show();
        } else {
            dialog();
        }

    }
    /*弹出框*/
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定登录？");
        builder.setTitle("提示：未登录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                intent = new Intent(JiFenGoodDetailActivity.this, LoginActivity.class);
                startActivityForResult(intent, CodeUtils.GDTAILD);


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }
    /**
     * 加载商品图文详情（html）
     *
     * @param html_bady
     */
    @SuppressLint("WrongConstant")
    public void getWebHTML(WebView webView, String html_bady) {
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(false);

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        String html = "<html>" + head + "<body>" + html_bady + "</body></html>";

        webView.loadDataWithBaseURL(HttpPath.NEW_HEADER, html, "text/html", "utf-8", null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView1.setWebChromeClient(null);
        webView1.setWebViewClient(null);
        webView1.getSettings().setJavaScriptEnabled(false);
        webView1.clearCache(true);
        webView2.setWebChromeClient(null);
        webView2.setWebViewClient(null);
        webView2.getSettings().setJavaScriptEnabled(false);
        webView2.clearCache(true);
        countDownUtil.stopThread();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.CONFIRM_ORDER) {
            if (resultCode == CodeUtils.ADDR_ADD || resultCode == CodeUtils.ADDR_LIST || resultCode == CodeUtils.ADDR_LISTS) {
                getAddr();
            }
        }
    }
}
