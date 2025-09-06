package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;

public abstract class PrfKey extends Key {
    @Override  // com.google.crypto.tink.Key
    public Parameters getParameters() {
        return this.getParameters();
    }

    public abstract PrfParameters getParameters();
}

