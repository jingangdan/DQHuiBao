package com.dq.huibao.ui.xstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.dq.huibao.R;
import com.dq.huibao.adapter.xstore.XStoreZXGoodsAdapter;
import com.dq.huibao.adapter.xstore.XStoreZXYXGoodsAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.xstore.XStoreGoods;
import com.dq.huibao.bean.xstore.XStoreZXGoods;
import com.dq.huibao.fragment.FMStore;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.utils.ShowUtils;
import com.dq.huibao.view.PullToRefreshLayout;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.mob.tools.gui.PullToRefreshView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 小店信息设置--已选商品
 * Created by d on 2018/4/2.
 */

public class XStoreSetFragment extends BaseFragment {
    View view = null;/*本地轻量型缓存*/
    @Bind(R.id.xstore_setting_zx)
    Switch xstoreSettingZx;
    @Bind(R.id.xstore_setting_add)
    LinearLayout xstoreSettingAdd;
    @Bind(R.id.xstore_setting_layout)
    LinearLayout xstoreSettingLayout;
    @Bind(R.id.xstore_goods_listview)
    LRecyclerView xstoreGoodsListview;
    private String uid = "", phone = "", token = "";
    int page = 0,pagesize = 20;
    /**所有已选商品id---新选择的*/
    Set<String> idsSet = new HashSet<>();
    //选择的商品
    XStoreZXGoods yzGoodsResult = new XStoreZXGoods();
    /**所有已选商品id--本页未提交--*/
    Set<String> idsSetOld = new HashSet<>();
    XStoreZXGoods yzGoodsOld = new XStoreZXGoods();
    //已选商品列表
    //

