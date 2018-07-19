package com.jsd.jsdweinxin.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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


import butterknife.BindColor;
import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

@Route(path = ARouterPaths.REGIST)
public class RegistActivity extends BaseSupportActivity<RegistPresenter> implements RegistContract.View {

    @BindView(R.id.etNick)
    EditText mEtNick;
    @BindView(R.id.vLineNick)
    View     mVLineNick;

    @BindView(R.id.etPhone)
    EditText mEtPhone;
    @BindView(R.id.vLinePhone)
    View     mVLinePhone;

    @BindView(R.id.etPwd)
    EditText  mEtPwd;
    @BindView(R.id.ivSeePwd)
    ImageView mIvSeePwd;
    @BindView(R.id.vLinePwd)
    View      mVLinePwd;

    @BindView(R.id.etVerifyCode)
    EditText mEtVerifyCode;
    @BindView(R.id.btnSendCode)
    Button   mBtnSendCode;
    @BindView(R.id.vLineVertifyCode)
    View     mVLineVertifyCode;

    @BindView(R.id.btnRegister)
    Button mBtnRegister;

    @BindColor(R.color.green0)
    int mGreen0;
    @BindColor(R.color.line)
    int mLine;

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBtnRegister.setEnabled(canRegister());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

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

    private void initListener() {
        mEtNick.addTextChangedListener(watcher);
        mEtPwd.addTextChangedListener(watcher);
        mEtPhone.addTextChangedListener(watcher);
        mEtVerifyCode.addTextChangedListener(watcher);

        mEtNick.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mVLineNick.setBackgroundColor(mGreen0);
            } else {
                mVLineNick.setBackgroundColor(mLine);
            }
        });
        mEtPwd.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mVLinePwd.setBackgroundColor(mGreen0);
            } else {
                mVLinePwd.setBackgroundColor(mLine);
            }
        });
        mEtPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mVLinePhone.setBackgroundColor(mGreen0);
            } else {
                mVLinePhone.setBackgroundColor(mLine);
            }
        });
        mEtVerifyCode.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mVLineVertifyCode.setBackgroundColor(mGreen0);
            } else {
                mVLineVertifyCode.setBackgroundColor(mLine);
            }
        });

        mIvSeePwd.setOnClickListener(v -> {

            if (mEtPwd.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }

            mEtPwd.setSelection(mEtPwd.getText().toString().trim().length());
        });

        mBtnSendCode.setOnClickListener(v -> {
            if (mBtnSendCode.isEnabled()) {
                mPresenter.sendCode();
            }
        });

        mBtnRegister.setOnClickListener(v -> {
            mPresenter.register();
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initListener();
    }

    private boolean canRegister() {
        int nickNameLength = mEtNick.getText().toString().trim().length();
        int pwdLength = mEtPwd.getText().toString().trim().length();
        int phoneLength = mEtPhone.getText().toString().trim().length();
        int codeLength = mEtVerifyCode.getText().toString().trim().length();
        if (nickNameLength > 0 && pwdLength > 0 && phoneLength > 0 && codeLength > 0) {
            return true;
        }
        return false;
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

    @Override
    public EditText getEtNickName() {
        return mEtNick;
    }

    @Override
    public EditText getEtPhone() {
        return mEtPhone;
    }

    @Override
    public EditText getEtPwd() {
        return mEtPwd;
    }

    @Override
    public EditText getEtVerifyCode() {
        return mEtVerifyCode;
    }

    @Override
    public Button getBtnSendCode() {
        return mBtnSendCode;
    }
}
