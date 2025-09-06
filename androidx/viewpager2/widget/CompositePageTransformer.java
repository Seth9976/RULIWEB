package androidx.viewpager2.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public final class CompositePageTransformer implements PageTransformer {
    private final List mTransformers;

    public CompositePageTransformer() {
        this.mTransformers = new ArrayList();
    }

    public void addTransformer(PageTransformer viewPager2$PageTransformer0) {
        this.mTransformers.add(viewPager2$PageTransformer0);
    }

    public void removeTransformer(PageTransformer viewPager2$PageTransformer0) {
        this.mTransformers.remove(viewPager2$PageTransformer0);
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$PageTransformer
    public void transformPage(View view0, float f) {
        for(Object object0: this.mTransformers) {
            ((PageTransformer)object0).transformPage(view0, f);
        }
    }
}

