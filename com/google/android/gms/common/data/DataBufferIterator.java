package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBufferIterator implements Iterator {
    protected final DataBuffer zaa;
    protected int zab;

    public DataBufferIterator(DataBuffer dataBuffer0) {
        this.zaa = (DataBuffer)Preconditions.checkNotNull(dataBuffer0);
        this.zab = -1;
    }

    @Override
    public final boolean hasNext() {
        return this.zab < this.zaa.getCount() - 1;
    }

    @Override
    public Object next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zab);
        }
        int v = this.zab + 1;
        this.zab = v;
        return this.zaa.get(v);
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}

