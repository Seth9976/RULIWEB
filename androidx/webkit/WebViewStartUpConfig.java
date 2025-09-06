package androidx.webkit;

import java.util.concurrent.Executor;

public final class WebViewStartUpConfig {
    public static final class Builder {
        private final Executor mExecutor;
        private boolean mShouldRunUiThreadStartUpTasks;

        public Builder(Executor executor0) {
            this.mShouldRunUiThreadStartUpTasks = true;
            this.mExecutor = executor0;
        }

        public WebViewStartUpConfig build() {
            return new WebViewStartUpConfig(this.mExecutor, this.mShouldRunUiThreadStartUpTasks, null);
        }

        public Builder setShouldRunUiThreadStartUpTasks(boolean z) {
            this.mShouldRunUiThreadStartUpTasks = z;
            return this;
        }
    }

    private final Executor mExecutor;
    private final boolean mShouldRunUiThreadStartUpTasks;

    private WebViewStartUpConfig(Executor executor0, boolean z) {
        this.mExecutor = executor0;
        this.mShouldRunUiThreadStartUpTasks = z;
    }

    WebViewStartUpConfig(Executor executor0, boolean z, androidx.webkit.WebViewStartUpConfig.1 webViewStartUpConfig$10) {
        this(executor0, z);
    }

    public Executor getBackgroundExecutor() {
        return this.mExecutor;
    }

    public boolean shouldRunUiThreadStartUpTasks() {
        return this.mShouldRunUiThreadStartUpTasks;
    }

    class androidx.webkit.WebViewStartUpConfig.1 {
    }

}

