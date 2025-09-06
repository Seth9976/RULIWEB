package com.google.android.material.search;

import android.view.View;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils.RelativePadding;

public final class SearchView..ExternalSyntheticLambda7 implements OnApplyWindowInsetsListener {
    public final SearchView f$0;

    public SearchView..ExternalSyntheticLambda7(SearchView searchView0) {
        this.f$0 = searchView0;
    }

    @Override  // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) {
        return this.f$0.lambda$setUpToolbarInsetListener$4$com-google-android-material-search-SearchView(view0, windowInsetsCompat0, viewUtils$RelativePadding0);
    }
}

