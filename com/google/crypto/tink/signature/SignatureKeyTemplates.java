package com.google.crypto.tink.signature;

import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.math.BigInteger;
import java.security.spec.RSAKeyGenParameterSpec;

public final class SignatureKeyTemplates {
    public static final KeyTemplate ECDSA_P256;
    public static final KeyTemplate ECDSA_P256_IEEE_P1363;
    public static final KeyTemplate ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX;
    public static final KeyTemplate ECDSA_P384;
    public static final KeyTemplate ECDSA_P384_IEEE_P1363;
    public static final KeyTemplate ECDSA_P521;
    public static final KeyTemplate ECDSA_P521_IEEE_P1363;
    public static final KeyTemplate ED25519;
    public static final KeyTemplate ED25519WithRawOutput;
    public static final KeyTemplate RSA_SSA_PKCS1_3072_SHA256_F4;
    public static final KeyTemplate RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX;
    public static final KeyTemplate RSA_SSA_PKCS1_4096_SHA512_F4;
    public static final KeyTemplate RSA_SSA_PSS_3072_SHA256_SHA256_32_F4;
    public static final KeyTemplate RSA_SSA_PSS_4096_SHA512_SHA512_64_F4;

    static {
        SignatureKeyTemplates.ECDSA_P256 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK);
        SignatureKeyTemplates.ECDSA_P384 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK);
        SignatureKeyTemplates.ECDSA_P521 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.DER, OutputPrefixType.TINK);
        SignatureKeyTemplates.ECDSA_P256_IEEE_P1363 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK);
        SignatureKeyTemplates.ECDSA_P384_IEEE_P1363 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK);
        SignatureKeyTemplates.ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.RAW);
        SignatureKeyTemplates.ECDSA_P521_IEEE_P1363 = SignatureKeyTemplates.createEcdsaKeyTemplate(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.IEEE_P1363, OutputPrefixType.TINK);
        SignatureKeyTemplates.ED25519 = (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey").setOutputPrefixType(OutputPrefixType.TINK).build();
        SignatureKeyTemplates.ED25519WithRawOutput = (KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey").setOutputPrefixType(OutputPrefixType.RAW).build();
        SignatureKeyTemplates.RSA_SSA_PKCS1_3072_SHA256_F4 = SignatureKeyTemplates.createRsaSsaPkcs1KeyTemplate(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
        SignatureKeyTemplates.RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX = SignatureKeyTemplates.createRsaSsaPkcs1KeyTemplate(HashType.SHA256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW);
        SignatureKeyTemplates.RSA_SSA_PKCS1_4096_SHA512_F4 = SignatureKeyTemplates.createRsaSsaPkcs1KeyTemplate(HashType.SHA512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK);
        SignatureKeyTemplates.RSA_SSA_PSS_3072_SHA256_SHA256_32_F4 = SignatureKeyTemplates.createRsaSsaPssKeyTemplate(HashType.SHA256, HashType.SHA256, 0x20, 0xC00, RSAKeyGenParameterSpec.F4);
        SignatureKeyTemplates.RSA_SSA_PSS_4096_SHA512_SHA512_64_F4 = SignatureKeyTemplates.createRsaSsaPssKeyTemplate(HashType.SHA512, HashType.SHA512, 0x40, 0x1000, RSAKeyGenParameterSpec.F4);
    }

    public static KeyTemplate createEcdsaKeyTemplate(HashType hashType0, EllipticCurveType ellipticCurveType0, EcdsaSignatureEncoding ecdsaSignatureEncoding0, OutputPrefixType outputPrefixType0) {
        EcdsaParams ecdsaParams0 = (EcdsaParams)EcdsaParams.newBuilder().setHashType(hashType0).setCurve(ellipticCurveType0).setEncoding(ecdsaSignatureEncoding0).build();
        EcdsaKeyFormat ecdsaKeyFormat0 = (EcdsaKeyFormat)EcdsaKeyFormat.newBuilder().setParams(ecdsaParams0).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(ecdsaKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey").setOutputPrefixType(outputPrefixType0).build();
    }

    public static KeyTemplate createRsaSsaPkcs1KeyTemplate(HashType hashType0, int v, BigInteger bigInteger0, OutputPrefixType outputPrefixType0) {
        RsaSsaPkcs1Params rsaSsaPkcs1Params0 = (RsaSsaPkcs1Params)RsaSsaPkcs1Params.newBuilder().setHashType(hashType0).build();
        RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat0 = (RsaSsaPkcs1KeyFormat)RsaSsaPkcs1KeyFormat.newBuilder().setParams(rsaSsaPkcs1Params0).setModulusSizeInBits(v).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(rsaSsaPkcs1KeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey").setOutputPrefixType(outputPrefixType0).build();
    }

    public static KeyTemplate createRsaSsaPssKeyTemplate(HashType hashType0, HashType hashType1, int v, int v1, BigInteger bigInteger0) {
        RsaSsaPssParams rsaSsaPssParams0 = (RsaSsaPssParams)RsaSsaPssParams.newBuilder().setSigHash(hashType0).setMgf1Hash(hashType1).setSaltLength(v).build();
        RsaSsaPssKeyFormat rsaSsaPssKeyFormat0 = (RsaSsaPssKeyFormat)RsaSsaPssKeyFormat.newBuilder().setParams(rsaSsaPssParams0).setModulusSizeInBits(v1).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(rsaSsaPssKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}

