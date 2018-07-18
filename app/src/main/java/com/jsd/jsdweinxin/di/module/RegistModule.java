package com.jsd.jsdweinxin.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.jsd.jsdweinxin.mvp.contract.RegistContract;
import com.jsd.jsdweinxin.mvp.model.RegistModel;


@Module
public class RegistModule {
    private RegistContract.View view;

    /**
     * 构建RegistModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RegistModule(RegistContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RegistContract.View provideRegistView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RegistContract.Model provideRegistModel(RegistModel model) {
        return model;
    }
}