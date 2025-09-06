package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

public class Dimension {
    public static enum Type {
        FIXED,
        WRAP,
        MATCH_PARENT,
        MATCH_CONSTRAINT;

        private static Type[] $values() [...] // Inlined contents
    }

    public static final Object FIXED_DIMENSION;
    public static final Object PARENT_DIMENSION;
    public static final Object PERCENT_DIMENSION;
    public static final Object RATIO_DIMENSION;
    public static final Object SPREAD_DIMENSION;
    public static final Object WRAP_DIMENSION;
    Object mInitialValue;
    boolean mIsSuggested;
    int mMax;
    int mMin;
    float mPercent;
    String mRatioString;
    int mValue;
    private final int mWrapContent;

    static {
        Dimension.FIXED_DIMENSION = new String("FIXED_DIMENSION");
        Dimension.WRAP_DIMENSION = new String("WRAP_DIMENSION");
        Dimension.SPREAD_DIMENSION = new String("SPREAD_DIMENSION");
        Dimension.PARENT_DIMENSION = new String("PARENT_DIMENSION");
        Dimension.PERCENT_DIMENSION = new String("PERCENT_DIMENSION");
        Dimension.RATIO_DIMENSION = new String("RATIO_DIMENSION");
    }

    private Dimension() {
        this.mWrapContent = -2;
        this.mMin = 0;
        this.mMax = 0x7FFFFFFF;
        this.mPercent = 1.0f;
        this.mValue = 0;
        this.mRatioString = null;
        this.mInitialValue = Dimension.WRAP_DIMENSION;
        this.mIsSuggested = false;
    }

    private Dimension(Object object0) {
        this.mWrapContent = -2;
        this.mMin = 0;
        this.mMax = 0x7FFFFFFF;
        this.mPercent = 1.0f;
        this.mValue = 0;
        this.mRatioString = null;
        this.mIsSuggested = false;
        this.mInitialValue = object0;
    }

    @Deprecated
    public static Dimension Fixed(int v) {
        return Dimension.createFixed(v);
    }

    @Deprecated
    public static Dimension Fixed(Object object0) {
        Dimension dimension0 = new Dimension(Dimension.FIXED_DIMENSION);
        dimension0.fixed(object0);
        return dimension0;
    }

    @Deprecated
    public static Dimension Parent() {
        return Dimension.createParent();
    }

    @Deprecated
    public static Dimension Percent(Object object0, float f) {
        return Dimension.createPercent(object0, f);
    }

    @Deprecated
    public static Dimension Ratio(String s) {
        return Dimension.createRatio(s);
    }

    @Deprecated
    public static Dimension Spread() {
        return Dimension.createSpread();
    }

    @Deprecated
    public static Dimension Suggested(int v) {
        return Dimension.createSuggested(v);
    }

    @Deprecated
    public static Dimension Suggested(Object object0) {
        return Dimension.createSuggested(object0);
    }

    @Deprecated
    public static Dimension Wrap() {
        return Dimension.createWrap();
    }

    public void apply(State state0, ConstraintWidget constraintWidget0, int v) {
        String s = this.mRatioString;
        if(s != null) {
            constraintWidget0.setDimensionRatio(s);
        }
        int v1 = 2;
        if(v == 0) {
            if(this.mIsSuggested) {
                constraintWidget0.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                Object object0 = this.mInitialValue;
                if(object0 == Dimension.WRAP_DIMENSION) {
                    v1 = 1;
                }
                else if(object0 != Dimension.PERCENT_DIMENSION) {
                    v1 = 0;
                }
                constraintWidget0.setHorizontalMatchStyle(v1, this.mMin, this.mMax, this.mPercent);
                return;
            }
            int v2 = this.mMin;
            if(v2 > 0) {
                constraintWidget0.setMinWidth(v2);
            }
            int v3 = this.mMax;
            if(v3 < 0x7FFFFFFF) {
                constraintWidget0.setMaxWidth(v3);
            }
            Object object1 = this.mInitialValue;
            if(object1 == Dimension.WRAP_DIMENSION) {
                constraintWidget0.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                return;
            }
            if(object1 == Dimension.PARENT_DIMENSION) {
                constraintWidget0.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                return;
            }
            if(object1 == null) {
                constraintWidget0.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidget0.setWidth(this.mValue);
            }
        }
        else {
            if(this.mIsSuggested) {
                constraintWidget0.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                Object object2 = this.mInitialValue;
                if(object2 == Dimension.WRAP_DIMENSION) {
                    v1 = 1;
                }
                else if(object2 != Dimension.PERCENT_DIMENSION) {
                    v1 = 0;
                }
                constraintWidget0.setVerticalMatchStyle(v1, this.mMin, this.mMax, this.mPercent);
                return;
            }
            int v4 = this.mMin;
            if(v4 > 0) {
                constraintWidget0.setMinHeight(v4);
            }
            int v5 = this.mMax;
            if(v5 < 0x7FFFFFFF) {
                constraintWidget0.setMaxHeight(v5);
            }
            Object object3 = this.mInitialValue;
            if(object3 == Dimension.WRAP_DIMENSION) {
                constraintWidget0.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                return;
            }
            if(object3 == Dimension.PARENT_DIMENSION) {
                constraintWidget0.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                return;
            }
            if(object3 == null) {
                constraintWidget0.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidget0.setHeight(this.mValue);
            }
        }
    }

