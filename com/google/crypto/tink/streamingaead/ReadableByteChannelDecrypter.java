package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.subtle.RewindableReadableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;

final class ReadableByteChannelDecrypter implements AutoCloseable, ReadableByteChannel {
    byte[] associatedData;
    ReadableByteChannel attemptingChannel;
    RewindableReadableByteChannel ciphertextChannel;
    ReadableByteChannel matchingChannel;
    Deque remainingPrimitives;

    public ReadableByteChannelDecrypter(PrimitiveSet primitiveSet0, ReadableByteChannel readableByteChannel0, byte[] arr_b) {
        this.attemptingChannel = null;
        this.matchingChannel = null;
        this.remainingPrimitives = new ArrayDeque();
        for(Object object0: primitiveSet0.getRawPrimitives()) {
            this.remainingPrimitives.add(((StreamingAead)((Entry)object0).getPrimitive()));
        }
        this.ciphertextChannel = new RewindableReadableByteChannel(readableByteChannel0);
        this.associatedData = (byte[])arr_b.clone();
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

    private ReadableByteChannel nextAttemptingChannel() throws IOException {
        ReadableByteChannel readableByteChannel0;
        synchronized(this) {
            while(!this.remainingPrimitives.isEmpty()) {
                StreamingAead streamingAead0 = (StreamingAead)this.remainingPrimitives.removeFirst();
                try {
                    readableByteChannel0 = streamingAead0.newDecryptingChannel(this.ciphertextChannel, this.associatedData);
                }
                catch(GeneralSecurityException unused_ex) {
                    this.ciphertextChannel.rewind();
                    continue;
                }
                return readableByteChannel0;
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(byteBuffer0.remaining() == 0) {
                return 0;
            }
            ReadableByteChannel readableByteChannel0 = this.matchingChannel;
            if(readableByteChannel0 != null) {
                return readableByteChannel0.read(byteBuffer0);
            }
            if(this.attemptingChannel == null) {
                this.attemptingChannel = this.nextAttemptingChannel();
            }
            while(true) {
                try {
                    int v2 = this.attemptingChannel.read(byteBuffer0);
                    if(v2 == 0) {
                        break;
                    }
                    this.matchingChannel = this.attemptingChannel;
                    this.attemptingChannel = null;
                    this.ciphertextChannel.disableRewinding();
                    return v2;
                }
                catch(IOException unused_ex) {
                    this.ciphertextChannel.rewind();
                    this.attemptingChannel = this.nextAttemptingChannel();
                }
            }
            return 0;
        }
    }
}

