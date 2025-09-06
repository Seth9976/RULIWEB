package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DropDownPreference extends ListPreference {
    private final ArrayAdapter mAdapter;
    private final Context mContext;
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    private Spinner mSpinner;

    public DropDownPreference(Context context0) {
        this(context0, null);
    }

    public DropDownPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.dropdownPreferenceStyle);
    }

    public DropDownPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public DropDownPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override  // android.widget.AdapterView$OnItemSelectedListener
            public void onItemSelected(AdapterView adapterView0, View view0, int v, long v1) {
                if(v >= 0) {
                    String s = DropDownPreference.this.getEntryValues()[v].toString();
                    if(!s.equals(DropDownPreference.this.getValue()) && DropDownPreference.this.callChangeListener(s)) {
                        DropDownPreference.this.setValue(s);
                    }
                }
            }

            @Override  // android.widget.AdapterView$OnItemSelectedListener
            public void onNothingSelected(AdapterView adapterView0) {
            }
        };
        this.mContext = context0;
        this.mAdapter = this.createAdapter();
        this.updateEntries();
    }

    protected ArrayAdapter createAdapter() {
        return new ArrayAdapter(this.mContext, 0x1090009);
    }

    private int findSpinnerIndexOfValue(String s) {
        CharSequence[] arr_charSequence = this.getEntryValues();
        if(s != null && arr_charSequence != null) {
            for(int v = arr_charSequence.length - 1; v >= 0; --v) {
                if(TextUtils.equals(arr_charSequence[v].toString(), s)) {
                    return v;
                }
            }
        }
        return -1;
    }

    @Override  // androidx.preference.Preference
    protected void notifyChanged() {
        super.notifyChanged();
        ArrayAdapter arrayAdapter0 = this.mAdapter;
        if(arrayAdapter0 != null) {
            arrayAdapter0.notifyDataSetChanged();
        }
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        Spinner spinner0 = (Spinner)preferenceViewHolder0.itemView.findViewById(id.spinner);
        this.mSpinner = spinner0;
        spinner0.setAdapter(this.mAdapter);
        this.mSpinner.setOnItemSelectedListener(this.mItemSelectedListener);
        this.mSpinner.setSelection(this.findSpinnerIndexOfValue(this.getValue()));
        super.onBindViewHolder(preferenceViewHolder0);
    }

    @Override  // androidx.preference.DialogPreference
    protected void onClick() {
        this.mSpinner.performClick();
    }

    @Override  // androidx.preference.ListPreference
    public void setEntries(CharSequence[] arr_charSequence) {
        super.setEntries(arr_charSequence);
        this.updateEntries();
    }

    @Override  // androidx.preference.ListPreference
    public void setValueIndex(int v) {
        this.setValue(this.getEntryValues()[v].toString());
    }

    private void updateEntries() {
        this.mAdapter.clear();
        if(this.getEntries() != null) {
            CharSequence[] arr_charSequence = this.getEntries();
            for(int v = 0; v < arr_charSequence.length; ++v) {
                this.mAdapter.add(arr_charSequence[v].toString());
            }
        }
    }
}

