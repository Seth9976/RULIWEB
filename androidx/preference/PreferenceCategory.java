package androidx.preference;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;

public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context0) {
        this(context0, null);
    }

    public PreferenceCategory(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.preferenceCategoryStyle, 0x101008C));
    }

    public PreferenceCategory(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public PreferenceCategory(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    @Override  // androidx.preference.Preference
    public boolean isEnabled() {
        return false;
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        super.onBindViewHolder(preferenceViewHolder0);
        if(Build.VERSION.SDK_INT >= 28) {
            PrintHelper..ExternalSyntheticApiModelOutline0.m(preferenceViewHolder0.itemView, true);
        }
    }

    @Override  // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return !super.isEnabled();
    }
}

