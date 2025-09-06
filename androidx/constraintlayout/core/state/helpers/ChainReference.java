package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Chain;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;
import java.util.HashMap;

public class ChainReference extends HelperReference {
    protected float mBias;
    private HashMap mMapPostGoneMargin;
    @Deprecated
    protected HashMap mMapPostMargin;
    private HashMap mMapPreGoneMargin;
    @Deprecated
    protected HashMap mMapPreMargin;
    @Deprecated
    protected HashMap mMapWeights;
    protected Chain mStyle;

    public ChainReference(State state0, Helper state$Helper0) {
        super(state0, state$Helper0);
        this.mBias = 0.5f;
        this.mMapWeights = new HashMap();
        this.mMapPreMargin = new HashMap();
        this.mMapPostMargin = new HashMap();
        this.mStyle = Chain.SPREAD;
    }

    public void addChainElement(Object object0, float f, float f1, float f2, float f3, float f4) {
        super.add(new Object[]{object0});
        String s = object0.toString();
        if(!Float.isNaN(f)) {
            this.mMapWeights.put(s, f);
        }
        if(!Float.isNaN(f1)) {
            this.mMapPreMargin.put(s, f1);
        }
        if(!Float.isNaN(f2)) {
            this.mMapPostMargin.put(s, f2);
        }
        if(!Float.isNaN(f3)) {
            if(this.mMapPreGoneMargin == null) {
                this.mMapPreGoneMargin = new HashMap();
            }
            this.mMapPreGoneMargin.put(s, f3);
        }
        if(!Float.isNaN(f4)) {
            if(this.mMapPostGoneMargin == null) {
                this.mMapPostGoneMargin = new HashMap();
            }
            this.mMapPostGoneMargin.put(s, f4);
        }
    }

    public void addChainElement(String s, float f, float f1, float f2) {
        this.addChainElement(s, f, f1, f2, 0.0f, 0.0f);
    }

    @Override  // androidx.constraintlayout.core.state.ConstraintReference
    public ConstraintReference bias(float f) {
        return this.bias(f);
    }

    public ChainReference bias(float f) {
        this.mBias = f;
        return this;
    }

    public float getBias() {
        return this.mBias;
    }

    float getPostGoneMargin(String s) {
        return this.mMapPostGoneMargin == null || !this.mMapPostGoneMargin.containsKey(s) ? 0.0f : ((float)(((Float)this.mMapPostGoneMargin.get(s))));
    }

    // 去混淆评级： 低(20)
    protected float getPostMargin(String s) {
        return this.mMapPostMargin.containsKey(s) ? ((float)(((Float)this.mMapPostMargin.get(s)))) : 0.0f;
    }

    float getPreGoneMargin(String s) {
        return this.mMapPreGoneMargin == null || !this.mMapPreGoneMargin.containsKey(s) ? 0.0f : ((float)(((Float)this.mMapPreGoneMargin.get(s))));
    }

    // 去混淆评级： 低(20)
    protected float getPreMargin(String s) {
        return this.mMapPreMargin.containsKey(s) ? ((float)(((Float)this.mMapPreMargin.get(s)))) : 0.0f;
    }

    public Chain getStyle() {
        return Chain.SPREAD;
    }

    // 去混淆评级： 低(20)
    protected float getWeight(String s) {
        return this.mMapWeights.containsKey(s) ? ((float)(((Float)this.mMapWeights.get(s)))) : -1.0f;
    }

    public ChainReference style(Chain state$Chain0) {
        this.mStyle = state$Chain0;
        return this;
    }
}

