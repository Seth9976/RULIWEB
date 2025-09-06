package com.google.android.material.animation;

import android.util.Property;
import android.view.ViewGroup;
import com.google.android.material.R.id;

public class ChildrenAlphaProperty extends Property {
    public static final Property CHILDREN_ALPHA;

    static {
        ChildrenAlphaProperty.CHILDREN_ALPHA = new ChildrenAlphaProperty("childrenAlpha");
    }

    private ChildrenAlphaProperty(String s) {
        super(Float.class, s);
    }

    public Float get(ViewGroup viewGroup0) {
        Float float0 = (Float)viewGroup0.getTag(id.mtrl_internal_children_alpha_tag);
        return float0 == null ? 1.0f : float0;
    }

    @Override  // android.util.Property
    public Object get(Object object0) {
        return this.get(((ViewGroup)object0));
    }

    public void set(ViewGroup viewGroup0, Float float0) {
        float f = (float)float0;
        viewGroup0.setTag(id.mtrl_internal_children_alpha_tag, float0);
        int v = viewGroup0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            viewGroup0.getChildAt(v1).setAlpha(f);
        }
    }

    @Override  // android.util.Property
    public void set(Object object0, Object object1) {
        this.set(((ViewGroup)object0), ((Float)object1));
    }
}

