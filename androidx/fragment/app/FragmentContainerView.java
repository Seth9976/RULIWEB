package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.R.styleable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001<B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004B#\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b\u00A2\u0006\u0002\u0010\tB\u001F\b\u0010\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\n\u001A\u00020\u000B\u00A2\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0011H\u0002J\"\u0010\u0018\u001A\u00020\u00162\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u001A\u001A\u00020\b2\b\u0010\u001B\u001A\u0004\u0018\u00010\u001CH\u0016J\u0010\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u001EH\u0017J\u0010\u0010 \u001A\u00020\u00162\u0006\u0010!\u001A\u00020\"H\u0014J \u0010#\u001A\u00020\u00132\u0006\u0010!\u001A\u00020\"2\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010$\u001A\u00020%H\u0014J\u0010\u0010&\u001A\u00020\u00162\u0006\u0010\'\u001A\u00020\u0011H\u0016J\u0017\u0010(\u001A\u0002H)\"\n\b\u0000\u0010)*\u0004\u0018\u00010*\u00A2\u0006\u0002\u0010+J\u0010\u0010,\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u001EH\u0017J\b\u0010-\u001A\u00020\u0016H\u0016J\u0010\u0010.\u001A\u00020\u00162\u0006\u0010\'\u001A\u00020\u0011H\u0016J\u0010\u0010/\u001A\u00020\u00162\u0006\u0010\u001A\u001A\u00020\bH\u0016J\u0010\u00100\u001A\u00020\u00162\u0006\u0010\'\u001A\u00020\u0011H\u0016J\u0018\u00101\u001A\u00020\u00162\u0006\u00102\u001A\u00020\b2\u0006\u00103\u001A\u00020\bH\u0016J\u0018\u00104\u001A\u00020\u00162\u0006\u00102\u001A\u00020\b2\u0006\u00103\u001A\u00020\bH\u0016J\u0010\u00105\u001A\u00020\u00162\u0006\u0010\u0012\u001A\u00020\u0013H\u0001J\u0012\u00106\u001A\u00020\u00162\b\u00107\u001A\u0004\u0018\u000108H\u0016J\u0010\u00109\u001A\u00020\u00162\u0006\u0010:\u001A\u00020\u000EH\u0016J\u0010\u0010;\u001A\u00020\u00162\u0006\u0010\'\u001A\u00020\u0011H\u0016R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006="}, d2 = {"Landroidx/fragment/app/FragmentContainerView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "fm", "Landroidx/fragment/app/FragmentManager;", "(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/fragment/app/FragmentManager;)V", "applyWindowInsetsListener", "Landroid/view/View$OnApplyWindowInsetsListener;", "disappearingFragmentChildren", "", "Landroid/view/View;", "drawDisappearingViewsFirst", "", "transitioningFragmentViews", "addDisappearingFragmentView", "", "v", "addView", "child", "index", "params", "Landroid/view/ViewGroup$LayoutParams;", "dispatchApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "drawChild", "drawingTime", "", "endViewTransition", "view", "getFragment", "F", "Landroidx/fragment/app/Fragment;", "()Landroidx/fragment/app/Fragment;", "onApplyWindowInsets", "removeAllViewsInLayout", "removeView", "removeViewAt", "removeViewInLayout", "removeViews", "start", "count", "removeViewsInLayout", "setDrawDisappearingViewsLast", "setLayoutTransition", "transition", "Landroid/animation/LayoutTransition;", "setOnApplyWindowInsetsListener", "listener", "startViewTransition", "Api20Impl", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class FragmentContainerView extends FrameLayout {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004¨\u0006\n"}, d2 = {"Landroidx/fragment/app/FragmentContainerView$Api20Impl;", "", "()V", "onApplyWindowInsets", "Landroid/view/WindowInsets;", "onApplyWindowInsetsListener", "Landroid/view/View$OnApplyWindowInsetsListener;", "v", "Landroid/view/View;", "insets", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Api20Impl {
        public static final Api20Impl INSTANCE;

        static {
            Api20Impl.INSTANCE = new Api20Impl();
        }

        public final WindowInsets onApplyWindowInsets(View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0, View view0, WindowInsets windowInsets0) {
            Intrinsics.checkNotNullParameter(view$OnApplyWindowInsetsListener0, "onApplyWindowInsetsListener");
            Intrinsics.checkNotNullParameter(view0, "v");
            Intrinsics.checkNotNullParameter(windowInsets0, "insets");
            WindowInsets windowInsets1 = view$OnApplyWindowInsetsListener0.onApplyWindowInsets(view0, windowInsets0);
            Intrinsics.checkNotNullExpressionValue(windowInsets1, "onApplyWindowInsetsListe…lyWindowInsets(v, insets)");
            return windowInsets1;
        }
    }

    private View.OnApplyWindowInsetsListener applyWindowInsetsListener;
    private final List disappearingFragmentChildren;
    private boolean drawDisappearingViewsFirst;
    private final List transitioningFragmentViews;

    public FragmentContainerView(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
    }

    public FragmentContainerView(Context context0, AttributeSet attributeSet0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        this(context0, attributeSet0, 0, 4, null);
    }

    public FragmentContainerView(Context context0, AttributeSet attributeSet0, int v) {
        Intrinsics.checkNotNullParameter(context0, "context");
        String s1;
        super(context0, attributeSet0, v);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
        if(attributeSet0 != null) {
            String s = attributeSet0.getClassAttribute();
            Intrinsics.checkNotNullExpressionValue(styleable.FragmentContainerView, "FragmentContainerView");
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.FragmentContainerView, 0, 0);
            if(s == null) {
                s = typedArray0.getString(styleable.FragmentContainerView_android_name);
                s1 = "android:name";
            }
            else {
                s1 = "class";
            }
            typedArray0.recycle();
            if(s != null && !this.isInEditMode()) {
                throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + s1 + "=\"" + s + '\"');
            }
        }
    }

    public FragmentContainerView(Context context0, AttributeSet attributeSet0, int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 4) != 0) {
            v = 0;
        }
        this(context0, attributeSet0, v);
    }

    public FragmentContainerView(Context context0, AttributeSet attributeSet0, FragmentManager fragmentManager0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(attributeSet0, "attrs");
        Intrinsics.checkNotNullParameter(fragmentManager0, "fm");
        super(context0, attributeSet0);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
        String s = attributeSet0.getClassAttribute();
        Intrinsics.checkNotNullExpressionValue(styleable.FragmentContainerView, "FragmentContainerView");
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.FragmentContainerView, 0, 0);
        if(s == null) {
            s = typedArray0.getString(styleable.FragmentContainerView_android_name);
        }
        String s1 = typedArray0.getString(styleable.FragmentContainerView_android_tag);
        typedArray0.recycle();
        int v = this.getId();
        if(s != null && fragmentManager0.findFragmentById(v) == null) {
            if(v == -1) {
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + s + (s1 == null ? "" : " with tag " + s1));
            }
            Fragment fragment0 = fragmentManager0.getFragmentFactory().instantiate(context0.getClassLoader(), s);
            Intrinsics.checkNotNullExpressionValue(fragment0, "fm.fragmentFactory.insta…ontext.classLoader, name)");
            fragment0.onInflate(context0, attributeSet0, null);
            fragmentManager0.beginTransaction().setReorderingAllowed(true).add(this, fragment0, s1).commitNowAllowingStateLoss();
        }
        fragmentManager0.onContainerAvailable(this);
    }

    private final void addDisappearingFragmentView(View view0) {
        if(this.transitioningFragmentViews.contains(view0)) {
            this.disappearingFragmentChildren.add(view0);
        }
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        Intrinsics.checkNotNullParameter(view0, "child");
        if(FragmentManager.getViewFragment(view0) == null) {
            throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + view0 + " is not associated with a Fragment.").toString());
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    @Override  // android.view.ViewGroup
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets0) {
        WindowInsetsCompat windowInsetsCompat1;
        Intrinsics.checkNotNullParameter(windowInsets0, "insets");
        WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(windowInsets0);
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompat0, "toWindowInsetsCompat(insets)");
        if(this.applyWindowInsetsListener == null) {
            windowInsetsCompat1 = ViewCompat.onApplyWindowInsets(this, windowInsetsCompat0);
        }
        else {
            View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0 = this.applyWindowInsetsListener;
            Intrinsics.checkNotNull(view$OnApplyWindowInsetsListener0);
            windowInsetsCompat1 = WindowInsetsCompat.toWindowInsetsCompat(Api20Impl.INSTANCE.onApplyWindowInsets(view$OnApplyWindowInsetsListener0, this, windowInsets0));
        }
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompat1, "if (applyWindowInsetsLis…, insetsCompat)\n        }");
        if(!windowInsetsCompat1.isConsumed()) {
            int v = this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                ViewCompat.dispatchApplyWindowInsets(this.getChildAt(v1), windowInsetsCompat1);
            }
        }
        return windowInsets0;
    }

    @Override  // android.view.ViewGroup
    protected void dispatchDraw(Canvas canvas0) {
        Intrinsics.checkNotNullParameter(canvas0, "canvas");
        if(this.drawDisappearingViewsFirst) {
            for(Object object0: this.disappearingFragmentChildren) {
                super.drawChild(canvas0, ((View)object0), this.getDrawingTime());
            }
        }
        super.dispatchDraw(canvas0);
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        Intrinsics.checkNotNullParameter(canvas0, "canvas");
        Intrinsics.checkNotNullParameter(view0, "child");
        return !this.drawDisappearingViewsFirst || this.disappearingFragmentChildren.isEmpty() || !this.disappearingFragmentChildren.contains(view0) ? super.drawChild(canvas0, view0, v) : false;
    }

    @Override  // android.view.ViewGroup
    public void endViewTransition(View view0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.transitioningFragmentViews.remove(view0);
        if(this.disappearingFragmentChildren.remove(view0)) {
            this.drawDisappearingViewsFirst = true;
        }
        super.endViewTransition(view0);
    }

    public final Fragment getFragment() {
        return FragmentManager.findFragmentManager(this).findFragmentById(this.getId());
    }

    @Override  // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets0) {
        Intrinsics.checkNotNullParameter(windowInsets0, "insets");
        return windowInsets0;
    }

    @Override  // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        for(int v = this.getChildCount() - 1; -1 < v; --v) {
            View view0 = this.getChildAt(v);
            Intrinsics.checkNotNullExpressionValue(view0, "view");
            this.addDisappearingFragmentView(view0);
        }
        super.removeAllViewsInLayout();
    }

    @Override  // android.view.ViewGroup
    public void removeView(View view0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.addDisappearingFragmentView(view0);
        super.removeView(view0);
    }

    @Override  // android.view.ViewGroup
    public void removeViewAt(int v) {
        View view0 = this.getChildAt(v);
        Intrinsics.checkNotNullExpressionValue(view0, "view");
        this.addDisappearingFragmentView(view0);
        super.removeViewAt(v);
    }

    @Override  // android.view.ViewGroup
    public void removeViewInLayout(View view0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        this.addDisappearingFragmentView(view0);
        super.removeViewInLayout(view0);
    }

    @Override  // android.view.ViewGroup
    public void removeViews(int v, int v1) {
        for(int v2 = v; v2 < v + v1; ++v2) {
            View view0 = this.getChildAt(v2);
            Intrinsics.checkNotNullExpressionValue(view0, "view");
            this.addDisappearingFragmentView(view0);
        }
        super.removeViews(v, v1);
    }

    @Override  // android.view.ViewGroup
    public void removeViewsInLayout(int v, int v1) {
        for(int v2 = v; v2 < v + v1; ++v2) {
            View view0 = this.getChildAt(v2);
            Intrinsics.checkNotNullExpressionValue(view0, "view");
            this.addDisappearingFragmentView(view0);
        }
        super.removeViewsInLayout(v, v1);
    }

    public final void setDrawDisappearingViewsLast(boolean z) {
        this.drawDisappearingViewsFirst = z;
    }

    @Override  // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition0) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override  // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0) {
        Intrinsics.checkNotNullParameter(view$OnApplyWindowInsetsListener0, "listener");
        this.applyWindowInsetsListener = view$OnApplyWindowInsetsListener0;
    }

    @Override  // android.view.ViewGroup
    public void startViewTransition(View view0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        if(view0.getParent() == this) {
            this.transitioningFragmentViews.add(view0);
        }
        super.startViewTransition(view0);
    }
}

