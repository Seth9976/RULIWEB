package com.kakao.sdk.auth.network;

import com.kakao.sdk.auth.AuthApiClient;
import com.kakao.sdk.auth.AuthCodeClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ApiError;
import com.kakao.sdk.common.model.ApiErrorCause;
import com.kakao.sdk.common.model.ApiErrorResponse;
import com.kakao.sdk.common.model.ApplicationContextInfo;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.network.ExceptionWrapper;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/kakao/sdk/auth/network/RequiredScopesInterceptor;", "Lokhttp3/Interceptor;", "contextInfo", "Lcom/kakao/sdk/common/model/ApplicationContextInfo;", "(Lcom/kakao/sdk/common/model/ApplicationContextInfo;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class RequiredScopesInterceptor implements Interceptor {
    private final ApplicationContextInfo contextInfo;

    public RequiredScopesInterceptor() {
        this(null, 1, null);
    }

    public RequiredScopesInterceptor(ApplicationContextInfo applicationContextInfo0) {
        Intrinsics.checkNotNullParameter(applicationContextInfo0, "contextInfo");
        super();
        this.contextInfo = applicationContextInfo0;
    }

    public RequiredScopesInterceptor(ApplicationContextInfo applicationContextInfo0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            applicationContextInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        this(applicationContextInfo0);
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) {
        ResponseBody responseBody1;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Response response0 = interceptor$Chain0.proceed(interceptor$Chain0.request());
        ResponseBody responseBody0 = response0.body();
        Response response1 = null;
        String s = responseBody0 == null ? null : responseBody0.string();
        Builder response$Builder0 = response0.newBuilder();
        if(s == null) {
            responseBody1 = null;
        }
        else {
            MediaType mediaType0 = responseBody0.contentType();
            responseBody1 = ResponseBody.Companion.create(s, mediaType0);
        }
        Response response2 = response$Builder0.body(responseBody1).build();
        if(s != null) {
            MediaType mediaType1 = responseBody0.contentType();
            ResponseBody.Companion.create(s, mediaType1);
        }
        if(!response2.isSuccessful()) {
            ApiErrorResponse apiErrorResponse0 = s == null ? null : ((ApiErrorResponse)KakaoJson.INSTANCE.fromJson(s, ApiErrorResponse.class));
            ApiErrorCause apiErrorCause0 = apiErrorResponse0 == null ? null : ((ApiErrorCause)KakaoJson.INSTANCE.fromJson(String.valueOf(apiErrorResponse0.getCode()), ApiErrorCause.class));
            if(apiErrorCause0 != null) {
                ApiError apiError0 = new ApiError(response2.code(), apiErrorCause0, apiErrorResponse0);
                List list0 = apiError0.getResponse().getRequiredScopes();
                if(apiError0.getReason() == ApiErrorCause.InsufficientScope && list0 != null && !list0.isEmpty()) {
                    ObjectRef ref$ObjectRef0 = new ObjectRef();
                    ObjectRef ref$ObjectRef1 = new ObjectRef();
                    CountDownLatch countDownLatch0 = new CountDownLatch(1);
                    AuthApiClient.Companion.getInstance().agt(new Function2(countDownLatch0, this, list0, ref$ObjectRef0) {
                        final ObjectRef $err;
                        final CountDownLatch $latch;
                        final List $requiredScopes;
                        final ObjectRef $token;

                        {
                            this.$err = ref$ObjectRef0;
                            this.$latch = countDownLatch0;
                            RequiredScopesInterceptor.this = requiredScopesInterceptor0;
                            this.$requiredScopes = list0;
                            this.$token = ref$ObjectRef1;
                            super(2);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            this.invoke(((String)object0), ((Throwable)object1));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String s, Throwable throwable0) {
                            if(throwable0 != null) {
                                this.$err.element = throwable0;
                                this.$latch.countDown();
                                return;
                            }
                            AuthCodeClient authCodeClient0 = AuthCodeClient.Companion.getInstance();
                            com.kakao.sdk.auth.network.RequiredScopesInterceptor.intercept.1.1.1 requiredScopesInterceptor$intercept$1$1$10 = new Function2(this.$latch, "RTMjOjx/A6SF/gpWerg6yktHFuwL6Duv92/JmabvMIgGQwyNPnLTpYxuS9QQOBEb/F1LCXrz/Y5xDw+QW3eHzA", this.$token) {
                                final String $codeVerifier;
                                final ObjectRef $err;
                                final CountDownLatch $latch;
                                final ObjectRef $token;

                                {
                                    this.$err = ref$ObjectRef0;
                                    this.$latch = countDownLatch0;
                                    this.$codeVerifier = s;
                                    this.$token = ref$ObjectRef1;
                                    super(2);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    this.invoke(((String)object0), ((Throwable)object1));
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(String s, Throwable throwable0) {
                                    if(throwable0 != null) {
                                        this.$err.element = throwable0;
                                        this.$latch.countDown();
                                        return;
                                    }
                                    AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                                    Intrinsics.checkNotNull(s);
                                    com.kakao.sdk.auth.network.RequiredScopesInterceptor.intercept.1.1.1.1 requiredScopesInterceptor$intercept$1$1$1$10 = new Function2(this.$err, this.$latch) {
                                        final ObjectRef $err;
                                        final CountDownLatch $latch;
                                        final ObjectRef $token;

                                        {
                                            this.$token = ref$ObjectRef0;
                                            this.$err = ref$ObjectRef1;
                                            this.$latch = countDownLatch0;
                                            super(2);
                                        }

                                        @Override  // kotlin.jvm.functions.Function2
                                        public Object invoke(Object object0, Object object1) {
                                            this.invoke(((OAuthToken)object0), ((Throwable)object1));
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                                            this.$token.element = oAuthToken0;
                                            this.$err.element = throwable0;
                                            this.$latch.countDown();
                                        }
                                    };
                                    authApiClient0.issueAccessToken(s, this.$codeVerifier, requiredScopesInterceptor$intercept$1$1$1$10);
                                }
                            };
                            AuthCodeClient.authorizeWithKakaoAccount$default(authCodeClient0, RequiredScopesInterceptor.this.contextInfo.getApplicationContext(), null, null, this.$requiredScopes, null, s, null, null, false, null, null, "RTMjOjx/A6SF/gpWerg6yktHFuwL6Duv92/JmabvMIgGQwyNPnLTpYxuS9QQOBEb/F1LCXrz/Y5xDw+QW3eHzA", null, null, requiredScopesInterceptor$intercept$1$1$10, 0x37D6, null);
                        }
                    });
                    countDownLatch0.await();
                    OAuthToken oAuthToken0 = (OAuthToken)ref$ObjectRef0.element;
                    if(oAuthToken0 != null) {
                        String s1 = oAuthToken0.getAccessToken();
                        if(s1 != null) {
                            response1 = interceptor$Chain0.proceed(AccessTokenInterceptorKt.withAccessToken(response2.request(), s1));
                        }
                    }
                    if(response1 != null) {
                        return response1;
                    }
                    Object object0 = ref$ObjectRef1.element;
                    Intrinsics.checkNotNull(object0);
                    throw new ExceptionWrapper(((Throwable)object0));
                }
                if(apiError0.getReason() != ApiErrorCause.InsufficientScope || list0 != null && !list0.isEmpty()) {
                    return response2;
                }
                ApiErrorResponse apiErrorResponse1 = new ApiErrorResponse(ApiErrorCause.Unknown.getErrorCode(), "requiredScopes not exist", null, apiError0.getResponse().getRequiredScopes(), apiError0.getResponse().getAllowedScopes(), 4, null);
                throw new ExceptionWrapper(new ApiError(apiError0.getStatusCode(), ApiErrorCause.Unknown, apiErrorResponse1));
            }
        }
        return response2;
    }
}

