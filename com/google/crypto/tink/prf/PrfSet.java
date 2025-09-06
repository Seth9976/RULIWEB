package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Map;

@Immutable
public abstract class PrfSet {
    public byte[] computePrimary(byte[] arr_b, int v) throws GeneralSecurityException {
        return ((Prf)this.getPrfs().get(this.getPrimaryId())).compute(arr_b, v);
    }

    public abstract Map getPrfs() throws GeneralSecurityException;

    public abstract int getPrimaryId();
}

