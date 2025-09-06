package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingStream extends FilterInputStream implements AutoCloseable {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean decryptionErrorOccured;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private final int headerLength;
    private boolean headerRead;
    private final ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead0, InputStream inputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        super(inputStream0);
        this.decrypter = nonceBasedStreamingAead0.newStreamSegmentDecrypter();
        this.headerLength = nonceBasedStreamingAead0.getHeaderLength();
        this.aad = Arrays.copyOf(arr_b, arr_b.length);
        int v = nonceBasedStreamingAead0.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = v;
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(v + 1);
        this.ciphertextSegment = byteBuffer0;
        byteBuffer0.limit(0);
        this.firstCiphertextSegmentSize = v - nonceBasedStreamingAead0.getCiphertextOffset();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(nonceBasedStreamingAead0.getPlaintextSegmentSize() + 16);
        this.plaintextSegment = byteBuffer1;
        byteBuffer1.limit(0);
        this.headerRead = false;
        this.endOfCiphertext = false;
        this.endOfPlaintext = false;
        this.segmentNr = 0;
        this.decryptionErrorOccured = false;
    }

    @Override
    public int available() {
        synchronized(this) {
        }
        return this.plaintextSegment.remaining();
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            super.close();
        }
    }

    private void loadSegment() throws IOException {
        int v1;
        while(!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int v = this.in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if(v > 0) {
                this.ciphertextSegment.position(this.ciphertextSegment.position() + v);
            }
            else {
                switch(v) {
                    case -1: {
                        this.endOfCiphertext = true;
                        break;
                    }
                    case 0: {
                        throw new IOException("Could not read bytes from the ciphertext stream");
                    }
                }
            }
        }
        if(this.endOfCiphertext) {
            v1 = 0;
        }
        else {
            v1 = this.ciphertextSegment.get(this.ciphertextSegment.position() - 1);
            this.ciphertextSegment.position(this.ciphertextSegment.position() - 1);
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
        }
        catch(GeneralSecurityException generalSecurityException0) {
            this.setDecryptionErrorOccured();
            throw new IOException(generalSecurityException0.getMessage() + "\n" + this.toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, generalSecurityException0);
        }
        ++this.segmentNr;
        this.plaintextSegment.flip();
        this.ciphertextSegment.clear();
        if(!this.endOfCiphertext) {
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
            this.ciphertextSegment.put(((byte)v1));
        }
    }

    @Override
    public void mark(int v) {
        synchronized(this) {
        }
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public int read() throws IOException {
        byte[] arr_b = new byte[1];
        int v = this.read(arr_b, 0, 1);
        if(v == 1) {
            return arr_b[0] & 0xFF;
        }
        if(v != -1) {
            throw new IOException("Reading failed");
        }
        return -1;
    }

    @Override
    public int read(byte[] arr_b) throws IOException {
        return this.read(arr_b, 0, arr_b.length);
    }

    @Override
    public int read(byte[] arr_b, int v, int v1) throws IOException {
        synchronized(this) {
            if(!this.decryptionErrorOccured) {
                if(!this.headerRead) {
                    this.readHeader();
                    this.ciphertextSegment.clear();
                    this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
                }
                if(this.endOfPlaintext) {
                    return -1;
                }
                int v3;
                for(v3 = 0; v3 < v1; v3 += v4) {
                    if(this.plaintextSegment.remaining() == 0) {
                        if(this.endOfCiphertext) {
                            this.endOfPlaintext = true;
                            break;
                        }
                        this.loadSegment();
                    }
                    int v4 = Math.min(this.plaintextSegment.remaining(), v1 - v3);
                    this.plaintextSegment.get(arr_b, v3 + v, v4);
                }
                return v3 == 0 && this.endOfPlaintext ? -1 : v3;
            }
        }
        throw new IOException("Decryption failed.");
    }

    private void readHeader() throws IOException {
        if(!this.headerRead) {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(this.headerLength);
            while(byteBuffer0.remaining() > 0) {
                int v = this.in.read(byteBuffer0.array(), byteBuffer0.position(), byteBuffer0.remaining());
                switch(v) {
                    case -1: {
                        this.setDecryptionErrorOccured();
                        throw new IOException("Ciphertext is too short");
                    }
                    case 0: {
                        throw new IOException("Could not read bytes from the ciphertext stream");
                    }
                }
                byteBuffer0.position(byteBuffer0.position() + v);
            }
            byteBuffer0.flip();
            try {
                this.decrypter.init(byteBuffer0, this.aad);
                this.headerRead = true;
                return;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new IOException(generalSecurityException0);
            }
        }
        this.setDecryptionErrorOccured();
        throw new IOException("Decryption failed.");
    }

    private void setDecryptionErrorOccured() {
        this.decryptionErrorOccured = true;
        this.plaintextSegment.limit(0);
    }

    @Override
    public long skip(long v) throws IOException {
        long v1 = (long)this.ciphertextSegmentSize;
        if(v <= 0L) {
            return 0L;
        }
        int v2 = (int)Math.min(v1, v);
        byte[] arr_b = new byte[v2];
        long v3;
        for(v3 = v; v3 > 0L; v3 -= (long)v4) {
            int v4 = this.read(arr_b, 0, ((int)Math.min(v2, v3)));
            if(v4 <= 0) {
                break;
            }
        }
        return v - v3;
    }

    @Override
    public String toString() {
        synchronized(this) {
            return "StreamingAeadDecryptingStream\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndecryptionErrorOccured:" + this.decryptionErrorOccured + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
        }
    }
}

