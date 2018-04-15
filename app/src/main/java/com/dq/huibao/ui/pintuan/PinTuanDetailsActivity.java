package com.dq.huibao.ui.pintuan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pintuan.PTChooseAdapter;
import com.dq.huibao.adapter.pintuan.PTParmasAdapter;
import com.dq.huibao.adapter.pintuan.PTkaiTuanAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.PinTuanDetails;
import com.dq.huibao.bean.account.Account;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.InjoyActivity;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.ShowBigPictrueActivity;
import com.dq.huibao.ui.SubmitOrderActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ShowUtils;
import com.dq.huibao.view.goodsdetails_foot.GradationScrollView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 拼团详情页
 * Created by d on 2018/4/11.
 */

public class PinTuanDetailsActivity extends BaseActivity implements GradationScrollView.ScrollViewListener {
    /*商品名称*/
    @Bind(R.id.pt_tv_name)
    TextView ptTvName;
    @Bind(R.id.pt_tv_content)
    TextView ptTvContent;
    /*商品价格*/
    @Bind(R.id.pintuan_tv_marketprice)
    TextView pintuanTvMarketprice;
    /*商品库存*/
    @Bind(R.id.pintuan_tv_total)
    TextView pintuanTvTotal;
    /*拼团-团列表*/
    @Bind(R.id.pintuan_list)
    ListView pinTuanListView;
    PTkaiTuanAdapter pTkaiTuanAdapter;
    /**/
    @Bind(R.id.pintuan_scrollview)
    GradationScrollView scrollView;
    /*图文详情——tv*/
    @Bind(R.id.pintuan_tv_content)
    TextView pintuanTvContent;
    @Bind(R.id.pt_view_content)
    View vGdContent;
    @Bind(R.id.pintuan_lin_content)
    LinearLayout pintuanLinContent;
    /*参数*/
    @Bind(R.id.pintuan_tv_params)
    TextView pintuanTvParams;
    @Bind(R.id.pt_view_params)
    View vGdParams;
    @Bind(R.id.pintuan_lin_params)
    LinearLayout pintuanLinParams;
    @Bind(R.id.pintuan_rv_params)
    RecyclerView pintuanRvParams;
    /*webview*/
    @Bind(R.id.pintuan_wv_goodsdetail)
    WebView webView;
    /*单独买*/
    @Bind(R.id.pintuan_danmai)
    Button pintuanDanmai;
    /*拼团买*/
    @Bind(R.id.pintuan_pintuan)
    Button pintuanPintuan;
    private WebSettings webSettings;

    @Bind(R.id.pintuan_isNoPin_layout)
    LinearLayout pintuanIsNoPinLayout;
    /*选择数量和颜色时背景变暗*/
    @Bind(R.id.pintuan_choice_layout)
    LinearLayout pintuanAllChoice;
    /*所选规格-展示*/
    @Bind(R.id.pt_tv_specification)
    TextView pt_tv_specification;
    private PinTuanDetailsActivity TAG = PinTuanDetailsActivity.this;

    CycleViewPager cycleViewPager;

    /*该商品是否提供规格选择*/
    private boolean isSpecs = true;
    /*接收页面传值*/
    private Intent intent;
    private String gid = "", tuanId = "";
    /*pid-区别发起团和加入团:0:发起团，getpid:加入团*/
    private String pid = "0";
    /*购买方式：0：直接买，1：拼团*/
    private String orderType = "0";

    /*图片*/
    public static List<String> picsList = new ArrayList<>();

    /*UI赋值*/
    private String title = "", marketprice = "", total = "", sales = "";
    /*单独买价格和拼团价格*/
    private String danduPrice = "", pintuanPrice = "";

    /*图文详情*/
    private String content = "";
    /*产品参数*/
    private PTParmasAdapter gdParmasAdapter;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String token = "", phone = "", username = "",uid = "";

    private PinTuanDetails goodsDetail;
    /*选择规格*/
    private List<PinTuanDetails.DataBean.SpecBean> specsList = new ArrayList<>();
    /*上传规格*/
    private List<PinTuanDetails.DataBean.OptionBean> optionsList = new ArrayList<>();
    /*参数*/
    private List<PinTuanDetails.DataBean.ParamBean> paramsList = new ArrayList<>();

