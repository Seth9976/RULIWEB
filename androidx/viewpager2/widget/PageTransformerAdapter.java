package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Locale;

final class PageTransformerAdapter extends OnPageChangeCallback {
    private final LinearLayoutManager mLayoutManager;
    private PageTransformer mPageTransformer;

    PageTransformerAdapter(LinearLayoutManager linearLayoutManager0) {
        this.mLayoutManager = linearLayoutManager0;
    }

    PageTransformer getPageTransformer() {
        return this.mPageTransformer;
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageScrollStateChanged(int v) {
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageScrolled(int v, float f, int v1) {
        if(this.mPageTransformer != null) {
            for(int v2 = 0; v2 < this.mLayoutManager.getChildCount(); ++v2) {
                View view0 = this.mLayoutManager.getChildAt(v2);
                if(view0 == null) {
                    throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", v2, this.mLayoutManager.getChildCount()));
                }
                int v3 = this.mLayoutManager.getPosition(view0);
                this.mPageTransformer.transformPage(view0, ((float)(v3 - v)) + -f);
            }
        }
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageSelected(int v) {
    }

    void setPageTransformer(PageTransformer viewPager2$PageTransformer0) {
        this.mPageTransformer = viewPager2$PageTransformer0;
    }
}

