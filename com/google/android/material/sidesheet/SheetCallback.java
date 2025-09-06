package com.google.android.material.sidesheet;

import android.view.View;

interface SheetCallback {
    void onSlide(View arg1, float arg2);

    void onStateChanged(View arg1, int arg2);
}

