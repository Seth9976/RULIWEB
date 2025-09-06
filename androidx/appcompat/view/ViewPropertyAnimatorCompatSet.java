package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;

public class ViewPropertyAnimatorCompatSet {
    final ArrayList mAnimators;
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    public ViewPropertyAnimatorCompatSet() {
        this.mDuration = -1L;
        this.mProxyListener = new ViewPropertyAnimatorListenerAdapter() {
            private int mProxyEndCount;
            private boolean mProxyStarted;

            {
                this.mProxyStarted = false;
                this.mProxyEndCount = 0;
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                int v = this.mProxyEndCount + 1;
                this.mProxyEndCount = v;
                if(v == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                    if(ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd(null);
                    }
                    this.onEnd();
                }
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationStart(View view0) {
                if(!this.mProxyStarted) {
                    this.mProxyStarted = true;
                    if(ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart(null);
                    }
                }
            }

            void onEnd() {
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
            }
        };
        this.mAnimators = new ArrayList();
    }

    public void cancel() {
        if(!this.mIsStarted) {
            return;
        }
        for(Object object0: this.mAnimators) {
            ((ViewPropertyAnimatorCompat)object0).cancel();
        }
        this.mIsStarted = false;
    }

    void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0) {
        if(!this.mIsStarted) {
            this.mAnimators.add(viewPropertyAnimatorCompat0);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1) {
        this.mAnimators.add(viewPropertyAnimatorCompat0);
        viewPropertyAnimatorCompat1.setStartDelay(viewPropertyAnimatorCompat0.getDuration());
        this.mAnimators.add(viewPropertyAnimatorCompat1);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long v) {
        if(!this.mIsStarted) {
            this.mDuration = v;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator0) {
        if(!this.mIsStarted) {
            this.mInterpolator = interpolator0;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener0) {
        if(!this.mIsStarted) {
            this.mListener = viewPropertyAnimatorListener0;
        }
        return this;
    }

    public void start() {
        if(this.mIsStarted) {
            return;
        }
        for(Object object0: this.mAnimators) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = (ViewPropertyAnimatorCompat)object0;
            long v = this.mDuration;
            if(v >= 0L) {
                viewPropertyAnimatorCompat0.setDuration(v);
            }
            Interpolator interpolator0 = this.mInterpolator;
            if(interpolator0 != null) {
                viewPropertyAnimatorCompat0.setInterpolator(interpolator0);
            }
            if(this.mListener != null) {
                viewPropertyAnimatorCompat0.setListener(this.mProxyListener);
            }
            viewPropertyAnimatorCompat0.start();
        }
        this.mIsStarted = true;
    }
}

