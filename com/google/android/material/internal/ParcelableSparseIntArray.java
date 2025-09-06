package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.SparseIntArray;

public class ParcelableSparseIntArray extends SparseIntArray implements Parcelable {
    public static final Parcelable.Creator CREATOR;

    static {
        ParcelableSparseIntArray.CREATOR = new Parcelable.Creator() {
            public ParcelableSparseIntArray createFromParcel(Parcel parcel0) {
                int v = parcel0.readInt();
                ParcelableSparseIntArray parcelableSparseIntArray0 = new ParcelableSparseIntArray(v);
                int[] arr_v = new int[v];
                int[] arr_v1 = new int[v];
                parcel0.readIntArray(arr_v);
                parcel0.readIntArray(arr_v1);
                for(int v1 = 0; v1 < v; ++v1) {
                    parcelableSparseIntArray0.put(arr_v[v1], arr_v1[v1]);
                }
                return parcelableSparseIntArray0;
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public ParcelableSparseIntArray[] newArray(int v) {
                return new ParcelableSparseIntArray[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public ParcelableSparseIntArray() {
    }

    public ParcelableSparseIntArray(int v) {
        super(v);
    }

    public ParcelableSparseIntArray(SparseIntArray sparseIntArray0) {
        for(int v = 0; v < sparseIntArray0.size(); ++v) {
            this.put(sparseIntArray0.keyAt(v), sparseIntArray0.valueAt(v));
        }
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int[] arr_v = new int[this.size()];
        int[] arr_v1 = new int[this.size()];
        for(int v1 = 0; v1 < this.size(); ++v1) {
            arr_v[v1] = this.keyAt(v1);
            arr_v1[v1] = this.valueAt(v1);
        }
        parcel0.writeInt(this.size());
        parcel0.writeIntArray(arr_v);
        parcel0.writeIntArray(arr_v1);
    }
}

