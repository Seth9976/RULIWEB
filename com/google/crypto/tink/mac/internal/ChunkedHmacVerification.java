package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

final class ChunkedHmacVerification implements ChunkedMacVerification {
    private final ChunkedHmacComputation hmacComputation;
    private final Bytes tag;

    ChunkedHmacVerification(HmacKey hmacKey0, byte[] arr_b) throws GeneralSecurityException {
        this.hmacComputation = new ChunkedHmacComputation(hmacKey0);
        this.tag = Bytes.copyFrom(arr_b);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
    public void update(ByteBuffer byteBuffer0) {
        this.hmacComputation.update(byteBuffer0);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
    public void verifyMac() throws GeneralSecurityException {
        Bytes bytes0 = Bytes.copyFrom(this.hmacComputation.computeMac());
        if(!this.tag.equals(bytes0)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}

