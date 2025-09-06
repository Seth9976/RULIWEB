package com.google.android.gms.tasks;

final class zza implements OnSuccessListener {
    final OnTokenCanceledListener zza;

    zza(zzb zzb0, OnTokenCanceledListener onTokenCanceledListener0) {
        this.zza = onTokenCanceledListener0;
        super();
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        Void void0 = (Void)object0;
        this.zza.onCanceled();
    }
}

