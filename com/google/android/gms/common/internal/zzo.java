package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public final class zzo {
    private static final Uri zza;
    private final String zzb;
    private final String zzc;
    private final ComponentName zzd;
    private final int zze;
    private final boolean zzf;

    static {
        zzo.zza = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    }

    public zzo(ComponentName componentName0, int v) {
        this.zzb = null;
        this.zzc = null;
        Preconditions.checkNotNull(componentName0);
        this.zzd = componentName0;
        this.zze = 0x1081;
        this.zzf = false;
    }

    public zzo(String s, int v, boolean z) {
        this(s, "com.google.android.gms", 0x1081, false);
    }

    public zzo(String s, String s1, int v, boolean z) {
        Preconditions.checkNotEmpty(s);
        this.zzb = s;
        Preconditions.checkNotEmpty(s1);
        this.zzc = s1;
        this.zzd = null;
        this.zze = 0x1081;
        this.zzf = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzo ? Objects.equal(this.zzb, ((zzo)object0).zzb) && Objects.equal(this.zzc, ((zzo)object0).zzc) && Objects.equal(this.zzd, ((zzo)object0).zzd) && this.zzf == ((zzo)object0).zzf : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, this.zzc, this.zzd, 0x1081, Boolean.valueOf(this.zzf)});
    }

    @Override
    public final String toString() {
        String s = this.zzb;
        if(s == null) {
            Preconditions.checkNotNull(this.zzd);
            return this.zzd.flattenToString();
        }
        return s;
    }

    public final ComponentName zza() {
        return this.zzd;
    }

    public final Intent zzb(Context context0) {
        Bundle bundle1;
        Intent intent0 = null;
        if(this.zzb != null) {
            if(this.zzf) {
                Bundle bundle0 = new Bundle();
                bundle0.putString("serviceActionBundleKey", this.zzb);
                try {
                    bundle1 = context0.getContentResolver().call(zzo.zza, "serviceIntentCall", null, bundle0);
                }
                catch(IllegalArgumentException illegalArgumentException0) {
                    Log.w("ConnectionStatusConfig", "Dynamic intent resolution failed: " + illegalArgumentException0.toString());
                    bundle1 = null;
                }
                if(bundle1 != null) {
                    intent0 = (Intent)bundle1.getParcelable("serviceResponseIntentKey");
                }
                if(intent0 == null) {
                    Log.w("ConnectionStatusConfig", "Dynamic lookup for intent failed for action: " + this.zzb);
                }
            }
            return intent0 == null ? new Intent(this.zzb).setPackage(this.zzc) : intent0;
        }
        return new Intent().setComponent(this.zzd);
    }

    public final String zzc() {
        return this.zzc;
    }
}

