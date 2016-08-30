package com.cloudream.hime.business.module.main.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudream.hime.business.R;
import com.cloudream.hime.business.base.BaseActivity;
import com.cloudream.hime.business.module.main.adapter.MainFragmentAdapter;
import com.cloudream.hime.business.module.main.fragment.IncomeFragment;
import com.cloudream.hime.business.module.main.fragment.MeFragment;
import com.cloudream.hime.business.module.main.fragment.VerificationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_main_bottom)
    TabLayout mTab;
    @Bind(R.id.vp_main_content)
    ViewPager mContentVp;

    private List<Fragment> mFragments;
    private String[] mTabTitle = new String[]{"验证", "收入", "我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    //初始化view
    private void initView() {
        if(mFragments == null || mFragments.size() == 0) {
            mFragments = new ArrayList<>();
            mFragments.add(VerificationFragment.getInstance(null));
            mFragments.add(IncomeFragment.getInstance(null));
            mFragments.add(MeFragment.getInstance(null));
        }
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), mFragments, mTabTitle);
        mContentVp.setAdapter(adapter);
        mTab.setupWithViewPager(mContentVp);
        //禁止点击底部导航viewpager滑动
        mTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mContentVp.setCurrentItem(tab.getPosition(),false);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            int iconId = 0;
            switch (i) {
                case 0:
                    iconId = R.drawable.tab_verification_selector;
                    break;
                case 1:
                    iconId = R.drawable.tab_income_selector;
                    break;
                case 2:
                    iconId = R.drawable.tab_me_selector;
                    break;
            }
            //设置图标
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab_title, null);
            TextView tv= (TextView) view.findViewById(R.id.tv_main_tab_txt);
            ImageView img = (ImageView) view.findViewById(R.id.iv_main_tab_img);
            tv.setText(mTabTitle[i]);
            tv.setTextColor(ContextCompat.getColorStateList(this,R.drawable.tab_title_color_selector));
            img.setImageResource(iconId);
            tab.setCustomView(view);
        }
        //设置默认选中tab
        mTab.getTabAt(0).getCustomView().setSelected(true);
    }
}
