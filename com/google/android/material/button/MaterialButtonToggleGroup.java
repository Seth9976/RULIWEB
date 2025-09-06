package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class MaterialButtonToggleGroup extends LinearLayout {
    static class CornerData {
        CornerSize bottomLeft;
        CornerSize bottomRight;
        private static final CornerSize noCorner;
        CornerSize topLeft;
        CornerSize topRight;

        static {
            CornerData.noCorner = new AbsoluteCornerSize(0.0f);
        }

        CornerData(CornerSize cornerSize0, CornerSize cornerSize1, CornerSize cornerSize2, CornerSize cornerSize3) {
            this.topLeft = cornerSize0;
            this.topRight = cornerSize2;
            this.bottomRight = cornerSize3;
            this.bottomLeft = cornerSize1;
        }

        public static CornerData bottom(CornerData materialButtonToggleGroup$CornerData0) {
            return new CornerData(CornerData.noCorner, materialButtonToggleGroup$CornerData0.bottomLeft, CornerData.noCorner, materialButtonToggleGroup$CornerData0.bottomRight);
        }

        // 去混淆评级： 低(20)
        public static CornerData end(CornerData materialButtonToggleGroup$CornerData0, View view0) {
            return ViewUtils.isLayoutRtl(view0) ? CornerData.left(materialButtonToggleGroup$CornerData0) : CornerData.right(materialButtonToggleGroup$CornerData0);
        }

        public static CornerData left(CornerData materialButtonToggleGroup$CornerData0) {
            return new CornerData(materialButtonToggleGroup$CornerData0.topLeft, materialButtonToggleGroup$CornerData0.bottomLeft, CornerData.noCorner, CornerData.noCorner);
        }

        public static CornerData right(CornerData materialButtonToggleGroup$CornerData0) {
            return new CornerData(CornerData.noCorner, CornerData.noCorner, materialButtonToggleGroup$CornerData0.topRight, materialButtonToggleGroup$CornerData0.bottomRight);
        }

        // 去混淆评级： 低(20)
        public static CornerData start(CornerData materialButtonToggleGroup$CornerData0, View view0) {
            return ViewUtils.isLayoutRtl(view0) ? CornerData.right(materialButtonToggleGroup$CornerData0) : CornerData.left(materialButtonToggleGroup$CornerData0);
        }

        public static CornerData top(CornerData materialButtonToggleGroup$CornerData0) {
            return new CornerData(materialButtonToggleGroup$CornerData0.topLeft, CornerData.noCorner, materialButtonToggleGroup$CornerData0.topRight, CornerData.noCorner);
        }
    }

    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup arg1, int arg2, boolean arg3);
    }

    class PressedStateTracker implements OnPressedChangeListener {
        private PressedStateTracker() {
        }

        PressedStateTracker(com.google.android.material.button.MaterialButtonToggleGroup.1 materialButtonToggleGroup$10) {
        }

        @Override  // com.google.android.material.button.MaterialButton$OnPressedChangeListener
        public void onPressedChanged(MaterialButton materialButton0, boolean z) {
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    private static final int DEF_STYLE_RES = 0;
    private static final String LOG_TAG = "MButtonToggleGroup";
    private Set checkedIds;
    private Integer[] childOrder;
    private final Comparator childOrderComparator;
    private final int defaultCheckId;
    private final LinkedHashSet onButtonCheckedListeners;
    private final List originalCornerData;
    private final PressedStateTracker pressedStateTracker;
    private boolean selectionRequired;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    static {
        MaterialButtonToggleGroup.DEF_STYLE_RES = style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    }

    public MaterialButtonToggleGroup(Context context0) {
        this(context0, null);
    }

    public MaterialButtonToggleGroup(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialButtonToggleGroupStyle);
    }

    public MaterialButtonToggleGroup(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialButtonToggleGroup.DEF_STYLE_RES), attributeSet0, v);
        this.originalCornerData = new ArrayList();
        this.pressedStateTracker = new PressedStateTracker(this, null);
        this.onButtonCheckedListeners = new LinkedHashSet();
        this.childOrderComparator = new Comparator() {
            public int compare(MaterialButton materialButton0, MaterialButton materialButton1) {
                int v = Boolean.valueOf(materialButton0.isChecked()).compareTo(Boolean.valueOf(materialButton1.isChecked()));
                if(v != 0) {
                    return v;
                }
                int v1 = Boolean.valueOf(materialButton0.isPressed()).compareTo(Boolean.valueOf(materialButton1.isPressed()));
                return v1 == 0 ? MaterialButtonToggleGroup.this.indexOfChild(materialButton0).compareTo(MaterialButtonToggleGroup.this.indexOfChild(materialButton1)) : v1;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((MaterialButton)object0), ((MaterialButton)object1));
            }
        };
        this.skipCheckedStateTracker = false;
        this.checkedIds = new HashSet();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(this.getContext(), attributeSet0, styleable.MaterialButtonToggleGroup, v, MaterialButtonToggleGroup.DEF_STYLE_RES, new int[0]);
        this.setSingleSelection(typedArray0.getBoolean(styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.defaultCheckId = typedArray0.getResourceId(styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.selectionRequired = typedArray0.getBoolean(styleable.MaterialButtonToggleGroup_selectionRequired, false);
        this.setChildrenDrawingOrderEnabled(true);
        this.setEnabled(typedArray0.getBoolean(styleable.MaterialButtonToggleGroup_android_enabled, true));
        typedArray0.recycle();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public void addOnButtonCheckedListener(OnButtonCheckedListener materialButtonToggleGroup$OnButtonCheckedListener0) {
        this.onButtonCheckedListeners.add(materialButtonToggleGroup$OnButtonCheckedListener0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(!(view0 instanceof MaterialButton)) {
            Log.e("MButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
        this.setGeneratedIdIfNeeded(((MaterialButton)view0));
        this.setupButtonChild(((MaterialButton)view0));
        this.checkInternal(((MaterialButton)view0).getId(), ((MaterialButton)view0).isChecked());
        ShapeAppearanceModel shapeAppearanceModel0 = ((MaterialButton)view0).getShapeAppearanceModel();
        CornerData materialButtonToggleGroup$CornerData0 = new CornerData(shapeAppearanceModel0.getTopLeftCornerSize(), shapeAppearanceModel0.getBottomLeftCornerSize(), shapeAppearanceModel0.getTopRightCornerSize(), shapeAppearanceModel0.getBottomRightCornerSize());
        this.originalCornerData.add(materialButtonToggleGroup$CornerData0);
        ((MaterialButton)view0).setEnabled(this.isEnabled());
        ViewCompat.setAccessibilityDelegate(((MaterialButton)view0), new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.this.getIndexWithinVisibleButtons(view0), 1, false, ((MaterialButton)view0).isChecked()));
            }
        });
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int v = this.getFirstVisibleChildIndex();
        if(v == -1) {
            return;
        }
        for(int v1 = v + 1; v1 < this.getChildCount(); ++v1) {
            MaterialButton materialButton0 = this.getChildButton(v1);
            int v2 = Math.min(materialButton0.getStrokeWidth(), this.getChildButton(v1 - 1).getStrokeWidth());
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = this.buildLayoutParams(materialButton0);
            if(this.getOrientation() == 0) {
                MarginLayoutParamsCompat.setMarginEnd(linearLayout$LayoutParams0, 0);
                MarginLayoutParamsCompat.setMarginStart(linearLayout$LayoutParams0, -v2);
                linearLayout$LayoutParams0.topMargin = 0;
            }
            else {
                linearLayout$LayoutParams0.bottomMargin = 0;
                linearLayout$LayoutParams0.topMargin = -v2;
                MarginLayoutParamsCompat.setMarginStart(linearLayout$LayoutParams0, 0);
            }
            materialButton0.setLayoutParams(linearLayout$LayoutParams0);
        }
        this.resetChildMargins(v);
    }

    private LinearLayout.LayoutParams buildLayoutParams(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof LinearLayout.LayoutParams ? ((LinearLayout.LayoutParams)viewGroup$LayoutParams0) : new LinearLayout.LayoutParams(viewGroup$LayoutParams0.width, viewGroup$LayoutParams0.height);
    }

    public void check(int v) {
        this.checkInternal(v, true);
    }

    private void checkInternal(int v, boolean z) {
        if(v == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + -1);
            return;
        }
        HashSet hashSet0 = new HashSet(this.checkedIds);
        if(z && !hashSet0.contains(v)) {
            if(this.singleSelection && !hashSet0.isEmpty()) {
                hashSet0.clear();
            }
            hashSet0.add(v);
            this.updateCheckedIds(hashSet0);
            return;
        }
        if(!z && hashSet0.contains(v)) {
            if(!this.selectionRequired || hashSet0.size() > 1) {
                hashSet0.remove(v);
            }
            this.updateCheckedIds(hashSet0);
        }
    }

    public void clearChecked() {
        this.updateCheckedIds(new HashSet());
    }

    public void clearOnButtonCheckedListeners() {
        this.onButtonCheckedListeners.clear();
    }

    @Override  // android.view.ViewGroup
    protected void dispatchDraw(Canvas canvas0) {
        this.updateChildOrder();
        super.dispatchDraw(canvas0);
    }

    private void dispatchOnButtonChecked(int v, boolean z) {
        for(Object object0: this.onButtonCheckedListeners) {
            ((OnButtonCheckedListener)object0).onButtonChecked(this, v, z);
        }
    }

    public int getCheckedButtonId() {
        return this.singleSelection && !this.checkedIds.isEmpty() ? this.checkedIds.iterator().next() : -1;
    }

    public List getCheckedButtonIds() {
        List list0 = new ArrayList();
        for(int v = 0; v < this.getChildCount(); ++v) {
            int v1 = this.getChildButton(v).getId();
            if(this.checkedIds.contains(v1)) {
                list0.add(v1);
            }
        }
        return list0;
    }

    private MaterialButton getChildButton(int v) {
        return (MaterialButton)this.getChildAt(v);
    }

    @Override  // android.view.ViewGroup
    protected int getChildDrawingOrder(int v, int v1) {
        Integer[] arr_integer = this.childOrder;
        if(arr_integer != null && v1 < arr_integer.length) {
            return (int)arr_integer[v1];
        }
        Log.w("MButtonToggleGroup", "Child order wasn\'t updated");
        return v1;
    }

    private int getFirstVisibleChildIndex() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.isChildVisible(v1)) {
                return v1;
            }
        }
        return -1;
    }

    private int getIndexWithinVisibleButtons(View view0) {
        if(!(view0 instanceof MaterialButton)) {
            return -1;
        }
        int v1 = 0;
        for(int v = 0; v < this.getChildCount(); ++v) {
            if(this.getChildAt(v) == view0) {
                return v1;
            }
            if(this.getChildAt(v) instanceof MaterialButton && this.isChildVisible(v)) {
                ++v1;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for(int v = this.getChildCount() - 1; v >= 0; --v) {
            if(this.isChildVisible(v)) {
                return v;
            }
        }
        return -1;
    }

    private CornerData getNewCornerData(int v, int v1, int v2) {
        CornerData materialButtonToggleGroup$CornerData0 = (CornerData)this.originalCornerData.get(v);
        if(v1 == v2) {
            return materialButtonToggleGroup$CornerData0;
        }
        boolean z = this.getOrientation() == 0;
        if(v == v1) {
            return z ? CornerData.start(materialButtonToggleGroup$CornerData0, this) : CornerData.top(materialButtonToggleGroup$CornerData0);
        }
        if(v == v2) {
            return z ? CornerData.end(materialButtonToggleGroup$CornerData0, this) : CornerData.bottom(materialButtonToggleGroup$CornerData0);
        }
        return null;
    }

    private int getVisibleButtonCount() {
        int v1 = 0;
        for(int v = 0; v < this.getChildCount(); ++v) {
            if(this.getChildAt(v) instanceof MaterialButton && this.isChildVisible(v)) {
                ++v1;
            }
        }
        return v1;
    }

    private boolean isChildVisible(int v) {
        return this.getChildAt(v).getVisibility() != 8;
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    void onButtonCheckedStateChanged(MaterialButton materialButton0, boolean z) {
        if(this.skipCheckedStateTracker) {
            return;
        }
        this.checkInternal(materialButton0.getId(), z);
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int v = this.defaultCheckId;
        if(v != -1) {
            this.updateCheckedIds(Collections.singleton(v));
        }
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0).setCollectionInfo(CollectionInfoCompat.obtain(1, this.getVisibleButtonCount(), false, (this.isSingleSelection() ? 1 : 2)));
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        this.updateChildShapes();
        this.adjustChildMarginsAndUpdateLayout();
        super.onMeasure(v, v1);
    }

    @Override  // android.view.ViewGroup
    public void onViewRemoved(View view0) {
        super.onViewRemoved(view0);
        if(view0 instanceof MaterialButton) {
            ((MaterialButton)view0).setOnPressedChangeListenerInternal(null);
        }
        int v = this.indexOfChild(view0);
        if(v >= 0) {
            this.originalCornerData.remove(v);
        }
        this.updateChildShapes();
        this.adjustChildMarginsAndUpdateLayout();
    }

    public void removeOnButtonCheckedListener(OnButtonCheckedListener materialButtonToggleGroup$OnButtonCheckedListener0) {
        this.onButtonCheckedListeners.remove(materialButtonToggleGroup$OnButtonCheckedListener0);
    }

    private void resetChildMargins(int v) {
        if(this.getChildCount() != 0 && v != -1) {
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)this.getChildButton(v).getLayoutParams();
            if(this.getOrientation() == 1) {
                linearLayout$LayoutParams0.topMargin = 0;
                linearLayout$LayoutParams0.bottomMargin = 0;
                return;
            }
            MarginLayoutParamsCompat.setMarginEnd(linearLayout$LayoutParams0, 0);
            MarginLayoutParamsCompat.setMarginStart(linearLayout$LayoutParams0, 0);
            linearLayout$LayoutParams0.leftMargin = 0;
            linearLayout$LayoutParams0.rightMargin = 0;
        }
    }

    private void setCheckedStateForView(int v, boolean z) {
        View view0 = this.findViewById(v);
        if(view0 instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton)view0).setChecked(z);
            this.skipCheckedStateTracker = false;
        }
    }

    @Override  // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for(int v = 0; v < this.getChildCount(); ++v) {
            this.getChildButton(v).setEnabled(z);
        }
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton0) {
        if(materialButton0.getId() == -1) {
            materialButton0.setId(ViewCompat.generateViewId());
        }
    }

    public void setSelectionRequired(boolean z) {
        this.selectionRequired = z;
    }

    public void setSingleSelection(int v) {
        this.setSingleSelection(this.getResources().getBoolean(v));
    }

    public void setSingleSelection(boolean z) {
        if(this.singleSelection != z) {
            this.singleSelection = z;
            this.clearChecked();
        }
        this.updateChildrenA11yClassName();
    }

    private void setupButtonChild(MaterialButton materialButton0) {
        materialButton0.setMaxLines(1);
        materialButton0.setEllipsize(TextUtils.TruncateAt.END);
        materialButton0.setCheckable(true);
        materialButton0.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        materialButton0.setShouldDrawSurfaceColorStroke(true);
    }

    public void uncheck(int v) {
        this.checkInternal(v, false);
    }

    private static void updateBuilderWithCornerData(Builder shapeAppearanceModel$Builder0, CornerData materialButtonToggleGroup$CornerData0) {
        if(materialButtonToggleGroup$CornerData0 == null) {
            shapeAppearanceModel$Builder0.setAllCornerSizes(0.0f);
            return;
        }
        shapeAppearanceModel$Builder0.setTopLeftCornerSize(materialButtonToggleGroup$CornerData0.topLeft).setBottomLeftCornerSize(materialButtonToggleGroup$CornerData0.bottomLeft).setTopRightCornerSize(materialButtonToggleGroup$CornerData0.topRight).setBottomRightCornerSize(materialButtonToggleGroup$CornerData0.bottomRight);
    }

    private void updateCheckedIds(Set set0) {
        Set set1 = this.checkedIds;
        this.checkedIds = new HashSet(set0);
        for(int v = 0; v < this.getChildCount(); ++v) {
            int v1 = this.getChildButton(v).getId();
            this.setCheckedStateForView(v1, set0.contains(v1));
            if(set1.contains(v1) != set0.contains(v1)) {
                this.dispatchOnButtonChecked(v1, set0.contains(v1));
            }
        }
        this.invalidate();
    }

    private void updateChildOrder() {
        TreeMap treeMap0 = new TreeMap(this.childOrderComparator);
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            treeMap0.put(this.getChildButton(v1), v1);
        }
        this.childOrder = (Integer[])treeMap0.values().toArray(new Integer[0]);
    }

    void updateChildShapes() {
        int v = this.getChildCount();
        int v1 = this.getFirstVisibleChildIndex();
        int v2 = this.getLastVisibleChildIndex();
        for(int v3 = 0; v3 < v; ++v3) {
            MaterialButton materialButton0 = this.getChildButton(v3);
            if(materialButton0.getVisibility() != 8) {
                Builder shapeAppearanceModel$Builder0 = materialButton0.getShapeAppearanceModel().toBuilder();
                MaterialButtonToggleGroup.updateBuilderWithCornerData(shapeAppearanceModel$Builder0, this.getNewCornerData(v3, v1, v2));
                materialButton0.setShapeAppearanceModel(shapeAppearanceModel$Builder0.build());
            }
        }
    }

    private void updateChildrenA11yClassName() {
        for(int v = 0; v < this.getChildCount(); ++v) {
            Class class0 = this.singleSelection ? RadioButton.class : ToggleButton.class;
            this.getChildButton(v).setA11yClassName(class0.getName());
        }
    }
}

