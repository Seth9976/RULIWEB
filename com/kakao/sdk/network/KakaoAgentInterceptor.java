package com.kakao.sdk.network;

import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ContextInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000B"}, d2 = {"Lcom/kakao/sdk/network/KakaoAgentInterceptor;", "Lokhttp3/Interceptor;", "contextInfo", "Lcom/kakao/sdk/common/model/ContextInfo;", "(Lcom/kakao/sdk/common/model/ContextInfo;)V", "getContextInfo", "()Lcom/kakao/sdk/common/model/ContextInfo;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoAgentInterceptor implements Interceptor {
    private final ContextInfo contextInfo;

    public KakaoAgentInterceptor() {
        this(null, 1, null);
    }

    public KakaoAgentInterceptor(ContextInfo contextInfo0) {
        Intrinsics.checkNotNullParameter(contextInfo0, "contextInfo");
        super();
        this.contextInfo = contextInfo0;
    }

    public KakaoAgentInterceptor(ContextInfo contextInfo0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            contextInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        this(contextInfo0);
    }

    public final ContextInfo getContextInfo() {
        return this.contextInfo;
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) {
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Request request0 = interceptor$Chain0.request();
        String s = this.contextInfo.getKaHeader();
        return interceptor$Chain0.proceed(request0.newBuilder().addHeader("KA", s).build());
    }
}

