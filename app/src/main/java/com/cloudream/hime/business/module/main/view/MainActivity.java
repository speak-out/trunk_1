package com.cloudream.hime.business.module.main.view;

import android.os.Bundle;

import com.cloudream.hime.business.R;
import com.cloudream.hime.business.base.BaseActivity;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
