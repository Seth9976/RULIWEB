package com.google.android.gms.internal.auth;

import java.io.IOException;

final class zzdu {
    static int zza(byte[] arr_b, int v, zzdt zzdt0) throws zzfb {
        int v1 = zzdu.zzh(arr_b, v, zzdt0);
        int v2 = zzdt0.zza;
        if(v2 < 0) {
            throw zzfb.zzc();
        }
        if(v2 > arr_b.length - v1) {
            throw zzfb.zzf();
        }
        if(v2 == 0) {
            zzdt0.zzc = zzef.zzb;
            return v1;
        }
        zzdt0.zzc = zzef.zzk(arr_b, v1, v2);
        return v1 + v2;
    }

    static int zzb(byte[] arr_b, int v) {
        return (arr_b[v + 3] & 0xFF) << 24 | ((arr_b[v + 1] & 0xFF) << 8 | arr_b[v] & 0xFF | (arr_b[v + 2] & 0xFF) << 16);
    }

    static int zzc(zzgi zzgi0, byte[] arr_b, int v, int v1, int v2, zzdt zzdt0) throws IOException {
        Object object0 = zzgi0.zzd();
        int v3 = zzdu.zzl(object0, zzgi0, arr_b, v, v1, v2, zzdt0);
        zzgi0.zze(object0);
        zzdt0.zzc = object0;
        return v3;
    }

    static int zzd(zzgi zzgi0, byte[] arr_b, int v, int v1, zzdt zzdt0) throws IOException {
        Object object0 = zzgi0.zzd();
        int v2 = zzdu.zzm(object0, zzgi0, arr_b, v, v1, zzdt0);
        zzgi0.zze(object0);
        zzdt0.zzc = object0;
        return v2;
    }

    static int zze(zzgi zzgi0, int v, byte[] arr_b, int v1, int v2, zzez zzez0, zzdt zzdt0) throws IOException {
        int v3 = zzdu.zzd(zzgi0, arr_b, v1, v2, zzdt0);
        zzez0.add(zzdt0.zzc);
        while(v3 < v2) {
            int v4 = zzdu.zzh(arr_b, v3, zzdt0);
            if(v != zzdt0.zza) {
                break;
            }
            v3 = zzdu.zzd(zzgi0, arr_b, v4, v2, zzdt0);
            zzez0.add(zzdt0.zzc);
        }
        return v3;
    }

    static int zzf(byte[] arr_b, int v, zzez zzez0, zzdt zzdt0) throws IOException {
        int v1 = zzdu.zzh(arr_b, v, zzdt0);
        int v2 = zzdt0.zza + v1;
        while(v1 < v2) {
            v1 = zzdu.zzh(arr_b, v1, zzdt0);
            ((zzew)zzez0).zze(zzdt0.zza);
        }
        if(v1 != v2) {
            throw zzfb.zzf();
        }
        return v1;
    }

    static int zzg(int v, byte[] arr_b, int v1, int v2, zzha zzha0, zzdt zzdt0) throws zzfb {
        if(v >>> 3 != 0) {
            switch(v & 7) {
                case 0: {
                    int v3 = zzdu.zzk(arr_b, v1, zzdt0);
                    zzha0.zzh(v, zzdt0.zzb);
                    return v3;
                }
                case 1: {
                    zzha0.zzh(v, zzdu.zzn(arr_b, v1));
                    return v1 + 8;
                }
                case 2: {
                    int v4 = zzdu.zzh(arr_b, v1, zzdt0);
                    int v5 = zzdt0.zza;
                    if(v5 < 0) {
                        throw zzfb.zzc();
                    }
                    if(v5 > arr_b.length - v4) {
                        throw zzfb.zzf();
                    }
                    if(v5 == 0) {
                        zzha0.zzh(v, zzef.zzb);
                        return v4;
                    }
                    zzha0.zzh(v, zzef.zzk(arr_b, v4, v5));
                    return v4 + v5;
                }
                case 3: {
                    int v6 = v & -8 | 4;
                    zzha zzha1 = zzha.zzd();
                    int v7 = 0;
                    while(v1 < v2) {
                        int v8 = zzdu.zzh(arr_b, v1, zzdt0);
                        v7 = zzdt0.zza;
                        if(v7 == v6) {
                            v1 = v8;
                            if(true) {
                                break;
                            }
                        }
                        else {
                            v1 = zzdu.zzg(v7, arr_b, v8, v2, zzha1, zzdt0);
                        }
                    }
                    if(v1 > v2 || v7 != v6) {
                        throw zzfb.zzd();
                    }
                    zzha0.zzh(v, zzha1);
                    return v1;
                }
                case 5: {
                    zzha0.zzh(v, zzdu.zzb(arr_b, v1));
                    return v1 + 4;
                }
                default: {
                    throw zzfb.zza();
                }
            }
        }
        throw zzfb.zza();
    }

