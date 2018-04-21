package com.dq.huibao.ui.jifen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的积分
 * Created by d on 2018/4/2.
 */

public class MyJfFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    View view = null;/*本地轻量型缓存*/
    @Bind(R.id.radiogroup_myjifen)
    RadioGroup radiogroupMyjifen;
    private String uid = "", phone = "", token = "";

    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;
    FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myjifen, null);
        ButterKnife.bind(this, view);

        fragments.add(MyJfChileDhFragment.newInstance(uid, phone, token));
        fragments.add(MyJfChileSyFragment.newInstance(uid, phone, token));

        final FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_myjifen,fragments.get(0),"dh");
        fragmentTransaction.add(R.id.fragment_myjifen,fragments.get(1),"dh");
        fragmentTransaction.hide(fragments.get(1));
        fragmentTransaction.commit();
        radiogroupMyjifen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.radiobutton_myjifen_1://兑换记录
                        fragmentTransaction.hide(fragments.get(1));
                        fragmentTransaction.show(fragments.get(0));
                        fragmentTransaction.commit();
                        break;
                    case R.id.radiobutton_myjifen_2://使用记录
                        fragmentTransaction.hide(fragments.get(0));
                        fragmentTransaction.show(fragments.get(1));
                        fragmentTransaction.commit();
                        break;
                }
            }
        });


        return view;
    }


    public static MyJfFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid", uid);
        args.putString("phone", phone);
        args.putString("token", token);
        MyJfFragment fragment = new MyJfFragment();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
