package com.google.android.material.sidesheet;

import android.view.View;

public abstract class SideSheetCallback implements SheetCallback {
    void onLayout(View view0) {
    }

    @Override  // com.google.android.material.sidesheet.SheetCallback
    public abstract void onSlide(View arg1, float arg2);

    @Override  // com.google.android.material.sidesheet.SheetCallback
    public abstract void onStateChanged(View arg1, int arg2);
}

