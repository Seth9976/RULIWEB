package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.Map;
import java.util.WeakHashMap;

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    public static class ItemDelegate extends AccessibilityDelegateCompat {
        private Map mOriginalItemDelegates;
        final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public ItemDelegate(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate0) {
            this.mOriginalItemDelegates = new WeakHashMap();
            this.mRecyclerViewDelegate = recyclerViewAccessibilityDelegate0;
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            return accessibilityDelegateCompat0 == null ? super.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0) : accessibilityDelegateCompat0.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            return accessibilityDelegateCompat0 == null ? super.getAccessibilityNodeProvider(view0) : accessibilityDelegateCompat0.getAccessibilityNodeProvider(view0);
        }

        AccessibilityDelegateCompat getAndRemoveOriginalDelegateForItem(View view0) {
            return (AccessibilityDelegateCompat)this.mOriginalItemDelegates.remove(view0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            if(accessibilityDelegateCompat0 != null) {
                accessibilityDelegateCompat0.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
                return;
            }
            super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            if(!this.mRecyclerViewDelegate.shouldIgnore() && this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() != null) {
                this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view0, accessibilityNodeInfoCompat0);
                AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
                if(accessibilityDelegateCompat0 != null) {
                    accessibilityDelegateCompat0.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                    return;
                }
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                return;
            }
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            if(accessibilityDelegateCompat0 != null) {
                accessibilityDelegateCompat0.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
                return;
            }
            super.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(viewGroup0);
            return accessibilityDelegateCompat0 == null ? super.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0) : accessibilityDelegateCompat0.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
            if(!this.mRecyclerViewDelegate.shouldIgnore() && this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() != null) {
                AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
                if(accessibilityDelegateCompat0 != null) {
                    return accessibilityDelegateCompat0.performAccessibilityAction(view0, v, bundle0) ? true : this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view0, v, bundle0);
                }
                return super.performAccessibilityAction(view0, v, bundle0) ? true : this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view0, v, bundle0);
            }
            return super.performAccessibilityAction(view0, v, bundle0);
        }

        void saveOriginalDelegate(View view0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = ViewCompat.getAccessibilityDelegate(view0);
            if(accessibilityDelegateCompat0 != null && accessibilityDelegateCompat0 != this) {
                this.mOriginalItemDelegates.put(view0, accessibilityDelegateCompat0);
            }
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void sendAccessibilityEvent(View view0, int v) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            if(accessibilityDelegateCompat0 != null) {
                accessibilityDelegateCompat0.sendAccessibilityEvent(view0, v);
                return;
            }
            super.sendAccessibilityEvent(view0, v);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void sendAccessibilityEventUnchecked(View view0, AccessibilityEvent accessibilityEvent0) {
            AccessibilityDelegateCompat accessibilityDelegateCompat0 = (AccessibilityDelegateCompat)this.mOriginalItemDelegates.get(view0);
            if(accessibilityDelegateCompat0 != null) {
                accessibilityDelegateCompat0.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
                return;
            }
            super.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
        }
    }

    private final ItemDelegate mItemDelegate;
    final RecyclerView mRecyclerView;

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerView0) {
        this.mRecyclerView = recyclerView0;
        AccessibilityDelegateCompat accessibilityDelegateCompat0 = this.getItemDelegate();
        if(accessibilityDelegateCompat0 != null && accessibilityDelegateCompat0 instanceof ItemDelegate) {
            this.mItemDelegate = (ItemDelegate)accessibilityDelegateCompat0;
            return;
        }
        this.mItemDelegate = new ItemDelegate(this);
    }

    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
        if(view0 instanceof RecyclerView && !this.shouldIgnore() && ((RecyclerView)view0).getLayoutManager() != null) {
            ((RecyclerView)view0).getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent0);
        }
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
        if(!this.shouldIgnore() && this.mRecyclerView.getLayoutManager() != null) {
            this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat0);
        }
    }

    @Override  // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
        if(super.performAccessibilityAction(view0, v, bundle0)) {
            return true;
        }
        return this.shouldIgnore() || this.mRecyclerView.getLayoutManager() == null ? false : this.mRecyclerView.getLayoutManager().performAccessibilityAction(v, bundle0);
    }

    boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}

