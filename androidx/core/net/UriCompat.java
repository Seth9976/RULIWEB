package androidx.core.net;

import android.net.Uri;

public final class UriCompat {
    public static String toSafeString(Uri uri0) {
        String s = uri0.getScheme();
        String s1 = uri0.getSchemeSpecificPart();
        if(s != null) {
            if(s.equalsIgnoreCase("tel") || s.equalsIgnoreCase("sip") || s.equalsIgnoreCase("sms") || s.equalsIgnoreCase("smsto") || s.equalsIgnoreCase("mailto") || s.equalsIgnoreCase("nfc")) {
                StringBuilder stringBuilder1 = new StringBuilder(0x40);
                stringBuilder1.append(s);
                stringBuilder1.append(':');
                if(s1 != null) {
                    for(int v = 0; v < s1.length(); ++v) {
                        int v1 = s1.charAt(v);
                        if(v1 == 45 || v1 == 0x40 || v1 == 46) {
                            stringBuilder1.append(((char)v1));
                        }
                        else {
                            stringBuilder1.append('x');
                        }
                    }
                }
                return stringBuilder1.toString();
            }
            else if(s.equalsIgnoreCase("http") || s.equalsIgnoreCase("https") || s.equalsIgnoreCase("ftp") || s.equalsIgnoreCase("rtsp")) {
                StringBuilder stringBuilder0 = new StringBuilder("//");
                String s2 = "";
                stringBuilder0.append((uri0.getHost() == null ? "" : uri0.getHost()));
                if(uri0.getPort() != -1) {
                    s2 = ":" + uri0.getPort();
                }
                stringBuilder0.append(s2);
                stringBuilder0.append("/...");
                s1 = stringBuilder0.toString();
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder(0x40);
        if(s != null) {
            stringBuilder2.append(s);
            stringBuilder2.append(':');
        }
        if(s1 != null) {
            stringBuilder2.append(s1);
        }
        return stringBuilder2.toString();
    }
}

