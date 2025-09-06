package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class ViewOverlayApi14 implements ViewOverlayImpl {
    static class OverlayViewGroup extends ViewGroup {
        private boolean disposed;
        ArrayList drawables;
        ViewGroup hostView;
        static Method invalidateChildInParentFastMethod;
        View requestingView;
        ViewOverlayApi14 viewOverlay;

        static {
            try {
                OverlayViewGroup.invalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
            }
            catch(NoSuchMethodException unused_ex) {
            }
        }

        OverlayViewGroup(Context context0, ViewGroup viewGroup0, View view0, ViewOverlayApi14 viewOverlayApi140) {
            super(context0);
            this.drawables = null;
            this.hostView = viewGroup0;
            this.requestingView = view0;
            this.setRight(viewGroup0.getWidth());
            this.setBottom(viewGroup0.getHeight());
            viewGroup0.addView(this);
            this.viewOverlay = viewOverlayApi140;
        }

        public void add(Drawable drawable0) {
            this.assertNotDisposed();
            if(this.drawables == null) {
                this.drawables = new ArrayList();
            }
            if(!this.drawables.contains(drawable0)) {
                this.drawables.add(drawable0);
                this.invalidate(drawable0.getBounds());
                drawable0.setCallback(this);
            }
        }

        public void add(View view0) {
            this.assertNotDisposed();
            if(view0.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
                if(viewGroup0 != this.hostView && viewGroup0.getParent() != null && ViewCompat.isAttachedToWindow(viewGroup0)) {
                    int[] arr_v = new int[2];
                    int[] arr_v1 = new int[2];
                    viewGroup0.getLocationOnScreen(arr_v);
                    this.hostView.getLocationOnScreen(arr_v1);
                    ViewCompat.offsetLeftAndRight(view0, arr_v[0] - arr_v1[0]);
                    ViewCompat.offsetTopAndBottom(view0, arr_v[1] - arr_v1[1]);
                }
                viewGroup0.removeView(view0);
                if(view0.getParent() != null) {
                    viewGroup0.removeView(view0);
                }
            }
            super.addView(view0);
        }

        private void assertNotDisposed() {
            if(this.disposed) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        @Override  // android.view.ViewGroup
        protected void dispatchDraw(Canvas canvas0) {
            int[] arr_v = new int[2];
            int[] arr_v1 = new int[2];
            this.hostView.getLocationOnScreen(arr_v);
            this.requestingView.getLocationOnScreen(arr_v1);
            canvas0.translate(((float)(arr_v1[0] - arr_v[0])), ((float)(arr_v1[1] - arr_v[1])));
            canvas0.clipRect(new Rect(0, 0, this.requestingView.getWidth(), this.requestingView.getHeight()));
            super.dispatchDraw(canvas0);
            int v1 = this.drawables == null ? 0 : this.drawables.size();
            for(int v = 0; v < v1; ++v) {
                ((Drawable)this.drawables.get(v)).draw(canvas0);
            }
        }

        @Override  // android.view.ViewGroup
        public boolean dispatchTouchEvent(MotionEvent motionEvent0) {
            return false;
        }

        private void disposeIfEmpty() {
            if(this.getChildCount() == 0 && (this.drawables == null || this.drawables.size() == 0)) {
                this.disposed = true;
                this.hostView.removeView(this);
            }
        }

        private void getOffset(int[] arr_v) {
            int[] arr_v1 = new int[2];
            int[] arr_v2 = new int[2];
            this.hostView.getLocationOnScreen(arr_v1);
            this.requestingView.getLocationOnScreen(arr_v2);
            arr_v[0] = arr_v2[0] - arr_v1[0];
            arr_v[1] = arr_v2[1] - arr_v1[1];
        }

        @Override  // android.view.ViewGroup
        public ViewParent invalidateChildInParent(int[] arr_v, Rect rect0) {
            if(this.hostView != null) {
                rect0.offset(arr_v[0], arr_v[1]);
                if(this.hostView != null) {
                    arr_v[0] = 0;
                    arr_v[1] = 0;
                    int[] arr_v1 = new int[2];
                    this.getOffset(arr_v1);
                    rect0.offset(arr_v1[0], arr_v1[1]);
                    return super.invalidateChildInParent(arr_v, rect0);
                }
                this.invalidate(rect0);
            }
            return null;
        }

        protected ViewParent invalidateChildInParentFast(int v, int v1, Rect rect0) {
            if(this.hostView != null && OverlayViewGroup.invalidateChildInParentFastMethod != null) {
                try {
                    this.getOffset(new int[2]);
                    OverlayViewGroup.invalidateChildInParentFastMethod.invoke(this.hostView, v, v1, rect0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    illegalAccessException0.printStackTrace();
                }
                catch(InvocationTargetException invocationTargetException0) {
                    invocationTargetException0.printStackTrace();
                }
                return null;
            }
            return null;
        }

        @Override  // android.view.View
        public void invalidateDrawable(Drawable drawable0) {
            this.invalidate(drawable0.getBounds());
        }

        @Override  // android.view.ViewGroup
        protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        }

        public void remove(Drawable drawable0) {
            ArrayList arrayList0 = this.drawables;
            if(arrayList0 != null) {
                arrayList0.remove(drawable0);
                this.invalidate(drawable0.getBounds());
                drawable0.setCallback(null);
                this.disposeIfEmpty();
            }
        }

        public void remove(View view0) {
            super.removeView(view0);
            this.disposeIfEmpty();
        }

        // 去混淆评级： 低(30)
        @Override  // android.view.View
        protected boolean verifyDrawable(Drawable drawable0) {
            return super.verifyDrawable(drawable0) || this.drawables != null && this.drawables.contains(drawable0);
        }
    }

    protected OverlayViewGroup overlayViewGroup;

    ViewOverlayApi14(Context context0, ViewGroup viewGroup0, View view0) {
        this.overlayViewGroup = new OverlayViewGroup(context0, viewGroup0, view0, this);
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void add(Drawable drawable0) {
        this.overlayViewGroup.add(drawable0);
    }

    static ViewOverlayApi14 createFrom(View view0) {
        ViewGroup viewGroup0 = ViewUtils.getContentView(view0);
        if(viewGroup0 != null) {
            int v = viewGroup0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = viewGroup0.getChildAt(v1);
                if(view1 instanceof OverlayViewGroup) {
                    return ((OverlayViewGroup)view1).viewOverlay;
                }
            }
            return new ViewGroupOverlayApi14(viewGroup0.getContext(), viewGroup0, view0);
        }
        return null;
    }

    @Override  // com.google.android.material.internal.ViewOverlayImpl
    public void remove(Drawable drawable0) {
        this.overlayViewGroup.remove(drawable0);
    }
}

