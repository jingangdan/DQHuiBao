package com.dq.huibao.ui.pingo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.adapter.pingo.PinGoGoodsAdapter;
import com.dq.huibao.adapter.pingo.PinGoLogsAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.pingo.PinGoLogsB;
import com.dq.huibao.ui.jifen.MyLogsFragment;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.NoScrollViewPager;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 拼go记录-非订单
 * Created by d on 2018/5/2.
 */

public class PinGoLogsActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;
    //
    private String phone = "", token = "", uid = "";

    private String[] titles = new String[]{"进行中", "已完成","已失败"};
    //score积分 balance现金
    private String[] type = new String[]{"0", "1","2"};
    private List<Fragment> fragments = new ArrayList<>();
    private SimpleFragmentPagerAdapter sfpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);
        ButterKnife.bind(this);

        isLogin();
        //
        for (String str:type) {
            fragments.add(PinGoLogsFragment.newInstance(uid,phone,token, str));
        }

        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(0);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);
        setTitleName("拼go记录");
    }
    /*
          * 判断登录状态
          *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        SPUserInfo spUserInfo = new SPUserInfo(this.getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                uid = login.getData().getUid();
            }
        }
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
