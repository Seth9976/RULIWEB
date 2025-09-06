package androidx.core.view.insets;

import android.content.res.Configuration;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SystemBarStateMonitor {
    interface Callback {
        void onAnimationEnd();

        void onAnimationProgress(int arg1, Insets arg2, RectF arg3);

        void onAnimationStart();

        void onColorHintChanged(int arg1);

        void onInsetsChanged(Insets arg1, Insets arg2);
    }

    private final ArrayList mCallbacks;
    private int mColorHint;
    private final View mDetector;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;

    SystemBarStateMonitor(ViewGroup viewGroup0) {
        this.mCallbacks = new ArrayList();
        this.mInsets = Insets.NONE;
        this.mInsetsIgnoringVisibility = Insets.NONE;
        Drawable drawable0 = viewGroup0.getBackground();
        this.mColorHint = drawable0 instanceof ColorDrawable ? ((ColorDrawable)drawable0).getColor() : 0;
        androidx.core.view.insets.SystemBarStateMonitor.1 systemBarStateMonitor$10 = new View(viewGroup0.getContext()) {
            @Override  // android.view.View
            protected void onConfigurationChanged(Configuration configuration0) {
                Drawable drawable0 = viewGroup0.getBackground();
                int v = drawable0 instanceof ColorDrawable ? ((ColorDrawable)drawable0).getColor() : 0;
                if(SystemBarStateMonitor.this.mColorHint != v) {
                    SystemBarStateMonitor.this.mColorHint = v;
                    for(int v1 = SystemBarStateMonitor.this.mCallbacks.size() - 1; v1 >= 0; --v1) {
                        ((Callback)SystemBarStateMonitor.this.mCallbacks.get(v1)).onColorHintChanged(v);
                    }
                }
            }
        };
        this.mDetector = systemBarStateMonitor$10;
        systemBarStateMonitor$10.setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(systemBarStateMonitor$10, (View view0, WindowInsetsCompat windowInsetsCompat0) -> {
            Insets insets0 = this.getInsets(windowInsetsCompat0);
            Insets insets1 = this.getInsetsIgnoringVisibility(windowInsetsCompat0);
            if(!insets0.equals(this.mInsets) || !insets1.equals(this.mInsetsIgnoringVisibility)) {
                this.mInsets = insets0;
                this.mInsetsIgnoringVisibility = insets1;
                for(int v = this.mCallbacks.size() - 1; v >= 0; --v) {
                    ((Callback)this.mCallbacks.get(v)).onInsetsChanged(insets0, insets1);
                }
            }
            return windowInsetsCompat0;
        });
        ViewCompat.setWindowInsetsAnimationCallback(systemBarStateMonitor$10, new androidx.core.view.WindowInsetsAnimationCompat.Callback(0) {
            private final HashMap mAnimationSidesMap;

            {
                int v = 0;  // 捕获的参数
                this.mAnimationSidesMap = new HashMap();
            }

            private boolean animatesSystemBars(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
                return (windowInsetsAnimationCompat0.getTypeMask() & 0x207) != 0;
            }

            @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
            public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
                if(this.animatesSystemBars(windowInsetsAnimationCompat0)) {
                    this.mAnimationSidesMap.remove(windowInsetsAnimationCompat0);
                    for(int v = SystemBarStateMonitor.this.mCallbacks.size() - 1; v >= 0; --v) {
                        ((Callback)SystemBarStateMonitor.this.mCallbacks.get(v)).onAnimationEnd();
                    }
                }
            }

            @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
            public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
                if(this.animatesSystemBars(windowInsetsAnimationCompat0)) {
                    for(int v = SystemBarStateMonitor.this.mCallbacks.size() - 1; v >= 0; --v) {
                        ((Callback)SystemBarStateMonitor.this.mCallbacks.get(v)).onAnimationStart();
                    }
                }
            }

            @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
            public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat0, List list0) {
                RectF rectF0 = new RectF(1.0f, 1.0f, 1.0f, 1.0f);
                int v = list0.size() - 1;
                int v1 = 0;
                while(v >= 0) {
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = (WindowInsetsAnimationCompat)list0.get(v);
                    Integer integer0 = (Integer)this.mAnimationSidesMap.get(windowInsetsAnimationCompat0);
                    if(integer0 != null) {
                        int v2 = (int)integer0;
                        float f = windowInsetsAnimationCompat0.getAlpha();
                        if((v2 & 1) != 0) {
                            rectF0.left = f;
                        }
                        if((v2 & 2) != 0) {
                            rectF0.top = f;
                        }
                        if((v2 & 4) != 0) {
                            rectF0.right = f;
                        }
                        if((v2 & 8) != 0) {
                            rectF0.bottom = f;
                        }
                        v1 |= v2;
                    }
                    --v;
                }
                Insets insets0 = SystemBarStateMonitor.this.getInsets(windowInsetsCompat0);
                for(int v3 = SystemBarStateMonitor.this.mCallbacks.size() - 1; v3 >= 0; --v3) {
                    ((Callback)SystemBarStateMonitor.this.mCallbacks.get(v3)).onAnimationProgress(v1, insets0, rectF0);
                }
                return windowInsetsCompat0;
            }

            @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
            public BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat0, BoundsCompat windowInsetsAnimationCompat$BoundsCompat0) {
                if(!this.animatesSystemBars(windowInsetsAnimationCompat0)) {
                    return windowInsetsAnimationCompat$BoundsCompat0;
                }
                Insets insets0 = windowInsetsAnimationCompat$BoundsCompat0.getUpperBound();
                Insets insets1 = windowInsetsAnimationCompat$BoundsCompat0.getLowerBound();
                int v = insets0.left == insets1.left ? 0 : 1;
                if(insets0.top != insets1.top) {
                    v |= 2;
                }
                if(insets0.right != insets1.right) {
                    v |= 4;
                }
                if(insets0.bottom != insets1.bottom) {
                    v |= 8;
                }
                this.mAnimationSidesMap.put(windowInsetsAnimationCompat0, v);
                return windowInsetsAnimationCompat$BoundsCompat0;
            }
        });
        viewGroup0.addView(systemBarStateMonitor$10, 0);
    }

    void addCallback(Callback systemBarStateMonitor$Callback0) {
        if(this.mCallbacks.contains(systemBarStateMonitor$Callback0)) {
            return;
        }
        this.mCallbacks.add(systemBarStateMonitor$Callback0);
        systemBarStateMonitor$Callback0.onInsetsChanged(this.mInsets, this.mInsetsIgnoringVisibility);
        systemBarStateMonitor$Callback0.onColorHintChanged(this.mColorHint);
    }

    void detachFromWindow() {
        SystemBarStateMonitor..ExternalSyntheticLambda1 systemBarStateMonitor$$ExternalSyntheticLambda10 = () -> {
            ViewParent viewParent0 = this.mDetector.getParent();
            if(viewParent0 instanceof ViewGroup) {
                ((ViewGroup)viewParent0).removeView(this.mDetector);
            }
        };
        this.mDetector.post(systemBarStateMonitor$$ExternalSyntheticLambda10);
    }

    private Insets getInsets(WindowInsetsCompat windowInsetsCompat0) {
        return Insets.min(windowInsetsCompat0.getInsets(0x207), windowInsetsCompat0.getInsets(0x40));
    }

    private Insets getInsetsIgnoringVisibility(WindowInsetsCompat windowInsetsCompat0) {
        return Insets.min(windowInsetsCompat0.getInsetsIgnoringVisibility(0x207), windowInsetsCompat0.getInsetsIgnoringVisibility(0x40));
    }

    boolean hasCallback() {
        return !this.mCallbacks.isEmpty();
    }

    // 检测为 Lambda 实现
    void lambda$detachFromWindow$1$androidx-core-view-insets-SystemBarStateMonitor() [...]

    // 检测为 Lambda 实现
    WindowInsetsCompat lambda$new$0$androidx-core-view-insets-SystemBarStateMonitor(View view0, WindowInsetsCompat windowInsetsCompat0) [...]

    void removeCallback(Callback systemBarStateMonitor$Callback0) {
        this.mCallbacks.remove(systemBarStateMonitor$Callback0);
    }
}

