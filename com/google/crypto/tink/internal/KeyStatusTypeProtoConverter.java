package com.google.crypto.tink.internal;

import com.google.crypto.tink.tinkkey.KeyHandle.KeyStatusType;

public final class KeyStatusTypeProtoConverter {
    public static KeyStatusType fromProto(com.google.crypto.tink.proto.KeyStatusType keyStatusType0) {
        switch(com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType0.ordinal()]) {
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
                throw new IllegalArgumentException("Unknown key status type.");
            }
        }
    }

    public static com.google.crypto.tink.proto.KeyStatusType toProto(KeyStatusType keyHandle$KeyStatusType0) {
        switch(com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[keyHandle$KeyStatusType0.ordinal()]) {
            case 1: {
                return com.google.crypto.tink.proto.KeyStatusType.ENABLED;
            }
            case 2: {
                return com.google.crypto.tink.proto.KeyStatusType.DISABLED;
            }
            case 3: {
                return com.google.crypto.tink.proto.KeyStatusType.DESTROYED;
            }
            default: {
                throw new IllegalArgumentException("Unknown key status type.");
            }
        }
    }

    class com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;
        static final int[] $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType;

        static {
            int[] arr_v = new int[KeyStatusType.values().length];
            com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType = arr_v;
            try {
                arr_v[KeyStatusType.ENABLED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[com.google.crypto.tink.proto.KeyStatusType.values().length];
            com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType = arr_v1;
            try {
                arr_v1[com.google.crypto.tink.proto.KeyStatusType.ENABLED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[com.google.crypto.tink.proto.KeyStatusType.DISABLED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[com.google.crypto.tink.proto.KeyStatusType.DESTROYED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

