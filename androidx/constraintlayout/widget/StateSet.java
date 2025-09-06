package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StateSet {
    static class State {
        int mConstraintID;
        int mId;
        boolean mIsLayout;
        ArrayList mVariants;

        State(Context context0, XmlPullParser xmlPullParser0) {
            this.mVariants = new ArrayList();
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.State);
            int v1 = typedArray0.getIndexCount();
            for(int v = 0; v < v1; ++v) {
                int v2 = typedArray0.getIndex(v);
                if(v2 == styleable.State_android_id) {
                    this.mId = typedArray0.getResourceId(v2, this.mId);
                }
                else if(v2 == styleable.State_constraints) {
                    this.mConstraintID = typedArray0.getResourceId(v2, this.mConstraintID);
                    String s = context0.getResources().getResourceTypeName(this.mConstraintID);
                    context0.getResources().getResourceName(this.mConstraintID);
                    if("layout".equals(s)) {
                        this.mIsLayout = true;
                    }
                }
            }
            typedArray0.recycle();
        }

        void add(Variant stateSet$Variant0) {
            this.mVariants.add(stateSet$Variant0);
        }

        public int findMatch(float f, float f1) {
            for(int v = 0; v < this.mVariants.size(); ++v) {
                if(((Variant)this.mVariants.get(v)).match(f, f1)) {
                    return v;
                }
            }
            return -1;
        }
    }

    static class Variant {
        int mConstraintID;
        int mId;
        boolean mIsLayout;
        float mMaxHeight;
        float mMaxWidth;
        float mMinHeight;
        float mMinWidth;

        Variant(Context context0, XmlPullParser xmlPullParser0) {
            this.mMinWidth = NaNf;
            this.mMinHeight = NaNf;
            this.mMaxWidth = NaNf;
            this.mMaxHeight = NaNf;
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.Variant);
            int v1 = typedArray0.getIndexCount();
            for(int v = 0; v < v1; ++v) {
                int v2 = typedArray0.getIndex(v);
                if(v2 == styleable.Variant_constraints) {
                    this.mConstraintID = typedArray0.getResourceId(v2, this.mConstraintID);
                    String s = context0.getResources().getResourceTypeName(this.mConstraintID);
                    context0.getResources().getResourceName(this.mConstraintID);
                    if("layout".equals(s)) {
                        this.mIsLayout = true;
                    }
                }
                else if(v2 == styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = typedArray0.getDimension(v2, this.mMaxHeight);
                }
                else if(v2 == styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = typedArray0.getDimension(v2, this.mMinHeight);
                }
                else if(v2 == styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = typedArray0.getDimension(v2, this.mMaxWidth);
                }
                else if(v2 == styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = typedArray0.getDimension(v2, this.mMinWidth);
                }
                else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArray0.recycle();
        }

        boolean match(float f, float f1) {
            if(!Float.isNaN(this.mMinWidth) && f < this.mMinWidth) {
                return false;
            }
            if(!Float.isNaN(this.mMinHeight) && f1 < this.mMinHeight) {
                return false;
            }
            return Float.isNaN(this.mMaxWidth) || f <= this.mMaxWidth ? Float.isNaN(this.mMaxHeight) || f1 <= this.mMaxHeight : false;
        }
    }

    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    private ConstraintsChangedListener mConstraintsChangedListener;
    int mCurrentConstraintNumber;
    int mCurrentStateId;
    int mDefaultState;
    private SparseArray mStateList;

    public StateSet(Context context0, XmlPullParser xmlPullParser0) {
        this.mDefaultState = -1;
        this.mCurrentStateId = -1;
        this.mCurrentConstraintNumber = -1;
        this.mStateList = new SparseArray();
        this.mConstraintsChangedListener = null;
        this.load(context0, xmlPullParser0);
    }

    public int convertToConstraintSet(int v, int v1, float f, float f1) {
        State stateSet$State0 = (State)this.mStateList.get(v1);
        if(stateSet$State0 == null) {
            return v1;
        }
        if(f != -1.0f && f1 != -1.0f) {
            Variant stateSet$Variant0 = null;
            for(Object object0: stateSet$State0.mVariants) {
                Variant stateSet$Variant1 = (Variant)object0;
                if(stateSet$Variant1.match(f, f1)) {
                    if(v == stateSet$Variant1.mConstraintID) {
                        return v;
                    }
                    stateSet$Variant0 = stateSet$Variant1;
                }
            }
            return stateSet$Variant0 == null ? stateSet$State0.mConstraintID : stateSet$Variant0.mConstraintID;
        }
        else if(stateSet$State0.mConstraintID != v) {
            for(Object object1: stateSet$State0.mVariants) {
                if(v != ((Variant)object1).mConstraintID) {
                    continue;
                }
                return v;
            }
            return stateSet$State0.mConstraintID;
        }
        return v;
    }

    private void load(Context context0, XmlPullParser xmlPullParser0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.StateSet);
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.StateSet_defaultState) {
                this.mDefaultState = typedArray0.getResourceId(v2, this.mDefaultState);
            }
        }
        typedArray0.recycle();
        try {
            int v3 = xmlPullParser0.getEventType();
            State stateSet$State0 = null;
            while(v3 != 1) {
            alab1:
                switch(v3) {
                    case 2: {
                        switch(xmlPullParser0.getName()) {
                            case "LayoutDescription": {
                                break alab1;
                            }
                            case "State": {
                                stateSet$State0 = new State(context0, xmlPullParser0);
                                this.mStateList.put(stateSet$State0.mId, stateSet$State0);
                                break alab1;
                            }
                            case "StateSet": {
                                break alab1;
                            }
                            case "Variant": {
                                Variant stateSet$Variant0 = new Variant(context0, xmlPullParser0);
                                if(stateSet$State0 != null) {
                                    stateSet$State0.add(stateSet$Variant0);
                                }
                                break alab1;
                            }
                            default: {
                                break alab1;
                            }
                        }
                    }
                    case 3: {
                        if("StateSet".equals(xmlPullParser0.getName())) {
                            return;
                        }
                    }
                }
                v3 = xmlPullParser0.next();
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ConstraintLayoutStates", "Error parsing XML resource", xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ConstraintLayoutStates", "Error parsing XML resource", iOException0);
        }
    }

    public boolean needsToChange(int v, float f, float f1) {
        int v1 = this.mCurrentStateId;
        if(v1 != v) {
            return true;
        }
        Object object0 = v == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(v1);
        return this.mCurrentConstraintNumber == -1 || !((Variant)((State)object0).mVariants.get(this.mCurrentConstraintNumber)).match(f, f1) ? this.mCurrentConstraintNumber != ((State)object0).findMatch(f, f1) : false;
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener0) {
        this.mConstraintsChangedListener = constraintsChangedListener0;
    }

    public int stateGetConstraintID(int v, int v1, int v2) {
        return this.updateConstraints(-1, v, ((float)v1), ((float)v2));
    }

    public int updateConstraints(int v, int v1, float f, float f1) {
        if(v == v1) {
            State stateSet$State0 = v1 == -1 ? ((State)this.mStateList.valueAt(0)) : ((State)this.mStateList.get(this.mCurrentStateId));
            if(stateSet$State0 == null) {
                return -1;
            }
            if(this.mCurrentConstraintNumber == -1 || !((Variant)stateSet$State0.mVariants.get(v)).match(f, f1)) {
                int v2 = stateSet$State0.findMatch(f, f1);
                if(v != v2) {
                    return v2 == -1 ? stateSet$State0.mConstraintID : ((Variant)stateSet$State0.mVariants.get(v2)).mConstraintID;
                }
            }
            return v;
        }
        State stateSet$State1 = (State)this.mStateList.get(v1);
        if(stateSet$State1 == null) {
            return -1;
        }
        int v3 = stateSet$State1.findMatch(f, f1);
        return v3 == -1 ? stateSet$State1.mConstraintID : ((Variant)stateSet$State1.mVariants.get(v3)).mConstraintID;
    }
}

