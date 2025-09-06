package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class FloatParser implements ValueParser {
    public static final FloatParser INSTANCE;

    static {
        FloatParser.INSTANCE = new FloatParser();
    }

    public Float parse(JsonReader jsonReader0, float f) throws IOException {
        return (float)(JsonUtils.valueFromObject(jsonReader0) * f);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

