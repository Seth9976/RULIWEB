package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

@Immutable
public final class LegacyProtoParameters extends Parameters {
    private final ProtoParametersSerialization serialization;

    public LegacyProtoParameters(ProtoParametersSerialization protoParametersSerialization0) {
        this.serialization = protoParametersSerialization0;
    }

    // 去混淆评级： 低(22)
    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof LegacyProtoParameters)) {
            return false;
        }
        ProtoParametersSerialization protoParametersSerialization0 = ((LegacyProtoParameters)object0).serialization;
        return this.serialization.getKeyTemplate().getOutputPrefixType().equals(protoParametersSerialization0.getKeyTemplate().getOutputPrefixType()) && this.serialization.getKeyTemplate().getValue().equals(protoParametersSerialization0.getKeyTemplate().getValue());
    }

    public ProtoParametersSerialization getSerialization() {
        return this.serialization;
    }

    @Override  // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW;
    }

    @Override
    public int hashCode() {
        Bytes bytes0 = this.serialization.getObjectIdentifier();
        return Objects.hash(new Object[]{this.serialization.getKeyTemplate(), bytes0});
    }

    private static String outputPrefixToString(OutputPrefixType outputPrefixType0) {
        switch(com.google.crypto.tink.internal.LegacyProtoParameters.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
            case 1: {
                return "TINK";
            }
            case 2: {
                return "LEGACY";
            }
            case 3: {
                return "RAW";
            }
            case 4: {
                return "CRUNCHY";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return String.format("(typeUrl=%s, outputPrefixType=%s)", "", LegacyProtoParameters.outputPrefixToString(this.serialization.getKeyTemplate().getOutputPrefixType()));
    }

    class com.google.crypto.tink.internal.LegacyProtoParameters.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.internal.LegacyProtoParameters.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoParameters.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoParameters.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoParameters.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

