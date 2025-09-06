package com.kakao.sdk.common.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import com.google.gson.JsonObject;
import com.kakao.sdk.common.KakaoSdk.Type;
import com.kakao.sdk.common.util.Utility;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\u0018\u00002\u00020\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fR\u0014\u0010\u0005\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0014\u0010\u000F\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u000ER\u0011\u0010\u0011\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0007\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u000ER\u0014\u0010\u0015\u001A\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u000ER\u000E\u0010\u001B\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001C\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001D\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001E\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u001F\u001A\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010 \u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\"\u001A\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010$\u001A\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\'\u0010\u000ER\u0014\u0010(\u001A\u00020#8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b)\u0010*R\u0011\u0010+\u001A\u00020%8F¢\u0006\u0006\u001A\u0004\b,\u0010-R\u0014\u0010.\u001A\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b/\u0010\u000E¨\u00060"}, d2 = {"Lcom/kakao/sdk/common/model/ApplicationContextInfo;", "Lcom/kakao/sdk/common/model/ApplicationInfo;", "Lcom/kakao/sdk/common/model/ContextInfo;", "context", "Landroid/content/Context;", "appKey", "", "customScheme", "sdkType", "Lcom/kakao/sdk/common/KakaoSdk$Type;", "sdkIdentifier", "Lcom/kakao/sdk/common/model/SdkIdentifier;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/kakao/sdk/common/KakaoSdk$Type;Lcom/kakao/sdk/common/model/SdkIdentifier;)V", "getAppKey", "()Ljava/lang/String;", "appVer", "getAppVer", "applicationContext", "getApplicationContext", "()Landroid/content/Context;", "getCustomScheme", "extras", "Lcom/google/gson/JsonObject;", "getExtras", "()Lcom/google/gson/JsonObject;", "kaHeader", "getKaHeader", "mAppVer", "mApplicationContext", "mClientId", "mCustomScheme", "mExtras", "mKaHeader", "mKeyHash", "mSalt", "", "mSharedPreferences", "Landroid/content/SharedPreferences;", "redirectUri", "getRedirectUri", "salt", "getSalt", "()[B", "sharedPreferences", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "signingKeyHash", "getSigningKeyHash", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ApplicationContextInfo implements ApplicationInfo, ContextInfo {
    private final String mAppVer;
    private final Context mApplicationContext;
    private final String mClientId;
    private final String mCustomScheme;
    private final JsonObject mExtras;
    private final String mKaHeader;
    private final String mKeyHash;
    private final byte[] mSalt;
    private final SharedPreferences mSharedPreferences;

    public ApplicationContextInfo(Context context0, String s, String s1, Type kakaoSdk$Type0, SdkIdentifier sdkIdentifier0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        String s2;
        Intrinsics.checkNotNullParameter(s, "appKey");
        Intrinsics.checkNotNullParameter(s1, "customScheme");
        Intrinsics.checkNotNullParameter(kakaoSdk$Type0, "sdkType");
        Intrinsics.checkNotNullParameter(sdkIdentifier0, "sdkIdentifier");
        super();
        this.mClientId = s;
        this.mCustomScheme = s1;
        this.mKaHeader = Utility.INSTANCE.getKAHeader(context0, kakaoSdk$Type0, sdkIdentifier0);
        this.mKeyHash = Utility.INSTANCE.getKeyHash(context0);
        this.mExtras = Utility.INSTANCE.getExtras(context0, kakaoSdk$Type0);
        SharedPreferences sharedPreferences0 = context0.getSharedPreferences(s, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences0, "context.getSharedPreferences(appKey, Context.MODE_PRIVATE)");
        this.mSharedPreferences = sharedPreferences0;
        if(Build.VERSION.SDK_INT >= 33) {
            s2 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0.getPackageManager(), "com.ruliweb.www.ruliapp", LinkFollowing..ExternalSyntheticApiModelOutline0.m(0L)).versionName;
            Intrinsics.checkNotNullExpressionValue(s2, "{\n        context.packageManager.getPackageInfo(\n            context.packageName,\n            PackageManager.PackageInfoFlags.of(0)\n        ).versionName\n    }");
        }
        else {
            s2 = context0.getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0).versionName;
            Intrinsics.checkNotNullExpressionValue(s2, "{\n        @Suppress(\"DEPRECATION\")\n        context.packageManager.getPackageInfo(context.packageName, 0).versionName\n    }");
        }
        this.mAppVer = s2;
        this.mSalt = Utility.INSTANCE.androidId(context0);
        Context context1 = context0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context1, "context.applicationContext");
        this.mApplicationContext = context1;
    }

    @Override  // com.kakao.sdk.common.model.ApplicationInfo
    public String getAppKey() {
        return this.mClientId;
    }

    @Override  // com.kakao.sdk.common.model.ContextInfo
    public String getAppVer() {
        return this.mAppVer;
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    @Override  // com.kakao.sdk.common.model.ApplicationInfo
    public String getCustomScheme() {
        return this.mCustomScheme;
    }

    @Override  // com.kakao.sdk.common.model.ContextInfo
    public JsonObject getExtras() {
        return this.mExtras;
    }

    @Override  // com.kakao.sdk.common.model.ContextInfo
    public String getKaHeader() {
        return this.mKaHeader;
    }

    @Override  // com.kakao.sdk.common.model.ApplicationInfo
    public String getRedirectUri() {
        return this.mCustomScheme + "://oauth";
    }

    @Override  // com.kakao.sdk.common.model.ContextInfo
    public byte[] getSalt() {
        return this.mSalt;
    }

    public final SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }

    @Override  // com.kakao.sdk.common.model.ContextInfo
    public String getSigningKeyHash() {
        return this.mKeyHash;
    }
}

