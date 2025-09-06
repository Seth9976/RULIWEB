package com.google.crypto.tink.subtle;

import com.google.crypto.tink.hybrid.subtle.AeadOrDaead;
import java.security.GeneralSecurityException;

public interface EciesAeadHkdfDemHelper {
    AeadOrDaead getAeadOrDaead(byte[] arg1) throws GeneralSecurityException;

    int getSymmetricKeySizeInBytes();
}

