package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatablePathValueParser {
    private static Options NAMES;

    static {
        AnimatablePathValueParser.NAMES = Options.of(new String[]{"k", "x", "y"});
    }

    public static AnimatablePathValue parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        if(jsonReader0.peek() == Token.BEGIN_ARRAY) {
            jsonReader0.beginArray();
            while(jsonReader0.hasNext()) {
                arrayList0.add(PathKeyframeParser.parse(jsonReader0, lottieComposition0));
            }
            jsonReader0.endArray();
            KeyframesParser.setEndFrames(arrayList0);
            return new AnimatablePathValue(arrayList0);
        }
        arrayList0.add(new Keyframe(JsonUtils.jsonToPoint(jsonReader0, Utils.dpScale())));
        return new AnimatablePathValue(arrayList0);
    }

    static AnimatableValue parseSplitPath(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        jsonReader0.beginObject();
        AnimatableValue animatableValue0 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        boolean z = false;
        while(jsonReader0.peek() != Token.END_OBJECT) {
            switch(jsonReader0.selectName(AnimatablePathValueParser.NAMES)) {
                case 0: {
                    animatableValue0 = AnimatablePathValueParser.parse(jsonReader0, lottieComposition0);
                    continue;
                }
                case 1: {
                    if(jsonReader0.peek() == Token.STRING) {
                        jsonReader0.skipValue();
                        break;
                    }
                    else {
                        animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                        continue;
                    }
                    goto label_17;
                }
                case 2: {
                label_17:
                    if(jsonReader0.peek() == Token.STRING) {
                        jsonReader0.skipValue();
                    }
                    else {
                        goto label_21;
                    }
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                    continue;
                }
            }
            z = true;
            continue;
        label_21:
            animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
        }
        jsonReader0.endObject();
        if(z) {
            lottieComposition0.addWarning("Lottie doesn\'t support expressions.");
        }
        return animatableValue0 != null ? animatableValue0 : new AnimatableSplitDimensionPathValue(animatableFloatValue0, animatableFloatValue1);
    }
}

