package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.PolystarShape.Type;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class PolystarShapeParser {
    private static final Options NAMES;

    static {
        PolystarShapeParser.NAMES = Options.of(new String[]{"nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd"});
    }

    static PolystarShape parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        String s = null;
        Type polystarShape$Type0 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableValue animatableValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        AnimatableFloatValue animatableFloatValue3 = null;
        AnimatableFloatValue animatableFloatValue4 = null;
        AnimatableFloatValue animatableFloatValue5 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(PolystarShapeParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    polystarShape$Type0 = Type.forValue(jsonReader0.nextInt());
                    break;
                }
                case 2: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 3: {
                    animatableValue0 = AnimatablePathValueParser.parseSplitPath(jsonReader0, lottieComposition0);
                    break;
                }
                case 4: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 5: {
                    animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    break;
                }
                case 6: {
                    animatableFloatValue5 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 7: {
                    animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    break;
                }
                case 8: {
                    animatableFloatValue4 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 9: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        return new PolystarShape(s, polystarShape$Type0, animatableFloatValue0, animatableValue0, animatableFloatValue1, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4, animatableFloatValue5, z);
    }
}

