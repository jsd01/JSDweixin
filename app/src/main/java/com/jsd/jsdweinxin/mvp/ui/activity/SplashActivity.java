package com.jsd.jsdweinxin.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.PermissionUtil;
import com.jsd.jsdweinxin.R;
import com.jsd.jsdweinxin.app.ARouterPaths;
import com.jsd.jsdweinxin.app.base.BaseSupportActivity;
import com.jsd.jsdweinxin.di.component.DaggerSplashComponent;
import com.jsd.jsdweinxin.di.module.SplashModule;
import com.jsd.jsdweinxin.mvp.contract.SplashContract;
import com.jsd.jsdweinxin.mvp.presenter.SplashPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * 项目名称：JSDweixin
 * 类描述：
 * 创建人：贾少东
 * 创建时间：2018-07-18 16:07
 * 修改人：jsd
 * 修改时间：2018-07-18 16:07
 * 修改备注：
 */
public class SplashActivity extends BaseSupportActivity<SplashPresenter> implements SplashContract.View{

    @Inject
    RxErrorHandler mErrorHandler;
    @BindView(R.id.rlButton)
    RelativeLayout mRlButton;
    @BindView(R.id.btnLogin)
    Button         mBtnLogin;
    @BindView(R.id.btnRegister)
    Button         mBtnRegister;
    private RxPermissions mRxPermissions;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        this.mRxPermissions = new RxPermissions(this);
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mBtnLogin.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPaths.LOGIN).navigation();
        });

        mBtnRegister.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPaths.REGIST).navigation();
        });
        mPresenter.requestPermisson();
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void killMyself() {
        mRxPermissions = null;
    }
}
