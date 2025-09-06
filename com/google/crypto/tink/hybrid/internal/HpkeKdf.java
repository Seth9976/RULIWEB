package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
interface HpkeKdf {
    byte[] extractAndExpand(byte[] arg1, byte[] arg2, String arg3, byte[] arg4, String arg5, byte[] arg6, int arg7) throws GeneralSecurityException;

    byte[] getKdfId() throws GeneralSecurityException;

    byte[] labeledExpand(byte[] arg1, byte[] arg2, String arg3, byte[] arg4, int arg5) throws GeneralSecurityException;

    byte[] labeledExtract(byte[] arg1, byte[] arg2, String arg3, byte[] arg4) throws GeneralSecurityException;
}

