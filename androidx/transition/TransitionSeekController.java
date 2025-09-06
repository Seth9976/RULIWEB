package androidx.transition;

import androidx.core.util.Consumer;

public interface TransitionSeekController {
    void addOnProgressChangedListener(Consumer arg1);

    void addOnReadyListener(Consumer arg1);

    void animateToEnd();

    void animateToStart(Runnable arg1);

    float getCurrentFraction();

    long getCurrentPlayTimeMillis();

    long getDurationMillis();

    boolean isReady();

    void removeOnProgressChangedListener(Consumer arg1);

    void removeOnReadyListener(Consumer arg1);

    void setCurrentFraction(float arg1);

    void setCurrentPlayTimeMillis(long arg1);
}

