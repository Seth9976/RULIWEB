package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;

public final class KeyTemplateProtoConverter {
    public static KeyTemplate fromByteArray(byte[] arr_b) throws GeneralSecurityException {
        try {
            return KeyTemplateProtoConverter.fromProto(com.google.crypto.tink.proto.KeyTemplate.parseFrom(arr_b, ExtensionRegistryLite.getEmptyRegistry()));
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("invalid key template", invalidProtocolBufferException0);
        }
    }

    // 去混淆评级： 低(20)
    public static KeyTemplate fromProto(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        return KeyTemplate.create("", keyTemplate0.getValue().toByteArray(), KeyTemplateProtoConverter.prefixFromProto(keyTemplate0.getOutputPrefixType()));
    }

    public static OutputPrefixType prefixFromProto(com.google.crypto.tink.proto.OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.internal.KeyTemplateProtoConverter.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
            case 1: {
                return OutputPrefixType.TINK;
            }
            case 2: {
                return OutputPrefixType.LEGACY;
            }
            case 3: {
                return OutputPrefixType.RAW;
            }
            case 4: {
                return OutputPrefixType.CRUNCHY;
            }
            default: {
                throw new GeneralSecurityException("Unknown output prefix type");
            }
        }
    }

    private static com.google.crypto.tink.proto.OutputPrefixType prefixToProto(OutputPrefixType keyTemplate$OutputPrefixType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.internal.KeyTemplateProtoConverter.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[keyTemplate$OutputPrefixType0.ordinal()]) {
            case 1: {
                return com.google.crypto.tink.proto.OutputPrefixType.TINK;
            }
            case 2: {
                return com.google.crypto.tink.proto.OutputPrefixType.LEGACY;
            }
            case 3: {
                return com.google.crypto.tink.proto.OutputPrefixType.RAW;
            }
            case 4: {
                return com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY;
            }
            default: {
                throw new GeneralSecurityException("Unknown output prefix type");
            }
        }
    }

    public static byte[] toByteArray(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        return KeyTemplateProtoConverter.toProto(keyTemplate0).toByteArray();
    }

    public static com.google.crypto.tink.proto.KeyTemplate toProto(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        return (com.google.crypto.tink.proto.KeyTemplate)com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate0.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate0.getValue())).setOutputPrefixType(KeyTemplateProtoConverter.prefixToProto(keyTemplate0.getOutputPrefixType())).build();
    }
}

