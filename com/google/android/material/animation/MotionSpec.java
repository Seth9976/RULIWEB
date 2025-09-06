package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Property;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.List;

public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final SimpleArrayMap propertyValues;
    private final SimpleArrayMap timings;

    public MotionSpec() {
        this.timings = new SimpleArrayMap();
        this.propertyValues = new SimpleArrayMap();
    }

    private static void addInfoFromAnimator(MotionSpec motionSpec0, Animator animator0) {
        if(!(animator0 instanceof ObjectAnimator)) {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator0);
        }
        motionSpec0.setPropertyValues(((ObjectAnimator)animator0).getPropertyName(), ((ObjectAnimator)animator0).getValues());
        motionSpec0.setTiming(((ObjectAnimator)animator0).getPropertyName(), MotionTiming.createFromAnimator(((ObjectAnimator)animator0)));
    }

    private PropertyValuesHolder[] clonePropertyValuesHolder(PropertyValuesHolder[] arr_propertyValuesHolder) {
        PropertyValuesHolder[] arr_propertyValuesHolder1 = new PropertyValuesHolder[arr_propertyValuesHolder.length];
        for(int v = 0; v < arr_propertyValuesHolder.length; ++v) {
            arr_propertyValuesHolder1[v] = arr_propertyValuesHolder[v].clone();
        }
        return arr_propertyValuesHolder1;
    }

    public static MotionSpec createFromAttribute(Context context0, TypedArray typedArray0, int v) {
        if(typedArray0.hasValue(v)) {
            int v1 = typedArray0.getResourceId(v, 0);
            return v1 == 0 ? null : MotionSpec.createFromResource(context0, v1);
        }
        return null;
    }

    public static MotionSpec createFromResource(Context context0, int v) {
        try {
            Animator animator0 = AnimatorInflater.loadAnimator(context0, v);
            if(animator0 instanceof AnimatorSet) {
                return MotionSpec.createSpecFromAnimators(((AnimatorSet)animator0).getChildAnimations());
            }
            if(animator0 != null) {
                ArrayList arrayList0 = new ArrayList();
                arrayList0.add(animator0);
                return MotionSpec.createSpecFromAnimators(arrayList0);
            }
        }
        catch(Exception exception0) {
            Log.w("MotionSpec", "Can\'t load animation resource ID #0x" + Integer.toHexString(v), exception0);
        }
        return null;
    }

    private static MotionSpec createSpecFromAnimators(List list0) {
        MotionSpec motionSpec0 = new MotionSpec();
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            MotionSpec.addInfoFromAnimator(motionSpec0, ((Animator)list0.get(v1)));
        }
        return motionSpec0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof MotionSpec ? this.timings.equals(((MotionSpec)object0).timings) : false;
    }

    public ObjectAnimator getAnimator(String s, Object object0, Property property0) {
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofPropertyValuesHolder(object0, this.getPropertyValues(s));
        objectAnimator0.setProperty(property0);
        this.getTiming(s).apply(objectAnimator0);
        return objectAnimator0;
    }

    public PropertyValuesHolder[] getPropertyValues(String s) {
        if(!this.hasPropertyValues(s)) {
            throw new IllegalArgumentException();
        }
        return this.clonePropertyValuesHolder(((PropertyValuesHolder[])this.propertyValues.get(s)));
    }

    public MotionTiming getTiming(String s) {
        if(!this.hasTiming(s)) {
            throw new IllegalArgumentException();
        }
        return (MotionTiming)this.timings.get(s);
    }

    public long getTotalDuration() {
        int v = this.timings.size();
        long v1 = 0L;
        for(int v2 = 0; v2 < v; ++v2) {
            MotionTiming motionTiming0 = (MotionTiming)this.timings.valueAt(v2);
            v1 = Math.max(v1, motionTiming0.getDelay() + motionTiming0.getDuration());
        }
        return v1;
    }

    public boolean hasPropertyValues(String s) {
        return this.propertyValues.get(s) != null;
    }

    public boolean hasTiming(String s) {
        return this.timings.get(s) != null;
    }

    @Override
    public int hashCode() {
        return this.timings.hashCode();
    }

    public void setPropertyValues(String s, PropertyValuesHolder[] arr_propertyValuesHolder) {
        this.propertyValues.put(s, arr_propertyValuesHolder);
    }

    public void setTiming(String s, MotionTiming motionTiming0) {
        this.timings.put(s, motionTiming0);
    }

    @Override
    public String toString() {
        return "\n" + this.getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}

