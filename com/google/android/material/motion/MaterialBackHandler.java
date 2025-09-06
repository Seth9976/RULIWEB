package com.google.android.material.motion;

import androidx.activity.BackEventCompat;

public interface MaterialBackHandler {
    void cancelBackProgress();

    void handleBackInvoked();

    void startBackProgress(BackEventCompat arg1);

    void updateBackProgress(BackEventCompat arg1);
}

