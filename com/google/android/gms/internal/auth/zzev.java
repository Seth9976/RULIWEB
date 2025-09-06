package com.google.android.gms.internal.auth;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzev extends zzdq {
    private static final Map zzb;
    protected zzha zzc;
    private int zzd;

    static {
        zzev.zzb = new ConcurrentHashMap();
    }

    public zzev() {
        this.zzd = -1;
        this.zzc = zzha.zza();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        return this.getClass() == object0.getClass() ? zzgf.zza().zzb(this.getClass()).zzh(this, ((zzev)object0)) : false;
    }

    @Override
    public final int hashCode() {
        if(!this.zzm()) {
            int v = this.zza;
            if(v == 0) {
                v = this.zza();
                this.zza = v;
            }
            return v;
        }
        return this.zza();
    }

    @Override
    public final String toString() {
        return zzfz.zza(this, super.toString());
    }

    final int zza() {
        return zzgf.zza().zzb(this.getClass()).zza(this);
    }

    static zzev zzb(Class class0) {
        Map map0 = zzev.zzb;
        zzev zzev0 = (zzev)map0.get(class0);
        if(zzev0 == null) {
            try {
                Class.forName(class0.getName(), true, class0.getClassLoader());
            }
            catch(ClassNotFoundException classNotFoundException0) {
                throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException0);
            }
            zzev0 = (zzev)map0.get(class0);
        }
        if(zzev0 == null) {
            zzev zzev1 = (zzev)((zzev)zzhj.zze(class0)).zzn(6, null, null);
            if(zzev1 == null) {
                throw new IllegalStateException();
            }
            map0.put(class0, zzev1);
            return zzev1;
        }
        return zzev0;
    }

    final zzev zzc() {
        return (zzev)this.zzn(4, null, null);
    }

    protected static zzev zzd(zzev zzev0, byte[] arr_b) throws zzfb {
        zzev zzev1 = zzev.zzo(zzev0, arr_b, 0, arr_b.length, zzel.zza);
        if(zzev1 != null) {
            switch(((byte)(((Byte)zzev1.zzn(1, null, null))))) {
                case 0: {
                label_6:
                    zzfb zzfb0 = new zzgy(zzev1).zza();
                    zzfb0.zze(zzev1);
                    throw zzfb0;
                }
                case 1: {
                    break;
                }
                default: {
                    boolean z = zzgf.zza().zzb(zzev1.getClass()).zzi(zzev1);
                    zzev1.zzn(2, (z ? zzev1 : null), null);
                    if(!z) {
                        goto label_6;
                    }
                }
            }
        }
        return zzev1;
    }

    @Override  // com.google.android.gms.internal.auth.zzfy
    public final zzfx zze() {
        return (zzev)this.zzn(6, null, null);
    }

    protected static zzez zzf() {
        return zzgg.zze();
    }

    static Object zzg(Method method0, Object object0, Object[] arr_object) {
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

    protected static Object zzh(zzfx zzfx0, String s, Object[] arr_object) {
        return new zzgh(zzfx0, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001A", arr_object);
    }

    protected final void zzi() {
        zzgf.zza().zzb(this.getClass()).zze(this);
        this.zzj();
    }

    final void zzj() {
        this.zzd &= 0x7FFFFFFF;
    }

    protected static void zzk(Class class0, zzev zzev0) {
        zzev0.zzj();
        zzev.zzb.put(class0, zzev0);
    }

    // 去混淆评级： 低(25)
    final void zzl(int v) {
        this.zzd |= 0x7FFFFFFF;
    }

    final boolean zzm() {
        return (this.zzd & 0x80000000) != 0;
    }

    protected abstract Object zzn(int arg1, Object arg2, Object arg3);

    private static zzev zzo(zzev zzev0, byte[] arr_b, int v, int v1, zzel zzel0) throws zzfb {
        zzev zzev1 = zzev0.zzc();
        try {
            zzgi zzgi0 = zzgf.zza().zzb(zzev1.getClass());
            zzgi0.zzg(zzev1, arr_b, 0, v1, new zzdt(zzel0));
            zzgi0.zze(zzev1);
            return zzev1;
        }
        catch(zzfb zzfb0) {
            zzfb0.zze(zzev1);
            throw zzfb0;
        }
        catch(zzgy zzgy0) {
            zzfb zzfb1 = zzgy0.zza();
            zzfb1.zze(zzev1);
            throw zzfb1;
        }
        catch(IOException iOException0) {
            if(iOException0.getCause() instanceof zzfb) {
                throw (zzfb)iOException0.getCause();
            }
            zzfb zzfb2 = new zzfb(iOException0);
            zzfb2.zze(zzev1);
            throw zzfb2;
        }
        catch(IndexOutOfBoundsException unused_ex) {
            zzfb zzfb3 = zzfb.zzf();
            zzfb3.zze(zzev1);
            throw zzfb3;
        }
    }
}

