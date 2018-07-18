package com.jsd.jsdweinxin.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.jsd.jsdweinxin.app.ARouterPaths;
import com.jsd.jsdweinxin.app.base.BaseSupportActivity;
import com.jsd.jsdweinxin.di.component.DaggerRegistComponent;
import com.jsd.jsdweinxin.di.module.RegistModule;
import com.jsd.jsdweinxin.mvp.contract.RegistContract;
import com.jsd.jsdweinxin.mvp.presenter.RegistPresenter;

import com.jsd.jsdweinxin.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;

@Route(path = ARouterPaths.REGIST)
public class RegistActivity extends BaseSupportActivity<RegistPresenter> implements RegistContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegistComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .registModule(new RegistModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_regist; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
