package androidx.core.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher.Component;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001)B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\u0010\u0010\u0015\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\'\u0010\u0016\u001A\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\b2\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00170\u0007H\u0017¢\u0006\u0002\u0010\u0019J\u0012\u0010\u001A\u001A\u00020\u001B2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DH\u0014J\u0010\u0010\u001E\u001A\u00020\u001B2\u0006\u0010\u001F\u001A\u00020\u001DH\u0015J\u0010\u0010 \u001A\u00020\u001B2\u0006\u0010!\u001A\u00020\bH\u0017J\u001D\u0010\"\u001A\u00020\u00122\u000E\u0010#\u001A\n\u0012\u0004\u0012\u00020%\u0018\u00010$H\u0004¢\u0006\u0002\u0010&J\u001D\u0010\'\u001A\u00020\u00122\u000E\u0010#\u001A\n\u0012\u0004\u0012\u00020%\u0018\u00010$H\u0002¢\u0006\u0002\u0010&J\u0010\u0010(\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0017R(\u0010\u0005\u001A\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0004R\u0014\u0010\n\u001A\u00020\u000B8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\rR\u0014\u0010\u000E\u001A\u00020\u000FX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0004¨\u0006*"}, d2 = {"Landroidx/core/app/ComponentActivity;", "Landroid/app/Activity;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/core/view/KeyEventDispatcher$Component;", "()V", "extraDataMap", "Landroidx/collection/SimpleArrayMap;", "Ljava/lang/Class;", "Landroidx/core/app/ComponentActivity$ExtraData;", "getExtraDataMap$annotations", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycleRegistry$annotations", "dispatchKeyEvent", "", "event", "Landroid/view/KeyEvent;", "dispatchKeyShortcutEvent", "getExtraData", "T", "extraDataClass", "(Ljava/lang/Class;)Landroidx/core/app/ComponentActivity$ExtraData;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "putExtraData", "extraData", "shouldDumpInternalState", "args", "", "", "([Ljava/lang/String;)Z", "shouldSkipDump", "superDispatchKeyEvent", "ExtraData", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ComponentActivity extends Activity implements Component, LifecycleOwner {
    @Deprecated(message = "Store the object you want to save directly by using\n      {@link View#setTag(int, Object)} with the window\'s decor view.")
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/core/app/ComponentActivity$ExtraData;", "", "()V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class ExtraData {
    }

    private final SimpleArrayMap extraDataMap;
    private final LifecycleRegistry lifecycleRegistry;

    public ComponentActivity() {
        this.extraDataMap = new SimpleArrayMap(0, 1, null);
        this.lifecycleRegistry = new LifecycleRegistry(this);
    }

    @Override  // android.app.Activity
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        Intrinsics.checkNotNullParameter(keyEvent0, "event");
        View view0 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
        return KeyEventDispatcher.dispatchBeforeHierarchy(view0, keyEvent0) ? true : KeyEventDispatcher.dispatchKeyEvent(this, view0, this, keyEvent0);
    }

    @Override  // android.app.Activity
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent0) {
        Intrinsics.checkNotNullParameter(keyEvent0, "event");
        View view0 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
        return KeyEventDispatcher.dispatchBeforeHierarchy(view0, keyEvent0) ? true : super.dispatchKeyShortcutEvent(keyEvent0);
    }

    @Deprecated(message = "Use {@link View#getTag(int)} with the window\'s decor view.")
    public ExtraData getExtraData(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "extraDataClass");
        return (ExtraData)this.extraDataMap.get(class0);
    }

    private static void getExtraDataMap$annotations() {
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    private static void getLifecycleRegistry$annotations() {
    }

    @Override  // android.app.Activity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        ReportFragment.Companion.injectIfNeededIn(this);
    }

    @Override  // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        this.lifecycleRegistry.setCurrentState(State.CREATED);
        super.onSaveInstanceState(bundle0);
    }

    @Deprecated(message = "Use {@link View#setTag(int, Object)} with the window\'s decor view.")
    public void putExtraData(ExtraData componentActivity$ExtraData0) {
        Intrinsics.checkNotNullParameter(componentActivity$ExtraData0, "extraData");
        Class class0 = componentActivity$ExtraData0.getClass();
        this.extraDataMap.put(class0, componentActivity$ExtraData0);
    }

    protected final boolean shouldDumpInternalState(String[] arr_s) {
        return !this.shouldSkipDump(arr_s);
    }

    private final boolean shouldSkipDump(String[] arr_s) {
        if(arr_s != null && arr_s.length != 0) {
            String s = arr_s[0];
            switch(s.hashCode()) {
                case 0xD98C2911: {
                    return s.equals("--translation") && Build.VERSION.SDK_INT >= 0x1F;
                }
                case 0x5FD0F67: {
                    return s.equals("--dump-dumpable") ? Build.VERSION.SDK_INT >= 33 : false;
                }
                case 0x1C2B8816: {
                    return s.equals("--list-dumpables") ? Build.VERSION.SDK_INT >= 33 : false;
                }
                case 0x4519F64D: {
                    return s.equals("--contentcapture") && Build.VERSION.SDK_INT >= 29;
                }
                case 1455016274: {
                    return s.equals("--autofill") && Build.VERSION.SDK_INT >= 26;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    @Override  // androidx.core.view.KeyEventDispatcher$Component
    public boolean superDispatchKeyEvent(KeyEvent keyEvent0) {
        Intrinsics.checkNotNullParameter(keyEvent0, "event");
        return super.dispatchKeyEvent(keyEvent0);
    }
}

