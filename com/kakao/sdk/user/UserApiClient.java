package com.kakao.sdk.user;

import android.content.Context;
import com.kakao.sdk.auth.AuthApiClient;
import com.kakao.sdk.auth.AuthCodeClient;
import com.kakao.sdk.auth.TokenManagerProvider;
import com.kakao.sdk.auth.model.CertTokenInfo;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.auth.model.Prompt;
import com.kakao.sdk.auth.network.ApiFactoryKt;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.network.ApiCallback;
import com.kakao.sdk.network.ApiFactory;
import com.kakao.sdk.user.model.AccessTokenInfo;
import com.kakao.sdk.user.model.ScopeInfo;
import com.kakao.sdk.user.model.User;
import com.kakao.sdk.user.model.UserServiceTerms;
import com.kakao.sdk.user.model.UserShippingAddresses;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000\u00A8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\u0018\u0000 E2\u00020\u0001:\u0001EB\u0019\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006JB\u0010\u0007\u001A\u00020\b2:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u000B\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000E\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJ\u00A6\u0001\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u00132\u0010\b\u0002\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001A\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152\u0010\b\u0002\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152\n\b\u0002\u0010\u001C\u001A\u0004\u0018\u00010\u00182:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u001D\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001E\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007J\u00A4\u0001\u0010\u001F\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u00132\u0010\b\u0002\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001A\u0004\u0018\u00010\u00182\b\b\u0002\u0010 \u001A\u00020!2\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152\u0010\b\u0002\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u001D\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u001E\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007J\u000E\u0010\"\u001A\u00020#2\u0006\u0010\u0012\u001A\u00020\u0013J\u009A\u0001\u0010$\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u00132\u0010\b\u0002\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\n\b\u0002\u0010\u001C\u001A\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152\u0010\b\u0002\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010%\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007J\u0086\u0001\u0010\'\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u00132\b\b\u0002\u0010 \u001A\u00020!2\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152\u0010\b\u0002\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010%\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007Jd\u0010(\u001A\u00020\b2\u0006\u0010\u0012\u001A\u00020\u00132\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\u00180\u00152\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\u00182:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010%\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJ+\u0010*\u001A\u00020\b2#\u0010\t\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0+J\u001E\u0010,\u001A\b\u0012\u0004\u0012\u00020\u00160\u00152\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0002JN\u0010-\u001A\u00020\b2\b\b\u0002\u0010.\u001A\u00020#2:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010/\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(0\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007JP\u00101\u001A\u00020\b2\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\u00180\u00152:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u000102\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(3\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJT\u0010)\u001A\u00020\b2\u0010\b\u0002\u0010)\u001A\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00152:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u000102\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(3\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJN\u0010\u001B\u001A\u00020\b2\n\b\u0002\u00104\u001A\u0004\u0018\u00010\u00182:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u000105\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(6\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJa\u00107\u001A\u00020\b2\n\b\u0002\u00108\u001A\u0004\u0018\u0001092\n\b\u0002\u0010:\u001A\u0004\u0018\u00010!2:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010;\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nH\u0007\u00A2\u0006\u0002\u0010=JJ\u00107\u001A\u00020\b2\u0006\u0010>\u001A\u00020?2:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010;\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJC\u0010@\u001A\u00020\b2\u0016\b\u0002\u0010A\u001A\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0018\u0018\u00010B2#\u0010\t\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0+J+\u0010C\u001A\u00020\b2#\u0010\t\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0+J?\u0010D\u001A\u00020\b2\u0012\u0010A\u001A\u000E\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180B2#\u0010\t\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000F\u00A2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0+R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006F"}, d2 = {"Lcom/kakao/sdk/user/UserApiClient;", "", "userApi", "Lcom/kakao/sdk/user/UserApi;", "tokenManagerProvider", "Lcom/kakao/sdk/auth/TokenManagerProvider;", "(Lcom/kakao/sdk/user/UserApi;Lcom/kakao/sdk/auth/TokenManagerProvider;)V", "accessTokenInfo", "", "callback", "Lkotlin/Function2;", "Lcom/kakao/sdk/user/model/AccessTokenInfo;", "Lkotlin/ParameterName;", "name", "tokenInfo", "", "error", "certLoginWithKakaoAccount", "context", "Landroid/content/Context;", "prompts", "", "Lcom/kakao/sdk/auth/model/Prompt;", "state", "", "nonce", "channelPublicIds", "serviceTerms", "loginHint", "Lcom/kakao/sdk/auth/model/CertTokenInfo;", "certTokenInfo", "certLoginWithKakaoTalk", "requestCode", "", "isKakaoTalkLoginAvailable", "", "loginWithKakaoAccount", "Lcom/kakao/sdk/auth/model/OAuthToken;", "token", "loginWithKakaoTalk", "loginWithNewScopes", "scopes", "logout", "Lkotlin/Function1;", "makeCertPrompts", "me", "secureReSource", "Lcom/kakao/sdk/user/model/User;", "user", "revokeScopes", "Lcom/kakao/sdk/user/model/ScopeInfo;", "scopeInfo", "extra", "Lcom/kakao/sdk/user/model/UserServiceTerms;", "userServiceTerms", "shippingAddresses", "fromUpdatedAt", "Ljava/util/Date;", "pageSize", "Lcom/kakao/sdk/user/model/UserShippingAddresses;", "userShippingAddresses", "(Ljava/util/Date;Ljava/lang/Integer;Lkotlin/jvm/functions/Function2;)V", "addressId", "", "signup", "properties", "", "unlink", "updateProfile", "Companion", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class UserApiClient {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001A\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001A\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/kakao/sdk/user/UserApiClient$Companion;", "", "()V", "instance", "Lcom/kakao/sdk/user/UserApiClient;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/user/UserApiClient;", "instance$delegate", "Lkotlin/Lazy;", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/user/UserApiClient;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final UserApiClient getInstance() {
            return (UserApiClient)UserApiClient.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }
    }

    public static final Companion Companion;
    private static final Lazy instance$delegate;
    private final TokenManagerProvider tokenManagerProvider;
    private final UserApi userApi;

    static {
        UserApiClient.Companion = new Companion(null);
        UserApiClient.instance$delegate = LazyKt.lazy(UserApiClient.Companion.instance.2.INSTANCE);
    }

    public UserApiClient() {
        this(null, null, 3, null);
    }

    public UserApiClient(UserApi userApi0, TokenManagerProvider tokenManagerProvider0) {
        Intrinsics.checkNotNullParameter(userApi0, "userApi");
        Intrinsics.checkNotNullParameter(tokenManagerProvider0, "tokenManagerProvider");
        super();
        this.userApi = userApi0;
        this.tokenManagerProvider = tokenManagerProvider0;
    }

    public UserApiClient(UserApi userApi0, TokenManagerProvider tokenManagerProvider0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            Object object0 = ApiFactoryKt.getKapiWithOAuth(ApiFactory.INSTANCE).create(UserApi.class);
            Intrinsics.checkNotNullExpressionValue(object0, "ApiFactory.kapiWithOAuth.create(UserApi::class.java)");
            userApi0 = (UserApi)object0;
        }
        if((v & 2) != 0) {
            tokenManagerProvider0 = TokenManagerProvider.Companion.getInstance();
        }
        this(userApi0, tokenManagerProvider0);
    }

    public final void accessTokenInfo(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.userApi.accessTokenInfo().enqueue(new ApiCallback() {
            public void onComplete(AccessTokenInfo accessTokenInfo0, Throwable throwable0) {
                this.$callback.invoke(accessTokenInfo0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((AccessTokenInfo)object0), throwable0);
            }
        });
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, String s, String s1, List list1, List list2, String s2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        List list3 = this.makeCertPrompts(list0);
        AuthCodeClient.authorizeWithKakaoAccount$default(AuthCodeClient.Companion.getInstance(), context0, list3, s, null, s1, null, list1, list2, false, s2, null, "1hZ/rtzWhaB+DTewPUjr2y0ZS7eXPtiucg9wh9MDzHwnwncwue/1ETs03Vv3mvDRkzyH4XM/WplqzWKrKcqWNA", null, null, new Function2("1hZ/rtzWhaB+DTewPUjr2y0ZS7eXPtiucg9wh9MDzHwnwncwue/1ETs03Vv3mvDRkzyH4XM/WplqzWKrKcqWNA") {
            final Function2 $callback;
            final String $codeVerifier;

            {
                this.$callback = function20;
                this.$codeVerifier = s;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((String)object0), ((Throwable)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(String s, Throwable throwable0) {
                if(throwable0 != null) {
                    this.$callback.invoke(null, throwable0);
                    return;
                }
                AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                Intrinsics.checkNotNull(s);
                com.kakao.sdk.user.UserApiClient.certLoginWithKakaoAccount.1.1 userApiClient$certLoginWithKakaoAccount$1$10 = new Function2() {
                    final Function2 $callback;

                    {
                        this.$callback = function20;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        this.invoke(((CertTokenInfo)object0), ((Throwable)object1));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(CertTokenInfo certTokenInfo0, Throwable throwable0) {
                        this.$callback.invoke(certTokenInfo0, throwable0);
                    }
                };
                authApiClient0.issueAccessTokenWithCert(s, this.$codeVerifier, userApiClient$certLoginWithKakaoAccount$1$10);
            }
        }, 13608, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, String s, String s1, List list1, List list2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, list0, s, s1, list1, list2, null, function20, 0x40, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, String s, String s1, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, list0, s, s1, list1, null, null, function20, 0x60, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, list0, s, s1, null, null, null, function20, 0x70, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, list0, s, null, null, null, null, function20, 120, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, list0, null, null, null, null, null, function20, 0x7C, null);
    }

    public final void certLoginWithKakaoAccount(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoAccount$default(this, context0, null, null, null, null, null, null, function20, 0x7E, null);
    }

    public static void certLoginWithKakaoAccount$default(UserApiClient userApiClient0, Context context0, List list0, String s, String s1, List list1, List list2, String s2, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            list0 = null;
        }
        if((v & 4) != 0) {
            s = null;
        }
        if((v & 8) != 0) {
            s1 = null;
        }
        if((v & 16) != 0) {
            list1 = null;
        }
        if((v & 0x20) != 0) {
            list2 = null;
        }
        if((v & 0x40) != 0) {
            s2 = null;
        }
        userApiClient0.certLoginWithKakaoAccount(context0, list0, s, s1, list1, list2, s2, function20);
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, String s, int v, String s1, List list1, List list2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.Companion.getInstance().authorizeWithKakaoTalk(context0, this.makeCertPrompts(list0), s, v, s1, list1, list2, "6UXzMxkBlamT+0dOqTufSsAX/5TFX+KLT35nSfoi9CqPtFmoN5/Q+O3U0ATrKlM1ypFUBIizpFOfO17rS2HJNQ", new Function2("6UXzMxkBlamT+0dOqTufSsAX/5TFX+KLT35nSfoi9CqPtFmoN5/Q+O3U0ATrKlM1ypFUBIizpFOfO17rS2HJNQ") {
            final Function2 $callback;
            final String $codeVerifier;

            {
                this.$callback = function20;
                this.$codeVerifier = s;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((String)object0), ((Throwable)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(String s, Throwable throwable0) {
                if(throwable0 != null) {
                    this.$callback.invoke(null, throwable0);
                    return;
                }
                AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                Intrinsics.checkNotNull(s);
                com.kakao.sdk.user.UserApiClient.certLoginWithKakaoTalk.1.1 userApiClient$certLoginWithKakaoTalk$1$10 = new Function2() {
                    final Function2 $callback;

                    {
                        this.$callback = function20;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        this.invoke(((CertTokenInfo)object0), ((Throwable)object1));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(CertTokenInfo certTokenInfo0, Throwable throwable0) {
                        this.$callback.invoke(certTokenInfo0, throwable0);
                    }
                };
                authApiClient0.issueAccessTokenWithCert(s, this.$codeVerifier, userApiClient$certLoginWithKakaoTalk$1$10);
            }
        });
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, String s, int v, String s1, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, list0, s, v, s1, list1, null, function20, 0x40, null);
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, String s, int v, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, list0, s, v, s1, null, null, function20, 0x60, null);
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, String s, int v, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, list0, s, v, null, null, null, function20, 0x70, null);
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, list0, s, 0, null, null, null, function20, 120, null);
    }

    public final void certLoginWithKakaoTalk(Context context0, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, list0, null, 0, null, null, null, function20, 0x7C, null);
    }

    public final void certLoginWithKakaoTalk(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.certLoginWithKakaoTalk$default(this, context0, null, null, 0, null, null, null, function20, 0x7E, null);
    }

    public static void certLoginWithKakaoTalk$default(UserApiClient userApiClient0, Context context0, List list0, String s, int v, String s1, List list1, List list2, Function2 function20, int v1, Object object0) {
        if((v1 & 2) != 0) {
            list0 = null;
        }
        if((v1 & 4) != 0) {
            s = null;
        }
        if((v1 & 8) != 0) {
            v = 10012;
        }
        if((v1 & 16) != 0) {
            s1 = null;
        }
        if((v1 & 0x20) != 0) {
            list1 = null;
        }
        if((v1 & 0x40) != 0) {
            list2 = null;
        }
        userApiClient0.certLoginWithKakaoTalk(context0, list0, s, v, s1, list1, list2, function20);
    }

    public static final UserApiClient getInstance() {
        return UserApiClient.Companion.getInstance();
    }

    public final boolean isKakaoTalkLoginAvailable(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return AuthCodeClient.Companion.getInstance().isKakaoTalkLoginAvailable(context0);
    }

    public final void loginWithKakaoAccount(Context context0, List list0, String s, String s1, List list1, List list2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(AuthCodeClient.Companion.getInstance(), context0, list0, null, null, s1, null, list1, list2, false, s, null, "H0COZQEXy8yZpbakI+oxXKoB+ZEe+IrAOdfydZepBk+E93m3FhaUdmtVSvX0YXJb78FH+37LhJr7Q88Vphk4tQ", null, null, new Function2("H0COZQEXy8yZpbakI+oxXKoB+ZEe+IrAOdfydZepBk+E93m3FhaUdmtVSvX0YXJb78FH+37LhJr7Q88Vphk4tQ") {
            final Function2 $callback;
            final String $codeVerifier;

            {
                this.$callback = function20;
                this.$codeVerifier = s;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((String)object0), ((Throwable)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(String s, Throwable throwable0) {
                if(throwable0 != null) {
                    this.$callback.invoke(null, throwable0);
                    return;
                }
                AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                Intrinsics.checkNotNull(s);
                com.kakao.sdk.user.UserApiClient.loginWithKakaoAccount.1.1 userApiClient$loginWithKakaoAccount$1$10 = new Function2() {
                    final Function2 $callback;

                    {
                        this.$callback = function20;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        this.invoke(((OAuthToken)object0), ((Throwable)object1));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                        this.$callback.invoke(oAuthToken0, throwable0);
                    }
                };
                authApiClient0.issueAccessToken(s, this.$codeVerifier, userApiClient$loginWithKakaoAccount$1$10);
            }
        }, 0x352C, null);
    }

    public final void loginWithKakaoAccount(Context context0, List list0, String s, String s1, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoAccount$default(this, context0, list0, s, s1, list1, null, function20, 0x20, null);
    }

    public final void loginWithKakaoAccount(Context context0, List list0, String s, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoAccount$default(this, context0, list0, s, s1, null, null, function20, 0x30, null);
    }

    public final void loginWithKakaoAccount(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoAccount$default(this, context0, list0, s, null, null, null, function20, 56, null);
    }

    public final void loginWithKakaoAccount(Context context0, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoAccount$default(this, context0, list0, null, null, null, null, function20, 60, null);
    }

    public final void loginWithKakaoAccount(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoAccount$default(this, context0, null, null, null, null, null, function20, 62, null);
    }

    public static void loginWithKakaoAccount$default(UserApiClient userApiClient0, Context context0, List list0, String s, String s1, List list1, List list2, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            list0 = null;
        }
        if((v & 4) != 0) {
            s = null;
        }
        if((v & 8) != 0) {
            s1 = null;
        }
        if((v & 16) != 0) {
            list1 = null;
        }
        if((v & 0x20) != 0) {
            list2 = null;
        }
        userApiClient0.loginWithKakaoAccount(context0, list0, s, s1, list1, list2, function20);
    }

    public final void loginWithKakaoTalk(Context context0, int v, String s, List list0, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.Companion.getInstance().authorizeWithKakaoTalk(context0, null, null, v, s, list0, list1, "cp1HjilO2EG1mGudkoXZ/Y7wevyVpDs9zPxHsvbKyqPOvjIRHwV/+NphIbDlUX7gy0Fa9CPsl0GCi33ZRpdOjg", new Function2("cp1HjilO2EG1mGudkoXZ/Y7wevyVpDs9zPxHsvbKyqPOvjIRHwV/+NphIbDlUX7gy0Fa9CPsl0GCi33ZRpdOjg") {
            final Function2 $callback;
            final String $codeVerifier;

            {
                this.$callback = function20;
                this.$codeVerifier = s;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((String)object0), ((Throwable)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(String s, Throwable throwable0) {
                if(throwable0 != null) {
                    this.$callback.invoke(null, throwable0);
                    return;
                }
                AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                Intrinsics.checkNotNull(s);
                com.kakao.sdk.user.UserApiClient.loginWithKakaoTalk.1.1 userApiClient$loginWithKakaoTalk$1$10 = new Function2() {
                    final Function2 $callback;

                    {
                        this.$callback = function20;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        this.invoke(((OAuthToken)object0), ((Throwable)object1));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                        this.$callback.invoke(oAuthToken0, throwable0);
                    }
                };
                authApiClient0.issueAccessToken(s, this.$codeVerifier, userApiClient$loginWithKakaoTalk$1$10);
            }
        });
    }

    public final void loginWithKakaoTalk(Context context0, int v, String s, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoTalk$default(this, context0, v, s, list0, null, function20, 16, null);
    }

    public final void loginWithKakaoTalk(Context context0, int v, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoTalk$default(this, context0, v, s, null, null, function20, 24, null);
    }

    public final void loginWithKakaoTalk(Context context0, int v, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoTalk$default(this, context0, v, null, null, null, function20, 28, null);
    }

    public final void loginWithKakaoTalk(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.loginWithKakaoTalk$default(this, context0, 0, null, null, null, function20, 30, null);
    }

    public static void loginWithKakaoTalk$default(UserApiClient userApiClient0, Context context0, int v, String s, List list0, List list1, Function2 function20, int v1, Object object0) {
        userApiClient0.loginWithKakaoTalk(context0, ((v1 & 2) == 0 ? v : 10012), ((v1 & 4) == 0 ? s : null), ((v1 & 8) == 0 ? list0 : null), ((v1 & 16) == 0 ? list1 : null), function20);
    }

    public final void loginWithNewScopes(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(list0, "scopes");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthApiClient.Companion.getInstance().agt(new Function2(context0, list0, s) {
            final Function2 $callback;
            final Context $context;
            final String $nonce;
            final List $scopes;

            {
                this.$callback = function20;
                this.$context = context0;
                this.$scopes = list0;
                this.$nonce = s;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((String)object0), ((Throwable)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(String s, Throwable throwable0) {
                if(throwable0 != null) {
                    this.$callback.invoke(null, throwable0);
                    return;
                }
                AuthCodeClient authCodeClient0 = AuthCodeClient.Companion.getInstance();
                com.kakao.sdk.user.UserApiClient.loginWithNewScopes.1.1 userApiClient$loginWithNewScopes$1$10 = new Function2("6BXwkyI/Gf2IRoHlesO8gw/MkK6AQAA6ottShJYSgTz2Atwh7xRQWIKQsn7/E3Emztn4E4j1KFN9TD0TinCo2A") {
                    final Function2 $callback;
                    final String $codeVerifier;

                    {
                        this.$callback = function20;
                        this.$codeVerifier = s;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        this.invoke(((String)object0), ((Throwable)object1));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String s, Throwable throwable0) {
                        if(throwable0 != null) {
                            this.$callback.invoke(null, throwable0);
                            return;
                        }
                        AuthApiClient authApiClient0 = AuthApiClient.Companion.getInstance();
                        Intrinsics.checkNotNull(s);
                        com.kakao.sdk.user.UserApiClient.loginWithNewScopes.1.1.1 userApiClient$loginWithNewScopes$1$1$10 = new Function2() {
                            final Function2 $callback;

                            {
                                this.$callback = function20;
                                super(2);
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                this.invoke(((OAuthToken)object0), ((Throwable)object1));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                                this.$callback.invoke(oAuthToken0, throwable0);
                            }
                        };
                        authApiClient0.issueAccessToken(s, this.$codeVerifier, userApiClient$loginWithNewScopes$1$1$10);
                    }
                };
                AuthCodeClient.authorizeWithKakaoAccount$default(authCodeClient0, this.$context, null, null, this.$scopes, this.$nonce, s, null, null, false, null, null, "6BXwkyI/Gf2IRoHlesO8gw/MkK6AQAA6ottShJYSgTz2Atwh7xRQWIKQsn7/E3Emztn4E4j1KFN9TD0TinCo2A", null, null, userApiClient$loginWithNewScopes$1$10, 0x37C6, null);
            }
        });
    }

    public static void loginWithNewScopes$default(UserApiClient userApiClient0, Context context0, List list0, String s, Function2 function20, int v, Object object0) {
        if((v & 4) != 0) {
            s = null;
        }
        userApiClient0.loginWithNewScopes(context0, list0, s, function20);
    }

    public final void logout(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "callback");
        this.userApi.logout().enqueue(new ApiCallback() {
            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((Unit)object0), throwable0);
            }

            public void onComplete(Unit unit0, Throwable throwable0) {
                function10.tokenManagerProvider.getManager().clear();
                this.$callback.invoke(throwable0);
            }
        });
    }

    private final List makeCertPrompts(List list0) {
        List list1 = new ArrayList();
        if(list0 != null) {
            list1.addAll(list0);
        }
        list1.add(Prompt.CERT);
        return list1;
    }

    public final void me(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.me$default(this, false, function20, 1, null);
    }

    public final void me(boolean z, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        DefaultImpls.me$default(this.userApi, z, null, 2, null).enqueue(new ApiCallback() {
            public void onComplete(User user0, Throwable throwable0) {
                this.$callback.invoke(user0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((User)object0), throwable0);
            }
        });
    }

    public static void me$default(UserApiClient userApiClient0, boolean z, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            z = true;
        }
        userApiClient0.me(z, function20);
    }

    public final void revokeScopes(List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(list0, "scopes");
        Intrinsics.checkNotNullParameter(function20, "callback");
        String s = KakaoJson.INSTANCE.toJson(list0);
        this.userApi.revokeScopes(s).enqueue(new ApiCallback() {
            public void onComplete(ScopeInfo scopeInfo0, Throwable throwable0) {
                this.$callback.invoke(scopeInfo0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((ScopeInfo)object0), throwable0);
            }
        });
    }

    public final void scopes(List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        String s = list0 == null ? null : KakaoJson.INSTANCE.toJson(list0);
        this.userApi.scopes(s).enqueue(new ApiCallback() {
            public void onComplete(ScopeInfo scopeInfo0, Throwable throwable0) {
                this.$callback.invoke(scopeInfo0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((ScopeInfo)object0), throwable0);
            }
        });
    }

    public static void scopes$default(UserApiClient userApiClient0, List list0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            list0 = null;
        }
        userApiClient0.scopes(list0, function20);
    }

    public final void serviceTerms(String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        this.userApi.serviceTerms(s).enqueue(new ApiCallback() {
            public void onComplete(UserServiceTerms userServiceTerms0, Throwable throwable0) {
                this.$callback.invoke(userServiceTerms0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((UserServiceTerms)object0), throwable0);
            }
        });
    }

    public static void serviceTerms$default(UserApiClient userApiClient0, String s, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            s = null;
        }
        userApiClient0.serviceTerms(s, function20);
    }

    public final void shippingAddresses(long v, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        DefaultImpls.shippingAddresses$default(this.userApi, v, null, null, 6, null).enqueue(new ApiCallback() {
            public void onComplete(UserShippingAddresses userShippingAddresses0, Throwable throwable0) {
                this.$callback.invoke(userShippingAddresses0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((UserShippingAddresses)object0), throwable0);
            }
        });
    }

    public final void shippingAddresses(Date date0, Integer integer0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        DefaultImpls.shippingAddresses$default(this.userApi, null, date0, integer0, 1, null).enqueue(new ApiCallback() {
            public void onComplete(UserShippingAddresses userShippingAddresses0, Throwable throwable0) {
                this.$callback.invoke(userShippingAddresses0, throwable0);
            }

            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((UserShippingAddresses)object0), throwable0);
            }
        });
    }

    public final void shippingAddresses(Date date0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.shippingAddresses$default(this, date0, null, function20, 2, null);
    }

    public final void shippingAddresses(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        UserApiClient.shippingAddresses$default(this, null, null, function20, 3, null);
    }

    public static void shippingAddresses$default(UserApiClient userApiClient0, Date date0, Integer integer0, Function2 function20, int v, Object object0) {
        if((v & 1) != 0) {
            date0 = null;
        }
        if((v & 2) != 0) {
            integer0 = null;
        }
        userApiClient0.shippingAddresses(date0, integer0, function20);
    }

    public final void signup(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "callback");
        this.userApi.signup(map0).enqueue(new ApiCallback() {
            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((Unit)object0), throwable0);
            }

            public void onComplete(Unit unit0, Throwable throwable0) {
                this.$callback.invoke(throwable0);
            }
        });
    }

    public static void signup$default(UserApiClient userApiClient0, Map map0, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            map0 = null;
        }
        userApiClient0.signup(map0, function10);
    }

    public final void unlink(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "callback");
        this.userApi.unlink().enqueue(new ApiCallback() {
            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((Unit)object0), throwable0);
            }

            public void onComplete(Unit unit0, Throwable throwable0) {
                if(throwable0 == null) {
                    function10.tokenManagerProvider.getManager().clear();
                }
                this.$callback.invoke(throwable0);
            }
        });
    }

    public final void updateProfile(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "properties");
        Intrinsics.checkNotNullParameter(function10, "callback");
        this.userApi.updateProfile(map0).enqueue(new ApiCallback() {
            @Override  // com.kakao.sdk.network.ApiCallback
            public void onComplete(Object object0, Throwable throwable0) {
                this.onComplete(((Unit)object0), throwable0);
            }

            public void onComplete(Unit unit0, Throwable throwable0) {
                this.$callback.invoke(throwable0);
            }
        });
    }
}

