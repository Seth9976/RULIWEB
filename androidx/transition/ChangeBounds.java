package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.content.res.TypedArrayUtils;

public class ChangeBounds extends Transition {
    static class ClipListener extends AnimatorListenerAdapter implements TransitionListener {
        private final int mEndBottom;
        private final Rect mEndClip;
        private final boolean mEndClipIsNull;
        private final int mEndLeft;
        private final int mEndRight;
        private final int mEndTop;
        private boolean mIsCanceled;
        private final int mStartBottom;
        private final Rect mStartClip;
        private final boolean mStartClipIsNull;
        private final int mStartLeft;
        private final int mStartRight;
        private final int mStartTop;
        private final View mView;

        ClipListener(View view0, Rect rect0, boolean z, Rect rect1, boolean z1, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
            this.mView = view0;
            this.mStartClip = rect0;
            this.mStartClipIsNull = z;
            this.mEndClip = rect1;
            this.mEndClipIsNull = z1;
            this.mStartLeft = v;
            this.mStartTop = v1;
            this.mStartRight = v2;
            this.mStartBottom = v3;
            this.mEndLeft = v4;
            this.mEndTop = v5;
            this.mEndRight = v6;
            this.mEndBottom = v7;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.onAnimationEnd(animator0, false);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            Rect rect0 = null;
            if(this.mIsCanceled) {
                return;
            }
            if(!z) {
                if(!this.mEndClipIsNull) {
                    rect0 = this.mEndClip;
                }
            }
            else if(!this.mStartClipIsNull) {
                rect0 = this.mStartClip;
            }
            this.mView.setClipBounds(rect0);
            if(z) {
                ViewUtils.setLeftTopRightBottom(this.mView, this.mStartLeft, this.mStartTop, this.mStartRight, this.mStartBottom);
                return;
            }
            ViewUtils.setLeftTopRightBottom(this.mView, this.mEndLeft, this.mEndTop, this.mEndRight, this.mEndBottom);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
            this.onAnimationStart(animator0, false);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationStart(Animator animator0, boolean z) {
            int v = z ? this.mEndLeft : this.mStartLeft;
            int v1 = z ? this.mEndTop : this.mStartTop;
            ViewUtils.setLeftTopRightBottom(this.mView, v, v1, Math.max(this.mStartRight - this.mStartLeft, this.mEndRight - this.mEndLeft) + v, Math.max(this.mStartBottom - this.mStartTop, this.mEndBottom - this.mEndTop) + v1);
            this.mView.setClipBounds((z ? this.mEndClip : this.mStartClip));
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
            this.mIsCanceled = true;
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
            Rect rect0 = this.mView.getClipBounds();
            this.mView.setTag(id.transition_clip, rect0);
            this.mView.setClipBounds((this.mEndClipIsNull ? null : this.mEndClip));
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            Rect rect0 = (Rect)this.mView.getTag(id.transition_clip);
            this.mView.setTag(id.transition_clip, null);
            this.mView.setClipBounds(rect0);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }
    }

    static class SuppressLayoutListener extends TransitionListenerAdapter {
        boolean mCanceled;
        final ViewGroup mParent;

        SuppressLayoutListener(ViewGroup viewGroup0) {
            this.mCanceled = false;
            this.mParent = viewGroup0;
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionCancel(Transition transition0) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
            this.mCanceled = true;
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionEnd(Transition transition0) {
            if(!this.mCanceled) {
                ViewGroupUtils.suppressLayout(this.mParent, false);
            }
            transition0.removeListener(this);
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionPause(Transition transition0) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionResume(Transition transition0) {
            ViewGroupUtils.suppressLayout(this.mParent, true);
        }
    }

    static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private final View mView;

        ViewBounds(View view0) {
            this.mView = view0;
        }

        void setBottomRight(PointF pointF0) {
            this.mRight = Math.round(pointF0.x);
            this.mBottom = Math.round(pointF0.y);
            int v = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = v;
            if(this.mTopLeftCalls == v) {
                this.setLeftTopRightBottom();
            }
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }

        void setTopLeft(PointF pointF0) {
            this.mLeft = Math.round(pointF0.x);
            this.mTop = Math.round(pointF0.y);
            int v = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = v;
            if(v == this.mBottomRightCalls) {
                this.setLeftTopRightBottom();
            }
        }
    }

    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = null;
    private static final Property BOTTOM_RIGHT_PROPERTY = null;
    private static final Property POSITION_PROPERTY = null;
    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_CLIP = "android:changeBounds:clip";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static final Property TOP_LEFT_ONLY_PROPERTY;
    private static final Property TOP_LEFT_PROPERTY;
    private boolean mResizeClip;
    private static final RectEvaluator sRectEvaluator;
    private static final String[] sTransitionProperties;

