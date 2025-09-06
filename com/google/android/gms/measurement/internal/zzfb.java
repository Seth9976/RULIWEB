package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;

final class zzfb extends BroadcastReceiver {
    static final String zza = "com.google.android.gms.measurement.internal.zzfb";
    private final zzlh zzb;
    private boolean zzc;
    private boolean zzd;

    static {
    }

    zzfb(zzlh zzlh0) {
        Preconditions.checkNotNull(zzlh0);
        this.zzb = zzlh0;
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        this.zzb.zzB();
        String s = intent0.getAction();
        this.zzb.zzaA().zzj().zzb("NetworkBroadcastReceiver received action", s);
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(s)) {
            boolean z = this.zzb.zzj().zza();
            if(this.zzd != z) {
                this.zzd = z;
                this.zzb.zzaB().zzp(new zzfa(this, z));
            }
            return;
        }
        this.zzb.zzaA().zzk().zzb("NetworkBroadcastReceiver received unknown action", s);
    }

    static zzlh zza(zzfb zzfb0) {
        return zzfb0.zzb;
    }

    public final void zzb() {
        this.zzb.zzB();
        this.zzb.zzaB().zzg();
        if(this.zzc) {
            return;
        }
        this.zzb.zzaw().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzd = this.zzb.zzj().zza();
        this.zzb.zzaA().zzj().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzd));
        this.zzc = true;
    }

    public final void zzc() {
        this.zzb.zzB();
        this.zzb.zzaB().zzg();
        this.zzb.zzaB().zzg();
        if(this.zzc) {
            this.zzb.zzaA().zzj().zza("Unregistering connectivity change receiver");
            this.zzc = false;
            this.zzd = false;
            Context context0 = this.zzb.zzaw();
            try {
                context0.unregisterReceiver(this);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                this.zzb.zzaA().zzd().zzb("Failed to unregister the network broadcast receiver", illegalArgumentException0);
            }
        }
    }
}

