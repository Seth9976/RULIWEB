package com.google.android.gms.common.data;

import android.content.ContentValues;
import java.util.HashMap;

final class zab extends Builder {
    zab(String[] arr_s, String s) {
        super(arr_s, null, null);
    }

    @Override  // com.google.android.gms.common.data.DataHolder$Builder
    public final Builder withRow(ContentValues contentValues0) {
        throw new UnsupportedOperationException("Cannot add data to empty builder");
    }

    @Override  // com.google.android.gms.common.data.DataHolder$Builder
    public final Builder zaa(HashMap hashMap0) {
        throw new UnsupportedOperationException("Cannot add data to empty builder");
    }
}

