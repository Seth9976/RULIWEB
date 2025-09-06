package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzjo {
    static int zza(byte[] arr_b, int v, zzjn zzjn0) throws zzll {
        int v1 = zzjo.zzj(arr_b, v, zzjn0);
        int v2 = zzjn0.zza;
        if(v2 < 0) {
            throw zzll.zzd();
        }
        if(v2 > arr_b.length - v1) {
            throw zzll.zzf();
        }
        if(v2 == 0) {
            zzjn0.zzc = zzka.zzb;
            return v1;
        }
        zzjn0.zzc = zzka.zzl(arr_b, v1, v2);
        return v1 + v2;
    }

    static int zzb(byte[] arr_b, int v) {
        return (arr_b[v + 3] & 0xFF) << 24 | ((arr_b[v + 1] & 0xFF) << 8 | arr_b[v] & 0xFF | (arr_b[v + 2] & 0xFF) << 16);
    }

    static int zzc(zzmt zzmt0, byte[] arr_b, int v, int v1, int v2, zzjn zzjn0) throws IOException {
        Object object0 = zzmt0.zze();
        int v3 = zzjo.zzn(object0, zzmt0, arr_b, v, v1, v2, zzjn0);
        zzmt0.zzf(object0);
        zzjn0.zzc = object0;
        return v3;
    }

    static int zzd(zzmt zzmt0, byte[] arr_b, int v, int v1, zzjn zzjn0) throws IOException {
        Object object0 = zzmt0.zze();
        int v2 = zzjo.zzo(object0, zzmt0, arr_b, v, v1, zzjn0);
        zzmt0.zzf(object0);
        zzjn0.zzc = object0;
        return v2;
    }

    static int zze(zzmt zzmt0, int v, byte[] arr_b, int v1, int v2, zzli zzli0, zzjn zzjn0) throws IOException {
        int v3 = zzjo.zzd(zzmt0, arr_b, v1, v2, zzjn0);
        zzli0.add(zzjn0.zzc);
        while(v3 < v2) {
            int v4 = zzjo.zzj(arr_b, v3, zzjn0);
            if(v != zzjn0.zza) {
                break;
            }
            v3 = zzjo.zzd(zzmt0, arr_b, v4, v2, zzjn0);
            zzli0.add(zzjn0.zzc);
        }
        return v3;
    }

    static int zzf(byte[] arr_b, int v, zzli zzli0, zzjn zzjn0) throws IOException {
        int v1 = zzjo.zzj(arr_b, v, zzjn0);
        int v2 = zzjn0.zza + v1;
        while(v1 < v2) {
            v1 = zzjo.zzj(arr_b, v1, zzjn0);
            ((zzlc)zzli0).zzh(zzjn0.zza);
        }
        if(v1 != v2) {
            throw zzll.zzf();
        }
        return v1;
    }

    static int zzg(byte[] arr_b, int v, zzjn zzjn0) throws zzll {
        int v1 = zzjo.zzj(arr_b, v, zzjn0);
        int v2 = zzjn0.zza;
        if(v2 < 0) {
            throw zzll.zzd();
        }
        if(v2 == 0) {
            zzjn0.zzc = "";
            return v1;
        }
        zzjn0.zzc = new String(arr_b, v1, v2, zzlj.zzb);
        return v1 + v2;
    }

    static int zzh(byte[] arr_b, int v, zzjn zzjn0) throws zzll {
        int v1 = zzjo.zzj(arr_b, v, zzjn0);
        int v2 = zzjn0.zza;
        if(v2 < 0) {
            throw zzll.zzd();
        }
        if(v2 == 0) {
            zzjn0.zzc = "";
            return v1;
        }
        if((arr_b.length - v1 - v2 | (v1 | v2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", ((int)arr_b.length), v1, v2));
        }
        int v3 = v1 + v2;
        char[] arr_c = new char[v2];
        int v4;
        for(v4 = 0; v1 < v3; ++v4) {
            int v5 = arr_b[v1];
            if(!zznv.zzd(((byte)v5))) {
                break;
            }
            ++v1;
            arr_c[v4] = (char)v5;
        }
        int v6 = v4;
        while(v1 < v3) {
            int v7 = v1 + 1;
            int v8 = arr_b[v1];
            if(zznv.zzd(((byte)v8))) {
                arr_c[v6] = (char)v8;
                ++v6;
                v1 = v7;
                while(v1 < v3) {
                    int v9 = arr_b[v1];
                    if(!zznv.zzd(((byte)v9))) {
                        break;
                    }
                    ++v1;
                    arr_c[v6] = (char)v9;
                    ++v6;
                }
                continue;
            }
            if(v8 < 0xFFFFFFE0) {
                if(v7 < v3) {
                    v1 += 2;
                    zznv.zzc(((byte)v8), arr_b[v7], arr_c, v6);
                    ++v6;
                    continue;
                }
            }
            else if(v8 >= -16) {
                if(v7 < v3 - 2) {
                    int v11 = arr_b[v7];
                    int v12 = v1 + 3;
                    int v13 = arr_b[v1 + 2];
                    v1 += 4;
                    zznv.zza(((byte)v8), ((byte)v11), ((byte)v13), arr_b[v12], arr_c, v6);
                    v6 += 2;
                    continue;
                }
            }
            else if(v7 < v3 - 1) {
                int v10 = v1 + 2;
                v1 += 3;
                zznv.zzb(((byte)v8), arr_b[v7], arr_b[v10], arr_c, v6);
                ++v6;
                continue;
            }
            throw zzll.zzc();
        }
        zzjn0.zzc = new String(arr_c, 0, v6);
        return v3;
    }

    static int zzi(int v, byte[] arr_b, int v1, int v2, zznl zznl0, zzjn zzjn0) throws zzll {
        if(v >>> 3 != 0) {
            switch(v & 7) {
                case 0: {
                    int v3 = zzjo.zzm(arr_b, v1, zzjn0);
                    zznl0.zzj(v, zzjn0.zzb);
                    return v3;
                }
                case 1: {
                    zznl0.zzj(v, zzjo.zzp(arr_b, v1));
                    return v1 + 8;
                }
                case 2: {
                    int v4 = zzjo.zzj(arr_b, v1, zzjn0);
                    int v5 = zzjn0.zza;
                    if(v5 < 0) {
                        throw zzll.zzd();
                    }
                    if(v5 > arr_b.length - v4) {
                        throw zzll.zzf();
                    }
                    if(v5 == 0) {
                        zznl0.zzj(v, zzka.zzb);
                        return v4;
                    }
                    zznl0.zzj(v, zzka.zzl(arr_b, v4, v5));
                    return v4 + v5;
                }
                case 3: {
                    int v6 = v & -8 | 4;
                    zznl zznl1 = zznl.zzf();
                    int v7 = 0;
                    while(v1 < v2) {
                        int v8 = zzjo.zzj(arr_b, v1, zzjn0);
                        v7 = zzjn0.zza;
                        if(v7 == v6) {
                            v1 = v8;
                            if(true) {
                                break;
                            }
                        }
                        else {
                            v1 = zzjo.zzi(v7, arr_b, v8, v2, zznl1, zzjn0);
                        }
                    }
                    if(v1 > v2 || v7 != v6) {
                        throw zzll.zze();
                    }
                    zznl0.zzj(v, zznl1);
                    return v1;
                }
                case 5: {
                    zznl0.zzj(v, zzjo.zzb(arr_b, v1));
                    return v1 + 4;
                }
                default: {
                    throw zzll.zzb();
                }
            }
        }
        throw zzll.zzb();
    }

    static int zzj(byte[] arr_b, int v, zzjn zzjn0) {
        int v1 = arr_b[v];
        if(v1 >= 0) {
            zzjn0.zza = v1;
            return v + 1;
        }
        return zzjo.zzk(v1, arr_b, v + 1, zzjn0);
    }

    static int zzk(int v, byte[] arr_b, int v1, zzjn zzjn0) {
        int v2 = arr_b[v1];
        if(v2 >= 0) {
            zzjn0.zza = v & 0x7F | v2 << 7;
            return v1 + 1;
        }
        int v3 = v & 0x7F | (v2 & 0x7F) << 7;
        int v4 = arr_b[v1 + 1];
        if(v4 >= 0) {
            zzjn0.zza = v3 | v4 << 14;
            return v1 + 2;
        }
        int v5 = v3 | (v4 & 0x7F) << 14;
        int v6 = arr_b[v1 + 2];
        if(v6 >= 0) {
            zzjn0.zza = v5 | v6 << 21;
            return v1 + 3;
        }
        int v7 = v5 | (v6 & 0x7F) << 21;
        int v8 = v1 + 4;
        int v9 = arr_b[v1 + 3];
        if(v9 >= 0) {
            zzjn0.zza = v7 | v9 << 28;
            return v8;
        }
        while(arr_b[v8] < 0) {
            ++v8;
        }
        zzjn0.zza = v7 | (v9 & 0x7F) << 28;
        return v8 + 1;
    }

    static int zzl(int v, byte[] arr_b, int v1, int v2, zzli zzli0, zzjn zzjn0) {
        int v3 = zzjo.zzj(arr_b, v1, zzjn0);
        ((zzlc)zzli0).zzh(zzjn0.zza);
        while(v3 < v2) {
            int v4 = zzjo.zzj(arr_b, v3, zzjn0);
            if(v != zzjn0.zza) {
                break;
            }
            v3 = zzjo.zzj(arr_b, v4, zzjn0);
            ((zzlc)zzli0).zzh(zzjn0.zza);
        }
        return v3;
    }

    static int zzm(byte[] arr_b, int v, zzjn zzjn0) {
        long v1 = (long)arr_b[v];
        if(Long.compare(v1, 0L) >= 0) {
            zzjn0.zzb = v1;
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
        zzjn0.zzb = v4;
        return v2;
    }

    static int zzn(Object object0, zzmt zzmt0, byte[] arr_b, int v, int v1, int v2, zzjn zzjn0) throws IOException {
        int v3 = ((zzml)zzmt0).zzc(object0, arr_b, v, v1, v2, zzjn0);
        zzjn0.zzc = object0;
        return v3;
    }

    static int zzo(Object object0, zzmt zzmt0, byte[] arr_b, int v, int v1, zzjn zzjn0) throws IOException {
        int v2 = v + 1;
        int v3 = arr_b[v];
        if(v3 < 0) {
            v2 = zzjo.zzk(v3, arr_b, v2, zzjn0);
            v3 = zzjn0.zza;
        }
        if(v3 < 0 || v3 > v1 - v2) {
            throw zzll.zzf();
        }
        int v4 = v2 + v3;
        zzmt0.zzh(object0, arr_b, v2, v4, zzjn0);
        zzjn0.zzc = object0;
        return v4;
    }

    static long zzp(byte[] arr_b, int v) {
        return ((long)arr_b[v]) & 0xFFL | (((long)arr_b[v + 1]) & 0xFFL) << 8 | (((long)arr_b[v + 2]) & 0xFFL) << 16 | (((long)arr_b[v + 3]) & 0xFFL) << 24 | (((long)arr_b[v + 4]) & 0xFFL) << 0x20 | (((long)arr_b[v + 5]) & 0xFFL) << 40 | (((long)arr_b[v + 6]) & 0xFFL) << 0x30 | (((long)arr_b[v + 7]) & 0xFFL) << 56;
    }
}

