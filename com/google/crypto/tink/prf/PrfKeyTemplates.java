package com.google.crypto.tink.prf;

import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class PrfKeyTemplates {
    public static final KeyTemplate AES_CMAC_PRF;
    public static final KeyTemplate HKDF_SHA256;
    public static final KeyTemplate HMAC_SHA256_PRF;
    public static final KeyTemplate HMAC_SHA512_PRF;

    static {
        PrfKeyTemplates.HKDF_SHA256 = PrfKeyTemplates.createHkdfKeyTemplate();
        PrfKeyTemplates.HMAC_SHA256_PRF = PrfKeyTemplates.createHmacTemplate(0x20, HashType.SHA256);
        PrfKeyTemplates.HMAC_SHA512_PRF = PrfKeyTemplates.createHmacTemplate(0x40, HashType.SHA512);
        PrfKeyTemplates.AES_CMAC_PRF = PrfKeyTemplates.createAes256CmacTemplate();
    }

    private static KeyTemplate createAes256CmacTemplate() {
        AesCmacPrfKeyFormat aesCmacPrfKeyFormat0 = (AesCmacPrfKeyFormat)AesCmacPrfKeyFormat.newBuilder().setKeySize(0x20).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesCmacPrfKey").setValue(aesCmacPrfKeyFormat0.toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    private static KeyTemplate createHkdfKeyTemplate() {
        HkdfPrfKeyFormat hkdfPrfKeyFormat0 = (HkdfPrfKeyFormat)HkdfPrfKeyFormat.newBuilder().setKeySize(0x20).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(hkdfPrfKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.HkdfPrfKey").setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    private static KeyTemplate createHmacTemplate(int v, HashType hashType0) {
        HmacPrfParams hmacPrfParams0 = (HmacPrfParams)HmacPrfParams.newBuilder().setHash(hashType0).build();
        HmacPrfKeyFormat hmacPrfKeyFormat0 = (HmacPrfKeyFormat)HmacPrfKeyFormat.newBuilder().setParams(hmacPrfParams0).setKeySize(v).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HmacPrfKey").setValue(hmacPrfKeyFormat0.toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }
}

