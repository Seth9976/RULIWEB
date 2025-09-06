package com.google.crypto.tink.mac;

import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class MacKeyTemplates {
    public static final KeyTemplate AES_CMAC;
    public static final KeyTemplate HMAC_SHA256_128BITTAG;
    public static final KeyTemplate HMAC_SHA256_256BITTAG;
    public static final KeyTemplate HMAC_SHA512_256BITTAG;
    public static final KeyTemplate HMAC_SHA512_512BITTAG;

    static {
        MacKeyTemplates.HMAC_SHA256_128BITTAG = MacKeyTemplates.createHmacKeyTemplate(0x20, 16, HashType.SHA256);
        MacKeyTemplates.HMAC_SHA256_256BITTAG = MacKeyTemplates.createHmacKeyTemplate(0x20, 0x20, HashType.SHA256);
        MacKeyTemplates.HMAC_SHA512_256BITTAG = MacKeyTemplates.createHmacKeyTemplate(0x40, 0x20, HashType.SHA512);
        MacKeyTemplates.HMAC_SHA512_512BITTAG = MacKeyTemplates.createHmacKeyTemplate(0x40, 0x40, HashType.SHA512);
        MacKeyTemplates.AES_CMAC = (KeyTemplate)KeyTemplate.newBuilder().setValue(((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setKeySize(0x20).setParams(((AesCmacParams)AesCmacParams.newBuilder().setTagSize(16).build())).build()).toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesCmacKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createHmacKeyTemplate(int v, int v1, HashType hashType0) {
        HmacParams hmacParams0 = (HmacParams)HmacParams.newBuilder().setHash(hashType0).setTagSize(v1).build();
        HmacKeyFormat hmacKeyFormat0 = (HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(hmacParams0).setKeySize(v).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(hmacKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.HmacKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}

