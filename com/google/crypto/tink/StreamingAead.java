package com.google.crypto.tink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

public interface StreamingAead {
    ReadableByteChannel newDecryptingChannel(ReadableByteChannel arg1, byte[] arg2) throws GeneralSecurityException, IOException;

    InputStream newDecryptingStream(InputStream arg1, byte[] arg2) throws GeneralSecurityException, IOException;

    WritableByteChannel newEncryptingChannel(WritableByteChannel arg1, byte[] arg2) throws GeneralSecurityException, IOException;

    OutputStream newEncryptingStream(OutputStream arg1, byte[] arg2) throws GeneralSecurityException, IOException;

    SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel arg1, byte[] arg2) throws GeneralSecurityException, IOException;
}

