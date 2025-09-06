package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum EcdsaSignatureEncoding implements EnumLite {
    static final class EcdsaSignatureEncodingVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            EcdsaSignatureEncodingVerifier.INSTANCE = new EcdsaSignatureEncodingVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return EcdsaSignatureEncoding.forNumber(v) != null;
        }
    }

    UNKNOWN_ENCODING(0),
    IEEE_P1363(1),
    DER(2),
    UNRECOGNIZED(-1);

    public static final int DER_VALUE = 2;
    public static final int IEEE_P1363_VALUE = 1;
    public static final int UNKNOWN_ENCODING_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        EcdsaSignatureEncoding.internalValueMap = new EnumLiteMap() {
            public EcdsaSignatureEncoding findValueByNumber(int v) {
                return EcdsaSignatureEncoding.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private EcdsaSignatureEncoding(int v1) {
        this.value = v1;
    }

    public static EcdsaSignatureEncoding forNumber(int v) {
        switch(v) {
            case 0: {
                return EcdsaSignatureEncoding.UNKNOWN_ENCODING;
            }
            case 1: {
                return EcdsaSignatureEncoding.IEEE_P1363;
            }
            case 2: {
                return EcdsaSignatureEncoding.DER;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == EcdsaSignatureEncoding.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return EcdsaSignatureEncoding.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return EcdsaSignatureEncodingVerifier.INSTANCE;
    }

    @Deprecated
    public static EcdsaSignatureEncoding valueOf(int v) {
        return EcdsaSignatureEncoding.forNumber(v);
    }
}

