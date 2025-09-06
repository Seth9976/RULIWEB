package androidx.constraintlayout.core.motion.utils;

public class Schlick extends Easing {
    private static final boolean DEBUG = false;
    double mEps;
    double mS;
    double mT;

    Schlick(String s) {
        this.mStr = s;
        int v = s.indexOf(40);
        int v1 = s.indexOf(44, v);
        this.mS = Double.parseDouble(s.substring(v + 1, v1).trim());
        this.mT = Double.parseDouble(s.substring(v1 + 1, s.indexOf(44, v1 + 1)).trim());
    }

    private double dfunc(double f) {
        return f < this.mT ? this.mS * this.mT * this.mT / (((this.mT - f) * this.mS + f) * (this.mS * (this.mT - f) + f)) : (this.mT - 1.0) * this.mS * (this.mT - 1.0) / ((-this.mS * (this.mT - f) - f + 1.0) * (-this.mS * (this.mT - f) - f + 1.0));
    }

    private double func(double f) {
        return f < this.mT ? this.mT * f / (f + this.mS * (this.mT - f)) : (1.0 - this.mT) * (f - 1.0) / (1.0 - f - this.mS * (this.mT - f));
    }

    @Override  // androidx.constraintlayout.core.motion.utils.Easing
    public double get(double f) {
        return this.func(f);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.Easing
    public double getDiff(double f) {
        return this.dfunc(f);
    }
}

