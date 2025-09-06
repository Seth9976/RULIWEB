package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.layer.Layer.LayerType;
import com.airbnb.lottie.model.layer.Layer.MatteType;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayerParser {
    private static final Options EFFECTS_NAMES;
    private static final Options NAMES;
    private static final Options TEXT_NAMES;

    static {
        LayerParser.NAMES = Options.of(new String[]{"nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd"});
        LayerParser.TEXT_NAMES = Options.of(new String[]{"d", "a"});
        LayerParser.EFFECTS_NAMES = Options.of(new String[]{"nm"});
    }

    public static Layer parse(LottieComposition lottieComposition0) {
        Rect rect0 = lottieComposition0.getBounds();
        List list0 = Collections.EMPTY_LIST;
        List list1 = Collections.EMPTY_LIST;
        AnimatableTransform animatableTransform0 = new AnimatableTransform();
        int v = rect0.width();
        int v1 = rect0.height();
        return new Layer(list0, lottieComposition0, "__container", -1L, LayerType.PRE_COMP, -1L, null, list1, animatableTransform0, 0, 0, 0, 0.0f, 0.0f, v, v1, null, null, Collections.EMPTY_LIST, MatteType.NONE, null, false);
    }

    public static Layer parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        ArrayList arrayList4;
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        jsonReader0.beginObject();
        String s = "UNSET";
        AnimatableTransform animatableTransform0 = null;
        AnimatableTextFrame animatableTextFrame0 = null;
        AnimatableTextProperties animatableTextProperties0 = null;
        AnimatableFloatValue animatableFloatValue0 = null;
        long v = 0L;
        long v1 = -1L;
        MatteType layer$MatteType0 = MatteType.NONE;
        float f = 0.0f;
        int v2 = 0;
        int v3 = 0;
        float f1 = 1.0f;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        float f2 = 0.0f;
        boolean z = false;
        String s1 = null;
        LayerType layer$LayerType0 = null;
        String s2 = null;
        float f3 = 0.0f;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(LayerParser.NAMES)) {
                case 0: {
                    s = jsonReader0.nextString();
                    break;
                }
                case 1: {
                    v = (long)jsonReader0.nextInt();
                    break;
                }
                case 2: {
                    s2 = jsonReader0.nextString();
                    break;
                }
                case 3: {
                    int v7 = jsonReader0.nextInt();
                    if(v7 < LayerType.UNKNOWN.ordinal()) {
                        layer$LayerType0 = LayerType.values()[v7];
                        continue;
                    }
                    else {
                        layer$LayerType0 = LayerType.UNKNOWN;
                        break;
                    }
                    v1 = (long)jsonReader0.nextInt();
                    break;
                }
                case 4: {
                    v1 = (long)jsonReader0.nextInt();
                    break;
                }
                case 5: {
                    v4 = (int)(((float)jsonReader0.nextInt()) * Utils.dpScale());
                    break;
                }
                case 6: {
                    v2 = (int)(((float)jsonReader0.nextInt()) * Utils.dpScale());
                    break;
                }
                case 7: {
                    v3 = Color.parseColor(jsonReader0.nextString());
                    break;
                }
                case 8: {
                    animatableTransform0 = AnimatableTransformParser.parse(jsonReader0, lottieComposition0);
                    break;
                }
                case 9: {
                    MatteType[] arr_layer$MatteType = MatteType.values();
                    layer$MatteType0 = arr_layer$MatteType[jsonReader0.nextInt()];
                    lottieComposition0.incrementMatteOrMaskCount(1);
                    break;
                }
                case 10: {
                    jsonReader0.beginArray();
                    while(jsonReader0.hasNext()) {
                        arrayList0.add(MaskParser.parse(jsonReader0, lottieComposition0));
                    }
                    lottieComposition0.incrementMatteOrMaskCount(arrayList0.size());
                    jsonReader0.endArray();
                    break;
                }
                case 11: {
                    jsonReader0.beginArray();
                    while(jsonReader0.hasNext()) {
                        ContentModel contentModel0 = ContentModelParser.parse(jsonReader0, lottieComposition0);
                        if(contentModel0 != null) {
                            arrayList1.add(contentModel0);
                        }
                    }
                    jsonReader0.endArray();
                    break;
                }
                case 12: {
                    jsonReader0.beginObject();
                    while(jsonReader0.hasNext()) {
                    alab1:
                        switch(jsonReader0.selectName(LayerParser.TEXT_NAMES)) {
                            case 0: {
                                animatableTextFrame0 = AnimatableValueParser.parseDocumentData(jsonReader0, lottieComposition0);
                                continue;
                            }
                            case 1: {
                                jsonReader0.beginArray();
                                if(jsonReader0.hasNext()) {
                                    animatableTextProperties0 = AnimatableTextPropertiesParser.parse(jsonReader0, lottieComposition0);
                                }
                                while(true) {
                                    if(!jsonReader0.hasNext()) {
                                        break alab1;
                                    }
                                    jsonReader0.skipValue();
                                }
                            }
                            default: {
                                jsonReader0.skipName();
                                jsonReader0.skipValue();
                                continue;
                            }
                        }
                        jsonReader0.endArray();
                    }
                    jsonReader0.endObject();
                    break;
                }
                case 13: {
                    jsonReader0.beginArray();
                    ArrayList arrayList2 = new ArrayList();
                    while(jsonReader0.hasNext()) {
                        jsonReader0.beginObject();
                        while(jsonReader0.hasNext()) {
                            if(jsonReader0.selectName(LayerParser.EFFECTS_NAMES) == 0) {
                                arrayList2.add(jsonReader0.nextString());
                            }
                            else {
                                jsonReader0.skipName();
                                jsonReader0.skipValue();
                            }
                        }
                        jsonReader0.endObject();
                    }
                    jsonReader0.endArray();
                    lottieComposition0.addWarning("Lottie doesn\'t support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList2);
                    break;
                }
                case 14: {
                    f1 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 15: {
                    f2 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 16: {
                    v5 = (int)(((float)jsonReader0.nextInt()) * Utils.dpScale());
                    break;
                }
                case 17: {
                    v6 = (int)(((float)jsonReader0.nextInt()) * Utils.dpScale());
                    break;
                }
                case 18: {
                    f = (float)jsonReader0.nextDouble();
                    break;
                }
                case 19: {
                    f3 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 20: {
                    animatableFloatValue0 = AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, false);
                    break;
                }
                case 21: {
                    s1 = jsonReader0.nextString();
                    break;
                }
                case 22: {
                    z = jsonReader0.nextBoolean();
                    break;
                }
                default: {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        float f4 = f / f1;
        float f5 = f3 / f1;
        ArrayList arrayList3 = new ArrayList();
        if(f4 > 0.0f) {
            arrayList4 = arrayList3;
            arrayList4.add(new Keyframe(lottieComposition0, 0.0f, 0.0f, null, 0.0f, f4));
        }
        else {
            arrayList4 = arrayList3;
        }
        if(f5 <= 0.0f) {
            f5 = lottieComposition0.getEndFrame();
        }
        arrayList4.add(new Keyframe(lottieComposition0, 1.0f, 1.0f, null, f4, f5));
        arrayList4.add(new Keyframe(lottieComposition0, 0.0f, 0.0f, null, f5, 3.402823E+38f));
        if(s.endsWith(".ai") || "ai".equals(s1)) {
            lottieComposition0.addWarning("Convert your Illustrator layers to shape layers.");
        }
        return new Layer(arrayList1, lottieComposition0, s, v, layer$LayerType0, v1, s2, arrayList0, animatableTransform0, v4, v2, v3, f1, f2, v5, v6, animatableTextFrame0, animatableTextProperties0, arrayList4, layer$MatteType0, animatableFloatValue0, z);
    }
}

