package com.google.android.material.motion;

import android.os.Build.VERSION;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.BackEventCompat;
import java.util.Objects;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class MaterialBackOrchestrator {
    static class Api33BackCallbackDelegate implements BackCallbackDelegate {
        private OnBackInvokedCallback onBackInvokedCallback;

        private Api33BackCallbackDelegate() {
        }

        Api33BackCallbackDelegate(com.google.android.material.motion.MaterialBackOrchestrator.1 materialBackOrchestrator$10) {
        }

        OnBackInvokedCallback createOnBackInvokedCallback(MaterialBackHandler materialBackHandler0) {
            Objects.requireNonNull(materialBackHandler0);
            return new MaterialBackOrchestrator.Api33BackCallbackDelegate..ExternalSyntheticLambda1(materialBackHandler0);
        }

        boolean isListeningForBackCallbacks() {
            return this.onBackInvokedCallback != null;
        }

        @Override  // com.google.android.material.motion.MaterialBackOrchestrator$BackCallbackDelegate
        public void startListeningForBackCallbacks(MaterialBackHandler materialBackHandler0, View view0, boolean z) {
            if(this.onBackInvokedCallback == null) {
                OnBackInvokedDispatcher onBackInvokedDispatcher0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(view0);
                if(onBackInvokedDispatcher0 != null) {
                    OnBackInvokedCallback onBackInvokedCallback0 = this.createOnBackInvokedCallback(materialBackHandler0);
                    this.onBackInvokedCallback = onBackInvokedCallback0;
                    onBackInvokedDispatcher0.registerOnBackInvokedCallback((z ? 1000000 : 0), onBackInvokedCallback0);
                }
            }
        }

        @Override  // com.google.android.material.motion.MaterialBackOrchestrator$BackCallbackDelegate
        public void stopListeningForBackCallbacks(View view0) {
            OnBackInvokedDispatcher onBackInvokedDispatcher0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(view0);
            if(onBackInvokedDispatcher0 == null) {
                return;
            }
            onBackInvokedDispatcher0.unregisterOnBackInvokedCallback(this.onBackInvokedCallback);
            this.onBackInvokedCallback = null;
        }
    }

    static class Api34BackCallbackDelegate extends Api33BackCallbackDelegate {
        private Api34BackCallbackDelegate() {
            super(null);
        }

        Api34BackCallbackDelegate(com.google.android.material.motion.MaterialBackOrchestrator.1 materialBackOrchestrator$10) {
        }

        @Override  // com.google.android.material.motion.MaterialBackOrchestrator$Api33BackCallbackDelegate
        OnBackInvokedCallback createOnBackInvokedCallback(MaterialBackHandler materialBackHandler0) {
            return new OnBackAnimationCallback() {
                @Override  // android.window.OnBackAnimationCallback
                public void onBackCancelled() {
                    if(!Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        return;
                    }
                    materialBackHandler0.cancelBackProgress();
                }

                @Override  // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    materialBackHandler0.handleBackInvoked();
                }

                @Override  // android.window.OnBackAnimationCallback
                public void onBackProgressed(BackEvent backEvent0) {
                    if(!Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        return;
                    }
                    BackEventCompat backEventCompat0 = new BackEventCompat(backEvent0);
                    materialBackHandler0.updateBackProgress(backEventCompat0);
                }

                @Override  // android.window.OnBackAnimationCallback
                public void onBackStarted(BackEvent backEvent0) {
                    if(!Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        return;
                    }
                    BackEventCompat backEventCompat0 = new BackEventCompat(backEvent0);
                    materialBackHandler0.startBackProgress(backEventCompat0);
                }
            };
        }
    }

    interface BackCallbackDelegate {
        void startListeningForBackCallbacks(MaterialBackHandler arg1, View arg2, boolean arg3);

        void stopListeningForBackCallbacks(View arg1);
    }

    private final BackCallbackDelegate backCallbackDelegate;
    private final MaterialBackHandler backHandler;
    private final View view;

    public MaterialBackOrchestrator(View view0) {
        this(((MaterialBackHandler)view0), view0);
    }

    public MaterialBackOrchestrator(MaterialBackHandler materialBackHandler0, View view0) {
        this.backCallbackDelegate = MaterialBackOrchestrator.createBackCallbackDelegate();
        this.backHandler = materialBackHandler0;
        this.view = view0;
    }

    private static BackCallbackDelegate createBackCallbackDelegate() {
        if(Build.VERSION.SDK_INT >= 34) {
            return new Api34BackCallbackDelegate(null);
        }
        return Build.VERSION.SDK_INT >= 33 ? new Api33BackCallbackDelegate(null) : null;
    }

    public boolean shouldListenForBackCallbacks() {
        return this.backCallbackDelegate != null;
    }

    private void startListeningForBackCallbacks(boolean z) {
        BackCallbackDelegate materialBackOrchestrator$BackCallbackDelegate0 = this.backCallbackDelegate;
        if(materialBackOrchestrator$BackCallbackDelegate0 != null) {
            materialBackOrchestrator$BackCallbackDelegate0.startListeningForBackCallbacks(this.backHandler, this.view, z);
        }
    }

    public void startListeningForBackCallbacks() {
        this.startListeningForBackCallbacks(false);
    }

    public void startListeningForBackCallbacksWithPriorityOverlay() {
        this.startListeningForBackCallbacks(true);
    }

    public void stopListeningForBackCallbacks() {
        BackCallbackDelegate materialBackOrchestrator$BackCallbackDelegate0 = this.backCallbackDelegate;
        if(materialBackOrchestrator$BackCallbackDelegate0 != null) {
            materialBackOrchestrator$BackCallbackDelegate0.stopListeningForBackCallbacks(this.view);
        }
    }

    class com.google.android.material.motion.MaterialBackOrchestrator.1 {
    }

}

