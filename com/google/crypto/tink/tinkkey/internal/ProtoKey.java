package com.google.crypto.tink.tinkkey.internal;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.tinkkey.TinkKey;
import com.google.errorprone.annotations.Immutable;

@Immutable
public final class ProtoKey implements TinkKey {
    private final boolean hasSecret;
    private final KeyData keyData;
    private final OutputPrefixType outputPrefixType;

    public ProtoKey(KeyData keyData0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        this.hasSecret = ProtoKey.isSecret(keyData0);
        this.keyData = keyData0;
        this.outputPrefixType = keyTemplate$OutputPrefixType0;
    }

    @Override  // com.google.crypto.tink.tinkkey.TinkKey
    public KeyTemplate getKeyTemplate() {
        throw new UnsupportedOperationException();
    }

    public OutputPrefixType getOutputPrefixType() {
        return this.outputPrefixType;
    }

    public KeyData getProtoKey() {
        return this.keyData;
    }

    @Override  // com.google.crypto.tink.tinkkey.TinkKey
    public boolean hasSecret() {
        return this.hasSecret;
    }

    private static boolean isSecret(KeyData keyData0) {
        return keyData0.getKeyMaterialType() == KeyMaterialType.UNKNOWN_KEYMATERIAL || keyData0.getKeyMaterialType() == KeyMaterialType.SYMMETRIC || keyData0.getKeyMaterialType() == KeyMaterialType.ASYMMETRIC_PRIVATE;
    }
}

