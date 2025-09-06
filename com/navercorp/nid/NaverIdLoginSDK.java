package com.navercorp.nid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import com.navercorp.nid.exception.NaverIdLoginSDKNotInitializedException;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.oauth.NidOAuthBehavior;
import com.navercorp.nid.oauth.NidOAuthBridgeActivity;
import com.navercorp.nid.oauth.NidOAuthErrorCode;
import com.navercorp.nid.oauth.NidOAuthLoginState;
import com.navercorp.nid.oauth.NidOAuthPreferencesManager;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.preference.EncryptedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u00C6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u001C\u0010 \u001A\u00020!2\u0006\u0010\"\u001A\u00020\u00042\f\u0010#\u001A\b\u0012\u0004\u0012\u00020%0$J\u0016\u0010 \u001A\u00020!2\u0006\u0010\"\u001A\u00020\u00042\u0006\u0010&\u001A\u00020\u001BJ\b\u0010\'\u001A\u0004\u0018\u00010(J\u0006\u0010)\u001A\u00020\u0004J\u0006\u0010*\u001A\u00020+J\u0006\u0010,\u001A\u00020-J\b\u0010.\u001A\u0004\u0018\u00010(J\b\u0010/\u001A\u0004\u0018\u00010(J\u0006\u00100\u001A\u000201J\b\u00102\u001A\u0004\u0018\u00010(J\u0006\u00103\u001A\u00020(J\u000E\u00104\u001A\u00020!2\u0006\u0010\"\u001A\u00020\u0004J&\u00105\u001A\u00020!2\u0006\u0010\"\u001A\u00020\u00042\u0006\u00106\u001A\u00020(2\u0006\u00107\u001A\u00020(2\u0006\u00108\u001A\u00020(J\u0006\u00109\u001A\u00020\fJ\u0006\u0010:\u001A\u00020!J\u001C\u0010;\u001A\u00020!2\u0006\u0010\"\u001A\u00020\u00042\f\u0010#\u001A\b\u0012\u0004\u0012\u00020%0$J\u0016\u0010;\u001A\u00020!2\u0006\u0010\"\u001A\u00020\u00042\u0006\u0010&\u001A\u00020\u001BJ\u000E\u0010<\u001A\u00020!2\u0006\u0010=\u001A\u00020\fR\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u00020\u0006X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\fX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\r\"\u0004\b\u000E\u0010\u000FR\u001A\u0010\u0010\u001A\u00020\fX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000FR\u001A\u0010\u0012\u001A\u00020\fX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000FR\u001A\u0010\u0014\u001A\u00020\u0015X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001C\u0010\u001A\u001A\u0004\u0018\u00010\u001BX\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001C\u0010\u001D\"\u0004\b\u001E\u0010\u001F\u00A8\u0006>"}, d2 = {"Lcom/navercorp/nid/NaverIdLoginSDK;", "", "()V", "applicationContext", "Landroid/content/Context;", "behavior", "Lcom/navercorp/nid/oauth/NidOAuthBehavior;", "getBehavior", "()Lcom/navercorp/nid/oauth/NidOAuthBehavior;", "setBehavior", "(Lcom/navercorp/nid/oauth/NidOAuthBehavior;)V", "isRequiredCustomTabsReAuth", "", "()Z", "setRequiredCustomTabsReAuth", "(Z)V", "isShowBottomTab", "setShowBottomTab", "isShowMarketLink", "setShowMarketLink", "naverappIntentFlag", "", "getNaverappIntentFlag", "()I", "setNaverappIntentFlag", "(I)V", "oauthLoginCallback", "Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "getOauthLoginCallback", "()Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "setOauthLoginCallback", "(Lcom/navercorp/nid/oauth/OAuthLoginCallback;)V", "authenticate", "", "context", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "callback", "getAccessToken", "", "getApplicationContext", "getExpiresAt", "", "getLastErrorCode", "Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "getLastErrorDescription", "getRefreshToken", "getState", "Lcom/navercorp/nid/oauth/NidOAuthLoginState;", "getTokenType", "getVersion", "init", "initialize", "clientId", "clientSecret", "clientName", "isInitialized", "logout", "reagreeAuthenticate", "showDevelopersLog", "isShow", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NaverIdLoginSDK {
    public static final NaverIdLoginSDK INSTANCE;
    private static Context applicationContext;
    private static NidOAuthBehavior behavior;
    private static boolean isRequiredCustomTabsReAuth;
    private static boolean isShowBottomTab;
    private static boolean isShowMarketLink;
    private static int naverappIntentFlag;
    private static OAuthLoginCallback oauthLoginCallback;

    static {
        NaverIdLoginSDK.INSTANCE = new NaverIdLoginSDK();
        NaverIdLoginSDK.isShowMarketLink = true;
        NaverIdLoginSDK.isShowBottomTab = true;
        NaverIdLoginSDK.naverappIntentFlag = -1;
        NaverIdLoginSDK.behavior = NidOAuthBehavior.DEFAULT;
    }

    public final void authenticate(Context context0, ActivityResultLauncher activityResultLauncher0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(activityResultLauncher0, "launcher");
        this.init(context0);
        if(this.getState() == NidOAuthLoginState.NEED_INIT) {
            Toast.makeText(context0.getApplicationContext(), "SDK 초기화가 필요합니다.", 0).show();
            return;
        }
        Activity activity0 = null;
        NaverIdLoginSDK.oauthLoginCallback = null;
        int v = context0.getResources().getConfiguration().orientation;
        Intent intent0 = new Intent(context0, NidOAuthBridgeActivity.class);
        intent0.putExtra("orientation", v);
        activityResultLauncher0.launch(intent0);
        if(context0 instanceof Activity) {
            activity0 = (Activity)context0;
        }
        if(activity0 != null) {
            activity0.overridePendingTransition(0, 0);
        }
    }

    public final void authenticate(Context context0, OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(oAuthLoginCallback0, "callback");
        this.init(context0);
        if(this.getState() == NidOAuthLoginState.NEED_INIT) {
            Toast.makeText(context0.getApplicationContext(), "SDK 초기화가 필요합니다.", 0).show();
            return;
        }
        NaverIdLoginSDK.oauthLoginCallback = oAuthLoginCallback0;
        int v = context0.getResources().getConfiguration().orientation;
        Intent intent0 = new Intent(context0, NidOAuthBridgeActivity.class);
        intent0.putExtra("orientation", v);
        context0.startActivity(intent0);
        Activity activity0 = context0 instanceof Activity ? ((Activity)context0) : null;
        if(activity0 != null) {
            activity0.overridePendingTransition(0, 0);
        }
    }

    public final String getAccessToken() {
        return NidOAuthPreferencesManager.getAccessToken();
    }

    public final Context getApplicationContext() {
        Context context0 = NaverIdLoginSDK.applicationContext;
        if(context0 == null) {
            throw new NaverIdLoginSDKNotInitializedException();
        }
        return context0;
    }

    public final NidOAuthBehavior getBehavior() {
        return NaverIdLoginSDK.behavior;
    }

    public final long getExpiresAt() {
        return NidOAuthPreferencesManager.getExpiresAt();
    }

    public final NidOAuthErrorCode getLastErrorCode() {
        return NidOAuthPreferencesManager.getLastErrorCode();
    }

    public final String getLastErrorDescription() {
        return NidOAuthPreferencesManager.getLastErrorDesc();
    }

    public final int getNaverappIntentFlag() [...] // 潜在的解密器

    public final OAuthLoginCallback getOauthLoginCallback() {
        return NaverIdLoginSDK.oauthLoginCallback;
    }

    public final String getRefreshToken() {
        return NidOAuthPreferencesManager.getRefreshToken();
    }

    public final NidOAuthLoginState getState() {
        CharSequence charSequence0 = NidOAuthPreferencesManager.getClientId();
        if(charSequence0 != null && charSequence0.length() != 0) {
            CharSequence charSequence1 = NidOAuthPreferencesManager.getClientSecret();
            if(charSequence1 != null && charSequence1.length() != 0) {
                String s = this.getAccessToken();
                String s1 = this.getRefreshToken();
                if(s != null && s.length() != 0) {
                    return NidOAuthLoginState.OK;
                }
                return s1 == null || s1.length() == 0 ? NidOAuthLoginState.NEED_LOGIN : NidOAuthLoginState.NEED_REFRESH_TOKEN;
            }
            return NidOAuthLoginState.NEED_INIT;
        }
        return NidOAuthLoginState.NEED_INIT;
    }

    public final String getTokenType() {
        return NidOAuthPreferencesManager.getTokenType();
    }

    public final String getVersion() [...] // Inlined contents

    public final void init(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        if(NaverIdLoginSDK.applicationContext == null) {
            NaverIdLoginSDK.applicationContext = context0.getApplicationContext();
        }
    }

    public final void initialize(Context context0, String s, String s1, String s2) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "clientId");
        Intrinsics.checkNotNullParameter(s1, "clientSecret");
        Intrinsics.checkNotNullParameter(s2, "clientName");
        Context context1 = context0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context1, "context.applicationContext");
        EncryptedPreferences.setContext(context1);
        NidOAuthPreferencesManager.setClientId(s);
        NidOAuthPreferencesManager.setClientSecret(s1);
        NidOAuthPreferencesManager.setClientName(s2);
        NidOAuthPreferencesManager.setCallbackUrl("com.ruliweb.www.ruliapp");
        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.NONE);
        NidOAuthPreferencesManager.setLastErrorDesc("");
        NidLog.setPrefix("NaverIdLogin|com.ruliweb.www.ruliapp|");
        NaverIdLoginSDK.applicationContext = context0.getApplicationContext();
    }

    public final boolean isInitialized() [...] // 潜在的解密器

    public final boolean isRequiredCustomTabsReAuth() [...] // 潜在的解密器

    public final boolean isShowBottomTab() {
        return NaverIdLoginSDK.isShowBottomTab;
    }

    public final boolean isShowMarketLink() {
        return NaverIdLoginSDK.isShowMarketLink;
    }

    public final void logout() {
        NidOAuthPreferencesManager.setAccessToken("");
        NidOAuthPreferencesManager.setRefreshToken("");
        NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.NONE);
        NidOAuthPreferencesManager.setLastErrorDesc("");
    }

    public final void reagreeAuthenticate(Context context0, ActivityResultLauncher activityResultLauncher0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(activityResultLauncher0, "launcher");
        this.init(context0);
        if(this.getState() == NidOAuthLoginState.NEED_INIT) {
            Toast.makeText(context0.getApplicationContext(), "SDK 초기화가 필요합니다.", 0).show();
            return;
        }
        NaverIdLoginSDK.oauthLoginCallback = null;
        int v = context0.getResources().getConfiguration().orientation;
        Intent intent0 = new Intent(context0, NidOAuthBridgeActivity.class);
        intent0.putExtra("orientation", v);
        intent0.putExtra("auth_type", "reprompt");
        activityResultLauncher0.launch(intent0);
    }

    public final void reagreeAuthenticate(Context context0, OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(oAuthLoginCallback0, "callback");
        this.init(context0);
        if(this.getState() == NidOAuthLoginState.NEED_INIT) {
            Toast.makeText(context0.getApplicationContext(), "SDK 초기화가 필요합니다.", 0).show();
            return;
        }
        NaverIdLoginSDK.oauthLoginCallback = oAuthLoginCallback0;
        int v = context0.getResources().getConfiguration().orientation;
        Intent intent0 = new Intent(context0, NidOAuthBridgeActivity.class);
        intent0.putExtra("orientation", v);
        intent0.putExtra("auth_type", "reprompt");
        context0.startActivity(intent0);
    }

    public final void setBehavior(NidOAuthBehavior nidOAuthBehavior0) {
        Intrinsics.checkNotNullParameter(nidOAuthBehavior0, "<set-?>");
        NaverIdLoginSDK.behavior = nidOAuthBehavior0;
    }

    public final void setNaverappIntentFlag(int v) {
        NaverIdLoginSDK.naverappIntentFlag = v;
    }

    public final void setOauthLoginCallback(OAuthLoginCallback oAuthLoginCallback0) {
        NaverIdLoginSDK.oauthLoginCallback = oAuthLoginCallback0;
    }

    public final void setRequiredCustomTabsReAuth(boolean z) {
        NaverIdLoginSDK.isRequiredCustomTabsReAuth = z;
    }

    public final void setShowBottomTab(boolean z) {
        NaverIdLoginSDK.isShowBottomTab = z;
    }

    public final void setShowMarketLink(boolean z) {
        NaverIdLoginSDK.isShowMarketLink = z;
    }

    public final void showDevelopersLog(boolean z) {
        NidLog.showLog(z);
    }
}

