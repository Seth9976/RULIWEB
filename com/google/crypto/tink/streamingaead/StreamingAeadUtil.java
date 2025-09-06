package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.proto.HashType;
import java.security.NoSuchAlgorithmException;

final class StreamingAeadUtil {
    public static String toHmacAlgo(HashType hashType0) throws NoSuchAlgorithmException {
        switch(com.google.crypto.tink.streamingaead.StreamingAeadUtil.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return "HmacSha1";
            }
            case 2: {
                return "HmacSha224";
            }
            case 3: {
                return "HmacSha256";
            }
            case 4: {
                return "HmacSha384";
            }
            case 5: {
                return "HmacSha512";
            }
            default: {
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + hashType0);
            }
        }
    }
}

