package com.google.crypto.tink.daead;

import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class DeterministicAeadKeyTemplates {
    public static final KeyTemplate AES256_SIV;

    static {
        DeterministicAeadKeyTemplates.AES256_SIV = DeterministicAeadKeyTemplates.createAesSivKeyTemplate(0x40);
    }

    public static KeyTemplate createAesSivKeyTemplate(int v) {
        AesSivKeyFormat aesSivKeyFormat0 = (AesSivKeyFormat)AesSivKeyFormat.newBuilder().setKeySize(v).build();
        return (KeyTemplate)KeyTemplate.newBuilder().setValue(aesSivKeyFormat0.toByteString()).setTypeUrl("type.googleapis.com/google.crypto.tink.AesSivKey").setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}

