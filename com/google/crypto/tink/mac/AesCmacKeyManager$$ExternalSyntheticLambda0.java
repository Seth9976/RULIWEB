package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.internal.PrimitiveConstructor.PrimitiveConstructionFunction;
import com.google.crypto.tink.mac.internal.ChunkedAesCmacImpl;

public final class AesCmacKeyManager..ExternalSyntheticLambda0 implements PrimitiveConstructionFunction {
    @Override  // com.google.crypto.tink.internal.PrimitiveConstructor$PrimitiveConstructionFunction
    public final Object constructPrimitive(Key key0) {
        return new ChunkedAesCmacImpl(((AesCmacKey)key0));
    }
}

