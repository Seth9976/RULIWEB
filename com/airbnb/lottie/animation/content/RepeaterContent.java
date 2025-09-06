package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class RepeaterContent implements DrawingContent, GreedyContent, KeyPathElementContent, PathContent, AnimationListener {
    private ContentGroup contentGroup;
    private final BaseKeyframeAnimation copies;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final String name;
    private final BaseKeyframeAnimation offset;
    private final Path path;
    private final TransformKeyframeAnimation transform;

    public RepeaterContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, Repeater repeater0) {
        this.matrix = new Matrix();
        this.path = new Path();
        this.lottieDrawable = lottieDrawable0;
        this.layer = baseLayer0;
        this.name = repeater0.getName();
        this.hidden = repeater0.isHidden();
        BaseKeyframeAnimation baseKeyframeAnimation0 = repeater0.getCopies().createAnimation();
        this.copies = baseKeyframeAnimation0;
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseKeyframeAnimation0.addUpdateListener(this);
        BaseKeyframeAnimation baseKeyframeAnimation1 = repeater0.getOffset().createAnimation();
        this.offset = baseKeyframeAnimation1;
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        baseKeyframeAnimation1.addUpdateListener(this);
        TransformKeyframeAnimation transformKeyframeAnimation0 = repeater0.getTransform().createAnimation();
        this.transform = transformKeyframeAnimation0;
        transformKeyframeAnimation0.addAnimationsToLayer(baseLayer0);
        transformKeyframeAnimation0.addListener(this);
    }

    @Override  // com.airbnb.lottie.animation.content.GreedyContent
    public void absorbContent(ListIterator listIterator0) {
        if(this.contentGroup != null) {
            return;
        }
        while(listIterator0.hasPrevious() && listIterator0.previous() != this) {
        }
        ArrayList arrayList0 = new ArrayList();
        while(listIterator0.hasPrevious()) {
            arrayList0.add(listIterator0.previous());
            listIterator0.remove();
        }
        Collections.reverse(arrayList0);
        this.contentGroup = new ContentGroup(this.lottieDrawable, this.layer, "Repeater", this.hidden, arrayList0, null);
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        if(!this.transform.applyValueCallback(object0, lottieValueCallback0)) {
            if(object0 == LottieProperty.REPEATER_COPIES) {
                this.copies.setValueCallback(lottieValueCallback0);
                return;
            }
            if(object0 == LottieProperty.REPEATER_OFFSET) {
                this.offset.setValueCallback(lottieValueCallback0);
            }
        }
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas0, Matrix matrix0, int v) {
        float f = (float)(((Float)this.copies.getValue()));
        float f1 = (float)(((Float)this.offset.getValue()));
        float f2 = (float)(((Float)this.transform.getStartOpacity().getValue()));
        float f3 = (float)(((Float)this.transform.getEndOpacity().getValue()));
        for(int v1 = ((int)f) - 1; v1 >= 0; --v1) {
            this.matrix.set(matrix0);
            Matrix matrix1 = this.transform.getMatrixForRepeater(((float)v1) + f1);
            this.matrix.preConcat(matrix1);
            this.contentGroup.draw(canvas0, this.matrix, ((int)(((float)v) * MiscUtils.lerp(f2 / 100.0f, f3 / 100.0f, ((float)v1) / f))));
        }
    }

    @Override  // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        this.contentGroup.getBounds(rectF0, matrix0, z);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override  // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        Path path0 = this.contentGroup.getPath();
        this.path.reset();
        float f = (float)(((Float)this.copies.getValue()));
        float f1 = (float)(((Float)this.offset.getValue()));
        for(int v = ((int)f) - 1; v >= 0; --v) {
            Matrix matrix0 = this.transform.getMatrixForRepeater(((float)v) + f1);
            this.matrix.set(matrix0);
            this.path.addPath(path0, this.matrix);
        }
        return this.path;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override  // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1) {
        MiscUtils.resolveKeyPath(keyPath0, v, list0, keyPath1, this);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
        this.contentGroup.setContents(list0, list1);
    }
}

