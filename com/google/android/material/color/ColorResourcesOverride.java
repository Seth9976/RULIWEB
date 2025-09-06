package com.google.android.material.color;

import android.content.Context;
import java.util.Map;

public interface ColorResourcesOverride {
    boolean applyIfPossible(Context arg1, Map arg2);

    Context wrapContextIfPossible(Context arg1, Map arg2);
}

