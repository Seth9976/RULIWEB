package com.navercorp.nid.oauth.api;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient.Builder;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0010\bf\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015Jg\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u00062\b\b\u0003\u0010\b\u001A\u00020\u00062\b\b\u0001\u0010\t\u001A\u00020\u00062\b\b\u0001\u0010\n\u001A\u00020\u00062\b\b\u0003\u0010\u000B\u001A\u00020\u00062\b\b\u0001\u0010\f\u001A\u00020\u00062\b\b\u0001\u0010\r\u001A\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000EJg\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u00062\b\b\u0003\u0010\b\u001A\u00020\u00062\b\b\u0001\u0010\u0010\u001A\u00020\u00062\b\b\u0003\u0010\u0011\u001A\u00020\u00062\b\b\u0003\u0010\u000B\u001A\u00020\u00062\b\b\u0001\u0010\f\u001A\u00020\u00062\b\b\u0001\u0010\r\u001A\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000EJ]\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u00062\b\b\u0003\u0010\b\u001A\u00020\u00062\b\b\u0001\u0010\u0013\u001A\u00020\u00062\b\b\u0003\u0010\u000B\u001A\u00020\u00062\b\b\u0001\u0010\f\u001A\u00020\u00062\b\b\u0001\u0010\r\u001A\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/navercorp/nid/oauth/api/NidOAuthLoginService;", "", "requestAccessToken", "Lretrofit2/Response;", "Lcom/navercorp/nid/oauth/data/NidOAuthResponse;", "clientId", "", "clientSecret", "grantType", "state", "code", "oauthOs", "version", "locale", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestDeleteToken", "accessToken", "serviceProvider", "requestRefreshToken", "refreshToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Factory", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface NidOAuthLoginService {
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static Object requestAccessToken$default(NidOAuthLoginService nidOAuthLoginService0, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, Continuation continuation0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestAccessToken");
            }
            if((v & 4) != 0) {
                s2 = "authorization_code";
            }
            return (v & 0x20) == 0 ? nidOAuthLoginService0.requestAccessToken(s, s1, s2, s3, s4, s5, s6, s7, continuation0) : nidOAuthLoginService0.requestAccessToken(s, s1, s2, s3, s4, "android", s6, s7, continuation0);
        }

        public static Object requestDeleteToken$default(NidOAuthLoginService nidOAuthLoginService0, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, Continuation continuation0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestDeleteToken");
            }
            if((v & 4) != 0) {
                s2 = "delete";
            }
            String s8 = (v & 16) == 0 ? s4 : "NAVER";
            return (v & 0x20) == 0 ? nidOAuthLoginService0.requestDeleteToken(s, s1, s2, s3, s8, s5, s6, s7, continuation0) : nidOAuthLoginService0.requestDeleteToken(s, s1, s2, s3, s8, "android", s6, s7, continuation0);
        }

        public static Object requestRefreshToken$default(NidOAuthLoginService nidOAuthLoginService0, String s, String s1, String s2, String s3, String s4, String s5, String s6, Continuation continuation0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestRefreshToken");
            }
            if((v & 4) != 0) {
                s2 = "refresh_token";
            }
            if((v & 16) != 0) {
                s4 = "android";
            }
            return nidOAuthLoginService0.requestRefreshToken(s, s1, s2, s3, s4, s5, s6, continuation0);
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/oauth/api/NidOAuthLoginService$Factory;", "", "()V", "BASE_URL", "", "httpClient", "Lokhttp3/OkHttpClient;", "create", "Lcom/navercorp/nid/oauth/api/NidOAuthLoginService;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Factory {
        static final Factory $$INSTANCE;
        private static final String BASE_URL;
        private static final OkHttpClient httpClient;

        static {
            Factory.$$INSTANCE = new Factory();
            Factory.BASE_URL = "https://nid.naver.com/oauth2.0/";
            Builder okHttpClient$Builder0 = new OkHttpClient().newBuilder().readTimeout(10000L, TimeUnit.MILLISECONDS).connectTimeout(10000L, TimeUnit.MILLISECONDS);
            okHttpClient$Builder0.addInterceptor(new NetworkConnectionInterceptor());
            okHttpClient$Builder0.addInterceptor(new UserAgentInterceptor());
            Factory.httpClient = okHttpClient$Builder0.build();
        }

        public final NidOAuthLoginService create() {
            Object object0 = new retrofit2.Retrofit.Builder().baseUrl(Factory.BASE_URL).client(Factory.httpClient).addConverterFactory(GsonConverterFactory.create()).build().create(NidOAuthLoginService.class);
            Intrinsics.checkNotNullExpressionValue(object0, "retrofit.create(NidOAuthLoginService::class.java)");
            return (NidOAuthLoginService)object0;
        }
    }

    public static final Factory Factory;

    static {
        NidOAuthLoginService.Factory = Factory.$$INSTANCE;
    }

    @GET("token")
    Object requestAccessToken(@Query("client_id") String arg1, @Query("client_secret") String arg2, @Query("grant_type") String arg3, @Query("state") String arg4, @Query("code") String arg5, @Query("oauth_os") String arg6, @Query("version") String arg7, @Query("locale") String arg8, Continuation arg9);

    @GET("token")
    Object requestDeleteToken(@Query("client_id") String arg1, @Query("client_secret") String arg2, @Query("grant_type") String arg3, @Query("access_token") String arg4, @Query("service_provider") String arg5, @Query("oauth_os") String arg6, @Query("version") String arg7, @Query("locale") String arg8, Continuation arg9);

    @GET("token")
    Object requestRefreshToken(@Query("client_id") String arg1, @Query("client_secret") String arg2, @Query("grant_type") String arg3, @Query("refresh_token") String arg4, @Query("oauth_os") String arg5, @Query("version") String arg6, @Query("locale") String arg7, Continuation arg8);
}

