package com.airbnb.lottie.parser;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.layer.Layer.LayerType;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LottieCompositionParser {
    static Options NAMES;

    static {
        LottieCompositionParser.NAMES = Options.of(new String[]{"w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers"});
    }

    public static LottieComposition parse(JsonReader jsonReader0) throws IOException {
        float f = Utils.dpScale();
        LongSparseArray longSparseArray0 = new LongSparseArray();
        ArrayList arrayList0 = new ArrayList();
        HashMap hashMap0 = new HashMap();
        HashMap hashMap1 = new HashMap();
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList1 = new ArrayList();
        SparseArrayCompat sparseArrayCompat0 = new SparseArrayCompat();
        LottieComposition lottieComposition0 = new LottieComposition();
        jsonReader0.beginObject();
        float f1 = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int v = 0;
        int v1 = 0;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(LottieCompositionParser.NAMES)) {
                case 0: {
                    v = jsonReader0.nextInt();
                    continue;
                }
                case 1: {
                    v1 = jsonReader0.nextInt();
                    continue;
                }
                case 2: {
                    f1 = (float)jsonReader0.nextDouble();
                    continue;
                }
                case 3: {
                    f2 = ((float)jsonReader0.nextDouble()) - 0.01f;
                    continue;
                }
                case 4: {
                    f3 = (float)jsonReader0.nextDouble();
                    continue;
                }
                case 5: {
                    String[] arr_s = jsonReader0.nextString().split("\\.");
                    if(Utils.isAtLeastVersion(Integer.parseInt(arr_s[0]), Integer.parseInt(arr_s[1]), Integer.parseInt(arr_s[2]), 4, 4, 0)) {
                        continue;
                    }
                    lottieComposition0.addWarning("Lottie only supports bodymovin >= 4.4.0");
                    continue;
                }
                case 6: {
                    LottieCompositionParser.parseLayers(jsonReader0, lottieComposition0, arrayList0, longSparseArray0);
                }
            }
            jsonReader0.skipValue();
        }
        lottieComposition0.init(new Rect(0, 0, ((int)(((float)v) * f)), ((int)(((float)v1) * f))), f1, f2, f3, arrayList0, longSparseArray0, hashMap0, hashMap1, sparseArrayCompat0, hashMap2, arrayList1);
        return lottieComposition0;
    }

    private static void parseLayers(JsonReader jsonReader0, LottieComposition lottieComposition0, List list0, LongSparseArray longSparseArray0) throws IOException {
        jsonReader0.beginArray();
        int v = 0;
        while(jsonReader0.hasNext()) {
            Layer layer0 = LayerParser.parse(jsonReader0, lottieComposition0);
            if(layer0.getLayerType() == LayerType.IMAGE) {
                ++v;
            }
            list0.add(layer0);
            longSparseArray0.put(layer0.getId(), layer0);
            if(v > 4) {
                Logger.warning(("You have " + v + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers."));
            }
        }
        jsonReader0.endArray();
    }
}

