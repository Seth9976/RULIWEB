package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat;
import com.google.crypto.tink.proto.AesCtrHmacStreamingParams;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class StreamingAeadKeyTemplates {
    public static final KeyTemplate AES128_CTR_HMAC_SHA256_1MB;
    public static final KeyTemplate AES128_CTR_HMAC_SHA256_4KB;
    public static final KeyTemplate AES128_GCM_HKDF_1MB;
    public static final KeyTemplate AES128_GCM_HKDF_4KB;
    public static final KeyTemplate AES256_CTR_HMAC_SHA256_1MB;
    public static final KeyTemplate AES256_CTR_HMAC_SHA256_4KB;
    public static final KeyTemplate AES256_GCM_HKDF_1MB;
    public static final KeyTemplate AES256_GCM_HKDF_4KB;

    static {
        StreamingAeadKeyTemplates.AES128_CTR_HMAC_SHA256_4KB = StreamingAeadKeyTemplates.createAesCtrHmacStreamingKeyTemplate(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x1000);
        StreamingAeadKeyTemplates.AES128_CTR_HMAC_SHA256_1MB = StreamingAeadKeyTemplates.createAesCtrHmacStreamingKeyTemplate(16, HashType.SHA256, 16, HashType.SHA256, 0x20, 0x100000);
        StreamingAeadKeyTemplates.AES256_CTR_HMAC_SHA256_4KB = StreamingAeadKeyTemplates.createAesCtrHmacStreamingKeyTemplate(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x1000);
        StreamingAeadKeyTemplates.AES256_CTR_HMAC_SHA256_1MB = StreamingAeadKeyTemplates.createAesCtrHmacStreamingKeyTemplate(0x20, HashType.SHA256, 0x20, HashType.SHA256, 0x20, 0x100000);
        StreamingAeadKeyTemplates.AES128_GCM_HKDF_4KB = StreamingAeadKeyTemplates.createAesGcmHkdfStreamingKeyTemplate(16, HashType.SHA256, 16, 0x1000);
        StreamingAeadKeyTemplates.AES128_GCM_HKDF_1MB = StreamingAeadKeyTemplates.createAesGcmHkdfStreamingKeyTemplate(16, HashType.SHA256, 16, 0x100000);
        StreamingAeadKeyTemplates.AES256_GCM_HKDF_4KB = StreamingAeadKeyTemplates.createAesGcmHkdfStreamingKeyTemplate(0x20, HashType.SHA256, 0x20, 0x1000);
        StreamingAeadKeyTemplates.AES256_GCM_HKDF_1MB = StreamingAeadKeyTemplates.createAesGcmHkdfStreamingKeyTemplate(0x20, HashType.SHA256, 0x20, 0x100000);
    }

    public static KeyTemplate createAesCtrHmacStreamingKeyTemplate(int v, HashType hashType0, int v1, HashType hashType1, int v2, int v3) {
        HmacParams hmacParams0 = (HmacParams)HmacParams.newBuilder().setHash(hashType1).setTagSize(v2).build();
        AesCtrHmacStreamingParams aesCtrHmacStreamingParams0 = (AesCtrHmacStreamingParams)AesCtrHmacStreamingParams.newBuilder().setCiphertextSegmentSize(v3).setDerivedKeySize(v1).setHkdfHashType(hashType0).setHmacParams(hmacParams0).build();
        AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat0 = (AesCtrHmacStreamingKeyFormat)AesCtrHmacStreamingKeyFormat.newBuilder().setParams(aesCtrHmacStreamingParams0).setKeySize(v).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesCtrHmacStreamingKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey").setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    public static KeyTemplate createAesGcmHkdfStreamingKeyTemplate(int v, HashType hashType0, int v1, int v2) {
        AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams0 = (AesGcmHkdfStreamingParams)AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(v2).setDerivedKeySize(v1).setHkdfHashType(hashType0).build();
        AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat0 = (AesGcmHkdfStreamingKeyFormat)AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(v).setParams(aesGcmHkdfStreamingParams0).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesGcmHkdfStreamingKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey").setOutputPrefixType(OutputPrefixType.RAW).build();
    }
}

