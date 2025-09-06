package com.google.crypto.tink.shaded.protobuf;

public enum Syntax implements EnumLite {
    static final class SyntaxVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            SyntaxVerifier.INSTANCE = new SyntaxVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return Syntax.forNumber(v) != null;
        }
    }

    SYNTAX_PROTO2(0),
    SYNTAX_PROTO3(1),
    UNRECOGNIZED(-1);

    public static final int SYNTAX_PROTO2_VALUE = 0;
    public static final int SYNTAX_PROTO3_VALUE = 1;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        Syntax.internalValueMap = new EnumLiteMap() {
            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }

            public Syntax findValueByNumber(int v) {
                return Syntax.forNumber(v);
            }
        };
    }

    private Syntax(int v1) {
        this.value = v1;
    }

    public static Syntax forNumber(int v) {
        switch(v) {
            case 0: {
                return Syntax.SYNTAX_PROTO2;
            }
            case 1: {
                return Syntax.SYNTAX_PROTO3;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == Syntax.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return Syntax.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return SyntaxVerifier.INSTANCE;
    }

    @Deprecated
    public static Syntax valueOf(int v) {
        return Syntax.forNumber(v);
    }
}

