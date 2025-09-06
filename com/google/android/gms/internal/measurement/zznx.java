package com.google.android.gms.internal.measurement;

final class zznx extends zznw {
    @Override  // com.google.android.gms.internal.measurement.zznw
    final int zza(int v, byte[] arr_b, int v1, int v2) {
        while(v1 < v2 && arr_b[v1] >= 0) {
            ++v1;
        }
        if(v1 >= v2) {
            return 0;
        }
        while(true) {
            if(v1 >= v2) {
                return 0;
            }
            int v3 = v1 + 1;
            int v4 = arr_b[v1];
            if(v4 < 0) {
                if(v4 < 0xFFFFFFE0) {
                    if(v3 >= v2) {
                        return v4;
                    }
                    if(v4 >= -62) {
                        v1 += 2;
                        if(arr_b[v3] > -65) {
                            return -1;
                        }
                        continue;
                    }
                }
                else if(v4 < -16) {
                    if(v3 >= v2 - 1) {
                        return zznz.zza(arr_b, v3, v2);
                    }
                    int v5 = v1 + 2;
                    int v6 = arr_b[v3];
                    if(v6 > -65 || v4 == 0xFFFFFFE0 && v6 < 0xFFFFFFA0) {
                        return -1;
                    }
                    if(v4 == -19 && v6 >= 0xFFFFFFA0) {
                        return -1;
                    }
                    v1 += 3;
                    if(arr_b[v5] <= -65) {
                        continue;
                    }
                }
                else {
                    if(v3 >= v2 - 2) {
                        return zznz.zza(arr_b, v3, v2);
                    }
                    int v7 = arr_b[v3];
                    if(v7 <= -65 && (v4 << 28) + (v7 + 0x70) >> 30 == 0) {
                        int v8 = v1 + 3;
                        if(arr_b[v1 + 2] <= -65) {
                            v1 += 4;
                            if(arr_b[v8] > -65) {
                                return -1;
                            }
                            continue;
                        }
                    }
                }
                return -1;
            }
            v1 = v3;
        }
    }
}

