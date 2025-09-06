package androidx.viewpager2.widget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

final class CompositeOnPageChangeCallback extends OnPageChangeCallback {
    private final List mCallbacks;

    CompositeOnPageChangeCallback(int v) {
        this.mCallbacks = new ArrayList(v);
    }

    void addOnPageChangeCallback(OnPageChangeCallback viewPager2$OnPageChangeCallback0) {
        this.mCallbacks.add(viewPager2$OnPageChangeCallback0);
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageScrollStateChanged(int v) {
        try {
            for(Object object0: this.mCallbacks) {
                ((OnPageChangeCallback)object0).onPageScrollStateChanged(v);
            }
        }
        catch(ConcurrentModificationException concurrentModificationException0) {
            this.throwCallbackListModifiedWhileInUse(concurrentModificationException0);
        }
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageScrolled(int v, float f, int v1) {
        try {
            for(Object object0: this.mCallbacks) {
                ((OnPageChangeCallback)object0).onPageScrolled(v, f, v1);
            }
        }
        catch(ConcurrentModificationException concurrentModificationException0) {
            this.throwCallbackListModifiedWhileInUse(concurrentModificationException0);
        }
    }

    @Override  // androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback
    public void onPageSelected(int v) {
        try {
            for(Object object0: this.mCallbacks) {
                ((OnPageChangeCallback)object0).onPageSelected(v);
            }
        }
        catch(ConcurrentModificationException concurrentModificationException0) {
            this.throwCallbackListModifiedWhileInUse(concurrentModificationException0);
        }
    }

    void removeOnPageChangeCallback(OnPageChangeCallback viewPager2$OnPageChangeCallback0) {
        this.mCallbacks.remove(viewPager2$OnPageChangeCallback0);
    }

    private void throwCallbackListModifiedWhileInUse(ConcurrentModificationException concurrentModificationException0) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException0);
    }
}

