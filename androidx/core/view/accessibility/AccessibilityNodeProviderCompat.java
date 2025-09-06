package androidx.core.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProvider {
        final AccessibilityNodeProviderCompat mCompat;

        AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0) {
            this.mCompat = accessibilityNodeProviderCompat0;
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int v) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.mCompat.createAccessibilityNodeInfo(v);
            return accessibilityNodeInfoCompat0 == null ? null : accessibilityNodeInfoCompat0.unwrap();
        }

        // 去混淆评级： 低(20)
        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public List findAccessibilityNodeInfosByText(String s, int v) {
            return null;
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int v) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.mCompat.findFocus(v);
            return accessibilityNodeInfoCompat0 == null ? null : accessibilityNodeInfoCompat0.unwrap();
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int v, int v1, Bundle bundle0) {
            return this.mCompat.performAction(v, v1, bundle0);
        }
    }

    static class AccessibilityNodeProviderApi26 extends AccessibilityNodeProviderApi19 {
        AccessibilityNodeProviderApi26(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0) {
            super(accessibilityNodeProviderCompat0);
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int v, AccessibilityNodeInfo accessibilityNodeInfo0, String s, Bundle bundle0) {
            this.mCompat.addExtraDataToAccessibilityNodeInfo(v, AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0), s, bundle0);
        }
    }

    public static final int HOST_VIEW_ID = -1;
    private final Object mProvider;

    public AccessibilityNodeProviderCompat() {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mProvider = new AccessibilityNodeProviderApi26(this);
            return;
        }
        this.mProvider = new AccessibilityNodeProviderApi19(this);
    }

    public AccessibilityNodeProviderCompat(Object object0) {
        this.mProvider = object0;
    }

    public void addExtraDataToAccessibilityNodeInfo(int v, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, String s, Bundle bundle0) {
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int v) {
        return null;
    }

    public List findAccessibilityNodeInfosByText(String s, int v) [...] // Inlined contents

    public AccessibilityNodeInfoCompat findFocus(int v) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int v, int v1, Bundle bundle0) {
        return false;
    }
}

