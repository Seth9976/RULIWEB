package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataBufferSafeParcelable extends AbstractDataBuffer {
    private static final String[] zaa;
    private final Parcelable.Creator zab;

    static {
        DataBufferSafeParcelable.zaa = new String[]{"data"};
    }

    public DataBufferSafeParcelable(DataHolder dataHolder0, Parcelable.Creator parcelable$Creator0) {
        super(dataHolder0);
        this.zab = parcelable$Creator0;
    }

    public static void addValue(Builder dataHolder$Builder0, SafeParcelable safeParcelable0) {
        Parcel parcel0 = Parcel.obtain();
        safeParcelable0.writeToParcel(parcel0, 0);
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("data", parcel0.marshall());
        dataHolder$Builder0.withRow(contentValues0);
        parcel0.recycle();
    }

    public static Builder buildDataHolder() {
        return DataHolder.builder(DataBufferSafeParcelable.zaa);
    }

    public SafeParcelable get(int v) {
        DataHolder dataHolder0 = (DataHolder)Preconditions.checkNotNull(this.mDataHolder);
        byte[] arr_b = dataHolder0.getByteArray("data", v, dataHolder0.getWindowIndex(v));
        Parcel parcel0 = Parcel.obtain();
        parcel0.unmarshall(arr_b, 0, arr_b.length);
        parcel0.setDataPosition(0);
        SafeParcelable safeParcelable0 = (SafeParcelable)this.zab.createFromParcel(parcel0);
        parcel0.recycle();
        return safeParcelable0;
    }

    @Override  // com.google.android.gms.common.data.AbstractDataBuffer
    public final Object get(int v) {
        return this.get(v);
    }
}

