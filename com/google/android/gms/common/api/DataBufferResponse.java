package com.google.android.gms.common.api;

import android.os.Bundle;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

public class DataBufferResponse extends Response implements DataBuffer, AutoCloseable {
    public DataBufferResponse() {
    }

    public DataBufferResponse(AbstractDataBuffer abstractDataBuffer0) {
        super(((Result)abstractDataBuffer0));
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final void close() {
        ((AbstractDataBuffer)this.getResult()).close();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final Object get(int v) {
        return ((AbstractDataBuffer)this.getResult()).get(v);
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final int getCount() {
        return ((AbstractDataBuffer)this.getResult()).getCount();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final Bundle getMetadata() {
        return ((AbstractDataBuffer)this.getResult()).getMetadata();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final boolean isClosed() {
        return ((AbstractDataBuffer)this.getResult()).isClosed();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final Iterator iterator() {
        return ((AbstractDataBuffer)this.getResult()).iterator();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final void release() {
        ((AbstractDataBuffer)this.getResult()).release();
    }

    @Override  // com.google.android.gms.common.data.DataBuffer
    public final Iterator singleRefIterator() {
        return ((AbstractDataBuffer)this.getResult()).singleRefIterator();
    }
}

