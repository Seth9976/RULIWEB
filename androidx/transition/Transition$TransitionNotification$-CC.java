package androidx.transition;

public final class Transition.TransitionNotification.-CC {
    static {
    }

    public static void lambda$static$0(TransitionListener transition$TransitionListener0, Transition transition0, boolean z) {
        transition$TransitionListener0.onTransitionCancel(transition0);
    }

    public static void lambda$static$1(TransitionListener transition$TransitionListener0, Transition transition0, boolean z) {
        transition$TransitionListener0.onTransitionPause(transition0);
    }

    public static void lambda$static$2(TransitionListener transition$TransitionListener0, Transition transition0, boolean z) {
        transition$TransitionListener0.onTransitionResume(transition0);
    }
}

