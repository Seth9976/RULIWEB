package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

class FontCharacterParser {
    private static final Options DATA_NAMES;
    private static final Options NAMES;

    static {
        FontCharacterParser.NAMES = Options.of(new String[]{"ch", "size", "w", "style", "fFamily", "data"});
        FontCharacterParser.DATA_NAMES = Options.of(new String[]{"shapes"});
    }

    static FontCharacter parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        jsonReader0.beginObject();
        double f = 0.0;
        String s = null;
        String s1 = null;
        double f1 = 0.0;
        int v = 0;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(FontCharacterParser.NAMES)) {
                case 0: {
                    v = jsonReader0.nextString().charAt(0);
                    break;
                }
                case 1: {
                    f1 = jsonReader0.nextDouble();
                    break;
                }
                case 2: {
                    f = jsonReader0.nextDouble();
                    break;
                }
                case 3: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 4: {
                    s1 = jsonReader0.nextString();
                    break;
                }
                case 5: {
                    jsonReader0.beginObject();
                    while(jsonReader0.hasNext()) {
                        if(jsonReader0.selectName(FontCharacterParser.DATA_NAMES) == 0) {
                            jsonReader0.beginArray();
                            while(jsonReader0.hasNext()) {
                                arrayList0.add(((ShapeGroup)ContentModelParser.parse(jsonReader0, lottieComposition0)));
                            }
                            jsonReader0.endArray();
                        }
                        else {
                            jsonReader0.skipName();
                            jsonReader0.skipValue();
                        }
                    }
                    jsonReader0.endObject();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new FontCharacter(arrayList0, ((char)v), f1, f, s, s1);
    }
}

