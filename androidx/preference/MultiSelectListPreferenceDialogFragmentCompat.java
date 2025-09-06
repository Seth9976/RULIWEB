package androidx.preference;

import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog.Builder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    private static final String SAVE_STATE_CHANGED = "MultiSelectListPreferenceDialogFragmentCompat.changed";
    private static final String SAVE_STATE_ENTRIES = "MultiSelectListPreferenceDialogFragmentCompat.entries";
    private static final String SAVE_STATE_ENTRY_VALUES = "MultiSelectListPreferenceDialogFragmentCompat.entryValues";
    private static final String SAVE_STATE_VALUES = "MultiSelectListPreferenceDialogFragmentCompat.values";
    CharSequence[] mEntries;
    CharSequence[] mEntryValues;
    Set mNewValues;
    boolean mPreferenceChanged;

    public MultiSelectListPreferenceDialogFragmentCompat() {
        this.mNewValues = new HashSet();
    }

    private MultiSelectListPreference getListPreference() {
        return (MultiSelectListPreference)this.getPreference();
    }

    public static MultiSelectListPreferenceDialogFragmentCompat newInstance(String s) {
        MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat0 = new MultiSelectListPreferenceDialogFragmentCompat();
        Bundle bundle0 = new Bundle(1);
        bundle0.putString("key", s);
        multiSelectListPreferenceDialogFragmentCompat0.setArguments(bundle0);
        return multiSelectListPreferenceDialogFragmentCompat0;
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
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
        this.mNewValues.addAll(bundle0.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
        this.mPreferenceChanged = bundle0.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
        this.mEntries = bundle0.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
        this.mEntryValues = bundle0.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    public void onDialogClosed(boolean z) {
        if(z && this.mPreferenceChanged) {
            MultiSelectListPreference multiSelectListPreference0 = this.getListPreference();
            if(multiSelectListPreference0.callChangeListener(this.mNewValues)) {
                multiSelectListPreference0.setValues(this.mNewValues);
            }
        }
        this.mPreferenceChanged = false;
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    protected void onPrepareDialogBuilder(Builder alertDialog$Builder0) {
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
                    boolean z1 = MultiSelectListPreferenceDialogFragmentCompat.this.mPreferenceChanged;
                    MultiSelectListPreferenceDialogFragmentCompat.this.mPreferenceChanged = MultiSelectListPreferenceDialogFragmentCompat.this.mNewValues.add(MultiSelectListPreferenceDialogFragmentCompat.this.mEntryValues[v].toString()) | z1;
                    return;
                }
                boolean z2 = MultiSelectListPreferenceDialogFragmentCompat.this.mPreferenceChanged;
                MultiSelectListPreferenceDialogFragmentCompat.this.mPreferenceChanged = MultiSelectListPreferenceDialogFragmentCompat.this.mNewValues.remove(MultiSelectListPreferenceDialogFragmentCompat.this.mEntryValues[v].toString()) | z2;
            }
        });
    }

    @Override  // androidx.preference.PreferenceDialogFragmentCompat
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList(this.mNewValues));
        bundle0.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.mPreferenceChanged);
        bundle0.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.mEntries);
        bundle0.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.mEntryValues);
    }
}

