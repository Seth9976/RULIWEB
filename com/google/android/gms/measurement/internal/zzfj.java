package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.android.gms.internal.measurement.zzra;

final class zzfj implements Runnable {
    final zzbr zza;
    final ServiceConnection zzb;
    final zzfk zzc;

    zzfj(zzfk zzfk0, zzbr zzbr0, ServiceConnection serviceConnection0) {
        this.zzc = zzfk0;
        this.zza = zzbr0;
        this.zzb = serviceConnection0;
        super();
    }

    @Override
    public final void run() {
        Bundle bundle1;
        zzfl zzfl0 = this.zzc.zza;
        String s = this.zzc.zzb;
        ServiceConnection serviceConnection0 = this.zzb;
        zzfl0.zza.zzaB().zzg();
        Bundle bundle0 = new Bundle();
        bundle0.putString("package_name", s);
        try {
            bundle1 = this.zza.zzd(bundle0);
            if(bundle1 == null) {
                zzfl0.zza.zzaA().zzd().zza("Install Referrer Service returned a null response");
                goto label_12;
            }
            goto label_13;
        }
        catch(Exception exception0) {
            zzfl0.zza.zzaA().zzd().zzb("Exception occurred while retrieving the Install Referrer", exception0.getMessage());
        }
    label_12:
        bundle1 = null;
    label_13:
        zzfl0.zza.zzaB().zzg();
        zzgd.zzO();
        if(bundle1 != null) {
            long v = bundle1.getLong("install_begin_timestamp_seconds", 0L);
            if(v * 1000L == 0L) {
                zzfl0.zza.zzaA().zzk().zza("Service response is missing Install Referrer install timestamp");
            }
            else {
                String s1 = bundle1.getString("install_referrer");
                if(s1 == null || s1.isEmpty()) {
                    zzfl0.zza.zzaA().zzd().zza("No referrer defined in Install Referrer response");
                }
                else {
                    zzfl0.zza.zzaA().zzj().zzb("InstallReferrer API result", s1);
                    zzlp zzlp0 = zzfl0.zza.zzv();
                    Uri uri0 = Uri.parse(("?" + s1));
                    zzra.zzc();
                    Bundle bundle2 = zzlp0.zzs(uri0, zzfl0.zza.zzf().zzs(null, zzeg.zzaw));
                    if(bundle2 == null) {
                        zzfl0.zza.zzaA().zzd().zza("No campaign params defined in Install Referrer result");
                    }
                    else {
                        String s2 = bundle2.getString("medium");
                        if(s2 == null || "(not set)".equalsIgnoreCase(s2) || "organic".equalsIgnoreCase(s2)) {
                        label_37:
                            if(v * 1000L == zzfl0.zza.zzm().zzd.zza()) {
                                zzfl0.zza.zzaA().zzj().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                            }
                            if(zzfl0.zza.zzJ()) {
                                zzfl0.zza.zzm().zzd.zzb(v * 1000L);
                                zzfl0.zza.zzaA().zzj().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                                bundle2.putString("_cis", "referrer API v2");
                                zzfl0.zza.zzq().zzF("auto", "_cmp", bundle2, s);
                            }
                        }
                        else {
                            long v1 = bundle1.getLong("referrer_click_timestamp_seconds", 0L);
                            if(v1 * 1000L == 0L) {
                                zzfl0.zza.zzaA().zzd().zza("Install Referrer is missing click timestamp for ad campaign");
                            }
                            else {
                                bundle2.putLong("click_timestamp", v1 * 1000L);
                                goto label_37;
                            }
                        }
                    }
                }
            }
        }
        ConnectionTracker.getInstance().unbindService(zzfl0.zza.zzaw(), serviceConnection0);
    }
}

