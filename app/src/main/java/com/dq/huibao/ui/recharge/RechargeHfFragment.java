package com.dq.huibao.ui.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.BillAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.BillB;
import com.dq.huibao.bean.RechargeOrderB;
import com.dq.huibao.ui.PayActivity;
import com.dq.huibao.ui.SubmitOrderActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 话费充值
 * Created by d on 2018/4/2.
 */

public class RechargeHfFragment extends BaseFragment {
    View view = null;
    @Bind(R.id.recharge_hf_phone)
    EditText rechargeHfPhone;
    @Bind(R.id.recharge_hf_btn)
    Button rechargeHfBtn;
    @Bind(R.id.recharge_hf_list)
    GridView rechargeHfList;
    /*本地轻量型缓存*/
    private String uid = "", phone = "", token = "";

    List<Integer> list = new ArrayList<>();
    BillAdapter billAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recharge_hf, null);
        ButterKnife.bind(this, view);

        rechargeHfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (rechargeHfPhone.getText().toString().equals("")){
                    toast("请填写手机号");
                    return;
                }
                toRecharge(list.get(position));
            }
        });

        rechargeHfPhone.setText(phone);

        getBill();
        return view;
    }

    public static RechargeHfFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("phone", phone);
        args.putString("token", token);
        RechargeHfFragment fragment = new RechargeHfFragment();
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
    /**
     * 话费充值
     */
    public void toRecharge(int num) {
        Map<String,String > map = new HashMap();
        map.put("uid",uid);
        map.put("pay_mobile",rechargeHfPhone.getText().toString());
        map.put("bill", num + "");
        System.out.println("话费充值 = map = " + map.toString());
        HttpxUtils.Get(getActivity(), HttpPath.RECHARGE_GET_ORDER, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("话费充值 = " + result);
                RechargeOrderB billB = GsonUtil.gsonIntance().gsonToBean(result, RechargeOrderB.class);
                if (billB.getStatus() == 1){
                    Intent intent = new Intent(getActivity(), PayActivity.class);
                    intent.putExtra("ordersn", billB.getData().getOrdersn());
                    intent.putExtra("price", billB.getData().getPay_money());
                    intent.putExtra("phone", phone);
                    intent.putExtra("token", token);
                    startActivityForResult(intent, CodeUtils.CONFIRM_ORDER);
                }else {
                    toast(billB.getData());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("话费充值 =  " + ex.toString());
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
     * 获取充值金额
     */
    public void getBill() {
        HttpxUtils.Get(getActivity(), HttpPath.RECHARGE_BILL, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("获取充值金额= " + result);
                BillB billB = GsonUtil.gsonIntance().gsonToBean(result, BillB.class);
                if (billB.getStatus() == 1) {
                    list.addAll(billB.getData());
                    billAdapter = new BillAdapter(getActivity(),list);
                    rechargeHfList.setAdapter(billAdapter);
                } else {
                    toast("获取充值金额失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("获取充值金额 " + ex.toString());
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
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
