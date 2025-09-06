package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class StreamingAeadSeekableDecryptingChannel implements AutoCloseable, SeekableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final SeekableByteChannel ciphertextChannel;
    private final long ciphertextChannelSize;
    private final int ciphertextOffset;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private int currentSegmentNr;
    private final StreamSegmentDecrypter decrypter;
    private final int firstSegmentOffset;
    private final ByteBuffer header;
    private boolean headerRead;
    private boolean isCurrentSegmentDecrypted;
    private boolean isopen;
    private final int lastCiphertextSegmentSize;
    private final int numberOfSegments;
    private long plaintextPosition;
    private final ByteBuffer plaintextSegment;
    private final int plaintextSegmentSize;
    private long plaintextSize;

    public StreamingAeadSeekableDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead0, SeekableByteChannel seekableByteChannel0, byte[] arr_b) throws IOException, GeneralSecurityException {
        this.decrypter = nonceBasedStreamingAead0.newStreamSegmentDecrypter();
        this.ciphertextChannel = seekableByteChannel0;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead0.getHeaderLength());
        int v = nonceBasedStreamingAead0.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = v;
        this.ciphertextSegment = ByteBuffer.allocate(v);
        int v1 = nonceBasedStreamingAead0.getPlaintextSegmentSize();
        this.plaintextSegmentSize = v1;
        this.plaintextSegment = ByteBuffer.allocate(v1 + 16);
        this.plaintextPosition = 0L;
        this.headerRead = false;
        this.currentSegmentNr = -1;
        this.isCurrentSegmentDecrypted = false;
        long v2 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0);
        this.ciphertextChannelSize = v2;
        this.aad = Arrays.copyOf(arr_b, arr_b.length);
        this.isopen = LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0);
        int v3 = (int)(v2 / ((long)v));
        int v4 = (int)(v2 % ((long)v));
        int v5 = nonceBasedStreamingAead0.getCiphertextOverhead();
        if(v4 > 0) {
            this.numberOfSegments = v3 + 1;
            if(v4 < v5) {
                throw new IOException("Invalid ciphertext size");
            }
            this.lastCiphertextSegmentSize = v4;
        }
        else {
            this.numberOfSegments = v3;
            this.lastCiphertextSegmentSize = v;
        }
        int v6 = nonceBasedStreamingAead0.getCiphertextOffset();
        this.ciphertextOffset = v6;
        int v7 = v6 - nonceBasedStreamingAead0.getHeaderLength();
        this.firstSegmentOffset = v7;
        if(v7 < 0) {
            throw new IOException("Invalid ciphertext offset or header length");
        }
        long v8 = ((long)this.numberOfSegments) * ((long)v5) + ((long)v6);
        if(v8 > v2) {
            throw new IOException("Ciphertext is too short");
        }
        this.plaintextSize = v2 - v8;
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel);
            this.isopen = false;
        }
    }

    private int getSegmentNr(long v) {
        return (int)((v + ((long)this.ciphertextOffset)) / ((long)this.plaintextSegmentSize));
    }

    @Override
    public boolean isOpen() {
        synchronized(this) {
        }
        return this.isopen;
    }

    @Override
    public long position() {
        synchronized(this) {
        }
        return this.plaintextPosition;
    }

    @Override
    public SeekableByteChannel position(long v) {
        synchronized(this) {
            this.plaintextPosition = v;
        }
        return this;
    }

    private boolean reachedEnd() {
        return this.isCurrentSegmentDecrypted && this.currentSegmentNr == this.numberOfSegments - 1 && this.plaintextSegment.remaining() == 0;
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(this.isopen) {
                if(!this.headerRead && !this.tryReadHeader()) {
                    return 0;
                }
                int v1 = byteBuffer0.position();
                while(byteBuffer0.remaining() > 0) {
                    long v2 = this.plaintextPosition;
                    if(v2 >= this.plaintextSize) {
                        break;
                    }
                    int v3 = this.getSegmentNr(v2);
                    long v4 = v3 == 0 ? this.plaintextPosition : (this.plaintextPosition + ((long)this.ciphertextOffset)) % ((long)this.plaintextSegmentSize);
                    if(!this.tryLoadSegment(v3)) {
                        break;
                    }
                    this.plaintextSegment.position(((int)v4));
                    if(this.plaintextSegment.remaining() <= byteBuffer0.remaining()) {
                        this.plaintextPosition += (long)this.plaintextSegment.remaining();
                        byteBuffer0.put(this.plaintextSegment);
                    }
                    else {
                        int v5 = byteBuffer0.remaining();
                        ByteBuffer byteBuffer1 = this.plaintextSegment.duplicate();
                        byteBuffer1.limit(byteBuffer1.position() + v5);
                        byteBuffer0.put(byteBuffer1);
                        this.plaintextPosition += (long)v5;
                        this.plaintextSegment.position(this.plaintextSegment.position() + v5);
                    }
                }
                int v6 = byteBuffer0.position() - v1;
                return v6 == 0 && this.reachedEnd() ? -1 : v6;
            }
        }
        throw new ClosedChannelException();
    }

    public int read(ByteBuffer byteBuffer0, long v) throws IOException {
        synchronized(this) {
            long v2 = this.position();
            try {
                this.position(v);
                return this.read(byteBuffer0);
            }
            finally {
                this.position(v2);
            }
        }
    }

    @Override
    public long size() {
        return this.plaintextSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0;
        String s;
        synchronized(this) {
            stringBuilder0 = new StringBuilder("StreamingAeadSeekableDecryptingChannel\nciphertextChannel");
            try {
                s = "position: n/a";
                s = "position:" + LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(this.ciphertextChannel);
            }
            catch(IOException unused_ex) {
            }
            stringBuilder0.append(s);
            stringBuilder0.append("\nciphertextChannelSize:");
            stringBuilder0.append(this.ciphertextChannelSize);
            stringBuilder0.append("\nplaintextSize:");
            stringBuilder0.append(this.plaintextSize);
            stringBuilder0.append("\nciphertextSegmentSize:");
            stringBuilder0.append(this.ciphertextSegmentSize);
            stringBuilder0.append("\nnumberOfSegments:");
            stringBuilder0.append(this.numberOfSegments);
            stringBuilder0.append("\nheaderRead:");
            stringBuilder0.append(this.headerRead);
            stringBuilder0.append("\nplaintextPosition:");
            stringBuilder0.append(this.plaintextPosition);
            stringBuilder0.append("\nHeader position:");
            stringBuilder0.append(this.header.position());
            stringBuilder0.append(" limit:");
            stringBuilder0.append(this.header.position());
            stringBuilder0.append("\ncurrentSegmentNr:");
            stringBuilder0.append(this.currentSegmentNr);
            stringBuilder0.append("\nciphertextSgement position:");
            stringBuilder0.append(this.ciphertextSegment.position());
            stringBuilder0.append(" limit:");
            stringBuilder0.append(this.ciphertextSegment.limit());
            stringBuilder0.append("\nisCurrentSegmentDecrypted:");
            stringBuilder0.append(this.isCurrentSegmentDecrypted);
            stringBuilder0.append("\nplaintextSegment position:");
            stringBuilder0.append(this.plaintextSegment.position());
            stringBuilder0.append(" limit:");
            stringBuilder0.append(this.plaintextSegment.limit());
        }
        return stringBuilder0.toString();
    }

    @Override
    public SeekableByteChannel truncate(long v) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    private boolean tryLoadSegment(int v) throws IOException {
        if(v >= 0) {
            int v1 = this.numberOfSegments;
            if(v < v1) {
                boolean z = v == v1 - 1;
                if(v != this.currentSegmentNr) {
                    int v2 = this.ciphertextSegmentSize;
                    long v3 = ((long)v) * ((long)v2);
                    if(z) {
                        v2 = this.lastCiphertextSegmentSize;
                    }
                    if(v == 0) {
                        v2 -= this.ciphertextOffset;
                        v3 = (long)this.ciphertextOffset;
                    }
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel, v3);
                    this.ciphertextSegment.clear();
                    this.ciphertextSegment.limit(v2);
                    this.currentSegmentNr = v;
                    this.isCurrentSegmentDecrypted = false;
                }
                else if(this.isCurrentSegmentDecrypted) {
                    return true;
                }
                if(this.ciphertextSegment.remaining() > 0) {
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel, this.ciphertextSegment);
                }
                if(this.ciphertextSegment.remaining() > 0) {
                    return false;
                }
                this.ciphertextSegment.flip();
                this.plaintextSegment.clear();
                try {
                    this.decrypter.decryptSegment(this.ciphertextSegment, v, z, this.plaintextSegment);
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    this.currentSegmentNr = -1;
                    throw new IOException("Failed to decrypt", generalSecurityException0);
                }
                this.plaintextSegment.flip();
                this.isCurrentSegmentDecrypted = true;
                return true;
            }
        }
        throw new IOException("Invalid position");
    }

    private boolean tryReadHeader() throws IOException {
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel, ((long)(this.header.position() + this.firstSegmentOffset)));
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel, this.header);
        if(this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IOException(generalSecurityException0);
        }
    }

    public long verifiedSize() throws IOException {
        synchronized(this) {
            if(this.tryLoadSegment(this.numberOfSegments - 1)) {
                return this.plaintextSize;
            }
        }
        throw new IOException("could not verify the size");
    }

    @Override
    public int write(ByteBuffer byteBuffer0) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }
}

