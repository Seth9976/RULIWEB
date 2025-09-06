package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

@Deprecated
public class PreferenceRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    final AccessibilityDelegateCompat mDefaultItemDelegate;
    final AccessibilityDelegateCompat mItemDelegate;
    final RecyclerView mRecyclerView;

    public PreferenceRecyclerViewAccessibilityDelegate(RecyclerView recyclerView0) {
        super(recyclerView0);
        this.mDefaultItemDelegate = super.getItemDelegate();
        this.mItemDelegate = new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                PreferenceRecyclerViewAccessibilityDelegate.this.mDefaultItemDelegate.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                int v = PreferenceRecyclerViewAccessibilityDelegate.this.mRecyclerView.getChildAdapterPosition(view0);
                Adapter recyclerView$Adapter0 = PreferenceRecyclerViewAccessibilityDelegate.this.mRecyclerView.getAdapter();
                if(!(recyclerView$Adapter0 instanceof PreferenceGroupAdapter) || ((PreferenceGroupAdapter)recyclerView$Adapter0).getItem(v) == null) {
                }
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                return PreferenceRecyclerViewAccessibilityDelegate.this.mDefaultItemDelegate.performAccessibilityAction(view0, v, bundle0);
            }
        };
        this.mRecyclerView = recyclerView0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }
}

