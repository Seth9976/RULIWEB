package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PreferenceGroup extends Preference {
    public interface OnExpandButtonClickListener {
        void onExpandButtonClick();
    }

    public interface PreferencePositionCallback {
        int getPreferenceAdapterPosition(Preference arg1);

        int getPreferenceAdapterPosition(String arg1);
    }

    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        int mInitialExpandedChildrenCount;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.mInitialExpandedChildrenCount = parcel0.readInt();
        }

        SavedState(Parcelable parcelable0, int v) {
            super(parcelable0);
            this.mInitialExpandedChildrenCount = v;
        }

        @Override  // android.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.mInitialExpandedChildrenCount);
        }
    }

    private static final String TAG = "PreferenceGroup";
    private boolean mAttachedToHierarchy;
    private final Runnable mClearRecycleCacheRunnable;
    private int mCurrentPreferenceOrder;
    private final Handler mHandler;
    final SimpleArrayMap mIdRecycleCache;
    private int mInitialExpandedChildrenCount;
    private OnExpandButtonClickListener mOnExpandButtonClickListener;
    private boolean mOrderingAsAdded;
    private final List mPreferences;

    public PreferenceGroup(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public PreferenceGroup(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public PreferenceGroup(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mIdRecycleCache = new SimpleArrayMap();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mOrderingAsAdded = true;
        this.mCurrentPreferenceOrder = 0;
        this.mAttachedToHierarchy = false;
        this.mInitialExpandedChildrenCount = 0x7FFFFFFF;
        this.mOnExpandButtonClickListener = null;
        this.mClearRecycleCacheRunnable = new Runnable() {
            @Override
            public void run() {
                synchronized(this) {
                    PreferenceGroup.this.mIdRecycleCache.clear();
                }
            }
        };
        this.mPreferences = new ArrayList();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.PreferenceGroup, v, v1);
        this.mOrderingAsAdded = TypedArrayUtils.getBoolean(typedArray0, styleable.PreferenceGroup_orderingFromXml, styleable.PreferenceGroup_orderingFromXml, true);
        if(typedArray0.hasValue(styleable.PreferenceGroup_initialExpandedChildrenCount)) {
            this.setInitialExpandedChildrenCount(TypedArrayUtils.getInt(typedArray0, styleable.PreferenceGroup_initialExpandedChildrenCount, styleable.PreferenceGroup_initialExpandedChildrenCount, 0x7FFFFFFF));
        }
        typedArray0.recycle();
    }

    public void addItemFromInflater(Preference preference0) {
        this.addPreference(preference0);
    }

    public boolean addPreference(Preference preference0) {
        long v3;
        if(this.mPreferences.contains(preference0)) {
            return true;
        }
        if(preference0.getKey() != null) {
            PreferenceGroup preferenceGroup0;
            for(preferenceGroup0 = this; preferenceGroup0.getParent() != null; preferenceGroup0 = preferenceGroup0.getParent()) {
            }
            String s = preference0.getKey();
            if(preferenceGroup0.findPreference(s) != null) {
                Log.e("PreferenceGroup", "Found duplicated key: \"" + s + "\". This can cause unintended behaviour, please use unique keys for every preference.");
            }
        }
        if(preference0.getOrder() == 0x7FFFFFFF) {
            if(this.mOrderingAsAdded) {
                int v = this.mCurrentPreferenceOrder;
                this.mCurrentPreferenceOrder = v + 1;
                preference0.setOrder(v);
            }
            if(preference0 instanceof PreferenceGroup) {
                ((PreferenceGroup)preference0).setOrderingAsAdded(this.mOrderingAsAdded);
            }
        }
        int v1 = Collections.binarySearch(this.mPreferences, preference0);
        if(v1 < 0) {
            v1 = -v1 - 1;
        }
        if(!this.onPrepareAddPreference(preference0)) {
            return false;
        }
        synchronized(this) {
            this.mPreferences.add(v1, preference0);
        }
        PreferenceManager preferenceManager0 = this.getPreferenceManager();
        String s1 = preference0.getKey();
        if(s1 == null || !this.mIdRecycleCache.containsKey(s1)) {
            v3 = preferenceManager0.getNextId();
        }
        else {
            v3 = (long)(((Long)this.mIdRecycleCache.get(s1)));
            this.mIdRecycleCache.remove(s1);
        }
        preference0.onAttachedToHierarchy(preferenceManager0, v3);
        preference0.assignParent(this);
        if(this.mAttachedToHierarchy) {
            preference0.onAttached();
        }
        this.notifyHierarchyChanged();
        return true;
    }

    @Override  // androidx.preference.Preference
    protected void dispatchRestoreInstanceState(Bundle bundle0) {
        super.dispatchRestoreInstanceState(bundle0);
        int v = this.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            this.getPreference(v1).dispatchRestoreInstanceState(bundle0);
        }
    }

    @Override  // androidx.preference.Preference
    protected void dispatchSaveInstanceState(Bundle bundle0) {
        super.dispatchSaveInstanceState(bundle0);
        int v = this.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            this.getPreference(v1).dispatchSaveInstanceState(bundle0);
        }
    }

    public Preference findPreference(CharSequence charSequence0) {
        if(charSequence0 == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if(TextUtils.equals(this.getKey(), charSequence0)) {
            return this;
        }
        int v = this.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            Preference preference0 = this.getPreference(v1);
            if(TextUtils.equals(preference0.getKey(), charSequence0)) {
                return preference0;
            }
            if(preference0 instanceof PreferenceGroup) {
                Preference preference1 = ((PreferenceGroup)preference0).findPreference(charSequence0);
                if(preference1 != null) {
                    return preference1;
                }
            }
        }
        return null;
    }

    public int getInitialExpandedChildrenCount() {
        return this.mInitialExpandedChildrenCount;
    }

    public OnExpandButtonClickListener getOnExpandButtonClickListener() {
        return this.mOnExpandButtonClickListener;
    }

    public Preference getPreference(int v) {
        return (Preference)this.mPreferences.get(v);
    }

    public int getPreferenceCount() {
        return this.mPreferences.size();
    }

    public boolean isAttached() {
        return this.mAttachedToHierarchy;
    }

    protected boolean isOnSameScreenAsChildren() {
        return true;
    }

    public boolean isOrderingAsAdded() {
        return this.mOrderingAsAdded;
    }

    @Override  // androidx.preference.Preference
    public void notifyDependencyChange(boolean z) {
        super.notifyDependencyChange(z);
        int v = this.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            this.getPreference(v1).onParentChanged(this, z);
        }
    }

    @Override  // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mAttachedToHierarchy = true;
        int v = this.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            this.getPreference(v1).onAttached();
        }
    }

    @Override  // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.mAttachedToHierarchy = false;
        int v1 = this.getPreferenceCount();
        for(int v = 0; v < v1; ++v) {
            this.getPreference(v).onDetached();
        }
    }

    protected boolean onPrepareAddPreference(Preference preference0) {
        preference0.onParentChanged(this, this.shouldDisableDependents());
        return true;
    }

    @Override  // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 != null && parcelable0.getClass().equals(SavedState.class)) {
            this.mInitialExpandedChildrenCount = ((SavedState)parcelable0).mInitialExpandedChildrenCount;
            super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
            return;
        }
        super.onRestoreInstanceState(parcelable0);
    }

    @Override  // androidx.preference.Preference
    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mInitialExpandedChildrenCount);
    }

    public void removeAll() {
        synchronized(this) {
            List list0 = this.mPreferences;
            for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                this.removePreferenceInt(((Preference)list0.get(0)));
            }
        }
        this.notifyHierarchyChanged();
    }

    public boolean removePreference(Preference preference0) {
        boolean z = this.removePreferenceInt(preference0);
        this.notifyHierarchyChanged();
        return z;
    }

    private boolean removePreferenceInt(Preference preference0) {
        synchronized(this) {
            preference0.onPrepareForRemoval();
            if(preference0.getParent() == this) {
                preference0.assignParent(null);
            }
            boolean z = this.mPreferences.remove(preference0);
            if(z) {
                String s = preference0.getKey();
                if(s != null) {
                    Long long0 = preference0.getId();
                    this.mIdRecycleCache.put(s, long0);
                    this.mHandler.removeCallbacks(this.mClearRecycleCacheRunnable);
                    this.mHandler.post(this.mClearRecycleCacheRunnable);
                }
                if(this.mAttachedToHierarchy) {
                    preference0.onDetached();
                }
            }
            return z;
        }
    }

    public boolean removePreferenceRecursively(CharSequence charSequence0) {
        Preference preference0 = this.findPreference(charSequence0);
        return preference0 == null ? false : preference0.getParent().removePreference(preference0);
    }

    public void setInitialExpandedChildrenCount(int v) {
        if(v != 0x7FFFFFFF && !this.hasKey()) {
            Log.e("PreferenceGroup", this.getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.mInitialExpandedChildrenCount = v;
    }

    public void setOnExpandButtonClickListener(OnExpandButtonClickListener preferenceGroup$OnExpandButtonClickListener0) {
        this.mOnExpandButtonClickListener = preferenceGroup$OnExpandButtonClickListener0;
    }

    public void setOrderingAsAdded(boolean z) {
        this.mOrderingAsAdded = z;
    }

    void sortPreferences() {
        synchronized(this) {
            Collections.sort(this.mPreferences);
        }
    }
}

