package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.R.color;
import com.google.android.gms.base.R.drawable;
import com.google.android.gms.base.R.string;
import com.google.android.gms.common.util.DeviceProperties;

public final class zaaa extends Button {
    public zaaa(Context context0, AttributeSet attributeSet0) {
        super(context0, null, 0x1010048);
    }

    public final void zaa(Resources resources0, int v, int v1) {
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(14.0f);
        int v2 = (int)(resources0.getDisplayMetrics().density * 48.0f + 0.5f);
        this.setMinHeight(v2);
        this.setMinWidth(v2);
        int v3 = zaaa.zab(v1, drawable.common_google_signin_btn_icon_dark, drawable.common_google_signin_btn_icon_light, drawable.common_google_signin_btn_icon_light);
        int v4 = zaaa.zab(v1, drawable.common_google_signin_btn_text_dark, drawable.common_google_signin_btn_text_light, drawable.common_google_signin_btn_text_light);
        switch(v) {
            case 0: 
            case 1: {
                v3 = v4;
                break;
            }
            case 2: {
                break;
            }
            default: {
                throw new IllegalStateException("Unknown button size: " + v);
            }
        }
        Drawable drawable0 = DrawableCompat.wrap(resources0.getDrawable(v3));
        DrawableCompat.setTintList(drawable0, resources0.getColorStateList(color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(drawable0, PorterDuff.Mode.SRC_ATOP);
        this.setBackgroundDrawable(drawable0);
        this.setTextColor(((ColorStateList)Preconditions.checkNotNull(resources0.getColorStateList(zaaa.zab(v1, color.common_google_signin_btn_text_dark, color.common_google_signin_btn_text_light, color.common_google_signin_btn_text_light)))));
        switch(v) {
            case 0: {
                this.setText(resources0.getString(string.common_signin_button_text));
                break;
            }
            case 1: {
                this.setText(resources0.getString(string.common_signin_button_text_long));
                break;
            }
            case 2: {
                this.setText(null);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown button size: " + v);
            }
        }
        this.setTransformationMethod(null);
        if(DeviceProperties.isWearable(this.getContext())) {
            this.setGravity(19);
        }
    }

    private static final int zab(int v, int v1, int v2, int v3) {
        switch(v) {
            case 0: {
                return v1;
            }
            case 1: {
                return v2;
            }
            case 2: {
                return v3;
            }
            default: {
                throw new IllegalStateException("Unknown color scheme: " + v);
            }
        }
    }
}

