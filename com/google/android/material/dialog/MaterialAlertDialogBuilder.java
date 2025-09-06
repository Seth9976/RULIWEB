package com.google.android.material.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialAlertDialogBuilder extends Builder {
    private static final int DEF_STYLE_ATTR;
    private static final int DEF_STYLE_RES;
    private static final int MATERIAL_ALERT_DIALOG_THEME_OVERLAY;
    private Drawable background;
    private final Rect backgroundInsets;

    static {
        MaterialAlertDialogBuilder.DEF_STYLE_ATTR = attr.alertDialogStyle;
        MaterialAlertDialogBuilder.DEF_STYLE_RES = style.MaterialAlertDialog_MaterialComponents;
        MaterialAlertDialogBuilder.MATERIAL_ALERT_DIALOG_THEME_OVERLAY = attr.materialAlertDialogTheme;
    }

    public MaterialAlertDialogBuilder(Context context0) {
        this(context0, 0);
    }

    public MaterialAlertDialogBuilder(Context context0, int v) {
        super(MaterialAlertDialogBuilder.createMaterialAlertDialogThemedContext(context0), MaterialAlertDialogBuilder.getOverridingThemeResId(context0, v));
        Context context1 = this.getContext();
        Resources.Theme resources$Theme0 = context1.getTheme();
        this.backgroundInsets = MaterialDialogs.getDialogBackgroundInsets(context1, MaterialAlertDialogBuilder.DEF_STYLE_ATTR, MaterialAlertDialogBuilder.DEF_STYLE_RES);
        String s = this.getClass().getCanonicalName();
        int v1 = MaterialColors.getColor(context1, attr.colorSurface, s);
        TypedArray typedArray0 = context1.obtainStyledAttributes(null, styleable.MaterialAlertDialog, MaterialAlertDialogBuilder.DEF_STYLE_ATTR, MaterialAlertDialogBuilder.DEF_STYLE_RES);
        int v2 = typedArray0.getColor(styleable.MaterialAlertDialog_backgroundTint, v1);
        typedArray0.recycle();
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(context1, null, MaterialAlertDialogBuilder.DEF_STYLE_ATTR, MaterialAlertDialogBuilder.DEF_STYLE_RES);
        materialShapeDrawable0.initializeElevationOverlay(context1);
        materialShapeDrawable0.setFillColor(ColorStateList.valueOf(v2));
        if(Build.VERSION.SDK_INT >= 28) {
            TypedValue typedValue0 = new TypedValue();
            resources$Theme0.resolveAttribute(0x1010571, typedValue0, true);
            float f = typedValue0.getDimension(this.getContext().getResources().getDisplayMetrics());
            if(typedValue0.type == 5 && f >= 0.0f) {
                materialShapeDrawable0.setCornerSize(f);
            }
        }
        this.background = materialShapeDrawable0;
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public AlertDialog create() {
        AlertDialog alertDialog0 = super.create();
        Window window0 = alertDialog0.getWindow();
        View view0 = window0.getDecorView();
        Drawable drawable0 = this.background;
        if(drawable0 instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable)drawable0).setElevation(ViewCompat.getElevation(view0));
        }
        window0.setBackgroundDrawable(MaterialDialogs.insetDrawable(this.background, this.backgroundInsets));
        view0.setOnTouchListener(new InsetDialogOnTouchListener(alertDialog0, this.backgroundInsets));
        return alertDialog0;
    }

    private static Context createMaterialAlertDialogThemedContext(Context context0) {
        int v = MaterialAlertDialogBuilder.getMaterialAlertDialogThemeOverlay(context0);
        Context context1 = MaterialThemeOverlay.wrap(context0, null, MaterialAlertDialogBuilder.DEF_STYLE_ATTR, MaterialAlertDialogBuilder.DEF_STYLE_RES);
        return v == 0 ? context1 : new ContextThemeWrapper(context1, v);
    }

    public Drawable getBackground() {
        return this.background;
    }

    private static int getMaterialAlertDialogThemeOverlay(Context context0) {
        TypedValue typedValue0 = MaterialAttributes.resolve(context0, MaterialAlertDialogBuilder.MATERIAL_ALERT_DIALOG_THEME_OVERLAY);
        return typedValue0 == null ? 0 : typedValue0.data;
    }

    private static int getOverridingThemeResId(Context context0, int v) {
        return v == 0 ? MaterialAlertDialogBuilder.getMaterialAlertDialogThemeOverlay(context0) : v;
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setAdapter(ListAdapter listAdapter0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setAdapter(listAdapter0, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setAdapter(ListAdapter listAdapter0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setAdapter(listAdapter0, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setBackground(Drawable drawable0) {
        this.background = drawable0;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetBottom(int v) {
        this.backgroundInsets.bottom = v;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetEnd(int v) {
        if(this.getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.left = v;
            return this;
        }
        this.backgroundInsets.right = v;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetStart(int v) {
        if(this.getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.right = v;
            return this;
        }
        this.backgroundInsets.left = v;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetTop(int v) {
        this.backgroundInsets.top = v;
        return this;
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setCancelable(boolean z) {
        return this.setCancelable(z);
    }

    public MaterialAlertDialogBuilder setCancelable(boolean z) {
        return (MaterialAlertDialogBuilder)super.setCancelable(z);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setCursor(Cursor cursor0, DialogInterface.OnClickListener dialogInterface$OnClickListener0, String s) {
        return this.setCursor(cursor0, dialogInterface$OnClickListener0, s);
    }

    public MaterialAlertDialogBuilder setCursor(Cursor cursor0, DialogInterface.OnClickListener dialogInterface$OnClickListener0, String s) {
        return (MaterialAlertDialogBuilder)super.setCursor(cursor0, dialogInterface$OnClickListener0, s);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setCustomTitle(View view0) {
        return this.setCustomTitle(view0);
    }

    public MaterialAlertDialogBuilder setCustomTitle(View view0) {
        return (MaterialAlertDialogBuilder)super.setCustomTitle(view0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setIcon(int v) {
        return this.setIcon(v);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setIcon(Drawable drawable0) {
        return this.setIcon(drawable0);
    }

    public MaterialAlertDialogBuilder setIcon(int v) {
        return (MaterialAlertDialogBuilder)super.setIcon(v);
    }

    public MaterialAlertDialogBuilder setIcon(Drawable drawable0) {
        return (MaterialAlertDialogBuilder)super.setIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setIconAttribute(int v) {
        return this.setIconAttribute(v);
    }

    public MaterialAlertDialogBuilder setIconAttribute(int v) {
        return (MaterialAlertDialogBuilder)super.setIconAttribute(v);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setItems(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setItems(v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setItems(CharSequence[] arr_charSequence, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setItems(arr_charSequence, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setItems(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setItems(v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setItems(CharSequence[] arr_charSequence, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setItems(arr_charSequence, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setMessage(int v) {
        return this.setMessage(v);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setMessage(CharSequence charSequence0) {
        return this.setMessage(charSequence0);
    }

    public MaterialAlertDialogBuilder setMessage(int v) {
        return (MaterialAlertDialogBuilder)super.setMessage(v);
    }

    public MaterialAlertDialogBuilder setMessage(CharSequence charSequence0) {
        return (MaterialAlertDialogBuilder)super.setMessage(charSequence0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setMultiChoiceItems(int v, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return this.setMultiChoiceItems(v, arr_z, dialogInterface$OnMultiChoiceClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setMultiChoiceItems(Cursor cursor0, String s, String s1, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return this.setMultiChoiceItems(cursor0, s, s1, dialogInterface$OnMultiChoiceClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setMultiChoiceItems(CharSequence[] arr_charSequence, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return this.setMultiChoiceItems(arr_charSequence, arr_z, dialogInterface$OnMultiChoiceClickListener0);
    }

    public MaterialAlertDialogBuilder setMultiChoiceItems(int v, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return (MaterialAlertDialogBuilder)super.setMultiChoiceItems(v, arr_z, dialogInterface$OnMultiChoiceClickListener0);
    }

    public MaterialAlertDialogBuilder setMultiChoiceItems(Cursor cursor0, String s, String s1, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return (MaterialAlertDialogBuilder)super.setMultiChoiceItems(cursor0, s, s1, dialogInterface$OnMultiChoiceClickListener0);
    }

    public MaterialAlertDialogBuilder setMultiChoiceItems(CharSequence[] arr_charSequence, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
        return (MaterialAlertDialogBuilder)super.setMultiChoiceItems(arr_charSequence, arr_z, dialogInterface$OnMultiChoiceClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNegativeButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setNegativeButton(v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNegativeButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setNegativeButton(charSequence0, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setNegativeButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setNegativeButton(v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setNegativeButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setNegativeButton(charSequence0, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNegativeButtonIcon(Drawable drawable0) {
        return this.setNegativeButtonIcon(drawable0);
    }

    public MaterialAlertDialogBuilder setNegativeButtonIcon(Drawable drawable0) {
        return (MaterialAlertDialogBuilder)super.setNegativeButtonIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNeutralButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setNeutralButton(v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNeutralButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setNeutralButton(charSequence0, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setNeutralButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setNeutralButton(v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setNeutralButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setNeutralButton(charSequence0, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setNeutralButtonIcon(Drawable drawable0) {
        return this.setNeutralButtonIcon(drawable0);
    }

    public MaterialAlertDialogBuilder setNeutralButtonIcon(Drawable drawable0) {
        return (MaterialAlertDialogBuilder)super.setNeutralButtonIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return this.setOnCancelListener(dialogInterface$OnCancelListener0);
    }

    public MaterialAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        return (MaterialAlertDialogBuilder)super.setOnCancelListener(dialogInterface$OnCancelListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return this.setOnDismissListener(dialogInterface$OnDismissListener0);
    }

    public MaterialAlertDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
        return (MaterialAlertDialogBuilder)super.setOnDismissListener(dialogInterface$OnDismissListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
        return this.setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
    }

    public MaterialAlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
        return (MaterialAlertDialogBuilder)super.setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setOnKeyListener(DialogInterface.OnKeyListener dialogInterface$OnKeyListener0) {
        return this.setOnKeyListener(dialogInterface$OnKeyListener0);
    }

    public MaterialAlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener dialogInterface$OnKeyListener0) {
        return (MaterialAlertDialogBuilder)super.setOnKeyListener(dialogInterface$OnKeyListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setPositiveButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setPositiveButton(v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setPositiveButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setPositiveButton(charSequence0, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setPositiveButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setPositiveButton(v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setPositiveButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setPositiveButton(charSequence0, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setPositiveButtonIcon(Drawable drawable0) {
        return this.setPositiveButtonIcon(drawable0);
    }

    public MaterialAlertDialogBuilder setPositiveButtonIcon(Drawable drawable0) {
        return (MaterialAlertDialogBuilder)super.setPositiveButtonIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setSingleChoiceItems(int v, int v1, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setSingleChoiceItems(v, v1, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setSingleChoiceItems(Cursor cursor0, int v, String s, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setSingleChoiceItems(cursor0, v, s, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setSingleChoiceItems(ListAdapter listAdapter0, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setSingleChoiceItems(listAdapter0, v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setSingleChoiceItems(CharSequence[] arr_charSequence, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return this.setSingleChoiceItems(arr_charSequence, v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setSingleChoiceItems(int v, int v1, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setSingleChoiceItems(v, v1, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setSingleChoiceItems(Cursor cursor0, int v, String s, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setSingleChoiceItems(cursor0, v, s, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setSingleChoiceItems(ListAdapter listAdapter0, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setSingleChoiceItems(listAdapter0, v, dialogInterface$OnClickListener0);
    }

    public MaterialAlertDialogBuilder setSingleChoiceItems(CharSequence[] arr_charSequence, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        return (MaterialAlertDialogBuilder)super.setSingleChoiceItems(arr_charSequence, v, dialogInterface$OnClickListener0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setTitle(int v) {
        return this.setTitle(v);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setTitle(CharSequence charSequence0) {
        return this.setTitle(charSequence0);
    }

    public MaterialAlertDialogBuilder setTitle(int v) {
        return (MaterialAlertDialogBuilder)super.setTitle(v);
    }

    public MaterialAlertDialogBuilder setTitle(CharSequence charSequence0) {
        return (MaterialAlertDialogBuilder)super.setTitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setView(int v) {
        return this.setView(v);
    }

    @Override  // androidx.appcompat.app.AlertDialog$Builder
    public Builder setView(View view0) {
        return this.setView(view0);
    }

    public MaterialAlertDialogBuilder setView(int v) {
        return (MaterialAlertDialogBuilder)super.setView(v);
    }

    public MaterialAlertDialogBuilder setView(View view0) {
        return (MaterialAlertDialogBuilder)super.setView(view0);
    }
}

