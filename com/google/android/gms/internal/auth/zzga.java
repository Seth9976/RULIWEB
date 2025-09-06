package com.google.android.gms.internal.auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import sun.misc.Unsafe;

final class zzga implements zzgi {
    private static final int[] zza;
    private static final Unsafe zzb;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfx zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzfl zzk;
    private final zzgz zzl;
    private final zzem zzm;
    private final zzgc zzn;
    private final zzfs zzo;

    static {
        zzga.zza = new int[0];
        zzga.zzb = zzhj.zzg();
    }

    private zzga(int[] arr_v, Object[] arr_object, int v, int v1, zzfx zzfx0, int v2, boolean z, int[] arr_v1, int v3, int v4, zzgc zzgc0, zzfl zzfl0, zzgz zzgz0, zzem zzem0, zzfs zzfs0) {
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zze = v;
        this.zzf = v1;
        this.zzh = arr_v1;
        this.zzi = v3;
        this.zzj = v4;
        this.zzn = zzgc0;
        this.zzk = zzfl0;
        this.zzl = zzgz0;
        this.zzm = zzem0;
        this.zzg = zzfx0;
        this.zzo = zzfs0;
    }

    private final void zzA(Object object0, int v, int v1) {
        zzhj.zzn(object0, ((long)(this.zzl(v1) & 0xFFFFF)), v);
    }

    private final void zzB(Object object0, int v, Object object1) {
        int v1 = this.zzo(v);
        zzga.zzb.putObject(object0, ((long)(v1 & 0xFFFFF)), object1);
        this.zzz(object0, v);
    }

    private final void zzC(Object object0, int v, int v1, Object object1) {
        int v2 = this.zzo(v1);
        zzga.zzb.putObject(object0, ((long)(v2 & 0xFFFFF)), object1);
        this.zzA(object0, v, v1);
    }

    private final boolean zzD(Object object0, Object object1, int v) {
        return this.zzE(object0, v) == this.zzE(object1, v);
    }

