package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

public abstract class DayViewDecorator implements Parcelable {
    public ColorStateList getBackgroundColor(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public Drawable getCompoundDrawableBottom(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public Drawable getCompoundDrawableLeft(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public Drawable getCompoundDrawableRight(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public Drawable getCompoundDrawableTop(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public CharSequence getContentDescription(Context context0, int v, int v1, int v2, boolean z, boolean z1, CharSequence charSequence0) [...] // Inlined contents

    public ColorStateList getTextColor(Context context0, int v, int v1, int v2, boolean z, boolean z1) [...] // Inlined contents

    public void initialize(Context context0) {
    }
}

