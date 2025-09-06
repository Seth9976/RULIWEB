package androidx.core.view;

import android.view.WindowInsetsAnimationController;
import androidx.core.graphics.Insets;

public final class WindowInsetsAnimationControllerCompat {
    static class Impl30 extends Impl {
        private final WindowInsetsAnimationController mController;

        Impl30(WindowInsetsAnimationController windowInsetsAnimationController0) {
            this.mController = windowInsetsAnimationController0;
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        void finish(boolean z) {
            this.mController.finish(z);
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public float getCurrentAlpha() {
            return this.mController.getCurrentAlpha();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public float getCurrentFraction() {
            return this.mController.getCurrentFraction();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public Insets getCurrentInsets() {
            return Insets.toCompatInsets(this.mController.getCurrentInsets());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public Insets getHiddenStateInsets() {
            return Insets.toCompatInsets(this.mController.getHiddenStateInsets());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public Insets getShownStateInsets() {
            return Insets.toCompatInsets(this.mController.getShownStateInsets());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public int getTypes() {
            return this.mController.getTypes();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        boolean isCancelled() {
            return this.mController.isCancelled();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        boolean isFinished() {
            return this.mController.isFinished();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationControllerCompat$Impl
        public void setInsetsAndAlpha(Insets insets0, float f, float f1) {
            android.graphics.Insets insets1 = insets0 == null ? null : insets0.toPlatformInsets();
            this.mController.setInsetsAndAlpha(insets1, f, f1);
        }
    }

    static class Impl {
        void finish(boolean z) {
        }

        public float getCurrentAlpha() {
            return 0.0f;
        }

        public float getCurrentFraction() {
            return 0.0f;
        }

        public Insets getCurrentInsets() {
            return Insets.NONE;
        }

        public Insets getHiddenStateInsets() {
            return Insets.NONE;
        }

        public Insets getShownStateInsets() {
            return Insets.NONE;
        }

        public int getTypes() {
            return 0;
        }

        boolean isCancelled() {
            return true;
        }

        boolean isFinished() {
            return false;
        }

        public void setInsetsAndAlpha(Insets insets0, float f, float f1) {
        }
    }

    private final Impl mImpl;

    WindowInsetsAnimationControllerCompat(WindowInsetsAnimationController windowInsetsAnimationController0) {
        this.mImpl = new Impl30(windowInsetsAnimationController0);
    }

    public void finish(boolean z) {
        this.mImpl.finish(z);
    }

    public float getCurrentAlpha() {
        return this.mImpl.getCurrentAlpha();
    }

    public float getCurrentFraction() {
        return this.mImpl.getCurrentFraction();
    }

    public Insets getCurrentInsets() {
        return this.mImpl.getCurrentInsets();
    }

    public Insets getHiddenStateInsets() {
        return this.mImpl.getHiddenStateInsets();
    }

    public Insets getShownStateInsets() {
        return this.mImpl.getShownStateInsets();
    }

    public int getTypes() {
        return this.mImpl.getTypes();
    }

    public boolean isCancelled() {
        return this.mImpl.isCancelled();
    }

    public boolean isFinished() {
        return this.mImpl.isFinished();
    }

    // 去混淆评级： 低(20)
    public boolean isReady() {
        return !this.isFinished() && !this.isCancelled();
    }

    public void setInsetsAndAlpha(Insets insets0, float f, float f1) {
        this.mImpl.setInsetsAndAlpha(insets0, f, f1);
    }
}

