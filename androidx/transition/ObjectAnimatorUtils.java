package androidx.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.util.Property;

class ObjectAnimatorUtils {
    static class Api21Impl {
        static ObjectAnimator ofObject(Object object0, Property property0, Path path0) {
            return ObjectAnimator.ofObject(object0, property0, null, path0);
        }
    }

    static ObjectAnimator ofPointF(Object object0, Property property0, Path path0) {
        return Api21Impl.ofObject(object0, property0, path0);
    }
}

