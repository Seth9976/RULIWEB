package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R.styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;

public class CircularFlow extends VirtualLayout {
    private static final String TAG = "CircularFlow";
    private float[] mAngles;
    ConstraintLayout mContainer;
    private int mCountAngle;
    private int mCountRadius;
    private int[] mRadius;
    private String mReferenceAngles;
    private Float mReferenceDefaultAngle;
    private Integer mReferenceDefaultRadius;
    private String mReferenceRadius;
    int mViewCenter;
    private static float sDefaultAngle;
    private static int sDefaultRadius;

    static {
    }

    public CircularFlow(Context context0) {
        super(context0);
    }

    public CircularFlow(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public CircularFlow(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    private void addAngle(String s) {
        if(s != null && s.length() != 0 && this.myContext != null) {
            float[] arr_f = this.mAngles;
            if(arr_f != null) {
                if(this.mCountAngle + 1 > arr_f.length) {
                    this.mAngles = Arrays.copyOf(arr_f, arr_f.length + 1);
                }
                float[] arr_f1 = this.mAngles;
                int v = this.mCountAngle;
                arr_f1[v] = (float)Integer.parseInt(s);
                ++this.mCountAngle;
            }
        }
    }

    private void addRadius(String s) {
        if(s != null && s.length() != 0 && this.myContext != null) {
            int[] arr_v = this.mRadius;
            if(arr_v != null) {
                if(this.mCountRadius + 1 > arr_v.length) {
                    this.mRadius = Arrays.copyOf(arr_v, arr_v.length + 1);
                }
                int[] arr_v1 = this.mRadius;
                int v = this.mCountRadius;
                arr_v1[v] = (int)(((float)Integer.parseInt(s)) * this.myContext.getResources().getDisplayMetrics().density);
                ++this.mCountRadius;
            }
        }
    }

    public void addViewToCircularFlow(View view0, int v, float f) {
        if(this.containsId(view0.getId())) {
            return;
        }
        this.addView(view0);
        ++this.mCountAngle;
        float[] arr_f = this.getAngles();
        this.mAngles = arr_f;
        arr_f[this.mCountAngle - 1] = f;
        ++this.mCountRadius;
        int[] arr_v = this.getRadius();
        this.mRadius = arr_v;
        int v1 = this.mCountRadius - 1;
        arr_v[v1] = (int)(((float)v) * this.myContext.getResources().getDisplayMetrics().density);
        this.anchorReferences();
    }

    private void anchorReferences() {
        this.mContainer = (ConstraintLayout)this.getParent();
        for(int v = 0; v < this.mCount; ++v) {
            View view0 = this.mContainer.getViewById(this.mIds[v]);
            if(view0 != null) {
                int v1 = CircularFlow.sDefaultRadius;
                float f = CircularFlow.sDefaultAngle;
                int[] arr_v = this.mRadius;
                if(arr_v != null && v < arr_v.length) {
                    v1 = arr_v[v];
                }
                else if(this.mReferenceDefaultRadius == null || ((int)this.mReferenceDefaultRadius) == -1) {
                    Log.e("CircularFlow", "Added radius to view with id: " + ((String)this.mMap.get(view0.getId())));
                }
                else {
                    ++this.mCountRadius;
                    if(this.mRadius == null) {
                        this.mRadius = new int[1];
                    }
                    int[] arr_v1 = this.getRadius();
                    this.mRadius = arr_v1;
                    arr_v1[this.mCountRadius - 1] = v1;
                }
                float[] arr_f = this.mAngles;
                if(arr_f != null && v < arr_f.length) {
                    f = arr_f[v];
                }
                else if(this.mReferenceDefaultAngle == null || ((float)this.mReferenceDefaultAngle) == -1.0f) {
                    Log.e("CircularFlow", "Added angle to view with id: " + ((String)this.mMap.get(view0.getId())));
                }
                else {
                    ++this.mCountAngle;
                    if(this.mAngles == null) {
                        this.mAngles = new float[1];
                    }
                    float[] arr_f1 = this.getAngles();
                    this.mAngles = arr_f1;
                    arr_f1[this.mCountAngle - 1] = f;
                }
                LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                constraintLayout$LayoutParams0.circleAngle = f;
                constraintLayout$LayoutParams0.circleConstraint = this.mViewCenter;
                constraintLayout$LayoutParams0.circleRadius = v1;
                view0.setLayoutParams(constraintLayout$LayoutParams0);
            }
        }
        this.applyLayoutFeatures();
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.mAngles, this.mCountAngle);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.mRadius, this.mCountRadius);
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.mViewCenter = typedArray0.getResourceId(v2, 0);
                }
                else if(v2 == styleable.ConstraintLayout_Layout_circularflow_angles) {
                    String s = typedArray0.getString(v2);
                    this.mReferenceAngles = s;
                    this.setAngles(s);
                }
                else if(v2 == styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String s1 = typedArray0.getString(v2);
                    this.mReferenceRadius = s1;
                    this.setRadius(s1);
                }
                else if(v2 == styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float float0 = typedArray0.getFloat(v2, CircularFlow.sDefaultAngle);
                    this.mReferenceDefaultAngle = float0;
                    this.setDefaultAngle(((float)float0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer integer0 = typedArray0.getDimensionPixelSize(v2, CircularFlow.sDefaultRadius);
                    this.mReferenceDefaultRadius = integer0;
                    this.setDefaultRadius(((int)integer0));
                }
            }
            typedArray0.recycle();
        }
    }

    public boolean isUpdatable(View view0) {
        return this.containsId(view0.getId()) ? this.indexFromId(view0.getId()) != -1 : false;
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String s = this.mReferenceAngles;
        if(s != null) {
            this.mAngles = new float[1];
            this.setAngles(s);
        }
        String s1 = this.mReferenceRadius;
        if(s1 != null) {
            this.mRadius = new int[1];
            this.setRadius(s1);
        }
        Float float0 = this.mReferenceDefaultAngle;
        if(float0 != null) {
            this.setDefaultAngle(((float)float0));
        }
        Integer integer0 = this.mReferenceDefaultRadius;
        if(integer0 != null) {
            this.setDefaultRadius(((int)integer0));
        }
        this.anchorReferences();
    }

    private float[] removeAngle(float[] arr_f, int v) {
        return arr_f == null || v < 0 || v >= this.mCountAngle ? arr_f : CircularFlow.removeElementFromArray(arr_f, v);
    }

    private static float[] removeElementFromArray(float[] arr_f, int v) {
        float[] arr_f1 = new float[arr_f.length - 1];
        int v2 = 0;
        for(int v1 = 0; v1 < arr_f.length; ++v1) {
            if(v1 != v) {
                arr_f1[v2] = arr_f[v1];
                ++v2;
            }
        }
        return arr_f1;
    }

    private static int[] removeElementFromArray(int[] arr_v, int v) {
        int[] arr_v1 = new int[arr_v.length - 1];
        int v2 = 0;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            if(v1 != v) {
                arr_v1[v2] = arr_v[v1];
                ++v2;
            }
        }
        return arr_v1;
    }

    private int[] removeRadius(int[] arr_v, int v) {
        return arr_v == null || v < 0 || v >= this.mCountRadius ? arr_v : CircularFlow.removeElementFromArray(arr_v, v);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public int removeView(View view0) {
        int v = super.removeView(view0);
        if(v == -1) {
            return -1;
        }
        ConstraintSet constraintSet0 = new ConstraintSet();
        constraintSet0.clone(this.mContainer);
        constraintSet0.clear(view0.getId(), 8);
        constraintSet0.applyTo(this.mContainer);
        float[] arr_f = this.mAngles;
        if(v < arr_f.length) {
            this.mAngles = this.removeAngle(arr_f, v);
            --this.mCountAngle;
        }
        int[] arr_v = this.mRadius;
        if(v < arr_v.length) {
            this.mRadius = this.removeRadius(arr_v, v);
            --this.mCountRadius;
        }
        this.anchorReferences();
        return v;
    }

    private void setAngles(String s) {
        if(s == null) {
            return;
        }
        int v = 0;
        this.mCountAngle = 0;
        int v1;
        while((v1 = s.indexOf(44, v)) != -1) {
            this.addAngle(s.substring(v, v1).trim());
            v = v1 + 1;
        }
        this.addAngle(s.substring(v).trim());
    }

    public void setDefaultAngle(float f) {
        CircularFlow.sDefaultAngle = f;
    }

    public void setDefaultRadius(int v) {
        CircularFlow.sDefaultRadius = v;
    }

    private void setRadius(String s) {
        if(s == null) {
            return;
        }
        int v = 0;
        this.mCountRadius = 0;
        int v1;
        while((v1 = s.indexOf(44, v)) != -1) {
            this.addRadius(s.substring(v, v1).trim());
            v = v1 + 1;
        }
        this.addRadius(s.substring(v).trim());
    }

    public void updateAngle(View view0, float f) {
        if(!this.isUpdatable(view0)) {
            Log.e("CircularFlow", "It was not possible to update angle to view with id: " + view0.getId());
            return;
        }
        int v = this.indexFromId(view0.getId());
        if(v > this.mAngles.length) {
            return;
        }
        float[] arr_f = this.getAngles();
        this.mAngles = arr_f;
        arr_f[v] = f;
        this.anchorReferences();
    }

    public void updateRadius(View view0, int v) {
        if(!this.isUpdatable(view0)) {
            Log.e("CircularFlow", "It was not possible to update radius to view with id: " + view0.getId());
            return;
        }
        int v1 = this.indexFromId(view0.getId());
        if(v1 > this.mRadius.length) {
            return;
        }
        int[] arr_v = this.getRadius();
        this.mRadius = arr_v;
        arr_v[v1] = (int)(((float)v) * this.myContext.getResources().getDisplayMetrics().density);
        this.anchorReferences();
    }

    public void updateReference(View view0, int v, float f) {
        if(!this.isUpdatable(view0)) {
            Log.e("CircularFlow", "It was not possible to update radius and angle to view with id: " + view0.getId());
            return;
        }
        int v1 = this.indexFromId(view0.getId());
        if(this.getAngles().length > v1) {
            float[] arr_f = this.getAngles();
            this.mAngles = arr_f;
            arr_f[v1] = f;
        }
        if(this.getRadius().length > v1) {
            int[] arr_v = this.getRadius();
            this.mRadius = arr_v;
            arr_v[v1] = (int)(((float)v) * this.myContext.getResources().getDisplayMetrics().density);
        }
        this.anchorReferences();
    }
}

