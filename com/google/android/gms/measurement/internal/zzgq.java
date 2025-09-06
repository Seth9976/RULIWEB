package com.google.android.gms.measurement.internal;

import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzga;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

final class zzgq implements Callable {
    final zzau zza;
    final String zzb;
    final zzgv zzc;

    zzgq(zzgv zzgv0, zzau zzau0, String s) {
        this.zzc = zzgv0;
        this.zza = zzau0;
        this.zzb = s;
        super();
    }

    @Override
    public final Object call() throws Exception {
        zzh zzh1;
        zzga zzga1;
        long v2;
        zzaq zzaq1;
        Bundle bundle2;
        zzgc zzgc1;
        List list1;
        this.zzc.zza.zzA();
        zzip zzip0 = this.zzc.zza.zzr();
        zzau zzau0 = this.zza;
        String s = this.zzb;
        zzip0.zzg();
        zzgd.zzO();
        Preconditions.checkNotNull(zzau0);
        Preconditions.checkNotEmpty(s);
        if(!zzip0.zzt.zzf().zzs(s, zzeg.zzU)) {
            zzip0.zzt.zzaA().zzc().zzb("Generating ScionPayload disabled. packageName", s);
            return new byte[0];
        }
        if(!"_iap".equals(zzau0.zza) && !"_iapx".equals(zzau0.zza)) {
            zzip0.zzt.zzaA().zzc().zzc("Generating a payload for this event is not available. package_name, event_name", s, zzau0.zza);
            return null;
        }
        zzga zzga0 = zzgb.zza();
        zzip0.zzf.zzh().zzw();
        try {
            zzh zzh0 = zzip0.zzf.zzh().zzj(s);
            if(zzh0 == null) {
                zzip0.zzt.zzaA().zzc().zzb("Log and bundle not available. package_name", s);
                return new byte[0];
            }
            if(!zzh0.zzan()) {
                zzip0.zzt.zzaA().zzc().zzb("Log and bundle disabled. package_name", s);
                return new byte[0];
            }
            zzgc zzgc0 = com.google.android.gms.internal.measurement.zzgd.zzu();
            zzgc0.zzad(1);
            zzgc0.zzZ("android");
            if(!TextUtils.isEmpty(zzh0.zzv())) {
                zzgc0.zzD(zzh0.zzv());
            }
            if(!TextUtils.isEmpty(zzh0.zzx())) {
                zzgc0.zzF(((String)Preconditions.checkNotNull(zzh0.zzx())));
            }
            if(!TextUtils.isEmpty(zzh0.zzy())) {
                zzgc0.zzG(((String)Preconditions.checkNotNull(zzh0.zzy())));
            }
            if(zzh0.zzb() != 0xFFFFFFFF80000000L) {
                zzgc0.zzH(((int)zzh0.zzb()));
            }
            zzgc0.zzV(zzh0.zzm());
            zzgc0.zzP(zzh0.zzk());
            String s1 = zzh0.zzA();
            String s2 = zzh0.zzt();
            if(!TextUtils.isEmpty(s1)) {
                zzgc0.zzU(s1);
            }
            else if(!TextUtils.isEmpty(s2)) {
                zzgc0.zzC(s2);
            }
            zzpz.zzc();
            if(zzip0.zzt.zzf().zzs(null, zzeg.zzaE)) {
                zzgc0.zzaj(zzh0.zzr());
            }
            zzhb zzhb0 = zzip0.zzf.zzq(s);
            zzgc0.zzM(zzh0.zzj());
            if(zzip0.zzt.zzJ() && (zzip0.zzt.zzf().zzt("") && zzhb0.zzj(zzha.zza) && !TextUtils.isEmpty(null))) {
                zzgc0.zzO(null);
            }
            byte[] arr_b = new byte[0];
            zzgc0.zzL(zzhb0.zzi());
            if(zzhb0.zzj(zzha.zza) && zzh0.zzam()) {
                String s3 = zzh0.zzv();
                Pair pair0 = zzip0.zzf.zzs().zzd(s3, zzhb0);
                if(zzh0.zzam() && !TextUtils.isEmpty(((CharSequence)pair0.first))) {
                    try {
                        zzgc0.zzae(zzip.zza(((String)pair0.first), Long.toString(zzau0.zzd)));
                    }
                    catch(SecurityException securityException0) {
                        zzip0.zzt.zzaA().zzc().zzb("Resettable device id encryption failed", securityException0.getMessage());
                        return arr_b;
                    }
                    if(pair0.second != null) {
                        zzgc0.zzX(((Boolean)pair0.second).booleanValue());
                    }
                }
            }
            zzip0.zzt.zzg().zzv();
            zzgc0.zzN(Build.MODEL);
            zzip0.zzt.zzg().zzv();
            zzgc0.zzY(Build.VERSION.RELEASE);
            zzgc0.zzak(((int)zzip0.zzt.zzg().zzb()));
            zzgc0.zzao(zzip0.zzt.zzg().zzc());
            try {
                if(zzhb0.zzj(zzha.zzb) && zzh0.zzw() != null) {
                    zzgc0.zzE(zzip.zza(((String)Preconditions.checkNotNull(zzh0.zzw())), Long.toString(zzau0.zzd)));
                }
            }
            catch(SecurityException securityException1) {
                zzip0.zzt.zzaA().zzc().zzb("app instance id encryption failed", securityException1.getMessage());
                return arr_b;
            }
            if(!TextUtils.isEmpty(zzh0.zzz())) {
                zzgc0.zzT(((String)Preconditions.checkNotNull(zzh0.zzz())));
            }
            zzlm zzlm0 = null;
            String s4 = zzh0.zzv();
            List list0 = zzip0.zzf.zzh().zzu(s4);
            for(Object object0: list0) {
                zzlm zzlm1 = (zzlm)object0;
                if("_lte".equals(zzlm1.zzc)) {
                    zzlm0 = zzlm1;
                    break;
                }
            }
            if(zzlm0 == null || zzlm0.zze == null) {
                list1 = list0;
                zzlm zzlm2 = new zzlm(s4, "auto", "_lte", zzip0.zzt.zzax().currentTimeMillis(), 0L);
                list1.add(zzlm2);
                zzip0.zzf.zzh().zzL(zzlm2);
            }
            else {
                list1 = list0;
            }
            zzlj zzlj0 = zzip0.zzf.zzu();
            zzlj0.zzt.zzaA().zzj().zza("Checking account type status for ad personalization signals");
            if(zzlj0.zzt.zzg().zze()) {
                String s5 = zzh0.zzv();
                Preconditions.checkNotNull(s5);
                if(zzh0.zzam() && zzlj0.zzf.zzm().zzn(s5)) {
                    zzlj0.zzt.zzaA().zzc().zza("Turning off ad personalization due to account type");
                    Iterator iterator1 = list1.iterator();
                    while(iterator1.hasNext()) {
                        Object object1 = iterator1.next();
                        if("_npa".equals(((zzlm)object1).zzc)) {
                            iterator1.remove();
                            break;
                        }
                        if(false) {
                            break;
                        }
                    }
                    list1.add(new zzlm(s5, "auto", "_npa", zzlj0.zzt.zzax().currentTimeMillis(), 1L));
                }
            }
            zzgm[] arr_zzgm = new zzgm[list1.size()];
            for(int v1 = 0; v1 < list1.size(); ++v1) {
                zzgl zzgl0 = zzgm.zzd();
                zzgl0.zzf(((zzlm)list1.get(v1)).zzc);
                zzgl0.zzg(((zzlm)list1.get(v1)).zzd);
                zzip0.zzf.zzu().zzv(zzgl0, ((zzlm)list1.get(v1)).zze);
                arr_zzgm[v1] = (zzgm)zzgl0.zzaD();
            }
            zzgc0.zzj(Arrays.asList(arr_zzgm));
            zzeu zzeu0 = zzeu.zzb(zzau0);
            zzlp zzlp0 = zzip0.zzt.zzv();
            Bundle bundle0 = zzip0.zzf.zzh().zzi(s);
            zzlp0.zzL(zzeu0.zzd, bundle0);
            zzip0.zzt.zzv().zzN(zzeu0, zzip0.zzt.zzf().zzd(s));
            Bundle bundle1 = zzeu0.zzd;
            bundle1.putLong("_c", 1L);
            zzip0.zzt.zzaA().zzc().zza("Marking in-app purchase as real-time");
            bundle1.putLong("_r", 1L);
            bundle1.putString("_o", zzau0.zzc);
            if(zzip0.zzt.zzv().zzaf("")) {
                zzip0.zzt.zzv().zzP(bundle1, "_dbg", 1L);
                zzip0.zzt.zzv().zzP(bundle1, "_r", 1L);
            }
            zzaq zzaq0 = zzip0.zzf.zzh().zzn(s, zzau0.zza);
            if(zzaq0 == null) {
                zzgc1 = zzgc0;
                bundle2 = bundle1;
                zzaq1 = new zzaq(s, zzau0.zza, 0L, 0L, 0L, zzau0.zzd, 0L, null, null, null, null);
                v2 = 0L;
                zzga1 = zzga0;
                zzh1 = zzh0;
            }
            else {
                zzga1 = zzga0;
                bundle2 = bundle1;
                zzh1 = zzh0;
                zzgc1 = zzgc0;
                zzaq1 = zzaq0.zzc(zzau0.zzd);
                v2 = zzaq0.zzf;
            }
            zzip0.zzf.zzh().zzE(zzaq1);
            zzap zzap0 = new zzap(zzip0.zzt, zzau0.zzc, s, zzau0.zza, zzau0.zzd, v2, bundle2);
            zzfs zzfs0 = zzft.zze();
            zzfs0.zzm(zzap0.zzd);
            zzfs0.zzi(zzap0.zzb);
            zzfs0.zzl(zzap0.zze);
            zzar zzar0 = new zzar(zzap0.zzf);
            while(zzar0.hasNext()) {
                String s6 = zzar0.zza();
                zzfw zzfw0 = zzfx.zze();
                zzfw0.zzj(s6);
                Object object2 = zzap0.zzf.zzf(s6);
                if(object2 != null) {
                    zzip0.zzf.zzu().zzu(zzfw0, object2);
                    zzfs0.zze(zzfw0);
                }
            }
            zzgc1.zzk(zzfs0);
            zzge zzge0 = zzgg.zza();
            zzfu zzfu0 = zzfv.zza();
            zzfu0.zza(zzaq1.zzc);
            zzfu0.zzb(zzau0.zza);
            zzge0.zza(zzfu0);
            zzgc1.zzaa(zzge0);
            zzgc1.zzf(zzip0.zzf.zzf().zza(zzh1.zzv(), Collections.EMPTY_LIST, zzgc1.zzau(), zzfs0.zzc(), zzfs0.zzc()));
            if(zzfs0.zzq()) {
                zzgc1.zzai(zzfs0.zzc());
                zzgc1.zzQ(zzfs0.zzc());
            }
            long v3 = zzh1.zzn();
            int v4 = Long.compare(v3, 0L);
            if(v4 != 0) {
                zzgc1.zzab(v3);
            }
            long v5 = zzh1.zzp();
            if(v5 != 0L) {
                zzgc1.zzac(v5);
            }
            else if(v4 != 0) {
                zzgc1.zzac(v3);
            }
            String s7 = zzh1.zzD();
            zzqu.zzc();
            if(zzip0.zzt.zzf().zzs(s, zzeg.zzao) && s7 != null) {
                zzgc1.zzah(s7);
            }
            zzh1.zzG();
            zzgc1.zzI(((int)zzh1.zzo()));
            zzip0.zzt.zzf().zzh();
            zzgc1.zzam(79000L);
            zzgc1.zzal(zzip0.zzt.zzax().currentTimeMillis());
            zzgc1.zzag(true);
            if(zzip0.zzt.zzf().zzs(null, zzeg.zzas)) {
                zzip0.zzf.zzC("", zzgc1);
            }
            zzga1.zza(zzgc1);
            zzh1.zzad(zzgc1.zzd());
            zzh1.zzab(zzgc1.zzc());
            zzip0.zzf.zzh().zzD(zzh1);
            zzip0.zzf.zzh().zzC();
        }
        finally {
            zzip0.zzf.zzh().zzx();
        }
        try {
            return zzip0.zzf.zzu().zzz(((zzgb)zzga1.zzaD()).zzbx());
        }
        catch(IOException iOException0) {
            zzip0.zzt.zzaA().zzd().zzc("Data loss. Failed to bundle and serialize. appId", zzet.zzn(s), iOException0);
            return null;
        }
    }
}

