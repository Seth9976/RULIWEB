package com.google.android.gms.common.util;

import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class HexDumpUtils {
    @ResultIgnorabilityUnspecified
    public static String dump(byte[] arr_b, int v, int v1, boolean z) {
        if(arr_b != null && arr_b.length != 0 && v >= 0 && v1 > 0 && v + v1 <= arr_b.length) {
            StringBuilder stringBuilder0 = new StringBuilder((z ? 75 : 57) * ((v1 + 15) / 16));
            int v2 = v1;
            int v3 = 0;
            int v4 = 0;
            while(v2 > 0) {
                switch(v3) {
                    case 0: {
                        if(v1 < 0x10000) {
                            stringBuilder0.append(String.format("%04X:", v));
                        }
                        else {
                            stringBuilder0.append(String.format("%08X:", v));
                        }
                        v4 = v;
                        break;
                    }
                    case 8: {
                        stringBuilder0.append(" -");
                    }
                }
                stringBuilder0.append(String.format(" %02X", ((int)(arr_b[v] & 0xFF))));
                --v2;
                ++v3;
                if(z && (v3 == 16 || v2 == 0)) {
                    int v5 = 16 - v3;
                    if(v5 > 0) {
                        for(int v6 = 0; v6 < v5; ++v6) {
                            stringBuilder0.append("   ");
                        }
                    }
                    if(v5 >= 8) {
                        stringBuilder0.append("  ");
                    }
                    stringBuilder0.append("  ");
                    for(int v7 = 0; v7 < v3; ++v7) {
                        int v8 = (char)arr_b[v4 + v7];
                        if(v8 < 0x20 || v8 > 0x7E) {
                            v8 = 46;
                        }
                        stringBuilder0.append(((char)v8));
                    }
                }
                if(v3 == 16 || v2 == 0) {
                    stringBuilder0.append('\n');
                    v3 = 0;
                }
                ++v;
            }
            return stringBuilder0.toString();
        }
        return null;
    }
}

