package com.airbnb.lottie.animation.content;

import android.graphics.Path.FillType;
import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

public class ShapeContent implements PathContent, AnimationListener {
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path;
    private final BaseKeyframeAnimation shapeAnimation;
    private CompoundTrimPathContent trimPaths;

    public ShapeContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0, ShapePath shapePath0) {
        this.path = new Path();
        this.trimPaths = new CompoundTrimPathContent();
        this.name = shapePath0.getName();
        this.hidden = shapePath0.isHidden();
        this.lottieDrawable = lottieDrawable0;
        BaseKeyframeAnimation baseKeyframeAnimation0 = shapePath0.getShapePath().createAnimation();
        this.shapeAnimation = baseKeyframeAnimation0;
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseKeyframeAnimation0.addUpdateListener(this);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override  // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if(this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if(this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        Path path0 = (Path)this.shapeAnimation.getValue();
        this.path.set(path0);
        this.path.setFillType(Path.FillType.EVEN_ODD);
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        this.invalidate();
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
        for(int v = 0; v < list0.size(); ++v) {
            Content content0 = (Content)list0.get(v);
            if(content0 instanceof TrimPathContent && ((TrimPathContent)content0).getType() == Type.SIMULTANEOUSLY) {
                this.trimPaths.addTrimPath(((TrimPathContent)content0));
                ((TrimPathContent)content0).addListener(this);
            }
        }
    }
}

