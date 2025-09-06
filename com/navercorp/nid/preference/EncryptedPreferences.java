package com.navercorp.nid.preference;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme;
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey.Builder;
import androidx.security.crypto.MasterKey.KeyScheme;
import androidx.security.crypto.MasterKey;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.oauth.NidOAuthPreferencesManager;
import java.util.List;
import java.util.Map.Entry;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\t\b\u00C6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0015\u001A\u00020\u0004H\u0002J\u000E\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0004J\u0018\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u0018\u001A\u00020\u00042\b\b\u0002\u0010\u001B\u001A\u00020\u001AJ\u0018\u0010\u0019\u001A\u00020\u001C2\u0006\u0010\u0018\u001A\u00020\u00042\b\b\u0002\u0010\u001B\u001A\u00020\u001CJ\u0018\u0010\u0019\u001A\u00020\u001D2\u0006\u0010\u0018\u001A\u00020\u00042\b\b\u0002\u0010\u001B\u001A\u00020\u001DJ\u001C\u0010\u0019\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001A\u00020\u00042\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\u0004J\b\u0010\u001E\u001A\u00020\u0006H\u0002J\b\u0010\u001F\u001A\u00020\bH\u0002J\b\u0010 \u001A\u00020\u0017H\u0002J\u0010\u0010!\u001A\u00020\u00172\u0006\u0010\"\u001A\u00020\bH\u0002J\u001A\u0010#\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\b\u0010$\u001A\u0004\u0018\u00010\u0001H\u0002J\u0016\u0010#\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010$\u001A\u00020\u001AJ\u0016\u0010#\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010$\u001A\u00020\u001CJ\u0016\u0010#\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010$\u001A\u00020\u001DJ\u0018\u0010#\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\b\u0010$\u001A\u0004\u0018\u00010\u0004J\u0010\u0010%\u001A\u00020\u00172\u0006\u0010\u0005\u001A\u00020\u0006H\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001B\u0010\u0007\u001A\u00020\b8BX\u0082\u0084\u0002\u00A2\u0006\f\n\u0004\b\u000B\u0010\f\u001A\u0004\b\t\u0010\nR\u0014\u0010\r\u001A\u00020\u000E8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0014\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006&"}, d2 = {"Lcom/navercorp/nid/preference/EncryptedPreferences;", "", "()V", "OLD_OAUTH_LOGIN_PREF_NAME", "", "context", "Landroid/content/Context;", "encryptedPreferences", "Landroid/content/SharedPreferences;", "getEncryptedPreferences", "()Landroid/content/SharedPreferences;", "encryptedPreferences$delegate", "Lkotlin/Lazy;", "masterKey", "Landroidx/security/crypto/MasterKey;", "getMasterKey", "()Landroidx/security/crypto/MasterKey;", "workaround", "", "Lcom/navercorp/nid/preference/EncryptedSharedPreferenceWorkaround;", "createSharedPreferences", "preferencesName", "del", "", "key", "get", "", "defaultValue", "", "", "getCtx", "init", "migration", "moveToData", "preferences", "set", "value", "setContext", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class EncryptedPreferences {
    public static final EncryptedPreferences INSTANCE = null;
    private static final String OLD_OAUTH_LOGIN_PREF_NAME = "NaverOAuthLoginPreferenceData";
    private static Context context;
    private static final Lazy encryptedPreferences$delegate;
    private static final List workaround;

    static {
        EncryptedPreferences.INSTANCE = new EncryptedPreferences();
        EncryptedPreferences.encryptedPreferences$delegate = LazyKt.lazy(EncryptedPreferences.encryptedPreferences.2.INSTANCE);
        EncryptedPreferences.workaround = CollectionsKt.listOf(new EncryptedSharedPreferenceWorkaround[]{new IncompatibleSharedPreferencesWorkaround(), new KeyStoreSharedPreferencesWorkaround(), new AEADBadTagSharedPreferencesWorkaround(), new GeneralSecurityPreferencesWorkaround(), new InvalidKeySharedPreferencesWorkaround()});
    }

    public static final SharedPreferences access$init(EncryptedPreferences encryptedPreferences0) {
        return encryptedPreferences0.init();
    }

    private final SharedPreferences createSharedPreferences(Context context0, String s) {
        SharedPreferences sharedPreferences0 = EncryptedSharedPreferences.create(context0, s, this.getMasterKey(), PrefKeyEncryptionScheme.AES256_SIV, PrefValueEncryptionScheme.AES256_GCM);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences0, "create(\n            cont…heme.AES256_GCM\n        )");
        return sharedPreferences0;
    }

    public final void del(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        SharedPreferences.Editor sharedPreferences$Editor0 = this.getEncryptedPreferences().edit();
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
        sharedPreferences$Editor0.remove(s);
        sharedPreferences$Editor0.apply();
    }

    public final int get(String s, int v) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.getEncryptedPreferences().getInt(s, v);
        }
    }

    public final long get(String s, long v) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.getEncryptedPreferences().getLong(s, v);
        }
    }

    public final String get(String s, String s1) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.getEncryptedPreferences().getString(s, s1);
        }
    }

    public final boolean get(String s, boolean z) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.getEncryptedPreferences().getBoolean(s, z);
        }
    }

    public static int get$default(EncryptedPreferences encryptedPreferences0, String s, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0;
        }
        return encryptedPreferences0.get(s, v);
    }

    public static long get$default(EncryptedPreferences encryptedPreferences0, String s, long v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0L;
        }
        return encryptedPreferences0.get(s, v);
    }

    public static String get$default(EncryptedPreferences encryptedPreferences0, String s, String s1, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        return encryptedPreferences0.get(s, s1);
    }

    public static boolean get$default(EncryptedPreferences encryptedPreferences0, String s, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = true;
        }
        return encryptedPreferences0.get(s, z);
    }

    private final Context getCtx() {
        return EncryptedPreferences.context == null ? NaverIdLoginSDK.INSTANCE.getApplicationContext() : EncryptedPreferences.context;
    }

    private final SharedPreferences getEncryptedPreferences() {
        return (SharedPreferences)EncryptedPreferences.encryptedPreferences$delegate.getValue();
    }

    private final MasterKey getMasterKey() {
        MasterKey masterKey0 = new Builder(this.getCtx()).setKeyScheme(KeyScheme.AES256_GCM).setUserAuthenticationRequired(false).build();
        Intrinsics.checkNotNullExpressionValue(masterKey0, "Builder(getCtx())\n      …\n                .build()");
        return masterKey0;
    }

    private final SharedPreferences init() {
        Object object2;
        Object object0;
        try {
            object0 = Result.constructor-impl(this.createSharedPreferences(this.getCtx(), "NaverOAuthLoginEncryptedPreferenceData"));
        }
        catch(Throwable throwable0) {
            object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        Throwable throwable1 = Result.exceptionOrNull-impl(object0);
        if(throwable1 != null) {
            try {
                for(Object object1: EncryptedPreferences.workaround) {
                    ((EncryptedSharedPreferenceWorkaround)object1).apply(EncryptedPreferences.INSTANCE.getCtx(), "NaverOAuthLoginEncryptedPreferenceData", throwable1);
                }
                Context context0 = EncryptedPreferences.INSTANCE.getCtx();
                object2 = Result.constructor-impl(EncryptedPreferences.INSTANCE.createSharedPreferences(context0, "NaverOAuthLoginEncryptedPreferenceData"));
            }
            catch(Throwable throwable2) {
                object2 = Result.constructor-impl(ResultKt.createFailure(throwable2));
            }
            object0 = object2;
        }
        Throwable throwable3 = Result.exceptionOrNull-impl(object0);
        if(throwable3 != null) {
            throw throwable3;
        }
        return (SharedPreferences)object0;
    }

    private final void migration() {
        Unit unit0;
        CharSequence charSequence0 = NidOAuthPreferencesManager.getClientId();
        if(charSequence0 != null && charSequence0.length() != 0) {
            return;
        }
        SharedPreferences sharedPreferences0 = this.getCtx().getSharedPreferences("NaverOAuthLoginPreferenceData", 0);
        try {
            Intrinsics.checkNotNullExpressionValue(sharedPreferences0, "oldPreference");
            EncryptedPreferences.INSTANCE.moveToData(sharedPreferences0);
            unit0 = Unit.INSTANCE;
        }
        catch(Throwable throwable0) {
            unit0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        Throwable throwable1 = Result.exceptionOrNull-impl(unit0);
        if(throwable1 != null && throwable1 instanceof SecurityException) {
            Intrinsics.checkNotNullExpressionValue(sharedPreferences0, "oldPreference");
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
            for(Object object0: NidOAuthPreferencesManager.INSTANCE.getKeyList()) {
                sharedPreferences$Editor0.remove(((String)object0));
            }
            sharedPreferences$Editor0.apply();
            SharedPreferences sharedPreferences1 = EncryptedSharedPreferences.create(EncryptedPreferences.INSTANCE.getCtx(), "NaverOAuthLoginPreferenceData", EncryptedPreferences.INSTANCE.getMasterKey(), PrefKeyEncryptionScheme.AES256_SIV, PrefValueEncryptionScheme.AES256_GCM);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences1, "oldPreference");
            EncryptedPreferences.INSTANCE.moveToData(sharedPreferences1);
            SharedPreferences.Editor sharedPreferences$Editor1 = sharedPreferences1.edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor1, "editor");
            sharedPreferences$Editor1.clear();
            sharedPreferences$Editor1.apply();
            sharedPreferences0 = sharedPreferences1;
        }
        if(Build.VERSION.SDK_INT >= 24) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(EncryptedPreferences.INSTANCE.getCtx(), "NaverOAuthLoginPreferenceData");
            return;
        }
        Intrinsics.checkNotNullExpressionValue(sharedPreferences0, "oldPreference");
        SharedPreferences.Editor sharedPreferences$Editor2 = sharedPreferences0.edit();
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor2, "editor");
        sharedPreferences$Editor2.clear();
        sharedPreferences$Editor2.apply();
    }

    private final void moveToData(SharedPreferences sharedPreferences0) throws SecurityException {
        for(Object object0: sharedPreferences0.getAll().entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            Object object1 = ((Map.Entry)object0).getValue();
            Intrinsics.checkNotNullExpressionValue(s, "key");
            this.set(s, object1);
        }
    }

    private final void set(String s, Object object0) throws SecurityException {
        if(object0 instanceof Integer) {
            this.set(s, ((Number)object0).intValue());
            return;
        }
        if(object0 instanceof Long) {
            this.set(s, ((Number)object0).longValue());
            return;
        }
        if((object0 == null ? true : object0 instanceof String)) {
            this.set(s, ((String)object0));
            return;
        }
        if(object0 instanceof Boolean) {
            this.set(s, ((Boolean)object0).booleanValue());
            return;
        }
        NidLog.d("EncryptedPreferences", "Preferences Set() fail | key:" + s);
    }

    public final void set(String s, int v) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            SharedPreferences.Editor sharedPreferences$Editor0 = this.getEncryptedPreferences().edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
            sharedPreferences$Editor0.putInt(s, v);
            sharedPreferences$Editor0.apply();
        }
    }

    public final void set(String s, long v) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            SharedPreferences.Editor sharedPreferences$Editor0 = this.getEncryptedPreferences().edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
            sharedPreferences$Editor0.putLong(s, v);
            sharedPreferences$Editor0.apply();
        }
    }

    public final void set(String s, String s1) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            SharedPreferences.Editor sharedPreferences$Editor0 = this.getEncryptedPreferences().edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
            sharedPreferences$Editor0.putString(s, s1);
            sharedPreferences$Editor0.apply();
        }
    }

    public final void set(String s, boolean z) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(s, "key");
            SharedPreferences.Editor sharedPreferences$Editor0 = this.getEncryptedPreferences().edit();
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences$Editor0, "editor");
            sharedPreferences$Editor0.putBoolean(s, z);
            sharedPreferences$Editor0.apply();
        }
    }

    @JvmStatic
    public static final void setContext(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        EncryptedPreferences.context = context0;
        EncryptedPreferences.INSTANCE.migration();
    }
}

