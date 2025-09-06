package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader.Token;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeDataParser implements ValueParser {
    public static final ShapeDataParser INSTANCE;
    private static final Options NAMES;

    static {
        ShapeDataParser.INSTANCE = new ShapeDataParser();
        ShapeDataParser.NAMES = Options.of(new String[]{"c", "v", "i", "o"});
    }

    public ShapeData parse(JsonReader jsonReader0, float f) throws IOException {
        if(jsonReader0.peek() == Token.BEGIN_ARRAY) {
            jsonReader0.beginArray();
        }
        jsonReader0.beginObject();
        List list0 = null;
        List list1 = null;
        List list2 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(ShapeDataParser.NAMES)) {
                case 0: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                case 1: {
                    list0 = JsonUtils.jsonToPoints(jsonReader0, f);
                    break;
                }
                case 2: {
                    list1 = JsonUtils.jsonToPoints(jsonReader0, f);
                    break;
                }
                case 3: {
                    list2 = JsonUtils.jsonToPoints(jsonReader0, f);
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        if(jsonReader0.peek() == Token.END_ARRAY) {
            jsonReader0.endArray();
        }
        if(list0 == null || list1 == null || list2 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if(list0.isEmpty()) {
            return new ShapeData(new PointF(), false, Collections.EMPTY_LIST);
        }
        int v = list0.size();
        PointF pointF0 = (PointF)list0.get(0);
        ArrayList arrayList0 = new ArrayList(v);
        for(int v1 = 1; v1 < v; ++v1) {
            PointF pointF1 = (PointF)list0.get(v1);
            PointF pointF2 = (PointF)list0.get(v1 - 1);
            PointF pointF3 = (PointF)list2.get(v1 - 1);
            PointF pointF4 = (PointF)list1.get(v1);
            arrayList0.add(new CubicCurveData(MiscUtils.addPoints(pointF2, pointF3), MiscUtils.addPoints(pointF1, pointF4), pointF1));
        }
        if(z) {
            PointF pointF5 = (PointF)list0.get(0);
            PointF pointF6 = (PointF)list0.get(v - 1);
            PointF pointF7 = (PointF)list2.get(v - 1);
            PointF pointF8 = (PointF)list1.get(0);
            arrayList0.add(new CubicCurveData(MiscUtils.addPoints(pointF6, pointF7), MiscUtils.addPoints(pointF5, pointF8), pointF5));
        }
        return new ShapeData(pointF0, z, arrayList0);
    }

    @Override  // com.airbnb.lottie.parser.ValueParser
    public Object parse(JsonReader jsonReader0, float f) throws IOException {
        return this.parse(jsonReader0, f);
    }
}

