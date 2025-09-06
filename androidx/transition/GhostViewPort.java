package androidx.transition;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;

class GhostViewPort extends ViewGroup implements GhostView {
    private Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    GhostViewPort(View view0) {
        super(view0.getContext());
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            @Override  // android.view.ViewTreeObserver$OnPreDrawListener
            public boolean onPreDraw() {
                GhostViewPort.this.postInvalidateOnAnimation();
                if(GhostViewPort.this.mStartParent != null && GhostViewPort.this.mStartView != null) {
                    GhostViewPort.this.mStartParent.endViewTransition(GhostViewPort.this.mStartView);
                    GhostViewPort.this.mStartParent.postInvalidateOnAnimation();
                    GhostViewPort.this.mStartParent = null;
                    GhostViewPort.this.mStartView = null;
                }
                return true;
            }
        };
        this.mView = view0;
        this.setWillNotDraw(false);
        this.setClipChildren(false);
        this.setLayerType(2, null);
    }

    static GhostViewPort addGhost(View view0, ViewGroup viewGroup0, Matrix matrix0) {
        int v;
        if(!(view0.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
        }
        GhostViewHolder ghostViewHolder0 = GhostViewHolder.getHolder(viewGroup0);
        GhostViewPort ghostViewPort0 = GhostViewPort.getGhostView(view0);
        if(ghostViewPort0 == null) {
            v = 0;
        }
        else {
            GhostViewHolder ghostViewHolder1 = (GhostViewHolder)ghostViewPort0.getParent();
            if(ghostViewHolder1 == ghostViewHolder0) {
                v = 0;
            }
            else {
                v = ghostViewPort0.mReferences;
                ghostViewHolder1.removeView(ghostViewPort0);
                ghostViewPort0 = null;
            }
        }
        if(ghostViewPort0 == null) {
            if(matrix0 == null) {
                matrix0 = new Matrix();
                GhostViewPort.calculateMatrix(view0, viewGroup0, matrix0);
            }
            ghostViewPort0 = new GhostViewPort(view0);
            ghostViewPort0.setMatrix(matrix0);
            if(ghostViewHolder0 == null) {
                ghostViewHolder0 = new GhostViewHolder(viewGroup0);
            }
            else {
                ghostViewHolder0.popToOverlayTop();
            }
            GhostViewPort.copySize(viewGroup0, ghostViewHolder0);
            GhostViewPort.copySize(viewGroup0, ghostViewPort0);
            ghostViewHolder0.addGhostView(ghostViewPort0);
            ghostViewPort0.mReferences = v;
        }
        else if(matrix0 != null) {
            ghostViewPort0.setMatrix(matrix0);
        }
        ++ghostViewPort0.mReferences;
        return ghostViewPort0;
    }

    static void calculateMatrix(View view0, ViewGroup viewGroup0, Matrix matrix0) {
        ViewGroup viewGroup1 = (ViewGroup)view0.getParent();
        matrix0.reset();
        ViewUtils.transformMatrixToGlobal(viewGroup1, matrix0);
        matrix0.preTranslate(((float)(-viewGroup1.getScrollX())), ((float)(-viewGroup1.getScrollY())));
        ViewUtils.transformMatrixToLocal(viewGroup0, matrix0);
    }

    static void copySize(View view0, View view1) {
        ViewUtils.setLeftTopRightBottom(view1, view1.getLeft(), view1.getTop(), view1.getLeft() + view0.getWidth(), view1.getTop() + view0.getHeight());
    }

    static GhostViewPort getGhostView(View view0) {
        return (GhostViewPort)view0.getTag(id.ghost_view);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GhostViewPort.setGhostView(this.mView, this);
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.setTransitionVisibility(this.mView, 4);
        if(this.mView.getParent() != null) {
            ((View)this.mView.getParent()).invalidate();
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        ViewUtils.setTransitionVisibility(this.mView, 0);
        GhostViewPort.setGhostView(this.mView, null);
        if(this.mView.getParent() != null) {
            ((View)this.mView.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        CanvasUtils.enableZ(canvas0, true);
        canvas0.setMatrix(this.mMatrix);
        ViewUtils.setTransitionVisibility(this.mView, 0);
        this.mView.invalidate();
        ViewUtils.setTransitionVisibility(this.mView, 4);
        long v = this.getDrawingTime();
        this.drawChild(canvas0, this.mView, v);
        CanvasUtils.enableZ(canvas0, false);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
    }

    static void removeGhost(View view0) {
        GhostViewPort ghostViewPort0 = GhostViewPort.getGhostView(view0);
        if(ghostViewPort0 != null) {
            int v = ghostViewPort0.mReferences - 1;
            ghostViewPort0.mReferences = v;
            if(v <= 0) {
                ((GhostViewHolder)ghostViewPort0.getParent()).removeView(ghostViewPort0);
            }
        }
    }

    @Override  // androidx.transition.GhostView
    public void reserveEndViewTransition(ViewGroup viewGroup0, View view0) {
        this.mStartParent = viewGroup0;
        this.mStartView = view0;
    }

    static void setGhostView(View view0, GhostViewPort ghostViewPort0) {
        view0.setTag(id.ghost_view, ghostViewPort0);
    }

    void setMatrix(Matrix matrix0) {
        this.mMatrix = matrix0;
    }

    @Override  // androidx.transition.GhostView, android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        if(GhostViewPort.getGhostView(this.mView) == this) {
            ViewUtils.setTransitionVisibility(this.mView, (v == 0 ? 4 : 0));
        }
    }
}

