package com.kakao.sdk.network;

import com.kakao.sdk.common.util.SdkLog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import okhttp3.logging.HttpLoggingInterceptor;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lokhttp3/logging/HttpLoggingInterceptor;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class ApiFactory.loggingInterceptor.2 extends Lambda implements Function0 {
    public static final ApiFactory.loggingInterceptor.2 INSTANCE;

    static {
        ApiFactory.loggingInterceptor.2.INSTANCE = new ApiFactory.loggingInterceptor.2();
    }

    ApiFactory.loggingInterceptor.2() {
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    public final HttpLoggingInterceptor invoke() {
        HttpLoggingInterceptor httpLoggingInterceptor0 = new HttpLoggingInterceptor(new com.kakao.sdk.network.ApiFactory.loggingInterceptor.2.interceptor.1());
        httpLoggingInterceptor0.level(Level.HEADERS);
        return httpLoggingInterceptor0;

        @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/kakao/sdk/network/ApiFactory$loggingInterceptor$2$interceptor$1", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "log", "", "message", "", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
        public final class com.kakao.sdk.network.ApiFactory.loggingInterceptor.2.interceptor.1 implements Logger {
            @Override  // okhttp3.logging.HttpLoggingInterceptor$Logger
            public void log(String s) {
                Intrinsics.checkNotNullParameter(s, "message");
                SdkLog.Companion.i(s);
            }
        }

    }
}

