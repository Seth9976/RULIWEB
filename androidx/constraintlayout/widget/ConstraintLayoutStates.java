package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintLayoutStates {
    static class State {
        int mConstraintID;
        ConstraintSet mConstraintSet;
        int mId;
        ArrayList mVariants;

        State(Context context0, XmlPullParser xmlPullParser0) {
            this.mVariants = new ArrayList();
            this.mConstraintID = -1;
            TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.State);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.State_android_id) {
                    this.mId = typedArray0.getResourceId(v2, this.mId);
                }
                else if(v2 == styleable.State_constraints) {
                    this.mConstraintID = typedArray0.getResourceId(v2, this.mConstraintID);
                    String s = context0.getResources().getResourceTypeName(this.mConstraintID);
                    context0.getResources().getResourceName(this.mConstraintID);
                    if("layout".equals(s)) {
                        ConstraintSet constraintSet0 = new ConstraintSet();
                        this.mConstraintSet = constraintSet0;
                        constraintSet0.clone(context0, this.mConstraintID);
                    }
                }
            }
            typedArray0.recycle();
        }

        void add(Variant constraintLayoutStates$Variant0) {
            this.mVariants.add(constraintLayoutStates$Variant0);
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
        ConstraintSet mConstraintSet;
        int mId;
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
            TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.Variant);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.Variant_constraints) {
                    this.mConstraintID = typedArray0.getResourceId(v2, this.mConstraintID);
                    String s = context0.getResources().getResourceTypeName(this.mConstraintID);
                    context0.getResources().getResourceName(this.mConstraintID);
                    if("layout".equals(s)) {
                        ConstraintSet constraintSet0 = new ConstraintSet();
                        this.mConstraintSet = constraintSet0;
                        constraintSet0.clone(context0, this.mConstraintID);
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
    private final ConstraintLayout mConstraintLayout;
    private SparseArray mConstraintSetMap;
    private ConstraintsChangedListener mConstraintsChangedListener;
    int mCurrentConstraintNumber;
    int mCurrentStateId;
    ConstraintSet mDefaultConstraintSet;
    private SparseArray mStateList;

    ConstraintLayoutStates(Context context0, ConstraintLayout constraintLayout0, int v) {
        this.mCurrentStateId = -1;
        this.mCurrentConstraintNumber = -1;
        this.mStateList = new SparseArray();
        this.mConstraintSetMap = new SparseArray();
        this.mConstraintsChangedListener = null;
        this.mConstraintLayout = constraintLayout0;
        this.load(context0, v);
    }

    private void load(Context context0, int v) {
        XmlResourceParser xmlResourceParser0 = context0.getResources().getXml(v);
        try {
            int v1 = xmlResourceParser0.getEventType();
            State constraintLayoutStates$State0 = null;
            while(true) {
                switch(v1) {
                    case 1: {
                        return;
                    }
                    case 2: {
                        switch(xmlResourceParser0.getName()) {
                            case "ConstraintSet": {
                                this.parseConstraintSet(context0, xmlResourceParser0);
                                break;
                            }
                            case "State": {
                                State constraintLayoutStates$State1 = new State(context0, xmlResourceParser0);
                                this.mStateList.put(constraintLayoutStates$State1.mId, constraintLayoutStates$State1);
                                constraintLayoutStates$State0 = constraintLayoutStates$State1;
                                break;
                            }
                            case "StateSet": {
                                break;
                            }
                            case "Variant": {
                                Variant constraintLayoutStates$Variant0 = new Variant(context0, xmlResourceParser0);
                                if(constraintLayoutStates$State0 != null) {
                                    constraintLayoutStates$State0.add(constraintLayoutStates$Variant0);
                                }
                                break;
                            }
                            case "layoutDescription": {
                            }
                        }
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                    default: {
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + v, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + v, iOException0);
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

    private void parseConstraintSet(Context context0, XmlPullParser xmlPullParser0) {
        ConstraintSet constraintSet0 = new ConstraintSet();
        int v = xmlPullParser0.getAttributeCount();
        for(int v1 = 0; v1 < v; ++v1) {
            String s = xmlPullParser0.getAttributeName(v1);
            String s1 = xmlPullParser0.getAttributeValue(v1);
            if(s != null && s1 != null && "id".equals(s)) {
                int v2 = s1.contains("/") ? context0.getResources().getIdentifier(s1.substring(s1.indexOf(0x2F) + 1), "id", "com.ruliweb.www.ruliapp") : -1;
                if(v2 == -1) {
                    if(s1.length() > 1) {
                        v2 = Integer.parseInt(s1.substring(1));
                    }
                    else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                constraintSet0.load(context0, xmlPullParser0);
                this.mConstraintSetMap.put(v2, constraintSet0);
                return;
            }
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener0) {
        this.mConstraintsChangedListener = constraintsChangedListener0;
    }

    public void updateConstraints(int v, float f, float f1) {
        int v1 = this.mCurrentStateId;
        if(v1 == v) {
            State constraintLayoutStates$State0 = v == -1 ? ((State)this.mStateList.valueAt(0)) : ((State)this.mStateList.get(v1));
            if(this.mCurrentConstraintNumber == -1 || !((Variant)constraintLayoutStates$State0.mVariants.get(this.mCurrentConstraintNumber)).match(f, f1)) {
                int v2 = constraintLayoutStates$State0.findMatch(f, f1);
                if(this.mCurrentConstraintNumber != v2) {
                    ConstraintSet constraintSet0 = v2 == -1 ? this.mDefaultConstraintSet : ((Variant)constraintLayoutStates$State0.mVariants.get(v2)).mConstraintSet;
                    if(v2 != -1) {
                        int v3 = ((Variant)constraintLayoutStates$State0.mVariants.get(v2)).mConstraintID;
                    }
                    if(constraintSet0 != null) {
                        this.mCurrentConstraintNumber = v2;
                        constraintSet0.applyTo(this.mConstraintLayout);
                        if(this.mConstraintsChangedListener != null) {
                        }
                    }
                }
            }
        }
        else {
            this.mCurrentStateId = v;
            State constraintLayoutStates$State1 = (State)this.mStateList.get(v);
            int v4 = constraintLayoutStates$State1.findMatch(f, f1);
            ConstraintSet constraintSet1 = v4 == -1 ? constraintLayoutStates$State1.mConstraintSet : ((Variant)constraintLayoutStates$State1.mVariants.get(v4)).mConstraintSet;
            if(v4 != -1) {
                int v5 = ((Variant)constraintLayoutStates$State1.mVariants.get(v4)).mConstraintID;
            }
            if(constraintSet1 == null) {
                Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + v + ", dim =" + f + ", " + f1);
                return;
            }
            this.mCurrentConstraintNumber = v4;
            constraintSet1.applyTo(this.mConstraintLayout);
        }
    }
}

