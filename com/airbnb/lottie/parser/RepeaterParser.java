package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class RepeaterParser {
    private static Options NAMES;

    static {
        RepeaterParser.NAMES = Options.of(new String[]{"nm", "c", "o", "tr", "hd"});
    }

    static Repeater parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        String s = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        AnimatableTransform animatableTransform0 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(RepeaterParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 2: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 3: {
                    animatableTransform0 = AnimatableTransformParser.parse(jsonReader0, lottieComposition0);
                    break;
                }
                case 4: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipValue();
                }
            }
        }
        return new Repeater(s, animatableFloatValue0, animatableFloatValue1, animatableTransform0, z);
    }
}

