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
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
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

final class AesEaxProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesEaxKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$V0XyUfwBtjzg7ct2AwYDLu4L-cg(AesEaxParameters aesEaxParameters0) [...]

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$hCX1FKbCEXveJ2WRy7RTy10wxag(AesEaxKey aesEaxKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static AesEaxKey $r8$lambda$hHwfqfxxleoKWfpTwJrxjQsq6qk(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static AesEaxParameters $r8$lambda$yIrOkSX0ibOU_I28OY2-wcZVsJQ(ProtoParametersSerialization protoParametersSerialization0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.AesEaxKey");
        AesEaxProtoSerialization.TYPE_URL_BYTES = bytes0;
        AesEaxProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((AesEaxParameters aesEaxParameters0) -> AesEaxProtoSerialization.serializeParameters(aesEaxParameters0), AesEaxParameters.class, ProtoParametersSerialization.class);
        AesEaxProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> AesEaxProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        AesEaxProtoSerialization.KEY_SERIALIZER = KeySerializer.create((AesEaxKey aesEaxKey0, SecretKeyAccess secretKeyAccess0) -> AesEaxProtoSerialization.serializeKey(aesEaxKey0, secretKeyAccess0), AesEaxKey.class, ProtoKeySerialization.class);
        AesEaxProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> AesEaxProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static AesEaxParams getProtoParams(AesEaxParameters aesEaxParameters0) throws GeneralSecurityException {
        if(aesEaxParameters0.getTagSizeBytes() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports aes eax keys with tag size equal to 16 bytes.", aesEaxParameters0.getTagSizeBytes()));
        }
        return (AesEaxParams)AesEaxParams.newBuilder().setIvSize(aesEaxParameters0.getIvSizeBytes()).build();
    }

    private static AesEaxKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                com.google.crypto.tink.proto.AesEaxKey aesEaxKey0 = com.google.crypto.tink.proto.AesEaxKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(aesEaxKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                AesEaxParameters aesEaxParameters0 = AesEaxParameters.builder().setKeySizeBytes(aesEaxKey0.getKeyValue().size()).setIvSizeBytes(aesEaxKey0.getParams().getIvSize()).setTagSizeBytes(16).setVariant(AesEaxProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                return AesEaxKey.builder().setParameters(aesEaxParameters0).setKeyBytes(SecretBytes.copyFrom(aesEaxKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
            }
            catch(InvalidProtocolBufferException unused_ex) {
                throw new GeneralSecurityException("Parsing AesEaxcKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesEaxParameters.parseParameters");
    }

    // 去混淆评级： 中等(80)
    private static AesEaxParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to AesEaxParameters.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        AesEaxProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(AesEaxProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(AesEaxProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(AesEaxProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(AesEaxProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(AesEaxKey aesEaxKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = ((com.google.crypto.tink.proto.AesEaxKey)com.google.crypto.tink.proto.AesEaxKey.newBuilder().setParams(AesEaxProtoSerialization.getProtoParams(aesEaxKey0.getParameters())).setKeyValue(ByteString.copyFrom(aesEaxKey0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = AesEaxProtoSerialization.toProtoOutputPrefixType(aesEaxKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.AesEaxKey", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, aesEaxKey0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(AesEaxParameters aesEaxParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesEaxKey").setValue(((AesEaxKeyFormat)AesEaxKeyFormat.newBuilder().setParams(AesEaxProtoSerialization.getProtoParams(aesEaxParameters0)).setKeySize(aesEaxParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(AesEaxProtoSerialization.toProtoOutputPrefixType(aesEaxParameters0.getVariant())).build()));
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant aesEaxParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(aesEaxParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(aesEaxParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(!Variant.NO_PREFIX.equals(aesEaxParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + aesEaxParameters$Variant0);
        }
        return OutputPrefixType.RAW;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.aead.AesEaxProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

    class com.google.crypto.tink.aead.AesEaxProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.aead.AesEaxProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesEaxProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesEaxProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesEaxProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

