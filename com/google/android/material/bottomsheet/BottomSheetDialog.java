package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.shape.MaterialShapeDrawable;

public class BottomSheetDialog extends AppCompatDialog {
    static class EdgeToEdgeCallback extends BottomSheetCallback {
        private final WindowInsetsCompat insetsCompat;
        private final Boolean lightBottomSheet;
        private boolean lightStatusBar;
        private Window window;

        private EdgeToEdgeCallback(View view0, WindowInsetsCompat windowInsetsCompat0) {
            this.insetsCompat = windowInsetsCompat0;
            MaterialShapeDrawable materialShapeDrawable0 = BottomSheetBehavior.from(view0).getMaterialShapeDrawable();
            ColorStateList colorStateList0 = materialShapeDrawable0 == null ? ViewCompat.getBackgroundTintList(view0) : materialShapeDrawable0.getFillColor();
            if(colorStateList0 != null) {
                this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(colorStateList0.getDefaultColor()));
                return;
            }
            Integer integer0 = ViewUtils.getBackgroundColor(view0);
            if(integer0 != null) {
                this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(((int)integer0)));
                return;
            }
            this.lightBottomSheet = null;
        }

        EdgeToEdgeCallback(View view0, WindowInsetsCompat windowInsetsCompat0, com.google.android.material.bottomsheet.BottomSheetDialog.1 bottomSheetDialog$10) {
            this(view0, windowInsetsCompat0);
        }

        @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
        void onLayout(View view0) {
            this.setPaddingForPosition(view0);
        }

        @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
        public void onSlide(View view0, float f) {
            this.setPaddingForPosition(view0);
        }

        @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
        public void onStateChanged(View view0, int v) {
            this.setPaddingForPosition(view0);
        }

        private void setPaddingForPosition(View view0) {
            if(view0.getTop() < this.insetsCompat.getSystemWindowInsetTop()) {
                Window window0 = this.window;
                if(window0 != null) {
                    EdgeToEdgeUtils.setLightStatusBar(window0, (this.lightBottomSheet == null ? this.lightStatusBar : this.lightBottomSheet.booleanValue()));
                }
                view0.setPadding(view0.getPaddingLeft(), this.insetsCompat.getSystemWindowInsetTop() - view0.getTop(), view0.getPaddingRight(), view0.getPaddingBottom());
                return;
            }
            if(view0.getTop() != 0) {
                Window window1 = this.window;
                if(window1 != null) {
                    EdgeToEdgeUtils.setLightStatusBar(window1, this.lightStatusBar);
                }
                view0.setPadding(view0.getPaddingLeft(), 0, view0.getPaddingRight(), view0.getPaddingBottom());
            }
        }

        void setWindow(Window window0) {
            if(this.window != window0) {
                this.window = window0;
                if(window0 != null) {
                    this.lightStatusBar = WindowCompat.getInsetsController(window0, window0.getDecorView()).isAppearanceLightStatusBars();
                }
            }
        }
    }

    private MaterialBackOrchestrator backOrchestrator;
    private BottomSheetBehavior behavior;
    private FrameLayout bottomSheet;
    private BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;
    private FrameLayout container;
    private CoordinatorLayout coordinator;
    boolean dismissWithAnimation;
    private EdgeToEdgeCallback edgeToEdgeCallback;
    private boolean edgeToEdgeEnabled;

    public BottomSheetDialog(Context context0) {
        this(context0, 0);
        this.edgeToEdgeEnabled = this.getContext().getTheme().obtainStyledAttributes(new int[]{attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    public BottomSheetDialog(Context context0, int v) {
        super(context0, BottomSheetDialog.getThemeResId(context0, v));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetCallback() {
            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onSlide(View view0, float f) {
            }

            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onStateChanged(View view0, int v) {
                if(v == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        this.supportRequestWindowFeature(1);
        this.edgeToEdgeEnabled = this.getContext().getTheme().obtainStyledAttributes(new int[]{attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    protected BottomSheetDialog(Context context0, boolean z, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        super(context0, z, dialogInterface$OnCancelListener0);
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetCallback() {
            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onSlide(View view0, float f) {
            }

            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onStateChanged(View view0, int v) {
                if(v == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        this.supportRequestWindowFeature(1);
        this.cancelable = z;
        this.edgeToEdgeEnabled = this.getContext().getTheme().obtainStyledAttributes(new int[]{attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    @Override  // android.app.Dialog
    public void cancel() {
        BottomSheetBehavior bottomSheetBehavior0 = this.getBehavior();
        if(this.dismissWithAnimation && bottomSheetBehavior0.getState() != 5) {
            bottomSheetBehavior0.setState(5);
            return;
        }
        super.cancel();
    }

    private FrameLayout ensureContainerAndBehavior() {
        if(this.container == null) {
            FrameLayout frameLayout0 = (FrameLayout)View.inflate(this.getContext(), layout.design_bottom_sheet_dialog, null);
            this.container = frameLayout0;
            this.coordinator = (CoordinatorLayout)frameLayout0.findViewById(id.coordinator);
            FrameLayout frameLayout1 = (FrameLayout)this.container.findViewById(id.design_bottom_sheet);
            this.bottomSheet = frameLayout1;
            BottomSheetBehavior bottomSheetBehavior0 = BottomSheetBehavior.from(frameLayout1);
            this.behavior = bottomSheetBehavior0;
            bottomSheetBehavior0.addBottomSheetCallback(this.bottomSheetCallback);
            this.behavior.setHideable(this.cancelable);
            this.backOrchestrator = new MaterialBackOrchestrator(this.behavior, this.bottomSheet);
        }
        return this.container;
    }

    public BottomSheetBehavior getBehavior() {
        if(this.behavior == null) {
            this.ensureContainerAndBehavior();
        }
        return this.behavior;
    }

    public boolean getDismissWithAnimation() {
        return this.dismissWithAnimation;
    }

    public boolean getEdgeToEdgeEnabled() {
        return this.edgeToEdgeEnabled;
    }

    private static int getThemeResId(Context context0, int v) {
        if(v == 0) {
            TypedValue typedValue0 = new TypedValue();
            return context0.getTheme().resolveAttribute(attr.bottomSheetDialogTheme, typedValue0, true) ? typedValue0.resourceId : style.Theme_Design_Light_BottomSheetDialog;
        }
        return v;
    }

    @Override  // android.app.Dialog
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window0 = this.getWindow();
        if(window0 != null) {
            int v = !this.edgeToEdgeEnabled || Color.alpha(window0.getNavigationBarColor()) >= 0xFF ? 0 : 1;
            FrameLayout frameLayout0 = this.container;
            if(frameLayout0 != null) {
                frameLayout0.setFitsSystemWindows(((boolean)(v ^ 1)));
            }
            CoordinatorLayout coordinatorLayout0 = this.coordinator;
            if(coordinatorLayout0 != null) {
                coordinatorLayout0.setFitsSystemWindows(((boolean)(v ^ 1)));
            }
            WindowCompat.setDecorFitsSystemWindows(window0, ((boolean)(v ^ 1)));
            EdgeToEdgeCallback bottomSheetDialog$EdgeToEdgeCallback0 = this.edgeToEdgeCallback;
            if(bottomSheetDialog$EdgeToEdgeCallback0 != null) {
                bottomSheetDialog$EdgeToEdgeCallback0.setWindow(window0);
            }
        }
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
        EdgeToEdgeCallback bottomSheetDialog$EdgeToEdgeCallback0 = this.edgeToEdgeCallback;
        if(bottomSheetDialog$EdgeToEdgeCallback0 != null) {
            bottomSheetDialog$EdgeToEdgeCallback0.setWindow(null);
        }
        MaterialBackOrchestrator materialBackOrchestrator0 = this.backOrchestrator;
        if(materialBackOrchestrator0 != null) {
            materialBackOrchestrator0.stopListeningForBackCallbacks();
        }
    }

    @Override  // androidx.activity.ComponentDialog
    protected void onStart() {
        super.onStart();
        if(this.behavior != null && this.behavior.getState() == 5) {
            this.behavior.setState(4);
        }
    }

    void removeDefaultCallback() {
        this.behavior.removeBottomSheetCallback(this.bottomSheetCallback);
    }

    @Override  // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if(this.cancelable != z) {
            this.cancelable = z;
            BottomSheetBehavior bottomSheetBehavior0 = this.behavior;
            if(bottomSheetBehavior0 != null) {
                bottomSheetBehavior0.setHideable(z);
            }
            if(this.getWindow() != null) {
                this.updateListeningForBackCallbacks();
            }
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
        super.setContentView(this.wrapInBottomSheet(v, null, null));
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view0) {
        super.setContentView(this.wrapInBottomSheet(0, view0, null));
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.setContentView(this.wrapInBottomSheet(0, view0, viewGroup$LayoutParams0));
    }

    public void setDismissWithAnimation(boolean z) {
        this.dismissWithAnimation = z;
    }

    @Deprecated
    public static void setLightStatusBar(View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 23) {
            int v = view0.getSystemUiVisibility();
            view0.setSystemUiVisibility((z ? v | 0x2000 : v & 0xFFFFDFFF));
        }
    }

    boolean shouldWindowCloseOnTouchOutside() {
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

    private View wrapInBottomSheet(int v, View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.ensureContainerAndBehavior();
        CoordinatorLayout coordinatorLayout0 = (CoordinatorLayout)this.container.findViewById(id.coordinator);
        if(v != 0 && view0 == null) {
            view0 = this.getLayoutInflater().inflate(v, coordinatorLayout0, false);
        }
        if(this.edgeToEdgeEnabled) {
            ViewCompat.setOnApplyWindowInsetsListener(this.bottomSheet, new OnApplyWindowInsetsListener() {
                @Override  // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                    if(BottomSheetDialog.this.edgeToEdgeCallback != null) {
                        BottomSheetDialog.this.behavior.removeBottomSheetCallback(BottomSheetDialog.this.edgeToEdgeCallback);
                    }
                    if(windowInsetsCompat0 != null) {
                        EdgeToEdgeCallback bottomSheetDialog$EdgeToEdgeCallback0 = new EdgeToEdgeCallback(BottomSheetDialog.this.bottomSheet, windowInsetsCompat0, null);
                        BottomSheetDialog.this.edgeToEdgeCallback = bottomSheetDialog$EdgeToEdgeCallback0;
                        BottomSheetDialog.this.edgeToEdgeCallback.setWindow(BottomSheetDialog.this.getWindow());
                        BottomSheetDialog.this.behavior.addBottomSheetCallback(BottomSheetDialog.this.edgeToEdgeCallback);
                    }
                    return windowInsetsCompat0;
                }
            });
        }
        this.bottomSheet.removeAllViews();
        if(viewGroup$LayoutParams0 == null) {
            this.bottomSheet.addView(view0);
        }
        else {
            this.bottomSheet.addView(view0, viewGroup$LayoutParams0);
        }
        coordinatorLayout0.findViewById(id.touch_outside).setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(BottomSheetDialog.this.cancelable && BottomSheetDialog.this.isShowing() && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside()) {
                    BottomSheetDialog.this.cancel();
                }
            }
        });
        ViewCompat.setAccessibilityDelegate(this.bottomSheet, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                if(BottomSheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat0.addAction(0x100000);
                    accessibilityNodeInfoCompat0.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat0.setDismissable(false);
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                if(v == 0x100000 && BottomSheetDialog.this.cancelable) {
                    BottomSheetDialog.this.cancel();
                    return true;
                }
                return super.performAccessibilityAction(view0, v, bundle0);
            }
        });
        this.bottomSheet.setOnTouchListener(new View.OnTouchListener() {
            @Override  // android.view.View$OnTouchListener
            public boolean onTouch(View view0, MotionEvent motionEvent0) {
                return true;
            }
        });
        return this.container;
    }
}

