package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CompoundTrimPathContent {
    private List contents;

    public CompoundTrimPathContent() {
        this.contents = new ArrayList();
    }

    void addTrimPath(TrimPathContent trimPathContent0) {
        this.contents.add(trimPathContent0);
    }

    public void apply(Path path0) {
        for(int v = this.contents.size() - 1; v >= 0; --v) {
            Utils.applyTrimPathIfNeeded(path0, ((TrimPathContent)this.contents.get(v)));
        }
    }
}

