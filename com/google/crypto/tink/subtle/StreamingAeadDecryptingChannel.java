package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingChannel implements AutoCloseable, ReadableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] associatedData;
    private ReadableByteChannel ciphertextChannel;
    private ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean definedState;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private ByteBuffer header;
    private boolean headerRead;
    private ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead0, ReadableByteChannel readableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        this.decrypter = nonceBasedStreamingAead0.newStreamSegmentDecrypter();
        this.ciphertextChannel = readableByteChannel0;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead0.getHeaderLength());
        this.associatedData = Arrays.copyOf(arr_b, arr_b.length);
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
        this.definedState = true;
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            this.ciphertextChannel.close();
        }
    }

    @Override
    public boolean isOpen() {
        synchronized(this) {
            return this.ciphertextChannel.isOpen();
        }
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(this.definedState) {
                if(!this.headerRead) {
                    if(!this.tryReadHeader()) {
                        return 0;
                    }
                    this.ciphertextSegment.clear();
                    this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
                }
                if(this.endOfPlaintext) {
                    return -1;
                }
                int v1 = byteBuffer0.position();
                while(byteBuffer0.remaining() > 0) {
                    if(this.plaintextSegment.remaining() == 0) {
                        if(this.endOfCiphertext) {
                            this.endOfPlaintext = true;
                            break;
                        }
                        if(this.tryLoadSegment()) {
                            goto label_21;
                        }
                        break;
                    }
                label_21:
                    if(this.plaintextSegment.remaining() <= byteBuffer0.remaining()) {
                        byteBuffer0.put(this.plaintextSegment);
                    }
                    else {
                        int v2 = byteBuffer0.remaining();
                        ByteBuffer byteBuffer1 = this.plaintextSegment.duplicate();
                        byteBuffer1.limit(byteBuffer1.position() + v2);
                        byteBuffer0.put(byteBuffer1);
                        this.plaintextSegment.position(this.plaintextSegment.position() + v2);
                    }
                }
                int v3 = byteBuffer0.position() - v1;
                return v3 == 0 && this.endOfPlaintext ? -1 : v3;
            }
        }
        throw new IOException("This StreamingAeadDecryptingChannel is in an undefined state");
    }

    private void readSomeCiphertext(ByteBuffer byteBuffer0) throws IOException {
        do {
            int v = this.ciphertextChannel.read(byteBuffer0);
        }
        while(v > 0 && byteBuffer0.remaining() > 0);
        if(v == -1) {
            this.endOfCiphertext = true;
        }
    }

    private void setUndefinedState() {
        this.definedState = false;
        this.plaintextSegment.limit(0);
    }

    @Override
    public String toString() {
        synchronized(this) {
            return "StreamingAeadDecryptingChannel\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndefinedState:" + this.definedState + "\nHeader position:" + this.header.position() + " limit:" + this.header.position() + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
        }
    }

    private boolean tryLoadSegment() throws IOException {
        if(!this.endOfCiphertext) {
            this.readSomeCiphertext(this.ciphertextSegment);
        }
        int v = 0;
        if(this.ciphertextSegment.remaining() > 0 && !this.endOfCiphertext) {
            return false;
        }
        if(!this.endOfCiphertext) {
            v = this.ciphertextSegment.get(this.ciphertextSegment.position() - 1);
            this.ciphertextSegment.position(this.ciphertextSegment.position() - 1);
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
        }
        catch(GeneralSecurityException generalSecurityException0) {
            this.setUndefinedState();
            throw new IOException(generalSecurityException0.getMessage() + "\n" + this.toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, generalSecurityException0);
        }
        ++this.segmentNr;
        this.plaintextSegment.flip();
        this.ciphertextSegment.clear();
        if(!this.endOfCiphertext) {
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
            this.ciphertextSegment.put(((byte)v));
        }
        return true;
    }

    private boolean tryReadHeader() throws IOException {
        if(!this.endOfCiphertext) {
            this.readSomeCiphertext(this.header);
            if(this.header.remaining() > 0) {
                return false;
            }
            this.header.flip();
            try {
                this.decrypter.init(this.header, this.associatedData);
                this.headerRead = true;
                return true;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.setUndefinedState();
                throw new IOException(generalSecurityException0);
            }
        }
        throw new IOException("Ciphertext is too short");
    }
}

