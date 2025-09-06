package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import sun.misc.Unsafe;

final class zzml implements zzmt {
    private static final int[] zza;
    private static final Unsafe zzb;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzmi zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzlw zzm;
    private final zznk zzn;
    private final zzko zzo;
    private final zzmn zzp;
    private final zzmd zzq;

    static {
        zzml.zza = new int[0];
        zzml.zzb = zznu.zzg();
    }

    private zzml(int[] arr_v, Object[] arr_object, int v, int v1, zzmi zzmi0, boolean z, boolean z1, int[] arr_v1, int v2, int v3, zzmn zzmn0, zzlw zzlw0, zznk zznk0, zzko zzko0, zzmd zzmd0) {
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zze = v;
        this.zzf = v1;
        this.zzi = z;
        this.zzh = zzko0 != null && zzko0.zzc(zzmi0);
        this.zzj = arr_v1;
        this.zzk = v2;
        this.zzl = v3;
        this.zzp = zzmn0;
        this.zzm = zzlw0;
        this.zzn = zznk0;
        this.zzo = zzko0;
        this.zzg = zzmi0;
        this.zzq = zzmd0;
    }

    private final zzlf zzA(int v) {
        return (zzlf)this.zzd[v / 3 * 2 + 1];
    }

    private final zzmt zzB(int v) {
        int v1 = v / 3 + v / 3;
        zzmt zzmt0 = (zzmt)this.zzd[v1];
        if(zzmt0 != null) {
            return zzmt0;
        }
        zzmt zzmt1 = zzmq.zza().zzb(((Class)this.zzd[v1 + 1]));
        this.zzd[v1] = zzmt1;
        return zzmt1;
    }

    private final Object zzC(int v) {
        return this.zzd[v / 3 + v / 3];
    }

    private final Object zzD(Object object0, int v) {
        zzmt zzmt0 = this.zzB(v);
        int v1 = this.zzy(v);
        if(!this.zzP(object0, v)) {
            return zzmt0.zze();
        }
        Object object1 = zzml.zzb.getObject(object0, ((long)(v1 & 0xFFFFF)));
        if(zzml.zzS(object1)) {
            return object1;
        }
        Object object2 = zzmt0.zze();
        if(object1 != null) {
            zzmt0.zzg(object2, object1);
        }
        return object2;
    }

    private final Object zzE(Object object0, int v, int v1) {
        zzmt zzmt0 = this.zzB(v1);
        if(!this.zzT(object0, v, v1)) {
            return zzmt0.zze();
        }
        int v2 = this.zzy(v1);
        Object object1 = zzml.zzb.getObject(object0, ((long)(v2 & 0xFFFFF)));
        if(zzml.zzS(object1)) {
            return object1;
        }
        Object object2 = zzmt0.zze();
        if(object1 != null) {
            zzmt0.zzg(object2, object1);
        }
        return object2;
    }

    private static Field zzF(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(NoSuchFieldException unused_ex) {
            Field[] arr_field = class0.getDeclaredFields();
            for(int v = 0; v < arr_field.length; ++v) {
                Field field0 = arr_field[v];
                if(s.equals(field0.getName())) {
                    return field0;
                }
            }
            throw new RuntimeException("Field " + s + " for " + class0.getName() + " not found. Known fields are " + Arrays.toString(arr_field));
        }
    }

    private static void zzG(Object object0) {
        if(!zzml.zzS(object0)) {
            throw new IllegalArgumentException("Mutating immutable message: " + object0);
        }
    }

