package com.google.crypto.tink.subtle;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

class StreamingAeadEncryptingStream extends FilterOutputStream implements AutoCloseable {
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead0, OutputStream outputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        super(outputStream0);
        this.encrypter = nonceBasedStreamingAead0.newStreamSegmentEncrypter(arr_b);
        int v = nonceBasedStreamingAead0.getPlaintextSegmentSize();
        this.plaintextSegmentSize = v;
        this.ptBuffer = ByteBuffer.allocate(v);
        this.ctBuffer = ByteBuffer.allocate(nonceBasedStreamingAead0.getCiphertextSegmentSize());
        this.ptBuffer.limit(this.plaintextSegmentSize - nonceBasedStreamingAead0.getCiphertextOffset());
        ByteBuffer byteBuffer0 = this.encrypter.getHeader();
        byte[] arr_b1 = new byte[byteBuffer0.remaining()];
        byteBuffer0.get(arr_b1);
        this.out.write(arr_b1);
        this.open = true;
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            if(!this.open) {
                return;
            }
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new IOException("ptBuffer.remaining():" + this.ptBuffer.remaining() + " ctBuffer.remaining():" + this.ctBuffer.remaining(), generalSecurityException0);
            }
            this.ctBuffer.flip();
            this.out.write(this.ctBuffer.array(), this.ctBuffer.position(), this.ctBuffer.remaining());
            this.open = false;
            super.close();
        }
    }

    @Override
    public void write(int v) throws IOException {
        this.write(new byte[]{((byte)v)});
    }

    @Override
    public void write(byte[] arr_b) throws IOException {
        this.write(arr_b, 0, arr_b.length);
    }

    @Override
    public void write(byte[] arr_b, int v, int v1) throws IOException {
        synchronized(this) {
            if(this.open) {
                while(v1 > this.ptBuffer.remaining()) {
                    int v3 = this.ptBuffer.remaining();
                    ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, v, v3);
                    v += v3;
                    v1 -= v3;
                    try {
                        this.ptBuffer.flip();
                        this.ctBuffer.clear();
                        this.encrypter.encryptSegment(this.ptBuffer, byteBuffer0, false, this.ctBuffer);
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        throw new IOException(generalSecurityException0);
                    }
                    this.ctBuffer.flip();
                    this.out.write(this.ctBuffer.array(), this.ctBuffer.position(), this.ctBuffer.remaining());
                    this.ptBuffer.clear();
                    this.ptBuffer.limit(this.plaintextSegmentSize);
                }
                this.ptBuffer.put(arr_b, v, v1);
                return;
            }
        }
        throw new IOException("Trying to write to closed stream");
    }
}

