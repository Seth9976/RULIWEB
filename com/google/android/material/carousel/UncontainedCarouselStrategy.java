package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    private float calculateMediumChildSize(float f, float f1, float f2) {
        return Math.min(f1, (Math.max(1.5f * f2, f) > 0.85f * f1 ? Math.max(0.85f * f1, f2 * 1.2f) : Math.max(1.5f * f2, f)));
    }

    private KeylineState createCenterAlignedKeylineState(float f, float f1, float f2, int v, float f3, float f4, float f5) {
        float f6 = Math.min(f4, f2);
        float f7 = UncontainedCarouselStrategy.getChildMaskPercentage(f6, f2, f1);
        float f8 = UncontainedCarouselStrategy.getChildMaskPercentage(f3, f2, f1);
        float f9 = f5 + 0.0f - f3 / 2.0f;
        float f10 = f9 + f3 / 2.0f;
        float f11 = ((float)v) * f2 + f10;
        Builder keylineState$Builder0 = new Builder(f2, f).addAnchorKeyline(f9 - f3 / 2.0f - f6 / 2.0f, f7, f6).addKeyline(f9, f8, f3, false).addKeylineRange(f2 / 2.0f + f10, 0.0f, f2, v, true);
        keylineState$Builder0.addKeyline(f3 / 2.0f + f11, f8, f3, false);
        keylineState$Builder0.addAnchorKeyline(f11 + f3 + f6 / 2.0f, f7, f6);
        return keylineState$Builder0.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context context0, float f, float f1, float f2, int v, float f3, int v1, float f4) {
        float f5 = Math.min(f4, f2);
        float f6 = Math.max(f5, 0.5f * f3);
        float f7 = UncontainedCarouselStrategy.getChildMaskPercentage(f6, f2, f);
        float f8 = UncontainedCarouselStrategy.getChildMaskPercentage(f5, f2, f);
        float f9 = UncontainedCarouselStrategy.getChildMaskPercentage(f3, f2, f);
        float f10 = ((float)v) * f2 + 0.0f;
        Builder keylineState$Builder0 = new Builder(f2, f1).addAnchorKeyline(0.0f - f6 / 2.0f, f7, f6).addKeylineRange(f2 / 2.0f, 0.0f, f2, v, true);
        if(v1 > 0) {
            float f11 = f3 / 2.0f + f10;
            f10 += f3;
            keylineState$Builder0.addKeyline(f11, f9, f3, false);
        }
        keylineState$Builder0.addAnchorKeyline(f10 + CarouselStrategyHelper.getExtraSmallSize(context0) / 2.0f, f8, f5);
        return keylineState$Builder0.build();
    }

    @Override  // com.google.android.material.carousel.CarouselStrategy
    boolean isContained() {
        return false;
    }

    @Override  // com.google.android.material.carousel.CarouselStrategy
    KeylineState onFirstChildMeasuredWithMargins(Carousel carousel0, View view0) {
        int v = carousel0.isHorizontal() ? carousel0.getContainerWidth() : carousel0.getContainerHeight();
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        float f = (float)(recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin);
        float f1 = (float)view0.getMeasuredHeight();
        if(carousel0.isHorizontal()) {
            f = (float)(recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin);
            f1 = (float)view0.getMeasuredWidth();
        }
        float f2 = f1 + f;
        float f3 = CarouselStrategyHelper.getExtraSmallSize(view0.getContext());
        float f4 = CarouselStrategyHelper.getExtraSmallSize(view0.getContext()) + f;
        int v1 = Math.max(1, ((int)Math.floor(((float)v) / f2)));
        float f5 = ((float)v) - ((float)v1) * f2;
        if(carousel0.getCarouselAlignment() == 1) {
            return this.createCenterAlignedKeylineState(((float)v), f, f2, v1, Math.max(Math.min(3.0f * (f5 / 2.0f), f2), this.getSmallItemSizeMin() + f), f4, f5 / 2.0f);
        }
        return f5 > 0.0f ? this.createLeftAlignedKeylineState(view0.getContext(), f, ((float)v), f2, v1, this.calculateMediumChildSize(f3 + f, f2, f5), 1, f4) : this.createLeftAlignedKeylineState(view0.getContext(), f, ((float)v), f2, v1, this.calculateMediumChildSize(f3 + f, f2, f5), 0, f4);
    }
}

