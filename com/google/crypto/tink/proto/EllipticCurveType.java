package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum EllipticCurveType implements EnumLite {
    static final class EllipticCurveTypeVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            EllipticCurveTypeVerifier.INSTANCE = new EllipticCurveTypeVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return EllipticCurveType.forNumber(v) != null;
        }
    }

    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    CURVE25519(5),
    UNRECOGNIZED(-1);

    public static final int CURVE25519_VALUE = 5;
    public static final int NIST_P256_VALUE = 2;
    public static final int NIST_P384_VALUE = 3;
    public static final int NIST_P521_VALUE = 4;
    public static final int UNKNOWN_CURVE_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        EllipticCurveType.internalValueMap = new EnumLiteMap() {
            public EllipticCurveType findValueByNumber(int v) {
                return EllipticCurveType.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private EllipticCurveType(int v1) {
        this.value = v1;
    }

    public static EllipticCurveType forNumber(int v) {
        switch(v) {
            case 0: {
                return EllipticCurveType.UNKNOWN_CURVE;
            }
            case 2: {
                return EllipticCurveType.NIST_P256;
            }
            case 3: {
                return EllipticCurveType.NIST_P384;
            }
            case 4: {
                return EllipticCurveType.NIST_P521;
            }
            case 5: {
                return EllipticCurveType.CURVE25519;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == EllipticCurveType.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return EllipticCurveType.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return EllipticCurveTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static EllipticCurveType valueOf(int v) {
        return EllipticCurveType.forNumber(v);
    }
}

