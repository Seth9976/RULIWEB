package com.google.android.material.navigation;

import com.google.android.material.motion.MaterialBackOrchestrator;

public final class NavigationView.1..ExternalSyntheticLambda0 implements Runnable {
    public final MaterialBackOrchestrator f$0;

    public NavigationView.1..ExternalSyntheticLambda0(MaterialBackOrchestrator materialBackOrchestrator0) {
        this.f$0 = materialBackOrchestrator0;
    }

    @Override
    public final void run() {
        this.f$0.startListeningForBackCallbacksWithPriorityOverlay();
    }
}

