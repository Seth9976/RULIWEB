package com.airbnb.lottie.model.layer;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import java.util.List;
import java.util.Locale;

public class Layer {
    public static enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN;

    }

    public static enum MatteType {
        NONE,
        ADD,
        INVERT,
        UNKNOWN;

    }

    private final LottieComposition composition;
    private final boolean hidden;
    private final List inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List masks;
    private final MatteType matteType;
    private final long parentId;
    private final int preCompHeight;
    private final int preCompWidth;
    private final String refId;
    private final List shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    private final AnimatableTextFrame text;
    private final AnimatableTextProperties textProperties;
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    public Layer(List list0, LottieComposition lottieComposition0, String s, long v, LayerType layer$LayerType0, long v1, String s1, List list1, AnimatableTransform animatableTransform0, int v2, int v3, int v4, float f, float f1, int v5, int v6, AnimatableTextFrame animatableTextFrame0, AnimatableTextProperties animatableTextProperties0, List list2, MatteType layer$MatteType0, AnimatableFloatValue animatableFloatValue0, boolean z) {
        this.shapes = list0;
        this.composition = lottieComposition0;
        this.layerName = s;
        this.layerId = v;
        this.layerType = layer$LayerType0;
        this.parentId = v1;
        this.refId = s1;
        this.masks = list1;
        this.transform = animatableTransform0;
        this.solidWidth = v2;
        this.solidHeight = v3;
        this.solidColor = v4;
        this.timeStretch = f;
        this.startFrame = f1;
        this.preCompWidth = v5;
        this.preCompHeight = v6;
        this.text = animatableTextFrame0;
        this.textProperties = animatableTextProperties0;
        this.inOutKeyframes = list2;
        this.matteType = layer$MatteType0;
        this.timeRemapping = animatableFloatValue0;
        this.hidden = z;
    }

    LottieComposition getComposition() {
        return this.composition;
    }

    public long getId() {
        return this.layerId;
    }

    List getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    List getMasks() {
        return this.masks;
    }

    MatteType getMatteType() {
        return this.matteType;
    }

    String getName() {
        return this.layerName;
    }

    long getParentId() {
        return this.parentId;
    }

    int getPreCompHeight() {
        return this.preCompHeight;
    }

    int getPreCompWidth() {
        return this.preCompWidth;
    }

    String getRefId() {
        return this.refId;
    }

    List getShapes() {
        return this.shapes;
    }

    int getSolidColor() {
        return this.solidColor;
    }

    int getSolidHeight() {
        return this.solidHeight;
    }

    int getSolidWidth() {
        return this.solidWidth;
    }

    float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    AnimatableTextFrame getText() {
        return this.text;
    }

    AnimatableTextProperties getTextProperties() {
        return this.textProperties;
    }

    AnimatableFloatValue getTimeRemapping() {
        return this.timeRemapping;
    }

    float getTimeStretch() {
        return this.timeStretch;
    }

    AnimatableTransform getTransform() {
        return this.transform;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override
    public String toString() {
        return this.toString("");
    }

    public String toString(String s) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(s);
        stringBuilder0.append(this.getName());
        stringBuilder0.append("\n");
        Layer layer0 = this.composition.layerModelForId(this.getParentId());
        if(layer0 != null) {
            stringBuilder0.append("\t\tParents: ");
            stringBuilder0.append(layer0.getName());
            for(Layer layer1 = this.composition.layerModelForId(layer0.getParentId()); layer1 != null; layer1 = this.composition.layerModelForId(layer1.getParentId())) {
                stringBuilder0.append("->");
                stringBuilder0.append(layer1.getName());
            }
            stringBuilder0.append(s);
            stringBuilder0.append("\n");
        }
        if(!this.getMasks().isEmpty()) {
            stringBuilder0.append(s);
            stringBuilder0.append("\tMasks: ");
            stringBuilder0.append(this.getMasks().size());
            stringBuilder0.append("\n");
        }
        if(this.getSolidWidth() != 0 && this.getSolidHeight() != 0) {
            stringBuilder0.append(s);
            stringBuilder0.append("\tBackground: ");
            stringBuilder0.append(String.format(Locale.US, "%dx%d %X\n", this.getSolidWidth(), this.getSolidHeight(), this.getSolidColor()));
        }
        if(!this.shapes.isEmpty()) {
            stringBuilder0.append(s);
            stringBuilder0.append("\tShapes:\n");
            for(Object object0: this.shapes) {
                stringBuilder0.append(s);
                stringBuilder0.append("\t\t");
                stringBuilder0.append(object0);
                stringBuilder0.append("\n");
            }
        }
        return stringBuilder0.toString();
    }
}

