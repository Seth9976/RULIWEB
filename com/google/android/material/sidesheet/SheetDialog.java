package com.google.android.material.sidesheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.style;
import com.google.android.material.motion.MaterialBackOrchestrator;

abstract class SheetDialog extends AppCompatDialog {
    private static final int COORDINATOR_LAYOUT_ID;
    private static final int TOUCH_OUTSIDE_ID;
    private MaterialBackOrchestrator backOrchestrator;
    private Sheet behavior;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;
    private FrameLayout container;
    boolean dismissWithAnimation;
    private FrameLayout sheet;

    static {
        SheetDialog.COORDINATOR_LAYOUT_ID = id.coordinator;
        SheetDialog.TOUCH_OUTSIDE_ID = id.touch_outside;
    }

    SheetDialog(Context context0, int v, int v1, int v2) {
        super(context0, SheetDialog.getThemeResId(context0, v, v1, v2));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.supportRequestWindowFeature(1);
    }

    abstract void addSheetCancelOnHideCallback(Sheet arg1);

    @Override  // android.app.Dialog
    public void cancel() {
        Sheet sheet0 = this.getBehavior();
        if(this.dismissWithAnimation && sheet0.getState() != 5) {
            sheet0.setState(5);
            return;
        }
        super.cancel();
    }

    private void ensureContainerAndBehavior() {
        if(this.container == null) {
            FrameLayout frameLayout0 = (FrameLayout)View.inflate(this.getContext(), this.getLayoutResId(), null);
            this.container = frameLayout0;
            FrameLayout frameLayout1 = (FrameLayout)frameLayout0.findViewById(this.getDialogId());
            this.sheet = frameLayout1;
            Sheet sheet0 = this.getBehaviorFromSheet(frameLayout1);
            this.behavior = sheet0;
            this.addSheetCancelOnHideCallback(sheet0);
            this.backOrchestrator = new MaterialBackOrchestrator(this.behavior, this.sheet);
        }
    }

    Sheet getBehavior() {
        if(this.behavior == null) {
            this.ensureContainerAndBehavior();
        }
        return this.behavior;
    }

    abstract Sheet getBehaviorFromSheet(FrameLayout arg1);

    private FrameLayout getContainer() {
        if(this.container == null) {
            this.ensureContainerAndBehavior();
        }
        return this.container;
    }

    abstract int getDialogId();

    abstract int getLayoutResId();

    private FrameLayout getSheet() {
        if(this.sheet == null) {
            this.ensureContainerAndBehavior();
        }
        return this.sheet;
    }

    abstract int getStateOnStart();

    private static int getThemeResId(Context context0, int v, int v1, int v2) {
        if(v == 0) {
            TypedValue typedValue0 = new TypedValue();
            return context0.getTheme().resolveAttribute(v1, typedValue0, true) ? typedValue0.resourceId : v2;
        }
        return v;
    }

    public boolean isDismissWithSheetAnimationEnabled() {
        return this.dismissWithAnimation;
    }

    // 检测为 Lambda 实现
    void lambda$wrapInSheet$0$com-google-android-material-sidesheet-SheetDialog(View view0) [...]

    private void maybeUpdateWindowAnimationsBasedOnLayoutDirection() {
        Window window0 = this.getWindow();
        if(window0 != null && (this.sheet != null && this.sheet.getLayoutParams() instanceof LayoutParams)) {
            window0.setWindowAnimations((GravityCompat.getAbsoluteGravity(((LayoutParams)this.sheet.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this.sheet)) == 3 ? style.Animation_Material3_SideSheetDialog_Left : style.Animation_Material3_SideSheetDialog_Right));
        }
    }

