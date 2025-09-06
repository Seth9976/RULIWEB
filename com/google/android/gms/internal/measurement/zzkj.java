package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zzkj implements zzoc {
    private final zzki zza;

    private zzkj(zzki zzki0) {
        this.zza = zzki0;
        zzki0.zza = this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzA(int v, int v1) throws IOException {
        this.zza.zzp(v, v1 >> 0x1F ^ v1 + v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzB(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                int v4 = (int)(((Integer)list0.get(v2)));
                v3 += zzki.zzx(v4 >> 0x1F ^ v4 + v4);
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v5 = (int)(((Integer)list0.get(v1)));
                this.zza.zzq(v5 >> 0x1F ^ v5 + v5);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v6 = (int)(((Integer)list0.get(v1)));
            this.zza.zzp(v, v6 >> 0x1F ^ v6 + v6);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzC(int v, long v1) throws IOException {
        this.zza.zzr(v, v1 >> 0x3F ^ v1 + v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzD(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                long v4 = (long)(((Long)list0.get(v2)));
                v3 += zzki.zzy(v4 >> 0x3F ^ v4 + v4);
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v5 = (long)(((Long)list0.get(v1)));
                this.zza.zzs(v5 >> 0x3F ^ v5 + v5);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v6 = (long)(((Long)list0.get(v1)));
            this.zza.zzr(v, v6 >> 0x3F ^ v6 + v6);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    @Deprecated
    public final void zzE(int v) throws IOException {
        this.zza.zzo(v, 3);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzF(int v, String s) throws IOException {
        this.zza.zzm(v, s);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzG(int v, List list0) throws IOException {
        int v1 = 0;
        if(list0 instanceof zzlq) {
            while(v1 < list0.size()) {
                Object object0 = ((zzlq)list0).zzf(v1);
                if(object0 instanceof String) {
                    this.zza.zzm(v, ((String)object0));
                }
                else {
                    this.zza.zze(v, ((zzka)object0));
                }
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            String s = (String)list0.get(v1);
            this.zza.zzm(v, s);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzH(int v, int v1) throws IOException {
        this.zza.zzp(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzI(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzki.zzx(((int)(((Integer)list0.get(v2)))));
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zza.zzq(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zza.zzp(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzJ(int v, long v1) throws IOException {
        this.zza.zzr(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzK(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzki.zzy(((long)(((Long)list0.get(v2)))));
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zza.zzs(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zza.zzr(v, v5);
            ++v1;
        }
    }

    public static zzkj zza(zzki zzki0) {
        zzkj zzkj0 = zzki0.zza;
        return zzkj0 == null ? new zzkj(zzki0) : zzkj0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzb(int v, boolean z) throws IOException {
        this.zza.zzd(v, z);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzc(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Boolean)list0.get(v2)).booleanValue();
                ++v3;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                boolean z1 = ((Boolean)list0.get(v1)).booleanValue();
                this.zza.zzb(((byte)z1));
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            boolean z2 = ((Boolean)list0.get(v1)).booleanValue();
            this.zza.zzd(v, z2);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzd(int v, zzka zzka0) throws IOException {
        this.zza.zze(v, zzka0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zze(int v, List list0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            zzka zzka0 = (zzka)list0.get(v1);
            this.zza.zze(v, zzka0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzf(int v, double f) throws IOException {
        this.zza.zzh(v, Double.doubleToRawLongBits(f));
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzg(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Double)list0.get(v2)).doubleValue();
                v3 += 8;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v4 = Double.doubleToRawLongBits(((double)(((Double)list0.get(v1)))));
                this.zza.zzi(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = Double.doubleToRawLongBits(((double)(((Double)list0.get(v1)))));
            this.zza.zzh(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    @Deprecated
    public final void zzh(int v) throws IOException {
        this.zza.zzo(v, 4);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzi(int v, int v1) throws IOException {
        this.zza.zzj(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzj(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzki.zzu(((int)(((Integer)list0.get(v2)))));
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zza.zzk(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zza.zzj(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzk(int v, int v1) throws IOException {
        this.zza.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzl(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Integer)list0.get(v2)).intValue();
                v3 += 4;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zza.zzg(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zza.zzf(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzm(int v, long v1) throws IOException {
        this.zza.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzn(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Long)list0.get(v2)).longValue();
                v3 += 8;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zza.zzi(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zza.zzh(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzo(int v, float f) throws IOException {
        this.zza.zzf(v, Float.floatToRawIntBits(f));
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzp(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Float)list0.get(v2)).floatValue();
                v3 += 4;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = Float.floatToRawIntBits(((float)(((Float)list0.get(v1)))));
                this.zza.zzg(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = Float.floatToRawIntBits(((float)(((Float)list0.get(v1)))));
            this.zza.zzf(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzq(int v, Object object0, zzmt zzmt0) throws IOException {
        this.zza.zzo(v, 3);
        zzmt0.zzi(((zzmi)object0), this.zza.zza);
        this.zza.zzo(v, 4);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzr(int v, int v1) throws IOException {
        this.zza.zzj(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzs(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzki.zzu(((int)(((Integer)list0.get(v2)))));
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zza.zzk(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zza.zzj(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzt(int v, long v1) throws IOException {
        this.zza.zzr(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzu(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzki.zzy(((long)(((Long)list0.get(v2)))));
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zza.zzs(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zza.zzr(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzv(int v, Object object0, zzmt zzmt0) throws IOException {
        ((zzkf)this.zza).zzq(v << 3 | 2);
        int v1 = ((zzjk)(((zzmi)object0))).zzbu(zzmt0);
        ((zzkf)this.zza).zzq(v1);
        zzmt0.zzi(((zzmi)object0), ((zzkf)this.zza).zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzw(int v, int v1) throws IOException {
        this.zza.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzx(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Integer)list0.get(v2)).intValue();
                v3 += 4;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zza.zzg(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zza.zzf(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzy(int v, long v1) throws IOException {
        this.zza.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoc
    public final void zzz(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zza.zzo(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                ((Long)list0.get(v2)).longValue();
                v3 += 8;
            }
            this.zza.zzq(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zza.zzi(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zza.zzh(v, v5);
            ++v1;
        }
    }
}

