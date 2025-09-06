package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke.LineCapType;
import com.airbnb.lottie.model.content.ShapeStroke.LineJoinType;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

class GradientStrokeParser {
    private static final Options DASH_PATTERN_NAMES;
    private static final Options GRADIENT_NAMES;
    private static Options NAMES;

    static {
        GradientStrokeParser.NAMES = Options.of(new String[]{"nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d"});
        GradientStrokeParser.GRADIENT_NAMES = Options.of(new String[]{"p", "k"});
        GradientStrokeParser.DASH_PATTERN_NAMES = Options.of(new String[]{"n", "v"});
    }

    static GradientStroke parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        String s1;
        ArrayList arrayList0 = new ArrayList();
        GradientType gradientType0 = null;
        String s = null;
        AnimatableGradientColorValue animatableGradientColorValue0 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        AnimatablePointValue animatablePointValue0 = null;
        AnimatablePointValue animatablePointValue1 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        LineCapType shapeStroke$LineCapType0 = null;
        LineJoinType shapeStroke$LineJoinType0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        float f = 0.0f;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(GradientStrokeParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    continue;
                }
                case 1: {
                    s1 = s;
                    jsonReader0.beginObject();
                    int v = -1;
                    while(jsonReader0.hasNext()) {
                        switch(jsonReader0.selectName(GradientStrokeParser.GRADIENT_NAMES)) {
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
                    continue;
                }
                case 3: {
                    s1 = s;
                    gradientType0 = jsonReader0.nextInt() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                }
                case 4: {
                    animatablePointValue0 = AnimatableValueParser.parsePoint(jsonReader0, lottieComposition0);
                    continue;
                }
                case 5: {
                    animatablePointValue1 = AnimatableValueParser.parsePoint(jsonReader0, lottieComposition0);
                    continue;
                }
                case 6: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    continue;
                }
                case 7: {
                    s1 = s;
                    LineCapType[] arr_shapeStroke$LineCapType = LineCapType.values();
                    shapeStroke$LineCapType0 = arr_shapeStroke$LineCapType[jsonReader0.nextInt() - 1];
                    break;
                }
                case 8: {
                    s1 = s;
                    LineJoinType[] arr_shapeStroke$LineJoinType = LineJoinType.values();
                    shapeStroke$LineJoinType0 = arr_shapeStroke$LineJoinType[jsonReader0.nextInt() - 1];
                    break;
                }
                case 9: {
                    s1 = s;
                    f = (float)jsonReader0.nextDouble();
                    break;
                }
                case 10: {
                    z = jsonReader0.nextBoolean();
                    continue;
                }
                case 11: {
                    jsonReader0.beginArray();
                    while(jsonReader0.hasNext()) {
                        jsonReader0.beginObject();
                        String s2 = null;
                        AnimatableFloatValue animatableFloatValue2 = null;
                        while(jsonReader0.hasNext()) {
                            switch(jsonReader0.selectName(GradientStrokeParser.DASH_PATTERN_NAMES)) {
                                case 0: {
                                    s2 = jsonReader0.nextString();
                                    break;
                                }
                                case 1: {
                                    animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                                    break;
                                }
                                default: {
                                    jsonReader0.skipName();
                                    jsonReader0.skipValue();
                                }
                            }
                        }
                        jsonReader0.endObject();
                        if(s2.equals("o")) {
                            animatableFloatValue1 = animatableFloatValue2;
                        }
                        else {
                            if(!s2.equals("d") && !s2.equals("g")) {
                                continue;
                            }
                            lottieComposition0.setHasDashPattern(true);
                            arrayList0.add(animatableFloatValue2);
                        }
                    }
                    s1 = s;
                    jsonReader0.endArray();
                    if(arrayList0.size() == 1) {
                        arrayList0.add(arrayList0.get(0));
                    }
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                    continue;
                }
            }
            s = s1;
        }
        return new GradientStroke(s, gradientType0, animatableGradientColorValue0, animatableIntegerValue0, animatablePointValue0, animatablePointValue1, animatableFloatValue0, shapeStroke$LineCapType0, shapeStroke$LineJoinType0, f, arrayList0, animatableFloatValue1, z);
    }
}

