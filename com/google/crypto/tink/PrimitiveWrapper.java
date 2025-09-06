package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface PrimitiveWrapper {
    Class getInputPrimitiveClass();

    Class getPrimitiveClass();

    Object wrap(PrimitiveSet arg1) throws GeneralSecurityException;
}

