package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import androidx.core.math.MathUtils;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class TypefaceUtils {
    public static Typeface maybeCopyWithFontWeightAdjustment(Context context0, Typeface typeface0) {
        return TypefaceUtils.maybeCopyWithFontWeightAdjustment(context0.getResources().getConfiguration(), typeface0);
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Configuration configuration0, Typeface typeface0) {
        if(Build.VERSION.SDK_INT >= 0x1F && (LinkFollowing..ExternalSyntheticApiModelOutline0.m(configuration0) != 0 && LinkFollowing..ExternalSyntheticApiModelOutline0.m(configuration0) != 0x7FFFFFFF)) {
            return typeface0 == null ? null : LinkFollowing..ExternalSyntheticApiModelOutline0.m(typeface0, MathUtils.clamp(LinkFollowing..ExternalSyntheticApiModelOutline0.m(typeface0) + LinkFollowing..ExternalSyntheticApiModelOutline0.m(configuration0), 1, 1000), typeface0.isItalic());
        }
        return null;
    }
}

