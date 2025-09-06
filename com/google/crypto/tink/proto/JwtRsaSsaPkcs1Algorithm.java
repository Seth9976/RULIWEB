package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum JwtRsaSsaPkcs1Algorithm implements EnumLite {
    static final class JwtRsaSsaPkcs1AlgorithmVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            JwtRsaSsaPkcs1AlgorithmVerifier.INSTANCE = new JwtRsaSsaPkcs1AlgorithmVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return JwtRsaSsaPkcs1Algorithm.forNumber(v) != null;
        }
    }

    RS_UNKNOWN(0),
    RS256(1),
    RS384(2),
    RS512(3),
    UNRECOGNIZED(-1);

    public static final int RS256_VALUE = 1;
    public static final int RS384_VALUE = 2;
    public static final int RS512_VALUE = 3;
    public static final int RS_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        JwtRsaSsaPkcs1Algorithm.internalValueMap = new EnumLiteMap() {
            public JwtRsaSsaPkcs1Algorithm findValueByNumber(int v) {
                return JwtRsaSsaPkcs1Algorithm.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private JwtRsaSsaPkcs1Algorithm(int v1) {
        this.value = v1;
    }

    public static JwtRsaSsaPkcs1Algorithm forNumber(int v) {
        switch(v) {
            case 0: {
                return JwtRsaSsaPkcs1Algorithm.RS_UNKNOWN;
            }
            case 1: {
                return JwtRsaSsaPkcs1Algorithm.RS256;
            }
            case 2: {
                return JwtRsaSsaPkcs1Algorithm.RS384;
            }
            case 3: {
                return JwtRsaSsaPkcs1Algorithm.RS512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == JwtRsaSsaPkcs1Algorithm.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return JwtRsaSsaPkcs1Algorithm.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return JwtRsaSsaPkcs1AlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtRsaSsaPkcs1Algorithm valueOf(int v) {
        return JwtRsaSsaPkcs1Algorithm.forNumber(v);
    }
}

