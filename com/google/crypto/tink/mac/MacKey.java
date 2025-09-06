package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.Bytes;

public abstract class MacKey extends Key {
    public abstract Bytes getOutputPrefix();

    @Override  // com.google.crypto.tink.Key
    public Parameters getParameters() {
        return this.getParameters();
    }

    public abstract MacParameters getParameters();
}

