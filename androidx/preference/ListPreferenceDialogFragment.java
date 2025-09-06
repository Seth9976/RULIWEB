package androidx.preference;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.os.Bundle;

@Deprecated
public class ListPreferenceDialogFragment extends PreferenceDialogFragment {
    private static final String SAVE_STATE_ENTRIES = "ListPreferenceDialogFragment.entries";
    private static final String SAVE_STATE_ENTRY_VALUES = "ListPreferenceDialogFragment.entryValues";
    private static final String SAVE_STATE_INDEX = "ListPreferenceDialogFragment.index";
    int mClickedDialogEntryIndex;
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;

    private ListPreference getListPreference() {
        return (ListPreference)this.getPreference();
    }

    @Deprecated
    public static ListPreferenceDialogFragment newInstance(String s) {
        ListPreferenceDialogFragment listPreferenceDialogFragment0 = new ListPreferenceDialogFragment();
        Bundle bundle0 = new Bundle(1);
        bundle0.putString("key", s);
        listPreferenceDialogFragment0.setArguments(bundle0);
        return listPreferenceDialogFragment0;
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            ListPreference listPreference0 = this.getListPreference();
            if(listPreference0.getEntries() == null || listPreference0.getEntryValues() == null) {
                throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
            }
            this.mClickedDialogEntryIndex = listPreference0.findIndexOfValue(listPreference0.getValue());
            this.mEntries = listPreference0.getEntries();
            this.mEntryValues = listPreference0.getEntryValues();
            return;
        }
        this.mClickedDialogEntryIndex = bundle0.getInt("ListPreferenceDialogFragment.index", 0);
        this.mEntries = bundle0.getCharSequenceArray("ListPreferenceDialogFragment.entries");
        this.mEntryValues = bundle0.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    @Deprecated
    public void onDialogClosed(boolean z) {
        ListPreference listPreference0 = this.getListPreference();
        if(z) {
            int v = this.mClickedDialogEntryIndex;
            if(v >= 0) {
                String s = this.mEntryValues[v].toString();
                if(listPreference0.callChangeListener(s)) {
                    listPreference0.setValue(s);
                }
            }
        }
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    protected void onPrepareDialogBuilder(AlertDialog.Builder alertDialog$Builder0) {
        super.onPrepareDialogBuilder(alertDialog$Builder0);
        alertDialog$Builder0.setSingleChoiceItems(this.mEntries, this.mClickedDialogEntryIndex, new DialogInterface.OnClickListener() {
            @Override  // android.content.DialogInterface$OnClickListener
            public void onClick(DialogInterface dialogInterface0, int v) {
                ListPreferenceDialogFragment.this.mClickedDialogEntryIndex = v;
                ListPreferenceDialogFragment.this.onClick(dialogInterface0, -1);
                dialogInterface0.dismiss();
            }
        });
        alertDialog$Builder0.setPositiveButton(null, null);
    }

    @Override  // androidx.preference.PreferenceDialogFragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putInt("ListPreferenceDialogFragment.index", this.mClickedDialogEntryIndex);
        bundle0.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.mEntries);
        bundle0.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.mEntryValues);
    }
}

