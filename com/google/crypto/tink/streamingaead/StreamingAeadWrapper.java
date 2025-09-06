package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

public class StreamingAeadWrapper implements PrimitiveWrapper {
    private static final StreamingAeadWrapper WRAPPER;

    static {
        StreamingAeadWrapper.WRAPPER = new StreamingAeadWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return StreamingAead.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return StreamingAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(StreamingAeadWrapper.WRAPPER);
    }

    public StreamingAead wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return new StreamingAeadHelper(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

