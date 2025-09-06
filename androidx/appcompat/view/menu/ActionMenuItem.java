package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;

public class ActionMenuItem implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private Context mContext;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private ColorStateList mIconTintList;
    private PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public ActionMenuItem(Context context0, int v, int v1, int v2, int v3, CharSequence charSequence0) {
        this.mShortcutNumericModifiers = 0x1000;
        this.mShortcutAlphabeticModifiers = 0x1000;
        this.mIconTintList = null;
        this.mIconTintMode = null;
        this.mHasIconTint = false;
        this.mHasIconTintMode = false;
        this.mFlags = 16;
        this.mContext = context0;
        this.mId = v1;
        this.mGroup = v;
        this.mOrdering = v3;
        this.mTitle = charSequence0;
    }

    private void applyIconTint() {
        Drawable drawable0 = this.mIconDrawable;
        if(drawable0 != null && (this.mHasIconTint || this.mHasIconTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0);
            this.mIconDrawable = drawable1;
            Drawable drawable2 = drawable1.mutate();
            this.mIconDrawable = drawable2;
            if(this.mHasIconTint) {
                DrawableCompat.setTintList(drawable2, this.mIconTintList);
            }
            if(this.mHasIconTintMode) {
                DrawableCompat.setTintMode(this.mIconDrawable, this.mIconTintMode);
            }
        }
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override  // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public View getActionView() {
        return null;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    @Override  // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override  // android.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override  // android.view.MenuItem
    public Drawable getIcon() {
        return this.mIconDrawable;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    @Override  // android.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override  // android.view.MenuItem
    public int getItemId() {
        return this.mId;
    }

    @Override  // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    @Override  // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override  // android.view.MenuItem
    public int getOrder() {
        return this.mOrdering;
    }

    @Override  // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public androidx.core.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    @Override  // android.view.MenuItem
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override  // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mTitleCondensed == null ? this.mTitle : this.mTitleCondensed;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    @Override  // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    public boolean invoke() {
        if(this.mClickListener != null && this.mClickListener.onMenuItemClick(this)) {
            return true;
        }
        Intent intent0 = this.mIntent;
        if(intent0 != null) {
            this.mContext.startActivity(intent0);
            return true;
        }
        return false;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override  // android.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    @Override  // android.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    @Override  // android.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    @Override  // android.view.MenuItem
    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public boolean requiresActionButton() {
        return true;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public boolean requiresOverflow() {
        return false;
    }

    @Override  // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider0) {
        throw new UnsupportedOperationException();
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setActionView(int v) {
        return this.setActionView(v);
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setActionView(View view0) {
        return this.setActionView(view0);
    }

    public SupportMenuItem setActionView(int v) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem setActionView(View view0) {
        throw new UnsupportedOperationException();
    }

    @Override  // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setAlphabeticShortcut(char c, int v) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.mFlags = z | this.mFlags & -2;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.mFlags = (z ? 2 : 0) | this.mFlags & -3;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence0) {
        return this.setContentDescription(charSequence0);
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setContentDescription(CharSequence charSequence0) {
        this.mContentDescription = charSequence0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.mFlags = (z ? 16 : 0) | this.mFlags & -17;
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean z) {
        this.mFlags = (z ? 4 : 0) | this.mFlags & -5;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIcon(int v) {
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, v);
        this.applyIconTint();
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable0) {
        this.mIconDrawable = drawable0;
        this.applyIconTint();
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList0) {
        this.mIconTintList = colorStateList0;
        this.mHasIconTint = true;
        this.applyIconTint();
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mIconTintMode = porterDuff$Mode0;
        this.mHasIconTintMode = true;
        this.applyIconTint();
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIntent(Intent intent0) {
        this.mIntent = intent0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        this.mShortcutNumericChar = c;
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setNumericShortcut(char c, int v) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener menuItem$OnActionExpandListener0) {
        throw new UnsupportedOperationException();
    }

    @Override  // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener menuItem$OnMenuItemClickListener0) {
        this.mClickListener = menuItem$OnMenuItemClickListener0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setShortcut(char c, char c1) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c1);
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setShortcut(char c, char c1, int v, int v1) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(v);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c1);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(v1);
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public void setShowAsAction(int v) {
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public MenuItem setShowAsActionFlags(int v) {
        return this.setShowAsActionFlags(v);
    }

    public SupportMenuItem setShowAsActionFlags(int v) {
        return this;
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setSupportActionProvider(androidx.core.view.ActionProvider actionProvider0) {
        throw new UnsupportedOperationException();
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitle(int v) {
        this.mTitle = this.mContext.getResources().getString(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence0) {
        this.mTitleCondensed = charSequence0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence0) {
        return this.setTooltipText(charSequence0);
    }

    @Override  // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setTooltipText(CharSequence charSequence0) {
        this.mTooltipText = charSequence0;
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        int v = 8;
        int v1 = this.mFlags & 8;
        if(z) {
            v = 0;
        }
        this.mFlags = v1 | v;
        return this;
    }
}

