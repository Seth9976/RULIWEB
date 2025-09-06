package com.google.android.material.transition.platform;

import android.graphics.RectF;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator;

public final class TransitionUtils..ExternalSyntheticLambda0 implements CornerSizeUnaryOperator {
    public final RectF f$0;

    public TransitionUtils..ExternalSyntheticLambda0(RectF rectF0) {
        this.f$0 = rectF0;
    }

    @Override  // com.google.android.material.shape.ShapeAppearanceModel$CornerSizeUnaryOperator
    public final CornerSize apply(CornerSize cornerSize0) {
        return TransitionUtils.lambda$convertToRelativeCornerSizes$0(this.f$0, cornerSize0);
    }
}

