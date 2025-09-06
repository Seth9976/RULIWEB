package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum HashType implements EnumLite {
    static final class HashTypeVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            HashTypeVerifier.INSTANCE = new HashTypeVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return HashType.forNumber(v) != null;
        }
    }

    UNKNOWN_HASH(0),
    SHA1(1),
    SHA384(2),
    SHA256(3),
    SHA512(4),
    SHA224(5),
    UNRECOGNIZED(-1);

    public static final int SHA1_VALUE = 1;
    public static final int SHA224_VALUE = 5;
    public static final int SHA256_VALUE = 3;
    public static final int SHA384_VALUE = 2;
    public static final int SHA512_VALUE = 4;
    public static final int UNKNOWN_HASH_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        HashType.internalValueMap = new EnumLiteMap() {
            public HashType findValueByNumber(int v) {
                return HashType.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private HashType(int v1) {
        this.value = v1;
    }

    public static HashType forNumber(int v) {
        switch(v) {
            case 0: {
                return HashType.UNKNOWN_HASH;
            }
            case 1: {
                return HashType.SHA1;
            }
            case 2: {
                return HashType.SHA384;
            }
            case 3: {
                return HashType.SHA256;
            }
            case 4: {
                return HashType.SHA512;
            }
            case 5: {
                return HashType.SHA224;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == HashType.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return HashType.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return HashTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static HashType valueOf(int v) {
        return HashType.forNumber(v);
    }
}

