package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeStroke.LineCapType;
import com.airbnb.lottie.model.content.ShapeStroke.LineJoinType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

class ShapeStrokeParser {
    private static final Options DASH_PATTERN_NAMES;
    private static Options NAMES;

    static {
        ShapeStrokeParser.NAMES = Options.of(new String[]{"nm", "c", "w", "o", "lc", "lj", "ml", "hd", "d"});
        ShapeStrokeParser.DASH_PATTERN_NAMES = Options.of(new String[]{"n", "v"});
    }

    static ShapeStroke parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        String s = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        AnimatableColorValue animatableColorValue0 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        AnimatableFloatValue animatableFloatValue1 = null;
        LineCapType shapeStroke$LineCapType0 = null;
        LineJoinType shapeStroke$LineJoinType0 = null;
        float f = 0.0f;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(ShapeStrokeParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    continue;
                }
                case 1: {
                    animatableColorValue0 = AnimatableValueParser.parseColor(jsonReader0, lottieComposition0);
                    continue;
                }
                case 2: {
                    animatableFloatValue1 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0);
                    continue;
                }
                case 3: {
                    animatableIntegerValue0 = AnimatableValueParser.parseInteger(jsonReader0, lottieComposition0);
                    continue;
                }
                case 4: {
                    LineCapType[] arr_shapeStroke$LineCapType = LineCapType.values();
                    shapeStroke$LineCapType0 = arr_shapeStroke$LineCapType[jsonReader0.nextInt() - 1];
                    continue;
                }
                case 5: {
                    LineJoinType[] arr_shapeStroke$LineJoinType = LineJoinType.values();
                    shapeStroke$LineJoinType0 = arr_shapeStroke$LineJoinType[jsonReader0.nextInt() - 1];
                    continue;
                }
                case 6: {
                    f = (float)jsonReader0.nextDouble();
                    continue;
                }
                case 7: {
                    z = jsonReader0.nextBoolean();
                    continue;
                }
                case 8: {
                    jsonReader0.beginArray();
                    while(jsonReader0.hasNext()) {
                        jsonReader0.beginObject();
                        String s1 = null;
                        AnimatableFloatValue animatableFloatValue2 = null;
                        while(jsonReader0.hasNext()) {
                            switch(jsonReader0.selectName(ShapeStrokeParser.DASH_PATTERN_NAMES)) {
                                case 0: {
                                    s1 = jsonReader0.nextString();
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
                        s1.hashCode();
                        switch(s1) {
                            case "d": 
                            case "g": {
                                lottieComposition0.setHasDashPattern(true);
                                arrayList0.add(animatableFloatValue2);
                                continue;
                            }
                            case "o": {
                                animatableFloatValue0 = animatableFloatValue2;
                            }
                        }
                    }
                    jsonReader0.endArray();
                    if(arrayList0.size() == 1) {
                        break;
                    }
                    continue;
                }
                default: {
                    jsonReader0.skipValue();
                    continue;
                }
            }
            arrayList0.add(arrayList0.get(0));
        }
        return new ShapeStroke(s, animatableFloatValue0, arrayList0, animatableColorValue0, animatableIntegerValue0, animatableFloatValue1, shapeStroke$LineCapType0, shapeStroke$LineJoinType0, f, z);
    }
}