    private final void zzH(Object object0, Object object1, int v) {
        if(!this.zzP(object1, v)) {
            return;
        }
        int v1 = this.zzy(v);
        Unsafe unsafe0 = zzml.zzb;
        Object object2 = unsafe0.getObject(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[v] + " is present but null: " + object1.toString());
        }
        zzmt zzmt0 = this.zzB(v);
        if(!this.zzP(object0, v)) {
            if(zzml.zzS(object2)) {
                Object object3 = zzmt0.zze();
                zzmt0.zzg(object3, object2);
                unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object3);
            }
            else {
                unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object2);
            }
            this.zzJ(object0, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, ((long)(v1 & 0xFFFFF)));
        if(!zzml.zzS(object4)) {
            Object object5 = zzmt0.zze();
            zzmt0.zzg(object5, object4);
            unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object5);
            object4 = object5;
        }
        zzmt0.zzg(object4, object2);
    }

    private final void zzI(Object object0, Object object1, int v) {
        int v1 = this.zzc[v];
        if(!this.zzT(object1, v1, v)) {
            return;
        }
        int v2 = this.zzy(v);
        Unsafe unsafe0 = zzml.zzb;
        Object object2 = unsafe0.getObject(object1, ((long)(v2 & 0xFFFFF)));
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[v] + " is present but null: " + object1.toString());
        }
        zzmt zzmt0 = this.zzB(v);
        if(!this.zzT(object0, v1, v)) {
            if(zzml.zzS(object2)) {
                Object object3 = zzmt0.zze();
                zzmt0.zzg(object3, object2);
                unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object3);
            }
            else {
                unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object2);
            }
            this.zzK(object0, v1, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, ((long)(v2 & 0xFFFFF)));
        if(!zzml.zzS(object4)) {
            Object object5 = zzmt0.zze();
            zzmt0.zzg(object5, object4);
            unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object5);
            object4 = object5;
        }
        zzmt0.zzg(object4, object2);
    }

    private final void zzJ(Object object0, int v) {
        int v1 = this.zzv(v);
        if(((long)(0xFFFFF & v1)) == 0xFFFFFL) {
            return;
        }
        zznu.zzq(object0, ((long)(0xFFFFF & v1)), 1 << (v1 >>> 20) | zznu.zzc(object0, ((long)(0xFFFFF & v1))));
    }

    private final void zzK(Object object0, int v, int v1) {
        zznu.zzq(object0, ((long)(this.zzv(v1) & 0xFFFFF)), v);
    }

    private final void zzL(Object object0, int v, Object object1) {
        int v1 = this.zzy(v);
        zzml.zzb.putObject(object0, ((long)(v1 & 0xFFFFF)), object1);
        this.zzJ(object0, v);
    }

    private final void zzM(Object object0, int v, int v1, Object object1) {
        int v2 = this.zzy(v1);
        zzml.zzb.putObject(object0, ((long)(v2 & 0xFFFFF)), object1);
        this.zzK(object0, v, v1);
    }

    private final void zzN(zzoc zzoc0, int v, Object object0, int v1) throws IOException {
        if(object0 == null) {
            return;
        }
        zzmb zzmb0 = (zzmb)this.zzC(v1);
        throw null;
    }

    private final boolean zzO(Object object0, Object object1, int v) {
        return this.zzP(object0, v) == this.zzP(object1, v);
    }

    private final boolean zzP(Object object0, int v) {
        int v1 = this.zzv(v);
        if(((long)(v1 & 0xFFFFF)) == 0xFFFFFL) {
            int v2 = this.zzy(v);
            switch(v2 >>> 20 & 0xFF) {
                case 0: {
                    return Double.doubleToRawLongBits(zznu.zza(object0, ((long)(v2 & 0xFFFFF)))) != 0L;
                }
                case 1: {
                    return Float.floatToRawIntBits(zznu.zzb(object0, ((long)(v2 & 0xFFFFF)))) != 0;
                }
                case 2: {
                    return zznu.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 3: {
                    return zznu.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 4: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 5: {
                    return zznu.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 6: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 7: {
                    return zznu.zzw(object0, ((long)(v2 & 0xFFFFF)));
                }
                case 8: {
                    Object object1 = zznu.zzf(object0, ((long)(v2 & 0xFFFFF)));
                    if(object1 instanceof String) {
                        return !((String)object1).isEmpty();
                    }
                    if(!(object1 instanceof zzka)) {
                        throw new IllegalArgumentException();
                    }
                    return !zzka.zzb.equals(object1);
                }
                case 9: {
                    return zznu.zzf(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                case 10: {
                    Object object2 = zznu.zzf(object0, ((long)(v2 & 0xFFFFF)));
                    return !zzka.zzb.equals(object2);
                }
                case 11: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 12: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 13: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 14: {
                    return zznu.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 15: {
                    return zznu.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 16: {
                    return zznu.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 17: {
                    return zznu.zzf(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        return (zznu.zzc(object0, ((long)(v1 & 0xFFFFF))) & 1 << (v1 >>> 20)) != 0;
    }

    private final boolean zzQ(Object object0, int v, int v1, int v2, int v3) {
        return v1 == 0xFFFFF ? this.zzP(object0, v) : (v2 & v3) != 0;
    }

    private static boolean zzR(Object object0, int v, zzmt zzmt0) {
        return zzmt0.zzk(zznu.zzf(object0, ((long)(v & 0xFFFFF))));
    }

    private static boolean zzS(Object object0) {
        if(object0 == null) {
            return false;
        }
        return object0 instanceof zzlb ? ((zzlb)object0).zzbR() : true;
    }

    private final boolean zzT(Object object0, int v, int v1) {
        return zznu.zzc(object0, ((long)(this.zzv(v1) & 0xFFFFF))) == v;
    }

    private static boolean zzU(Object object0, long v) {
        return ((Boolean)zznu.zzf(object0, v)).booleanValue();
    }

    private static final void zzV(int v, Object object0, zzoc zzoc0) throws IOException {
        if(object0 instanceof String) {
            zzoc0.zzF(v, ((String)object0));
            return;
        }
        zzoc0.zzd(v, ((zzka)object0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final int zza(Object object0) {
        int v19;
        int v18;
        int v14;
        int v13;
        int v11;
        int v10;
        int v9;
        int v8;
        int v7;
        if(this.zzi) {
            Unsafe unsafe0 = zzml.zzb;
            int v1 = 0;
            for(int v = 0; v < this.zzc.length; v += 3) {
                int v2 = this.zzy(v);
                int v3 = v2 >>> 20 & 0xFF;
                int v4 = this.zzc[v];
                if(v3 >= zzkt.zzJ.zza() && v3 <= zzkt.zzW.zza()) {
                    int v5 = this.zzc[v + 2];
                }
                long v6 = (long)(v2 & 0xFFFFF);
                switch(v3) {
                    case 0: {
                        if(this.zzP(object0, v)) {
                            v7 = zzki.zzx(v4 << 3);
                            v11 = v7 + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if(this.zzP(object0, v)) {
                            v8 = zzki.zzx(v4 << 3);
                            v11 = v8 + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if(this.zzP(object0, v)) {
                            v9 = zzki.zzy(zznu.zzd(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if(this.zzP(object0, v)) {
                            v9 = zzki.zzy(zznu.zzd(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if(this.zzP(object0, v)) {
                            v9 = zzki.zzu(zznu.zzc(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if(this.zzP(object0, v)) {
                            v7 = zzki.zzx(v4 << 3);
                            v11 = v7 + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if(this.zzP(object0, v)) {
                            v8 = zzki.zzx(v4 << 3);
                            v11 = v8 + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if(this.zzP(object0, v)) {
                            v11 = zzki.zzx(v4 << 3) + 1;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if(this.zzP(object0, v)) {
                            Object object1 = zznu.zzf(object0, v6);
                            if(object1 instanceof zzka) {
                                int v12 = ((zzka)object1).zzd();
                                v13 = zzki.zzx(v12) + v12;
                                v14 = zzki.zzx(v4 << 3);
                                v11 = v14 + v13;
                                v1 += v11;
                            }
                            else {
                                v9 = zzki.zzw(((String)object1));
                                v10 = zzki.zzx(v4 << 3);
                                v1 += v10 + v9;
                            }
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if(this.zzP(object0, v)) {
                            v11 = zzmv.zzn(v4, zznu.zzf(object0, v6), this.zzB(v));
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 10: {
                        if(this.zzP(object0, v)) {
                            int v15 = ((zzka)zznu.zzf(object0, v6)).zzd();
                            v13 = zzki.zzx(v15) + v15;
                            v14 = zzki.zzx(v4 << 3);
                            v11 = v14 + v13;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 11: {
                        if(this.zzP(object0, v)) {
                            v9 = zzki.zzx(zznu.zzc(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 12: {
                        if(this.zzP(object0, v)) {
                            v9 = zzki.zzu(zznu.zzc(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 13: {
                        if(this.zzP(object0, v)) {
                            v8 = zzki.zzx(v4 << 3);
                            v11 = v8 + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 14: {
                        if(this.zzP(object0, v)) {
                            v7 = zzki.zzx(v4 << 3);
                            v11 = v7 + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 15: {
                        if(this.zzP(object0, v)) {
                            int v16 = zznu.zzc(object0, v6);
                            v10 = zzki.zzx(v4 << 3);
                            v9 = zzki.zzx(v16 >> 0x1F ^ v16 + v16);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 16: {
                        if(this.zzP(object0, v)) {
                            long v17 = zznu.zzd(object0, v6);
                            v11 = zzki.zzx(v4 << 3) + zzki.zzy(v17 >> 0x3F ^ v17 + v17);
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 17: {
                        if(this.zzP(object0, v)) {
                            v11 = zzki.zzt(v4, ((zzmi)zznu.zzf(object0, v6)), this.zzB(v));
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 18: {
                        v11 = zzmv.zzg(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 19: {
                        v11 = zzmv.zze(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 20: {
                        v11 = zzmv.zzl(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 21: {
                        v11 = zzmv.zzw(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 22: {
                        v11 = zzmv.zzj(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 23: {
                        v11 = zzmv.zzg(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 24: {
                        v11 = zzmv.zze(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 25: {
                        v11 = zzmv.zza(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 26: {
                        v11 = zzmv.zzt(v4, ((List)zznu.zzf(object0, v6)));
                        v1 += v11;
                        break;
                    }
                    case 27: {
                        v11 = zzmv.zzo(v4, ((List)zznu.zzf(object0, v6)), this.zzB(v));
                        v1 += v11;
                        break;
                    }
                    case 28: {
                        v11 = zzmv.zzb(v4, ((List)zznu.zzf(object0, v6)));
                        v1 += v11;
                        break;
                    }
                    case 29: {
                        v11 = zzmv.zzu(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 30: {
                        v11 = zzmv.zzc(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 0x1F: {
                        v11 = zzmv.zze(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 0x20: {
                        v11 = zzmv.zzg(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 33: {
                        v11 = zzmv.zzp(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 34: {
                        v11 = zzmv.zzr(v4, ((List)zznu.zzf(object0, v6)), false);
                        v1 += v11;
                        break;
                    }
                    case 35: {
                        v9 = zzmv.zzh(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 36: {
                        v9 = zzmv.zzf(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 37: {
                        v9 = zzmv.zzm(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 38: {
                        v9 = zzmv.zzx(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 39: {
                        v9 = zzmv.zzk(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 40: {
                        v9 = zzmv.zzh(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 41: {
                        v9 = zzmv.zzf(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 42: {
                        v9 = ((List)unsafe0.getObject(object0, v6)).size();
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 43: {
                        v9 = zzmv.zzv(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 44: {
                        v9 = zzmv.zzd(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 45: {
                        v9 = zzmv.zzf(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 46: {
                        v9 = zzmv.zzh(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 0x2F: {
                        v9 = zzmv.zzq(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v18 = zzki.zzx(v9);
                            v19 = zzki.zzx(v4 << 3);
                            v10 = v19 + v18;
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 0x30: {
                        v9 = zzmv.zzs(((List)unsafe0.getObject(object0, v6)));
                        if(v9 > 0) {
                            v10 = zzki.zzx(v4 << 3) + zzki.zzx(v9);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 49: {
                        v11 = zzmv.zzi(v4, ((List)zznu.zzf(object0, v6)), this.zzB(v));
                        v1 += v11;
                        break;
                    }
                    case 50: {
                        zzmd.zza(v4, zznu.zzf(object0, v6), this.zzC(v));
                        break;
                    }
                    case 51: {
                        if(this.zzT(object0, v4, v)) {
                            v7 = zzki.zzx(v4 << 3);
                            v11 = v7 + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 52: {
                        if(this.zzT(object0, v4, v)) {
                            v8 = zzki.zzx(v4 << 3);
                            v11 = v8 + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 53: {
                        if(this.zzT(object0, v4, v)) {
                            v9 = zzki.zzy(zzml.zzz(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 54: {
                        if(this.zzT(object0, v4, v)) {
                            v9 = zzki.zzy(zzml.zzz(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 55: {
                        if(this.zzT(object0, v4, v)) {
                            v9 = zzki.zzu(zzml.zzp(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 56: {
                        if(this.zzT(object0, v4, v)) {
                            v7 = zzki.zzx(v4 << 3);
                            v11 = v7 + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 57: {
                        if(this.zzT(object0, v4, v)) {
                            v8 = zzki.zzx(v4 << 3);
                            v11 = v8 + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 58: {
                        if(this.zzT(object0, v4, v)) {
                            v11 = zzki.zzx(v4 << 3) + 1;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 59: {
                        if(this.zzT(object0, v4, v)) {
                            Object object2 = zznu.zzf(object0, v6);
                            if(object2 instanceof zzka) {
                                int v20 = ((zzka)object2).zzd();
                                v13 = zzki.zzx(v20) + v20;
                                v14 = zzki.zzx(v4 << 3);
                                v11 = v14 + v13;
                                v1 += v11;
                            }
                            else {
                                v9 = zzki.zzw(((String)object2));
                                v10 = zzki.zzx(v4 << 3);
                                v1 += v10 + v9;
                            }
                            break;
                        }
                        break;
                    }
                    case 60: {
                        if(this.zzT(object0, v4, v)) {
                            v11 = zzmv.zzn(v4, zznu.zzf(object0, v6), this.zzB(v));
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 61: {
                        if(this.zzT(object0, v4, v)) {
                            int v21 = ((zzka)zznu.zzf(object0, v6)).zzd();
                            v11 = zzki.zzx(v4 << 3) + (zzki.zzx(v21) + v21);
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 62: {
                        if(this.zzT(object0, v4, v)) {
                            v9 = zzki.zzx(zzml.zzp(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                            break;
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zzT(object0, v4, v)) {
                            v9 = zzki.zzu(zzml.zzp(object0, v6));
                            v10 = zzki.zzx(v4 << 3);
                            v1 += v10 + v9;
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zzT(object0, v4, v)) {
                            v11 = zzki.zzx(v4 << 3) + 4;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 65: {
                        if(this.zzT(object0, v4, v)) {
                            v11 = zzki.zzx(v4 << 3) + 8;
                            v1 += v11;
                            break;
                        }
                        break;
                    }
                    case 66: {
                        if(this.zzT(object0, v4, v)) {
                            int v22 = zzml.zzp(object0, v6);
                            v1 += zzki.zzx(v4 << 3) + zzki.zzx(v22 >> 0x1F ^ v22 + v22);
                        }
                        break;
                    }
                    case 67: {
                        if(this.zzT(object0, v4, v)) {
                            long v23 = zzml.zzz(object0, v6);
                            v11 = zzki.zzx(v4 << 3) + zzki.zzy(v23 >> 0x3F ^ v23 + v23);
                            v1 += v11;
                        }
                        break;
                    }
                    case 68: {
                        if(this.zzT(object0, v4, v)) {
                            v1 += zzki.zzt(v4, ((zzmi)zznu.zzf(object0, v6)), this.zzB(v));
                        }
                    }
                }
            }
            Object object3 = this.zzn.zzd(object0);
            return v1 + this.zzn.zza(object3);
        }
        return this.zzo(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final int zzb(Object object0) {
        int v8;
        int v7;
        long v6;
        int v1 = 0;
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v2 = this.zzy(v);
            int v3 = this.zzc[v];
            long v4 = (long)(0xFFFFF & v2);
            int v5 = 37;
            switch(v2 >>> 20 & 0xFF) {
                case 0: {
                    v6 = Double.doubleToLongBits(zznu.zza(object0, v4));
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 1: {
                    v7 = v1 * 53;
                    v8 = Float.floatToIntBits(zznu.zzb(object0, v4));
                    v1 = v7 + v8;
                    break;
                }
                case 2: {
                    v6 = zznu.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 3: {
                    v6 = zznu.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 4: {
                    v7 = v1 * 53;
                    v8 = zznu.zzc(object0, v4);
                    v1 = v7 + v8;
                    break;
                }
                case 5: {
                    v6 = zznu.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 6: {
                    v7 = v1 * 53;
                    v8 = zznu.zzc(object0, v4);
                    v1 = v7 + v8;
                    break;
                }
                case 7: {
                    v7 = v1 * 53;
                    v8 = zzlj.zza(zznu.zzw(object0, v4));
                    v1 = v7 + v8;
                    break;
                }
                case 8: {
                    v1 = v1 * 53 + ((String)zznu.zzf(object0, v4)).hashCode();
                    break;
                }
                case 9: {
                    Object object1 = zznu.zzf(object0, v4);
                    if(object1 != null) {
                        v5 = object1.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 10: {
                    v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    break;
                }
                case 11: {
                    v1 = v1 * 53 + zznu.zzc(object0, v4);
                    break;
                }
                case 12: {
                    v1 = v1 * 53 + zznu.zzc(object0, v4);
                    break;
                }
                case 13: {
                    v1 = v1 * 53 + zznu.zzc(object0, v4);
                    break;
                }
                case 14: {
                    v6 = zznu.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 15: {
                    v1 = v1 * 53 + zznu.zzc(object0, v4);
                    break;
                }
                case 16: {
                    v6 = zznu.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 17: {
                    Object object2 = zznu.zzf(object0, v4);
                    if(object2 != null) {
                        v5 = object2.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    break;
                }
                case 50: {
                    v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    break;
                }
                case 51: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = Double.doubleToLongBits(zzml.zzm(object0, v4));
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 52: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + Float.floatToIntBits(zzml.zzn(object0, v4));
                    }
                    break;
                }
                case 53: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = zzml.zzz(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 54: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = zzml.zzz(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 55: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 56: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = zzml.zzz(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 57: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 58: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzlj.zza(zzml.zzU(object0, v4));
                    }
                    break;
                }
                case 59: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + ((String)zznu.zzf(object0, v4)).hashCode();
                    }
                    break;
                }
                case 60: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 61: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 62: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 0x40: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 65: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = zzml.zzz(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 66: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zzml.zzp(object0, v4);
                    }
                    break;
                }
                case 67: {
                    if(this.zzT(object0, v3, v)) {
                        v6 = zzml.zzz(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 68: {
                    if(this.zzT(object0, v3, v)) {
                        v1 = v1 * 53 + zznu.zzf(object0, v4).hashCode();
                    }
                }
            }
        }
        int v9 = this.zzn.zzd(object0).hashCode();
        if(!this.zzh) {
            return v1 * 53 + v9;
        }
        this.zzo.zza(object0);
        throw null;
    }

    final int zzc(Object object0, byte[] arr_b, int v, int v1, int v2, zzjn zzjn0) throws IOException {
        int v40;
        int v36;
        int v35;
        int v34;
        int v27;
        Unsafe unsafe2;
        Object object3;
        int v25;
        int v24;
        int v23;
        Unsafe unsafe3;
        byte[] arr_b3;
        zzjn zzjn3;
        Object object5;
        Unsafe unsafe4;
        Object object6;
        zzjn zzjn2;
        byte[] arr_b2;
        int v22;
        int v15;
        int v14;
        int v13;
        Unsafe unsafe1;
        Object object2;
        zzml zzml1;
        zzml zzml0 = this;
        Object object1 = object0;
        byte[] arr_b1 = arr_b;
        zzjn zzjn1 = zzjn0;
        zzml.zzG(object1);
        Unsafe unsafe0 = zzml.zzb;
        int v3 = v;
        int v4 = -1;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0xFFFFF;
        int v8 = 0;
        while(true) {
            if(v3 >= v1) {
                object2 = object1;
                unsafe1 = unsafe0;
                zzml1 = zzml0;
                break;
            }
            int v9 = v3 + 1;
            int v10 = arr_b1[v3];
            if(v10 < 0) {
                v9 = zzjo.zzk(v10, arr_b1, v9, zzjn1);
                v10 = zzjn1.zza;
            }
            int v11 = v9;
            int v12 = v10 >>> 3 <= v4 ? zzml0.zzt(v10 >>> 3) : zzml0.zzu(v10 >>> 3, v5 / 3);
            if(v12 == -1) {
                zzml1 = zzml0;
                object2 = object1;
                unsafe1 = unsafe0;
                v13 = v11;
                v14 = v10;
                v15 = 0;
            }
            else {
                int v16 = zzml0.zzc[v12 + 1];
                int v17 = v16 >>> 20 & 0xFF;
                int[] arr_v = zzml0.zzc;
                long v18 = (long)(v16 & 0xFFFFF);
                if(v17 <= 17) {
                    int v19 = arr_v[v12 + 2];
                    int v20 = 1 << (v19 >>> 20);
                    int v21 = v19 & 0xFFFFF;
                    if(v21 == v7) {
                        v22 = v20;
                    }
                    else {
                        v22 = v20;
                        if(v7 != 0xFFFFF) {
                            unsafe0.putInt(object1, ((long)v7), v8);
                        }
                        v8 = unsafe0.getInt(object1, ((long)v21));
                        v7 = v21;
                    }
                    switch(v17) {
                        case 0: {
                            arr_b2 = arr_b1;
                            zzjn2 = zzjn1;
                            v23 = v11;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            object3 = object1;
                            unsafe2 = unsafe0;
                            if((v10 & 7) == 1) {
                                zznu.zzo(object3, v18, Double.longBitsToDouble(zzjo.zzp(arr_b2, v23)));
                                v3 = v23 + 8;
                                goto label_84;
                            }
                            v11 = v23;
                            break;
                        }
                        case 1: {
                            arr_b2 = arr_b1;
                            zzjn2 = zzjn1;
                            v23 = v11;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            object3 = object1;
                            unsafe2 = unsafe0;
                            if((v10 & 7) == 5) {
                                zznu.zzp(object3, v18, Float.intBitsToFloat(zzjo.zzb(arr_b2, v23)));
                                v3 = v23 + 4;
                            label_84:
                                v27 = v25 | v22;
                                zzjn1 = zzjn2;
                                arr_b1 = arr_b2;
                                v5 = v15;
                                goto label_101;
                            }
                            v11 = v23;
                            break;
                        }
                        case 2: 
                        case 3: {
                            v23 = v11;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 0) {
                                int v28 = zzjo.zzm(arr_b1, v23, zzjn1);
                                unsafe0.putLong(object1, v18, zzjn1.zzb);
                                unsafe2 = unsafe0;
                                object3 = object1;
                                v27 = v25 | v22;
                                v5 = v15;
                                v3 = v28;
                            label_101:
                                unsafe0 = unsafe2;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                v8 = v27;
                                object1 = object3;
                                v7 = v24;
                                continue;
                            }
                            else {
                                object3 = object1;
                                unsafe2 = unsafe0;
                            }
                            v11 = v23;
                            break;
                        }
                        case 7: {
                            object5 = object1;
                            v24 = v7;
                            v25 = v8;
                            zzjn3 = zzjn1;
                            v15 = v12;
                            arr_b3 = arr_b1;
                            unsafe3 = unsafe0;
                            if((v10 & 7) == 0) {
                                v3 = zzjo.zzm(arr_b3, v11, zzjn3);
                                zznu.zzm(object5, v18, zzjn3.zzb != 0L);
                                goto label_173;
                            }
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 8: {
                            object5 = object1;
                            v24 = v7;
                            v25 = v8;
                            zzjn3 = zzjn1;
                            v15 = v12;
                            arr_b3 = arr_b1;
                            unsafe3 = unsafe0;
                            if((v10 & 7) == 2) {
                                v3 = (0x20000000 & v16) == 0 ? zzjo.zzg(arr_b3, v11, zzjn3) : zzjo.zzh(arr_b3, v11, zzjn3);
                                unsafe3.putObject(object5, v18, zzjn3.zzc);
                            label_173:
                                v8 = v25 | v22;
                                v7 = v24;
                                unsafe0 = unsafe3;
                                v5 = v15;
                                arr_b1 = arr_b3;
                                v6 = v10;
                                zzjn1 = zzjn3;
                                object1 = object5;
                                v4 = v10 >>> 3;
                                continue;
                            }
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 9: {
                            object6 = object1;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 2) {
                                Object object7 = zzml0.zzD(object6, v15);
                                int v30 = zzjo.zzo(object7, zzml0.zzB(v15), arr_b1, v11, v1, zzjn1);
                                zzml0.zzL(object6, v15, object7);
                                v8 = v25 | v22;
                                v7 = v24;
                                v3 = v30;
                                v5 = v15;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                object1 = object6;
                                continue;
                            }
                            else {
                                unsafe2 = unsafe0;
                                object3 = object6;
                                break;
                            }
                            goto label_204;
                        }
                        case 10: {
                        label_204:
                            object6 = object1;
                            unsafe4 = unsafe0;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 2) {
                                v3 = zzjo.zza(arr_b1, v11, zzjn1);
                                unsafe4.putObject(object6, v18, zzjn1.zzc);
                                goto label_238;
                            }
                            unsafe2 = unsafe4;
                            object3 = object6;
                            break;
                        }
                        case 4: 
                        case 11: {
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 0) {
                                int v29 = zzjo.zzj(arr_b1, v11, zzjn1);
                                unsafe0.putInt(object1, v18, zzjn1.zza);
                                v8 = v25 | v22;
                                v7 = v24;
                                v3 = v29;
                                v5 = v15;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                continue;
                            }
                            else {
                                object3 = object1;
                                unsafe2 = unsafe0;
                                break;
                            }
                            goto label_126;
                        }
                        case 12: {
                            object6 = object1;
                            unsafe4 = unsafe0;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 0) {
                                v3 = zzjo.zzj(arr_b1, v11, zzjn1);
                                int v31 = zzjn1.zza;
                                zzlf zzlf0 = zzml0.zzA(v15);
                                if(zzlf0 == null || zzlf0.zza(v31)) {
                                    unsafe4.putInt(object6, v18, v31);
                                    goto label_238;
                                }
                                else {
                                    zzml.zzd(object6).zzj(v10, ((long)v31));
                                    v7 = v24;
                                    unsafe0 = unsafe4;
                                    v8 = v25;
                                    goto label_255;
                                }
                                goto label_230;
                            }
                            unsafe2 = unsafe4;
                            object3 = object6;
                            break;
                        }
                        case 6: 
                        case 13: {
                        label_141:
                            object5 = object1;
                            v24 = v7;
                            v25 = v8;
                            zzjn3 = zzjn1;
                            v15 = v12;
                            arr_b3 = arr_b1;
                            unsafe3 = unsafe0;
                            if((v10 & 7) == 5) {
                                unsafe3.putInt(object5, v18, zzjo.zzb(arr_b3, v11));
                                v3 = v11 + 4;
                                goto label_173;
                            }
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 5: 
                        case 14: {
                        label_126:
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 1) {
                                unsafe0.putLong(object1, v18, zzjo.zzp(arr_b1, v11));
                                v3 = v11 + 8;
                                v8 = v25 | v22;
                                v7 = v24;
                                v5 = v15;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                continue;
                            }
                            else {
                                unsafe2 = unsafe0;
                                object3 = object1;
                                break;
                            }
                            goto label_141;
                        }
                        case 15: {
                        label_230:
                            object6 = object1;
                            unsafe4 = unsafe0;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            if((v10 & 7) == 0) {
                                v3 = zzjo.zzj(arr_b1, v11, zzjn1);
                                unsafe4.putInt(object6, v18, zzjn1.zza >>> 1 ^ -(zzjn1.zza & 1));
                            label_238:
                                v8 = v25 | v22;
                                v7 = v24;
                                unsafe0 = unsafe4;
                                v5 = v15;
                                object1 = object6;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                continue;
                            }
                            unsafe2 = unsafe4;
                            object3 = object6;
                            break;
                        }
                        case 16: {
                            if((v10 & 7) == 0) {
                                int v32 = zzjo.zzm(arr_b1, v11, zzjn1);
                                v15 = v12;
                                unsafe0.putLong(object1, v18, zzjn1.zzb >>> 1 ^ -(1L & zzjn1.zzb));
                                object6 = object1;
                                v8 |= v22;
                                v3 = v32;
                            label_255:
                                v5 = v15;
                                object1 = object6;
                                v6 = v10;
                                v4 = v10 >>> 3;
                                continue;
                            }
                            else {
                                object6 = object1;
                                v15 = v12;
                                v24 = v7;
                                v25 = v8;
                                unsafe2 = unsafe0;
                            }
                            object3 = object6;
                            break;
                        }
                        default: {
                            v23 = v11;
                            v24 = v7;
                            v25 = v8;
                            v15 = v12;
                            object3 = object1;
                            unsafe2 = unsafe0;
                            if((v10 & 7) == 3) {
                                Object object4 = zzml0.zzD(object3, v15);
                                int v26 = zzjo.zzn(object4, zzml0.zzB(v15), arr_b1, v23, v1, v10 >>> 3 << 3 | 4, zzjn1);
                                zzml0.zzL(object3, v15, object4);
                                v27 = v25 | v22;
                                arr_b1 = arr_b;
                                zzjn1 = zzjn0;
                                v3 = v26;
                                v5 = v15;
                                goto label_101;
                            }
                            v11 = v23;
                            break;
                        }
                    }
                    zzjn1 = zzjn0;
                    v13 = v11;
                    object2 = object3;
                    unsafe1 = unsafe2;
                    v7 = v24;
                    v8 = v25;
                    zzml1 = zzml0;
                    v14 = v10;
                }
                else {
                    v15 = v12;
                    if(v17 == 27) {
                        if((v10 & 7) == 2) {
                            zzli zzli0 = (zzli)unsafe0.getObject(object1, v18);
                            if(!zzli0.zzc()) {
                                int v33 = zzli0.size();
                                zzli0 = zzli0.zzd((v33 == 0 ? 10 : v33 + v33));
                                unsafe0.putObject(object1, v18, zzli0);
                            }
                            v6 = v10;
                            arr_b1 = arr_b;
                            v3 = zzjo.zze(zzml0.zzB(v15), v10, arr_b, v11, v1, zzli0, zzjn0);
                            v5 = v15;
                            v4 = v10 >>> 3;
                            zzjn1 = zzjn0;
                            continue;
                        }
                        else {
                            v34 = v7;
                            v35 = v8;
                            unsafe1 = unsafe0;
                            v36 = v10;
                            goto label_344;
                        }
                        goto label_295;
                    }
                    else {
                    label_295:
                        if(v17 <= 49) {
                            v34 = v7;
                            v35 = v8;
                            unsafe1 = unsafe0;
                            int v37 = zzml0.zzs(object1, arr_b, v11, v1, v10, v10 >>> 3, v10 & 7, v15, ((long)v16), v17, v18, zzjn0);
                            v36 = v10;
                            if(v37 == v11) {
                                zzml1 = this;
                                object2 = object0;
                                zzjn1 = zzjn0;
                                v13 = v37;
                                goto label_348;
                            }
                            else {
                                zzml0 = this;
                                object1 = object0;
                                zzjn1 = zzjn0;
                                v3 = v37;
                                v5 = v15;
                                v6 = v36;
                                v7 = v34;
                                v8 = v35;
                                v4 = v10 >>> 3;
                                unsafe0 = unsafe1;
                                arr_b1 = arr_b;
                                continue;
                            }
                            goto label_319;
                        }
                        else {
                        label_319:
                            v34 = v7;
                            v35 = v8;
                            unsafe1 = unsafe0;
                            v36 = v10;
                            if(v17 == 50) {
                                if((v10 & 7) == 2) {
                                    int v38 = this.zzq(object0, arr_b, v11, v1, v15, v18, zzjn0);
                                    if(v38 == v11) {
                                        zzml1 = this;
                                        object2 = object0;
                                        zzjn1 = zzjn0;
                                        v13 = v38;
                                    }
                                    else {
                                        zzml0 = this;
                                        object1 = object0;
                                        arr_b1 = arr_b;
                                        zzjn1 = zzjn0;
                                        v3 = v38;
                                        v5 = v15;
                                        v6 = v36;
                                        v7 = v34;
                                        v8 = v35;
                                        v4 = v10 >>> 3;
                                        unsafe0 = unsafe1;
                                        continue;
                                    }
                                }
                                else {
                                label_344:
                                    zzml1 = this;
                                    object2 = object0;
                                    zzjn1 = zzjn0;
                                    v13 = v11;
                                }
                            label_348:
                                v14 = v36;
                                v7 = v34;
                                v8 = v35;
                            }
                            else {
                                zzjn1 = zzjn0;
                                int v39 = this.zzr(object0, arr_b, v11, v1, v36, v10 >>> 3, v10 & 7, v16, v17, v18, v15, zzjn1);
                                zzml1 = this;
                                object2 = object0;
                                v14 = v36;
                                if(v39 == v11) {
                                    v13 = v39;
                                    v7 = v34;
                                    v8 = v35;
                                }
                                else {
                                    v4 = v10 >>> 3;
                                    v3 = v39;
                                    v5 = v15;
                                    object1 = object2;
                                    v7 = v34;
                                    v8 = v35;
                                    unsafe0 = unsafe1;
                                    arr_b1 = arr_b;
                                    v6 = v14;
                                    zzml0 = zzml1;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            if(v14 == v2 && v2 != 0) {
                v6 = v14;
                v3 = v13;
                break;
            }
            if(zzml1.zzh) {
                zzkn zzkn0 = zzjn1.zzd;
                if(zzkn0 != zzkn.zza) {
                    if(zzkn0.zzb(zzml1.zzg, v10 >>> 3) == null) {
                        v40 = zzjo.zzi(v14, arr_b, v13, v1, zzml.zzd(object2), zzjn1);
                        goto label_385;
                    }
                    zzky zzky0 = (zzky)object2;
                    throw null;
                }
            }
            v40 = zzjo.zzi(v14, arr_b, v13, v1, zzml.zzd(object2), zzjn0);
        label_385:
            v3 = v40;
            v6 = v14;
            zzml0 = zzml1;
            v4 = v10 >>> 3;
            arr_b1 = arr_b;
            zzjn1 = zzjn0;
            v5 = v15;
            object1 = object2;
            unsafe0 = unsafe1;
        }
        if(v7 != 0xFFFFF) {
            unsafe1.putInt(object2, ((long)v7), v8);
        }
        int v41 = zzml1.zzk;
        while(v41 < zzml1.zzl) {
            int v42 = zzml1.zzj[v41];
            int v43 = zzml1.zzc[v42];
            Object object8 = zznu.zzf(object2, ((long)(zzml1.zzy(v42) & 0xFFFFF)));
            if(object8 == null || zzml1.zzA(v42) == null) {
                ++v41;
                continue;
            }
            zzmc zzmc0 = (zzmc)object8;
            zzmb zzmb0 = (zzmb)zzml1.zzC(v42);
            throw null;
        }
        if(v2 == 0) {
            if(v3 != v1) {
                throw zzll.zze();
            }
            return v3;
        }
        if(v3 > v1 || v6 != v2) {
            throw zzll.zze();
        }
        return v3;
    }

    static zznl zzd(Object object0) {
        zznl zznl0 = ((zzlb)object0).zzc;
        if(zznl0 == zznl.zzc()) {
            zznl0 = zznl.zzf();
            ((zzlb)object0).zzc = zznl0;
        }
        return zznl0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final Object zze() {
        return ((zzlb)this.zzg).zzbD();
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzf(Object object0) {
        if(zzml.zzS(object0)) {
            if(object0 instanceof zzlb) {
                ((zzlb)object0).zzbP(0x7FFFFFFF);
                ((zzlb)object0).zzb = 0;
                ((zzlb)object0).zzbN();
            }
            for(int v = 0; v < this.zzc.length; v += 3) {
                int v1 = this.zzy(v);
                long v2 = (long)(0xFFFFF & v1);
                switch(v1 >>> 20 & 0xFF) {
                    case 9: 
                    case 17: {
                        if(this.zzP(object0, v)) {
                            this.zzB(v).zzf(zzml.zzb.getObject(object0, v2));
                        }
                        break;
                    }
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: 
                    case 24: 
                    case 25: 
                    case 26: 
                    case 27: 
                    case 28: 
                    case 29: 
                    case 30: 
                    case 0x1F: 
                    case 0x20: 
                    case 33: 
                    case 34: 
                    case 35: 
                    case 36: 
                    case 37: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 42: 
                    case 43: 
                    case 44: 
                    case 45: 
                    case 46: 
                    case 0x2F: 
                    case 0x30: 
                    case 49: {
                        this.zzm.zza(object0, v2);
                        break;
                    }
                    case 50: {
                        Unsafe unsafe0 = zzml.zzb;
                        Object object1 = unsafe0.getObject(object0, v2);
                        if(object1 != null) {
                            ((zzmc)object1).zzc();
                            unsafe0.putObject(object0, v2, object1);
                        }
                        break;
                    }
                    case 60: 
                    case 68: {
                        if(this.zzT(object0, this.zzc[v], v)) {
                            this.zzB(v).zzf(zzml.zzb.getObject(object0, v2));
                        }
                    }
                }
            }
            this.zzn.zzg(object0);
            if(this.zzh) {
                this.zzo.zzb(object0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzg(Object object0, Object object1) {
        zzml.zzG(object0);
        object1.getClass();
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v1 = this.zzy(v);
            int v2 = this.zzc[v];
            long v3 = (long)(0xFFFFF & v1);
            switch(v1 >>> 20 & 0xFF) {
                case 0: {
                    if(this.zzP(object1, v)) {
                        zznu.zzo(object0, v3, zznu.zza(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 1: {
                    if(this.zzP(object1, v)) {
                        zznu.zzp(object0, v3, zznu.zzb(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 2: {
                    if(this.zzP(object1, v)) {
                        zznu.zzr(object0, v3, zznu.zzd(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 3: {
                    if(this.zzP(object1, v)) {
                        zznu.zzr(object0, v3, zznu.zzd(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 4: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 5: {
                    if(this.zzP(object1, v)) {
                        zznu.zzr(object0, v3, zznu.zzd(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 6: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 7: {
                    if(this.zzP(object1, v)) {
                        zznu.zzm(object0, v3, zznu.zzw(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 8: {
                    if(this.zzP(object1, v)) {
                        zznu.zzs(object0, v3, zznu.zzf(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 9: {
                    this.zzH(object0, object1, v);
                    break;
                }
                case 10: {
                    if(this.zzP(object1, v)) {
                        zznu.zzs(object0, v3, zznu.zzf(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 11: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 12: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 13: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 14: {
                    if(this.zzP(object1, v)) {
                        zznu.zzr(object0, v3, zznu.zzd(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 15: {
                    if(this.zzP(object1, v)) {
                        zznu.zzq(object0, v3, zznu.zzc(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 16: {
                    if(this.zzP(object1, v)) {
                        zznu.zzr(object0, v3, zznu.zzd(object1, v3));
                        this.zzJ(object0, v);
                    }
                    break;
                }
                case 17: {
                    this.zzH(object0, object1, v);
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    this.zzm.zzb(object0, object1, v3);
                    break;
                }
                case 50: {
                    zznu.zzs(object0, v3, zzmd.zzb(zznu.zzf(object0, v3), zznu.zzf(object1, v3)));
                    break;
                }
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 59: {
                    if(this.zzT(object1, v2, v)) {
                        zznu.zzs(object0, v3, zznu.zzf(object1, v3));
                        this.zzK(object0, v2, v);
                    }
                    break;
                }
                case 60: {
                    this.zzI(object0, object1, v);
                    break;
                }
                case 61: 
                case 62: 
                case 0x3F: 
                case 0x40: 
                case 65: 
                case 66: 
                case 67: {
                    if(this.zzT(object1, v2, v)) {
                        zznu.zzs(object0, v3, zznu.zzf(object1, v3));
                        this.zzK(object0, v2, v);
                    }
                    break;
                }
                case 68: {
                    this.zzI(object0, object1, v);
                }
            }
        }
        zzmv.zzB(this.zzn, object0, object1);
        if(!this.zzh) {
            return;
        }
        this.zzo.zza(object1);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzh(Object object0, byte[] arr_b, int v, int v1, zzjn zzjn0) throws IOException {
        int v32;
        int v31;
        Object object4;
        int v26;
        Unsafe unsafe3;
        Unsafe unsafe4;
        int v27;
        int v23;
        Unsafe unsafe2;
        Object object3;
        int v22;
        int v21;
        int v13;
        int v12;
        int v11;
        int v10;
        Unsafe unsafe1;
        Object object2;
        Object object1 = object0;
        if(this.zzi) {
            zzml.zzG(object1);
            Unsafe unsafe0 = zzml.zzb;
            int v2 = v;
            int v3 = -1;
            int v4 = 0;
            int v5 = 0xFFFFF;
            int v6 = 0;
            while(v2 < v1) {
                int v7 = v2 + 1;
                int v8 = arr_b[v2];
                if(v8 < 0) {
                    v7 = zzjo.zzk(v8, arr_b, v7, zzjn0);
                    v8 = zzjn0.zza;
                }
                int v9 = v8 >>> 3 <= v3 ? this.zzt(v8 >>> 3) : this.zzu(v8 >>> 3, v4 / 3);
                if(v9 == -1) {
                    object2 = object1;
                    unsafe1 = unsafe0;
                    v10 = v8;
                    v11 = v7;
                    v12 = v8 >>> 3;
                    v13 = 0;
                }
                else {
                    int v14 = this.zzc[v9 + 1];
                    int v15 = v14 >>> 20 & 0xFF;
                    int v16 = v14;
                    long v17 = (long)(v14 & 0xFFFFF);
                    int[] arr_v = this.zzc;
                    if(v15 <= 17) {
                        int v18 = arr_v[v9 + 2];
                        int v19 = 1 << (v18 >>> 20);
                        int v20 = v18 & 0xFFFFF;
                        if(v20 == v5) {
                            v21 = v7;
                        }
                        else {
                            v21 = v7;
                            if(v5 != 0xFFFFF) {
                                unsafe0.putInt(object1, ((long)v5), v6);
                            }
                            if(v20 != 0xFFFFF) {
                                v6 = unsafe0.getInt(object1, ((long)v20));
                            }
                            v5 = v20;
                        }
                        switch(v15) {
                            case 0: {
                                v12 = v8 >>> 3;
                                v22 = v21;
                                object3 = object1;
                                unsafe2 = unsafe0;
                                v23 = v9;
                                if((v8 & 7) == 1) {
                                    zznu.zzo(object3, v17, Double.longBitsToDouble(zzjo.zzp(arr_b, v22)));
                                    v2 = v22 + 8;
                                    goto label_61;
                                }
                                break;
                            }
                            case 1: {
                                v12 = v8 >>> 3;
                                v22 = v21;
                                object3 = object1;
                                unsafe2 = unsafe0;
                                v23 = v9;
                                if((v8 & 7) == 5) {
                                    zznu.zzp(object3, v17, Float.intBitsToFloat(zzjo.zzb(arr_b, v22)));
                                    v2 = v22 + 4;
                                label_61:
                                    v6 |= v19;
                                    unsafe0 = unsafe2;
                                    v4 = v23;
                                    object1 = object3;
                                    v3 = v12;
                                    continue;
                                }
                                break;
                            }
                            case 2: 
                            case 3: {
                                v22 = v21;
                                v23 = v9;
                                if((v8 & 7) == 0) {
                                    int v24 = zzjo.zzm(arr_b, v22, zzjn0);
                                    unsafe0.putLong(object1, v17, zzjn0.zzb);
                                    v6 |= v19;
                                    v4 = v23;
                                    v3 = v8 >>> 3;
                                    v2 = v24;
                                    continue;
                                }
                                else {
                                    object3 = object1;
                                    unsafe2 = unsafe0;
                                    v12 = v8 >>> 3;
                                    break;
                                }
                                goto label_80;
                            }
                            case 7: {
                                v26 = v21;
                                v23 = v9;
                                object4 = object1;
                                unsafe3 = unsafe0;
                                if((v8 & 7) == 0) {
                                    v2 = zzjo.zzm(arr_b, v26, zzjn0);
                                    zznu.zzm(object4, v17, zzjn0.zzb != 0L);
                                    v6 |= v19;
                                    goto label_149;
                                }
                                goto label_134;
                            }
                            case 8: {
                                v26 = v21;
                                v23 = v9;
                                object4 = object1;
                                unsafe3 = unsafe0;
                                if((v8 & 7) == 2) {
                                    v2 = (v16 & 0x20000000) == 0 ? zzjo.zzg(arr_b, v26, zzjn0) : zzjo.zzh(arr_b, v26, zzjn0);
                                    unsafe3.putObject(object4, v17, zzjn0.zzc);
                                    v6 |= v19;
                                    goto label_149;
                                }
                            label_134:
                                v12 = v8 >>> 3;
                                unsafe2 = unsafe3;
                                object3 = object4;
                                v22 = v26;
                                break;
                            }
                            case 9: {
                                object4 = object1;
                                unsafe3 = unsafe0;
                                v27 = v21;
                                v23 = v9;
                                if((v8 & 7) == 2) {
                                    Object object5 = this.zzD(object4, v23);
                                    int v28 = zzjo.zzo(object5, this.zzB(v23), arr_b, v27, v1, zzjn0);
                                    this.zzL(object4, v23, object5);
                                    v6 |= v19;
                                    v2 = v28;
                                label_149:
                                    v4 = v23;
                                    v3 = v8 >>> 3;
                                    object1 = object4;
                                    unsafe0 = unsafe3;
                                    continue;
                                }
                                else {
                                    v12 = v8 >>> 3;
                                    unsafe2 = unsafe3;
                                    object3 = object4;
                                    v22 = v27;
                                    break;
                                }
                                goto label_157;
                            }
                            case 10: {
                            label_157:
                                v27 = v21;
                                object4 = object1;
                                unsafe4 = unsafe0;
                                if((v8 & 7) == 2) {
                                    v2 = zzjo.zza(arr_b, v27, zzjn0);
                                    unsafe4.putObject(object4, v17, zzjn0.zzc);
                                    v6 |= v19;
                                    goto label_194;
                                }
                                goto label_181;
                            }
                            case 4: 
                            case 11: {
                            label_80:
                                unsafe3 = unsafe0;
                                v22 = v21;
                                v23 = v9;
                                if((v8 & 7) == 0) {
                                    int v25 = zzjo.zzj(arr_b, v22, zzjn0);
                                    unsafe3.putInt(object1, v17, zzjn0.zza);
                                    v6 |= v19;
                                    v2 = v25;
                                    v4 = v23;
                                    v3 = v8 >>> 3;
                                    unsafe0 = unsafe3;
                                    continue;
                                }
                                else {
                                    object3 = object1;
                                    unsafe2 = unsafe3;
                                    v12 = v8 >>> 3;
                                    break;
                                }
                                goto label_94;
                            }
                            case 12: {
                                v27 = v21;
                                object4 = object1;
                                unsafe4 = unsafe0;
                                if((v8 & 7) == 0) {
                                    v2 = zzjo.zzj(arr_b, v27, zzjn0);
                                    unsafe4.putInt(object4, v17, zzjn0.zza);
                                    v6 |= v19;
                                    goto label_194;
                                }
                                goto label_181;
                            }
                            case 6: 
                            case 13: {
                            label_107:
                                v26 = v21;
                                v23 = v9;
                                object4 = object1;
                                unsafe3 = unsafe0;
                                if((v8 & 7) == 5) {
                                    unsafe3.putInt(object4, v17, zzjo.zzb(arr_b, v26));
                                    v2 = v26 + 4;
                                    v6 |= v19;
                                    goto label_149;
                                }
                                goto label_134;
                            }
                            case 5: 
                            case 14: {
                            label_94:
                                v23 = v9;
                                unsafe3 = unsafe0;
                                if((v8 & 7) == 1) {
                                    unsafe3.putLong(object1, v17, zzjo.zzp(arr_b, v21));
                                    v2 = v21 + 8;
                                    v6 |= v19;
                                    v4 = v23;
                                    v3 = v8 >>> 3;
                                    unsafe0 = unsafe3;
                                    continue;
                                }
                                else {
                                    v22 = v21;
                                    object3 = object1;
                                    unsafe2 = unsafe3;
                                    v12 = v8 >>> 3;
                                    break;
                                }
                                goto label_107;
                            }
                            case 15: {
                                v27 = v21;
                                object4 = object1;
                                unsafe4 = unsafe0;
                                if((v8 & 7) == 0) {
                                    v2 = zzjo.zzj(arr_b, v27, zzjn0);
                                    unsafe4.putInt(object4, v17, zzjn0.zza >>> 1 ^ -(zzjn0.zza & 1));
                                    v6 |= v19;
                                    goto label_194;
                                }
                            label_181:
                                v12 = v8 >>> 3;
                                unsafe2 = unsafe4;
                                v23 = v9;
                                object3 = object4;
                                v22 = v27;
                                break;
                            }
                            case 16: {
                                if((v8 & 7) == 0) {
                                    int v29 = zzjo.zzm(arr_b, v21, zzjn0);
                                    unsafe0.putLong(object1, v17, zzjn0.zzb >>> 1 ^ -(1L & zzjn0.zzb));
                                    unsafe4 = unsafe0;
                                    object4 = object1;
                                    v6 |= v19;
                                    v2 = v29;
                                label_194:
                                    v3 = v8 >>> 3;
                                    object1 = object4;
                                    unsafe0 = unsafe4;
                                    v4 = v9;
                                    continue;
                                }
                            label_199:
                                object3 = object1;
                                unsafe2 = unsafe0;
                                v22 = v21;
                                v23 = v9;
                                v12 = v8 >>> 3;
                                break;
                            }
                            default: {
                                goto label_199;
                            }
                        }
                        v10 = v8;
                        unsafe1 = unsafe2;
                        v13 = v23;
                        v11 = v22;
                        object2 = object3;
                    }
                    else {
                        v12 = v8 >>> 3;
                        if(v15 == 27) {
                            if((v8 & 7) == 2) {
                                zzli zzli0 = (zzli)unsafe0.getObject(object1, v17);
                                if(!zzli0.zzc()) {
                                    int v30 = zzli0.size();
                                    zzli0 = zzli0.zzd((v30 == 0 ? 10 : v30 + v30));
                                    unsafe0.putObject(object1, v17, zzli0);
                                }
                                v2 = zzjo.zze(this.zzB(v9), v8, arr_b, v7, v1, zzli0, zzjn0);
                                v3 = v12;
                                v4 = v9;
                                continue;
                            }
                            else {
                                unsafe1 = unsafe0;
                                v13 = v9;
                                v31 = v5;
                                v32 = v6;
                                object2 = object0;
                                v11 = v7;
                                v10 = v8;
                                goto label_279;
                            }
                            goto label_230;
                        }
                        else {
                        label_230:
                            v13 = v9;
                            if(v15 <= 49) {
                                v32 = v6;
                                unsafe1 = unsafe0;
                                v31 = v5;
                                int v33 = this.zzs(object1, arr_b, v7, v1, v8, v12, v8 & 7, v13, ((long)v16), v15, v17, zzjn0);
                                if(v33 == v7) {
                                    object2 = object0;
                                    v11 = v33;
                                    v10 = v8;
                                    goto label_279;
                                }
                                else {
                                    object1 = object0;
                                    v2 = v33;
                                    goto label_272;
                                }
                                goto label_244;
                            }
                            else {
                            label_244:
                                v31 = v5;
                                v32 = v6;
                                unsafe1 = unsafe0;
                                if(v15 == 50) {
                                    if((v8 & 7) == 2) {
                                        int v34 = this.zzq(object0, arr_b, v7, v1, v13, v17, zzjn0);
                                        if(v34 == v7) {
                                            object2 = object0;
                                            v11 = v34;
                                            goto label_264;
                                        }
                                        else {
                                            object1 = object0;
                                            v2 = v34;
                                            v5 = v31;
                                            v3 = v12;
                                            v4 = v13;
                                            v6 = v32;
                                            unsafe0 = unsafe1;
                                            continue;
                                        }
                                        goto label_262;
                                    }
                                    else {
                                    label_262:
                                        object2 = object0;
                                        v11 = v7;
                                    }
                                label_264:
                                    v10 = v8;
                                }
                                else {
                                    v10 = v8;
                                    int v35 = this.zzr(object0, arr_b, v7, v1, v10, v12, v8 & 7, v16, v15, v17, v13, zzjn0);
                                    object2 = object0;
                                    if(v35 == v7) {
                                        v11 = v35;
                                    }
                                    else {
                                        v2 = v35;
                                        object1 = object2;
                                    label_272:
                                        v5 = v31;
                                        v3 = v12;
                                        v4 = v13;
                                        v6 = v32;
                                        unsafe0 = unsafe1;
                                        continue;
                                    }
                                }
                            }
                        }
                    label_279:
                        v5 = v31;
                        v6 = v32;
                    }
                }
                object1 = object2;
                v3 = v12;
                v4 = v13;
                unsafe0 = unsafe1;
                v2 = zzjo.zzi(v10, arr_b, v11, v1, zzml.zzd(object2), zzjn0);
            }
            if(v5 != 0xFFFFF) {
                unsafe0.putInt(object1, ((long)v5), v6);
            }
            if(v2 != v1) {
                throw zzll.zze();
            }
            return;
        }
        this.zzc(object1, arr_b, v, v1, 0, zzjn0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final void zzi(Object object0, zzoc zzoc0) throws IOException {
        int v11;
        if(this.zzi) {
            if(!this.zzh) {
                for(int v = 0; v < this.zzc.length; v += 3) {
                    int v1 = this.zzy(v);
                    int v2 = this.zzc[v];
                    switch(v1 >>> 20 & 0xFF) {
                        case 0: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzf(v2, zznu.zza(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 1: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzo(v2, zznu.zzb(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 2: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzt(v2, zznu.zzd(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 3: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzJ(v2, zznu.zzd(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 4: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzr(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 5: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzm(v2, zznu.zzd(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 6: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzk(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 7: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzb(v2, zznu.zzw(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 8: {
                            if(this.zzP(object0, v)) {
                                zzml.zzV(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), zzoc0);
                            }
                            break;
                        }
                        case 9: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzv(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zzB(v));
                            }
                            break;
                        }
                        case 10: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzd(v2, ((zzka)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))));
                            }
                            break;
                        }
                        case 11: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzH(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 12: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzi(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 13: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzw(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 14: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzy(v2, zznu.zzd(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 15: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzA(v2, zznu.zzc(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 16: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzC(v2, zznu.zzd(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 17: {
                            if(this.zzP(object0, v)) {
                                zzoc0.zzq(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zzB(v));
                            }
                            break;
                        }
                        case 18: {
                            zzmv.zzF(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 19: {
                            zzmv.zzJ(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 20: {
                            zzmv.zzM(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 21: {
                            zzmv.zzU(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 22: {
                            zzmv.zzL(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 23: {
                            zzmv.zzI(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 24: {
                            zzmv.zzH(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 25: {
                            zzmv.zzD(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 26: {
                            zzmv.zzS(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0);
                            break;
                        }
                        case 27: {
                            zzmv.zzN(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, this.zzB(v));
                            break;
                        }
                        case 28: {
                            zzmv.zzE(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0);
                            break;
                        }
                        case 29: {
                            zzmv.zzT(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 30: {
                            zzmv.zzG(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 0x1F: {
                            zzmv.zzO(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 0x20: {
                            zzmv.zzP(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 33: {
                            zzmv.zzQ(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 34: {
                            zzmv.zzR(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, false);
                            break;
                        }
                        case 35: {
                            zzmv.zzF(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 36: {
                            zzmv.zzJ(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 37: {
                            zzmv.zzM(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 38: {
                            zzmv.zzU(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 39: {
                            zzmv.zzL(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 40: {
                            zzmv.zzI(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 41: {
                            zzmv.zzH(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 42: {
                            zzmv.zzD(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 43: {
                            zzmv.zzT(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 44: {
                            zzmv.zzG(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 45: {
                            zzmv.zzO(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 46: {
                            zzmv.zzP(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 0x2F: {
                            zzmv.zzQ(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 0x30: {
                            zzmv.zzR(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, true);
                            break;
                        }
                        case 49: {
                            zzmv.zzK(v2, ((List)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzoc0, this.zzB(v));
                            break;
                        }
                        case 50: {
                            this.zzN(zzoc0, v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), v);
                            break;
                        }
                        case 51: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzf(v2, zzml.zzm(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 52: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzo(v2, zzml.zzn(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 53: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzt(v2, zzml.zzz(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 54: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzJ(v2, zzml.zzz(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 55: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzr(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 56: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzm(v2, zzml.zzz(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 57: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzk(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 58: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzb(v2, zzml.zzU(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 59: {
                            if(this.zzT(object0, v2, v)) {
                                zzml.zzV(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), zzoc0);
                            }
                            break;
                        }
                        case 60: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzv(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zzB(v));
                            }
                            break;
                        }
                        case 61: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzd(v2, ((zzka)zznu.zzf(object0, ((long)(v1 & 0xFFFFF)))));
                            }
                            break;
                        }
                        case 62: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzH(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 0x3F: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzi(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 0x40: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzw(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 65: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzy(v2, zzml.zzz(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 66: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzA(v2, zzml.zzp(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 67: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzC(v2, zzml.zzz(object0, ((long)(v1 & 0xFFFFF))));
                            }
                            break;
                        }
                        case 68: {
                            if(this.zzT(object0, v2, v)) {
                                zzoc0.zzq(v2, zznu.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zzB(v));
                            }
                        }
                    }
                }
                Object object1 = this.zzn.zzd(object0);
                this.zzn.zzi(object1, zzoc0);
                return;
            }
            this.zzo.zza(object0);
            throw null;
        }
        if(!this.zzh) {
            Unsafe unsafe0 = zzml.zzb;
            int v4 = 0xFFFFF;
            int v5 = 0;
            for(int v3 = 0; v3 < this.zzc.length; v3 += 3) {
                int v6 = this.zzy(v3);
                int[] arr_v = this.zzc;
                int v7 = arr_v[v3];
                int v8 = v6 >>> 20 & 0xFF;
                if(v8 <= 17) {
                    int v9 = arr_v[v3 + 2];
                    int v10 = v9 & 0xFFFFF;
                    if(v10 != v4) {
                        v5 = unsafe0.getInt(object0, ((long)v10));
                        v4 = v10;
                    }
                    v11 = 1 << (v9 >>> 20);
                }
                else {
                    v11 = 0;
                }
                long v12 = (long)(v6 & 0xFFFFF);
                switch(v8) {
                    case 0: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzf(v7, zznu.zza(object0, v12));
                        }
                        break;
                    }
                    case 1: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzo(v7, zznu.zzb(object0, v12));
                        }
                        break;
                    }
                    case 2: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzt(v7, unsafe0.getLong(object0, v12));
                        }
                        break;
                    }
                    case 3: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzJ(v7, unsafe0.getLong(object0, v12));
                        }
                        break;
                    }
                    case 4: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzr(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 5: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzm(v7, unsafe0.getLong(object0, v12));
                        }
                        break;
                    }
                    case 6: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzk(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 7: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzb(v7, zznu.zzw(object0, v12));
                        }
                        break;
                    }
                    case 8: {
                        if((v5 & v11) != 0) {
                            zzml.zzV(v7, unsafe0.getObject(object0, v12), zzoc0);
                        }
                        break;
                    }
                    case 9: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzv(v7, unsafe0.getObject(object0, v12), this.zzB(v3));
                        }
                        break;
                    }
                    case 10: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzd(v7, ((zzka)unsafe0.getObject(object0, v12)));
                        }
                        break;
                    }
                    case 11: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzH(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 12: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzi(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 13: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzw(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 14: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzy(v7, unsafe0.getLong(object0, v12));
                        }
                        break;
                    }
                    case 15: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzA(v7, unsafe0.getInt(object0, v12));
                        }
                        break;
                    }
                    case 16: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzC(v7, unsafe0.getLong(object0, v12));
                        }
                        break;
                    }
                    case 17: {
                        if((v5 & v11) != 0) {
                            zzoc0.zzq(v7, unsafe0.getObject(object0, v12), this.zzB(v3));
                        }
                        break;
                    }
                    case 18: {
                        zzmv.zzF(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 19: {
                        zzmv.zzJ(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 20: {
                        zzmv.zzM(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 21: {
                        zzmv.zzU(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 22: {
                        zzmv.zzL(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 23: {
                        zzmv.zzI(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 24: {
                        zzmv.zzH(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 25: {
                        zzmv.zzD(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 26: {
                        zzmv.zzS(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0);
                        break;
                    }
                    case 27: {
                        zzmv.zzN(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, this.zzB(v3));
                        break;
                    }
                    case 28: {
                        zzmv.zzE(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0);
                        break;
                    }
                    case 29: {
                        zzmv.zzT(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 30: {
                        zzmv.zzG(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 0x1F: {
                        zzmv.zzO(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 0x20: {
                        zzmv.zzP(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 33: {
                        zzmv.zzQ(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 34: {
                        zzmv.zzR(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, false);
                        break;
                    }
                    case 35: {
                        zzmv.zzF(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 36: {
                        zzmv.zzJ(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 37: {
                        zzmv.zzM(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 38: {
                        zzmv.zzU(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 39: {
                        zzmv.zzL(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 40: {
                        zzmv.zzI(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 41: {
                        zzmv.zzH(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 42: {
                        zzmv.zzD(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 43: {
                        zzmv.zzT(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 44: {
                        zzmv.zzG(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 45: {
                        zzmv.zzO(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 46: {
                        zzmv.zzP(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 0x2F: {
                        zzmv.zzQ(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 0x30: {
                        zzmv.zzR(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, true);
                        break;
                    }
                    case 49: {
                        zzmv.zzK(this.zzc[v3], ((List)unsafe0.getObject(object0, v12)), zzoc0, this.zzB(v3));
                        break;
                    }
                    case 50: {
                        this.zzN(zzoc0, v7, unsafe0.getObject(object0, v12), v3);
                        break;
                    }
                    case 51: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzf(v7, zzml.zzm(object0, v12));
                        }
                        break;
                    }
                    case 52: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzo(v7, zzml.zzn(object0, v12));
                        }
                        break;
                    }
                    case 53: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzt(v7, zzml.zzz(object0, v12));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzJ(v7, zzml.zzz(object0, v12));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzr(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzm(v7, zzml.zzz(object0, v12));
                        }
                        break;
                    }
                    case 57: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzk(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 58: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzb(v7, zzml.zzU(object0, v12));
                        }
                        break;
                    }
                    case 59: {
                        if(this.zzT(object0, v7, v3)) {
                            zzml.zzV(v7, unsafe0.getObject(object0, v12), zzoc0);
                        }
                        break;
                    }
                    case 60: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzv(v7, unsafe0.getObject(object0, v12), this.zzB(v3));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzd(v7, ((zzka)unsafe0.getObject(object0, v12)));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzH(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzi(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzw(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 65: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzy(v7, zzml.zzz(object0, v12));
                        }
                        break;
                    }
                    case 66: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzA(v7, zzml.zzp(object0, v12));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzC(v7, zzml.zzz(object0, v12));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zzT(object0, v7, v3)) {
                            zzoc0.zzq(v7, unsafe0.getObject(object0, v12), this.zzB(v3));
                        }
                    }
                }
            }
            Object object2 = this.zzn.zzd(object0);
            this.zzn.zzi(object2, zzoc0);
            return;
        }
        this.zzo.zza(object0);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final boolean zzj(Object object0, Object object1) {
        boolean z;
        int v = 0;
        while(v < this.zzc.length) {
            int v1 = this.zzy(v);
            long v2 = (long)(v1 & 0xFFFFF);
            switch(v1 >>> 20 & 0xFF) {
                case 0: {
                    if(!this.zzO(object0, object1, v) || Double.doubleToLongBits(zznu.zza(object0, v2)) != Double.doubleToLongBits(zznu.zza(object1, v2))) {
                        return false;
                    }
                    break;
                }
                case 1: {
                    if(this.zzO(object0, object1, v) && Float.floatToIntBits(zznu.zzb(object0, v2)) == Float.floatToIntBits(zznu.zzb(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 2: {
                    if(this.zzO(object0, object1, v) && zznu.zzd(object0, v2) == zznu.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 3: {
                    if(this.zzO(object0, object1, v) && zznu.zzd(object0, v2) == zznu.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 4: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 5: {
                    if(this.zzO(object0, object1, v) && zznu.zzd(object0, v2) == zznu.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 6: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 7: {
                    if(this.zzO(object0, object1, v) && zznu.zzw(object0, v2) == zznu.zzw(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 8: {
                    if(this.zzO(object0, object1, v) && zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 9: {
                    if(this.zzO(object0, object1, v) && zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 10: {
                    if(this.zzO(object0, object1, v) && zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 11: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 12: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 13: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 14: {
                    if(this.zzO(object0, object1, v) && zznu.zzd(object0, v2) == zznu.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 15: {
                    if(this.zzO(object0, object1, v) && zznu.zzc(object0, v2) == zznu.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 16: {
                    if(this.zzO(object0, object1, v) && zznu.zzd(object0, v2) == zznu.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 17: {
                    if(this.zzO(object0, object1, v) && zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    z = zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2));
                    goto label_45;
                }
                case 50: {
                    z = zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2));
                label_45:
                    if(!z) {
                        return false;
                    }
                    break;
                }
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 59: 
                case 60: 
                case 61: 
                case 62: 
                case 0x3F: 
                case 0x40: 
                case 65: 
                case 66: 
                case 67: 
                case 68: {
                    int v3 = this.zzv(v);
                    if(zznu.zzc(object0, ((long)(v3 & 0xFFFFF))) != zznu.zzc(object1, ((long)(v3 & 0xFFFFF))) || !zzmv.zzV(zznu.zzf(object0, v2), zznu.zzf(object1, v2))) {
                        return false;
                    }
                }
            }
            v += 3;
        }
        if(!this.zzn.zzd(object0).equals(this.zzn.zzd(object1))) {
            return false;
        }
        if(!this.zzh) {
            return true;
        }
        this.zzo.zza(object0);
        this.zzo.zza(object1);
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmt
    public final boolean zzk(Object object0) {
        int v11;
        int v10;
        int v9;
        int v = 0xFFFFF;
        int v2 = 0;
        for(int v1 = 0; v2 < this.zzk; v1 = v9) {
            int v3 = this.zzj[v2];
            int v4 = this.zzc[v3];
            int v5 = this.zzy(v3);
            int v6 = this.zzc[v3 + 2];
            int v7 = v6 & 0xFFFFF;
            int v8 = 1 << (v6 >>> 20);
            if(v7 == v) {
                v9 = v1;
            }
            else {
                if(v7 != 0xFFFFF) {
                    v1 = zzml.zzb.getInt(object0, ((long)v7));
                }
                v9 = v1;
                v = v7;
            }
            if((0x10000000 & v5) == 0) {
                v10 = v3;
                v11 = v;
            }
            else {
                v10 = v3;
                v11 = v;
                if(!this.zzQ(object0, v10, v11, v9, v8)) {
                    return false;
                }
            }
            switch(v5 >>> 20 & 0xFF) {
                case 9: 
                case 17: {
                    if(this.zzQ(object0, v10, v11, v9, v8) && !zzml.zzR(object0, v5, this.zzB(v10))) {
                        return false;
                    }
                    break;
                }
                case 27: 
                case 49: {
                    List list0 = (List)zznu.zzf(object0, ((long)(v5 & 0xFFFFF)));
                    if(!list0.isEmpty()) {
                        zzmt zzmt0 = this.zzB(v10);
                        for(int v12 = 0; v12 < list0.size(); ++v12) {
                            if(!zzmt0.zzk(list0.get(v12))) {
                                return false;
                            }
                        }
                    }
                    break;
                }
                case 50: {
                    if(!((zzmc)zznu.zzf(object0, ((long)(v5 & 0xFFFFF)))).isEmpty()) {
                        zzmb zzmb0 = (zzmb)this.zzC(v10);
                        throw null;
                    }
                    break;
                }
                case 60: 
                case 68: {
                    if(this.zzT(object0, v4, v10) && !zzml.zzR(object0, v5, this.zzB(v10))) {
                        return false;
                    }
                }
            }
            ++v2;
            v = v11;
        }
        if(!this.zzh) {
            return true;
        }
        this.zzo.zza(object0);
        throw null;
    }

    static zzml zzl(Class class0, zzmf zzmf0, zzmn zzmn0, zzlw zzlw0, zznk zznk0, zzko zzko0, zzmd zzmd0) {
        int v95;
        Field field3;
        int v93;
        int v92;
        int v84;
        String s1;
        int v83;
        int v82;
        Field field1;
        Field field0;
        int v79;
        int v78;
        int v67;
        zzms zzms1;
        int v66;
        boolean z;
        int v61;
        int v15;
        int v14;
        int v13;
        int v12;
        int v11;
        int v10;
        int[] arr_v;
        int v4;
        if(zzmf0 instanceof zzms) {
            zzms zzms0 = (zzms)zzmf0;
            int v = zzms0.zzc();
            String s = zzms0.zzd();
            int v1 = s.length();
            int v2 = 0;
            if(s.charAt(0) >= 0xD800) {
                for(int v3 = 1; true; v3 = v4) {
                    v4 = v3 + 1;
                    if(s.charAt(v3) < 0xD800) {
                        break;
                    }
                }
            }
            else {
                v4 = 1;
            }
            int v5 = v4 + 1;
            int v6 = s.charAt(v4);
            if(v6 >= 0xD800) {
                int v7 = v6 & 0x1FFF;
                int v8 = 13;
                int v9;
                while((v9 = s.charAt(v5)) >= 0xD800) {
                    v7 |= (v9 & 0x1FFF) << v8;
                    v8 += 13;
                    ++v5;
                }
                v6 = v7 | v9 << v8;
                ++v5;
            }
            if(v6 == 0) {
                arr_v = zzml.zza;
                v10 = 0;
                v11 = 0;
                v12 = 0;
                v13 = 0;
                v14 = 0;
                v15 = 0;
            }
            else {
                int v16 = v5 + 1;
                int v17 = s.charAt(v5);
                if(v17 >= 0xD800) {
                    int v18 = v17 & 0x1FFF;
                    int v19 = 13;
                    int v20;
                    while((v20 = s.charAt(v16)) >= 0xD800) {
                        v18 |= (v20 & 0x1FFF) << v19;
                        v19 += 13;
                        ++v16;
                    }
                    v17 = v18 | v20 << v19;
                    ++v16;
                }
                int v21 = v16 + 1;
                int v22 = s.charAt(v16);
                if(v22 >= 0xD800) {
                    int v23 = v22 & 0x1FFF;
                    int v24 = 13;
                    int v25;
                    while((v25 = s.charAt(v21)) >= 0xD800) {
                        v23 |= (v25 & 0x1FFF) << v24;
                        v24 += 13;
                        ++v21;
                    }
                    v22 = v23 | v25 << v24;
                    ++v21;
                }
                int v26 = v21 + 1;
                int v27 = s.charAt(v21);
                if(v27 >= 0xD800) {
                    int v28 = v27 & 0x1FFF;
                    int v29 = 13;
                    int v30;
                    while((v30 = s.charAt(v26)) >= 0xD800) {
                        v28 |= (v30 & 0x1FFF) << v29;
                        v29 += 13;
                        ++v26;
                    }
                    v27 = v28 | v30 << v29;
                    ++v26;
                }
                int v31 = v26 + 1;
                int v32 = s.charAt(v26);
                if(v32 >= 0xD800) {
                    int v33 = v32 & 0x1FFF;
                    int v34 = 13;
                    int v35;
                    while((v35 = s.charAt(v31)) >= 0xD800) {
                        v33 |= (v35 & 0x1FFF) << v34;
                        v34 += 13;
                        ++v31;
                    }
                    v32 = v33 | v35 << v34;
                    ++v31;
                }
                int v36 = v31 + 1;
                v12 = s.charAt(v31);
                if(v12 >= 0xD800) {
                    int v37 = v12 & 0x1FFF;
                    int v38 = 13;
                    int v39;
                    while((v39 = s.charAt(v36)) >= 0xD800) {
                        v37 |= (v39 & 0x1FFF) << v38;
                        v38 += 13;
                        ++v36;
                    }
                    v12 = v37 | v39 << v38;
                    ++v36;
                }
                int v40 = v36 + 1;
                int v41 = s.charAt(v36);
                if(v41 >= 0xD800) {
                    int v42 = v41 & 0x1FFF;
                    int v43 = 13;
                    int v44;
                    while((v44 = s.charAt(v40)) >= 0xD800) {
                        v42 |= (v44 & 0x1FFF) << v43;
                        v43 += 13;
                        ++v40;
                    }
                    v41 = v42 | v44 << v43;
                    ++v40;
                }
                int v45 = v40 + 1;
                int v46 = s.charAt(v40);
                if(v46 >= 0xD800) {
                    int v47 = v46 & 0x1FFF;
                    int v48 = 13;
                    int v49;
                    while((v49 = s.charAt(v45)) >= 0xD800) {
                        v47 |= (v49 & 0x1FFF) << v48;
                        v48 += 13;
                        ++v45;
                    }
                    v46 = v47 | v49 << v48;
                    ++v45;
                }
                int v50 = v45 + 1;
                int v51 = s.charAt(v45);
                if(v51 >= 0xD800) {
                    int v52 = v51 & 0x1FFF;
                    int v53 = v50;
                    int v54 = 13;
                    int v55;
                    while((v55 = s.charAt(v53)) >= 0xD800) {
                        v52 |= (v55 & 0x1FFF) << v54;
                        v54 += 13;
                        ++v53;
                    }
                    v51 = v52 | v55 << v54;
                    v50 = v53 + 1;
                }
                v2 = v17;
                arr_v = new int[v51 + v41 + v46];
                v10 = v41;
                v15 = v51;
                v5 = v50;
                v13 = v27;
                v11 = v17 * 2 + v22;
                v14 = v32;
            }
            Unsafe unsafe0 = zzml.zzb;
            Object[] arr_object = zzms0.zze();
            Class class1 = zzms0.zza().getClass();
            int v56 = v15 + v10;
            int[] arr_v1 = new int[v12 * 3];
            Object[] arr_object1 = new Object[v12 + v12];
            int v57 = v15;
            int v58 = v56;
            int v59 = 0;
            int v60 = 0;
            while(true) {
                if(v == 2) {
                    v61 = v59;
                    z = true;
                }
                else {
                    v61 = v59;
                    z = false;
                }
                if(v5 >= v1) {
                    break;
                }
                int v62 = s.charAt(v5);
                if(v62 >= 0xD800) {
                    int v63 = v62 & 0x1FFF;
                    int v64 = v5 + 1;
                    int v65 = 13;
                    while(true) {
                        v66 = s.charAt(v64);
                        zzms1 = zzms0;
                        if(v66 < 0xD800) {
                            break;
                        }
                        v63 |= (v66 & 0x1FFF) << v65;
                        v65 += 13;
                        ++v64;
                        zzms0 = zzms1;
                    }
                    v62 = v63 | v66 << v65;
                    v67 = v64 + 1;
                }
                else {
                    zzms1 = zzms0;
                    v67 = v5 + 1;
                }
                int v68 = v67 + 1;
                int v69 = s.charAt(v67);
                if(v69 >= 0xD800) {
                    int v70 = v69 & 0x1FFF;
                    int v71 = 13;
                    int v72;
                    while((v72 = s.charAt(v68)) >= 0xD800) {
                        v70 |= (v72 & 0x1FFF) << v71;
                        v71 += 13;
                        ++v68;
                    }
                    v69 = v70 | v72 << v71;
                    ++v68;
                }
                if((v69 & 0x400) != 0) {
                    arr_v[v61] = v60;
                    ++v61;
                }
                if((v69 & 0xFF) >= 51) {
                    int v73 = s.charAt(v68);
                    if(v73 >= 0xD800) {
                        int v74 = v73 & 0x1FFF;
                        int v75 = v68 + 1;
                        int v76 = 13;
                        int v77;
                        while((v77 = s.charAt(v75)) >= 0xD800) {
                            v74 |= (v77 & 0x1FFF) << v76;
                            v76 += 13;
                            ++v75;
                        }
                        v73 = v74 | v77 << v76;
                        v78 = v75 + 1;
                    }
                    else {
                        v78 = v68 + 1;
                    }
                    v79 = v2;
                    switch((v69 & 0xFF) - 51) {
                        case 12: {
                            if(!z) {
                                arr_object1[v60 / 3 + v60 / 3 + 1] = arr_object[v11];
                                ++v11;
                            }
                            break;
                        }
                        case 9: 
                        case 17: {
                            arr_object1[v60 / 3 + v60 / 3 + 1] = arr_object[v11];
                            ++v11;
                        }
                    }
                    int v80 = v73 + v73;
                    Object object0 = arr_object[v80];
                    if(object0 instanceof Field) {
                        field0 = (Field)object0;
                    }
                    else {
                        field0 = zzml.zzF(class1, ((String)object0));
                        arr_object[v80] = field0;
                    }
                    int v81 = (int)unsafe0.objectFieldOffset(field0);
                    Object object1 = arr_object[v80 + 1];
                    if(object1 instanceof Field) {
                        field1 = (Field)object1;
                    }
                    else {
                        field1 = zzml.zzF(class1, ((String)object1));
                        arr_object[v80 + 1] = field1;
                    }
                    v82 = (int)unsafe0.objectFieldOffset(field1);
                    v83 = v81;
                    s1 = s;
                    v68 = v78;
                    v84 = 0;
                }
                else {
                    v79 = v2;
                    Field field2 = zzml.zzF(class1, ((String)arr_object[v11]));
                    int v85 = v11 + 1;
                    switch(v69 & 0xFF) {
                        case 9: 
                        case 17: {
                            arr_object1[v60 / 3 + v60 / 3 + 1] = field2.getType();
                            v11 = v85;
                            break;
                        }
                        case 12: 
                        case 30: 
                        case 44: {
                            if(!z) {
                                v11 += 2;
                                arr_object1[v60 / 3 + v60 / 3 + 1] = arr_object[v85];
                                break;
                            }
                            v11 = v85;
                            break;
                        }
                        case 27: 
                        case 49: {
                            v11 += 2;
                            arr_object1[v60 / 3 + v60 / 3 + 1] = arr_object[v85];
                            break;
                        }
                        case 50: {
                            arr_v[v57] = v60;
                            int v86 = v60 / 3 + v60 / 3;
                            arr_object1[v86] = arr_object[v85];
                            if((v69 & 0x800) == 0) {
                                v11 += 2;
                            }
                            else {
                                arr_object1[v86 + 1] = arr_object[v11 + 2];
                                v11 += 3;
                            }
                            ++v57;
                            break;
                        }
                        default: {
                            v11 = v85;
                            break;
                        }
                    }
                    v82 = 0xFFFFF;
                    int v87 = (int)unsafe0.objectFieldOffset(field2);
                    if((v69 & 0x1000) != 0x1000 || (v69 & 0xFF) > 17) {
                        s1 = s;
                        v95 = 0;
                    }
                    else {
                        int v88 = v68 + 1;
                        int v89 = s.charAt(v68);
                        if(v89 >= 0xD800) {
                            int v90 = v89 & 0x1FFF;
                            int v91 = 13;
                            while(true) {
                                v92 = v88 + 1;
                                v93 = s.charAt(v88);
                                if(v93 < 0xD800) {
                                    break;
                                }
                                v90 |= (v93 & 0x1FFF) << v91;
                                v91 += 13;
                                v88 = v92;
                            }
                            v89 = v90 | v93 << v91;
                        }
                        else {
                            v92 = v88;
                        }
                        int v94 = v89 / 0x20 + v79 * 2;
                        Object object2 = arr_object[v94];
                        s1 = s;
                        if(object2 instanceof Field) {
                            field3 = (Field)object2;
                        }
                        else {
                            field3 = zzml.zzF(class1, ((String)object2));
                            arr_object[v94] = field3;
                        }
                        v95 = v89 % 0x20;
                        v82 = (int)unsafe0.objectFieldOffset(field3);
                        v68 = v92;
                    }
                    if((v69 & 0xFF) >= 18 && (v69 & 0xFF) <= 49) {
                        arr_v[v58] = v87;
                        ++v58;
                    }
                    v84 = v95;
                    v83 = v87;
                }
                arr_v1[v60] = v62;
                int v96 = v60 + 2;
                arr_v1[v60 + 1] = ((v69 & 0x100) == 0 ? 0 : 0x10000000) | ((v69 & 0x200) == 0 ? 0 : 0x20000000) | (v69 & 0xFF) << 20 | v83;
                v60 += 3;
                arr_v1[v96] = v84 << 20 | v82;
                v5 = v68;
                v59 = v61;
                zzms0 = zzms1;
                s = s1;
                v2 = v79;
            }
            return new zzml(arr_v1, arr_object1, v13, v14, zzms0.zza(), z, false, arr_v, v15, v56, zzmn0, zzlw0, zznk0, zzko0, zzmd0);
        }
        zznh zznh0 = (zznh)zzmf0;
        throw null;
    }

    private static double zzm(Object object0, long v) {
        return (double)(((Double)zznu.zzf(object0, v)));
    }

    private static float zzn(Object object0, long v) {
        return (float)(((Float)zznu.zzf(object0, v)));
    }

    private final int zzo(Object object0) {
        int v23;
        int v22;
        int v18;
        int v17;
        int v15;
        int v14;
        int v13;
        int v12;
        int v11;
        int v9;
        Unsafe unsafe0 = zzml.zzb;
        int v1 = 0;
        int v2 = 0xFFFFF;
        int v3 = 0;
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v4 = this.zzy(v);
            int[] arr_v = this.zzc;
            int v5 = arr_v[v];
            int v6 = v4 >>> 20 & 0xFF;
            if(v6 <= 17) {
                int v7 = arr_v[v + 2];
                int v8 = v7 & 0xFFFFF;
                if(v8 != v2) {
                    v3 = unsafe0.getInt(object0, ((long)v8));
                    v2 = v8;
                }
                v9 = 1 << (v7 >>> 20);
            }
            else {
                v9 = 0;
            }
            long v10 = (long)(v4 & 0xFFFFF);
            switch(v6) {
                case 0: {
                    if((v3 & v9) != 0) {
                        v11 = zzki.zzx(v5 << 3);
                        v15 = v11 + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 1: {
                    if((v3 & v9) != 0) {
                        v12 = zzki.zzx(v5 << 3);
                        v15 = v12 + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 2: {
                    if((v3 & v9) != 0) {
                        v13 = zzki.zzy(unsafe0.getLong(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 3: {
                    if((v3 & v9) != 0) {
                        v13 = zzki.zzy(unsafe0.getLong(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 4: {
                    if((v3 & v9) != 0) {
                        v13 = zzki.zzu(unsafe0.getInt(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 5: {
                    if((v3 & v9) != 0) {
                        v11 = zzki.zzx(v5 << 3);
                        v15 = v11 + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 6: {
                    if((v3 & v9) != 0) {
                        v12 = zzki.zzx(v5 << 3);
                        v15 = v12 + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 7: {
                    if((v3 & v9) != 0) {
                        v15 = zzki.zzx(v5 << 3) + 1;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 8: {
                    if((v3 & v9) != 0) {
                        Object object1 = unsafe0.getObject(object0, v10);
                        if(object1 instanceof zzka) {
                            int v16 = ((zzka)object1).zzd();
                            v17 = zzki.zzx(v16) + v16;
                            v18 = zzki.zzx(v5 << 3);
                            v15 = v18 + v17;
                            v1 += v15;
                        }
                        else {
                            v13 = zzki.zzw(((String)object1));
                            v14 = zzki.zzx(v5 << 3);
                            v1 += v14 + v13;
                        }
                        break;
                    }
                    break;
                }
                case 9: {
                    if((v3 & v9) != 0) {
                        v15 = zzmv.zzn(v5, unsafe0.getObject(object0, v10), this.zzB(v));
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 10: {
                    if((v3 & v9) != 0) {
                        int v19 = ((zzka)unsafe0.getObject(object0, v10)).zzd();
                        v17 = zzki.zzx(v19) + v19;
                        v18 = zzki.zzx(v5 << 3);
                        v15 = v18 + v17;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 11: {
                    if((v3 & v9) != 0) {
                        v13 = zzki.zzx(unsafe0.getInt(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 12: {
                    if((v3 & v9) != 0) {
                        v13 = zzki.zzu(unsafe0.getInt(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 13: {
                    if((v3 & v9) != 0) {
                        v12 = zzki.zzx(v5 << 3);
                        v15 = v12 + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 14: {
                    if((v3 & v9) != 0) {
                        v11 = zzki.zzx(v5 << 3);
                        v15 = v11 + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 15: {
                    if((v3 & v9) != 0) {
                        int v20 = unsafe0.getInt(object0, v10);
                        v14 = zzki.zzx(v5 << 3);
                        v13 = zzki.zzx(v20 >> 0x1F ^ v20 + v20);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 16: {
                    if((v9 & v3) != 0) {
                        long v21 = unsafe0.getLong(object0, v10);
                        v15 = zzki.zzx(v5 << 3) + zzki.zzy(v21 >> 0x3F ^ v21 + v21);
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 17: {
                    if((v3 & v9) != 0) {
                        v15 = zzki.zzt(v5, ((zzmi)unsafe0.getObject(object0, v10)), this.zzB(v));
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 18: {
                    v15 = zzmv.zzg(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 19: {
                    v15 = zzmv.zze(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 20: {
                    v15 = zzmv.zzl(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 21: {
                    v15 = zzmv.zzw(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 22: {
                    v15 = zzmv.zzj(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 23: {
                    v15 = zzmv.zzg(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 24: {
                    v15 = zzmv.zze(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 25: {
                    v15 = zzmv.zza(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 26: {
                    v15 = zzmv.zzt(v5, ((List)unsafe0.getObject(object0, v10)));
                    v1 += v15;
                    break;
                }
                case 27: {
                    v15 = zzmv.zzo(v5, ((List)unsafe0.getObject(object0, v10)), this.zzB(v));
                    v1 += v15;
                    break;
                }
                case 28: {
                    v15 = zzmv.zzb(v5, ((List)unsafe0.getObject(object0, v10)));
                    v1 += v15;
                    break;
                }
                case 29: {
                    v15 = zzmv.zzu(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 30: {
                    v15 = zzmv.zzc(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 0x1F: {
                    v15 = zzmv.zze(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 0x20: {
                    v15 = zzmv.zzg(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 33: {
                    v15 = zzmv.zzp(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 34: {
                    v15 = zzmv.zzr(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v15;
                    break;
                }
                case 35: {
                    v13 = zzmv.zzh(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 36: {
                    v13 = zzmv.zzf(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 37: {
                    v13 = zzmv.zzm(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 38: {
                    v13 = zzmv.zzx(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 39: {
                    v13 = zzmv.zzk(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 40: {
                    v13 = zzmv.zzh(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 41: {
                    v13 = zzmv.zzf(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 42: {
                    v13 = ((List)unsafe0.getObject(object0, v10)).size();
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 43: {
                    v13 = zzmv.zzv(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 44: {
                    v13 = zzmv.zzd(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 45: {
                    v13 = zzmv.zzf(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 46: {
                    v13 = zzmv.zzh(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 0x2F: {
                    v13 = zzmv.zzq(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v22 = zzki.zzx(v13);
                        v23 = zzki.zzx(v5 << 3);
                        v14 = v23 + v22;
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 0x30: {
                    v13 = zzmv.zzs(((List)unsafe0.getObject(object0, v10)));
                    if(v13 > 0) {
                        v14 = zzki.zzx(v5 << 3) + zzki.zzx(v13);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 49: {
                    v15 = zzmv.zzi(v5, ((List)unsafe0.getObject(object0, v10)), this.zzB(v));
                    v1 += v15;
                    break;
                }
                case 50: {
                    zzmd.zza(v5, unsafe0.getObject(object0, v10), this.zzC(v));
                    break;
                }
                case 51: {
                    if(this.zzT(object0, v5, v)) {
                        v11 = zzki.zzx(v5 << 3);
                        v15 = v11 + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 52: {
                    if(this.zzT(object0, v5, v)) {
                        v12 = zzki.zzx(v5 << 3);
                        v15 = v12 + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 53: {
                    if(this.zzT(object0, v5, v)) {
                        v13 = zzki.zzy(zzml.zzz(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 54: {
                    if(this.zzT(object0, v5, v)) {
                        v13 = zzki.zzy(zzml.zzz(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 55: {
                    if(this.zzT(object0, v5, v)) {
                        v13 = zzki.zzu(zzml.zzp(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 56: {
                    if(this.zzT(object0, v5, v)) {
                        v11 = zzki.zzx(v5 << 3);
                        v15 = v11 + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 57: {
                    if(this.zzT(object0, v5, v)) {
                        v12 = zzki.zzx(v5 << 3);
                        v15 = v12 + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 58: {
                    if(this.zzT(object0, v5, v)) {
                        v15 = zzki.zzx(v5 << 3) + 1;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 59: {
                    if(this.zzT(object0, v5, v)) {
                        Object object2 = unsafe0.getObject(object0, v10);
                        if(object2 instanceof zzka) {
                            int v24 = ((zzka)object2).zzd();
                            v17 = zzki.zzx(v24) + v24;
                            v18 = zzki.zzx(v5 << 3);
                            v15 = v18 + v17;
                            v1 += v15;
                        }
                        else {
                            v13 = zzki.zzw(((String)object2));
                            v14 = zzki.zzx(v5 << 3);
                            v1 += v14 + v13;
                        }
                        break;
                    }
                    break;
                }
                case 60: {
                    if(this.zzT(object0, v5, v)) {
                        v15 = zzmv.zzn(v5, unsafe0.getObject(object0, v10), this.zzB(v));
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 61: {
                    if(this.zzT(object0, v5, v)) {
                        int v25 = ((zzka)unsafe0.getObject(object0, v10)).zzd();
                        v15 = zzki.zzx(v5 << 3) + (zzki.zzx(v25) + v25);
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 62: {
                    if(this.zzT(object0, v5, v)) {
                        v13 = zzki.zzx(zzml.zzp(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                        break;
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zzT(object0, v5, v)) {
                        v13 = zzki.zzu(zzml.zzp(object0, v10));
                        v14 = zzki.zzx(v5 << 3);
                        v1 += v14 + v13;
                    }
                    break;
                }
                case 0x40: {
                    if(this.zzT(object0, v5, v)) {
                        v15 = zzki.zzx(v5 << 3) + 4;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 65: {
                    if(this.zzT(object0, v5, v)) {
                        v15 = zzki.zzx(v5 << 3) + 8;
                        v1 += v15;
                        break;
                    }
                    break;
                }
                case 66: {
                    if(this.zzT(object0, v5, v)) {
                        int v26 = zzml.zzp(object0, v10);
                        v1 += zzki.zzx(v5 << 3) + zzki.zzx(v26 >> 0x1F ^ v26 + v26);
                    }
                    break;
                }
                case 67: {
                    if(this.zzT(object0, v5, v)) {
                        long v27 = zzml.zzz(object0, v10);
                        v15 = zzki.zzx(v5 << 3) + zzki.zzy(v27 >> 0x3F ^ v27 + v27);
                        v1 += v15;
                    }
                    break;
                }
                case 68: {
                    if(this.zzT(object0, v5, v)) {
                        v1 += zzki.zzt(v5, ((zzmi)unsafe0.getObject(object0, v10)), this.zzB(v));
                    }
                }
            }
        }
        Object object3 = this.zzn.zzd(object0);
        int v28 = this.zzn.zza(object3);
        if(!this.zzh) {
            return v1 + v28;
        }
        this.zzo.zza(object0);
        throw null;
    }

    private static int zzp(Object object0, long v) {
        return (int)(((Integer)zznu.zzf(object0, v)));
    }

    private final int zzq(Object object0, byte[] arr_b, int v, int v1, int v2, long v3, zzjn zzjn0) throws IOException {
        Unsafe unsafe0 = zzml.zzb;
        Object object1 = this.zzC(v2);
        Object object2 = unsafe0.getObject(object0, v3);
        if(!((zzmc)object2).zze()) {
            zzmc zzmc0 = zzmc.zza().zzb();
            zzmd.zzb(zzmc0, object2);
            unsafe0.putObject(object0, v3, zzmc0);
        }
        zzmb zzmb0 = (zzmb)object1;
        throw null;
    }

    private final int zzr(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, int v6, long v7, int v8, zzjn zzjn0) throws IOException {
        Unsafe unsafe0 = zzml.zzb;
        long v9 = (long)(this.zzc[v8 + 2] & 0xFFFFF);
        boolean z = true;
        switch(v6) {
            case 51: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, Double.longBitsToDouble(zzjo.zzp(arr_b, v)));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 8;
                }
                break;
            }
            case 52: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, Float.intBitsToFloat(zzjo.zzb(arr_b, v)));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 4;
                }
                break;
            }
            case 53: 
            case 54: {
                if(v4 == 0) {
                    int v11 = zzjo.zzm(arr_b, v, zzjn0);
                    unsafe0.putObject(object0, v7, zzjn0.zzb);
                    unsafe0.putInt(object0, v9, v3);
                    return v11;
                }
                break;
            }
            case 58: {
                if(v4 == 0) {
                    int v13 = zzjo.zzm(arr_b, v, zzjn0);
                    if(zzjn0.zzb == 0L) {
                        z = false;
                    }
                    unsafe0.putObject(object0, v7, Boolean.valueOf(z));
                    unsafe0.putInt(object0, v9, v3);
                    return v13;
                }
                break;
            }
            case 59: {
                if(v4 == 2) {
                    int v14 = zzjo.zzj(arr_b, v, zzjn0);
                    int v15 = zzjn0.zza;
                    if(v15 == 0) {
                        unsafe0.putObject(object0, v7, "");
                    }
                    else {
                        if((v5 & 0x20000000) != 0 && !zznz.zze(arr_b, v14, v14 + v15)) {
                            throw zzll.zzc();
                        }
                        unsafe0.putObject(object0, v7, new String(arr_b, v14, v15, zzlj.zzb));
                        v14 += v15;
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v14;
                }
                break;
            }
            case 60: {
                if(v4 == 2) {
                    Object object2 = this.zzE(object0, v3, v8);
                    int v16 = zzjo.zzo(object2, this.zzB(v8), arr_b, v, v1, zzjn0);
                    this.zzM(object0, v3, v8, object2);
                    return v16;
                }
                break;
            }
            case 61: {
                if(v4 == 2) {
                    int v17 = zzjo.zza(arr_b, v, zzjn0);
                    unsafe0.putObject(object0, v7, zzjn0.zzc);
                    unsafe0.putInt(object0, v9, v3);
                    return v17;
                }
                break;
            }
            case 55: 
            case 62: {
                if(v4 == 0) {
                    int v12 = zzjo.zzj(arr_b, v, zzjn0);
                    unsafe0.putObject(object0, v7, zzjn0.zza);
                    unsafe0.putInt(object0, v9, v3);
                    return v12;
                }
                break;
            }
            case 0x3F: {
                if(v4 == 0) {
                    int v18 = zzjo.zzj(arr_b, v, zzjn0);
                    int v19 = zzjn0.zza;
                    zzlf zzlf0 = this.zzA(v8);
                    if(zzlf0 != null && !zzlf0.zza(v19)) {
                        zzml.zzd(object0).zzj(v2, ((long)v19));
                        return v18;
                    }
                    unsafe0.putObject(object0, v7, v19);
                    unsafe0.putInt(object0, v9, v3);
                    return v18;
                }
                break;
            }
            case 57: 
            case 0x40: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, zzjo.zzb(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 4;
                }
                break;
            }
            case 56: 
            case 65: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, zzjo.zzp(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 8;
                }
                break;
            }
            case 66: {
                if(v4 == 0) {
                    int v20 = zzjo.zzj(arr_b, v, zzjn0);
                    unsafe0.putObject(object0, v7, ((int)(zzjn0.zza >>> 1 ^ -(zzjn0.zza & 1))));
                    unsafe0.putInt(object0, v9, v3);
                    return v20;
                }
                break;
            }
            case 67: {
                if(v4 == 0) {
                    int v21 = zzjo.zzm(arr_b, v, zzjn0);
                    unsafe0.putObject(object0, v7, ((long)(zzjn0.zzb >>> 1 ^ -(1L & zzjn0.zzb))));
                    unsafe0.putInt(object0, v9, v3);
                    return v21;
                }
                break;
            }
            case 68: {
                if(v4 == 3) {
                    Object object1 = this.zzE(object0, v3, v8);
                    int v10 = zzjo.zzn(object1, this.zzB(v8), arr_b, v, v1, v2 & -8 | 4, zzjn0);
                    this.zzM(object0, v3, v8, object1);
                    return v10;
                }
                return v;
            }
            default: {
                return v;
            }
        }
        return v;
    }

    private final int zzs(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, long v6, int v7, long v8, zzjn zzjn0) throws IOException {
        int v46;
        int v9 = v;
        Unsafe unsafe0 = zzml.zzb;
        zzli zzli0 = (zzli)unsafe0.getObject(object0, v8);
        if(!zzli0.zzc()) {
            int v10 = zzli0.size();
            zzli0 = zzli0.zzd((v10 == 0 ? 10 : v10 + v10));
            unsafe0.putObject(object0, v8, zzli0);
        }
        switch(v7) {
            case 26: {
                if(v4 == 2) {
                    if((v6 & 0x20000000L) == 0L) {
                        int v35 = zzjo.zzj(arr_b, v9, zzjn0);
                        int v36 = zzjn0.zza;
                        if(v36 < 0) {
                            throw zzll.zzd();
                        }
                        if(v36 == 0) {
                            zzli0.add("");
                            goto label_152;
                        }
                        else {
                            zzli0.add(new String(arr_b, v35, v36, zzlj.zzb));
                        }
                    alab1:
                        while(true) {
                            v35 += v36;
                            while(true) {
                            label_152:
                                if(v35 >= v1) {
                                    break alab1;
                                }
                                int v37 = zzjo.zzj(arr_b, v35, zzjn0);
                                if(v2 != zzjn0.zza) {
                                    break alab1;
                                }
                                v35 = zzjo.zzj(arr_b, v37, zzjn0);
                                v36 = zzjn0.zza;
                                if(v36 < 0) {
                                    throw zzll.zzd();
                                }
                                if(v36 != 0) {
                                    zzli0.add(new String(arr_b, v35, v36, zzlj.zzb));
                                    break;
                                }
                                zzli0.add("");
                            }
                        }
                        return v35;
                    }
                    int v38 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v39 = zzjn0.zza;
                    if(v39 < 0) {
                        throw zzll.zzd();
                    }
                    if(v39 == 0) {
                        zzli0.add("");
                        goto label_176;
                    }
                    int v40 = v38 + v39;
                    if(!zznz.zze(arr_b, v38, v40)) {
                        throw zzll.zzc();
                    }
                    zzli0.add(new String(arr_b, v38, v39, zzlj.zzb));
                alab2:
                    while(true) {
                        v38 = v40;
                        while(true) {
                        label_176:
                            if(v38 >= v1) {
                                return v38;
                            }
                            int v41 = zzjo.zzj(arr_b, v38, zzjn0);
                            if(v2 != zzjn0.zza) {
                                return v38;
                            }
                            v38 = zzjo.zzj(arr_b, v41, zzjn0);
                            int v42 = zzjn0.zza;
                            if(v42 < 0) {
                                throw zzll.zzd();
                            }
                            if(v42 != 0) {
                                v40 = v38 + v42;
                                if(!zznz.zze(arr_b, v38, v40)) {
                                    break alab2;
                                }
                                zzli0.add(new String(arr_b, v38, v42, zzlj.zzb));
                                break;
                            }
                            zzli0.add("");
                        }
                    }
                    throw zzll.zzc();
                }
                break;
            }
            case 27: {
                return v4 == 2 ? zzjo.zze(this.zzB(v5), v2, arr_b, v9, v1, zzli0, zzjn0) : v;
            label_14:
                if(v4 == 3) {
                    zzmt zzmt0 = this.zzB(v5);
                    int v12 = v2 & -8 | 4;
                    int v13 = zzjo.zzc(zzmt0, arr_b, v9, v1, v12, zzjn0);
                    zzli0.add(zzjn0.zzc);
                    while(v13 < v1) {
                        int v14 = zzjo.zzj(arr_b, v13, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        v13 = zzjo.zzc(zzmt0, arr_b, v14, v1, v12, zzjn0);
                        zzli0.add(zzjn0.zzc);
                    }
                    return v13;
                }
                break;
            }
            case 28: {
                if(v4 == 2) {
                    int v43 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v44 = zzjn0.zza;
                    if(v44 < 0) {
                        throw zzll.zzd();
                    }
                    if(v44 > arr_b.length - v43) {
                        throw zzll.zzf();
                    }
                    if(v44 == 0) {
                        zzli0.add(zzka.zzb);
                        goto label_204;
                    }
                    else {
                        zzli0.add(zzka.zzl(arr_b, v43, v44));
                    }
                alab3:
                    while(true) {
                        v43 += v44;
                        while(true) {
                        label_204:
                            if(v43 >= v1) {
                                return v43;
                            }
                            int v45 = zzjo.zzj(arr_b, v43, zzjn0);
                            if(v2 != zzjn0.zza) {
                                return v43;
                            }
                            v43 = zzjo.zzj(arr_b, v45, zzjn0);
                            v44 = zzjn0.zza;
                            if(v44 < 0) {
                                throw zzll.zzd();
                            }
                            if(v44 > arr_b.length - v43) {
                                break alab3;
                            }
                            if(v44 != 0) {
                                zzli0.add(zzka.zzl(arr_b, v43, v44));
                                break;
                            }
                            zzli0.add(zzka.zzb);
                        }
                    }
                    throw zzll.zzf();
                }
                break;
            }
            case 18: 
            case 35: {
                if(v4 == 2) {
                    int v15 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v16 = zzjn0.zza + v15;
                    while(v15 < v16) {
                        ((zzkk)zzli0).zze(Double.longBitsToDouble(zzjo.zzp(arr_b, v15)));
                        v15 += 8;
                    }
                    if(v15 != v16) {
                        throw zzll.zzf();
                    }
                    return v15;
                }
                if(v4 == 1) {
                    ((zzkk)zzli0).zze(Double.longBitsToDouble(zzjo.zzp(arr_b, v)));
                    int v17;
                    while((v17 = v9 + 8) < v1) {
                        v9 = zzjo.zzj(arr_b, v17, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        ((zzkk)zzli0).zze(Double.longBitsToDouble(zzjo.zzp(arr_b, v9)));
                    }
                    return v17;
                }
                break;
            }
            case 19: 
            case 36: {
                if(v4 == 2) {
                    int v18 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v19 = zzjn0.zza + v18;
                    while(v18 < v19) {
                        ((zzku)zzli0).zze(Float.intBitsToFloat(zzjo.zzb(arr_b, v18)));
                        v18 += 4;
                    }
                    if(v18 != v19) {
                        throw zzll.zzf();
                    }
                    return v18;
                }
                if(v4 == 5) {
                    ((zzku)zzli0).zze(Float.intBitsToFloat(zzjo.zzb(arr_b, v)));
                    int v20;
                    while((v20 = v9 + 4) < v1) {
                        v9 = zzjo.zzj(arr_b, v20, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        ((zzku)zzli0).zze(Float.intBitsToFloat(zzjo.zzb(arr_b, v9)));
                    }
                    return v20;
                }
                break;
            }
            case 20: 
            case 21: 
            case 37: 
            case 38: {
                if(v4 == 2) {
                    int v21 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v22 = zzjn0.zza + v21;
                    while(v21 < v22) {
                        v21 = zzjo.zzm(arr_b, v21, zzjn0);
                        ((zzlx)zzli0).zzg(zzjn0.zzb);
                    }
                    if(v21 != v22) {
                        throw zzll.zzf();
                    }
                    return v21;
                }
                if(v4 == 0) {
                    int v23 = zzjo.zzm(arr_b, v9, zzjn0);
                    ((zzlx)zzli0).zzg(zzjn0.zzb);
                    while(v23 < v1) {
                        int v24 = zzjo.zzj(arr_b, v23, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        v23 = zzjo.zzm(arr_b, v24, zzjn0);
                        ((zzlx)zzli0).zzg(zzjn0.zzb);
                    }
                    return v23;
                }
                break;
            }
            case 25: 
            case 42: {
                if(v4 == 2) {
                    int v31 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v32 = zzjn0.zza + v31;
                    while(v31 < v32) {
                        v31 = zzjo.zzm(arr_b, v31, zzjn0);
                        ((zzjp)zzli0).zze(zzjn0.zzb != 0L);
                    }
                    if(v31 != v32) {
                        throw zzll.zzf();
                    }
                    return v31;
                }
                if(v4 == 0) {
                    int v33 = zzjo.zzm(arr_b, v9, zzjn0);
                    ((zzjp)zzli0).zze(zzjn0.zzb != 0L);
                    while(v33 < v1) {
                        int v34 = zzjo.zzj(arr_b, v33, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        v33 = zzjo.zzm(arr_b, v34, zzjn0);
                        ((zzjp)zzli0).zze(zzjn0.zzb != 0L);
                    }
                    return v33;
                }
                break;
            }
            case 22: 
            case 29: 
            case 39: 
            case 43: {
                if(v4 == 2) {
                    return zzjo.zzf(arr_b, v9, zzli0, zzjn0);
                }
                return v4 == 0 ? zzjo.zzl(v2, arr_b, v9, v1, zzli0, zzjn0) : v;
            }
            case 30: 
            case 44: {
                if(v4 == 2) {
                    v46 = zzjo.zzf(arr_b, v9, zzli0, zzjn0);
                }
                else if(v4 == 0) {
                    v46 = zzjo.zzl(v2, arr_b, v9, v1, zzli0, zzjn0);
                }
                else {
                    break;
                }
                Object object1 = null;
                zzlf zzlf0 = this.zzA(v5);
                zznk zznk0 = this.zzn;
                if(zzlf0 != null) {
                    if(zzli0 instanceof RandomAccess) {
                        int v47 = zzli0.size();
                        int v48 = 0;
                        for(int v11 = 0; v11 < v47; ++v11) {
                            Integer integer0 = (Integer)zzli0.get(v11);
                            int v49 = (int)integer0;
                            if(zzlf0.zza(v49)) {
                                if(v11 != v48) {
                                    zzli0.set(v48, integer0);
                                }
                                ++v48;
                            }
                            else {
                                object1 = zzmv.zzA(object0, v3, v49, object1, zznk0);
                            }
                        }
                        if(v48 != v47) {
                            zzli0.subList(v48, v47).clear();
                            return v46;
                        }
                    }
                    else {
                        Iterator iterator0 = zzli0.iterator();
                        while(iterator0.hasNext()) {
                            Object object2 = iterator0.next();
                            int v50 = (int)(((Integer)object2));
                            if(!zzlf0.zza(v50)) {
                                object1 = zzmv.zzA(object0, v3, v50, object1, zznk0);
                                iterator0.remove();
                            }
                        }
                    }
                }
                return v46;
            }
            case 24: 
            case 0x1F: 
            case 41: 
            case 45: {
                if(v4 == 2) {
                    int v28 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v29 = zzjn0.zza + v28;
                    while(v28 < v29) {
                        ((zzlc)zzli0).zzh(zzjo.zzb(arr_b, v28));
                        v28 += 4;
                    }
                    if(v28 != v29) {
                        throw zzll.zzf();
                    }
                    return v28;
                }
                if(v4 == 5) {
                    ((zzlc)zzli0).zzh(zzjo.zzb(arr_b, v));
                    int v30;
                    while((v30 = v9 + 4) < v1) {
                        v9 = zzjo.zzj(arr_b, v30, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        ((zzlc)zzli0).zzh(zzjo.zzb(arr_b, v9));
                    }
                    return v30;
                }
                break;
            }
            case 23: 
            case 0x20: 
            case 40: 
            case 46: {
                if(v4 == 2) {
                    int v25 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v26 = zzjn0.zza + v25;
                    while(v25 < v26) {
                        ((zzlx)zzli0).zzg(zzjo.zzp(arr_b, v25));
                        v25 += 8;
                    }
                    if(v25 != v26) {
                        throw zzll.zzf();
                    }
                    return v25;
                }
                if(v4 == 1) {
                    ((zzlx)zzli0).zzg(zzjo.zzp(arr_b, v));
                    int v27;
                    while((v27 = v9 + 8) < v1) {
                        v9 = zzjo.zzj(arr_b, v27, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        ((zzlx)zzli0).zzg(zzjo.zzp(arr_b, v9));
                    }
                    return v27;
                }
                break;
            }
            case 33: 
            case 0x2F: {
                if(v4 == 2) {
                    int v51 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v52 = zzjn0.zza + v51;
                    while(v51 < v52) {
                        v51 = zzjo.zzj(arr_b, v51, zzjn0);
                        ((zzlc)zzli0).zzh(zzjn0.zza >>> 1 ^ -(zzjn0.zza & 1));
                    }
                    if(v51 != v52) {
                        throw zzll.zzf();
                    }
                    return v51;
                }
                if(v4 == 0) {
                    int v53 = zzjo.zzj(arr_b, v9, zzjn0);
                    ((zzlc)zzli0).zzh(zzjn0.zza >>> 1 ^ -(zzjn0.zza & 1));
                    while(v53 < v1) {
                        int v54 = zzjo.zzj(arr_b, v53, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        v53 = zzjo.zzj(arr_b, v54, zzjn0);
                        ((zzlc)zzli0).zzh(zzjn0.zza >>> 1 ^ -(zzjn0.zza & 1));
                    }
                    return v53;
                }
                break;
            }
            case 34: 
            case 0x30: {
                if(v4 == 2) {
                    int v55 = zzjo.zzj(arr_b, v9, zzjn0);
                    int v56 = zzjn0.zza + v55;
                    while(v55 < v56) {
                        v55 = zzjo.zzm(arr_b, v55, zzjn0);
                        ((zzlx)zzli0).zzg(zzjn0.zzb >>> 1 ^ -(1L & zzjn0.zzb));
                    }
                    if(v55 != v56) {
                        throw zzll.zzf();
                    }
                    return v55;
                }
                if(v4 == 0) {
                    int v57 = zzjo.zzm(arr_b, v9, zzjn0);
                    ((zzlx)zzli0).zzg(zzjn0.zzb >>> 1 ^ -(1L & zzjn0.zzb));
                    while(v57 < v1) {
                        int v58 = zzjo.zzj(arr_b, v57, zzjn0);
                        if(v2 != zzjn0.zza) {
                            break;
                        }
                        v57 = zzjo.zzm(arr_b, v58, zzjn0);
                        ((zzlx)zzli0).zzg(zzjn0.zzb >>> 1 ^ -(1L & zzjn0.zzb));
                    }
                    return v57;
                }
                break;
            }
            default: {
                goto label_14;
            }
        }
        return v;
    }

    private final int zzt(int v) {
        return v < this.zze || v > this.zzf ? -1 : this.zzw(v, 0);
    }

    private final int zzu(int v, int v1) {
        return v < this.zze || v > this.zzf ? -1 : this.zzw(v, v1);
    }

    private final int zzv(int v) {
        return this.zzc[v + 2];
    }

    private final int zzw(int v, int v1) {
        int v2 = this.zzc.length / 3 - 1;
        while(v1 <= v2) {
            int v3 = v2 + v1 >>> 1;
            int v4 = v3 * 3;
            int v5 = this.zzc[v4];
            if(v == v5) {
                return v4;
            }
            if(v < v5) {
                v2 = v3 - 1;
            }
            else {
                v1 = v3 + 1;
            }
        }
        return -1;
    }

    private static int zzx(int v) [...] // Inlined contents

    private final int zzy(int v) {
        return this.zzc[v + 1];
    }

    private static long zzz(Object object0, long v) {
        return (long)(((Long)zznu.zzf(object0, v)));
    }
}

