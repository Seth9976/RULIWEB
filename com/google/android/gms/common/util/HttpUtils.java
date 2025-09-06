package com.google.android.gms.common.util;

import com.google.android.gms.internal.common.zzo;
import com.google.android.gms.internal.common.zzx;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpUtils {
    private static final Pattern zza;
    private static final Pattern zzb;
    private static final Pattern zzc;

    static {
        HttpUtils.zza = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
        HttpUtils.zzb = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
        HttpUtils.zzc = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
    }

    public static Map parse(URI uRI0, String s) {
        Map map0 = Collections.EMPTY_MAP;
        String s1 = uRI0.getRawQuery();
        if(s1 != null && s1.length() > 0) {
            map0 = new HashMap();
            zzx zzx0 = zzx.zzc(zzo.zzb('='));
            for(Object object0: zzx.zzc(zzo.zzb('&')).zzb().zzd(s1)) {
                List list0 = zzx0.zzf(((String)object0));
                if(list0.isEmpty() || list0.size() > 2) {
                    throw new IllegalArgumentException("bad parameter");
                }
                map0.put(HttpUtils.zza(((String)list0.get(0)), s), (list0.size() == 2 ? HttpUtils.zza(((String)list0.get(1)), s) : null));
            }
        }
        return map0;
    }

    private static String zza(String s, String s1) {
        try {
            if(s1 == null) {
                s1 = "ISO-8859-1";
            }
            return URLDecoder.decode(s, s1);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new IllegalArgumentException(unsupportedEncodingException0);
        }
    }
}

