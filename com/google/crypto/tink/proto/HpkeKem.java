package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum HpkeKem implements EnumLite {
    static final class HpkeKemVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            HpkeKemVerifier.INSTANCE = new HpkeKemVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return HpkeKem.forNumber(v) != null;
        }
    }

    KEM_UNKNOWN(0),
    DHKEM_X25519_HKDF_SHA256(1),
    DHKEM_P256_HKDF_SHA256(2),
    DHKEM_P384_HKDF_SHA384(3),
    DHKEM_P521_HKDF_SHA512(4),
    UNRECOGNIZED(-1);

    public static final int DHKEM_P256_HKDF_SHA256_VALUE = 2;
    public static final int DHKEM_P384_HKDF_SHA384_VALUE = 3;
    public static final int DHKEM_P521_HKDF_SHA512_VALUE = 4;
    public static final int DHKEM_X25519_HKDF_SHA256_VALUE = 1;
    public static final int KEM_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        HpkeKem.internalValueMap = new EnumLiteMap() {
            public HpkeKem findValueByNumber(int v) {
                return HpkeKem.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private HpkeKem(int v1) {
        this.value = v1;
    }

    public static HpkeKem forNumber(int v) {
        switch(v) {
            case 0: {
                return HpkeKem.KEM_UNKNOWN;
            }
            case 1: {
                return HpkeKem.DHKEM_X25519_HKDF_SHA256;
            }
            case 2: {
                return HpkeKem.DHKEM_P256_HKDF_SHA256;
            }
            case 3: {
                return HpkeKem.DHKEM_P384_HKDF_SHA384;
            }
            case 4: {
                return HpkeKem.DHKEM_P521_HKDF_SHA512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == HpkeKem.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return HpkeKem.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return HpkeKemVerifier.INSTANCE;
    }

    @Deprecated
    public static HpkeKem valueOf(int v) {
        return HpkeKem.forNumber(v);
    }
}

