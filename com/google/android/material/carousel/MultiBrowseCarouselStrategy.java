package com.google.android.material.carousel;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

public final class MultiBrowseCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS;
    private static final int[] SMALL_COUNTS;
    private int keylineCount;

    static {
        MultiBrowseCarouselStrategy.SMALL_COUNTS = new int[]{1};
        MultiBrowseCarouselStrategy.MEDIUM_COUNTS = new int[]{1, 0};
    }

    public MultiBrowseCarouselStrategy() {
        this.keylineCount = 0;
    }

    boolean ensureArrangementFitsItemCount(Arrangement arrangement0, int v) {
        int v1 = arrangement0.getItemCount() - v;
        boolean z = v1 > 0 && (arrangement0.smallCount > 0 || arrangement0.mediumCount > 1);
        while(v1 > 0) {
            if(arrangement0.smallCount > 0) {
                --arrangement0.smallCount;
            }
            else if(arrangement0.mediumCount > 1) {
                --arrangement0.mediumCount;
            }
            --v1;
        }
        return z;
    }

    @Override  // com.google.android.material.carousel.CarouselStrategy
    KeylineState onFirstChildMeasuredWithMargins(Carousel carousel0, View view0) {
        float f = (float)carousel0.getContainerHeight();
        if(carousel0.isHorizontal()) {
            f = (float)carousel0.getContainerWidth();
        }
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        float f1 = (float)(recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin);
        float f2 = (float)view0.getMeasuredHeight();
        if(carousel0.isHorizontal()) {
            f1 = (float)(recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin);
            f2 = (float)view0.getMeasuredWidth();
        }
        float f3 = this.getSmallItemSizeMin() + f1;
        float f4 = Math.max(this.getSmallItemSizeMax() + f1, f3);
        float f5 = Math.min(f2 + f1, f);
        float f6 = MathUtils.clamp(f2 / 3.0f + f1, f3 + f1, f4 + f1);
        int[] arr_v = f < 2.0f * f3 ? new int[]{0} : MultiBrowseCarouselStrategy.SMALL_COUNTS;
        int[] arr_v1 = MultiBrowseCarouselStrategy.MEDIUM_COUNTS;
        if(carousel0.getCarouselAlignment() == 1) {
            arr_v = MultiBrowseCarouselStrategy.doubleCounts(arr_v);
            arr_v1 = MultiBrowseCarouselStrategy.doubleCounts(arr_v1);
        }
        float f7 = (f5 + f6) / 2.0f;
        double f8 = Math.floor((f - ((float)CarouselStrategyHelper.maxValue(arr_v1)) * f7 - ((float)CarouselStrategyHelper.maxValue(arr_v)) * f4) / f5);
        int v = (int)Math.ceil(f / f5);
        int v1 = v - ((int)Math.max(1.0, f8)) + 1;
        int[] arr_v2 = new int[v1];
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_v2[v2] = v - v2;
        }
        Arrangement arrangement0 = Arrangement.findLowestCostArrangement(f, f6, f3, f4, arr_v, f7, arr_v1, f5, arr_v2);
        this.keylineCount = arrangement0.getItemCount();
        if(this.ensureArrangementFitsItemCount(arrangement0, carousel0.getItemCount())) {
            arrangement0 = Arrangement.findLowestCostArrangement(f, f6, f3, f4, new int[]{arrangement0.smallCount}, f7, new int[]{arrangement0.mediumCount}, f5, new int[]{arrangement0.largeCount});
        }
        return CarouselStrategyHelper.createKeylineState(view0.getContext(), f1, f, arrangement0, carousel0.getCarouselAlignment());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.carousel.CarouselStrategy
    boolean shouldRefreshKeylineState(Carousel carousel0, int v) {
        return v < this.keylineCount && carousel0.getItemCount() >= this.keylineCount || v >= this.keylineCount && carousel0.getItemCount() < this.keylineCount;
    }
}

