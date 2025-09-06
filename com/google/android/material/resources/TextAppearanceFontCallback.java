package com.google.android.material.resources;

import android.graphics.Typeface;

public abstract class TextAppearanceFontCallback {
    public abstract void onFontRetrievalFailed(int arg1);

    public abstract void onFontRetrieved(Typeface arg1, boolean arg2);
}

