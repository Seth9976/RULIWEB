package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;

public final class PreferenceScreen extends PreferenceGroup {
    private boolean mShouldUseGeneratedIds;

    public PreferenceScreen(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.preferenceScreenStyle, 0x101008B));
        this.mShouldUseGeneratedIds = true;
    }

    @Override  // androidx.preference.PreferenceGroup
    protected boolean isOnSameScreenAsChildren() {
        return false;
    }

    @Override  // androidx.preference.Preference
    protected void onClick() {
        if(this.getIntent() == null && this.getFragment() == null && this.getPreferenceCount() != 0) {
            OnNavigateToScreenListener preferenceManager$OnNavigateToScreenListener0 = this.getPreferenceManager().getOnNavigateToScreenListener();
            if(preferenceManager$OnNavigateToScreenListener0 != null) {
                preferenceManager$OnNavigateToScreenListener0.onNavigateToScreen(this);
            }
        }
    }

    public void setShouldUseGeneratedIds(boolean z) {
        if(this.isAttached()) {
            throw new IllegalStateException("Cannot change the usage of generated IDs while attached to the preference hierarchy");
        }
        this.mShouldUseGeneratedIds = z;
    }

    public boolean shouldUseGeneratedIds() {
        return this.mShouldUseGeneratedIds;
    }
}

