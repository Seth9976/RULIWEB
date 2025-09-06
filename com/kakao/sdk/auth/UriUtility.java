package com.kakao.sdk.auth;

import android.net.Uri.Builder;
import android.net.Uri;
import com.kakao.sdk.auth.model.Prompt;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ServerHosts;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0016\b\u0002\u0010\b\u001A\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJÛ\u0001\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\n2\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\n2\u0006\u0010\u000E\u001A\u00020\n2\u0010\b\u0002\u0010\u000F\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001A\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u0012\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00102\u0010\b\u0002\u0010\u0013\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00102\u0010\b\u0002\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00102\n\b\u0002\u0010\u0016\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0017\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0018\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0019\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001A\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001C\u001A\u0004\u0018\u00010\u001D2\n\b\u0002\u0010\u001E\u001A\u0004\u0018\u00010\u001D¢\u0006\u0002\u0010\u001FR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/kakao/sdk/auth/UriUtility;", "", "hosts", "Lcom/kakao/sdk/common/model/ServerHosts;", "(Lcom/kakao/sdk/common/model/ServerHosts;)V", "accountLoginAndAuthorize", "Landroid/net/Uri;", "authorizeUri", "accountParameters", "", "", "authorize", "clientId", "agt", "redirectUri", "scopes", "", "kaHeader", "channelPublicIds", "serviceTerms", "prompts", "Lcom/kakao/sdk/auth/model/Prompt;", "state", "loginHint", "nonce", "approvalType", "codeChallenge", "codeChallengeMethod", "accountsSkipIntro", "", "accountsTalkLoginVisible", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Landroid/net/Uri;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class UriUtility {
    private final ServerHosts hosts;

    public UriUtility() {
        this(null, 1, null);
    }

    public UriUtility(ServerHosts serverHosts0) {
        Intrinsics.checkNotNullParameter(serverHosts0, "hosts");
        super();
        this.hosts = serverHosts0;
    }

    public UriUtility(ServerHosts serverHosts0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            serverHosts0 = KakaoSdk.INSTANCE.getHosts();
        }
        this(serverHosts0);
    }

    public final Uri accountLoginAndAuthorize(Uri uri0, Map map0) {
        Intrinsics.checkNotNullParameter(uri0, "authorizeUri");
        Uri.Builder uri$Builder0 = new Uri.Builder().scheme("https").authority("auth.kakao.com").path("/sdks/page").appendQueryParameter("continue", uri0.toString());
        if(map0 != null) {
            for(Object object0: map0.entrySet()) {
                uri$Builder0.appendQueryParameter(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
            }
        }
        Uri uri1 = uri$Builder0.build();
        Intrinsics.checkNotNullExpressionValue(uri1, "Builder()\n            .scheme(com.kakao.sdk.common.Constants.SCHEME)\n            .authority(hosts.mobileAccount)\n            .path(Constants.ACCOUNT_LOGIN_PATH)\n            .appendQueryParameter(Constants.ACCOUNT_LOGIN_PARAM_CONTINUE, authorizeUri.toString())\n            .apply {\n                accountParameters?.forEach {\n                    appendQueryParameter(it.key, it.value)\n                }\n            }.build()");
        return uri1;
    }

    public static Uri accountLoginAndAuthorize$default(UriUtility uriUtility0, Uri uri0, Map map0, int v, Object object0) {
        if((v & 2) != 0) {
            map0 = null;
        }
        return uriUtility0.accountLoginAndAuthorize(uri0, map0);
    }

    public final Uri authorize(String s, String s1, String s2, List list0, String s3, List list1, List list2, List list3, String s4, String s5, String s6, String s7, String s8, String s9, Boolean boolean0, Boolean boolean1) {
        Intrinsics.checkNotNullParameter(s, "clientId");
        Intrinsics.checkNotNullParameter(s2, "redirectUri");
        Uri.Builder uri$Builder0 = new Uri.Builder().scheme("https").authority("kauth.kakao.com").path("oauth/authorize").appendQueryParameter("client_id", s).appendQueryParameter("redirect_uri", s2).appendQueryParameter("response_type", "code");
        if(s1 != null) {
            uri$Builder0.appendQueryParameter("agt", s1);
        }
        if(list0 != null && !list0.isEmpty()) {
            uri$Builder0.appendQueryParameter("scope", CollectionsKt.joinToString$default(list0, ",", null, null, 0, null, null, 62, null));
        }
        if(list1 != null) {
            uri$Builder0.appendQueryParameter("channel_public_id", CollectionsKt.joinToString$default(list1, ",", null, null, 0, null, null, 62, null));
        }
        if(list2 != null) {
            uri$Builder0.appendQueryParameter("service_terms", CollectionsKt.joinToString$default(list2, ",", null, null, 0, null, null, 62, null));
        }
        if(list3 != null) {
            uri$Builder0.appendQueryParameter("prompt", CollectionsKt.joinToString$default(list3, ",", null, null, 0, null, com.kakao.sdk.auth.UriUtility.authorize.1.4.1.INSTANCE, 30, null));
        }
        if(s4 != null) {
            uri$Builder0.appendQueryParameter("state", s4);
        }
        if(s5 != null) {
            uri$Builder0.appendQueryParameter("login_hint", s5);
        }
        if(s6 != null) {
            uri$Builder0.appendQueryParameter("nonce", s6);
        }
        if(s7 != null) {
            uri$Builder0.appendQueryParameter("approval_type", s7);
        }
        if(s8 != null) {
            uri$Builder0.appendQueryParameter("code_challenge", s8);
        }
        if(s9 != null) {
            uri$Builder0.appendQueryParameter("code_challenge_method", s9);
        }
        if(boolean0 != null) {
            uri$Builder0.appendQueryParameter("accounts_skip_intro", String.valueOf(boolean0.booleanValue()));
        }
        if(boolean1 != null) {
            uri$Builder0.appendQueryParameter("accounts_talk_login_visible", String.valueOf(boolean1.booleanValue()));
        }
        Uri uri0 = uri$Builder0.appendQueryParameter("ka", s3).build();
        Intrinsics.checkNotNullExpressionValue(uri0, "Builder()\n            .scheme(com.kakao.sdk.common.Constants.SCHEME)\n            .authority(hosts.kauth).path(Constants.AUTHORIZE_PATH)\n            .appendQueryParameter(Constants.CLIENT_ID, clientId)\n            .appendQueryParameter(Constants.REDIRECT_URI, redirectUri)\n            .appendQueryParameter(Constants.RESPONSE_TYPE, Constants.CODE).apply {\n                agt?.let { appendQueryParameter(Constants.AGT, agt) }\n                if (!scopes.isNullOrEmpty()) {\n                    appendQueryParameter(Constants.SCOPE, scopes.joinToString(\",\"))\n                }\n                channelPublicIds?.let {\n                    appendQueryParameter(\n                        Constants.CHANNEL_PUBLIC_ID,\n                        channelPublicIds.joinToString(\",\")\n                    )\n                }\n                serviceTerms?.let {\n                    appendQueryParameter(\n                        Constants.SERVICE_TERMS,\n                        serviceTerms.joinToString(\",\")\n                    )\n                }\n                prompts?.let { prompts ->\n                    appendQueryParameter(\n                        Constants.PROMPT,\n                        prompts.joinToString(\",\") { prompt -> prompt.value }\n                    )\n                }\n                state?.let { appendQueryParameter(Constants.STATE, it) }\n                loginHint?.let { appendQueryParameter(Constants.LOGIN_HINT, it) }\n                nonce?.let { appendQueryParameter(Constants.NONCE, it) }\n                approvalType?.let { appendQueryParameter(Constants.APPROVAL_TYPE, it) }\n                codeChallenge?.let { appendQueryParameter(Constants.CODE_CHALLENGE, it) }\n                codeChallengeMethod?.let {\n                    appendQueryParameter(\n                        Constants.CODE_CHALLENGE_METHOD,\n                        it\n                    )\n                }\n                accountsSkipIntro?.let { appendQueryParameter(Constants.ACCOUNTS_SKIP_INTRO, it.toString()) }\n                accountsTalkLoginVisible?.let { appendQueryParameter(Constants.ACCOUNTS_TALK_LOGIN_VISIBLE, it.toString()) }\n            }\n            .appendQueryParameter(Constants.KA_HEADER, kaHeader)\n            .build()");
        return uri0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "prompt", "Lcom/kakao/sdk/auth/model/Prompt;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
        final class com.kakao.sdk.auth.UriUtility.authorize.1.4.1 extends Lambda implements Function1 {
            public static final com.kakao.sdk.auth.UriUtility.authorize.1.4.1 INSTANCE;

            static {
                com.kakao.sdk.auth.UriUtility.authorize.1.4.1.INSTANCE = new com.kakao.sdk.auth.UriUtility.authorize.1.4.1();
            }

            com.kakao.sdk.auth.UriUtility.authorize.1.4.1() {
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

    public static Uri authorize$default(UriUtility uriUtility0, String s, String s1, String s2, List list0, String s3, List list1, List list2, List list3, String s4, String s5, String s6, String s7, String s8, String s9, Boolean boolean0, Boolean boolean1, int v, Object object0) {
        String s10 = (v & 2) == 0 ? s1 : null;
        List list4 = (v & 8) == 0 ? list0 : null;
        String s11 = (v & 16) == 0 ? s3 : null;
        List list5 = (v & 0x20) == 0 ? list1 : null;
        List list6 = (v & 0x40) == 0 ? list2 : null;
        List list7 = (v & 0x80) == 0 ? list3 : null;
        String s12 = (v & 0x100) == 0 ? s4 : null;
        String s13 = (v & 0x200) == 0 ? s5 : null;
        String s14 = (v & 0x400) == 0 ? s6 : null;
        String s15 = (v & 0x800) == 0 ? s7 : null;
        String s16 = (v & 0x1000) == 0 ? s8 : null;
        String s17 = (v & 0x2000) == 0 ? s9 : null;
        Boolean boolean2 = (v & 0x4000) == 0 ? boolean0 : null;
        return (v & 0x8000) == 0 ? uriUtility0.authorize(s, s10, s2, list4, s11, list5, list6, list7, s12, s13, s14, s15, s16, s17, boolean2, boolean1) : uriUtility0.authorize(s, s10, s2, list4, s11, list5, list6, list7, s12, s13, s14, s15, s16, s17, boolean2, null);
    }
}

