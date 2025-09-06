package androidx.constraintlayout.motion.widget;

import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.Objects;

public class DesignTool {
    private static final boolean DEBUG = false;
    private static final boolean DO_NOT_USE = false;
    private static final String TAG = "DesignTool";
    private String mLastEndState;
    private int mLastEndStateId;
    private String mLastStartState;
    private int mLastStartStateId;
    private final MotionLayout mMotionLayout;
    private MotionScene mSceneCache;
    static final HashMap sAllAttributes;
    static final HashMap sAllMargins;

    static {
        HashMap hashMap0 = new HashMap();
        DesignTool.sAllAttributes = hashMap0;
        HashMap hashMap1 = new HashMap();
        DesignTool.sAllMargins = hashMap1;
        hashMap0.put(Pair.create(4, 4), "layout_constraintBottom_toBottomOf");
        hashMap0.put(Pair.create(4, 3), "layout_constraintBottom_toTopOf");
        hashMap0.put(Pair.create(3, 4), "layout_constraintTop_toBottomOf");
        hashMap0.put(Pair.create(3, 3), "layout_constraintTop_toTopOf");
        hashMap0.put(Pair.create(6, 6), "layout_constraintStart_toStartOf");
        hashMap0.put(Pair.create(6, 7), "layout_constraintStart_toEndOf");
        hashMap0.put(Pair.create(7, 6), "layout_constraintEnd_toStartOf");
        hashMap0.put(Pair.create(7, 7), "layout_constraintEnd_toEndOf");
        hashMap0.put(Pair.create(1, 1), "layout_constraintLeft_toLeftOf");
        hashMap0.put(Pair.create(1, 2), "layout_constraintLeft_toRightOf");
        hashMap0.put(Pair.create(2, 2), "layout_constraintRight_toRightOf");
        hashMap0.put(Pair.create(2, 1), "layout_constraintRight_toLeftOf");
        hashMap0.put(Pair.create(5, 5), "layout_constraintBaseline_toBaselineOf");
        hashMap1.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
        hashMap1.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
        hashMap1.put("layout_constraintTop_toBottomOf", "layout_marginTop");
        hashMap1.put("layout_constraintTop_toTopOf", "layout_marginTop");
        hashMap1.put("layout_constraintStart_toStartOf", "layout_marginStart");
        hashMap1.put("layout_constraintStart_toEndOf", "layout_marginStart");
        hashMap1.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
        hashMap1.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
        hashMap1.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
        hashMap1.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        hashMap1.put("layout_constraintRight_toRightOf", "layout_marginRight");
        hashMap1.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    public DesignTool(MotionLayout motionLayout0) {
        this.mLastStartState = null;
        this.mLastEndState = null;
        this.mLastStartStateId = -1;
        this.mLastEndStateId = -1;
        this.mMotionLayout = motionLayout0;
    }

    private static void connect(int v, ConstraintSet constraintSet0, View view0, HashMap hashMap0, int v1, int v2) {
        Pair pair0 = Pair.create(v1, v2);
        String s = (String)DesignTool.sAllAttributes.get(pair0);
        String s1 = (String)hashMap0.get(s);
        if(s1 != null) {
            String s2 = (String)DesignTool.sAllMargins.get(s);
            int v3 = s2 == null ? 0 : DesignTool.getPxFromDp(v, ((String)hashMap0.get(s2)));
            int v4 = Integer.parseInt(s1);
            constraintSet0.connect(view0.getId(), v1, v4, v2, v3);
        }
    }

    public int designAccess(int v, String s, Object object0, float[] arr_f, int v1, float[] arr_f1, int v2) {
        MotionController motionController0;
        if(v == 0) {
            motionController0 = null;
        }
        else {
            if(this.mMotionLayout.mScene == null) {
                return -1;
            }
            if(((View)object0) == null) {
                return -1;
            }
            motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object0));
            if(motionController0 == null) {
                return -1;
            }
        }
        switch(v) {
            case 0: {
                return 1;
            }
            case 1: {
                int v3 = this.mMotionLayout.mScene.getDuration();
                motionController0.buildPath(arr_f1, v3 / 16);
                return v3 / 16;
            }
            case 2: {
                int v4 = this.mMotionLayout.mScene.getDuration();
                motionController0.buildKeyFrames(arr_f1, null);
                return v4 / 16;
            }
            case 3: {
                return motionController0.getAttributeValues(s, arr_f1, v2);
            }
            default: {
                return -1;
            }
        }
    }

    public void disableAutoTransition(boolean z) {
        this.mMotionLayout.disableAutoTransition(z);
    }

    public void dumpConstraintSet(String s) {
        if(this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        int v = this.mMotionLayout.lookUpConstraintId(s);
        System.out.println(" dumping  " + s + " (" + v + ")");
        try {
            this.mMotionLayout.mScene.getConstraintSet(v).dump(this.mMotionLayout.mScene, new int[0]);
        }
        catch(Exception exception0) {
            Log.e("DesignTool", "Error while dumping: " + s + " (" + v + ")", exception0);
        }
    }

    public int getAnimationKeyFrames(Object object0, float[] arr_f) {
        if(this.mMotionLayout.mScene == null) {
            return -1;
        }
        int v = this.mMotionLayout.mScene.getDuration();
        MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(object0);
        if(motionController0 == null) {
            return 0;
        }
        motionController0.buildKeyFrames(arr_f, null);
        return v / 16;
    }

    public int getAnimationPath(Object object0, float[] arr_f, int v) {
        if(this.mMotionLayout.mScene == null) {
            return -1;
        }
        MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(object0);
        if(motionController0 == null) {
            return 0;
        }
        motionController0.buildPath(arr_f, v);
        return v;
    }

    public void getAnimationRectangles(Object object0, float[] arr_f) {
        if(this.mMotionLayout.mScene != null) {
            int v = this.mMotionLayout.mScene.getDuration();
            MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(object0);
            if(motionController0 != null) {
                motionController0.buildRectangles(arr_f, v / 16);
            }
        }
    }

    public String getEndState() {
        int v = this.mMotionLayout.getEndState();
        if(this.mLastEndStateId == v) {
            return this.mLastEndState;
        }
        String s = this.mMotionLayout.getConstraintSetNames(v);
        if(s != null) {
            this.mLastEndState = s;
            this.mLastEndStateId = v;
        }
        return s;
    }

    public int getKeyFrameInfo(Object object0, int v, int[] arr_v) {
        MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object0));
        return motionController0 == null ? 0 : motionController0.getKeyFrameInfo(v, arr_v);
    }

    public float getKeyFramePosition(Object object0, int v, float f, float f1) {
        if(!(object0 instanceof View)) {
            return 0.0f;
        }
        MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object0));
        return motionController0 == null ? 0.0f : motionController0.getKeyFrameParameter(v, f, f1);
    }

    public int getKeyFramePositions(Object object0, int[] arr_v, float[] arr_f) {
        MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object0));
        return motionController0 == null ? 0 : motionController0.getKeyFramePositions(arr_v, arr_f);
    }

    public Object getKeyframe(int v, int v1, int v2) {
        return this.mMotionLayout.mScene == null ? null : this.mMotionLayout.mScene.getKeyFrame(this.mMotionLayout.getContext(), v, v1, v2);
    }

    public Object getKeyframe(Object object0, int v, int v1) {
        if(this.mMotionLayout.mScene == null) {
            return null;
        }
        int v2 = ((View)object0).getId();
        return this.mMotionLayout.mScene.getKeyFrame(this.mMotionLayout.getContext(), v, v2, v1);
    }

    public Object getKeyframeAtLocation(Object object0, float f, float f1) {
        if(this.mMotionLayout.mScene == null) {
            return -1;
        }
        if(((View)object0) != null) {
            MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object0));
            if(motionController0 == null) {
                return null;
            }
            ViewGroup viewGroup0 = (ViewGroup)((View)object0).getParent();
            return motionController0.getPositionKeyframe(viewGroup0.getWidth(), viewGroup0.getHeight(), f, f1);
        }
        return null;
    }

    public Boolean getPositionKeyframe(Object object0, Object object1, float f, float f1, String[] arr_s, float[] arr_f) {
        if(object0 instanceof KeyPositionBase) {
            ((MotionController)this.mMotionLayout.mFrameArrayList.get(((View)object1))).positionKeyframe(((View)object1), ((KeyPositionBase)object0), f, f1, arr_s, arr_f);
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.mInTransition = true;
            return true;
        }
        return false;
    }

    public float getProgress() {
        return this.mMotionLayout.getProgress();
    }

    private static int getPxFromDp(int v, String s) {
        if(s == null) {
            return 0;
        }
        int v1 = s.indexOf(100);
        return v1 == -1 ? 0 : ((int)(((float)(((int)Integer.valueOf(s.substring(0, v1))) * v)) / 160.0f));
    }

    public String getStartState() {
        int v = this.mMotionLayout.getStartState();
        if(this.mLastStartStateId == v) {
            return this.mLastStartState;
        }
        String s = this.mMotionLayout.getConstraintSetNames(v);
        if(s != null) {
            this.mLastStartState = s;
            this.mLastStartStateId = v;
        }
        return this.mMotionLayout.getConstraintSetNames(v);
    }

    public String getState() {
        if(this.mLastStartState != null && this.mLastEndState != null) {
            float f = this.getProgress();
            if(f <= 0.01f) {
                return this.mLastStartState;
            }
            return f >= 0.99f ? this.mLastEndState : this.mLastStartState;
        }
        return this.mLastStartState;
    }

    public long getTransitionTimeMs() {
        return this.mMotionLayout.getTransitionTimeMs();
    }

    public boolean isInTransition() {
        return this.mLastStartState != null && this.mLastEndState != null;
    }

    private static void setAbsolutePositions(int v, ConstraintSet constraintSet0, View view0, HashMap hashMap0) {
        String s = (String)hashMap0.get("layout_editor_absoluteX");
        if(s != null) {
            constraintSet0.setEditorAbsoluteX(view0.getId(), DesignTool.getPxFromDp(v, s));
        }
        String s1 = (String)hashMap0.get("layout_editor_absoluteY");
        if(s1 != null) {
            constraintSet0.setEditorAbsoluteY(view0.getId(), DesignTool.getPxFromDp(v, s1));
        }
    }

    public void setAttributes(int v, String s, Object object0, Object object1) {
        HashMap hashMap0 = object1 instanceof HashMap ? ((HashMap)object1) : new HashMap();
        int v1 = this.mMotionLayout.lookUpConstraintId(s);
        ConstraintSet constraintSet0 = this.mMotionLayout.mScene.getConstraintSet(v1);
        if(constraintSet0 == null) {
            return;
        }
        constraintSet0.clear(((View)object0).getId());
        DesignTool.setDimensions(v, constraintSet0, ((View)object0), hashMap0, 0);
        DesignTool.setDimensions(v, constraintSet0, ((View)object0), hashMap0, 1);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 6, 6);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 6, 7);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 7, 7);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 7, 6);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 1, 1);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 1, 2);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 2, 2);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 2, 1);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 3, 3);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 3, 4);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 4, 3);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 4, 4);
        DesignTool.connect(v, constraintSet0, ((View)object0), hashMap0, 5, 5);
        DesignTool.setBias(constraintSet0, ((View)object0), hashMap0, 0);
        DesignTool.setBias(constraintSet0, ((View)object0), hashMap0, 1);
        DesignTool.setAbsolutePositions(v, constraintSet0, ((View)object0), hashMap0);
        this.mMotionLayout.updateState(v1, constraintSet0);
        this.mMotionLayout.requestLayout();
    }

    private static void setBias(ConstraintSet constraintSet0, View view0, HashMap hashMap0, int v) {
        String s = (String)hashMap0.get((v == 1 ? "layout_constraintVertical_bias" : "layout_constraintHorizontal_bias"));
        if(s != null) {
            if(v == 0) {
                constraintSet0.setHorizontalBias(view0.getId(), Float.parseFloat(s));
                return;
            }
            if(v == 1) {
                constraintSet0.setVerticalBias(view0.getId(), Float.parseFloat(s));
            }
        }
    }

    private static void setDimensions(int v, ConstraintSet constraintSet0, View view0, HashMap hashMap0, int v1) {
        String s = (String)hashMap0.get((v1 == 1 ? "layout_height" : "layout_width"));
        if(s != null) {
            int v2 = s.equalsIgnoreCase("wrap_content") ? -2 : DesignTool.getPxFromDp(v, s);
            if(v1 == 0) {
                constraintSet0.constrainWidth(view0.getId(), v2);
                return;
            }
            constraintSet0.constrainHeight(view0.getId(), v2);
        }
    }

    public void setKeyFrame(Object object0, int v, String s, Object object1) {
        if(this.mMotionLayout.mScene != null) {
            this.mMotionLayout.mScene.setKeyframe(((View)object0), v, s, object1);
            this.mMotionLayout.mTransitionGoalPosition = ((float)v) / 100.0f;
            this.mMotionLayout.mTransitionLastPosition = 0.0f;
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.evaluate(true);
        }
    }

    public boolean setKeyFramePosition(Object object0, int v, int v1, float f, float f1) {
        if(!(object0 instanceof View)) {
            return false;
        }
        if(this.mMotionLayout.mScene != null) {
            MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(object0);
            int v2 = (int)(this.mMotionLayout.mTransitionPosition * 100.0f);
            if(motionController0 != null && this.mMotionLayout.mScene.hasKeyFramePosition(((View)object0), v2)) {
                float f2 = motionController0.getKeyFrameParameter(2, f, f1);
                float f3 = motionController0.getKeyFrameParameter(5, f, f1);
                this.mMotionLayout.mScene.setKeyframe(((View)object0), v2, "motion:percentX", f2);
                this.mMotionLayout.mScene.setKeyframe(((View)object0), v2, "motion:percentY", f3);
                this.mMotionLayout.rebuildScene();
                this.mMotionLayout.evaluate(true);
                this.mMotionLayout.invalidate();
                return true;
            }
        }
        return false;
    }

    public void setKeyframe(Object object0, String s, Object object1) {
        if(object0 instanceof Key) {
            ((Key)object0).setValue(s, object1);
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.mInTransition = true;
        }
    }

    public void setState(String s) {
        if(s == null) {
            s = "motion_base";
        }
        if(Objects.equals(this.mLastStartState, s)) {
            return;
        }
        this.mLastStartState = s;
        this.mLastEndState = null;
        if(this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        int v = this.mMotionLayout.lookUpConstraintId(s);
        this.mLastStartStateId = v;
        if(v != 0) {
            if(v == this.mMotionLayout.getStartState()) {
                this.mMotionLayout.setProgress(0.0f);
            }
            else {
                if(v != this.mMotionLayout.getEndState()) {
                    this.mMotionLayout.transitionToState(v);
                }
                this.mMotionLayout.setProgress(1.0f);
            }
        }
        this.mMotionLayout.requestLayout();
    }

    public void setToolPosition(float f) {
        if(this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        this.mMotionLayout.setProgress(f);
        this.mMotionLayout.evaluate(true);
        this.mMotionLayout.requestLayout();
        this.mMotionLayout.invalidate();
    }

    public void setTransition(String s, String s1) {
        if(this.mMotionLayout.mScene == null) {
            this.mMotionLayout.mScene = this.mSceneCache;
        }
        int v = this.mMotionLayout.lookUpConstraintId(s);
        int v1 = this.mMotionLayout.lookUpConstraintId(s1);
        this.mMotionLayout.setTransition(v, v1);
        this.mLastStartStateId = v;
        this.mLastEndStateId = v1;
        this.mLastStartState = s;
        this.mLastEndState = s1;
    }

    public void setViewDebug(Object object0, int v) {
        if(object0 instanceof View) {
            MotionController motionController0 = (MotionController)this.mMotionLayout.mFrameArrayList.get(object0);
            if(motionController0 != null) {
                motionController0.setDrawPath(v);
                this.mMotionLayout.invalidate();
            }
        }
    }
}

