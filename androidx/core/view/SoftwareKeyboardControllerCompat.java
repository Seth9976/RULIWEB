package androidx.core.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SoftwareKeyboardControllerCompat {
    static class Impl20 extends Impl {
        private final View mView;

        Impl20(View view0) {
            this.mView = view0;
        }

        @Override  // androidx.core.view.SoftwareKeyboardControllerCompat$Impl
        void hide() {
            View view0 = this.mView;
            if(view0 != null) {
                ((InputMethodManager)view0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
            }
        }

        // 检测为 Lambda 实现
        static void lambda$show$0(View view0) [...]

        @Override  // androidx.core.view.SoftwareKeyboardControllerCompat$Impl
        void show() {
            View view0 = this.mView;
            if(view0 != null) {
                if(view0.isInEditMode() || view0.onCheckIsTextEditor()) {
                    view0.requestFocus();
                }
                else {
                    view0 = view0.getRootView().findFocus();
                }
                if(view0 == null) {
                    view0 = this.mView.getRootView().findViewById(0x1020002);
                }
                if(view0 != null && view0.hasWindowFocus()) {
                    view0.post(() -> ((InputMethodManager)view0.getContext().getSystemService("input_method")).showSoftInput(view0, 0));
                }
            }
        }
    }

    static class Impl30 extends Impl20 {
        private View mView;
        private WindowInsetsController mWindowInsetsController;

        Impl30(View view0) {
            super(view0);
            this.mView = view0;
        }

        Impl30(WindowInsetsController windowInsetsController0) {
            super(null);
            this.mWindowInsetsController = windowInsetsController0;
        }

        @Override  // androidx.core.view.SoftwareKeyboardControllerCompat$Impl20
        void hide() {
            WindowInsetsController windowInsetsController0 = this.mWindowInsetsController;
            if(windowInsetsController0 == null) {
                windowInsetsController0 = this.mView == null ? null : this.mView.getWindowInsetsController();
            }
            if(windowInsetsController0 != null) {
                AtomicBoolean atomicBoolean0 = new AtomicBoolean(false);
                SoftwareKeyboardControllerCompat.Impl30..ExternalSyntheticLambda6 softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda60 = (WindowInsetsController windowInsetsController0, int v) -> atomicBoolean0.set((v & 8) != 0);
                windowInsetsController0.addOnControllableInsetsChangedListener(softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda60);
                if(!atomicBoolean0.get()) {
                    View view0 = this.mView;
                    if(view0 != null) {
                        ((InputMethodManager)view0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
                    }
                }
                windowInsetsController0.removeOnControllableInsetsChangedListener(softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda60);
                windowInsetsController0.hide(HalfKt..ExternalSyntheticApiModelOutline0.m());
                return;
            }
            super.hide();
        }

        // 检测为 Lambda 实现
        static void lambda$hide$0(AtomicBoolean atomicBoolean0, WindowInsetsController windowInsetsController0, int v) [...]

        @Override  // androidx.core.view.SoftwareKeyboardControllerCompat$Impl20
        void show() {
            if(this.mView != null && Build.VERSION.SDK_INT < 33) {
                ((InputMethodManager)this.mView.getContext().getSystemService("input_method")).isActive();
            }
            WindowInsetsController windowInsetsController0 = this.mWindowInsetsController;
            if(windowInsetsController0 == null) {
                windowInsetsController0 = this.mView == null ? null : this.mView.getWindowInsetsController();
            }
            if(windowInsetsController0 != null) {
                windowInsetsController0.show(HalfKt..ExternalSyntheticApiModelOutline0.m());
            }
            super.show();
        }
    }

    static class Impl {
        void hide() {
        }

        void show() {
        }
    }

    private final Impl mImpl;

    public SoftwareKeyboardControllerCompat(View view0) {
        if(Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(view0);
            return;
        }
        this.mImpl = new Impl20(view0);
    }

    @Deprecated
    SoftwareKeyboardControllerCompat(WindowInsetsController windowInsetsController0) {
        this.mImpl = new Impl30(windowInsetsController0);
    }

    public void hide() {
        this.mImpl.hide();
    }

    public void show() {
        this.mImpl.show();
    }
}

