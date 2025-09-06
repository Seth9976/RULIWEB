package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.List;

public class AnimatableValueParser {
    private static List parse(JsonReader jsonReader0, float f, LottieComposition lottieComposition0, ValueParser valueParser0) throws IOException {
        return KeyframesParser.parse(jsonReader0, lottieComposition0, f, valueParser0);
    }

    private static List parse(JsonReader jsonReader0, LottieComposition lottieComposition0, ValueParser valueParser0) throws IOException {
        return KeyframesParser.parse(jsonReader0, lottieComposition0, 1.0f, valueParser0);
    }

    static AnimatableColorValue parseColor(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatableColorValue(AnimatableValueParser.parse(jsonReader0, lottieComposition0, ColorParser.INSTANCE));
    }

    static AnimatableTextFrame parseDocumentData(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatableTextFrame(AnimatableValueParser.parse(jsonReader0, lottieComposition0, DocumentDataParser.INSTANCE));
    }

    public static AnimatableFloatValue parseFloat(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return AnimatableValueParser.parseFloat(jsonReader0, lottieComposition0, true);
    }

    // 去混淆评级： 低(20)
    public static AnimatableFloatValue parseFloat(JsonReader jsonReader0, LottieComposition lottieComposition0, boolean z) throws IOException {
        return z ? new AnimatableFloatValue(AnimatableValueParser.parse(jsonReader0, Utils.dpScale(), lottieComposition0, FloatParser.INSTANCE)) : new AnimatableFloatValue(AnimatableValueParser.parse(jsonReader0, 1.0f, lottieComposition0, FloatParser.INSTANCE));
    }

    static AnimatableGradientColorValue parseGradientColor(JsonReader jsonReader0, LottieComposition lottieComposition0, int v) throws IOException {
        return new AnimatableGradientColorValue(AnimatableValueParser.parse(jsonReader0, lottieComposition0, new GradientColorParser(v)));
    }

    static AnimatableIntegerValue parseInteger(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatableIntegerValue(AnimatableValueParser.parse(jsonReader0, lottieComposition0, IntegerParser.INSTANCE));
    }

    static AnimatablePointValue parsePoint(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatablePointValue(AnimatableValueParser.parse(jsonReader0, Utils.dpScale(), lottieComposition0, PointFParser.INSTANCE));
    }

    static AnimatableScaleValue parseScale(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatableScaleValue(AnimatableValueParser.parse(jsonReader0, lottieComposition0, ScaleXYParser.INSTANCE));
    }

    static AnimatableShapeValue parseShapeData(JsonReader jsonReader0, LottieComposition lottieComposition0) throws IOException {
        return new AnimatableShapeValue(AnimatableValueParser.parse(jsonReader0, Utils.dpScale(), lottieComposition0, ShapeDataParser.INSTANCE));
    }
}

