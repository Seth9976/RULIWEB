package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class zzc {
    private static final Pattern zza;

    static {
        zzc.zza = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
    }

    public static String zza(String s) {
        if(!TextUtils.isEmpty(s)) {
            Matcher matcher0 = zzc.zza.matcher(s);
            StringBuilder stringBuilder0 = null;
            int v = 0;
            while(matcher0.find()) {
                if(stringBuilder0 == null) {
                    stringBuilder0 = new StringBuilder();
                }
                int v1 = matcher0.start();
                int v2;
                for(v2 = v1; v2 >= 0 && s.charAt(v2) == 92; --v2) {
                }
                if((v1 - v2) % 2 != 0) {
                    int v3 = Integer.parseInt(matcher0.group().substring(2), 16);
                    stringBuilder0.append(s, v, matcher0.start());
                    if(v3 == 92) {
                        stringBuilder0.append("\\\\");
                    }
                    else {
                        stringBuilder0.append(Character.toChars(v3));
                    }
                    v = matcher0.end();
                }
            }
            if(stringBuilder0 != null) {
                if(v < matcher0.regionEnd()) {
                    stringBuilder0.append(s, v, matcher0.regionEnd());
                }
                return stringBuilder0.toString();
            }
        }
        return s;
    }
}

