package androidx.core.view.insets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.core.R.id;
import androidx.core.graphics.Insets;
import java.util.ArrayList;
import java.util.List;

public class ProtectionLayout extends FrameLayout {
    private static final Object PROTECTION_VIEW;
    private ProtectionGroup mGroup;
    private final List mProtections;

    static {
        ProtectionLayout.PROTECTION_VIEW = new Object();
    }

    public ProtectionLayout(Context context0) {
        super(context0);
        this.mProtections = new ArrayList();
    }

    public ProtectionLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ProtectionLayout(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public ProtectionLayout(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.mProtections = new ArrayList();
    }

    public ProtectionLayout(Context context0, List list0) {
        super(context0);
        this.mProtections = new ArrayList();
        this.setProtections(list0);
    }

    private void addProtectionView(Context context0, int v, Protection protection0) {
        int v5;
        int v4;
        Attributes protection$Attributes0 = protection0.getAttributes();
        int v1 = protection0.getSide();
        int v2 = 4;
        int v3 = -1;
        if(v1 == 1) {
            v5 = 3;
            v3 = protection$Attributes0.getWidth();
            v4 = -1;
        }
        else {
            switch(v1) {
                case 2: {
                    v4 = protection$Attributes0.getHeight();
                    v5 = 0x30;
                    break;
                }
                case 4: {
                    v5 = 5;
                    v3 = protection$Attributes0.getWidth();
                    v4 = -1;
                    break;
                }
                default: {
                    if(v1 != 8) {
                        throw new IllegalArgumentException("Unexpected side: " + protection0.getSide());
                    }
                    v4 = protection$Attributes0.getHeight();
                    v5 = 80;
                    break;
                }
            }
        }
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(v3, v4, v5);
        Insets insets0 = protection$Attributes0.getMargin();
        frameLayout$LayoutParams0.leftMargin = insets0.left;
        frameLayout$LayoutParams0.topMargin = insets0.top;
        frameLayout$LayoutParams0.rightMargin = insets0.right;
        frameLayout$LayoutParams0.bottomMargin = insets0.bottom;
        View view0 = new View(context0);
        view0.setTag(ProtectionLayout.PROTECTION_VIEW);
        view0.setTranslationX(protection$Attributes0.getTranslationX());
        view0.setTranslationY(protection$Attributes0.getTranslationY());
        view0.setAlpha(protection$Attributes0.getAlpha());
        if(protection$Attributes0.isVisible()) {
            v2 = 0;
        }
        view0.setVisibility(v2);
        view0.setBackground(protection$Attributes0.getDrawable());
        protection$Attributes0.setCallback(new Callback() {
            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onAlphaChanged(float f) {
                view0.setAlpha(f);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onDrawableChanged(Drawable drawable0) {
                view0.setBackground(drawable0);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onHeightChanged(int v) {
                frameLayout$LayoutParams0.height = v;
                view0.setLayoutParams(frameLayout$LayoutParams0);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onMarginChanged(Insets insets0) {
                frameLayout$LayoutParams0.leftMargin = insets0.left;
                frameLayout$LayoutParams0.topMargin = insets0.top;
                frameLayout$LayoutParams0.rightMargin = insets0.right;
                frameLayout$LayoutParams0.bottomMargin = insets0.bottom;
                view0.setLayoutParams(frameLayout$LayoutParams0);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onTranslationXChanged(float f) {
                view0.setTranslationX(f);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onTranslationYChanged(float f) {
                view0.setTranslationY(f);
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onVisibilityChanged(boolean z) {
                view0.setVisibility((z ? 0 : 4));
            }

            @Override  // androidx.core.view.insets.Protection$Attributes$Callback
            public void onWidthChanged(int v) {
                frameLayout$LayoutParams0.width = v;
                view0.setLayoutParams(frameLayout$LayoutParams0);
            }
        });
        this.addView(view0, v, frameLayout$LayoutParams0);
    }

    private void addProtectionViews() {
        if(!this.mProtections.isEmpty()) {
            this.mGroup = new ProtectionGroup(this.getOrInstallSystemBarStateMonitor(), this.mProtections);
            int v = this.getChildCount();
            int v1 = this.mGroup.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                Protection protection0 = this.mGroup.getProtection(v2);
                this.addProtectionView(this.getContext(), v2 + v, protection0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(view0 != null && view0.getTag() != ProtectionLayout.PROTECTION_VIEW) {
            int v1 = this.mGroup == null ? 0 : this.mGroup.size();
            int v2 = this.getChildCount() - v1;
            if(v > v2 || v < 0) {
                v = v2;
            }
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    private SystemBarStateMonitor getOrInstallSystemBarStateMonitor() {
        ViewGroup viewGroup0 = (ViewGroup)this.getRootView();
        Object object0 = viewGroup0.getTag(id.tag_system_bar_state_monitor);
        if(object0 instanceof SystemBarStateMonitor) {
            return (SystemBarStateMonitor)object0;
        }
        SystemBarStateMonitor systemBarStateMonitor0 = new SystemBarStateMonitor(viewGroup0);
        viewGroup0.setTag(id.tag_system_bar_state_monitor, systemBarStateMonitor0);
        return systemBarStateMonitor0;
    }

    private void maybeUninstallSystemBarStateMonitor() {
        ViewGroup viewGroup0 = (ViewGroup)this.getRootView();
        Object object0 = viewGroup0.getTag(id.tag_system_bar_state_monitor);
        if(!(object0 instanceof SystemBarStateMonitor) || ((SystemBarStateMonitor)object0).hasCallback()) {
            return;
        }
        ((SystemBarStateMonitor)object0).detachFromWindow();
        viewGroup0.setTag(id.tag_system_bar_state_monitor, null);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.mGroup != null) {
            this.removeProtectionViews();
        }
        this.addProtectionViews();
        this.requestApplyInsets();
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeProtectionViews();
        this.maybeUninstallSystemBarStateMonitor();
    }

    private void removeProtectionViews() {
        if(this.mGroup != null) {
            this.removeViews(this.getChildCount() - this.mGroup.size(), this.mGroup.size());
            int v = this.mGroup.size();
            for(int v1 = 0; v1 < v; ++v1) {
                this.mGroup.getProtection(v1).getAttributes().setCallback(null);
            }
            this.mGroup.dispose();
            this.mGroup = null;
        }
    }

    public void setProtections(List list0) {
        this.mProtections.clear();
        this.mProtections.addAll(list0);
        if(this.isAttachedToWindow()) {
            this.removeProtectionViews();
            this.addProtectionViews();
            this.requestApplyInsets();
        }
    }
}

