package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;

public class Placeholder extends View {
    private View mContent;
    private int mContentId;
    private int mEmptyVisibility;

    public Placeholder(Context context0) {
        super(context0);
        this.mContentId = -1;
        this.mContent = null;
        this.mEmptyVisibility = 4;
        this.init(null);
    }

    public Placeholder(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mContentId = -1;
        this.mContent = null;
        this.mEmptyVisibility = 4;
        this.init(attributeSet0);
    }

    public Placeholder(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mContentId = -1;
        this.mContent = null;
        this.mEmptyVisibility = 4;
        this.init(attributeSet0);
    }

    public Placeholder(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v);
        this.mContentId = -1;
        this.mContent = null;
        this.mEmptyVisibility = 4;
        this.init(attributeSet0);
    }

    public View getContent() {
        return this.mContent;
    }

    public int getEmptyVisibility() {
        return this.mEmptyVisibility;
    }

    private void init(AttributeSet attributeSet0) {
        super.setVisibility(this.mEmptyVisibility);
        this.mContentId = -1;
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_placeholder);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_placeholder_content) {
                    this.mContentId = typedArray0.getResourceId(v2, this.mContentId);
                }
                else if(v2 == styleable.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.mEmptyVisibility = typedArray0.getInt(v2, this.mEmptyVisibility);
                }
            }
            typedArray0.recycle();
        }
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
        if(this.isInEditMode()) {
            canvas0.drawRGB(0xDF, 0xDF, 0xDF);
            Paint paint0 = new Paint();
            paint0.setARGB(0xFF, 210, 210, 210);
            paint0.setTextAlign(Paint.Align.CENTER);
            paint0.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect0 = new Rect();
            canvas0.getClipBounds(rect0);
            paint0.setTextSize(((float)rect0.height()));
            int v = rect0.height();
            int v1 = rect0.width();
            paint0.setTextAlign(Paint.Align.LEFT);
            paint0.getTextBounds("?", 0, 1, rect0);
            canvas0.drawText("?", ((float)v1) / 2.0f - ((float)rect0.width()) / 2.0f - ((float)rect0.left), ((float)v) / 2.0f + ((float)rect0.height()) / 2.0f - ((float)rect0.bottom), paint0);
        }
    }

    public void setContentId(int v) {
        if(this.mContentId != v) {
            View view0 = this.mContent;
            if(view0 != null) {
                view0.setVisibility(0);
                ((LayoutParams)this.mContent.getLayoutParams()).mIsInPlaceholder = false;
                this.mContent = null;
            }
            this.mContentId = v;
            if(v != -1) {
                View view1 = ((View)this.getParent()).findViewById(v);
                if(view1 != null) {
                    view1.setVisibility(8);
                }
            }
        }
    }

    public void setEmptyVisibility(int v) {
        this.mEmptyVisibility = v;
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout0) {
        if(this.mContent == null) {
            return;
        }
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        LayoutParams constraintLayout$LayoutParams1 = (LayoutParams)this.mContent.getLayoutParams();
        constraintLayout$LayoutParams1.mWidget.setVisibility(0);
        if(constraintLayout$LayoutParams0.mWidget.getHorizontalDimensionBehaviour() != DimensionBehaviour.FIXED) {
            constraintLayout$LayoutParams0.mWidget.setWidth(constraintLayout$LayoutParams1.mWidget.getWidth());
        }
        if(constraintLayout$LayoutParams0.mWidget.getVerticalDimensionBehaviour() != DimensionBehaviour.FIXED) {
            constraintLayout$LayoutParams0.mWidget.setHeight(constraintLayout$LayoutParams1.mWidget.getHeight());
        }
        constraintLayout$LayoutParams1.mWidget.setVisibility(8);
    }

    public void updatePreLayout(ConstraintLayout constraintLayout0) {
        if(this.mContentId == -1 && !this.isInEditMode()) {
            this.setVisibility(this.mEmptyVisibility);
        }
        View view0 = constraintLayout0.findViewById(this.mContentId);
        this.mContent = view0;
        if(view0 != null) {
            ((LayoutParams)view0.getLayoutParams()).mIsInPlaceholder = true;
            this.mContent.setVisibility(0);
            this.setVisibility(0);
        }
    }
}

