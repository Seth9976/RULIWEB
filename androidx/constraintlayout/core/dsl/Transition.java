package androidx.constraintlayout.core.dsl;

public class Transition {
    private final int DEFAULT_DURATION;
    private final float DEFAULT_STAGGER;
    final int UNSET;
    private String mConstraintSetEnd;
    private String mConstraintSetStart;
    private int mDefaultInterpolator;
    private int mDefaultInterpolatorID;
    private String mDefaultInterpolatorString;
    private int mDuration;
    private String mId;
    private KeyFrames mKeyFrames;
    private OnSwipe mOnSwipe;
    private float mStagger;

    public Transition(String s, String s1) {
        this.mOnSwipe = null;
        this.UNSET = -1;
        this.DEFAULT_DURATION = 400;
        this.DEFAULT_STAGGER = 0.0f;
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mDefaultInterpolator = 0;
        this.mDefaultInterpolatorString = null;
        this.mDefaultInterpolatorID = -1;
        this.mDuration = 400;
        this.mStagger = 0.0f;
        this.mKeyFrames = new KeyFrames();
        this.mId = "default";
        this.mConstraintSetStart = s;
        this.mConstraintSetEnd = s1;
    }

    public Transition(String s, String s1, String s2) {
        this.mOnSwipe = null;
        this.UNSET = -1;
        this.DEFAULT_DURATION = 400;
        this.DEFAULT_STAGGER = 0.0f;
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mDefaultInterpolator = 0;
        this.mDefaultInterpolatorString = null;
        this.mDefaultInterpolatorID = -1;
        this.mDuration = 400;
        this.mStagger = 0.0f;
        this.mKeyFrames = new KeyFrames();
        this.mId = s;
        this.mConstraintSetStart = s1;
        this.mConstraintSetEnd = s2;
    }

    public String getId() {
        return this.mId;
    }

    public void setDuration(int v) {
        this.mDuration = v;
    }

    public void setFrom(String s) {
        this.mConstraintSetStart = s;
    }

    public void setId(String s) {
        this.mId = s;
    }

    public void setKeyFrames(Keys keys0) {
        this.mKeyFrames.add(keys0);
    }

    public void setOnSwipe(OnSwipe onSwipe0) {
        this.mOnSwipe = onSwipe0;
    }

    public void setStagger(float f) {
        this.mStagger = f;
    }

    public void setTo(String s) {
        this.mConstraintSetEnd = s;
    }

    String toJson() {
        return this.toString();
    }

    @Override
    public String toString() {
        String s = this.mDuration == 400 ? this.mId + ":{\nfrom:\'" + this.mConstraintSetStart + "\',\nto:\'" + this.mConstraintSetEnd + "\',\n" : this.mId + ":{\nfrom:\'" + this.mConstraintSetStart + "\',\nto:\'" + this.mConstraintSetEnd + "\',\n" + "duration:" + this.mDuration + ",\n";
        if(this.mStagger != 0.0f) {
            s = s + "stagger:" + this.mStagger + ",\n";
        }
        if(this.mOnSwipe != null) {
            s = s + "OnSwipe:{\n},\n";
        }
        return s + "" + "},\n";
    }
}

