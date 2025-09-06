package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.R.styleable;
import java.lang.ref.WeakReference;

public final class ViewStubCompat extends View {
    public interface OnInflateListener {
        void onInflate(ViewStubCompat arg1, View arg2);
    }

    private OnInflateListener mInflateListener;
    private int mInflatedId;
    private WeakReference mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public ViewStubCompat(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ViewStubCompat(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mLayoutResource = 0;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ViewStubCompat, v, 0);
        this.mInflatedId = typedArray0.getResourceId(styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = typedArray0.getResourceId(styleable.ViewStubCompat_android_layout, 0);
        this.setId(typedArray0.getResourceId(styleable.ViewStubCompat_android_id, -1));
        typedArray0.recycle();
        this.setVisibility(8);
        this.setWillNotDraw(true);
    }

    @Override  // android.view.View
    protected void dispatchDraw(Canvas canvas0) {
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public View inflate() {
        ViewParent viewParent0 = this.getParent();
        if(!(viewParent0 instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if(this.mLayoutResource == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        View view0 = (this.mInflater == null ? LayoutInflater.from(this.getContext()) : this.mInflater).inflate(this.mLayoutResource, ((ViewGroup)viewParent0), false);
        int v = this.mInflatedId;
        if(v != -1) {
            view0.setId(v);
        }
        int v1 = ((ViewGroup)viewParent0).indexOfChild(this);
        ((ViewGroup)viewParent0).removeViewInLayout(this);
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
        if(viewGroup$LayoutParams0 == null) {
            ((ViewGroup)viewParent0).addView(view0, v1);
        }
        else {
            ((ViewGroup)viewParent0).addView(view0, v1, viewGroup$LayoutParams0);
        }
        this.mInflatedViewRef = new WeakReference(view0);
        OnInflateListener viewStubCompat$OnInflateListener0 = this.mInflateListener;
        if(viewStubCompat$OnInflateListener0 != null) {
            viewStubCompat$OnInflateListener0.onInflate(this, view0);
        }
        return view0;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        this.setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int v) {
        this.mInflatedId = v;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater0) {
        this.mInflater = layoutInflater0;
    }

    public void setLayoutResource(int v) {
        this.mLayoutResource = v;
    }

    public void setOnInflateListener(OnInflateListener viewStubCompat$OnInflateListener0) {
        this.mInflateListener = viewStubCompat$OnInflateListener0;
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        WeakReference weakReference0 = this.mInflatedViewRef;
        if(weakReference0 != null) {
            View view0 = (View)weakReference0.get();
            if(view0 == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view0.setVisibility(v);
            return;
        }
        super.setVisibility(v);
        if(v != 0 && v != 4) {
            return;
        }
        this.inflate();
    }
}

