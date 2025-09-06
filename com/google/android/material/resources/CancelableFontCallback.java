package com.google.android.material.resources;

import android.graphics.Typeface;

public final class CancelableFontCallback extends TextAppearanceFontCallback {
    public interface ApplyFont {
        void apply(Typeface arg1);
    }

    private final ApplyFont applyFont;
    private boolean cancelled;
    private final Typeface fallbackFont;

    public CancelableFontCallback(ApplyFont cancelableFontCallback$ApplyFont0, Typeface typeface0) {
        this.fallbackFont = typeface0;
        this.applyFont = cancelableFontCallback$ApplyFont0;
    }

    public void cancel() {
        this.cancelled = true;
    }

    @Override  // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrievalFailed(int v) {
        this.updateIfNotCancelled(this.fallbackFont);
    }

    @Override  // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrieved(Typeface typeface0, boolean z) {
        this.updateIfNotCancelled(typeface0);
    }

    private void updateIfNotCancelled(Typeface typeface0) {
        if(!this.cancelled) {
            this.applyFont.apply(typeface0);
        }
    }
}