    static {
        ChangeBounds.sTransitionProperties = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
        ChangeBounds.TOP_LEFT_PROPERTY = new Property(PointF.class, "topLeft") {
            public PointF get(ViewBounds changeBounds$ViewBounds0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((ViewBounds)object0));
            }

            public void set(ViewBounds changeBounds$ViewBounds0, PointF pointF0) {
                changeBounds$ViewBounds0.setTopLeft(pointF0);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((ViewBounds)object0), ((PointF)object1));
            }
        };
        ChangeBounds.BOTTOM_RIGHT_PROPERTY = new Property(PointF.class, "bottomRight") {
            public PointF get(ViewBounds changeBounds$ViewBounds0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((ViewBounds)object0));
            }

            public void set(ViewBounds changeBounds$ViewBounds0, PointF pointF0) {
                changeBounds$ViewBounds0.setBottomRight(pointF0);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((ViewBounds)object0), ((PointF)object1));
            }
        };
        ChangeBounds.BOTTOM_RIGHT_ONLY_PROPERTY = new Property(PointF.class, "bottomRight") {
            public PointF get(View view0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, PointF pointF0) {
                ViewUtils.setLeftTopRightBottom(view0, view0.getLeft(), view0.getTop(), Math.round(pointF0.x), Math.round(pointF0.y));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((PointF)object1));
            }
        };
        ChangeBounds.TOP_LEFT_ONLY_PROPERTY = new Property(PointF.class, "topLeft") {
            public PointF get(View view0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, PointF pointF0) {
                ViewUtils.setLeftTopRightBottom(view0, Math.round(pointF0.x), Math.round(pointF0.y), view0.getRight(), view0.getBottom());
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((PointF)object1));
            }
        };
        ChangeBounds.POSITION_PROPERTY = new Property(PointF.class, "position") {
            public PointF get(View view0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, PointF pointF0) {
                int v = Math.round(pointF0.x);
                int v1 = Math.round(pointF0.y);
                ViewUtils.setLeftTopRightBottom(view0, v, v1, view0.getWidth() + v, view0.getHeight() + v1);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((PointF)object1));
            }
        };
        ChangeBounds.sRectEvaluator = new RectEvaluator();
    }

    public ChangeBounds() {
        this.mResizeClip = false;
    }

    public ChangeBounds(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mResizeClip = false;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.CHANGE_BOUNDS);
        boolean z = TypedArrayUtils.getNamedBoolean(typedArray0, ((XmlResourceParser)attributeSet0), "resizeClip", 0, false);
        typedArray0.recycle();
        this.setResizeClip(z);
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
        if(this.mResizeClip) {
            Rect rect0 = (Rect)transitionValues0.view.getTag(id.transition_clip);
            if(rect0 != null) {
                transitionValues0.values.put("android:changeBounds:clip", rect0);
            }
        }
    }

    private void captureValues(TransitionValues transitionValues0) {
        View view0 = transitionValues0.view;
        if(view0.isLaidOut() || view0.getWidth() != 0 || view0.getHeight() != 0) {
            Rect rect0 = new Rect(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
            transitionValues0.values.put("android:changeBounds:bounds", rect0);
            ViewParent viewParent0 = transitionValues0.view.getParent();
            transitionValues0.values.put("android:changeBounds:parent", viewParent0);
            if(this.mResizeClip) {
                Rect rect1 = view0.getClipBounds();
                transitionValues0.values.put("android:changeBounds:clip", rect1);
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        Animator animator0;
        ObjectAnimator objectAnimator3;
        int v15;
        int v14;
        int v13;
        ObjectAnimator objectAnimator2;
        int v12;
        if(transitionValues0 != null && transitionValues1 != null && (((ViewGroup)transitionValues0.values.get("android:changeBounds:parent")) != null && ((ViewGroup)transitionValues1.values.get("android:changeBounds:parent")) != null)) {
            View view0 = transitionValues1.view;
            Rect rect0 = (Rect)transitionValues0.values.get("android:changeBounds:bounds");
            Rect rect1 = (Rect)transitionValues1.values.get("android:changeBounds:bounds");
            int v = rect0.left;
            int v1 = rect1.left;
            int v2 = rect0.top;
            int v3 = rect1.top;
            int v4 = rect0.right;
            int v5 = rect1.right;
            int v6 = rect0.bottom;
            int v7 = rect1.bottom;
            int v8 = v4 - v;
            int v9 = v6 - v2;
            int v10 = v5 - v1;
            int v11 = v7 - v3;
            Rect rect2 = (Rect)transitionValues0.values.get("android:changeBounds:clip");
            Rect rect3 = (Rect)transitionValues1.values.get("android:changeBounds:clip");
            if((v8 == 0 || v9 == 0) && (v10 == 0 || v11 == 0)) {
                v12 = 0;
            }
            else {
                v12 = v != v1 || v2 != v3 ? 1 : 0;
                if(v4 != v5 || v6 != v7) {
                    ++v12;
                }
            }
            if(rect2 != null && !rect2.equals(rect3) || rect2 == null && rect3 != null) {
                ++v12;
            }
            if(v12 > 0) {
                if(this.mResizeClip) {
                    ViewUtils.setLeftTopRightBottom(view0, v, v2, v + Math.max(v8, v10), v2 + Math.max(v9, v11));
                    if(v != v1 || v2 != v3) {
                        v13 = v7;
                        v15 = v5;
                        v14 = v1;
                        Path path5 = this.getPathMotion().getPath(((float)v), ((float)v2), ((float)v1), ((float)v3));
                        objectAnimator2 = ObjectAnimatorUtils.ofPointF(view0, ChangeBounds.POSITION_PROPERTY, path5);
                    }
                    else {
                        objectAnimator2 = null;
                        v13 = v7;
                        v14 = v1;
                        v15 = v5;
                    }
                    Rect rect4 = rect2 == null ? rect2 : new Rect(0, 0, v8, v9);
                    Rect rect5 = rect3 == null ? rect3 : new Rect(0, 0, v10, v11);
                    if(rect4.equals(rect5)) {
                        objectAnimator3 = null;
                    }
                    else {
                        view0.setClipBounds(rect4);
                        objectAnimator3 = ObjectAnimator.ofObject(view0, "clipBounds", ChangeBounds.sRectEvaluator, new Object[]{rect4, rect5});
                        ClipListener changeBounds$ClipListener0 = new ClipListener(view0, rect4, rect2 == null, rect5, rect3 == null, v, v2, v4, v6, v14, v3, v15, v13);
                        objectAnimator3.addListener(changeBounds$ClipListener0);
                        this.addListener(changeBounds$ClipListener0);
                    }
                    animator0 = TransitionUtils.mergeAnimators(objectAnimator2, objectAnimator3);
                }
                else {
                    ViewUtils.setLeftTopRightBottom(view0, v, v2, v4, v6);
                    if(v12 != 2) {
                        if(v != v1 || v2 != v3) {
                            Path path4 = this.getPathMotion().getPath(((float)v), ((float)v2), ((float)v1), ((float)v3));
                            animator0 = ObjectAnimatorUtils.ofPointF(view0, ChangeBounds.TOP_LEFT_ONLY_PROPERTY, path4);
                        }
                        else {
                            Path path3 = this.getPathMotion().getPath(((float)v4), ((float)v6), ((float)v5), ((float)v7));
                            animator0 = ObjectAnimatorUtils.ofPointF(view0, ChangeBounds.BOTTOM_RIGHT_ONLY_PROPERTY, path3);
                        }
                    }
                    else if(v8 == v10 && v9 == v11) {
                        Path path0 = this.getPathMotion().getPath(((float)v), ((float)v2), ((float)v1), ((float)v3));
                        animator0 = ObjectAnimatorUtils.ofPointF(view0, ChangeBounds.POSITION_PROPERTY, path0);
                    }
                    else {
                        ViewBounds changeBounds$ViewBounds0 = new ViewBounds(view0);
                        Path path1 = this.getPathMotion().getPath(((float)v), ((float)v2), ((float)v1), ((float)v3));
                        ObjectAnimator objectAnimator0 = ObjectAnimatorUtils.ofPointF(changeBounds$ViewBounds0, ChangeBounds.TOP_LEFT_PROPERTY, path1);
                        Path path2 = this.getPathMotion().getPath(((float)v4), ((float)v6), ((float)v5), ((float)v7));
                        ObjectAnimator objectAnimator1 = ObjectAnimatorUtils.ofPointF(changeBounds$ViewBounds0, ChangeBounds.BOTTOM_RIGHT_PROPERTY, path2);
                        AnimatorSet animatorSet0 = new AnimatorSet();
                        animatorSet0.playTogether(new Animator[]{objectAnimator0, objectAnimator1});
                        animatorSet0.addListener(new AnimatorListenerAdapter() {
                            private final ViewBounds mViewBounds;

                            {
                                ViewBounds changeBounds$ViewBounds0 = changeBounds$ViewBounds0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                                this.mViewBounds = changeBounds$ViewBounds0;
                            }
                        });
                        animator0 = animatorSet0;
                    }
                }
                if(view0.getParent() instanceof ViewGroup) {
                    ViewGroup viewGroup1 = (ViewGroup)view0.getParent();
                    ViewGroupUtils.suppressLayout(viewGroup1, true);
                    this.getRootTransition().addListener(new SuppressLayoutListener(viewGroup1));
                }
                return animator0;
            }
            return null;
        }
        return null;
    }

    public boolean getResizeClip() {
        return this.mResizeClip;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return ChangeBounds.sTransitionProperties;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    public void setResizeClip(boolean z) {
        this.mResizeClip = z;
    }
}

