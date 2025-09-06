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
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
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

final class HmacProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HmacKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static HmacParameters $r8$lambda$D9KNC5jR9YqvL7EOqeF5Ognp3rc(ProtoParametersSerialization protoParametersSerialization0) [...]

    // 检测为 Lambda 实现
    public static HmacKey $r8$lambda$IoMtUrUtLG8MzwN1oB0SwnssxTQ(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$tC06darXYVAj1L6fPLrf2h7YmuE(HmacParameters hmacParameters0) [...]

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$tyWLoSUw9rNzlp_7oBscuA3ucvE(HmacKey hmacKey0, SecretKeyAccess secretKeyAccess0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.HmacKey");
        HmacProtoSerialization.TYPE_URL_BYTES = bytes0;
        HmacProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((HmacParameters hmacParameters0) -> HmacProtoSerialization.serializeParameters(hmacParameters0), HmacParameters.class, ProtoParametersSerialization.class);
        HmacProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> HmacProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        HmacProtoSerialization.KEY_SERIALIZER = KeySerializer.create((HmacKey hmacKey0, SecretKeyAccess secretKeyAccess0) -> HmacProtoSerialization.serializeKey(hmacKey0, secretKeyAccess0), HmacKey.class, ProtoKeySerialization.class);
        HmacProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> HmacProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static HmacParams getProtoParams(HmacParameters hmacParameters0) throws GeneralSecurityException {
        return (HmacParams)HmacParams.newBuilder().setTagSize(hmacParameters0.getCryptographicTagSizeBytes()).setHash(HmacProtoSerialization.toProtoHashType(hmacParameters0.getHashType())).build();
    }

    private static HmacKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                com.google.crypto.tink.proto.HmacKey hmacKey0 = com.google.crypto.tink.proto.HmacKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(hmacKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                HmacParameters hmacParameters0 = HmacParameters.builder().setKeySizeBytes(hmacKey0.getKeyValue().size()).setTagSizeBytes(hmacKey0.getParams().getTagSize()).setHashType(HmacProtoSerialization.toHashType(hmacKey0.getParams().getHash())).setVariant(HmacProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType())).build();
                return HmacKey.builder().setParameters(hmacParameters0).setKeyBytes(SecretBytes.copyFrom(hmacKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).setIdRequirement(protoKeySerialization0.getIdRequirementOrNull()).build();
            }
            catch(InvalidProtocolBufferException | IllegalArgumentException unused_ex) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
    }

    // 去混淆评级： 中等(80)
    private static HmacParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        HmacProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(HmacProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(HmacProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(HmacProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(HmacProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(HmacKey hmacKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = ((com.google.crypto.tink.proto.HmacKey)com.google.crypto.tink.proto.HmacKey.newBuilder().setParams(HmacProtoSerialization.getProtoParams(hmacKey0.getParameters())).setKeyValue(ByteString.copyFrom(hmacKey0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = HmacProtoSerialization.toProtoOutputPrefixType(hmacKey0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.HmacKey", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, hmacKey0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(HmacParameters hmacParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HmacKey").setValue(((HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(HmacProtoSerialization.getProtoParams(hmacParameters0)).setKeySize(hmacParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(HmacProtoSerialization.toProtoOutputPrefixType(hmacParameters0.getVariant())).build()));
    }

    private static HashType toHashType(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return HashType.SHA1;
            }
            case 2: {
                return HashType.SHA224;
            }
            case 3: {
                return HashType.SHA256;
            }
            case 4: {
                return HashType.SHA384;
            }
            case 5: {
                return HashType.SHA512;
            }
            default: {
                throw new GeneralSecurityException("Unable to parse HashType: " + hashType0.getNumber());
            }
        }
    }

    private static com.google.crypto.tink.proto.HashType toProtoHashType(HashType hmacParameters$HashType0) throws GeneralSecurityException {
        if(HashType.SHA1.equals(hmacParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA1;
        }
        if(HashType.SHA224.equals(hmacParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA224;
        }
        if(HashType.SHA256.equals(hmacParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA256;
        }
        if(HashType.SHA384.equals(hmacParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA384;
        }
        if(!HashType.SHA512.equals(hmacParameters$HashType0)) {
            throw new GeneralSecurityException("Unable to serialize HashType " + hmacParameters$HashType0);
        }
        return com.google.crypto.tink.proto.HashType.SHA512;
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant hmacParameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(hmacParameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(hmacParameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(Variant.NO_PREFIX.equals(hmacParameters$Variant0)) {
            return OutputPrefixType.RAW;
        }
        if(!Variant.LEGACY.equals(hmacParameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + hmacParameters$Variant0);
        }
        return OutputPrefixType.LEGACY;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

    class com.google.crypto.tink.mac.HmacProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[com.google.crypto.tink.proto.HashType.values().length];
            com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v1;
            try {
                arr_v1[com.google.crypto.tink.proto.HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA224.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA256.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA384.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA512.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

