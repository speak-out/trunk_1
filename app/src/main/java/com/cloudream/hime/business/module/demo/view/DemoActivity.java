package com.cloudream.hime.business.module.demo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cloudream.hime.business.R;
import com.cloudream.hime.business.base.BaseActivity;
import com.cloudream.hime.business.module.demo.presenter.DemoPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoActivity extends BaseActivity implements View.OnClickListener,IdemoView{

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.text)
    TextView mTv;

    private DemoPresenter mDemoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
        initView();
        mDemoPresenter = new DemoPresenter(this);
    }

    private void initView() {
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mDemoPresenter.getArea();
                break;
            case R.id.button1:
                mDemoPresenter.getDemo();
                break;
        }
    }

    @Override
    public void setTextView(String text) {
        mTv.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDemoPresenter.unsubscribe();
    }
}
