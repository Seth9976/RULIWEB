package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

abstract class CarouselOrientationHelper {
    final int orientation;

    private CarouselOrientationHelper(int v) {
        this.orientation = v;
    }

    CarouselOrientationHelper(int v, com.google.android.material.carousel.CarouselOrientationHelper.1 carouselOrientationHelper$10) {
        this(v);
    }

    abstract void containMaskWithinBounds(RectF arg1, RectF arg2, RectF arg3);

    private static CarouselOrientationHelper createHorizontalHelper(CarouselLayoutManager carouselLayoutManager0) {
        return new CarouselOrientationHelper(0, carouselLayoutManager0) {
            final CarouselLayoutManager val$carouselLayoutManager;

            {
                this.val$carouselLayoutManager = carouselLayoutManager0;
                super(v, null);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void containMaskWithinBounds(RectF rectF0, RectF rectF1, RectF rectF2) {
                if(rectF1.left < rectF2.left && rectF1.right > rectF2.left) {
                    float f = rectF2.left - rectF1.left;
                    rectF0.left += f;
                    rectF1.left += f;
                }
                if(rectF1.right > rectF2.right && rectF1.left < rectF2.right) {
                    float f1 = rectF1.right - rectF2.right;
                    rectF0.right = Math.max(rectF0.right - f1, rectF0.left);
                    rectF1.right = Math.max(rectF1.right - f1, rectF1.left);
                }
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getDecoratedCrossAxisMeasurement(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.val$carouselLayoutManager.getDecoratedMeasuredHeight(view0) + recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin;
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public float getMaskMargins(LayoutParams recyclerView$LayoutParams0) {
                return (float)(recyclerView$LayoutParams0.rightMargin + recyclerView$LayoutParams0.leftMargin);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF getMaskRect(float f, float f1, float f2, float f3) {
                return new RectF(f3, 0.0f, f1 - f3, f);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentBottom() {
                return this.val$carouselLayoutManager.getHeight() - this.val$carouselLayoutManager.getPaddingBottom();
            }

            // 去混淆评级： 低(20)
            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentEnd() {
                return this.val$carouselLayoutManager.isLayoutRtl() ? 0 : this.getParentRight();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentLeft() [...] // Inlined contents

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentRight() {
                return this.val$carouselLayoutManager.getWidth();
            }

            // 去混淆评级： 低(20)
            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentStart() {
                return this.val$carouselLayoutManager.isLayoutRtl() ? this.getParentRight() : 0;
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentTop() {
                return this.val$carouselLayoutManager.getPaddingTop();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void layoutDecoratedWithMargins(View view0, int v, int v1) {
                int v2 = this.getParentTop();
                int v3 = this.getDecoratedCrossAxisMeasurement(view0);
                this.val$carouselLayoutManager.layoutDecoratedWithMargins(view0, v, v2, v1, v2 + v3);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void moveMaskOnEdgeOutsideBounds(RectF rectF0, RectF rectF1, RectF rectF2) {
                if(rectF1.right <= rectF2.left) {
                    rectF0.right = ((float)Math.floor(rectF0.right)) - 1.0f;
                    rectF0.left = Math.min(rectF0.left, rectF0.right);
                }
                if(rectF1.left >= rectF2.right) {
                    rectF0.left = ((float)Math.ceil(rectF0.left)) + 1.0f;
                    rectF0.right = Math.max(rectF0.left, rectF0.right);
                }
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void offsetChild(View view0, Rect rect0, float f, float f1) {
                view0.offsetLeftAndRight(((int)(f1 - (((float)rect0.left) + f))));
            }
        };
    }

    static CarouselOrientationHelper createOrientationHelper(CarouselLayoutManager carouselLayoutManager0, int v) {
        switch(v) {
            case 0: {
                return CarouselOrientationHelper.createHorizontalHelper(carouselLayoutManager0);
            }
            case 1: {
                return CarouselOrientationHelper.createVerticalHelper(carouselLayoutManager0);
            }
            default: {
                throw new IllegalArgumentException("invalid orientation");
            }
        }
    }

    private static CarouselOrientationHelper createVerticalHelper(CarouselLayoutManager carouselLayoutManager0) {
        return new CarouselOrientationHelper(1, carouselLayoutManager0) {
            final CarouselLayoutManager val$carouselLayoutManager;

            {
                this.val$carouselLayoutManager = carouselLayoutManager0;
                super(v, null);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void containMaskWithinBounds(RectF rectF0, RectF rectF1, RectF rectF2) {
                if(rectF1.top < rectF2.top && rectF1.bottom > rectF2.top) {
                    float f = rectF2.top - rectF1.top;
                    rectF0.top += f;
                    rectF2.top += f;
                }
                if(rectF1.bottom > rectF2.bottom && rectF1.top < rectF2.bottom) {
                    float f1 = rectF1.bottom - rectF2.bottom;
                    rectF0.bottom = Math.max(rectF0.bottom - f1, rectF0.top);
                    rectF1.bottom = Math.max(rectF1.bottom - f1, rectF1.top);
                }
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getDecoratedCrossAxisMeasurement(View view0) {
                LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                return this.val$carouselLayoutManager.getDecoratedMeasuredWidth(view0) + recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin;
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public float getMaskMargins(LayoutParams recyclerView$LayoutParams0) {
                return (float)(recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF getMaskRect(float f, float f1, float f2, float f3) {
                return new RectF(0.0f, f2, f1, f - f2);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentBottom() {
                return this.val$carouselLayoutManager.getHeight();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentEnd() {
                return this.getParentBottom();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentLeft() {
                return this.val$carouselLayoutManager.getPaddingLeft();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentRight() {
                return this.val$carouselLayoutManager.getWidth() - this.val$carouselLayoutManager.getPaddingRight();
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentStart() {
                return 0;
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            int getParentTop() [...] // Inlined contents

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void layoutDecoratedWithMargins(View view0, int v, int v1) {
                int v2 = this.getParentLeft();
                int v3 = this.getDecoratedCrossAxisMeasurement(view0);
                this.val$carouselLayoutManager.layoutDecoratedWithMargins(view0, v2, v, v2 + v3, v1);
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void moveMaskOnEdgeOutsideBounds(RectF rectF0, RectF rectF1, RectF rectF2) {
                if(rectF1.bottom <= rectF2.top) {
                    rectF0.bottom = ((float)Math.floor(rectF0.bottom)) - 1.0f;
                    rectF0.top = Math.min(rectF0.top, rectF0.bottom);
                }
                if(rectF1.top >= rectF2.bottom) {
                    rectF0.top = ((float)Math.ceil(rectF0.top)) + 1.0f;
                    rectF0.bottom = Math.max(rectF0.top, rectF0.bottom);
                }
            }

            @Override  // com.google.android.material.carousel.CarouselOrientationHelper
            public void offsetChild(View view0, Rect rect0, float f, float f1) {
                view0.offsetTopAndBottom(((int)(f1 - (((float)rect0.top) + f))));
            }
        };
    }

    abstract int getDecoratedCrossAxisMeasurement(View arg1);

    abstract float getMaskMargins(LayoutParams arg1);

    abstract RectF getMaskRect(float arg1, float arg2, float arg3, float arg4);

    abstract int getParentBottom();

    abstract int getParentEnd();

    abstract int getParentLeft();

    abstract int getParentRight();

    abstract int getParentStart();

    abstract int getParentTop();

    abstract void layoutDecoratedWithMargins(View arg1, int arg2, int arg3);

    abstract void moveMaskOnEdgeOutsideBounds(RectF arg1, RectF arg2, RectF arg3);

    abstract void offsetChild(View arg1, Rect arg2, float arg3, float arg4);
}

