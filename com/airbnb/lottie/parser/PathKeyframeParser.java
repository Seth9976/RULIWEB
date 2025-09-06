package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;

class PathKeyframeParser {
    static PathKeyframe parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return jsonReader0.peek() == Token.BEGIN_OBJECT ? new PathKeyframe(lottieComposition0, KeyframeParser.parse(jsonReader0, lottieComposition0, Utils.dpScale(), PathParser.INSTANCE, true)) : new PathKeyframe(lottieComposition0, KeyframeParser.parse(jsonReader0, lottieComposition0, Utils.dpScale(), PathParser.INSTANCE, false));
    }
}

