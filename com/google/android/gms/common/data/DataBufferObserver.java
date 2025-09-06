package com.google.android.gms.common.data;

public interface DataBufferObserver {
    public interface Observable {
        void addObserver(DataBufferObserver arg1);

        void removeObserver(DataBufferObserver arg1);
    }

    void onDataChanged();

    void onDataRangeChanged(int arg1, int arg2);

    void onDataRangeInserted(int arg1, int arg2);

    void onDataRangeMoved(int arg1, int arg2, int arg3);

    void onDataRangeRemoved(int arg1, int arg2);
}

