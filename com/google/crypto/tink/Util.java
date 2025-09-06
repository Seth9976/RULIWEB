package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo.Builder;
import com.google.crypto.tink.proto.KeysetInfo.KeyInfo;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class Util {
    public static final Charset UTF_8;

    static {
        Util.UTF_8 = Charset.forName("UTF-8");
    }

    // 去混淆评级： 低(20)
    public static KeyInfo getKeyInfo(Key keyset$Key0) {
        return (KeyInfo)KeyInfo.newBuilder().setTypeUrl("").setStatus(keyset$Key0.getStatus()).setOutputPrefixType(keyset$Key0.getOutputPrefixType()).setKeyId(keyset$Key0.getKeyId()).build();
    }

    public static KeysetInfo getKeysetInfo(Keyset keyset0) {
        Builder keysetInfo$Builder0 = KeysetInfo.newBuilder().setPrimaryKeyId(keyset0.getPrimaryKeyId());
        for(Object object0: keyset0.getKeyList()) {
            keysetInfo$Builder0.addKeyInfo(Util.getKeyInfo(((Key)object0)));
        }
        return (KeysetInfo)keysetInfo$Builder0.build();
    }

    public static byte[] readAll(InputStream inputStream0) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        byte[] arr_b = new byte[0x400];
        int v;
        while((v = inputStream0.read(arr_b)) != -1) {
            byteArrayOutputStream0.write(arr_b, 0, v);
        }
        return byteArrayOutputStream0.toByteArray();
    }

    public static void validateKey(Key keyset$Key0) throws GeneralSecurityException {
        if(!keyset$Key0.hasKeyData()) {
            throw new GeneralSecurityException(String.format("key %d has no key data", keyset$Key0.getKeyId()));
        }
        if(keyset$Key0.getOutputPrefixType() == OutputPrefixType.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException(String.format("key %d has unknown prefix", keyset$Key0.getKeyId()));
        }
        if(keyset$Key0.getStatus() == KeyStatusType.UNKNOWN_STATUS) {
            throw new GeneralSecurityException(String.format("key %d has unknown status", keyset$Key0.getKeyId()));
        }
    }

    public static void validateKeyset(Keyset keyset0) throws GeneralSecurityException {
        int v = keyset0.getPrimaryKeyId();
        int v1 = 0;
        boolean z = false;
        boolean z1 = true;
        for(Object object0: keyset0.getKeyList()) {
            Key keyset$Key0 = (Key)object0;
            if(keyset$Key0.getStatus() == KeyStatusType.ENABLED) {
                Util.validateKey(keyset$Key0);
                if(keyset$Key0.getKeyId() == v) {
                    if(z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if(keyset$Key0.getKeyData().getKeyMaterialType() != KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    z1 = false;
                }
                ++v1;
            }
        }
        if(v1 == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if(!z && !z1) {
            throw new GeneralSecurityException("keyset doesn\'t contain a valid primary key");
        }
    }
}

