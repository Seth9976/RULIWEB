package com.google.android.material.carousel;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS;
    private static final int[] SMALL_COUNTS;
    private int keylineCount;

    static {
        HeroCarouselStrategy.SMALL_COUNTS = new int[]{1};
        HeroCarouselStrategy.MEDIUM_COUNTS = new int[]{0, 1};
    }

    public HeroCarouselStrategy() {
        this.keylineCount = 0;
    }

    @Override  // com.google.android.material.carousel.CarouselStrategy
    KeylineState onFirstChildMeasuredWithMargins(Carousel carousel0, View view0) {
        int v = carousel0.getContainerHeight();
        if(carousel0.isHorizontal()) {
            v = carousel0.getContainerWidth();
        }
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        float f = (float)(recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin);
        float f1 = (float)(view0.getMeasuredWidth() * 2);
        if(carousel0.isHorizontal()) {
            f = (float)(recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin);
            f1 = (float)(view0.getMeasuredHeight() * 2);
        }
        float f2 = this.getSmallItemSizeMin() + f;
        float f3 = Math.max(this.getSmallItemSizeMax() + f, f2);
        float f4 = Math.min(f1 + f, v);
        float f5 = MathUtils.clamp(f1 / 3.0f + f, f2 + f, f3 + f);
        float f6 = (f4 + f5) / 2.0f;
        int[] arr_v = ((float)v) < 2.0f * f2 ? new int[]{0} : HeroCarouselStrategy.SMALL_COUNTS;
        int v1 = (int)Math.max(1.0, Math.floor((((float)v) - 1.0f * f3) / f4));
        int v2 = ((int)Math.ceil(((float)v) / f4)) - v1 + 1;
        int[] arr_v1 = new int[v2];
        for(int v3 = 0; v3 < v2; ++v3) {
            arr_v1[v3] = v1 + v3;
        }
        int v4 = carousel0.getCarouselAlignment() == 1 ? 1 : 0;
        Arrangement arrangement0 = Arrangement.findLowestCostArrangement(v, f5, f2, f3, (v4 == 0 ? arr_v : HeroCarouselStrategy.doubleCounts(arr_v)), f6, (v4 == 0 ? HeroCarouselStrategy.MEDIUM_COUNTS : HeroCarouselStrategy.doubleCounts(HeroCarouselStrategy.MEDIUM_COUNTS)), f4, arr_v1);
        this.keylineCount = arrangement0.getItemCount();
        if(arrangement0.getItemCount() > carousel0.getItemCount()) {
            arrangement0 = Arrangement.findLowestCostArrangement(v, f5, f2, f3, arr_v, f6, HeroCarouselStrategy.MEDIUM_COUNTS, f4, arr_v1);
            return CarouselStrategyHelper.createKeylineState(view0.getContext(), f, ((float)v), arrangement0, 0);
        }
        return CarouselStrategyHelper.createKeylineState(view0.getContext(), f, ((float)v), arrangement0, v4);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.carousel.CarouselStrategy
    boolean shouldRefreshKeylineState(Carousel carousel0, int v) {
        return carousel0.getCarouselAlignment() == 1 && (v < this.keylineCount && carousel0.getItemCount() >= this.keylineCount || v >= this.keylineCount && carousel0.getItemCount() < this.keylineCount);
    }
}

