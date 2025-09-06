package androidx.webkit;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URLUtilCompat {
    private static final Pattern DISPOSITION_PATTERN;

    static {
        URLUtilCompat.DISPOSITION_PATTERN = Pattern.compile("\\s*(\\S+?) # Group 1: parameter name\n\\s*=\\s* # Match equals sign\n(?: # non-capturing group of options\n   \'( (?: [^\'\\\\] | \\\\. )* )\' # Group 2: single-quoted\n | \"( (?: [^\"\\\\] | \\\\. )*  )\" # Group 3: double-quoted\n | ( [^\'\"][^;\\s]* ) # Group 4: un-quoted parameter\n)\\s*;? # Optional end semicolon", 4);
    }

    private static String encodePlusCharacters(String s, String s1) {
        Charset charset0 = Charset.forName(s1);
        StringBuilder stringBuilder0 = new StringBuilder();
        byte[] arr_b = charset0.encode("+").array();
        for(int v = 0; v < arr_b.length; ++v) {
            stringBuilder0.append(String.format("%02x", ((byte)arr_b[v])));
        }
        return s.replaceAll("\\+", stringBuilder0.toString());
    }

    private static boolean extensionDifferentFromMimeType(String s, String s1) {
        if(s1 == null) {
            return false;
        }
        String s2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s.substring(s.lastIndexOf(46) + 1));
        return s2 != null && !s2.equalsIgnoreCase(s1);
    }

    public static String getFilenameFromContentDisposition(String s) {
        String s4;
        String[] arr_s = s.trim().split(";", 2);
        String s1 = null;
        if(arr_s.length < 2) {
            return null;
        }
        if("inline".equalsIgnoreCase(arr_s[0].trim())) {
            return null;
        }
        Matcher matcher0 = URLUtilCompat.DISPOSITION_PATTERN.matcher(arr_s[1]);
        String s2 = null;
        while(matcher0.find()) {
            String s3 = matcher0.group(1);
            if(matcher0.group(2) == null) {
                s4 = matcher0.group(3) == null ? matcher0.group(4) : URLUtilCompat.removeSlashEscapes(matcher0.group(3));
            }
            else {
                s4 = URLUtilCompat.removeSlashEscapes(matcher0.group(2));
            }
            if(s3 == null || s4 == null) {
            }
            else if("filename*".equalsIgnoreCase(s3)) {
                s1 = URLUtilCompat.parseExtValueString(s4);
            }
            else if("filename".equalsIgnoreCase(s3)) {
                s2 = s4;
            }
        }
        return s1 == null ? s2 : s1;
    }

    public static String guessFileName(String s, String s1, String s2) {
        if(s1 != null) {
            String s3 = URLUtilCompat.getFilenameFromContentDisposition(s1);
            if(s3 != null) {
                return URLUtilCompat.replacePathSeparators(s3);
            }
        }
        Uri uri0 = Uri.parse(s);
        if(uri0 != null) {
            String s4 = uri0.getLastPathSegment();
            if(s4 != null) {
                String s5 = URLUtilCompat.replacePathSeparators(s4);
                return s5.indexOf(46) < 0 || URLUtilCompat.extensionDifferentFromMimeType(s5, s2) ? s5 + URLUtilCompat.suggestExtensionFromMimeType(s2) : s5;
            }
        }
        return "downloadfile" + URLUtilCompat.suggestExtensionFromMimeType(s2);
    }

    private static String parseExtValueString(String s) {
        String[] arr_s = s.split("\'", 3);
        if(arr_s.length < 3) {
            return null;
        }
        String s1 = arr_s[0];
        String s2 = arr_s[2];
        try {
            return URLDecoder.decode(URLUtilCompat.encodePlusCharacters(s2, s1), s1);
        }
        catch(RuntimeException | UnsupportedEncodingException unused_ex) {
            return null;
        }
    }

    private static String removeSlashEscapes(String s) {
        return s == null ? null : s.replaceAll("\\\\(.)", "$1");
    }

    private static String replacePathSeparators(String s) {
        return s.replaceAll("/", "_");
    }

    private static String suggestExtensionFromMimeType(String s) {
        if(s == null) {
            return "";
        }
        String s1 = s.trim().toLowerCase(Locale.ROOT);
        if(s1.equals("application/octet-stream")) {
            return "";
        }
        String s2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(s1);
        return s2 == null ? "" : "." + s2;
    }
}

