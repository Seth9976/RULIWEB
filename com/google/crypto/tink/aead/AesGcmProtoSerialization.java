package com.google.crypto.tink.aead;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

final class AesGcmProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$EUz4ME7O9dcEVpDksD4G5pFlPQA(AesGcmKey aesGcmKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static AesGcmParameters $r8$lambda$uQT556We0XfoRJcWuy27A6jAebE(ProtoParametersSerialization protoParametersSerialization0) [...]

    // 检测为 Lambda 实现
    public static AesGcmKey $r8$lambda$vgZA9XPoksba5kVX1QfZs6Wg60E(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$xlok2PY_IO6uwuqHTkpSWLES9S8(AesGcmParameters aesGcmParameters0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.AesGcmKey");
        AesGcmProtoSerialization.TYPE_URL_BYTES = bytes0;
        AesGcmProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((AesGcmParameters aesGcmParameters0) -> AesGcmProtoSerialization.serializeParameters(aesGcmParameters0), AesGcmParameters.class, ProtoParametersSerialization.class);
        AesGcmProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> AesGcmProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        AesGcmProtoSerialization.KEY_SERIALIZER = KeySerializer.create((AesGcmKey aesGcmKey0, SecretKeyAccess secretKeyAccess0) -> AesGcmProtoSerialization.serializeKey(aesGcmKey0, secretKeyAccess0), AesGcmKey.class, ProtoKeySerialization.class);
        AesGcmProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> AesGcmProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static AesGcmKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                com.google.crypto.tink.proto.AesGcmKey aesGcmKey0 = com.google.crypto.tink.proto.AesGcmKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(aesGcmKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                AesGcmParameters aesGcmParameters0 = AesGcmParameters.builder().setKeySizeBytes(aesGcmKey0.getKeyValue().size()).setIvSizeBytes(12).setTagSizeBytes(16).setVariant(AesGcmProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                return AesGcmKey.builder().setParameters(aesGcmParameters0).setKeyBytes(SecretBytes.copyFrom(aesGcmKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
            }
            catch(InvalidProtocolBufferException unused_ex) {
                throw new GeneralSecurityException("Parsing AesGcmKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesGcmParameters.parseParameters");
    }

    // 去混淆评级： 中等(80)
    private static AesGcmParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to AesGcmParameters.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        AesGcmProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(AesGcmProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(AesGcmProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(AesGcmProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(AesGcmProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(AesGcmKey aesGcmKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        AesGcmProtoSerialization.validateParameters(aesGcmKey0.getParameters());
        ByteString byteString0 = ((com.google.crypto.tink.proto.AesGcmKey)com.google.crypto.tink.proto.AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(aesGcmKey0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = AesGcmProtoSerialization.toProtoOutputPrefixType(aesGcmKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.AesGcmKey", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, aesGcmKey0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(AesGcmParameters aesGcmParameters0) throws GeneralSecurityException {
        AesGcmProtoSerialization.validateParameters(aesGcmParameters0);
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesGcmKey").setValue(((AesGcmKeyFormat)AesGcmKeyFormat.newBuilder().setKeySize(aesGcmParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(AesGcmProtoSerialization.toProtoOutputPrefixType(aesGcmParameters0.getVariant())).build()));
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant aesGcmParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(aesGcmParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(aesGcmParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(!Variant.NO_PREFIX.equals(aesGcmParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + aesGcmParameters$Variant0);
        }
        return OutputPrefixType.RAW;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.aead.AesGcmProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
            case 1: {
                return Variant.TINK;
            }
            case 2: 
            case 3: {
                return Variant.CRUNCHY;
            }
            case 4: {
                return Variant.NO_PREFIX;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType0.getNumber());
            }
        }
    }

    private static void validateParameters(AesGcmParameters aesGcmParameters0) throws GeneralSecurityException {
        if(aesGcmParameters0.getTagSizeBytes() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports serialization of AES GCM keys with tag size equal to 16 bytes.", aesGcmParameters0.getTagSizeBytes()));
        }
        if(aesGcmParameters0.getIvSizeBytes() != 12) {
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d. Currently Tink only supports serialization of AES GCM keys with IV size equal to 12 bytes.", aesGcmParameters0.getIvSizeBytes()));
        }
    }

    class com.google.crypto.tink.aead.AesGcmProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.aead.AesGcmProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

