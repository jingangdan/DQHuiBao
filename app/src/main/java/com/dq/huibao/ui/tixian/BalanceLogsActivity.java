package com.dq.huibao.ui.tixian;

import android.os.Bundle;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by d on 2018/4/29.
 */

public class BalanceLogsActivity extends BaseActivity {
    @Bind(R.id.lrecyclerView)
    LRecyclerView lrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrlist_layout);
        ButterKnife.bind(this);


    }
}
