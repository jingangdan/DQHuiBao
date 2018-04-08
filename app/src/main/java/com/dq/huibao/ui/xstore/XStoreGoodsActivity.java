package com.dq.huibao.ui.xstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.xstore.XStoreTypeAdapter;
import com.dq.huibao.adapter.xstore.XStoreZXGoodsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.xstore.XStoreZXGoods;
import com.dq.huibao.bean.xstore.XstoreGoodsType;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 小店商品选择
 * Created by d on 2018/4/2.
 */

public class XStoreGoodsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    /**
     * 自选商品分类
     */
    @Bind(R.id.xstore_goods_type_list)
    ListView xstoreGoodTypeList;
    @Bind(R.id.xstore_goods_list_right)
    LRecyclerView xstoreGoodsListRight;
    @Bind(R.id.xstore_goods_ok)
    Button xstoreGoodsOk;


    /*商品分类*/
    List<XstoreGoodsType.DataBean> typeList = new ArrayList<>();
    XStoreTypeAdapter typeAdapter;

    /*右侧商品*/
    XStoreZXGoodsAdapter goodsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    //
    private int page = 1,pagesize = 20;
    /**当前页面展示商品*/
    XStoreZXGoods storeZXGoods;
    /**当前分类id*/
    private int typeIdNow = 0;
    /*所有已选商品id标记-*/
    Map<String,String> goodsIdSets = new HashMap<>();
    /**仅用于页面传值*/
    XStoreZXGoods yzGoods = new XStoreZXGoods();
    /**保存选择的商品列表*/
    List<XStoreZXGoods.DataBean.ListBean> xzGoodsList = new ArrayList<>();
    //
    boolean isNoMore = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xstore_goods);
        ButterKnife.bind(this);
        setTitleName("自选商品");

        //商品
        goodsAdapter = new XStoreZXGoodsAdapter(this, goodsIdSets) {
            @Override
            public void addGoods(boolean isAdd, XStoreZXGoods.DataBean.ListBean bean) {
                if (isAdd){
                    xzGoodsList.add(bean);
                }else {
                    for (XStoreZXGoods.DataBean.ListBean child:xzGoodsList) {
                        if (child.getId().equals(bean.getId())){
                            xzGoodsList.remove(child);
                            break;
                        }
                    }
                    Log.d("mmmmmmmmmmmmm","移除选择：剩余大小"+xzGoodsList.size());
                }
            }
        };
        lRecyclerViewAdapter = new LRecyclerViewAdapter(goodsAdapter);
        xstoreGoodsListRight.setLayoutManager(new LinearLayoutManager(this));
        xstoreGoodsListRight.setAdapter(lRecyclerViewAdapter);
        xstoreGoodsListRight.setPullRefreshEnabled(false);
        xstoreGoodsListRight.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isNoMore){
                    xstoreGoodsListRight.setNoMore(true);
                }else {
                    page++;
                    getGoods();
                }
            }
        });
        //存在的已选商品
        Bundle bundle = getIntent().getBundleExtra("request");
        Set<String> sets = new HashSet<>(bundle.getStringArrayList("idsRequest"));
        yzGoods = (XStoreZXGoods) bundle.getSerializable("goodsRequest");
        xzGoodsList.addAll(yzGoods.getData().getList());
        for (String s:sets) {
            goodsIdSets.put(s,s);
        }
        Log.d("mmmmmmmm","商品页初始idsList="+goodsIdSets.toString());
        //获取分类
        getTypeData();
    }

    /**
     * 获取分类数据
     */
    public void getTypeData() {
        HttpxUtils.Get(this,HttpPath.XSHOP_GOODS_ZX_TYPE,null,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        XstoreGoodsType xstoreGoodsType = GsonUtil.gsonIntance().gsonToBean(result, XstoreGoodsType.class);
                        //分类
                        typeList = xstoreGoodsType.getData();
                        typeAdapter = new XStoreTypeAdapter(XStoreGoodsActivity.this,typeList);
                        xstoreGoodTypeList.setAdapter(typeAdapter);
                        xstoreGoodTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                typeIdNow = typeList.get(position).getId();
                                getGoodsInit();
                            }
                        });
                        xstoreGoodTypeList.setItemChecked(0,true);
                        //获取默认第一个分类商品
                        typeIdNow = typeList.get(0).getId();
                        getGoodsInit();
                        System.out.println("获取小店自选商品分类 = " + xstoreGoodsType.getData().toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取小店分类 = 失败" + ex.getMessage());
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
     * 刷新，或者重新加载
     */
    public void getGoodsInit(){
        goodsAdapter.clear();
        page = 1;
        isNoMore = false;
        xstoreGoodsListRight.setNoMore(false);
        getGoods();
    }
    /**
     * 获取商品列表
     *
     */
    @SuppressLint("WrongConstant")
    public void getGoods() {
        xstoreGoodsListRight.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<>();
        map.put("cateid", "" + typeIdNow);
        map.put("curpage", "" + page);
        map.put("pagesize", "" + pagesize);
        map.put("idstr", getSetsIdstr());
        HttpxUtils.Get(this,HttpPath.XSHOP_GOODS_ZX_ALL, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("status") == 1){
                                storeZXGoods = GsonUtil.gsonIntance().gsonToBean(result, XStoreZXGoods.class);
                                xstoreGoodsListRight.refreshComplete(pagesize);
                                //
                                getDataIdstr(storeZXGoods.getData().getList());
                                goodsAdapter.clear();
                                goodsAdapter.addAll(storeZXGoods.getData().getList());
                                if (storeZXGoods.getData().getIsload() == 0){
                                    isNoMore = true;
                                }
                                System.out.println("获取小店自选商品 = " + storeZXGoods.getData().getList().toString());
                            }else {
//                                toast(jsonObject.getString("data"));
                                xstoreGoodsListRight.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取小店自选商品 = 失败" + ex.toString());
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
     *  将新获取的商品被选过的筛选出来
     * @param listBeans:获取的商品列表
     */
    public void getDataIdstr(List<XStoreZXGoods.DataBean.ListBean> listBeans){
        for (XStoreZXGoods.DataBean.ListBean bean:listBeans) {
            if (bean.getFlag().equals("1")){
                goodsIdSets.put(bean.getId(),bean.getId());
            }
        }
        Log.d("mmmmmm","已选商品id=getDataIdstr()="+goodsIdSets.toString());
    }
    /**
     * 获取已选商品id拼接
     * @return
     */
    public String getSetsIdstr(){
        StringBuffer idstr = new StringBuffer();
        for (String s: goodsIdSets.keySet()) {
            idstr.append(s+",");
        }
        Log.d("mmmmmm","已选商品id=goodssets()="+goodsIdSets.toString());
        Log.d("mmmmmm","已选商品id=getIdstr()="+idstr.toString());
        return idstr.toString();
    }

    /**
     * 确认后返回选择的数据
     */
    @OnClick(R.id.xstore_goods_ok)
    public void submit(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        //将已选的商品添加到数据
        yzGoods.getData().getList().clear();
        yzGoods.getData().getList().addAll(xzGoodsList);
        Log.d("mmmmmmmmmm","商品页---返回数据大小："+xzGoodsList.size());
        Log.d("mmmmmmmmmm","商品页---返回数据大小："+yzGoods.getData().getList().size());
        bundle.putSerializable("goodsResult",yzGoods);
        bundle.putStringArrayList("idsResult",new ArrayList<>(goodsIdSets.keySet()));
        intent.putExtra("result",bundle);
        setResult(CodeUtils.XSTORE_GOODS_RESULT,intent);
        finish();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.xstore_goods_type_list://分类

                break;
            case R.id.xstore_goods_list_right://商品

                break;
        }
//        getGoods(typeList.get(position).getId());
    }
}
