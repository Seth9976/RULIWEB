package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
    static class Api21Impl {
        static ViewPropertyAnimator translationZ(ViewPropertyAnimator viewPropertyAnimator0, float f) {
            return viewPropertyAnimator0.translationZ(f);
        }

        static ViewPropertyAnimator translationZBy(ViewPropertyAnimator viewPropertyAnimator0, float f) {
            return viewPropertyAnimator0.translationZBy(f);
        }

        static ViewPropertyAnimator z(ViewPropertyAnimator viewPropertyAnimator0, float f) {
            return viewPropertyAnimator0.z(f);
        }

        static ViewPropertyAnimator zBy(ViewPropertyAnimator viewPropertyAnimator0, float f) {
            return viewPropertyAnimator0.zBy(f);
        }
    }

    private final WeakReference mView;

    ViewPropertyAnimatorCompat(View view0) {
        this.mView = new WeakReference(view0);
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().alpha(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().alphaBy(f);
        }
        return this;
    }

    public void cancel() {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().cancel();
        }
    }

    public long getDuration() {
        View view0 = (View)this.mView.get();
        return view0 == null ? 0L : view0.animate().getDuration();
    }

    public Interpolator getInterpolator() {
        View view0 = (View)this.mView.get();
        return view0 == null ? null : ((Interpolator)view0.animate().getInterpolator());
    }

    public long getStartDelay() {
        View view0 = (View)this.mView.get();
        return view0 == null ? 0L : view0.animate().getStartDelay();
    }

    // 检测为 Lambda 实现
    static void lambda$setUpdateListener$0(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener0, View view0, ValueAnimator valueAnimator0) [...]

    public ViewPropertyAnimatorCompat rotation(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotation(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotationBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().rotationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().scaleX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().scaleXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().scaleY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().scaleYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long v) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().setDuration(v);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator0) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().setInterpolator(interpolator0);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener0) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            this.setListenerInternal(view0, viewPropertyAnimatorListener0);
        }
        return this;
    }

    private void setListenerInternal(View view0, ViewPropertyAnimatorListener viewPropertyAnimatorListener0) {
        if(viewPropertyAnimatorListener0 != null) {
            view0.animate().setListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationCancel(Animator animator0) {
                    viewPropertyAnimatorListener0.onAnimationCancel(view0);
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    viewPropertyAnimatorListener0.onAnimationEnd(view0);
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    viewPropertyAnimatorListener0.onAnimationStart(view0);
                }
            });
            return;
        }
        view0.animate().setListener(null);
    }

    public ViewPropertyAnimatorCompat setStartDelay(long v) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().setStartDelay(v);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener0) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            ViewPropertyAnimatorCompat..ExternalSyntheticLambda0 viewPropertyAnimatorCompat$$ExternalSyntheticLambda00 = viewPropertyAnimatorUpdateListener0 == null ? null : (ValueAnimator valueAnimator0) -> viewPropertyAnimatorUpdateListener0.onAnimationUpdate(view0);
            view0.animate().setUpdateListener(viewPropertyAnimatorCompat$$ExternalSyntheticLambda00);
        }
        return this;
    }

    public void start() {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationX(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().translationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().translationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().translationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().translationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            Api21Impl.translationZ(view0.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            Api21Impl.translationZBy(view0.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable0) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().withEndAction(runnable0);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer() {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().withLayer();
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable0) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().withStartAction(runnable0);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat x(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().x(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().xBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().y(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            view0.animate().yBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat z(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            Api21Impl.z(view0.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f) {
        View view0 = (View)this.mView.get();
        if(view0 != null) {
            Api21Impl.zBy(view0.animate(), f);
        }
        return this;
    }
}

