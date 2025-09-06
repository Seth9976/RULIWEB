package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class PathParser implements ValueParser {
    public static final PathParser INSTANCE;

    static {
        PathParser.INSTANCE = new PathParser();
    }

    public PointF parse(JsonReader jsonReader0, float f) throws IOException {
        return JsonUtils.jsonToPoint(jsonReader0, f);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

