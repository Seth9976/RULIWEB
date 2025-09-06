package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.Arrays;
import java.util.HashMap;

public abstract class ConstraintHelper extends View {
    protected static final String CHILD_TAG = "CONSTRAINT_LAYOUT_HELPER_CHILD";
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds;
    protected HashMap mMap;
    protected String mReferenceIds;
    protected String mReferenceTags;
    protected boolean mUseViewMeasure;
    private View[] mViews;
    protected Context myContext;

    public ConstraintHelper(Context context0) {
        super(context0);
        this.mIds = new int[0x20];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = context0;
        this.init(null);
    }

    public ConstraintHelper(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mIds = new int[0x20];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = context0;
        this.init(attributeSet0);
    }

    public ConstraintHelper(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mIds = new int[0x20];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = context0;
        this.init(attributeSet0);
    }

    private void addID(String s) {
        if(s != null && s.length() != 0 && this.myContext != null) {
            String s1 = s.trim();
            int v = this.findId(s1);
            if(v != 0) {
                this.mMap.put(v, s1);
                this.addRscID(v);
                return;
            }
            Log.w("ConstraintHelper", "Could not find id of \"" + s1 + "\"");
        }
    }

    private void addRscID(int v) {
        if(v == this.getId()) {
            return;
        }
        int[] arr_v = this.mIds;
        if(this.mCount + 1 > arr_v.length) {
            this.mIds = Arrays.copyOf(arr_v, arr_v.length * 2);
        }
        int v1 = this.mCount;
        this.mIds[v1] = v;
        this.mCount = v1 + 1;
    }

