package androidx.preference;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil.Callback;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class PreferenceGroupAdapter extends Adapter implements OnPreferenceChangeInternalListener, PreferencePositionCallback {
    static class PreferenceResourceDescriptor {
        String mClassName;
        int mLayoutResId;
        int mWidgetLayoutResId;

        PreferenceResourceDescriptor(Preference preference0) {
            this.mClassName = preference0.getClass().getName();
            this.mLayoutResId = preference0.getLayoutResource();
            this.mWidgetLayoutResId = preference0.getWidgetLayoutResource();
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof PreferenceResourceDescriptor ? this.mLayoutResId == ((PreferenceResourceDescriptor)object0).mLayoutResId && this.mWidgetLayoutResId == ((PreferenceResourceDescriptor)object0).mWidgetLayoutResId && TextUtils.equals(this.mClassName, ((PreferenceResourceDescriptor)object0).mClassName) : false;
        }

        @Override
        public int hashCode() {
            return ((this.mLayoutResId + 0x20F) * 0x1F + this.mWidgetLayoutResId) * 0x1F + this.mClassName.hashCode();
        }
    }

    private final Handler mHandler;
    private final PreferenceGroup mPreferenceGroup;
    private final List mPreferenceResourceDescriptors;
    private List mPreferences;
    private final Runnable mSyncRunnable;
    private List mVisiblePreferences;

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup0) {
        this.mSyncRunnable = () -> {
            for(Object object0: PreferenceGroupAdapter.this.mPreferences) {
                ((Preference)object0).setOnPreferenceChangeInternalListener(null);
            }
            ArrayList arrayList0 = new ArrayList(PreferenceGroupAdapter.this.mPreferences.size());
            PreferenceGroupAdapter.this.mPreferences = arrayList0;
            PreferenceGroupAdapter.this.flattenPreferenceGroup(arrayList0, PreferenceGroupAdapter.this.mPreferenceGroup);
            List list0 = PreferenceGroupAdapter.this.mVisiblePreferences;
            List list1 = PreferenceGroupAdapter.this.createVisiblePreferencesList(PreferenceGroupAdapter.this.mPreferenceGroup);
            PreferenceGroupAdapter.this.mVisiblePreferences = list1;
            PreferenceManager preferenceManager0 = PreferenceGroupAdapter.this.mPreferenceGroup.getPreferenceManager();
            if(preferenceManager0 == null || preferenceManager0.getPreferenceComparisonCallback() == null) {
                PreferenceGroupAdapter.this.notifyDataSetChanged();
            }
            else {
                DiffUtil.calculateDiff(new Callback() {
                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public boolean areContentsTheSame(int v, int v1) {
                        Preference preference0 = (Preference)list0.get(v);
                        Preference preference1 = (Preference)list1.get(v1);
                        return preferenceManager0.getPreferenceComparisonCallback().arePreferenceContentsTheSame(preference0, preference1);
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public boolean areItemsTheSame(int v, int v1) {
                        Preference preference0 = (Preference)list0.get(v);
                        Preference preference1 = (Preference)list1.get(v1);
                        return preferenceManager0.getPreferenceComparisonCallback().arePreferenceItemsTheSame(preference0, preference1);
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public int getNewListSize() {
                        return list1.size();
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public int getOldListSize() {
                        return list0.size();
                    }
                }).dispatchUpdatesTo(PreferenceGroupAdapter.this);
            }
            for(Object object1: PreferenceGroupAdapter.this.mPreferences) {
                ((Preference)object1).clearWasDetached();
            }
        };
        this.mPreferenceGroup = preferenceGroup0;
        this.mHandler = new Handler(Looper.getMainLooper());
        preferenceGroup0.setOnPreferenceChangeInternalListener(this);
        this.mPreferences = new ArrayList();
        this.mVisiblePreferences = new ArrayList();
        this.mPreferenceResourceDescriptors = new ArrayList();
        if(preferenceGroup0 instanceof PreferenceScreen) {
            this.setHasStableIds(((PreferenceScreen)preferenceGroup0).shouldUseGeneratedIds());
        }
        else {
            this.setHasStableIds(true);
        }
        this.updatePreferences();
    }

    private ExpandButton createExpandButton(PreferenceGroup preferenceGroup0, List list0) {
        ExpandButton expandButton0 = new ExpandButton(preferenceGroup0.getContext(), list0, preferenceGroup0.getId());
        expandButton0.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            @Override  // androidx.preference.Preference$OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference0) {
                preferenceGroup0.setInitialExpandedChildrenCount(0x7FFFFFFF);
                PreferenceGroupAdapter.this.onPreferenceHierarchyChange(preference0);
                OnExpandButtonClickListener preferenceGroup$OnExpandButtonClickListener0 = preferenceGroup0.getOnExpandButtonClickListener();
                if(preferenceGroup$OnExpandButtonClickListener0 != null) {
                    preferenceGroup$OnExpandButtonClickListener0.onExpandButtonClick();
                }
                return true;
            }
        });
        return expandButton0;
    }

    private List createVisiblePreferencesList(PreferenceGroup preferenceGroup0) {
        List list0 = new ArrayList();
        ArrayList arrayList0 = new ArrayList();
        int v = preferenceGroup0.getPreferenceCount();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Preference preference0 = preferenceGroup0.getPreference(v1);
            if(preference0.isVisible()) {
                if(!this.isGroupExpandable(preferenceGroup0) || v2 < preferenceGroup0.getInitialExpandedChildrenCount()) {
                    list0.add(preference0);
                }
                else {
                    arrayList0.add(preference0);
                }
                if(!(preference0 instanceof PreferenceGroup)) {
                    ++v2;
                }
                else if(((PreferenceGroup)preference0).isOnSameScreenAsChildren()) {
                    if(this.isGroupExpandable(preferenceGroup0) && this.isGroupExpandable(((PreferenceGroup)preference0))) {
                        throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                    }
                    for(Object object0: this.createVisiblePreferencesList(((PreferenceGroup)preference0))) {
                        Preference preference1 = (Preference)object0;
                        if(!this.isGroupExpandable(preferenceGroup0) || v2 < preferenceGroup0.getInitialExpandedChildrenCount()) {
                            list0.add(preference1);
                        }
                        else {
                            arrayList0.add(preference1);
                        }
                        ++v2;
                    }
                }
            }
        }
        if(this.isGroupExpandable(preferenceGroup0) && v2 > preferenceGroup0.getInitialExpandedChildrenCount()) {
            list0.add(this.createExpandButton(preferenceGroup0, arrayList0));
        }
        return list0;
    }

    private void flattenPreferenceGroup(List list0, PreferenceGroup preferenceGroup0) {
        preferenceGroup0.sortPreferences();
        int v = preferenceGroup0.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            Preference preference0 = preferenceGroup0.getPreference(v1);
            list0.add(preference0);
            PreferenceResourceDescriptor preferenceGroupAdapter$PreferenceResourceDescriptor0 = new PreferenceResourceDescriptor(preference0);
            if(!this.mPreferenceResourceDescriptors.contains(preferenceGroupAdapter$PreferenceResourceDescriptor0)) {
                this.mPreferenceResourceDescriptors.add(preferenceGroupAdapter$PreferenceResourceDescriptor0);
            }
            if(preference0 instanceof PreferenceGroup && ((PreferenceGroup)preference0).isOnSameScreenAsChildren()) {
                this.flattenPreferenceGroup(list0, ((PreferenceGroup)preference0));
            }
            preference0.setOnPreferenceChangeInternalListener(this);
        }
    }

    public Preference getItem(int v) {
        return v < 0 || v >= this.getItemCount() ? null : ((Preference)this.mVisiblePreferences.get(v));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemCount() {
        return this.mVisiblePreferences.size();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public long getItemId(int v) {
        return this.hasStableIds() ? this.getItem(v).getId() : -1L;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemViewType(int v) {
        PreferenceResourceDescriptor preferenceGroupAdapter$PreferenceResourceDescriptor0 = new PreferenceResourceDescriptor(this.getItem(v));
        int v1 = this.mPreferenceResourceDescriptors.indexOf(preferenceGroupAdapter$PreferenceResourceDescriptor0);
        if(v1 != -1) {
            return v1;
        }
        this.mPreferenceResourceDescriptors.add(preferenceGroupAdapter$PreferenceResourceDescriptor0);
        return this.mPreferenceResourceDescriptors.size();
    }

    @Override  // androidx.preference.PreferenceGroup$PreferencePositionCallback
    public int getPreferenceAdapterPosition(Preference preference0) {
        int v = this.mVisiblePreferences.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Preference preference1 = (Preference)this.mVisiblePreferences.get(v1);
            if(preference1 != null && preference1.equals(preference0)) {
                return v1;
            }
        }
        return -1;
    }

    @Override  // androidx.preference.PreferenceGroup$PreferencePositionCallback
    public int getPreferenceAdapterPosition(String s) {
        int v = this.mVisiblePreferences.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(TextUtils.equals(s, ((Preference)this.mVisiblePreferences.get(v1)).getKey())) {
                return v1;
            }
        }
        return -1;
    }

    private boolean isGroupExpandable(PreferenceGroup preferenceGroup0) {
        return preferenceGroup0.getInitialExpandedChildrenCount() != 0x7FFFFFFF;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0, int v) {
        Preference preference0 = this.getItem(v);
        preferenceViewHolder0.resetState();
        preference0.onBindViewHolder(preferenceViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
        this.onBindViewHolder(((PreferenceViewHolder)recyclerView$ViewHolder0), v);
    }

    public PreferenceViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        PreferenceResourceDescriptor preferenceGroupAdapter$PreferenceResourceDescriptor0 = (PreferenceResourceDescriptor)this.mPreferenceResourceDescriptors.get(v);
        LayoutInflater layoutInflater0 = LayoutInflater.from(viewGroup0.getContext());
        TypedArray typedArray0 = viewGroup0.getContext().obtainStyledAttributes(null, styleable.BackgroundStyle);
        Drawable drawable0 = typedArray0.getDrawable(styleable.BackgroundStyle_android_selectableItemBackground);
        if(drawable0 == null) {
            drawable0 = AppCompatResources.getDrawable(viewGroup0.getContext(), 0x1080062);
        }
        typedArray0.recycle();
        View view0 = layoutInflater0.inflate(preferenceGroupAdapter$PreferenceResourceDescriptor0.mLayoutResId, viewGroup0, false);
        if(view0.getBackground() == null) {
            ViewCompat.setBackground(view0, drawable0);
        }
        ViewGroup viewGroup1 = (ViewGroup)view0.findViewById(0x1020018);
        if(viewGroup1 != null) {
            if(preferenceGroupAdapter$PreferenceResourceDescriptor0.mWidgetLayoutResId != 0) {
                layoutInflater0.inflate(preferenceGroupAdapter$PreferenceResourceDescriptor0.mWidgetLayoutResId, viewGroup1);
                return new PreferenceViewHolder(view0);
            }
            viewGroup1.setVisibility(8);
        }
        return new PreferenceViewHolder(view0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.onCreateViewHolder(viewGroup0, v);
    }

    @Override  // androidx.preference.Preference$OnPreferenceChangeInternalListener
    public void onPreferenceChange(Preference preference0) {
        int v = this.mVisiblePreferences.indexOf(preference0);
        if(v != -1) {
            this.notifyItemChanged(v, preference0);
        }
    }

    @Override  // androidx.preference.Preference$OnPreferenceChangeInternalListener
    public void onPreferenceHierarchyChange(Preference preference0) {
        this.mHandler.removeCallbacks(this.mSyncRunnable);
        this.mHandler.post(this.mSyncRunnable);
    }

    @Override  // androidx.preference.Preference$OnPreferenceChangeInternalListener
    public void onPreferenceVisibilityChange(Preference preference0) {
        this.onPreferenceHierarchyChange(preference0);
    }

    // 检测为 Lambda 实现
    void updatePreferences() [...]

    class androidx.preference.PreferenceGroupAdapter.1 implements Runnable {
        @Override
        public void run() {
            PreferenceGroupAdapter.this.updatePreferences();
        }
    }

}

