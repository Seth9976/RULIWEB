package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum OutputPrefixType implements EnumLite {
    static final class OutputPrefixTypeVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            OutputPrefixTypeVerifier.INSTANCE = new OutputPrefixTypeVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return OutputPrefixType.forNumber(v) != null;
        }
    }

    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);

    public static final int CRUNCHY_VALUE = 4;
    public static final int LEGACY_VALUE = 2;
    public static final int RAW_VALUE = 3;
    public static final int TINK_VALUE = 1;
    public static final int UNKNOWN_PREFIX_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        OutputPrefixType.internalValueMap = new EnumLiteMap() {
            public OutputPrefixType findValueByNumber(int v) {
                return OutputPrefixType.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private OutputPrefixType(int v1) {
        this.value = v1;
    }

    public static OutputPrefixType forNumber(int v) {
        switch(v) {
            case 0: {
                return OutputPrefixType.UNKNOWN_PREFIX;
            }
            case 1: {
                return OutputPrefixType.TINK;
            }
            case 2: {
                return OutputPrefixType.LEGACY;
            }
            case 3: {
                return OutputPrefixType.RAW;
            }
            case 4: {
                return OutputPrefixType.CRUNCHY;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == OutputPrefixType.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return OutputPrefixType.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return OutputPrefixTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static OutputPrefixType valueOf(int v) {
        return OutputPrefixType.forNumber(v);
    }
}

