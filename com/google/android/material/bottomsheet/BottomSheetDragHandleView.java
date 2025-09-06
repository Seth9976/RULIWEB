package com.google.android.material.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import com.google.android.material.R.attr;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    private static final int DEF_STYLE_RES;
    private final AccessibilityManager accessibilityManager;
    private boolean accessibilityServiceEnabled;
    private BottomSheetBehavior bottomSheetBehavior;
    private final BottomSheetCallback bottomSheetCallback;
    private final String clickFeedback;
    private final String clickToCollapseActionLabel;
    private boolean clickToExpand;
    private final String clickToExpandActionLabel;
    private boolean interactable;

    static {
        BottomSheetDragHandleView.DEF_STYLE_RES = style.Widget_Material3_BottomSheet_DragHandle;
    }

    public BottomSheetDragHandleView(Context context0) {
        this(context0, null);
    }

    public BottomSheetDragHandleView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.bottomSheetDragHandleStyle);
    }

    public BottomSheetDragHandleView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, BottomSheetDragHandleView.DEF_STYLE_RES), attributeSet0, v);
        this.clickToExpandActionLabel = this.getResources().getString(string.bottomsheet_action_expand);
        this.clickToCollapseActionLabel = this.getResources().getString(string.bottomsheet_action_collapse);
        this.clickFeedback = this.getResources().getString(string.bottomsheet_drag_handle_clicked);
        this.bottomSheetCallback = new BottomSheetCallback() {
            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onSlide(View view0, float f) {
            }

            @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
            public void onStateChanged(View view0, int v) {
                BottomSheetDragHandleView.this.onBottomSheetStateChanged(v);
            }
        };
        this.accessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.updateInteractableState();
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
                super.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
                if(accessibilityEvent0.getEventType() == 1) {
                    BottomSheetDragHandleView.this.expandOrCollapseBottomSheetIfPossible();
                }
            }
        });
    }

    private void announceAccessibilityEvent(String s) {
        if(this.accessibilityManager == null) {
            return;
        }
        AccessibilityEvent accessibilityEvent0 = AccessibilityEvent.obtain(0x4000);
        accessibilityEvent0.getText().add(s);
        this.accessibilityManager.sendAccessibilityEvent(accessibilityEvent0);
    }

    private boolean expandOrCollapseBottomSheetIfPossible() {
        boolean z = false;
        if(!this.interactable) {
            return false;
        }
        this.announceAccessibilityEvent(this.clickFeedback);
        if(!this.bottomSheetBehavior.isFitToContents()) {
            z = true;
        }
        int v = this.bottomSheetBehavior.getState();
        int v1 = 6;
        int v2 = 3;
        if(v != 4) {
            if(v != 3) {
                if(!this.clickToExpand) {
                    v2 = 4;
                }
                v1 = v2;
            }
            else if(!z) {
                v1 = 4;
            }
        }
        else if(!z) {
            v1 = 3;
        }
        this.bottomSheetBehavior.setState(v1);
        return true;
    }

    private BottomSheetBehavior findParentBottomSheetBehavior() {
        Behavior coordinatorLayout$Behavior0;
        View view0 = this;
    alab1:
        while(true) {
            do {
                view0 = BottomSheetDragHandleView.getParentView(view0);
                if(view0 == null) {
                    break alab1;
                }
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
                    continue alab1;
                }
                coordinatorLayout$Behavior0 = ((LayoutParams)viewGroup$LayoutParams0).getBehavior();
            }
            while(!(coordinatorLayout$Behavior0 instanceof BottomSheetBehavior));
            return (BottomSheetBehavior)coordinatorLayout$Behavior0;
        }
        return null;
    }

    private static View getParentView(View view0) {
        ViewParent viewParent0 = view0.getParent();
        return viewParent0 instanceof View ? ((View)viewParent0) : null;
    }

    // 检测为 Lambda 实现
    boolean lambda$onBottomSheetStateChanged$0$com-google-android-material-bottomsheet-BottomSheetDragHandleView(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) [...]

    @Override  // android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean z) {
        this.accessibilityServiceEnabled = z;
        this.updateInteractableState();
    }

    @Override  // android.widget.ImageView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.setBottomSheetBehavior(this.findParentBottomSheetBehavior());
        AccessibilityManager accessibilityManager0 = this.accessibilityManager;
        if(accessibilityManager0 != null) {
            accessibilityManager0.addAccessibilityStateChangeListener(this);
            this.onAccessibilityStateChanged(this.accessibilityManager.isEnabled());
        }
    }

    private void onBottomSheetStateChanged(int v) {
        switch(v) {
            case 3: {
                this.clickToExpand = false;
                break;
            }
            case 4: {
                this.clickToExpand = true;
            }
        }
        ViewCompat.replaceAccessibilityAction(this, AccessibilityActionCompat.ACTION_CLICK, (this.clickToExpand ? this.clickToExpandActionLabel : this.clickToCollapseActionLabel), (View view0, CommandArguments accessibilityViewCommand$CommandArguments0) -> this.expandOrCollapseBottomSheetIfPossible());
    }

    @Override  // android.widget.ImageView
    protected void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager0 = this.accessibilityManager;
        if(accessibilityManager0 != null) {
            accessibilityManager0.removeAccessibilityStateChangeListener(this);
        }
        this.setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }

    private void setBottomSheetBehavior(BottomSheetBehavior bottomSheetBehavior0) {
        BottomSheetBehavior bottomSheetBehavior1 = this.bottomSheetBehavior;
        if(bottomSheetBehavior1 != null) {
            bottomSheetBehavior1.removeBottomSheetCallback(this.bottomSheetCallback);
            this.bottomSheetBehavior.setAccessibilityDelegateView(null);
        }
        this.bottomSheetBehavior = bottomSheetBehavior0;
        if(bottomSheetBehavior0 != null) {
            bottomSheetBehavior0.setAccessibilityDelegateView(this);
            this.onBottomSheetStateChanged(this.bottomSheetBehavior.getState());
            this.bottomSheetBehavior.addBottomSheetCallback(this.bottomSheetCallback);
        }
        this.updateInteractableState();
    }

    private void updateInteractableState() {
        int v = 1;
        this.interactable = this.accessibilityServiceEnabled && this.bottomSheetBehavior != null;
        if(this.bottomSheetBehavior == null) {
            v = 2;
        }
        ViewCompat.setImportantForAccessibility(this, v);
        this.setClickable(this.interactable);
    }
}

