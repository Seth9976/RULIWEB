package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.CheckableGroup;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;
import java.util.Set;

public class ChipGroup extends FlowLayout {
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int v, int v1) {
            super(v, v1);
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
        }
    }

    @Deprecated
    public interface OnCheckedChangeListener {
        void onCheckedChanged(ChipGroup arg1, int arg2);
    }

    public interface OnCheckedStateChangeListener {
        void onCheckedChanged(ChipGroup arg1, List arg2);
    }

    class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        PassThroughHierarchyChangeListener(com.google.android.material.chip.ChipGroup.1 chipGroup$10) {
        }

        static ViewGroup.OnHierarchyChangeListener access$302(PassThroughHierarchyChangeListener chipGroup$PassThroughHierarchyChangeListener0, ViewGroup.OnHierarchyChangeListener viewGroup$OnHierarchyChangeListener0) {
            chipGroup$PassThroughHierarchyChangeListener0.onHierarchyChangeListener = viewGroup$OnHierarchyChangeListener0;
            return viewGroup$OnHierarchyChangeListener0;
        }

        @Override  // android.view.ViewGroup$OnHierarchyChangeListener
        public void onChildViewAdded(View view0, View view1) {
            if(view0 == ChipGroup.this && view1 instanceof Chip) {
                if(view1.getId() == -1) {
                    view1.setId(ViewCompat.generateViewId());
                }
                ChipGroup.this.checkableGroup.addCheckable(((Chip)view1));
            }
            ViewGroup.OnHierarchyChangeListener viewGroup$OnHierarchyChangeListener0 = this.onHierarchyChangeListener;
            if(viewGroup$OnHierarchyChangeListener0 != null) {
                viewGroup$OnHierarchyChangeListener0.onChildViewAdded(view0, view1);
            }
        }

        @Override  // android.view.ViewGroup$OnHierarchyChangeListener
        public void onChildViewRemoved(View view0, View view1) {
            ChipGroup chipGroup0 = ChipGroup.this;
            if(view0 == chipGroup0 && view1 instanceof Chip) {
                chipGroup0.checkableGroup.removeCheckable(((Chip)view1));
            }
            ViewGroup.OnHierarchyChangeListener viewGroup$OnHierarchyChangeListener0 = this.onHierarchyChangeListener;
            if(viewGroup$OnHierarchyChangeListener0 != null) {
                viewGroup$OnHierarchyChangeListener0.onChildViewRemoved(view0, view1);
            }
        }
    }

    private static final int DEF_STYLE_RES;
    private final CheckableGroup checkableGroup;
    private int chipSpacingHorizontal;
    private int chipSpacingVertical;
    private final int defaultCheckedId;
    private OnCheckedStateChangeListener onCheckedStateChangeListener;
    private final PassThroughHierarchyChangeListener passThroughListener;

    static {
        ChipGroup.DEF_STYLE_RES = style.Widget_MaterialComponents_ChipGroup;
    }

    public ChipGroup(Context context0) {
        this(context0, null);
    }

    public ChipGroup(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.chipGroupStyle);
    }

    public ChipGroup(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, ChipGroup.DEF_STYLE_RES), attributeSet0, v);
        CheckableGroup checkableGroup0 = new CheckableGroup();
        this.checkableGroup = checkableGroup0;
        PassThroughHierarchyChangeListener chipGroup$PassThroughHierarchyChangeListener0 = new PassThroughHierarchyChangeListener(this, null);
        this.passThroughListener = chipGroup$PassThroughHierarchyChangeListener0;
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(this.getContext(), attributeSet0, styleable.ChipGroup, v, ChipGroup.DEF_STYLE_RES, new int[0]);
        int v1 = typedArray0.getDimensionPixelOffset(styleable.ChipGroup_chipSpacing, 0);
        this.setChipSpacingHorizontal(typedArray0.getDimensionPixelOffset(styleable.ChipGroup_chipSpacingHorizontal, v1));
        this.setChipSpacingVertical(typedArray0.getDimensionPixelOffset(styleable.ChipGroup_chipSpacingVertical, v1));
        this.setSingleLine(typedArray0.getBoolean(styleable.ChipGroup_singleLine, false));
        this.setSingleSelection(typedArray0.getBoolean(styleable.ChipGroup_singleSelection, false));
        this.setSelectionRequired(typedArray0.getBoolean(styleable.ChipGroup_selectionRequired, false));
        this.defaultCheckedId = typedArray0.getResourceId(styleable.ChipGroup_checkedChip, -1);
        typedArray0.recycle();
        checkableGroup0.setOnCheckedStateChangeListener(new com.google.android.material.internal.CheckableGroup.OnCheckedStateChangeListener() {
            @Override  // com.google.android.material.internal.CheckableGroup$OnCheckedStateChangeListener
            public void onCheckedStateChanged(Set set0) {
                if(ChipGroup.this.onCheckedStateChangeListener != null) {
                    OnCheckedStateChangeListener chipGroup$OnCheckedStateChangeListener0 = ChipGroup.this.onCheckedStateChangeListener;
                    List list0 = ChipGroup.this.checkableGroup.getCheckedIdsSortedByChildOrder(ChipGroup.this);
                    chipGroup$OnCheckedStateChangeListener0.onCheckedChanged(ChipGroup.this, list0);
                }
            }
        });
        super.setOnHierarchyChangeListener(chipGroup$PassThroughHierarchyChangeListener0);
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public void check(int v) {
        this.checkableGroup.check(v);
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return super.checkLayoutParams(viewGroup$LayoutParams0) && viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    public void clearCheck() {
        this.checkableGroup.clearCheck();
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return new LayoutParams(viewGroup$LayoutParams0);
    }

    public int getCheckedChipId() {
        return this.checkableGroup.getSingleCheckedId();
    }

    public List getCheckedChipIds() {
        return this.checkableGroup.getCheckedIdsSortedByChildOrder(this);
    }

    public int getChipSpacingHorizontal() {
        return this.chipSpacingHorizontal;
    }

    public int getChipSpacingVertical() {
        return this.chipSpacingVertical;
    }

    int getIndexOfChip(View view0) {
        if(!(view0 instanceof Chip)) {
            return -1;
        }
        int v1 = 0;
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view1 = this.getChildAt(v);
            if(view1 instanceof Chip && this.isChildVisible(v)) {
                if(((Chip)view1) == view0) {
                    return v1;
                }
                ++v1;
            }
        }
        return -1;
    }

    private int getVisibleChipCount() {
        int v1 = 0;
        for(int v = 0; v < this.getChildCount(); ++v) {
            if(this.getChildAt(v) instanceof Chip && this.isChildVisible(v)) {
                ++v1;
            }
        }
        return v1;
    }

    private boolean isChildVisible(int v) {
        return this.getChildAt(v).getVisibility() == 0;
    }

    public boolean isSelectionRequired() {
        return this.checkableGroup.isSelectionRequired();
    }

    @Override  // com.google.android.material.internal.FlowLayout
    public boolean isSingleLine() {
        return super.isSingleLine();
    }

    public boolean isSingleSelection() {
        return this.checkableGroup.isSingleSelection();
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int v = this.defaultCheckedId;
        if(v != -1) {
            this.checkableGroup.check(v);
        }
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
        int v = this.isSingleLine() ? this.getVisibleChipCount() : -1;
        accessibilityNodeInfoCompat0.setCollectionInfo(CollectionInfoCompat.obtain(this.getRowCount(), v, false, (this.isSingleSelection() ? 1 : 2)));
    }

    public void setChipSpacing(int v) {
        this.setChipSpacingHorizontal(v);
        this.setChipSpacingVertical(v);
    }

    public void setChipSpacingHorizontal(int v) {
        if(this.chipSpacingHorizontal != v) {
            this.chipSpacingHorizontal = v;
            this.setItemSpacing(v);
            this.requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int v) {
        this.setChipSpacingHorizontal(this.getResources().getDimensionPixelOffset(v));
    }

    public void setChipSpacingResource(int v) {
        this.setChipSpacing(this.getResources().getDimensionPixelOffset(v));
    }

    public void setChipSpacingVertical(int v) {
        if(this.chipSpacingVertical != v) {
            this.chipSpacingVertical = v;
            this.setLineSpacing(v);
            this.requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int v) {
        this.setChipSpacingVertical(this.getResources().getDimensionPixelOffset(v));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable0) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable0) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int v) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    @Deprecated
    public void setOnCheckedChangeListener(OnCheckedChangeListener chipGroup$OnCheckedChangeListener0) {
        if(chipGroup$OnCheckedChangeListener0 == null) {
            this.setOnCheckedStateChangeListener(null);
            return;
        }
        this.setOnCheckedStateChangeListener(new OnCheckedStateChangeListener() {
            @Override  // com.google.android.material.chip.ChipGroup$OnCheckedStateChangeListener
            public void onCheckedChanged(ChipGroup chipGroup0, List list0) {
                if(!ChipGroup.this.checkableGroup.isSingleSelection()) {
                    return;
                }
                int v = ChipGroup.this.getCheckedChipId();
                chipGroup$OnCheckedChangeListener0.onCheckedChanged(chipGroup0, v);
            }
        });
    }

    public void setOnCheckedStateChangeListener(OnCheckedStateChangeListener chipGroup$OnCheckedStateChangeListener0) {
        this.onCheckedStateChangeListener = chipGroup$OnCheckedStateChangeListener0;
    }

    @Override  // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener viewGroup$OnHierarchyChangeListener0) {
        PassThroughHierarchyChangeListener.access$302(this.passThroughListener, viewGroup$OnHierarchyChangeListener0);
    }

    public void setSelectionRequired(boolean z) {
        this.checkableGroup.setSelectionRequired(z);
    }

    @Deprecated
    public void setShowDividerHorizontal(int v) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int v) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    public void setSingleLine(int v) {
        this.setSingleLine(this.getResources().getBoolean(v));
    }

    @Override  // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(int v) {
        this.setSingleSelection(this.getResources().getBoolean(v));
    }

    public void setSingleSelection(boolean z) {
        this.checkableGroup.setSingleSelection(z);
    }
}

