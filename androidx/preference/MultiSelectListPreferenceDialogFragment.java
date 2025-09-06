package androidx.preference;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {
    private static final String SAVE_STATE_CHANGED = "MultiSelectListPreferenceDialogFragment.changed";
    private static final String SAVE_STATE_ENTRIES = "MultiSelectListPreferenceDialogFragment.entries";
    private static final String SAVE_STATE_ENTRY_VALUES = "MultiSelectListPreferenceDialogFragment.entryValues";
    private static final String SAVE_STATE_VALUES = "MultiSelectListPreferenceDialogFragment.values";
    CharSequence[] mEntries;
    CharSequence[] mEntryValues;
    Set mNewValues;
    boolean mPreferenceChanged;

    @Deprecated
    public MultiSelectListPreferenceDialogFragment() {
        this.mNewValues = new HashSet();
    }

    private MultiSelectListPreference getListPreference() {
        return (MultiSelectListPreference)this.getPreference();
    }

    @Deprecated
    public static MultiSelectListPreferenceDialogFragment newInstance(String s) {
        MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment0 = new MultiSelectListPreferenceDialogFragment();
        Bundle bundle0 = new Bundle(1);
        bundle0.putString("key", s);
        multiSelectListPreferenceDialogFragment0.setArguments(bundle0);
        return multiSelectListPreferenceDialogFragment0;
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            MultiSelectListPreference multiSelectListPreference0 = this.getListPreference();
            if(multiSelectListPreference0.getEntries() == null || multiSelectListPreference0.getEntryValues() == null) {
                throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
            }
            this.mNewValues.clear();
            this.mNewValues.addAll(multiSelectListPreference0.getValues());
            this.mPreferenceChanged = false;
            this.mEntries = multiSelectListPreference0.getEntries();
            this.mEntryValues = multiSelectListPreference0.getEntryValues();
            return;
        }
        this.mNewValues.clear();
        this.mNewValues.addAll(bundle0.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
        this.mPreferenceChanged = bundle0.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
        this.mEntries = bundle0.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
        this.mEntryValues = bundle0.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public void onDialogClosed(boolean z) {
        MultiSelectListPreference multiSelectListPreference0 = this.getListPreference();
        if(z && this.mPreferenceChanged) {
            Set set0 = this.mNewValues;
            if(multiSelectListPreference0.callChangeListener(set0)) {
                multiSelectListPreference0.setValues(set0);
            }
        }
        this.mPreferenceChanged = false;
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    protected void onPrepareDialogBuilder(AlertDialog.Builder alertDialog$Builder0) {
        super.onPrepareDialogBuilder(alertDialog$Builder0);
        int v = this.mEntryValues.length;
        boolean[] arr_z = new boolean[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_z[v1] = this.mNewValues.contains(this.mEntryValues[v1].toString());
        }
        alertDialog$Builder0.setMultiChoiceItems(this.mEntries, arr_z, new DialogInterface.OnMultiChoiceClickListener() {
            @Override  // android.content.DialogInterface$OnMultiChoiceClickListener
            public void onClick(DialogInterface dialogInterface0, int v, boolean z) {
                if(z) {
                    boolean z1 = MultiSelectListPreferenceDialogFragment.this.mPreferenceChanged;
                    MultiSelectListPreferenceDialogFragment.this.mPreferenceChanged = MultiSelectListPreferenceDialogFragment.this.mNewValues.add(MultiSelectListPreferenceDialogFragment.this.mEntryValues[v].toString()) | z1;
                    return;
                }
                boolean z2 = MultiSelectListPreferenceDialogFragment.this.mPreferenceChanged;
                MultiSelectListPreferenceDialogFragment.this.mPreferenceChanged = MultiSelectListPreferenceDialogFragment.this.mNewValues.remove(MultiSelectListPreferenceDialogFragment.this.mEntryValues[v].toString()) | z2;
            }
        });
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList(this.mNewValues));
        bundle0.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.mPreferenceChanged);
        bundle0.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.mEntries);
        bundle0.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.mEntryValues);
    }
}