    /*购物车数据*/
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_details);
        ButterKnife.bind(this);
        setTitleName("拼团");

        gdParmasAdapter = new PTParmasAdapter(TAG, paramsList);
        pintuanRvParams.setLayoutManager(new LinearLayoutManager(TAG));
        pintuanRvParams.setAdapter(gdParmasAdapter);

        intent = getIntent();
        gid = intent.getStringExtra("gid");
        tuanId = intent.getStringExtra("tuanId");

        spUserInfo = new SPUserInfo(getApplication());
        initDate();

    }

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @OnClick({R.id.pt_rel_gd_choose,R.id.pintuan_danmai, R.id.pintuan_pintuan,
            R.id.pintuan_no_btn, R.id.pintuan_lin_distribution,
            R.id.pintuan_tv_content, R.id.pintuan_tv_params})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pintuan_lin_distribution:
                //我要推广
                if (isLogin()) {
                    //Toast.makeText(TAG, "我要推广", Toast.LENGTH_SHORT).show();
                    intent = new Intent(TAG, InjoyActivity.class);
                    intent.putExtra("gid", gid);
                    intent.putExtra("username", "" + username);
                    intent.putExtra("phone", phone);
                    intent.putExtra("token", token);
                    intent.putExtra("sales", "" + goodsDetail.getData().getSales());
                    intent.putExtra("thumb", "" + picsList.get(0).toString());
                    intent.putExtra("goodsname", goodsDetail.getData().getGoodsname());
                    intent.putExtra("price", "" + goodsDetail.getData().getMarketprice());
                    startActivityForResult(intent, CodeUtils.GDTAILD);
                } else {
                    dialog();
                }

                break;
            case R.id.pt_rel_gd_choose://选择 规格
                //选择商品规格和数量
                setPopTest(true,orderType.equals("1"));
                setBackgroundBlack(pintuanAllChoice, 0);
                break;
            case R.id.pintuan_danmai:
                //单独购买
                pid = "0";
                orderType = "0";
                if (isLogin()) {
                    if (optionid.equals("")) {
                        setPopTest(false,false);
                        setBackgroundBlack(pintuanAllChoice, 0);
                    } else {
                        toSubmitActivity();
                    }

                } else {
                    dialog();
                }

                break;
            case R.id.pintuan_pintuan:
                //拼团购买
                //价格需要更改
                pid = "0";
                orderType = "1";
                if (isLogin()) {
                    if (optionid.equals("")) {
                        setPopTest(false,true);
                        setBackgroundBlack(pintuanAllChoice, 0);
                    } else {
                        //需要验证是否还可以参团
                        verifyTuan();
                    }

                } else {
                    dialog();
                }

                break;
            case R.id.pintuan_no_btn:
                //拼团已满或者失效，查看更多拼团产品

                break;
            case R.id.pintuan_tv_content:
                //图文推荐
                setTabChoose();
                pintuanTvContent.setTextColor(Color.rgb(241, 83, 83));
                vGdContent.setVisibility(View.VISIBLE);
                pintuanLinContent.setVisibility(View.VISIBLE);

                getWebHTML(content);

                break;

            case R.id.pintuan_tv_params:
                //产品参数
                setTabChoose();

                pintuanTvParams.setTextColor(Color.rgb(241, 83, 83));
                vGdParams.setVisibility(View.VISIBLE);
                pintuanLinParams.setVisibility(View.VISIBLE);

                paramsList.clear();
                paramsList.addAll(goodsDetail.getData().getParam());
                gdParmasAdapter.notifyDataSetChanged();


                break;
        }
    }

    /*
    * 获取用户登录信息
    * */
    @SuppressLint("WrongConstant")
    public void initDate() {
        if (!(spUserInfo.getLoginReturn().equals(""))) {
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();
            uid = login.getData().getUid();
            username = login.getData().getNickname();
            getGoodsDetail();

        } else {
            //toast("登录状态出错，请重新登录");
            getGoodsDetail();
        }

    }

    /*判断登录状态*/
    public boolean isLogin() {
        if (spUserInfo.getLogin().equals("1")) {
            return true;
        } else if (spUserInfo.getLogin().equals("")) {
            return false;
        }
        return false;
    }

    /**
     * 获取商品详情
     */
    public void getGoodsDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("goodsid", gid);
        map.put("tuanid", tuanId);
        Log.d("fffffffffffff", "拼团商品详情=" + map.toString());
        HttpxUtils.Get(this, HttpPath.PINTUAN_DETAILS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("拼团商品详情 = " + result);
                goodsDetail = GsonUtil.gsonIntance().gsonToBean(result, PinTuanDetails.class);
                isSpecs = (goodsDetail.getData().getHasoption() == 1);
                if (goodsDetail.getData().getFooter_status() == 0) {
                    pintuanIsNoPinLayout.setVisibility(View.VISIBLE);
                }else {
                    pintuanIsNoPinLayout.setVisibility(View.GONE);
                }

                specsList.clear();
                optionsList.clear();
                picsList.clear();
                if (!goodsDetail.getData().getSpec().toString().equals("[]")) {
                    specsList = goodsDetail.getData().getSpec();
                }
                if (!goodsDetail.getData().getOption().toString().equals("[]")) {
                    optionsList = goodsDetail.getData().getOption();
                }
                picsList = goodsDetail.getData().getThumb_url();


                title = goodsDetail.getData().getGoodsname();
                marketprice = "" + goodsDetail.getData().getMarketprice();
                total = "" + goodsDetail.getData().getStock();
                sales = "" + goodsDetail.getData().getSales();
                content = goodsDetail.getData().getContent();
                //
                danduPrice = goodsDetail.getData().getMarketprice();
                pintuanPrice = goodsDetail.getData().getTuanprice();
                //
                if (!goodsDetail.getList().equals("[]")){
                    pTkaiTuanAdapter = new PTkaiTuanAdapter(PinTuanDetailsActivity.this,goodsDetail.getList());
                    pinTuanListView.setAdapter(pTkaiTuanAdapter);
                    pinTuanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            orderType = "1";
                            pid = goodsDetail.getList().get(position).getPid();
                            //验证
                            verifyTuan();
                        }
                    });
                }
                //
                setLunbotu();

                initData();

                getWebHTML(content);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("拼团商品详情失败 = " + ex.toString());
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
     * 拼团验证：包括发起和加入
     */
    public void verifyTuan(){
        Map<String, String> map = new HashMap<>();
        map.put("goodsid", gid);
        map.put("tuanid", tuanId);
        map.put("pid", pid);
        map.put("uid", uid);
        HttpxUtils.Get(this, HttpPath.PINTUAN_VERIFY, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("参团验证 = " + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e("fffffff参团验证",jsonObject.getInt("status")+"");
                    if (jsonObject.getInt("status") == 1){
                        toSubmitActivity();
                    }else {
                        toast("无法参团");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("参团验证失败 = " + ex.toString());
                toast("无法参团");
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
     *  进入订单确认页
     */
    public void toSubmitActivity(){
        intent = new Intent(TAG, PTSubmitOrderActivity.class);
        intent.putExtra("goodsid", gid);
        intent.putExtra("tag", orderType);
        intent.putExtra("tuanid", tuanId);
        intent.putExtra("count", num + "");
        intent.putExtra("optioned", optionid);
        intent.putExtra("optionSpecs", optionSpecs);
        intent.putExtra("pid", pid);
        startActivity(intent);
    }

    /*组件赋值*/
    public void initData() {
        ptTvName.setText("" + title);
        ptTvContent.setText("" + title);
        pintuanTvMarketprice.setText("市场价 ¥ " + marketprice);
        pintuanTvTotal.setText("库存：" + total + " 销量：" + sales);
        pintuanDanmai.setText("直接购买：￥" + danduPrice);
        pintuanPintuan.setText("拼团：￥" + pintuanPrice);
    }

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;

    /**
     * 设置轮播图
     */
    public void setLunbotu() {
        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.pintuan_cycleviewpager);

        infos = new ArrayList<>();
        for (int i = 0; i < picsList.size(); i++) {
            info = new ADInfo();
            info.setUrl(HttpPath.IMG_HEADER + picsList.get(i).toString());
            info.setContent("");
            info.setImg("");
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(TAG, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(TAG, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(TAG, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    int index = position - 1;
                    if (cycleViewPager.isCycle()) {
                        intent = new Intent(TAG, ShowBigPictrueActivity.class);
                        intent.putExtra("position", index);
                        intent.putExtra("picslist", picsList.toString());
                        startActivity(intent);

                    }

                }

            };


    /*popupwindows*/
    private PopupWindow popWindow;
    private View view;
    private ImageView iv_cancel, iv_thumb;
    private LinearLayout linGdMain, linAdd, linSub;
    private TextView tv_marketprice, tv_total, tv_specification, tv_num, tv_ok;

    /*动态添加UI*/
    private RecyclerView recyclerView;

    private SpecAdapter specAdapter;
    /*数量*/
    private int num = 1;

    /**
     *
     * 选择商品规格和数量
     * @param isOnlySel:是否是只选择规格，不购买
     * @param isPT:是否是拼团购买
     */
    @SuppressLint("WrongConstant")
    public void setPopTest(final boolean isOnlySel, final boolean isPT) {

        num = 1;
        view = View.inflate(this, R.layout.pop_gd_choose,
                null);
        // 最后一个参数false 代表：不与其余布局发生交互， true代表：可以与其余布局发生交互事件
        popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                false) {

            // 重写popupWindow消失时事件
            @Override
            public void dismiss() {
                super.dismiss();
                setBackgroundBlack(pintuanAllChoice, 1);

            }
        };
        // 设置Pop入场动画效果
        popWindow.setAnimationStyle(R.style.pop_style);
        // 设置Pop响应内部区域焦点
        popWindow.setFocusable(true);
        // 设置Pop响应外部区域焦点
        popWindow.setOutsideTouchable(true);
        // 设置PopupWindow弹出软键盘模式（此处为覆盖式）
        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // 响应返回键必须的语句
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 依附的父布局自己设定，我这里为了方便，这样写的。
        popWindow.showAtLocation(pintuanAllChoice, Gravity.BOTTOM, 0, 0);

        linGdMain = (LinearLayout) view.findViewById(R.id.lin_pop_gd_main);

        /*图片 取消*/
        iv_cancel = (ImageView) view.findViewById(R.id.iv_pop_gd_back);
        iv_thumb = (ImageView) view.findViewById(R.id.iv_pop_gd_thumb);

        recyclerView = view.findViewById(R.id.rv_goods_spec);
        specAdapter = new PinTuanDetailsActivity.SpecAdapter(this, specsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(specAdapter);

        Glide.with(TAG)
                .load(HttpPath.IMG_HEADER + goodsDetail.getData().getThumb())
                .placeholder(R.mipmap.icon_empty002)
                .error(R.mipmap.icon_error002)
                .into(iv_thumb);

        /*价格 库存 规格 数量 确定*/
        tv_marketprice = (TextView) view.findViewById(R.id.tv_pop_gd_marketprice);
        tv_total = (TextView) view.findViewById(R.id.tv_pop_gd_total);
        tv_specification = (TextView) view.findViewById(R.id.tv_pop_gd_specification);
        tv_num = (TextView) view.findViewById(R.id.tv_pop_gd_num);
        tv_ok = (TextView) view.findViewById(R.id.tv_pop_gd_ok);

        /*加减*/
        linAdd = (LinearLayout) view.findViewById(R.id.lin_pop_gd_add);
        linSub = (LinearLayout) view.findViewById(R.id.lin_pop_gd_sub);

        tv_marketprice.setText("￥" + goodsDetail.getData().getMarketprice());
        tv_total.setText("库存：" + goodsDetail.getData().getStock());

        pt_tv_specification.setText(optionTitle);
        /*取消*/
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        /*增加*/
        linAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(tv_num.getText().toString());
                num++;
                tv_num.setText("" + num);
            }
        });

        /*减少*/
        linSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(tv_num.getText().toString());
                if (num > 1) {
                    num--;
                    tv_num.setText("" + num);
                } else {
                    Toast.makeText(TAG, "数量需大于0", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*确定*/
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnlySel){
                    popWindow.dismiss();
                    return;
                }
                if (isSpecs && optionsList.size() > 0) {
                    if (!optionid.equals("")) {
                        //拼团购买，需要验证是否还可以加入
                        if (isPT){
                            verifyTuan();
                        }else {//直接购买
                            toSubmitActivity();
                        }
                        popWindow.dismiss();
                    } else {
                        Toast.makeText(TAG, "未选规格", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //拼团购买，需要验证是否还可以加入
                    if (isPT){
                        verifyTuan();
                    }else {//直接购买
                        toSubmitActivity();
                    }
                    popWindow.dismiss();
                }
                popWindow.dismiss();
            }
        });

    }


    /**
     * 加载商品图文详情（html）
     *
     * @param html_bady
     */
    @SuppressLint("WrongConstant")
    public void getWebHTML(String html_bady) {
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

        webView.loadDataWithBaseURL(HttpPath.IMG_HEADER, html, "text/html", "utf-8", null);

    }

    /**
     * 是否被顶号
     *
     * @param result
     */
    public void setLoginAgain(String result) {
        if (!TextUtils.isEmpty(result)) {
            Account account = GsonUtil.gsonIntance().gsonToBean(result, Account.class);
            if (account.getData().equals("用户验证错误")) {
                ShowUtils.showDialog(TAG, "提示：用户验证错误", "此账号长时间未登录或在别处已登录，是否重新登录？", new ShowUtils.OnDialogListener() {
                    @Override
                    public void confirm() {
                        intent = new Intent(TAG, LoginActivity.class);
                        startActivityForResult(intent, CodeUtils.GDTAILD);
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        }
    }

    /**
     * 设置文字样式
     */
    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    public void setTabChoose() {
        pintuanTvContent.setTextColor(Color.rgb(102, 102, 102));
        pintuanTvParams.setTextColor(Color.rgb(102, 102, 102));

        vGdContent.setVisibility(View.INVISIBLE);
        vGdParams.setVisibility(View.INVISIBLE);

        pintuanLinContent.setVisibility(View.GONE);
        pintuanLinParams.setVisibility(View.GONE);
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

    }

    /**
     * 控制背景变暗 0变暗 1变亮
     */
    @SuppressLint("WrongConstant")
    public void setBackgroundBlack(View view, int what) {
        switch (what) {
            case 0:
                pintuanAllChoice.setVisibility(View.VISIBLE);
                break;
            case 1:
                pintuanAllChoice.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();

        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);

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
                intent = new Intent(TAG, LoginActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.GDTAILD) {
            if (resultCode == CodeUtils.LOGIN) {
                initDate();
            }
        }
    }

    String[] strings;
    String string = "";
    String string_name = "";
    String price = "", stock = "";
    String optionid = "";
    String optionTitle = "";
    String optionSpecs = "";

    public class SpecAdapter extends RecyclerView.Adapter<PinTuanDetailsActivity.SpecAdapter.MyViewHolder> {
        private Context mContext;
        private List<PinTuanDetails.DataBean.SpecBean> specBeanList;
        private OnItemClickListener onItemClickListener;

        public SpecAdapter(Context mContext, List<PinTuanDetails.DataBean.SpecBean> specBeanList) {
            this.mContext = mContext;
            this.specBeanList = specBeanList;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public PinTuanDetailsActivity.SpecAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            PinTuanDetailsActivity.SpecAdapter.MyViewHolder vh = new PinTuanDetailsActivity.SpecAdapter.MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_spec, parent, false));
            return vh;
        }

        @Override
        public void onBindViewHolder(final PinTuanDetailsActivity.SpecAdapter.MyViewHolder holder, final int i) {
            if (onItemClickListener != null) {
                //
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        onItemClickListener.onItemClick(holder.itemView, position); // 2
                    }
                });

            }
            holder.title.setText("" + specBeanList.get(i).getTitle());

            final PTChooseAdapter chooseAdapter = new PTChooseAdapter(mContext, specBeanList.get(i).getItems());
            holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(chooseAdapter);

            strings = new String[specBeanList.size()];

            chooseAdapter.setmOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    chooseAdapter.changeSelected(position);

                    if (specBeanList.get(i).getItems().get(position).getThumb().equals("")) {

                    } else {
                        Glide.with(mContext)
                                .load(HttpPath.IMG_HEADER + specBeanList.get(i).getItems().get(position).getThumb())
                                .placeholder(R.mipmap.icon_empty002)
                                .error(R.mipmap.icon_error002)
                                .into(iv_thumb);
                    }
                    String itemid = specBeanList.get(i).getItems().get(position).getId();

                    strings[i] = itemid;

                    for (int j = 0; j < strings.length; j++) {
                        if (j == 0) {
                            string = strings[j];
                        } else {
                            string = string + "_" + strings[j];
                        }
                    }
                    for (int k = 0; k < optionsList.size(); k++) {
                        if (string.equals(optionsList.get(k).getSpecs())) {
                            optionid = optionsList.get(k).getId();
                            optionTitle = optionsList.get(k).getTitle();
                            optionSpecs = optionsList.get(k).getSpecs();
                            string_name = optionsList.get(k).getTitle();
                            price = optionsList.get(k).getMarketprice();
                            stock = optionsList.get(k).getStock();
                            tv_specification.setText("已选：" + string_name);
                            tv_marketprice.setText("¥" + price);
                            tv_total.setText("库存：" + stock);
                        }
                    }
                    System.out.println("string = " + string);

                }
            });


        }

        @Override
        public int getItemCount() {
            return specBeanList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            private TextView title;
            private RecyclerView recyclerView;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.tv_spec_title);
                recyclerView = view.findViewById(R.id.rv_spec);

            }
        }
    }
}
