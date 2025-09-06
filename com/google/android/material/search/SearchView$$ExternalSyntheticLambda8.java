package com.google.android.material.search;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

public final class SearchView..ExternalSyntheticLambda8 implements OnApplyWindowInsetsListener {
    public final SearchView f$0;

    public SearchView..ExternalSyntheticLambda8(SearchView searchView0) {
        this.f$0 = searchView0;
    }

    @Override  // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
        return this.f$0.lambda$setUpStatusBarSpacerInsetListener$5$com-google-android-material-search-SearchView(view0, windowInsetsCompat0);
    }
}

