package com.jsd.jsdweinxin.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import com.jsd.jsdweinxin.R;
import com.jsd.jsdweinxin.app.consts.AppConst;
import com.jsd.jsdweinxin.app.entity.exception.ServerException;
import com.jsd.jsdweinxin.app.entity.response.CheckPhoneResponse;
import com.jsd.jsdweinxin.app.entity.response.SendCodeResponse;
import com.jsd.jsdweinxin.app.util.DialogUtils;
import com.jsd.jsdweinxin.app.util.RegularUtils;
import com.jsd.jsdweinxin.mvp.contract.RegistContract;


@ActivityScope
public class RegistPresenter extends BasePresenter<RegistContract.Model, RegistContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application    mApplication;
    @Inject
    ImageLoader    mImageLoader;
    @Inject
    AppManager     mAppManager;

    @Inject
    public RegistPresenter(RegistContract.Model model, RegistContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void sendCode() {
        String phone = mRootView.getEtPhone().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ArmsUtils.mToast.setText(ArmsUtils.getString(mApplication, R.string.phone_not_empty));
            return;
        }

        if (!RegularUtils.isMobile(phone)) {
            ArmsUtils.mToast.setText(ArmsUtils.getString(mApplication, R.string.phone_format_error));
            return;
        }

        DialogUtils.showWaitingDialog(mApplication, ArmsUtils.getString(mApplication, R.string.please_wait));
        /*mModel.checkPhoneAvailable(AppConst.REGION, phone)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<CheckPhoneResponse, ObservableSource<SendCodeResponse>>() {

                    @Override
                    public Observable<SendCodeResponse> apply(CheckPhoneResponse checkPhoneResponse) throws Exception {
                        int code = checkPhoneResponse.getCode();
                        if (code == 200) {
                            return ApiRetrofit.getInstance().sendCode(AppConst.REGION, phone);
                        } else {
                            return Observable.error(new ServerException(ArmsUtils.getString(mApplication, R.string.phone_not_available)));
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sendCodeResponse -> {
                    DialogUtils.hideWaitingDialog();
                    int code = sendCodeResponse.getCode();
                    if (code == 200) {
                        changeSendCodeBtn();
                    } else {
                        sendCodeError(new ServerException(ArmsUtils.getString(mApplication, R.string.send_code_error)));
                    }
                }, this::sendCodeError);*/
    }

    public void register() {

    }
}
