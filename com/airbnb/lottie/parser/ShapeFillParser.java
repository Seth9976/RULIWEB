package com.airbnb.lottie.parser;

import android.graphics.Path.FillType;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class ShapeFillParser {
    private static final Options NAMES;

    static {
        ShapeFillParser.NAMES = Options.of(new String[]{"nm", "c", "o", "fillEnabled", "r", "hd"});
    }

    static ShapeFill parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        String s = null;
        AnimatableColorValue animatableColorValue0 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        int v = 1;
        boolean z = false;
        boolean z1 = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(ShapeFillParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    animatableColorValue0 = AnimatableValueParser.parseColor(jsonReader0, lottieComposition0);
                    break;
                }
                case 2: {
                    animatableIntegerValue0 = AnimatableValueParser.parseInteger(jsonReader0, lottieComposition0);
                    break;
                }
                case 3: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                case 4: {
                    v = jsonReader0.nextInt();
                    break;
                }
                case 5: {
                    z1 = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        return v == 1 ? new ShapeFill(s, z, Path.FillType.WINDING, animatableColorValue0, animatableIntegerValue0, z1) : new ShapeFill(s, z, Path.FillType.EVEN_ODD, animatableColorValue0, animatableIntegerValue0, z1);
    }
}

