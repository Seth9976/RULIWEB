package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JsonUtils {
    private static final Options POINT_NAMES;

    static {
        JsonUtils.POINT_NAMES = Options.of(new String[]{"x", "y"});
    }

    private static PointF jsonArrayToPoint(JsonReader jsonReader0, float f) throws IOException {
        jsonReader0.beginArray();
        float f1 = (float)jsonReader0.nextDouble();
        float f2 = (float)jsonReader0.nextDouble();
        while(jsonReader0.peek() != Token.END_ARRAY) {
            jsonReader0.skipValue();
        }
        jsonReader0.endArray();
        return new PointF(f1 * f, f2 * f);
    }

    private static PointF jsonNumbersToPoint(JsonReader jsonReader0, float f) throws IOException {
        float f1 = (float)jsonReader0.nextDouble();
        float f2 = (float)jsonReader0.nextDouble();
        while(jsonReader0.hasNext()) {
            jsonReader0.skipValue();
        }
        return new PointF(f1 * f, f2 * f);
    }

    private static PointF jsonObjectToPoint(JsonReader jsonReader0, float f) throws IOException {
        jsonReader0.beginObject();
        float f1 = 0.0f;
        float f2 = 0.0f;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(JsonUtils.POINT_NAMES)) {
                case 0: {
                    f1 = JsonUtils.valueFromObject(jsonReader0);
                    break;
                }
                case 1: {
                    f2 = JsonUtils.valueFromObject(jsonReader0);
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new PointF(f1 * f, f2 * f);
    }

    static int jsonToColor(JsonReader jsonReader0) throws IOException {
        jsonReader0.beginArray();
        double f = jsonReader0.nextDouble();
        double f1 = jsonReader0.nextDouble();
        double f2 = jsonReader0.nextDouble();
        while(jsonReader0.hasNext()) {
            jsonReader0.skipValue();
        }
        jsonReader0.endArray();
        return Color.argb(0xFF, ((int)(f * 255.0)), ((int)(f1 * 255.0)), ((int)(f2 * 255.0)));
    }

    static PointF jsonToPoint(JsonReader jsonReader0, float f) throws IOException {
        switch(com.airbnb.lottie.parser.JsonUtils.1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[jsonReader0.peek().ordinal()]) {
            case 1: {
                return JsonUtils.jsonNumbersToPoint(jsonReader0, f);
            }
            case 2: {
                return JsonUtils.jsonArrayToPoint(jsonReader0, f);
            }
            case 3: {
                return JsonUtils.jsonObjectToPoint(jsonReader0, f);
            }
            default: {
                throw new IllegalArgumentException("Unknown point starts with " + jsonReader0.peek());
            }
        }
    }

    static List jsonToPoints(JsonReader jsonReader0, float f) throws IOException {
        List list0 = new ArrayList();
        jsonReader0.beginArray();
        while(jsonReader0.peek() == Token.BEGIN_ARRAY) {
            jsonReader0.beginArray();
            list0.add(JsonUtils.jsonToPoint(jsonReader0, f));
            jsonReader0.endArray();
        }
        jsonReader0.endArray();
        return list0;
    }

    static float valueFromObject(JsonReader jsonReader0) throws IOException {
        Token jsonReader$Token0 = jsonReader0.peek();
        switch(com.airbnb.lottie.parser.JsonUtils.1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[jsonReader$Token0.ordinal()]) {
            case 1: {
                return (float)jsonReader0.nextDouble();
            }
            case 2: {
                jsonReader0.beginArray();
                float f = (float)jsonReader0.nextDouble();
                while(jsonReader0.hasNext()) {
                    jsonReader0.skipValue();
                }
                jsonReader0.endArray();
                return f;
            }
            default: {
                throw new IllegalArgumentException("Unknown value for token of type " + jsonReader$Token0);
            }
        }
    }

    class com.airbnb.lottie.parser.JsonUtils.1 {
        static final int[] $SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token;

        static {
            int[] arr_v = new int[Token.values().length];
            com.airbnb.lottie.parser.JsonUtils.1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token = arr_v;
            try {
                arr_v[Token.NUMBER.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.parser.JsonUtils.1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[Token.BEGIN_ARRAY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.parser.JsonUtils.1.$SwitchMap$com$airbnb$lottie$parser$moshi$JsonReader$Token[Token.BEGIN_OBJECT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

