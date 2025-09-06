package com.google.android.datatransport.cct;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedDestination;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class CCTDestination implements EncodedDestination {
    private static final String DEFAULT_API_KEY = null;
    static final String DEFAULT_END_POINT = null;
    static final String DESTINATION_NAME = "cct";
    private static final String EXTRAS_DELIMITER = "\\";
    private static final String EXTRAS_VERSION_MARKER = "1$";
    public static final CCTDestination INSTANCE;
    static final String LEGACY_END_POINT;
    public static final CCTDestination LEGACY_INSTANCE;
    private static final Set SUPPORTED_ENCODINGS;
    private final String apiKey;
    private final String endPoint;

    static {
        CCTDestination.DEFAULT_END_POINT = "https://firebaselogging.googleapis.com/v0cc/log/batch?format=json_proto3";
        CCTDestination.LEGACY_END_POINT = "https://firebaselogging-pa.googleapis.com/v1/firelog/legacy/batchlog";
        CCTDestination.DEFAULT_API_KEY = "AIzaSyCckkiH8i2ZARwOs1LEzFKld15aOG8ozKo";
        CCTDestination.SUPPORTED_ENCODINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Encoding[]{Encoding.of("proto"), Encoding.of("json")})));
        CCTDestination.INSTANCE = new CCTDestination("https://firebaselogging.googleapis.com/v0cc/log/batch?format=json_proto3", null);
        CCTDestination.LEGACY_INSTANCE = new CCTDestination("https://firebaselogging-pa.googleapis.com/v1/firelog/legacy/batchlog", "AIzaSyCckkiH8i2ZARwOs1LEzFKld15aOG8ozKo");
    }

    public CCTDestination(String s, String s1) {
        this.endPoint = s;
        this.apiKey = s1;
    }

    public byte[] asByteArray() {
        String s = this.apiKey;
        if(s == null && this.endPoint == null) {
            return null;
        }
        String s1 = this.endPoint;
        if(s == null) {
            s = "";
        }
        return String.format("%s%s%s%s", "1$", s1, "\\", s).getBytes(Charset.forName("UTF-8"));
    }

    static String decodeExtras(byte[] arr_b) {
        return new String(arr_b, Charset.forName("UTF-8"));
    }

    static byte[] encodeString(String s) {
        return s.getBytes(Charset.forName("UTF-8"));
    }

    public static CCTDestination fromByteArray(byte[] arr_b) {
        String s = new String(arr_b, Charset.forName("UTF-8"));
        if(!s.startsWith("1$")) {
            throw new IllegalArgumentException("Version marker missing from extras");
        }
        String[] arr_s = s.substring(2).split("\\Q\\\\E", 2);
        if(arr_s.length != 2) {
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        String s1 = arr_s[0];
        if(s1.isEmpty()) {
            throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
        }
        String s2 = arr_s[1];
        if(s2.isEmpty()) {
            s2 = null;
        }
        return new CCTDestination(s1, s2);
    }

    public String getAPIKey() {
        return this.apiKey;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    @Override  // com.google.android.datatransport.runtime.Destination
    public byte[] getExtras() {
        return this.asByteArray();
    }

    @Override  // com.google.android.datatransport.runtime.Destination
    public String getName() {
        return "cct";
    }

    @Override  // com.google.android.datatransport.runtime.EncodedDestination
    public Set getSupportedEncodings() {
        return CCTDestination.SUPPORTED_ENCODINGS;
    }
}

