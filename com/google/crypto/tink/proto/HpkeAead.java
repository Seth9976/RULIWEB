package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum HpkeAead implements EnumLite {
    static final class HpkeAeadVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            HpkeAeadVerifier.INSTANCE = new HpkeAeadVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return HpkeAead.forNumber(v) != null;
        }
    }

    AEAD_UNKNOWN(0),
    AES_128_GCM(1),
    AES_256_GCM(2),
    CHACHA20_POLY1305(3),
    UNRECOGNIZED(-1);

    public static final int AEAD_UNKNOWN_VALUE = 0;
    public static final int AES_128_GCM_VALUE = 1;
    public static final int AES_256_GCM_VALUE = 2;
    public static final int CHACHA20_POLY1305_VALUE = 3;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        HpkeAead.internalValueMap = new EnumLiteMap() {
            public HpkeAead findValueByNumber(int v) {
                return HpkeAead.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private HpkeAead(int v1) {
        this.value = v1;
    }

    public static HpkeAead forNumber(int v) {
        switch(v) {
            case 0: {
                return HpkeAead.AEAD_UNKNOWN;
            }
            case 1: {
                return HpkeAead.AES_128_GCM;
            }
            case 2: {
                return HpkeAead.AES_256_GCM;
            }
            case 3: {
                return HpkeAead.CHACHA20_POLY1305;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == HpkeAead.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return HpkeAead.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return HpkeAeadVerifier.INSTANCE;
    }

    @Deprecated
    public static HpkeAead valueOf(int v) {
        return HpkeAead.forNumber(v);
    }
}

