package com.dq.huibao.ui.pintuan;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pintuan.PTTuanTuanChildListAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.bean.pintuan.PinTuanTuanDetail;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.InjoyActivity;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.ShowBigPictrueActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.goodsdetails_foot.GradationScrollView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 拼团-团详情页
 * Created by d on 2018/4/11.
 */

public class PinTuanTuanDetailsActivity extends BaseActivity implements GradationScrollView.ScrollViewListener {
    /*商品名称*/
    @Bind(R.id.pt_tv_name)
    TextView ptTvName;
    @Bind(R.id.pt_tv_content)
    TextView ptTvContent;
    //拼团价格
    @Bind(R.id.pintuan_tv_pintuan_price)
    TextView ptTvpintuanPrice;
    /*商品价格*/
    @Bind(R.id.pintuan_tv_marketprice)
    TextView pintuanTvMarketprice;
    /*商品库存*/
    @Bind(R.id.pintuan_tv_total)
    TextView pintuanTvTotal;
    /*拼团-团列表*/
    @Bind(R.id.pintuan_list)
    RecyclerView pinTuanListView;
    PTTuanTuanChildListAdapter pTkaiTuanAdapter;
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
    /*拼团买*/
    @Bind(R.id.pintuan_pintuan)
    Button pintuanPintuan;
    @Bind(R.id.item_pt_tuan_list_image)
    ImageView itemPtTuanListImage;
    @Bind(R.id.item_pt_tuan_list_name)
    TextView itemPtTuanListName;
    private WebSettings webSettings;
    /*选择数量和颜色时背景变暗*/
    @Bind(R.id.pintuan_choice_layout)
    LinearLayout pintuanAllChoice;
    private PinTuanTuanDetailsActivity TAG = PinTuanTuanDetailsActivity.this;

    CycleViewPager cycleViewPager;

    /*接收页面传值*/
    private Intent intent;
    private String gid = "", tuanId = "";
    /*pid-区别发起团和加入团:0:发起团，getpid:加入团*/
    private String pid = "0";

    /*图片*/
    public static List<String> picsList = new ArrayList<>();

    /*图文详情*/
    private String content = "";

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String token = "", phone = "", username = "", uid = "";

    private PinTuanTuanDetail tuanDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_tuan_details);
        ButterKnife.bind(this);
        setTitleName("拼团详情");

        pinTuanListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        intent = getIntent();
        gid = intent.getStringExtra("goodsid");
        tuanId = intent.getStringExtra("tuanid");
        pid = intent.getStringExtra("pid");

        spUserInfo = new SPUserInfo(getApplication());
        initDate();

    }

    @SuppressLint({"WrongConstant", "ResourceAsColor"})
    @OnClick({R.id.pintuan_pintuan, R.id.pintuan_lin_distribution,
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
                    intent.putExtra("sales", "" + tuanDetail.getData().getGoods().getSales());
                    intent.putExtra("thumb", "" + picsList.get(0).toString());
                    intent.putExtra("goodsname", tuanDetail.getData().getGoods().getGoodsname());
                    intent.putExtra("price", "" + tuanDetail.getData().getGoods().getMarketprice());
                    startActivityForResult(intent, CodeUtils.GDTAILD);
                } else {
                    dialog();
                }

                break;
            case R.id.pintuan_pintuan:
                if (tuanDetail.getData().getTuan().getStatus().equals("0")) {
                    setResult(RESULT_OK);
                }
                finish();
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

//                paramsList.clear();
//                paramsList.addAll(goodsDetail.getData().getParam());
//                gdParmasAdapter.notifyDataSetChanged();

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
     * 获取团详情
     */
    public void getGoodsDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("goodsid", gid);
        map.put("tuanid", tuanId);
        map.put("pid", pid);
        System.out.println("团详情 = " + map.toString());
        HttpxUtils.Get(this,HttpPath.PINTUAN_TUAN_DETAILS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("团详情 = " + result);
                tuanDetail = GsonUtil.gsonIntance().gsonToBean(result, PinTuanTuanDetail.class);
                if (!tuanDetail.getData().getTuan().getStatus().equals("0")) {
                    pintuanPintuan.setBackgroundColor(getResources().getColor(R.color.dark));
                }
                picsList.clear();
                picsList = tuanDetail.getData().getGoods().getThumb_url();


                content = tuanDetail.getData().getGoods().getContent();
                //
                //拼团列表
                if (!tuanDetail.getData().getMember().equals("[]")) {
                    pTkaiTuanAdapter = new PTTuanTuanChildListAdapter(PinTuanTuanDetailsActivity.this, tuanDetail.getData().getMember());
                    pinTuanListView.setAdapter(pTkaiTuanAdapter);

                }
                //
                setLunbotu();

                initData();

                getWebHTML(content);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("团详情 = " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /*组件赋值*/
    public void initData() {
        ptTvName.setText(tuanDetail.getData().getGoods().getGoodsname());
        ptTvContent.setText(tuanDetail.getData().getTuan().getTuanname());
        ptTvpintuanPrice.setText("拼团价 ￥" + tuanDetail.getData().getTuan().getTuanprice());
        pintuanTvMarketprice.setText("￥" + tuanDetail.getData().getTuan().getMarketprice());
        pintuanTvTotal.setText("库存：" + tuanDetail.getData().getGoods().getStock() + " 销量：" + tuanDetail.getData().getGoods().getSales());
        //团发起人信息
        Glide.with(this)
                .load(ImageUtils.getImagePath(tuanDetail.getData().getHead().getHeadimgurl()))
                .placeholder(R.mipmap.ic_header)
                .into(itemPtTuanListImage);
        itemPtTuanListName.setText(tuanDetail.getData().getHead().getNickname());
//        pintuanDanmai.setText("直接购买：￥" + danduPrice);
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
            info.setUrl(ImageUtils.getImagePath(picsList.get(i).toString()));
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
                        intent.putStringArrayListExtra("picsList", (ArrayList<String>) picsList);
                        startActivity(intent);

                    }

                }

            };

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

        webView.loadDataWithBaseURL(HttpPath.NEW_HEADER, html, "text/html", "utf-8", null);

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
}
