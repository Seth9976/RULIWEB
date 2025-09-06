package com.kakao.sdk.auth;

import com.google.gson.JsonObject;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.util.AESCipher;
import com.kakao.sdk.common.util.Cipher;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.common.util.PersistentKVStore.DefaultImpls;
import com.kakao.sdk.common.util.PersistentKVStore;
import com.kakao.sdk.common.util.SdkLog;
import com.kakao.sdk.common.util.SharedPrefsWrapper;
import java.util.Date;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001A\u00020\u000EH\u0016J\n\u0010\u000F\u001A\u0004\u0018\u00010\nH\u0016J\b\u0010\u0010\u001A\u00020\u000EH\u0002J\u0010\u0010\u0011\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\nH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0014"}, d2 = {"Lcom/kakao/sdk/auth/TokenManager;", "Lcom/kakao/sdk/auth/TokenManageable;", "appCache", "Lcom/kakao/sdk/common/util/PersistentKVStore;", "encryptor", "Lcom/kakao/sdk/common/util/Cipher;", "(Lcom/kakao/sdk/common/util/PersistentKVStore;Lcom/kakao/sdk/common/util/Cipher;)V", "getAppCache", "()Lcom/kakao/sdk/common/util/PersistentKVStore;", "currentToken", "Lcom/kakao/sdk/auth/model/OAuthToken;", "getEncryptor", "()Lcom/kakao/sdk/common/util/Cipher;", "clear", "", "getToken", "migrateFromOldVersion", "setToken", "token", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class TokenManager implements TokenManageable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0006\u001A\u00020\u00078FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000B\u0010\f\u0012\u0004\b\b\u0010\u0002\u001A\u0004\b\t\u0010\nR\u000E\u0010\r\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/kakao/sdk/auth/TokenManager$Companion;", "", "()V", "atExpiresAtKey", "", "atKey", "instance", "Lcom/kakao/sdk/auth/TokenManager;", "getInstance$annotations", "getInstance", "()Lcom/kakao/sdk/auth/TokenManager;", "instance$delegate", "Lkotlin/Lazy;", "rtExpiresAtKey", "rtKey", "secureModeKey", "tokenKey", "versionKey", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        static final KProperty[] $$delegatedProperties;

        static {
            Companion.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/kakao/sdk/auth/TokenManager;"))};
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final TokenManager getInstance() {
            return (TokenManager)TokenManager.instance$delegate.getValue();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }
    }

    public static final Companion Companion = null;
    private final PersistentKVStore appCache;
    public static final String atExpiresAtKey = "com.kakao.token.OAuthToken.ExpiresAt";
    public static final String atKey = "com.kakao.token.AccessToken";
    private OAuthToken currentToken;
    private final Cipher encryptor;
    private static final Lazy instance$delegate = null;
    public static final String rtExpiresAtKey = "com.kakao.token.RefreshToken.ExpiresAt";
    public static final String rtKey = "com.kakao.token.RefreshToken";
    public static final String secureModeKey = "com.kakao.token.KakaoSecureMode";
    public static final String tokenKey = "com.kakao.sdk.oauth_token";
    public static final String versionKey = "com.kakao.sdk.version";

    static {
        TokenManager.Companion = new Companion(null);
        TokenManager.instance$delegate = LazyKt.lazy(TokenManager.Companion.instance.2.INSTANCE);
    }

    public TokenManager() {
        this(null, null, 3, null);
    }

    public TokenManager(PersistentKVStore persistentKVStore0, Cipher cipher0) {
        Intrinsics.checkNotNullParameter(persistentKVStore0, "appCache");
        Intrinsics.checkNotNullParameter(cipher0, "encryptor");
        super();
        this.appCache = persistentKVStore0;
        this.encryptor = cipher0;
        OAuthToken oAuthToken0 = null;
        if(DefaultImpls.getString$default(persistentKVStore0, "com.kakao.sdk.version", null, 2, null) == null) {
            this.migrateFromOldVersion();
        }
        String s = DefaultImpls.getString$default(persistentKVStore0, "com.kakao.sdk.oauth_token", null, 2, null);
        if(s != null) {
            try {
                String s1 = this.getEncryptor().decrypt(s);
                oAuthToken0 = (OAuthToken)KakaoJson.INSTANCE.fromJson(s1, OAuthToken.class);
            }
            catch(Throwable throwable0) {
                SdkLog.Companion.e(throwable0);
            }
        }
        this.currentToken = oAuthToken0;
    }

    public TokenManager(PersistentKVStore persistentKVStore0, Cipher cipher0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            persistentKVStore0 = new SharedPrefsWrapper(KakaoSdk.INSTANCE.getApplicationContextInfo().getSharedPreferences());
        }
        if((v & 2) != 0) {
            cipher0 = new AESCipher(null, 1, null);
        }
        this(persistentKVStore0, cipher0);
    }

    @Override  // com.kakao.sdk.auth.TokenManageable
    public void clear() {
        this.currentToken = null;
        this.appCache.remove("com.kakao.sdk.oauth_token").commit();
    }

    public final PersistentKVStore getAppCache() {
        return this.appCache;
    }

    public final Cipher getEncryptor() {
        return this.encryptor;
    }

    public static final TokenManager getInstance() {
        return TokenManager.Companion.getInstance();
    }

    @Override  // com.kakao.sdk.auth.TokenManageable
    public OAuthToken getToken() {
        return this.currentToken;
    }

    private final void migrateFromOldVersion() {
        Long long1;
        String s8;
        String s7;
        String s5;
        String s4;
        SdkLog.Companion.i("=== Migrate from old version token");
        this.appCache.putString("com.kakao.sdk.version", "2.13.0").commit();
        Long long0 = null;
        String s = this.appCache.getString("com.kakao.token.KakaoSecureMode", null);
        String s1 = "false";
        if(s != null) {
            String s2 = ((JsonObject)KakaoJson.INSTANCE.fromJson(s, JsonObject.class)).get("value").getAsString();
            if(s2 != null) {
                s1 = s2;
            }
        }
        SdkLog.Companion.i("secureMode: " + s1);
        try {
            String s3 = this.getAppCache().getString("com.kakao.token.AccessToken", null);
            if(s3 == null) {
                s4 = null;
            }
            else {
                s4 = ((JsonObject)KakaoJson.INSTANCE.fromJson(s3, JsonObject.class)).get("value").getAsString();
                if(s4 != null && Intrinsics.areEqual(s1, "true")) {
                    s4 = this.getEncryptor().decrypt(s4);
                }
            }
            s5 = s4;
        }
        catch(Exception exception0) {
            SdkLog.Companion.e(exception0);
            s5 = null;
        }
        SdkLog.Companion.i("accessToken: " + s5);
        try {
            String s6 = this.getAppCache().getString("com.kakao.token.RefreshToken", null);
            if(s6 == null) {
                s7 = null;
            }
            else {
                s7 = ((JsonObject)KakaoJson.INSTANCE.fromJson(s6, JsonObject.class)).get("value").getAsString();
                if(s7 != null && Intrinsics.areEqual(s1, "true")) {
                    s7 = this.getEncryptor().decrypt(s7);
                }
            }
            s8 = s7;
        }
        catch(Exception exception1) {
            SdkLog.Companion.e(exception1);
            s8 = null;
        }
        SdkLog.Companion.i("refreshToken: " + s8);
        try {
            String s9 = this.getAppCache().getString("com.kakao.token.OAuthToken.ExpiresAt", null);
            if(s9 == null) {
                goto label_44;
            }
            else {
                long1 = ((JsonObject)KakaoJson.INSTANCE.fromJson(s9, JsonObject.class)).get("value").getAsLong();
            }
        }
        catch(Exception exception2) {
            SdkLog.Companion.e(exception2);
            long1 = null;
        }
        goto label_45;
    label_44:
        long1 = null;
    label_45:
        long v = long1 == null ? 0L : ((long)long1);
        try {
            String s10 = this.getAppCache().getString("com.kakao.token.RefreshToken.ExpiresAt", null);
            if(s10 != null) {
                long0 = ((JsonObject)KakaoJson.INSTANCE.fromJson(s10, JsonObject.class)).get("value").getAsLong();
            }
        }
        catch(Exception exception3) {
            SdkLog.Companion.e(exception3);
        }
        long v1 = long0 == null ? 0x7FFFFFFFFFFFFFFFL : ((long)long0);
        if(s5 != null && s8 != null) {
            OAuthToken oAuthToken0 = new OAuthToken(s5, new Date(v), s8, new Date(v1), null, null, 0x30, null);
            String s11 = KakaoJson.INSTANCE.toJson(oAuthToken0);
            String s12 = this.encryptor.encrypt(s11);
            this.appCache.putString("com.kakao.sdk.oauth_token", s12).remove("com.kakao.token.KakaoSecureMode").remove("com.kakao.token.AccessToken").remove("com.kakao.token.RefreshToken").remove("com.kakao.token.OAuthToken.ExpiresAt").remove("com.kakao.token.RefreshToken.ExpiresAt").commit();
        }
    }

    @Override  // com.kakao.sdk.auth.TokenManageable
    public void setToken(OAuthToken oAuthToken0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(oAuthToken0, "token");
            OAuthToken oAuthToken1 = new OAuthToken(oAuthToken0.getAccessToken(), oAuthToken0.getAccessTokenExpiresAt(), oAuthToken0.getRefreshToken(), oAuthToken0.getRefreshTokenExpiresAt(), null, oAuthToken0.getScopes(), 16, null);
            try {
                String s = KakaoJson.INSTANCE.toJson(oAuthToken1);
                String s1 = this.encryptor.encrypt(s);
                this.appCache.putString("com.kakao.sdk.oauth_token", s1).commit();
            }
            catch(Throwable throwable0) {
                SdkLog.Companion.e(throwable0);
            }
            this.currentToken = oAuthToken1;
        }
    }
}

