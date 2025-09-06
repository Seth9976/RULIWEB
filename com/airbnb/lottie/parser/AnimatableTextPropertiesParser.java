package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class AnimatableTextPropertiesParser {
    private static Options ANIMATABLE_PROPERTIES_NAMES;
    private static Options PROPERTIES_NAMES;

    static {
        AnimatableTextPropertiesParser.PROPERTIES_NAMES = Options.of(new String[]{"a"});
        AnimatableTextPropertiesParser.ANIMATABLE_PROPERTIES_NAMES = Options.of(new String[]{"fc", "sc", "sw", "t"});
    }

    public static AnimatableTextProperties parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        jsonReader0.beginObject();
        AnimatableTextProperties animatableTextProperties0 = null;
        while(jsonReader0.hasNext()) {
            if(jsonReader0.selectName(AnimatableTextPropertiesParser.PROPERTIES_NAMES) == 0) {
                animatableTextProperties0 = AnimatableTextPropertiesParser.parseAnimatableTextProperties(jsonReader0, lottieComposition0);
            }
            else {
                jsonReader0.skipName();
                jsonReader0.skipValue();
            }
        }
        jsonReader0.endObject();
        return animatableTextProperties0 == null ? new AnimatableTextProperties(null, null, null, null) : animatableTextProperties0;
    }

    private static AnimatableTextProperties parseAnimatableTextProperties(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        jsonReader0.beginObject();
        AnimatableColorValue animatableColorValue0 = null;
        AnimatableColorValue animatableColorValue1 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(AnimatableTextPropertiesParser.ANIMATABLE_PROPERTIES_NAMES)) {
                case 0: {
                    animatableColorValue0 = AnimatableValueParser.parseColor(jsonReader0, lottieComposition0);
                    break;
                }
                case 1: {
                    animatableColorValue1 = AnimatableValueParser.parseColor(jsonReader0, lottieComposition0);
                    break;
                }
                case 2: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    break;
                }
                case 3: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new AnimatableTextProperties(animatableColorValue0, animatableColorValue1, animatableFloatValue0, animatableFloatValue1);
    }
}

