package com.navercorp.nid.scheme.api;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J$\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\'¨\u0006\b"}, d2 = {"Lcom/navercorp/nid/scheme/api/NidSchemeService;", "", "requestSchemeLog", "Lretrofit2/Call;", "", "bodies", "", "Factory", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface NidSchemeService {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/scheme/api/NidSchemeService$Factory;", "", "()V", "BASE_URL", "", "httpClient", "Lokhttp3/OkHttpClient;", "create", "Lcom/navercorp/nid/scheme/api/NidSchemeService;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Factory {
        static final Factory $$INSTANCE = null;
        private static final String BASE_URL = "https://nid.naver.com/";
        private static final OkHttpClient httpClient;

        static {
            Factory.$$INSTANCE = new Factory();
            Factory.httpClient = new OkHttpClient().newBuilder().readTimeout(10000L, TimeUnit.MILLISECONDS).connectTimeout(10000L, TimeUnit.MILLISECONDS).build();
        }

        public final NidSchemeService create() {
            Object object0 = new Builder().baseUrl("https://nid.naver.com/").client(Factory.httpClient).addConverterFactory(GsonConverterFactory.create()).build().create(NidSchemeService.class);
            Intrinsics.checkNotNullExpressionValue(object0, "retrofit.create(NidSchemeService::class.java)");
            return (NidSchemeService)object0;
        }
    }

    public static final Factory Factory;

    static {
        NidSchemeService.Factory = Factory.$$INSTANCE;
    }

    @POST("login/api/log.report")
    Call requestSchemeLog(@QueryMap Map arg1);
}

