package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

final class InputStreamDecrypter extends InputStream implements AutoCloseable {
    byte[] associatedData;
    boolean attemptedMatching;
    InputStream ciphertextStream;
    InputStream matchingStream;
    PrimitiveSet primitives;

    public InputStreamDecrypter(PrimitiveSet primitiveSet0, InputStream inputStream0, byte[] arr_b) {
        this.attemptedMatching = false;
        this.matchingStream = null;
        this.primitives = primitiveSet0;
        this.ciphertextStream = inputStream0.markSupported() ? inputStream0 : new BufferedInputStream(inputStream0);
        this.ciphertextStream.mark(0x7FFFFFFF);
        this.associatedData = (byte[])arr_b.clone();
    }

    @Override
    public int available() throws IOException {
        synchronized(this) {
            InputStream inputStream0 = this.matchingStream;
            return inputStream0 == null ? 0 : inputStream0.available();
        }
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            this.ciphertextStream.close();
        }
    }

    private void disableRewinding() throws IOException {
        this.ciphertextStream.mark(0);
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read() throws IOException {
        synchronized(this) {
            byte[] arr_b = new byte[1];
            return this.read(arr_b) == 1 ? arr_b[0] : -1;
        }
    }

    @Override
    public int read(byte[] arr_b) throws IOException {
        synchronized(this) {
            return this.read(arr_b, 0, arr_b.length);
        }
    }

    @Override
    public int read(byte[] arr_b, int v, int v1) throws IOException {
        synchronized(this) {
            if(v1 == 0) {
                return 0;
            }
            InputStream inputStream0 = this.matchingStream;
            if(inputStream0 != null) {
                return inputStream0.read(arr_b, v, v1);
            }
            if(!this.attemptedMatching) {
                this.attemptedMatching = true;
                for(Object object0: this.primitives.getRawPrimitives()) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    try {
                        InputStream inputStream1 = ((StreamingAead)primitiveSet$Entry0.getPrimitive()).newDecryptingStream(this.ciphertextStream, this.associatedData);
                        int v4 = inputStream1.read(arr_b, v, v1);
                        if(v4 != 0) {
                            this.matchingStream = inputStream1;
                            this.disableRewinding();
                            return v4;
                        }
                    }
                    catch(IOException unused_ex) {
                        this.rewind();
                        continue;
                    }
                    catch(GeneralSecurityException unused_ex) {
                    }
                    this.rewind();
                }
                throw new IOException("No matching key found for the ciphertext in the stream.");
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }

    private void rewind() throws IOException {
        this.ciphertextStream.reset();
    }
}

