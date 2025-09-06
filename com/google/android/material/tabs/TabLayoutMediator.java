package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import androidx.viewpager2.widget.ViewPager2;
import java.lang.ref.WeakReference;

public final class TabLayoutMediator {
    class PagerAdapterObserver extends AdapterDataObserver {
        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeChanged(int v, int v1) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeChanged(int v, int v1, Object object0) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeInserted(int v, int v1) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeMoved(int v, int v1, int v2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeRemoved(int v, int v1) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }
    }

    public interface TabConfigurationStrategy {
        void onConfigureTab(Tab arg1, int arg2);
    }

    static class TabLayoutOnPageChangeCallback extends OnPageChangeCallback {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference tabLayoutRef;

        TabLayoutOnPageChangeCallback(TabLayout tabLayout0) {
            this.tabLayoutRef = new WeakReference(tabLayout0);
            this.reset();
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
        public void onPageScrollStateChanged(int v) {
            this.previousScrollState = this.scrollState;
            this.scrollState = v;
            TabLayout tabLayout0 = (TabLayout)this.tabLayoutRef.get();
            if(tabLayout0 != null) {
                tabLayout0.updateViewPagerScrollState(this.scrollState);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
        public void onPageScrolled(int v, float f, int v1) {
            Object object0 = this.tabLayoutRef.get();
            if(((TabLayout)object0) != null) {
                ((TabLayout)object0).setScrollPosition(v, f, this.scrollState != 2 || this.previousScrollState == 1, this.scrollState != 2 || this.previousScrollState != 0, false);
            }
        }

        @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
        public void onPageSelected(int v) {
            TabLayout tabLayout0 = (TabLayout)this.tabLayoutRef.get();
            if(tabLayout0 != null && tabLayout0.getSelectedTabPosition() != v && v < tabLayout0.getTabCount()) {
                boolean z = this.scrollState == 0 || this.scrollState == 2 && this.previousScrollState == 0;
                tabLayout0.selectTab(tabLayout0.getTabAt(v), z);
            }
        }

        void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final boolean smoothScroll;
        private final ViewPager2 viewPager;

        ViewPagerOnTabSelectedListener(ViewPager2 viewPager20, boolean z) {
            this.viewPager = viewPager20;
            this.smoothScroll = z;
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabReselected(Tab tabLayout$Tab0) {
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabSelected(Tab tabLayout$Tab0) {
            this.viewPager.setCurrentItem(tabLayout$Tab0.getPosition(), this.smoothScroll);
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabUnselected(Tab tabLayout$Tab0) {
        }
    }

    private Adapter adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private OnTabSelectedListener onTabSelectedListener;
    private AdapterDataObserver pagerAdapterObserver;
    private final boolean smoothScroll;
    private final TabConfigurationStrategy tabConfigurationStrategy;
    private final TabLayout tabLayout;
    private final ViewPager2 viewPager;

    public TabLayoutMediator(TabLayout tabLayout0, ViewPager2 viewPager20, TabConfigurationStrategy tabLayoutMediator$TabConfigurationStrategy0) {
        this(tabLayout0, viewPager20, true, tabLayoutMediator$TabConfigurationStrategy0);
    }

    public TabLayoutMediator(TabLayout tabLayout0, ViewPager2 viewPager20, boolean z, TabConfigurationStrategy tabLayoutMediator$TabConfigurationStrategy0) {
        this(tabLayout0, viewPager20, z, true, tabLayoutMediator$TabConfigurationStrategy0);
    }

    public TabLayoutMediator(TabLayout tabLayout0, ViewPager2 viewPager20, boolean z, boolean z1, TabConfigurationStrategy tabLayoutMediator$TabConfigurationStrategy0) {
        this.tabLayout = tabLayout0;
        this.viewPager = viewPager20;
        this.autoRefresh = z;
        this.smoothScroll = z1;
        this.tabConfigurationStrategy = tabLayoutMediator$TabConfigurationStrategy0;
    }

    public void attach() {
        if(this.attached) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        Adapter recyclerView$Adapter0 = this.viewPager.getAdapter();
        this.adapter = recyclerView$Adapter0;
        if(recyclerView$Adapter0 == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.attached = true;
        TabLayoutOnPageChangeCallback tabLayoutMediator$TabLayoutOnPageChangeCallback0 = new TabLayoutOnPageChangeCallback(this.tabLayout);
        this.onPageChangeCallback = tabLayoutMediator$TabLayoutOnPageChangeCallback0;
        this.viewPager.registerOnPageChangeCallback(tabLayoutMediator$TabLayoutOnPageChangeCallback0);
        ViewPagerOnTabSelectedListener tabLayoutMediator$ViewPagerOnTabSelectedListener0 = new ViewPagerOnTabSelectedListener(this.viewPager, this.smoothScroll);
        this.onTabSelectedListener = tabLayoutMediator$ViewPagerOnTabSelectedListener0;
        this.tabLayout.addOnTabSelectedListener(tabLayoutMediator$ViewPagerOnTabSelectedListener0);
        if(this.autoRefresh) {
            PagerAdapterObserver tabLayoutMediator$PagerAdapterObserver0 = new PagerAdapterObserver(this);
            this.pagerAdapterObserver = tabLayoutMediator$PagerAdapterObserver0;
            this.adapter.registerAdapterDataObserver(tabLayoutMediator$PagerAdapterObserver0);
        }
        this.populateTabsFromPagerAdapter();
        this.tabLayout.setScrollPosition(this.viewPager.getCurrentItem(), 0.0f, true);
    }

    public void detach() {
        if(this.autoRefresh) {
            Adapter recyclerView$Adapter0 = this.adapter;
            if(recyclerView$Adapter0 != null) {
                recyclerView$Adapter0.unregisterAdapterDataObserver(this.pagerAdapterObserver);
                this.pagerAdapterObserver = null;
            }
        }
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        this.viewPager.unregisterOnPageChangeCallback(this.onPageChangeCallback);
        this.onTabSelectedListener = null;
        this.onPageChangeCallback = null;
        this.adapter = null;
        this.attached = false;
    }

    public boolean isAttached() {
        return this.attached;
    }

    void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        Adapter recyclerView$Adapter0 = this.adapter;
        if(recyclerView$Adapter0 != null) {
            int v = recyclerView$Adapter0.getItemCount();
            for(int v1 = 0; v1 < v; ++v1) {
                Tab tabLayout$Tab0 = this.tabLayout.newTab();
                this.tabConfigurationStrategy.onConfigureTab(tabLayout$Tab0, v1);
                this.tabLayout.addTab(tabLayout$Tab0, false);
            }
            if(v > 0) {
                int v2 = this.tabLayout.getTabCount();
                int v3 = Math.min(this.viewPager.getCurrentItem(), v2 - 1);
                if(v3 != this.tabLayout.getSelectedTabPosition()) {
                    Tab tabLayout$Tab1 = this.tabLayout.getTabAt(v3);
                    this.tabLayout.selectTab(tabLayout$Tab1);
                }
            }
        }
    }
}

