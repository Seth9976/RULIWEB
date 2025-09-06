package com.kakao.sdk.auth;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001F2\u00020\u0001:\u0001\u001FB\u0019\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006JB\u0010\t\u001A\u00020\n2:\u0010\u000B\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\r\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\fJ\u0006\u0010\u0012\u001A\u00020\u0013JV\u0010\u0014\u001A\u00020\n2\u0006\u0010\u0015\u001A\u00020\r2\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\r2:\u0010\u000B\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0017\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\fJV\u0010\u0019\u001A\u00020\n2\u0006\u0010\u0015\u001A\u00020\r2\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\r2:\u0010\u000B\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u001A\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u001B\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\fJN\u0010\u001C\u001A\u00020\n2\b\b\u0002\u0010\u001D\u001A\u00020\u00172:\u0010\u000B\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0017\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\fH\u0007JN\u0010\u001E\u001A\u00020\n2\b\b\u0002\u0010\u001D\u001A\u00020\u00172:\u0010\u000B\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0017\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0010\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\n0\fH\u0007R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b\u00A8\u0006 "}, d2 = {"Lcom/kakao/sdk/auth/AuthApiClient;", "", "manager", "Lcom/kakao/sdk/auth/AuthApiManager;", "tokenManagerProvider", "Lcom/kakao/sdk/auth/TokenManagerProvider;", "(Lcom/kakao/sdk/auth/AuthApiManager;Lcom/kakao/sdk/auth/TokenManagerProvider;)V", "getTokenManagerProvider", "()Lcom/kakao/sdk/auth/TokenManagerProvider;", "agt", "", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "", "error", "hasToken", "", "issueAccessToken", "code", "codeVerifier", "Lcom/kakao/sdk/auth/model/OAuthToken;", "token", "issueAccessTokenWithCert", "Lcom/kakao/sdk/auth/model/CertTokenInfo;", "certTokenInfo", "refreshAccessToken", "oldToken", "refreshToken", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthApiClient {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001A\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001A\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/kakao/sdk/auth/AuthApiClient$Companion;", "", "()V", "instance", "Lcom/kakao/sdk/auth/AuthApiClient;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/auth/AuthApiClient;", "instance$delegate", "Lkotlin/Lazy;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/auth/AuthApiClient;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final AuthApiClient getInstance() {
            return (AuthApiClient)AuthApiClient.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }
    }

    public static final Companion Companion;
    private static final Lazy instance$delegate;
    private final AuthApiManager manager;
    private final TokenManagerProvider tokenManagerProvider;

    static {
        AuthApiClient.Companion = new Companion(null);
        AuthApiClient.instance$delegate = LazyKt.lazy(AuthApiClient.Companion.instance.2.INSTANCE);
    }

    public AuthApiClient() {
        this(null, null, 3, null);
    }

    public AuthApiClient(AuthApiManager authApiManager0, TokenManagerProvider tokenManagerProvider0) {
        Intrinsics.checkNotNullParameter(authApiManager0, "manager");
        Intrinsics.checkNotNullParameter(tokenManagerProvider0, "tokenManagerProvider");
        super();
        this.manager = authApiManager0;
        this.tokenManagerProvider = tokenManagerProvider0;
    }

    public AuthApiClient(AuthApiManager authApiManager0, TokenManagerProvider tokenManagerProvider0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            authApiManager0 = AuthApiManager.Companion.getInstance();
        }
        if((v & 2) != 0) {
            tokenManagerProvider0 = TokenManagerProvider.Companion.getInstance();
        }
        this(authApiManager0, tokenManagerProvider0);
    }

    public final void agt(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.manager.agt$auth_release(function20);
    }

    public static final AuthApiClient getInstance() {
        return AuthApiClient.Companion.getInstance();
    }

    public final TokenManagerProvider getTokenManagerProvider() {
        return this.tokenManagerProvider;
    }

    public final boolean hasToken() {
        return this.manager.hasToken$auth_release();
    }

    public final void issueAccessToken(String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(s, "code");
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.manager.issueAccessToken$auth_release(s, s1, function20);
    }

    public static void issueAccessToken$default(AuthApiClient authApiClient0, String s, String s1, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        authApiClient0.issueAccessToken(s, s1, function20);
    }

    public final void issueAccessTokenWithCert(String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(s, "code");
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.manager.issueAccessTokenWithCert$auth_release(s, s1, function20);
    }

    public static void issueAccessTokenWithCert$default(AuthApiClient authApiClient0, String s, String s1, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        authApiClient0.issueAccessTokenWithCert(s, s1, function20);
    }

    @Deprecated(message = "이 메서드는 더 이상 사용되지 않으므로 refreshToken()을 사용합니다.")
    public final void refreshAccessToken(OAuthToken oAuthToken0, Function2 function20) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "oldToken");
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.manager.refreshToken$auth_release(oAuthToken0, function20);
    }

    @Deprecated(message = "이 메서드는 더 이상 사용되지 않으므로 refreshToken()을 사용합니다.")
    public final void refreshAccessToken(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthApiClient.refreshAccessToken$default(this, null, function20, 1, null);
    }

    public static void refreshAccessToken$default(AuthApiClient authApiClient0, OAuthToken oAuthToken0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            oAuthToken0 = authApiClient0.tokenManagerProvider.getManager().getToken();
            if(oAuthToken0 == null) {
                throw new ClientError(ClientErrorCause.TokenNotFound, "Refresh token not found. You must login first.");
            }
        }
        authApiClient0.refreshAccessToken(oAuthToken0, function20);
    }

    public final void refreshToken(OAuthToken oAuthToken0, Function2 function20) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "oldToken");
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.manager.refreshToken$auth_release(oAuthToken0, function20);
    }

    public final void refreshToken(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthApiClient.refreshToken$default(this, null, function20, 1, null);
    }

    public static void refreshToken$default(AuthApiClient authApiClient0, OAuthToken oAuthToken0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            oAuthToken0 = authApiClient0.tokenManagerProvider.getManager().getToken();
            if(oAuthToken0 == null) {
                throw new ClientError(ClientErrorCause.TokenNotFound, "Refresh token not found. You must login first.");
            }
        }
        authApiClient0.refreshToken(oAuthToken0, function20);
    }
}

