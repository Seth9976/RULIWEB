package com.google.android.datatransport.cct;

public final class StringMerger {
    static String mergeStrings(String s, String s1) {
        int v = s.length() - s1.length();
        if(v < 0 || v > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder stringBuilder0 = new StringBuilder(s.length() + s1.length());
        for(int v1 = 0; v1 < s.length(); ++v1) {
            stringBuilder0.append(s.charAt(v1));
            if(s1.length() > v1) {
                stringBuilder0.append(s1.charAt(v1));
            }
        }
        return stringBuilder0.toString();
    }
}

