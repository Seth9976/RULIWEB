package com.google.crypto.tink.signature;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

final class EcdsaProtoSerialization {
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final KeyParser PRIVATE_KEY_PARSER = null;
    private static final KeySerializer PRIVATE_KEY_SERIALIZER = null;
    private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    private static final Bytes PRIVATE_TYPE_URL_BYTES = null;
    private static final KeyParser PUBLIC_KEY_PARSER = null;
    private static final KeySerializer PUBLIC_KEY_SERIALIZER = null;
    private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    private static final Bytes PUBLIC_TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static EcdsaParameters $r8$lambda$-1QaVTEpxVoK9ADilVdLTv7koG4(ProtoParametersSerialization protoParametersSerialization0) [...]

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$QL64PlliBOEO_yvpgc5iADKWtsE(EcdsaPrivateKey ecdsaPrivateKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$XUL5p5QSPtLET6REQv92nwwzNDw(EcdsaParameters ecdsaParameters0) [...]

    // 检测为 Lambda 实现
    public static EcdsaPrivateKey $r8$lambda$fyP_R8_FXP7yUskYeHgYdWzzYCM(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$yE7K0ejbZkHm9FdR1y2N5HSU7y8(EcdsaPublicKey ecdsaPublicKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static EcdsaPublicKey $r8$lambda$yS9Hwh6FSrUpXOxo6QSA4zOibRQ(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey");
        EcdsaProtoSerialization.PRIVATE_TYPE_URL_BYTES = bytes0;
        Bytes bytes1 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.EcdsaPublicKey");
        EcdsaProtoSerialization.PUBLIC_TYPE_URL_BYTES = bytes1;
        EcdsaProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((EcdsaParameters ecdsaParameters0) -> EcdsaProtoSerialization.serializeParameters(ecdsaParameters0), EcdsaParameters.class, ProtoParametersSerialization.class);
        EcdsaProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> EcdsaProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        EcdsaProtoSerialization.PUBLIC_KEY_SERIALIZER = KeySerializer.create((EcdsaPublicKey ecdsaPublicKey0, SecretKeyAccess secretKeyAccess0) -> EcdsaProtoSerialization.serializePublicKey(ecdsaPublicKey0, secretKeyAccess0), EcdsaPublicKey.class, ProtoKeySerialization.class);
        EcdsaProtoSerialization.PUBLIC_KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> EcdsaProtoSerialization.parsePublicKey(protoKeySerialization0, secretKeyAccess0), bytes1, ProtoKeySerialization.class);
        EcdsaProtoSerialization.PRIVATE_KEY_SERIALIZER = KeySerializer.create((EcdsaPrivateKey ecdsaPrivateKey0, SecretKeyAccess secretKeyAccess0) -> EcdsaProtoSerialization.serializePrivateKey(ecdsaPrivateKey0, secretKeyAccess0), EcdsaPrivateKey.class, ProtoKeySerialization.class);
        EcdsaProtoSerialization.PRIVATE_KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> EcdsaProtoSerialization.parsePrivateKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static int getEncodingLength(CurveType ecdsaParameters$CurveType0) throws GeneralSecurityException {
        if(CurveType.NIST_P256.equals(ecdsaParameters$CurveType0)) {
            return 33;
        }
        if(CurveType.NIST_P384.equals(ecdsaParameters$CurveType0)) {
            return 49;
        }
        if(!CurveType.NIST_P521.equals(ecdsaParameters$CurveType0)) {
            throw new GeneralSecurityException("Unable to serialize CurveType " + ecdsaParameters$CurveType0);
        }
        return 67;
    }

    private static EcdsaParams getProtoParams(EcdsaParameters ecdsaParameters0) throws GeneralSecurityException {
        return (EcdsaParams)EcdsaParams.newBuilder().setHashType(EcdsaProtoSerialization.toProtoHashType(ecdsaParameters0.getHashType())).setCurve(EcdsaProtoSerialization.toProtoCurveType(ecdsaParameters0.getCurveType())).setEncoding(EcdsaProtoSerialization.toProtoSignatureEncoding(ecdsaParameters0.getSignatureEncoding())).build();
    }

    private static com.google.crypto.tink.proto.EcdsaPublicKey getProtoPublicKey(EcdsaPublicKey ecdsaPublicKey0) throws GeneralSecurityException {
        int v = EcdsaProtoSerialization.getEncodingLength(ecdsaPublicKey0.getParameters().getCurveType());
        ECPoint eCPoint0 = ecdsaPublicKey0.getPublicPoint();
        return (com.google.crypto.tink.proto.EcdsaPublicKey)com.google.crypto.tink.proto.EcdsaPublicKey.newBuilder().setParams(EcdsaProtoSerialization.getProtoParams(ecdsaPublicKey0.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(eCPoint0.getAffineX(), v))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(eCPoint0.getAffineY(), v))).build();
    }

    // 去混淆评级： 中等(80)
    private static EcdsaParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parseParameters: ");
    }

    private static EcdsaPrivateKey parsePrivateKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey")) {
            try {
                com.google.crypto.tink.proto.EcdsaPrivateKey ecdsaPrivateKey0 = com.google.crypto.tink.proto.EcdsaPrivateKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(ecdsaPrivateKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                com.google.crypto.tink.proto.EcdsaPublicKey ecdsaPublicKey0 = ecdsaPrivateKey0.getPublicKey();
                EcdsaParameters ecdsaParameters0 = EcdsaParameters.builder().setHashType(EcdsaProtoSerialization.toHashType(ecdsaPublicKey0.getParams().getHashType())).setSignatureEncoding(EcdsaProtoSerialization.toSignatureEncoding(ecdsaPublicKey0.getParams().getEncoding())).setCurveType(EcdsaProtoSerialization.toCurveType(ecdsaPublicKey0.getParams().getCurve())).setVariant(EcdsaProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                EcdsaPublicKey ecdsaPublicKey1 = EcdsaPublicKey.builder().setParameters(ecdsaParameters0).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(ecdsaPublicKey0.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(ecdsaPublicKey0.getY().toByteArray()))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
                return EcdsaPrivateKey.builder().setPublicKey(ecdsaPublicKey1).setPrivateValue(SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(ecdsaPrivateKey0.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(secretKeyAccess0))).build();
            }
            catch(InvalidProtocolBufferException | IllegalArgumentException unused_ex) {
                throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: " + protoKeySerialization0.getTypeUrl());
    }

    private static EcdsaPublicKey parsePublicKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.EcdsaPublicKey")) {
            try {
                com.google.crypto.tink.proto.EcdsaPublicKey ecdsaPublicKey0 = com.google.crypto.tink.proto.EcdsaPublicKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(ecdsaPublicKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                EcdsaParameters ecdsaParameters0 = EcdsaParameters.builder().setHashType(EcdsaProtoSerialization.toHashType(ecdsaPublicKey0.getParams().getHashType())).setSignatureEncoding(EcdsaProtoSerialization.toSignatureEncoding(ecdsaPublicKey0.getParams().getEncoding())).setCurveType(EcdsaProtoSerialization.toCurveType(ecdsaPublicKey0.getParams().getCurve())).setVariant(EcdsaProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                return EcdsaPublicKey.builder().setParameters(ecdsaParameters0).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(ecdsaPublicKey0.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(ecdsaPublicKey0.getY().toByteArray()))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
            }
            catch(InvalidProtocolBufferException | IllegalArgumentException unused_ex) {
                throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: " + protoKeySerialization0.getTypeUrl());
    }

    public static void register() throws GeneralSecurityException {
        EcdsaProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(EcdsaProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(EcdsaProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(EcdsaProtoSerialization.PUBLIC_KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(EcdsaProtoSerialization.PUBLIC_KEY_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(EcdsaProtoSerialization.PRIVATE_KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(EcdsaProtoSerialization.PRIVATE_KEY_PARSER);
    }

    private static ProtoParametersSerialization serializeParameters(EcdsaParameters ecdsaParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey").setValue(((EcdsaKeyFormat)EcdsaKeyFormat.newBuilder().setParams(EcdsaProtoSerialization.getProtoParams(ecdsaParameters0)).build()).toByteString()).setOutputPrefixType(EcdsaProtoSerialization.toProtoOutputPrefixType(ecdsaParameters0.getVariant())).build()));
    }

    private static ProtoKeySerialization serializePrivateKey(EcdsaPrivateKey ecdsaPrivateKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        int v = EcdsaProtoSerialization.getEncodingLength(ecdsaPrivateKey0.getParameters().getCurveType());
        ByteString byteString0 = ((com.google.crypto.tink.proto.EcdsaPrivateKey)com.google.crypto.tink.proto.EcdsaPrivateKey.newBuilder().setPublicKey(EcdsaProtoSerialization.getProtoPublicKey(ecdsaPrivateKey0.getPublicKey())).setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(ecdsaPrivateKey0.getPrivateValue().getBigInteger(SecretKeyAccess.requireAccess(secretKeyAccess0)), v))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = EcdsaProtoSerialization.toProtoOutputPrefixType(ecdsaPrivateKey0.getParameters().getVariant());
        Integer integer0 = ecdsaPrivateKey0.getIdRequirementOrNull();
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey", byteString0, KeyMaterialType.ASYMMETRIC_PRIVATE, outputPrefixType0, integer0);
    }

    private static ProtoKeySerialization serializePublicKey(EcdsaPublicKey ecdsaPublicKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = EcdsaProtoSerialization.getProtoPublicKey(ecdsaPublicKey0).toByteString();
        OutputPrefixType outputPrefixType0 = EcdsaProtoSerialization.toProtoOutputPrefixType(ecdsaPublicKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.EcdsaPublicKey", byteString0, KeyMaterialType.ASYMMETRIC_PUBLIC, outputPrefixType0, ecdsaPublicKey0.getIdRequirementOrNull());
    }

    private static CurveType toCurveType(EllipticCurveType ellipticCurveType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.EcdsaProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType0.ordinal()]) {
            case 1: {
                return CurveType.NIST_P256;
            }
            case 2: {
                return CurveType.NIST_P384;
            }
            case 3: {
                return CurveType.NIST_P521;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse EllipticCurveType: " + ellipticCurveType0.getNumber());
            }
        }
    }

    private static HashType toHashType(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.EcdsaProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return HashType.SHA256;
            }
            case 2: {
                return HashType.SHA384;
            }
            case 3: {
                return HashType.SHA512;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse HashType: " + hashType0.getNumber());
            }
        }
    }

    private static EllipticCurveType toProtoCurveType(CurveType ecdsaParameters$CurveType0) throws GeneralSecurityException {
        if(CurveType.NIST_P256.equals(ecdsaParameters$CurveType0)) {
            return EllipticCurveType.NIST_P256;
        }
        if(CurveType.NIST_P384.equals(ecdsaParameters$CurveType0)) {
            return EllipticCurveType.NIST_P384;
        }
        if(!CurveType.NIST_P521.equals(ecdsaParameters$CurveType0)) {
            throw new GeneralSecurityException("Unable to serialize CurveType " + ecdsaParameters$CurveType0);
        }
        return EllipticCurveType.NIST_P521;
    }

    private static com.google.crypto.tink.proto.HashType toProtoHashType(HashType ecdsaParameters$HashType0) throws GeneralSecurityException {
        if(HashType.SHA256.equals(ecdsaParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA256;
        }
        if(HashType.SHA384.equals(ecdsaParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA384;
        }
        if(!HashType.SHA512.equals(ecdsaParameters$HashType0)) {
            throw new GeneralSecurityException("Unable to serialize HashType " + ecdsaParameters$HashType0);
        }
        return com.google.crypto.tink.proto.HashType.SHA512;
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant ecdsaParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(ecdsaParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(ecdsaParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(Variant.NO_PREFIX.equals(ecdsaParameters$Variant0)) {
            return OutputPrefixType.RAW;
        }
        if(!Variant.LEGACY.equals(ecdsaParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + ecdsaParameters$Variant0);
        }
        return OutputPrefixType.LEGACY;
    }

    private static EcdsaSignatureEncoding toProtoSignatureEncoding(SignatureEncoding ecdsaParameters$SignatureEncoding0) throws GeneralSecurityException {
        if(SignatureEncoding.IEEE_P1363.equals(ecdsaParameters$SignatureEncoding0)) {
            return EcdsaSignatureEncoding.IEEE_P1363;
        }
        if(!SignatureEncoding.DER.equals(ecdsaParameters$SignatureEncoding0)) {
            throw new GeneralSecurityException("Unable to serialize SignatureEncoding " + ecdsaParameters$SignatureEncoding0);
        }
        return EcdsaSignatureEncoding.DER;
    }

    private static SignatureEncoding toSignatureEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.EcdsaProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[ecdsaSignatureEncoding0.ordinal()]) {
            case 1: {
                return SignatureEncoding.IEEE_P1363;
            }
            case 2: {
                return SignatureEncoding.DER;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse EcdsaSignatureEncoding: " + ecdsaSignatureEncoding0.getNumber());
            }
        }
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.signature.EcdsaProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
            case 1: {
                return Variant.TINK;
            }
            case 2: {
                return Variant.CRUNCHY;
            }
            case 3: {
                return Variant.LEGACY;
            }
            case 4: {
                return Variant.NO_PREFIX;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType0.getNumber());
            }
        }
    }
}