    private final boolean zzE(Object object0, int v) {
        int v1 = this.zzl(v);
        if(((long)(v1 & 0xFFFFF)) == 0xFFFFFL) {
            int v2 = this.zzo(v);
            switch(v2 >>> 20 & 0xFF) {
                case 0: {
                    return Double.doubleToRawLongBits(zzhj.zza(object0, ((long)(v2 & 0xFFFFF)))) != 0L;
                }
                case 1: {
                    return Float.floatToRawIntBits(zzhj.zzb(object0, ((long)(v2 & 0xFFFFF)))) != 0;
                }
                case 2: {
                    return zzhj.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 3: {
                    return zzhj.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 4: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 5: {
                    return zzhj.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 6: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 7: {
                    return zzhj.zzt(object0, ((long)(v2 & 0xFFFFF)));
                }
                case 8: {
                    Object object1 = zzhj.zzf(object0, ((long)(v2 & 0xFFFFF)));
                    if(object1 instanceof String) {
                        return !((String)object1).isEmpty();
                    }
                    if(!(object1 instanceof zzef)) {
                        throw new IllegalArgumentException();
                    }
                    return !zzef.zzb.equals(object1);
                }
                case 9: {
                    return zzhj.zzf(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                case 10: {
                    Object object2 = zzhj.zzf(object0, ((long)(v2 & 0xFFFFF)));
                    return !zzef.zzb.equals(object2);
                }
                case 11: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 12: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 13: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 14: {
                    return zzhj.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 15: {
                    return zzhj.zzc(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 16: {
                    return zzhj.zzd(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 17: {
                    return zzhj.zzf(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        return (zzhj.zzc(object0, ((long)(v1 & 0xFFFFF))) & 1 << (v1 >>> 20)) != 0;
    }

    private final boolean zzF(Object object0, int v, int v1, int v2, int v3) {
        return v1 == 0xFFFFF ? this.zzE(object0, v) : (v2 & v3) != 0;
    }

    private static boolean zzG(Object object0, int v, zzgi zzgi0) {
        return zzgi0.zzi(zzhj.zzf(object0, ((long)(v & 0xFFFFF))));
    }

    private static boolean zzH(Object object0) {
        if(object0 == null) {
            return false;
        }
        return object0 instanceof zzev ? ((zzev)object0).zzm() : true;
    }

    private final boolean zzI(Object object0, int v, int v1) {
        return zzhj.zzc(object0, ((long)(this.zzl(v1) & 0xFFFFF))) == v;
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final int zza(Object object0) {
        int v8;
        int v7;
        long v6;
        int v1 = 0;
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v2 = this.zzo(v);
            int v3 = this.zzc[v];
            long v4 = (long)(0xFFFFF & v2);
            int v5 = 37;
            switch(v2 >>> 20 & 0xFF) {
                case 0: {
                    v6 = Double.doubleToLongBits(zzhj.zza(object0, v4));
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 1: {
                    v7 = v1 * 53;
                    v8 = Float.floatToIntBits(zzhj.zzb(object0, v4));
                    v1 = v7 + v8;
                    break;
                }
                case 2: {
                    v6 = zzhj.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 3: {
                    v6 = zzhj.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 4: {
                    v7 = v1 * 53;
                    v8 = zzhj.zzc(object0, v4);
                    v1 = v7 + v8;
                    break;
                }
                case 5: {
                    v6 = zzhj.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 6: {
                    v1 = v1 * 53 + zzhj.zzc(object0, v4);
                    break;
                }
                case 7: {
                    v1 = v1 * 53 + zzfa.zza(zzhj.zzt(object0, v4));
                    break;
                }
                case 8: {
                    v1 = v1 * 53 + ((String)zzhj.zzf(object0, v4)).hashCode();
                    break;
                }
                case 9: {
                    Object object1 = zzhj.zzf(object0, v4);
                    if(object1 != null) {
                        v5 = object1.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 10: {
                    v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    break;
                }
                case 11: {
                    v1 = v1 * 53 + zzhj.zzc(object0, v4);
                    break;
                }
                case 12: {
                    v1 = v1 * 53 + zzhj.zzc(object0, v4);
                    break;
                }
                case 13: {
                    v1 = v1 * 53 + zzhj.zzc(object0, v4);
                    break;
                }
                case 14: {
                    v6 = zzhj.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 15: {
                    v1 = v1 * 53 + zzhj.zzc(object0, v4);
                    break;
                }
                case 16: {
                    v6 = zzhj.zzd(object0, v4);
                    v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    break;
                }
                case 17: {
                    Object object2 = zzhj.zzf(object0, v4);
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
                    v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    break;
                }
                case 50: {
                    v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    break;
                }
                case 51: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = Double.doubleToLongBits(((double)(((Double)zzhj.zzf(object0, v4)))));
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 52: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + Float.floatToIntBits(((float)(((Float)zzhj.zzf(object0, v4)))));
                    }
                    break;
                }
                case 53: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = zzga.zzp(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 54: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = zzga.zzp(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 55: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 56: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = zzga.zzp(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 57: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 58: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzfa.zza(((Boolean)zzhj.zzf(object0, v4)).booleanValue());
                    }
                    break;
                }
                case 59: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + ((String)zzhj.zzf(object0, v4)).hashCode();
                    }
                    break;
                }
                case 60: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 61: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 62: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 0x40: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 65: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = zzga.zzp(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 66: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzga.zzk(object0, v4);
                    }
                    break;
                }
                case 67: {
                    if(this.zzI(object0, v3, v)) {
                        v6 = zzga.zzp(object0, v4);
                        v1 = v1 * 53 + ((int)(v6 ^ v6 >>> 0x20));
                    }
                    break;
                }
                case 68: {
                    if(this.zzI(object0, v3, v)) {
                        v1 = v1 * 53 + zzhj.zzf(object0, v4).hashCode();
                    }
                }
            }
        }
        return v1 * 53 + this.zzl.zzb(object0).hashCode();
    }

    final int zzb(Object object0, byte[] arr_b, int v, int v1, int v2, zzdt zzdt0) throws IOException {
        int v100;
        int v99;
        int v97;
        int v94;
        int v93;
        int v92;
        zzga zzga1;
        int v50;
        int v86;
        int v84;
        int v79;
        int v78;
        int v77;
        int v62;
        int v61;
        int v54;
        int v47;
        int v46;
        Unsafe unsafe2;
        int v26;
        Object object3;
        int v25;
        int v24;
        int v31;
        int v30;
        Object object5;
        byte[] arr_b3;
        Unsafe unsafe3;
        int v42;
        int v41;
        Object object7;
        Unsafe unsafe4;
        Object object2;
        zzdt zzdt2;
        int v15;
        int v14;
        int v13;
        Unsafe unsafe1;
        int v12;
        byte[] arr_b1 = arr_b;
        zzga zzga0 = this;
        Object object1 = object0;
        byte[] arr_b2 = arr_b;
        zzdt zzdt1 = zzdt0;
        zzga.zzw(object1);
        Unsafe unsafe0 = zzga.zzb;
        int v3 = v;
        int v4 = -1;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0xFFFFF;
        int v8 = 0;
        while(true) {
            if(v3 >= v1) {
                unsafe1 = unsafe0;
                object2 = object1;
                break;
            }
            int v9 = v3 + 1;
            int v10 = arr_b2[v3];
            if(v10 < 0) {
                v9 = zzdu.zzi(v10, arr_b2, v9, zzdt1);
                v10 = zzdt1.zza;
            }
            v3 = v9;
            int v11 = v10 >>> 3;
            if(v11 <= v4) {
                if(v11 >= zzga0.zze && v11 <= zzga0.zzf) {
                    v12 = zzga0.zzm(v11, 0);
                }
            }
            else if(v11 >= zzga0.zze && v11 <= zzga0.zzf) {
                v12 = zzga0.zzm(v11, v5 / 3);
            }
            else {
                v12 = -1;
            }
            if(v12 == -1) {
                unsafe1 = unsafe0;
                v13 = v10;
                arr_b1 = arr_b2;
                v14 = v7;
                v15 = 0;
                zzdt2 = zzdt1;
                object2 = object1;
            }
            else {
                int v16 = v10 & 7;
                int[] arr_v = zzga0.zzc;
                int v17 = arr_v[v12 + 1];
                int v18 = v17 >>> 20 & 0xFF;
                int v19 = v10;
                long v20 = (long)(v17 & 0xFFFFF);
                if(v18 <= 17) {
                    int v21 = arr_v[v12 + 2];
                    int v22 = 1 << (v21 >>> 20);
                    int v23 = v21 & 0xFFFFF;
                    if(v23 != v7) {
                        if(v7 != 0xFFFFF) {
                            unsafe0.putInt(object1, ((long)v7), v8);
                        }
                        v8 = v23 == 0xFFFFF ? 0 : unsafe0.getInt(object1, ((long)v23));
                        v7 = v23;
                    }
                    switch(v18) {
                        case 0: {
                            zzdt1 = zzdt0;
                            v24 = v3;
                            v15 = v12;
                            v26 = v8;
                            unsafe2 = unsafe0;
                            object3 = object1;
                            v25 = v7;
                            if(v16 == 1) {
                                zzhj.zzl(object3, v20, Double.longBitsToDouble(zzdu.zzn(arr_b2, v24)));
                                v3 = v24 + 8;
                                v8 = v26 | v22;
                                unsafe0 = unsafe2;
                            label_82:
                                v5 = v15;
                                v4 = v11;
                                object1 = object3;
                                v6 = v19;
                                v7 = v25;
                                continue;
                            }
                            v3 = v24;
                            break;
                        }
                        case 1: {
                            zzdt1 = zzdt0;
                            v24 = v3;
                            v15 = v12;
                            v26 = v8;
                            unsafe2 = unsafe0;
                            object3 = object1;
                            v25 = v7;
                            if(v16 == 5) {
                                zzhj.zzm(object3, v20, Float.intBitsToFloat(zzdu.zzb(arr_b2, v24)));
                                v3 = v24 + 4;
                                v8 = v26 | v22;
                                unsafe0 = unsafe2;
                                v5 = v15;
                                v4 = v11;
                                object1 = object3;
                                v6 = v19;
                                v7 = v25;
                                continue;
                            }
                            v3 = v24;
                            break;
                        }
                        case 2: 
                        case 3: {
                            zzdt1 = zzdt0;
                            v24 = v3;
                            v15 = v12;
                            v25 = v7;
                            v26 = v8;
                            if(v16 == 0) {
                                int v28 = zzdu.zzk(arr_b2, v24, zzdt1);
                                unsafe0.putLong(object1, v20, zzdt1.zzb);
                                v8 = v26 | v22;
                                v5 = v15;
                                v3 = v28;
                                v4 = v11;
                                v6 = v19;
                                v7 = v25;
                                continue;
                            }
                            unsafe2 = unsafe0;
                            object3 = object1;
                            v3 = v24;
                            break;
                        }
                        case 7: {
                            unsafe3 = unsafe0;
                            arr_b3 = arr_b2;
                            v15 = v12;
                            object5 = object1;
                            v30 = v3;
                            v26 = v8;
                            v25 = v7;
                            if(v16 == 0) {
                                v31 = zzdu.zzk(arr_b3, v30, zzdt0);
                                zzhj.zzk(object5, v20, zzdt0.zzb != 0L);
                                goto label_238;
                            }
                            v3 = v30;
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 8: {
                            unsafe3 = unsafe0;
                            arr_b3 = arr_b2;
                            v15 = v12;
                            object5 = object1;
                            v30 = v3;
                            v26 = v8;
                            if(v16 == 2) {
                                if((v17 & 0x20000000) == 0) {
                                    v25 = v7;
                                    v31 = zzdu.zzh(arr_b3, v30, zzdt0);
                                    int v39 = zzdt0.zza;
                                    if(v39 >= 0) {
                                        if(v39 == 0) {
                                            zzdt0.zzc = "";
                                        }
                                        else {
                                            zzdt0.zzc = new String(arr_b3, v31, v39, zzfa.zzb);
                                            v31 += v39;
                                        }
                                    label_237:
                                        unsafe3.putObject(object5, v20, zzdt0.zzc);
                                    label_238:
                                        v8 = v26 | v22;
                                        arr_b2 = arr_b3;
                                        unsafe0 = unsafe3;
                                        zzdt1 = zzdt0;
                                        v3 = v31;
                                        object1 = object5;
                                    label_244:
                                        v5 = v15;
                                        v4 = v11;
                                        v6 = v19;
                                        v7 = v25;
                                        continue;
                                    }
                                }
                                else {
                                    v31 = zzdu.zzh(arr_b3, v30, zzdt0);
                                    int v32 = zzdt0.zza;
                                    if(v32 >= 0) {
                                        if(v32 == 0) {
                                            zzdt0.zzc = "";
                                            v25 = v7;
                                        }
                                        else {
                                            if((arr_b3.length - v31 - v32 | (v31 | v32)) < 0) {
                                                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", ((int)arr_b3.length), v31, v32));
                                            }
                                            int v33 = v31 + v32;
                                            char[] arr_c = new char[v32];
                                            int v34;
                                            for(v34 = 0; v31 < v33; ++v34) {
                                                int v35 = arr_b3[v31];
                                                if(!zzhk.zzd(((byte)v35))) {
                                                    break;
                                                }
                                                ++v31;
                                                arr_c[v34] = (char)v35;
                                            }
                                        label_188:
                                            while(v31 < v33) {
                                                int v36 = arr_b3[v31];
                                                if(zzhk.zzd(((byte)v36))) {
                                                    int v37 = v34 + 1;
                                                    arr_c[v34] = (char)v36;
                                                    ++v31;
                                                    while(true) {
                                                        v34 = v37;
                                                        if(v31 >= v33) {
                                                            continue label_188;
                                                        }
                                                        int v38 = arr_b3[v31];
                                                        if(!zzhk.zzd(((byte)v38))) {
                                                            continue label_188;
                                                        }
                                                        ++v31;
                                                        v37 = v34 + 1;
                                                        arr_c[v34] = (char)v38;
                                                    }
                                                }
                                                if(v36 < 0xFFFFFFE0) {
                                                    if(v31 + 1 < v33) {
                                                        zzhk.zzc(((byte)v36), arr_b3[v31 + 1], arr_c, v34);
                                                        v31 += 2;
                                                        ++v34;
                                                        continue;
                                                    }
                                                }
                                                else if(v36 >= -16) {
                                                    if(v31 + 1 < v33 - 2) {
                                                        zzhk.zza(((byte)v36), arr_b3[v31 + 1], arr_b3[v31 + 2], arr_b3[v31 + 3], arr_c, v34);
                                                        v34 += 2;
                                                        v31 += 4;
                                                        continue;
                                                    }
                                                }
                                                else if(v31 + 1 < v33 - 1) {
                                                    zzhk.zzb(((byte)v36), arr_b3[v31 + 1], arr_b3[v31 + 2], arr_c, v34);
                                                    v31 += 3;
                                                    ++v34;
                                                    continue;
                                                }
                                                throw zzfb.zzb();
                                            }
                                            v25 = v7;
                                            zzdt0.zzc = new String(arr_c, 0, v34);
                                            v31 = v33;
                                        }
                                        goto label_237;
                                    }
                                }
                                throw zzfb.zzc();
                            }
                            else {
                                v25 = v7;
                            }
                            v3 = v30;
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 9: {
                            v15 = v12;
                            v26 = v8;
                            if(v16 == 2) {
                                Object object6 = zzga0.zzt(object1, v15);
                                int v40 = zzdu.zzm(object6, zzga0.zzr(v15), arr_b2, v3, v1, zzdt0);
                                zzga0.zzB(object1, v15, object6);
                                v8 = v26 | v22;
                                zzdt1 = zzdt0;
                                v3 = v40;
                                v5 = v15;
                                v4 = v11;
                                v6 = v19;
                                continue;
                            }
                            else {
                                object3 = object1;
                                v25 = v7;
                                unsafe2 = unsafe0;
                                break;
                            }
                            goto label_269;
                        }
                        case 10: {
                        label_269:
                            unsafe4 = unsafe0;
                            object7 = object1;
                            zzdt1 = zzdt0;
                            v41 = v7;
                            v15 = v12;
                            v42 = v19;
                            v26 = v8;
                            if(v16 == 2) {
                                v3 = zzdu.zza(arr_b2, v3, zzdt1);
                                unsafe4.putObject(object7, v20, zzdt1.zzc);
                                goto label_302;
                            }
                            goto label_310;
                        }
                        case 4: 
                        case 11: {
                            zzdt1 = zzdt0;
                            v24 = v3;
                            v15 = v12;
                            v25 = v7;
                            v26 = v8;
                            if(v16 == 0) {
                                int v29 = zzdu.zzh(arr_b2, v24, zzdt1);
                                unsafe0.putInt(object1, v20, zzdt1.zza);
                                v8 = v26 | v22;
                                v3 = v29;
                                goto label_244;
                            }
                            unsafe2 = unsafe0;
                            object3 = object1;
                            v3 = v24;
                            break;
                        }
                        case 12: {
                            unsafe4 = unsafe0;
                            object7 = object1;
                            zzdt1 = zzdt0;
                            v41 = v7;
                            v15 = v12;
                            v42 = v19;
                            v26 = v8;
                            if(v16 == 0) {
                                v3 = zzdu.zzh(arr_b2, v3, zzdt1);
                                int v43 = zzdt1.zza;
                                zzey zzey0 = zzga0.zzq(v15);
                                if((v17 & 0x80000000) == 0 || zzey0 == null || zzey0.zza()) {
                                    unsafe4.putInt(object7, v20, v43);
                                }
                                else {
                                    zzga.zzc(object7).zzh(v42, ((long)v43));
                                    object1 = object7;
                                    unsafe0 = unsafe4;
                                    v6 = v42;
                                    v7 = v41;
                                    v5 = v15;
                                    v4 = v11;
                                    v8 = v26;
                                    continue;
                                }
                            label_302:
                                v8 = v26 | v22;
                                object1 = object7;
                                unsafe0 = unsafe4;
                                v6 = v42;
                                v7 = v41;
                                v5 = v15;
                                v4 = v11;
                                continue;
                            }
                        label_310:
                            object3 = object7;
                            unsafe2 = unsafe4;
                            v19 = v42;
                            v25 = v41;
                            break;
                        }
                        case 6: 
                        case 13: {
                        label_140:
                            unsafe3 = unsafe0;
                            arr_b3 = arr_b2;
                            v15 = v12;
                            object5 = object1;
                            v30 = v3;
                            v26 = v8;
                            v25 = v7;
                            if(v16 == 5) {
                                unsafe3.putInt(object5, v20, zzdu.zzb(arr_b3, v30));
                                v31 = v30 + 4;
                                goto label_238;
                            }
                            v3 = v30;
                            object3 = object5;
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 5: 
                        case 14: {
                            v15 = v12;
                            v26 = v8;
                            v25 = v7;
                            if(v16 == 1) {
                                zzdt1 = zzdt0;
                                unsafe0.putLong(object1, v20, zzdu.zzn(arr_b2, v3));
                                v3 += 8;
                                v8 = v26 | v22;
                                goto label_244;
                            }
                            else {
                                unsafe2 = unsafe0;
                                object3 = object1;
                                break;
                            }
                            goto label_140;
                        }
                        case 15: {
                            zzdt1 = zzdt0;
                            v15 = v12;
                            v26 = v8;
                            if(v16 == 0) {
                                v3 = zzdu.zzh(arr_b2, v3, zzdt1);
                                unsafe0.putInt(object1, v20, zzdt1.zza >>> 1 ^ -(zzdt1.zza & 1));
                                v8 = v26 | v22;
                                v5 = v15;
                                v4 = v11;
                                v6 = v19;
                                continue;
                            }
                            else {
                                object3 = object1;
                                unsafe2 = unsafe0;
                                v25 = v7;
                                break;
                            }
                            goto label_327;
                        }
                        case 16: {
                        label_327:
                            if(v16 == 0) {
                                zzdt1 = zzdt0;
                                int v44 = zzdu.zzk(arr_b2, v3, zzdt1);
                                v15 = v12;
                                unsafe0.putLong(object1, v20, zzdt1.zzb >>> 1 ^ -(1L & zzdt1.zzb));
                                v8 |= v22;
                                v3 = v44;
                                v5 = v15;
                                v4 = v11;
                                v6 = v19;
                                continue;
                            }
                            else {
                                v15 = v12;
                                v25 = v7;
                                v26 = v8;
                                object3 = object1;
                                unsafe2 = unsafe0;
                            }
                            break;
                        }
                        default: {
                            v24 = v3;
                            v25 = v7;
                            v15 = v12;
                            object3 = object1;
                            v26 = v8;
                            unsafe2 = unsafe0;
                            if(v16 == 3) {
                                Object object4 = zzga0.zzt(object3, v15);
                                int v27 = zzdu.zzl(object4, zzga0.zzr(v15), arr_b2, v24, v1, v11 << 3 | 4, zzdt0);
                                zzga0.zzB(object3, v15, object4);
                                v8 = v26 | v22;
                                unsafe0 = unsafe2;
                                arr_b2 = arr_b;
                                zzdt1 = zzdt0;
                                v3 = v27;
                                goto label_82;
                            }
                            v3 = v24;
                            break;
                        }
                    }
                    object2 = object3;
                    unsafe1 = unsafe2;
                    v8 = v26;
                    v13 = v19;
                    v14 = v25;
                    zzdt2 = zzdt0;
                }
                else {
                    object2 = object1;
                    v15 = v12;
                    if(v18 == 27) {
                        if(v16 == 2) {
                            zzez zzez0 = (zzez)unsafe0.getObject(object2, v20);
                            if(!zzez0.zzc()) {
                                int v45 = zzez0.size();
                                zzez0 = zzez0.zzd((v45 == 0 ? 10 : v45 + v45));
                                unsafe0.putObject(object2, v20, zzez0);
                            }
                            arr_b2 = arr_b;
                            v6 = v19;
                            v5 = v15;
                            object1 = object2;
                            v4 = v11;
                            zzdt1 = zzdt0;
                            v3 = zzdu.zze(zzga0.zzr(v15), v19, arr_b, v3, v1, zzez0, zzdt0);
                            continue;
                        }
                        else {
                            unsafe1 = unsafe0;
                            v14 = v7;
                            v46 = v8;
                            v13 = v19;
                            v47 = v3;
                            goto label_789;
                        }
                        goto label_373;
                    }
                    else {
                    label_373:
                        unsafe1 = unsafe0;
                        v14 = v7;
                        v46 = v8;
                        v47 = v3;
                        int v48 = v19;
                        if(v18 <= 49) {
                            Unsafe unsafe5 = zzga.zzb;
                            zzez zzez1 = (zzez)unsafe5.getObject(object2, v20);
                            if(!zzez1.zzc()) {
                                int v49 = zzez1.size();
                                zzez1 = zzez1.zzd((v49 == 0 ? 10 : v49 + v49));
                                unsafe5.putObject(object2, v20, zzez1);
                            }
                        alab4:
                            switch(v18) {
                                case 26: {
                                    v61 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        if((((long)v17) & 0x20000000L) == 0L) {
                                            v62 = zzdu.zzh(arr_b, v50, zzdt0);
                                            int v69 = zzdt0.zza;
                                            if(v69 >= 0) {
                                                if(v69 == 0) {
                                                    zzez1.add("");
                                                    goto label_577;
                                                }
                                                else {
                                                    zzez1.add(new String(arr_b, v62, v69, zzfa.zzb));
                                                }
                                            alab1:
                                                while(true) {
                                                    v62 += v69;
                                                    while(true) {
                                                    label_577:
                                                        if(v62 >= v1) {
                                                            goto label_615;
                                                        }
                                                        int v70 = zzdu.zzh(arr_b, v62, zzdt0);
                                                        if(v61 != zzdt0.zza) {
                                                            goto label_615;
                                                        }
                                                        v62 = zzdu.zzh(arr_b, v70, zzdt0);
                                                        v69 = zzdt0.zza;
                                                        if(v69 < 0) {
                                                            break alab1;
                                                        }
                                                        if(v69 != 0) {
                                                            zzez1.add(new String(arr_b, v62, v69, zzfa.zzb));
                                                            break;
                                                        }
                                                        zzez1.add("");
                                                    }
                                                }
                                                throw zzfb.zzc();
                                            }
                                        }
                                        else {
                                            v62 = zzdu.zzh(arr_b, v50, zzdt0);
                                            int v71 = zzdt0.zza;
                                            if(v71 >= 0) {
                                                if(v71 == 0) {
                                                    zzez1.add("");
                                                    goto label_600;
                                                }
                                                else {
                                                    int v72 = v62 + v71;
                                                    if(!zzhn.zzc(arr_b, v62, v72)) {
                                                        throw zzfb.zzb();
                                                    }
                                                    zzez1.add(new String(arr_b, v62, v71, zzfa.zzb));
                                                alab2:
                                                    while(true) {
                                                        v62 = v72;
                                                        while(true) {
                                                        label_600:
                                                            if(v62 >= v1) {
                                                                goto label_615;
                                                            }
                                                            int v73 = zzdu.zzh(arr_b, v62, zzdt0);
                                                            if(v61 != zzdt0.zza) {
                                                                goto label_615;
                                                            }
                                                            v62 = zzdu.zzh(arr_b, v73, zzdt0);
                                                            int v74 = zzdt0.zza;
                                                            if(v74 < 0) {
                                                                throw zzfb.zzc();
                                                            }
                                                            if(v74 != 0) {
                                                                v72 = v62 + v74;
                                                                if(!zzhn.zzc(arr_b, v62, v72)) {
                                                                    break alab2;
                                                                }
                                                                zzez1.add(new String(arr_b, v62, v74, zzfa.zzb));
                                                                break;
                                                            }
                                                            zzez1.add("");
                                                        }
                                                    }
                                                    throw zzfb.zzb();
                                                label_615:
                                                    v3 = v62;
                                                    v13 = v61;
                                                label_617:
                                                    v47 = v50;
                                                    break;
                                                }
                                            }
                                        }
                                        throw zzfb.zzc();
                                    }
                                    v13 = v61;
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 28: {
                                    v61 = v48;
                                    if(v16 == 2) {
                                        v62 = zzdu.zzh(arr_b, v47, zzdt0);
                                        int v75 = zzdt0.zza;
                                        if(v75 < 0) {
                                            throw zzfb.zzc();
                                        }
                                        if(v75 > arr_b.length - v62) {
                                            throw zzfb.zzf();
                                        }
                                        if(v75 == 0) {
                                            zzez1.add(zzef.zzb);
                                            goto label_635;
                                        }
                                        else {
                                            zzez1.add(zzef.zzk(arr_b, v62, v75));
                                        }
                                    alab3:
                                        while(true) {
                                            v62 += v75;
                                            while(true) {
                                            label_635:
                                                if(v62 >= v1) {
                                                    goto label_649;
                                                }
                                                int v76 = zzdu.zzh(arr_b, v62, zzdt0);
                                                if(v61 != zzdt0.zza) {
                                                    goto label_649;
                                                }
                                                v62 = zzdu.zzh(arr_b, v76, zzdt0);
                                                v75 = zzdt0.zza;
                                                if(v75 < 0) {
                                                    throw zzfb.zzc();
                                                }
                                                if(v75 > arr_b.length - v62) {
                                                    break alab3;
                                                }
                                                if(v75 != 0) {
                                                    zzez1.add(zzef.zzk(arr_b, v62, v75));
                                                    break;
                                                }
                                                zzez1.add(zzef.zzb);
                                            }
                                        }
                                        throw zzfb.zzf();
                                    label_649:
                                        zzga1 = this;
                                        v3 = v62;
                                        v13 = v61;
                                        break;
                                    }
                                    else {
                                        zzga1 = this;
                                        v13 = v61;
                                        v3 = v47;
                                        break alab4;
                                    }
                                    goto label_658;
                                }
                                case 18: 
                                case 35: {
                                    v13 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzek zzek0 = (zzek)zzez1;
                                        v54 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v55 = zzdt0.zza + v54;
                                        while(v54 < v55) {
                                            zzek0.zze(Double.longBitsToDouble(zzdu.zzn(arr_b, v54)));
                                            v54 += 8;
                                        }
                                        if(v54 != v55) {
                                            throw zzfb.zzf();
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    else if(v16 == 1) {
                                        zzek zzek1 = (zzek)zzez1;
                                        zzek1.zze(Double.longBitsToDouble(zzdu.zzn(arr_b, v50)));
                                        for(v54 = v50 + 8; v54 < v1; v54 = v56 + 8) {
                                            int v56 = zzdu.zzh(arr_b, v54, zzdt0);
                                            if(v13 != zzdt0.zza) {
                                                break;
                                            }
                                            zzek1.zze(Double.longBitsToDouble(zzdu.zzn(arr_b, v56)));
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 19: 
                                case 36: {
                                    v13 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzer zzer0 = (zzer)zzez1;
                                        v54 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v57 = zzdt0.zza + v54;
                                        while(v54 < v57) {
                                            zzer0.zze(Float.intBitsToFloat(zzdu.zzb(arr_b, v54)));
                                            v54 += 4;
                                        }
                                        if(v54 != v57) {
                                            throw zzfb.zzf();
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    else if(v16 == 5) {
                                        zzer zzer1 = (zzer)zzez1;
                                        zzer1.zze(Float.intBitsToFloat(zzdu.zzb(arr_b, v50)));
                                        for(v54 = v50 + 4; v54 < v1; v54 = v58 + 4) {
                                            int v58 = zzdu.zzh(arr_b, v54, zzdt0);
                                            if(v13 != zzdt0.zza) {
                                                break;
                                            }
                                            zzer1.zze(Float.intBitsToFloat(zzdu.zzb(arr_b, v58)));
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 20: 
                                case 21: 
                                case 37: 
                                case 38: {
                                    v13 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzfm zzfm0 = (zzfm)zzez1;
                                        v54 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v59 = zzdt0.zza + v54;
                                        while(v54 < v59) {
                                            v54 = zzdu.zzk(arr_b, v54, zzdt0);
                                            zzfm0.zze(zzdt0.zzb);
                                        }
                                        if(v54 != v59) {
                                            throw zzfb.zzf();
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    else if(v16 == 0) {
                                        zzfm zzfm1 = (zzfm)zzez1;
                                        v54 = zzdu.zzk(arr_b, v50, zzdt0);
                                        zzfm1.zze(zzdt0.zzb);
                                        while(v54 < v1) {
                                            int v60 = zzdu.zzh(arr_b, v54, zzdt0);
                                            if(v13 != zzdt0.zza) {
                                                break;
                                            }
                                            v54 = zzdu.zzk(arr_b, v60, zzdt0);
                                            zzfm1.zze(zzdt0.zzb);
                                        }
                                        v3 = v54;
                                        goto label_617;
                                    }
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 25: 
                                case 42: {
                                    v61 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzdv zzdv0 = (zzdv)zzez1;
                                        v62 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v67 = zzdt0.zza + v62;
                                        while(v62 < v67) {
                                            v62 = zzdu.zzk(arr_b, v62, zzdt0);
                                            zzdv0.zze(zzdt0.zzb != 0L);
                                        }
                                        if(v62 != v67) {
                                            throw zzfb.zzf();
                                        }
                                        goto label_615;
                                    }
                                    else if(v16 == 0) {
                                        zzdv zzdv1 = (zzdv)zzez1;
                                        v62 = zzdu.zzk(arr_b, v50, zzdt0);
                                        zzdv1.zze(zzdt0.zzb != 0L);
                                        while(v62 < v1) {
                                            int v68 = zzdu.zzh(arr_b, v62, zzdt0);
                                            if(v61 != zzdt0.zza) {
                                                break;
                                            }
                                            v62 = zzdu.zzk(arr_b, v68, zzdt0);
                                            zzdv1.zze(zzdt0.zzb != 0L);
                                        }
                                        goto label_615;
                                    }
                                    v13 = v61;
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 22: 
                                case 29: 
                                case 39: 
                                case 43: {
                                    v61 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        v62 = zzdu.zzf(arr_b, v50, zzez1, zzdt0);
                                        goto label_615;
                                    }
                                    else if(v16 == 0) {
                                        v13 = v61;
                                        v3 = zzdu.zzj(v61, arr_b, v50, v1, zzez1, zzdt0);
                                        goto label_617;
                                    }
                                    v13 = v61;
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 30: 
                                case 44: {
                                label_658:
                                    switch(v16) {
                                        case 0: {
                                            v77 = v48;
                                            v78 = zzdu.zzj(v77, arr_b, v47, v1, zzez1, zzdt0);
                                            v79 = v47;
                                            break;
                                        }
                                        case 2: {
                                            v78 = zzdu.zzf(arr_b, v47, zzez1, zzdt0);
                                            v77 = v48;
                                            v79 = v47;
                                            break;
                                        }
                                        default: {
                                            zzga1 = this;
                                            v13 = v48;
                                            v3 = v47;
                                            break alab4;
                                        }
                                    }
                                    zzey zzey1 = zzga0.zzq(v15);
                                    zzgz zzgz0 = zzga0.zzl;
                                    if(zzey1 == null) {
                                        v84 = v78;
                                    }
                                    else if(zzez1 instanceof RandomAccess) {
                                        int v80 = zzez1.size();
                                        zzey zzey2 = zzey1;
                                        Object object8 = null;
                                        int v82 = 0;
                                        for(int v81 = 0; v81 < v80; ++v81) {
                                            Integer integer0 = (Integer)zzez1.get(v81);
                                            int v83 = (int)integer0;
                                            if(zzey2.zza()) {
                                                if(v81 != v82) {
                                                    zzez1.set(v82, integer0);
                                                }
                                                ++v82;
                                            }
                                            else {
                                                object8 = zzgk.zzc(object2, v11, v83, object8, zzgz0);
                                            }
                                        }
                                        v84 = v78;
                                        if(v82 != v80) {
                                            zzez1.subList(v82, v80).clear();
                                        }
                                    }
                                    else {
                                        zzey zzey3 = zzey1;
                                        v84 = v78;
                                        Object object9 = null;
                                        Iterator iterator0 = zzez1.iterator();
                                        while(iterator0.hasNext()) {
                                            Object object10 = iterator0.next();
                                            int v85 = (int)(((Integer)object10));
                                            if(!zzey3.zza()) {
                                                object9 = zzgk.zzc(object2, v11, v85, object9, zzgz0);
                                                iterator0.remove();
                                            }
                                        }
                                    }
                                    zzga1 = this;
                                    v13 = v77;
                                    v47 = v79;
                                    v3 = v84;
                                    break;
                                }
                                case 24: 
                                case 0x1F: 
                                case 41: 
                                case 45: {
                                    v61 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzew zzew0 = (zzew)zzez1;
                                        v62 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v65 = zzdt0.zza + v62;
                                        while(v62 < v65) {
                                            zzew0.zze(zzdu.zzb(arr_b, v62));
                                            v62 += 4;
                                        }
                                        if(v62 != v65) {
                                            throw zzfb.zzf();
                                        }
                                        goto label_615;
                                    }
                                    else if(v16 == 5) {
                                        zzew zzew1 = (zzew)zzez1;
                                        zzew1.zze(zzdu.zzb(arr_b, v50));
                                        for(v62 = v50 + 4; v62 < v1; v62 = v66 + 4) {
                                            int v66 = zzdu.zzh(arr_b, v62, zzdt0);
                                            if(v61 != zzdt0.zza) {
                                                break;
                                            }
                                            zzew1.zze(zzdu.zzb(arr_b, v66));
                                        }
                                        goto label_615;
                                    }
                                    v13 = v61;
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 23: 
                                case 0x20: 
                                case 40: 
                                case 46: {
                                    v61 = v48;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 2) {
                                        zzfm zzfm2 = (zzfm)zzez1;
                                        v62 = zzdu.zzh(arr_b, v50, zzdt0);
                                        int v63 = zzdt0.zza + v62;
                                        while(v62 < v63) {
                                            zzfm2.zze(zzdu.zzn(arr_b, v62));
                                            v62 += 8;
                                        }
                                        if(v62 != v63) {
                                            throw zzfb.zzf();
                                        }
                                        goto label_615;
                                    }
                                    else if(v16 == 1) {
                                        zzfm zzfm3 = (zzfm)zzez1;
                                        zzfm3.zze(zzdu.zzn(arr_b, v50));
                                        for(v62 = v50 + 8; v62 < v1; v62 = v64 + 8) {
                                            int v64 = zzdu.zzh(arr_b, v62, zzdt0);
                                            if(v61 != zzdt0.zza) {
                                                break;
                                            }
                                            zzfm3.zze(zzdu.zzn(arr_b, v64));
                                        }
                                        goto label_615;
                                    }
                                    v13 = v61;
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                                case 33: 
                                case 0x2F: {
                                    switch(v16) {
                                        case 0: {
                                            zzew zzew2 = (zzew)zzez1;
                                            v86 = zzdu.zzh(arr_b, v47, zzdt0);
                                            zzew2.zze(zzdt0.zza >>> 1 ^ -(zzdt0.zza & 1));
                                            while(v86 < v1) {
                                                int v87 = zzdu.zzh(arr_b, v86, zzdt0);
                                                if(v48 != zzdt0.zza) {
                                                    break;
                                                }
                                                v86 = zzdu.zzh(arr_b, v87, zzdt0);
                                                zzew2.zze(zzdt0.zza >>> 1 ^ -(zzdt0.zza & 1));
                                            }
                                            goto label_759;
                                        }
                                        case 2: {
                                            zzew zzew3 = (zzew)zzez1;
                                            v86 = zzdu.zzh(arr_b, v47, zzdt0);
                                            int v88 = zzdt0.zza + v86;
                                            while(v86 < v88) {
                                                v86 = zzdu.zzh(arr_b, v86, zzdt0);
                                                zzew3.zze(zzdt0.zza >>> 1 ^ -(zzdt0.zza & 1));
                                            }
                                            if(v86 != v88) {
                                                throw zzfb.zzf();
                                            }
                                            goto label_759;
                                        }
                                        default: {
                                            zzga1 = zzga0;
                                            v13 = v48;
                                            v3 = v47;
                                            break alab4;
                                        }
                                    }
                                }
                                case 34: 
                                case 0x30: {
                                    switch(v16) {
                                        case 0: {
                                            zzfm zzfm4 = (zzfm)zzez1;
                                            v86 = zzdu.zzk(arr_b, v47, zzdt0);
                                            zzfm4.zze(zzdt0.zzb >>> 1 ^ -(1L & zzdt0.zzb));
                                            while(v86 < v1) {
                                                int v89 = zzdu.zzh(arr_b, v86, zzdt0);
                                                if(v48 != zzdt0.zza) {
                                                    break;
                                                }
                                                v86 = zzdu.zzk(arr_b, v89, zzdt0);
                                                zzfm4.zze(zzdt0.zzb >>> 1 ^ -(1L & zzdt0.zzb));
                                            }
                                            break;
                                        }
                                        case 2: {
                                            zzfm zzfm5 = (zzfm)zzez1;
                                            v86 = zzdu.zzh(arr_b, v47, zzdt0);
                                            int v90 = zzdt0.zza + v86;
                                            while(v86 < v90) {
                                                v86 = zzdu.zzk(arr_b, v86, zzdt0);
                                                zzfm5.zze(zzdt0.zzb >>> 1 ^ -(1L & zzdt0.zzb));
                                            }
                                            if(v86 != v90) {
                                                throw zzfb.zzf();
                                            }
                                            break;
                                        }
                                        default: {
                                            zzga1 = zzga0;
                                            v13 = v48;
                                            v3 = v47;
                                            break alab4;
                                        }
                                    }
                                label_759:
                                    zzga1 = zzga0;
                                    v13 = v48;
                                    v3 = v86;
                                    break;
                                }
                                default: {
                                    v13 = v48;
                                    zzez zzez2 = zzez1;
                                    v50 = v47;
                                    zzga1 = zzga0;
                                    if(v16 == 3) {
                                        zzgi zzgi0 = zzga1.zzr(v15);
                                        int v51 = v13 & -8 | 4;
                                        int v52 = zzdu.zzc(zzgi0, arr_b, v50, v1, v51, zzdt0);
                                        v47 = v50;
                                        zzez2.add(zzdt0.zzc);
                                        while(v52 < v1) {
                                            int v53 = zzdu.zzh(arr_b, v52, zzdt0);
                                            if(v13 != zzdt0.zza) {
                                                break;
                                            }
                                            v52 = zzdu.zzc(zzgi0, arr_b, v53, v1, v51, zzdt0);
                                            zzez2.add(zzdt0.zzc);
                                        }
                                        v3 = v52;
                                        break;
                                    }
                                    v47 = v50;
                                    v3 = v47;
                                    break alab4;
                                }
                            }
                            if(v3 == v47) {
                                zzdt2 = zzdt0;
                                v8 = v46;
                                goto label_955;
                            }
                            else {
                                arr_b2 = arr_b;
                                v5 = v15;
                                object1 = object2;
                                zzga0 = zzga1;
                                v4 = v11;
                                v7 = v14;
                                v8 = v46;
                                unsafe0 = unsafe1;
                                zzdt1 = zzdt0;
                                v6 = v13;
                                continue;
                            }
                            goto label_777;
                        }
                        else {
                        label_777:
                            v13 = v48;
                            if(v18 == 50) {
                                if(v16 == 2) {
                                    Unsafe unsafe6 = zzga.zzb;
                                    Object object11 = zzga0.zzs(v15);
                                    Object object12 = unsafe6.getObject(object2, v20);
                                    if(!((zzfr)object12).zze()) {
                                        zzfr zzfr0 = zzfr.zza().zzb();
                                        zzfs.zza(zzfr0, object12);
                                        unsafe6.putObject(object2, v20, zzfr0);
                                    }
                                    zzfq zzfq0 = (zzfq)object11;
                                    throw null;
                                }
                            label_789:
                                v3 = v47;
                                v8 = v46;
                                zzdt2 = zzdt0;
                            }
                            else {
                                Unsafe unsafe7 = zzga.zzb;
                                long v91 = (long)(arr_v[v15 + 2] & 0xFFFFF);
                                switch(v18) {
                                    case 51: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 1) {
                                            unsafe7.putObject(object2, v20, Double.longBitsToDouble(zzdu.zzn(arr_b1, v93)));
                                            v94 = v93 + 8;
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 52: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 5) {
                                            unsafe7.putObject(object2, v20, Float.intBitsToFloat(zzdu.zzb(arr_b1, v93)));
                                            v94 = v93 + 4;
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 53: 
                                    case 54: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 0) {
                                            v94 = zzdu.zzk(arr_b1, v93, zzdt2);
                                            unsafe7.putObject(object2, v20, zzdt2.zzb);
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 58: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 0) {
                                            v94 = zzdu.zzk(arr_b1, v93, zzdt2);
                                            unsafe7.putObject(object2, v20, Boolean.valueOf(zzdt2.zzb != 0L));
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 59: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 2) {
                                            v94 = zzdu.zzh(arr_b1, v93, zzdt2);
                                            int v95 = zzdt2.zza;
                                            if(v95 == 0) {
                                                unsafe7.putObject(object2, v20, "");
                                            }
                                            else {
                                                if((v17 & 0x20000000) != 0 && !zzhn.zzc(arr_b1, v94, v94 + v95)) {
                                                    throw zzfb.zzb();
                                                }
                                                unsafe7.putObject(object2, v20, new String(arr_b1, v94, v95, zzfa.zzb));
                                                v94 += v95;
                                            }
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 60: {
                                        if(v16 == 2) {
                                            Object object13 = zzga0.zzu(object2, v11, v15);
                                            int v96 = zzdu.zzm(object13, zzga0.zzr(v15), arr_b1, v47, v1, zzdt0);
                                            zzdt2 = zzdt0;
                                            zzga0.zzC(object2, v11, v15, object13);
                                            v3 = v96;
                                            v92 = v15;
                                            v93 = v47;
                                            goto label_941;
                                        }
                                        else {
                                            zzdt2 = zzdt0;
                                            v92 = v15;
                                            v93 = v47;
                                            break;
                                        }
                                        goto label_883;
                                    }
                                    case 61: {
                                    label_883:
                                        if(v16 == 2) {
                                            v97 = zzdu.zza(arr_b1, v47, zzdt0);
                                            unsafe7.putObject(object2, v20, zzdt0.zzc);
                                            unsafe7.putInt(object2, v91, v11);
                                            goto label_932;
                                        }
                                        else {
                                            v92 = v15;
                                            v93 = v47;
                                            zzdt2 = zzdt0;
                                            break;
                                        }
                                        goto label_892;
                                    }
                                    case 55: 
                                    case 62: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 0) {
                                            v94 = zzdu.zzh(arr_b1, v93, zzdt2);
                                            unsafe7.putObject(object2, v20, zzdt2.zza);
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 0x3F: {
                                    label_892:
                                        v92 = v15;
                                        if(v16 == 0) {
                                            v97 = zzdu.zzh(arr_b1, v47, zzdt0);
                                            int v98 = zzdt0.zza;
                                            v15 = v92;
                                            zzey zzey4 = zzga0.zzq(v15);
                                            if(zzey4 == null || zzey4.zza()) {
                                                unsafe7.putObject(object2, v20, v98);
                                                unsafe7.putInt(object2, v91, v11);
                                            }
                                            else {
                                                zzga.zzc(object2).zzh(v13, ((long)v98));
                                            }
                                            goto label_932;
                                        }
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        break;
                                    }
                                    case 57: 
                                    case 0x40: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 5) {
                                            unsafe7.putObject(object2, v20, zzdu.zzb(arr_b1, v93));
                                            v94 = v93 + 4;
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 56: 
                                    case 65: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        if(v16 == 1) {
                                            unsafe7.putObject(object2, v20, zzdu.zzn(arr_b1, v93));
                                            v94 = v93 + 8;
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v94;
                                            goto label_941;
                                        }
                                        break;
                                    }
                                    case 66: {
                                        v99 = v13;
                                        v92 = v15;
                                        if(v16 == 0) {
                                            v100 = zzdu.zzh(arr_b1, v47, zzdt0);
                                            unsafe7.putObject(object2, v20, ((int)(zzdt0.zza >>> 1 ^ -(zzdt0.zza & 1))));
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v100;
                                            v93 = v47;
                                            v13 = v99;
                                            zzdt2 = zzdt0;
                                            goto label_941;
                                        }
                                        else {
                                            v93 = v47;
                                            v13 = v99;
                                            zzdt2 = zzdt0;
                                            break;
                                        }
                                        goto label_914;
                                    }
                                    case 67: {
                                    label_914:
                                        if(v16 == 0) {
                                            v100 = zzdu.zzk(arr_b1, v47, zzdt0);
                                            v99 = v13;
                                            v92 = v15;
                                            unsafe7.putObject(object2, v20, ((long)(zzdt0.zzb >>> 1 ^ -(1L & zzdt0.zzb))));
                                            unsafe7.putInt(object2, v91, v11);
                                            v3 = v100;
                                            v93 = v47;
                                            v13 = v99;
                                            zzdt2 = zzdt0;
                                            goto label_941;
                                        }
                                        else {
                                            v92 = v15;
                                        }
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        break;
                                    }
                                    case 68: {
                                        if(v16 == 3) {
                                            Object object14 = zzga0.zzu(object2, v11, v15);
                                            v97 = zzdu.zzl(object14, zzga0.zzr(v15), arr_b, v47, v1, v13 & -8 | 4, zzdt0);
                                            zzga0.zzC(object2, v11, v15, object14);
                                        label_932:
                                            v3 = v97;
                                            v92 = v15;
                                            v93 = v47;
                                            zzdt2 = zzdt0;
                                            goto label_941;
                                        }
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        break;
                                    }
                                    default: {
                                        v92 = v15;
                                        v93 = v47;
                                        zzdt2 = zzdt0;
                                        break;
                                    }
                                }
                                v3 = v93;
                            label_941:
                                if(v3 == v93) {
                                    v15 = v92;
                                    v8 = v46;
                                }
                                else {
                                    zzga0 = this;
                                    arr_b2 = arr_b1;
                                    v6 = v13;
                                    object1 = object2;
                                    v4 = v11;
                                    zzdt1 = zzdt2;
                                    v5 = v92;
                                    v7 = v14;
                                    v8 = v46;
                                    unsafe0 = unsafe1;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        label_955:
            if(v13 != v2 || v2 == 0) {
                v3 = zzdu.zzg(v13, arr_b1, v3, v1, zzga.zzc(object2), zzdt2);
                zzga0 = this;
                arr_b2 = arr_b;
                v6 = v13;
                v5 = v15;
                object1 = object2;
                v4 = v11;
                v7 = v14;
                unsafe0 = unsafe1;
                zzdt1 = zzdt0;
                continue;
            }
            else {
                v6 = v13;
                v7 = v14;
            }
            break;
        }
        if(v7 != 0xFFFFF) {
            unsafe1.putInt(object2, ((long)v7), v8);
        }
        int v101 = this.zzi;
        while(v101 < this.zzj) {
            int v102 = this.zzh[v101];
            int v103 = this.zzc[v102];
            Object object15 = zzhj.zzf(object2, ((long)(this.zzo(v102) & 0xFFFFF)));
            if(object15 == null || this.zzq(v102) == null) {
                ++v101;
                continue;
            }
            zzfr zzfr1 = (zzfr)object15;
            zzfq zzfq1 = (zzfq)this.zzs(v102);
            throw null;
        }
        if(v2 == 0) {
            if(v3 != v1) {
                throw zzfb.zzd();
            }
            return v3;
        }
        if(v3 > v1 || v6 != v2) {
            throw zzfb.zzd();
        }
        return v3;
    }

    static zzha zzc(Object object0) {
        zzha zzha0 = ((zzev)object0).zzc;
        if(zzha0 == zzha.zza()) {
            zzha0 = zzha.zzd();
            ((zzev)object0).zzc = zzha0;
        }
        return zzha0;
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final Object zzd() {
        return ((zzev)this.zzg).zzc();
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zze(Object object0) {
        if(!zzga.zzH(object0)) {
            return;
        }
        if(object0 instanceof zzev) {
            ((zzev)object0).zzl(0x7FFFFFFF);
            ((zzev)object0).zza = 0;
            ((zzev)object0).zzj();
        }
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v1 = this.zzo(v);
            long v2 = (long)(0xFFFFF & v1);
            switch(v1 >>> 20 & 0xFF) {
                case 9: 
                case 17: {
                    if(this.zzE(object0, v)) {
                        this.zzr(v).zze(zzga.zzb.getObject(object0, v2));
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
                    this.zzk.zza(object0, v2);
                    break;
                }
                case 50: {
                    Unsafe unsafe0 = zzga.zzb;
                    Object object1 = unsafe0.getObject(object0, v2);
                    if(object1 != null) {
                        ((zzfr)object1).zzc();
                        unsafe0.putObject(object0, v2, object1);
                    }
                    break;
                }
                case 60: 
                case 68: {
                    if(this.zzI(object0, this.zzc[v], v)) {
                        this.zzr(v).zze(zzga.zzb.getObject(object0, v2));
                    }
                }
            }
        }
        this.zzl.zze(object0);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zzf(Object object0, Object object1) {
        zzga.zzw(object0);
        object1.getClass();
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v1 = this.zzo(v);
            int v2 = this.zzc[v];
            long v3 = (long)(0xFFFFF & v1);
            switch(v1 >>> 20 & 0xFF) {
                case 0: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzl(object0, v3, zzhj.zza(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 1: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzm(object0, v3, zzhj.zzb(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 2: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzo(object0, v3, zzhj.zzd(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 3: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzo(object0, v3, zzhj.zzd(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 4: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 5: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzo(object0, v3, zzhj.zzd(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 6: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 7: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzk(object0, v3, zzhj.zzt(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 8: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzp(object0, v3, zzhj.zzf(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 9: {
                    this.zzx(object0, object1, v);
                    break;
                }
                case 10: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzp(object0, v3, zzhj.zzf(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 11: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 12: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 13: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 14: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzo(object0, v3, zzhj.zzd(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 15: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzn(object0, v3, zzhj.zzc(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 16: {
                    if(this.zzE(object1, v)) {
                        zzhj.zzo(object0, v3, zzhj.zzd(object1, v3));
                        this.zzz(object0, v);
                    }
                    break;
                }
                case 17: {
                    this.zzx(object0, object1, v);
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
                    this.zzk.zzb(object0, object1, v3);
                    break;
                }
                case 50: {
                    zzhj.zzp(object0, v3, zzfs.zza(zzhj.zzf(object0, v3), zzhj.zzf(object1, v3)));
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
                    if(this.zzI(object1, v2, v)) {
                        zzhj.zzp(object0, v3, zzhj.zzf(object1, v3));
                        this.zzA(object0, v2, v);
                    }
                    break;
                }
                case 60: {
                    this.zzy(object0, object1, v);
                    break;
                }
                case 61: 
                case 62: 
                case 0x3F: 
                case 0x40: 
                case 65: 
                case 66: 
                case 67: {
                    if(this.zzI(object1, v2, v)) {
                        zzhj.zzp(object0, v3, zzhj.zzf(object1, v3));
                        this.zzA(object0, v2, v);
                    }
                    break;
                }
                case 68: {
                    this.zzy(object0, object1, v);
                }
            }
        }
        zzgk.zzd(this.zzl, object0, object1);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final void zzg(Object object0, byte[] arr_b, int v, int v1, zzdt zzdt0) throws IOException {
        this.zzb(object0, arr_b, v, v1, 0, zzdt0);
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final boolean zzh(Object object0, Object object1) {
        boolean z;
        int v = 0;
        while(v < this.zzc.length) {
            int v1 = this.zzo(v);
            long v2 = (long)(v1 & 0xFFFFF);
            switch(v1 >>> 20 & 0xFF) {
                case 0: {
                    if(!this.zzD(object0, object1, v) || Double.doubleToLongBits(zzhj.zza(object0, v2)) != Double.doubleToLongBits(zzhj.zza(object1, v2))) {
                        return false;
                    }
                    break;
                }
                case 1: {
                    if(this.zzD(object0, object1, v) && Float.floatToIntBits(zzhj.zzb(object0, v2)) == Float.floatToIntBits(zzhj.zzb(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 2: {
                    if(this.zzD(object0, object1, v) && zzhj.zzd(object0, v2) == zzhj.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 3: {
                    if(this.zzD(object0, object1, v) && zzhj.zzd(object0, v2) == zzhj.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 4: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 5: {
                    if(this.zzD(object0, object1, v) && zzhj.zzd(object0, v2) == zzhj.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 6: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 7: {
                    if(this.zzD(object0, object1, v) && zzhj.zzt(object0, v2) == zzhj.zzt(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 8: {
                    if(this.zzD(object0, object1, v) && zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 9: {
                    if(this.zzD(object0, object1, v) && zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 10: {
                    if(this.zzD(object0, object1, v) && zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2))) {
                        break;
                    }
                    return false;
                }
                case 11: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 12: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 13: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 14: {
                    if(this.zzD(object0, object1, v) && zzhj.zzd(object0, v2) == zzhj.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 15: {
                    if(this.zzD(object0, object1, v) && zzhj.zzc(object0, v2) == zzhj.zzc(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 16: {
                    if(this.zzD(object0, object1, v) && zzhj.zzd(object0, v2) == zzhj.zzd(object1, v2)) {
                        break;
                    }
                    return false;
                }
                case 17: {
                    if(this.zzD(object0, object1, v) && zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2))) {
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
                    z = zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2));
                    goto label_45;
                }
                case 50: {
                    z = zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2));
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
                    int v3 = this.zzl(v);
                    if(zzhj.zzc(object0, ((long)(v3 & 0xFFFFF))) != zzhj.zzc(object1, ((long)(v3 & 0xFFFFF))) || !zzgk.zzf(zzhj.zzf(object0, v2), zzhj.zzf(object1, v2))) {
                        return false;
                    }
                }
            }
            v += 3;
        }
        return this.zzl.zzb(object0).equals(this.zzl.zzb(object1));
    }

    @Override  // com.google.android.gms.internal.auth.zzgi
    public final boolean zzi(Object object0) {
        int v10;
        int v9;
        int v = 0;
        int v1 = 0xFFFFF;
        for(int v2 = 0; v < this.zzi; v2 = v9) {
            int v3 = this.zzh[v];
            int v4 = this.zzc[v3];
            int v5 = this.zzo(v3);
            int v6 = this.zzc[v3 + 2];
            int v7 = v6 & 0xFFFFF;
            int v8 = 1 << (v6 >>> 20);
            if(v7 == v1) {
                v10 = v1;
                v9 = v2;
            }
            else {
                if(v7 != 0xFFFFF) {
                    v2 = zzga.zzb.getInt(object0, ((long)v7));
                }
                v9 = v2;
                v10 = v7;
            }
            if((0x10000000 & v5) != 0 && !this.zzF(object0, v3, v10, v9, v8)) {
                return false;
            }
            switch(v5 >>> 20 & 0xFF) {
                case 9: 
                case 17: {
                    if(this.zzF(object0, v3, v10, v9, v8) && !zzga.zzG(object0, v5, this.zzr(v3))) {
                        return false;
                    }
                    break;
                }
                case 27: 
                case 49: {
                    List list0 = (List)zzhj.zzf(object0, ((long)(v5 & 0xFFFFF)));
                    if(!list0.isEmpty()) {
                        zzgi zzgi0 = this.zzr(v3);
                        for(int v11 = 0; v11 < list0.size(); ++v11) {
                            if(!zzgi0.zzi(list0.get(v11))) {
                                return false;
                            }
                        }
                    }
                    break;
                }
                case 50: {
                    if(!((zzfr)zzhj.zzf(object0, ((long)(v5 & 0xFFFFF)))).isEmpty()) {
                        zzfq zzfq0 = (zzfq)this.zzs(v3);
                        throw null;
                    }
                    break;
                }
                case 60: 
                case 68: {
                    if(this.zzI(object0, v4, v3) && !zzga.zzG(object0, v5, this.zzr(v3))) {
                        return false;
                    }
                }
            }
            ++v;
            v1 = v10;
        }
        return true;
    }

    static zzga zzj(Class class0, zzfu zzfu0, zzgc zzgc0, zzfl zzfl0, zzgz zzgz0, zzem zzem0, zzfs zzfs0) {
        Field field3;
        int v91;
        int v90;
        int v82;
        int v81;
        String s1;
        int v80;
        Field field1;
        int v79;
        int v78;
        Field field0;
        int v76;
        zzgh zzgh1;
        int v70;
        int v64;
        int v14;
        int v13;
        int v12;
        int v11;
        int v10;
        int v9;
        int v8;
        int[] arr_v;
        int v2;
        if(zzfu0 instanceof zzgh) {
            zzgh zzgh0 = (zzgh)zzfu0;
            String s = zzgh0.zzd();
            int v = s.length();
            if(s.charAt(0) >= 0xD800) {
                for(int v1 = 1; true; v1 = v2) {
                    v2 = v1 + 1;
                    if(s.charAt(v1) < 0xD800) {
                        break;
                    }
                }
            }
            else {
                v2 = 1;
            }
            int v3 = v2 + 1;
            int v4 = s.charAt(v2);
            if(v4 >= 0xD800) {
                int v5 = v4 & 0x1FFF;
                int v6 = 13;
                int v7;
                while((v7 = s.charAt(v3)) >= 0xD800) {
                    v5 |= (v7 & 0x1FFF) << v6;
                    v6 += 13;
                    ++v3;
                }
                v4 = v5 | v7 << v6;
                ++v3;
            }
            if(v4 == 0) {
                arr_v = zzga.zza;
                v8 = 0;
                v9 = 0;
                v10 = 0;
                v11 = 0;
                v12 = 0;
                v13 = 0;
                v14 = 0;
            }
            else {
                int v15 = v3 + 1;
                int v16 = s.charAt(v3);
                if(v16 >= 0xD800) {
                    int v17 = v16 & 0x1FFF;
                    int v18 = 13;
                    int v19;
                    while((v19 = s.charAt(v15)) >= 0xD800) {
                        v17 |= (v19 & 0x1FFF) << v18;
                        v18 += 13;
                        ++v15;
                    }
                    v16 = v17 | v19 << v18;
                    ++v15;
                }
                int v20 = v15 + 1;
                int v21 = s.charAt(v15);
                if(v21 >= 0xD800) {
                    int v22 = v21 & 0x1FFF;
                    int v23 = 13;
                    int v24;
                    while((v24 = s.charAt(v20)) >= 0xD800) {
                        v22 |= (v24 & 0x1FFF) << v23;
                        v23 += 13;
                        ++v20;
                    }
                    v21 = v22 | v24 << v23;
                    ++v20;
                }
                int v25 = v20 + 1;
                int v26 = s.charAt(v20);
                if(v26 >= 0xD800) {
                    int v27 = v26 & 0x1FFF;
                    int v28 = 13;
                    int v29;
                    while((v29 = s.charAt(v25)) >= 0xD800) {
                        v27 |= (v29 & 0x1FFF) << v28;
                        v28 += 13;
                        ++v25;
                    }
                    v26 = v27 | v29 << v28;
                    ++v25;
                }
                int v30 = v25 + 1;
                int v31 = s.charAt(v25);
                if(v31 >= 0xD800) {
                    int v32 = v31 & 0x1FFF;
                    int v33 = 13;
                    int v34;
                    while((v34 = s.charAt(v30)) >= 0xD800) {
                        v32 |= (v34 & 0x1FFF) << v33;
                        v33 += 13;
                        ++v30;
                    }
                    v31 = v32 | v34 << v33;
                    ++v30;
                }
                int v35 = v30 + 1;
                v10 = s.charAt(v30);
                if(v10 >= 0xD800) {
                    int v36 = v10 & 0x1FFF;
                    int v37 = 13;
                    int v38;
                    while((v38 = s.charAt(v35)) >= 0xD800) {
                        v36 |= (v38 & 0x1FFF) << v37;
                        v37 += 13;
                        ++v35;
                    }
                    v10 = v36 | v38 << v37;
                    ++v35;
                }
                int v39 = v35 + 1;
                int v40 = s.charAt(v35);
                if(v40 >= 0xD800) {
                    int v41 = v40 & 0x1FFF;
                    int v42 = 13;
                    int v43;
                    while((v43 = s.charAt(v39)) >= 0xD800) {
                        v41 |= (v43 & 0x1FFF) << v42;
                        v42 += 13;
                        ++v39;
                    }
                    v40 = v41 | v43 << v42;
                    ++v39;
                }
                int v44 = v39 + 1;
                int v45 = s.charAt(v39);
                if(v45 >= 0xD800) {
                    int v46 = v45 & 0x1FFF;
                    int v47 = 13;
                    int v48;
                    while((v48 = s.charAt(v44)) >= 0xD800) {
                        v46 |= (v48 & 0x1FFF) << v47;
                        v47 += 13;
                        ++v44;
                    }
                    v45 = v46 | v48 << v47;
                    ++v44;
                }
                int v49 = v44 + 1;
                int v50 = s.charAt(v44);
                if(v50 >= 0xD800) {
                    int v51 = v50 & 0x1FFF;
                    int v52 = 13;
                    int v53;
                    while((v53 = s.charAt(v49)) >= 0xD800) {
                        v51 |= (v53 & 0x1FFF) << v52;
                        v52 += 13;
                        ++v49;
                    }
                    v50 = v51 | v53 << v52;
                    ++v49;
                }
                v13 = v16 * 2 + v21;
                v11 = v26;
                v9 = v40;
                arr_v = new int[v50 + v40 + v45];
                v12 = v31;
                v14 = v50;
                v8 = v16;
                v3 = v49;
            }
            Unsafe unsafe0 = zzga.zzb;
            Object[] arr_object = zzgh0.zze();
            Class class1 = zzgh0.zza().getClass();
            int v54 = v14 + v9;
            int[] arr_v1 = new int[v10 * 3];
            Object[] arr_object1 = new Object[v10 + v10];
            int v55 = v14;
            int v56 = v54;
            int v57 = 0;
            int v58 = 0;
            while(v3 < v) {
                int v59 = s.charAt(v3);
                if(v59 >= 0xD800) {
                    int v60 = v59 & 0x1FFF;
                    int v61 = v3 + 1;
                    int v62 = 13;
                    int v63;
                    while((v63 = s.charAt(v61)) >= 0xD800) {
                        v60 |= (v63 & 0x1FFF) << v62;
                        v62 += 13;
                        ++v61;
                    }
                    v59 = v60 | v63 << v62;
                    v64 = v61 + 1;
                }
                else {
                    v64 = v3 + 1;
                }
                int v65 = s.charAt(v64);
                if(v65 >= 0xD800) {
                    int v66 = v65 & 0x1FFF;
                    int v67 = v64 + 1;
                    int v68 = 13;
                    int v69;
                    while((v69 = s.charAt(v67)) >= 0xD800) {
                        v66 |= (v69 & 0x1FFF) << v68;
                        v68 += 13;
                        ++v67;
                    }
                    v65 = v66 | v69 << v68;
                    v70 = v67 + 1;
                }
                else {
                    v70 = v64 + 1;
                }
                if((v65 & 0x400) != 0) {
                    arr_v[v57] = v58;
                    ++v57;
                }
                if((v65 & 0xFF) >= 51) {
                    int v71 = v70 + 1;
                    int v72 = s.charAt(v70);
                    zzgh1 = zzgh0;
                    if(v72 >= 0xD800) {
                        int v73 = v72 & 0x1FFF;
                        int v74 = 13;
                        int v75;
                        while((v75 = s.charAt(v71)) >= 0xD800) {
                            v73 |= (v75 & 0x1FFF) << v74;
                            v74 += 13;
                            ++v71;
                        }
                        v72 = v73 | v75 << v74;
                        ++v71;
                    }
                    v76 = v;
                    switch((v65 & 0xFF) - 51) {
                        case 12: {
                            if(zzgh1.zzc() == 1 || (v65 & 0x800) != 0) {
                                arr_object1[v58 / 3 + v58 / 3 + 1] = arr_object[v13];
                                ++v13;
                            }
                            break;
                        }
                        case 9: 
                        case 17: {
                            arr_object1[v58 / 3 + v58 / 3 + 1] = arr_object[v13];
                            ++v13;
                        }
                    }
                    int v77 = v72 + v72;
                    Object object0 = arr_object[v77];
                    if(object0 instanceof Field) {
                        field0 = (Field)object0;
                    }
                    else {
                        field0 = zzga.zzv(class1, ((String)object0));
                        arr_object[v77] = field0;
                    }
                    v78 = v59;
                    v79 = (int)unsafe0.objectFieldOffset(field0);
                    Object object1 = arr_object[v77 + 1];
                    if(object1 instanceof Field) {
                        field1 = (Field)object1;
                    }
                    else {
                        field1 = zzga.zzv(class1, ((String)object1));
                        arr_object[v77 + 1] = field1;
                    }
                    v80 = (int)unsafe0.objectFieldOffset(field1);
                    s1 = s;
                    v3 = v71;
                    v81 = 0;
                }
                else {
                    zzgh1 = zzgh0;
                    v76 = v;
                    v78 = v59;
                    Field field2 = zzga.zzv(class1, ((String)arr_object[v13]));
                    switch(v65 & 0xFF) {
                        case 9: 
                        case 17: {
                            v82 = v13 + 1;
                            arr_object1[v58 / 3 + v58 / 3 + 1] = field2.getType();
                            break;
                        }
                        case 12: 
                        case 30: 
                        case 44: {
                            v82 = v13 + 1;
                            if(zzgh1.zzc() == 1 || (v65 & 0x800) != 0) {
                                arr_object1[v58 / 3 + v58 / 3 + 1] = arr_object[v82];
                                v82 = v13 + 2;
                            }
                            break;
                        }
                        case 27: 
                        case 49: {
                            arr_object1[v58 / 3 + v58 / 3 + 1] = arr_object[v13 + 1];
                            v82 = v13 + 2;
                            break;
                        }
                        case 50: {
                            arr_v[v55] = v58;
                            v82 = v13 + 2;
                            int v83 = v58 / 3 + v58 / 3;
                            arr_object1[v83] = arr_object[v13 + 1];
                            if((v65 & 0x800) != 0) {
                                arr_object1[v83 + 1] = arr_object[v82];
                                v82 = v13 + 3;
                            }
                            ++v55;
                            break;
                        }
                        default: {
                            v82 = v13 + 1;
                        }
                    }
                    int v84 = (int)unsafe0.objectFieldOffset(field2);
                    int v85 = 0xFFFFF;
                    if((v65 & 0x1000) == 0 || (v65 & 0xFF) > 17) {
                        s1 = s;
                        v90 = v70;
                        v81 = 0;
                    }
                    else {
                        int v86 = v70 + 1;
                        int v87 = s.charAt(v70);
                        if(v87 >= 0xD800) {
                            int v88 = v87 & 0x1FFF;
                            int v89 = 13;
                            while(true) {
                                v90 = v86 + 1;
                                v91 = s.charAt(v86);
                                if(v91 < 0xD800) {
                                    break;
                                }
                                v88 |= (v91 & 0x1FFF) << v89;
                                v89 += 13;
                                v86 = v90;
                            }
                            v87 = v88 | v91 << v89;
                        }
                        else {
                            v90 = v86;
                        }
                        int v92 = v87 / 0x20 + v8 * 2;
                        Object object2 = arr_object[v92];
                        s1 = s;
                        if(object2 instanceof Field) {
                            field3 = (Field)object2;
                        }
                        else {
                            field3 = zzga.zzv(class1, ((String)object2));
                            arr_object[v92] = field3;
                        }
                        v81 = v87 % 0x20;
                        v85 = (int)unsafe0.objectFieldOffset(field3);
                    }
                    if((v65 & 0xFF) >= 18 && (v65 & 0xFF) <= 49) {
                        arr_v[v56] = v84;
                        ++v56;
                    }
                    v79 = v84;
                    v80 = v85;
                    v3 = v90;
                    v13 = v82;
                }
                arr_v1[v58] = v78;
                int v93 = v58 + 2;
                arr_v1[v58 + 1] = ((v65 & 0x200) == 0 ? 0 : 0x20000000) | ((v65 & 0x100) == 0 ? 0 : 0x10000000) | ((v65 & 0x800) == 0 ? 0 : 0x80000000) | (v65 & 0xFF) << 20 | v79;
                v58 += 3;
                arr_v1[v93] = v81 << 20 | v80;
                zzgh0 = zzgh1;
                s = s1;
                v = v76;
            }
            return new zzga(arr_v1, arr_object1, v11, v12, zzgh0.zza(), zzgh0.zzc(), false, arr_v, v14, v54, zzgc0, zzfl0, zzgz0, zzem0, zzfs0);
        }
        zzgw zzgw0 = (zzgw)zzfu0;
        throw null;
    }

    private static int zzk(Object object0, long v) {
        return (int)(((Integer)zzhj.zzf(object0, v)));
    }

    private final int zzl(int v) {
        return this.zzc[v + 2];
    }

    private final int zzm(int v, int v1) {
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

    private static int zzn(int v) [...] // Inlined contents

    private final int zzo(int v) {
        return this.zzc[v + 1];
    }

    private static long zzp(Object object0, long v) {
        return (long)(((Long)zzhj.zzf(object0, v)));
    }

    private final zzey zzq(int v) {
        return (zzey)this.zzd[v / 3 * 2 + 1];
    }

    private final zzgi zzr(int v) {
        int v1 = v / 3 + v / 3;
        zzgi zzgi0 = (zzgi)this.zzd[v1];
        if(zzgi0 != null) {
            return zzgi0;
        }
        zzgi zzgi1 = zzgf.zza().zzb(((Class)this.zzd[v1 + 1]));
        this.zzd[v1] = zzgi1;
        return zzgi1;
    }

    private final Object zzs(int v) {
        return this.zzd[v / 3 + v / 3];
    }

    private final Object zzt(Object object0, int v) {
        zzgi zzgi0 = this.zzr(v);
        int v1 = this.zzo(v);
        if(!this.zzE(object0, v)) {
            return zzgi0.zzd();
        }
        Object object1 = zzga.zzb.getObject(object0, ((long)(v1 & 0xFFFFF)));
        if(zzga.zzH(object1)) {
            return object1;
        }
        Object object2 = zzgi0.zzd();
        if(object1 != null) {
            zzgi0.zzf(object2, object1);
        }
        return object2;
    }

    private final Object zzu(Object object0, int v, int v1) {
        zzgi zzgi0 = this.zzr(v1);
        if(!this.zzI(object0, v, v1)) {
            return zzgi0.zzd();
        }
        int v2 = this.zzo(v1);
        Object object1 = zzga.zzb.getObject(object0, ((long)(v2 & 0xFFFFF)));
        if(zzga.zzH(object1)) {
            return object1;
        }
        Object object2 = zzgi0.zzd();
        if(object1 != null) {
            zzgi0.zzf(object2, object1);
        }
        return object2;
    }

    private static Field zzv(Class class0, String s) {
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

    private static void zzw(Object object0) {
        if(!zzga.zzH(object0)) {
            throw new IllegalArgumentException("Mutating immutable message: " + object0);
        }
    }

    private final void zzx(Object object0, Object object1, int v) {
        if(!this.zzE(object1, v)) {
            return;
        }
        int v1 = this.zzo(v);
        Unsafe unsafe0 = zzga.zzb;
        Object object2 = unsafe0.getObject(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[v] + " is present but null: " + object1.toString());
        }
        zzgi zzgi0 = this.zzr(v);
        if(!this.zzE(object0, v)) {
            if(zzga.zzH(object2)) {
                Object object3 = zzgi0.zzd();
                zzgi0.zzf(object3, object2);
                unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object3);
            }
            else {
                unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object2);
            }
            this.zzz(object0, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, ((long)(v1 & 0xFFFFF)));
        if(!zzga.zzH(object4)) {
            Object object5 = zzgi0.zzd();
            zzgi0.zzf(object5, object4);
            unsafe0.putObject(object0, ((long)(v1 & 0xFFFFF)), object5);
            object4 = object5;
        }
        zzgi0.zzf(object4, object2);
    }

    private final void zzy(Object object0, Object object1, int v) {
        int v1 = this.zzc[v];
        if(!this.zzI(object1, v1, v)) {
            return;
        }
        int v2 = this.zzo(v);
        Unsafe unsafe0 = zzga.zzb;
        Object object2 = unsafe0.getObject(object1, ((long)(v2 & 0xFFFFF)));
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.zzc[v] + " is present but null: " + object1.toString());
        }
        zzgi zzgi0 = this.zzr(v);
        if(!this.zzI(object0, v1, v)) {
            if(zzga.zzH(object2)) {
                Object object3 = zzgi0.zzd();
                zzgi0.zzf(object3, object2);
                unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object3);
            }
            else {
                unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object2);
            }
            this.zzA(object0, v1, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, ((long)(v2 & 0xFFFFF)));
        if(!zzga.zzH(object4)) {
            Object object5 = zzgi0.zzd();
            zzgi0.zzf(object5, object4);
            unsafe0.putObject(object0, ((long)(v2 & 0xFFFFF)), object5);
            object4 = object5;
        }
        zzgi0.zzf(object4, object2);
    }

    private final void zzz(Object object0, int v) {
        int v1 = this.zzl(v);
        if(((long)(0xFFFFF & v1)) == 0xFFFFFL) {
            return;
        }
        zzhj.zzn(object0, ((long)(0xFFFFF & v1)), 1 << (v1 >>> 20) | zzhj.zzc(object0, ((long)(0xFFFFF & v1))));
    }
}