    XStoreZXYXGoodsAdapter zxyxGoodsAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xstore_setting, null);
        ButterKnife.bind(this, view);

        xstoreSettingZx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    xstoreSettingLayout.setVisibility(View.VISIBLE);
                    getData();
                } else {
                    xstoreSettingLayout.setVisibility(View.GONE);
                }
            }
        });

        zxyxGoodsAdapter = new XStoreZXYXGoodsAdapter(getActivity()) {
            @Override
            public void remove(int postion, String id) {
                //移除
                yzGoodsResult.getData().getList().remove(yzGoodsResult.getData().getList().get(postion));
                zxyxGoodsAdapter.clear();
                zxyxGoodsAdapter.addAll(yzGoodsResult.getData().getList());
                idsSet.remove(id);
            }
        };
        lRecyclerViewAdapter = new LRecyclerViewAdapter(zxyxGoodsAdapter);
        xstoreGoodsListview.setLayoutManager(new LinearLayoutManager(getActivity()));
        xstoreGoodsListview.setAdapter(lRecyclerViewAdapter);
        xstoreGoodsListview.setPullRefreshEnabled(false);
        xstoreGoodsListview.setLoadMoreEnabled(false);

        //
        xstoreSettingZx.setChecked(FMStore.isZx);
        return view;
    }

    public static XStoreSetFragment newInstance(String uid,String phone,String token) {

        Bundle args = new Bundle();
        args.putString("uid",uid);
        args.putString("phone",phone);
        args.putString("token",token);
        XStoreSetFragment fragment = new XStoreSetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uid = getArguments().getString("uid");
            phone = getArguments().getString("phone");
            token = getArguments().getString("token");
        }
    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 从网络获取已选商品-提交后刷新一次
     */
    public void getData(){
        Map<String, String> map = new HashMap<>();
        map.put("mid",uid);
        HttpxUtils.Get(getActivity(),HttpPath.XSHOP_YX_GOODS, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        yzGoodsOld = GsonUtil.gsonIntance().gsonToBean(result, XStoreZXGoods.class);
                        //更新ui
                        yzGoodsResult.getData().getList().clear();
                        yzGoodsResult.getData().getList().addAll(yzGoodsOld.getData().getList());
                        zxyxGoodsAdapter.clear();
                        zxyxGoodsAdapter.addAll(yzGoodsResult.getData().getList());
                        //获取id
                        for (XStoreZXGoods.DataBean.ListBean bean : yzGoodsOld.getData().getList()){
                            idsSetOld.add(bean.getId());
                            idsSet.add(bean.getId());
                        }
                        Log.d("mmmmmmmm","开启自选页网络获取idsList="+idsSet);
                        System.out.println("获取小店已选商品 = " + yzGoodsOld.getData().getList().toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("获取小店已选商品 = 失败" + ex.toString());
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
     * 提交选择的商品
     */
    public void submitGoods(){
        FMStore.isZx = true;
        String PATH = HttpPath.XSHOP_SAVA_GOODS;
        Map<String,String> map = new HashMap<>();
        map.put("sign",  MD5Util.getMD5String("phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpPath.KEY));
        map.put("mid", uid);
        map.put("strid",getSetsIdstr());
        map.put("phone", phone);
        map.put("timestamp", String.valueOf((System.currentTimeMillis() / 1000)));
        map.put("token", token);

        Log.d("mmmmmm","提交已选商品map="+map.toString());
        HttpxUtils.Get(getActivity(),PATH, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //将之前的数据清掉，换成最新的数据
                        idsSetOld.clear();
                        idsSetOld.addAll(idsSet);
                        yzGoodsOld.getData().getList().clear();
                        yzGoodsOld.getData().getList().addAll(yzGoodsResult.getData().getList());
                        toast("保存成功");
                        //刷新
                        getData();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("保存小店已选商品 = 失败" + ex.toString());
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
     * 关闭自选状态
     * 移除所有
     */
    private void removeAll() {
        FMStore.isZx = false;
        String PATH = HttpPath.XSHOP_MOVE_GOODS;
        Map<String,String> map = new HashMap<>();
        map.put("sign",  MD5Util.getMD5String("phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpPath.KEY));
        map.put("mid", uid);
        map.put("phone", phone);
        map.put("timestamp", String.valueOf((System.currentTimeMillis() / 1000)));
        map.put("token", token);

        Log.d("mmmmmm","移除删除已选商品map="+map.toString());
        HttpxUtils.Get(getActivity(),PATH, map,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("关闭小店自选 = 成功" + result);
                        //将之前的数据清掉，换成最新的数据
                        idsSetOld.clear();
                        yzGoodsOld.getData().getList().clear();
                        zxyxGoodsAdapter.clear();
                        xstoreSettingZx.setChecked(false);
                        toast("关闭成功");
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("关闭小店自选 = 失败" + ex.toString());
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
     * 获取已选商品id拼接
     * @return
     */
    public String getSetsIdstr(){
        StringBuffer idstr = new StringBuffer();
        for (String s: idsSet) {
            idstr.append(s+",");
        }
        return idstr.toString();
    }
    /**
     * 确认
     */
    @OnClick({R.id.xstore_setting_ok, R.id.xstore_setting_add})
    public void queren(View view) {
        switch (view.getId()) {
            case R.id.xstore_setting_add://添加商品
                Intent intent = new Intent(getActivity(), XStoreGoodsActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("goodsRequest",yzGoodsResult);
                bundle.putStringArrayList("idsRequest",new ArrayList<>(idsSet));
                intent.putExtra("request",bundle);
                startActivityForResult(intent, CodeUtils.XSTORE_GOODS);
                break;
            case R.id.xstore_setting_ok://确认
                if (xstoreSettingZx.isChecked()){
                    if (idsSet.size() > 0){
                        submitGoods();
                    }else {
                        dialogRemoveAll();
                    }
                }else {
                    //提示移除所有商品是否关闭
                    dialogRemoveAll();
                }
                break;
        }
    }

    /**
     * 提示是否移除全部
     */
    public void dialogRemoveAll(){
        ShowUtils.showDialog(getActivity(), "提示", "是否移除全部商品", new ShowUtils.OnDialogListener() {
            @Override
            public void confirm() {
                removeAll();
            }

            @Override
            public void cancel() {
                getData();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.XSTORE_GOODS) {
            if (resultCode == CodeUtils.XSTORE_GOODS_RESULT) {
                Bundle bundle = data.getBundleExtra("result");
                //商品
                yzGoodsResult = (XStoreZXGoods) bundle.getSerializable("goodsResult");
                if (yzGoodsResult != null && yzGoodsResult.getData().getList().size() > 0){
                    //选择的商品id
                    idsSet = new HashSet<>(bundle.getStringArrayList("idsResult"));
                }
                zxyxGoodsAdapter.clear();
                Log.d("mmmmm","返回数据大小="+yzGoodsResult.getData().getList().size());
                Log.d("mmmmm","商品页返回的idsList=="+idsSet);
                zxyxGoodsAdapter.addAll(yzGoodsResult.getData().getList());

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
