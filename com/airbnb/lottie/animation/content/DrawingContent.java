package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

public interface DrawingContent extends Content {
    void draw(Canvas arg1, Matrix arg2, int arg3);

    void getBounds(RectF arg1, Matrix arg2, boolean arg3);
}

