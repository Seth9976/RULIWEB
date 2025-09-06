package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public final class RewindableReadableByteChannel implements AutoCloseable, ReadableByteChannel {
    final ReadableByteChannel baseChannel;
    ByteBuffer buffer;
    boolean canRewind;
    boolean directRead;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel0) {
        this.baseChannel = readableByteChannel0;
        this.buffer = null;
        this.canRewind = true;
        this.directRead = false;
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            this.canRewind = false;
            this.directRead = true;
            this.baseChannel.close();
        }
    }

    public void disableRewinding() {
        synchronized(this) {
            this.canRewind = false;
        }
    }

    @Override
    public boolean isOpen() {
        synchronized(this) {
            return this.baseChannel.isOpen();
        }
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        synchronized(this) {
            if(this.directRead) {
                return this.baseChannel.read(byteBuffer0);
            }
            int v2 = byteBuffer0.remaining();
            if(v2 == 0) {
                return 0;
            }
            ByteBuffer byteBuffer1 = this.buffer;
            if(byteBuffer1 == null) {
                if(!this.canRewind) {
                    this.directRead = true;
                    return this.baseChannel.read(byteBuffer0);
                }
                ByteBuffer byteBuffer2 = ByteBuffer.allocate(v2);
                this.buffer = byteBuffer2;
                int v4 = this.baseChannel.read(byteBuffer2);
                this.buffer.flip();
                if(v4 > 0) {
                    byteBuffer0.put(this.buffer);
                }
                return v4;
            }
            if(byteBuffer1.remaining() >= v2) {
                int v5 = this.buffer.limit();
                this.buffer.limit(this.buffer.position() + v2);
                byteBuffer0.put(this.buffer);
                this.buffer.limit(v5);
                if(!this.canRewind && !this.buffer.hasRemaining()) {
                    this.buffer = null;
                    this.directRead = true;
                }
                return v2;
            }
            int v6 = this.buffer.remaining();
            int v7 = this.buffer.position();
            int v8 = this.buffer.limit();
            this.setBufferLimit(v2 - v6 + v8);
            this.buffer.position(v8);
            int v9 = this.baseChannel.read(this.buffer);
            this.buffer.flip();
            this.buffer.position(v7);
            byteBuffer0.put(this.buffer);
            if(v6 == 0 && v9 < 0) {
                return -1;
            }
            int v10 = this.buffer.position();
            if(!this.canRewind && !this.buffer.hasRemaining()) {
                this.buffer = null;
                this.directRead = true;
            }
            return v10 - v7;
        }
    }

    public void rewind() throws IOException {
        synchronized(this) {
            if(this.canRewind) {
                ByteBuffer byteBuffer0 = this.buffer;
                if(byteBuffer0 != null) {
                    byteBuffer0.position(0);
                }
                return;
            }
        }
        throw new IOException("Cannot rewind anymore.");
    }

    private void setBufferLimit(int v) {
        synchronized(this) {
            if(this.buffer.capacity() < v) {
                int v2 = this.buffer.position();
                ByteBuffer byteBuffer0 = ByteBuffer.allocate(Math.max(this.buffer.capacity() * 2, v));
                this.buffer.rewind();
                byteBuffer0.put(this.buffer);
                byteBuffer0.position(v2);
                this.buffer = byteBuffer0;
            }
            this.buffer.limit(v);
        }
    }
}

