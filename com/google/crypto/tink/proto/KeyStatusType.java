package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal.EnumLite;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap;
import com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier;

public enum KeyStatusType implements EnumLite {
    static final class KeyStatusTypeVerifier implements EnumVerifier {
        static final EnumVerifier INSTANCE;

        static {
            KeyStatusTypeVerifier.INSTANCE = new KeyStatusTypeVerifier();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumVerifier
        public boolean isInRange(int v) {
            return KeyStatusType.forNumber(v) != null;
        }
    }

    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);

    public static final int DESTROYED_VALUE = 3;
    public static final int DISABLED_VALUE = 2;
    public static final int ENABLED_VALUE = 1;
    public static final int UNKNOWN_STATUS_VALUE;
    private static final EnumLiteMap internalValueMap;
    private final int value;

    static {
        KeyStatusType.internalValueMap = new EnumLiteMap() {
            public KeyStatusType findValueByNumber(int v) {
                return KeyStatusType.forNumber(v);
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLiteMap
            public EnumLite findValueByNumber(int v) {
                return this.findValueByNumber(v);
            }
        };
    }

    private KeyStatusType(int v1) {
        this.value = v1;
    }

    public static KeyStatusType forNumber(int v) {
        switch(v) {
            case 0: {
                return KeyStatusType.UNKNOWN_STATUS;
            }
            case 1: {
                return KeyStatusType.ENABLED;
            }
            case 2: {
                return KeyStatusType.DISABLED;
            }
            case 3: {
                return KeyStatusType.DESTROYED;
            }
            default: {
                return null;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Internal$EnumLite
    public final int getNumber() {
        if(this == KeyStatusType.UNRECOGNIZED) {
            throw new IllegalArgumentException("Can\'t get the number of an unknown enum value.");
        }
        return this.value;
    }

    public static EnumLiteMap internalGetValueMap() {
        return KeyStatusType.internalValueMap;
    }

    public static EnumVerifier internalGetVerifier() {
        return KeyStatusTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static KeyStatusType valueOf(int v) {
        return KeyStatusType.forNumber(v);
    }
}

