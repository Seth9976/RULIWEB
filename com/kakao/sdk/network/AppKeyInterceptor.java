package com.kakao.sdk.network;

import com.kakao.sdk.common.KakaoSdk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/kakao/sdk/network/AppKeyInterceptor;", "Lokhttp3/Interceptor;", "appKey", "", "(Ljava/lang/String;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AppKeyInterceptor implements Interceptor {
    private final String appKey;

    public AppKeyInterceptor() {
        this(null, 1, null);
    }

    public AppKeyInterceptor(String s) {
        Intrinsics.checkNotNullParameter(s, "appKey");
        super();
        this.appKey = s;
    }

    public AppKeyInterceptor(String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            s = KakaoSdk.INSTANCE.getApplicationContextInfo().getAppKey();
        }
        this(s);
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) {
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        return interceptor$Chain0.proceed(interceptor$Chain0.request().newBuilder().addHeader("Authorization", "KakaoAK " + this.appKey).build());
    }
}

