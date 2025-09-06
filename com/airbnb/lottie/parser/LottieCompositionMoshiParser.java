package com.airbnb.lottie.parser;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
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
import java.util.Map;

public class LottieCompositionMoshiParser {
    static Options ASSETS_NAMES;
    private static final Options FONT_NAMES;
    private static final Options MARKER_NAMES;
    private static final Options NAMES;

    static {
        LottieCompositionMoshiParser.NAMES = Options.of(new String[]{"w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers"});
        LottieCompositionMoshiParser.ASSETS_NAMES = Options.of(new String[]{"id", "layers", "w", "h", "p", "u"});
        LottieCompositionMoshiParser.FONT_NAMES = Options.of(new String[]{"list"});
        LottieCompositionMoshiParser.MARKER_NAMES = Options.of(new String[]{"cm", "tm", "dr"});
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
            switch(jsonReader0.selectName(LottieCompositionMoshiParser.NAMES)) {
                case 0: {
                    v = jsonReader0.nextInt();
                    break;
                }
                case 1: {
                    v1 = jsonReader0.nextInt();
                    break;
                }
                case 2: {
                    f1 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 3: {
                    f2 = ((float)jsonReader0.nextDouble()) - 0.01f;
                    break;
                }
                case 4: {
                    f3 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 5: {
                    String[] arr_s = jsonReader0.nextString().split("\\.");
                    if(Utils.isAtLeastVersion(Integer.parseInt(arr_s[0]), Integer.parseInt(arr_s[1]), Integer.parseInt(arr_s[2]), 4, 4, 0)) {
                        continue;
                    }
                    lottieComposition0.addWarning("Lottie only supports bodymovin >= 4.4.0");
                    break;
                }
                case 6: {
                    LottieCompositionMoshiParser.parseLayers(jsonReader0, lottieComposition0, arrayList0, longSparseArray0);
                    break;
                }
                case 7: {
                    LottieCompositionMoshiParser.parseAssets(jsonReader0, lottieComposition0, hashMap0, hashMap1);
                    break;
                }
                case 8: {
                    LottieCompositionMoshiParser.parseFonts(jsonReader0, hashMap2);
                    break;
                }
                case 9: {
                    LottieCompositionMoshiParser.parseChars(jsonReader0, lottieComposition0, sparseArrayCompat0);
                    break;
                }
                case 10: {
                    LottieCompositionMoshiParser.parseMarkers(jsonReader0, lottieComposition0, arrayList1);
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        lottieComposition0.init(new Rect(0, 0, ((int)(((float)v) * f)), ((int)(((float)v1) * f))), f1, f2, f3, arrayList0, longSparseArray0, hashMap0, hashMap1, sparseArrayCompat0, hashMap2, arrayList1);
        return lottieComposition0;
    }

    private static void parseAssets(JsonReader jsonReader0, LottieComposition lottieComposition0, Map map0, Map map1) throws IOException {
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            ArrayList arrayList0 = new ArrayList();
            LongSparseArray longSparseArray0 = new LongSparseArray();
            jsonReader0.beginObject();
            String s = null;
            String s1 = null;
            String s2 = null;
            int v = 0;
            int v1 = 0;
            while(jsonReader0.hasNext()) {
                switch(jsonReader0.selectName(LottieCompositionMoshiParser.ASSETS_NAMES)) {
                    case 0: {
                        s = jsonReader0.nextString();
                        break;
                    }
                    case 1: {
                        jsonReader0.beginArray();
                        while(jsonReader0.hasNext()) {
                            Layer layer0 = LayerParser.parse(jsonReader0, lottieComposition0);
                            longSparseArray0.put(layer0.getId(), layer0);
                            arrayList0.add(layer0);
                        }
                        jsonReader0.endArray();
                        break;
                    }
                    case 2: {
                        v = jsonReader0.nextInt();
                        break;
                    }
                    case 3: {
                        v1 = jsonReader0.nextInt();
                        break;
                    }
                    case 4: {
                        s1 = jsonReader0.nextString();
                        break;
                    }
                    case 5: {
                        s2 = jsonReader0.nextString();
                        break;
                    }
                    default: {
                        jsonReader0.skipName();
                        jsonReader0.skipValue();
                    }
                }
            }
            jsonReader0.endObject();
            if(s1 == null) {
                map0.put(s, arrayList0);
            }
            else {
                LottieImageAsset lottieImageAsset0 = new LottieImageAsset(v, v1, s, s1, s2);
                map1.put(lottieImageAsset0.getId(), lottieImageAsset0);
            }
        }
        jsonReader0.endArray();
    }

    private static void parseChars(JsonReader jsonReader0, LottieComposition lottieComposition0, SparseArrayCompat sparseArrayCompat0) throws IOException {
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            FontCharacter fontCharacter0 = FontCharacterParser.parse(jsonReader0, lottieComposition0);
            sparseArrayCompat0.put(fontCharacter0.hashCode(), fontCharacter0);
        }
        jsonReader0.endArray();
    }

    private static void parseFonts(JsonReader jsonReader0, Map map0) throws IOException {
        jsonReader0.beginObject();
        while(jsonReader0.hasNext()) {
            if(jsonReader0.selectName(LottieCompositionMoshiParser.FONT_NAMES) == 0) {
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    Font font0 = FontParser.parse(jsonReader0);
                    map0.put(font0.getName(), font0);
                }
                jsonReader0.endArray();
            }
            else {
                jsonReader0.skipName();
                jsonReader0.skipValue();
            }
        }
        jsonReader0.endObject();
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

    private static void parseMarkers(JsonReader jsonReader0, LottieComposition lottieComposition0, List list0) throws IOException {
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            jsonReader0.beginObject();
            float f = 0.0f;
            String s = null;
            float f1 = 0.0f;
            while(jsonReader0.hasNext()) {
                switch(jsonReader0.selectName(LottieCompositionMoshiParser.MARKER_NAMES)) {
                    case 0: {
                        s = jsonReader0.nextString();
                        break;
                    }
                    case 1: {
                        f = (float)jsonReader0.nextDouble();
                        break;
                    }
                    case 2: {
                        f1 = (float)jsonReader0.nextDouble();
                        break;
                    }
                    default: {
                        jsonReader0.skipName();
                        jsonReader0.skipValue();
                    }
                }
            }
            jsonReader0.endObject();
            list0.add(new Marker(s, f, f1));
        }
        jsonReader0.endArray();
    }
}

