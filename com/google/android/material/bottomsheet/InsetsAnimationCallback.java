package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat;
import androidx.core.view.WindowInsetsAnimationCompat.Callback;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.animation.AnimationUtils;
import java.util.List;

class InsetsAnimationCallback extends Callback {
    private int startTranslationY;
    private int startY;
    private final int[] tmpLocation;
    private final View view;

    public InsetsAnimationCallback(View view0) {
        super(0);
        this.tmpLocation = new int[2];
        this.view = view0;
    }

    @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
        this.view.setTranslationY(0.0f);
    }

    @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
        this.view.getLocationOnScreen(this.tmpLocation);
        this.startY = this.tmpLocation[1];
    }

    @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat0, List list0) {
        for(Object object0: list0) {
            WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = (WindowInsetsAnimationCompat)object0;
            if((windowInsetsAnimationCompat0.getTypeMask() & 8) != 0) {
                int v = this.startTranslationY;
                float f = windowInsetsAnimationCompat0.getInterpolatedFraction();
                this.view.setTranslationY(((float)AnimationUtils.lerp(v, 0, f)));
                return windowInsetsCompat0;
            }
            if(false) {
                break;
            }
        }
        return windowInsetsCompat0;
    }

    @Override  // androidx.core.view.WindowInsetsAnimationCompat$Callback
    public BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat0, BoundsCompat windowInsetsAnimationCompat$BoundsCompat0) {
        this.view.getLocationOnScreen(this.tmpLocation);
        int v = this.startY - this.tmpLocation[1];
        this.startTranslationY = v;
        this.view.setTranslationY(((float)v));
        return windowInsetsAnimationCompat$BoundsCompat0;
    }
}

