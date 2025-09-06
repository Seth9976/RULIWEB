package com.google.android.material.textfield;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;

public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    class MaterialArrayAdapter extends ArrayAdapter {
        private ColorStateList pressedRippleColor;
        private ColorStateList selectedItemRippleOverlaidColor;

        MaterialArrayAdapter(Context context0, int v, String[] arr_s) {
            super(context0, v, arr_s);
            this.updateSelectedItemColorStateList();
        }

        private ColorStateList createItemSelectedColorStateList() {
            if(this.hasSelectedColor() && this.hasSelectedRippleColor()) {
                int[] arr_v = {0x1010367, 0xFEFEFF59};
                int[] arr_v1 = {0x10100A1, 0xFEFEFF59};
                int v = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(arr_v1, 0);
                int v1 = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(arr_v, 0);
                int[] arr_v2 = {MaterialColors.layer(MaterialAutoCompleteTextView.this.simpleItemSelectedColor, v), MaterialColors.layer(MaterialAutoCompleteTextView.this.simpleItemSelectedColor, v1), MaterialAutoCompleteTextView.this.simpleItemSelectedColor};
                return new ColorStateList(new int[][]{arr_v1, arr_v, new int[0]}, arr_v2);
            }
            return null;
        }

        private Drawable getSelectedItemDrawable() {
            if(this.hasSelectedColor()) {
                Drawable drawable0 = new ColorDrawable(MaterialAutoCompleteTextView.this.simpleItemSelectedColor);
                if(this.pressedRippleColor != null) {
                    DrawableCompat.setTintList(drawable0, this.selectedItemRippleOverlaidColor);
                    return new RippleDrawable(this.pressedRippleColor, drawable0, null);
                }
                return drawable0;
            }
            return null;
        }

        @Override  // android.widget.ArrayAdapter
        public View getView(int v, View view0, ViewGroup viewGroup0) {
            View view1 = super.getView(v, view0, viewGroup0);
            if(view1 instanceof TextView) {
                ViewCompat.setBackground(((TextView)view1), (MaterialAutoCompleteTextView.this.getText().toString().contentEquals(((TextView)view1).getText()) ? this.getSelectedItemDrawable() : null));
            }
            return view1;
        }

        private boolean hasSelectedColor() {
            return MaterialAutoCompleteTextView.this.simpleItemSelectedColor != 0;
        }

        private boolean hasSelectedRippleColor() {
            return MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor != null;
        }

        private ColorStateList sanitizeDropdownItemSelectedRippleColor() {
            if(!this.hasSelectedRippleColor()) {
                return null;
            }
            int[] arr_v = {0x10100A7};
            int[] arr_v1 = {MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(arr_v, 0), 0};
            return new ColorStateList(new int[][]{arr_v, new int[0]}, arr_v1);
        }

        void updateSelectedItemColorStateList() {
            this.pressedRippleColor = this.sanitizeDropdownItemSelectedRippleColor();
            this.selectedItemRippleOverlaidColor = this.createItemSelectedColorStateList();
        }
    }

    private static final int MAX_ITEMS_MEASURED = 15;
    private static final String SWITCH_ACCESS_ACTIVITY_NAME = "SwitchAccess";
    private final AccessibilityManager accessibilityManager;
    private ColorStateList dropDownBackgroundTint;
    private final ListPopupWindow modalListPopup;
    private final float popupElevation;
    private final int simpleItemLayout;
    private int simpleItemSelectedColor;
    private ColorStateList simpleItemSelectedRippleColor;
    private final Rect tempRect;

    public MaterialAutoCompleteTextView(Context context0) {
        this(context0, null);
    }

    public MaterialAutoCompleteTextView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.autoCompleteTextViewStyle);
    }

    public MaterialAutoCompleteTextView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, 0), attributeSet0, v);
        this.tempRect = new Rect();
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.MaterialAutoCompleteTextView, v, style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if(typedArray0.hasValue(styleable.MaterialAutoCompleteTextView_android_inputType) && typedArray0.getInt(styleable.MaterialAutoCompleteTextView_android_inputType, 0) == 0) {
            this.setKeyListener(null);
        }
        this.simpleItemLayout = typedArray0.getResourceId(styleable.MaterialAutoCompleteTextView_simpleItemLayout, layout.mtrl_auto_complete_simple_item);
        this.popupElevation = (float)typedArray0.getDimensionPixelOffset(styleable.MaterialAutoCompleteTextView_android_popupElevation, dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        if(typedArray0.hasValue(styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint)) {
            this.dropDownBackgroundTint = ColorStateList.valueOf(typedArray0.getColor(styleable.MaterialAutoCompleteTextView_dropDownBackgroundTint, 0));
        }
        this.simpleItemSelectedColor = typedArray0.getColor(styleable.MaterialAutoCompleteTextView_simpleItemSelectedColor, 0);
        this.simpleItemSelectedRippleColor = MaterialResources.getColorStateList(context1, typedArray0, styleable.MaterialAutoCompleteTextView_simpleItemSelectedRippleColor);
        this.accessibilityManager = (AccessibilityManager)context1.getSystemService("accessibility");
        ListPopupWindow listPopupWindow0 = new ListPopupWindow(context1);
        this.modalListPopup = listPopupWindow0;
        listPopupWindow0.setModal(true);
        listPopupWindow0.setAnchorView(this);
        listPopupWindow0.setInputMethodMode(2);
        listPopupWindow0.setAdapter(this.getAdapter());
        listPopupWindow0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  // android.widget.AdapterView$OnItemClickListener
            public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
                Object object0 = v >= 0 ? MaterialAutoCompleteTextView.this.getAdapter().getItem(v) : MaterialAutoCompleteTextView.this.modalListPopup.getSelectedItem();
                MaterialAutoCompleteTextView.this.updateText(object0);
                AdapterView.OnItemClickListener adapterView$OnItemClickListener0 = MaterialAutoCompleteTextView.this.getOnItemClickListener();
                if(adapterView$OnItemClickListener0 != null) {
                    if(view0 == null || v < 0) {
                        view0 = MaterialAutoCompleteTextView.this.modalListPopup.getSelectedView();
                        v = MaterialAutoCompleteTextView.this.modalListPopup.getSelectedItemPosition();
                        v1 = MaterialAutoCompleteTextView.this.modalListPopup.getSelectedItemId();
                    }
                    adapterView$OnItemClickListener0.onItemClick(MaterialAutoCompleteTextView.this.modalListPopup.getListView(), view0, v, v1);
                }
                MaterialAutoCompleteTextView.this.modalListPopup.dismiss();
            }
        });
        if(typedArray0.hasValue(styleable.MaterialAutoCompleteTextView_simpleItems)) {
            this.setSimpleItems(typedArray0.getResourceId(styleable.MaterialAutoCompleteTextView_simpleItems, 0));
        }
        typedArray0.recycle();
    }

    @Override  // android.widget.AutoCompleteTextView
    public void dismissDropDown() {
        if(this.isPopupRequired()) {
            this.modalListPopup.dismiss();
            return;
        }
        super.dismissDropDown();
    }

    private TextInputLayout findTextInputLayoutAncestor() {
        for(ViewParent viewParent0 = this.getParent(); viewParent0 != null; viewParent0 = viewParent0.getParent()) {
            if(viewParent0 instanceof TextInputLayout) {
                return (TextInputLayout)viewParent0;
            }
        }
        return null;
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.dropDownBackgroundTint;
    }

    @Override  // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayout0 = this.findTextInputLayoutAncestor();
        return textInputLayout0 == null || !textInputLayout0.isProvidingHint() ? super.getHint() : textInputLayout0.getHint();
    }

    public float getPopupElevation() {
        return this.popupElevation;
    }

    public int getSimpleItemSelectedColor() {
        return this.simpleItemSelectedColor;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.simpleItemSelectedRippleColor;
    }

    // 去混淆评级： 低(20)
    private boolean isPopupRequired() {
        return this.isTouchExplorationEnabled() || this.isSwitchAccessEnabled();
    }

    private boolean isSwitchAccessEnabled() {
        if(this.accessibilityManager != null && this.accessibilityManager.isEnabled()) {
            List list0 = this.accessibilityManager.getEnabledAccessibilityServiceList(16);
            if(list0 != null) {
                for(Object object0: list0) {
                    if(((AccessibilityServiceInfo)object0).getSettingsActivityName() != null && ((AccessibilityServiceInfo)object0).getSettingsActivityName().contains("SwitchAccess")) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    private boolean isTouchExplorationEnabled() {
        return this.accessibilityManager != null && this.accessibilityManager.isTouchExplorationEnabled();
    }

    private int measureContentWidth() {
        ListAdapter listAdapter0 = this.getAdapter();
        TextInputLayout textInputLayout0 = this.findTextInputLayoutAncestor();
        int v = 0;
        if(listAdapter0 != null && textInputLayout0 != null) {
            int v1 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
            int v2 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
            int v3 = Math.max(0, this.modalListPopup.getSelectedItemPosition());
            int v4 = Math.min(listAdapter0.getCount(), v3 + 15);
            int v5 = Math.max(0, v4 - 15);
            View view0 = null;
            int v6 = 0;
            while(v5 < v4) {
                int v7 = listAdapter0.getItemViewType(v5);
                if(v7 != v) {
                    view0 = null;
                    v = v7;
                }
                view0 = listAdapter0.getView(v5, view0, textInputLayout0);
                if(view0.getLayoutParams() == null) {
                    view0.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                }
                view0.measure(v1, v2);
                v6 = Math.max(v6, view0.getMeasuredWidth());
                ++v5;
            }
            Drawable drawable0 = this.modalListPopup.getBackground();
            if(drawable0 != null) {
                drawable0.getPadding(this.tempRect);
                v6 += this.tempRect.left + this.tempRect.right;
            }
            return v6 + textInputLayout0.getEndIconView().getMeasuredWidth();
        }
        return 0;
    }

    @Override  // android.widget.AutoCompleteTextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayout0 = this.findTextInputLayoutAncestor();
        if(textInputLayout0 != null && textInputLayout0.isProvidingHint() && super.getHint() == null && ManufacturerUtils.isMeizuDevice()) {
            this.setHint("");
        }
    }

    @Override  // android.widget.AutoCompleteTextView
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.modalListPopup.dismiss();
    }

    private void onInputTypeChanged() {
        TextInputLayout textInputLayout0 = this.findTextInputLayoutAncestor();
        if(textInputLayout0 != null) {
            textInputLayout0.updateEditTextBoxBackgroundIfNeeded();
        }
    }

    @Override  // android.widget.TextView
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(View.MeasureSpec.getMode(v) == 0x80000000) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.measureContentWidth()), View.MeasureSpec.getSize(v)), this.getMeasuredHeight());
        }
    }

    @Override  // android.widget.AutoCompleteTextView
    public void onWindowFocusChanged(boolean z) {
        if(this.isPopupRequired()) {
            return;
        }
        super.onWindowFocusChanged(z);
    }

    @Override  // android.widget.AutoCompleteTextView
    public void setAdapter(ListAdapter listAdapter0) {
        super.setAdapter(listAdapter0);
        ListAdapter listAdapter1 = this.getAdapter();
        this.modalListPopup.setAdapter(listAdapter1);
    }

    @Override  // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable drawable0) {
        super.setDropDownBackgroundDrawable(drawable0);
        ListPopupWindow listPopupWindow0 = this.modalListPopup;
        if(listPopupWindow0 != null) {
            listPopupWindow0.setBackgroundDrawable(drawable0);
        }
    }

    public void setDropDownBackgroundTint(int v) {
        this.setDropDownBackgroundTintList(ColorStateList.valueOf(v));
    }

    public void setDropDownBackgroundTintList(ColorStateList colorStateList0) {
        this.dropDownBackgroundTint = colorStateList0;
        Drawable drawable0 = this.getDropDownBackground();
        if(drawable0 instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable)drawable0).setFillColor(this.dropDownBackgroundTint);
        }
    }

    @Override  // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
        super.setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
        AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener1 = this.getOnItemSelectedListener();
        this.modalListPopup.setOnItemSelectedListener(adapterView$OnItemSelectedListener1);
    }

    @Override  // android.widget.TextView
    public void setRawInputType(int v) {
        super.setRawInputType(v);
        this.onInputTypeChanged();
    }

    public void setSimpleItemSelectedColor(int v) {
        this.simpleItemSelectedColor = v;
        if(this.getAdapter() instanceof MaterialArrayAdapter) {
            ((MaterialArrayAdapter)this.getAdapter()).updateSelectedItemColorStateList();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList0) {
        this.simpleItemSelectedRippleColor = colorStateList0;
        if(this.getAdapter() instanceof MaterialArrayAdapter) {
            ((MaterialArrayAdapter)this.getAdapter()).updateSelectedItemColorStateList();
        }
    }

    public void setSimpleItems(int v) {
        this.setSimpleItems(this.getResources().getStringArray(v));
    }

    public void setSimpleItems(String[] arr_s) {
        this.setAdapter(new MaterialArrayAdapter(this, this.getContext(), this.simpleItemLayout, arr_s));
    }

    @Override  // android.widget.AutoCompleteTextView
    public void showDropDown() {
        if(this.isPopupRequired()) {
            this.modalListPopup.show();
            return;
        }
        super.showDropDown();
    }

    private void updateText(Object object0) {
        this.setText(this.convertSelectionToString(object0), false);
    }
}

