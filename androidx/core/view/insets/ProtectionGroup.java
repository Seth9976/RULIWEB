package androidx.core.view.insets;

import android.graphics.RectF;
import androidx.core.graphics.Insets;
import java.util.ArrayList;
import java.util.List;

class ProtectionGroup implements Callback {
    private int mAnimationCount;
    private boolean mDisposed;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final SystemBarStateMonitor mMonitor;
    private final ArrayList mProtections;

    ProtectionGroup(SystemBarStateMonitor systemBarStateMonitor0, List list0) {
        this.mProtections = new ArrayList();
        this.mInsets = Insets.NONE;
        this.mInsetsIgnoringVisibility = Insets.NONE;
        this.addProtections(list0, false);
        this.addProtections(list0, true);
        systemBarStateMonitor0.addCallback(this);
        this.mMonitor = systemBarStateMonitor0;
    }

    private void addProtections(List list0, boolean z) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Protection protection0 = (Protection)list0.get(v1);
            if(protection0.occupiesCorners() == z) {
                Object object0 = protection0.getController();
                if(object0 != null) {
                    throw new IllegalStateException(protection0 + " is already controlled by " + object0);
                }
                protection0.setController(this);
                this.mProtections.add(protection0);
                continue;
            }
        }
    }

    void dispose() {
        if(this.mDisposed) {
            return;
        }
        this.mDisposed = true;
        this.mMonitor.removeCallback(this);
        for(int v = this.mProtections.size() - 1; v >= 0; --v) {
            ((Protection)this.mProtections.get(v)).setController(null);
        }
        this.mProtections.clear();
    }

    Protection getProtection(int v) {
        return (Protection)this.mProtections.get(v);
    }

    @Override  // androidx.core.view.insets.SystemBarStateMonitor$Callback
    public void onAnimationEnd() {
        int v = this.mAnimationCount;
        this.mAnimationCount = v - 1;
        if(v > 0 && v - 1 == 0) {
            this.updateInsets();
        }
    }

    @Override  // androidx.core.view.insets.SystemBarStateMonitor$Callback
    public void onAnimationProgress(int v, Insets insets0, RectF rectF0) {
        Insets insets1 = this.mInsetsIgnoringVisibility;
        for(int v1 = this.mProtections.size() - 1; v1 >= 0; --v1) {
            Protection protection0 = (Protection)this.mProtections.get(v1);
            int v2 = protection0.getSide();
            if((v2 & v) != 0) {
                protection0.setSystemVisible(true);
                switch(v2) {
                    case 1: {
                        if(insets1.left > 0) {
                            protection0.setSystemInsetAmount(((float)insets0.left) / ((float)insets1.left));
                        }
                        protection0.setSystemAlpha(rectF0.left);
                        break;
                    }
                    case 2: {
                        if(insets1.top > 0) {
                            protection0.setSystemInsetAmount(((float)insets0.top) / ((float)insets1.top));
                        }
                        protection0.setSystemAlpha(rectF0.top);
                        break;
                    }
                    case 4: {
                        if(insets1.right > 0) {
                            protection0.setSystemInsetAmount(((float)insets0.right) / ((float)insets1.right));
                        }
                        protection0.setSystemAlpha(rectF0.right);
                        break;
                    }
                    case 8: {
                        if(insets1.bottom > 0) {
                            protection0.setSystemInsetAmount(((float)insets0.bottom) / ((float)insets1.bottom));
                        }
                        protection0.setSystemAlpha(rectF0.bottom);
                    }
                }
            }
        }
    }

    @Override  // androidx.core.view.insets.SystemBarStateMonitor$Callback
    public void onAnimationStart() {
        ++this.mAnimationCount;
    }

    @Override  // androidx.core.view.insets.SystemBarStateMonitor$Callback
    public void onColorHintChanged(int v) {
        for(int v1 = this.mProtections.size() - 1; v1 >= 0; --v1) {
            ((Protection)this.mProtections.get(v1)).dispatchColorHint(v);
        }
    }

    @Override  // androidx.core.view.insets.SystemBarStateMonitor$Callback
    public void onInsetsChanged(Insets insets0, Insets insets1) {
        this.mInsets = insets0;
        this.mInsetsIgnoringVisibility = insets1;
        this.updateInsets();
    }

    int size() {
        return this.mProtections.size();
    }

    private void updateInsets() {
        Insets insets0 = Insets.NONE;
        for(int v = this.mProtections.size() - 1; v >= 0; --v) {
            insets0 = Insets.max(insets0, ((Protection)this.mProtections.get(v)).dispatchInsets(this.mInsets, this.mInsetsIgnoringVisibility, insets0));
        }
    }
}

