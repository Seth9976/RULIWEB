package com.google.android.gms.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzfm.zza;
import com.google.android.gms.measurement.internal.zzfm;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzfm.zza {
    private zzfm zza;

    public BroadcastReceiver.PendingResult doGoAsync() {
        return this.goAsync();
    }

    @Override  // com.google.android.gms.measurement.internal.zzfm$zza
    public void doStartService(Context context0, Intent intent0) {
        AppMeasurementReceiver.startWakefulService(context0, intent0);
    }

    @Override  // android.content.BroadcastReceiver
    public void onReceive(Context context0, Intent intent0) {
        if(this.zza == null) {
            this.zza = new zzfm(this);
        }
        this.zza.zza(context0, intent0);
    }
}

