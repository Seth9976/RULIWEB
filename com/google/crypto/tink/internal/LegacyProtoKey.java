package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class LegacyProtoKey extends Key {
    @Immutable
    static class LegacyProtoParametersNotForCreation extends Parameters {
        private final OutputPrefixType outputPrefixType;
        private final String typeUrl;

        private LegacyProtoParametersNotForCreation(String s, OutputPrefixType outputPrefixType0) {
            this.typeUrl = s;
            this.outputPrefixType = outputPrefixType0;
        }

        LegacyProtoParametersNotForCreation(String s, OutputPrefixType outputPrefixType0, com.google.crypto.tink.internal.LegacyProtoKey.1 legacyProtoKey$10) {
            this(s, outputPrefixType0);
        }

        @Override  // com.google.crypto.tink.Parameters
        public boolean hasIdRequirement() {
            return this.outputPrefixType != OutputPrefixType.RAW;
        }

        private static String outputPrefixToString(OutputPrefixType outputPrefixType0) {
            switch(com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
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

        @Override
        public String toString() {
            String s = LegacyProtoParametersNotForCreation.outputPrefixToString(this.outputPrefixType);
            return String.format("(typeUrl=%s, outputPrefixType=%s)", this.typeUrl, s);
        }
    }

    private final ProtoKeySerialization serialization;

    public LegacyProtoKey(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        LegacyProtoKey.throwIfMissingAccess(protoKeySerialization0, secretKeyAccess0);
        this.serialization = protoKeySerialization0;
    }

    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        if(!(key0 instanceof LegacyProtoKey)) {
            return false;
        }
        ProtoKeySerialization protoKeySerialization0 = ((LegacyProtoKey)key0).serialization;
        if(!protoKeySerialization0.getOutputPrefixType().equals(this.serialization.getOutputPrefixType())) {
            return false;
        }
        if(!protoKeySerialization0.getKeyMaterialType().equals(this.serialization.getKeyMaterialType())) {
            return false;
        }
        if(!protoKeySerialization0.getTypeUrl().equals(this.serialization.getTypeUrl())) {
            return false;
        }
        return Objects.equals(protoKeySerialization0.getIdRequirementOrNull(), this.serialization.getIdRequirementOrNull()) ? Bytes.equal(this.serialization.getValue().toByteArray(), protoKeySerialization0.getValue().toByteArray()) : false;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.serialization.getIdRequirementOrNull();
    }

    @Override  // com.google.crypto.tink.Key
    public Parameters getParameters() {
        return new LegacyProtoParametersNotForCreation(this.serialization.getTypeUrl(), this.serialization.getOutputPrefixType(), null);
    }

    public ProtoKeySerialization getSerialization(@Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        LegacyProtoKey.throwIfMissingAccess(this.serialization, secretKeyAccess0);
        return this.serialization;
    }

    private static void throwIfMissingAccess(ProtoKeySerialization protoKeySerialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType[protoKeySerialization0.getKeyMaterialType().ordinal()]) {
            case 1: 
            case 2: {
                SecretKeyAccess.requireAccess(secretKeyAccess0);
            }
        }
    }

    class com.google.crypto.tink.internal.LegacyProtoKey.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[KeyMaterialType.values().length];
            com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType = arr_v;
            try {
                arr_v[KeyMaterialType.SYMMETRIC.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType[KeyMaterialType.ASYMMETRIC_PRIVATE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[OutputPrefixType.values().length];
            com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v1;
            try {
                arr_v1[OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.LegacyProtoKey.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

