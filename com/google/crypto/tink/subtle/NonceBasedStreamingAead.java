package com.google.crypto.tink.subtle;

import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

abstract class NonceBasedStreamingAead implements StreamingAead {
    public abstract int getCiphertextOffset();

    public abstract int getCiphertextOverhead();

    public abstract int getCiphertextSegmentSize();

    public abstract int getHeaderLength();

    public abstract int getPlaintextSegmentSize();

    @Override  // com.google.crypto.tink.StreamingAead
    public ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new StreamingAeadDecryptingChannel(this, readableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public InputStream newDecryptingStream(InputStream inputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new StreamingAeadDecryptingStream(this, inputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new StreamingAeadEncryptingChannel(this, writableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public OutputStream newEncryptingStream(OutputStream outputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new StreamingAeadEncryptingStream(this, outputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new StreamingAeadSeekableDecryptingChannel(this, seekableByteChannel0, arr_b);
    }

    public abstract StreamSegmentDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException;

    public abstract StreamSegmentEncrypter newStreamSegmentEncrypter(byte[] arg1) throws GeneralSecurityException;
}

