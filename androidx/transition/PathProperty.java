package androidx.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty extends Property {
    private float mCurrentFraction;
    private final float mPathLength;
    private final PathMeasure mPathMeasure;
    private final PointF mPointF;
    private final float[] mPosition;
    private final Property mProperty;

    PathProperty(Property property0, Path path0) {
        String s = property0.getName();
        super(Float.class, s);
        this.mPosition = new float[2];
        this.mPointF = new PointF();
        this.mProperty = property0;
        PathMeasure pathMeasure0 = new PathMeasure(path0, false);
        this.mPathMeasure = pathMeasure0;
        this.mPathLength = pathMeasure0.getLength();
    }

    public Float get(Object object0) {
        return this.mCurrentFraction;
    }

    @Override  // android.util.Property
    public Object get(Object object0) {
        return this.get(object0);
    }

    public void set(Object object0, Float float0) {
        this.mCurrentFraction = (float)float0;
        this.mPathMeasure.getPosTan(this.mPathLength * ((float)float0), this.mPosition, null);
        this.mPointF.x = this.mPosition[0];
        this.mPointF.y = this.mPosition[1];
        this.mProperty.set(object0, this.mPointF);
    }

    @Override  // android.util.Property
    public void set(Object object0, Object object1) {
        this.set(object0, ((Float)object1));
    }
}

