package com.google.android.gms.internal.measurement;

public final class zzol implements zzok {
    public static final zzib zzA;
    public static final zzib zzB;
    public static final zzib zzC;
    public static final zzib zzD;
    public static final zzib zzE;
    public static final zzib zzF;
    public static final zzib zzG;
    public static final zzib zzH;
    public static final zzib zzI;
    public static final zzib zzJ;
    public static final zzib zzK;
    public static final zzib zzL;
    public static final zzib zzM;
    public static final zzib zzN;
    public static final zzib zzO;
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;
    public static final zzib zze;
    public static final zzib zzf;
    public static final zzib zzg;
    public static final zzib zzh;
    public static final zzib zzi;
    public static final zzib zzj;
    public static final zzib zzk;
    public static final zzib zzl;
    public static final zzib zzm;
    public static final zzib zzn;
    public static final zzib zzo;
    public static final zzib zzp;
    public static final zzib zzq;
    public static final zzib zzr;
    public static final zzib zzs;
    public static final zzib zzt;
    public static final zzib zzu;
    public static final zzib zzv;
    public static final zzib zzw;
    public static final zzib zzx;
    public static final zzib zzy;
    public static final zzib zzz;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzol.zza = zzhy0.zzd("measurement.ad_id_cache_time", 10000L);
        zzol.zzb = zzhy0.zzd("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000L);
        zzol.zzc = zzhy0.zzd("measurement.max_bundles_per_iteration", 100L);
        zzol.zzd = zzhy0.zzd("measurement.config.cache_time", 86400000L);
        zzol.zze = zzhy0.zze("measurement.log_tag", "FA");
        zzol.zzf = zzhy0.zze("measurement.config.url_authority", "app-measurement.com");
        zzol.zzg = zzhy0.zze("measurement.config.url_scheme", "https");
        zzol.zzh = zzhy0.zzd("measurement.upload.debug_upload_interval", 1000L);
        zzol.zzi = zzhy0.zzd("measurement.lifetimevalue.max_currency_tracked", 4L);
        zzol.zzj = zzhy0.zzd("measurement.store.max_stored_events_per_app", 100000L);
        zzol.zzk = zzhy0.zzd("measurement.experiment.max_ids", 50L);
        zzol.zzl = zzhy0.zzd("measurement.audience.filter_result_max_count", 200L);
        zzol.zzm = zzhy0.zzd("measurement.upload.max_item_scoped_custom_parameters", 27L);
        zzol.zzn = zzhy0.zzd("measurement.alarm_manager.minimum_interval", 60000L);
        zzol.zzo = zzhy0.zzd("measurement.upload.minimum_delay", 500L);
        zzol.zzp = zzhy0.zzd("measurement.monitoring.sample_period_millis", 86400000L);
        zzol.zzq = zzhy0.zzd("measurement.upload.realtime_upload_interval", 10000L);
        zzol.zzr = zzhy0.zzd("measurement.upload.refresh_blacklisted_config_interval", 604800000L);
        zzol.zzs = zzhy0.zzd("measurement.config.cache_time.service", 3600000L);
        zzol.zzt = zzhy0.zzd("measurement.service_client.idle_disconnect_millis", 5000L);
        zzol.zzu = zzhy0.zze("measurement.log_tag.service", "FA-SVC");
        zzol.zzv = zzhy0.zzd("measurement.upload.stale_data_deletion_interval", 86400000L);
        zzol.zzw = zzhy0.zzd("measurement.sdk.attribution.cache.ttl", 604800000L);
        zzol.zzx = zzhy0.zzd("measurement.redaction.app_instance_id.ttl", 7200000L);
        zzol.zzy = zzhy0.zzd("measurement.upload.backoff_period", 43200000L);
        zzol.zzz = zzhy0.zzd("measurement.upload.initial_upload_delay_time", 15000L);
        zzol.zzA = zzhy0.zzd("measurement.upload.interval", 3600000L);
        zzol.zzB = zzhy0.zzd("measurement.upload.max_bundle_size", 0x10000L);
        zzol.zzC = zzhy0.zzd("measurement.upload.max_bundles", 100L);
        zzol.zzD = zzhy0.zzd("measurement.upload.max_conversions_per_day", 500L);
        zzol.zzE = zzhy0.zzd("measurement.upload.max_error_events_per_day", 1000L);
        zzol.zzF = zzhy0.zzd("measurement.upload.max_events_per_bundle", 1000L);
        zzol.zzG = zzhy0.zzd("measurement.upload.max_events_per_day", 100000L);
        zzol.zzH = zzhy0.zzd("measurement.upload.max_public_events_per_day", 50000L);
        zzol.zzI = zzhy0.zzd("measurement.upload.max_queue_time", 2419200000L);
        zzol.zzJ = zzhy0.zzd("measurement.upload.max_realtime_events_per_day", 10L);
        zzol.zzK = zzhy0.zzd("measurement.upload.max_batch_size", 0x10000L);
        zzol.zzL = zzhy0.zzd("measurement.upload.retry_count", 6L);
        zzol.zzM = zzhy0.zzd("measurement.upload.retry_time", 1800000L);
        zzol.zzN = zzhy0.zze("measurement.upload.url", "https://app-measurement.com/a");
        zzol.zzO = zzhy0.zzd("measurement.upload.window_interval", 3600000L);
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzA() {
        return (long)(((Long)zzol.zzF.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzB() {
        return (long)(((Long)zzol.zzG.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzC() {
        return (long)(((Long)zzol.zzH.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzD() {
        return (long)(((Long)zzol.zzI.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzE() {
        return (long)(((Long)zzol.zzJ.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzF() {
        return (long)(((Long)zzol.zzK.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzG() {
        return (long)(((Long)zzol.zzL.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzH() {
        return (long)(((Long)zzol.zzM.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzI() {
        return (long)(((Long)zzol.zzO.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final String zzJ() {
        return (String)zzol.zzf.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final String zzK() {
        return (String)zzol.zzg.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final String zzL() {
        return (String)zzol.zzN.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zza() {
        return (long)(((Long)zzol.zza.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzb() {
        return (long)(((Long)zzol.zzb.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzc() {
        return (long)(((Long)zzol.zzc.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzd() {
        return (long)(((Long)zzol.zzd.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zze() {
        return (long)(((Long)zzol.zzh.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzf() {
        return (long)(((Long)zzol.zzi.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzg() {
        return (long)(((Long)zzol.zzj.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzh() {
        return (long)(((Long)zzol.zzk.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzi() {
        return (long)(((Long)zzol.zzl.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzj() {
        return (long)(((Long)zzol.zzm.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzk() {
        return (long)(((Long)zzol.zzn.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzl() {
        return (long)(((Long)zzol.zzo.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzm() {
        return (long)(((Long)zzol.zzp.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzn() {
        return (long)(((Long)zzol.zzq.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzo() {
        return (long)(((Long)zzol.zzr.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzp() {
        return (long)(((Long)zzol.zzt.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzq() {
        return (long)(((Long)zzol.zzv.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzr() {
        return (long)(((Long)zzol.zzw.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzs() {
        return (long)(((Long)zzol.zzx.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzt() {
        return (long)(((Long)zzol.zzy.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzu() {
        return (long)(((Long)zzol.zzz.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzv() {
        return (long)(((Long)zzol.zzA.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzw() {
        return (long)(((Long)zzol.zzB.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzx() {
        return (long)(((Long)zzol.zzC.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzy() {
        return (long)(((Long)zzol.zzD.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzok
    public final long zzz() {
        return (long)(((Long)zzol.zzE.zzb()));
    }
}

