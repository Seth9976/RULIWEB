package com.navercorp.nid.oauth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.Global;
import com.navercorp.nid.oauth.activity.NidOAuthCustomTabActivity;
import com.navercorp.nid.util.NidApplicationUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthIntent;", "", "()V", "Builder", "Companion", "Type", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthIntent {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001A\u0004\u0018\u00010\u000EJ\n\u0010\u000F\u001A\u0004\u0018\u00010\u000EH\u0002J\n\u0010\u0010\u001A\u0004\u0018\u00010\u000EH\u0002J\n\u0010\u0011\u001A\u0004\u0018\u00010\u000EH\u0002J\u0010\u0010\u0012\u001A\u00020\u00002\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006J\u000E\u0010\u0013\u001A\u00020\u00002\u0006\u0010\u000B\u001A\u00020\fR\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthIntent$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authType", "", "callbackUrl", "clientId", "clientName", "initState", "type", "Lcom/navercorp/nid/oauth/NidOAuthIntent$Type;", "build", "Landroid/content/Intent;", "getCustomTabsIntent", "getIntent", "getNaverAppIntent", "setAuthType", "setType", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Builder {
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 0x30)
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[Type.values().length];
                arr_v[Type.NAVER_APP.ordinal()] = 1;
                arr_v[Type.CUSTOM_TABS.ordinal()] = 2;
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        private String authType;
        private String callbackUrl;
        private String clientId;
        private String clientName;
        private Context context;
        private String initState;
        private Type type;

        public Builder(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            super();
            this.clientId = NidOAuthPreferencesManager.getClientId();
            this.callbackUrl = NidOAuthPreferencesManager.getCallbackUrl();
            this.clientName = NidOAuthPreferencesManager.getClientName();
            this.initState = NidOAuthPreferencesManager.INSTANCE.getInitState();
            this.context = context0;
        }

        public final Intent build() {
            if(this.type == null) {
                return null;
            }
            if(this.clientId == null || this.clientId.length() == 0 || this.type == Type.NAVER_APP && (this.clientName == null || this.clientName.length() == 0)) {
                return null;
            }
            return this.callbackUrl == null || this.callbackUrl.length() == 0 || (this.initState == null || this.initState.length() == 0) ? null : this.getIntent();
        }

        private final Intent getCustomTabsIntent() {
            if(Settings.Global.getInt(this.context.getContentResolver(), "always_finish_activities", 0) == 1) {
                return null;
            }
            if(NidApplicationUtil.INSTANCE.isNotCustomTabsAvailable(this.context)) {
                return null;
            }
            Intent intent0 = new Intent(this.context, NidOAuthCustomTabActivity.class);
            intent0.putExtra("ClientId", this.clientId);
            intent0.putExtra("ClientCallbackUrl", this.callbackUrl);
            intent0.putExtra("state", this.initState);
            intent0.putExtra("oauth_sdk_version", "5.10.0");
            String s = this.authType;
            if(s != null) {
                intent0.putExtra("auth_type", s);
            }
            intent0.addFlags(0x10000);
            return intent0;
        }

        private final Intent getIntent() {
            switch((this.type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()])) {
                case 1: {
                    return this.getNaverAppIntent();
                }
                case 2: {
                    return this.getCustomTabsIntent();
                }
                default: {
                    return null;
                }
            }
        }

        private final Intent getNaverAppIntent() {
            if(NidApplicationUtil.INSTANCE.isNotExistIntentFilter(this.context, "com.nhn.android.search", "com.nhn.android.search.action.OAUTH2_LOGIN")) {
                return null;
            }
            Intent intent0 = new Intent();
            intent0.putExtra("ClientId", this.clientId);
            intent0.putExtra("ClientCallbackUrl", this.callbackUrl);
            intent0.putExtra("app_name", this.clientName);
            intent0.putExtra("state", this.initState);
            intent0.putExtra("oauth_sdk_version", "5.10.0");
            if(this.authType != null) {
                if(NidApplicationUtil.INSTANCE.getNaverAppVersion(this.context) < 11160000L) {
                    return new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.nhn.android.search"));
                }
                intent0.putExtra("auth_type", this.authType);
            }
            intent0.setPackage("com.nhn.android.search");
            intent0.setAction("com.nhn.android.search.action.OAUTH2_LOGIN");
            return intent0;
        }

        public final Builder setAuthType(String s) {
            this.authType = s;
            return this;
        }

        public final Builder setType(Type nidOAuthIntent$Type0) {
            Intrinsics.checkNotNullParameter(nidOAuthIntent$Type0, "type");
            this.type = nidOAuthIntent$Type0;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u000E\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthIntent$Companion;", "", "()V", "OAUTH_REQUEST_AGREE_FROM_CONTENT", "", "OAUTH_REQUEST_AGREE_FROM_URL", "OAUTH_REQUEST_AUTH_TYPE", "OAUTH_REQUEST_CALLBACK_URL", "OAUTH_REQUEST_CLIENT_ID", "OAUTH_REQUEST_CLIENT_NAME", "OAUTH_REQUEST_INIT_STATE", "OAUTH_REQUEST_SDK_VERSION", "OAUTH_REQUEST_URL", "OAUTH_RESULT_ACCESS_TOKEN", "OAUTH_RESULT_CODE", "OAUTH_RESULT_ERROR_CODE", "OAUTH_RESULT_ERROR_DESCRIPTION", "OAUTH_RESULT_STATE", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthIntent$Type;", "", "(Ljava/lang/String;I)V", "NAVER_APP", "CUSTOM_TABS", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static enum Type {
        NAVER_APP,
        CUSTOM_TABS;

        private static final Type[] $values() [...] // Inlined contents
    }

    public static final Companion Companion = null;
    public static final String OAUTH_REQUEST_AGREE_FROM_CONTENT = "OAuthUrl";
    public static final String OAUTH_REQUEST_AGREE_FROM_URL = "agreeFormUrl";
    public static final String OAUTH_REQUEST_AUTH_TYPE = "auth_type";
    public static final String OAUTH_REQUEST_CALLBACK_URL = "ClientCallbackUrl";
    public static final String OAUTH_REQUEST_CLIENT_ID = "ClientId";
    public static final String OAUTH_REQUEST_CLIENT_NAME = "app_name";
    public static final String OAUTH_REQUEST_INIT_STATE = "state";
    public static final String OAUTH_REQUEST_SDK_VERSION = "oauth_sdk_version";
    public static final String OAUTH_REQUEST_URL = "OAuthUrl";
    public static final String OAUTH_RESULT_ACCESS_TOKEN = "oauth_at";
    public static final String OAUTH_RESULT_CODE = "oauth_code";
    public static final String OAUTH_RESULT_ERROR_CODE = "oauth_error_code";
    public static final String OAUTH_RESULT_ERROR_DESCRIPTION = "oauth_error_desc";
    public static final String OAUTH_RESULT_STATE = "oauth_state";

    static {
        NidOAuthIntent.Companion = new Companion(null);
    }
}

