package com.dq.huibao.ui.pingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.pingo.PinGoCartAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsTopAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.pingo.GoodsListTop;
import com.dq.huibao.bean.pingo.PinGoCartList;
import com.dq.huibao.bean.pingo.PinGoIndexMoreGoods;
import com.dq.huibao.bean.pingo.PinGoiQuSelect;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 同学拼go -购物车
 * Created by d on 2018/4/2.
 */

public class PinGoCartActivity extends BaseActivity {
    View view = null;
    @Bind(R.id.list_pingo_cart)
    RecyclerView goodsListView;
    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String uid = "", phone = "", token = "";
    private int page = 1,pagesize = 20;
    //
    //
    PinGoCartAdapter pinGoListGoodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingo_cart);
        ButterKnife.bind(this, this);
        yanzheng();
        getListGoods();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    /**
     * 获取购物车
     */
    public void getListGoods(){
        Map<String,String> map = new HashMap<>();
        map.put("mid","21");
        HttpxUtils.Get(this,HttpPath.PINGO_CARTLIST, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取购物车 = " + result);

                        try {
                            final PinGoCartList indexMoreGoods = GsonUtil.gsonIntance().gsonToBean(result, PinGoCartList.class);
                            pinGoListGoodsAdapter = new PinGoCartAdapter(PinGoCartActivity.this,indexMoreGoods.getData().getList());
                            pinGoListGoodsAdapter.setOnItemClickListener(new PinGoCartAdapter.OnItemClickListener() {
                                @Override
                                public void jian(int postion) {
                                    getUPCart(indexMoreGoods.getData().getList().get(postion).getId(),
                                            indexMoreGoods.getData().getList().get(postion).getGoodsid(),
                                            indexMoreGoods.getData().getList().get(postion).getCount(),
                                            "inc");
                                }

                                @Override
                                public void jia(int postion) {
                                    getUPCart(indexMoreGoods.getData().getList().get(postion).getId(),
                                            indexMoreGoods.getData().getList().get(postion).getGoodsid(),
                                            indexMoreGoods.getData().getList().get(postion).getCount(),
                                            "dec");
                                }

                                @Override
                                public void del(int postion) {
                                    deleteCart(indexMoreGoods.getData().getList().get(postion).getId());
                                }
                            });
                            goodsListView.setLayoutManager(new LinearLayoutManager(PinGoCartActivity.this));
                            goodsListView.setAdapter(pinGoListGoodsAdapter);
                        }catch (Exception d){
                            Log.d("sssssss",""+d.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取购物车 = 失败" + ex.getMessage());
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
     *
     * 更新购物车
     * @param type:dotype 选项：inc/dec  inc增加；dec减少
     * @param cartid:id
     * @param goodsid:goodid
     * @param count
     */
    public void getUPCart(String cartid,String goodsid,String count,String type){
        Map<String,String> map = new HashMap<>();
        map.put("mid","21");
        map.put("id",cartid);
        map.put("goodsid",goodsid);
        map.put("count","1");
        map.put("dotype",type);
        System.out.println("更新购物车 = " + map.toString());
        HttpxUtils.Get(this,HttpPath.PINGO_CART_UPDATA, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("更新购物车 = " + result);

                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (aReturn.getStatus() == 1){
                            getListGoods();
                            toast(aReturn.getData());
                        }else{
                            toast(aReturn.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("更新= 失败" + ex.getMessage());
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
     *
     * 删除购物车商品
     * @param cartid:id
     */
    public void deleteCart(String cartid){
        Map<String,String> map = new HashMap<>();
        map.put("mid","21");
        map.put("id",cartid);
        HttpxUtils.Get(this,HttpPath.PINGO_CART_DELETE, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("删除购物车商品 = " + result);

                        AddrReturn aReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (aReturn.getStatus() == 1){
                            getListGoods();
                            toast(aReturn.getData());
                        }else{
                            toast(aReturn.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("删除购物车商品= 失败" + ex.getMessage());
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
     * 地区
     */
    public void yanzheng(){
        HttpxUtils.Get(this,HttpPath.PINGO_REGION, null,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("地区地区 = " + result);

                        PinGoiQuSelect aReturn = GsonUtil.gsonIntance().gsonToBean(result, PinGoiQuSelect.class);


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
}
