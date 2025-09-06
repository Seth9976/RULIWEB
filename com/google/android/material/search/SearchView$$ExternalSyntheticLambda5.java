package com.google.android.material.search;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

public final class SearchView..ExternalSyntheticLambda5 implements OnApplyWindowInsetsListener {
    public final ViewGroup.MarginLayoutParams f$0;
    public final int f$1;
    public final int f$2;

    public SearchView..ExternalSyntheticLambda5(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, int v1) {
        this.f$0 = viewGroup$MarginLayoutParams0;
        this.f$1 = v;
        this.f$2 = v1;
    }

    @Override  // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
        return SearchView.lambda$setUpDividerInsetListener$6(this.f$0, this.f$1, this.f$2, view0, windowInsetsCompat0);
    }
}

