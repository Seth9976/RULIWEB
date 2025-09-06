package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R.id;
import androidx.constraintlayout.widget.R.styleable;
import androidx.core.view.NestedScrollingParent3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    class DecelerateInterpolator extends MotionInterpolator {
        float mCurrentP;
        float mInitialV;
        float mMaxA;

        DecelerateInterpolator() {
            this.mInitialV = 0.0f;
            this.mCurrentP = 0.0f;
        }

        public void config(float f, float f1, float f2) {
            this.mInitialV = f;
            this.mCurrentP = f1;
            this.mMaxA = f2;
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getInterpolation(float f) {
            float f1 = this.mInitialV;
            if(f1 > 0.0f) {
                float f2 = this.mMaxA;
                if(f1 / f2 < f) {
                    f = f1 / f2;
                }
                MotionLayout.this.mLastVelocity = f1 - f2 * f;
                return this.mInitialV * f - this.mMaxA * f * f / 2.0f + this.mCurrentP;
            }
            float f3 = this.mMaxA;
            if(-f1 / f3 < f) {
                f = -f1 / f3;
            }
            MotionLayout.this.mLastVelocity = f1 + f3 * f;
            return this.mInitialV * f + this.mMaxA * f * f / 2.0f + this.mCurrentP;
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.mLastVelocity;
        }
    }

    class DevModeDraw {
        private static final int DEBUG_PATH_TICKS_PER_MS = 16;
        Rect mBounds;
        DashPathEffect mDashPathEffect;
        final int mDiamondSize;
        Paint mFillPaint;
        final int mGraphColor;
        int mKeyFrameCount;
        float[] mKeyFramePoints;
        final int mKeyframeColor;
        Paint mPaint;
        Paint mPaintGraph;
        Paint mPaintKeyframes;
        Path mPath;
        int[] mPathMode;
        float[] mPoints;
        boolean mPresentationMode;
        private float[] mRectangle;
        final int mRedColor;
        final int mShadowColor;
        int mShadowTranslate;
        Paint mTextPaint;

        DevModeDraw() {
            this.mRedColor = 0xFFFFAA33;
            this.mKeyframeColor = -2067046;
            this.mGraphColor = 0xFF33AA00;
            this.mShadowColor = 0x77000000;
            this.mDiamondSize = 10;
            this.mBounds = new Rect();
            this.mPresentationMode = false;
            this.mShadowTranslate = 1;
            Paint paint0 = new Paint();
            this.mPaint = paint0;
            paint0.setAntiAlias(true);
            this.mPaint.setColor(0xFFFFAA33);
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setStyle(Paint.Style.STROKE);
            Paint paint1 = new Paint();
            this.mPaintKeyframes = paint1;
            paint1.setAntiAlias(true);
            this.mPaintKeyframes.setColor(-2067046);
            this.mPaintKeyframes.setStrokeWidth(2.0f);
            this.mPaintKeyframes.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.mPaintGraph = paint2;
            paint2.setAntiAlias(true);
            this.mPaintGraph.setColor(0xFF33AA00);
            this.mPaintGraph.setStrokeWidth(2.0f);
            this.mPaintGraph.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.mTextPaint = paint3;
            paint3.setAntiAlias(true);
            this.mTextPaint.setColor(0xFF33AA00);
            this.mTextPaint.setTextSize(motionLayout0.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            Paint paint4 = new Paint();
            this.mFillPaint = paint4;
            paint4.setAntiAlias(true);
            DashPathEffect dashPathEffect0 = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.mDashPathEffect = dashPathEffect0;
            this.mPaintGraph.setPathEffect(dashPathEffect0);
            this.mKeyFramePoints = new float[100];
            this.mPathMode = new int[50];
            if(this.mPresentationMode) {
                this.mPaint.setStrokeWidth(8.0f);
                this.mFillPaint.setStrokeWidth(8.0f);
                this.mPaintKeyframes.setStrokeWidth(8.0f);
                this.mShadowTranslate = 4;
            }
        }

        public void draw(Canvas canvas0, HashMap hashMap0, int v, int v1) {
            if(hashMap0 != null && hashMap0.size() != 0) {
                canvas0.save();
                if(!MotionLayout.this.isInEditMode() && (v1 & 1) == 2) {
                    String s = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState) + ":" + MotionLayout.this.getProgress();
                    canvas0.drawText(s, 10.0f, ((float)(MotionLayout.this.getHeight() - 30)), this.mTextPaint);
                    canvas0.drawText(s, 11.0f, ((float)(MotionLayout.this.getHeight() - 29)), this.mPaint);
                }
                for(Object object0: hashMap0.values()) {
                    MotionController motionController0 = (MotionController)object0;
                    int v2 = motionController0.getDrawPath();
                    if(v1 > 0 && v2 == 0) {
                        v2 = 1;
                    }
                    if(v2 != 0) {
                        this.mKeyFrameCount = motionController0.buildKeyFrames(this.mKeyFramePoints, this.mPathMode);
                        if(v2 >= 1) {
                            if(this.mPoints == null || this.mPoints.length != v / 16 * 2) {
                                this.mPoints = new float[v / 16 * 2];
                                this.mPath = new Path();
                            }
                            canvas0.translate(((float)this.mShadowTranslate), ((float)this.mShadowTranslate));
                            this.mPaint.setColor(0x77000000);
                            this.mFillPaint.setColor(0x77000000);
                            this.mPaintKeyframes.setColor(0x77000000);
                            this.mPaintGraph.setColor(0x77000000);
                            motionController0.buildPath(this.mPoints, v / 16);
                            this.drawAll(canvas0, v2, this.mKeyFrameCount, motionController0);
                            this.mPaint.setColor(0xFFFFAA33);
                            this.mPaintKeyframes.setColor(-2067046);
                            this.mFillPaint.setColor(-2067046);
                            this.mPaintGraph.setColor(0xFF33AA00);
                            canvas0.translate(((float)(-this.mShadowTranslate)), ((float)(-this.mShadowTranslate)));
                            this.drawAll(canvas0, v2, this.mKeyFrameCount, motionController0);
                            if(v2 == 5) {
                                this.drawRectangle(canvas0, motionController0);
                            }
                        }
                    }
                }
                canvas0.restore();
            }
        }

        public void drawAll(Canvas canvas0, int v, int v1, MotionController motionController0) {
            if(v == 4) {
                this.drawPathAsConfigured(canvas0);
            }
            if(v == 2) {
                this.drawPathRelative(canvas0);
            }
            if(v == 3) {
                this.drawPathCartesian(canvas0);
            }
            this.drawBasicPath(canvas0);
            this.drawTicks(canvas0, v, v1, motionController0);
        }

        private void drawBasicPath(Canvas canvas0) {
            canvas0.drawLines(this.mPoints, this.mPaint);
        }

        private void drawPathAsConfigured(Canvas canvas0) {
            boolean z = false;
            boolean z1 = false;
            for(int v = 0; v < this.mKeyFrameCount; ++v) {
                int v1 = this.mPathMode[v];
                if(v1 == 1) {
                    z = true;
                }
                if(v1 == 0) {
                    z1 = true;
                }
            }
            if(z) {
                this.drawPathRelative(canvas0);
            }
            if(z1) {
                this.drawPathCartesian(canvas0);
            }
        }

        private void drawPathCartesian(Canvas canvas0) {
            float[] arr_f = this.mPoints;
            float f = arr_f[0];
            float f1 = arr_f[1];
            float f2 = arr_f[arr_f.length - 2];
            float f3 = arr_f[arr_f.length - 1];
            canvas0.drawLine(Math.min(f, f2), Math.max(f1, f3), Math.max(f, f2), Math.max(f1, f3), this.mPaintGraph);
            canvas0.drawLine(Math.min(f, f2), Math.min(f1, f3), Math.min(f, f2), Math.max(f1, f3), this.mPaintGraph);
        }

        private void drawPathCartesianTicks(Canvas canvas0, float f, float f1) {
            float[] arr_f = this.mPoints;
            float f2 = arr_f[0];
            float f3 = arr_f[1];
            float f4 = arr_f[arr_f.length - 2];
            float f5 = arr_f[arr_f.length - 1];
            float f6 = f - Math.min(f2, f4);
            float f7 = Math.max(f3, f5) - f1;
            String s = "" + ((float)(((int)(((double)(f6 * 100.0f / Math.abs(f4 - f2))) + 0.5)))) / 100.0f;
            this.getTextBounds(s, this.mTextPaint);
            canvas0.drawText(s, f6 / 2.0f - ((float)(this.mBounds.width() / 2)) + Math.min(f2, f4), f1 - 20.0f, this.mTextPaint);
            canvas0.drawLine(f, f1, Math.min(f2, f4), f1, this.mPaintGraph);
            String s1 = "" + ((float)(((int)(((double)(f7 * 100.0f / Math.abs(f5 - f3))) + 0.5)))) / 100.0f;
            this.getTextBounds(s1, this.mTextPaint);
            canvas0.drawText(s1, f + 5.0f, Math.max(f3, f5) - (f7 / 2.0f - ((float)(this.mBounds.height() / 2))), this.mTextPaint);
            canvas0.drawLine(f, f1, f, Math.max(f3, f5), this.mPaintGraph);
        }

        private void drawPathRelative(Canvas canvas0) {
            canvas0.drawLine(this.mPoints[0], this.mPoints[1], this.mPoints[this.mPoints.length - 2], this.mPoints[this.mPoints.length - 1], this.mPaintGraph);
        }

        private void drawPathRelativeTicks(Canvas canvas0, float f, float f1) {
            float[] arr_f = this.mPoints;
            float f2 = arr_f[0];
            float f3 = arr_f[1];
            float f4 = arr_f[arr_f.length - 2];
            float f5 = arr_f[arr_f.length - 1];
            float f6 = (float)Math.hypot(f2 - f4, f3 - f5);
            float f7 = f4 - f2;
            float f8 = f5 - f3;
            float f9 = ((f - f2) * f7 + (f1 - f3) * f8) / (f6 * f6);
            float f10 = f2 + f7 * f9;
            float f11 = f3 + f9 * f8;
            Path path0 = new Path();
            path0.moveTo(f, f1);
            path0.lineTo(f10, f11);
            float f12 = (float)Math.hypot(f10 - f, f11 - f1);
            String s = "" + ((float)(((int)(f12 * 100.0f / f6)))) / 100.0f;
            this.getTextBounds(s, this.mTextPaint);
            canvas0.drawTextOnPath(s, path0, f12 / 2.0f - ((float)(this.mBounds.width() / 2)), -20.0f, this.mTextPaint);
            canvas0.drawLine(f, f1, f10, f11, this.mPaintGraph);
        }

        private void drawPathScreenTicks(Canvas canvas0, float f, float f1, int v, int v1) {
            String s = "" + ((float)(((int)(((double)((f - ((float)(v / 2))) * 100.0f / ((float)(MotionLayout.this.getWidth() - v)))) + 0.5)))) / 100.0f;
            this.getTextBounds(s, this.mTextPaint);
            canvas0.drawText(s, f / 2.0f - ((float)(this.mBounds.width() / 2)) + 0.0f, f1 - 20.0f, this.mTextPaint);
            canvas0.drawLine(f, f1, 0.0f, f1, this.mPaintGraph);
            String s1 = "" + ((float)(((int)(((double)((f1 - ((float)(v1 / 2))) * 100.0f / ((float)(MotionLayout.this.getHeight() - v1)))) + 0.5)))) / 100.0f;
            this.getTextBounds(s1, this.mTextPaint);
            canvas0.drawText(s1, f + 5.0f, 0.0f - (f1 / 2.0f - ((float)(this.mBounds.height() / 2))), this.mTextPaint);
            canvas0.drawLine(f, f1, f, 1.0f, this.mPaintGraph);
        }

        private void drawRectangle(Canvas canvas0, MotionController motionController0) {
            this.mPath.reset();
            for(int v = 0; v <= 50; ++v) {
                motionController0.buildRect(((float)v) / 50.0f, this.mRectangle, 0);
                this.mPath.moveTo(this.mRectangle[0], this.mRectangle[1]);
                this.mPath.lineTo(this.mRectangle[2], this.mRectangle[3]);
                this.mPath.lineTo(this.mRectangle[4], this.mRectangle[5]);
                this.mPath.lineTo(this.mRectangle[6], this.mRectangle[7]);
                this.mPath.close();
            }
            this.mPaint.setColor(0x44000000);
            canvas0.translate(2.0f, 2.0f);
            canvas0.drawPath(this.mPath, this.mPaint);
            canvas0.translate(-2.0f, -2.0f);
            this.mPaint.setColor(0xFFFF0000);
            canvas0.drawPath(this.mPath, this.mPaint);
        }

        private void drawTicks(Canvas canvas0, int v, int v1, MotionController motionController0) {
            int v3;
            int v2;
            if(motionController0.mView == null) {
                v2 = 0;
                v3 = 0;
            }
            else {
                v2 = motionController0.mView.getWidth();
                v3 = motionController0.mView.getHeight();
            }
            for(int v4 = 1; v4 < v1 - 1; ++v4) {
                if(v != 4 || this.mPathMode[v4 - 1] != 0) {
                    float[] arr_f = this.mKeyFramePoints;
                    float f = arr_f[v4 * 2];
                    float f1 = arr_f[v4 * 2 + 1];
                    this.mPath.reset();
                    this.mPath.moveTo(f, f1 + 10.0f);
                    this.mPath.lineTo(f + 10.0f, f1);
                    this.mPath.lineTo(f, f1 - 10.0f);
                    this.mPath.lineTo(f - 10.0f, f1);
                    this.mPath.close();
                    motionController0.getKeyFrame(v4 - 1);
                    if(v == 4) {
                        int v5 = this.mPathMode[v4 - 1];
                        if(v5 == 1) {
                            this.drawPathRelativeTicks(canvas0, f - 0.0f, f1 - 0.0f);
                        }
                        else if(v5 == 0) {
                            this.drawPathCartesianTicks(canvas0, f - 0.0f, f1 - 0.0f);
                        }
                        else if(v5 == 2) {
                            this.drawPathScreenTicks(canvas0, f - 0.0f, f1 - 0.0f, v2, v3);
                        }
                        canvas0.drawPath(this.mPath, this.mFillPaint);
                    }
                    if(v == 2) {
                        this.drawPathRelativeTicks(canvas0, f - 0.0f, f1 - 0.0f);
                    }
                    if(v == 3) {
                        this.drawPathCartesianTicks(canvas0, f - 0.0f, f1 - 0.0f);
                    }
                    if(v == 6) {
                        this.drawPathScreenTicks(canvas0, f - 0.0f, f1 - 0.0f, v2, v3);
                    }
                    canvas0.drawPath(this.mPath, this.mFillPaint);
                }
            }
            float[] arr_f1 = this.mPoints;
            if(arr_f1.length > 1) {
                canvas0.drawCircle(arr_f1[0], arr_f1[1], 8.0f, this.mPaintKeyframes);
                canvas0.drawCircle(this.mPoints[this.mPoints.length - 2], this.mPoints[this.mPoints.length - 1], 8.0f, this.mPaintKeyframes);
            }
        }

        private void drawTranslation(Canvas canvas0, float f, float f1, float f2, float f3) {
            canvas0.drawRect(f, f1, f2, f3, this.mPaintGraph);
            canvas0.drawLine(f, f1, f2, f3, this.mPaintGraph);
        }

        void getTextBounds(String s, Paint paint0) {
            paint0.getTextBounds(s, 0, s.length(), this.mBounds);
        }
    }

    class Model {
        ConstraintSet mEnd;
        int mEndId;
        ConstraintWidgetContainer mLayoutEnd;
        ConstraintWidgetContainer mLayoutStart;
        ConstraintSet mStart;
        int mStartId;

        Model() {
            this.mLayoutStart = new ConstraintWidgetContainer();
            this.mLayoutEnd = new ConstraintWidgetContainer();
            this.mStart = null;
            this.mEnd = null;
        }

        public void build() {
            int v = MotionLayout.this.getChildCount();
            MotionLayout.this.mFrameArrayList.clear();
            SparseArray sparseArray0 = new SparseArray();
            int[] arr_v = new int[v];
            for(int v2 = 0; v2 < v; ++v2) {
                View view0 = MotionLayout.this.getChildAt(v2);
                MotionController motionController0 = new MotionController(view0);
                int v3 = view0.getId();
                arr_v[v2] = v3;
                sparseArray0.put(v3, motionController0);
                MotionLayout.this.mFrameArrayList.put(view0, motionController0);
            }
            for(int v4 = 0; v4 < v; ++v4) {
                View view1 = MotionLayout.this.getChildAt(v4);
                MotionController motionController1 = (MotionController)MotionLayout.this.mFrameArrayList.get(view1);
                if(motionController1 != null) {
                    if(this.mStart != null) {
                        ConstraintWidget constraintWidget0 = this.getWidget(this.mLayoutStart, view1);
                        if(constraintWidget0 != null) {
                            motionController1.setStartState(MotionLayout.this.toRect(constraintWidget0), this.mStart, MotionLayout.this.getWidth(), MotionLayout.this.getHeight());
                        }
                        else if(MotionLayout.this.mDebugPath != 0) {
                            Log.e("MotionLayout", ".(null:-1)no widget for  " + Debug.getName(view1) + " (" + view1.getClass().getName() + ")");
                        }
                    }
                    else if(MotionLayout.this.mInRotation) {
                        motionController1.setStartState(((ViewState)MotionLayout.this.mPreRotate.get(view1)), view1, MotionLayout.this.mRotatMode, MotionLayout.this.mPreRotateWidth, MotionLayout.this.mPreRotateHeight);
                    }
                    if(this.mEnd != null) {
                        ConstraintWidget constraintWidget1 = this.getWidget(this.mLayoutEnd, view1);
                        if(constraintWidget1 != null) {
                            motionController1.setEndState(MotionLayout.this.toRect(constraintWidget1), this.mEnd, MotionLayout.this.getWidth(), MotionLayout.this.getHeight());
                        }
                        else if(MotionLayout.this.mDebugPath != 0) {
                            Log.e("MotionLayout", ".(null:-1)no widget for  " + Debug.getName(view1) + " (" + view1.getClass().getName() + ")");
                        }
                    }
                }
            }
            for(int v1 = 0; v1 < v; ++v1) {
                MotionController motionController2 = (MotionController)sparseArray0.get(arr_v[v1]);
                int v5 = motionController2.getAnimateRelativeTo();
                if(v5 != -1) {
                    motionController2.setupRelative(((MotionController)sparseArray0.get(v5)));
                }
            }
        }

        private void computeStartEndSize(int v, int v1) {
            int v2 = MotionLayout.this.getOptimizationLevel();
            if(MotionLayout.this.mCurrentState == MotionLayout.this.getStartState()) {
                MotionLayout.this.resolveSystem(this.mLayoutEnd, v2, (this.mEnd == null || this.mEnd.mRotate == 0 ? v : v1), (this.mEnd == null || this.mEnd.mRotate == 0 ? v1 : v));
                ConstraintSet constraintSet0 = this.mStart;
                if(constraintSet0 != null) {
                    MotionLayout motionLayout0 = MotionLayout.this;
                    ConstraintWidgetContainer constraintWidgetContainer0 = this.mLayoutStart;
                    int v3 = constraintSet0.mRotate == 0 ? v : v1;
                    if(this.mStart.mRotate == 0) {
                        v = v1;
                    }
                    motionLayout0.resolveSystem(constraintWidgetContainer0, v2, v3, v);
                }
                return;
            }
            ConstraintSet constraintSet1 = this.mStart;
            if(constraintSet1 != null) {
                MotionLayout.this.resolveSystem(this.mLayoutStart, v2, (constraintSet1.mRotate == 0 ? v : v1), (this.mStart.mRotate == 0 ? v1 : v));
            }
            MotionLayout motionLayout1 = MotionLayout.this;
            ConstraintWidgetContainer constraintWidgetContainer1 = this.mLayoutEnd;
            int v4 = this.mEnd == null || this.mEnd.mRotate == 0 ? v : v1;
            if(this.mEnd == null || this.mEnd.mRotate == 0) {
                v = v1;
            }
            motionLayout1.resolveSystem(constraintWidgetContainer1, v2, v4, v);
        }

        void copy(ConstraintWidgetContainer constraintWidgetContainer0, ConstraintWidgetContainer constraintWidgetContainer1) {
            ConstraintWidget constraintWidget1;
            ArrayList arrayList0 = constraintWidgetContainer0.getChildren();
            HashMap hashMap0 = new HashMap();
            hashMap0.put(constraintWidgetContainer0, constraintWidgetContainer1);
            constraintWidgetContainer1.getChildren().clear();
            constraintWidgetContainer1.copy(constraintWidgetContainer0, hashMap0);
            for(Object object0: arrayList0) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)object0;
                if(constraintWidget0 instanceof Barrier) {
                    constraintWidget1 = new Barrier();
                }
                else if(constraintWidget0 instanceof Guideline) {
                    constraintWidget1 = new Guideline();
                }
                else if(constraintWidget0 instanceof Flow) {
                    constraintWidget1 = new Flow();
                }
                else if(constraintWidget0 instanceof Placeholder) {
                    constraintWidget1 = new Placeholder();
                }
                else if(constraintWidget0 instanceof Helper) {
                    constraintWidget1 = new HelperWidget();
                }
                else {
                    constraintWidget1 = new ConstraintWidget();
                }
                constraintWidgetContainer1.add(constraintWidget1);
                hashMap0.put(constraintWidget0, constraintWidget1);
            }
            for(Object object1: arrayList0) {
                ((ConstraintWidget)hashMap0.get(((ConstraintWidget)object1))).copy(((ConstraintWidget)object1), hashMap0);
            }
        }

        private void debugLayout(String s, ConstraintWidgetContainer constraintWidgetContainer0) {
            String s1 = s + " " + Debug.getName(((View)constraintWidgetContainer0.getCompanionWidget()));
            Log.v("MotionLayout", s1 + "  ========= " + constraintWidgetContainer0);
            int v = constraintWidgetContainer0.getChildren().size();
            for(int v1 = 0; v1 < v; ++v1) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)constraintWidgetContainer0.getChildren().get(v1);
                String s2 = "_";
                String s3 = constraintWidget0.mTop.mTarget == null ? "_" : "T";
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append(s3 + (constraintWidget0.mBottom.mTarget == null ? "_" : "B") + (constraintWidget0.mLeft.mTarget == null ? "_" : "L"));
                if(constraintWidget0.mRight.mTarget != null) {
                    s2 = "R";
                }
                stringBuilder0.append(s2);
                String s4 = stringBuilder0.toString();
                View view0 = (View)constraintWidget0.getCompanionWidget();
                String s5 = Debug.getName(view0);
                if(view0 instanceof TextView) {
                    s5 = s5 + "(" + ((TextView)view0).getText() + ")";
                }
                Log.v("MotionLayout", s1 + "[" + v1 + "] " + "  " + s5 + " " + constraintWidget0 + " " + s4);
            }
            Log.v("MotionLayout", s1 + " done. ");
        }

        private void debugLayoutParam(String s, LayoutParams constraintLayout$LayoutParams0) {
            String s1 = constraintLayout$LayoutParams0.startToStart == -1 ? "__" : "SS";
            String s2 = "|__";
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(" " + s1 + (constraintLayout$LayoutParams0.startToEnd == -1 ? "|__" : "|SE") + (constraintLayout$LayoutParams0.endToStart == -1 ? "|__" : "|ES") + (constraintLayout$LayoutParams0.endToEnd == -1 ? "|__" : "|EE") + (constraintLayout$LayoutParams0.leftToLeft == -1 ? "|__" : "|LL") + (constraintLayout$LayoutParams0.leftToRight == -1 ? "|__" : "|LR") + (constraintLayout$LayoutParams0.rightToLeft == -1 ? "|__" : "|RL") + (constraintLayout$LayoutParams0.rightToRight == -1 ? "|__" : "|RR") + (constraintLayout$LayoutParams0.topToTop == -1 ? "|__" : "|TT") + (constraintLayout$LayoutParams0.topToBottom == -1 ? "|__" : "|TB") + (constraintLayout$LayoutParams0.bottomToTop == -1 ? "|__" : "|BT"));
            if(constraintLayout$LayoutParams0.bottomToBottom != -1) {
                s2 = "|BB";
            }
            stringBuilder0.append(s2);
            Log.v("MotionLayout", s + stringBuilder0.toString());
        }

        private void debugWidget(String s, ConstraintWidget constraintWidget0) {
            String s3;
            String s1 = "T";
            String s2 = "__";
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(" " + (constraintWidget0.mTop.mTarget == null ? "__" : "T" + (constraintWidget0.mTop.mTarget.mType == Type.TOP ? "T" : "B")));
            if(constraintWidget0.mBottom.mTarget == null) {
                s3 = "__";
            }
            else {
                if(constraintWidget0.mBottom.mTarget.mType != Type.TOP) {
                    s1 = "B";
                }
                s3 = "B" + s1;
            }
            stringBuilder0.append(s3);
            String s4 = "L";
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(stringBuilder0.toString() + (constraintWidget0.mLeft.mTarget == null ? "__" : "L" + (constraintWidget0.mLeft.mTarget.mType == Type.LEFT ? "L" : "R")));
            if(constraintWidget0.mRight.mTarget != null) {
                if(constraintWidget0.mRight.mTarget.mType != Type.LEFT) {
                    s4 = "R";
                }
                s2 = "R" + s4;
            }
            stringBuilder1.append(s2);
            Log.v("MotionLayout", s + stringBuilder1.toString() + " ---  " + constraintWidget0);
        }

        ConstraintWidget getWidget(ConstraintWidgetContainer constraintWidgetContainer0, View view0) {
            if(constraintWidgetContainer0.getCompanionWidget() == view0) {
                return constraintWidgetContainer0;
            }
            ArrayList arrayList0 = constraintWidgetContainer0.getChildren();
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)arrayList0.get(v1);
                if(constraintWidget0.getCompanionWidget() == view0) {
                    return constraintWidget0;
                }
            }
            return null;
        }

        void initFrom(ConstraintWidgetContainer constraintWidgetContainer0, ConstraintSet constraintSet0, ConstraintSet constraintSet1) {
            this.mStart = constraintSet0;
            this.mEnd = constraintSet1;
            this.mLayoutStart = new ConstraintWidgetContainer();
            this.mLayoutEnd = new ConstraintWidgetContainer();
            this.mLayoutStart.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutEnd.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutStart.removeAllChildren();
            this.mLayoutEnd.removeAllChildren();
            this.copy(MotionLayout.this.mLayoutWidget, this.mLayoutStart);
            this.copy(MotionLayout.this.mLayoutWidget, this.mLayoutEnd);
            if(((double)MotionLayout.this.mTransitionLastPosition) > 0.5) {
                if(constraintSet0 != null) {
                    this.setupConstraintWidget(this.mLayoutStart, constraintSet0);
                }
                this.setupConstraintWidget(this.mLayoutEnd, constraintSet1);
            }
            else {
                this.setupConstraintWidget(this.mLayoutEnd, constraintSet1);
                if(constraintSet0 != null) {
                    this.setupConstraintWidget(this.mLayoutStart, constraintSet0);
                }
            }
            this.mLayoutStart.setRtl(MotionLayout.this.isRtl());
            this.mLayoutStart.updateHierarchy();
            this.mLayoutEnd.setRtl(MotionLayout.this.isRtl());
            this.mLayoutEnd.updateHierarchy();
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = MotionLayout.this.getLayoutParams();
            if(viewGroup$LayoutParams0 != null) {
                if(viewGroup$LayoutParams0.width == -2) {
                    this.mLayoutStart.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
                if(viewGroup$LayoutParams0.height == -2) {
                    this.mLayoutStart.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }

        public boolean isNotConfiguredWith(int v, int v1) {
            return v != this.mStartId || v1 != this.mEndId;
        }

        public void measure(int v, int v1) {
            int v2 = View.MeasureSpec.getMode(v);
            int v3 = View.MeasureSpec.getMode(v1);
            MotionLayout.this.mWidthMeasureMode = v2;
            MotionLayout.this.mHeightMeasureMode = v3;
            this.computeStartEndSize(v, v1);
            if(!(MotionLayout.this.getParent() instanceof MotionLayout) || v2 != 0x40000000 || v3 != 0x40000000) {
                this.computeStartEndSize(v, v1);
                MotionLayout.this.mStartWrapWidth = this.mLayoutStart.getWidth();
                MotionLayout.this.mStartWrapHeight = this.mLayoutStart.getHeight();
                MotionLayout.this.mEndWrapWidth = this.mLayoutEnd.getWidth();
                MotionLayout.this.mEndWrapHeight = this.mLayoutEnd.getHeight();
                MotionLayout.this.mMeasureDuringTransition = MotionLayout.this.mStartWrapWidth != MotionLayout.this.mEndWrapWidth || MotionLayout.this.mStartWrapHeight != MotionLayout.this.mEndWrapHeight;
            }
            int v4 = MotionLayout.this.mStartWrapWidth;
            int v5 = MotionLayout.this.mStartWrapHeight;
            if(MotionLayout.this.mWidthMeasureMode == 0x80000000 || MotionLayout.this.mWidthMeasureMode == 0) {
                v4 = (int)(((float)MotionLayout.this.mStartWrapWidth) + MotionLayout.this.mPostInterpolationPosition * ((float)(MotionLayout.this.mEndWrapWidth - MotionLayout.this.mStartWrapWidth)));
            }
            if(MotionLayout.this.mHeightMeasureMode == 0x80000000 || MotionLayout.this.mHeightMeasureMode == 0) {
                v5 = (int)(((float)MotionLayout.this.mStartWrapHeight) + MotionLayout.this.mPostInterpolationPosition * ((float)(MotionLayout.this.mEndWrapHeight - MotionLayout.this.mStartWrapHeight)));
            }
            MotionLayout.this.resolveMeasuredDimension(v, v1, v4, v5, this.mLayoutStart.isWidthMeasuredTooSmall() || this.mLayoutEnd.isWidthMeasuredTooSmall(), this.mLayoutStart.isHeightMeasuredTooSmall() || this.mLayoutEnd.isHeightMeasuredTooSmall());
        }

        public void reEvaluateState() {
            this.measure(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.setupMotionViews();
        }

        public void setMeasuredId(int v, int v1) {
            this.mStartId = v;
            this.mEndId = v1;
        }

        private void setupConstraintWidget(ConstraintWidgetContainer constraintWidgetContainer0, ConstraintSet constraintSet0) {
            SparseArray sparseArray0 = new SparseArray();
            androidx.constraintlayout.widget.Constraints.LayoutParams constraints$LayoutParams0 = new androidx.constraintlayout.widget.Constraints.LayoutParams(-2, -2);
            sparseArray0.clear();
            sparseArray0.put(0, constraintWidgetContainer0);
            sparseArray0.put(MotionLayout.this.getId(), constraintWidgetContainer0);
            if(constraintSet0 != null && constraintSet0.mRotate != 0) {
                ConstraintWidgetContainer constraintWidgetContainer1 = this.mLayoutEnd;
                int v = MotionLayout.this.getOptimizationLevel();
                int v1 = View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), 0x40000000);
                int v2 = View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), 0x40000000);
                MotionLayout.this.resolveSystem(constraintWidgetContainer1, v, v1, v2);
            }
            for(Object object0: constraintWidgetContainer0.getChildren()) {
                ((ConstraintWidget)object0).setAnimated(true);
                sparseArray0.put(((View)((ConstraintWidget)object0).getCompanionWidget()).getId(), ((ConstraintWidget)object0));
            }
            for(Object object1: constraintWidgetContainer0.getChildren()) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)object1;
                View view0 = (View)constraintWidget0.getCompanionWidget();
                constraintSet0.applyToLayoutParams(view0.getId(), constraints$LayoutParams0);
                constraintWidget0.setWidth(constraintSet0.getWidth(view0.getId()));
                constraintWidget0.setHeight(constraintSet0.getHeight(view0.getId()));
                if(view0 instanceof ConstraintHelper) {
                    constraintSet0.applyToHelper(((ConstraintHelper)view0), constraintWidget0, constraints$LayoutParams0, sparseArray0);
                    if(view0 instanceof androidx.constraintlayout.widget.Barrier) {
                        ((androidx.constraintlayout.widget.Barrier)view0).validateParams();
                    }
                }
                constraints$LayoutParams0.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                MotionLayout.this.applyConstraintsFromLayoutParams(false, view0, constraintWidget0, constraints$LayoutParams0, sparseArray0);
                if(constraintSet0.getVisibilityMode(view0.getId()) == 1) {
                    constraintWidget0.setVisibility(view0.getVisibility());
                }
                else {
                    constraintWidget0.setVisibility(constraintSet0.getVisibility(view0.getId()));
                }
            }
            for(Object object2: constraintWidgetContainer0.getChildren()) {
                ConstraintWidget constraintWidget1 = (ConstraintWidget)object2;
                if(constraintWidget1 instanceof VirtualLayout) {
                    ((ConstraintHelper)constraintWidget1.getCompanionWidget()).updatePreLayout(constraintWidgetContainer0, ((Helper)constraintWidget1), sparseArray0);
                    ((VirtualLayout)(((Helper)constraintWidget1))).captureWidgets();
                }
            }
        }
    }

    public interface MotionTracker {
        void addMovement(MotionEvent arg1);

        void clear();

        void computeCurrentVelocity(int arg1);

        void computeCurrentVelocity(int arg1, float arg2);

        float getXVelocity();

        float getXVelocity(int arg1);

        float getYVelocity();

        float getYVelocity(int arg1);

        void recycle();
    }

    static class MyTracker implements MotionTracker {
        VelocityTracker mTracker;
        private static MyTracker sMe;

        static {
            MyTracker.sMe = new MyTracker();
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public void addMovement(MotionEvent motionEvent0) {
            VelocityTracker velocityTracker0 = this.mTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.addMovement(motionEvent0);
            }
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public void clear() {
            VelocityTracker velocityTracker0 = this.mTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.clear();
            }
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public void computeCurrentVelocity(int v) {
            VelocityTracker velocityTracker0 = this.mTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.computeCurrentVelocity(v);
            }
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public void computeCurrentVelocity(int v, float f) {
            VelocityTracker velocityTracker0 = this.mTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.computeCurrentVelocity(v, f);
            }
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public float getXVelocity() {
            return this.mTracker == null ? 0.0f : this.mTracker.getXVelocity();
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public float getXVelocity(int v) {
            return this.mTracker == null ? 0.0f : this.mTracker.getXVelocity(v);
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public float getYVelocity() {
            return this.mTracker == null ? 0.0f : this.mTracker.getYVelocity();
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public float getYVelocity(int v) {
            return this.mTracker == null ? 0.0f : this.getYVelocity(v);
        }

        public static MyTracker obtain() {
            MyTracker motionLayout$MyTracker0 = MyTracker.sMe;
            motionLayout$MyTracker0.mTracker = VelocityTracker.obtain();
            return MyTracker.sMe;
        }

        @Override  // androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker
        public void recycle() {
            VelocityTracker velocityTracker0 = this.mTracker;
            if(velocityTracker0 != null) {
                velocityTracker0.recycle();
                this.mTracker = null;
            }
        }
    }

    class StateCache {
        int mEndState;
        final String mKeyEndState;
        final String mKeyProgress;
        final String mKeyStartState;
        final String mKeyVelocity;
        float mProgress;
        int mStartState;
        float mVelocity;

        StateCache() {
            this.mProgress = NaNf;
            this.mVelocity = NaNf;
            this.mStartState = -1;
            this.mEndState = -1;
            this.mKeyProgress = "motion.progress";
            this.mKeyVelocity = "motion.velocity";
            this.mKeyStartState = "motion.StartState";
            this.mKeyEndState = "motion.EndState";
        }

        void apply() {
            int v = this.mStartState;
            if(v != -1 || this.mEndState != -1) {
                if(v == -1) {
                    MotionLayout.this.transitionToState(this.mEndState);
                }
                else {
                    int v1 = this.mEndState;
                    if(v1 == -1) {
                        MotionLayout.this.setState(v, -1, -1);
                    }
                    else {
                        MotionLayout.this.setTransition(v, v1);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if(Float.isNaN(this.mVelocity)) {
                if(Float.isNaN(this.mProgress)) {
                    return;
                }
                MotionLayout.this.setProgress(this.mProgress);
                return;
            }
            MotionLayout.this.setProgress(this.mProgress, this.mVelocity);
            this.mProgress = NaNf;
            this.mVelocity = NaNf;
            this.mStartState = -1;
            this.mEndState = -1;
        }

        public Bundle getTransitionState() {
            Bundle bundle0 = new Bundle();
            bundle0.putFloat("motion.progress", this.mProgress);
            bundle0.putFloat("motion.velocity", this.mVelocity);
            bundle0.putInt("motion.StartState", this.mStartState);
            bundle0.putInt("motion.EndState", this.mEndState);
            return bundle0;
        }

        public void recordState() {
            this.mEndState = MotionLayout.this.mEndState;
            this.mStartState = MotionLayout.this.mBeginState;
            this.mVelocity = MotionLayout.this.getVelocity();
            this.mProgress = MotionLayout.this.getProgress();
        }

        public void setEndState(int v) {
            this.mEndState = v;
        }

        public void setProgress(float f) {
            this.mProgress = f;
        }

        public void setStartState(int v) {
            this.mStartState = v;
        }

        public void setTransitionState(Bundle bundle0) {
            this.mProgress = bundle0.getFloat("motion.progress");
            this.mVelocity = bundle0.getFloat("motion.velocity");
            this.mStartState = bundle0.getInt("motion.StartState");
            this.mEndState = bundle0.getInt("motion.EndState");
        }

        public void setVelocity(float f) {
            this.mVelocity = f;
        }
    }

    public interface TransitionListener {
        void onTransitionChange(MotionLayout arg1, int arg2, int arg3, float arg4);

        void onTransitionCompleted(MotionLayout arg1, int arg2);

        void onTransitionStarted(MotionLayout arg1, int arg2, int arg3);

        void onTransitionTrigger(MotionLayout arg1, int arg2, boolean arg3, float arg4);
    }

    static enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED;

        private static TransitionState[] $values() [...] // Inlined contents
    }

    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    private static final float EPSILON = 0.00001f;
    public static boolean IS_IN_EDIT_MODE = false;
    static final int MAX_KEY_FRAMES = 50;
    static final String TAG = "MotionLayout";
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_NEVER_TO_END = 7;
    public static final int TOUCH_UP_NEVER_TO_START = 6;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    private long mAnimationStartTime;
    private int mBeginState;
    private RectF mBoundsCheck;
    int mCurrentState;
    int mDebugPath;
    private DecelerateInterpolator mDecelerateLogic;
    private ArrayList mDecoratorsHelpers;
    private boolean mDelayedApply;
    private DesignTool mDesignTool;
    DevModeDraw mDevModeDraw;
    private int mEndState;
    int mEndWrapHeight;
    int mEndWrapWidth;
    boolean mFirstDown;
    HashMap mFrameArrayList;
    private int mFrames;
    int mHeightMeasureMode;
    private boolean mInLayout;
    private boolean mInRotation;
    boolean mInTransition;
    boolean mIndirectTransition;
    private boolean mInteractionEnabled;
    Interpolator mInterpolator;
    private Matrix mInverseMatrix;
    boolean mIsAnimating;
    private boolean mKeepAnimating;
    private KeyCache mKeyCache;
    private long mLastDrawTime;
    private float mLastFps;
    private int mLastHeightMeasureSpec;
    int mLastLayoutHeight;
    int mLastLayoutWidth;
    private float mLastPos;
    float mLastVelocity;
    private int mLastWidthMeasureSpec;
    private float mLastY;
    private float mListenerPosition;
    private int mListenerState;
    protected boolean mMeasureDuringTransition;
    Model mModel;
    private boolean mNeedsFireTransitionCompleted;
    int mOldHeight;
    int mOldWidth;
    private Runnable mOnComplete;
    private ArrayList mOnHideHelpers;
    private ArrayList mOnShowHelpers;
    float mPostInterpolationPosition;
    HashMap mPreRotate;
    private int mPreRotateHeight;
    private int mPreRotateWidth;
    private int mPreviouseRotation;
    Interpolator mProgressInterpolator;
    private View mRegionView;
    int mRotatMode;
    MotionScene mScene;
    private int[] mScheduledTransitionTo;
    int mScheduledTransitions;
    float mScrollTargetDT;
    float mScrollTargetDX;
    float mScrollTargetDY;
    long mScrollTargetTime;
    int mStartWrapHeight;
    int mStartWrapWidth;
    private StateCache mStateCache;
    private StopLogic mStopLogic;
    Rect mTempRect;
    private boolean mTemporalInterpolator;
    ArrayList mTransitionCompleted;
    private float mTransitionDuration;
    float mTransitionGoalPosition;
    private boolean mTransitionInstantly;
    float mTransitionLastPosition;
    private long mTransitionLastTime;
    private TransitionListener mTransitionListener;
    private CopyOnWriteArrayList mTransitionListeners;
    float mTransitionPosition;
    TransitionState mTransitionState;
    boolean mUndergoingMotion;
    int mWidthMeasureMode;

    public MotionLayout(Context context0) {
        super(context0);
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator(this);
        this.mFirstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.mScheduledTransitions = 0;
        this.mInRotation = false;
        this.mRotatMode = 0;
        this.mPreRotate = new HashMap();
        this.mTempRect = new Rect();
        this.mDelayedApply = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model(this);
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.mTransitionCompleted = new ArrayList();
        this.init(null);
    }

    public MotionLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator(this);
        this.mFirstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.mScheduledTransitions = 0;
        this.mInRotation = false;
        this.mRotatMode = 0;
        this.mPreRotate = new HashMap();
        this.mTempRect = new Rect();
        this.mDelayedApply = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model(this);
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.mTransitionCompleted = new ArrayList();
        this.init(attributeSet0);
    }

    public MotionLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator(this);
        this.mFirstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.mScheduledTransitions = 0;
        this.mInRotation = false;
        this.mRotatMode = 0;
        this.mPreRotate = new HashMap();
        this.mTempRect = new Rect();
        this.mDelayedApply = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model(this);
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.mTransitionCompleted = new ArrayList();
        this.init(attributeSet0);
    }

    public void addTransitionListener(TransitionListener motionLayout$TransitionListener0) {
        if(this.mTransitionListeners == null) {
            this.mTransitionListeners = new CopyOnWriteArrayList();
        }
        this.mTransitionListeners.add(motionLayout$TransitionListener0);
    }

    void animateTo(float f) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            float f1 = this.mTransitionPosition;
            if(this.mTransitionLastPosition != f1 && this.mTransitionInstantly) {
                this.mTransitionLastPosition = f1;
            }
            float f2 = this.mTransitionLastPosition;
            if(f2 != f) {
                this.mTemporalInterpolator = false;
                this.mTransitionGoalPosition = f;
                this.mTransitionDuration = ((float)motionScene0.getDuration()) / 1000.0f;
                this.setProgress(this.mTransitionGoalPosition);
                this.mInterpolator = null;
                this.mProgressInterpolator = this.mScene.getInterpolator();
                this.mTransitionInstantly = false;
                this.mAnimationStartTime = 19042604786000L;
                this.mInTransition = true;
                this.mTransitionPosition = f2;
                this.mTransitionLastPosition = f2;
                this.invalidate();
            }
        }
    }

    public boolean applyViewTransition(int v, MotionController motionController0) {
        return this.mScene == null ? false : this.mScene.applyViewTransition(v, motionController0);
    }

    private boolean callTransformedTouchEvent(View view0, MotionEvent motionEvent0, float f, float f1) {
        Matrix matrix0 = view0.getMatrix();
        if(matrix0.isIdentity()) {
            motionEvent0.offsetLocation(f, f1);
            boolean z = view0.onTouchEvent(motionEvent0);
            motionEvent0.offsetLocation(-f, -f1);
            return z;
        }
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        motionEvent1.offsetLocation(f, f1);
        if(this.mInverseMatrix == null) {
            this.mInverseMatrix = new Matrix();
        }
        matrix0.invert(this.mInverseMatrix);
        motionEvent1.transform(this.mInverseMatrix);
        boolean z1 = view0.onTouchEvent(motionEvent1);
        motionEvent1.recycle();
        return z1;
    }

    private void checkStructure() {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        this.checkStructure(motionScene0.getStartId(), this.mScene.getConstraintSet(this.mScene.getStartId()));
        SparseIntArray sparseIntArray0 = new SparseIntArray();
        SparseIntArray sparseIntArray1 = new SparseIntArray();
        for(Object object0: this.mScene.getDefinedTransitions()) {
            if(((Transition)object0) == this.mScene.mCurrentTransition) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            this.checkStructure(((Transition)object0));
            int v = ((Transition)object0).getStartConstraintSetId();
            int v1 = ((Transition)object0).getEndConstraintSetId();
            String s = Debug.getName(this.getContext(), v);
            String s1 = Debug.getName(this.getContext(), v1);
            if(sparseIntArray0.get(v) == v1) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + s + "->" + s1);
            }
            if(sparseIntArray1.get(v1) == v) {
                Log.e("MotionLayout", "CHECK: you can\'t have reverse transitions" + s + "->" + s1);
            }
            sparseIntArray0.put(v, v1);
            sparseIntArray1.put(v1, v);
            if(this.mScene.getConstraintSet(v) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + s);
            }
            if(this.mScene.getConstraintSet(v1) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + s);
            }
        }
    }

    private void checkStructure(int v, ConstraintSet constraintSet0) {
        String s = Debug.getName(this.getContext(), v);
        int v1 = this.getChildCount();
        for(int v3 = 0; v3 < v1; ++v3) {
            View view0 = this.getChildAt(v3);
            int v4 = view0.getId();
            if(v4 == -1) {
                Log.w("MotionLayout", "CHECK: " + s + " ALL VIEWS SHOULD HAVE ID\'s " + view0.getClass().getName() + " does not!");
            }
            if(constraintSet0.getConstraint(v4) == null) {
                Log.w("MotionLayout", "CHECK: " + s + " NO CONSTRAINTS for " + Debug.getName(view0));
            }
        }
        int[] arr_v = constraintSet0.getKnownIds();
        for(int v2 = 0; v2 < arr_v.length; ++v2) {
            int v5 = arr_v[v2];
            String s1 = Debug.getName(this.getContext(), v5);
            if(this.findViewById(arr_v[v2]) == null) {
                Log.w("MotionLayout", "CHECK: " + s + " NO View matches id " + s1);
            }
            if(constraintSet0.getHeight(v5) == -1) {
                Log.w("MotionLayout", "CHECK: " + s + "(" + s1 + ") no LAYOUT_HEIGHT");
            }
            if(constraintSet0.getWidth(v5) == -1) {
                Log.w("MotionLayout", "CHECK: " + s + "(" + s1 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void checkStructure(Transition motionScene$Transition0) {
        if(motionScene$Transition0.getStartConstraintSetId() == motionScene$Transition0.getEndConstraintSetId()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    public ConstraintSet cloneConstraintSet(int v) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 == null) {
            return null;
        }
        ConstraintSet constraintSet0 = motionScene0.getConstraintSet(v);
        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(constraintSet0);
        return constraintSet1;
    }

    private void computeCurrentPositions() {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            MotionController motionController0 = (MotionController)this.mFrameArrayList.get(view0);
            if(motionController0 != null) {
                motionController0.setStartCurrentState(view0);
            }
        }
    }

    private void debugPos() {
        for(int v = 0; v < this.getChildCount(); ++v) {
            View view0 = this.getChildAt(v);
            Log.v("MotionLayout", " .(null:-1) " + Debug.getName(this) + " " + Debug.getName(this.getContext(), this.mCurrentState) + " " + Debug.getName(view0) + view0.getLeft() + " " + view0.getTop());
        }
    }

    void disableAutoTransition(boolean z) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 == null) {
            return;
        }
        motionScene0.disableAutoTransition(z);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void dispatchDraw(Canvas canvas0) {
        ArrayList arrayList0 = this.mDecoratorsHelpers;
        if(arrayList0 != null) {
            Iterator iterator0 = arrayList0.iterator();
            while(iterator0.hasNext()) {
                iterator0.next();
            }
        }
        this.evaluate(false);
        if(this.mScene != null && this.mScene.mViewTransitionController != null) {
            this.mScene.mViewTransitionController.animate();
        }
        super.dispatchDraw(canvas0);
        if(this.mScene != null) {
            if((this.mDebugPath & 1) == 1 && !this.isInEditMode()) {
                ++this.mFrames;
                long v = this.mLastDrawTime;
                if(v == -1L) {
                    this.mLastDrawTime = 19042758694800L;
                }
                else if(19042758694800L - v > 200000000L) {
                    this.mLastFps = ((float)(((int)(((float)this.mFrames) / (((float)(19042758694800L - v)) * 1.000000E-09f) * 100.0f)))) / 100.0f;
                    this.mFrames = 0;
                    this.mLastDrawTime = 19042758694800L;
                }
                Paint paint0 = new Paint();
                paint0.setTextSize(42.0f);
                String s = this.mLastFps + " fps " + Debug.getState(this, this.mBeginState) + " -> " + Debug.getState(this, this.mEndState) + " (progress: " + ((float)(((int)(this.getProgress() * 1000.0f)))) / 10.0f + " ) state=" + (this.mCurrentState == -1 ? "undefined" : Debug.getState(this, this.mCurrentState));
                paint0.setColor(0xFF000000);
                canvas0.drawText(s, 11.0f, ((float)(this.getHeight() - 29)), paint0);
                paint0.setColor(0xFF880088);
                canvas0.drawText(s, 10.0f, ((float)(this.getHeight() - 30)), paint0);
            }
            if(this.mDebugPath > 1) {
                if(this.mDevModeDraw == null) {
                    this.mDevModeDraw = new DevModeDraw(this);
                }
                this.mDevModeDraw.draw(canvas0, this.mFrameArrayList, this.mScene.getDuration(), this.mDebugPath);
            }
            ArrayList arrayList1 = this.mDecoratorsHelpers;
            if(arrayList1 != null) {
                Iterator iterator1 = arrayList1.iterator();
                while(iterator1.hasNext()) {
                    iterator1.next();
                }
            }
        }
    }

    public void enableTransition(int v, boolean z) {
        Transition motionScene$Transition0 = this.getTransition(v);
        if(z) {
            motionScene$Transition0.setEnabled(true);
            return;
        }
        if(motionScene$Transition0 == this.mScene.mCurrentTransition) {
            for(Object object0: this.mScene.getTransitionsWithState(this.mCurrentState)) {
                Transition motionScene$Transition1 = (Transition)object0;
                if(motionScene$Transition1.isEnabled()) {
                    this.mScene.mCurrentTransition = motionScene$Transition1;
                    break;
                }
                if(false) {
                    break;
                }
            }
        }
        motionScene$Transition0.setEnabled(false);
    }

    public void enableViewTransition(int v, boolean z) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            motionScene0.enableViewTransition(v, z);
        }
    }

    void endTrigger(boolean z) {
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            MotionController motionController0 = (MotionController)this.mFrameArrayList.get(view0);
            if(motionController0 != null) {
                motionController0.endTrigger(z);
            }
        }
    }

    void evaluate(boolean z) {
        int v1;
        boolean z3;
        if(this.mTransitionLastTime == -1L) {
            this.mTransitionLastTime = 19046067325800L;
        }
        float f = this.mTransitionLastPosition;
        if(f > 0.0f && f < 1.0f) {
            this.mCurrentState = -1;
        }
        boolean z1 = true;
        boolean z2 = false;
        if(this.mKeepAnimating || this.mInTransition && (z || this.mTransitionGoalPosition != f)) {
            float f1 = Math.signum(this.mTransitionGoalPosition - f);
            Interpolator interpolator0 = this.mInterpolator;
            float f2 = interpolator0 instanceof MotionInterpolator ? 0.0f : ((float)(19046067786700L - this.mTransitionLastTime)) * f1 * 1.000000E-09f / this.mTransitionDuration;
            float f3 = this.mTransitionInstantly ? this.mTransitionGoalPosition : this.mTransitionLastPosition + f2;
            int v = Float.compare(f1, 0.0f);
            if((v <= 0 || f3 < this.mTransitionGoalPosition) && (f1 > 0.0f || f3 > this.mTransitionGoalPosition)) {
                z3 = false;
            }
            else {
                f3 = this.mTransitionGoalPosition;
                this.mInTransition = false;
                z3 = true;
            }
            this.mTransitionLastPosition = f3;
            this.mTransitionPosition = f3;
            this.mTransitionLastTime = 19046067786700L;
            if(interpolator0 == null || z3) {
                this.mLastVelocity = f2;
                v1 = 0;
            }
            else if(this.mTemporalInterpolator) {
                float f4 = interpolator0.getInterpolation(((float)(19046067786700L - this.mAnimationStartTime)) * 1.000000E-09f);
                StopLogic stopLogic0 = this.mStopLogic;
                if(this.mInterpolator != stopLogic0) {
                    v1 = 0;
                }
                else if(stopLogic0.isStopped()) {
                    v1 = 2;
                }
                else {
                    v1 = 1;
                }
                this.mTransitionLastPosition = f4;
                this.mTransitionLastTime = 19046067786700L;
                Interpolator interpolator1 = this.mInterpolator;
                if(interpolator1 instanceof MotionInterpolator) {
                    float f5 = ((MotionInterpolator)interpolator1).getVelocity();
                    this.mLastVelocity = f5;
                    if(Math.abs(f5) * this.mTransitionDuration <= 0.00001f && v1 == 2) {
                        this.mInTransition = false;
                    }
                    if(f5 > 0.0f && f4 >= 1.0f) {
                        this.mTransitionLastPosition = 1.0f;
                        this.mInTransition = false;
                        f4 = 1.0f;
                    }
                    if(f5 >= 0.0f || f4 > 0.0f) {
                        f3 = f4;
                    }
                    else {
                        this.mTransitionLastPosition = 0.0f;
                        this.mInTransition = false;
                        f3 = 0.0f;
                    }
                }
                else {
                    f3 = f4;
                }
            }
            else {
                float f6 = interpolator0.getInterpolation(f3);
                Interpolator interpolator2 = this.mInterpolator;
                this.mLastVelocity = interpolator2 instanceof MotionInterpolator ? ((MotionInterpolator)interpolator2).getVelocity() : (interpolator2.getInterpolation(f3 + f2) - f6) * f1 / f2;
                f3 = f6;
                v1 = 0;
            }
            if(Math.abs(this.mLastVelocity) > 0.00001f) {
                this.setState(TransitionState.MOVING);
            }
            if(v1 != 1) {
                if(v > 0 && f3 >= this.mTransitionGoalPosition || f1 <= 0.0f && f3 <= this.mTransitionGoalPosition) {
                    f3 = this.mTransitionGoalPosition;
                    this.mInTransition = false;
                }
                if(f3 >= 1.0f || f3 <= 0.0f) {
                    this.mInTransition = false;
                    this.setState(TransitionState.FINISHED);
                }
            }
            int v2 = this.getChildCount();
            this.mKeepAnimating = false;
            this.mPostInterpolationPosition = f3;
            float f7 = this.mProgressInterpolator == null ? f3 : this.mProgressInterpolator.getInterpolation(f3);
            Interpolator interpolator3 = this.mProgressInterpolator;
            if(interpolator3 != null) {
                this.mLastVelocity = interpolator3.getInterpolation(f1 / this.mTransitionDuration + f3) - this.mProgressInterpolator.getInterpolation(f3);
            }
            for(int v3 = 0; v3 < v2; ++v3) {
                View view0 = this.getChildAt(v3);
                MotionController motionController0 = (MotionController)this.mFrameArrayList.get(view0);
                if(motionController0 != null) {
                    this.mKeepAnimating |= motionController0.interpolate(view0, f7, 19046068288100L, this.mKeyCache);
                }
            }
            int v4 = (v <= 0 || f3 < this.mTransitionGoalPosition) && (f1 > 0.0f || f3 > this.mTransitionGoalPosition) ? 0 : 1;
            if(!this.mKeepAnimating && !this.mInTransition && v4 != 0) {
                this.setState(TransitionState.FINISHED);
            }
            if(this.mMeasureDuringTransition) {
                this.requestLayout();
            }
            this.mKeepAnimating |= v4 ^ 1;
            if(f3 <= 0.0f) {
                int v5 = this.mBeginState;
                if(v5 != -1 && this.mCurrentState != v5) {
                    this.mCurrentState = v5;
                    this.mScene.getConstraintSet(v5).applyCustomAttributes(this);
                    this.setState(TransitionState.FINISHED);
                    z2 = true;
                }
            }
            if(((double)f3) >= 1.0) {
                int v6 = this.mEndState;
                if(this.mCurrentState != v6) {
                    this.mCurrentState = v6;
                    this.mScene.getConstraintSet(v6).applyCustomAttributes(this);
                    this.setState(TransitionState.FINISHED);
                    z2 = true;
                }
            }
            if(this.mKeepAnimating || this.mInTransition) {
                this.invalidate();
            }
            else if(v > 0 && f3 == 1.0f || f1 < 0.0f && f3 == 0.0f) {
                this.setState(TransitionState.FINISHED);
            }
            if(!this.mKeepAnimating && !this.mInTransition && (v > 0 && f3 == 1.0f || f1 < 0.0f && f3 == 0.0f)) {
                this.onNewStateAttachHandlers();
            }
        }
        float f8 = this.mTransitionLastPosition;
        if(f8 >= 1.0f) {
            int v7 = this.mEndState;
            if(this.mCurrentState == v7) {
                z1 = z2;
            }
            this.mCurrentState = v7;
            z2 = z1;
        }
        else if(f8 <= 0.0f) {
            int v8 = this.mBeginState;
            if(this.mCurrentState == v8) {
                z1 = z2;
            }
            this.mCurrentState = v8;
            z2 = z1;
        }
        this.mNeedsFireTransitionCompleted |= z2;
        if(z2 && !this.mInLayout) {
            this.requestLayout();
        }
        this.mTransitionPosition = this.mTransitionLastPosition;
    }

    private void evaluateLayout() {
        boolean z;
        float f = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
        Interpolator interpolator0 = this.mInterpolator;
        float f1 = this.mTransitionInstantly ? this.mTransitionGoalPosition : this.mTransitionLastPosition + (interpolator0 instanceof StopLogic ? 0.0f : ((float)(19041576748000L - this.mTransitionLastTime)) * f * 1.000000E-09f / this.mTransitionDuration);
        int v1 = Float.compare(f, 0.0f);
        if((v1 <= 0 || f1 < this.mTransitionGoalPosition) && (f > 0.0f || f1 > this.mTransitionGoalPosition)) {
            z = false;
        }
        else {
            f1 = this.mTransitionGoalPosition;
            z = true;
        }
        if(interpolator0 != null && !z) {
            f1 = this.mTemporalInterpolator ? interpolator0.getInterpolation(((float)(19041576748000L - this.mAnimationStartTime)) * 1.000000E-09f) : interpolator0.getInterpolation(f1);
        }
        if(v1 > 0 && f1 >= this.mTransitionGoalPosition || f <= 0.0f && f1 <= this.mTransitionGoalPosition) {
            f1 = this.mTransitionGoalPosition;
        }
        this.mPostInterpolationPosition = f1;
        int v2 = this.getChildCount();
        Interpolator interpolator1 = this.mProgressInterpolator;
        if(interpolator1 != null) {
            f1 = interpolator1.getInterpolation(f1);
        }
        for(int v = 0; v < v2; ++v) {
            View view0 = this.getChildAt(v);
            MotionController motionController0 = (MotionController)this.mFrameArrayList.get(view0);
            if(motionController0 != null) {
                motionController0.interpolate(view0, f1, 19041577247400L, this.mKeyCache);
            }
        }
        if(this.mMeasureDuringTransition) {
            this.requestLayout();
        }
    }

    private void fireTransitionChange() {
        if((this.mTransitionListener != null || this.mTransitionListeners != null && !this.mTransitionListeners.isEmpty()) && this.mListenerPosition != this.mTransitionPosition) {
            if(this.mListenerState != -1) {
                this.fireTransitionStarted();
                this.mIsAnimating = true;
            }
            this.mListenerState = -1;
            float f = this.mTransitionPosition;
            this.mListenerPosition = f;
            TransitionListener motionLayout$TransitionListener0 = this.mTransitionListener;
            if(motionLayout$TransitionListener0 != null) {
                motionLayout$TransitionListener0.onTransitionChange(this, this.mBeginState, this.mEndState, f);
            }
            CopyOnWriteArrayList copyOnWriteArrayList0 = this.mTransitionListeners;
            if(copyOnWriteArrayList0 != null) {
                for(Object object0: copyOnWriteArrayList0) {
                    ((TransitionListener)object0).onTransitionChange(this, this.mBeginState, this.mEndState, this.mTransitionPosition);
                }
            }
            this.mIsAnimating = true;
        }
    }

    protected void fireTransitionCompleted() {
        if((this.mTransitionListener != null || this.mTransitionListeners != null && !this.mTransitionListeners.isEmpty()) && this.mListenerState == -1) {
            this.mListenerState = this.mCurrentState;
            int v = this.mTransitionCompleted.isEmpty() ? -1 : ((int)(((Integer)this.mTransitionCompleted.get(this.mTransitionCompleted.size() - 1))));
            int v1 = this.mCurrentState;
            if(v != v1 && v1 != -1) {
                this.mTransitionCompleted.add(v1);
            }
        }
        this.processTransitionCompleted();
        Runnable runnable0 = this.mOnComplete;
        if(runnable0 != null) {
            runnable0.run();
            this.mOnComplete = null;
        }
        int[] arr_v = this.mScheduledTransitionTo;
        if(arr_v != null && this.mScheduledTransitions > 0) {
            this.transitionToState(arr_v[0]);
            System.arraycopy(this.mScheduledTransitionTo, 1, this.mScheduledTransitionTo, 0, this.mScheduledTransitionTo.length - 1);
            --this.mScheduledTransitions;
        }
    }

    private void fireTransitionStarted() {
        TransitionListener motionLayout$TransitionListener0 = this.mTransitionListener;
        if(motionLayout$TransitionListener0 != null) {
            motionLayout$TransitionListener0.onTransitionStarted(this, this.mBeginState, this.mEndState);
        }
        CopyOnWriteArrayList copyOnWriteArrayList0 = this.mTransitionListeners;
        if(copyOnWriteArrayList0 != null) {
            for(Object object0: copyOnWriteArrayList0) {
                ((TransitionListener)object0).onTransitionStarted(this, this.mBeginState, this.mEndState);
            }
        }
    }

    public void fireTrigger(int v, boolean z, float f) {
        TransitionListener motionLayout$TransitionListener0 = this.mTransitionListener;
        if(motionLayout$TransitionListener0 != null) {
            motionLayout$TransitionListener0.onTransitionTrigger(this, v, z, f);
        }
        CopyOnWriteArrayList copyOnWriteArrayList0 = this.mTransitionListeners;
        if(copyOnWriteArrayList0 != null) {
            for(Object object0: copyOnWriteArrayList0) {
                ((TransitionListener)object0).onTransitionTrigger(this, v, z, f);
            }
        }
    }

    void getAnchorDpDt(int v, float f, float f1, float f2, float[] arr_f) {
        HashMap hashMap0 = this.mFrameArrayList;
        View view0 = this.getViewById(v);
        MotionController motionController0 = (MotionController)hashMap0.get(view0);
        if(motionController0 != null) {
            motionController0.getDpDt(f, f1, f2, arr_f);
            float f3 = view0.getY();
            this.mLastPos = f;
            this.mLastY = f3;
            return;
        }
        Log.w("MotionLayout", "WARNING could not find view id " + (view0 == null ? "" + v : view0.getContext().getResources().getResourceName(v)));
    }

    public ConstraintSet getConstraintSet(int v) {
        return this.mScene == null ? null : this.mScene.getConstraintSet(v);
    }

    public int[] getConstraintSetIds() {
        return this.mScene == null ? null : this.mScene.getConstraintSetIds();
    }

    String getConstraintSetNames(int v) {
        return this.mScene == null ? null : this.mScene.lookUpConstraintName(v);
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public ArrayList getDefinedTransitions() {
        return this.mScene == null ? null : this.mScene.getDefinedTransitions();
    }

    public DesignTool getDesignTool() {
        if(this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    public int getEndState() {
        return this.mEndState;
    }

    public int[] getMatchingConstraintSetIds(String[] arr_s) {
        return this.mScene == null ? null : this.mScene.getMatchingStateLabels(arr_s);
    }

    MotionController getMotionController(int v) {
        return (MotionController)this.mFrameArrayList.get(this.findViewById(v));
    }

    protected long getNanoTime() [...] // 潜在的解密器

    public float getProgress() {
        return this.mTransitionLastPosition;
    }

    public MotionScene getScene() {
        return this.mScene;
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public float getTargetPosition() {
        return this.mTransitionGoalPosition;
    }

    public Transition getTransition(int v) {
        return this.mScene.getTransitionById(v);
    }

    public Bundle getTransitionState() {
        if(this.mStateCache == null) {
            this.mStateCache = new StateCache(this);
        }
        this.mStateCache.recordState();
        return this.mStateCache.getTransitionState();
    }

    public long getTransitionTimeMs() {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            this.mTransitionDuration = ((float)motionScene0.getDuration()) / 1000.0f;
        }
        return (long)(this.mTransitionDuration * 1000.0f);
    }

    public float getVelocity() {
        return this.mLastVelocity;
    }

    public void getViewVelocity(View view0, float f, float f1, float[] arr_f, int v) {
        float f6;
        float f2 = this.mLastVelocity;
        float f3 = this.mTransitionLastPosition;
        if(this.mInterpolator == null) {
            f6 = f3;
        }
        else {
            float f4 = Math.signum(this.mTransitionGoalPosition - f3);
            float f5 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition + 0.00001f);
            f6 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition);
            f2 = f4 * ((f5 - f6) / 0.00001f) / this.mTransitionDuration;
        }
        Interpolator interpolator0 = this.mInterpolator;
        if(interpolator0 instanceof MotionInterpolator) {
            f2 = ((MotionInterpolator)interpolator0).getVelocity();
        }
        MotionController motionController0 = (MotionController)this.mFrameArrayList.get(view0);
        if((v & 1) == 0) {
            motionController0.getPostLayoutDvDp(f6, view0.getWidth(), view0.getHeight(), f, f1, arr_f);
        }
        else {
            motionController0.getDpDt(f6, f, f1, arr_f);
        }
        if(v < 2) {
            arr_f[0] *= f2;
            arr_f[1] *= f2;
        }
    }

    private boolean handlesTouchEvent(float f, float f1, View view0, MotionEvent motionEvent0) {
        boolean z = false;
        if(view0 instanceof ViewGroup) {
            for(int v = ((ViewGroup)view0).getChildCount() - 1; v >= 0; --v) {
                View view1 = ((ViewGroup)view0).getChildAt(v);
                if(this.handlesTouchEvent(((float)view1.getLeft()) + f - ((float)view0.getScrollX()), ((float)view1.getTop()) + f1 - ((float)view0.getScrollY()), view1, motionEvent0)) {
                    z = true;
                    break;
                }
            }
        }
        if(!z) {
            this.mBoundsCheck.set(f, f1, ((float)view0.getRight()) + f - ((float)view0.getLeft()), ((float)view0.getBottom()) + f1 - ((float)view0.getTop()));
            return (motionEvent0.getAction() != 0 || this.mBoundsCheck.contains(motionEvent0.getX(), motionEvent0.getY())) && this.callTransformedTouchEvent(view0, motionEvent0, -f, -f1);
        }
        return true;
    }

    private void init(AttributeSet attributeSet0) {
        MotionLayout.IS_IN_EDIT_MODE = this.isInEditMode();
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.MotionLayout);
            int v = typedArray0.getIndexCount();
            boolean z = true;
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MotionLayout_layoutDescription) {
                    int v3 = typedArray0.getResourceId(v2, -1);
                    this.mScene = new MotionScene(this.getContext(), this, v3);
                }
                else if(v2 == styleable.MotionLayout_currentState) {
                    this.mCurrentState = typedArray0.getResourceId(v2, -1);
                }
                else if(v2 == styleable.MotionLayout_motionProgress) {
                    this.mTransitionGoalPosition = typedArray0.getFloat(v2, 0.0f);
                    this.mInTransition = true;
                }
                else if(v2 == styleable.MotionLayout_applyMotionScene) {
                    z = typedArray0.getBoolean(v2, z);
                }
                else if(v2 != styleable.MotionLayout_showPaths) {
                    if(v2 == styleable.MotionLayout_motionDebug) {
                        this.mDebugPath = typedArray0.getInt(v2, 0);
                    }
                }
                else if(this.mDebugPath == 0) {
                    this.mDebugPath = typedArray0.getBoolean(v2, false) ? 2 : 0;
                }
            }
            typedArray0.recycle();
            if(this.mScene == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if(!z) {
                this.mScene = null;
            }
        }
        if(this.mDebugPath != 0) {
            this.checkStructure();
        }
        if(this.mCurrentState == -1) {
            MotionScene motionScene0 = this.mScene;
            if(motionScene0 != null) {
                this.mCurrentState = motionScene0.getStartId();
                this.mBeginState = this.mScene.getStartId();
                this.mEndState = this.mScene.getEndId();
            }
        }
    }

    public boolean isDelayedApplicationOfInitialState() {
        return this.mDelayedApply;
    }

    public boolean isInRotation() {
        return this.mInRotation;
    }

    public boolean isInteractionEnabled() {
        return this.mInteractionEnabled;
    }

    public boolean isViewTransitionEnabled(int v) {
        return this.mScene == null ? false : this.mScene.isViewTransitionEnabled(v);
    }

    public void jumpToState(int v) {
        if(!this.isAttachedToWindow()) {
            this.mCurrentState = v;
        }
        if(this.mBeginState == v) {
            this.setProgress(0.0f);
            return;
        }
        if(this.mEndState == v) {
            this.setProgress(1.0f);
            return;
        }
        this.setTransition(v, v);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void loadLayoutDescription(int v) {
        if(v != 0) {
            try {
                MotionScene motionScene0 = new MotionScene(this.getContext(), this, v);
                this.mScene = motionScene0;
                if(this.mCurrentState == -1) {
                    this.mCurrentState = motionScene0.getStartId();
                    this.mBeginState = this.mScene.getStartId();
                    this.mEndState = this.mScene.getEndId();
                }
                if(!this.isAttachedToWindow()) {
                    this.mScene = null;
                    return;
                }
                try {
                    Display display0 = this.getDisplay();
                    this.mPreviouseRotation = display0 == null ? 0 : display0.getRotation();
                    MotionScene motionScene1 = this.mScene;
                    if(motionScene1 != null) {
                        ConstraintSet constraintSet0 = motionScene1.getConstraintSet(this.mCurrentState);
                        this.mScene.readFallback(this);
                        ArrayList arrayList0 = this.mDecoratorsHelpers;
                        if(arrayList0 != null) {
                            Iterator iterator0 = arrayList0.iterator();
                            while(iterator0.hasNext()) {
                                iterator0.next();
                            }
                        }
                        if(constraintSet0 != null) {
                            constraintSet0.applyTo(this);
                        }
                        this.mBeginState = this.mCurrentState;
                    }
                    this.onNewStateAttachHandlers();
                    StateCache motionLayout$StateCache0 = this.mStateCache;
                    if(motionLayout$StateCache0 != null) {
                        if(this.mDelayedApply) {
                            this.post(new Runnable() {
                                @Override
                                public void run() {
                                    MotionLayout.this.mStateCache.apply();
                                }
                            });
                            return;
                        }
                        motionLayout$StateCache0.apply();
                        return;
                    }
                    if(this.mScene != null && this.mScene.mCurrentTransition != null && this.mScene.mCurrentTransition.getAutoTransition() == 4) {
                        this.transitionToEnd();
                        this.setState(TransitionState.SETUP);
                        this.setState(TransitionState.MOVING);
                    }
                    return;
                }
                catch(Exception exception1) {
                    throw new IllegalArgumentException("unable to parse MotionScene file", exception1);
                }
            }
            catch(Exception exception0) {
                throw new IllegalArgumentException("unable to parse MotionScene file", exception0);
            }
        }
        this.mScene = null;
    }

    int lookUpConstraintId(String s) {
        return this.mScene == null ? 0 : this.mScene.lookUpConstraintId(s);
    }

    protected MotionTracker obtainVelocityTracker() {
        return MyTracker.obtain();
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Display display0 = this.getDisplay();
        if(display0 != null) {
            this.mPreviouseRotation = display0.getRotation();
        }
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            int v = this.mCurrentState;
            if(v != -1) {
                ConstraintSet constraintSet0 = motionScene0.getConstraintSet(v);
                this.mScene.readFallback(this);
                ArrayList arrayList0 = this.mDecoratorsHelpers;
                if(arrayList0 != null) {
                    Iterator iterator0 = arrayList0.iterator();
                    while(iterator0.hasNext()) {
                        iterator0.next();
                    }
                }
                if(constraintSet0 != null) {
                    constraintSet0.applyTo(this);
                }
                this.mBeginState = this.mCurrentState;
            }
        }
        this.onNewStateAttachHandlers();
        StateCache motionLayout$StateCache0 = this.mStateCache;
        if(motionLayout$StateCache0 != null) {
            if(this.mDelayedApply) {
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        MotionLayout.this.mStateCache.apply();
                    }
                });
                return;
            }
            motionLayout$StateCache0.apply();
            return;
        }
        if(this.mScene != null && this.mScene.mCurrentTransition != null && this.mScene.mCurrentTransition.getAutoTransition() == 4) {
            this.transitionToEnd();
            this.setState(TransitionState.SETUP);
            this.setState(TransitionState.MOVING);
        }
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null && this.mInteractionEnabled) {
            if(motionScene0.mViewTransitionController != null) {
                this.mScene.mViewTransitionController.touchEvent(motionEvent0);
            }
            Transition motionScene$Transition0 = this.mScene.mCurrentTransition;
            if(motionScene$Transition0 != null && motionScene$Transition0.isEnabled()) {
                TouchResponse touchResponse0 = motionScene$Transition0.getTouchResponse();
                if(touchResponse0 != null) {
                    if(motionEvent0.getAction() == 0) {
                        RectF rectF0 = touchResponse0.getTouchRegion(this, new RectF());
                        if(rectF0 != null && !rectF0.contains(motionEvent0.getX(), motionEvent0.getY())) {
                            return false;
                        }
                    }
                    int v = touchResponse0.getTouchRegionId();
                    if(v != -1) {
                        if(this.mRegionView == null || this.mRegionView.getId() != v) {
                            this.mRegionView = this.findViewById(v);
                        }
                        View view0 = this.mRegionView;
                        if(view0 != null) {
                            this.mBoundsCheck.set(((float)view0.getLeft()), ((float)this.mRegionView.getTop()), ((float)this.mRegionView.getRight()), ((float)this.mRegionView.getBottom()));
                            return !this.mBoundsCheck.contains(motionEvent0.getX(), motionEvent0.getY()) || this.handlesTouchEvent(((float)this.mRegionView.getLeft()), ((float)this.mRegionView.getTop()), this.mRegionView, motionEvent0) ? false : this.onTouchEvent(motionEvent0);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        try {
            this.mInLayout = true;
            if(this.mScene == null) {
                super.onLayout(z, v, v1, v2, v3);
                this.mInLayout = false;
                return;
            }
            int v4 = v2 - v;
            int v5 = v3 - v1;
            if(this.mLastLayoutWidth != v4 || this.mLastLayoutHeight != v5) {
                this.rebuildScene();
                this.evaluate(true);
            }
            this.mLastLayoutWidth = v4;
            this.mLastLayoutHeight = v5;
            this.mOldWidth = v4;
            this.mOldHeight = v5;
            this.mInLayout = false;
        }
        catch(Throwable throwable0) {
            this.mInLayout = false;
            throw throwable0;
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void onMeasure(int v, int v1) {
        boolean z = false;
        if(this.mScene == null) {
            super.onMeasure(v, v1);
            return;
        }
        boolean z1 = this.mLastWidthMeasureSpec != v || this.mLastHeightMeasureSpec != v1;
        if(this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            this.onNewStateAttachHandlers();
            this.processTransitionCompleted();
            z1 = true;
        }
        if(this.mDirtyHierarchy) {
            z1 = true;
        }
        this.mLastWidthMeasureSpec = v;
        this.mLastHeightMeasureSpec = v1;
        int v2 = this.mScene.getStartId();
        int v3 = this.mScene.getEndId();
        if(!z1 && !this.mModel.isNotConfiguredWith(v2, v3) || this.mBeginState == -1) {
            if(z1) {
                super.onMeasure(v, v1);
            }
            z = true;
        }
        else {
            super.onMeasure(v, v1);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(v2), this.mScene.getConstraintSet(v3));
            this.mModel.reEvaluateState();
            this.mModel.setMeasuredId(v2, v3);
        }
        if(this.mMeasureDuringTransition || z) {
            int v4 = this.getPaddingTop();
            int v5 = this.getPaddingBottom();
            int v6 = this.getPaddingLeft();
            int v7 = this.getPaddingRight();
            int v8 = this.mLayoutWidget.getWidth() + (v6 + v7);
            int v9 = this.mLayoutWidget.getHeight() + (v4 + v5);
            if(this.mWidthMeasureMode == 0x80000000 || this.mWidthMeasureMode == 0) {
                v8 = (int)(((float)this.mStartWrapWidth) + this.mPostInterpolationPosition * ((float)(this.mEndWrapWidth - this.mStartWrapWidth)));
                this.requestLayout();
            }
            if(this.mHeightMeasureMode == 0x80000000 || this.mHeightMeasureMode == 0) {
                v9 = (int)(((float)this.mStartWrapHeight) + this.mPostInterpolationPosition * ((float)(this.mEndWrapHeight - this.mStartWrapHeight)));
                this.requestLayout();
            }
            this.setMeasuredDimension(v8, v9);
        }
        this.evaluateLayout();
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view0, float f, float f1, boolean z) {
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view0, float f, float f1) {
        return false;
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v, int v2) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            int v3 = -1;
            Transition motionScene$Transition0 = motionScene0.mCurrentTransition;
            if(motionScene$Transition0 != null && motionScene$Transition0.isEnabled()) {
                TouchResponse touchResponse0 = motionScene$Transition0.getTouchResponse();
                if(touchResponse0 == null) {
                label_9:
                    if(motionScene0.getMoveWhenScrollAtTop()) {
                        TouchResponse touchResponse1 = motionScene$Transition0.getTouchResponse();
                        if(touchResponse1 != null && (touchResponse1.getFlags() & 4) != 0) {
                            v3 = v1;
                        }
                        if(this.mTransitionPosition != 0 && this.mTransitionPosition != 0x3F800000 || !view0.canScrollVertically(v3)) {
                            goto label_14;
                        }
                    }
                    else {
                    label_14:
                        if(motionScene$Transition0.getTouchResponse() != null && (motionScene$Transition0.getTouchResponse().getFlags() & 1) != 0) {
                            float f = motionScene0.getProgressDirection(((float)v), ((float)v1));
                            if(this.mTransitionLastPosition <= 0.0f && f < 0.0f || this.mTransitionLastPosition >= 1.0f && f > 0.0f) {
                                view0.setNestedScrollingEnabled(false);
                                view0.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        view0.setNestedScrollingEnabled(true);
                                    }
                                });
                                return;
                            }
                        }
                        float f1 = this.mTransitionPosition;
                        this.mScrollTargetDX = (float)v;
                        this.mScrollTargetDY = (float)v1;
                        this.mScrollTargetDT = (float)(((double)(19053703552300L - this.mScrollTargetTime)) * 1.000000E-09);
                        this.mScrollTargetTime = 19053703552300L;
                        motionScene0.processScrollMove(((float)v), ((float)v1));
                        if(f1 != this.mTransitionPosition) {
                            arr_v[0] = v;
                            arr_v[1] = v1;
                        }
                        this.evaluate(false);
                        if(arr_v[0] != 0 || arr_v[1] != 0) {
                            this.mUndergoingMotion = true;
                        }
                    }
                }
                else {
                    int v4 = touchResponse0.getTouchRegionId();
                    if(v4 == -1 || view0.getId() == v4) {
                        goto label_9;
                    }
                }
            }
        }
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4) {
    }

    @Override  // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
        if(this.mUndergoingMotion || v != 0 || v1 != 0) {
            arr_v[0] += v2;
            arr_v[1] += v3;
        }
        this.mUndergoingMotion = false;
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view0, View view1, int v, int v1) {
        this.mScrollTargetTime = 19053991653400L;
        this.mScrollTargetDT = 0.0f;
        this.mScrollTargetDX = 0.0f;
        this.mScrollTargetDY = 0.0f;
    }

    void onNewStateAttachHandlers() {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            if(motionScene0.autoTransition(this, this.mCurrentState)) {
                this.requestLayout();
                return;
            }
            int v = this.mCurrentState;
            if(v != -1) {
                this.mScene.addOnClickListeners(this, v);
            }
            if(this.mScene.supportTouch()) {
                this.mScene.setupTouch();
            }
        }
    }

    @Override  // android.view.View
    public void onRtlPropertiesChanged(int v) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            motionScene0.setRtl(this.isRtl());
        }
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view0, View view1, int v, int v1) {
        return this.mScene != null && this.mScene.mCurrentTransition != null && this.mScene.mCurrentTransition.getTouchResponse() != null && (this.mScene.mCurrentTransition.getTouchResponse().getFlags() & 2) == 0;
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view0, int v) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            float f = this.mScrollTargetDT;
            if(f != 0.0f) {
                motionScene0.processScrollUp(this.mScrollTargetDX / f, this.mScrollTargetDY / f);
            }
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(this.mScene != null && this.mInteractionEnabled && this.mScene.supportTouch()) {
            Transition motionScene$Transition0 = this.mScene.mCurrentTransition;
            if(motionScene$Transition0 != null && !motionScene$Transition0.isEnabled()) {
                return super.onTouchEvent(motionEvent0);
            }
            this.mScene.processTouchEvent(motionEvent0, this.getCurrentState(), this);
            return this.mScene.mCurrentTransition.isTransitionFlag(4) ? this.mScene.mCurrentTransition.getTouchResponse().isDragStarted() : true;
        }
        return super.onTouchEvent(motionEvent0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void onViewAdded(View view0) {
        super.onViewAdded(view0);
        if(view0 instanceof MotionHelper) {
            if(this.mTransitionListeners == null) {
                this.mTransitionListeners = new CopyOnWriteArrayList();
            }
            this.mTransitionListeners.add(((MotionHelper)view0));
            if(((MotionHelper)view0).isUsedOnShow()) {
                if(this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList();
                }
                this.mOnShowHelpers.add(((MotionHelper)view0));
            }
            if(((MotionHelper)view0).isUseOnHide()) {
                if(this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList();
                }
                this.mOnHideHelpers.add(((MotionHelper)view0));
            }
            if(((MotionHelper)view0).isDecorator()) {
                if(this.mDecoratorsHelpers == null) {
                    this.mDecoratorsHelpers = new ArrayList();
                }
                this.mDecoratorsHelpers.add(((MotionHelper)view0));
            }
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void onViewRemoved(View view0) {
        super.onViewRemoved(view0);
        ArrayList arrayList0 = this.mOnShowHelpers;
        if(arrayList0 != null) {
            arrayList0.remove(view0);
        }
        ArrayList arrayList1 = this.mOnHideHelpers;
        if(arrayList1 != null) {
            arrayList1.remove(view0);
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void parseLayoutDescription(int v) {
        this.mConstraintLayoutSpec = null;
    }

    private void processTransitionCompleted() {
        if(this.mTransitionListener == null && (this.mTransitionListeners == null || this.mTransitionListeners.isEmpty())) {
            return;
        }
        this.mIsAnimating = false;
        for(Object object0: this.mTransitionCompleted) {
            Integer integer0 = (Integer)object0;
            TransitionListener motionLayout$TransitionListener0 = this.mTransitionListener;
            if(motionLayout$TransitionListener0 != null) {
                motionLayout$TransitionListener0.onTransitionCompleted(this, ((int)integer0));
            }
            CopyOnWriteArrayList copyOnWriteArrayList0 = this.mTransitionListeners;
            if(copyOnWriteArrayList0 != null) {
                for(Object object1: copyOnWriteArrayList0) {
                    ((TransitionListener)object1).onTransitionCompleted(this, ((int)integer0));
                }
            }
        }
        this.mTransitionCompleted.clear();
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e("MotionLayout", "This method is deprecated. Please call rebuildScene() instead.");
        this.rebuildScene();
    }

    public void rebuildScene() {
        this.mModel.reEvaluateState();
        this.invalidate();
    }

    public boolean removeTransitionListener(TransitionListener motionLayout$TransitionListener0) {
        return this.mTransitionListeners == null ? false : this.mTransitionListeners.remove(motionLayout$TransitionListener0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void requestLayout() {
        if(!this.mMeasureDuringTransition && this.mCurrentState == -1 && (this.mScene != null && this.mScene.mCurrentTransition != null)) {
            int v = this.mScene.mCurrentTransition.getLayoutDuringTransition();
            if(v == 0) {
                return;
            }
            if(v == 2) {
                int v1 = this.getChildCount();
                for(int v2 = 0; v2 < v1; ++v2) {
                    View view0 = this.getChildAt(v2);
                    ((MotionController)this.mFrameArrayList.get(view0)).remeasure();
                }
                return;
            }
        }
        super.requestLayout();
    }

    public void rotateTo(int v, int v1) {
        int v2 = 1;
        this.mInRotation = true;
        this.mPreRotateWidth = this.getWidth();
        this.mPreRotateHeight = this.getHeight();
        int v3 = this.getDisplay().getRotation();
        if((v3 + 1) % 4 <= (this.mPreviouseRotation + 1) % 4) {
            v2 = 2;
        }
        this.mRotatMode = v2;
        this.mPreviouseRotation = v3;
        int v4 = this.getChildCount();
        for(int v5 = 0; v5 < v4; ++v5) {
            View view0 = this.getChildAt(v5);
            ViewState viewState0 = (ViewState)this.mPreRotate.get(view0);
            if(viewState0 == null) {
                viewState0 = new ViewState();
                this.mPreRotate.put(view0, viewState0);
            }
            viewState0.getState(view0);
        }
        this.mBeginState = -1;
        this.mEndState = v;
        this.mScene.setTransition(-1, v);
        this.mModel.initFrom(this.mLayoutWidget, null, this.mScene.getConstraintSet(this.mEndState));
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.invalidate();
        this.transitionToEnd(new Runnable() {
            @Override
            public void run() {
                MotionLayout.this.mInRotation = false;
            }
        });
        if(v1 > 0) {
            this.mTransitionDuration = ((float)v1) / 1000.0f;
        }
    }

    public void scheduleTransitionTo(int v) {
        if(this.getCurrentState() == -1) {
            this.transitionToState(v);
            return;
        }
        int[] arr_v = this.mScheduledTransitionTo;
        if(arr_v == null) {
            this.mScheduledTransitionTo = new int[4];
        }
        else if(arr_v.length <= this.mScheduledTransitions) {
            this.mScheduledTransitionTo = Arrays.copyOf(arr_v, arr_v.length * 2);
        }
        int v1 = this.mScheduledTransitions;
        this.mScheduledTransitions = v1 + 1;
        this.mScheduledTransitionTo[v1] = v;
    }

    public void setDebugMode(int v) {
        this.mDebugPath = v;
        this.invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z) {
        this.mDelayedApply = z;
    }

    public void setInteractionEnabled(boolean z) {
        this.mInteractionEnabled = z;
    }

    public void setInterpolatedProgress(float f) {
        if(this.mScene != null) {
            this.setState(TransitionState.MOVING);
            Interpolator interpolator0 = this.mScene.getInterpolator();
            if(interpolator0 != null) {
                this.setProgress(interpolator0.getInterpolation(f));
                return;
            }
        }
        this.setProgress(f);
    }

    public void setOnHide(float f) {
        ArrayList arrayList0 = this.mOnHideHelpers;
        if(arrayList0 != null) {
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((MotionHelper)this.mOnHideHelpers.get(v1)).setProgress(f);
            }
        }
    }

    public void setOnShow(float f) {
        ArrayList arrayList0 = this.mOnShowHelpers;
        if(arrayList0 != null) {
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((MotionHelper)this.mOnShowHelpers.get(v1)).setProgress(f);
            }
        }
    }

    public void setProgress(float f) {
        int v = Float.compare(f, 0.0f);
        if(v < 0 || f > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setProgress(f);
            return;
        }
        if(v <= 0) {
            if(this.mTransitionLastPosition == 1.0f && this.mCurrentState == this.mEndState) {
                this.setState(TransitionState.MOVING);
            }
            this.mCurrentState = this.mBeginState;
            if(this.mTransitionLastPosition == 0.0f) {
                this.setState(TransitionState.FINISHED);
            }
        }
        else if(f >= 1.0f) {
            if(this.mTransitionLastPosition == 0.0f && this.mCurrentState == this.mBeginState) {
                this.setState(TransitionState.MOVING);
            }
            this.mCurrentState = this.mEndState;
            if(this.mTransitionLastPosition == 1.0f) {
                this.setState(TransitionState.FINISHED);
            }
        }
        else {
            this.mCurrentState = -1;
            this.setState(TransitionState.MOVING);
        }
        if(this.mScene == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.mTransitionGoalPosition = f;
        this.mTransitionPosition = f;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.mInterpolator = null;
        this.mInTransition = true;
        this.invalidate();
    }

    public void setProgress(float f, float f1) {
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setProgress(f);
            this.mStateCache.setVelocity(f1);
            return;
        }
        this.setProgress(f);
        this.setState(TransitionState.MOVING);
        this.mLastVelocity = f1;
        float f2 = 1.0f;
        int v = Float.compare(f1, 0.0f);
        if(v != 0) {
            if(v <= 0) {
                f2 = 0.0f;
            }
            this.animateTo(f2);
            return;
        }
        if(f != 0 && f != 0x3F800000) {
            if(f <= 0.5f) {
                f2 = 0.0f;
            }
            this.animateTo(f2);
        }
    }

    public void setScene(MotionScene motionScene0) {
        this.mScene = motionScene0;
        motionScene0.setRtl(this.isRtl());
        this.rebuildScene();
    }

    void setStartState(int v) {
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setStartState(v);
            this.mStateCache.setEndState(v);
            return;
        }
        this.mCurrentState = v;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int v, int v1, int v2) {
        this.setState(TransitionState.SETUP);
        this.mCurrentState = v;
        this.mBeginState = -1;
        this.mEndState = -1;
        if(this.mConstraintLayoutSpec != null) {
            this.mConstraintLayoutSpec.updateConstraints(v, ((float)v1), ((float)v2));
            return;
        }
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            motionScene0.getConstraintSet(v).applyTo(this);
        }
    }

    void setState(TransitionState motionLayout$TransitionState0) {
        if(motionLayout$TransitionState0 != TransitionState.FINISHED || this.mCurrentState != -1) {
            TransitionState motionLayout$TransitionState1 = this.mTransitionState;
            this.mTransitionState = motionLayout$TransitionState0;
            if(motionLayout$TransitionState1 == TransitionState.MOVING && motionLayout$TransitionState0 == TransitionState.MOVING) {
                this.fireTransitionChange();
            }
            switch(motionLayout$TransitionState1.ordinal()) {
                case 0: 
                case 1: {
                    if(motionLayout$TransitionState0 == TransitionState.MOVING) {
                        this.fireTransitionChange();
                    }
                    if(motionLayout$TransitionState0 == TransitionState.FINISHED) {
                        this.fireTransitionCompleted();
                        return;
                    }
                    break;
                }
                case 2: {
                    if(motionLayout$TransitionState0 == TransitionState.FINISHED) {
                        this.fireTransitionCompleted();
                        return;
                    }
                    break;
                }
            }
        }
    }

    public void setTransition(int v) {
        float f1;
        if(this.mScene != null) {
            Transition motionScene$Transition0 = this.getTransition(v);
            this.mBeginState = motionScene$Transition0.getStartConstraintSetId();
            this.mEndState = motionScene$Transition0.getEndConstraintSetId();
            if(!this.isAttachedToWindow()) {
                if(this.mStateCache == null) {
                    this.mStateCache = new StateCache(this);
                }
                this.mStateCache.setStartState(this.mBeginState);
                this.mStateCache.setEndState(this.mEndState);
                return;
            }
            int v1 = this.mCurrentState;
            float f = 0.0f;
            if(v1 == this.mBeginState) {
                f1 = 0.0f;
            }
            else {
                f1 = v1 == this.mEndState ? 1.0f : NaNf;
            }
            this.mScene.setTransition(motionScene$Transition0);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
            this.rebuildScene();
            if(this.mTransitionLastPosition != f1) {
                switch(f1) {
                    case 0: {
                        this.endTrigger(true);
                        this.mScene.getConstraintSet(this.mBeginState).applyTo(this);
                        break;
                    }
                    case 0x3F800000: {
                        this.endTrigger(false);
                        this.mScene.getConstraintSet(this.mEndState).applyTo(this);
                    }
                }
            }
            if(!Float.isNaN(f1)) {
                f = f1;
            }
            this.mTransitionLastPosition = f;
            if(Float.isNaN(f1)) {
                Log.v("MotionLayout", ".(null:-1) transitionToStart ");
                this.transitionToStart();
                return;
            }
            this.setProgress(f1);
        }
    }

    public void setTransition(int v, int v1) {
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setStartState(v);
            this.mStateCache.setEndState(v1);
            return;
        }
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            this.mBeginState = v;
            this.mEndState = v1;
            motionScene0.setTransition(v, v1);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(v), this.mScene.getConstraintSet(v1));
            this.rebuildScene();
            this.mTransitionLastPosition = 0.0f;
            this.transitionToStart();
        }
    }

    protected void setTransition(Transition motionScene$Transition0) {
        this.mScene.setTransition(motionScene$Transition0);
        this.setState(TransitionState.SETUP);
        if(this.mCurrentState == this.mScene.getEndId()) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        }
        else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = motionScene$Transition0.isTransitionFlag(1) ? -1L : 19054831876700L;
        int v = this.mScene.getStartId();
        int v1 = this.mScene.getEndId();
        if(v == this.mBeginState && v1 == this.mEndState) {
            return;
        }
        this.mBeginState = v;
        this.mEndState = v1;
        this.mScene.setTransition(v, v1);
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        this.mModel.setMeasuredId(this.mBeginState, this.mEndState);
        this.mModel.reEvaluateState();
        this.rebuildScene();
    }

    public void setTransitionDuration(int v) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 == null) {
            Log.e("MotionLayout", "MotionScene not defined");
            return;
        }
        motionScene0.setDuration(v);
    }

    public void setTransitionListener(TransitionListener motionLayout$TransitionListener0) {
        this.mTransitionListener = motionLayout$TransitionListener0;
    }

    public void setTransitionState(Bundle bundle0) {
        if(this.mStateCache == null) {
            this.mStateCache = new StateCache(this);
        }
        this.mStateCache.setTransitionState(bundle0);
        if(this.isAttachedToWindow()) {
            this.mStateCache.apply();
        }
    }

    private void setupMotionViews() {
        int v = this.getChildCount();
        this.mModel.build();
        boolean z = true;
        this.mInTransition = true;
        SparseArray sparseArray0 = new SparseArray();
        int v1 = 0;
        for(int v2 = 0; v2 < v; ++v2) {
            View view0 = this.getChildAt(v2);
            sparseArray0.put(view0.getId(), ((MotionController)this.mFrameArrayList.get(view0)));
        }
        int v3 = this.getWidth();
        int v4 = this.getHeight();
        int v5 = this.mScene.gatPathMotionArc();
        if(v5 != -1) {
            for(int v6 = 0; v6 < v; ++v6) {
                MotionController motionController0 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v6));
                if(motionController0 != null) {
                    motionController0.setPathMotionArc(v5);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray0 = new SparseBooleanArray();
        int[] arr_v = new int[this.mFrameArrayList.size()];
        int v8 = 0;
        for(int v7 = 0; v7 < v; ++v7) {
            View view1 = this.getChildAt(v7);
            MotionController motionController1 = (MotionController)this.mFrameArrayList.get(view1);
            if(motionController1.getAnimateRelativeTo() != -1) {
                sparseBooleanArray0.put(motionController1.getAnimateRelativeTo(), true);
                arr_v[v8] = motionController1.getAnimateRelativeTo();
                ++v8;
            }
        }
        if(this.mDecoratorsHelpers == null) {
            for(int v11 = 0; v11 < v8; ++v11) {
                MotionController motionController4 = (MotionController)this.mFrameArrayList.get(this.findViewById(arr_v[v11]));
                if(motionController4 != null) {
                    this.mScene.getKeyFrames(motionController4);
                    motionController4.setup(v3, v4, this.mTransitionDuration, 19042060321700L);
                }
            }
        }
        else {
            for(int v9 = 0; v9 < v8; ++v9) {
                MotionController motionController2 = (MotionController)this.mFrameArrayList.get(this.findViewById(arr_v[v9]));
                if(motionController2 != null) {
                    this.mScene.getKeyFrames(motionController2);
                }
            }
            for(Object object0: this.mDecoratorsHelpers) {
                ((MotionHelper)object0).onPreSetup(this, this.mFrameArrayList);
            }
            for(int v10 = 0; v10 < v8; ++v10) {
                MotionController motionController3 = (MotionController)this.mFrameArrayList.get(this.findViewById(arr_v[v10]));
                if(motionController3 != null) {
                    motionController3.setup(v3, v4, this.mTransitionDuration, 19042059901400L);
                }
            }
        }
        for(int v12 = 0; v12 < v; ++v12) {
            View view2 = this.getChildAt(v12);
            MotionController motionController5 = (MotionController)this.mFrameArrayList.get(view2);
            if(!sparseBooleanArray0.get(view2.getId()) && motionController5 != null) {
                this.mScene.getKeyFrames(motionController5);
                motionController5.setup(v3, v4, this.mTransitionDuration, 19042060689600L);
            }
        }
        float f = this.mScene.getStaggered();
        if(f != 0.0f) {
            if(((double)f) >= 0.0) {
                z = false;
            }
            float f1 = Math.abs(f);
            float f2 = -3.402823E+38f;
            float f3 = 3.402823E+38f;
            float f4 = 3.402823E+38f;
            float f5 = -3.402823E+38f;
            for(int v13 = 0; v13 < v; ++v13) {
                MotionController motionController6 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v13));
                if(!Float.isNaN(motionController6.mMotionStagger)) {
                    for(int v14 = 0; v14 < v; ++v14) {
                        MotionController motionController7 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v14));
                        if(!Float.isNaN(motionController7.mMotionStagger)) {
                            f3 = Math.min(f3, motionController7.mMotionStagger);
                            f2 = Math.max(f2, motionController7.mMotionStagger);
                        }
                    }
                    while(v1 < v) {
                        MotionController motionController8 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v1));
                        if(!Float.isNaN(motionController8.mMotionStagger)) {
                            motionController8.mStaggerScale = 1.0f / (1.0f - f1);
                            motionController8.mStaggerOffset = z ? f1 - (f2 - motionController8.mMotionStagger) / (f2 - f3) * f1 : f1 - (motionController8.mMotionStagger - f3) * f1 / (f2 - f3);
                        }
                        ++v1;
                    }
                    return;
                }
                float f6 = motionController6.getFinalX();
                float f7 = motionController6.getFinalY();
                float f8 = z ? f7 - f6 : f7 + f6;
                f4 = Math.min(f4, f8);
                f5 = Math.max(f5, f8);
            }
            while(v1 < v) {
                MotionController motionController9 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v1));
                float f9 = motionController9.getFinalX();
                float f10 = motionController9.getFinalY();
                motionController9.mStaggerScale = 1.0f / (1.0f - f1);
                motionController9.mStaggerOffset = f1 - ((z ? f10 - f9 : f10 + f9) - f4) * f1 / (f5 - f4);
                ++v1;
            }
        }
    }

    private Rect toRect(ConstraintWidget constraintWidget0) {
        this.mTempRect.top = constraintWidget0.getY();
        this.mTempRect.left = constraintWidget0.getX();
        this.mTempRect.right = constraintWidget0.getWidth() + this.mTempRect.left;
        this.mTempRect.bottom = constraintWidget0.getHeight() + this.mTempRect.top;
        return this.mTempRect;
    }

    @Override  // android.view.View
    public String toString() {
        Context context0 = this.getContext();
        return Debug.getName(context0, this.mBeginState) + "->" + Debug.getName(context0, this.mEndState) + " (pos:" + this.mTransitionLastPosition + " Dpos/Dt:" + this.mLastVelocity;
    }

    public void touchAnimateTo(int v, float f, float f1) {
        float f2;
        if(this.mScene == null || this.mTransitionLastPosition == f) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = 19055095791000L;
        this.mTransitionDuration = ((float)this.mScene.getDuration()) / 1000.0f;
        this.mTransitionGoalPosition = f;
        this.mInTransition = true;
        if(v == 0 || v == 1 || v == 2) {
        label_24:
            if(v == 1 || v == 7) {
                f2 = 0.0f;
            }
            else if(v != 2 && v != 6) {
                f2 = f;
            }
            else {
                f2 = 1.0f;
            }
            if(this.mScene.getAutoCompleteMode() == 0) {
                this.mStopLogic.config(this.mTransitionLastPosition, f2, f1, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
            }
            else {
                this.mStopLogic.springConfig(this.mTransitionLastPosition, f2, f1, this.mScene.getSpringMass(), this.mScene.getSpringStiffiness(), this.mScene.getSpringDamping(), this.mScene.getSpringStopThreshold(), this.mScene.getSpringBoundary());
            }
            this.mTransitionGoalPosition = f2;
            this.mCurrentState = this.mCurrentState;
            this.mInterpolator = this.mStopLogic;
        }
        else if(v != 4) {
            switch(v) {
                case 5: {
                    if(MotionLayout.willJump(f1, this.mTransitionLastPosition, this.mScene.getMaxAcceleration())) {
                        this.mDecelerateLogic.config(f1, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                        this.mInterpolator = this.mDecelerateLogic;
                    }
                    else {
                        this.mStopLogic.config(this.mTransitionLastPosition, f, f1, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                        this.mLastVelocity = 0.0f;
                        this.mTransitionGoalPosition = f;
                        this.mCurrentState = this.mCurrentState;
                        this.mInterpolator = this.mStopLogic;
                    }
                    break;
                }
                case 6: 
                case 7: {
                    goto label_24;
                }
            }
        }
        else {
            this.mDecelerateLogic.config(f1, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
            this.mInterpolator = this.mDecelerateLogic;
        }
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = 19055104456800L;
        this.invalidate();
    }

    public void touchSpringTo(float f, float f1) {
        if(this.mScene == null || this.mTransitionLastPosition == f) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = 19055154956500L;
        this.mTransitionDuration = ((float)this.mScene.getDuration()) / 1000.0f;
        this.mTransitionGoalPosition = f;
        this.mInTransition = true;
        this.mStopLogic.springConfig(this.mTransitionLastPosition, f, f1, this.mScene.getSpringMass(), this.mScene.getSpringStiffiness(), this.mScene.getSpringDamping(), this.mScene.getSpringStopThreshold(), this.mScene.getSpringBoundary());
        this.mTransitionGoalPosition = f;
        this.mCurrentState = this.mCurrentState;
        this.mInterpolator = this.mStopLogic;
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = 19055158082000L;
        this.invalidate();
    }

    public void transitionToEnd() {
        this.animateTo(1.0f);
        this.mOnComplete = null;
    }

    public void transitionToEnd(Runnable runnable0) {
        this.animateTo(1.0f);
        this.mOnComplete = runnable0;
    }

    public void transitionToStart() {
        this.animateTo(0.0f);
    }

    public void transitionToStart(Runnable runnable0) {
        this.animateTo(0.0f);
        this.mOnComplete = runnable0;
    }

    public void transitionToState(int v) {
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setEndState(v);
            return;
        }
        this.transitionToState(v, -1, -1);
    }

    public void transitionToState(int v, int v1) {
        if(!this.isAttachedToWindow()) {
            if(this.mStateCache == null) {
                this.mStateCache = new StateCache(this);
            }
            this.mStateCache.setEndState(v);
            return;
        }
        this.transitionToState(v, -1, -1, v1);
    }

    public void transitionToState(int v, int v1, int v2) {
        this.transitionToState(v, v1, v2, -1);
    }

    public void transitionToState(int v, int v1, int v2, int v3) {
        if(this.mScene != null && this.mScene.mStateSet != null) {
            int v4 = this.mScene.mStateSet.convertToConstraintSet(this.mCurrentState, v, ((float)v1), ((float)v2));
            if(v4 != -1) {
                v = v4;
            }
        }
        int v5 = this.mCurrentState;
        if(v5 != v) {
            if(this.mBeginState == v) {
                this.animateTo(0.0f);
                if(v3 > 0) {
                    this.mTransitionDuration = ((float)v3) / 1000.0f;
                }
            }
            else if(this.mEndState == v) {
                this.animateTo(1.0f);
                if(v3 > 0) {
                    this.mTransitionDuration = ((float)v3) / 1000.0f;
                }
            }
            else {
                this.mEndState = v;
                if(v5 == -1) {
                    goto label_25;
                }
                this.setTransition(v5, v);
                this.animateTo(1.0f);
                this.mTransitionLastPosition = 0.0f;
                this.transitionToEnd();
                if(v3 > 0) {
                    this.mTransitionDuration = ((float)v3) / 1000.0f;
                    return;
                label_25:
                    this.mTemporalInterpolator = false;
                    this.mTransitionGoalPosition = 1.0f;
                    this.mTransitionPosition = 0.0f;
                    this.mTransitionLastPosition = 0.0f;
                    this.mTransitionLastTime = 19055476549500L;
                    this.mAnimationStartTime = 19055477182600L;
                    this.mTransitionInstantly = false;
                    this.mInterpolator = null;
                    if(v3 == -1) {
                        this.mTransitionDuration = ((float)this.mScene.getDuration()) / 1000.0f;
                    }
                    this.mBeginState = -1;
                    this.mScene.setTransition(-1, this.mEndState);
                    SparseArray sparseArray0 = new SparseArray();
                    if(v3 == 0) {
                        this.mTransitionDuration = ((float)this.mScene.getDuration()) / 1000.0f;
                    }
                    else if(v3 > 0) {
                        this.mTransitionDuration = ((float)v3) / 1000.0f;
                    }
                    int v7 = this.getChildCount();
                    this.mFrameArrayList.clear();
                    for(int v8 = 0; v8 < v7; ++v8) {
                        View view0 = this.getChildAt(v8);
                        MotionController motionController0 = new MotionController(view0);
                        this.mFrameArrayList.put(view0, motionController0);
                        sparseArray0.put(view0.getId(), ((MotionController)this.mFrameArrayList.get(view0)));
                    }
                    this.mInTransition = true;
                    this.mModel.initFrom(this.mLayoutWidget, null, this.mScene.getConstraintSet(v));
                    this.rebuildScene();
                    this.mModel.build();
                    this.computeCurrentPositions();
                    int v9 = this.getWidth();
                    int v10 = this.getHeight();
                    if(this.mDecoratorsHelpers == null) {
                        for(int v13 = 0; v13 < v7; ++v13) {
                            MotionController motionController3 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v13));
                            if(motionController3 != null) {
                                this.mScene.getKeyFrames(motionController3);
                                motionController3.setup(v9, v10, this.mTransitionDuration, 19055478153700L);
                            }
                        }
                    }
                    else {
                        for(int v11 = 0; v11 < v7; ++v11) {
                            MotionController motionController1 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v11));
                            if(motionController1 != null) {
                                this.mScene.getKeyFrames(motionController1);
                            }
                        }
                        for(Object object0: this.mDecoratorsHelpers) {
                            ((MotionHelper)object0).onPreSetup(this, this.mFrameArrayList);
                        }
                        for(int v12 = 0; v12 < v7; ++v12) {
                            MotionController motionController2 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v12));
                            if(motionController2 != null) {
                                motionController2.setup(v9, v10, this.mTransitionDuration, 19055477776500L);
                            }
                        }
                    }
                    float f = this.mScene.getStaggered();
                    if(f != 0.0f) {
                        float f1 = 3.402823E+38f;
                        float f2 = -3.402823E+38f;
                        for(int v14 = 0; v14 < v7; ++v14) {
                            MotionController motionController4 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v14));
                            float f3 = motionController4.getFinalY() + motionController4.getFinalX();
                            f1 = Math.min(f1, f3);
                            f2 = Math.max(f2, f3);
                        }
                        for(int v6 = 0; v6 < v7; ++v6) {
                            MotionController motionController5 = (MotionController)this.mFrameArrayList.get(this.getChildAt(v6));
                            motionController5.mStaggerScale = 1.0f / (1.0f - f);
                            motionController5.mStaggerOffset = f - (motionController5.getFinalX() + motionController5.getFinalY() - f1) * f / (f2 - f1);
                        }
                    }
                    this.mTransitionPosition = 0.0f;
                    this.mTransitionLastPosition = 0.0f;
                    this.mInTransition = true;
                    this.invalidate();
                }
            }
        }
    }

    public void updateState() {
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        this.rebuildScene();
    }

    public void updateState(int v, ConstraintSet constraintSet0) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            motionScene0.setConstraintSet(v, constraintSet0);
        }
        this.updateState();
        if(this.mCurrentState == v) {
            constraintSet0.applyTo(this);
        }
    }

    public void updateStateAnimate(int v, ConstraintSet constraintSet0, int v1) {
        if(this.mScene != null && this.mCurrentState == v) {
            ConstraintSet constraintSet1 = this.getConstraintSet(v);
            this.updateState(id.view_transition, constraintSet1);
            this.setState(id.view_transition, -1, -1);
            this.updateState(v, constraintSet0);
            Transition motionScene$Transition0 = new Transition(-1, this.mScene, id.view_transition, v);
            motionScene$Transition0.setDuration(v1);
            this.setTransition(motionScene$Transition0);
            this.transitionToEnd();
        }
    }

    public void viewTransition(int v, View[] arr_view) {
        MotionScene motionScene0 = this.mScene;
        if(motionScene0 != null) {
            motionScene0.viewTransition(v, arr_view);
            return;
        }
        Log.e("MotionLayout", " no motionScene");
    }

    private static boolean willJump(float f, float f1, float f2) {
        if(f > 0.0f) {
            float f3 = f / f2;
            return f1 + (f * f3 - f2 * f3 * f3 / 2.0f) > 1.0f;
        }
        float f4 = -f / f2;
        return f1 + (f * f4 + f2 * f4 * f4 / 2.0f) < 0.0f;
    }
}

