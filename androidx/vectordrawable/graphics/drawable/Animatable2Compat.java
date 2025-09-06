package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable2.AnimationCallback;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public interface Animatable2Compat extends Animatable {
    public static abstract class AnimationCallback {
        Animatable2.AnimationCallback mPlatformCallback;

        Animatable2.AnimationCallback getPlatformCallback() {
            if(this.mPlatformCallback == null) {
                this.mPlatformCallback = new Animatable2.AnimationCallback() {
                    @Override  // android.graphics.drawable.Animatable2$AnimationCallback
                    public void onAnimationEnd(Drawable drawable0) {
                        AnimationCallback.this.onAnimationEnd(drawable0);
                    }

                    @Override  // android.graphics.drawable.Animatable2$AnimationCallback
                    public void onAnimationStart(Drawable drawable0) {
                        AnimationCallback.this.onAnimationStart(drawable0);
                    }
                };
            }
            return this.mPlatformCallback;
        }

        public void onAnimationEnd(Drawable drawable0) {
        }

        public void onAnimationStart(Drawable drawable0) {
        }
    }

    void clearAnimationCallbacks();

    void registerAnimationCallback(AnimationCallback arg1);

    boolean unregisterAnimationCallback(AnimationCallback arg1);
}

