package androidx.appcompat.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SupportMenuInflater extends MenuInflater {
    static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class[] PARAM_TYPES;
        private Method mMethod;
        private Object mRealOwner;

        static {
            InflatedOnMenuItemClickListener.PARAM_TYPES = new Class[]{MenuItem.class};
        }

        public InflatedOnMenuItemClickListener(Object object0, String s) {
            this.mRealOwner = object0;
            Class class0 = object0.getClass();
            try {
                this.mMethod = class0.getMethod(s, InflatedOnMenuItemClickListener.PARAM_TYPES);
            }
            catch(Exception exception0) {
                InflateException inflateException0 = new InflateException("Couldn\'t resolve menu item onClick handler " + s + " in class " + class0.getName());
                inflateException0.initCause(exception0);
                throw inflateException0;
            }
        }

        @Override  // android.view.MenuItem$OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem0) {
            try {
                if(this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean)this.mMethod.invoke(this.mRealOwner, menuItem0)).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, menuItem0);
                return true;
            }
            catch(Exception exception0) {
                throw new RuntimeException(exception0);
            }
        }
    }

    class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList;
        private PorterDuff.Mode itemIconTintMode;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(Menu menu0) {
            this.itemIconTintList = null;
            this.itemIconTintMode = null;
            this.menu = menu0;
            this.resetGroup();
        }

        public void addItem() {
            this.itemAdded = true;
            this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu subMenu0 = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            this.setItem(subMenu0.getItem());
            return subMenu0;
        }

        private char getShortcut(String s) {
            return s == null ? '\u0000' : s.charAt(0);
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        private Object newInstance(String s, Class[] arr_class, Object[] arr_object) {
            try {
                Constructor constructor0 = Class.forName(s, false, SupportMenuInflater.this.mContext.getClassLoader()).getConstructor(arr_class);
                constructor0.setAccessible(true);
                return constructor0.newInstance(arr_object);
            }
            catch(Exception exception0) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + s, exception0);
                return null;
            }
        }

        public void readGroup(AttributeSet attributeSet0) {
            TypedArray typedArray0 = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet0, styleable.MenuGroup);
            this.groupId = typedArray0.getResourceId(styleable.MenuGroup_android_id, 0);
            this.groupCategory = typedArray0.getInt(styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = typedArray0.getInt(styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = typedArray0.getInt(styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = typedArray0.getBoolean(styleable.MenuGroup_android_visible, true);
            this.groupEnabled = typedArray0.getBoolean(styleable.MenuGroup_android_enabled, true);
            typedArray0.recycle();
        }

        public void readItem(AttributeSet attributeSet0) {
            TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(SupportMenuInflater.this.mContext, attributeSet0, styleable.MenuItem);
            this.itemId = tintTypedArray0.getResourceId(styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = tintTypedArray0.getInt(styleable.MenuItem_android_menuCategory, this.groupCategory) & 0xFFFF0000 | tintTypedArray0.getInt(styleable.MenuItem_android_orderInCategory, this.groupOrder) & 0xFFFF;
            this.itemTitle = tintTypedArray0.getText(styleable.MenuItem_android_title);
            this.itemTitleCondensed = tintTypedArray0.getText(styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = tintTypedArray0.getResourceId(styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = this.getShortcut(tintTypedArray0.getString(styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = tintTypedArray0.getInt(styleable.MenuItem_alphabeticModifiers, 0x1000);
            this.itemNumericShortcut = this.getShortcut(tintTypedArray0.getString(styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = tintTypedArray0.getInt(styleable.MenuItem_numericModifiers, 0x1000);
            this.itemCheckable = tintTypedArray0.hasValue(styleable.MenuItem_android_checkable) ? tintTypedArray0.getBoolean(styleable.MenuItem_android_checkable, false) : this.groupCheckable;
            this.itemChecked = tintTypedArray0.getBoolean(styleable.MenuItem_android_checked, false);
            this.itemVisible = tintTypedArray0.getBoolean(styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = tintTypedArray0.getBoolean(styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = tintTypedArray0.getInt(styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = tintTypedArray0.getString(styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = tintTypedArray0.getResourceId(styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = tintTypedArray0.getString(styleable.MenuItem_actionViewClass);
            String s = tintTypedArray0.getString(styleable.MenuItem_actionProviderClass);
            this.itemActionProviderClassName = s;
            if(s == null || this.itemActionViewLayout != 0 || this.itemActionViewClassName != null) {
                if(s != null) {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'actionProviderClass\'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            else {
                this.itemActionProvider = (ActionProvider)this.newInstance(s, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            }
            this.itemContentDescription = tintTypedArray0.getText(styleable.MenuItem_contentDescription);
            this.itemTooltipText = tintTypedArray0.getText(styleable.MenuItem_tooltipText);
            this.itemIconTintMode = tintTypedArray0.hasValue(styleable.MenuItem_iconTintMode) ? DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode) : null;
            this.itemIconTintList = tintTypedArray0.hasValue(styleable.MenuItem_iconTint) ? tintTypedArray0.getColorStateList(styleable.MenuItem_iconTint) : null;
            tintTypedArray0.recycle();
            this.itemAdded = false;
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        private void setItem(MenuItem menuItem0) {
            boolean z = false;
            menuItem0.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            int v = this.itemShowAsAction;
            if(v >= 0) {
                menuItem0.setShowAsAction(v);
            }
            if(this.itemListenerMethodName != null) {
                if(SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem0.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
            }
            if(this.itemCheckable >= 2) {
                if(menuItem0 instanceof MenuItemImpl) {
                    ((MenuItemImpl)menuItem0).setExclusiveCheckable(true);
                }
                else if(menuItem0 instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS)menuItem0).setExclusiveCheckable(true);
                }
            }
            String s = this.itemActionViewClassName;
            if(s != null) {
                menuItem0.setActionView(((View)this.newInstance(s, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments)));
                z = true;
            }
            int v1 = this.itemActionViewLayout;
            if(v1 > 0) {
                if(z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'itemActionViewLayout\'. Action view already specified.");
                }
                else {
                    menuItem0.setActionView(v1);
                }
            }
            ActionProvider actionProvider0 = this.itemActionProvider;
            if(actionProvider0 != null) {
                MenuItemCompat.setActionProvider(menuItem0, actionProvider0);
            }
            MenuItemCompat.setContentDescription(menuItem0, this.itemContentDescription);
            MenuItemCompat.setTooltipText(menuItem0, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(menuItem0, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(menuItem0, this.itemNumericShortcut, this.itemNumericModifiers);
            PorterDuff.Mode porterDuff$Mode0 = this.itemIconTintMode;
            if(porterDuff$Mode0 != null) {
                MenuItemCompat.setIconTintMode(menuItem0, porterDuff$Mode0);
            }
            ColorStateList colorStateList0 = this.itemIconTintList;
            if(colorStateList0 != null) {
                MenuItemCompat.setIconTintList(menuItem0, colorStateList0);
            }
        }
    }

    static final Class[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = null;
    static final Class[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = null;
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object[] mActionProviderConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    static {
        Class[] arr_class = {Context.class};
        SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE = arr_class;
        SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = arr_class;
    }

    public SupportMenuInflater(Context context0) {
        super(context0);
        this.mContext = context0;
        Object[] arr_object = {context0};
        this.mActionViewConstructorArguments = arr_object;
        this.mActionProviderConstructorArguments = arr_object;
    }

    // 去混淆评级： 低(20)
    private Object findRealOwner(Object object0) {
        return object0 instanceof Activity || !(object0 instanceof ContextWrapper) ? object0 : this.findRealOwner(((ContextWrapper)object0).getBaseContext());
    }

    Object getRealOwner() {
        if(this.mRealOwner == null) {
            this.mRealOwner = this.findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }

    @Override  // android.view.MenuInflater
    public void inflate(int v, Menu menu0) {
        XmlResourceParser xmlResourceParser0;
        if(!(menu0 instanceof SupportMenu)) {
            super.inflate(v, menu0);
            return;
        }
        try {
            try {
                xmlResourceParser0 = null;
                boolean z = false;
                xmlResourceParser0 = this.mContext.getResources().getLayout(v);
                AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
                if(menu0 instanceof MenuBuilder && ((MenuBuilder)menu0).isDispatchingItemsChanged()) {
                    ((MenuBuilder)menu0).stopDispatchingItemsChanged();
                    z = true;
                }
                this.parseMenu(xmlResourceParser0, attributeSet0, menu0);
            }
            catch(XmlPullParserException xmlPullParserException0) {
                throw new InflateException("Error inflating menu XML", xmlPullParserException0);
            }
            catch(IOException iOException0) {
                throw new InflateException("Error inflating menu XML", iOException0);
            }
        }
        catch(Throwable throwable0) {
            if(z) {
                ((MenuBuilder)menu0).startDispatchingItemsChanged();
            }
            if(xmlResourceParser0 != null) {
                xmlResourceParser0.close();
            }
            throw throwable0;
        }
        if(z) {
            ((MenuBuilder)menu0).startDispatchingItemsChanged();
        }
        if(xmlResourceParser0 != null) {
            xmlResourceParser0.close();
        }
    }

    private void parseMenu(XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Menu menu0) throws XmlPullParserException, IOException {
        MenuState supportMenuInflater$MenuState0 = new MenuState(this, menu0);
        int v = xmlPullParser0.getEventType();
        do {
            if(v == 2) {
                String s = xmlPullParser0.getName();
                if(!s.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got " + s);
                }
                v = xmlPullParser0.next();
                break;
            }
            v = xmlPullParser0.next();
        }
        while(v != 1);
        String s1 = null;
        boolean z = false;
        boolean z1 = false;
        while(!z1) {
            switch(v) {
                case 1: {
                    throw new RuntimeException("Unexpected end of document");
                }
                case 2: {
                    if(!z) {
                        String s2 = xmlPullParser0.getName();
                        if(s2.equals("group")) {
                            supportMenuInflater$MenuState0.readGroup(attributeSet0);
                        }
                        else if(s2.equals("item")) {
                            supportMenuInflater$MenuState0.readItem(attributeSet0);
                        }
                        else if(s2.equals("menu")) {
                            this.parseMenu(xmlPullParser0, attributeSet0, supportMenuInflater$MenuState0.addSubMenuItem());
                        }
                        else {
                            s1 = s2;
                            z = true;
                        }
                    }
                    break;
                }
                case 3: {
                    String s3 = xmlPullParser0.getName();
                    if(z && s3.equals(s1)) {
                        s1 = null;
                        z = false;
                    }
                    else if(s3.equals("group")) {
                        supportMenuInflater$MenuState0.resetGroup();
                    }
                    else if(!s3.equals("item")) {
                        if(s3.equals("menu")) {
                            z1 = true;
                        }
                    }
                    else if(!supportMenuInflater$MenuState0.hasAddedItem()) {
                        if(supportMenuInflater$MenuState0.itemActionProvider == null || !supportMenuInflater$MenuState0.itemActionProvider.hasSubMenu()) {
                            supportMenuInflater$MenuState0.addItem();
                        }
                        else {
                            supportMenuInflater$MenuState0.addSubMenuItem();
                        }
                    }
                }
            }
            v = xmlPullParser0.next();
        }
    }
}

