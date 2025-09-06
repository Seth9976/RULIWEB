package com.navercorp.nid.oauth;

import android.app.Activity;
import android.content.Context;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.exception.NoConnectivityException;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.oauth.api.NidOAuthApi;
import com.navercorp.nid.oauth.data.NidOAuthResponse;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.api.NidProfileApi;
import com.navercorp.nid.profile.data.NidProfile;
import com.navercorp.nid.profile.data.NidProfileMap;
import com.navercorp.nid.profile.data.NidProfileResponse;
import com.navercorp.nid.progress.NidProgressDialog;
import com.nhn.android.oauth.R.string;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import retrofit2.Response;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bJ\u000E\u0010\t\u001A\u00020\n2\u0006\u0010\u0007\u001A\u00020\bJ\u0014\u0010\u000B\u001A\u00020\n2\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\r0\fJ\u000E\u0010\u000E\u001A\u00020\n2\u0006\u0010\u0007\u001A\u00020\bJ\u0010\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0011H\u0002J\u0010\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0012\u001A\u00020\u0013H\u0002J\u0014\u0010\u0014\u001A\u00020\n2\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00150\fJ\u0011\u0010\u0016\u001A\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001B\u0010\u0019\u001A\u0004\u0018\u00010\u001A2\u0006\u0010\u0005\u001A\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001BJ#\u0010\u0019\u001A\u0004\u0018\u00010\u001A2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001CJ\u001B\u0010\u001D\u001A\u0004\u0018\u00010\u001E2\u0006\u0010\u0007\u001A\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001F\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthLogin;", "", "()V", "accessToken", "", "context", "Landroid/content/Context;", "callback", "Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "callDeleteTokenApi", "Lkotlinx/coroutines/Job;", "callProfileApi", "Lcom/navercorp/nid/profile/NidProfileCallback;", "Lcom/navercorp/nid/profile/data/NidProfileResponse;", "callRefreshAccessTokenApi", "errorHandling", "errorCode", "", "throwable", "", "getProfileMap", "Lcom/navercorp/nid/profile/data/NidProfileMap;", "refreshToken", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestAccessToken", "Lcom/navercorp/nid/oauth/data/NidOAuthResponse;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroid/content/Context;Lcom/navercorp/nid/oauth/OAuthLoginCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestRefreshAccessToken", "", "(Lcom/navercorp/nid/oauth/OAuthLoginCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthLogin {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthLogin$Companion;", "", "()V", "TAG", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final String TAG = "NidOAuthLogin";

    static {
        NidOAuthLogin.Companion = new Companion(null);
    }

    public final void accessToken(Context context0, OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        NidProgressDialog nidProgressDialog0 = new NidProgressDialog(context0);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(oAuthLoginCallback0, this, context0, null) {
            final OAuthLoginCallback $callback;
            final Context $context;
            final NidProgressDialog $progressDialog;
            int label;

            {
                this.$progressDialog = nidProgressDialog0;
                this.$callback = oAuthLoginCallback0;
                NidOAuthLogin.this = nidOAuthLogin0;
                this.$context = context0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.accessToken.1(this.$progressDialog, this.$callback, NidOAuthLogin.this, this.$context, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.accessToken.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                NidOAuthResponse nidOAuthResponse0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.$progressDialog.showProgress(string.naveroauthlogin_string_getting_token);
                        OAuthLoginCallback oAuthLoginCallback0 = this.$callback;
                        if(oAuthLoginCallback0 == null) {
                            this.label = 1;
                            object0 = NidOAuthLogin.this.requestAccessToken(this.$context, this);
                            if(object0 == object1) {
                                return object1;
                            }
                        }
                        else {
                            this.label = 2;
                            object0 = NidOAuthLogin.this.requestAccessToken(this.$context, oAuthLoginCallback0, this);
                            if(object0 == object1) {
                                return object1;
                            }
                        }
                        nidOAuthResponse0 = (NidOAuthResponse)object0;
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        nidOAuthResponse0 = (NidOAuthResponse)object0;
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        nidOAuthResponse0 = (NidOAuthResponse)object0;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                this.$progressDialog.hideProgress();
                if(nidOAuthResponse0 != null) {
                    CharSequence charSequence0 = nidOAuthResponse0.getError();
                    if(charSequence0 != null && charSequence0.length() != 0) {
                    label_29:
                        NidOAuthErrorCode nidOAuthErrorCode0 = NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError());
                        if(NidOAuthErrorCode.NONE == nidOAuthErrorCode0) {
                            NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.CLIENT_USER_CANCEL);
                            NidOAuthPreferencesManager.setLastErrorDesc("user_cancel");
                        }
                    }
                    else {
                        CharSequence charSequence1 = nidOAuthResponse0.getAccessToken();
                        if(charSequence1 == null || charSequence1.length() == 0) {
                            goto label_29;
                        }
                    }
                }
                if(this.$context instanceof Activity && !((Activity)this.$context).isFinishing()) {
                    ((Activity)this.$context).finish();
                }
                return Unit.INSTANCE;
            }
        }, 3, null);
    }

    public final Job callDeleteTokenApi(OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(oAuthLoginCallback0, "callback");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(oAuthLoginCallback0, null) {
            final OAuthLoginCallback $callback;
            int label;

            {
                NidOAuthLogin.this = nidOAuthLogin0;
                this.$callback = oAuthLoginCallback0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.callDeleteTokenApi.1(NidOAuthLogin.this, this.$callback, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.callDeleteTokenApi.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Response response0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            com.navercorp.nid.oauth.NidOAuthLogin.callDeleteTokenApi.1.1 nidOAuthLogin$callDeleteTokenApi$1$10 = new Function2() {
                                int label;

                                {
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new com.navercorp.nid.oauth.NidOAuthLogin.callDeleteTokenApi.1.1(continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((com.navercorp.nid.oauth.NidOAuthLogin.callDeleteTokenApi.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            NidOAuthApi nidOAuthApi0 = new NidOAuthApi();
                                            this.label = 1;
                                            Object object2 = nidOAuthApi0.deleteToken(this);
                                            return object2 == object1 ? object1 : object2;
                                        }
                                        case 1: {
                                            ResultKt.throwOnFailure(object0);
                                            return object0;
                                        }
                                        default: {
                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                        }
                                    }
                                }
                            };
                            this.label = 1;
                            object0 = BuildersKt.withContext(Dispatchers.getIO(), nidOAuthLogin$callDeleteTokenApi$1$10, this);
                            if(object0 == object1) {
                                return object1;
                            label_9:
                                ResultKt.throwOnFailure(object0);
                            }
                            response0 = (Response)object0;
                            goto label_20;
                        }
                        catch(Throwable throwable0) {
                        }
                        break;
                    }
                    case 1: {
                        goto label_9;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                try {
                    NidOAuthLogin.this.errorHandling(throwable0);
                    this.$callback.onError(-1, throwable0.toString());
                    return Unit.INSTANCE;
                }
                finally {
                    NaverIdLoginSDK.INSTANCE.logout();
                }
            label_20:
                NaverIdLoginSDK.INSTANCE.logout();
                BooleanRef ref$BooleanRef0 = new BooleanRef();
                NidOAuthResponse nidOAuthResponse0 = (NidOAuthResponse)response0.body();
                if(nidOAuthResponse0 != null && StringsKt.equals("success", nidOAuthResponse0.getResult(), true)) {
                    ref$BooleanRef0.element = true;
                }
                int v1 = response0.code();
                if(200 <= v1 && v1 < 300) {
                    NidOAuthResponse nidOAuthResponse1 = (NidOAuthResponse)response0.body();
                    if(nidOAuthResponse1 != null && !ref$BooleanRef0.element) {
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse1.getError()));
                        NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse1.getErrorDescription() == null ? "" : nidOAuthResponse1.getErrorDescription()));
                    }
                    if(ref$BooleanRef0.element) {
                        this.$callback.onSuccess();
                        return Unit.INSTANCE;
                    }
                    int v2 = response0.code();
                    String s = response0.message();
                    Intrinsics.checkNotNullExpressionValue(s, "response.message()");
                    this.$callback.onFailure(v2, s);
                    return Unit.INSTANCE;
                }
                if(400 <= v1 && v1 < 500) {
                    int v3 = response0.code();
                    String s1 = response0.message();
                    Intrinsics.checkNotNullExpressionValue(s1, "response.message()");
                    this.$callback.onFailure(v3, s1);
                    return Unit.INSTANCE;
                }
                int v4 = response0.code();
                NidOAuthLogin.this.errorHandling(v4);
                int v5 = response0.code();
                String s2 = response0.message();
                Intrinsics.checkNotNullExpressionValue(s2, "response.message()");
                this.$callback.onError(v5, s2);
                return Unit.INSTANCE;
            }
        }, 3, null);
    }

    public final Job callProfileApi(NidProfileCallback nidProfileCallback0) {
        Intrinsics.checkNotNullParameter(nidProfileCallback0, "callback");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(nidProfileCallback0, null) {
            final NidProfileCallback $callback;
            int label;

            {
                NidOAuthLogin.this = nidOAuthLogin0;
                this.$callback = nidProfileCallback0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.callProfileApi.1(NidOAuthLogin.this, this.$callback, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.callProfileApi.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                String s1;
                Response response0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                NidProfile nidProfile0 = null;
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            Function2 function20 = new Function2() {
                                int label;

                                {
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new com.navercorp.nid.oauth.NidOAuthLogin.callProfileApi.1.1(continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((com.navercorp.nid.oauth.NidOAuthLogin.callProfileApi.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            NidProfileApi nidProfileApi0 = new NidProfileApi();
                                            this.label = 1;
                                            Object object2 = nidProfileApi0.requestApi(this);
                                            return object2 == object1 ? object1 : object2;
                                        }
                                        case 1: {
                                            ResultKt.throwOnFailure(object0);
                                            return object0;
                                        }
                                        default: {
                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                        }
                                    }
                                }
                            };
                            this.label = 1;
                            object0 = BuildersKt.withContext(Dispatchers.getIO(), function20, this);
                            if(object0 == object1) {
                                return object1;
                            label_10:
                                ResultKt.throwOnFailure(object0);
                            }
                            response0 = (Response)object0;
                            goto label_17;
                        }
                        catch(Throwable throwable0) {
                        }
                        break;
                    }
                    case 1: {
                        goto label_10;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                NidOAuthLogin.this.errorHandling(throwable0);
                this.$callback.onError(-1, throwable0.toString());
                return Unit.INSTANCE;
            label_17:
                NidProfileResponse nidProfileResponse0 = (NidProfileResponse)response0.body();
                int v = response0.code();
                if(200 <= v && v < 300) {
                    if(nidProfileResponse0 != null) {
                        nidProfile0 = nidProfileResponse0.getProfile();
                    }
                    if(nidProfile0 != null) {
                        CharSequence charSequence0 = nidProfileResponse0.getProfile().getId();
                        if(charSequence0 != null && charSequence0.length() != 0) {
                            this.$callback.onSuccess(nidProfileResponse0);
                            return Unit.INSTANCE;
                        }
                    }
                    NidProfileCallback nidProfileCallback0 = this.$callback;
                    int v1 = response0.code();
                    String s = "";
                    if(nidProfileResponse0 == null) {
                        s1 = "";
                    }
                    else {
                        s1 = nidProfileResponse0.getResultCode();
                        if(s1 == null) {
                            s1 = "";
                        }
                    }
                    if(nidProfileResponse0 != null) {
                        String s2 = nidProfileResponse0.getMessage();
                        if(s2 != null) {
                            s = s2;
                        }
                    }
                    nidProfileCallback0.onFailure(v1, s1 + " " + s);
                    return Unit.INSTANCE;
                }
                if(400 <= v && v < 500) {
                    int v2 = response0.code();
                    String s3 = response0.message();
                    Intrinsics.checkNotNullExpressionValue(s3, "response.message()");
                    this.$callback.onFailure(v2, s3);
                    return Unit.INSTANCE;
                }
                int v3 = response0.code();
                NidOAuthLogin.this.errorHandling(v3);
                int v4 = response0.code();
                String s4 = response0.message();
                Intrinsics.checkNotNullExpressionValue(s4, "response.message()");
                this.$callback.onError(v4, s4);
                return Unit.INSTANCE;
            }
        }, 3, null);
    }

    public final Job callRefreshAccessTokenApi(OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(oAuthLoginCallback0, "callback");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(oAuthLoginCallback0, null) {
            final OAuthLoginCallback $callback;
            int label;

            {
                NidOAuthLogin.this = nidOAuthLogin0;
                this.$callback = oAuthLoginCallback0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.callRefreshAccessTokenApi.1(NidOAuthLogin.this, this.$callback, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.callRefreshAccessTokenApi.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return NidOAuthLogin.this.requestRefreshAccessToken(this.$callback, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }, 3, null);
    }

    private final void errorHandling(int v) {
        switch(v) {
            case 500: {
                NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.SERVER_ERROR_SERVER_ERROR);
                NidOAuthPreferencesManager.setLastErrorDesc("server_error");
                return;
            }
            case 503: {
                NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.SERVER_ERROR_TEMPORARILY_UNAVAILABLE);
                NidOAuthPreferencesManager.setLastErrorDesc("temporarily_unavailable");
                return;
            }
            default: {
                NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.ERROR_NO_CATAGORIZED);
                NidOAuthPreferencesManager.setLastErrorDesc("no_catagorized_error");
            }
        }
    }

    private final void errorHandling(Throwable throwable0) {
        boolean z = true;
        if((((throwable0 instanceof NoConnectivityException ? true : throwable0 instanceof IOException) ? true : throwable0 instanceof SocketTimeoutException) ? true : throwable0 instanceof SocketException)) {
            NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.CLIENT_ERROR_CONNECTION_ERROR);
            NidOAuthPreferencesManager.setLastErrorDesc("connection_error");
        }
        else {
            if(!(((throwable0 instanceof SSLPeerUnverifiedException ? true : throwable0 instanceof SSLProtocolException) ? true : throwable0 instanceof SSLKeyException) ? true : throwable0 instanceof SSLHandshakeException)) {
                z = throwable0 instanceof SSLException;
            }
            if(z) {
                NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.CLIENT_ERROR_CERTIFICATION_ERROR);
                NidOAuthPreferencesManager.setLastErrorDesc("certification_error");
            }
            else {
                NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.ERROR_NO_CATAGORIZED);
                NidOAuthPreferencesManager.setLastErrorDesc("no_catagorized_error");
            }
        }
        NidLog.e("NidOAuthLogin", String.valueOf(throwable0));
    }

    public final Job getProfileMap(NidProfileCallback nidProfileCallback0) {
        Intrinsics.checkNotNullParameter(nidProfileCallback0, "callback");
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(nidProfileCallback0, null) {
            final NidProfileCallback $callback;
            int label;

            {
                NidOAuthLogin.this = nidOAuthLogin0;
                this.$callback = nidProfileCallback0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.getProfileMap.1(NidOAuthLogin.this, this.$callback, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.getProfileMap.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                String s2;
                Object object2;
                Response response0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                Map map0 = null;
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            Function2 function20 = new Function2() {
                                int label;

                                {
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    return new com.navercorp.nid.oauth.NidOAuthLogin.getProfileMap.1.1(continuation0);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                    return ((com.navercorp.nid.oauth.NidOAuthLogin.getProfileMap.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            NidProfileApi nidProfileApi0 = new NidProfileApi();
                                            this.label = 1;
                                            Object object2 = nidProfileApi0.getNidProfileMap(this);
                                            return object2 == object1 ? object1 : object2;
                                        }
                                        case 1: {
                                            ResultKt.throwOnFailure(object0);
                                            return object0;
                                        }
                                        default: {
                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                        }
                                    }
                                }
                            };
                            this.label = 1;
                            object0 = BuildersKt.withContext(Dispatchers.getIO(), function20, this);
                            if(object0 == object1) {
                                return object1;
                            label_10:
                                ResultKt.throwOnFailure(object0);
                            }
                            response0 = (Response)object0;
                            goto label_17;
                        }
                        catch(Throwable throwable0) {
                        }
                        break;
                    }
                    case 1: {
                        goto label_10;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                NidOAuthLogin.this.errorHandling(throwable0);
                this.$callback.onError(-1, throwable0.toString());
                return Unit.INSTANCE;
            label_17:
                NidProfileMap nidProfileMap0 = (NidProfileMap)response0.body();
                int v = response0.code();
                if(200 <= v && v < 300) {
                    if(nidProfileMap0 == null) {
                        object2 = null;
                    }
                    else {
                        Map map1 = nidProfileMap0.getProfile();
                        object2 = map1 == null ? null : map1.get("id");
                    }
                    String s = object2 instanceof String ? ((String)object2) : null;
                    if(nidProfileMap0 != null) {
                        map0 = nidProfileMap0.getProfile();
                    }
                    if(map0 != null && s != null && s.length() != 0) {
                        this.$callback.onSuccess(nidProfileMap0);
                        return Unit.INSTANCE;
                    }
                    NidProfileCallback nidProfileCallback0 = this.$callback;
                    int v1 = response0.code();
                    String s1 = "";
                    if(nidProfileMap0 == null) {
                        s2 = "";
                    }
                    else {
                        s2 = nidProfileMap0.getResultCode();
                        if(s2 == null) {
                            s2 = "";
                        }
                    }
                    if(nidProfileMap0 != null) {
                        String s3 = nidProfileMap0.getMessage();
                        if(s3 != null) {
                            s1 = s3;
                        }
                    }
                    nidProfileCallback0.onFailure(v1, "resultCode : " + s2 + ", message : " + s1);
                    return Unit.INSTANCE;
                }
                if(400 <= v && v < 500) {
                    int v2 = response0.code();
                    String s4 = response0.message();
                    Intrinsics.checkNotNullExpressionValue(s4, "response.message()");
                    this.$callback.onFailure(v2, s4);
                    return Unit.INSTANCE;
                }
                int v3 = response0.code();
                NidOAuthLogin.this.errorHandling(v3);
                int v4 = response0.code();
                String s5 = response0.message();
                Intrinsics.checkNotNullExpressionValue(s5, "response.message()");
                this.$callback.onError(v4, s5);
                return Unit.INSTANCE;
            }
        }, 3, null);
    }

    public final Object refreshToken(Continuation continuation0) {
        return BuildersKt.withContext(Dispatchers.getMain(), new Function2(null) {
            int label;

            {
                NidOAuthLogin.this = nidOAuthLogin0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.NidOAuthLogin.refreshToken.2(NidOAuthLogin.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.NidOAuthLogin.refreshToken.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        OAuthLoginCallback oAuthLoginCallback0 = new com.navercorp.nid.oauth.NidOAuthLogin.refreshToken.2.accessToken.1();
                        this.label = 1;
                        object0 = NidOAuthLogin.this.requestRefreshAccessToken(oAuthLoginCallback0, this);
                        if(object0 == object1) {
                            return object1;
                        }
                        return ((String)object0) == null || ((String)object0).length() == 0 ? Boxing.boxBoolean(((boolean)0)) : Boxing.boxBoolean(((boolean)1));
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return ((String)object0) == null || ((String)object0).length() == 0 ? Boxing.boxBoolean(((boolean)0)) : Boxing.boxBoolean(((boolean)1));
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }

                @Metadata(d1 = {"\u0000\u001F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u0018\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\b\u0010\n\u001A\u00020\u0003H\u0016¨\u0006\u000B"}, d2 = {"com/navercorp/nid/oauth/NidOAuthLogin$refreshToken$2$accessToken$1", "Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "onError", "", "errorCode", "", "message", "", "onFailure", "httpStatus", "onSuccess", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
                public final class com.navercorp.nid.oauth.NidOAuthLogin.refreshToken.2.accessToken.1 implements OAuthLoginCallback {
                    @Override  // com.navercorp.nid.oauth.OAuthLoginCallback
                    public void onError(int v, String s) {
                        Intrinsics.checkNotNullParameter(s, "message");
                        NidLog.d("NidOAuthLogin", "requestRefreshAccessToken | onError()");
                    }

                    @Override  // com.navercorp.nid.oauth.OAuthLoginCallback
                    public void onFailure(int v, String s) {
                        Intrinsics.checkNotNullParameter(s, "message");
                        NidLog.d("NidOAuthLogin", "requestRefreshAccessToken | onFailure()");
                    }

                    @Override  // com.navercorp.nid.oauth.OAuthLoginCallback
                    public void onSuccess() {
                        NidLog.d("NidOAuthLogin", "requestRefreshAccessToken | onSuccess()");
                    }
                }

            }
        }, continuation0);
    }

    private final Object requestAccessToken(Context context0, OAuthLoginCallback oAuthLoginCallback0, Continuation continuation0) {
        boolean z;
        Response response0;
        NidOAuthLogin nidOAuthLogin0;
        com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.4 nidOAuthLogin$requestAccessToken$40;
        if(continuation0 instanceof com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.4) {
            nidOAuthLogin$requestAccessToken$40 = (com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.4)continuation0;
            if((nidOAuthLogin$requestAccessToken$40.label & 0x80000000) == 0) {
                nidOAuthLogin$requestAccessToken$40 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.requestAccessToken(null, null, this);
                    }
                };
            }
            else {
                nidOAuthLogin$requestAccessToken$40.label ^= 0x80000000;
            }
        }
        else {
            nidOAuthLogin$requestAccessToken$40 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.requestAccessToken(null, null, this);
                }
            };
        }
        Object object0 = nidOAuthLogin$requestAccessToken$40.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(nidOAuthLogin$requestAccessToken$40.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    Function2 function20 = new Function2() {
                        int label;

                        {
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            return new com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.5(continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                            return ((com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.5)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    NidOAuthApi nidOAuthApi0 = new NidOAuthApi();
                                    this.label = 1;
                                    Object object2 = nidOAuthApi0.requestAccessToken(this);
                                    return object2 == object1 ? object1 : object2;
                                }
                                case 1: {
                                    ResultKt.throwOnFailure(object0);
                                    return object0;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                        }
                    };
                    nidOAuthLogin$requestAccessToken$40.L$0 = this;
                    nidOAuthLogin$requestAccessToken$40.L$1 = oAuthLoginCallback0;
                    nidOAuthLogin$requestAccessToken$40.label = 1;
                    object0 = BuildersKt.withContext(Dispatchers.getIO(), function20, nidOAuthLogin$requestAccessToken$40);
                }
                catch(Throwable throwable0) {
                    nidOAuthLogin0 = this;
                    break;
                }
                if(object0 == object1) {
                    return object1;
                }
                nidOAuthLogin0 = this;
                response0 = (Response)object0;
                goto label_35;
            }
            case 1: {
                oAuthLoginCallback0 = (OAuthLoginCallback)nidOAuthLogin$requestAccessToken$40.L$1;
                nidOAuthLogin0 = (NidOAuthLogin)nidOAuthLogin$requestAccessToken$40.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    response0 = (Response)object0;
                    goto label_35;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        nidOAuthLogin0.errorHandling(throwable0);
        oAuthLoginCallback0.onError(-1, throwable0.toString());
        return null;
    label_35:
        int v = response0.code();
        if(200 <= v && v < 300) {
            NidOAuthResponse nidOAuthResponse0 = (NidOAuthResponse)response0.body();
            if(nidOAuthResponse0 == null) {
                z = false;
            }
            else {
                CharSequence charSequence0 = nidOAuthResponse0.getError();
                if(charSequence0 != null && charSequence0.length() != 0) {
                    NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                    NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                    z = false;
                }
                else {
                    CharSequence charSequence1 = nidOAuthResponse0.getAccessToken();
                    if(charSequence1 == null || charSequence1.length() == 0) {
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                        NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                        z = false;
                    }
                    else {
                        NidOAuthPreferencesManager.setAccessToken(nidOAuthResponse0.getAccessToken());
                        NidOAuthPreferencesManager.setRefreshToken(nidOAuthResponse0.getRefreshToken());
                        NidOAuthPreferencesManager.setExpiresAt(System.currentTimeMillis() / 1000L + nidOAuthResponse0.getExpiresIn());
                        NidOAuthPreferencesManager.setTokenType(nidOAuthResponse0.getTokenType());
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.NONE);
                        NidOAuthPreferencesManager.setLastErrorDesc("");
                        z = true;
                    }
                }
            }
            if(z) {
                oAuthLoginCallback0.onSuccess();
                return response0.body();
            }
            int v1 = response0.code();
            String s = response0.message();
            Intrinsics.checkNotNullExpressionValue(s, "response.message()");
            oAuthLoginCallback0.onFailure(v1, s);
            return response0.body();
        }
        if(400 <= v && v < 500) {
            int v2 = response0.code();
            String s1 = response0.message();
            Intrinsics.checkNotNullExpressionValue(s1, "response.message()");
            oAuthLoginCallback0.onFailure(v2, s1);
            return response0.body();
        }
        nidOAuthLogin0.errorHandling(response0.code());
        int v3 = response0.code();
        String s2 = response0.message();
        Intrinsics.checkNotNullExpressionValue(s2, "response.message()");
        oAuthLoginCallback0.onError(v3, s2);
        return response0.body();
    }

    private final Object requestAccessToken(Context context0, Continuation continuation0) {
        boolean z;
        Response response0;
        NidOAuthLogin nidOAuthLogin0;
        com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.1 nidOAuthLogin$requestAccessToken$10;
        if(continuation0 instanceof com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.1) {
            nidOAuthLogin$requestAccessToken$10 = (com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.1)continuation0;
            if((nidOAuthLogin$requestAccessToken$10.label & 0x80000000) == 0) {
                nidOAuthLogin$requestAccessToken$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.requestAccessToken(null, this);
                    }
                };
            }
            else {
                nidOAuthLogin$requestAccessToken$10.label ^= 0x80000000;
            }
        }
        else {
            nidOAuthLogin$requestAccessToken$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.requestAccessToken(null, this);
                }
            };
        }
        Object object0 = nidOAuthLogin$requestAccessToken$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(nidOAuthLogin$requestAccessToken$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    Function2 function20 = new Function2() {
                        int label;

                        {
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            return new com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.2(continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                            return ((com.navercorp.nid.oauth.NidOAuthLogin.requestAccessToken.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    NidOAuthApi nidOAuthApi0 = new NidOAuthApi();
                                    this.label = 1;
                                    Object object2 = nidOAuthApi0.requestAccessToken(this);
                                    return object2 == object1 ? object1 : object2;
                                }
                                case 1: {
                                    ResultKt.throwOnFailure(object0);
                                    return object0;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                        }
                    };
                    nidOAuthLogin$requestAccessToken$10.L$0 = this;
                    nidOAuthLogin$requestAccessToken$10.L$1 = context0;
                    nidOAuthLogin$requestAccessToken$10.label = 1;
                    object0 = BuildersKt.withContext(Dispatchers.getIO(), function20, nidOAuthLogin$requestAccessToken$10);
                }
                catch(Throwable throwable0) {
                    nidOAuthLogin0 = this;
                    break;
                }
                if(object0 == object1) {
                    return object1;
                }
                nidOAuthLogin0 = this;
                response0 = (Response)object0;
                goto label_36;
            }
            case 1: {
                context0 = (Context)nidOAuthLogin$requestAccessToken$10.L$1;
                nidOAuthLogin0 = (NidOAuthLogin)nidOAuthLogin$requestAccessToken$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    response0 = (Response)object0;
                    goto label_36;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        nidOAuthLogin0.errorHandling(throwable0);
        if(context0 instanceof Activity) {
            ((Activity)context0).setResult(0);
        }
        return null;
    label_36:
        int v = response0.code();
        if(200 <= v && v < 300) {
            NidOAuthResponse nidOAuthResponse0 = (NidOAuthResponse)response0.body();
            if(nidOAuthResponse0 == null) {
                z = false;
            }
            else {
                CharSequence charSequence0 = nidOAuthResponse0.getError();
                if(charSequence0 != null && charSequence0.length() != 0) {
                    NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                    NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                    z = false;
                }
                else {
                    CharSequence charSequence1 = nidOAuthResponse0.getAccessToken();
                    if(charSequence1 == null || charSequence1.length() == 0) {
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                        NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                        z = false;
                    }
                    else {
                        NidOAuthPreferencesManager.setAccessToken(nidOAuthResponse0.getAccessToken());
                        NidOAuthPreferencesManager.setRefreshToken(nidOAuthResponse0.getRefreshToken());
                        NidOAuthPreferencesManager.setExpiresAt(System.currentTimeMillis() / 1000L + nidOAuthResponse0.getExpiresIn());
                        NidOAuthPreferencesManager.setTokenType(nidOAuthResponse0.getTokenType());
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.NONE);
                        NidOAuthPreferencesManager.setLastErrorDesc("");
                        z = true;
                    }
                }
            }
            if(z) {
                if(context0 instanceof Activity) {
                    ((Activity)context0).setResult(-1);
                    return response0.body();
                }
            }
            else if(context0 instanceof Activity) {
                ((Activity)context0).setResult(0);
                return response0.body();
            }
        }
        else if(400 > v || v >= 500) {
            nidOAuthLogin0.errorHandling(response0.code());
            if(context0 instanceof Activity) {
                ((Activity)context0).setResult(0);
            }
        }
        else if(context0 instanceof Activity) {
            ((Activity)context0).setResult(0);
            return response0.body();
        }
        return response0.body();
    }

    private final Object requestRefreshAccessToken(OAuthLoginCallback oAuthLoginCallback0, Continuation continuation0) {
        boolean z;
        Response response0;
        NidOAuthLogin nidOAuthLogin0;
        com.navercorp.nid.oauth.NidOAuthLogin.requestRefreshAccessToken.1 nidOAuthLogin$requestRefreshAccessToken$10;
        if(continuation0 instanceof com.navercorp.nid.oauth.NidOAuthLogin.requestRefreshAccessToken.1) {
            nidOAuthLogin$requestRefreshAccessToken$10 = (com.navercorp.nid.oauth.NidOAuthLogin.requestRefreshAccessToken.1)continuation0;
            if((nidOAuthLogin$requestRefreshAccessToken$10.label & 0x80000000) == 0) {
                nidOAuthLogin$requestRefreshAccessToken$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.requestRefreshAccessToken(null, this);
                    }
                };
            }
            else {
                nidOAuthLogin$requestRefreshAccessToken$10.label ^= 0x80000000;
            }
        }
        else {
            nidOAuthLogin$requestRefreshAccessToken$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.requestRefreshAccessToken(null, this);
                }
            };
        }
        Object object0 = nidOAuthLogin$requestRefreshAccessToken$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(nidOAuthLogin$requestRefreshAccessToken$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                try {
                    Function2 function20 = new Function2() {
                        int label;

                        {
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            return new com.navercorp.nid.oauth.NidOAuthLogin.requestRefreshAccessToken.2(continuation0);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                            return ((com.navercorp.nid.oauth.NidOAuthLogin.requestRefreshAccessToken.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    NidOAuthApi nidOAuthApi0 = new NidOAuthApi();
                                    this.label = 1;
                                    Object object2 = nidOAuthApi0.requestRefreshToken(this);
                                    return object2 == object1 ? object1 : object2;
                                }
                                case 1: {
                                    ResultKt.throwOnFailure(object0);
                                    return object0;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                        }
                    };
                    nidOAuthLogin$requestRefreshAccessToken$10.L$0 = this;
                    nidOAuthLogin$requestRefreshAccessToken$10.L$1 = oAuthLoginCallback0;
                    nidOAuthLogin$requestRefreshAccessToken$10.label = 1;
                    object0 = BuildersKt.withContext(Dispatchers.getIO(), function20, nidOAuthLogin$requestRefreshAccessToken$10);
                }
                catch(Throwable throwable0) {
                    nidOAuthLogin0 = this;
                    break;
                }
                if(object0 == object1) {
                    return object1;
                }
                nidOAuthLogin0 = this;
                response0 = (Response)object0;
                goto label_35;
            }
            case 1: {
                oAuthLoginCallback0 = (OAuthLoginCallback)nidOAuthLogin$requestRefreshAccessToken$10.L$1;
                nidOAuthLogin0 = (NidOAuthLogin)nidOAuthLogin$requestRefreshAccessToken$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    response0 = (Response)object0;
                    goto label_35;
                }
                catch(Throwable throwable0) {
                }
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        nidOAuthLogin0.errorHandling(throwable0);
        oAuthLoginCallback0.onError(-1, throwable0.toString());
        return null;
    label_35:
        int v = response0.code();
        if(200 <= v && v < 300) {
            NidOAuthResponse nidOAuthResponse0 = (NidOAuthResponse)response0.body();
            if(nidOAuthResponse0 == null) {
                z = false;
            }
            else {
                CharSequence charSequence0 = nidOAuthResponse0.getError();
                if(charSequence0 != null && charSequence0.length() != 0) {
                    NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                    NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                    z = false;
                }
                else {
                    CharSequence charSequence1 = nidOAuthResponse0.getAccessToken();
                    if(charSequence1 == null || charSequence1.length() == 0) {
                        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.INSTANCE.fromString(nidOAuthResponse0.getError()));
                        NidOAuthPreferencesManager.setLastErrorDesc((nidOAuthResponse0.getErrorDescription() == null ? "" : nidOAuthResponse0.getErrorDescription()));
                        z = false;
                    }
                    else {
                        NidOAuthPreferencesManager.setAccessToken(nidOAuthResponse0.getAccessToken());
                        NidOAuthPreferencesManager.setExpiresAt(System.currentTimeMillis() / 1000L + nidOAuthResponse0.getExpiresIn());
                        z = true;
                    }
                }
            }
            if(z) {
                oAuthLoginCallback0.onSuccess();
            }
            else {
                int v1 = response0.code();
                String s = response0.message();
                Intrinsics.checkNotNullExpressionValue(s, "response.message()");
                oAuthLoginCallback0.onFailure(v1, s);
            }
        }
        else if(400 > v || v >= 500) {
            nidOAuthLogin0.errorHandling(response0.code());
            int v3 = response0.code();
            String s2 = response0.message();
            Intrinsics.checkNotNullExpressionValue(s2, "response.message()");
            oAuthLoginCallback0.onError(v3, s2);
        }
        else {
            int v2 = response0.code();
            String s1 = response0.message();
            Intrinsics.checkNotNullExpressionValue(s1, "response.message()");
            oAuthLoginCallback0.onFailure(v2, s1);
        }
        NidOAuthResponse nidOAuthResponse1 = (NidOAuthResponse)response0.body();
        return nidOAuthResponse1 != null ? nidOAuthResponse1.getAccessToken() : null;
    }
}

