package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

final class SeekableByteChannelDecrypter implements AutoCloseable, SeekableByteChannel {
    byte[] associatedData;
    SeekableByteChannel attemptingChannel;
    long cachedPosition;
    SeekableByteChannel ciphertextChannel;
    SeekableByteChannel matchingChannel;
    Deque remainingPrimitives;
    long startingPosition;

    public SeekableByteChannelDecrypter(PrimitiveSet primitiveSet0, SeekableByteChannel seekableByteChannel0, byte[] arr_b) throws IOException {
        this.attemptingChannel = null;
        this.matchingChannel = null;
        this.remainingPrimitives = new ArrayDeque();
        for(Object object0: primitiveSet0.getRawPrimitives()) {
            this.remainingPrimitives.add(((StreamingAead)((Entry)object0).getPrimitive()));
        }
        this.ciphertextChannel = seekableByteChannel0;
        this.cachedPosition = -1L;
        this.startingPosition = LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(seekableByteChannel0);
        this.associatedData = (byte[])arr_b.clone();
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel);
        }
    }

    @Override
    public boolean isOpen() {
        synchronized(this) {
            return LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel);
        }
    }

    private SeekableByteChannel nextAttemptingChannel() throws IOException {
        SeekableByteChannel seekableByteChannel0;
        __monitor_enter(this);
    label_1:
        try {
            if(!this.remainingPrimitives.isEmpty()) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.ciphertextChannel, this.startingPosition);
                StreamingAead streamingAead0 = (StreamingAead)this.remainingPrimitives.removeFirst();
                try {
                    seekableByteChannel0 = streamingAead0.newSeekableDecryptingChannel(this.ciphertextChannel, this.associatedData);
                    long v1 = this.cachedPosition;
                    if(v1 >= 0L) {
                        LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0, v1);
                    }
                }
                catch(GeneralSecurityException unused_ex) {
                    goto label_1;
                }
                return seekableByteChannel0;
            }
        }
        finally {
            __monitor_exit(this);
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }

    @Override
    public long position() throws IOException {
        synchronized(this) {
            SeekableByteChannel seekableByteChannel0 = this.matchingChannel;
            return seekableByteChannel0 != null ? LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(seekableByteChannel0) : this.cachedPosition;
        }
    }

    @Override
    public SeekableByteChannel position(long v) throws IOException {
        synchronized(this) {
            SeekableByteChannel seekableByteChannel0 = this.matchingChannel;
            if(seekableByteChannel0 != null) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0, v);
                return this;
            }
            if(v >= 0L) {
                this.cachedPosition = v;
                SeekableByteChannel seekableByteChannel1 = this.attemptingChannel;
                if(seekableByteChannel1 != null) {
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel1, v);
                }
                return this;
            }
        }
        throw new IllegalArgumentException("Position must be non-negative");
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(byteBuffer0.remaining() == 0) {
                return 0;
            }
            SeekableByteChannel seekableByteChannel0 = this.matchingChannel;
            if(seekableByteChannel0 != null) {
                return LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0, byteBuffer0);
            }
            if(this.attemptingChannel == null) {
                this.attemptingChannel = this.nextAttemptingChannel();
            }
            while(true) {
                try {
                    int v2 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.attemptingChannel, byteBuffer0);
                    if(v2 == 0) {
                        break;
                    }
                    this.matchingChannel = this.attemptingChannel;
                    this.attemptingChannel = null;
                    return v2;
                }
                catch(IOException unused_ex) {
                    this.attemptingChannel = this.nextAttemptingChannel();
                }
            }
            return 0;
        }
    }

    @Override
    public long size() throws IOException {
        synchronized(this) {
            SeekableByteChannel seekableByteChannel0 = this.matchingChannel;
            if(seekableByteChannel0 != null) {
                return LinkFollowing..ExternalSyntheticApiModelOutline0.m(seekableByteChannel0);
            }
        }
        throw new IOException("Cannot determine size before first read()-call.");
    }

    @Override
    public SeekableByteChannel truncate(long v) throws IOException {
        throw new NonWritableChannelException();
    }

    @Override
    public int write(ByteBuffer byteBuffer0) throws IOException {
        throw new NonWritableChannelException();
    }
}

