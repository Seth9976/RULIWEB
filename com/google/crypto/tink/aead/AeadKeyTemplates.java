package com.google.crypto.tink.aead;

import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class AeadKeyTemplates {
    public static final KeyTemplate AES128_CTR_HMAC_SHA256;
    public static final KeyTemplate AES128_EAX;
    public static final KeyTemplate AES128_GCM;
    public static final KeyTemplate AES256_CTR_HMAC_SHA256;
    public static final KeyTemplate AES256_EAX;
    public static final KeyTemplate AES256_GCM;
    public static final KeyTemplate CHACHA20_POLY1305;
    public static final KeyTemplate XCHACHA20_POLY1305;

    static {
        AeadKeyTemplates.AES128_GCM = AeadKeyTemplates.createAesGcmKeyTemplate(16);
        AeadKeyTemplates.AES256_GCM = AeadKeyTemplates.createAesGcmKeyTemplate(0x20);
        AeadKeyTemplates.AES128_EAX = AeadKeyTemplates.createAesEaxKeyTemplate(16, 16);
        AeadKeyTemplates.AES256_EAX = AeadKeyTemplates.createAesEaxKeyTemplate(0x20, 16);
        AeadKeyTemplates.AES128_CTR_HMAC_SHA256 = AeadKeyTemplates.createAesCtrHmacAeadKeyTemplate(16, 16, 0x20, 16, HashType.SHA256);
        AeadKeyTemplates.AES256_CTR_HMAC_SHA256 = AeadKeyTemplates.createAesCtrHmacAeadKeyTemplate(0x20, 16, 0x20, 0x20, HashType.SHA256);
        AeadKeyTemplates.CHACHA20_POLY1305 = (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").setOutputPrefixType(OutputPrefixType.TINK).build();
        AeadKeyTemplates.XCHACHA20_POLY1305 = (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createAesCtrHmacAeadKeyTemplate(int v, int v1, int v2, int v3, HashType hashType0) {
        AesCtrKeyFormat aesCtrKeyFormat0 = (AesCtrKeyFormat)AesCtrKeyFormat.newBuilder().setParams(((AesCtrParams)AesCtrParams.newBuilder().setIvSize(v1).build())).setKeySize(v).build();
        HmacKeyFormat hmacKeyFormat0 = (HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(((HmacParams)HmacParams.newBuilder().setHash(hashType0).setTagSize(v3).build())).setKeySize(v2).build();
        AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0 = (AesCtrHmacAeadKeyFormat)AesCtrHmacAeadKeyFormat.newBuilder().setAesCtrKeyFormat(aesCtrKeyFormat0).setHmacKeyFormat(hmacKeyFormat0).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesCtrHmacAeadKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createAesEaxKeyTemplate(int v, int v1) {
        AesEaxKeyFormat aesEaxKeyFormat0 = (AesEaxKeyFormat)AesEaxKeyFormat.newBuilder().setKeySize(v).setParams(((AesEaxParams)AesEaxParams.newBuilder().setIvSize(v1).build())).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesEaxKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesEaxKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createAesGcmKeyTemplate(int v) {
        AesGcmKeyFormat aesGcmKeyFormat0 = (AesGcmKeyFormat)AesGcmKeyFormat.newBuilder().setKeySize(v).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesGcmKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesGcmKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createKmsAeadKeyTemplate(String s) {
        KmsAeadKeyFormat kmsAeadKeyFormat0 = (KmsAeadKeyFormat)KmsAeadKeyFormat.newBuilder().setKeyUri(s).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(kmsAeadKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.KmsAeadKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createKmsEnvelopeAeadKeyTemplate(String s, KeyTemplate keyTemplate0) {
        KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat0 = (KmsEnvelopeAeadKeyFormat)KmsEnvelopeAeadKeyFormat.newBuilder().setDekTemplate(keyTemplate0).setKekUri(s).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(kmsEnvelopeAeadKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").setOutputPrefixType(OutputPrefixType.RAW).build();
    }
}

