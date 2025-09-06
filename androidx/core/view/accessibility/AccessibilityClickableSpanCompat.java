package androidx.core.view.accessibility;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

public final class AccessibilityClickableSpanCompat extends ClickableSpan {
    public static final String SPAN_ID = "ACCESSIBILITY_CLICKABLE_SPAN_ID";
    private final int mClickableSpanActionId;
    private final AccessibilityNodeInfoCompat mNodeInfoCompat;
    private final int mOriginalClickableSpanId;

    public AccessibilityClickableSpanCompat(int v, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0, int v1) {
        this.mOriginalClickableSpanId = v;
        this.mNodeInfoCompat = accessibilityNodeInfoCompat0;
        this.mClickableSpanActionId = v1;
    }

    @Override  // android.text.style.ClickableSpan
    public void onClick(View view0) {
        Bundle bundle0 = new Bundle();
        bundle0.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.mOriginalClickableSpanId);
        this.mNodeInfoCompat.performAction(this.mClickableSpanActionId, bundle0);
    }
}

