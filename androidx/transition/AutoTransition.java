package androidx.transition;

import android.content.Context;
import android.util.AttributeSet;

public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        this.init();
    }

    public AutoTransition(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.init();
    }

    private void init() {
        this.setOrdering(1);
        this.addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}

