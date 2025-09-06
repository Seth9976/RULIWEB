package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum EcPointFormat implements EnumLite {
    static final class EcPointFormatVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            EcPointFormatVerifier.INSTANCE = new EcPointFormatVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return EcPointFormat.forNumber(v) != null;
        }
    }

    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    DO_NOT_USE_CRUNCHY_UNCOMPRESSED(3),
    UNRECOGNIZED(-1);

    public static final int COMPRESSED_VALUE = 2;
    public static final int DO_NOT_USE_CRUNCHY_UNCOMPRESSED_VALUE = 3;
    public static final int UNCOMPRESSED_VALUE = 1;
    public static final int UNKNOWN_FORMAT_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        EcPointFormat.internalValueMap = new EnumLiteMap() {
            public EcPointFormat findValueByNumber(int v) {
                return EcPointFormat.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private EcPointFormat(int v1) {
        this.value = v1;
    }

    public static EcPointFormat forNumber(int v) {
        switch(v) {
            case 0: {
                return EcPointFormat.UNKNOWN_FORMAT;
            }
            case 1: {
                return EcPointFormat.UNCOMPRESSED;
            }
            case 2: {
                return EcPointFormat.COMPRESSED;
            }
            case 3: {
                return EcPointFormat.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == EcPointFormat.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return EcPointFormat.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return EcPointFormatVerifier.INSTANCE;
    }

    @Deprecated
    public static EcPointFormat valueOf(int v) {
        return EcPointFormat.forNumber(v);
    }
}

