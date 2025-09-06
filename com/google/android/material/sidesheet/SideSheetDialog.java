package com.google.android.material.sidesheet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;

public class SideSheetDialog extends SheetDialog {
    private static final int SIDE_SHEET_DIALOG_DEFAULT_THEME_RES;
    private static final int SIDE_SHEET_DIALOG_THEME_ATTR;

    static {
        SideSheetDialog.SIDE_SHEET_DIALOG_THEME_ATTR = attr.sideSheetDialogTheme;
        SideSheetDialog.SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = style.Theme_Material3_Light_SideSheetDialog;
    }

    public SideSheetDialog(Context context0) {
        this(context0, 0);
    }

    public SideSheetDialog(Context context0, int v) {
        super(context0, v, SideSheetDialog.SIDE_SHEET_DIALOG_THEME_ATTR, SideSheetDialog.SIDE_SHEET_DIALOG_DEFAULT_THEME_RES);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    void addSheetCancelOnHideCallback(Sheet sheet0) {
        sheet0.addCallback(new SideSheetCallback() {
            @Override  // com.google.android.material.sidesheet.SideSheetCallback
            public void onSlide(View view0, float f) {
            }

            @Override  // com.google.android.material.sidesheet.SideSheetCallback
            public void onStateChanged(View view0, int v) {
                if(v == 5) {
                    SideSheetDialog.this.cancel();
                }
            }
        });
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void cancel() {
        super.cancel();
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public Sheet getBehavior() {
        return this.getBehavior();
    }

    public SideSheetBehavior getBehavior() {
        Sheet sheet0 = super.getBehavior();
        if(!(sheet0 instanceof SideSheetBehavior)) {
            throw new IllegalStateException("The view is not associated with SideSheetBehavior");
        }
        return (SideSheetBehavior)sheet0;
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    Sheet getBehaviorFromSheet(FrameLayout frameLayout0) {
        return SideSheetBehavior.from(frameLayout0);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    int getDialogId() {
        return id.m3_side_sheet;
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    int getLayoutResId() {
        return layout.m3_side_sheet_dialog;
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    int getStateOnStart() {
        return 3;
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public boolean isDismissWithSheetAnimationEnabled() {
        return super.isDismissWithSheetAnimationEnabled();
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setContentView(int v) {
        super.setContentView(v);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setContentView(View view0) {
        super.setContentView(view0);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.setContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setDismissWithSheetAnimationEnabled(boolean z) {
        super.setDismissWithSheetAnimationEnabled(z);
    }

    @Override  // com.google.android.material.sidesheet.SheetDialog
    public void setSheetEdge(int v) {
        super.setSheetEdge(v);
    }
}

