package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class CircleShapeParser {
    private static Options NAMES;

    static {
        CircleShapeParser.NAMES = Options.of(new String[]{"nm", "p", "s", "hd", "d"});
    }

    static CircleShape parse(JsonReader jsonReader0, LottieComposition lottieComposition0, int v) throws IOException {
        boolean z = v == 3;
        String s = null;
        AnimatableValue animatableValue0 = null;
        AnimatablePointValue animatablePointValue0 = null;
        boolean z1 = false;
        while(jsonReader0.hasNext()) {
            int v1 = jsonReader0.selectName(CircleShapeParser.NAMES);
            switch(v1) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    animatableValue0 = AnimatablePathValueParser.parseSplitPath(jsonReader0, lottieComposition0);
                    break;
                }
                case 2: {
                    animatablePointValue0 = AnimatableValueParser.parsePoint(jsonReader0, lottieComposition0);
                    break;
                }
                case 3: {
                    z1 = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    if(v1 != 4) {
                        jsonReader0.skipName();
                        jsonReader0.skipValue();
                        continue;
                    }
                    else if(jsonReader0.nextInt() == 3) {
                        z = true;
                        continue;
                    }
                    else {
                        z = false;
                        break;
                    }
                    s = jsonReader0.nextString();
                    break;
                }
            }
        }
        return new CircleShape(s, animatableValue0, animatablePointValue0, z, z1);
    }
}

