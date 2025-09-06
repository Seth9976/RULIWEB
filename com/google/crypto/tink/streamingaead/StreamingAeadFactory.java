package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

@Deprecated
public final class StreamingAeadFactory {
    public static StreamingAead getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        StreamingAeadWrapper.register();
        return (StreamingAead)keysetHandle0.getPrimitive(StreamingAead.class);
    }
}

