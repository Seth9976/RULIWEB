package androidx.constraintlayout.core.motion.utils;

public class Easing {
    static class CubicEasing extends Easing {
        double mX1;
        double mX2;
        double mY1;
        double mY2;
        private static double sDError = 0.0001;
        private static double sError = 0.01;

        static {
        }

        CubicEasing(double f, double f1, double f2, double f3) {
            this.setup(f, f1, f2, f3);
        }

        CubicEasing(String s) {
            this.mStr = s;
            int v = s.indexOf(40);
            int v1 = s.indexOf(44, v);
            this.mX1 = Double.parseDouble(s.substring(v + 1, v1).trim());
            int v2 = s.indexOf(44, v1 + 1);
            this.mY1 = Double.parseDouble(s.substring(v1 + 1, v2).trim());
            int v3 = s.indexOf(44, v2 + 1);
            this.mX2 = Double.parseDouble(s.substring(v2 + 1, v3).trim());
            this.mY2 = Double.parseDouble(s.substring(v3 + 1, s.indexOf(41, v3 + 1)).trim());
        }

        @Override  // androidx.constraintlayout.core.motion.utils.Easing
        public double get(double f) {
            if(f <= 0.0) {
                return 0.0;
            }
            if(f >= 1.0) {
                return 1.0;
            }
            double f1 = 0.5;
            double f2 = 0.5;
            while(f1 > CubicEasing.sError) {
                f1 *= 0.5;
                if(this.getX(f2) < f) {
                    f2 += f1;
                }
                else {
                    f2 -= f1;
                }
            }
            double f3 = f2 - f1;
            double f4 = this.getX(f3);
            double f5 = f2 + f1;
            double f6 = this.getX(f5);
            double f7 = this.getY(f3);
            return (this.getY(f5) - f7) * (f - f4) / (f6 - f4) + f7;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.Easing
        public double getDiff(double f) {
            double f1 = 0.5;
            double f2 = 0.5;
            while(f1 > CubicEasing.sDError) {
                f1 *= 0.5;
                if(this.getX(f2) < f) {
                    f2 += f1;
                }
                else {
                    f2 -= f1;
                }
            }
            return (this.getY(f2 + f1) - this.getY(f2 - f1)) / (this.getX(f2 + f1) - this.getX(f2 - f1));
        }

        private double getDiffX(double f) {
            return (1.0 - f) * 3.0 * (1.0 - f) * this.mX1 + (1.0 - f) * 6.0 * f * (this.mX2 - this.mX1) + 3.0 * f * f * (1.0 - this.mX2);
        }

        private double getDiffY(double f) {
            return (1.0 - f) * 3.0 * (1.0 - f) * this.mY1 + (1.0 - f) * 6.0 * f * (this.mY2 - this.mY1) + 3.0 * f * f * (1.0 - this.mY2);
        }

        private double getX(double f) {
            double f1 = 3.0 * (1.0 - f);
            return this.mX1 * ((1.0 - f) * f1 * f) + this.mX2 * (f1 * f * f) + f * f * f;
        }

        private double getY(double f) {
            double f1 = 3.0 * (1.0 - f);
            return this.mY1 * ((1.0 - f) * f1 * f) + this.mY2 * (f1 * f * f) + f * f * f;
        }

        void setup(double f, double f1, double f2, double f3) {
            this.mX1 = f;
            this.mY1 = f1;
            this.mX2 = f2;
            this.mY2 = f3;
        }
    }

    private static final String ACCELERATE = "cubic(0.4, 0.05, 0.8, 0.7)";
    private static final String ACCELERATE_NAME = "accelerate";
    private static final String ANTICIPATE = "cubic(0.36, 0, 0.66, -0.56)";
    private static final String ANTICIPATE_NAME = "anticipate";
    private static final String DECELERATE = "cubic(0.0, 0.0, 0.2, 0.95)";
    private static final String DECELERATE_NAME = "decelerate";
    private static final String LINEAR = "cubic(1, 1, 0, 0)";
    private static final String LINEAR_NAME = "linear";
    public static String[] NAMED_EASING = null;
    private static final String OVERSHOOT = "cubic(0.34, 1.56, 0.64, 1)";
    private static final String OVERSHOOT_NAME = "overshoot";
    private static final String STANDARD = "cubic(0.4, 0.0, 0.2, 1)";
    private static final String STANDARD_NAME = "standard";
    String mStr;
    static Easing sDefault;

    static {
        Easing.sDefault = new Easing();
        Easing.NAMED_EASING = new String[]{"standard", "accelerate", "decelerate", "linear"};
    }

    public Easing() {
        this.mStr = "identity";
    }

    public double get(double f) {
        return f;
    }

    public double getDiff(double f) {
        return 1.0;
    }

    public static Easing getInterpolator(String s) {
        if(s == null) {
            return null;
        }
        if(s.startsWith("cubic")) {
            return new CubicEasing(s);
        }
        if(s.startsWith("spline")) {
            return new StepCurve(s);
        }
        if(s.startsWith("Schlick")) {
            return new Schlick(s);
        }
        switch(s) {
            case "accelerate": {
                return new CubicEasing("cubic(0.4, 0.05, 0.8, 0.7)");
            }
            case "anticipate": {
                return new CubicEasing("cubic(0.36, 0, 0.66, -0.56)");
            }
            case "decelerate": {
                return new CubicEasing("cubic(0.0, 0.0, 0.2, 0.95)");
            }
            case "linear": {
                return new CubicEasing("cubic(1, 1, 0, 0)");
            }
            case "overshoot": {
                return new CubicEasing("cubic(0.34, 1.56, 0.64, 1)");
            }
            case "standard": {
                return new CubicEasing("cubic(0.4, 0.0, 0.2, 1)");
            }
            default: {
                System.err.println("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or [standard, accelerate, decelerate, linear]");
                return Easing.sDefault;
            }
        }
    }

    @Override
    public String toString() {
        return this.mStr;
    }
}

