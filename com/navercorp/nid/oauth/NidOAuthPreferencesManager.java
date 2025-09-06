package com.navercorp.nid.oauth;

import android.media.UnsupportedSchemeException;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.preference.EncryptedPreferences;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b.\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u00C6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R.\u0010\u0014\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b\u0015\u0010\u0002\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R.\u0010\u001A\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b\u001B\u0010\u0002\u001A\u0004\b\u001C\u0010\u0017\"\u0004\b\u001D\u0010\u0019R.\u0010\u001E\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b\u001F\u0010\u0002\u001A\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R.\u0010\"\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b#\u0010\u0002\u001A\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R.\u0010&\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b\'\u0010\u0002\u001A\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R(\u0010*\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0086\u000E\u00A2\u0006\f\u001A\u0004\b+\u0010\u0017\"\u0004\b,\u0010\u0019R(\u0010-\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0086\u000E\u00A2\u0006\f\u001A\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R(\u00100\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0086\u000E\u00A2\u0006\f\u001A\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R*\u00104\u001A\u0002032\u0006\u0010\u0013\u001A\u0002038F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\b5\u0010\u0002\u001A\u0004\b6\u00107\"\u0004\b8\u00109R(\u0010:\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0086\u000E\u00A2\u0006\f\u001A\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019R\u0017\u0010=\u001A\b\u0012\u0004\u0012\u00020\u00040>\u00A2\u0006\b\n\u0000\u001A\u0004\b?\u0010@R*\u0010B\u001A\u00020A2\u0006\u0010\u0013\u001A\u00020A8F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\bC\u0010\u0002\u001A\u0004\bD\u0010E\"\u0004\bF\u0010GR.\u0010H\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\bI\u0010\u0002\u001A\u0004\bJ\u0010\u0017\"\u0004\bK\u0010\u0019R.\u0010L\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\bM\u0010\u0002\u001A\u0004\bN\u0010\u0017\"\u0004\bO\u0010\u0019R(\u0010P\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0086\u000E\u00A2\u0006\f\u001A\u0004\bQ\u0010\u0017\"\u0004\bR\u0010\u0019R.\u0010S\u001A\u0004\u0018\u00010\u00042\b\u0010\u0013\u001A\u0004\u0018\u00010\u00048F@FX\u0087\u000E\u00A2\u0006\u0012\u0012\u0004\bT\u0010\u0002\u001A\u0004\bU\u0010\u0017\"\u0004\bV\u0010\u0019\u00A8\u0006W"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthPreferencesManager;", "", "()V", "ACCESS_TOKEN", "", "CALLBACK_URL", "CLIENT_ID", "CLIENT_NAME", "CLIENT_SECRET", "EXPIRES_AT", "LAST_ERROR_CODE", "LAST_ERROR_DESC", "OAUTH_CHECK_STATE", "OAUTH_CODE", "OAUTH_ERROR_CODE", "OAUTH_ERROR_DESCRIPTION", "OAUTH_INIT_STATE", "REFRESH_TOKEN", "TOKEN_TYPE", "value", "accessToken", "getAccessToken$annotations", "getAccessToken", "()Ljava/lang/String;", "setAccessToken", "(Ljava/lang/String;)V", "callbackUrl", "getCallbackUrl$annotations", "getCallbackUrl", "setCallbackUrl", "clientId", "getClientId$annotations", "getClientId", "setClientId", "clientName", "getClientName$annotations", "getClientName", "setClientName", "clientSecret", "getClientSecret$annotations", "getClientSecret", "setClientSecret", "code", "getCode", "setCode", "errorCode", "getErrorCode", "setErrorCode", "errorDescription", "getErrorDescription", "setErrorDescription", "", "expiresAt", "getExpiresAt$annotations", "getExpiresAt", "()J", "setExpiresAt", "(J)V", "initState", "getInitState", "setInitState", "keyList", "", "getKeyList", "()Ljava/util/List;", "Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "lastErrorCode", "getLastErrorCode$annotations", "getLastErrorCode", "()Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "setLastErrorCode", "(Lcom/navercorp/nid/oauth/NidOAuthErrorCode;)V", "lastErrorDesc", "getLastErrorDesc$annotations", "getLastErrorDesc", "setLastErrorDesc", "refreshToken", "getRefreshToken$annotations", "getRefreshToken", "setRefreshToken", "state", "getState", "setState", "tokenType", "getTokenType$annotations", "getTokenType", "setTokenType", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthPreferencesManager {
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String CALLBACK_URL = "CALLBACK_URL";
    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String CLIENT_NAME = "CLIENT_NAME";
    private static final String CLIENT_SECRET = "CLIENT_SECRET";
    private static final String EXPIRES_AT = "EXPIRES_AT";
    public static final NidOAuthPreferencesManager INSTANCE = null;
    private static final String LAST_ERROR_CODE = "LAST_ERROR_CODE";
    private static final String LAST_ERROR_DESC = "LAST_ERROR_DESC";
    private static final String OAUTH_CHECK_STATE = "OAUTH_CHECK_STATE";
    private static final String OAUTH_CODE = "OAUTH_CODE";
    private static final String OAUTH_ERROR_CODE = "OAUTH_ERROR_CODE";
    private static final String OAUTH_ERROR_DESCRIPTION = "OAUTH_ERROR_DESCRIPTION";
    private static final String OAUTH_INIT_STATE = "OAUTH_INIT_STATE";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String TOKEN_TYPE = "TOKEN_TYPE";
    private static final List keyList;

    static {
        NidOAuthPreferencesManager.INSTANCE = new NidOAuthPreferencesManager();
        NidOAuthPreferencesManager.keyList = CollectionsKt.listOf(new String[]{"ACCESS_TOKEN", "REFRESH_TOKEN", "EXPIRES_AT", "CLIENT_ID", "CLIENT_SECRET", "CLIENT_NAME", "CALLBACK_URL", "TOKEN_TYPE", "LAST_ERROR_CODE", "LAST_ERROR_DESC", "OAUTH_INIT_STATE", "OAUTH_CODE", "OAUTH_CHECK_STATE", "OAUTH_ERROR_CODE", "OAUTH_ERROR_DESCRIPTION"});
    }

    public static final String getAccessToken() {
        String s = EncryptedPreferences.INSTANCE.get("ACCESS_TOKEN", null);
        if(s != null && s.length() != 0) {
            if(System.currentTimeMillis() / 1000L - NidOAuthPreferencesManager.getExpiresAt() < 0L) {
                return s;
            }
            NidLog.i("OAuthLoginEncryptedPreferenceManager", "access token is expired.");
        }
        return null;
    }

    @JvmStatic
    public static void getAccessToken$annotations() {
    }

    public static final String getCallbackUrl() {
        return EncryptedPreferences.INSTANCE.get("CALLBACK_URL", null);
    }

    @JvmStatic
    public static void getCallbackUrl$annotations() {
    }

    public static final String getClientId() {
        return EncryptedPreferences.INSTANCE.get("CLIENT_ID", null);
    }

    @JvmStatic
    public static void getClientId$annotations() {
    }

    public static final String getClientName() {
        return EncryptedPreferences.INSTANCE.get("CLIENT_NAME", null);
    }

    @JvmStatic
    public static void getClientName$annotations() {
    }

    public static final String getClientSecret() {
        return EncryptedPreferences.INSTANCE.get("CLIENT_SECRET", null);
    }

    @JvmStatic
    public static void getClientSecret$annotations() {
    }

    public final String getCode() {
        return EncryptedPreferences.INSTANCE.get("OAUTH_CODE", null);
    }

    public final String getErrorCode() {
        return EncryptedPreferences.INSTANCE.get("OAUTH_ERROR_CODE", null);
    }

    public final String getErrorDescription() {
        return EncryptedPreferences.INSTANCE.get("OAUTH_ERROR_DESCRIPTION", null);
    }

    public static final long getExpiresAt() {
        return EncryptedPreferences.INSTANCE.get("EXPIRES_AT", 0L);
    }

    @JvmStatic
    public static void getExpiresAt$annotations() {
    }

    public final String getInitState() {
        String s = EncryptedPreferences.INSTANCE.get("OAUTH_INIT_STATE", null);
        if(s != null) {
            return s;
        }
        String s1 = new BigInteger(130, new SecureRandom()).toString(0x20);
        try {
            s1 = URLEncoder.encode(s1, "UTF-8");
        }
        catch(UnsupportedSchemeException unsupportedSchemeException0) {
            NidLog.e("OAuthLoginEncryptedPreferenceManager", unsupportedSchemeException0);
        }
        this.setInitState(s1);
        return s1;
    }

    public final List getKeyList() {
        return NidOAuthPreferencesManager.keyList;
    }

    public static final NidOAuthErrorCode getLastErrorCode() {
        String s = EncryptedPreferences.INSTANCE.get("LAST_ERROR_CODE", null);
        if(s == null) {
            s = "";
        }
        return NidOAuthErrorCode.INSTANCE.fromString(s);
    }

    @JvmStatic
    public static void getLastErrorCode$annotations() {
    }

    public static final String getLastErrorDesc() {
        return EncryptedPreferences.INSTANCE.get("LAST_ERROR_DESC", null);
    }

    @JvmStatic
    public static void getLastErrorDesc$annotations() {
    }

    public static final String getRefreshToken() {
        String s = EncryptedPreferences.INSTANCE.get("REFRESH_TOKEN", null);
        return s == null || s.length() == 0 ? null : s;
    }

    @JvmStatic
    public static void getRefreshToken$annotations() {
    }

    public final String getState() {
        return EncryptedPreferences.INSTANCE.get("OAUTH_CHECK_STATE", null);
    }

    public static final String getTokenType() {
        return EncryptedPreferences.INSTANCE.get("TOKEN_TYPE", null);
    }

    @JvmStatic
    public static void getTokenType$annotations() {
    }

    public static final void setAccessToken(String s) {
        EncryptedPreferences.INSTANCE.set("ACCESS_TOKEN", s);
    }

    public static final void setCallbackUrl(String s) {
        EncryptedPreferences.INSTANCE.set("CALLBACK_URL", s);
    }

    public static final void setClientId(String s) {
        EncryptedPreferences.INSTANCE.set("CLIENT_ID", s);
    }

    public static final void setClientName(String s) {
        EncryptedPreferences.INSTANCE.set("CLIENT_NAME", s);
    }

    public static final void setClientSecret(String s) {
        EncryptedPreferences.INSTANCE.set("CLIENT_SECRET", s);
    }

    public final void setCode(String s) {
        EncryptedPreferences.INSTANCE.set("OAUTH_CODE", s);
    }

    public final void setErrorCode(String s) {
        EncryptedPreferences.INSTANCE.set("OAUTH_ERROR_CODE", s);
    }

    public final void setErrorDescription(String s) {
        EncryptedPreferences.INSTANCE.set("OAUTH_ERROR_DESCRIPTION", s);
    }

    public static final void setExpiresAt(long v) {
        EncryptedPreferences.INSTANCE.set("EXPIRES_AT", v);
    }

    public final void setInitState(String s) {
        EncryptedPreferences.INSTANCE.set("OAUTH_INIT_STATE", s);
    }

    public static final void setLastErrorCode(NidOAuthErrorCode nidOAuthErrorCode0) {
        Intrinsics.checkNotNullParameter(nidOAuthErrorCode0, "value");
        EncryptedPreferences.INSTANCE.set("LAST_ERROR_CODE", nidOAuthErrorCode0.getCode());
    }

    public static final void setLastErrorDesc(String s) {
        EncryptedPreferences.INSTANCE.set("LAST_ERROR_DESC", s);
    }

    public static final void setRefreshToken(String s) {
        EncryptedPreferences.INSTANCE.set("REFRESH_TOKEN", s);
    }

    public final void setState(String s) {
        EncryptedPreferences.INSTANCE.set("OAUTH_CHECK_STATE", s);
    }

    public static final void setTokenType(String s) {
        EncryptedPreferences.INSTANCE.set("TOKEN_TYPE", s);
    }
}

