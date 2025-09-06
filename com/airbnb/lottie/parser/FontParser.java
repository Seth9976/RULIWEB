package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class FontParser {
    private static final Options NAMES;

    static {
        FontParser.NAMES = Options.of(new String[]{"fFamily", "fName", "fStyle", "ascent"});
    }

    static Font parse(JsonReader jsonReader0) throws IOException {
        jsonReader0.beginObject();
        String s = null;
        String s1 = null;
        String s2 = null;
        float f = 0.0f;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(FontParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    s1 = jsonReader0.nextString();
                    break;
                }
                case 2: {
                    s2 = jsonReader0.nextString();
                    break;
                }
                case 3: {
                    f = (float)jsonReader0.nextDouble();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new Font(s, s1, s2, f);
    }
}

