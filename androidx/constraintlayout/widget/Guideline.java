package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Guideline extends View {
    private boolean mFilterRedundantCalls;

    public Guideline(Context context0) {
        super(context0);
        this.mFilterRedundantCalls = true;
        super.setVisibility(8);
    }

    public Guideline(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mFilterRedundantCalls = true;
        super.setVisibility(8);
    }

    public Guideline(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mFilterRedundantCalls = true;
        super.setVisibility(8);
    }

    public Guideline(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v);
        this.mFilterRedundantCalls = true;
        super.setVisibility(8);
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        this.setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z) {
        this.mFilterRedundantCalls = z;
    }

    public void setGuidelineBegin(int v) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        if(this.mFilterRedundantCalls && constraintLayout$LayoutParams0.guideBegin == v) {
            return;
        }
        constraintLayout$LayoutParams0.guideBegin = v;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    public void setGuidelineEnd(int v) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        if(this.mFilterRedundantCalls && constraintLayout$LayoutParams0.guideEnd == v) {
            return;
        }
        constraintLayout$LayoutParams0.guideEnd = v;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    public void setGuidelinePercent(float f) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        if(this.mFilterRedundantCalls && constraintLayout$LayoutParams0.guidePercent == f) {
            return;
        }
        constraintLayout$LayoutParams0.guidePercent = f;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
    }
}

