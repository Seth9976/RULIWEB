package androidx.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import androidx.preference.R.styleable;

public class PreferenceImageView extends ImageView {
    private int mMaxHeight;
    private int mMaxWidth;

    public PreferenceImageView(Context context0) {
        this(context0, null);
    }

    public PreferenceImageView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public PreferenceImageView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mMaxWidth = 0x7FFFFFFF;
        this.mMaxHeight = 0x7FFFFFFF;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.PreferenceImageView, v, 0);
        this.setMaxWidth(typedArray0.getDimensionPixelSize(styleable.PreferenceImageView_maxWidth, 0x7FFFFFFF));
        this.setMaxHeight(typedArray0.getDimensionPixelSize(styleable.PreferenceImageView_maxHeight, 0x7FFFFFFF));
        typedArray0.recycle();
    }

    @Override  // android.widget.ImageView
    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    @Override  // android.widget.ImageView
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override  // android.widget.ImageView
    protected void onMeasure(int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        if(v2 == 0x80000000 || v2 == 0) {
            int v3 = this.getMaxWidth();
            if(v3 != 0x7FFFFFFF && (v3 < View.MeasureSpec.getSize(v) || v2 == 0)) {
                v = View.MeasureSpec.makeMeasureSpec(v3, 0x80000000);
            }
        }
        int v4 = View.MeasureSpec.getMode(v1);
        if(v4 == 0x80000000 || v4 == 0) {
            int v5 = this.getMaxHeight();
            if(v5 != 0x7FFFFFFF && (v5 < View.MeasureSpec.getSize(v1) || v4 == 0)) {
                v1 = View.MeasureSpec.makeMeasureSpec(v5, 0x80000000);
            }
        }
        super.onMeasure(v, v1);
    }

    @Override  // android.widget.ImageView
    public void setMaxHeight(int v) {
        this.mMaxHeight = v;
        super.setMaxHeight(v);
    }

    @Override  // android.widget.ImageView
    public void setMaxWidth(int v) {
        this.mMaxWidth = v;
        super.setMaxWidth(v);
    }
}

