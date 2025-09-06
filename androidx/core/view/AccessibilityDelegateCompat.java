package androidx.core.view;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View.AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R.id;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class AccessibilityDelegateCompat {
    static final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {
        final AccessibilityDelegateCompat mCompat;

        AccessibilityDelegateAdapter(AccessibilityDelegateCompat accessibilityDelegateCompat0) {
            this.mCompat = accessibilityDelegateCompat0;
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            return this.mCompat.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view0) {
            AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0 = this.mCompat.getAccessibilityNodeProvider(view0);
            return accessibilityNodeProviderCompat0 == null ? null : ((AccessibilityNodeProvider)accessibilityNodeProviderCompat0.getProvider());
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfo accessibilityNodeInfo0) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            accessibilityNodeInfoCompat0.setScreenReaderFocusable(ViewCompat.isScreenReaderFocusable(view0));
            accessibilityNodeInfoCompat0.setHeading(ViewCompat.isAccessibilityHeading(view0));
            accessibilityNodeInfoCompat0.setPaneTitle(ViewCompat.getAccessibilityPaneTitle(view0));
            accessibilityNodeInfoCompat0.setStateDescription(ViewCompat.getStateDescription(view0));
            this.mCompat.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            accessibilityNodeInfoCompat0.addSpansToExtras(accessibilityNodeInfo0.getText(), view0);
            List list0 = AccessibilityDelegateCompat.getActionList(view0);
            for(int v = 0; v < list0.size(); ++v) {
                accessibilityNodeInfoCompat0.addAction(((AccessibilityActionCompat)list0.get(v)));
            }
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            return this.mCompat.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
            return this.mCompat.performAccessibilityAction(view0, v, bundle0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void sendAccessibilityEvent(View view0, int v) {
            this.mCompat.sendAccessibilityEvent(view0, v);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
        }
    }

    private static final View.AccessibilityDelegate DEFAULT_DELEGATE;
    private final View.AccessibilityDelegate mBridge;
    private final View.AccessibilityDelegate mOriginalDelegate;

    static {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    }

    public AccessibilityDelegateCompat() {
        this(AccessibilityDelegateCompat.DEFAULT_DELEGATE);
    }

    public AccessibilityDelegateCompat(View.AccessibilityDelegate view$AccessibilityDelegate0) {
        this.mOriginalDelegate = view$AccessibilityDelegate0;
        this.mBridge = new AccessibilityDelegateAdapter(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view0) {
        AccessibilityNodeProvider accessibilityNodeProvider0 = this.mOriginalDelegate.getAccessibilityNodeProvider(view0);
        return accessibilityNodeProvider0 == null ? null : new AccessibilityNodeProviderCompat(accessibilityNodeProvider0);
    }

    static List getActionList(View view0) {
        List list0 = (List)view0.getTag(id.tag_accessibility_actions);
        return list0 == null ? Collections.EMPTY_LIST : list0;
    }

    View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    private boolean isSpanStillValid(ClickableSpan clickableSpan0, View view0) {
        if(clickableSpan0 != null) {
            ClickableSpan[] arr_clickableSpan = AccessibilityNodeInfoCompat.getClickableSpans(view0.createAccessibilityNodeInfo().getText());
            for(int v = 0; arr_clickableSpan != null && v < arr_clickableSpan.length; ++v) {
                if(clickableSpan0.equals(arr_clickableSpan[v])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
    }

    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        AccessibilityNodeInfo accessibilityNodeInfo0 = accessibilityNodeInfoCompat0.unwrap();
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfo0);
    }

    public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        this.mOriginalDelegate.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
        return this.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
    }

    public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
        List list0 = AccessibilityDelegateCompat.getActionList(view0);
        boolean z = false;
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0 = (AccessibilityActionCompat)list0.get(v1);
            if(accessibilityNodeInfoCompat$AccessibilityActionCompat0.getId() == v) {
                z = accessibilityNodeInfoCompat$AccessibilityActionCompat0.perform(view0, bundle0);
                break;
            }
        }
        if(!z) {
            z = this.mOriginalDelegate.performAccessibilityAction(view0, v, bundle0);
        }
        return z || v != id.accessibility_action_clickable_span || bundle0 == null ? z : this.performClickableSpanAction(bundle0.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view0);
    }

    private boolean performClickableSpanAction(int v, View view0) {
        SparseArray sparseArray0 = (SparseArray)view0.getTag(id.tag_accessibility_clickable_spans);
        if(sparseArray0 != null) {
            WeakReference weakReference0 = (WeakReference)sparseArray0.get(v);
            if(weakReference0 != null) {
                ClickableSpan clickableSpan0 = (ClickableSpan)weakReference0.get();
                if(this.isSpanStillValid(clickableSpan0, view0)) {
                    clickableSpan0.onClick(view0);
                    return true;
                }
            }
        }
        return false;
    }

    public void sendAccessibilityEvent(View view0, int v) {
        this.mOriginalDelegate.sendAccessibilityEvent(view0, v);
    }

    public void sendAccessibilityEventUnchecked(View view0, AccessibilityEvent accessibilityEvent0) {
        this.mOriginalDelegate.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
    }
}

