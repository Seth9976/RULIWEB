package androidx.dynamicanimation.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer.FrameCallback;
import android.view.Choreographer;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

class AnimationHandler {
    class AnimationCallbackDispatcher {
        void dispatchAnimationFrame() {
            AnimationHandler.this.mCurrentFrameTime = SystemClock.uptimeMillis();
            AnimationHandler.this.doAnimationFrame(AnimationHandler.this.mCurrentFrameTime);
            if(AnimationHandler.this.mAnimationCallbacks.size() > 0) {
                AnimationHandler.this.getProvider().postFrameCallback();
            }
        }
    }

    interface AnimationFrameCallback {
        boolean doAnimationFrame(long arg1);
    }

    static abstract class AnimationFrameCallbackProvider {
        final AnimationCallbackDispatcher mDispatcher;

        AnimationFrameCallbackProvider(AnimationCallbackDispatcher animationHandler$AnimationCallbackDispatcher0) {
            this.mDispatcher = animationHandler$AnimationCallbackDispatcher0;
        }

        abstract void postFrameCallback();
    }

    static class FrameCallbackProvider14 extends AnimationFrameCallbackProvider {
        private final Handler mHandler;
        long mLastFrameTime;
        private final Runnable mRunnable;

        FrameCallbackProvider14(AnimationCallbackDispatcher animationHandler$AnimationCallbackDispatcher0) {
            super(animationHandler$AnimationCallbackDispatcher0);
            this.mLastFrameTime = -1L;
            this.mRunnable = new Runnable() {
                @Override
                public void run() {
                    FrameCallbackProvider14.this.mLastFrameTime = SystemClock.uptimeMillis();
                    FrameCallbackProvider14.this.mDispatcher.dispatchAnimationFrame();
                }
            };
            this.mHandler = new Handler(Looper.myLooper());
        }

        @Override  // androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallbackProvider
        void postFrameCallback() {
            long v = Math.max(10L - (SystemClock.uptimeMillis() - this.mLastFrameTime), 0L);
            this.mHandler.postDelayed(this.mRunnable, v);
        }
    }

    static class FrameCallbackProvider16 extends AnimationFrameCallbackProvider {
        private final Choreographer mChoreographer;
        private final Choreographer.FrameCallback mChoreographerCallback;

        FrameCallbackProvider16(AnimationCallbackDispatcher animationHandler$AnimationCallbackDispatcher0) {
            super(animationHandler$AnimationCallbackDispatcher0);
            this.mChoreographer = Choreographer.getInstance();
            this.mChoreographerCallback = new Choreographer.FrameCallback() {
                @Override  // android.view.Choreographer$FrameCallback
                public void doFrame(long v) {
                    FrameCallbackProvider16.this.mDispatcher.dispatchAnimationFrame();
                }
            };
        }

        @Override  // androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallbackProvider
        void postFrameCallback() {
            this.mChoreographer.postFrameCallback(this.mChoreographerCallback);
        }
    }

    private static final long FRAME_DELAY_MS = 10L;
    final ArrayList mAnimationCallbacks;
    private final AnimationCallbackDispatcher mCallbackDispatcher;
    long mCurrentFrameTime;
    private final SimpleArrayMap mDelayedCallbackStartTime;
    private boolean mListDirty;
    private AnimationFrameCallbackProvider mProvider;
    public static final ThreadLocal sAnimatorHandler;

    static {
        AnimationHandler.sAnimatorHandler = new ThreadLocal();
    }

    AnimationHandler() {
        this.mDelayedCallbackStartTime = new SimpleArrayMap();
        this.mAnimationCallbacks = new ArrayList();
        this.mCallbackDispatcher = new AnimationCallbackDispatcher(this);
        this.mCurrentFrameTime = 0L;
        this.mListDirty = false;
    }

    public void addAnimationFrameCallback(AnimationFrameCallback animationHandler$AnimationFrameCallback0, long v) {
        if(this.mAnimationCallbacks.size() == 0) {
            this.getProvider().postFrameCallback();
        }
        if(!this.mAnimationCallbacks.contains(animationHandler$AnimationFrameCallback0)) {
            this.mAnimationCallbacks.add(animationHandler$AnimationFrameCallback0);
        }
        if(v > 0L) {
            Long long0 = (long)(SystemClock.uptimeMillis() + v);
            this.mDelayedCallbackStartTime.put(animationHandler$AnimationFrameCallback0, long0);
        }
    }

    private void cleanUpList() {
        if(this.mListDirty) {
            for(int v = this.mAnimationCallbacks.size() - 1; v >= 0; --v) {
                if(this.mAnimationCallbacks.get(v) == null) {
                    this.mAnimationCallbacks.remove(v);
                }
            }
            this.mListDirty = false;
        }
    }

    void doAnimationFrame(long v) {
        long v1 = SystemClock.uptimeMillis();
        for(int v2 = 0; v2 < this.mAnimationCallbacks.size(); ++v2) {
            AnimationFrameCallback animationHandler$AnimationFrameCallback0 = (AnimationFrameCallback)this.mAnimationCallbacks.get(v2);
            if(animationHandler$AnimationFrameCallback0 != null && this.isCallbackDue(animationHandler$AnimationFrameCallback0, v1)) {
                animationHandler$AnimationFrameCallback0.doAnimationFrame(v);
            }
        }
        this.cleanUpList();
    }

    public static long getFrameTime() {
        return AnimationHandler.sAnimatorHandler.get() == null ? 0L : ((AnimationHandler)AnimationHandler.sAnimatorHandler.get()).mCurrentFrameTime;
    }

    public static AnimationHandler getInstance() {
        ThreadLocal threadLocal0 = AnimationHandler.sAnimatorHandler;
        if(threadLocal0.get() == null) {
            threadLocal0.set(new AnimationHandler());
        }
        return (AnimationHandler)threadLocal0.get();
    }

    AnimationFrameCallbackProvider getProvider() {
        if(this.mProvider == null) {
            this.mProvider = new FrameCallbackProvider16(this.mCallbackDispatcher);
        }
        return this.mProvider;
    }

    private boolean isCallbackDue(AnimationFrameCallback animationHandler$AnimationFrameCallback0, long v) {
        Long long0 = (Long)this.mDelayedCallbackStartTime.get(animationHandler$AnimationFrameCallback0);
        if(long0 == null) {
            return true;
        }
        if(((long)long0) < v) {
            this.mDelayedCallbackStartTime.remove(animationHandler$AnimationFrameCallback0);
            return true;
        }
        return false;
    }

    public void removeCallback(AnimationFrameCallback animationHandler$AnimationFrameCallback0) {
        this.mDelayedCallbackStartTime.remove(animationHandler$AnimationFrameCallback0);
        int v = this.mAnimationCallbacks.indexOf(animationHandler$AnimationFrameCallback0);
        if(v >= 0) {
            this.mAnimationCallbacks.set(v, null);
            this.mListDirty = true;
        }
    }

    public void setProvider(AnimationFrameCallbackProvider animationHandler$AnimationFrameCallbackProvider0) {
        this.mProvider = animationHandler$AnimationFrameCallbackProvider0;
    }
}

