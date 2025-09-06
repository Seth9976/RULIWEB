package androidx.viewpager2.widget;

import android.os.Build.VERSION;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public final class WindowInsetsApplier implements OnApplyWindowInsetsListener {
    private WindowInsetsCompat consumeAllInsets(WindowInsetsCompat windowInsetsCompat0) {
        return WindowInsetsCompat.CONSUMED.toWindowInsets() == null ? windowInsetsCompat0.consumeSystemWindowInsets().consumeStableInsets() : WindowInsetsCompat.CONSUMED;
    }

    public static boolean install(ViewPager2 viewPager20) {
        viewPager20.getContext().getApplicationInfo();
        if(Build.VERSION.SDK_INT >= 30) {
            return false;
        }
        ViewCompat.setOnApplyWindowInsetsListener(viewPager20, new WindowInsetsApplier());
        return true;
    }

    @Override  // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
        WindowInsetsCompat windowInsetsCompat1 = ViewCompat.onApplyWindowInsets(((ViewPager2)view0), windowInsetsCompat0);
        if(windowInsetsCompat1.isConsumed()) {
            return windowInsetsCompat1;
        }
        RecyclerView recyclerView0 = ((ViewPager2)view0).mRecyclerView;
        int v = recyclerView0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            ViewCompat.dispatchApplyWindowInsets(recyclerView0.getChildAt(v1), new WindowInsetsCompat(windowInsetsCompat1));
        }
        return this.consumeAllInsets(windowInsetsCompat1);
    }
}

