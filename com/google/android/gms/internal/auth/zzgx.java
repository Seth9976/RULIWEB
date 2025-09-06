package com.google.android.gms.internal.auth;

final class zzgx {
    static String zza(zzef zzef0) {
        StringBuilder stringBuilder0 = new StringBuilder(zzef0.zzd());
        for(int v = 0; v < zzef0.zzd(); ++v) {
            int v1 = zzef0.zza(v);
            switch(v1) {
                case 7: {
                    stringBuilder0.append("\\a");
                    break;
                }
                case 8: {
                    stringBuilder0.append("\\b");
                    break;
                }
                case 9: {
                    stringBuilder0.append("\\t");
                    break;
                }
                case 10: {
                    stringBuilder0.append("\\n");
                    break;
                }
                case 11: {
                    stringBuilder0.append("\\v");
                    break;
                }
                case 12: {
                    stringBuilder0.append("\\f");
                    break;
                }
                case 13: {
                    stringBuilder0.append("\\r");
                    break;
                }
                case 34: {
                    stringBuilder0.append("\\\"");
                    break;
                }
                case 39: {
                    stringBuilder0.append("\\\'");
                    break;
                }
                case 92: {
                    stringBuilder0.append("\\\\");
                    break;
                }
                default: {
                    if(v1 < 0x20 || v1 > 0x7E) {
                        stringBuilder0.append('\\');
                        stringBuilder0.append(((char)((v1 >>> 6 & 3) + 0x30)));
                        stringBuilder0.append(((char)((v1 >>> 3 & 7) + 0x30)));
                        stringBuilder0.append(((char)((v1 & 7) + 0x30)));
                    }
                    else {
                        stringBuilder0.append(((char)v1));
                    }
                }
            }
        }
        return stringBuilder0.toString();
    }
}

