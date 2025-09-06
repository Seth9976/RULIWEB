package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class PointFParser implements ValueParser {
    public static final PointFParser INSTANCE;

    static {
        PointFParser.INSTANCE = new PointFParser();
    }

    public PointF parse(JsonReader jsonReader0, float f) throws IOException {
        Token jsonReader$Token0 = jsonReader0.peek();
        if(jsonReader$Token0 == Token.BEGIN_ARRAY) {
            return JsonUtils.jsonToPoint(jsonReader0, f);
        }
        if(jsonReader$Token0 == Token.BEGIN_OBJECT) {
            return JsonUtils.jsonToPoint(jsonReader0, f);
        }
        if(jsonReader$Token0 != Token.NUMBER) {
            throw new IllegalArgumentException("Cannot convert json to point. Next token is " + jsonReader$Token0);
        }
        PointF pointF0 = new PointF(((float)jsonReader0.nextDouble()) * f, ((float)jsonReader0.nextDouble()) * f);
        while(jsonReader0.hasNext()) {
            jsonReader0.skipValue();
        }
        return pointF0;
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

