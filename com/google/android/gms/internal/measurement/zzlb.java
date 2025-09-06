package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzlb extends zzjk {
    private static final Map zza;
    protected zznl zzc;
    private int zzd;

    static {
        zzlb.zza = new ConcurrentHashMap();
    }

    public zzlb() {
        this.zzd = -1;
        this.zzc = zznl.zzc();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        return this.getClass() == object0.getClass() ? zzmq.zza().zzb(this.getClass()).zzj(this, ((zzlb)object0)) : false;
    }

    @Override
    public final int hashCode() {
        if(!this.zzbR()) {
            int v = this.zzb;
            if(v == 0) {
                v = this.zzby();
                this.zzb = v;
            }
            return v;
        }
        return this.zzby();
    }

    @Override
    public final String toString() {
        return zzmk.zza(this, super.toString());
    }

    private final int zza(zzmt zzmt0) {
        return zzmt0 == null ? zzmq.zza().zzb(this.getClass()).zza(this) : zzmt0.zza(this);
    }

    protected final zzkx zzbA() {
        return (zzkx)this.zzl(5, null, null);
    }

    public final zzkx zzbB() {
        zzkx zzkx0 = (zzkx)this.zzl(5, null, null);
        zzkx0.zzaB(this);
        return zzkx0;
    }

    static zzlb zzbC(Class class0) {
        Map map0 = zzlb.zza;
        zzlb zzlb0 = (zzlb)map0.get(class0);
        if(zzlb0 == null) {
            try {
                Class.forName(class0.getName(), true, class0.getClassLoader());
            }
            catch(ClassNotFoundException classNotFoundException0) {
                throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException0);
            }
            zzlb0 = (zzlb)map0.get(class0);
        }
        if(zzlb0 == null) {
            zzlb zzlb1 = (zzlb)((zzlb)zznu.zze(class0)).zzl(6, null, null);
            if(zzlb1 == null) {
                throw new IllegalStateException();
            }
            map0.put(class0, zzlb1);
            return zzlb1;
        }
        return zzlb0;
    }

    final zzlb zzbD() {
        return (zzlb)this.zzl(4, null, null);
    }

    protected static zzlg zzbE() {
        return zzlc.zzf();
    }

    protected static zzlh zzbF() {
        return zzlx.zzf();
    }

    protected static zzlh zzbG(zzlh zzlh0) {
        int v = zzlh0.size();
        return v == 0 ? zzlh0.zze(10) : zzlh0.zze(v + v);
    }

    protected static zzli zzbH() {
        return zzmr.zze();
    }

    protected static zzli zzbI(zzli zzli0) {
        int v = zzli0.size();
        return v == 0 ? zzli0.zzd(10) : zzli0.zzd(v + v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmi
    public final zzmh zzbJ() {
        return (zzkx)this.zzl(5, null, null);
    }

    static Object zzbK(Method method0, Object object0, Object[] arr_object) {
        try {
            return method0.invoke(object0, arr_object);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("Couldn\'t use Java reflection to implement protocol message reflection.", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Throwable throwable0 = invocationTargetException0.getCause();
            if(throwable0 instanceof RuntimeException) {
                throw (RuntimeException)throwable0;
            }
            if(throwable0 instanceof Error) {
                throw (Error)throwable0;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable0);
        }
    }

    protected static Object zzbL(zzmi zzmi0, String s, Object[] arr_object) {
        return new zzms(zzmi0, s, arr_object);
    }

    protected final void zzbM() {
        zzmq.zza().zzb(this.getClass()).zzf(this);
        this.zzbN();
    }

    final void zzbN() {
        this.zzd &= 0x7FFFFFFF;
    }

    protected static void zzbO(Class class0, zzlb zzlb0) {
        zzlb0.zzbN();
        zzlb.zza.put(class0, zzlb0);
    }

    // 去混淆评级： 低(25)
    final void zzbP(int v) {
        this.zzd |= 0x7FFFFFFF;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmi
    public final void zzbQ(zzki zzki0) throws IOException {
        zzmq.zza().zzb(this.getClass()).zzi(this, zzkj.zza(zzki0));
    }

    final boolean zzbR() {
        return (this.zzd & 0x80000000) != 0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmj
    public final zzmi zzbV() {
        return (zzlb)this.zzl(6, null, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjk
    final int zzbu(zzmt zzmt0) {
        if(this.zzbR()) {
            int v = this.zza(zzmt0);
            if(v < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + v);
            }
            return v;
        }
        int v1 = this.zzd & 0x7FFFFFFF;
        if(v1 != 0x7FFFFFFF) {
            return v1;
        }
        int v2 = this.zza(zzmt0);
        if(v2 < 0) {
            throw new IllegalStateException("serialized size must be non-negative, was " + v2);
        }
        this.zzd = this.zzd & 0x80000000 | v2;
        return v2;
    }

    final int zzby() {
        return zzmq.zza().zzb(this.getClass()).zzb(this);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmi
    public final int zzbz() {
        if(this.zzbR()) {
            int v = this.zza(null);
            if(v < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + v);
            }
            return v;
        }
        int v1 = this.zzd & 0x7FFFFFFF;
        if(v1 != 0x7FFFFFFF) {
            return v1;
        }
        int v2 = this.zza(null);
        if(v2 < 0) {
            throw new IllegalStateException("serialized size must be non-negative, was " + v2);
        }
        this.zzd = this.zzd & 0x80000000 | v2;
        return v2;
    }

    protected abstract Object zzl(int arg1, Object arg2, Object arg3);
}

