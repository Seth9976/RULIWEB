package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzfl {
    final zzgd zza;

    zzfl(zzlh zzlh0) {
        this.zza = zzlh0.zzp();
    }

    final boolean zza() {
        try {
            PackageManagerWrapper packageManagerWrapper0 = Wrappers.packageManager(this.zza.zzaw());
            if(packageManagerWrapper0 == null) {
                this.zza.zzaA().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
                return false;
            }
            if(packageManagerWrapper0.getPackageInfo("com.android.vending", 0x80).versionCode >= 80837300) {
                return true;
            }
        }
        catch(Exception exception0) {
            this.zza.zzaA().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", exception0);
        }
        return false;
    }
}

