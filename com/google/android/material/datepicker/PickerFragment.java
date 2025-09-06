package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

abstract class PickerFragment extends Fragment {
    protected final LinkedHashSet onSelectionChangedListeners;

    PickerFragment() {
        this.onSelectionChangedListeners = new LinkedHashSet();
    }

    boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener0) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener0);
    }

    void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }

    abstract DateSelector getDateSelector();

    boolean removeOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener0) {
        return this.onSelectionChangedListeners.remove(onSelectionChangedListener0);
    }
}

