package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader.Options;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

class KeyframeParser {
    private static final Interpolator LINEAR_INTERPOLATOR = null;
    private static final float MAX_CP_VALUE = 100.0f;
    static Options NAMES;
    private static SparseArrayCompat pathInterpolatorCache;

    static {
        KeyframeParser.LINEAR_INTERPOLATOR = new LinearInterpolator();
        KeyframeParser.NAMES = Options.of(new String[]{"t", "s", "e", "o", "i", "h", "to", "ti"});
    }

    private static WeakReference getInterpolator(int v) {
        synchronized(KeyframeParser.class) {
        }
        return (WeakReference)KeyframeParser.pathInterpolatorCache().get(v);
    }

    // 去混淆评级： 低(20)
    static Keyframe parse(JsonReader jsonReader0, LottieComposition lottieComposition0, float f, ValueParser valueParser0, boolean z) throws IOException {
        return z ? KeyframeParser.parseKeyframe(lottieComposition0, jsonReader0, f, valueParser0) : KeyframeParser.parseStaticValue(jsonReader0, f, valueParser0);
    }

    private static Keyframe parseKeyframe(LottieComposition lottieComposition0, JsonReader jsonReader0, float f, ValueParser valueParser0) throws IOException {
        Interpolator interpolator2;
        Interpolator interpolator1;
        jsonReader0.beginObject();
        Interpolator interpolator0 = null;
        PointF pointF0 = null;
        PointF pointF1 = null;
        Object object0 = null;
        Object object1 = null;
        PointF pointF2 = null;
        PointF pointF3 = null;
        boolean z = false;
        float f1 = 0.0f;
        while(jsonReader0.hasNext()) {
            switch(jsonReader0.selectName(KeyframeParser.NAMES)) {
                case 0: {
                    f1 = (float)jsonReader0.nextDouble();
                    break;
                }
                case 1: {
                    object0 = valueParser0.parse(jsonReader0, f);
                    break;
                }
                case 2: {
                    object1 = valueParser0.parse(jsonReader0, f);
                    break;
                }
                case 3: {
                    pointF0 = JsonUtils.jsonToPoint(jsonReader0, f);
                    break;
                }
                case 4: {
                    pointF1 = JsonUtils.jsonToPoint(jsonReader0, f);
                    break;
                }
                case 5: {
                    if(jsonReader0.nextInt() == 1) {
                        z = true;
                        continue;
                    }
                    else {
                        z = false;
                        break;
                    }
                    pointF2 = JsonUtils.jsonToPoint(jsonReader0, f);
                    break;
                }
                case 6: {
                    pointF2 = JsonUtils.jsonToPoint(jsonReader0, f);
                    break;
                }
                case 7: {
                    pointF3 = JsonUtils.jsonToPoint(jsonReader0, f);
                    break;
                }
                default: {
                    jsonReader0.skipValue();
                }
            }
        }
        jsonReader0.endObject();
        if(z) {
            interpolator1 = KeyframeParser.LINEAR_INTERPOLATOR;
            object1 = object0;
        }
        else {
            if(pointF0 == null || pointF1 == null) {
                interpolator2 = KeyframeParser.LINEAR_INTERPOLATOR;
            }
            else {
                pointF0.x = MiscUtils.clamp(pointF0.x, -f, f);
                pointF0.y = MiscUtils.clamp(pointF0.y, -100.0f, 100.0f);
                pointF1.x = MiscUtils.clamp(pointF1.x, -f, f);
                pointF1.y = MiscUtils.clamp(pointF1.y, -100.0f, 100.0f);
                int v = Utils.hashFor(pointF0.x, pointF0.y, pointF1.x, pointF1.y);
                WeakReference weakReference0 = KeyframeParser.getInterpolator(v);
                if(weakReference0 != null) {
                    interpolator0 = (Interpolator)weakReference0.get();
                }
                if(weakReference0 == null || interpolator0 == null) {
                    Interpolator interpolator3 = PathInterpolatorCompat.create(pointF0.x / f, pointF0.y / f, pointF1.x / f, pointF1.y / f);
                    try {
                        KeyframeParser.putInterpolator(v, new WeakReference(interpolator3));
                    }
                    catch(ArrayIndexOutOfBoundsException unused_ex) {
                    }
                    interpolator2 = interpolator3;
                }
                else {
                    interpolator2 = interpolator0;
                }
            }
            interpolator1 = interpolator2;
        }
        Keyframe keyframe0 = new Keyframe(lottieComposition0, object0, object1, interpolator1, f1, null);
        keyframe0.pathCp1 = pointF2;
        keyframe0.pathCp2 = pointF3;
        return keyframe0;
    }

    private static Keyframe parseStaticValue(JsonReader jsonReader0, float f, ValueParser valueParser0) throws IOException {
        return new Keyframe(valueParser0.parse(jsonReader0, f));
    }

    private static SparseArrayCompat pathInterpolatorCache() {
        if(KeyframeParser.pathInterpolatorCache == null) {
            KeyframeParser.pathInterpolatorCache = new SparseArrayCompat();
        }
        return KeyframeParser.pathInterpolatorCache;
    }

    private static void putInterpolator(int v, WeakReference weakReference0) {
        synchronized(KeyframeParser.class) {
            KeyframeParser.pathInterpolatorCache.put(v, weakReference0);
        }
    }
}

