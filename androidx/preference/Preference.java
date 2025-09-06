package androidx.preference;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Preference implements Comparable {
    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;

        static {
            BaseSavedState.CREATOR = new Parcelable.Creator() {
                public BaseSavedState createFromParcel(Parcel parcel0) {
                    return new BaseSavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public BaseSavedState[] newArray(int v) {
                    return new BaseSavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public BaseSavedState(Parcel parcel0) {
            super(parcel0);
        }

        public BaseSavedState(Parcelable parcelable0) {
            super(parcelable0);
        }
    }

    interface OnPreferenceChangeInternalListener {
        void onPreferenceChange(Preference arg1);

        void onPreferenceHierarchyChange(Preference arg1);

        void onPreferenceVisibilityChange(Preference arg1);
    }

    public interface OnPreferenceChangeListener {
        boolean onPreferenceChange(Preference arg1, Object arg2);
    }

    public interface OnPreferenceClickListener {
        boolean onPreferenceClick(Preference arg1);
    }

    static class OnPreferenceCopyListener implements MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener {
        private final Preference mPreference;

        OnPreferenceCopyListener(Preference preference0) {
            this.mPreference = preference0;
        }

        @Override  // android.view.View$OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu0, View view0, ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0) {
            CharSequence charSequence0 = this.mPreference.getSummary();
            if(this.mPreference.isCopyingEnabled() && !TextUtils.isEmpty(charSequence0)) {
                contextMenu0.setHeaderTitle(charSequence0);
                contextMenu0.add(0, 0, 0, string.copy).setOnMenuItemClickListener(this);
            }
        }

        @Override  // android.view.MenuItem$OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem0) {
            ClipboardManager clipboardManager0 = (ClipboardManager)this.mPreference.getContext().getSystemService("clipboard");
            CharSequence charSequence0 = this.mPreference.getSummary();
            clipboardManager0.setPrimaryClip(ClipData.newPlainText("Preference", charSequence0));
            String s = this.mPreference.getContext().getString(string.preference_copied, new Object[]{charSequence0});
            Toast.makeText(this.mPreference.getContext(), s, 0).show();
            return true;
        }
    }

    public interface SummaryProvider {
        CharSequence provideSummary(Preference arg1);
    }

    private static final String CLIPBOARD_ID = "Preference";
    public static final int DEFAULT_ORDER = 0x7FFFFFFF;
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private boolean mBaseMethodCalled;
    private final View.OnClickListener mClickListener;
    private final Context mContext;
    private boolean mCopyingEnabled;
    private Object mDefaultValue;
    private String mDependencyKey;
    private boolean mDependencyMet;
    private List mDependents;
    private boolean mEnabled;
    private Bundle mExtras;
    private String mFragment;
    private boolean mHasId;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    private int mIconResId;
    private boolean mIconSpaceReserved;
    private long mId;
    private Intent mIntent;
    private String mKey;
    private int mLayoutResId;
    private OnPreferenceChangeInternalListener mListener;
    private OnPreferenceChangeListener mOnChangeListener;
    private OnPreferenceClickListener mOnClickListener;
    private OnPreferenceCopyListener mOnCopyListener;
    private int mOrder;
    private boolean mParentDependencyMet;
    private PreferenceGroup mParentGroup;
    private boolean mPersistent;
    private PreferenceDataStore mPreferenceDataStore;
    private PreferenceManager mPreferenceManager;
    private boolean mRequiresKey;
    private boolean mSelectable;
    private boolean mShouldDisableView;
    private boolean mSingleLineTitle;
    private CharSequence mSummary;
    private SummaryProvider mSummaryProvider;
    private CharSequence mTitle;
    private int mViewId;
    private boolean mVisible;
    private boolean mWasDetached;
    private int mWidgetLayoutResId;

    public Preference(Context context0) {
        this(context0, null);
    }

    public Preference(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, TypedArrayUtils.getAttr(context0, attr.preferenceStyle, 0x101008E));
    }

    public Preference(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public Preference(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.mOrder = 0x7FFFFFFF;
        this.mViewId = 0;
        this.mEnabled = true;
        this.mSelectable = true;
        this.mPersistent = true;
        this.mDependencyMet = true;
        this.mParentDependencyMet = true;
        this.mVisible = true;
        this.mAllowDividerAbove = true;
        this.mAllowDividerBelow = true;
        this.mSingleLineTitle = true;
        this.mShouldDisableView = true;
        this.mLayoutResId = layout.preference;
        this.mClickListener = (View view0) -> Preference.this.performClick();
        this.mContext = context0;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Preference, v, v1);
        this.mIconResId = TypedArrayUtils.getResourceId(typedArray0, styleable.Preference_icon, styleable.Preference_android_icon, 0);
        this.mKey = TypedArrayUtils.getString(typedArray0, styleable.Preference_key, styleable.Preference_android_key);
        this.mTitle = TypedArrayUtils.getText(typedArray0, styleable.Preference_title, styleable.Preference_android_title);
        this.mSummary = TypedArrayUtils.getText(typedArray0, styleable.Preference_summary, styleable.Preference_android_summary);
        this.mOrder = TypedArrayUtils.getInt(typedArray0, styleable.Preference_order, styleable.Preference_android_order, 0x7FFFFFFF);
        this.mFragment = TypedArrayUtils.getString(typedArray0, styleable.Preference_fragment, styleable.Preference_android_fragment);
        this.mLayoutResId = TypedArrayUtils.getResourceId(typedArray0, styleable.Preference_layout, styleable.Preference_android_layout, layout.preference);
        this.mWidgetLayoutResId = TypedArrayUtils.getResourceId(typedArray0, styleable.Preference_widgetLayout, styleable.Preference_android_widgetLayout, 0);
        this.mEnabled = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_enabled, styleable.Preference_android_enabled, true);
        this.mSelectable = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_selectable, styleable.Preference_android_selectable, true);
        this.mPersistent = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_persistent, styleable.Preference_android_persistent, true);
        this.mDependencyKey = TypedArrayUtils.getString(typedArray0, styleable.Preference_dependency, styleable.Preference_android_dependency);
        this.mAllowDividerAbove = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_allowDividerAbove, styleable.Preference_allowDividerAbove, this.mSelectable);
        this.mAllowDividerBelow = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_allowDividerBelow, styleable.Preference_allowDividerBelow, this.mSelectable);
        if(typedArray0.hasValue(styleable.Preference_defaultValue)) {
            this.mDefaultValue = this.onGetDefaultValue(typedArray0, styleable.Preference_defaultValue);
        }
        else if(typedArray0.hasValue(styleable.Preference_android_defaultValue)) {
            this.mDefaultValue = this.onGetDefaultValue(typedArray0, styleable.Preference_android_defaultValue);
        }
        this.mShouldDisableView = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_shouldDisableView, styleable.Preference_android_shouldDisableView, true);
        boolean z = typedArray0.hasValue(styleable.Preference_singleLineTitle);
        this.mHasSingleLineTitleAttr = z;
        if(z) {
            this.mSingleLineTitle = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_singleLineTitle, styleable.Preference_android_singleLineTitle, true);
        }
        this.mIconSpaceReserved = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_iconSpaceReserved, styleable.Preference_android_iconSpaceReserved, false);
        this.mVisible = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_isPreferenceVisible, styleable.Preference_isPreferenceVisible, true);
        this.mCopyingEnabled = TypedArrayUtils.getBoolean(typedArray0, styleable.Preference_enableCopying, styleable.Preference_enableCopying, false);
        typedArray0.recycle();
    }

    void assignParent(PreferenceGroup preferenceGroup0) {
        if(preferenceGroup0 != null && this.mParentGroup != null) {
            throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
        }
        this.mParentGroup = preferenceGroup0;
    }

    public boolean callChangeListener(Object object0) {
        return this.mOnChangeListener == null || this.mOnChangeListener.onPreferenceChange(this, object0);
    }

    final void clearWasDetached() {
        this.mWasDetached = false;
    }

    public int compareTo(Preference preference0) {
        int v = this.mOrder;
        int v1 = preference0.mOrder;
        if(v != v1) {
            return v - v1;
        }
        CharSequence charSequence0 = this.mTitle;
        CharSequence charSequence1 = preference0.mTitle;
        if(charSequence0 == charSequence1) {
            return 0;
        }
        if(charSequence0 == null) {
            return 1;
        }
        return charSequence1 == null ? -1 : charSequence0.toString().compareToIgnoreCase(preference0.mTitle.toString());
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Preference)object0));
    }

    void dispatchRestoreInstanceState(Bundle bundle0) {
        if(this.hasKey()) {
            Parcelable parcelable0 = bundle0.getParcelable(this.mKey);
            if(parcelable0 != null) {
                this.mBaseMethodCalled = false;
                this.onRestoreInstanceState(parcelable0);
                if(!this.mBaseMethodCalled) {
                    throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
                }
            }
        }
    }

    void dispatchSaveInstanceState(Bundle bundle0) {
        if(this.hasKey()) {
            this.mBaseMethodCalled = false;
            Parcelable parcelable0 = this.onSaveInstanceState();
            if(!this.mBaseMethodCalled) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if(parcelable0 != null) {
                bundle0.putParcelable(this.mKey, parcelable0);
            }
        }
    }

    private void dispatchSetInitialValue() {
        if(this.getPreferenceDataStore() != null) {
            this.onSetInitialValue(true, this.mDefaultValue);
            return;
        }
        if(this.shouldPersist() && this.getSharedPreferences().contains(this.mKey)) {
            this.onSetInitialValue(true, null);
            return;
        }
        Object object0 = this.mDefaultValue;
        if(object0 != null) {
            this.onSetInitialValue(false, object0);
        }
    }

    protected Preference findPreferenceInHierarchy(String s) {
        return this.mPreferenceManager == null ? null : this.mPreferenceManager.findPreference(s);
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDependency() {
        return this.mDependencyKey;
    }

    public Bundle getExtras() {
        if(this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        return this.mExtras;
    }

    StringBuilder getFilterableStringBuilder() {
        StringBuilder stringBuilder0 = new StringBuilder();
        CharSequence charSequence0 = this.getTitle();
        if(!TextUtils.isEmpty(charSequence0)) {
            stringBuilder0.append(charSequence0);
            stringBuilder0.append(' ');
        }
        CharSequence charSequence1 = this.getSummary();
        if(!TextUtils.isEmpty(charSequence1)) {
            stringBuilder0.append(charSequence1);
            stringBuilder0.append(' ');
        }
        if(stringBuilder0.length() > 0) {
            stringBuilder0.setLength(stringBuilder0.length() - 1);
        }
        return stringBuilder0;
    }

    public String getFragment() {
        return this.mFragment;
    }

    public Drawable getIcon() {
        if(this.mIcon == null) {
            int v = this.mIconResId;
            if(v != 0) {
                this.mIcon = AppCompatResources.getDrawable(this.mContext, v);
            }
        }
        return this.mIcon;
    }

    long getId() {
        return this.mId;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getKey() {
        return this.mKey;
    }

    public final int getLayoutResource() {
        return this.mLayoutResId;
    }

    public OnPreferenceChangeListener getOnPreferenceChangeListener() {
        return this.mOnChangeListener;
    }

    public OnPreferenceClickListener getOnPreferenceClickListener() {
        return this.mOnClickListener;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public PreferenceGroup getParent() {
        return this.mParentGroup;
    }

    protected boolean getPersistedBoolean(boolean z) {
        if(!this.shouldPersist()) {
            return z;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getBoolean(this.mKey, z) : z;
    }

    protected float getPersistedFloat(float f) {
        if(!this.shouldPersist()) {
            return f;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getFloat(this.mKey, f) : f;
    }

    protected int getPersistedInt(int v) {
        if(!this.shouldPersist()) {
            return v;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getInt(this.mKey, v) : v;
    }

    protected long getPersistedLong(long v) {
        if(!this.shouldPersist()) {
            return v;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getLong(this.mKey, v) : v;
    }

    protected String getPersistedString(String s) {
        if(!this.shouldPersist()) {
            return s;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getString(this.mKey, s) : s;
    }

    public Set getPersistedStringSet(Set set0) {
        if(!this.shouldPersist()) {
            return set0;
        }
        return this.getPreferenceDataStore() == null ? this.mPreferenceManager.getSharedPreferences().getStringSet(this.mKey, set0) : set0;
    }

    public PreferenceDataStore getPreferenceDataStore() {
        PreferenceDataStore preferenceDataStore0 = this.mPreferenceDataStore;
        if(preferenceDataStore0 != null) {
            return preferenceDataStore0;
        }
        return this.mPreferenceManager == null ? null : this.mPreferenceManager.getPreferenceDataStore();
    }

    public PreferenceManager getPreferenceManager() {
        return this.mPreferenceManager;
    }

    public SharedPreferences getSharedPreferences() {
        return this.mPreferenceManager == null || this.getPreferenceDataStore() != null ? null : this.mPreferenceManager.getSharedPreferences();
    }

    public boolean getShouldDisableView() {
        return this.mShouldDisableView;
    }

    public CharSequence getSummary() {
        return this.getSummaryProvider() == null ? this.mSummary : this.getSummaryProvider().provideSummary(this);
    }

    public final SummaryProvider getSummaryProvider() {
        return this.mSummaryProvider;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public final int getWidgetLayoutResource() {
        return this.mWidgetLayoutResId;
    }

    public boolean hasKey() {
        return !TextUtils.isEmpty(this.mKey);
    }

    public boolean isCopyingEnabled() {
        return this.mCopyingEnabled;
    }

    // 去混淆评级： 低(30)
    public boolean isEnabled() {
        return this.mEnabled && this.mDependencyMet && this.mParentDependencyMet;
    }

    public boolean isIconSpaceReserved() {
        return this.mIconSpaceReserved;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public boolean isSelectable() {
        return this.mSelectable;
    }

    public final boolean isShown() {
        if(!this.isVisible()) {
            return false;
        }
        if(this.getPreferenceManager() == null) {
            return false;
        }
        if(this == this.getPreferenceManager().getPreferenceScreen()) {
            return true;
        }
        PreferenceGroup preferenceGroup0 = this.getParent();
        return preferenceGroup0 == null ? false : preferenceGroup0.isShown();
    }

    public boolean isSingleLineTitle() {
        return this.mSingleLineTitle;
    }

    public final boolean isVisible() {
        return this.mVisible;
    }

    protected void notifyChanged() {
        OnPreferenceChangeInternalListener preference$OnPreferenceChangeInternalListener0 = this.mListener;
        if(preference$OnPreferenceChangeInternalListener0 != null) {
            preference$OnPreferenceChangeInternalListener0.onPreferenceChange(this);
        }
    }

    public void notifyDependencyChange(boolean z) {
        List list0 = this.mDependents;
        if(list0 != null) {
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((Preference)list0.get(v1)).onDependencyChanged(this, z);
            }
        }
    }

    protected void notifyHierarchyChanged() {
        OnPreferenceChangeInternalListener preference$OnPreferenceChangeInternalListener0 = this.mListener;
        if(preference$OnPreferenceChangeInternalListener0 != null) {
            preference$OnPreferenceChangeInternalListener0.onPreferenceHierarchyChange(this);
        }
    }

    public void onAttached() {
        this.registerDependency();
    }

    protected void onAttachedToHierarchy(PreferenceManager preferenceManager0) {
        this.mPreferenceManager = preferenceManager0;
        if(!this.mHasId) {
            this.mId = preferenceManager0.getNextId();
        }
        this.dispatchSetInitialValue();
    }

    protected void onAttachedToHierarchy(PreferenceManager preferenceManager0, long v) {
        try {
            this.mId = v;
            this.mHasId = true;
            this.onAttachedToHierarchy(preferenceManager0);
            this.mHasId = false;
        }
        catch(Throwable throwable0) {
            this.mHasId = false;
            throw throwable0;
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        Integer integer0;
        View view0 = preferenceViewHolder0.itemView;
        view0.setOnClickListener(this.mClickListener);
        view0.setId(this.mViewId);
        TextView textView0 = (TextView)preferenceViewHolder0.findViewById(0x1020010);
        int v = 8;
        if(textView0 == null) {
            integer0 = null;
        }
        else {
            CharSequence charSequence0 = this.getSummary();
            if(TextUtils.isEmpty(charSequence0)) {
                textView0.setVisibility(8);
                integer0 = null;
            }
            else {
                textView0.setText(charSequence0);
                textView0.setVisibility(0);
                integer0 = textView0.getCurrentTextColor();
            }
        }
        TextView textView1 = (TextView)preferenceViewHolder0.findViewById(0x1020016);
        if(textView1 != null) {
            CharSequence charSequence1 = this.getTitle();
            if(TextUtils.isEmpty(charSequence1)) {
                textView1.setVisibility(8);
            }
            else {
                textView1.setText(charSequence1);
                textView1.setVisibility(0);
                if(this.mHasSingleLineTitleAttr) {
                    textView1.setSingleLine(this.mSingleLineTitle);
                }
                if(!this.isSelectable() && this.isEnabled() && integer0 != null) {
                    textView1.setTextColor(((int)integer0));
                }
            }
        }
        ImageView imageView0 = (ImageView)preferenceViewHolder0.findViewById(0x1020006);
        if(imageView0 != null) {
            int v1 = this.mIconResId;
            if(v1 != 0 || this.mIcon != null) {
                if(this.mIcon == null) {
                    this.mIcon = AppCompatResources.getDrawable(this.mContext, v1);
                }
                Drawable drawable0 = this.mIcon;
                if(drawable0 != null) {
                    imageView0.setImageDrawable(drawable0);
                }
            }
            if(this.mIcon == null) {
                imageView0.setVisibility((this.mIconSpaceReserved ? 4 : 8));
            }
            else {
                imageView0.setVisibility(0);
            }
        }
        View view1 = preferenceViewHolder0.findViewById(id.icon_frame);
        if(view1 == null) {
            view1 = preferenceViewHolder0.findViewById(0x102003E);
        }
        if(view1 != null) {
            if(this.mIcon == null) {
                if(this.mIconSpaceReserved) {
                    v = 4;
                }
                view1.setVisibility(v);
            }
            else {
                view1.setVisibility(0);
            }
        }
        if(this.mShouldDisableView) {
            this.setEnabledStateOnViews(view0, this.isEnabled());
        }
        else {
            this.setEnabledStateOnViews(view0, true);
        }
        boolean z = this.isSelectable();
        view0.setFocusable(z);
        view0.setClickable(z);
        preferenceViewHolder0.setDividerAllowedAbove(this.mAllowDividerAbove);
        preferenceViewHolder0.setDividerAllowedBelow(this.mAllowDividerBelow);
        boolean z1 = this.isCopyingEnabled();
        if(z1 && this.mOnCopyListener == null) {
            this.mOnCopyListener = new OnPreferenceCopyListener(this);
        }
        view0.setOnCreateContextMenuListener((z1 ? this.mOnCopyListener : null));
        view0.setLongClickable(z1);
        if(z1 && !z) {
            ViewCompat.setBackground(view0, null);
        }
    }

    protected void onClick() {
    }

    public void onDependencyChanged(Preference preference0, boolean z) {
        if(this.mDependencyMet == z) {
            this.mDependencyMet = !z;
            this.notifyDependencyChange(this.shouldDisableDependents());
            this.notifyChanged();
        }
    }

    public void onDetached() {
        this.unregisterDependency();
        this.mWasDetached = true;
    }

    protected Object onGetDefaultValue(TypedArray typedArray0, int v) {
        return null;
    }

    @Deprecated
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
    }

    public void onParentChanged(Preference preference0, boolean z) {
        if(this.mParentDependencyMet == z) {
            this.mParentDependencyMet = !z;
            this.notifyDependencyChange(this.shouldDisableDependents());
            this.notifyChanged();
        }
    }

    protected void onPrepareForRemoval() {
        this.unregisterDependency();
    }

    protected void onRestoreInstanceState(Parcelable parcelable0) {
        this.mBaseMethodCalled = true;
        if(parcelable0 != BaseSavedState.EMPTY_STATE && parcelable0 != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    protected Parcelable onSaveInstanceState() {
        this.mBaseMethodCalled = true;
        return BaseSavedState.EMPTY_STATE;
    }

    protected void onSetInitialValue(Object object0) {
    }

    @Deprecated
    protected void onSetInitialValue(boolean z, Object object0) {
        this.onSetInitialValue(object0);
    }

    public Bundle peekExtras() {
        return this.mExtras;
    }

    public void performClick() {
        if(this.isEnabled() && this.isSelectable()) {
            this.onClick();
            if(this.mOnClickListener == null || !this.mOnClickListener.onPreferenceClick(this)) {
                PreferenceManager preferenceManager0 = this.getPreferenceManager();
                if(preferenceManager0 == null) {
                label_7:
                    if(this.mIntent != null) {
                        this.getContext().startActivity(this.mIntent);
                    }
                }
                else {
                    OnPreferenceTreeClickListener preferenceManager$OnPreferenceTreeClickListener0 = preferenceManager0.getOnPreferenceTreeClickListener();
                    if(preferenceManager$OnPreferenceTreeClickListener0 == null || !preferenceManager$OnPreferenceTreeClickListener0.onPreferenceTreeClick(this)) {
                        goto label_7;
                    }
                }
            }
        }
    }

    // 检测为 Lambda 实现
    protected void performClick(View view0) [...]

    protected boolean persistBoolean(boolean z) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(z == this.getPersistedBoolean(!z)) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putBoolean(this.mKey, z);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putBoolean(this.mKey, z);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    protected boolean persistFloat(float f) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(f == this.getPersistedFloat(NaNf)) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putFloat(this.mKey, f);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putFloat(this.mKey, f);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    protected boolean persistInt(int v) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(v == this.getPersistedInt(~v)) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putInt(this.mKey, v);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putInt(this.mKey, v);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    protected boolean persistLong(long v) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(v == this.getPersistedLong(~v)) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putLong(this.mKey, v);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putLong(this.mKey, v);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    protected boolean persistString(String s) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(TextUtils.equals(s, this.getPersistedString(null))) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putString(this.mKey, s);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putString(this.mKey, s);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    public boolean persistStringSet(Set set0) {
        if(!this.shouldPersist()) {
            return false;
        }
        if(set0.equals(this.getPersistedStringSet(null))) {
            return true;
        }
        PreferenceDataStore preferenceDataStore0 = this.getPreferenceDataStore();
        if(preferenceDataStore0 != null) {
            preferenceDataStore0.putStringSet(this.mKey, set0);
            return true;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.mPreferenceManager.getEditor();
        sharedPreferences$Editor0.putStringSet(this.mKey, set0);
        this.tryCommit(sharedPreferences$Editor0);
        return true;
    }

    private void registerDependency() {
        if(TextUtils.isEmpty(this.mDependencyKey)) {
            return;
        }
        Preference preference0 = this.findPreferenceInHierarchy(this.mDependencyKey);
        if(preference0 == null) {
            throw new IllegalStateException("Dependency \"" + this.mDependencyKey + "\" not found for preference \"" + this.mKey + "\" (title: \"" + this.mTitle + "\"");
        }
        preference0.registerDependent(this);
    }

    private void registerDependent(Preference preference0) {
        if(this.mDependents == null) {
            this.mDependents = new ArrayList();
        }
        this.mDependents.add(preference0);
        preference0.onDependencyChanged(this, this.shouldDisableDependents());
    }

    void requireKey() {
        if(TextUtils.isEmpty(this.mKey)) {
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
        this.mRequiresKey = true;
    }

    public void restoreHierarchyState(Bundle bundle0) {
        this.dispatchRestoreInstanceState(bundle0);
    }

    public void saveHierarchyState(Bundle bundle0) {
        this.dispatchSaveInstanceState(bundle0);
    }

    public void setCopyingEnabled(boolean z) {
        if(this.mCopyingEnabled != z) {
            this.mCopyingEnabled = z;
            this.notifyChanged();
        }
    }

    public void setDefaultValue(Object object0) {
        this.mDefaultValue = object0;
    }

    public void setDependency(String s) {
        this.unregisterDependency();
        this.mDependencyKey = s;
        this.registerDependency();
    }

    public void setEnabled(boolean z) {
        if(this.mEnabled != z) {
            this.mEnabled = z;
            this.notifyDependencyChange(this.shouldDisableDependents());
            this.notifyChanged();
        }
    }

    private void setEnabledStateOnViews(View view0, boolean z) {
        view0.setEnabled(z);
        if(view0 instanceof ViewGroup) {
            for(int v = ((ViewGroup)view0).getChildCount() - 1; v >= 0; --v) {
                this.setEnabledStateOnViews(((ViewGroup)view0).getChildAt(v), z);
            }
        }
    }

    public void setFragment(String s) {
        this.mFragment = s;
    }

    public void setIcon(int v) {
        this.setIcon(AppCompatResources.getDrawable(this.mContext, v));
        this.mIconResId = v;
    }

    public void setIcon(Drawable drawable0) {
        if(this.mIcon != drawable0) {
            this.mIcon = drawable0;
            this.mIconResId = 0;
            this.notifyChanged();
        }
    }

    public void setIconSpaceReserved(boolean z) {
        if(this.mIconSpaceReserved != z) {
            this.mIconSpaceReserved = z;
            this.notifyChanged();
        }
    }

    public void setIntent(Intent intent0) {
        this.mIntent = intent0;
    }

    public void setKey(String s) {
        this.mKey = s;
        if(this.mRequiresKey && !this.hasKey()) {
            this.requireKey();
        }
    }

    public void setLayoutResource(int v) {
        this.mLayoutResId = v;
    }

    final void setOnPreferenceChangeInternalListener(OnPreferenceChangeInternalListener preference$OnPreferenceChangeInternalListener0) {
        this.mListener = preference$OnPreferenceChangeInternalListener0;
    }

    public void setOnPreferenceChangeListener(OnPreferenceChangeListener preference$OnPreferenceChangeListener0) {
        this.mOnChangeListener = preference$OnPreferenceChangeListener0;
    }

    public void setOnPreferenceClickListener(OnPreferenceClickListener preference$OnPreferenceClickListener0) {
        this.mOnClickListener = preference$OnPreferenceClickListener0;
    }

    public void setOrder(int v) {
        if(v != this.mOrder) {
            this.mOrder = v;
            this.notifyHierarchyChanged();
        }
    }

    public void setPersistent(boolean z) {
        this.mPersistent = z;
    }

    public void setPreferenceDataStore(PreferenceDataStore preferenceDataStore0) {
        this.mPreferenceDataStore = preferenceDataStore0;
    }

    public void setSelectable(boolean z) {
        if(this.mSelectable != z) {
            this.mSelectable = z;
            this.notifyChanged();
        }
    }

    public void setShouldDisableView(boolean z) {
        if(this.mShouldDisableView != z) {
            this.mShouldDisableView = z;
            this.notifyChanged();
        }
    }

    public void setSingleLineTitle(boolean z) {
        this.mHasSingleLineTitleAttr = true;
        this.mSingleLineTitle = z;
    }

    public void setSummary(int v) {
        this.setSummary(this.mContext.getString(v));
    }

    public void setSummary(CharSequence charSequence0) {
        if(this.getSummaryProvider() != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        }
        if(!TextUtils.equals(this.mSummary, charSequence0)) {
            this.mSummary = charSequence0;
            this.notifyChanged();
        }
    }

    public final void setSummaryProvider(SummaryProvider preference$SummaryProvider0) {
        this.mSummaryProvider = preference$SummaryProvider0;
        this.notifyChanged();
    }

    public void setTitle(int v) {
        this.setTitle(this.mContext.getString(v));
    }

    public void setTitle(CharSequence charSequence0) {
        if(!TextUtils.equals(charSequence0, this.mTitle)) {
            this.mTitle = charSequence0;
            this.notifyChanged();
        }
    }

    public void setViewId(int v) {
        this.mViewId = v;
    }

    public final void setVisible(boolean z) {
        if(this.mVisible != z) {
            this.mVisible = z;
            OnPreferenceChangeInternalListener preference$OnPreferenceChangeInternalListener0 = this.mListener;
            if(preference$OnPreferenceChangeInternalListener0 != null) {
                preference$OnPreferenceChangeInternalListener0.onPreferenceVisibilityChange(this);
            }
        }
    }

    public void setWidgetLayoutResource(int v) {
        this.mWidgetLayoutResId = v;
    }

    public boolean shouldDisableDependents() {
        return !this.isEnabled();
    }

    // 去混淆评级： 低(20)
    protected boolean shouldPersist() {
        return this.mPreferenceManager != null && this.isPersistent() && this.hasKey();
    }

    @Override
    public String toString() {
        return this.getFilterableStringBuilder().toString();
    }

    private void tryCommit(SharedPreferences.Editor sharedPreferences$Editor0) {
        if(this.mPreferenceManager.shouldCommit()) {
            sharedPreferences$Editor0.apply();
        }
    }

    private void unregisterDependency() {
        String s = this.mDependencyKey;
        if(s != null) {
            Preference preference0 = this.findPreferenceInHierarchy(s);
            if(preference0 != null) {
                preference0.unregisterDependent(this);
            }
        }
    }

    private void unregisterDependent(Preference preference0) {
        List list0 = this.mDependents;
        if(list0 != null) {
            list0.remove(preference0);
        }
    }

    final boolean wasDetached() {
        return this.mWasDetached;
    }

    class androidx.preference.Preference.1 implements View.OnClickListener {
        @Override  // android.view.View$OnClickListener
        public void onClick(View view0) {
            Preference.this.performClick(view0);
        }
    }

}

