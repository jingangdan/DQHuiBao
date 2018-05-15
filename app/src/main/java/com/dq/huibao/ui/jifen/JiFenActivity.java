package com.dq.huibao.ui.jifen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.SimpleFragmentPagerAdapter;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.ui.coupons.CouponsActivity;
import com.dq.huibao.ui.coupons.CouponsGetActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 积分兑换
 * Created by d on 2018/4/14.
 */

public class JiFenActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.jifen_tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.jifen_tb_noScrollViewPage)
    NoScrollViewPager noScrollViewPager;
    @Bind(R.id.jifen_tv_title)
    TextView jifenTvTitle;

    private String[] titles = new String[]{"积分兑换", "兑换记录"};
    private List<Fragment> fragments = new ArrayList<>();

    private SimpleFragmentPagerAdapter sfpAdapter;

    Intent intent;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen);

        ButterKnife.bind(this);

        fragments.add(JifenFlFragment.newInstance(uidBase, phoneBase, tokenBase));
        fragments.add(MyJfLogsFragment.newInstance(uidBase, phoneBase, tokenBase));


        sfpAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        noScrollViewPager.setAdapter(sfpAdapter);

        noScrollViewPager.setCurrentItem(page);


        noScrollViewPager.setOffscreenPageLimit(titles.length);

        noScrollViewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(noScrollViewPager);

        jifenTvTitle.setText("积分兑换");
    }
    @OnClick({R.id.jifen_iv_back, R.id.jifen_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jifen_iv_back://返回
                finish();
                break;
            case R.id.jifen_tv_right://记录
                Intent intent = new Intent(JiFenActivity.this,MyLogsActivity.class);
                startActivity(intent);
                break;
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
