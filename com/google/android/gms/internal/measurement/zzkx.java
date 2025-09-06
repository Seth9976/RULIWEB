package com.google.android.gms.internal.measurement;

import java.io.IOException;

public class zzkx extends zzjj {
    protected zzlb zza;
    private final zzlb zzb;

    protected zzkx(zzlb zzlb0) {
        this.zzb = zzlb0;
        if(zzlb0.zzbR()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = zzlb0.zzbD();
    }

    @Override  // com.google.android.gms.internal.measurement.zzjj
    public final Object clone() throws CloneNotSupportedException {
        return this.zzaA();
    }

    private static void zza(Object object0, Object object1) {
        zzmq.zza().zzb(object0.getClass()).zzg(object0, object1);
    }

    public final zzkx zzaA() {
        zzkx zzkx0 = (zzkx)this.zzb.zzl(5, null, null);
        zzkx0.zza = this.zzaE();
        return zzkx0;
    }

    public final zzkx zzaB(zzlb zzlb0) {
        if(!this.zzb.equals(zzlb0)) {
            if(!this.zza.zzbR()) {
                this.zzaI();
            }
            zzkx.zza(this.zza, zzlb0);
        }
        return this;
    }

    public final zzkx zzaC(byte[] arr_b, int v, int v1, zzkn zzkn0) throws zzll {
        if(!this.zza.zzbR()) {
            this.zzaI();
        }
        try {
            zzmq.zza().zzb(this.zza.getClass()).zzh(this.zza, arr_b, 0, v1, new zzjn(zzkn0));
            return this;
        }
        catch(zzll zzll0) {
            throw zzll0;
        }
        catch(IndexOutOfBoundsException unused_ex) {
            throw zzll.zzf();
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", iOException0);
        }
    }

    public final zzlb zzaD() {
        zzlb zzlb0 = this.zzaE();
        switch(((byte)(((Byte)zzlb0.zzl(1, null, null))))) {
            case 0: {
                break;
            }
            case 1: {
                return zzlb0;
            }
            default: {
                boolean z = zzmq.zza().zzb(zzlb0.getClass()).zzk(zzlb0);
                zzlb0.zzl(2, (z ? zzlb0 : null), null);
                if(z) {
                    return zzlb0;
                }
            }
        }
        throw new zznj(zzlb0);
    }

    public zzlb zzaE() {
        if(!this.zza.zzbR()) {
            return this.zza;
        }
        this.zza.zzbM();
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmh
    public zzmi zzaF() {
        return this.zzaE();
    }

    protected final void zzaH() {
        if(!this.zza.zzbR()) {
            this.zzaI();
        }
    }

    protected void zzaI() {
        zzlb zzlb0 = this.zzb.zzbD();
        zzkx.zza(zzlb0, this.zza);
        this.zza = zzlb0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjj
    public final zzjj zzav() {
        return this.zzaA();
    }

    @Override  // com.google.android.gms.internal.measurement.zzjj
    public final zzjj zzaw(byte[] arr_b, int v, int v1) throws zzll {
        this.zzaC(arr_b, 0, v1, zzkn.zza);
        return this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjj
    public final zzjj zzax(byte[] arr_b, int v, int v1, zzkn zzkn0) throws zzll {
        this.zzaC(arr_b, 0, v1, zzkn0);
        return this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmj
    public final zzmi zzbV() {
        throw null;
    }
}

