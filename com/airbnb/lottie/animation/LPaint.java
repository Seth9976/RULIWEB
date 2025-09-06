package com.airbnb.lottie.animation;

import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.LocaleList;

public class LPaint extends Paint {
    public LPaint() {
    }

    public LPaint(int v) {
        super(v);
    }

    public LPaint(int v, PorterDuff.Mode porterDuff$Mode0) {
        super(v);
        this.setXfermode(new PorterDuffXfermode(porterDuff$Mode0));
    }

    public LPaint(PorterDuff.Mode porterDuff$Mode0) {
        this.setXfermode(new PorterDuffXfermode(porterDuff$Mode0));
    }

    @Override  // android.graphics.Paint
    public void setTextLocales(LocaleList localeList0) {
    }
}

