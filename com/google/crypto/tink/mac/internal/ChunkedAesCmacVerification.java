package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

final class ChunkedAesCmacVerification implements ChunkedMacVerification {
    private final ChunkedAesCmacComputation aesCmacComputation;
    private final Bytes tag;

    ChunkedAesCmacVerification(AesCmacKey aesCmacKey0, byte[] arr_b) throws GeneralSecurityException {
        this.aesCmacComputation = new ChunkedAesCmacComputation(aesCmacKey0);
        this.tag = Bytes.copyFrom(arr_b);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
    public void update(ByteBuffer byteBuffer0) throws GeneralSecurityException {
        this.aesCmacComputation.update(byteBuffer0);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
    public void verifyMac() throws GeneralSecurityException {
        Bytes bytes0 = Bytes.copyFrom(this.aesCmacComputation.computeMac());
        if(!this.tag.equals(bytes0)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}

