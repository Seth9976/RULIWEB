package com.google.android.material.search;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;

public final class SearchBarAnimationHelper..ExternalSyntheticLambda3 implements Runnable {
    public final SearchBarAnimationHelper f$0;
    public final SearchBar f$1;
    public final View f$2;
    public final AppBarLayout f$3;
    public final boolean f$4;

    public SearchBarAnimationHelper..ExternalSyntheticLambda3(SearchBarAnimationHelper searchBarAnimationHelper0, SearchBar searchBar0, View view0, AppBarLayout appBarLayout0, boolean z) {
        this.f$0 = searchBarAnimationHelper0;
        this.f$1 = searchBar0;
        this.f$2 = view0;
        this.f$3 = appBarLayout0;
        this.f$4 = z;
    }

    @Override
    public final void run() {
        this.f$0.lambda$startExpandAnimation$0$com-google-android-material-search-SearchBarAnimationHelper(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

