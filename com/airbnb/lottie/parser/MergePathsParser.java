package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.content.MergePaths.MergePathsMode;
import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class MergePathsParser {
    private static final Options NAMES;

    static {
        MergePathsParser.NAMES = Options.of(new String[]{"nm", "mm", "hd"});
    }

    static MergePaths parse(JsonReader jsonReader0) throws IOException {
        String s = null;
        MergePathsMode mergePaths$MergePathsMode0 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(MergePathsParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    mergePaths$MergePathsMode0 = MergePathsMode.forId(jsonReader0.nextInt());
                    break;
                }
                case 2: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        return new MergePaths(s, mergePaths$MergePathsMode0, z);
    }
}

