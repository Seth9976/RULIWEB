package com.kakao.sdk.network;

import com.kakao.sdk.common.util.KakaoJson;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter.Factory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\n\b\u0002\u0010\u0013\u001A\u0004\u0018\u00010\u0014R\u001B\u0010\u0003\u001A\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001A\u0004\b\u0005\u0010\u0006R\u001B\u0010\t\u001A\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001A\u0004\b\u000B\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/kakao/sdk/network/ApiFactory;", "", "()V", "kapi", "Lretrofit2/Retrofit;", "getKapi", "()Lretrofit2/Retrofit;", "kapi$delegate", "Lkotlin/Lazy;", "loggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "getLoggingInterceptor", "()Lokhttp3/logging/HttpLoggingInterceptor;", "loggingInterceptor$delegate", "withClientAndAdapter", "url", "", "clientBuilder", "Lokhttp3/OkHttpClient$Builder;", "factory", "Lretrofit2/CallAdapter$Factory;", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ApiFactory {
    public static final ApiFactory INSTANCE;
    private static final Lazy kapi$delegate;
    private static final Lazy loggingInterceptor$delegate;

    static {
        ApiFactory.INSTANCE = new ApiFactory();
        ApiFactory.loggingInterceptor$delegate = LazyKt.lazy(ApiFactory.loggingInterceptor.2.INSTANCE);
        ApiFactory.kapi$delegate = LazyKt.lazy(ApiFactory.kapi.2.INSTANCE);
    }

    public final Retrofit getKapi() {
        return (Retrofit)ApiFactory.kapi$delegate.getValue();
    }

    public final HttpLoggingInterceptor getLoggingInterceptor() {
        return (HttpLoggingInterceptor)ApiFactory.loggingInterceptor$delegate.getValue();
    }

    public final Retrofit withClientAndAdapter(String s, Builder okHttpClient$Builder0, Factory callAdapter$Factory0) {
        Intrinsics.checkNotNullParameter(s, "url");
        Intrinsics.checkNotNullParameter(okHttpClient$Builder0, "clientBuilder");
        retrofit2.Retrofit.Builder retrofit$Builder0 = new retrofit2.Retrofit.Builder().baseUrl(s).addConverterFactory(new KakaoRetrofitConverterFactory()).addConverterFactory(GsonConverterFactory.create(KakaoJson.INSTANCE.getBase())).client(okHttpClient$Builder0.build());
        if(callAdapter$Factory0 != null) {
            retrofit$Builder0.addCallAdapterFactory(callAdapter$Factory0);
        }
        Retrofit retrofit0 = retrofit$Builder0.build();
        Intrinsics.checkNotNullExpressionValue(retrofit0, "builder.build()");
        return retrofit0;
    }

    public static Retrofit withClientAndAdapter$default(ApiFactory apiFactory0, String s, Builder okHttpClient$Builder0, Factory callAdapter$Factory0, int v, Object object0) {
        if((v & 4) != 0) {
            callAdapter$Factory0 = null;
        }
        return apiFactory0.withClientAndAdapter(s, okHttpClient$Builder0, callAdapter$Factory0);
    }
}

