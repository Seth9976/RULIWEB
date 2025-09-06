package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class ColorParser implements ValueParser {
    public static final ColorParser INSTANCE;

    static {
        ColorParser.INSTANCE = new ColorParser();
    }

    public Integer parse(JsonReader jsonReader0, float f) throws IOException {
        boolean z = jsonReader0.peek() == Token.BEGIN_ARRAY;
        if(z) {
            jsonReader0.beginArray();
        }
        double f1 = jsonReader0.nextDouble();
        double f2 = jsonReader0.nextDouble();
        double f3 = jsonReader0.nextDouble();
        double f4 = jsonReader0.nextDouble();
        if(z) {
            jsonReader0.endArray();
        }
        if(f1 <= 1.0 && f2 <= 1.0 && f3 <= 1.0 && f4 <= 1.0) {
            f1 *= 255.0;
            f2 *= 255.0;
            f3 *= 255.0;
            f4 *= 255.0;
        }
        return Color.argb(((int)f4), ((int)f1), ((int)f2), ((int)f3));
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

