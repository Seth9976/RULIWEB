package com.google.android.material.datepicker;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;

public class MaterialStyledDatePickerDialog extends DatePickerDialog {
    private static final int DEF_STYLE_ATTR = 0x101035C;
    private static final int DEF_STYLE_RES;
    private final Drawable background;
    private final Rect backgroundInsets;

    static {
        MaterialStyledDatePickerDialog.DEF_STYLE_RES = style.MaterialAlertDialog_MaterialComponents_Picker_Date_Spinner;
    }

    public MaterialStyledDatePickerDialog(Context context0) {
        this(context0, 0);
    }

    public MaterialStyledDatePickerDialog(Context context0, int v) {
        this(context0, v, null, -1, -1, -1);
    }

    public MaterialStyledDatePickerDialog(Context context0, int v, DatePickerDialog.OnDateSetListener datePickerDialog$OnDateSetListener0, int v1, int v2, int v3) {
        super(context0, v, datePickerDialog$OnDateSetListener0, v1, v2, v3);
        Context context1 = this.getContext();
        Context context2 = this.getContext();
        String s = this.getClass().getCanonicalName();
        int v4 = MaterialAttributes.resolveOrThrow(context2, attr.colorSurface, s);
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(context1, null, 0x101035C, MaterialStyledDatePickerDialog.DEF_STYLE_RES);
        materialShapeDrawable0.setFillColor(ColorStateList.valueOf(v4));
        Rect rect0 = MaterialDialogs.getDialogBackgroundInsets(context1, 0x101035C, MaterialStyledDatePickerDialog.DEF_STYLE_RES);
        this.backgroundInsets = rect0;
        this.background = MaterialDialogs.insetDrawable(materialShapeDrawable0, rect0);
    }

    public MaterialStyledDatePickerDialog(Context context0, DatePickerDialog.OnDateSetListener datePickerDialog$OnDateSetListener0, int v, int v1, int v2) {
        this(context0, 0, datePickerDialog$OnDateSetListener0, v, v1, v2);
    }

    @Override  // android.app.AlertDialog
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.getWindow().setBackgroundDrawable(this.background);
        this.getWindow().getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(this, this.backgroundInsets));
    }
}

