package androidx.constraintlayout.core.state;

public interface CoreMotionScene {
    String getConstraintSet(int arg1);

    String getConstraintSet(String arg1);

    String getTransition(String arg1);

    void setConstraintSetContent(String arg1, String arg2);

    void setDebugName(String arg1);

    void setTransitionContent(String arg1, String arg2);
}

