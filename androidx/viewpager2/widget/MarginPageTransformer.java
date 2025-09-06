package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewParent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

public final class MarginPageTransformer implements PageTransformer {
    private final int mMarginPx;

    public MarginPageTransformer(int v) {
        Preconditions.checkArgumentNonnegative(v, "Margin must be non-negative");
        this.mMarginPx = v;
    }

    private ViewPager2 requireViewPager(View view0) {
        ViewParent viewParent0 = view0.getParent();
        ViewParent viewParent1 = viewParent0.getParent();
        if(!(viewParent0 instanceof RecyclerView) || !(viewParent1 instanceof ViewPager2)) {
            throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
        }
        return (ViewPager2)viewParent1;
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$PageTransformer
    public void transformPage(View view0, float f) {
        ViewPager2 viewPager20 = this.requireViewPager(view0);
        float f1 = ((float)this.mMarginPx) * f;
        if(viewPager20.getOrientation() == 0) {
            if(viewPager20.isRtl()) {
                f1 = -f1;
            }
            view0.setTranslationX(f1);
            return;
        }
        view0.setTranslationY(f1);
    }
}

