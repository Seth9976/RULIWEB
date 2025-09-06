package com.airbnb.lottie.animation.content;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class TrimPathContent implements Content, AnimationListener {
    private final BaseKeyframeAnimation endAnimation;
    private final boolean hidden;
    private final List listeners;
    private final String name;
    private final BaseKeyframeAnimation offsetAnimation;
    private final BaseKeyframeAnimation startAnimation;
    private final Type type;

    public TrimPathContent(BaseLayer baseLayer0, ShapeTrimPath shapeTrimPath0) {
        this.listeners = new ArrayList();
        this.name = shapeTrimPath0.getName();
        this.hidden = shapeTrimPath0.isHidden();
        this.type = shapeTrimPath0.getType();
        BaseKeyframeAnimation baseKeyframeAnimation0 = shapeTrimPath0.getStart().createAnimation();
        this.startAnimation = baseKeyframeAnimation0;
        BaseKeyframeAnimation baseKeyframeAnimation1 = shapeTrimPath0.getEnd().createAnimation();
        this.endAnimation = baseKeyframeAnimation1;
        BaseKeyframeAnimation baseKeyframeAnimation2 = shapeTrimPath0.getOffset().createAnimation();
        this.offsetAnimation = baseKeyframeAnimation2;
        baseLayer0.addAnimation(baseKeyframeAnimation0);
        baseLayer0.addAnimation(baseKeyframeAnimation1);
        baseLayer0.addAnimation(baseKeyframeAnimation2);
        baseKeyframeAnimation0.addUpdateListener(this);
        baseKeyframeAnimation1.addUpdateListener(this);
        baseKeyframeAnimation2.addUpdateListener(this);
    }

    void addListener(AnimationListener baseKeyframeAnimation$AnimationListener0) {
        this.listeners.add(baseKeyframeAnimation$AnimationListener0);
    }

    public BaseKeyframeAnimation getEnd() {
        return this.endAnimation;
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    public BaseKeyframeAnimation getOffset() {
        return this.offsetAnimation;
    }

    public BaseKeyframeAnimation getStart() {
        return this.startAnimation;
    }

    Type getType() {
        return this.type;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener
    public void onValueChanged() {
        for(int v = 0; v < this.listeners.size(); ++v) {
            ((AnimationListener)this.listeners.get(v)).onValueChanged();
        }
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
    }
}

