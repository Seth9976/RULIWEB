package com.google.crypto.tink.prf;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams.Builder;
import com.google.crypto.tink.proto.HkdfPrfParams;
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

final class HkdfPrfProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$Cd7BK-0UX4it_C-F5LEzj5wO9ak(HkdfPrfKey hkdfPrfKey0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static HkdfPrfKey $r8$lambda$IpFaC26ZHcYc5OZmbpVL0zf3U9I(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$WLzwSoWAQkBnmSJqjqrLA0gJJjM(HkdfPrfParameters hkdfPrfParameters0) [...]

    // 检测为 Lambda 实现
    public static HkdfPrfParameters $r8$lambda$yumWNCDl_IPEwJGNKCgAQCdZimM(ProtoParametersSerialization protoParametersSerialization0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.HkdfPrfKey");
        HkdfPrfProtoSerialization.TYPE_URL_BYTES = bytes0;
        HkdfPrfProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((HkdfPrfParameters hkdfPrfParameters0) -> HkdfPrfProtoSerialization.serializeParameters(hkdfPrfParameters0), HkdfPrfParameters.class, ProtoParametersSerialization.class);
        HkdfPrfProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> HkdfPrfProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        HkdfPrfProtoSerialization.KEY_SERIALIZER = KeySerializer.create((HkdfPrfKey hkdfPrfKey0, SecretKeyAccess secretKeyAccess0) -> HkdfPrfProtoSerialization.serializeKey(hkdfPrfKey0, secretKeyAccess0), HkdfPrfKey.class, ProtoKeySerialization.class);
        HkdfPrfProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> HkdfPrfProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static HkdfPrfParams getProtoParams(HkdfPrfParameters hkdfPrfParameters0) throws GeneralSecurityException {
        Builder hkdfPrfParams$Builder0 = HkdfPrfParams.newBuilder().setHash(HkdfPrfProtoSerialization.toProtoHashType(hkdfPrfParameters0.getHashType()));
        if(hkdfPrfParameters0.getSalt() != null && hkdfPrfParameters0.getSalt().size() > 0) {
            hkdfPrfParams$Builder0.setSalt(ByteString.copyFrom(hkdfPrfParameters0.getSalt().toByteArray()));
        }
        return (HkdfPrfParams)hkdfPrfParams$Builder0.build();
    }

    private static HkdfPrfKey parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.HkdfPrfKey")) {
            try {
                com.google.crypto.tink.proto.HkdfPrfKey hkdfPrfKey0 = com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(hkdfPrfKey0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                if(protoKeySerialization0.getIdRequirementOrNull() != null) {
                    throw new GeneralSecurityException("ID requirement must be null.");
                }
                HkdfPrfParameters hkdfPrfParameters0 = HkdfPrfParameters.builder().setKeySizeBytes(hkdfPrfKey0.getKeyValue().size()).setHashType(HkdfPrfProtoSerialization.toHashType(hkdfPrfKey0.getParams().getHash())).setSalt(Bytes.copyFrom(hkdfPrfKey0.getParams().getSalt().toByteArray())).build();
                return HkdfPrfKey.builder().setParameters(hkdfPrfParameters0).setKeyBytes(SecretBytes.copyFrom(hkdfPrfKey0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0))).build();
            }
            catch(InvalidProtocolBufferException | IllegalArgumentException unused_ex) {
                throw new GeneralSecurityException("Parsing HkdfPrfKey failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseKey");
    }

    // 去混淆评级： 中等(80)
    private static HkdfPrfParameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        HkdfPrfProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(HkdfPrfProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(HkdfPrfProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(HkdfPrfProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(HkdfPrfProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(HkdfPrfKey hkdfPrfKey0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.HkdfPrfKey", ((com.google.crypto.tink.proto.HkdfPrfKey)com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setParams(HkdfPrfProtoSerialization.getProtoParams(hkdfPrfKey0.getParameters())).setKeyValue(ByteString.copyFrom(hkdfPrfKey0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString(), KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, null);
    }

    private static ProtoParametersSerialization serializeParameters(HkdfPrfParameters hkdfPrfParameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HkdfPrfKey").setValue(((HkdfPrfKeyFormat)HkdfPrfKeyFormat.newBuilder().setParams(HkdfPrfProtoSerialization.getProtoParams(hkdfPrfParameters0)).setKeySize(hkdfPrfParameters0.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build()));
    }

    private static HashType toHashType(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
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

    private static com.google.crypto.tink.proto.HashType toProtoHashType(HashType hkdfPrfParameters$HashType0) throws GeneralSecurityException {
        if(HashType.SHA1.equals(hkdfPrfParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA1;
        }
        if(HashType.SHA224.equals(hkdfPrfParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA224;
        }
        if(HashType.SHA256.equals(hkdfPrfParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA256;
        }
        if(HashType.SHA384.equals(hkdfPrfParameters$HashType0)) {
            return com.google.crypto.tink.proto.HashType.SHA384;
        }
        if(!HashType.SHA512.equals(hkdfPrfParameters$HashType0)) {
            throw new GeneralSecurityException("Unable to serialize HashType " + hkdfPrfParameters$HashType0);
        }
        return com.google.crypto.tink.proto.HashType.SHA512;
    }

    class com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] arr_v = new int[com.google.crypto.tink.proto.HashType.values().length];
            com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v;
            try {
                arr_v[com.google.crypto.tink.proto.HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA224.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA256.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA384.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA512.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

