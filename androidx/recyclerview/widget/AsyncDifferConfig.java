package androidx.recyclerview.widget;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class AsyncDifferConfig {
    public static final class Builder {
        private Executor mBackgroundThreadExecutor;
        private final ItemCallback mDiffCallback;
        private Executor mMainThreadExecutor;
        private static Executor sDiffExecutor;
        private static final Object sExecutorLock;

        static {
            Builder.sExecutorLock = new Object();
        }

        public Builder(ItemCallback diffUtil$ItemCallback0) {
            this.mDiffCallback = diffUtil$ItemCallback0;
        }

        public AsyncDifferConfig build() {
            if(this.mBackgroundThreadExecutor == null) {
                Object object0 = Builder.sExecutorLock;
                synchronized(object0) {
                    if(Builder.sDiffExecutor == null) {
                        Builder.sDiffExecutor = Executors.newFixedThreadPool(2);
                    }
                }
                this.mBackgroundThreadExecutor = Builder.sDiffExecutor;
                return new AsyncDifferConfig(this.mMainThreadExecutor, this.mBackgroundThreadExecutor, this.mDiffCallback);
            }
            return new AsyncDifferConfig(this.mMainThreadExecutor, this.mBackgroundThreadExecutor, this.mDiffCallback);
        }

        public Builder setBackgroundThreadExecutor(Executor executor0) {
            this.mBackgroundThreadExecutor = executor0;
            return this;
        }

        public Builder setMainThreadExecutor(Executor executor0) {
            this.mMainThreadExecutor = executor0;
            return this;
        }
    }

    private final Executor mBackgroundThreadExecutor;
    private final ItemCallback mDiffCallback;
    private final Executor mMainThreadExecutor;

    AsyncDifferConfig(Executor executor0, Executor executor1, ItemCallback diffUtil$ItemCallback0) {
        this.mMainThreadExecutor = executor0;
        this.mBackgroundThreadExecutor = executor1;
        this.mDiffCallback = diffUtil$ItemCallback0;
    }

    public Executor getBackgroundThreadExecutor() {
        return this.mBackgroundThreadExecutor;
    }

    public ItemCallback getDiffCallback() {
        return this.mDiffCallback;
    }

    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }
}

