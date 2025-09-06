package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.util.Property;

class PropertyValuesHolderUtils {
    static class Api21Impl {
        static PropertyValuesHolder ofObject(Property property0, Path path0) {
            return PropertyValuesHolder.ofObject(property0, null, path0);
        }
    }

    static PropertyValuesHolder ofPointF(Property property0, Path path0) {
        return Api21Impl.ofObject(property0, path0);
    }
}

