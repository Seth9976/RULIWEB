package com.kakao.sdk.auth;

import com.kakao.sdk.auth.model.AccessTokenResponse;
import com.kakao.sdk.auth.model.AgtResponse;
import com.kakao.sdk.auth.model.CertTokenInfo;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.auth.network.ApiFactoryKt;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ApplicationInfo;
import com.kakao.sdk.common.model.ApprovalType;
import com.kakao.sdk.common.model.AuthError;
import com.kakao.sdk.common.model.AuthErrorCause;
import com.kakao.sdk.common.model.AuthErrorResponse;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.model.ContextInfo;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.network.ApiFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B7\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\u000B\u00A2\u0006\u0002\u0010\fJI\u0010\u0015\u001A\u00020\u00162:\u0010\u0017\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u001C\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001D\u0012\u0004\u0012\u00020\u00160\u0018H\u0000\u00A2\u0006\u0002\b\u001EJ\r\u0010\u001F\u001A\u00020 H\u0000\u00A2\u0006\u0002\b!J]\u0010\"\u001A\u00020\u00162\u0006\u0010#\u001A\u00020\u00192\n\b\u0002\u0010$\u001A\u0004\u0018\u00010\u00192:\u0010\u0017\u001A6\u0012\u0015\u0012\u0013\u0018\u00010%\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u001C\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001D\u0012\u0004\u0012\u00020\u00160\u0018H\u0000\u00A2\u0006\u0002\b\'J]\u0010(\u001A\u00020\u00162\u0006\u0010#\u001A\u00020\u00192\n\b\u0002\u0010$\u001A\u0004\u0018\u00010\u00192:\u0010\u0017\u001A6\u0012\u0015\u0012\u0013\u0018\u00010)\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(*\u0012\u0015\u0012\u0013\u0018\u00010\u001C\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001D\u0012\u0004\u0012\u00020\u00160\u0018H\u0000\u00A2\u0006\u0002\b+J\u0017\u0010,\u001A\u00020%2\b\b\u0002\u0010-\u001A\u00020%H\u0000\u00A2\u0006\u0002\b.JS\u0010,\u001A\u00020\u00162\b\b\u0002\u0010-\u001A\u00020%2:\u0010\u0017\u001A6\u0012\u0015\u0012\u0013\u0018\u00010%\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u001C\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001D\u0012\u0004\u0012\u00020\u00160\u0018H\u0001\u00A2\u0006\u0002\b.R\u0011\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\n\u001A\u00020\u000B\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\b\u001A\u00020\t\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014\u00A8\u00060"}, d2 = {"Lcom/kakao/sdk/auth/AuthApiManager;", "", "authApi", "Lcom/kakao/sdk/auth/AuthApi;", "tokenManagerProvider", "Lcom/kakao/sdk/auth/TokenManagerProvider;", "applicationInfo", "Lcom/kakao/sdk/common/model/ApplicationInfo;", "contextInfo", "Lcom/kakao/sdk/common/model/ContextInfo;", "approvalType", "Lcom/kakao/sdk/common/model/ApprovalType;", "(Lcom/kakao/sdk/auth/AuthApi;Lcom/kakao/sdk/auth/TokenManagerProvider;Lcom/kakao/sdk/common/model/ApplicationInfo;Lcom/kakao/sdk/common/model/ContextInfo;Lcom/kakao/sdk/common/model/ApprovalType;)V", "getApplicationInfo", "()Lcom/kakao/sdk/common/model/ApplicationInfo;", "getApprovalType", "()Lcom/kakao/sdk/common/model/ApprovalType;", "getContextInfo", "()Lcom/kakao/sdk/common/model/ContextInfo;", "getTokenManagerProvider", "()Lcom/kakao/sdk/auth/TokenManagerProvider;", "agt", "", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "", "error", "agt$auth_release", "hasToken", "", "hasToken$auth_release", "issueAccessToken", "code", "codeVerifier", "Lcom/kakao/sdk/auth/model/OAuthToken;", "token", "issueAccessToken$auth_release", "issueAccessTokenWithCert", "Lcom/kakao/sdk/auth/model/CertTokenInfo;", "certTokenInfo", "issueAccessTokenWithCert$auth_release", "refreshToken", "oldToken", "refreshToken$auth_release", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthApiManager {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000BR!\u0010\u0003\u001A\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001A\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/kakao/sdk/auth/AuthApiManager$Companion;", "", "()V", "instance", "Lcom/kakao/sdk/auth/AuthApiManager;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/auth/AuthApiManager;", "instance$delegate", "Lkotlin/Lazy;", "translateError", "", "t", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/auth/AuthApiManager;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final AuthApiManager getInstance() {
            return (AuthApiManager)AuthApiManager.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final Throwable translateError(Throwable throwable0) {
            AuthErrorCause authErrorCause0;
            Intrinsics.checkNotNullParameter(throwable0, "t");
            try {
                if(!(throwable0 instanceof HttpException)) {
                    return throwable0;
                }
                Response response0 = ((HttpException)throwable0).response();
                String s = null;
                if(response0 != null) {
                    ResponseBody responseBody0 = response0.errorBody();
                    if(responseBody0 != null) {
                        s = responseBody0.string();
                    }
                }
                Intrinsics.checkNotNull(s);
                AuthErrorResponse authErrorResponse0 = (AuthErrorResponse)KakaoJson.INSTANCE.fromJson(s, AuthErrorResponse.class);
                try {
                    authErrorCause0 = Result.constructor-impl(((AuthErrorCause)KakaoJson.INSTANCE.fromJson(authErrorResponse0.getError(), AuthErrorCause.class)));
                }
                catch(Throwable throwable2) {
                    authErrorCause0 = Result.constructor-impl(ResultKt.createFailure(throwable2));
                }
                AuthErrorCause authErrorCause1 = AuthErrorCause.Unknown;
                if(Result.isFailure-impl(authErrorCause0)) {
                    authErrorCause0 = authErrorCause1;
                }
                return new AuthError(((HttpException)throwable0).code(), authErrorCause0, authErrorResponse0);
            }
            catch(Throwable throwable1) {
                return throwable1;
            }
        }
    }

    public static final Companion Companion;
    private final ApplicationInfo applicationInfo;
    private final ApprovalType approvalType;
    private final AuthApi authApi;
    private final ContextInfo contextInfo;
    private static final Lazy instance$delegate;
    private final TokenManagerProvider tokenManagerProvider;

    static {
        AuthApiManager.Companion = new Companion(null);
        AuthApiManager.instance$delegate = LazyKt.lazy(AuthApiManager.Companion.instance.2.INSTANCE);
    }

    public AuthApiManager() {
        this(null, null, null, null, null, 0x1F, null);
    }

    public AuthApiManager(AuthApi authApi0, TokenManagerProvider tokenManagerProvider0, ApplicationInfo applicationInfo0, ContextInfo contextInfo0, ApprovalType approvalType0) {
        Intrinsics.checkNotNullParameter(authApi0, "authApi");
        Intrinsics.checkNotNullParameter(tokenManagerProvider0, "tokenManagerProvider");
        Intrinsics.checkNotNullParameter(applicationInfo0, "applicationInfo");
        Intrinsics.checkNotNullParameter(contextInfo0, "contextInfo");
        Intrinsics.checkNotNullParameter(approvalType0, "approvalType");
        super();
        this.authApi = authApi0;
        this.tokenManagerProvider = tokenManagerProvider0;
        this.applicationInfo = applicationInfo0;
        this.contextInfo = contextInfo0;
        this.approvalType = approvalType0;
    }

    public AuthApiManager(AuthApi authApi0, TokenManagerProvider tokenManagerProvider0, ApplicationInfo applicationInfo0, ContextInfo contextInfo0, ApprovalType approvalType0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            Object object0 = ApiFactoryKt.getKauth(ApiFactory.INSTANCE).create(AuthApi.class);
            Intrinsics.checkNotNullExpressionValue(object0, "ApiFactory.kauth.create(AuthApi::class.java)");
            authApi0 = (AuthApi)object0;
        }
        if((v & 2) != 0) {
            tokenManagerProvider0 = TokenManagerProvider.Companion.getInstance();
        }
        if((v & 4) != 0) {
            applicationInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        if((v & 8) != 0) {
            contextInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        if((v & 16) != 0) {
            approvalType0 = KakaoSdk.INSTANCE.getApprovalType();
        }
        this(authApi0, tokenManagerProvider0, applicationInfo0, contextInfo0, approvalType0);
    }

    public final void agt$auth_release(Function2 function20) {
        Unit unit0;
        Intrinsics.checkNotNullParameter(function20, "callback");
        OAuthToken oAuthToken0 = this.tokenManagerProvider.getManager().getToken();
        if(oAuthToken0 == null) {
            unit0 = null;
        }
        else {
            String s = oAuthToken0.getAccessToken();
            if(s == null) {
                unit0 = null;
            }
            else {
                String s1 = this.getApplicationInfo().getAppKey();
                this.authApi.agt(s1, s).enqueue(new Callback() {
                    @Override  // retrofit2.Callback
                    public void onFailure(Call call0, Throwable throwable0) {
                        Intrinsics.checkNotNullParameter(call0, "call");
                        Intrinsics.checkNotNullParameter(throwable0, "t");
                        this.$callback.invoke(null, throwable0);
                    }

                    @Override  // retrofit2.Callback
                    public void onResponse(Call call0, Response response0) {
                        Intrinsics.checkNotNullParameter(call0, "call");
                        Intrinsics.checkNotNullParameter(response0, "response");
                        AgtResponse agtResponse0 = (AgtResponse)response0.body();
                        if(agtResponse0 == null) {
                            Throwable throwable0 = new HttpException(response0);
                            Throwable throwable1 = AuthApiManager.Companion.translateError(throwable0);
                            this.$callback.invoke(null, throwable1);
                            return;
                        }
                        this.$callback.invoke(agtResponse0.getAgt(), null);
                    }
                });
                unit0 = Unit.INSTANCE;
            }
        }
        if(unit0 == null) {
            function20.invoke(null, new ClientError(ClientErrorCause.TokenNotFound, "Access token not found. You must login first."));
        }
    }

    public final ApplicationInfo getApplicationInfo() {
        return this.applicationInfo;
    }

    public final ApprovalType getApprovalType() {
        return this.approvalType;
    }

    public final ContextInfo getContextInfo() {
        return this.contextInfo;
    }

    public static final AuthApiManager getInstance() {
        return AuthApiManager.Companion.getInstance();
    }

    public final TokenManagerProvider getTokenManagerProvider() {
        return this.tokenManagerProvider;
    }

    public final boolean hasToken$auth_release() {
        return this.tokenManagerProvider.getManager().getToken() != null;
    }

    public final void issueAccessToken$auth_release(String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(s, "code");
        Intrinsics.checkNotNullParameter(function20, "callback");
        String s2 = this.applicationInfo.getAppKey();
        String s3 = this.contextInfo.getSigningKeyHash();
        String s4 = this.applicationInfo.getRedirectUri();
        DefaultImpls.issueAccessToken$default(this.authApi, s2, s3, s, s4, s1, this.approvalType.getValue(), null, 0x40, null).enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(throwable0, "t");
                this.invoke(null, throwable0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(response0, "response");
                if(response0.isSuccessful()) {
                    AccessTokenResponse accessTokenResponse0 = (AccessTokenResponse)response0.body();
                    if(accessTokenResponse0 == null) {
                        ClientError clientError0 = new ClientError(ClientErrorCause.Unknown, "No body");
                        this.invoke(null, clientError0);
                        return;
                    }
                    OAuthToken oAuthToken0 = com.kakao.sdk.auth.model.OAuthToken.Companion.fromResponse$default(OAuthToken.Companion, accessTokenResponse0, null, 2, null);
                    AuthApiManager.this.getTokenManagerProvider().getManager().setToken(oAuthToken0);
                    this.invoke(oAuthToken0, null);
                    return;
                }
                Throwable throwable0 = new HttpException(response0);
                Throwable throwable1 = AuthApiManager.Companion.translateError(throwable0);
                this.invoke(null, throwable1);
            }
        });
    }

    public static void issueAccessToken$auth_release$default(AuthApiManager authApiManager0, String s, String s1, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        authApiManager0.issueAccessToken$auth_release(s, s1, function20);
    }

    public final void issueAccessTokenWithCert$auth_release(String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(s, "code");
        Intrinsics.checkNotNullParameter(function20, "callback");
        String s2 = this.applicationInfo.getAppKey();
        String s3 = this.contextInfo.getSigningKeyHash();
        String s4 = this.applicationInfo.getRedirectUri();
        DefaultImpls.issueAccessToken$default(this.authApi, s2, s3, s, s4, s1, this.approvalType.getValue(), null, 0x40, null).enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(throwable0, "t");
                this.invoke(null, throwable0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(response0, "response");
                if(response0.isSuccessful()) {
                    AccessTokenResponse accessTokenResponse0 = (AccessTokenResponse)response0.body();
                    if(accessTokenResponse0 == null) {
                        ClientError clientError0 = new ClientError(ClientErrorCause.Unknown, "No body");
                        this.invoke(null, clientError0);
                        return;
                    }
                    Function2 function20 = this;
                    AuthApiManager authApiManager0 = AuthApiManager.this;
                    OAuthToken oAuthToken0 = com.kakao.sdk.auth.model.OAuthToken.Companion.fromResponse$default(OAuthToken.Companion, accessTokenResponse0, null, 2, null);
                    String s = accessTokenResponse0.getTxId();
                    if(s == null) {
                        function20.invoke(null, new ClientError(ClientErrorCause.Unknown, "txId is null"));
                        return;
                    }
                    authApiManager0.getTokenManagerProvider().getManager().setToken(oAuthToken0);
                    function20.invoke(new CertTokenInfo(oAuthToken0, s), null);
                    return;
                }
                Throwable throwable0 = new HttpException(response0);
                Throwable throwable1 = AuthApiManager.Companion.translateError(throwable0);
                this.invoke(null, throwable1);
            }
        });
    }

    public static void issueAccessTokenWithCert$auth_release$default(AuthApiManager authApiManager0, String s, String s1, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        authApiManager0.issueAccessTokenWithCert$auth_release(s, s1, function20);
    }

    public final OAuthToken refreshToken$auth_release(OAuthToken oAuthToken0) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "oldToken");
        String s = this.applicationInfo.getAppKey();
        String s1 = this.contextInfo.getSigningKeyHash();
        Response response0 = DefaultImpls.refreshToken$default(this.authApi, s, s1, oAuthToken0.getRefreshToken(), this.approvalType.getValue(), null, 16, null).execute();
        AccessTokenResponse accessTokenResponse0 = (AccessTokenResponse)response0.body();
        OAuthToken oAuthToken1 = accessTokenResponse0 == null ? null : OAuthToken.Companion.fromResponse(accessTokenResponse0, oAuthToken0);
        if(oAuthToken1 != null) {
            this.tokenManagerProvider.getManager().setToken(oAuthToken1);
            return oAuthToken1;
        }
        Throwable throwable0 = new HttpException(response0);
        throw AuthApiManager.Companion.translateError(throwable0);
    }

    public final void refreshToken$auth_release(OAuthToken oAuthToken0, Function2 function20) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "oldToken");
        Intrinsics.checkNotNullParameter(function20, "callback");
        String s = this.applicationInfo.getAppKey();
        String s1 = this.contextInfo.getSigningKeyHash();
        DefaultImpls.refreshToken$default(this.authApi, s, s1, oAuthToken0.getRefreshToken(), this.approvalType.getValue(), null, 16, null).enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(throwable0, "t");
                oAuthToken0.invoke(null, throwable0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(response0, "response");
                if(response0.isSuccessful()) {
                    AccessTokenResponse accessTokenResponse0 = (AccessTokenResponse)response0.body();
                    if(accessTokenResponse0 == null) {
                        ClientError clientError0 = new ClientError(ClientErrorCause.Unknown, "No body");
                        oAuthToken0.invoke(null, clientError0);
                        return;
                    }
                    OAuthToken oAuthToken0 = OAuthToken.Companion.fromResponse(accessTokenResponse0, this);
                    AuthApiManager.this.getTokenManagerProvider().getManager().setToken(oAuthToken0);
                    oAuthToken0.invoke(oAuthToken0, null);
                    return;
                }
                Throwable throwable0 = new HttpException(response0);
                Throwable throwable1 = AuthApiManager.Companion.translateError(throwable0);
                oAuthToken0.invoke(null, throwable1);
            }
        });
    }

    public final void refreshToken$auth_release(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthApiManager.refreshToken$auth_release$default(this, null, function20, 1, null);
    }

    public static OAuthToken refreshToken$auth_release$default(AuthApiManager authApiManager0, OAuthToken oAuthToken0, int v, Object object0) {
        if((v & 1) != 0) {
            oAuthToken0 = authApiManager0.tokenManagerProvider.getManager().getToken();
            if(oAuthToken0 == null) {
                throw new ClientError(ClientErrorCause.TokenNotFound, "Refresh token not found. You must login first.");
            }
        }
        return authApiManager0.refreshToken$auth_release(oAuthToken0);
    }

    public static void refreshToken$auth_release$default(AuthApiManager authApiManager0, OAuthToken oAuthToken0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            oAuthToken0 = authApiManager0.tokenManagerProvider.getManager().getToken();
            if(oAuthToken0 == null) {
                throw new ClientError(ClientErrorCause.TokenNotFound, "Refresh token not found. You must login first.");
            }
        }
        authApiManager0.refreshToken$auth_release(oAuthToken0, function20);
    }
}

