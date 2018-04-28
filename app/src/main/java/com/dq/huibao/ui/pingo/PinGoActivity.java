package com.dq.huibao.ui.pingo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.HomePageInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoHomeAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.homepage.IndexMoreGoods;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.pingo.PinGoCenterTuan;
import com.dq.huibao.bean.pingo.PinGoIndex;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.refresh.PullToRefreshView;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.KeywordsActivity;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.pintuan.PinTuanActivity;
import com.dq.huibao.ui.homepage.WebActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.CustomProgress;
import com.dq.huibao.view.TopicScrollView;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 同学拼go首页
 * Created by jingang on 2018/1/29.
 */

public class PinGoActivity extends BaseActivity implements
        HomePageInterface,
        PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener {

    @Bind(R.id.college_go_listview)
    RecyclerView recyclerView;

    @Bind(R.id.college_go_hp)
    PullToRefreshView pullToRefreshView;

    /*无网络或网络不佳*/
    @Bind(R.id.lin_hp_nonetwork)
    LinearLayout linHpNonetwork;
    @Bind(R.id.college_go_network)
    LinearLayout linHpNetwork;
    @Bind(R.id.but_refresh)
    Button butRefresh;
    @Bind(R.id.college_go_topicScrollView)
    TopicScrollView topicScrollView;
    @Bind(R.id.search_layout)
    LinearLayout searchLayout;
    private View view;
    private CustomProgress progressDialog = null;

    /*接口地址*/
    private String PATH = "", MD5_PATH = "";

    private PinGoHomeAdapter collegeGoHomeAdapter;
    List<PinGoIndex.DataBean> dataList = new ArrayList<>();

    /**/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    /*签到信息*/
    private TextView tv_sign, tv_sign_rule, tv_sign_days;
    private Boolean cansign = false;
    private String cur_count = "", cur_money = "";
    //
    private int page = 1,pagesize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_go);
        ButterKnife.bind(this, this);

        // startProgressDialog();
        setTitleName("同学拼go");
        getLogin();

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());

        pullToRefreshView.setOnScrollChanged(new PullToRefreshView.OnScrollChanged() {
            @Override
            public void onScroll(MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        searchLayout.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        searchLayout.setVisibility(View.VISIBLE);
                        break;
                }

            }
        });
        topicScrollView.setOnScrollChanged(new TopicScrollView.OnScrollChanged() {
            @Override
            public void onScroll(int t) {
                if (t < 30){
                    searchLayout.setBackgroundColor(Color.argb(0,0,0,0));
                }else {
                    searchLayout.setBackgroundColor(getResources().getColor(R.color.bg_white));
                }
            }
        });
        //
    }

    /*获取登录返回的数据*/
    public void getLogin() {
        if (isLogin()) {
            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                getIndex(phone, token);
            }

        } else {
            getIndex(phone, token);
        }
    }

    /*登录状态*/
    public boolean isLogin() {
        spUserInfo = new SPUserInfo(getApplication());
        if (spUserInfo.getLogin().equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 搜索栏的所有点击事件
     * @param v
     */
    @OnClick({R.id.homepage_location,R.id.et_hp_search,R.id.homepage_scan,R.id.homepage_message})
    public void onSearchClick(View v){
        switch (v.getId()){
            case R.id.homepage_location://定位
                break;
            case R.id.et_hp_search://搜索
                //搜索
                intent = new Intent(PinGoActivity.this, KeywordsActivity.class);
                intent.putExtra("searchType",1);
                startActivity(intent);
                break;
            case R.id.homepage_scan://扫描二维码
                break;
            case R.id.homepage_message://消息
                break;
        }
    }

    /**
     * 获取拼go首页信息
     * <p>
     * -url链接
     * article文章
     * cate分类
     * goods商品
     * custom自定义分类
     * articlecate文章分类
     * search  搜索
     * url # 不做操作
     */
    public void getIndex(String phone, String token) {
        searchLayout.setVisibility(View.GONE);
        PATH = HttpPath.PINGO_INFEX;
        System.out.println("拼go首页 = " + PATH);
        HttpxUtils.Get(this, PATH, null, new Callback.CommonCallback<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String result) {
                System.out.println("拼go首页 = " + result);
                //刷新完成，显示搜索框
                searchLayout.setVisibility(View.VISIBLE);
                // stopProgressDialog();
                linHpNetwork.setVisibility(View.VISIBLE);
                linHpNonetwork.setVisibility(View.GONE);
                PinGoIndex index = GsonUtil.gsonIntance().gsonToBean(result, PinGoIndex.class);
                dataList.clear();
                dataList.addAll(index.getData());

                recyclerView.setLayoutManager(new LinearLayoutManager(PinGoActivity.this));
                collegeGoHomeAdapter = new PinGoHomeAdapter(PinGoActivity.this, dataList);
                recyclerView.setAdapter(collegeGoHomeAdapter);

                collegeGoHomeAdapter.setHpInterface(PinGoActivity.this);


                //获取中间拼团信息
                getCenterTuan();
                //刷新并获取底部更多商品
                collegeGoHomeAdapter.refreshMoreGoods();
                getMoreGoods();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //stopProgressDialog();
                linHpNetwork.setVisibility(View.GONE);
                linHpNonetwork.setVisibility(View.VISIBLE);
                //toast(ex.getMessage());
                if (ex instanceof HttpException) {
                    //网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    toast("" + responseMsg);
                } else {
                    //其他错误
                    toast("网络不佳，请重试");
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
     * 获取拼go中间拼团信息
     */
    public void getCenterTuan() {
        PATH = HttpPath.PINGO_CENTER_TUAN;
        System.out.println("拼go中间拼团信息 = " + PATH);
        HttpxUtils.Get(this, PATH, null, new Callback.CommonCallback<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String result) {
                System.out.println("拼go中间拼团信息 = " + result);
                PinGoCenterTuan centerTuan = GsonUtil.gsonIntance().gsonToBean(result, PinGoCenterTuan.class);
                //刷新数据
                collegeGoHomeAdapter.refreshPinGo(centerTuan.getData());

            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println("拼go中间拼团信息 =失败 " + ex.toString());
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
     * 获取底部更多商品
     */
    public void getMoreGoods(){
        Map<String,String> map = new HashMap<>();
        map.put("page",page + "");
        map.put("pagesize",pagesize + "");
        map.put("isindex", "1");
        HttpxUtils.Get(this,HttpPath.PINGO_MORE_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取首页底部更多商品信息 = " + result);
                        if (page > 1)
                            pullToRefreshView.onFooterRefreshComplete();//加载更多数据

                        PinGoIndexMoreGoods indexMoreGoods = GsonUtil.gsonIntance().gsonToBean(result, PinGoIndexMoreGoods.class);
                        collegeGoHomeAdapter.setMoreGoods(indexMoreGoods.getData().getList());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取首页底部更多商品信息 = 失败" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    @Override
    public void doHomePage(int position, String title, String type, String content) {
        if (content.equals("#")) return;
        switch (type) {
            case "url":
                //拼团
                if ("pintuan".equals(content)){
                    intent = new Intent(this, PinTuanActivity.class);
                    startActivity(intent);
                    return;
                }
                //链接 web
                intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", content);
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            case "article":
                //文章
                break;
            case "cate":
                //分类（商品列表）
                intent = new Intent(this, GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            case "goods":
                //商品详情
                intent = new Intent(this, PinGoDetailsActivity.class);
                intent.putExtra("gid", content);
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            case "goodsList":
                //商品列表
                intent = new Intent(this, PinGoGoodsActivity.class);
                intent.putExtra("goodsType", content);
                intent.putExtra("isms", "0");
                intent.putExtra("istm", "0");
                intent.putExtra("title", "拼go");
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            case "custom":
                //自定义分类
                intent = new Intent(this, GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            case "articlecate":
                //文章分类
                break;
            case "search":
                //搜索
                break;
            case "#":
                //不做操作
                break;

            case "action":
                if (content.equals("sign")) {
                    if (isLogin()) {
                        if (!(spUserInfo.getLoginReturn().equals(""))) {

                           } else {
                            dialog();
                        }
                    } else {
                        /*弹出框*/
                        dialog();
                    }
                }

                break;
            case "other":

                //商品列表
                intent = new Intent(this, PinGoGoodsActivity.class);
                intent.putExtra("isms","0");
                intent.putExtra("istm","0");
                intent.putExtra("title",content);
                if ("立减金额".equals(content)){
                    content = "jian";
                }else if ("立打折扣".equals(content)){
                    content = "zhe";
                }else if ("当季特卖".equals(content)){
                    intent.putExtra("istm","1");
                    content = "jian";
                }else if ("周五秒杀".equals(content)){
                    intent.putExtra("isms","1");
                    content = "jian";
                }else if ("论坛".equals(content)){
//                    content = "jian";
                    toast("开发中");
                    return;
                }

                intent.putExtra("goodsType", content);
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);
                break;
            default:
                break;
        }
    }

    /**/
    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定登录？");
        builder.setTitle("提示：未登录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                intent = new Intent(PinGoActivity.this, LoginActivity.class);
                startActivityForResult(intent, CodeUtils.PINGO_HOMEPAGE);


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

    /*开始dialog*/
//    private void startProgressDialog() {
//        if (progressDialog == null) {
//            progressDialog = CustomProgress.createDialog(getActivity());
//            progressDialog.setMessage("请稍候...");
//        }
//        progressDialog.show();
//    }

    /*结束dialog*/
//    private void stopProgressDialog() {
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        page++;
        getMoreGoods();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        searchLayout.setVisibility(View.GONE);
        page = 1;
        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新完成，显示搜索框
                searchLayout.setVisibility(View.VISIBLE);
                // startProgressDialog();
                //刷新数据
                pullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + Calendar.getInstance().getTime().toLocaleString());
                pullToRefreshView.onHeaderRefreshComplete();


                getLogin();

            }

        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataList.clear();
//        collegeGoHomeAdapter = null;
    }
}