package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zzmv {
    public static final int zza;
    private static final Class zzb;
    private static final zznk zzc;
    private static final zznk zzd;

    static {
        Class class1;
        Class class0;
        zznk zznk0 = null;
        try {
            class0 = null;
            class0 = Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable unused_ex) {
        }
        try {
            zzmv.zzb = class0;
            class1 = null;
            class1 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable unused_ex) {
        }
        if(class1 != null) {
            try {
                zznk0 = (zznk)class1.getConstructor(null).newInstance(null);
            }
            catch(Throwable unused_ex) {
            }
        }
        zzmv.zzc = zznk0;
        zzmv.zzd = new zznm();
    }

    static Object zzA(Object object0, int v, int v1, Object object1, zznk zznk0) {
        if(object1 == null) {
            object1 = zznk0.zzc(object0);
        }
        zznk0.zzf(object1, v, ((long)v1));
        return object1;
    }

    static void zzB(zznk zznk0, Object object0, Object object1) {
        zznk0.zzh(object0, zznk0.zze(zznk0.zzd(object0), zznk0.zzd(object1)));
    }

    public static void zzC(Class class0) {
        if(!zzlb.class.isAssignableFrom(class0) && (zzmv.zzb != null && !zzmv.zzb.isAssignableFrom(class0))) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzD(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzc(v, list0, z);
        }
    }

    public static void zzE(int v, List list0, zzoc zzoc0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zze(v, list0);
        }
    }

    public static void zzF(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzg(v, list0, z);
        }
    }

    public static void zzG(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzj(v, list0, z);
        }
    }

    public static void zzH(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzl(v, list0, z);
        }
    }

    public static void zzI(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzn(v, list0, z);
        }
    }

    public static void zzJ(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzp(v, list0, z);
        }
    }

    public static void zzK(int v, List list0, zzoc zzoc0, zzmt zzmt0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                ((zzkj)zzoc0).zzq(v, list0.get(v1), zzmt0);
            }
        }
    }

    public static void zzL(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzs(v, list0, z);
        }
    }

    public static void zzM(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzu(v, list0, z);
        }
    }

    public static void zzN(int v, List list0, zzoc zzoc0, zzmt zzmt0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                ((zzkj)zzoc0).zzv(v, list0.get(v1), zzmt0);
            }
        }
    }

    public static void zzO(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzx(v, list0, z);
        }
    }

    public static void zzP(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzz(v, list0, z);
        }
    }

    public static void zzQ(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzB(v, list0, z);
        }
    }

    public static void zzR(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzD(v, list0, z);
        }
    }

    public static void zzS(int v, List list0, zzoc zzoc0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzG(v, list0);
        }
    }

    public static void zzT(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzI(v, list0, z);
        }
    }

    public static void zzU(int v, List list0, zzoc zzoc0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzoc0.zzK(v, list0, z);
        }
    }

    // 去混淆评级： 低(20)
    static boolean zzV(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    static int zza(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * (zzki.zzx(v << 3) + 1);
    }

    static int zzb(int v, List list0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = v1 * zzki.zzx(v << 3);
        for(int v2 = 0; v2 < list0.size(); ++v2) {
            int v4 = ((zzka)list0.get(v2)).zzd();
            v3 += zzki.zzx(v4) + v4;
        }
        return v3;
    }

    static int zzc(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzd(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzd(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlc) {
            int v2 = 0;
            while(v1 < v) {
                v2 += zzki.zzu(((zzlc)list0).zze(v1));
                ++v1;
            }
            return v2;
        }
        int v3 = 0;
        while(v1 < v) {
            v3 += zzki.zzu(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v3;
    }

    static int zze(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * (zzki.zzx(v << 3) + 4);
    }

    static int zzf(List list0) {
        return list0.size() * 4;
    }

    static int zzg(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * (zzki.zzx(v << 3) + 8);
    }

    static int zzh(List list0) {
        return list0.size() * 8;
    }

    static int zzi(int v, List list0, zzmt zzmt0) {
        int v1 = list0.size();
        if(v1 != 0) {
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                v3 += zzki.zzt(v, ((zzmi)list0.get(v2)), zzmt0);
            }
            return v3;
        }
        return 0;
    }

    static int zzj(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzk(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzk(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlc) {
            int v2 = 0;
            while(v1 < v) {
                v2 += zzki.zzu(((zzlc)list0).zze(v1));
                ++v1;
            }
            return v2;
        }
        int v3 = 0;
        while(v1 < v) {
            v3 += zzki.zzu(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v3;
    }

    static int zzl(int v, List list0, boolean z) {
        return list0.size() == 0 ? 0 : zzmv.zzm(list0) + list0.size() * zzki.zzx(v << 3);
    }

    static int zzm(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlx) {
            int v2 = 0;
            while(v1 < v) {
                v2 += zzki.zzy(((zzlx)list0).zza(v1));
                ++v1;
            }
            return v2;
        }
        int v3 = 0;
        while(v1 < v) {
            v3 += zzki.zzy(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v3;
    }

    static int zzn(int v, Object object0, zzmt zzmt0) {
        if(object0 instanceof zzlo) {
            int v1 = ((zzlo)object0).zza();
            return zzki.zzx(v << 3) + (zzki.zzx(v1) + v1);
        }
        return zzki.zzx(v << 3) + zzki.zzv(((zzmi)object0), zzmt0);
    }

    static int zzo(int v, List list0, zzmt zzmt0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzki.zzx(v << 3) * v1;
        for(int v2 = 0; v2 < v1; ++v2) {
            Object object0 = list0.get(v2);
            if(object0 instanceof zzlo) {
                int v4 = ((zzlo)object0).zza();
                v3 += zzki.zzx(v4) + v4;
            }
            else {
                v3 += zzki.zzv(((zzmi)object0), zzmt0);
            }
        }
        return v3;
    }

    static int zzp(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzq(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzq(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlc) {
            int v2 = 0;
            while(v1 < v) {
                int v3 = ((zzlc)list0).zze(v1);
                v2 += zzki.zzx(v3 >> 0x1F ^ v3 + v3);
                ++v1;
            }
            return v2;
        }
        int v4 = 0;
        while(v1 < v) {
            int v5 = (int)(((Integer)list0.get(v1)));
            v4 += zzki.zzx(v5 >> 0x1F ^ v5 + v5);
            ++v1;
        }
        return v4;
    }

    static int zzr(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzs(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzs(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlx) {
            int v2 = 0;
            while(v1 < v) {
                long v3 = ((zzlx)list0).zza(v1);
                v2 += zzki.zzy(v3 >> 0x3F ^ v3 + v3);
                ++v1;
            }
            return v2;
        }
        int v4 = 0;
        while(v1 < v) {
            long v5 = (long)(((Long)list0.get(v1)));
            v4 += zzki.zzy(v5 >> 0x3F ^ v5 + v5);
            ++v1;
        }
        return v4;
    }

    static int zzt(int v, List list0) {
        int v1 = list0.size();
        int v2 = 0;
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzki.zzx(v << 3) * v1;
        if(list0 instanceof zzlq) {
            while(v2 < v1) {
                Object object0 = ((zzlq)list0).zzf(v2);
                if(object0 instanceof zzka) {
                    int v4 = ((zzka)object0).zzd();
                    v3 += zzki.zzx(v4) + v4;
                }
                else {
                    v3 += zzki.zzw(((String)object0));
                }
                ++v2;
            }
            return v3;
        }
        while(v2 < v1) {
            Object object1 = list0.get(v2);
            if(object1 instanceof zzka) {
                int v5 = ((zzka)object1).zzd();
                v3 += zzki.zzx(v5) + v5;
            }
            else {
                v3 += zzki.zzw(((String)object1));
            }
            ++v2;
        }
        return v3;
    }

    static int zzu(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzv(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzv(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlc) {
            int v2 = 0;
            while(v1 < v) {
                v2 += zzki.zzx(((zzlc)list0).zze(v1));
                ++v1;
            }
            return v2;
        }
        int v3 = 0;
        while(v1 < v) {
            v3 += zzki.zzx(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v3;
    }

    static int zzw(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzmv.zzx(list0) + v1 * zzki.zzx(v << 3);
    }

    static int zzx(List list0) {
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzlx) {
            int v2 = 0;
            while(v1 < v) {
                v2 += zzki.zzy(((zzlx)list0).zza(v1));
                ++v1;
            }
            return v2;
        }
        int v3 = 0;
        while(v1 < v) {
            v3 += zzki.zzy(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v3;
    }

    public static zznk zzy() {
        return zzmv.zzc;
    }

    public static zznk zzz() {
        return zzmv.zzd;
    }
}

