package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;

public class MaterialAttributes {
    public static TypedValue resolve(Context context0, int v) {
        TypedValue typedValue0 = new TypedValue();
        return context0.getTheme().resolveAttribute(v, typedValue0, true) ? typedValue0 : null;
    }

    public static boolean resolveBoolean(Context context0, int v, boolean z) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        return typedValue0 == null || typedValue0.type != 18 ? z : typedValue0.data != 0;
    }

    public static boolean resolveBooleanOrThrow(Context context0, int v, String s) {
        return MaterialAttributes.resolveOrThrow(context0, v, s) != 0;
    }

    public static int resolveDimension(Context context0, int v, int v1) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        return typedValue0 == null || typedValue0.type != 5 ? ((int)context0.getResources().getDimension(v1)) : ((int)typedValue0.getDimension(context0.getResources().getDisplayMetrics()));
    }

    public static int resolveInteger(Context context0, int v, int v1) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        return typedValue0 == null || typedValue0.type != 16 ? v1 : typedValue0.data;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context0) {
        return MaterialAttributes.resolveDimension(context0, attr.minTouchTargetSize, dimen.mtrl_min_touch_target_size);
    }

    public static int resolveOrThrow(Context context0, int v, String s) {
        return MaterialAttributes.resolveTypedValueOrThrow(context0, v, s).data;
    }

    public static int resolveOrThrow(View view0, int v) {
        return MaterialAttributes.resolveTypedValueOrThrow(view0, v).data;
    }

    public static TypedValue resolveTypedValueOrThrow(Context context0, int v, String s) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, v);
        if(typedValue0 == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", s, context0.getResources().getResourceName(v)));
        }
        return typedValue0;
    }

    public static TypedValue resolveTypedValueOrThrow(View view0, int v) {
        return MaterialAttributes.resolveTypedValueOrThrow(view0.getContext(), v, view0.getClass().getCanonicalName());
    }
}

