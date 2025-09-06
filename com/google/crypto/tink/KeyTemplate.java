package com.google.crypto.tink;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.errorprone.annotations.Immutable;

@Immutable
public final class KeyTemplate {
    public static enum OutputPrefixType {
        TINK,
        LEGACY,
        RAW,
        CRUNCHY;

    }

    private final com.google.crypto.tink.proto.KeyTemplate kt;

    private KeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) {
        this.kt = keyTemplate0;
    }

    public static KeyTemplate create(String s, byte[] arr_b, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyTemplate(((com.google.crypto.tink.proto.KeyTemplate)com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(s).setValue(ByteString.copyFrom(arr_b)).setOutputPrefixType(KeyTemplate.toProto(keyTemplate$OutputPrefixType0)).build()));
    }

    static OutputPrefixType fromProto(com.google.crypto.tink.proto.OutputPrefixType outputPrefixType0) {
        switch(com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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
                throw new IllegalArgumentException("Unknown output prefix type");
            }
        }
    }

    public OutputPrefixType getOutputPrefixType() {
        return KeyTemplate.fromProto(this.kt.getOutputPrefixType());
    }

    com.google.crypto.tink.proto.KeyTemplate getProto() {
        return this.kt;
    }

    // 去混淆评级： 低(20)
    public String getTypeUrl() {
        return "";
    }

    public byte[] getValue() {
        return this.kt.getValue().toByteArray();
    }

    static com.google.crypto.tink.proto.OutputPrefixType toProto(OutputPrefixType keyTemplate$OutputPrefixType0) {
        switch(com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[keyTemplate$OutputPrefixType0.ordinal()]) {
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
                throw new IllegalArgumentException("Unknown output prefix type");
            }
        }
    }

    class com.google.crypto.tink.KeyTemplate.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[com.google.crypto.tink.proto.OutputPrefixType.values().length];
            com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v1;
            try {
                arr_v1[com.google.crypto.tink.proto.OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.LEGACY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.RAW.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeyTemplate.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

