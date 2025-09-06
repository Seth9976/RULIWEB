package androidx.transition;

import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;

class TransitionValuesMaps {
    final SparseArray mIdValues;
    final LongSparseArray mItemIdValues;
    final ArrayMap mNameValues;
    final ArrayMap mViewValues;

    TransitionValuesMaps() {
        this.mViewValues = new ArrayMap();
        this.mIdValues = new SparseArray();
        this.mItemIdValues = new LongSparseArray();
        this.mNameValues = new ArrayMap();
    }
}

