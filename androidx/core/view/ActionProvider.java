package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class ActionProvider {
    public interface SubUiVisibilityListener {
        void onSubUiVisibilityChanged(boolean arg1);
    }

    public interface VisibilityListener {
        void onActionProviderVisibilityChanged(boolean arg1);
    }

    private static final String TAG = "ActionProvider(support)";
    private final Context mContext;
    private SubUiVisibilityListener mSubUiVisibilityListener;
    private VisibilityListener mVisibilityListener;

    public ActionProvider(Context context0) {
        this.mContext = context0;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem menuItem0) {
        return this.onCreateActionView();
    }

    public boolean onPerformDefaultAction() {
        return false;
    }

    public void onPrepareSubMenu(SubMenu subMenu0) {
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void refreshVisibility() {
        if(this.mVisibilityListener != null && this.overridesItemVisibility()) {
            this.mVisibilityListener.onActionProviderVisibilityChanged(this.isVisible());
        }
    }

    public void reset() {
        this.mVisibilityListener = null;
        this.mSubUiVisibilityListener = null;
    }

    public void setSubUiVisibilityListener(SubUiVisibilityListener actionProvider$SubUiVisibilityListener0) {
        this.mSubUiVisibilityListener = actionProvider$SubUiVisibilityListener0;
    }

    public void setVisibilityListener(VisibilityListener actionProvider$VisibilityListener0) {
        if(this.mVisibilityListener != null && actionProvider$VisibilityListener0 != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + this.getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.mVisibilityListener = actionProvider$VisibilityListener0;
    }

    public void subUiVisibilityChanged(boolean z) {
        SubUiVisibilityListener actionProvider$SubUiVisibilityListener0 = this.mSubUiVisibilityListener;
        if(actionProvider$SubUiVisibilityListener0 != null) {
            actionProvider$SubUiVisibilityListener0.onSubUiVisibilityChanged(z);
        }
    }
}

