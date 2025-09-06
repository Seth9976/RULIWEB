package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class zak extends Drawable implements Drawable.Callback {
    private int zaa;
    private long zab;
    private int zac;
    private int zad;
    private int zae;
    private int zaf;
    private boolean zag;
    private boolean zah;
    private zaj zai;
    private Drawable zaj;
    private Drawable zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private int zao;

    public zak(Drawable drawable0, Drawable drawable1) {
        this(null);
        if(drawable0 == null) {
            drawable0 = zai.zaa;
        }
        this.zaj = drawable0;
        drawable0.setCallback(this);
        zaj zaj0 = this.zai;
        int v = zaj0.zab;
        zaj0.zab = drawable0.getChangingConfigurations() | v;
        if(drawable1 == null) {
            drawable1 = zai.zaa;
        }
        this.zak = drawable1;
        drawable1.setCallback(this);
        zaj zaj1 = this.zai;
        int v1 = zaj1.zab;
        zaj1.zab = drawable1.getChangingConfigurations() | v1;
    }

    zak(zaj zaj0) {
        this.zaa = 0;
        this.zad = 0xFF;
        this.zaf = 0;
        this.zag = true;
        this.zai = new zaj(zaj0);
    }

    @Override  // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas0) {
        boolean z1;
        boolean z = false;
        switch(this.zaa) {
            case 1: {
                this.zab = SystemClock.uptimeMillis();
                this.zaa = 2;
                z1 = false;
                break;
            }
            case 2: {
                if(this.zab >= 0L) {
                    float f = ((float)(SystemClock.uptimeMillis() - this.zab)) / ((float)this.zae);
                    z1 = f >= 1.0f;
                    if(z1) {
                        this.zaa = 0;
                    }
                    this.zaf = (int)(((float)this.zac) * Math.min(f, 1.0f) + 0.0f);
                }
                else {
                    z1 = true;
                }
                break;
            }
            default: {
                z1 = true;
            }
        }
        int v = this.zaf;
        boolean z2 = this.zag;
        Drawable drawable0 = this.zaj;
        Drawable drawable1 = this.zak;
        if(z1) {
            if(!z2) {
                drawable0.draw(canvas0);
            }
            else if(v == 0) {
                drawable0.draw(canvas0);
                v = 0;
            }
            int v1 = this.zad;
            if(v == v1) {
                drawable1.setAlpha(v1);
                drawable1.draw(canvas0);
            }
            return;
        }
        if(z2) {
            drawable0.setAlpha(this.zad - v);
            z = true;
        }
        drawable0.draw(canvas0);
        if(z) {
            drawable0.setAlpha(this.zad);
        }
        if(v > 0) {
            drawable1.setAlpha(v);
            drawable1.draw(canvas0);
            drawable1.setAlpha(this.zad);
        }
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zai.zaa | this.zai.zab;
    }

    @Override  // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if(this.zac()) {
            zaj zaj0 = this.zai;
            zaj0.zaa = this.getChangingConfigurations();
            return this.zai;
        }
        return null;
    }

    @Override  // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zaj.getIntrinsicHeight(), this.zak.getIntrinsicHeight());
    }

    @Override  // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zaj.getIntrinsicWidth(), this.zak.getIntrinsicWidth());
    }

    @Override  // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if(!this.zan) {
            this.zao = Drawable.resolveOpacity(this.zaj.getOpacity(), this.zak.getOpacity());
            this.zan = true;
        }
        return this.zao;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public final void invalidateDrawable(Drawable drawable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.invalidateDrawable(this);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if(!this.zah && super.mutate() == this) {
            if(!this.zac()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zaj.mutate();
            this.zak.mutate();
            this.zah = true;
            return this;
        }
        return this;
    }

    @Override  // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect0) {
        this.zaj.setBounds(rect0);
        this.zak.setBounds(rect0);
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public final void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.scheduleDrawable(this, runnable0, v);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public final void setAlpha(int v) {
        if(this.zaf == this.zad) {
            this.zaf = v;
        }
        this.zad = v;
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter0) {
        this.zaj.setColorFilter(colorFilter0);
        this.zak.setColorFilter(colorFilter0);
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public final void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.unscheduleDrawable(this, runnable0);
        }
    }

    public final Drawable zaa() {
        return this.zak;
    }

    public final void zab(int v) {
        this.zac = this.zad;
        this.zaf = 0;
        this.zae = 0xFA;
        this.zaa = 1;
        this.invalidateSelf();
    }

    public final boolean zac() {
        boolean z = false;
        if(!this.zal) {
            if(this.zaj.getConstantState() != null && this.zak.getConstantState() != null) {
                z = true;
            }
            this.zam = z;
            this.zal = true;
        }
        return this.zam;
    }
}

