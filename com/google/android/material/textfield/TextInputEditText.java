package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class TextInputEditText extends AppCompatEditText {
    private final Rect parentRect;
    private boolean textInputLayoutFocusedRectEnabled;

    public TextInputEditText(Context context0) {
        this(context0, null);
    }

    public TextInputEditText(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.editTextStyle);
    }

    public TextInputEditText(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, 0), attributeSet0, v);
        this.parentRect = new Rect();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.TextInputEditText, v, style.Widget_Design_TextInputEditText, new int[0]);
        this.setTextInputLayoutFocusedRectEnabled(typedArray0.getBoolean(styleable.TextInputEditText_textInputLayoutFocusedRectEnabled, false));
        typedArray0.recycle();
    }

    private String getAccessibilityNodeInfoText(TextInputLayout textInputLayout0) {
        Editable editable0 = this.getText();
        CharSequence charSequence0 = textInputLayout0.getHint();
        boolean z = TextUtils.isEmpty(editable0);
        String s = "";
        String s1 = TextUtils.isEmpty(charSequence0) ? "" : charSequence0.toString();
        if(!z) {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(editable0);
            if(!TextUtils.isEmpty(s1)) {
                s = ", " + s1;
            }
            stringBuilder0.append(s);
            return stringBuilder0.toString();
        }
        return TextUtils.isEmpty(s1) ? "" : s1;
    }

    @Override  // android.widget.TextView
    public void getFocusedRect(Rect rect0) {
        super.getFocusedRect(rect0);
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        if(this.shouldUseTextInputLayoutFocusedRect(textInputLayout0) && rect0 != null) {
            textInputLayout0.getFocusedRect(this.parentRect);
            rect0.bottom = this.parentRect.bottom;
        }
    }

    @Override  // android.view.View
    public boolean getGlobalVisibleRect(Rect rect0, Point point0) {
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        if(this.shouldUseTextInputLayoutFocusedRect(textInputLayout0)) {
            boolean z = textInputLayout0.getGlobalVisibleRect(rect0, point0);
            if(z && point0 != null) {
                point0.offset(-this.getScrollX(), -this.getScrollY());
            }
            return z;
        }
        return super.getGlobalVisibleRect(rect0, point0);
    }

    @Override  // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        return textInputLayout0 == null || !textInputLayout0.isProvidingHint() ? super.getHint() : textInputLayout0.getHint();
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        return textInputLayout0 == null ? null : textInputLayout0.getHint();
    }

    private TextInputLayout getTextInputLayout() {
        for(ViewParent viewParent0 = this.getParent(); viewParent0 instanceof View; viewParent0 = viewParent0.getParent()) {
            if(viewParent0 instanceof TextInputLayout) {
                return (TextInputLayout)viewParent0;
            }
        }
        return null;
    }

    public boolean isTextInputLayoutFocusedRectEnabled() {
        return this.textInputLayoutFocusedRectEnabled;
    }

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        if(textInputLayout0 != null && textInputLayout0.isProvidingHint() && super.getHint() == null && ManufacturerUtils.isMeizuDevice()) {
            this.setHint("");
        }
    }

    @Override  // androidx.appcompat.widget.AppCompatEditText
    public InputConnection onCreateInputConnection(EditorInfo editorInfo0) {
        InputConnection inputConnection0 = super.onCreateInputConnection(editorInfo0);
        if(inputConnection0 != null && editorInfo0.hintText == null) {
            editorInfo0.hintText = this.getHintFromLayout();
        }
        return inputConnection0;
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        if(Build.VERSION.SDK_INT < 23 && textInputLayout0 != null) {
            accessibilityNodeInfo0.setText(this.getAccessibilityNodeInfoText(textInputLayout0));
        }
    }

    @Override  // android.view.View
    public boolean requestRectangleOnScreen(Rect rect0) {
        TextInputLayout textInputLayout0 = this.getTextInputLayout();
        if(this.shouldUseTextInputLayoutFocusedRect(textInputLayout0) && rect0 != null) {
            int v = textInputLayout0.getHeight();
            int v1 = this.getHeight();
            this.parentRect.set(rect0.left, rect0.top, rect0.right, rect0.bottom + (v - v1));
            return super.requestRectangleOnScreen(this.parentRect);
        }
        return super.requestRectangleOnScreen(rect0);
    }

    public void setTextInputLayoutFocusedRectEnabled(boolean z) {
        this.textInputLayoutFocusedRectEnabled = z;
    }

    private boolean shouldUseTextInputLayoutFocusedRect(TextInputLayout textInputLayout0) {
        return textInputLayout0 != null && this.textInputLayoutFocusedRectEnabled;
    }
}

