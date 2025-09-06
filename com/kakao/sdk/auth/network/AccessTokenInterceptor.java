package com.kakao.sdk.auth.network;

import com.kakao.sdk.auth.AuthApiManager;
import com.kakao.sdk.auth.TokenManagerProvider;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.model.ApiError;
import com.kakao.sdk.common.model.ApiErrorCause;
import com.kakao.sdk.common.model.ApiErrorResponse;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.network.ExceptionWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lcom/kakao/sdk/auth/network/AccessTokenInterceptor;", "Lokhttp3/Interceptor;", "tokenManagerProvider", "Lcom/kakao/sdk/auth/TokenManagerProvider;", "manager", "Lcom/kakao/sdk/auth/AuthApiManager;", "(Lcom/kakao/sdk/auth/TokenManagerProvider;Lcom/kakao/sdk/auth/AuthApiManager;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AccessTokenInterceptor implements Interceptor {
    private final AuthApiManager manager;
    private final TokenManagerProvider tokenManagerProvider;

    public AccessTokenInterceptor() {
        this(null, null, 3, null);
    }

    public AccessTokenInterceptor(TokenManagerProvider tokenManagerProvider0, AuthApiManager authApiManager0) {
        Intrinsics.checkNotNullParameter(tokenManagerProvider0, "tokenManagerProvider");
        Intrinsics.checkNotNullParameter(authApiManager0, "manager");
        super();
        this.tokenManagerProvider = tokenManagerProvider0;
        this.manager = authApiManager0;
    }

    public AccessTokenInterceptor(TokenManagerProvider tokenManagerProvider0, AuthApiManager authApiManager0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            tokenManagerProvider0 = TokenManagerProvider.Companion.getInstance();
        }
        if((v & 2) != 0) {
            authApiManager0 = AuthApiManager.Companion.getInstance();
        }
        this(tokenManagerProvider0, authApiManager0);
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) {
        String s2;
        ResponseBody responseBody1;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        OAuthToken oAuthToken0 = this.tokenManagerProvider.getManager().getToken();
        ApiErrorCause apiErrorCause0 = null;
        String s = oAuthToken0 == null ? null : oAuthToken0.getAccessToken();
        Request request0 = s == null ? null : AccessTokenInterceptorKt.withAccessToken(interceptor$Chain0.request(), s);
        if(request0 == null) {
            throw new ExceptionWrapper(new ClientError(ClientErrorCause.TokenNotFound, null, 2, null));
        }
        Response response0 = interceptor$Chain0.proceed(request0);
        ResponseBody responseBody0 = response0.body();
        String s1 = responseBody0 == null ? null : responseBody0.string();
        Builder response$Builder0 = response0.newBuilder();
        if(s1 == null) {
            responseBody1 = null;
        }
        else {
            MediaType mediaType0 = responseBody0.contentType();
            responseBody1 = ResponseBody.Companion.create(s1, mediaType0);
        }
        Response response1 = response$Builder0.body(responseBody1).build();
        if(s1 != null) {
            MediaType mediaType1 = responseBody0.contentType();
            ResponseBody.Companion.create(s1, mediaType1);
        }
        if(!response1.isSuccessful()) {
            ApiErrorResponse apiErrorResponse0 = s1 == null ? null : ((ApiErrorResponse)KakaoJson.INSTANCE.fromJson(s1, ApiErrorResponse.class));
            if(apiErrorResponse0 != null) {
                apiErrorCause0 = (ApiErrorCause)KakaoJson.INSTANCE.fromJson(String.valueOf(apiErrorResponse0.getCode()), ApiErrorCause.class);
            }
            if(apiErrorCause0 != null && new ApiError(response1.code(), apiErrorCause0, apiErrorResponse0).getReason() == ApiErrorCause.InvalidToken) {
                synchronized(this) {
                    OAuthToken oAuthToken1 = this.tokenManagerProvider.getManager().getToken();
                    if(oAuthToken1 != null) {
                        if(Intrinsics.areEqual(oAuthToken1.getAccessToken(), s)) {
                            try {
                                s2 = this.manager.refreshToken$auth_release(oAuthToken1).getAccessToken();
                            }
                            catch(Throwable throwable0) {
                                throw new ExceptionWrapper(throwable0);
                            }
                        }
                        else {
                            s2 = oAuthToken1.getAccessToken();
                        }
                        return interceptor$Chain0.proceed(AccessTokenInterceptorKt.withAccessToken(request0, s2));
                    }
                    return response1;
                }
            }
        }
        return response1;
    }
}

