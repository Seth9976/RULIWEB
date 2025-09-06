package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum JwtEcdsaAlgorithm implements EnumLite {
    static final class JwtEcdsaAlgorithmVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            JwtEcdsaAlgorithmVerifier.INSTANCE = new JwtEcdsaAlgorithmVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return JwtEcdsaAlgorithm.forNumber(v) != null;
        }
    }

    ES_UNKNOWN(0),
    ES256(1),
    ES384(2),
    ES512(3),
    UNRECOGNIZED(-1);

    public static final int ES256_VALUE = 1;
    public static final int ES384_VALUE = 2;
    public static final int ES512_VALUE = 3;
    public static final int ES_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        JwtEcdsaAlgorithm.internalValueMap = new EnumLiteMap() {
            public JwtEcdsaAlgorithm findValueByNumber(int v) {
                return JwtEcdsaAlgorithm.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private JwtEcdsaAlgorithm(int v1) {
        this.value = v1;
    }

    public static JwtEcdsaAlgorithm forNumber(int v) {
        switch(v) {
            case 0: {
                return JwtEcdsaAlgorithm.ES_UNKNOWN;
            }
            case 1: {
                return JwtEcdsaAlgorithm.ES256;
            }
            case 2: {
                return JwtEcdsaAlgorithm.ES384;
            }
            case 3: {
                return JwtEcdsaAlgorithm.ES512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == JwtEcdsaAlgorithm.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return JwtEcdsaAlgorithm.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return JwtEcdsaAlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtEcdsaAlgorithm valueOf(int v) {
        return JwtEcdsaAlgorithm.forNumber(v);
    }
}

