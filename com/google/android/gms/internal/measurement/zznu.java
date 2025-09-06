package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zznu {
    static final long zza;
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zznt zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    static {
        boolean z4;
        boolean z3;
        Unsafe unsafe0 = zznu.zzg();
        zznu.zzc = unsafe0;
        zznu.zzd = Memory.class;
        boolean z = zznu.zzv(Long.TYPE);
        zznu.zze = z;
        boolean z1 = zznu.zzv(Integer.TYPE);
        zznt zznt0 = null;
        if(unsafe0 != null) {
            if(z) {
                zznt0 = new zzns(unsafe0);
            }
            else if(z1) {
                zznt0 = new zznr(unsafe0);
            }
        }
        zznu.zzf = zznt0;
        boolean z2 = true;
        if(zznt0 == null) {
        label_27:
            z3 = false;
        }
        else {
            try {
                Class class0 = zznt0.zza.getClass();
                class0.getMethod("objectFieldOffset", Field.class);
                class0.getMethod("getLong", Object.class, Long.TYPE);
                if(zznu.zzB() == null) {
                    goto label_27;
                }
                else {
                    goto label_25;
                }
            }
            catch(Throwable throwable0) {
                zznu.zzh(throwable0);
                z3 = false;
            }
            goto label_30;
        label_25:
            z3 = true;
        }
    label_30:
        zznu.zzg = z3;
        zznt zznt1 = zznu.zzf;
        if(zznt1 == null) {
            z4 = false;
        }
        else {
            try {
                Class class1 = zznt1.zza.getClass();
                class1.getMethod("objectFieldOffset", Field.class);
                class1.getMethod("arrayBaseOffset", Class.class);
                class1.getMethod("arrayIndexScale", Class.class);
                class1.getMethod("getInt", Object.class, Long.TYPE);
                class1.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                class1.getMethod("getLong", Object.class, Long.TYPE);
                class1.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                class1.getMethod("getObject", Object.class, Long.TYPE);
                class1.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                z4 = true;
            }
            catch(Throwable throwable1) {
                zznu.zzh(throwable1);
                z4 = false;
            }
        }
        zznu.zzh = z4;
        zznu.zza = (long)zznu.zzz(byte[].class);
        zznu.zzz(boolean[].class);
        zznu.zzA(boolean[].class);
        zznu.zzz(int[].class);
        zznu.zzA(int[].class);
        zznu.zzz(long[].class);
        zznu.zzA(long[].class);
        zznu.zzz(float[].class);
        zznu.zzA(float[].class);
        zznu.zzz(double[].class);
        zznu.zzA(double[].class);
        zznu.zzz(Object[].class);
        zznu.zzA(Object[].class);
        Field field0 = zznu.zzB();
        if(field0 != null) {
            zznt zznt2 = zznu.zzf;
            if(zznt2 != null) {
                zznt2.zza.objectFieldOffset(field0);
            }
        }
        if(ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) {
            z2 = false;
        }
        zznu.zzb = z2;
    }

    // 去混淆评级： 低(20)
    private static int zzA(Class class0) {
        return zznu.zzh ? zznu.zzf.zza.arrayIndexScale(class0) : -1;
    }

    private static Field zzB() {
        Field field0 = zznu.zzC(Buffer.class, "effectiveDirectAddress");
        if(field0 == null) {
            Field field1 = zznu.zzC(Buffer.class, "address");
            return field1 == null || field1.getType() != Long.TYPE ? null : field1;
        }
        return field0;
    }

    private static Field zzC(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static void zzD(Object object0, long v, byte b) {
        int v1 = zznu.zzf.zza.getInt(object0, -4L & v);
        int v2 = (~((int)v) & 3) << 3;
        zznu.zzf.zza.putInt(object0, -4L & v, (0xFF & b) << v2 | v1 & ~(0xFF << v2));
    }

    private static void zzE(Object object0, long v, byte b) {
        int v1 = zznu.zzf.zza.getInt(object0, -4L & v);
        int v2 = (((int)v) & 3) << 3;
        zznu.zzf.zza.putInt(object0, -4L & v, (0xFF & b) << v2 | v1 & ~(0xFF << v2));
    }

    static double zza(Object object0, long v) {
        return zznu.zzf.zza(object0, v);
    }

    static float zzb(Object object0, long v) {
        return zznu.zzf.zzb(object0, v);
    }

    static int zzc(Object object0, long v) {
        return zznu.zzf.zza.getInt(object0, v);
    }

    static long zzd(Object object0, long v) {
        return zznu.zzf.zza.getLong(object0, v);
    }

    static Object zze(Class class0) {
        try {
            return zznu.zzc.allocateInstance(class0);
        }
        catch(InstantiationException instantiationException0) {
            throw new IllegalStateException(instantiationException0);
        }
    }

    static Object zzf(Object object0, long v) {
        return zznu.zzf.zza.getObject(object0, v);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe)AccessController.doPrivileged(new zznq());
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static void zzh(Throwable throwable0) {
        Logger.getLogger("com.google.android.gms.internal.measurement.zznu").logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: " + throwable0.toString());
    }

    static void zzi(Object object0, long v, boolean z) {
        zznu.zzD(object0, v, ((byte)z));
    }

    static void zzj(Object object0, long v, boolean z) {
        zznu.zzE(object0, v, ((byte)z));
    }

    static void zzk(Object object0, long v, byte b) {
        zznu.zzD(object0, v, b);
    }

    static void zzl(Object object0, long v, byte b) {
        zznu.zzE(object0, v, b);
    }

    static void zzm(Object object0, long v, boolean z) {
        zznu.zzf.zzc(object0, v, z);
    }

    static void zzn(byte[] arr_b, long v, byte b) {
        zznu.zzf.zzd(arr_b, zznu.zza + v, b);
    }

    static void zzo(Object object0, long v, double f) {
        zznu.zzf.zze(object0, v, f);
    }

    static void zzp(Object object0, long v, float f) {
        zznu.zzf.zzf(object0, v, f);
    }

    static void zzq(Object object0, long v, int v1) {
        zznu.zzf.zza.putInt(object0, v, v1);
    }

    static void zzr(Object object0, long v, long v1) {
        zznu.zzf.zza.putLong(object0, v, v1);
    }

    static void zzs(Object object0, long v, Object object1) {
        zznu.zzf.zza.putObject(object0, v, object1);
    }

    static boolean zzt(Object object0, long v) {
        return ((byte)(zznu.zzf.zza.getInt(object0, -4L & v) >>> ((int)((~v & 3L) << 3)) & 0xFF)) != 0;
    }

    static boolean zzu(Object object0, long v) {
        return ((byte)(zznu.zzf.zza.getInt(object0, -4L & v) >>> ((int)((v & 3L) << 3)) & 0xFF)) != 0;
    }

    static boolean zzv(Class class0) {
        try {
            zznu.zzd.getMethod("peekLong", class0, Boolean.TYPE);
            zznu.zzd.getMethod("pokeLong", class0, Long.TYPE, Boolean.TYPE);
            zznu.zzd.getMethod("pokeInt", class0, Integer.TYPE, Boolean.TYPE);
            zznu.zzd.getMethod("peekInt", class0, Boolean.TYPE);
            zznu.zzd.getMethod("pokeByte", class0, Byte.TYPE);
            zznu.zzd.getMethod("peekByte", class0);
            zznu.zzd.getMethod("pokeByteArray", class0, byte[].class, Integer.TYPE, Integer.TYPE);
            zznu.zzd.getMethod("peekByteArray", class0, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        }
        catch(Throwable unused_ex) {
            return false;
        }
    }

    static boolean zzw(Object object0, long v) {
        return zznu.zzf.zzg(object0, v);
    }

    static boolean zzx() [...] // 潜在的解密器

    static boolean zzy() {
        return zznu.zzg;
    }

    // 去混淆评级： 低(20)
    private static int zzz(Class class0) {
        return zznu.zzh ? zznu.zzf.zza.arrayBaseOffset(class0) : -1;
    }
}

