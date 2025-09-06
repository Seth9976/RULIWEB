package com.airbnb.lottie.parser;

import android.graphics.Path.FillType;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class GradientFillParser {
    private static final Options GRADIENT_NAMES;
    private static final Options NAMES;

    static {
        GradientFillParser.NAMES = Options.of(new String[]{"nm", "g", "o", "t", "s", "e", "r", "hd"});
        GradientFillParser.GRADIENT_NAMES = Options.of(new String[]{"p", "k"});
    }

    static GradientFill parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        Path.FillType path$FillType0 = Path.FillType.WINDING;
        String s = null;
        GradientType gradientType0 = null;
        AnimatableGradientColorValue animatableGradientColorValue0 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        AnimatablePointValue animatablePointValue0 = null;
        AnimatablePointValue animatablePointValue1 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(GradientFillParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    jsonReader0.beginObject();
                    int v = -1;
                    while(jsonReader0.hasNext()) {
                        switch(jsonReader0.selectName(GradientFillParser.GRADIENT_NAMES)) {
                            case 0: {
                                v = jsonReader0.nextInt();
                                break;
                            }
                            case 1: {
                                animatableGradientColorValue0 = AnimatableValueParser.parseGradientColor(jsonReader0, lottieComposition0, v);
                                break;
                            }
                            default: {
                                jsonReader0.skipName();
                                jsonReader0.skipValue();
                            }
                        }
                    }
                    jsonReader0.endObject();
                    break;
                }
                case 2: {
                    animatableIntegerValue0 = AnimatableValueParser.parseInteger(jsonReader0, lottieComposition0);
                    break;
                }
                case 3: {
                    gradientType0 = jsonReader0.nextInt() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                }
                case 4: {
                    animatablePointValue0 = AnimatableValueParser.parsePoint(jsonReader0, lottieComposition0);
                    break;
                }
                case 5: {
                    animatablePointValue1 = AnimatableValueParser.parsePoint(jsonReader0, lottieComposition0);
                    break;
                }
                case 6: {
                    path$FillType0 = jsonReader0.nextInt() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                }
                case 7: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        return new GradientFill(s, gradientType0, path$FillType0, animatableGradientColorValue0, animatableIntegerValue0, animatablePointValue0, animatablePointValue1, null, null, z);
    }
}

