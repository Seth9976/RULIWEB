package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum JwtRsaSsaPssAlgorithm implements EnumLite {
    static final class JwtRsaSsaPssAlgorithmVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            JwtRsaSsaPssAlgorithmVerifier.INSTANCE = new JwtRsaSsaPssAlgorithmVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return JwtRsaSsaPssAlgorithm.forNumber(v) != null;
        }
    }

    PS_UNKNOWN(0),
    PS256(1),
    PS384(2),
    PS512(3),
    UNRECOGNIZED(-1);

    public static final int PS256_VALUE = 1;
    public static final int PS384_VALUE = 2;
    public static final int PS512_VALUE = 3;
    public static final int PS_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        JwtRsaSsaPssAlgorithm.internalValueMap = new EnumLiteMap() {
            public JwtRsaSsaPssAlgorithm findValueByNumber(int v) {
                return JwtRsaSsaPssAlgorithm.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private JwtRsaSsaPssAlgorithm(int v1) {
        this.value = v1;
    }

    public static JwtRsaSsaPssAlgorithm forNumber(int v) {
        switch(v) {
            case 0: {
                return JwtRsaSsaPssAlgorithm.PS_UNKNOWN;
            }
            case 1: {
                return JwtRsaSsaPssAlgorithm.PS256;
            }
            case 2: {
                return JwtRsaSsaPssAlgorithm.PS384;
            }
            case 3: {
                return JwtRsaSsaPssAlgorithm.PS512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == JwtRsaSsaPssAlgorithm.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return JwtRsaSsaPssAlgorithm.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return JwtRsaSsaPssAlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtRsaSsaPssAlgorithm valueOf(int v) {
        return JwtRsaSsaPssAlgorithm.forNumber(v);
    }
}

