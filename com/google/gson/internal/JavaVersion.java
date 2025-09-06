package com.google.gson.internal;

public final class JavaVersion {
    private static final int majorJavaVersion;

    static {
        JavaVersion.majorJavaVersion = JavaVersion.determineMajorJavaVersion();
    }

    // 去混淆评级： 低(20)
    private static int determineMajorJavaVersion() {
        return JavaVersion.getMajorJavaVersion("0");
    }

    private static int extractBeginningInt(String s) {
        try {
            StringBuilder stringBuilder0 = new StringBuilder();
            for(int v = 0; v < s.length(); ++v) {
                int v1 = s.charAt(v);
                if(!Character.isDigit(((char)v1))) {
                    break;
                }
                stringBuilder0.append(((char)v1));
            }
            return Integer.parseInt(stringBuilder0.toString());
        }
        catch(NumberFormatException unused_ex) {
            return -1;
        }
    }

    public static int getMajorJavaVersion() [...] // 潜在的解密器

    static int getMajorJavaVersion(String s) {
        int v = JavaVersion.parseDotted(s);
        if(v == -1) {
            v = JavaVersion.extractBeginningInt(s);
        }
        return v == -1 ? 6 : v;
    }

    public static boolean isJava9OrLater() [...] // 潜在的解密器

    private static int parseDotted(String s) {
        try {
            String[] arr_s = s.split("[._]");
            int v = Integer.parseInt(arr_s[0]);
            return v != 1 || arr_s.length <= 1 ? v : Integer.parseInt(arr_s[1]);
        }
        catch(NumberFormatException unused_ex) {
            return -1;
        }
    }
}

