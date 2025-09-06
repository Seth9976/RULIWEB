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
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

final class XChaCha20Poly1305ProtoSerialization {
    private static final KeyParser KEY_PARSER = null;
    private static final KeySerializer KEY_SERIALIZER = null;
    private static final ParametersParser PARAMETERS_PARSER = null;
    private static final ParametersSerializer PARAMETERS_SERIALIZER = null;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    private static final Bytes TYPE_URL_BYTES;

    // 检测为 Lambda 实现
    public static XChaCha20Poly1305Key $r8$lambda$CshRoNazMt5NRtdRkSWAYsXM9uI(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static ProtoParametersSerialization $r8$lambda$MkgHpbYeGHUDeWUdbQUBFvvRGho(XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0) [...]

    // 检测为 Lambda 实现
    public static ProtoKeySerialization $r8$lambda$bEkdnXQxuYKf9bXoZfJjPvsAnmw(XChaCha20Poly1305Key xChaCha20Poly1305Key0, SecretKeyAccess secretKeyAccess0) [...]

    // 检测为 Lambda 实现
    public static XChaCha20Poly1305Parameters $r8$lambda$rNXME44dfY3cISY2b0jZ-pUuaLo(ProtoParametersSerialization protoParametersSerialization0) [...]

    static {
        Bytes bytes0 = Util.toBytesFromPrintableAscii("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        XChaCha20Poly1305ProtoSerialization.TYPE_URL_BYTES = bytes0;
        XChaCha20Poly1305ProtoSerialization.PARAMETERS_SERIALIZER = ParametersSerializer.create((XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0) -> XChaCha20Poly1305ProtoSerialization.serializeParameters(xChaCha20Poly1305Parameters0), XChaCha20Poly1305Parameters.class, ProtoParametersSerialization.class);
        XChaCha20Poly1305ProtoSerialization.PARAMETERS_PARSER = ParametersParser.create((ProtoParametersSerialization protoParametersSerialization0) -> XChaCha20Poly1305ProtoSerialization.parseParameters(protoParametersSerialization0), bytes0, ProtoParametersSerialization.class);
        XChaCha20Poly1305ProtoSerialization.KEY_SERIALIZER = KeySerializer.create((XChaCha20Poly1305Key xChaCha20Poly1305Key0, SecretKeyAccess secretKeyAccess0) -> XChaCha20Poly1305ProtoSerialization.serializeKey(xChaCha20Poly1305Key0, secretKeyAccess0), XChaCha20Poly1305Key.class, ProtoKeySerialization.class);
        XChaCha20Poly1305ProtoSerialization.KEY_PARSER = KeyParser.create((ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) -> XChaCha20Poly1305ProtoSerialization.parseKey(protoKeySerialization0, secretKeyAccess0), bytes0, ProtoKeySerialization.class);
    }

    private static XChaCha20Poly1305Key parseKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(protoKeySerialization0.getTypeUrl().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                com.google.crypto.tink.proto.XChaCha20Poly1305Key xChaCha20Poly1305Key0 = com.google.crypto.tink.proto.XChaCha20Poly1305Key.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if(xChaCha20Poly1305Key0.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                return XChaCha20Poly1305Key.create(XChaCha20Poly1305ProtoSerialization.toVariant(protoKeySerialization0.getOutputPrefixType()), SecretBytes.copyFrom(xChaCha20Poly1305Key0.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess0)), protoKeySerialization0.getIdRequirementOrNull());
            }
            catch(InvalidProtocolBufferException unused_ex) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
            }
        }
        throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305Parameters.parseParameters");
    }

    // 去混淆评级： 中等(80)
    private static XChaCha20Poly1305Parameters parseParameters(ProtoParametersSerialization protoParametersSerialization0) throws GeneralSecurityException {
        throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305Parameters.parseParameters: ");
    }

    public static void register() throws GeneralSecurityException {
        XChaCha20Poly1305ProtoSerialization.register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry0) throws GeneralSecurityException {
        mutableSerializationRegistry0.registerParametersSerializer(XChaCha20Poly1305ProtoSerialization.PARAMETERS_SERIALIZER);
        mutableSerializationRegistry0.registerParametersParser(XChaCha20Poly1305ProtoSerialization.PARAMETERS_PARSER);
        mutableSerializationRegistry0.registerKeySerializer(XChaCha20Poly1305ProtoSerialization.KEY_SERIALIZER);
        mutableSerializationRegistry0.registerKeyParser(XChaCha20Poly1305ProtoSerialization.KEY_PARSER);
    }

    private static ProtoKeySerialization serializeKey(XChaCha20Poly1305Key xChaCha20Poly1305Key0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ByteString byteString0 = ((com.google.crypto.tink.proto.XChaCha20Poly1305Key)com.google.crypto.tink.proto.XChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(xChaCha20Poly1305Key0.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess0)))).build()).toByteString();
        OutputPrefixType outputPrefixType0 = XChaCha20Poly1305ProtoSerialization.toProtoOutputPrefixType(xChaCha20Poly1305Key0.getParameters().getVariant());
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", byteString0, KeyMaterialType.SYMMETRIC, outputPrefixType0, xChaCha20Poly1305Key0.getIdRequirementOrNull());
    }

    private static ProtoParametersSerialization serializeParameters(XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0) throws GeneralSecurityException {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key").setValue(XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteString()).setOutputPrefixType(XChaCha20Poly1305ProtoSerialization.toProtoOutputPrefixType(xChaCha20Poly1305Parameters0.getVariant())).build()));
    }

    private static OutputPrefixType toProtoOutputPrefixType(Variant xChaCha20Poly1305Parameters$Variant0) throws GeneralSecurityException {
        if(Variant.TINK.equals(xChaCha20Poly1305Parameters$Variant0)) {
            return OutputPrefixType.TINK;
        }
        if(Variant.CRUNCHY.equals(xChaCha20Poly1305Parameters$Variant0)) {
            return OutputPrefixType.CRUNCHY;
        }
        if(!Variant.NO_PREFIX.equals(xChaCha20Poly1305Parameters$Variant0)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + xChaCha20Poly1305Parameters$Variant0);
        }
        return OutputPrefixType.RAW;
    }

    private static Variant toVariant(OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

    class com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

