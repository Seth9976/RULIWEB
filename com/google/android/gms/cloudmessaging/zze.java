package com.google.android.gms.cloudmessaging;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;

public final class zze implements Runnable {
    public final CloudMessagingReceiver zza;
    public final Intent zzb;
    public final Context zzc;
    public final boolean zzd;
    public final BroadcastReceiver.PendingResult zze;

    public zze(CloudMessagingReceiver cloudMessagingReceiver0, Intent intent0, Context context0, boolean z, BroadcastReceiver.PendingResult broadcastReceiver$PendingResult0) {
        this.zza = cloudMessagingReceiver0;
        this.zzb = intent0;
        this.zzc = context0;
        this.zzd = z;
        this.zze = broadcastReceiver$PendingResult0;
    }

    @Override
    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}

