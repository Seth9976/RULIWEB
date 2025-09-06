package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;

class ContentModelParser {
    private static Options NAMES;

    static {
        ContentModelParser.NAMES = Options.of(new String[]{"ty", "d"});
    }

    static ContentModel parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ContentModel contentModel0;
        String s;
        jsonReader0.beginObject();
        int v = 2;
    alab1:
        while(true) {
            s = null;
            contentModel0 = null;
            if(!jsonReader0.hasNext()) {
                break;
            }
            switch(jsonReader0.selectName(ContentModelParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break alab1;
                }
                case 1: {
                    v = jsonReader0.nextInt();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        if(s == null) {
            return null;
        }
        s.hashCode();
        switch(s) {
            case "el": {
                contentModel0 = CircleShapeParser.parse(jsonReader0, lottieComposition0, v);
                break;
            }
            case "fl": {
                contentModel0 = ShapeFillParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "gf": {
                contentModel0 = GradientFillParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "gr": {
                contentModel0 = ShapeGroupParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "gs": {
                contentModel0 = GradientStrokeParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "mm": {
                contentModel0 = MergePathsParser.parse(jsonReader0);
                lottieComposition0.addWarning("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            }
            case "rc": {
                contentModel0 = RectangleShapeParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "rp": {
                contentModel0 = RepeaterParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "sh": {
                contentModel0 = ShapePathParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "sr": {
                contentModel0 = PolystarShapeParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "st": {
                contentModel0 = ShapeStrokeParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "tm": {
                contentModel0 = ShapeTrimPathParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            case "tr": {
                contentModel0 = AnimatableTransformParser.parse(jsonReader0, lottieComposition0);
                break;
            }
            default: {
                Logger.warning(("Unknown shape type " + s));
            }
        }
        while(jsonReader0.hasNext()) {
            jsonReader0.skipValue();
        }
        jsonReader0.endObject();
        return contentModel0;
    }
}

