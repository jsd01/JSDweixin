package com.jsd.jsdweinxin.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.jsd.jsdweinxin.mvp.contract.RegistContract;


@ActivityScope
public class RegistModel extends BaseModel implements RegistContract.Model {
    @Inject
    Gson        mGson;
    @Inject
    Application mApplication;

    @Inject
    public RegistModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}