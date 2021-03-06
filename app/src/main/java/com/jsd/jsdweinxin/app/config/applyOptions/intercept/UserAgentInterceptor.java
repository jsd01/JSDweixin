package com.jsd.jsdweinxin.app.config.applyOptions.intercept;

import com.jsd.jsdweinxin.app.api.Api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加UA拦截器，B站请求API需要加上UA才能正常使用
 */
public class UserAgentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", Api.COMMON)
                .build();
        return chain.proceed(originalRequest);
    }
}