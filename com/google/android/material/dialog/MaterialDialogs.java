package com.google.android.material.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import com.google.android.material.R.dimen;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialDialogs {
    public static Rect getDialogBackgroundInsets(Context context0, int v, int v1) {
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, null, styleable.MaterialAlertDialog, v, v1, new int[0]);
        int v2 = context0.getResources().getDimensionPixelSize(dimen.mtrl_alert_dialog_background_inset_start);
        int v3 = typedArray0.getDimensionPixelSize(styleable.MaterialAlertDialog_backgroundInsetStart, v2);
        int v4 = context0.getResources().getDimensionPixelSize(dimen.mtrl_alert_dialog_background_inset_top);
        int v5 = typedArray0.getDimensionPixelSize(styleable.MaterialAlertDialog_backgroundInsetTop, v4);
        int v6 = context0.getResources().getDimensionPixelSize(dimen.mtrl_alert_dialog_background_inset_end);
        int v7 = typedArray0.getDimensionPixelSize(styleable.MaterialAlertDialog_backgroundInsetEnd, v6);
        int v8 = context0.getResources().getDimensionPixelSize(dimen.mtrl_alert_dialog_background_inset_bottom);
        int v9 = typedArray0.getDimensionPixelSize(styleable.MaterialAlertDialog_backgroundInsetBottom, v8);
        typedArray0.recycle();
        if(context0.getResources().getConfiguration().getLayoutDirection() == 1) {
            int v10 = v7;
            v7 = v3;
            v3 = v10;
        }
        return new Rect(v3, v5, v7, v9);
    }

    public static InsetDrawable insetDrawable(Drawable drawable0, Rect rect0) {
        return new InsetDrawable(drawable0, rect0.left, rect0.top, rect0.right, rect0.bottom);
    }
}

