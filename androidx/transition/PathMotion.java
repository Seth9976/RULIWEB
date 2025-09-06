package androidx.transition;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

public abstract class PathMotion {
    public PathMotion() {
    }

    public PathMotion(Context context0, AttributeSet attributeSet0) {
    }

    public abstract Path getPath(float arg1, float arg2, float arg3, float arg4);
}

