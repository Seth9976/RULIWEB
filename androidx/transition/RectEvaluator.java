package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

class RectEvaluator implements TypeEvaluator {
    private Rect mRect;

    RectEvaluator() {
    }

    RectEvaluator(Rect rect0) {
        this.mRect = rect0;
    }

    public Rect evaluate(float f, Rect rect0, Rect rect1) {
        int v = rect0.left + ((int)(((float)(rect1.left - rect0.left)) * f));
        int v1 = rect0.top + ((int)(((float)(rect1.top - rect0.top)) * f));
        int v2 = rect0.right + ((int)(((float)(rect1.right - rect0.right)) * f));
        int v3 = rect0.bottom + ((int)(((float)(rect1.bottom - rect0.bottom)) * f));
        Rect rect2 = this.mRect;
        if(rect2 == null) {
            return new Rect(v, v1, v2, v3);
        }
        rect2.set(v, v1, v2, v3);
        return this.mRect;
    }

    @Override  // android.animation.TypeEvaluator
    public Object evaluate(float f, Object object0, Object object1) {
        return this.evaluate(f, ((Rect)object0), ((Rect)object1));
    }
}

