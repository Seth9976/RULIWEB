package com.google.android.gms.measurement.internal;

import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class zzkb extends zzku {
    public final zzfe zza;
    public final zzfe zzb;
    public final zzfe zzc;
    public final zzfe zzd;
    public final zzfe zze;
    private final Map zzg;

    zzkb(zzlh zzlh0) {
        super(zzlh0);
        this.zzg = new HashMap();
        zzfi zzfi0 = this.zzt.zzm();
        zzfi0.getClass();
        this.zza = new zzfe(zzfi0, "last_delete_stale", 0L);
        zzfi zzfi1 = this.zzt.zzm();
        zzfi1.getClass();
        this.zzb = new zzfe(zzfi1, "backoff", 0L);
        zzfi zzfi2 = this.zzt.zzm();
        zzfi2.getClass();
        this.zzc = new zzfe(zzfi2, "last_upload", 0L);
        zzfi zzfi3 = this.zzt.zzm();
        zzfi3.getClass();
        this.zzd = new zzfe(zzfi3, "last_upload_attempt", 0L);
        zzfi zzfi4 = this.zzt.zzm();
        zzfi4.getClass();
        this.zze = new zzfe(zzfi4, "midnight_offset", 0L);
    }

    @Deprecated
    final Pair zza(String s) {
        zzka zzka1;
        Info advertisingIdClient$Info0;
        this.zzg();
        long v = this.zzt.zzax().elapsedRealtime();
        zzka zzka0 = (zzka)this.zzg.get(s);
        if(zzka0 != null && v < zzka0.zzc) {
            return new Pair(zzka0.zza, Boolean.valueOf(zzka0.zzb));
        }
        long v1 = this.zzt.zzf().zzi(s, zzeg.zza) + v;
        try {
            long v2 = this.zzt.zzf().zzi(s, zzeg.zzb);
            if(v2 <= 0L) {
                advertisingIdClient$Info0 = AdvertisingIdClient.getAdvertisingIdInfo(this.zzt.zzaw());
            }
            else {
                try {
                    advertisingIdClient$Info0 = AdvertisingIdClient.getAdvertisingIdInfo(this.zzt.zzaw());
                }
                catch(PackageManager.NameNotFoundException unused_ex) {
                    if(zzka0 != null && v < zzka0.zzc + v2) {
                        return new Pair(zzka0.zza, Boolean.valueOf(zzka0.zzb));
                    }
                    advertisingIdClient$Info0 = null;
                }
            }
            if(advertisingIdClient$Info0 == null) {
                return new Pair("00000000-0000-0000-0000-000000000000", Boolean.FALSE);
            }
            String s1 = advertisingIdClient$Info0.getId();
            zzka1 = s1 == null ? new zzka("", advertisingIdClient$Info0.isLimitAdTrackingEnabled(), v1) : new zzka(s1, advertisingIdClient$Info0.isLimitAdTrackingEnabled(), v1);
        }
        catch(Exception exception0) {
            this.zzt.zzaA().zzc().zzb("Unable to get advertising id", exception0);
            zzka1 = new zzka("", false, v1);
        }
        this.zzg.put(s, zzka1);
        return new Pair(zzka1.zza, Boolean.valueOf(zzka1.zzb));
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    // 去混淆评级： 低(20)
    final Pair zzd(String s, zzhb zzhb0) {
        return zzhb0.zzj(zzha.zza) ? this.zza(s) : new Pair("", Boolean.FALSE);
    }

    @Deprecated
    final String zzf(String s, boolean z) {
        this.zzg();
        String s1 = z ? ((String)this.zza(s).first) : "00000000-0000-0000-0000-000000000000";
        MessageDigest messageDigest0 = zzlp.zzF();
        return messageDigest0 == null ? null : String.format(Locale.US, "%032X", new BigInteger(1, messageDigest0.digest(s1.getBytes())));
    }
}

