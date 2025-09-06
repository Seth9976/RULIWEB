package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.NoSuchElementException;

public class SingleRefDataBufferIterator extends DataBufferIterator {
    private Object zac;

    public SingleRefDataBufferIterator(DataBuffer dataBuffer0) {
        super(dataBuffer0);
    }

    @Override  // com.google.android.gms.common.data.DataBufferIterator
    public final Object next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zab);
        }
        int v = this.zab + 1;
        this.zab = v;
        if(v == 0) {
            Object object0 = Preconditions.checkNotNull(this.zaa.get(0));
            this.zac = object0;
            if(!(object0 instanceof DataBufferRef)) {
                throw new IllegalStateException("DataBuffer reference of type " + object0.getClass() + " is not movable");
            }
        }
        else {
            ((DataBufferRef)Preconditions.checkNotNull(this.zac)).zaa(this.zab);
        }
        return this.zac;
    }
}

