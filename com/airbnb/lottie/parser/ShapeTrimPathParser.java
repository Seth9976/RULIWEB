package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class ShapeTrimPathParser {
    private static Options NAMES;

    static {
        ShapeTrimPathParser.NAMES = Options.of(new String[]{"s", "e", "o", "nm", "m", "hd"});
    }

    static ShapeTrimPath parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        String s = null;
        Type shapeTrimPath$Type0 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(ShapeTrimPathParser.NAMES)) {
                case 0: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 1: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 2: {
                    animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 3: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 4: {
                    shapeTrimPath$Type0 = Type.forId(jsonReader0.nextInt());
                    break;
                }
                case 5: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipValue();
                }
            }
        }
        return new ShapeTrimPath(s, shapeTrimPath$Type0, animatableFloatValue0, animatableFloatValue1, animatableFloatValue2, z);
    }
}

