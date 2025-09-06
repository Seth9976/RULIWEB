package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@Immutable
public class PrfImpl implements Prf {
    private final StreamingPrf prfStreamer;

    private PrfImpl(StreamingPrf streamingPrf0) {
        this.prfStreamer = streamingPrf0;
    }

    @Override  // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] arr_b, int v) throws GeneralSecurityException {
        if(arr_b == null) {
            throw new GeneralSecurityException("Invalid input provided.");
        }
        if(v <= 0) {
            throw new GeneralSecurityException("Invalid outputLength specified.");
        }
        return PrfImpl.readBytesFromStream(this.prfStreamer.computePrf(arr_b), v);
    }

    private static byte[] readBytesFromStream(InputStream inputStream0, int v) throws GeneralSecurityException {
        try {
            byte[] arr_b = new byte[v];
            for(int v1 = 0; v1 < v; v1 += v2) {
                int v2 = inputStream0.read(arr_b, v1, v - v1);
                if(v2 <= 0) {
                    throw new GeneralSecurityException("Provided StreamingPrf terminated before providing requested number of bytes.");
                }
            }
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new GeneralSecurityException(iOException0);
        }
    }

    public static PrfImpl wrap(StreamingPrf streamingPrf0) {
        return new PrfImpl(streamingPrf0);
    }
}

