package androidx.appcompat.view;

import android.view.ActionMode.Callback;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class WindowCallbackWrapper implements Window.Callback {
    static class Api23Impl {
        static boolean onSearchRequested(Window.Callback window$Callback0, SearchEvent searchEvent0) {
            return window$Callback0.onSearchRequested(searchEvent0);
        }

        static ActionMode onWindowStartingActionMode(Window.Callback window$Callback0, ActionMode.Callback actionMode$Callback0, int v) {
            return window$Callback0.onWindowStartingActionMode(actionMode$Callback0, v);
        }
    }

    static class Api24Impl {
        static void onProvideKeyboardShortcuts(Window.Callback window$Callback0, List list0, Menu menu0, int v) {
            window$Callback0.onProvideKeyboardShortcuts(list0, menu0, v);
        }
    }

    static class Api26Impl {
        static void onPointerCaptureChanged(Window.Callback window$Callback0, boolean z) {
            window$Callback0.onPointerCaptureChanged(z);
        }
    }

    final Window.Callback mWrapped;

    public WindowCallbackWrapper(Window.Callback window$Callback0) {
        if(window$Callback0 == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.mWrapped = window$Callback0;
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent0) {
        return this.mWrapped.dispatchGenericMotionEvent(motionEvent0);
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        return this.mWrapped.dispatchKeyEvent(keyEvent0);
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent0) {
        return this.mWrapped.dispatchKeyShortcutEvent(keyEvent0);
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        return this.mWrapped.dispatchPopulateAccessibilityEvent(accessibilityEvent0);
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent0) {
        return this.mWrapped.dispatchTouchEvent(motionEvent0);
    }

    @Override  // android.view.Window$Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent0) {
        return this.mWrapped.dispatchTrackballEvent(motionEvent0);
    }

    public final Window.Callback getWrapped() {
        return this.mWrapped;
    }

    @Override  // android.view.Window$Callback
    public void onActionModeFinished(ActionMode actionMode0) {
        this.mWrapped.onActionModeFinished(actionMode0);
    }

    @Override  // android.view.Window$Callback
    public void onActionModeStarted(ActionMode actionMode0) {
        this.mWrapped.onActionModeStarted(actionMode0);
    }

    @Override  // android.view.Window$Callback
    public void onAttachedToWindow() {
        this.mWrapped.onAttachedToWindow();
    }

    @Override  // android.view.Window$Callback
    public void onContentChanged() {
        this.mWrapped.onContentChanged();
    }

    @Override  // android.view.Window$Callback
    public boolean onCreatePanelMenu(int v, Menu menu0) {
        return this.mWrapped.onCreatePanelMenu(v, menu0);
    }

    @Override  // android.view.Window$Callback
    public View onCreatePanelView(int v) {
        return this.mWrapped.onCreatePanelView(v);
    }

    @Override  // android.view.Window$Callback
    public void onDetachedFromWindow() {
        this.mWrapped.onDetachedFromWindow();
    }

    @Override  // android.view.Window$Callback
    public boolean onMenuItemSelected(int v, MenuItem menuItem0) {
        return this.mWrapped.onMenuItemSelected(v, menuItem0);
    }

    @Override  // android.view.Window$Callback
    public boolean onMenuOpened(int v, Menu menu0) {
        return this.mWrapped.onMenuOpened(v, menu0);
    }

    @Override  // android.view.Window$Callback
    public void onPanelClosed(int v, Menu menu0) {
        this.mWrapped.onPanelClosed(v, menu0);
    }

    @Override  // android.view.Window$Callback
    public void onPointerCaptureChanged(boolean z) {
        Api26Impl.onPointerCaptureChanged(this.mWrapped, z);
    }

    @Override  // android.view.Window$Callback
    public boolean onPreparePanel(int v, View view0, Menu menu0) {
        return this.mWrapped.onPreparePanel(v, view0, menu0);
    }

    @Override  // android.view.Window$Callback
    public void onProvideKeyboardShortcuts(List list0, Menu menu0, int v) {
        Api24Impl.onProvideKeyboardShortcuts(this.mWrapped, list0, menu0, v);
    }

    @Override  // android.view.Window$Callback
    public boolean onSearchRequested() {
        return this.mWrapped.onSearchRequested();
    }

    @Override  // android.view.Window$Callback
    public boolean onSearchRequested(SearchEvent searchEvent0) {
        return Api23Impl.onSearchRequested(this.mWrapped, searchEvent0);
    }

    @Override  // android.view.Window$Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams windowManager$LayoutParams0) {
        this.mWrapped.onWindowAttributesChanged(windowManager$LayoutParams0);
    }

    @Override  // android.view.Window$Callback
    public void onWindowFocusChanged(boolean z) {
        this.mWrapped.onWindowFocusChanged(z);
    }

    @Override  // android.view.Window$Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback actionMode$Callback0) {
        return this.mWrapped.onWindowStartingActionMode(actionMode$Callback0);
    }

    @Override  // android.view.Window$Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback actionMode$Callback0, int v) {
        return Api23Impl.onWindowStartingActionMode(this.mWrapped, actionMode$Callback0, v);
    }
}

