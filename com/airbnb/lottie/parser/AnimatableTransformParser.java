package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import java.io.IOException;

public class AnimatableTransformParser {
    private static Options ANIMATABLE_NAMES;
    private static Options NAMES;

    static {
        AnimatableTransformParser.NAMES = Options.of(new String[]{"a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa"});
        AnimatableTransformParser.ANIMATABLE_NAMES = Options.of(new String[]{"k"});
    }

    // 去混淆评级： 低(20)
    private static boolean isAnchorPointIdentity(AnimatablePathValue animatablePathValue0) {
        return animatablePathValue0 == null || animatablePathValue0.isStatic() && ((PointF)((Keyframe)animatablePathValue0.getKeyframes().get(0)).startValue).equals(0.0f, 0.0f);
    }

    // 去混淆评级： 低(30)
    private static boolean isPositionIdentity(AnimatableValue animatableValue0) {
        return animatableValue0 == null || !(animatableValue0 instanceof AnimatableSplitDimensionPathValue) && animatableValue0.isStatic() && ((PointF)((Keyframe)animatableValue0.getKeyframes().get(0)).startValue).equals(0.0f, 0.0f);
    }

    private static boolean isRotationIdentity(AnimatableFloatValue animatableFloatValue0) {
        return animatableFloatValue0 == null || animatableFloatValue0.isStatic() && ((float)(((Float)((Keyframe)animatableFloatValue0.getKeyframes().get(0)).startValue))) == 0.0f;
    }

    // 去混淆评级： 低(20)
    private static boolean isScaleIdentity(AnimatableScaleValue animatableScaleValue0) {
        return animatableScaleValue0 == null || animatableScaleValue0.isStatic() && ((ScaleXY)((Keyframe)animatableScaleValue0.getKeyframes().get(0)).startValue).equals(1.0f, 1.0f);
    }

    private static boolean isSkewAngleIdentity(AnimatableFloatValue animatableFloatValue0) {
        return animatableFloatValue0 == null || animatableFloatValue0.isStatic() && ((float)(((Float)((Keyframe)animatableFloatValue0.getKeyframes().get(0)).startValue))) == 0.0f;
    }

    private static boolean isSkewIdentity(AnimatableFloatValue animatableFloatValue0) {
        return animatableFloatValue0 == null || animatableFloatValue0.isStatic() && ((float)(((Float)((Keyframe)animatableFloatValue0.getKeyframes().get(0)).startValue))) == 0.0f;
    }

    public static AnimatableTransform parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        boolean z = jsonReader0.peek() == Token.BEGIN_OBJECT;
        if(z) {
            jsonReader0.beginObject();
        }
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatablePathValue animatablePathValue0 = null;
        AnimatableValue animatableValue0 = null;
        AnimatableScaleValue animatableScaleValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        AnimatableFloatValue animatableFloatValue4 = null;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(AnimatableTransformParser.NAMES)) {
                case 0: {
                    jsonReader0.beginObject();
                    while(jsonReader0.hasNext()) {
                        if(jsonReader0.selectName(AnimatableTransformParser.ANIMATABLE_NAMES) == 0) {
                            animatablePathValue0 = AnimatablePathValueParser.parse(jsonReader0, lottieComposition0);
                        }
                        else {
                            jsonReader0.skipName();
                            jsonReader0.skipValue();
                        }
                    }
                    jsonReader0.endObject();
                    break;
                }
                case 1: {
                    animatableValue0 = AnimatablePathValueParser.parseSplitPath(jsonReader0, lottieComposition0);
                    break;
                }
                case 2: {
                    animatableScaleValue0 = AnimatableValueParser.parseScale(jsonReader0, lottieComposition0);
                    break;
                }
                case 3: {
                    lottieComposition0.addWarning("Lottie doesn\'t support 3D layers.");
                    goto label_32;
                }
                case 4: {
                label_32:
                    AnimatableFloatValue animatableFloatValue5 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    if(animatableFloatValue5.getKeyframes().isEmpty()) {
                        animatableFloatValue5.getKeyframes().add(new Keyframe(lottieComposition0, 0.0f, 0.0f, null, 0.0f, lottieComposition0.getEndFrame()));
                    }
                    else if(((Keyframe)animatableFloatValue5.getKeyframes().get(0)).startValue == null) {
                        animatableFloatValue5.getKeyframes().set(0, new Keyframe(lottieComposition0, 0.0f, 0.0f, null, 0.0f, lottieComposition0.getEndFrame()));
                    }
                    animatableFloatValue0 = animatableFloatValue5;
                    break;
                }
                case 5: {
                    animatableIntegerValue0 = AnimatableValueParser.parseInteger(jsonReader0, lottieComposition0);
                    break;
                }
                case 6: {
                    animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 7: {
                    animatableFloatValue4 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 8: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 9: {
                    animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        if(z) {
            jsonReader0.endObject();
        }
        AnimatablePathValue animatablePathValue1 = AnimatableTransformParser.isAnchorPointIdentity(animatablePathValue0) ? null : animatablePathValue0;
        if(AnimatableTransformParser.isPositionIdentity(animatableValue0)) {
            animatableValue0 = null;
        }
        AnimatableFloatValue animatableFloatValue6 = AnimatableTransformParser.isRotationIdentity(animatableFloatValue0) ? null : animatableFloatValue0;
        AnimatableScaleValue animatableScaleValue1 = AnimatableTransformParser.isScaleIdentity(animatableScaleValue0) ? null : animatableScaleValue0;
        AnimatableFloatValue animatableFloatValue7 = AnimatableTransformParser.isSkewIdentity(animatableFloatValue1) ? null : animatableFloatValue1;
        return AnimatableTransformParser.isSkewAngleIdentity(animatableFloatValue2) ? new AnimatableTransform(animatablePathValue1, animatableValue0, animatableScaleValue1, animatableFloatValue6, animatableIntegerValue0, animatableFloatValue3, animatableFloatValue4, animatableFloatValue7, null) : new AnimatableTransform(animatablePathValue1, animatableValue0, animatableScaleValue1, animatableFloatValue6, animatableIntegerValue0, animatableFloatValue3, animatableFloatValue4, animatableFloatValue7, animatableFloatValue2);
    }
}

