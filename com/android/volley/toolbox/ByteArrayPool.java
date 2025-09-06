package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ByteArrayPool {
    protected static final Comparator BUF_COMPARATOR;
    private final List mBuffersByLastUse;
    private final List mBuffersBySize;
    private int mCurrentSize;
    private final int mSizeLimit;

    static {
        ByteArrayPool.BUF_COMPARATOR = new Comparator() {
            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((byte[])object0), ((byte[])object1));
            }

            public int compare(byte[] arr_b, byte[] arr_b1) {
                return arr_b.length - arr_b1.length;
            }
        };
    }

    public ByteArrayPool(int v) {
        this.mBuffersByLastUse = new ArrayList();
        this.mBuffersBySize = new ArrayList(0x40);
        this.mCurrentSize = 0;
        this.mSizeLimit = v;
    }

    public byte[] getBuf(int v) {
        synchronized(this) {
            for(int v2 = 0; v2 < this.mBuffersBySize.size(); ++v2) {
                byte[] arr_b = (byte[])this.mBuffersBySize.get(v2);
                if(arr_b.length >= v) {
                    this.mCurrentSize -= arr_b.length;
                    this.mBuffersBySize.remove(v2);
                    this.mBuffersByLastUse.remove(arr_b);
                    return arr_b;
                }
            }
        }
        return new byte[v];
    }

    public void returnBuf(byte[] arr_b) {
        synchronized(this) {
            if(arr_b != null && arr_b.length <= this.mSizeLimit) {
                this.mBuffersByLastUse.add(arr_b);
                int v1 = Collections.binarySearch(this.mBuffersBySize, arr_b, ByteArrayPool.BUF_COMPARATOR);
                if(v1 < 0) {
                    v1 = -v1 - 1;
                }
                this.mBuffersBySize.add(v1, arr_b);
                this.mCurrentSize += arr_b.length;
                this.trim();
            }
        }
    }

    private void trim() {
        synchronized(this) {
            while(this.mCurrentSize > this.mSizeLimit) {
                byte[] arr_b = (byte[])this.mBuffersByLastUse.remove(0);
                this.mBuffersBySize.remove(arr_b);
                this.mCurrentSize -= arr_b.length;
            }
        }
    }
}

