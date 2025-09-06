package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class IntegerParser implements ValueParser {
    public static final IntegerParser INSTANCE;

    static {
        IntegerParser.INSTANCE = new IntegerParser();
    }

    public Integer parse(JsonReader jsonReader0, float f) throws IOException {
        return Math.round(JsonUtils.valueFromObject(jsonReader0) * f);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

