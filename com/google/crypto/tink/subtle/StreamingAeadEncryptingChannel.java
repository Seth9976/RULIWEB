package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

class StreamingAeadEncryptingChannel implements AutoCloseable, WritableByteChannel {
    private WritableByteChannel ciphertextChannel;
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead0, WritableByteChannel writableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        this.open = true;
        this.ciphertextChannel = writableByteChannel0;
        this.encrypter = nonceBasedStreamingAead0.newStreamSegmentEncrypter(arr_b);
        int v = nonceBasedStreamingAead0.getPlaintextSegmentSize();
        this.plaintextSegmentSize = v;
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(v);
        this.ptBuffer = byteBuffer0;
        byteBuffer0.limit(this.plaintextSegmentSize - nonceBasedStreamingAead0.getCiphertextOffset());
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(nonceBasedStreamingAead0.getCiphertextSegmentSize());
        this.ctBuffer = byteBuffer1;
        byteBuffer1.put(this.encrypter.getHeader());
        this.ctBuffer.flip();
        writableByteChannel0.write(this.ctBuffer);
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            if(!this.open) {
                return;
            }
            while(this.ctBuffer.remaining() > 0) {
                if(this.ciphertextChannel.write(this.ctBuffer) <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
                if(false) {
                    break;
                }
            }
            try {
                this.ctBuffer.clear();
                this.ptBuffer.flip();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new IOException(generalSecurityException0);
            }
            this.ctBuffer.flip();
            while(this.ctBuffer.remaining() > 0) {
                if(this.ciphertextChannel.write(this.ctBuffer) <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
                if(false) {
                    break;
                }
            }
            this.ciphertextChannel.close();
            this.open = false;
        }
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }

    @Override
    public int write(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(this.open) {
                if(this.ctBuffer.remaining() > 0) {
                    this.ciphertextChannel.write(this.ctBuffer);
                }
                int v1 = byteBuffer0.position();
                while(byteBuffer0.remaining() > this.ptBuffer.remaining()) {
                    if(this.ctBuffer.remaining() > 0) {
                        return byteBuffer0.position() - v1;
                    }
                    int v2 = this.ptBuffer.remaining();
                    ByteBuffer byteBuffer1 = byteBuffer0.slice();
                    byteBuffer1.limit(v2);
                    byteBuffer0.position(byteBuffer0.position() + v2);
                    try {
                        this.ptBuffer.flip();
                        this.ctBuffer.clear();
                        if(byteBuffer1.remaining() == 0) {
                            this.encrypter.encryptSegment(this.ptBuffer, false, this.ctBuffer);
                        }
                        else {
                            this.encrypter.encryptSegment(this.ptBuffer, byteBuffer1, false, this.ctBuffer);
                        }
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        throw new IOException(generalSecurityException0);
                    }
                    this.ctBuffer.flip();
                    this.ciphertextChannel.write(this.ctBuffer);
                    this.ptBuffer.clear();
                    this.ptBuffer.limit(this.plaintextSegmentSize);
                }
                this.ptBuffer.put(byteBuffer0);
                return byteBuffer0.position() - v1;
            }
        }
        throw new ClosedChannelException();
    }
}

