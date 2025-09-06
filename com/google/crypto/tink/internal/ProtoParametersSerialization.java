package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;

@Immutable
public final class ProtoParametersSerialization implements Serialization {
    private final KeyTemplate keyTemplate;
    private final Bytes objectIdentifier;

    private ProtoParametersSerialization(KeyTemplate keyTemplate0) {
        this.keyTemplate = keyTemplate0;
        this.objectIdentifier = Util.toBytesFromPrintableAscii("");
    }

    public static ProtoParametersSerialization create(KeyTemplate keyTemplate0) {
        return new ProtoParametersSerialization(keyTemplate0);
    }

    public static ProtoParametersSerialization create(String s, OutputPrefixType outputPrefixType0, MessageLite messageLite0) {
        return ProtoParametersSerialization.create(((KeyTemplate)KeyTemplate.newBuilder().setTypeUrl(s).setOutputPrefixType(outputPrefixType0).setValue(messageLite0.toByteString()).build()));
    }

    public KeyTemplate getKeyTemplate() {
        return this.keyTemplate;
    }

    @Override  // com.google.crypto.tink.internal.Serialization
    public Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }
}

