package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import androidx.core.content.res.TypedArrayUtils;

public class CheckBoxPreference extends TwoStatePreference {
    class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override  // android.widget.CompoundButton$OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton0, boolean z) {
            if(!CheckBoxPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton0.setChecked(!z);
                return;
            }
            CheckBoxPreference.this.setChecked(z);
        }
    }

    private final Listener mListener;

    public CheckBoxPreference(Context context0) {
        this(context0, null);
    }

    public CheckBoxPreference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.checkBoxPreferenceStyle, 0x101008F));
    }

    public CheckBoxPreference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public CheckBoxPreference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mListener = new Listener(this);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.CheckBoxPreference, v, v1);
        this.setSummaryOn(TypedArrayUtils.getString(typedArray0, styleable.CheckBoxPreference_summaryOn, styleable.CheckBoxPreference_android_summaryOn));
        this.setSummaryOff(TypedArrayUtils.getString(typedArray0, styleable.CheckBoxPreference_summaryOff, styleable.CheckBoxPreference_android_summaryOff));
        this.setDisableDependentsState(TypedArrayUtils.getBoolean(typedArray0, styleable.CheckBoxPreference_disableDependentsState, styleable.CheckBoxPreference_android_disableDependentsState, false));
        typedArray0.recycle();
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        super.onBindViewHolder(preferenceViewHolder0);
        this.syncCheckboxView(preferenceViewHolder0.findViewById(0x1020001));
        this.syncSummaryView(preferenceViewHolder0);
    }

    @Override  // androidx.preference.Preference
    protected void performClick(View view0) {
        super.performClick(view0);
        this.syncViewIfAccessibilityEnabled(view0);
    }

    private void syncCheckboxView(View view0) {
        if(view0 instanceof CompoundButton) {
            ((CompoundButton)view0).setOnCheckedChangeListener(null);
        }
        if(view0 instanceof Checkable) {
            ((Checkable)view0).setChecked(this.mChecked);
        }
        if(view0 instanceof CompoundButton) {
            ((CompoundButton)view0).setOnCheckedChangeListener(this.mListener);
        }
    }

    private void syncViewIfAccessibilityEnabled(View view0) {
        if(!((AccessibilityManager)this.getContext().getSystemService("accessibility")).isEnabled()) {
            return;
        }
        this.syncCheckboxView(view0.findViewById(0x1020001));
        this.syncSummaryView(view0.findViewById(0x1020010));
    }
}

