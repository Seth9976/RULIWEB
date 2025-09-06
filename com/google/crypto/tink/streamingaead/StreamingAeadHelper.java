package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

final class StreamingAeadHelper implements StreamingAead {
    PrimitiveSet primitives;

    public StreamingAeadHelper(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        if(primitiveSet0.getPrimary() == null) {
            throw new GeneralSecurityException("Missing primary primitive.");
        }
        this.primitives = primitiveSet0;
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new ReadableByteChannelDecrypter(this.primitives, readableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public InputStream newDecryptingStream(InputStream inputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new InputStreamDecrypter(this.primitives, inputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return ((StreamingAead)this.primitives.getPrimary().getPrimitive()).newEncryptingChannel(writableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public OutputStream newEncryptingStream(OutputStream outputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return ((StreamingAead)this.primitives.getPrimary().getPrimitive()).newEncryptingStream(outputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.StreamingAead
    public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return new SeekableByteChannelDecrypter(this.primitives, seekableByteChannel0, arr_b);
    }
}

