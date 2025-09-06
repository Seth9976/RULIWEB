package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.ScaleXY;
import java.io.IOException;

public class ScaleXYParser implements ValueParser {
    public static final ScaleXYParser INSTANCE;

    static {
        ScaleXYParser.INSTANCE = new ScaleXYParser();
    }

    public ScaleXY parse(JsonReader jsonReader0, float f) throws IOException {
        boolean z = jsonReader0.peek() == Token.BEGIN_ARRAY;
        if(z) {
            jsonReader0.beginArray();
        }
        float f1 = (float)jsonReader0.nextDouble();
        float f2 = (float)jsonReader0.nextDouble();
        while(jsonReader0.hasNext()) {
            jsonReader0.skipValue();
        }
        if(z) {
            jsonReader0.endArray();
        }
        return new ScaleXY(f1 / 100.0f * f, f2 / 100.0f * f);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

