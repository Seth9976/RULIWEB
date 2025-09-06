package com.google.android.material.carousel;

import android.content.Context;
import com.google.android.material.R.dimen;

final class CarouselStrategyHelper {
    static float addEnd(float f, float f1, int v) {
        return f + ((float)Math.max(0, v - 1)) * f1;
    }

    static float addStart(float f, float f1, int v) {
        return v <= 0 ? f : f + f1 / 2.0f;
    }

    static KeylineState createCenterAlignedKeylineState(Context context0, float f, float f1, Arrangement arrangement0) {
        float f2 = Math.min(CarouselStrategyHelper.getExtraSmallSize(context0) + f, arrangement0.largeSize);
        float f3 = CarouselStrategyHelper.addStart(0.0f, arrangement0.smallSize, arrangement0.smallCount);
        float f4 = CarouselStrategyHelper.updateCurPosition(0.0f, CarouselStrategyHelper.addEnd(f3, arrangement0.smallSize, ((int)Math.floor(((float)arrangement0.smallCount) / 2.0f))), arrangement0.smallSize, arrangement0.smallCount);
        float f5 = CarouselStrategyHelper.addStart(f4, arrangement0.mediumSize, arrangement0.mediumCount);
        float f6 = CarouselStrategyHelper.updateCurPosition(f4, CarouselStrategyHelper.addEnd(f5, arrangement0.mediumSize, ((int)Math.floor(((float)arrangement0.mediumCount) / 2.0f))), arrangement0.mediumSize, arrangement0.mediumCount);
        float f7 = CarouselStrategyHelper.addStart(f6, arrangement0.largeSize, arrangement0.largeCount);
        float f8 = CarouselStrategyHelper.updateCurPosition(f6, CarouselStrategyHelper.addEnd(f7, arrangement0.largeSize, arrangement0.largeCount), arrangement0.largeSize, arrangement0.largeCount);
        float f9 = CarouselStrategyHelper.addStart(f8, arrangement0.mediumSize, arrangement0.mediumCount);
        float f10 = CarouselStrategyHelper.addStart(CarouselStrategyHelper.updateCurPosition(f8, CarouselStrategyHelper.addEnd(f9, arrangement0.mediumSize, ((int)Math.ceil(((float)arrangement0.mediumCount) / 2.0f))), arrangement0.mediumSize, arrangement0.mediumCount), arrangement0.smallSize, arrangement0.smallCount);
        float f11 = 1.0f - (f2 - f) / (arrangement0.largeSize - f);
        float f12 = 1.0f - (arrangement0.smallSize - f) / (arrangement0.largeSize - f);
        float f13 = 1.0f - (arrangement0.mediumSize - f) / (arrangement0.largeSize - f);
        Builder keylineState$Builder0 = new Builder(arrangement0.largeSize, f1).addAnchorKeyline(0.0f - f2 / 2.0f, f11, f2);
        if(arrangement0.smallCount > 0) {
            keylineState$Builder0.addKeylineRange(f3, f12, arrangement0.smallSize, ((int)Math.floor(((float)arrangement0.smallCount) / 2.0f)));
        }
        if(arrangement0.mediumCount > 0) {
            keylineState$Builder0.addKeylineRange(f5, f13, arrangement0.mediumSize, ((int)Math.floor(((float)arrangement0.mediumCount) / 2.0f)));
        }
        keylineState$Builder0.addKeylineRange(f7, 0.0f, arrangement0.largeSize, arrangement0.largeCount, true);
        if(arrangement0.mediumCount > 0) {
            keylineState$Builder0.addKeylineRange(f9, f13, arrangement0.mediumSize, ((int)Math.ceil(((float)arrangement0.mediumCount) / 2.0f)));
        }
        if(arrangement0.smallCount > 0) {
            keylineState$Builder0.addKeylineRange(f10, f12, arrangement0.smallSize, ((int)Math.ceil(((float)arrangement0.smallCount) / 2.0f)));
        }
        keylineState$Builder0.addAnchorKeyline(f2 / 2.0f + f1, f11, f2);
        return keylineState$Builder0.build();
    }

    static KeylineState createKeylineState(Context context0, float f, float f1, Arrangement arrangement0, int v) {
        return v == 1 ? CarouselStrategyHelper.createCenterAlignedKeylineState(context0, f, f1, arrangement0) : CarouselStrategyHelper.createLeftAlignedKeylineState(context0, f, f1, arrangement0);
    }

    static KeylineState createLeftAlignedKeylineState(Context context0, float f, float f1, Arrangement arrangement0) {
        float f2 = Math.min(CarouselStrategyHelper.getExtraSmallSize(context0) + f, arrangement0.largeSize);
        float f3 = CarouselStrategyHelper.addStart(0.0f, arrangement0.largeSize, arrangement0.largeCount);
        float f4 = CarouselStrategyHelper.updateCurPosition(0.0f, CarouselStrategyHelper.addEnd(f3, arrangement0.largeSize, arrangement0.largeCount), arrangement0.largeSize, arrangement0.largeCount);
        float f5 = CarouselStrategyHelper.addStart(f4, arrangement0.mediumSize, arrangement0.mediumCount);
        float f6 = CarouselStrategyHelper.addStart(CarouselStrategyHelper.updateCurPosition(f4, f5, arrangement0.mediumSize, arrangement0.mediumCount), arrangement0.smallSize, arrangement0.smallCount);
        float f7 = 1.0f - (f2 - f) / (arrangement0.largeSize - f);
        float f8 = 1.0f - (arrangement0.smallSize - f) / (arrangement0.largeSize - f);
        float f9 = 1.0f - (arrangement0.mediumSize - f) / (arrangement0.largeSize - f);
        Builder keylineState$Builder0 = new Builder(arrangement0.largeSize, f1).addAnchorKeyline(0.0f - f2 / 2.0f, f7, f2).addKeylineRange(f3, 0.0f, arrangement0.largeSize, arrangement0.largeCount, true);
        if(arrangement0.mediumCount > 0) {
            keylineState$Builder0.addKeyline(f5, f9, arrangement0.mediumSize);
        }
        if(arrangement0.smallCount > 0) {
            keylineState$Builder0.addKeylineRange(f6, f8, arrangement0.smallSize, arrangement0.smallCount);
        }
        keylineState$Builder0.addAnchorKeyline(f2 / 2.0f + f1, f7, f2);
        return keylineState$Builder0.build();
    }

    static float getExtraSmallSize(Context context0) {
        return context0.getResources().getDimension(dimen.m3_carousel_gone_size);
    }

    static float getSmallSizeMax(Context context0) {
        return context0.getResources().getDimension(dimen.m3_carousel_small_item_size_max);
    }

    static float getSmallSizeMin(Context context0) {
        return context0.getResources().getDimension(dimen.m3_carousel_small_item_size_min);
    }

    static int maxValue(int[] arr_v) [...] // 潜在的解密器

    static float updateCurPosition(float f, float f1, float f2, int v) {
        return v <= 0 ? f : f1 + f2 / 2.0f;
    }
}

