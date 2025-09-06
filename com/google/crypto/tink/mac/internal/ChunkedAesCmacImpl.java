package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.ChunkedMac;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class ChunkedAesCmacImpl implements ChunkedMac {
    private static final AlgorithmFipsCompatibility FIPS;
    private final AesCmacKey key;

    static {
        ChunkedAesCmacImpl.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public ChunkedAesCmacImpl(AesCmacKey aesCmacKey0) throws GeneralSecurityException {
        if(!ChunkedAesCmacImpl.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        this.key = aesCmacKey0;
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMac
    public ChunkedMacComputation createComputation() throws GeneralSecurityException {
        return new ChunkedAesCmacComputation(this.key);
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
        return new ChunkedAesCmacVerification(this.key, arr_b);
    }
}