    public static Dimension createFixed(int v) {
        Dimension dimension0 = new Dimension(Dimension.FIXED_DIMENSION);
        dimension0.fixed(v);
        return dimension0;
    }

    public static Dimension createFixed(Object object0) {
        Dimension dimension0 = new Dimension(Dimension.FIXED_DIMENSION);
        dimension0.fixed(object0);
        return dimension0;
    }

    public static Dimension createParent() {
        return new Dimension(Dimension.PARENT_DIMENSION);
    }

    public static Dimension createPercent(Object object0, float f) {
        Dimension dimension0 = new Dimension(Dimension.PERCENT_DIMENSION);
        dimension0.percent(object0, f);
        return dimension0;
    }

    public static Dimension createRatio(String s) {
        Dimension dimension0 = new Dimension(Dimension.RATIO_DIMENSION);
        dimension0.ratio(s);
        return dimension0;
    }

    public static Dimension createSpread() {
        return new Dimension(Dimension.SPREAD_DIMENSION);
    }

    public static Dimension createSuggested(int v) {
        Dimension dimension0 = new Dimension();
        dimension0.suggested(v);
        return dimension0;
    }

    public static Dimension createSuggested(Object object0) {
        Dimension dimension0 = new Dimension();
        dimension0.suggested(object0);
        return dimension0;
    }

    public static Dimension createWrap() {
        return new Dimension(Dimension.WRAP_DIMENSION);
    }

    public boolean equalsFixedValue(int v) {
        return this.mInitialValue == null && this.mValue == v;
    }

    public Dimension fixed(int v) {
        this.mInitialValue = null;
        this.mValue = v;
        return this;
    }

    public Dimension fixed(Object object0) {
        this.mInitialValue = object0;
        if(object0 instanceof Integer) {
            this.mValue = (int)(((Integer)object0));
            this.mInitialValue = null;
        }
        return this;
    }

    int getValue() {
        return this.mValue;
    }

    public Dimension max(int v) {
        if(this.mMax >= 0) {
            this.mMax = v;
        }
        return this;
    }

    public Dimension max(Object object0) {
        Object object1 = Dimension.WRAP_DIMENSION;
        if(object0 == object1 && this.mIsSuggested) {
            this.mInitialValue = object1;
            this.mMax = 0x7FFFFFFF;
        }
        return this;
    }

    public Dimension min(int v) {
        if(v >= 0) {
            this.mMin = v;
        }
        return this;
    }

    public Dimension min(Object object0) {
        if(object0 == Dimension.WRAP_DIMENSION) {
            this.mMin = -2;
        }
        return this;
    }

    public Dimension percent(Object object0, float f) {
        this.mPercent = f;
        return this;
    }

    public Dimension ratio(String s) {
        this.mRatioString = s;
        return this;
    }

    void setValue(int v) {
        this.mIsSuggested = false;
        this.mInitialValue = null;
        this.mValue = v;
    }

    public Dimension suggested(int v) {
        this.mIsSuggested = true;
        if(v >= 0) {
            this.mMax = v;
        }
        return this;
    }

    public Dimension suggested(Object object0) {
        this.mInitialValue = object0;
        this.mIsSuggested = true;
        return this;
    }
}

