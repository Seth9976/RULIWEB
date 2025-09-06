package com.google.crypto.tink;

import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class CryptoFormat {
    public static final int LEGACY_PREFIX_SIZE = 5;
    public static final byte LEGACY_START_BYTE = 0;
    public static final int NON_RAW_PREFIX_SIZE = 5;
    public static final byte[] RAW_PREFIX = null;
    public static final int RAW_PREFIX_SIZE = 0;
    public static final int TINK_PREFIX_SIZE = 5;
    public static final byte TINK_START_BYTE = 1;

    static {
        CryptoFormat.RAW_PREFIX = new byte[0];
    }

    public static byte[] getOutputPrefix(Key keyset$Key0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.CryptoFormat.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[keyset$Key0.getOutputPrefixType().ordinal()]) {
            case 1: 
            case 2: {
                return ByteBuffer.allocate(5).put(0).putInt(keyset$Key0.getKeyId()).array();
            }
            case 3: {
                return ByteBuffer.allocate(5).put(1).putInt(keyset$Key0.getKeyId()).array();
            }
            case 4: {
                return CryptoFormat.RAW_PREFIX;
            }
            default: {
                throw new GeneralSecurityException("unknown output prefix type");
            }
        }
    }

    class com.google.crypto.tink.CryptoFormat.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.CryptoFormat.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.LEGACY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.CryptoFormat.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.CryptoFormat.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.TINK.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.CryptoFormat.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

