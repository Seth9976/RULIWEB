package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView.SelectionBoundsAdjuster;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.styleable;
import androidx.appcompat.widget.TintTypedArray;

public class ListMenuItemView extends LinearLayout implements AbsListView.SelectionBoundsAdjuster, ItemView {
    private static final String TAG = "ListMenuItemView";
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    private boolean mForceShowIcon;
    private ImageView mGroupDivider;
    private boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet0, styleable.MenuView, v, 0);
        this.mBackground = tintTypedArray0.getDrawable(styleable.MenuView_android_itemBackground);
        this.mTextAppearance = tintTypedArray0.getResourceId(styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = tintTypedArray0.getBoolean(styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = context0;
        this.mSubMenuArrow = tintTypedArray0.getDrawable(styleable.MenuView_subMenuArrow);
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(null, new int[]{0x1010129}, attr.dropDownListViewStyle, 0);
        this.mHasListDivider = typedArray0.hasValue(0);
        tintTypedArray0.recycle();
        typedArray0.recycle();
    }

    private void addContentView(View view0) {
        this.addContentView(view0, -1);
    }

    private void addContentView(View view0, int v) {
        LinearLayout linearLayout0 = this.mContent;
        if(linearLayout0 != null) {
            linearLayout0.addView(view0, v);
            return;
        }
        this.addView(view0, v);
    }

    @Override  // android.widget.AbsListView$SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect0) {
        if(this.mGroupDivider != null && this.mGroupDivider.getVisibility() == 0) {
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)this.mGroupDivider.getLayoutParams();
            rect0.top += this.mGroupDivider.getHeight() + linearLayout$LayoutParams0.topMargin + linearLayout$LayoutParams0.bottomMargin;
        }
    }

    private LayoutInflater getInflater() {
        if(this.mInflater == null) {
            this.mInflater = LayoutInflater.from(this.getContext());
        }
        return this.mInflater;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void initialize(MenuItemImpl menuItemImpl0, int v) {
        this.mItemData = menuItemImpl0;
        this.setVisibility((menuItemImpl0.isVisible() ? 0 : 8));
        this.setTitle(menuItemImpl0.getTitleForItemView(this));
        this.setCheckable(menuItemImpl0.isCheckable());
        this.setShortcut(menuItemImpl0.shouldShowShortcut(), menuItemImpl0.getShortcut());
        this.setIcon(menuItemImpl0.getIcon());
        this.setEnabled(menuItemImpl0.isEnabled());
        this.setSubMenuArrowVisible(menuItemImpl0.hasSubMenu());
        this.setContentDescription(menuItemImpl0.getContentDescription());
    }

    private void insertCheckBox() {
        CheckBox checkBox0 = (CheckBox)this.getInflater().inflate(layout.abc_list_menu_item_checkbox, this, false);
        this.mCheckBox = checkBox0;
        this.addContentView(checkBox0);
    }

    private void insertIconView() {
        ImageView imageView0 = (ImageView)this.getInflater().inflate(layout.abc_list_menu_item_icon, this, false);
        this.mIconView = imageView0;
        this.addContentView(imageView0, 0);
    }

    private void insertRadioButton() {
        RadioButton radioButton0 = (RadioButton)this.getInflater().inflate(layout.abc_list_menu_item_radio, this, false);
        this.mRadioButton = radioButton0;
        this.addContentView(radioButton0);
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setBackground(this.mBackground);
        TextView textView0 = (TextView)this.findViewById(id.title);
        this.mTitleView = textView0;
        int v = this.mTextAppearance;
        if(v != -1) {
            textView0.setTextAppearance(this.mTextAppearanceContext, v);
        }
        this.mShortcutView = (TextView)this.findViewById(id.shortcut);
        ImageView imageView0 = (ImageView)this.findViewById(id.submenuarrow);
        this.mSubMenuArrowView = imageView0;
        if(imageView0 != null) {
            imageView0.setImageDrawable(this.mSubMenuArrow);
        }
        this.mGroupDivider = (ImageView)this.findViewById(id.group_divider);
        this.mContent = (LinearLayout)this.findViewById(id.content);
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        if(this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)this.mIconView.getLayoutParams();
            if(viewGroup$LayoutParams0.height > 0 && linearLayout$LayoutParams0.width <= 0) {
                linearLayout$LayoutParams0.width = viewGroup$LayoutParams0.height;
            }
        }
        super.onMeasure(v, v1);
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setCheckable(boolean z) {
        CheckBox checkBox0;
        RadioButton radioButton0;
        if(z || this.mRadioButton != null || this.mCheckBox != null) {
            if(this.mItemData.isExclusiveCheckable()) {
                if(this.mRadioButton == null) {
                    this.insertRadioButton();
                }
                radioButton0 = this.mRadioButton;
                checkBox0 = this.mCheckBox;
            }
            else {
                if(this.mCheckBox == null) {
                    this.insertCheckBox();
                }
                radioButton0 = this.mCheckBox;
                checkBox0 = this.mRadioButton;
            }
            if(z) {
                radioButton0.setChecked(this.mItemData.isChecked());
                if(radioButton0.getVisibility() != 0) {
                    radioButton0.setVisibility(0);
                }
                if(checkBox0 != null && checkBox0.getVisibility() != 8) {
                    checkBox0.setVisibility(8);
                }
            }
            else {
                CheckBox checkBox1 = this.mCheckBox;
                if(checkBox1 != null) {
                    checkBox1.setVisibility(8);
                }
                RadioButton radioButton1 = this.mRadioButton;
                if(radioButton1 != null) {
                    radioButton1.setVisibility(8);
                }
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setChecked(boolean z) {
        RadioButton radioButton0;
        if(this.mItemData.isExclusiveCheckable()) {
            if(this.mRadioButton == null) {
                this.insertRadioButton();
            }
            radioButton0 = this.mRadioButton;
        }
        else {
            if(this.mCheckBox == null) {
                this.insertCheckBox();
            }
            radioButton0 = this.mCheckBox;
        }
        radioButton0.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        this.mPreserveIconSpacing = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView0 = this.mGroupDivider;
        if(imageView0 != null) {
            imageView0.setVisibility((this.mHasListDivider || !z ? 8 : 0));
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setIcon(Drawable drawable0) {
        boolean z = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
        if(z || this.mPreserveIconSpacing) {
            ImageView imageView0 = this.mIconView;
            if(imageView0 != null || drawable0 != null || this.mPreserveIconSpacing) {
                if(imageView0 == null) {
                    this.insertIconView();
                }
                if(drawable0 == null && !this.mPreserveIconSpacing) {
                    this.mIconView.setVisibility(8);
                    return;
                }
                ImageView imageView1 = this.mIconView;
                if(!z) {
                    drawable0 = null;
                }
                imageView1.setImageDrawable(drawable0);
                if(this.mIconView.getVisibility() != 0) {
                    this.mIconView.setVisibility(0);
                }
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setShortcut(boolean z, char c) {
        int v = !z || !this.mItemData.shouldShowShortcut() ? 8 : 0;
        if(v == 0) {
            this.mShortcutView.setText(this.mItemData.getShortcutLabel());
        }
        if(this.mShortcutView.getVisibility() != v) {
            this.mShortcutView.setVisibility(v);
        }
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView0 = this.mSubMenuArrowView;
        if(imageView0 != null) {
            imageView0.setVisibility((z ? 0 : 8));
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setTitle(CharSequence charSequence0) {
        if(charSequence0 != null) {
            this.mTitleView.setText(charSequence0);
            if(this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        }
        else if(this.mTitleView.getVisibility() != 8) {
            this.mTitleView.setVisibility(8);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean showsIcon() {
        return this.mForceShowIcon;
    }
}

