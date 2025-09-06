package androidx.constraintlayout.core.state;

public final class Transition..ExternalSyntheticLambda0 implements Interpolator {
    public final String f$0;

    public Transition..ExternalSyntheticLambda0(String s) {
        this.f$0 = s;
    }

    @Override  // androidx.constraintlayout.core.state.Interpolator
    public final float getInterpolation(float f) {
        return Transition.lambda$getInterpolator$0(this.f$0, f);
    }
}