    @Override  // android.app.Dialog
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.maybeUpdateWindowAnimationsBasedOnLayoutDirection();
        this.updateListeningForBackCallbacks();
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        Window window0 = this.getWindow();
        if(window0 != null) {
            window0.setStatusBarColor(0);
            window0.addFlags(0x80000000);
            if(Build.VERSION.SDK_INT < 23) {
                window0.addFlags(0x4000000);
            }
            window0.setLayout(-1, -1);
        }
    }

    @Override  // android.app.Dialog
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialBackOrchestrator materialBackOrchestrator0 = this.backOrchestrator;
        if(materialBackOrchestrator0 != null) {
            materialBackOrchestrator0.stopListeningForBackCallbacks();
        }
    }

    @Override  // androidx.activity.ComponentDialog
    protected void onStart() {
        super.onStart();
        if(this.behavior != null && this.behavior.getState() == 5) {
            this.behavior.setState(this.getStateOnStart());
        }
    }

    @Override  // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if(this.cancelable != z) {
            this.cancelable = z;
        }
        if(this.getWindow() != null) {
            this.updateListeningForBackCallbacks();
        }
    }

    @Override  // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if(z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setContentView(int v) {
        super.setContentView(this.wrapInSheet(v, null, null));
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view0) {
        super.setContentView(this.wrapInSheet(0, view0, null));
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.setContentView(this.wrapInSheet(0, view0, viewGroup$LayoutParams0));
    }

    public void setDismissWithSheetAnimationEnabled(boolean z) {
        this.dismissWithAnimation = z;
    }

    public void setSheetEdge(int v) {
        FrameLayout frameLayout0 = this.sheet;
        if(frameLayout0 == null) {
            throw new IllegalStateException("Sheet view reference is null; sheet edge cannot be changed if the sheet view is null.");
        }
        if(ViewCompat.isLaidOut(frameLayout0)) {
            throw new IllegalStateException("Sheet view has been laid out; sheet edge cannot be changed once the sheet has been laid out.");
        }
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.sheet.getLayoutParams();
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            ((LayoutParams)viewGroup$LayoutParams0).gravity = v;
            this.maybeUpdateWindowAnimationsBasedOnLayoutDirection();
        }
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if(!this.canceledOnTouchOutsideSet) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(new int[]{0x101035B});
            this.canceledOnTouchOutside = typedArray0.getBoolean(0, true);
            typedArray0.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    private void updateListeningForBackCallbacks() {
        MaterialBackOrchestrator materialBackOrchestrator0 = this.backOrchestrator;
        if(materialBackOrchestrator0 == null) {
            return;
        }
        if(this.cancelable) {
            materialBackOrchestrator0.startListeningForBackCallbacks();
            return;
        }
        materialBackOrchestrator0.stopListeningForBackCallbacks();
    }

    private View wrapInSheet(int v, View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.ensureContainerAndBehavior();
        CoordinatorLayout coordinatorLayout0 = (CoordinatorLayout)this.getContainer().findViewById(SheetDialog.COORDINATOR_LAYOUT_ID);
        if(v != 0 && view0 == null) {
            view0 = this.getLayoutInflater().inflate(v, coordinatorLayout0, false);
        }
        FrameLayout frameLayout0 = this.getSheet();
        frameLayout0.removeAllViews();
        if(viewGroup$LayoutParams0 == null) {
            frameLayout0.addView(view0);
        }
        else {
            frameLayout0.addView(view0, viewGroup$LayoutParams0);
        }
        coordinatorLayout0.findViewById(SheetDialog.TOUCH_OUTSIDE_ID).setOnClickListener((View view0) -> if(this.cancelable && this.isShowing() && this.shouldWindowCloseOnTouchOutside()) {
            this.cancel();
        });
        ViewCompat.setAccessibilityDelegate(this.getSheet(), new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                if(SheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat0.addAction(0x100000);
                    accessibilityNodeInfoCompat0.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat0.setDismissable(false);
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                if(v == 0x100000 && SheetDialog.this.cancelable) {
                    SheetDialog.this.cancel();
                    return true;
                }
                return super.performAccessibilityAction(view0, v, bundle0);
            }
        });
        return this.container;
    }
}

