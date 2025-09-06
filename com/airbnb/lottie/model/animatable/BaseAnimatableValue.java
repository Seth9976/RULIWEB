package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseAnimatableValue implements AnimatableValue {
    final List keyframes;

    BaseAnimatableValue(Object object0) {
        this(Collections.singletonList(new Keyframe(object0)));
    }

    BaseAnimatableValue(List list0) {
        this.keyframes = list0;
    }

    @Override  // com.airbnb.lottie.model.animatable.AnimatableValue
    public List getKeyframes() {
        return this.keyframes;
    }

    // 去混淆评级： 低(30)
    @Override  // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean isStatic() {
        return this.keyframes.isEmpty() || this.keyframes.size() == 1 && ((Keyframe)this.keyframes.get(0)).isStatic();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        if(!this.keyframes.isEmpty()) {
            stringBuilder0.append("values=");
            stringBuilder0.append(Arrays.toString(this.keyframes.toArray()));
        }
        return stringBuilder0.toString();
    }
}

