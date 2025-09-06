package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
public class PrfMac implements Mac {
    static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private final int tagSize;
    private final Prf wrappedPrf;

    public PrfMac(Prf prf0, int v) throws GeneralSecurityException {
        this.wrappedPrf = prf0;
        this.tagSize = v;
        if(v < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        prf0.compute(new byte[0], v);
    }

    @Override  // com.google.crypto.tink.Mac
    public byte[] computeMac(byte[] arr_b) throws GeneralSecurityException {
        return this.wrappedPrf.compute(arr_b, this.tagSize);
    }

    @Override  // com.google.crypto.tink.Mac
    public void verifyMac(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(!Bytes.equal(this.computeMac(arr_b1), arr_b)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}

