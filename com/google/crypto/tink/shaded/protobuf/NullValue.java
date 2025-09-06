package com.google.crypto.tink.shaded.protobuf;

public enum NullValue implements EnumLite {
    static final class NullValueVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            NullValueVerifier.INSTANCE = new NullValueVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return NullValue.forNumber(v) != null;
        }
    }

    NULL_VALUE(0),
    UNRECOGNIZED(-1);

    public static final int NULL_VALUE_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        NullValue.internalValueMap = new EnumLiteMap() {
            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }

            public NullValue findValueByNumber(int v) {
                return NullValue.forNumber(v);
            }
        };
    }

    private NullValue(int v1) {
        this.value = v1;
    }

    public static NullValue forNumber(int v) {
        return v == 0 ? NullValue.NULL_VALUE : null;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == NullValue.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return NullValue.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return NullValueVerifier.INSTANCE;
    }

    @Deprecated
    public static NullValue valueOf(int v) {
        return NullValue.forNumber(v);
    }
}

