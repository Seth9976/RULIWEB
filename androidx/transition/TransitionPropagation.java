package androidx.transition;

import android.view.ViewGroup;

public abstract class TransitionPropagation {
    public abstract void captureValues(TransitionValues arg1);

    public abstract String[] getPropagationProperties();

    public abstract long getStartDelay(ViewGroup arg1, Transition arg2, TransitionValues arg3, TransitionValues arg4);
}