    private void addTag(String s) {
        if(s != null && s.length() != 0 && this.myContext != null) {
            String s1 = s.trim();
            ConstraintLayout constraintLayout0 = this.getParent() instanceof ConstraintLayout ? ((ConstraintLayout)this.getParent()) : null;
            if(constraintLayout0 == null) {
                Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
                return;
            }
            int v = constraintLayout0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = constraintLayout0.getChildAt(v1);
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                if(viewGroup$LayoutParams0 instanceof LayoutParams && s1.equals(((LayoutParams)viewGroup$LayoutParams0).constraintTag)) {
                    if(view0.getId() == -1) {
                        Log.w("ConstraintHelper", "to use ConstraintTag view " + view0.getClass().getSimpleName() + " must have an ID");
                    }
                    else {
                        this.addRscID(view0.getId());
                    }
                }
            }
        }
    }

    public void addView(View view0) {
        if(view0 == this) {
            return;
        }
        if(view0.getId() == -1) {
            Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have an id");
            return;
        }
        if(view0.getParent() == null) {
            Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have a parent");
            return;
        }
        this.mReferenceIds = null;
        this.addRscID(view0.getId());
        this.requestLayout();
    }

    public void applyHelperParams() {
    }

    protected void applyLayoutFeatures() {
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 != null && viewParent0 instanceof ConstraintLayout) {
            this.applyLayoutFeatures(((ConstraintLayout)viewParent0));
        }
    }

    protected void applyLayoutFeatures(ConstraintLayout constraintLayout0) {
        int v = this.getVisibility();
        float f = this.getElevation();
        for(int v1 = 0; v1 < this.mCount; ++v1) {
            View view0 = constraintLayout0.getViewById(this.mIds[v1]);
            if(view0 != null) {
                view0.setVisibility(v);
                if(f > 0.0f) {
                    view0.setTranslationZ(view0.getTranslationZ() + f);
                }
            }
        }
    }

    protected void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout0) {
    }

    public boolean containsId(int v) {
        int[] arr_v = this.mIds;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            if(arr_v[v1] == v) {
                return true;
            }
        }
        return false;
    }

    private int[] convertReferenceString(String s) {
        String[] arr_s = s.split(",");
        int[] arr_v = new int[arr_s.length];
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            int v2 = this.findId(arr_s[v].trim());
            if(v2 != 0) {
                arr_v[v1] = v2;
                ++v1;
            }
        }
        return v1 == arr_s.length ? arr_v : Arrays.copyOf(arr_v, v1);
    }

    private int findId(ConstraintLayout constraintLayout0, String s) {
        if(s != null && constraintLayout0 != null) {
            Resources resources0 = this.myContext.getResources();
            if(resources0 == null) {
                return 0;
            }
            int v = constraintLayout0.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = constraintLayout0.getChildAt(v1);
                if(view0.getId() != -1) {
                    try {
                        String s1 = null;
                        s1 = resources0.getResourceEntryName(view0.getId());
                    }
                    catch(Resources.NotFoundException unused_ex) {
                    }
                    if(s.equals(s1)) {
                        return view0.getId();
                    }
                }
            }
        }
        return 0;
    }

    private int findId(String s) {
        ConstraintLayout constraintLayout0 = this.getParent() instanceof ConstraintLayout ? ((ConstraintLayout)this.getParent()) : null;
        int v = 0;
        if(this.isInEditMode() && constraintLayout0 != null) {
            Object object0 = constraintLayout0.getDesignInformation(0, s);
            if(object0 instanceof Integer) {
                v = (int)(((Integer)object0));
            }
        }
        if(v == 0 && constraintLayout0 != null) {
            v = this.findId(constraintLayout0, s);
        }
        if(v == 0) {
            try {
                v = id.class.getField(s).getInt(null);
            }
            catch(Exception unused_ex) {
            }
        }
        return v == 0 ? this.myContext.getResources().getIdentifier(s, "id", "com.ruliweb.www.ruliapp") : v;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    protected View[] getViews(ConstraintLayout constraintLayout0) {
        if(this.mViews == null || this.mViews.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for(int v = 0; v < this.mCount; ++v) {
            View[] arr_view = this.mViews;
            arr_view[v] = constraintLayout0.getViewById(this.mIds[v]);
        }
        return this.mViews;
    }

    public int indexFromId(int v) {
        int[] arr_v = this.mIds;
        int v1 = -1;
        for(int v2 = 0; v2 < arr_v.length; ++v2) {
            ++v1;
            if(arr_v[v2] == v) {
                return v1;
            }
        }
        return v1;
    }

    protected void init(AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String s = typedArray0.getString(v2);
                    this.mReferenceIds = s;
                    this.setIds(s);
                }
                else if(v2 == styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String s1 = typedArray0.getString(v2);
                    this.mReferenceTags = s1;
                    this.setReferenceTags(s1);
                }
            }
            typedArray0.recycle();
        }
    }

    public static boolean isChildOfHelper(View view0) {
        return "CONSTRAINT_LAYOUT_HELPER_CHILD" == view0.getTag();
    }

    public void loadParameters(Constraint constraintSet$Constraint0, HelperWidget helperWidget0, LayoutParams constraintLayout$LayoutParams0, SparseArray sparseArray0) {
        if(constraintSet$Constraint0.layout.mReferenceIds != null) {
            this.setReferencedIds(constraintSet$Constraint0.layout.mReferenceIds);
        }
        else if(constraintSet$Constraint0.layout.mReferenceIdString != null) {
            constraintSet$Constraint0.layout.mReferenceIds = constraintSet$Constraint0.layout.mReferenceIdString.length() > 0 ? this.convertReferenceString(constraintSet$Constraint0.layout.mReferenceIdString) : null;
        }
        if(helperWidget0 != null) {
            helperWidget0.removeAllIds();
            if(constraintSet$Constraint0.layout.mReferenceIds != null) {
                for(int v = 0; v < constraintSet$Constraint0.layout.mReferenceIds.length; ++v) {
                    ConstraintWidget constraintWidget0 = (ConstraintWidget)sparseArray0.get(constraintSet$Constraint0.layout.mReferenceIds[v]);
                    if(constraintWidget0 != null) {
                        helperWidget0.add(constraintWidget0);
                    }
                }
            }
        }
    }

    @Override  // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String s = this.mReferenceIds;
        if(s != null) {
            this.setIds(s);
        }
        String s1 = this.mReferenceTags;
        if(s1 != null) {
            this.setReferenceTags(s1);
        }
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        if(this.mUseViewMeasure) {
            super.onMeasure(v, v1);
            return;
        }
        this.setMeasuredDimension(0, 0);
    }

    public int removeView(View view0) {
        int v4;
        int v = view0.getId();
        int v1 = -1;
        if(v == -1) {
            return -1;
        }
        this.mReferenceIds = null;
        for(int v2 = 0; v2 < this.mCount; ++v2) {
            if(this.mIds[v2] == v) {
                for(int v3 = v2; true; ++v3) {
                    v4 = this.mCount;
                    if(v3 >= v4 - 1) {
                        break;
                    }
                    this.mIds[v3] = this.mIds[v3 + 1];
                }
                this.mIds[v4 - 1] = 0;
                this.mCount = v4 - 1;
                v1 = v2;
                break;
            }
        }
        this.requestLayout();
        return v1;
    }

    public void resolveRtl(ConstraintWidget constraintWidget0, boolean z) {
    }

    protected void setIds(String s) {
        this.mReferenceIds = s;
        if(s == null) {
            return;
        }
        int v = 0;
        this.mCount = 0;
        int v1;
        while((v1 = s.indexOf(44, v)) != -1) {
            this.addID(s.substring(v, v1));
            v = v1 + 1;
        }
        this.addID(s.substring(v));
    }

    protected void setReferenceTags(String s) {
        this.mReferenceTags = s;
        if(s == null) {
            return;
        }
        int v = 0;
        this.mCount = 0;
        int v1;
        while((v1 = s.indexOf(44, v)) != -1) {
            this.addTag(s.substring(v, v1));
            v = v1 + 1;
        }
        this.addTag(s.substring(v));
    }

    public void setReferencedIds(int[] arr_v) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for(int v = 0; v < arr_v.length; ++v) {
            this.addRscID(arr_v[v]);
        }
    }

    @Override  // android.view.View
    public void setTag(int v, Object object0) {
        super.setTag(v, object0);
        if(object0 == null && this.mReferenceIds == null) {
            this.addRscID(v);
        }
    }

    public void updatePostConstraints(ConstraintLayout constraintLayout0) {
    }

    public void updatePostLayout(ConstraintLayout constraintLayout0) {
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout0) {
    }

    public void updatePreDraw(ConstraintLayout constraintLayout0) {
    }

    public void updatePreLayout(ConstraintWidgetContainer constraintWidgetContainer0, Helper helper0, SparseArray sparseArray0) {
        helper0.removeAllIds();
        for(int v = 0; v < this.mCount; ++v) {
            helper0.add(((ConstraintWidget)sparseArray0.get(this.mIds[v])));
        }
    }

    public void updatePreLayout(ConstraintLayout constraintLayout0) {
        if(this.isInEditMode()) {
            this.setIds(this.mReferenceIds);
        }
        Helper helper0 = this.mHelperWidget;
        if(helper0 == null) {
            return;
        }
        helper0.removeAllIds();
        for(int v = 0; v < this.mCount; ++v) {
            int v1 = this.mIds[v];
            View view0 = constraintLayout0.getViewById(v1);
            if(view0 == null) {
                String s = (String)this.mMap.get(v1);
                int v2 = this.findId(constraintLayout0, s);
                if(v2 != 0) {
                    this.mIds[v] = v2;
                    this.mMap.put(v2, s);
                    view0 = constraintLayout0.getViewById(v2);
                }
            }
            if(view0 != null) {
                this.mHelperWidget.add(constraintLayout0.getViewWidget(view0));
            }
        }
        this.mHelperWidget.updateConstraints(constraintLayout0.mLayoutWidget);
    }

    public void validateParams() {
        if(this.mHelperWidget != null) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
            if(viewGroup$LayoutParams0 instanceof LayoutParams) {
                ((LayoutParams)viewGroup$LayoutParams0).mWidget = (ConstraintWidget)this.mHelperWidget;
            }
        }
    }
}

