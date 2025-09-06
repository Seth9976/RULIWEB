package com.kakao.sdk.auth.network;

import com.kakao.sdk.network.ApiFactory;
import com.kakao.sdk.network.KakaoAgentInterceptor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lretrofit2/Retrofit;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class ApiFactoryKt.kauth.2 extends Lambda implements Function0 {
    public static final ApiFactoryKt.kauth.2 INSTANCE;

    static {
        ApiFactoryKt.kauth.2.INSTANCE = new ApiFactoryKt.kauth.2();
    }

    ApiFactoryKt.kauth.2() {
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    // 去混淆评级： 低(20)
    public final Retrofit invoke() {
        Builder okHttpClient$Builder0 = new Builder().addInterceptor(new KakaoAgentInterceptor(null, 1, null)).addInterceptor(ApiFactory.INSTANCE.getLoggingInterceptor());
        return ApiFactory.withClientAndAdapter$default(ApiFactory.INSTANCE, "https://kauth.kakao.com", okHttpClient$Builder0, null, 4, null);
    }
}

