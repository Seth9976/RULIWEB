package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;

public abstract class EntityBuffer extends AbstractDataBuffer {
    private boolean zaa;
    private ArrayList zab;

    protected EntityBuffer(DataHolder dataHolder0) {
        super(dataHolder0);
        this.zaa = false;
    }

    @Override  // com.google.android.gms.common.data.AbstractDataBuffer
    @ResultIgnorabilityUnspecified
    public final Object get(int v) {
        int v4;
        int v3;
        this.zab();
        int v1 = this.zaa(v);
        int v2 = 0;
        if(v >= 0 && v != this.zab.size()) {
            v3 = v == this.zab.size() - 1 ? ((DataHolder)Preconditions.checkNotNull(this.mDataHolder)).getCount() : ((int)(((Integer)this.zab.get(v + 1))));
            v4 = (int)(((Integer)this.zab.get(v)));
            int v5 = v3 - v4;
            if(v5 == 1) {
                int v6 = this.zaa(v);
                ((DataHolder)Preconditions.checkNotNull(this.mDataHolder)).getWindowIndex(v6);
                return this.getEntry(v1, 1);
            }
            v2 = v5;
        }
        return this.getEntry(v1, v2);
    }

    protected String getChildDataMarkerColumn() [...] // Inlined contents

    @Override  // com.google.android.gms.common.data.AbstractDataBuffer
    public int getCount() {
        this.zab();
        return this.zab.size();
    }

    protected abstract Object getEntry(int arg1, int arg2);

    protected abstract String getPrimaryDataMarkerColumn();

    final int zaa(int v) {
        if(v < 0 || v >= this.zab.size()) {
            throw new IllegalArgumentException("Position " + v + " is out of bounds for this buffer");
        }
        return (int)(((Integer)this.zab.get(v)));
    }

    private final void zab() {
        synchronized(this) {
            if(!this.zaa) {
                int v1 = ((DataHolder)Preconditions.checkNotNull(this.mDataHolder)).getCount();
                ArrayList arrayList0 = new ArrayList();
                this.zab = arrayList0;
                if(v1 > 0) {
                    arrayList0.add(0);
                    String s = this.getPrimaryDataMarkerColumn();
                    int v2 = this.mDataHolder.getWindowIndex(0);
                    String s1 = this.mDataHolder.getString(s, 0, v2);
                    for(int v3 = 1; v3 < v1; ++v3) {
                        int v4 = this.mDataHolder.getWindowIndex(v3);
                        String s2 = this.mDataHolder.getString(s, v3, v4);
                        if(s2 == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + s + ", at row: " + v3 + ", for window: " + v4);
                        }
                        if(!s2.equals(s1)) {
                            this.zab.add(v3);
                            s1 = s2;
                        }
                    }
                }
                this.zaa = true;
            }
        }
    }
}

