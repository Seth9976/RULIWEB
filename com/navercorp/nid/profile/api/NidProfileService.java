package com.navercorp.nid.profile.api;

import com.navercorp.nid.oauth.api.NetworkConnectionInterceptor;
import com.navercorp.nid.oauth.api.UserAgentInterceptor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient.Builder;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ!\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J!\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"Lcom/navercorp/nid/profile/api/NidProfileService;", "", "getProfileMap", "Lretrofit2/Response;", "Lcom/navercorp/nid/profile/data/NidProfileMap;", "authorization", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestApi", "Lcom/navercorp/nid/profile/data/NidProfileResponse;", "Factory", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface NidProfileService {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/profile/api/NidProfileService$Factory;", "", "()V", "BASE_URL", "", "httpClient", "Lokhttp3/OkHttpClient;", "create", "Lcom/navercorp/nid/profile/api/NidProfileService;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Factory {
        static final Factory $$INSTANCE;
        private static final String BASE_URL;
        private static final OkHttpClient httpClient;

        static {
            Factory.$$INSTANCE = new Factory();
            Factory.BASE_URL = "https://openapi.naver.com/v1/";
            Builder okHttpClient$Builder0 = new OkHttpClient().newBuilder().readTimeout(10000L, TimeUnit.MILLISECONDS).connectTimeout(10000L, TimeUnit.MILLISECONDS);
            okHttpClient$Builder0.addInterceptor(new NetworkConnectionInterceptor());
            okHttpClient$Builder0.addInterceptor(new UserAgentInterceptor());
            Factory.httpClient = okHttpClient$Builder0.build();
        }

        public final NidProfileService create() {
            Object object0 = new retrofit2.Retrofit.Builder().baseUrl(Factory.BASE_URL).client(Factory.httpClient).addConverterFactory(GsonConverterFactory.create()).build().create(NidProfileService.class);
            Intrinsics.checkNotNullExpressionValue(object0, "retrofit.create(NidProfileService::class.java)");
            return (NidProfileService)object0;
        }
    }

    public static final Factory Factory;

    static {
        NidProfileService.Factory = Factory.$$INSTANCE;
    }

    @GET("nid/me")
    Object getProfileMap(@Header("Authorization") String arg1, Continuation arg2);

    @GET("nid/me")
    Object requestApi(@Header("Authorization") String arg1, Continuation arg2);
}