    static int zzh(byte[] arr_b, int v, zzdt zzdt0) {
        int v1 = arr_b[v];
        if(v1 >= 0) {
            zzdt0.zza = v1;
            return v + 1;
        }
        return zzdu.zzi(v1, arr_b, v + 1, zzdt0);
    }

    static int zzi(int v, byte[] arr_b, int v1, zzdt zzdt0) {
        int v2 = arr_b[v1];
        if(v2 >= 0) {
            zzdt0.zza = v & 0x7F | v2 << 7;
            return v1 + 1;
        }
        int v3 = v & 0x7F | (v2 & 0x7F) << 7;
        int v4 = arr_b[v1 + 1];
        if(v4 >= 0) {
            zzdt0.zza = v3 | v4 << 14;
            return v1 + 2;
        }
        int v5 = v3 | (v4 & 0x7F) << 14;
        int v6 = arr_b[v1 + 2];
        if(v6 >= 0) {
            zzdt0.zza = v5 | v6 << 21;
            return v1 + 3;
        }
        int v7 = v5 | (v6 & 0x7F) << 21;
        int v8 = v1 + 4;
        int v9 = arr_b[v1 + 3];
        if(v9 >= 0) {
            zzdt0.zza = v7 | v9 << 28;
            return v8;
        }
        while(arr_b[v8] < 0) {
            ++v8;
        }
        zzdt0.zza = v7 | (v9 & 0x7F) << 28;
        return v8 + 1;
    }

    static int zzj(int v, byte[] arr_b, int v1, int v2, zzez zzez0, zzdt zzdt0) {
        int v3 = zzdu.zzh(arr_b, v1, zzdt0);
        ((zzew)zzez0).zze(zzdt0.zza);
        while(v3 < v2) {
            int v4 = zzdu.zzh(arr_b, v3, zzdt0);
            if(v != zzdt0.zza) {
                break;
            }
            v3 = zzdu.zzh(arr_b, v4, zzdt0);
            ((zzew)zzez0).zze(zzdt0.zza);
        }
        return v3;
    }

    static int zzk(byte[] arr_b, int v, zzdt zzdt0) {
        long v1 = (long)arr_b[v];
        if(Long.compare(v1, 0L) >= 0) {
            zzdt0.zzb = v1;
            return v + 1;
        }
        int v2 = v + 2;
        int v3 = arr_b[v + 1];
        long v4 = v1 & 0x7FL | ((long)(v3 & 0x7F)) << 7;
        int v5 = 7;
        while(v3 < 0) {
            int v6 = arr_b[v2];
            v5 += 7;
            v4 |= ((long)(v6 & 0x7F)) << v5;
            v3 = v6;
            ++v2;
        }
        zzdt0.zzb = v4;
        return v2;
    }

    static int zzl(Object object0, zzgi zzgi0, byte[] arr_b, int v, int v1, int v2, zzdt zzdt0) throws IOException {
        int v3 = ((zzga)zzgi0).zzb(object0, arr_b, v, v1, v2, zzdt0);
        zzdt0.zzc = object0;
        return v3;
    }

    static int zzm(Object object0, zzgi zzgi0, byte[] arr_b, int v, int v1, zzdt zzdt0) throws IOException {
        int v2 = v + 1;
        int v3 = arr_b[v];
        if(v3 < 0) {
            v2 = zzdu.zzi(v3, arr_b, v2, zzdt0);
            v3 = zzdt0.zza;
        }
        if(v3 < 0 || v3 > v1 - v2) {
            throw zzfb.zzf();
        }
        int v4 = v2 + v3;
        zzgi0.zzg(object0, arr_b, v2, v4, zzdt0);
        zzdt0.zzc = object0;
        return v4;
    }

    static long zzn(byte[] arr_b, int v) {
        return ((long)arr_b[v]) & 0xFFL | (((long)arr_b[v + 1]) & 0xFFL) << 8 | (((long)arr_b[v + 2]) & 0xFFL) << 16 | (((long)arr_b[v + 3]) & 0xFFL) << 24 | (((long)arr_b[v + 4]) & 0xFFL) << 0x20 | (((long)arr_b[v + 5]) & 0xFFL) << 40 | (((long)arr_b[v + 6]) & 0xFFL) << 0x30 | (((long)arr_b[v + 7]) & 0xFFL) << 56;
    }
}

