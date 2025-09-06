package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public abstract class DataBufferRef {
    protected final DataHolder mDataHolder;
    protected int mDataRow;
    private int zaa;

    public DataBufferRef(DataHolder dataHolder0, int v) {
        this.mDataHolder = (DataHolder)Preconditions.checkNotNull(dataHolder0);
        this.zaa(v);
    }

    protected void copyToBuffer(String s, CharArrayBuffer charArrayBuffer0) {
        this.mDataHolder.zac(s, this.mDataRow, this.zaa, charArrayBuffer0);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof DataBufferRef && Objects.equal(((DataBufferRef)object0).mDataRow, this.mDataRow) && Objects.equal(((DataBufferRef)object0).zaa, this.zaa) && ((DataBufferRef)object0).mDataHolder == this.mDataHolder;
    }

    protected boolean getBoolean(String s) {
        return this.mDataHolder.getBoolean(s, this.mDataRow, this.zaa);
    }

    protected byte[] getByteArray(String s) {
        return this.mDataHolder.getByteArray(s, this.mDataRow, this.zaa);
    }

    protected int getDataRow() {
        return this.mDataRow;
    }

    protected double getDouble(String s) {
        return this.mDataHolder.zaa(s, this.mDataRow, this.zaa);
    }

    protected float getFloat(String s) {
        return this.mDataHolder.zab(s, this.mDataRow, this.zaa);
    }

    protected int getInteger(String s) {
        return this.mDataHolder.getInteger(s, this.mDataRow, this.zaa);
    }

    protected long getLong(String s) {
        return this.mDataHolder.getLong(s, this.mDataRow, this.zaa);
    }

    protected String getString(String s) {
        return this.mDataHolder.getString(s, this.mDataRow, this.zaa);
    }

    public boolean hasColumn(String s) {
        return this.mDataHolder.hasColumn(s);
    }

    protected boolean hasNull(String s) {
        return this.mDataHolder.hasNull(s, this.mDataRow, this.zaa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.mDataRow, this.zaa, this.mDataHolder});
    }

    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    protected Uri parseUri(String s) {
        String s1 = this.mDataHolder.getString(s, this.mDataRow, this.zaa);
        return s1 == null ? null : Uri.parse(s1);
    }

    protected final void zaa(int v) {
        Preconditions.checkState(v >= 0 && v < this.mDataHolder.getCount());
        this.mDataRow = v;
        this.zaa = this.mDataHolder.getWindowIndex(v);
    }
}

