package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class KeyframesParser {
    static Options NAMES;

    static {
        KeyframesParser.NAMES = Options.of(new String[]{"k"});
    }

    static List parse(JsonReader jsonReader0, LottieComposition lottieComposition0, float f, ValueParser valueParser0) throws IOException {
        List list0 = new ArrayList();
        if(jsonReader0.peek() == Token.STRING) {
            lottieComposition0.addWarning("Lottie doesn\'t support expressions.");
            return list0;
        }
        jsonReader0.beginObject();
        while(jsonReader0.hasNext()) {
            if(jsonReader0.selectName(KeyframesParser.NAMES) != 0) {
                jsonReader0.skipValue();
            }
            else if(jsonReader0.peek() == Token.BEGIN_ARRAY) {
                jsonReader0.beginArray();
                if(jsonReader0.peek() == Token.NUMBER) {
                    list0.add(KeyframeParser.parse(jsonReader0, lottieComposition0, f, valueParser0, false));
                }
                else {
                    while(jsonReader0.hasNext()) {
                        list0.add(KeyframeParser.parse(jsonReader0, lottieComposition0, f, valueParser0, true));
                    }
                }
                jsonReader0.endArray();
            }
            else {
                list0.add(KeyframeParser.parse(jsonReader0, lottieComposition0, f, valueParser0, false));
            }
        }
        jsonReader0.endObject();
        KeyframesParser.setEndFrames(list0);
        return list0;
    }

    public static void setEndFrames(List list0) {
        int v = list0.size();
        int v1 = 0;
        while(v1 < v - 1) {
            Keyframe keyframe0 = (Keyframe)list0.get(v1);
            ++v1;
            Keyframe keyframe1 = (Keyframe)list0.get(v1);
            keyframe0.endFrame = keyframe1.startFrame;
            if(keyframe0.endValue == null && keyframe1.startValue != null) {
                keyframe0.endValue = keyframe1.startValue;
                if(keyframe0 instanceof PathKeyframe) {
                    ((PathKeyframe)keyframe0).createPath();
                }
            }
        }
        Keyframe keyframe2 = (Keyframe)list0.get(v - 1);
        if((keyframe2.startValue == null || keyframe2.endValue == null) && list0.size() > 1) {
            list0.remove(keyframe2);
        }
    }
}

