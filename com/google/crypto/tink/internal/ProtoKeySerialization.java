package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@Immutable
public final class ProtoKeySerialization implements Serialization {
    @Nullable
    private final Integer idRequirement;
    private final KeyMaterialType keyMaterialType;
    private final Bytes objectIdentifier;
    private final OutputPrefixType outputPrefixType;
    private final String typeUrl;
    private final ByteString value;

    private ProtoKeySerialization(String s, ByteString byteString0, KeyMaterialType keyData$KeyMaterialType0, OutputPrefixType outputPrefixType0, @Nullable Integer integer0) {
        this.typeUrl = s;
        this.objectIdentifier = Util.toBytesFromPrintableAscii(s);
        this.value = byteString0;
        this.keyMaterialType = keyData$KeyMaterialType0;
        this.outputPrefixType = outputPrefixType0;
        this.idRequirement = integer0;
    }

    public static ProtoKeySerialization create(String s, ByteString byteString0, KeyMaterialType keyData$KeyMaterialType0, OutputPrefixType outputPrefixType0, @Nullable Integer integer0) throws GeneralSecurityException {
        if(outputPrefixType0 == OutputPrefixType.RAW) {
            if(integer0 != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        }
        else if(integer0 == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new ProtoKeySerialization(s, byteString0, keyData$KeyMaterialType0, outputPrefixType0, integer0);
    }

    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    public KeyMaterialType getKeyMaterialType() {
        return this.keyMaterialType;
    }

    @Override  // com.google.crypto.tink.internal.Serialization
    public Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public OutputPrefixType getOutputPrefixType() {
        return this.outputPrefixType;
    }

    public String getTypeUrl() {
        return this.typeUrl;
    }

    public ByteString getValue() {
        return this.value;
    }
}

