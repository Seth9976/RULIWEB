package com.navercorp.nid.preference;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000B\u001A\u00020\fH\u0016J\u0010\u0010\r\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\fH\u0002R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lcom/navercorp/nid/preference/AEADBadTagSharedPreferencesWorkaround;", "Lcom/navercorp/nid/preference/EncryptedSharedPreferenceWorkaround;", "()V", "statusMap", "", "", "", "apply", "context", "Landroid/content/Context;", "fileName", "throwable", "", "isAEADBadTagError", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class AEADBadTagSharedPreferencesWorkaround implements EncryptedSharedPreferenceWorkaround {
    private final Map statusMap;

    public AEADBadTagSharedPreferencesWorkaround() {
        this.statusMap = new LinkedHashMap();
    }

    @Override  // com.navercorp.nid.preference.EncryptedSharedPreferenceWorkaround
    public boolean apply(Context context0, String s, Throwable throwable0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "fileName");
        Intrinsics.checkNotNullParameter(throwable0, "throwable");
        if(Intrinsics.areEqual(this.statusMap.get(s), Boolean.TRUE)) {
            return false;
        }
        if(!this.isAEADBadTagError(throwable0)) {
            return false;
        }
        this.statusMap.put(s, Boolean.TRUE);
        if(Build.VERSION.SDK_INT >= 24) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0, s);
            return true;
        }
        context0.getSharedPreferences(s, 0).edit().clear().apply();
        return true;
    }

    private final boolean isAEADBadTagError(Throwable throwable0) {
        return StringsKt.contains$default(throwable0.toString(), "AEADBadTagException", false, 2, null);
    }
}

