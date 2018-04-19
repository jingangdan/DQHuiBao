package com.dq.huibao.ui.jifen;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;
    private String uid = "", phone = "", token = "";

    private String[] titles = new String[]{"兑换记录", "使用记录"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myjifen, null);
        ButterKnife.bind(this, view);

        fragments.add(MyJfChileDhFragment.newInstance(uid,phone,token));
        fragments.add(MyJfChileSyFragment.newInstance(uid,phone,token));


        sfpAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), getActivity(), fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(0);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);
        return view;
    }


    public static MyJfFragment newInstance(String uid, String phone, String token) {

        Bundle args = new Bundle();
        args.putString("uid",uid);
        args.putString("phone",phone);
        args.putString("token",token);
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
