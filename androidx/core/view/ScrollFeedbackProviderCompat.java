package androidx.core.view;

import android.os.Build.VERSION;
import android.view.ScrollFeedbackProvider;
import android.view.View;

public class ScrollFeedbackProviderCompat {
    static class ScrollFeedbackProviderApi35Impl implements ScrollFeedbackProviderImpl {
        private final ScrollFeedbackProvider mProvider;

        ScrollFeedbackProviderApi35Impl(View view0) {
            this.mProvider = ScrollFeedbackProvider.createProvider(view0);
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onScrollLimit(int v, int v1, int v2, boolean z) {
            this.mProvider.onScrollLimit(v, v1, v2, z);
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onScrollProgress(int v, int v1, int v2, int v3) {
            this.mProvider.onScrollProgress(v, v1, v2, v3);
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onSnapToItem(int v, int v1, int v2) {
            this.mProvider.onSnapToItem(v, v1, v2);
        }
    }

    static class ScrollFeedbackProviderBaseImpl implements ScrollFeedbackProviderImpl {
        private ScrollFeedbackProviderBaseImpl() {
        }

        ScrollFeedbackProviderBaseImpl(androidx.core.view.ScrollFeedbackProviderCompat.1 scrollFeedbackProviderCompat$10) {
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onScrollLimit(int v, int v1, int v2, boolean z) {
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onScrollProgress(int v, int v1, int v2, int v3) {
        }

        @Override  // androidx.core.view.ScrollFeedbackProviderCompat$ScrollFeedbackProviderImpl
        public void onSnapToItem(int v, int v1, int v2) {
        }
    }

    interface ScrollFeedbackProviderImpl {
        void onScrollLimit(int arg1, int arg2, int arg3, boolean arg4);

        void onScrollProgress(int arg1, int arg2, int arg3, int arg4);

        void onSnapToItem(int arg1, int arg2, int arg3);
    }

    private final ScrollFeedbackProviderImpl mImpl;

    private ScrollFeedbackProviderCompat(View view0) {
        if(Build.VERSION.SDK_INT >= 35) {
            this.mImpl = new ScrollFeedbackProviderApi35Impl(view0);
            return;
        }
        this.mImpl = new ScrollFeedbackProviderBaseImpl(null);
    }

    public static ScrollFeedbackProviderCompat createProvider(View view0) {
        return new ScrollFeedbackProviderCompat(view0);
    }

    public void onScrollLimit(int v, int v1, int v2, boolean z) {
        this.mImpl.onScrollLimit(v, v1, v2, z);
    }

    public void onScrollProgress(int v, int v1, int v2, int v3) {
        this.mImpl.onScrollProgress(v, v1, v2, v3);
    }

    public void onSnapToItem(int v, int v1, int v2) {
        this.mImpl.onSnapToItem(v, v1, v2);
    }

    class androidx.core.view.ScrollFeedbackProviderCompat.1 {
    }

}

