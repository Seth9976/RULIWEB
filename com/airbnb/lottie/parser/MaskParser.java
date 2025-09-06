package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask.MaskMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;

class MaskParser {
    // This method was un-flattened
    static Mask parse(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        jsonReader0.beginObject();
        MaskMode mask$MaskMode0 = null;
        AnimatableShapeValue animatableShapeValue0 = null;
        AnimatableIntegerValue animatableIntegerValue0 = null;
        boolean z = false;
        while(jsonReader0.hasNext()) {
            String s = jsonReader0.nextName();
            s.hashCode();
        alab1:
            switch(s) {
                case "inv": {
                    z = jsonReader0.nextBoolean();
                    continue;
                }
                case "mode": {
                    String s1 = jsonReader0.nextString();
                    s1.hashCode();
                    switch(s1) {
                        case "a": {
                            mask$MaskMode0 = MaskMode.MASK_MODE_ADD;
                            break alab1;
                        }
                        case "i": {
                            lottieComposition0.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            mask$MaskMode0 = MaskMode.MASK_MODE_INTERSECT;
                            break alab1;
                        }
                        case "s": {
                            mask$MaskMode0 = MaskMode.MASK_MODE_SUBTRACT;
                            break alab1;
                        }
                        default: {
                            Logger.warning(("Unknown mask mode " + s + ". Defaulting to Add."));
                            mask$MaskMode0 = MaskMode.MASK_MODE_ADD;
                            break alab1;
                        }
                    }
                }
                case "o": {
                    animatableIntegerValue0 = AnimatableValueParser.parseInteger(jsonReader0, lottieComposition0);
                    continue;
                }
                case "pt": {
                    animatableShapeValue0 = AnimatableValueParser.parseShapeData(jsonReader0, lottieComposition0);
                    continue;
                }
                default: {
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        return new Mask(mask$MaskMode0, animatableShapeValue0, animatableIntegerValue0, z);
    }
}

