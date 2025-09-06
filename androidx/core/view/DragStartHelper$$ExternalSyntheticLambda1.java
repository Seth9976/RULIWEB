package androidx.core.view;

import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;

public final class DragStartHelper..ExternalSyntheticLambda1 implements View.OnTouchListener {
    public final DragStartHelper f$0;

    public DragStartHelper..ExternalSyntheticLambda1(DragStartHelper dragStartHelper0) {
        this.f$0 = dragStartHelper0;
    }

    @Override  // android.view.View$OnTouchListener
    public final boolean onTouch(View view0, MotionEvent motionEvent0) {
        return this.f$0.onTouch(view0, motionEvent0);
    }
}

