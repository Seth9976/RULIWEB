package androidx.constraintlayout.core.motion.utils;

public final class TypedValues.MotionScene.-CC {
    static {
    }

    public static int getId(String s) {
        s.hashCode();
        if(!s.equals("defaultDuration")) {
            return s.equals("layoutDuringTransition") ? 601 : -1;
        }
        return 600;
    }

    public static int getType(int v) {
        switch(v) {
            case 600: {
                return 2;
            }
            case 601: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }
}

