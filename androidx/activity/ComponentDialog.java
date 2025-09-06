package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0019\b\u0007\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\u001A\u0010\u001F\u001A\u00020 2\u0006\u0010!\u001A\u00020\"2\b\u0010#\u001A\u0004\u0018\u00010$H\u0016J\b\u0010%\u001A\u00020 H\u0017J\b\u0010&\u001A\u00020 H\u0017J\u0012\u0010\'\u001A\u00020 2\b\u0010(\u001A\u0004\u0018\u00010)H\u0015J\b\u0010*\u001A\u00020)H\u0016J\b\u0010+\u001A\u00020 H\u0015J\b\u0010,\u001A\u00020 H\u0015J\u0010\u0010-\u001A\u00020 2\u0006\u0010!\u001A\u00020\"H\u0016J\u001A\u0010-\u001A\u00020 2\u0006\u0010!\u001A\u00020\"2\b\u0010#\u001A\u0004\u0018\u00010$H\u0016J\u0010\u0010-\u001A\u00020 2\u0006\u0010.\u001A\u00020\bH\u0016R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u0014\u0010\u0010\u001A\u00020\u000B8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001A\u00020\u0014¢\u0006\u000E\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001A\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001A\u00020\u001A8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001B\u0010\u001CR\u000E\u0010\u001D\u001A\u00020\u001EX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/activity/ComponentDialog;", "Landroid/app/Dialog;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/activity/OnBackPressedDispatcherOwner;", "Landroidx/savedstate/SavedStateRegistryOwner;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "_lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "lifecycleRegistry", "getLifecycleRegistry", "()Landroidx/lifecycle/LifecycleRegistry;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "getOnBackPressedDispatcher$annotations", "()V", "getOnBackPressedDispatcher", "()Landroidx/activity/OnBackPressedDispatcher;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistryController", "Landroidx/savedstate/SavedStateRegistryController;", "addContentView", "", "view", "Landroid/view/View;", "params", "Landroid/view/ViewGroup$LayoutParams;", "initializeViewTreeOwners", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "onStart", "onStop", "setContentView", "layoutResID", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ComponentDialog extends Dialog implements OnBackPressedDispatcherOwner, LifecycleOwner, SavedStateRegistryOwner {
    private LifecycleRegistry _lifecycleRegistry;
    private final OnBackPressedDispatcher onBackPressedDispatcher;
    private final SavedStateRegistryController savedStateRegistryController;

    public ComponentDialog(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        this(context0, 0, 2, null);
    }

    public ComponentDialog(Context context0, int v) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0, v);
        this.savedStateRegistryController = SavedStateRegistryController.Companion.create(this);
        this.onBackPressedDispatcher = new OnBackPressedDispatcher(() -> this.super.onBackPressed());
    }

    public ComponentDialog(Context context0, int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            v = 0;
        }
        this(context0, v);
    }

    @Override  // android.app.Dialog
    public void addContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.initializeViewTreeOwners();
        super.addContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.getLifecycleRegistry();
    }

    private final LifecycleRegistry getLifecycleRegistry() {
        LifecycleRegistry lifecycleRegistry0 = this._lifecycleRegistry;
        if(lifecycleRegistry0 == null) {
            lifecycleRegistry0 = new LifecycleRegistry(this);
            this._lifecycleRegistry = lifecycleRegistry0;
        }
        return lifecycleRegistry0;
    }

    @Override  // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.onBackPressedDispatcher;
    }

    public static void getOnBackPressedDispatcher$annotations() {
    }

    @Override  // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.getSavedStateRegistry();
    }

    public void initializeViewTreeOwners() {
        Window window0 = this.getWindow();
        Intrinsics.checkNotNull(window0);
        View view0 = window0.getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window!!.decorView");
        ViewTreeLifecycleOwner.set(view0, this);
        Window window1 = this.getWindow();
        Intrinsics.checkNotNull(window1);
        View view1 = window1.getDecorView();
        Intrinsics.checkNotNullExpressionValue(view1, "window!!.decorView");
        ViewTreeOnBackPressedDispatcherOwner.set(view1, this);
        Window window2 = this.getWindow();
        Intrinsics.checkNotNull(window2);
        View view2 = window2.getDecorView();
        Intrinsics.checkNotNullExpressionValue(view2, "window!!.decorView");
        ViewTreeSavedStateRegistryOwner.set(view2, this);
    }

    @Override  // android.app.Dialog
    public void onBackPressed() {
        this.onBackPressedDispatcher.onBackPressed();
    }

    // 检测为 Lambda 实现
    private static final void onBackPressedDispatcher$lambda$1(ComponentDialog componentDialog0) [...]

    @Override  // android.app.Dialog
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher0 = this.getOnBackInvokedDispatcher();
            Intrinsics.checkNotNullExpressionValue(onBackInvokedDispatcher0, "onBackInvokedDispatcher");
            this.onBackPressedDispatcher.setOnBackInvokedDispatcher(onBackInvokedDispatcher0);
        }
        this.savedStateRegistryController.performRestore(bundle0);
        this.getLifecycleRegistry().handleLifecycleEvent(Event.ON_CREATE);
    }

    @Override  // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle bundle0 = super.onSaveInstanceState();
        Intrinsics.checkNotNullExpressionValue(bundle0, "super.onSaveInstanceState()");
        this.savedStateRegistryController.performSave(bundle0);
        return bundle0;
    }

    @Override  // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.getLifecycleRegistry().handleLifecycleEvent(Event.ON_RESUME);
    }

    @Override  // android.app.Dialog
    protected void onStop() {
        this.getLifecycleRegistry().handleLifecycleEvent(Event.ON_DESTROY);
        this._lifecycleRegistry = null;
        super.onStop();
    }

    @Override  // android.app.Dialog
    public void setContentView(int v) {
        this.initializeViewTreeOwners();
        super.setContentView(v);
    }

    @Override  // android.app.Dialog
    public void setContentView(View view0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.initializeViewTreeOwners();
        super.setContentView(view0);
    }

    @Override  // android.app.Dialog
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.initializeViewTreeOwners();
        super.setContentView(view0, viewGroup$LayoutParams0);
    }
}

