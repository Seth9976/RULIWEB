package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

class IterableByteBufferInputStream extends InputStream {
    private long currentAddress;
    private byte[] currentArray;
    private int currentArrayOffset;
    private ByteBuffer currentByteBuffer;
    private int currentByteBufferPos;
    private int currentIndex;
    private int dataSize;
    private boolean hasArray;
    private Iterator iterator;

    IterableByteBufferInputStream(Iterable iterable0) {
        this.iterator = iterable0.iterator();
        this.dataSize = 0;
        for(Object object0: iterable0) {
            ByteBuffer byteBuffer0 = (ByteBuffer)object0;
            ++this.dataSize;
        }
        this.currentIndex = -1;
        if(!this.getNextByteBuffer()) {
            this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
            this.currentIndex = 0;
            this.currentByteBufferPos = 0;
            this.currentAddress = 0L;
        }
    }

    private boolean getNextByteBuffer() {
        ++this.currentIndex;
        if(!this.iterator.hasNext()) {
            return false;
        }
        Object object0 = this.iterator.next();
        this.currentByteBuffer = (ByteBuffer)object0;
        this.currentByteBufferPos = ((ByteBuffer)object0).position();
        if(this.currentByteBuffer.hasArray()) {
            this.hasArray = true;
            this.currentArray = this.currentByteBuffer.array();
            this.currentArrayOffset = this.currentByteBuffer.arrayOffset();
            return true;
        }
        this.hasArray = false;
        this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
        this.currentArray = null;
        return true;
    }

    @Override
    public int read() throws IOException {
        if(this.currentIndex == this.dataSize) {
            return -1;
        }
        if(this.hasArray) {
            int v = this.currentArray[this.currentByteBufferPos + this.currentArrayOffset] & 0xFF;
            this.updateCurrentByteBufferPos(1);
            return v;
        }
        int v1 = UnsafeUtil.getByte(((long)this.currentByteBufferPos) + this.currentAddress);
        this.updateCurrentByteBufferPos(1);
        return v1 & 0xFF;
    }

    @Override
    public int read(byte[] arr_b, int v, int v1) throws IOException {
        if(this.currentIndex == this.dataSize) {
            return -1;
        }
        int v2 = this.currentByteBufferPos;
        int v3 = this.currentByteBuffer.limit() - v2;
        if(v1 > v3) {
            v1 = v3;
        }
        if(this.hasArray) {
            System.arraycopy(this.currentArray, v2 + this.currentArrayOffset, arr_b, v, v1);
            this.updateCurrentByteBufferPos(v1);
            return v1;
        }
        int v4 = this.currentByteBuffer.position();
        this.currentByteBuffer.position(this.currentByteBufferPos);
        this.currentByteBuffer.get(arr_b, v, v1);
        this.currentByteBuffer.position(v4);
        this.updateCurrentByteBufferPos(v1);
        return v1;
    }

    private void updateCurrentByteBufferPos(int v) {
        int v1 = this.currentByteBufferPos + v;
        this.currentByteBufferPos = v1;
        if(v1 == this.currentByteBuffer.limit()) {
            this.getNextByteBuffer();
        }
    }
}

