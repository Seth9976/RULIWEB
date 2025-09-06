package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.mac.ChunkedMac;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class ChunkedHmacImpl implements ChunkedMac {
    private static final AlgorithmFipsCompatibility FIPS;
    private final HmacKey key;

    static {
        ChunkedHmacImpl.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public ChunkedHmacImpl(HmacKey hmacKey0) throws GeneralSecurityException {
        if(!ChunkedHmacImpl.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.key = hmacKey0;
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMac
    public ChunkedMacComputation createComputation() throws GeneralSecurityException {
        return new ChunkedHmacComputation(this.key);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMac
    public ChunkedMacVerification createVerification(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length < this.key.getOutputPrefix().size()) {
            throw new GeneralSecurityException("Tag too short");
        }
        Bytes bytes0 = Bytes.copyFrom(arr_b, 0, this.key.getOutputPrefix().size());
        if(!this.key.getOutputPrefix().equals(bytes0)) {
            throw new GeneralSecurityException("Wrong tag prefix");
        }
        return new ChunkedHmacVerification(this.key, arr_b);
    }
}

