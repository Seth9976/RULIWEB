package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

class ShapeGroupParser {
    private static Options NAMES;

    static {
        ShapeGroupParser.NAMES = Options.of(new String[]{"nm", "hd", "it"});
    }

    static ShapeGroup parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        String s = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
        alab1:
            switch(jsonReader0.selectName(ShapeGroupParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    continue;
                }
                case 1: {
                    z = jsonReader0.nextBoolean();
                    continue;
                }
                case 2: {
                    jsonReader0.beginArray();
                    while(true) {
                        if(!jsonReader0.hasNext()) {
                            break alab1;
                        }
                        ContentModel contentModel0 = ContentModelParser.parse(jsonReader0, lottieComposition0);
                        if(contentModel0 != null) {
                            arrayList0.add(contentModel0);
                        }
                    }
                }
                default: {
                    jsonReader0.skipValue();
                    continue;
                }
            }
            jsonReader0.endArray();
        }
        return new ShapeGroup(s, arrayList0, z);
    }
}

