package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public interface StreamSegmentDecrypter {
    void decryptSegment(ByteBuffer arg1, int arg2, boolean arg3, ByteBuffer arg4) throws GeneralSecurityException;

    void init(ByteBuffer arg1, byte[] arg2) throws GeneralSecurityException;
}

