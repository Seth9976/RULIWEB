package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum HpkeKdf implements EnumLite {
    static final class HpkeKdfVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            HpkeKdfVerifier.INSTANCE = new HpkeKdfVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return HpkeKdf.forNumber(v) != null;
        }
    }

    KDF_UNKNOWN(0),
    HKDF_SHA256(1),
    HKDF_SHA384(2),
    HKDF_SHA512(3),
    UNRECOGNIZED(-1);

    public static final int HKDF_SHA256_VALUE = 1;
    public static final int HKDF_SHA384_VALUE = 2;
    public static final int HKDF_SHA512_VALUE = 3;
    public static final int KDF_UNKNOWN_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        HpkeKdf.internalValueMap = new EnumLiteMap() {
            public HpkeKdf findValueByNumber(int v) {
                return HpkeKdf.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private HpkeKdf(int v1) {
        this.value = v1;
    }

    public static HpkeKdf forNumber(int v) {
        switch(v) {
            case 0: {
                return HpkeKdf.KDF_UNKNOWN;
            }
            case 1: {
                return HpkeKdf.HKDF_SHA256;
            }
            case 2: {
                return HpkeKdf.HKDF_SHA384;
            }
            case 3: {
                return HpkeKdf.HKDF_SHA512;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == HpkeKdf.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return HpkeKdf.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return HpkeKdfVerifier.INSTANCE;
    }

    @Deprecated
    public static HpkeKdf valueOf(int v) {
        return HpkeKdf.forNumber(v);
    }
}

