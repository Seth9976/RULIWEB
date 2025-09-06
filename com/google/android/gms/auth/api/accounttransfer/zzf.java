package com.google.android.gms.auth.api.accounttransfer;

final class zzf extends zzj {
    final zzg zza;

    zzf(zzg zzg0, zzl zzl0) {
        this.zza = zzg0;
        super(zzl0);
    }

    @Override  // com.google.android.gms.internal.auth.zzan
    public final void zzc(DeviceMetaData deviceMetaData0) {
        this.zza.zzb.setResult(deviceMetaData0);
    }
}

