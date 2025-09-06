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
import com.google.crypto.tink.proto.AesGcmSivKeyFormat;
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

final class AesGcmSivProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$ZVix_XPd6SkCZney6dJhi30uqRo(AesGcmSivKey aesGcmSivKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static AesGcmSivParameters $r8$lambda$_CjeUohHZg37GVQOl-cq0m20wqE(ProtoParametersSerialization protoParametersSerialization0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$o3qJk7Calra0FRqSOcIbp0808uc(AesGcmSivParameters aesGcmSivParameters0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        AesGcmSivProtoSerialization.TYPE_URL_BYTES = bytes0;
        AesGcmSivProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((AesGcmSivParameters aesGcmSivParameters0) -> AesGcmSivProtoSerialization.serializeParameters(aesGcmSivParameters0), AesGcmSivParameters.class, ProtoParametersSerialization.class);
        AesGcmSivProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> AesGcmSivProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        AesGcmSivProtoSerialization.KEY_SERIALIZER = KeySerializer.create((AesGcmSivKey aesGcmSivKey0, SecretKeyAccess secretKeyAccess0) -> AesGcmSivProtoSerialization.serializeKey(aesGcmSivKey0, secretKeyAccess0), AesGcmSivKey.class, ProtoKeySerialization.class);
        AesGcmSivProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> {
            if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
                try {
                    com.google.crypto.tink.proto.AesGcmSivKey aesGcmSivKey0 = com.google.crypto.tink.proto.AesGcmSivKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                    if(aesGcmSivKey0.getVersion() != 0) {
                        throw new GeneralSecurityException("Only version 0 keys are accepted");
                    }
                    AesGcmSivParameters aesGcmSivParameters0 = AesGcmSivParameters.builder().setKeySizeBytes(aesGcmSivKey0.getKeyValue().size()).setVariant(AesGcmSivProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                    return AesGcmSivKey.builder().setParameters(aesGcmSivParameters0).setKeyBytes(SecretBytes.copyFrom(aesGcmSivKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
                }
                catch(InvalidProtocolBufferException unused_ex) {
                    throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
                }
            }
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivParameters.parseParameters");
        }, bytes0, ProtoKeySerialization.class);
    }

    // 检测为 Lambda 实现
    private static AesGcmSivKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException [...]

    // 去混淆评级： 中等(80)
    private static AesGcmSivParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivParameters.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        AesGcmSivProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(AesGcmSivProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(AesGcmSivProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(AesGcmSivProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(AesGcmSivProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(AesGcmSivKey aesGcmSivKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = ((com.google.crypto.tink.proto.AesGcmSivKey)com.google.crypto.tink.proto.AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(aesGcmSivKey0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = AesGcmSivProtoSerialization.toProtoOutputPrefixType(aesGcmSivKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.AesGcmSivKey", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, aesGcmSivKey0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(AesGcmSivParameters aesGcmSivParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesGcmSivKey").setValue(((AesGcmSivKeyFormat)AesGcmSivKeyFormat.newBuilder().setKeySize(aesGcmSivParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(AesGcmSivProtoSerialization.toProtoOutputPrefixType(aesGcmSivParameters0.getVariant())).build()));
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant aesGcmSivParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(aesGcmSivParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(aesGcmSivParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(!Variant.NO_PREFIX.equals(aesGcmSivParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + aesGcmSivParameters$Variant0);
        }
        return OutputPrefixType.RAW;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

    class com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.AesGcmSivProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

