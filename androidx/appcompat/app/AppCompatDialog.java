package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import androidx.activity.ComponentDialog;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.appcompat.R.attr;
import androidx.appcompat.view.ActionMode.Callback;
import androidx.appcompat.view.ActionMode;
import androidx.core.view.KeyEventDispatcher.Component;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

public class AppCompatDialog extends ComponentDialog implements AppCompatCallback {
    private AppCompatDelegate mDelegate;
    private final Component mKeyDispatcher;

    public AppCompatDialog(Context context0) {
        this(context0, 0);
    }

    public AppCompatDialog(Context context0, int v) {
        super(context0, AppCompatDialog.getThemeResId(context0, v));
        this.mKeyDispatcher = (KeyEvent keyEvent0) -> super.dispatchKeyEvent(keyEvent0);
        AppCompatDelegate appCompatDelegate0 = this.getDelegate();
        appCompatDelegate0.setTheme(AppCompatDialog.getThemeResId(context0, v));
        appCompatDelegate0.onCreate(null);
    }

    protected AppCompatDialog(Context context0, boolean z, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        super(context0);
        this.mKeyDispatcher = (KeyEvent keyEvent0) -> super.dispatchKeyEvent(keyEvent0);
        this.setCancelable(z);
        this.setOnCancelListener(dialogInterface$OnCancelListener0);
    }

    @Override  // androidx.activity.ComponentDialog
    public void addContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.getDelegate().addContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // android.app.Dialog
    public void dismiss() {
        super.dismiss();
        this.getDelegate().onDestroy();
    }

    @Override  // android.app.Dialog
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        View view0 = this.getWindow().getDecorView();
        return KeyEventDispatcher.dispatchKeyEvent(this.mKeyDispatcher, view0, this, keyEvent0);
    }

    @Override  // android.app.Dialog
    public View findViewById(int v) {
        return this.getDelegate().findViewById(v);
    }

    public AppCompatDelegate getDelegate() {
        if(this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, this);
        }
        return this.mDelegate;
    }

    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }

    private static int getThemeResId(Context context0, int v) {
        if(v == 0) {
            TypedValue typedValue0 = new TypedValue();
            context0.getTheme().resolveAttribute(attr.dialogTheme, typedValue0, true);
            return typedValue0.resourceId;
        }
        return v;
    }

    private void initViewTreeOwners() {
        ViewTreeLifecycleOwner.set(this.getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.set(this.getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.set(this.getWindow().getDecorView(), this);
    }

    @Override  // android.app.Dialog
    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }

    @Override  // androidx.activity.ComponentDialog
    protected void onCreate(Bundle bundle0) {
        this.getDelegate().installViewFactory();
        super.onCreate(bundle0);
        this.getDelegate().onCreate(bundle0);
    }

    @Override  // androidx.activity.ComponentDialog
    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }

    @Override  // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeFinished(ActionMode actionMode0) {
    }

    @Override  // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeStarted(ActionMode actionMode0) {
    }

    @Override  // androidx.appcompat.app.AppCompatCallback
    public ActionMode onWindowStartingSupportActionMode(Callback actionMode$Callback0) {
        return null;
    }

    @Override  // androidx.activity.ComponentDialog
    public void setContentView(int v) {
        this.initViewTreeOwners();
        this.getDelegate().setContentView(v);
    }

    @Override  // androidx.activity.ComponentDialog
    public void setContentView(View view0) {
        this.initViewTreeOwners();
        this.getDelegate().setContentView(view0);
    }

    @Override  // androidx.activity.ComponentDialog
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.initViewTreeOwners();
        this.getDelegate().setContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // android.app.Dialog
    public void setTitle(int v) {
        super.setTitle(v);
        this.getDelegate().setTitle(this.getContext().getString(v));
    }

    @Override  // android.app.Dialog
    public void setTitle(CharSequence charSequence0) {
        super.setTitle(charSequence0);
        this.getDelegate().setTitle(charSequence0);
    }

    // 检测为 Lambda 实现
    boolean superDispatchKeyEvent(KeyEvent keyEvent0) [...]

    public boolean supportRequestWindowFeature(int v) {
        return this.getDelegate().requestWindowFeature(v);
    }
}

