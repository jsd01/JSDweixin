package com.jsd.jsdweinxin.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jsd.jsdweinxin.di.module.RegistModule;

import com.jsd.jsdweinxin.mvp.ui.activity.RegistActivity;

@ActivityScope
@Component(modules = RegistModule.class, dependencies = AppComponent.class)
public interface RegistComponent {
    void inject(RegistActivity activity);
}