package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public interface StreamSegmentEncrypter {
    void encryptSegment(ByteBuffer arg1, ByteBuffer arg2, boolean arg3, ByteBuffer arg4) throws GeneralSecurityException;

    void encryptSegment(ByteBuffer arg1, boolean arg2, ByteBuffer arg3) throws GeneralSecurityException;

    ByteBuffer getHeader();
}

