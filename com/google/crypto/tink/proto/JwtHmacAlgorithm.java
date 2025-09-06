package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum JwtHmacAlgorithm implements EnumLite {
    static final class JwtHmacAlgorithmVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            JwtHmacAlgorithmVerifier.INSTANCE = new JwtHmacAlgorithmVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return JwtHmacAlgorithm.forNumber(v) != null;
        }
    }

    HS_UNKNOWN(0),
    HS256(1),
    HS384(2),
    HS512(3),
    UNRECOGNIZED(-1);

    public static final int HS256_VALUE = 1;
    public static final int HS384_VALUE = 2;
    public static final int HS512_VALUE = 3;
    public static final int HS_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        JwtHmacAlgorithm.internalValueMap = new EnumLiteMap() {
            public JwtHmacAlgorithm findValueByNumber(int v) {
                return JwtHmacAlgorithm.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private JwtHmacAlgorithm(int v1) {
        this.value = v1;
    }

    public static JwtHmacAlgorithm forNumber(int v) {
        switch(v) {
            case 0: {
                return JwtHmacAlgorithm.HS_UNKNOWN;
            }
            case 1: {
                return JwtHmacAlgorithm.HS256;
            }
            case 2: {
                return JwtHmacAlgorithm.HS384;
            }
            case 3: {
                return JwtHmacAlgorithm.HS512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == JwtHmacAlgorithm.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return JwtHmacAlgorithm.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return JwtHmacAlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtHmacAlgorithm valueOf(int v) {
        return JwtHmacAlgorithm.forNumber(v);
    }
}

