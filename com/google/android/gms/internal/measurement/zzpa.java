package com.google.android.gms.internal.measurement;

public final class zzpa implements zzoz {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzpa.zza = zzhy0.zzf("measurement.service.audience.fix_skip_audience_with_failed_filters", true);
        zzpa.zzb = zzhy0.zzf("measurement.audience.refresh_event_count_filters_timestamp", false);
        zzpa.zzc = zzhy0.zzf("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false);
        zzpa.zzd = zzhy0.zzf("measurement.audience.use_bundle_timestamp_for_event_count_filters", false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoz
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzoz
    public final boolean zzb() {
        return ((Boolean)zzpa.zzb.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzoz
    public final boolean zzc() {
        return ((Boolean)zzpa.zzc.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzoz
    public final boolean zzd() {
        return ((Boolean)zzpa.zzd.zzb()).booleanValue();
    }
}

