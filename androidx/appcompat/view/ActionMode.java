package androidx.appcompat.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class ActionMode {
    public interface Callback {
        boolean onActionItemClicked(ActionMode arg1, MenuItem arg2);

        boolean onCreateActionMode(ActionMode arg1, Menu arg2);

        void onDestroyActionMode(ActionMode arg1);

        boolean onPrepareActionMode(ActionMode arg1, Menu arg2);
    }

    private Object mTag;
    private boolean mTitleOptionalHint;

    public abstract void finish();

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag() {
        return this.mTag;
    }

    public abstract CharSequence getTitle();

    public boolean getTitleOptionalHint() {
        return this.mTitleOptionalHint;
    }

    public abstract void invalidate();

    public boolean isTitleOptional() {
        return false;
    }

    public boolean isUiFocusable() {
        return true;
    }

    public abstract void setCustomView(View arg1);

    public abstract void setSubtitle(int arg1);

    public abstract void setSubtitle(CharSequence arg1);

    public void setTag(Object object0) {
        this.mTag = object0;
    }

    public abstract void setTitle(int arg1);

    public abstract void setTitle(CharSequence arg1);

    public void setTitleOptionalHint(boolean z) {
        this.mTitleOptionalHint = z;
    }
}

