package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.collection.ArraySet;
import androidx.collection.LongSparseArray;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment.SavedState;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class FragmentStateAdapter extends Adapter implements StatefulAdapter {
    static abstract class DataSetChangeObserver extends AdapterDataObserver {
        private DataSetChangeObserver() {
        }

        DataSetChangeObserver(androidx.viewpager2.adapter.FragmentStateAdapter.1 fragmentStateAdapter$10) {
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public abstract void onChanged();

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeChanged(int v, int v1) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeChanged(int v, int v1, Object object0) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeInserted(int v, int v1) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeMoved(int v, int v1, int v2) {
            this.onChanged();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public final void onItemRangeRemoved(int v, int v1) {
            this.onChanged();
        }
    }

    public @interface ExperimentalFragmentStateAdapterApi {
    }

    static class FragmentEventDispatcher {
        private List mCallbacks;

        FragmentEventDispatcher() {
            this.mCallbacks = new CopyOnWriteArrayList();
        }

        public List dispatchMaxLifecyclePreUpdated(Fragment fragment0, State lifecycle$State0) {
            List list0 = new ArrayList();
            for(Object object0: this.mCallbacks) {
                list0.add(((FragmentTransactionCallback)object0).onFragmentMaxLifecyclePreUpdated(fragment0, lifecycle$State0));
            }
            return list0;
        }

        public void dispatchPostEvents(List list0) {
            for(Object object0: list0) {
                ((OnPostEventListener)object0).onPost();
            }
        }

        public List dispatchPreAdded(Fragment fragment0) {
            List list0 = new ArrayList();
            for(Object object0: this.mCallbacks) {
                list0.add(((FragmentTransactionCallback)object0).onFragmentPreAdded(fragment0));
            }
            return list0;
        }

        public List dispatchPreRemoved(Fragment fragment0) {
            List list0 = new ArrayList();
            for(Object object0: this.mCallbacks) {
                list0.add(((FragmentTransactionCallback)object0).onFragmentPreRemoved(fragment0));
            }
            return list0;
        }

        public List dispatchPreSavedInstanceState(Fragment fragment0) {
            List list0 = new ArrayList();
            for(Object object0: this.mCallbacks) {
                list0.add(((FragmentTransactionCallback)object0).onFragmentPreSavedInstanceState(fragment0));
            }
            return list0;
        }

        public void registerCallback(FragmentTransactionCallback fragmentStateAdapter$FragmentTransactionCallback0) {
            this.mCallbacks.add(fragmentStateAdapter$FragmentTransactionCallback0);
        }

        public void unregisterCallback(FragmentTransactionCallback fragmentStateAdapter$FragmentTransactionCallback0) {
            this.mCallbacks.remove(fragmentStateAdapter$FragmentTransactionCallback0);
        }
    }

    class FragmentMaxLifecycleEnforcer {
        private AdapterDataObserver mDataObserver;
        private LifecycleEventObserver mLifecycleObserver;
        private OnPageChangeCallback mPageChangeCallback;
        private long mPrimaryItemId;
        private ViewPager2 mViewPager;

        FragmentMaxLifecycleEnforcer() {
            this.mPrimaryItemId = -1L;
        }

        private ViewPager2 inferViewPager(RecyclerView recyclerView0) {
            ViewParent viewParent0 = recyclerView0.getParent();
            if(!(viewParent0 instanceof ViewPager2)) {
                throw new IllegalStateException("Expected ViewPager2 instance. Got: " + viewParent0);
            }
            return (ViewPager2)viewParent0;
        }

        void register(RecyclerView recyclerView0) {
            this.mViewPager = this.inferViewPager(recyclerView0);
            androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.1 fragmentStateAdapter$FragmentMaxLifecycleEnforcer$10 = new OnPageChangeCallback() {
                @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
                public void onPageScrollStateChanged(int v) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }

                @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
                public void onPageSelected(int v) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }
            };
            this.mPageChangeCallback = fragmentStateAdapter$FragmentMaxLifecycleEnforcer$10;
            this.mViewPager.registerOnPageChangeCallback(fragmentStateAdapter$FragmentMaxLifecycleEnforcer$10);
            androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.2 fragmentStateAdapter$FragmentMaxLifecycleEnforcer$20 = new DataSetChangeObserver() {
                {
                    FragmentMaxLifecycleEnforcer.this = fragmentStateAdapter$FragmentMaxLifecycleEnforcer0;
                    super(null);
                }

                @Override  // androidx.viewpager2.adapter.FragmentStateAdapter$DataSetChangeObserver
                public void onChanged() {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(true);
                }
            };
            this.mDataObserver = fragmentStateAdapter$FragmentMaxLifecycleEnforcer$20;
            FragmentStateAdapter.this.registerAdapterDataObserver(fragmentStateAdapter$FragmentMaxLifecycleEnforcer$20);
            this.mLifecycleObserver = (/* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */) -> if(!FragmentStateAdapter.this.shouldDelayFragmentTransactions() && FragmentMaxLifecycleEnforcer.this.mViewPager.getScrollState() == 0 && !FragmentStateAdapter.this.mFragments.isEmpty() && FragmentStateAdapter.this.getItemCount() != 0) {
                int v = FragmentMaxLifecycleEnforcer.this.mViewPager.getCurrentItem();
                if(v < FragmentStateAdapter.this.getItemCount() && (((long)v) != FragmentMaxLifecycleEnforcer.this.mPrimaryItemId || false)) {
                    Fragment fragment0 = (Fragment)FragmentStateAdapter.this.mFragments.get(((long)v));
                    if(fragment0 != null && fragment0.isAdded()) {
                        FragmentMaxLifecycleEnforcer.this.mPrimaryItemId = (long)v;
                        FragmentTransaction fragmentTransaction0 = FragmentStateAdapter.this.mFragmentManager.beginTransaction();
                        ArrayList arrayList0 = new ArrayList();
                        Fragment fragment1 = null;
                        for(int v1 = 0; v1 < FragmentStateAdapter.this.mFragments.size(); ++v1) {
                            long v2 = FragmentStateAdapter.this.mFragments.keyAt(v1);
                            Fragment fragment2 = (Fragment)FragmentStateAdapter.this.mFragments.valueAt(v1);
                            if(fragment2.isAdded()) {
                                if(v2 == FragmentMaxLifecycleEnforcer.this.mPrimaryItemId) {
                                    fragment1 = fragment2;
                                }
                                else {
                                    fragmentTransaction0.setMaxLifecycle(fragment2, State.STARTED);
                                    arrayList0.add(FragmentStateAdapter.this.mFragmentEventDispatcher.dispatchMaxLifecyclePreUpdated(fragment2, State.STARTED));
                                }
                                fragment2.setMenuVisibility(v2 == FragmentMaxLifecycleEnforcer.this.mPrimaryItemId);
                            }
                        }
                        if(fragment1 != null) {
                            fragmentTransaction0.setMaxLifecycle(fragment1, State.RESUMED);
                            arrayList0.add(FragmentStateAdapter.this.mFragmentEventDispatcher.dispatchMaxLifecyclePreUpdated(fragment1, State.RESUMED));
                        }
                        if(!fragmentTransaction0.isEmpty()) {
                            fragmentTransaction0.commitNow();
                            Collections.reverse(arrayList0);
                            for(Object object0: arrayList0) {
                                FragmentStateAdapter.this.mFragmentEventDispatcher.dispatchPostEvents(((List)object0));
                            }
                        }
                    }
                }
            };
            FragmentStateAdapter.this.mLifecycle.addObserver(this.mLifecycleObserver);

            class androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3 implements LifecycleEventObserver {
                @Override  // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }
            }

        }

        void unregister(RecyclerView recyclerView0) {
            this.inferViewPager(recyclerView0).unregisterOnPageChangeCallback(this.mPageChangeCallback);
            FragmentStateAdapter.this.unregisterAdapterDataObserver(this.mDataObserver);
            FragmentStateAdapter.this.mLifecycle.removeObserver(this.mLifecycleObserver);
            this.mViewPager = null;
        }

        // 检测为 Lambda 实现
        void updateFragmentMaxLifecycle(boolean z) [...]
    }

    public static abstract class FragmentTransactionCallback {
        public interface OnPostEventListener {
            void onPost();
        }

        private static final OnPostEventListener NO_OP;

        static {
            FragmentTransactionCallback.NO_OP = new OnPostEventListener() {
                @Override  // androidx.viewpager2.adapter.FragmentStateAdapter$FragmentTransactionCallback$OnPostEventListener
                public void onPost() {
                }
            };
        }

        public OnPostEventListener onFragmentMaxLifecyclePreUpdated(Fragment fragment0, State lifecycle$State0) {
            return FragmentTransactionCallback.NO_OP;
        }

        public OnPostEventListener onFragmentPreAdded(Fragment fragment0) {
            return FragmentTransactionCallback.NO_OP;
        }

        public OnPostEventListener onFragmentPreRemoved(Fragment fragment0) {
            return FragmentTransactionCallback.NO_OP;
        }

        public OnPostEventListener onFragmentPreSavedInstanceState(Fragment fragment0) {
            return FragmentTransactionCallback.NO_OP;
        }
    }

    private static final long GRACE_WINDOW_TIME_MS = 10000L;
    private static final String KEY_PREFIX_FRAGMENT = "f#";
    private static final String KEY_PREFIX_STATE = "s#";
    FragmentEventDispatcher mFragmentEventDispatcher;
    final FragmentManager mFragmentManager;
    private FragmentMaxLifecycleEnforcer mFragmentMaxLifecycleEnforcer;
    final LongSparseArray mFragments;
    private boolean mHasStaleFragments;
    boolean mIsInGracePeriod;
    private final LongSparseArray mItemIdToViewHolder;
    final Lifecycle mLifecycle;
    private final LongSparseArray mSavedStates;

    public FragmentStateAdapter(Fragment fragment0) {
        this(fragment0.getChildFragmentManager(), fragment0.getLifecycle());
    }

    public FragmentStateAdapter(FragmentActivity fragmentActivity0) {
        this(fragmentActivity0.getSupportFragmentManager(), fragmentActivity0.getLifecycle());
    }

    public FragmentStateAdapter(FragmentManager fragmentManager0, Lifecycle lifecycle0) {
        this.mFragments = new LongSparseArray();
        this.mSavedStates = new LongSparseArray();
        this.mItemIdToViewHolder = new LongSparseArray();
        this.mFragmentEventDispatcher = new FragmentEventDispatcher();
        this.mIsInGracePeriod = false;
        this.mHasStaleFragments = false;
        this.mFragmentManager = fragmentManager0;
        this.mLifecycle = lifecycle0;
        super.setHasStableIds(true);
    }

    void addViewToContainer(View view0, FrameLayout frameLayout0) {
        if(frameLayout0.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if(view0.getParent() == frameLayout0) {
            return;
        }
        if(frameLayout0.getChildCount() > 0) {
            frameLayout0.removeAllViews();
        }
        if(view0.getParent() != null) {
            ((ViewGroup)view0.getParent()).removeView(view0);
        }
        frameLayout0.addView(view0);
    }

    public boolean containsItem(long v) {
        return v >= 0L && v < ((long)this.getItemCount());
    }

    public abstract Fragment createFragment(int arg1);

    private static String createKey(String s, long v) [...] // Inlined contents

    private void ensureFragment(int v) {
        if(!this.mFragments.containsKey(((long)v))) {
            Fragment fragment0 = this.createFragment(v);
            fragment0.setInitialSavedState(((SavedState)this.mSavedStates.get(((long)v))));
            this.mFragments.put(((long)v), fragment0);
        }
    }

    void gcFragments() {
        if(this.mHasStaleFragments && !this.shouldDelayFragmentTransactions()) {
            ArraySet arraySet0 = new ArraySet();
            for(int v1 = 0; v1 < this.mFragments.size(); ++v1) {
                long v2 = this.mFragments.keyAt(v1);
                if(!this.containsItem(v2)) {
                    arraySet0.add(v2);
                    this.mItemIdToViewHolder.remove(v2);
                }
            }
            if(!this.mIsInGracePeriod) {
                this.mHasStaleFragments = false;
                for(int v = 0; v < this.mFragments.size(); ++v) {
                    long v3 = this.mFragments.keyAt(v);
                    if(!this.isFragmentViewBound(v3)) {
                        arraySet0.add(v3);
                    }
                }
            }
            for(Object object0: arraySet0) {
                this.removeFragment(((long)(((Long)object0))));
            }
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public long getItemId(int v) [...] // Inlined contents

    private boolean isFragmentViewBound(long v) {
        if(this.mItemIdToViewHolder.containsKey(v)) {
            return true;
        }
        Fragment fragment0 = (Fragment)this.mFragments.get(v);
        if(fragment0 == null) {
            return false;
        }
        View view0 = fragment0.getView();
        return view0 == null ? false : view0.getParent() != null;
    }

    private static boolean isValidKey(String s, String s1) {
        return s.startsWith(s1) && s.length() > s1.length();
    }

    private Long itemForViewHolder(int v) {
        Long long0 = null;
        for(int v1 = 0; v1 < this.mItemIdToViewHolder.size(); ++v1) {
            if(((int)(((Integer)this.mItemIdToViewHolder.valueAt(v1)))) == v) {
                if(long0 != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                long0 = this.mItemIdToViewHolder.keyAt(v1);
                continue;
            }
        }
        return long0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView0) {
        Preconditions.checkArgument(this.mFragmentMaxLifecycleEnforcer == null);
        FragmentMaxLifecycleEnforcer fragmentStateAdapter$FragmentMaxLifecycleEnforcer0 = new FragmentMaxLifecycleEnforcer(this);
        this.mFragmentMaxLifecycleEnforcer = fragmentStateAdapter$FragmentMaxLifecycleEnforcer0;
        fragmentStateAdapter$FragmentMaxLifecycleEnforcer0.register(recyclerView0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
        this.onBindViewHolder(((FragmentViewHolder)recyclerView$ViewHolder0), v);
    }

    public final void onBindViewHolder(FragmentViewHolder fragmentViewHolder0, int v) {
        long v1 = fragmentViewHolder0.getItemId();
        int v2 = fragmentViewHolder0.getContainer().getId();
        Long long0 = this.itemForViewHolder(v2);
        if(long0 != null && ((long)long0) != v1) {
            this.removeFragment(((long)long0));
            this.mItemIdToViewHolder.remove(((long)long0));
        }
        this.mItemIdToViewHolder.put(v1, v2);
        this.ensureFragment(v);
        if(ViewCompat.isAttachedToWindow(fragmentViewHolder0.getContainer())) {
            this.placeFragmentInViewHolder(fragmentViewHolder0);
        }
        this.gcFragments();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.onCreateViewHolder(viewGroup0, v);
    }

    public final FragmentViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return FragmentViewHolder.create(viewGroup0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView0) {
        this.mFragmentMaxLifecycleEnforcer.unregister(recyclerView0);
        this.mFragmentMaxLifecycleEnforcer = null;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public boolean onFailedToRecycleView(ViewHolder recyclerView$ViewHolder0) {
        return this.onFailedToRecycleView(((FragmentViewHolder)recyclerView$ViewHolder0));
    }

    public final boolean onFailedToRecycleView(FragmentViewHolder fragmentViewHolder0) {
        return true;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onViewAttachedToWindow(ViewHolder recyclerView$ViewHolder0) {
        this.onViewAttachedToWindow(((FragmentViewHolder)recyclerView$ViewHolder0));
    }

    public final void onViewAttachedToWindow(FragmentViewHolder fragmentViewHolder0) {
        this.placeFragmentInViewHolder(fragmentViewHolder0);
        this.gcFragments();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onViewRecycled(ViewHolder recyclerView$ViewHolder0) {
        this.onViewRecycled(((FragmentViewHolder)recyclerView$ViewHolder0));
    }

    public final void onViewRecycled(FragmentViewHolder fragmentViewHolder0) {
        Long long0 = this.itemForViewHolder(fragmentViewHolder0.getContainer().getId());
        if(long0 != null) {
            this.removeFragment(((long)long0));
            this.mItemIdToViewHolder.remove(((long)long0));
        }
    }

    private static long parseIdFromKey(String s, String s1) {
        return Long.parseLong(s.substring(s1.length()));
    }

    void placeFragmentInViewHolder(FragmentViewHolder fragmentViewHolder0) {
        long v = fragmentViewHolder0.getItemId();
        Fragment fragment0 = (Fragment)this.mFragments.get(v);
        if(fragment0 == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout frameLayout0 = fragmentViewHolder0.getContainer();
        View view0 = fragment0.getView();
        if(!fragment0.isAdded() && view0 != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if(fragment0.isAdded() && view0 == null) {
            this.scheduleViewAttach(fragment0, frameLayout0);
            return;
        }
        if(fragment0.isAdded() && view0.getParent() != null) {
            if(view0.getParent() == frameLayout0) {
                return;
            }
            this.addViewToContainer(view0, frameLayout0);
            return;
        }
        if(fragment0.isAdded()) {
            this.addViewToContainer(view0, frameLayout0);
            return;
        }
        if(!this.shouldDelayFragmentTransactions()) {
            this.scheduleViewAttach(fragment0, frameLayout0);
            List list0 = this.mFragmentEventDispatcher.dispatchPreAdded(fragment0);
            try {
                fragment0.setMenuVisibility(false);
                this.mFragmentManager.beginTransaction().add(fragment0, "f" + fragmentViewHolder0.getItemId()).setMaxLifecycle(fragment0, State.STARTED).commitNow();
                this.mFragmentMaxLifecycleEnforcer.updateFragmentMaxLifecycle(false);
            }
            finally {
                this.mFragmentEventDispatcher.dispatchPostEvents(list0);
            }
            return;
        }
        if(this.mFragmentManager.isDestroyed()) {
            return;
        }
        androidx.viewpager2.adapter.FragmentStateAdapter.1 fragmentStateAdapter$10 = new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                if(!FragmentStateAdapter.this.shouldDelayFragmentTransactions()) {
                    lifecycleOwner0.getLifecycle().removeObserver(this);
                    if(ViewCompat.isAttachedToWindow(fragmentViewHolder0.getContainer())) {
                        FragmentStateAdapter.this.placeFragmentInViewHolder(fragmentViewHolder0);
                    }
                }
            }
        };
        this.mLifecycle.addObserver(fragmentStateAdapter$10);
    }

    public void registerFragmentTransactionCallback(FragmentTransactionCallback fragmentStateAdapter$FragmentTransactionCallback0) {
        this.mFragmentEventDispatcher.registerCallback(fragmentStateAdapter$FragmentTransactionCallback0);
    }

    private void removeFragment(long v) {
        Fragment fragment0 = (Fragment)this.mFragments.get(v);
        if(fragment0 == null) {
            return;
        }
        if(fragment0.getView() != null) {
            ViewParent viewParent0 = fragment0.getView().getParent();
            if(viewParent0 != null) {
                ((FrameLayout)viewParent0).removeAllViews();
            }
        }
        if(!this.containsItem(v)) {
            this.mSavedStates.remove(v);
        }
        if(!fragment0.isAdded()) {
            this.mFragments.remove(v);
            return;
        }
        if(this.shouldDelayFragmentTransactions()) {
            this.mHasStaleFragments = true;
            return;
        }
        if(fragment0.isAdded() && this.containsItem(v)) {
            List list0 = this.mFragmentEventDispatcher.dispatchPreSavedInstanceState(fragment0);
            SavedState fragment$SavedState0 = this.mFragmentManager.saveFragmentInstanceState(fragment0);
            this.mFragmentEventDispatcher.dispatchPostEvents(list0);
            this.mSavedStates.put(v, fragment$SavedState0);
        }
        List list1 = this.mFragmentEventDispatcher.dispatchPreRemoved(fragment0);
        try {
            this.mFragmentManager.beginTransaction().remove(fragment0).commitNow();
            this.mFragments.remove(v);
        }
        finally {
            this.mFragmentEventDispatcher.dispatchPostEvents(list1);
        }
    }

    @Override  // androidx.viewpager2.adapter.StatefulAdapter
    public final void restoreState(Parcelable parcelable0) {
        if(!this.mSavedStates.isEmpty() || !this.mFragments.isEmpty()) {
            throw new IllegalStateException("Expected the adapter to be \'fresh\' while restoring state.");
        }
        if(((Bundle)parcelable0).getClassLoader() == null) {
            ((Bundle)parcelable0).setClassLoader(this.getClass().getClassLoader());
        }
        for(Object object0: ((Bundle)parcelable0).keySet()) {
            String s = (String)object0;
            if(FragmentStateAdapter.isValidKey(s, "f#")) {
                long v = FragmentStateAdapter.parseIdFromKey(s, "f#");
                Fragment fragment0 = this.mFragmentManager.getFragment(((Bundle)parcelable0), s);
                this.mFragments.put(v, fragment0);
            }
            else {
                if(!FragmentStateAdapter.isValidKey(s, "s#")) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + s);
                }
                long v1 = FragmentStateAdapter.parseIdFromKey(s, "s#");
                SavedState fragment$SavedState0 = (SavedState)((Bundle)parcelable0).getParcelable(s);
                if(this.containsItem(v1)) {
                    this.mSavedStates.put(v1, fragment$SavedState0);
                }
            }
        }
        if(!this.mFragments.isEmpty()) {
            this.mHasStaleFragments = true;
            this.mIsInGracePeriod = true;
            this.gcFragments();
            this.scheduleGracePeriodEnd();
        }
    }

    @Override  // androidx.viewpager2.adapter.StatefulAdapter
    public final Parcelable saveState() {
        Parcelable parcelable0 = new Bundle(this.mFragments.size() + this.mSavedStates.size());
        for(int v1 = 0; v1 < this.mFragments.size(); ++v1) {
            long v2 = this.mFragments.keyAt(v1);
            Fragment fragment0 = (Fragment)this.mFragments.get(v2);
            if(fragment0 != null && fragment0.isAdded()) {
                this.mFragmentManager.putFragment(((Bundle)parcelable0), "f#" + v2, fragment0);
            }
        }
        for(int v = 0; v < this.mSavedStates.size(); ++v) {
            long v3 = this.mSavedStates.keyAt(v);
            if(this.containsItem(v3)) {
                ((Bundle)parcelable0).putParcelable("s#" + v3, ((Parcelable)this.mSavedStates.get(v3)));
            }
        }
        return parcelable0;
    }

    private void scheduleGracePeriodEnd() {
        Handler handler0 = new Handler(Looper.getMainLooper());
        androidx.viewpager2.adapter.FragmentStateAdapter.3 fragmentStateAdapter$30 = new Runnable() {
            @Override
            public void run() {
                FragmentStateAdapter.this.mIsInGracePeriod = false;
                FragmentStateAdapter.this.gcFragments();
            }
        };
        androidx.viewpager2.adapter.FragmentStateAdapter.4 fragmentStateAdapter$40 = new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                if(lifecycle$Event0 == Event.ON_DESTROY) {
                    handler0.removeCallbacks(fragmentStateAdapter$30);
                    lifecycleOwner0.getLifecycle().removeObserver(this);
                }
            }
        };
        this.mLifecycle.addObserver(fragmentStateAdapter$40);
        handler0.postDelayed(fragmentStateAdapter$30, 10000L);
    }

    private void scheduleViewAttach(Fragment fragment0, FrameLayout frameLayout0) {
        androidx.viewpager2.adapter.FragmentStateAdapter.2 fragmentStateAdapter$20 = new FragmentLifecycleCallbacks() {
            @Override  // androidx.fragment.app.FragmentManager$FragmentLifecycleCallbacks
            public void onFragmentViewCreated(FragmentManager fragmentManager0, Fragment fragment0, View view0, Bundle bundle0) {
                if(fragment0 == fragment0) {
                    fragmentManager0.unregisterFragmentLifecycleCallbacks(this);
                    FragmentStateAdapter.this.addViewToContainer(view0, frameLayout0);
                }
            }
        };
        this.mFragmentManager.registerFragmentLifecycleCallbacks(fragmentStateAdapter$20, false);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }

    boolean shouldDelayFragmentTransactions() {
        return this.mFragmentManager.isStateSaved();
    }

    public void unregisterFragmentTransactionCallback(FragmentTransactionCallback fragmentStateAdapter$FragmentTransactionCallback0) {
        this.mFragmentEventDispatcher.unregisterCallback(fragmentStateAdapter$FragmentTransactionCallback0);
    }
}

