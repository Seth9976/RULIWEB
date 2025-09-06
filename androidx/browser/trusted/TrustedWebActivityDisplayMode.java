package androidx.browser.trusted;

import android.os.Bundle;

public interface TrustedWebActivityDisplayMode {
    public static class DefaultMode implements TrustedWebActivityDisplayMode {
        private static final int ID;

        @Override  // androidx.browser.trusted.TrustedWebActivityDisplayMode
        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putInt("androidx.browser.trusted.displaymode.KEY_ID", 0);
            return bundle0;
        }
    }

    public static class ImmersiveMode implements TrustedWebActivityDisplayMode {
        private static final int ID = 1;
        public static final String KEY_CUTOUT_MODE = "androidx.browser.trusted.displaymode.KEY_CUTOUT_MODE";
        public static final String KEY_STICKY = "androidx.browser.trusted.displaymode.KEY_STICKY";
        private final boolean mIsSticky;
        private final int mLayoutInDisplayCutoutMode;

        public ImmersiveMode(boolean z, int v) {
            this.mIsSticky = z;
            this.mLayoutInDisplayCutoutMode = v;
        }

        static TrustedWebActivityDisplayMode fromBundle(Bundle bundle0) {
            return new ImmersiveMode(bundle0.getBoolean("androidx.browser.trusted.displaymode.KEY_STICKY"), bundle0.getInt("androidx.browser.trusted.displaymode.KEY_CUTOUT_MODE"));
        }

        public boolean isSticky() {
            return this.mIsSticky;
        }

        public int layoutInDisplayCutoutMode() {
            return this.mLayoutInDisplayCutoutMode;
        }

        @Override  // androidx.browser.trusted.TrustedWebActivityDisplayMode
        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putInt("androidx.browser.trusted.displaymode.KEY_ID", 1);
            bundle0.putBoolean("androidx.browser.trusted.displaymode.KEY_STICKY", this.mIsSticky);
            bundle0.putInt("androidx.browser.trusted.displaymode.KEY_CUTOUT_MODE", this.mLayoutInDisplayCutoutMode);
            return bundle0;
        }
    }

    public static final String KEY_ID = "androidx.browser.trusted.displaymode.KEY_ID";

    Bundle toBundle();
}

