package com.kakao.sdk.auth.network;

import com.kakao.sdk.network.ApiFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\"\u001F\u0010\u0000\u001A\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u001F\u0010\u0007\u001A\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\u0006\u001A\u0004\b\b\u0010\u0004¨\u0006\n"}, d2 = {"kapiWithOAuth", "Lretrofit2/Retrofit;", "Lcom/kakao/sdk/network/ApiFactory;", "getKapiWithOAuth", "(Lcom/kakao/sdk/network/ApiFactory;)Lretrofit2/Retrofit;", "kapiWithOAuth$delegate", "Lkotlin/Lazy;", "kauth", "getKauth", "kauth$delegate", "auth_release"}, k = 2, mv = {1, 5, 1}, xi = 0x30)
public final class ApiFactoryKt {
    private static final Lazy kapiWithOAuth$delegate;
    private static final Lazy kauth$delegate;

    static {
        ApiFactoryKt.kapiWithOAuth$delegate = LazyKt.lazy(ApiFactoryKt.kapiWithOAuth.2.INSTANCE);
        ApiFactoryKt.kauth$delegate = LazyKt.lazy(ApiFactoryKt.kauth.2.INSTANCE);
    }

    public static final Retrofit getKapiWithOAuth(ApiFactory apiFactory0) {
        Intrinsics.checkNotNullParameter(apiFactory0, "<this>");
        return (Retrofit)ApiFactoryKt.kapiWithOAuth$delegate.getValue();
    }

    public static final Retrofit getKauth(ApiFactory apiFactory0) {
        Intrinsics.checkNotNullParameter(apiFactory0, "<this>");
        return (Retrofit)ApiFactoryKt.kauth$delegate.getValue();
    }
}

