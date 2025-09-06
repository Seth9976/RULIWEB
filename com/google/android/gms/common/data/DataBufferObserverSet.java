package com.google.android.gms.common.data;

import java.util.HashSet;

public final class DataBufferObserverSet implements Observable, DataBufferObserver {
    private final HashSet zaa;

    public DataBufferObserverSet() {
        this.zaa = new HashSet();
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver$Observable
    public void addObserver(DataBufferObserver dataBufferObserver0) {
        this.zaa.add(dataBufferObserver0);
    }

    public void clear() {
        this.zaa.clear();
    }

    public boolean hasObservers() {
        return !this.zaa.isEmpty();
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver
    public void onDataChanged() {
        for(Object object0: this.zaa) {
            ((DataBufferObserver)object0).onDataChanged();
        }
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeChanged(int v, int v1) {
        for(Object object0: this.zaa) {
            ((DataBufferObserver)object0).onDataRangeChanged(v, v1);
        }
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeInserted(int v, int v1) {
        for(Object object0: this.zaa) {
            ((DataBufferObserver)object0).onDataRangeInserted(v, v1);
        }
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeMoved(int v, int v1, int v2) {
        for(Object object0: this.zaa) {
            ((DataBufferObserver)object0).onDataRangeMoved(v, v1, v2);
        }
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeRemoved(int v, int v1) {
        for(Object object0: this.zaa) {
            ((DataBufferObserver)object0).onDataRangeRemoved(v, v1);
        }
    }

    @Override  // com.google.android.gms.common.data.DataBufferObserver$Observable
    public void removeObserver(DataBufferObserver dataBufferObserver0) {
        this.zaa.remove(dataBufferObserver0);
    }
}

