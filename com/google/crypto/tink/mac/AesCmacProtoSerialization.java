package com.google.crypto.tink.mac;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
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

final class AesCmacProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesCmacKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$7L351wgjnjO4ZKV0ZQV15gTo0vg(AesCmacKey aesCmacKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$XoAYR2uAeH9mIXNgu5uUJ0qQD-8(AesCmacParameters aesCmacParameters0) [...]

    // 检测为 Lambda 实现
    public static AesCmacKey $r8$lambda$aVUkw1tXpDU9iD7R6w_Z-dgG0sY(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static AesCmacParameters $r8$lambda$kP_363MC8_7156Kgw7KikH01Utk(ProtoParametersSerialization protoParametersSerialization0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.AesCmacKey");
        AesCmacProtoSerialization.TYPE_URL_BYTES = bytes0;
        AesCmacProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((AesCmacParameters aesCmacParameters0) -> AesCmacProtoSerialization.serializeParameters(aesCmacParameters0), AesCmacParameters.class, ProtoParametersSerialization.class);
        AesCmacProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> AesCmacProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        AesCmacProtoSerialization.KEY_SERIALIZER = KeySerializer.create((AesCmacKey aesCmacKey0, SecretKeyAccess secretKeyAccess0) -> AesCmacProtoSerialization.serializeKey(aesCmacKey0, secretKeyAccess0), AesCmacKey.class, ProtoKeySerialization.class);
        AesCmacProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> AesCmacProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static AesCmacParams getProtoParams(AesCmacParameters aesCmacParameters0) {
        return (AesCmacParams)AesCmacParams.newBuilder().setTagSize(aesCmacParameters0.getCryptographicTagSizeBytes()).build();
    }

    private static AesCmacKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                com.google.crypto.tink.proto.AesCmacKey aesCmacKey0 = com.google.crypto.tink.proto.AesCmacKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(aesCmacKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                AesCmacParameters aesCmacParameters0 = AesCmacParameters.builder().setKeySizeBytes(aesCmacKey0.getKeyValue().size()).setTagSizeBytes(aesCmacKey0.getParams().getTagSize()).setVariant(AesCmacProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                return AesCmacKey.builder().setParameters(aesCmacParameters0).setAesKeyBytes(SecretBytes.copyFrom(aesCmacKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
            }
            catch(InvalidProtocolBufferException | IllegalArgumentException unused_ex) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to AesCmacParameters.parseParameters");
    }

    // 去混淆评级： 中等(80)
    private static AesCmacParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to AesCmacParameters.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        AesCmacProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(AesCmacProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(AesCmacProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(AesCmacProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(AesCmacProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(AesCmacKey aesCmacKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = ((com.google.crypto.tink.proto.AesCmacKey)com.google.crypto.tink.proto.AesCmacKey.newBuilder().setParams(AesCmacProtoSerialization.getProtoParams(aesCmacKey0.getParameters())).setKeyValue(ByteString.copyFrom(aesCmacKey0.getAesKey().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = AesCmacProtoSerialization.toOutputPrefixType(aesCmacKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.AesCmacKey", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, aesCmacKey0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(AesCmacParameters aesCmacParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesCmacKey").setValue(((AesCmacKeyFormat)AesCmacKeyFormat.newBuilder().setParams(AesCmacProtoSerialization.getProtoParams(aesCmacParameters0)).setKeySize(aesCmacParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(AesCmacProtoSerialization.toOutputPrefixType(aesCmacParameters0.getVariant())).build()));
    }

    private static OutputPrefixType toOutputPrefixType(Variant aesCmacParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(aesCmacParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(aesCmacParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(Variant.NO_PREFIX.equals(aesCmacParameters$Variant0)) {
            return OutputPrefixType.RAW;
        }
        if(!Variant.LEGACY.equals(aesCmacParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + aesCmacParameters$Variant0);
        }
        return OutputPrefixType.LEGACY;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.mac.AesCmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

    class com.google.crypto.tink.mac.AesCmacProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.mac.AesCmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.AesCmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.AesCmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.AesCmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

