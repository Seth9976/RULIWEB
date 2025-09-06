package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import androidx.appcompat.R.styleable;
import androidx.appcompat.widget.ActionMenuView.ActionMenuChildView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;
import androidx.appcompat.widget.TooltipCompat;

public class ActionMenuItemView extends AppCompatTextView implements View.OnClickListener, ItemView, ActionMenuChildView {
    class ActionMenuItemForwardingListener extends ForwardingListener {
        @Override  // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu getPopup() {
            return ActionMenuItemView.this.mPopupCallback == null ? null : ActionMenuItemView.this.mPopupCallback.getPopup();
        }

        @Override  // androidx.appcompat.widget.ForwardingListener
        protected boolean onForwardingStarted() {
            if(ActionMenuItemView.this.mItemInvoker != null && ActionMenuItemView.this.mItemInvoker.invokeItem(ActionMenuItemView.this.mItemData)) {
                ShowableListMenu showableListMenu0 = this.getPopup();
                return showableListMenu0 != null && showableListMenu0.isShowing();
            }
            return false;
        }
    }

    public static abstract class PopupCallback {
        public abstract ShowableListMenu getPopup();
    }

    private static final int MAX_ICON_SIZE = 0x20;
    private static final String TAG = "ActionMenuItemView";
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    public ActionMenuItemView(Context context0) {
        this(context0, null);
    }

    public ActionMenuItemView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ActionMenuItemView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        Resources resources0 = context0.getResources();
        this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ActionMenuItemView, v, 0);
        this.mMinWidth = typedArray0.getDimensionPixelSize(styleable.ActionMenuItemView_android_minWidth, 0);
        typedArray0.recycle();
        this.mMaxIconSize = (int)(resources0.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener(this);
        this.mSavedPaddingLeft = -1;
        this.setSaveEnabled(false);
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.TextView
    public CharSequence getAccessibilityClassName() {
        return "android.widget.Button";
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(this.getText());
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void initialize(MenuItemImpl menuItemImpl0, int v) {
        this.mItemData = menuItemImpl0;
        this.setIcon(menuItemImpl0.getIcon());
        this.setTitle(menuItemImpl0.getTitleForItemView(this));
        this.setId(menuItemImpl0.getItemId());
        this.setVisibility((menuItemImpl0.isVisible() ? 0 : 8));
        this.setEnabled(menuItemImpl0.isEnabled());
        if(menuItemImpl0.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new ActionMenuItemForwardingListener(this);
        }
    }

    @Override  // androidx.appcompat.widget.ActionMenuView$ActionMenuChildView
    public boolean needsDividerAfter() {
        return this.hasText();
    }

    @Override  // androidx.appcompat.widget.ActionMenuView$ActionMenuChildView
    public boolean needsDividerBefore() {
        return this.hasText() && this.mItemData.getIcon() == null;
    }

    @Override  // android.view.View$OnClickListener
    public void onClick(View view0) {
        ItemInvoker menuBuilder$ItemInvoker0 = this.mItemInvoker;
        if(menuBuilder$ItemInvoker0 != null) {
            menuBuilder$ItemInvoker0.invokeItem(this.mItemData);
        }
    }

    @Override  // android.widget.TextView
    public void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
        this.updateTextButtonVisibility();
    }

    @Override  // androidx.appcompat.widget.AppCompatTextView
    protected void onMeasure(int v, int v1) {
        boolean z = this.hasText();
        if(z) {
            int v2 = this.mSavedPaddingLeft;
            if(v2 >= 0) {
                super.setPadding(v2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
            }
        }
        super.onMeasure(v, v1);
        int v3 = View.MeasureSpec.getMode(v);
        int v4 = View.MeasureSpec.getSize(v);
        int v5 = this.getMeasuredWidth();
        int v6 = v3 == 0x80000000 ? Math.min(v4, this.mMinWidth) : this.mMinWidth;
        if(v3 != 0x40000000 && this.mMinWidth > 0 && v5 < v6) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(v6, 0x40000000), v1);
        }
        if(!z && this.mIcon != null) {
            super.setPadding((this.getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }

    @Override  // android.widget.TextView
    public void onRestoreInstanceState(Parcelable parcelable0) {
        super.onRestoreInstanceState(null);
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.TextView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return !this.mItemData.hasSubMenu() || (this.mForwardingListener == null || !this.mForwardingListener.onTouch(this, motionEvent0)) ? super.onTouchEvent(motionEvent0) : true;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean prefersCondensedTitle() {
        return true;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setCheckable(boolean z) {
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if(this.mExpandedFormat != z) {
            this.mExpandedFormat = z;
            MenuItemImpl menuItemImpl0 = this.mItemData;
            if(menuItemImpl0 != null) {
                menuItemImpl0.actionFormatChanged();
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setIcon(Drawable drawable0) {
        this.mIcon = drawable0;
        if(drawable0 != null) {
            int v = drawable0.getIntrinsicWidth();
            int v1 = drawable0.getIntrinsicHeight();
            int v2 = this.mMaxIconSize;
            if(v > v2) {
                v1 = (int)(((float)v1) * (((float)v2) / ((float)v)));
                v = v2;
            }
            if(v1 > v2) {
                v = (int)(((float)v) * (((float)v2) / ((float)v1)));
            }
            else {
                v2 = v1;
            }
            drawable0.setBounds(0, 0, v, v2);
        }
        this.setCompoundDrawables(drawable0, null, null, null);
        this.updateTextButtonVisibility();
    }

    public void setItemInvoker(ItemInvoker menuBuilder$ItemInvoker0) {
        this.mItemInvoker = menuBuilder$ItemInvoker0;
    }

    @Override  // android.widget.TextView
    public void setPadding(int v, int v1, int v2, int v3) {
        this.mSavedPaddingLeft = v;
        super.setPadding(v, v1, v2, v3);
    }

    public void setPopupCallback(PopupCallback actionMenuItemView$PopupCallback0) {
        this.mPopupCallback = actionMenuItemView$PopupCallback0;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setShortcut(boolean z, char c) {
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setTitle(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        this.updateTextButtonVisibility();
    }

    private boolean shouldAllowTextWithIcon() {
        Configuration configuration0 = this.getContext().getResources().getConfiguration();
        return configuration0.screenWidthDp >= 480 || configuration0.screenWidthDp >= 640 && configuration0.screenHeightDp >= 480 || configuration0.orientation == 2;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean showsIcon() {
        return true;
    }

    private void updateTextButtonVisibility() {
        int v = !TextUtils.isEmpty(this.mTitle) & (this.mIcon == null || this.mItemData.showsTextAsAction() && (this.mAllowTextWithIcon || this.mExpandedFormat) ? 1 : 0);
        CharSequence charSequence0 = null;
        this.setText((v == 0 ? null : this.mTitle));
        CharSequence charSequence1 = this.mItemData.getContentDescription();
        if(TextUtils.isEmpty(charSequence1)) {
            this.setContentDescription((v == 0 ? this.mItemData.getTitle() : null));
        }
        else {
            this.setContentDescription(charSequence1);
        }
        CharSequence charSequence2 = this.mItemData.getTooltipText();
        if(TextUtils.isEmpty(charSequence2)) {
            if(v == 0) {
                charSequence0 = this.mItemData.getTitle();
            }
            TooltipCompat.setTooltipText(this, charSequence0);
            return;
        }
        TooltipCompat.setTooltipText(this, charSequence2);
    }
}

