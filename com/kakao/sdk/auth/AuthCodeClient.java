package com.kakao.sdk.auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Base64;
import com.kakao.sdk.auth.model.Prompt;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ApplicationInfo;
import com.kakao.sdk.common.model.ApprovalType;
import com.kakao.sdk.common.model.AuthError;
import com.kakao.sdk.common.model.AuthErrorCause;
import com.kakao.sdk.common.model.AuthErrorResponse;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.model.ContextInfo;
import com.kakao.sdk.common.model.KakaoSdkError;
import com.kakao.sdk.common.util.IntentResolveClient;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.common.util.KakaoResultReceiver;
import com.kakao.sdk.common.util.SdkLog;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 02\u00020\u0001:\u00010B-\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ\u008F\u0002\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0010\b\u0002\u0010\u000F\u001A\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001A\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00102\n\b\u0002\u0010\u0015\u001A\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0017\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00102\u0010\b\u0002\u0010\u0018\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00102\b\b\u0002\u0010\u0019\u001A\u00020\u001A2\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\u00132\u0016\b\u0002\u0010\u001C\u001A\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001D2\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u001F\u001A\u0004\u0018\u00010\u001A2\n\b\u0002\u0010 \u001A\u0004\u0018\u00010\u001A2:\u0010!\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0013\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010&\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\'\u0012\u0004\u0012\u00020\f0\"H\u0007\u00A2\u0006\u0002\u0010(J\u00B0\u0001\u0010)\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0010\b\u0002\u0010\u000F\u001A\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001A\u0004\u0018\u00010\u00132\b\b\u0002\u0010*\u001A\u00020+2\n\b\u0002\u0010\u0015\u001A\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0017\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00102\u0010\b\u0002\u0010\u0018\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00102\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u00132:\u0010!\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0013\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010&\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\'\u0012\u0004\u0012\u00020\f0\"H\u0007J\u000E\u0010,\u001A\u00020\u001A2\u0006\u0010\r\u001A\u00020\u000EJe\u0010-\u001A<\u00128\u00126\u0012\u0015\u0012\u0013\u0018\u00010\u0013\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010&\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\'\u0012\u0004\u0012\u00020\f0\"0.2\u001C\u0010!\u001A\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010&\u0012\u0004\u0012\u00020\f0\"H\u0000\u00A2\u0006\u0002\b/R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u00061"}, d2 = {"Lcom/kakao/sdk/auth/AuthCodeClient;", "", "intentResolveClient", "Lcom/kakao/sdk/common/util/IntentResolveClient;", "applicationInfo", "Lcom/kakao/sdk/common/model/ApplicationInfo;", "contextInfo", "Lcom/kakao/sdk/common/model/ContextInfo;", "approvalType", "Lcom/kakao/sdk/common/model/ApprovalType;", "(Lcom/kakao/sdk/common/util/IntentResolveClient;Lcom/kakao/sdk/common/model/ApplicationInfo;Lcom/kakao/sdk/common/model/ContextInfo;Lcom/kakao/sdk/common/model/ApprovalType;)V", "authorizeWithKakaoAccount", "", "context", "Landroid/content/Context;", "prompts", "", "Lcom/kakao/sdk/auth/model/Prompt;", "state", "", "scopes", "nonce", "agt", "channelPublicIds", "serviceTerms", "forceAccountLogin", "", "loginHint", "accountParameters", "", "codeVerifier", "accountsSkipIntro", "accountsTalkLoginVisible", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "code", "", "error", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function2;)V", "authorizeWithKakaoTalk", "requestCode", "", "isKakaoTalkLoginAvailable", "resultReceiver", "Lcom/kakao/sdk/common/util/KakaoResultReceiver;", "resultReceiver$auth_release", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthCodeClient {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FJ\u0006\u0010\u000E\u001A\u00020\rR\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0005\u001A\u00020\u00068FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000B\u0012\u0004\b\u0007\u0010\u0002\u001A\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/kakao/sdk/auth/AuthCodeClient$Companion;", "", "()V", "DEFAULT_REQUEST_CODE", "", "instance", "Lcom/kakao/sdk/auth/AuthCodeClient;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/auth/AuthCodeClient;", "instance$delegate", "Lkotlin/Lazy;", "codeChallenge", "", "codeVerifier", "", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/auth/AuthCodeClient;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final String codeChallenge(byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "codeVerifier");
            String s = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(arr_b), 11);
            Intrinsics.checkNotNullExpressionValue(s, "encodeToString(\n                MessageDigest.getInstance(Constants.CODE_CHALLENGE_ALGORITHM).digest(codeVerifier),\n                Base64.NO_WRAP or Base64.NO_PADDING or Base64.URL_SAFE\n            )");
            return s;
        }

        // 去混淆评级： 低(24)
        public final String codeVerifier() [...] // 潜在的解密器

        public final AuthCodeClient getInstance() {
            return (AuthCodeClient)AuthCodeClient.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }
    }

    public static final Companion Companion = null;
    public static final int DEFAULT_REQUEST_CODE = 10012;
    private final ApplicationInfo applicationInfo;
    private final ApprovalType approvalType;
    private final ContextInfo contextInfo;
    private static final Lazy instance$delegate;
    private final IntentResolveClient intentResolveClient;

    static {
        AuthCodeClient.Companion = new Companion(null);
        AuthCodeClient.instance$delegate = LazyKt.lazy(AuthCodeClient.Companion.instance.2.INSTANCE);
    }

    public AuthCodeClient() {
        this(null, null, null, null, 15, null);
    }

    public AuthCodeClient(IntentResolveClient intentResolveClient0, ApplicationInfo applicationInfo0, ContextInfo contextInfo0, ApprovalType approvalType0) {
        Intrinsics.checkNotNullParameter(intentResolveClient0, "intentResolveClient");
        Intrinsics.checkNotNullParameter(applicationInfo0, "applicationInfo");
        Intrinsics.checkNotNullParameter(contextInfo0, "contextInfo");
        Intrinsics.checkNotNullParameter(approvalType0, "approvalType");
        super();
        this.intentResolveClient = intentResolveClient0;
        this.applicationInfo = applicationInfo0;
        this.contextInfo = contextInfo0;
        this.approvalType = approvalType0;
    }

    public AuthCodeClient(IntentResolveClient intentResolveClient0, ApplicationInfo applicationInfo0, ContextInfo contextInfo0, ApprovalType approvalType0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            intentResolveClient0 = IntentResolveClient.Companion.getInstance();
        }
        if((v & 2) != 0) {
            applicationInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        if((v & 4) != 0) {
            contextInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        if((v & 8) != 0) {
            approvalType0 = KakaoSdk.INSTANCE.getApprovalType();
        }
        this(intentResolveClient0, applicationInfo0, contextInfo0, approvalType0);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, false, null, null, null, null, null, function20, 0x3F00, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Map map0, String s4, Boolean boolean0, Boolean boolean1, Function2 function20) {
        String s9;
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        UriUtility uriUtility0 = new UriUtility(null, 1, null);
        String s5 = this.applicationInfo.getAppKey();
        String s6 = this.applicationInfo.getRedirectUri();
        String s7 = this.contextInfo.getKaHeader();
        String s8 = this.approvalType.getValue();
        if(s4 == null) {
            s9 = null;
        }
        else {
            byte[] arr_b = s4.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(arr_b, "(this as java.lang.String).getBytes(charset)");
            s9 = AuthCodeClient.Companion.codeChallenge(arr_b);
        }
        Uri uri0 = uriUtility0.authorize(s5, s2, s6, list1, s7, list2, list3, list0, s, s3, s1, s8, s9, (s4 == null ? null : "S256"), boolean0, boolean1);
        if(z && map0 != null) {
            uri0 = uriUtility0.accountLoginAndAuthorize(uri0, map0);
        }
        SdkLog.Companion.i(uri0);
        try {
            String s10 = this.applicationInfo.getRedirectUri();
            ResultReceiver resultReceiver0 = this.resultReceiver$auth_release(function20);
            context0.startActivity(AuthCodeIntentFactory.INSTANCE.account(context0, uri0, s10, resultReceiver0));
        }
        catch(Throwable throwable0) {
            SdkLog.Companion.e(throwable0);
            function20.invoke(null, throwable0);
        }
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Map map0, String s4, Boolean boolean0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, z, s3, map0, s4, boolean0, null, function20, 0x2000, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Map map0, String s4, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, z, s3, map0, s4, null, null, function20, 0x3000, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Map map0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, z, s3, map0, null, null, null, function20, 0x3800, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, z, s3, null, null, null, null, function20, 0x3C00, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, list3, z, null, null, null, null, null, function20, 0x3E00, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, List list2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, list2, null, false, null, null, null, null, null, function20, 0x3F80, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, String s2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, s2, null, null, false, null, null, null, null, null, function20, 0x3FC0, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, s1, null, null, null, false, null, null, null, null, null, function20, 0x3FE0, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, list1, null, null, null, null, false, null, null, null, null, null, function20, 0x3FF0, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, s, null, null, null, null, null, false, null, null, null, null, null, function20, 0x3FF8, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, list0, null, null, null, null, null, null, false, null, null, null, null, null, function20, 0x3FFC, null);
    }

    public final void authorizeWithKakaoAccount(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoAccount$default(this, context0, null, null, null, null, null, null, null, false, null, null, null, null, null, function20, 0x3FFE, null);
    }

    public static void authorizeWithKakaoAccount$default(AuthCodeClient authCodeClient0, Context context0, List list0, String s, List list1, String s1, String s2, List list2, List list3, boolean z, String s3, Map map0, String s4, Boolean boolean0, Boolean boolean1, Function2 function20, int v, Object object0) {
        authCodeClient0.authorizeWithKakaoAccount(context0, ((v & 2) == 0 ? list0 : null), ((v & 4) == 0 ? s : null), ((v & 8) == 0 ? list1 : null), ((v & 16) == 0 ? s1 : null), ((v & 0x20) == 0 ? s2 : null), ((v & 0x40) == 0 ? list2 : null), ((v & 0x80) == 0 ? list3 : null), ((v & 0x100) == 0 ? z : false), ((v & 0x200) == 0 ? s3 : null), ((v & 0x400) == 0 ? map0 : null), ((v & 0x800) == 0 ? s4 : null), ((v & 0x1000) == 0 ? boolean0 : null), ((v & 0x2000) == 0 ? boolean1 : null), function20);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, int v, String s1, List list1, List list2, String s2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        if(!this.isKakaoTalkLoginAvailable(context0)) {
            function20.invoke(null, new ClientError(ClientErrorCause.NotSupported, "KakaoTalk not installed"));
            return;
        }
        try {
            String s3 = this.applicationInfo.getAppKey();
            String s4 = this.applicationInfo.getRedirectUri();
            String s5 = this.contextInfo.getKaHeader();
            AuthCodeIntentFactory authCodeIntentFactory0 = AuthCodeIntentFactory.INSTANCE;
            Bundle bundle0 = new Bundle();
            if(list1 != null) {
                bundle0.putString("channel_public_id", CollectionsKt.joinToString$default(list1, ",", null, null, 0, null, null, 62, null));
            }
            if(list2 != null) {
                bundle0.putString("service_terms", CollectionsKt.joinToString$default(list2, ",", null, null, 0, null, null, 62, null));
            }
            String s6 = this.approvalType.getValue();
            if(s6 != null) {
                bundle0.putString("approval_type", s6);
            }
            if(s2 != null) {
                byte[] arr_b = s2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(arr_b, "(this as java.lang.String).getBytes(charset)");
                bundle0.putString("code_challenge", AuthCodeClient.Companion.codeChallenge(arr_b));
                bundle0.putString("code_challenge_method", "S256");
            }
            if(list0 != null) {
                bundle0.putString("prompt", CollectionsKt.joinToString$default(list0, ",", null, null, 0, null, com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1.INSTANCE, 30, null));
            }
            if(s != null) {
                bundle0.putString("state", s);
            }
            if(s1 != null) {
                bundle0.putString("nonce", s1);
            }
            context0.startActivity(authCodeIntentFactory0.talk(context0, v, s3, s4, s5, bundle0, this.resultReceiver$auth_release(function20)));
        }
        catch(Throwable throwable0) {
            SdkLog.Companion.e(throwable0);
            function20.invoke(null, throwable0);
        }

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prompt", "Lcom/kakao/sdk/auth/model/Prompt;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
        final class com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1 extends Lambda implements Function1 {
            public static final com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1 INSTANCE;

            static {
                com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1.INSTANCE = new com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1();
            }

            com.kakao.sdk.auth.AuthCodeClient.authorizeWithKakaoTalk.1.5.1() {
                super(1);
            }

            public final CharSequence invoke(Prompt prompt0) {
                Intrinsics.checkNotNullParameter(prompt0, "prompt");
                return prompt0.getValue();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Prompt)object0));
            }
        }

    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, int v, String s1, List list1, List list2, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, s, v, s1, list1, list2, null, function20, 0x80, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, int v, String s1, List list1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, s, v, s1, list1, null, null, function20, 0xC0, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, int v, String s1, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, s, v, s1, null, null, null, function20, 0xE0, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, int v, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, s, v, null, null, null, null, function20, 0xF0, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, s, 0, null, null, null, null, function20, 0xF8, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, list0, null, 0, null, null, null, null, function20, 0xFC, null);
    }

    public final void authorizeWithKakaoTalk(Context context0, Function2 function20) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function20, "callback");
        AuthCodeClient.authorizeWithKakaoTalk$default(this, context0, null, null, 0, null, null, null, null, function20, 0xFE, null);
    }

    public static void authorizeWithKakaoTalk$default(AuthCodeClient authCodeClient0, Context context0, List list0, String s, int v, String s1, List list1, List list2, String s2, Function2 function20, int v1, Object object0) {
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
        if((v1 & 0x80) != 0) {
            s2 = null;
        }
        authCodeClient0.authorizeWithKakaoTalk(context0, list0, s, v, s1, list1, list2, s2, function20);
    }

    public static final AuthCodeClient getInstance() {
        return AuthCodeClient.Companion.getInstance();
    }

    public final boolean isKakaoTalkLoginAvailable(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intent intent0 = AuthCodeIntentFactory.INSTANCE.talkBase();
        return this.intentResolveClient.resolveTalkIntent(context0, intent0) != null;
    }

    public final KakaoResultReceiver resultReceiver$auth_release(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "callback");
        com.kakao.sdk.auth.AuthCodeClient.resultReceiver.1 authCodeClient$resultReceiver$10 = new com.kakao.sdk.auth.AuthCodeClient.resultReceiver.1();
        authCodeClient$resultReceiver$10.setEmitter(function20);
        return authCodeClient$resultReceiver$10;

        @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002<\u00128\u00126\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00020\u0001J\b\u0010\n\u001A\u00020\tH\u0016J\u0012\u0010\u000B\u001A\u00020\t2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000E\u001A\u00020\t2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0016¨\u0006\u000F"}, d2 = {"com/kakao/sdk/auth/AuthCodeClient$resultReceiver$1", "Lcom/kakao/sdk/common/util/KakaoResultReceiver;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "code", "", "error", "", "processError", "receiveCanceled", "resultData", "Landroid/os/Bundle;", "receiveOk", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
        public final class com.kakao.sdk.auth.AuthCodeClient.resultReceiver.1 extends KakaoResultReceiver {
            com.kakao.sdk.auth.AuthCodeClient.resultReceiver.1() {
                super("Auth Code");
            }

            @Override  // com.kakao.sdk.common.util.KakaoResultReceiver
            public void processError() {
                IllegalArgumentException illegalArgumentException0 = new IllegalArgumentException("Unknown resultCode in AuthCodeReceiver#onReceivedResult()");
                Function2 function20 = (Function2)this.getEmitter();
                if(function20 == null) {
                    return;
                }
                function20.invoke(null, illegalArgumentException0);
            }

            @Override  // com.kakao.sdk.common.util.KakaoResultReceiver
            public void receiveCanceled(Bundle bundle0) {
                KakaoSdkError kakaoSdkError0;
                if(Build.VERSION.SDK_INT >= 33) {
                    if(bundle0 == null) {
                        kakaoSdkError0 = null;
                        goto label_9;
                    }
                    kakaoSdkError0 = (KakaoSdkError)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle0, "key.exception", KakaoSdkError.class);
                }
                else {
                    Serializable serializable0 = bundle0 == null ? null : bundle0.getSerializable("key.exception");
                    if(serializable0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.kakao.sdk.common.model.KakaoSdkError");
                    }
                    kakaoSdkError0 = (KakaoSdkError)serializable0;
                }
            label_9:
                Function2 function20 = (Function2)this.getEmitter();
                if(function20 == null) {
                    return;
                }
                function20.invoke(null, kakaoSdkError0);
            }

            @Override  // com.kakao.sdk.common.util.KakaoResultReceiver
            public void receiveOk(Bundle bundle0) {
                AuthErrorCause authErrorCause0;
                Uri uri0;
                if(Build.VERSION.SDK_INT < 33) {
                    uri0 = bundle0 == null ? null : ((Uri)bundle0.getParcelable("key.url"));
                }
                else if(bundle0 != null) {
                    uri0 = (Uri)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle0, "key.url", Uri.class);
                }
                else {
                    uri0 = null;
                }
                String s = uri0 == null ? null : uri0.getQueryParameter("code");
                String s1 = "unknown";
                if(s == null) {
                    if(uri0 != null) {
                        String s2 = uri0.getQueryParameter("error");
                        if(s2 != null) {
                            s1 = s2;
                        }
                    }
                    String s3 = uri0 == null ? null : uri0.getQueryParameter("error_description");
                    Function2 function21 = (Function2)this.getEmitter();
                    if(function21 != null) {
                        try {
                            authErrorCause0 = Result.constructor-impl(((AuthErrorCause)KakaoJson.INSTANCE.fromJson(s1, AuthErrorCause.class)));
                        }
                        catch(Throwable throwable0) {
                            authErrorCause0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                        }
                        AuthErrorCause authErrorCause1 = AuthErrorCause.Unknown;
                        if(Result.isFailure-impl(authErrorCause0)) {
                            authErrorCause0 = authErrorCause1;
                        }
                        function21.invoke(null, new AuthError(302, authErrorCause0, new AuthErrorResponse(s1, s3)));
                    }
                }
                else {
                    Function2 function20 = (Function2)this.getEmitter();
                    if(function20 != null) {
                        function20.invoke(s, null);
                    }
                }
            }
        }

    }
}

