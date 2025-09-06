package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.io.Closeable;
import java.util.Iterator;

public interface DataBuffer extends Releasable, Closeable, Iterable {
    @Override
    void close();

    Object get(int arg1);

    int getCount();

    Bundle getMetadata();

    @Deprecated
    boolean isClosed();

    @Override
    Iterator iterator();

    @Override  // com.google.android.gms.common.api.Releasable
    void release();

    Iterator singleRefIterator();
}

