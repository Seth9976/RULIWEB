package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues.SharedValuesListener;
import java.util.ArrayList;
import java.util.HashSet;

public class ViewTransitionController {
    ArrayList mAnimations;
    private final MotionLayout mMotionLayout;
    private HashSet mRelatedViews;
    ArrayList mRemoveList;
    private String mTAG;
    private ArrayList mViewTransitions;

    public ViewTransitionController(MotionLayout motionLayout0) {
        this.mViewTransitions = new ArrayList();
        this.mTAG = "ViewTransitionController";
        this.mRemoveList = new ArrayList();
        this.mMotionLayout = motionLayout0;
    }

    public void add(ViewTransition viewTransition0) {
        this.mViewTransitions.add(viewTransition0);
        this.mRelatedViews = null;
        if(viewTransition0.getStateTransition() == 4) {
            this.listenForSharedVariable(viewTransition0, true);
            return;
        }
        if(viewTransition0.getStateTransition() == 5) {
            this.listenForSharedVariable(viewTransition0, false);
        }
    }

    void addAnimation(Animate viewTransition$Animate0) {
        if(this.mAnimations == null) {
            this.mAnimations = new ArrayList();
        }
        this.mAnimations.add(viewTransition$Animate0);
    }

    void animate() {
        ArrayList arrayList0 = this.mAnimations;
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                ((Animate)object0).mutate();
            }
            this.mAnimations.removeAll(this.mRemoveList);
            this.mRemoveList.clear();
            if(this.mAnimations.isEmpty()) {
                this.mAnimations = null;
            }
        }
    }

    boolean applyViewTransition(int v, MotionController motionController0) {
        for(Object object0: this.mViewTransitions) {
            ViewTransition viewTransition0 = (ViewTransition)object0;
            if(viewTransition0.getId() == v) {
                viewTransition0.mKeyFrames.addAllFrames(motionController0);
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    void enableViewTransition(int v, boolean z) {
        for(Object object0: this.mViewTransitions) {
            ViewTransition viewTransition0 = (ViewTransition)object0;
            if(viewTransition0.getId() == v) {
                viewTransition0.setEnabled(z);
                return;
            }
            if(false) {
                break;
            }
        }
    }

    void invalidate() {
        this.mMotionLayout.invalidate();
    }

    boolean isViewTransitionEnabled(int v) {
        for(Object object0: this.mViewTransitions) {
            ViewTransition viewTransition0 = (ViewTransition)object0;
            if(viewTransition0.getId() == v) {
                return viewTransition0.isEnabled();
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private void listenForSharedVariable(ViewTransition viewTransition0, boolean z) {
        ConstraintLayout.getSharedValues().addListener(viewTransition0.getSharedValueID(), new SharedValuesListener() {
            @Override  // androidx.constraintlayout.widget.SharedValues$SharedValuesListener
            public void onNewValue(int v, int v1, int v2) {
                viewTransition0.setSharedValueCurrent(v1);
                if(viewTransition0.getSharedValueID() == v && viewTransition0.getSharedValueCurrent() != v1) {
                    if(z) {
                        if(viewTransition0.getSharedValue() == v1) {
                            int v3 = ViewTransitionController.this.mMotionLayout.getChildCount();
                            for(int v4 = 0; v4 < v3; ++v4) {
                                View view0 = ViewTransitionController.this.mMotionLayout.getChildAt(v4);
                                if(viewTransition0.matchesView(view0)) {
                                    int v5 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                    ConstraintSet constraintSet0 = ViewTransitionController.this.mMotionLayout.getConstraintSet(v5);
                                    viewTransition0.applyTransition(ViewTransitionController.this, ViewTransitionController.this.mMotionLayout, v5, constraintSet0, new View[]{view0});
                                }
                            }
                        }
                    }
                    else if(viewTransition0.getSharedValue() != v1) {
                        int v6 = ViewTransitionController.this.mMotionLayout.getChildCount();
                        for(int v7 = 0; v7 < v6; ++v7) {
                            View view1 = ViewTransitionController.this.mMotionLayout.getChildAt(v7);
                            if(viewTransition0.matchesView(view1)) {
                                int v8 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                ConstraintSet constraintSet1 = ViewTransitionController.this.mMotionLayout.getConstraintSet(v8);
                                viewTransition0.applyTransition(ViewTransitionController.this, ViewTransitionController.this.mMotionLayout, v8, constraintSet1, new View[]{view1});
                            }
                        }
                    }
                }
            }
        });
    }

    void remove(int v) {
        ViewTransition viewTransition0 = null;
        for(Object object0: this.mViewTransitions) {
            ViewTransition viewTransition1 = (ViewTransition)object0;
            if(viewTransition1.getId() == v) {
                viewTransition0 = viewTransition1;
                break;
            }
        }
        if(viewTransition0 != null) {
            this.mRelatedViews = null;
            this.mViewTransitions.remove(viewTransition0);
        }
    }

    void removeAnimation(Animate viewTransition$Animate0) {
        this.mRemoveList.add(viewTransition$Animate0);
    }

    void touchEvent(MotionEvent motionEvent0) {
        int v = this.mMotionLayout.getCurrentState();
        if(v != -1) {
            if(this.mRelatedViews == null) {
                this.mRelatedViews = new HashSet();
                for(Object object0: this.mViewTransitions) {
                    ViewTransition viewTransition0 = (ViewTransition)object0;
                    int v1 = this.mMotionLayout.getChildCount();
                    for(int v2 = 0; v2 < v1; ++v2) {
                        View view0 = this.mMotionLayout.getChildAt(v2);
                        if(viewTransition0.matchesView(view0)) {
                            view0.getId();
                            this.mRelatedViews.add(view0);
                        }
                    }
                }
            }
            float f = motionEvent0.getX();
            float f1 = motionEvent0.getY();
            Rect rect0 = new Rect();
            int v3 = motionEvent0.getAction();
            if(this.mAnimations != null && !this.mAnimations.isEmpty()) {
                for(Object object1: this.mAnimations) {
                    ((Animate)object1).reactTo(v3, f, f1);
                }
            }
            if(v3 == 0 || v3 == 1) {
                ConstraintSet constraintSet0 = this.mMotionLayout.getConstraintSet(v);
                for(Object object2: this.mViewTransitions) {
                    ViewTransition viewTransition1 = (ViewTransition)object2;
                    if(viewTransition1.supports(v3)) {
                        for(Object object3: this.mRelatedViews) {
                            View view1 = (View)object3;
                            if(viewTransition1.matchesView(view1)) {
                                view1.getHitRect(rect0);
                                if(rect0.contains(((int)f), ((int)f1))) {
                                    viewTransition1.applyTransition(this, this.mMotionLayout, v, constraintSet0, new View[]{view1});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void viewTransition(ViewTransition viewTransition0, View[] arr_view) {
        int v = this.mMotionLayout.getCurrentState();
        if(viewTransition0.mViewTransitionMode != 2) {
            if(v == -1) {
                Log.w(this.mTAG, "No support for ViewTransition within transition yet. Currently: " + this.mMotionLayout.toString());
                return;
            }
            ConstraintSet constraintSet0 = this.mMotionLayout.getConstraintSet(v);
            if(constraintSet0 == null) {
                return;
            }
            viewTransition0.applyTransition(this, this.mMotionLayout, v, constraintSet0, arr_view);
            return;
        }
        viewTransition0.applyTransition(this, this.mMotionLayout, v, null, arr_view);
    }

    void viewTransition(int v, View[] arr_view) {
        ArrayList arrayList0 = new ArrayList();
        ViewTransition viewTransition0 = null;
        for(Object object0: this.mViewTransitions) {
            ViewTransition viewTransition1 = (ViewTransition)object0;
            if(viewTransition1.getId() == v) {
                for(int v1 = 0; v1 < arr_view.length; ++v1) {
                    View view0 = arr_view[v1];
                    if(viewTransition1.checkTags(view0)) {
                        arrayList0.add(view0);
                    }
                }
                if(!arrayList0.isEmpty()) {
                    this.viewTransition(viewTransition1, ((View[])arrayList0.toArray(new View[0])));
                    arrayList0.clear();
                }
                viewTransition0 = viewTransition1;
            }
        }
        if(viewTransition0 == null) {
            Log.e(this.mTAG, " Could not find ViewTransition");
        }
    }
}

