package com.google.android.material.search;

public final class SearchViewAnimationHelper..ExternalSyntheticLambda6 implements Runnable {
    public final SearchView f$0;

    public SearchViewAnimationHelper..ExternalSyntheticLambda6(SearchView searchView0) {
        this.f$0 = searchView0;
    }

    @Override
    public final void run() {
        this.f$0.requestFocusAndShowKeyboardIfNeeded();
    }
}

