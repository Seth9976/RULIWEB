package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.FillType;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser.PathDataNode;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
    static class VClipPath extends VPath {
        VClipPath() {
        }

        VClipPath(VClipPath vectorDrawableCompat$VClipPath0) {
            super(vectorDrawableCompat$VClipPath0);
        }

        public void inflate(Resources resources0, AttributeSet attributeSet0, Resources.Theme resources$Theme0, XmlPullParser xmlPullParser0) {
            if(!TypedArrayUtils.hasAttribute(xmlPullParser0, "pathData")) {
                return;
            }
            TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
            this.updateStateFromTypedArray(typedArray0, xmlPullParser0);
            typedArray0.recycle();
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VPath
        public boolean isClipPath() {
            return true;
        }

        private void updateStateFromTypedArray(TypedArray typedArray0, XmlPullParser xmlPullParser0) {
            String s = typedArray0.getString(0);
            if(s != null) {
                this.mPathName = s;
            }
            String s1 = typedArray0.getString(1);
            if(s1 != null) {
                this.mNodes = PathParser.createNodesFromPathData(s1);
            }
            this.mFillRule = TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "fillType", 2, 0);
        }
    }

    static class VFullPath extends VPath {
        float mFillAlpha;
        ComplexColorCompat mFillColor;
        float mStrokeAlpha;
        ComplexColorCompat mStrokeColor;
        Paint.Cap mStrokeLineCap;
        Paint.Join mStrokeLineJoin;
        float mStrokeMiterlimit;
        float mStrokeWidth;
        private int[] mThemeAttrs;
        float mTrimPathEnd;
        float mTrimPathOffset;
        float mTrimPathStart;

        VFullPath() {
            this.mStrokeWidth = 0.0f;
            this.mStrokeAlpha = 1.0f;
            this.mFillAlpha = 1.0f;
            this.mTrimPathStart = 0.0f;
            this.mTrimPathEnd = 1.0f;
            this.mTrimPathOffset = 0.0f;
            this.mStrokeLineCap = Paint.Cap.BUTT;
            this.mStrokeLineJoin = Paint.Join.MITER;
            this.mStrokeMiterlimit = 4.0f;
        }

        VFullPath(VFullPath vectorDrawableCompat$VFullPath0) {
            super(vectorDrawableCompat$VFullPath0);
            this.mThemeAttrs = vectorDrawableCompat$VFullPath0.mThemeAttrs;
            this.mStrokeColor = vectorDrawableCompat$VFullPath0.mStrokeColor;
            this.mStrokeWidth = vectorDrawableCompat$VFullPath0.mStrokeWidth;
            this.mStrokeAlpha = vectorDrawableCompat$VFullPath0.mStrokeAlpha;
            this.mFillColor = vectorDrawableCompat$VFullPath0.mFillColor;
            this.mFillRule = vectorDrawableCompat$VFullPath0.mFillRule;
            this.mFillAlpha = vectorDrawableCompat$VFullPath0.mFillAlpha;
            this.mTrimPathStart = vectorDrawableCompat$VFullPath0.mTrimPathStart;
            this.mTrimPathEnd = vectorDrawableCompat$VFullPath0.mTrimPathEnd;
            this.mTrimPathOffset = vectorDrawableCompat$VFullPath0.mTrimPathOffset;
            this.mStrokeLineCap = vectorDrawableCompat$VFullPath0.mStrokeLineCap;
            this.mStrokeLineJoin = vectorDrawableCompat$VFullPath0.mStrokeLineJoin;
            this.mStrokeMiterlimit = vectorDrawableCompat$VFullPath0.mStrokeMiterlimit;
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VPath
        public void applyTheme(Resources.Theme resources$Theme0) {
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VPath
        public boolean canApplyTheme() {
            return this.mThemeAttrs != null;
        }

        float getFillAlpha() {
            return this.mFillAlpha;
        }

        int getFillColor() {
            return this.mFillColor.getColor();
        }

        float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        int getStrokeColor() {
            return this.mStrokeColor.getColor();
        }

        private Paint.Cap getStrokeLineCap(int v, Paint.Cap paint$Cap0) {
            switch(v) {
                case 0: {
                    return Paint.Cap.BUTT;
                }
                case 1: {
                    return Paint.Cap.ROUND;
                }
                case 2: {
                    return Paint.Cap.SQUARE;
                }
                default: {
                    return paint$Cap0;
                }
            }
        }

        private Paint.Join getStrokeLineJoin(int v, Paint.Join paint$Join0) {
            switch(v) {
                case 0: {
                    return Paint.Join.MITER;
                }
                case 1: {
                    return Paint.Join.ROUND;
                }
                case 2: {
                    return Paint.Join.BEVEL;
                }
                default: {
                    return paint$Join0;
                }
            }
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        public void inflate(Resources resources0, AttributeSet attributeSet0, Resources.Theme resources$Theme0, XmlPullParser xmlPullParser0) {
            TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
            this.updateStateFromTypedArray(typedArray0, xmlPullParser0, resources$Theme0);
            typedArray0.recycle();
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VObject
        public boolean isStateful() {
            return this.mFillColor.isStateful() || this.mStrokeColor.isStateful();
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VObject
        public boolean onStateChanged(int[] arr_v) {
            boolean z = this.mFillColor.onStateChanged(arr_v);
            return this.mStrokeColor.onStateChanged(arr_v) | z;
        }

        void setFillAlpha(float f) {
            this.mFillAlpha = f;
        }

        void setFillColor(int v) {
            this.mFillColor.setColor(v);
        }

        void setStrokeAlpha(float f) {
            this.mStrokeAlpha = f;
        }

        void setStrokeColor(int v) {
            this.mStrokeColor.setColor(v);
        }

        void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
        }

        void setTrimPathEnd(float f) {
            this.mTrimPathEnd = f;
        }

        void setTrimPathOffset(float f) {
            this.mTrimPathOffset = f;
        }

        void setTrimPathStart(float f) {
            this.mTrimPathStart = f;
        }

        private void updateStateFromTypedArray(TypedArray typedArray0, XmlPullParser xmlPullParser0, Resources.Theme resources$Theme0) {
            this.mThemeAttrs = null;
            if(!TypedArrayUtils.hasAttribute(xmlPullParser0, "pathData")) {
                return;
            }
            String s = typedArray0.getString(0);
            if(s != null) {
                this.mPathName = s;
            }
            String s1 = typedArray0.getString(2);
            if(s1 != null) {
                this.mNodes = PathParser.createNodesFromPathData(s1);
            }
            this.mFillColor = TypedArrayUtils.getNamedComplexColor(typedArray0, xmlPullParser0, resources$Theme0, "fillColor", 1, 0);
            this.mFillAlpha = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "fillAlpha", 12, this.mFillAlpha);
            this.mStrokeLineCap = this.getStrokeLineCap(TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "strokeLineCap", 8, -1), this.mStrokeLineCap);
            this.mStrokeLineJoin = this.getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
            this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
            this.mStrokeColor = TypedArrayUtils.getNamedComplexColor(typedArray0, xmlPullParser0, resources$Theme0, "strokeColor", 3, 0);
            this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "strokeAlpha", 11, this.mStrokeAlpha);
            this.mStrokeWidth = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "strokeWidth", 4, this.mStrokeWidth);
            this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "trimPathEnd", 6, this.mTrimPathEnd);
            this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "trimPathOffset", 7, this.mTrimPathOffset);
            this.mTrimPathStart = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "trimPathStart", 5, this.mTrimPathStart);
            this.mFillRule = TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "fillType", 13, this.mFillRule);
        }
    }

    static class VGroup extends VObject {
        int mChangingConfigurations;
        final ArrayList mChildren;
        private String mGroupName;
        final Matrix mLocalMatrix;
        private float mPivotX;
        private float mPivotY;
        float mRotate;
        private float mScaleX;
        private float mScaleY;
        final Matrix mStackedMatrix;
        private int[] mThemeAttrs;
        private float mTranslateX;
        private float mTranslateY;

        public VGroup() {
            super(null);
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
        }

        public VGroup(VGroup vectorDrawableCompat$VGroup0, ArrayMap arrayMap0) {
            super(null);
            VFullPath vectorDrawableCompat$VFullPath0;
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            Matrix matrix0 = new Matrix();
            this.mLocalMatrix = matrix0;
            this.mRotate = vectorDrawableCompat$VGroup0.mRotate;
            this.mPivotX = vectorDrawableCompat$VGroup0.mPivotX;
            this.mPivotY = vectorDrawableCompat$VGroup0.mPivotY;
            this.mScaleX = vectorDrawableCompat$VGroup0.mScaleX;
            this.mScaleY = vectorDrawableCompat$VGroup0.mScaleY;
            this.mTranslateX = vectorDrawableCompat$VGroup0.mTranslateX;
            this.mTranslateY = vectorDrawableCompat$VGroup0.mTranslateY;
            this.mThemeAttrs = vectorDrawableCompat$VGroup0.mThemeAttrs;
            String s = vectorDrawableCompat$VGroup0.mGroupName;
            this.mGroupName = s;
            this.mChangingConfigurations = vectorDrawableCompat$VGroup0.mChangingConfigurations;
            if(s != null) {
                arrayMap0.put(s, this);
            }
            matrix0.set(vectorDrawableCompat$VGroup0.mLocalMatrix);
            ArrayList arrayList0 = vectorDrawableCompat$VGroup0.mChildren;
            for(int v = 0; v < arrayList0.size(); ++v) {
                Object object0 = arrayList0.get(v);
                if(object0 instanceof VGroup) {
                    VGroup vectorDrawableCompat$VGroup1 = new VGroup(((VGroup)object0), arrayMap0);
                    this.mChildren.add(vectorDrawableCompat$VGroup1);
                }
                else {
                    if(object0 instanceof VFullPath) {
                        vectorDrawableCompat$VFullPath0 = new VFullPath(((VFullPath)object0));
                    }
                    else {
                        if(!(object0 instanceof VClipPath)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        vectorDrawableCompat$VFullPath0 = new VClipPath(((VClipPath)object0));
                    }
                    this.mChildren.add(vectorDrawableCompat$VFullPath0);
                    if(vectorDrawableCompat$VFullPath0.mPathName != null) {
                        arrayMap0.put(vectorDrawableCompat$VFullPath0.mPathName, vectorDrawableCompat$VFullPath0);
                    }
                }
            }
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources resources0, AttributeSet attributeSet0, Resources.Theme resources$Theme0, XmlPullParser xmlPullParser0) {
            TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
            this.updateStateFromTypedArray(typedArray0, xmlPullParser0);
            typedArray0.recycle();
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VObject
        public boolean isStateful() {
            for(int v = 0; v < this.mChildren.size(); ++v) {
                if(((VObject)this.mChildren.get(v)).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$VObject
        public boolean onStateChanged(int[] arr_v) {
            boolean z = false;
            for(int v = 0; v < this.mChildren.size(); ++v) {
                z |= ((VObject)this.mChildren.get(v)).onStateChanged(arr_v);
            }
            return z;
        }

        public void setPivotX(float f) {
            if(f != this.mPivotX) {
                this.mPivotX = f;
                this.updateLocalMatrix();
            }
        }

        public void setPivotY(float f) {
            if(f != this.mPivotY) {
                this.mPivotY = f;
                this.updateLocalMatrix();
            }
        }

        public void setRotation(float f) {
            if(f != this.mRotate) {
                this.mRotate = f;
                this.updateLocalMatrix();
            }
        }

        public void setScaleX(float f) {
            if(f != this.mScaleX) {
                this.mScaleX = f;
                this.updateLocalMatrix();
            }
        }

        public void setScaleY(float f) {
            if(f != this.mScaleY) {
                this.mScaleY = f;
                this.updateLocalMatrix();
            }
        }

        public void setTranslateX(float f) {
            if(f != this.mTranslateX) {
                this.mTranslateX = f;
                this.updateLocalMatrix();
            }
        }

        public void setTranslateY(float f) {
            if(f != this.mTranslateY) {
                this.mTranslateY = f;
                this.updateLocalMatrix();
            }
        }

        private void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray typedArray0, XmlPullParser xmlPullParser0) {
            this.mThemeAttrs = null;
            this.mRotate = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "rotation", 5, this.mRotate);
            this.mPivotX = typedArray0.getFloat(1, this.mPivotX);
            this.mPivotY = typedArray0.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "translateY", 7, this.mTranslateY);
            String s = typedArray0.getString(0);
            if(s != null) {
                this.mGroupName = s;
            }
            this.updateLocalMatrix();
        }
    }

    static abstract class VObject {
        private VObject() {
        }

        VObject(androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.1 vectorDrawableCompat$10) {
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] arr_v) {
            return false;
        }
    }

    static abstract class VPath extends VObject {
        protected static final int FILL_TYPE_WINDING;
        int mChangingConfigurations;
        int mFillRule;
        protected PathDataNode[] mNodes;
        String mPathName;

        public VPath() {
            super(null);
            this.mNodes = null;
            this.mFillRule = 0;
        }

        public VPath(VPath vectorDrawableCompat$VPath0) {
            super(null);
            this.mFillRule = 0;
            this.mPathName = vectorDrawableCompat$VPath0.mPathName;
            this.mChangingConfigurations = vectorDrawableCompat$VPath0.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(vectorDrawableCompat$VPath0.mNodes);
        }

        public void applyTheme(Resources.Theme resources$Theme0) {
        }

        public boolean canApplyTheme() {
            return false;
        }

        public PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean isClipPath() {
            return false;
        }

        public String nodesToString(PathDataNode[] arr_pathParser$PathDataNode) {
            String s = " ";
            for(int v = 0; v < arr_pathParser$PathDataNode.length; ++v) {
                s = s + arr_pathParser$PathDataNode[v].mType + ":";
                float[] arr_f = arr_pathParser$PathDataNode[v].mParams;
                for(int v1 = 0; v1 < arr_f.length; ++v1) {
                    s = s + arr_f[v1] + ",";
                }
            }
            return s;
        }

        public void printVPath(int v) {
            String s = "";
            for(int v1 = 0; v1 < v; ++v1) {
                s = s + "    ";
            }
            Log.v("VectorDrawableCompat", s + "current path is :" + this.mPathName + " pathData is " + this.nodesToString(this.mNodes));
        }

        public void setPathData(PathDataNode[] arr_pathParser$PathDataNode) {
            if(!PathParser.canMorph(this.mNodes, arr_pathParser$PathDataNode)) {
                this.mNodes = PathParser.deepCopyNodes(arr_pathParser$PathDataNode);
                return;
            }
            PathParser.updateNodes(this.mNodes, arr_pathParser$PathDataNode);
        }

        public void toPath(Path path0) {
            path0.reset();
            PathDataNode[] arr_pathParser$PathDataNode = this.mNodes;
            if(arr_pathParser$PathDataNode != null) {
                PathDataNode.nodesToPath(arr_pathParser$PathDataNode, path0);
            }
        }
    }

    static class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX;
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        Boolean mIsStateful;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        Paint mStrokePaint;
        final ArrayMap mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;

        static {
            VPathRenderer.IDENTITY_MATRIX = new Matrix();
        }

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 0xFF;
            this.mRootName = null;
            this.mIsStateful = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer vectorDrawableCompat$VPathRenderer0) {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 0xFF;
            this.mRootName = null;
            this.mIsStateful = null;
            ArrayMap arrayMap0 = new ArrayMap();
            this.mVGTargetsMap = arrayMap0;
            this.mRootGroup = new VGroup(vectorDrawableCompat$VPathRenderer0.mRootGroup, arrayMap0);
            this.mPath = new Path(vectorDrawableCompat$VPathRenderer0.mPath);
            this.mRenderPath = new Path(vectorDrawableCompat$VPathRenderer0.mRenderPath);
            this.mBaseWidth = vectorDrawableCompat$VPathRenderer0.mBaseWidth;
            this.mBaseHeight = vectorDrawableCompat$VPathRenderer0.mBaseHeight;
            this.mViewportWidth = vectorDrawableCompat$VPathRenderer0.mViewportWidth;
            this.mViewportHeight = vectorDrawableCompat$VPathRenderer0.mViewportHeight;
            this.mChangingConfigurations = vectorDrawableCompat$VPathRenderer0.mChangingConfigurations;
            this.mRootAlpha = vectorDrawableCompat$VPathRenderer0.mRootAlpha;
            this.mRootName = vectorDrawableCompat$VPathRenderer0.mRootName;
            String s = vectorDrawableCompat$VPathRenderer0.mRootName;
            if(s != null) {
                arrayMap0.put(s, this);
            }
            this.mIsStateful = vectorDrawableCompat$VPathRenderer0.mIsStateful;
        }

        private static float cross(float f, float f1, float f2, float f3) [...] // Inlined contents

        public void draw(Canvas canvas0, int v, int v1, ColorFilter colorFilter0) {
            this.drawGroupTree(this.mRootGroup, VPathRenderer.IDENTITY_MATRIX, canvas0, v, v1, colorFilter0);
        }

        private void drawGroupTree(VGroup vectorDrawableCompat$VGroup0, Matrix matrix0, Canvas canvas0, int v, int v1, ColorFilter colorFilter0) {
            vectorDrawableCompat$VGroup0.mStackedMatrix.set(matrix0);
            vectorDrawableCompat$VGroup0.mStackedMatrix.preConcat(vectorDrawableCompat$VGroup0.mLocalMatrix);
            canvas0.save();
            for(int v2 = 0; v2 < vectorDrawableCompat$VGroup0.mChildren.size(); ++v2) {
                VObject vectorDrawableCompat$VObject0 = (VObject)vectorDrawableCompat$VGroup0.mChildren.get(v2);
                if(vectorDrawableCompat$VObject0 instanceof VGroup) {
                    this.drawGroupTree(((VGroup)vectorDrawableCompat$VObject0), vectorDrawableCompat$VGroup0.mStackedMatrix, canvas0, v, v1, colorFilter0);
                }
                else if(vectorDrawableCompat$VObject0 instanceof VPath) {
                    this.drawPath(vectorDrawableCompat$VGroup0, ((VPath)vectorDrawableCompat$VObject0), canvas0, v, v1, colorFilter0);
                }
            }
            canvas0.restore();
        }

        private void drawPath(VGroup vectorDrawableCompat$VGroup0, VPath vectorDrawableCompat$VPath0, Canvas canvas0, int v, int v1, ColorFilter colorFilter0) {
            float f = ((float)v) / this.mViewportWidth;
            float f1 = ((float)v1) / this.mViewportHeight;
            float f2 = Math.min(f, f1);
            this.mFinalPathMatrix.set(vectorDrawableCompat$VGroup0.mStackedMatrix);
            this.mFinalPathMatrix.postScale(f, f1);
            float f3 = this.getMatrixScale(vectorDrawableCompat$VGroup0.mStackedMatrix);
            if(f3 != 0.0f) {
                vectorDrawableCompat$VPath0.toPath(this.mPath);
                Path path0 = this.mPath;
                this.mRenderPath.reset();
                if(vectorDrawableCompat$VPath0.isClipPath()) {
                    this.mRenderPath.setFillType((vectorDrawableCompat$VPath0.mFillRule == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD));
                    this.mRenderPath.addPath(path0, this.mFinalPathMatrix);
                    canvas0.clipPath(this.mRenderPath);
                    return;
                }
                if(((VFullPath)vectorDrawableCompat$VPath0).mTrimPathStart != 0.0f || ((VFullPath)vectorDrawableCompat$VPath0).mTrimPathEnd != 1.0f) {
                    float f4 = (((VFullPath)vectorDrawableCompat$VPath0).mTrimPathStart + ((VFullPath)vectorDrawableCompat$VPath0).mTrimPathOffset) % 1.0f;
                    float f5 = (((VFullPath)vectorDrawableCompat$VPath0).mTrimPathEnd + ((VFullPath)vectorDrawableCompat$VPath0).mTrimPathOffset) % 1.0f;
                    if(this.mPathMeasure == null) {
                        this.mPathMeasure = new PathMeasure();
                    }
                    this.mPathMeasure.setPath(this.mPath, false);
                    float f6 = this.mPathMeasure.getLength();
                    float f7 = f4 * f6;
                    float f8 = f5 * f6;
                    path0.reset();
                    if(f7 > f8) {
                        this.mPathMeasure.getSegment(f7, f6, path0, true);
                        this.mPathMeasure.getSegment(0.0f, f8, path0, true);
                    }
                    else {
                        this.mPathMeasure.getSegment(f7, f8, path0, true);
                    }
                    path0.rLineTo(0.0f, 0.0f);
                }
                this.mRenderPath.addPath(path0, this.mFinalPathMatrix);
                if(((VFullPath)vectorDrawableCompat$VPath0).mFillColor.willDraw()) {
                    ComplexColorCompat complexColorCompat0 = ((VFullPath)vectorDrawableCompat$VPath0).mFillColor;
                    if(this.mFillPaint == null) {
                        Paint paint0 = new Paint(1);
                        this.mFillPaint = paint0;
                        paint0.setStyle(Paint.Style.FILL);
                    }
                    Paint paint1 = this.mFillPaint;
                    if(complexColorCompat0.isGradient()) {
                        Shader shader0 = complexColorCompat0.getShader();
                        shader0.setLocalMatrix(this.mFinalPathMatrix);
                        paint1.setShader(shader0);
                        paint1.setAlpha(Math.round(((VFullPath)vectorDrawableCompat$VPath0).mFillAlpha * 255.0f));
                    }
                    else {
                        paint1.setShader(null);
                        paint1.setAlpha(0xFF);
                        paint1.setColor(VectorDrawableCompat.applyAlpha(complexColorCompat0.getColor(), ((VFullPath)vectorDrawableCompat$VPath0).mFillAlpha));
                    }
                    paint1.setColorFilter(colorFilter0);
                    this.mRenderPath.setFillType((((VFullPath)vectorDrawableCompat$VPath0).mFillRule == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD));
                    canvas0.drawPath(this.mRenderPath, paint1);
                }
                if(((VFullPath)vectorDrawableCompat$VPath0).mStrokeColor.willDraw()) {
                    ComplexColorCompat complexColorCompat1 = ((VFullPath)vectorDrawableCompat$VPath0).mStrokeColor;
                    if(this.mStrokePaint == null) {
                        Paint paint2 = new Paint(1);
                        this.mStrokePaint = paint2;
                        paint2.setStyle(Paint.Style.STROKE);
                    }
                    Paint paint3 = this.mStrokePaint;
                    if(((VFullPath)vectorDrawableCompat$VPath0).mStrokeLineJoin != null) {
                        paint3.setStrokeJoin(((VFullPath)vectorDrawableCompat$VPath0).mStrokeLineJoin);
                    }
                    if(((VFullPath)vectorDrawableCompat$VPath0).mStrokeLineCap != null) {
                        paint3.setStrokeCap(((VFullPath)vectorDrawableCompat$VPath0).mStrokeLineCap);
                    }
                    paint3.setStrokeMiter(((VFullPath)vectorDrawableCompat$VPath0).mStrokeMiterlimit);
                    if(complexColorCompat1.isGradient()) {
                        Shader shader1 = complexColorCompat1.getShader();
                        shader1.setLocalMatrix(this.mFinalPathMatrix);
                        paint3.setShader(shader1);
                        paint3.setAlpha(Math.round(((VFullPath)vectorDrawableCompat$VPath0).mStrokeAlpha * 255.0f));
                    }
                    else {
                        paint3.setShader(null);
                        paint3.setAlpha(0xFF);
                        paint3.setColor(VectorDrawableCompat.applyAlpha(complexColorCompat1.getColor(), ((VFullPath)vectorDrawableCompat$VPath0).mStrokeAlpha));
                    }
                    paint3.setColorFilter(colorFilter0);
                    paint3.setStrokeWidth(((VFullPath)vectorDrawableCompat$VPath0).mStrokeWidth * (f2 * f3));
                    canvas0.drawPath(this.mRenderPath, paint3);
                }
            }
        }

        public float getAlpha() {
            return ((float)this.getRootAlpha()) / 255.0f;
        }

        private float getMatrixScale(Matrix matrix0) {
            float[] arr_f = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix0.mapVectors(arr_f);
            float f = Math.max(((float)Math.hypot(arr_f[0], arr_f[1])), ((float)Math.hypot(arr_f[2], arr_f[3])));
            return f > 0.0f ? Math.abs(arr_f[0] * arr_f[3] - arr_f[1] * arr_f[2]) / f : 0.0f;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public boolean isStateful() {
            if(this.mIsStateful == null) {
                this.mIsStateful = Boolean.valueOf(this.mRootGroup.isStateful());
            }
            return this.mIsStateful.booleanValue();
        }

        public boolean onStateChanged(int[] arr_v) {
            return this.mRootGroup.onStateChanged(arr_v);
        }

        public void setAlpha(float f) {
            this.setRootAlpha(((int)(f * 255.0f)));
        }

        public void setRootAlpha(int v) {
            this.mRootAlpha = v;
        }
    }

    static class VectorDrawableCompatState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        int[] mCachedThemeAttrs;
        ColorStateList mCachedTint;
        PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState() {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            this.mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0) {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if(vectorDrawableCompat$VectorDrawableCompatState0 != null) {
                this.mChangingConfigurations = vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations;
                this.mVPathRenderer = new VPathRenderer(vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer);
                if(vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer.mFillPaint != null) {
                    VPathRenderer vectorDrawableCompat$VPathRenderer0 = this.mVPathRenderer;
                    vectorDrawableCompat$VPathRenderer0.mFillPaint = new Paint(vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer.mFillPaint);
                }
                if(vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer.mStrokePaint != null) {
                    VPathRenderer vectorDrawableCompat$VPathRenderer1 = this.mVPathRenderer;
                    vectorDrawableCompat$VPathRenderer1.mStrokePaint = new Paint(vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer.mStrokePaint);
                }
                this.mTint = vectorDrawableCompat$VectorDrawableCompatState0.mTint;
                this.mTintMode = vectorDrawableCompat$VectorDrawableCompatState0.mTintMode;
                this.mAutoMirrored = vectorDrawableCompat$VectorDrawableCompatState0.mAutoMirrored;
            }
        }

        public boolean canReuseBitmap(int v, int v1) {
            return v == this.mCachedBitmap.getWidth() && v1 == this.mCachedBitmap.getHeight();
        }

        public boolean canReuseCache() {
            return !this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha();
        }

        public void createCachedBitmapIfNeeded(int v, int v1) {
            if(this.mCachedBitmap != null && this.canReuseBitmap(v, v1)) {
                return;
            }
            this.mCachedBitmap = Bitmap.createBitmap(v, v1, Bitmap.Config.ARGB_8888);
            this.mCacheDirty = true;
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas0, ColorFilter colorFilter0, Rect rect0) {
            Paint paint0 = this.getPaint(colorFilter0);
            canvas0.drawBitmap(this.mCachedBitmap, null, rect0, paint0);
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Paint getPaint(ColorFilter colorFilter0) {
            if(!this.hasTranslucentRoot() && colorFilter0 == null) {
                return null;
            }
            if(this.mTempPaint == null) {
                Paint paint0 = new Paint();
                this.mTempPaint = paint0;
                paint0.setFilterBitmap(true);
            }
            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            this.mTempPaint.setColorFilter(colorFilter0);
            return this.mTempPaint;
        }

        public boolean hasTranslucentRoot() {
            return this.mVPathRenderer.getRootAlpha() < 0xFF;
        }

        public boolean isStateful() {
            return this.mVPathRenderer.isStateful();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0) {
            return new VectorDrawableCompat(this);
        }

        public boolean onStateChanged(int[] arr_v) {
            boolean z = this.mVPathRenderer.onStateChanged(arr_v);
            this.mCacheDirty |= z;
            return z;
        }

        public void updateCacheStates() {
            this.mCachedTint = this.mTint;
            this.mCachedTintMode = this.mTintMode;
            this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
            this.mCachedAutoMirrored = this.mAutoMirrored;
            this.mCacheDirty = false;
        }

        public void updateCachedBitmap(int v, int v1) {
            this.mCachedBitmap.eraseColor(0);
            Canvas canvas0 = new Canvas(this.mCachedBitmap);
            this.mVPathRenderer.draw(canvas0, v, v1, null);
        }
    }

    static class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState drawable$ConstantState0) {
            this.mDelegateState = drawable$ConstantState0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            Drawable drawable0 = new VectorDrawableCompat();
            drawable0.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable();
            return drawable0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0) {
            Drawable drawable0 = new VectorDrawableCompat();
            drawable0.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable(resources0);
            return drawable0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0, Resources.Theme resources$Theme0) {
            Drawable drawable0 = new VectorDrawableCompat();
            drawable0.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable(resources0, resources$Theme0);
            return drawable0;
        }
    }

    private static final boolean DBG_VECTOR_DRAWABLE = false;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = null;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    static final String LOGTAG = "VectorDrawableCompat";
    private static final int MAX_CACHED_BITMAP_SIZE = 0x800;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    private boolean mAllowCaching;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    static {
        VectorDrawableCompat.DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompat$VectorDrawableCompatState0;
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, vectorDrawableCompat$VectorDrawableCompatState0.mTint, vectorDrawableCompat$VectorDrawableCompatState0.mTintMode);
    }

    static int applyAlpha(int v, float f) {
        return v & 0xFFFFFF | ((int)(((float)Color.alpha(v)) * f)) << 24;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void applyTheme(Resources.Theme resources$Theme0) {
        super.applyTheme(resources$Theme0);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }
        return false;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void clearColorFilter() {
        super.clearColorFilter();
    }

    public static VectorDrawableCompat create(Resources resources0, int v, Resources.Theme resources$Theme0) {
        if(Build.VERSION.SDK_INT >= 24) {
            VectorDrawableCompat vectorDrawableCompat0 = new VectorDrawableCompat();
            vectorDrawableCompat0.mDelegateDrawable = ResourcesCompat.getDrawable(resources0, v, resources$Theme0);
            vectorDrawableCompat0.mCachedConstantStateDelegate = new VectorDrawableDelegateState(vectorDrawableCompat0.mDelegateDrawable.getConstantState());
            return vectorDrawableCompat0;
        }
        try {
            XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
            AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
            do {
                int v1 = xmlResourceParser0.next();
            }
            while(v1 != 1 && v1 != 2);
            if(v1 != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            return VectorDrawableCompat.createFromXmlInner(resources0, xmlResourceParser0, attributeSet0, resources$Theme0);
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("VectorDrawableCompat", "parser error", xmlPullParserException0);
            return null;
        }
        catch(IOException iOException0) {
            Log.e("VectorDrawableCompat", "parser error", iOException0);
            return null;
        }
    }

    public static VectorDrawableCompat createFromXmlInner(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        VectorDrawableCompat vectorDrawableCompat0 = new VectorDrawableCompat();
        vectorDrawableCompat0.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        return vectorDrawableCompat0;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas0);
            return;
        }
        this.copyBounds(this.mTmpBounds);
        if(this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            ColorFilter colorFilter0 = this.mColorFilter;
            if(colorFilter0 == null) {
                colorFilter0 = this.mTintFilter;
            }
            canvas0.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float f = Math.abs(this.mTmpFloats[0]);
            float f1 = Math.abs(this.mTmpFloats[4]);
            if(Math.abs(this.mTmpFloats[1]) != 0.0f || Math.abs(this.mTmpFloats[3]) != 0.0f) {
                f = 1.0f;
                f1 = 1.0f;
            }
            int v = Math.min(0x800, ((int)(((float)this.mTmpBounds.width()) * f)));
            int v1 = Math.min(0x800, ((int)(((float)this.mTmpBounds.height()) * f1)));
            if(v > 0 && v1 > 0) {
                int v2 = canvas0.save();
                canvas0.translate(((float)this.mTmpBounds.left), ((float)this.mTmpBounds.top));
                if(this.needMirroring()) {
                    canvas0.translate(((float)this.mTmpBounds.width()), 0.0f);
                    canvas0.scale(-1.0f, 1.0f);
                }
                this.mTmpBounds.offsetTo(0, 0);
                this.mVectorState.createCachedBitmapIfNeeded(v, v1);
                if(!this.mAllowCaching) {
                    this.mVectorState.updateCachedBitmap(v, v1);
                }
                else if(!this.mVectorState.canReuseCache()) {
                    this.mVectorState.updateCachedBitmap(v, v1);
                    this.mVectorState.updateCacheStates();
                }
                this.mVectorState.drawCachedBitmapWithRootAlpha(canvas0, colorFilter0, this.mTmpBounds);
                canvas0.restoreToCount(v2);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mDelegateDrawable == null ? this.mVectorState.mVPathRenderer.getRootAlpha() : DrawableCompat.getAlpha(this.mDelegateDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mDelegateDrawable == null ? super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations() : this.mDelegateDrawable.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mDelegateDrawable == null ? this.mColorFilter : DrawableCompat.getColorFilter(this.mDelegateDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if(this.mDelegateDrawable != null && Build.VERSION.SDK_INT >= 24) {
            return new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations = this.getChangingConfigurations();
        return this.mVectorState;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDelegateDrawable == null ? ((int)this.mVectorState.mVPathRenderer.mBaseHeight) : this.mDelegateDrawable.getIntrinsicHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDelegateDrawable == null ? ((int)this.mVectorState.mVPathRenderer.mBaseWidth) : this.mDelegateDrawable.getIntrinsicWidth();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDelegateDrawable == null ? -3 : this.mDelegateDrawable.getOpacity();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public boolean getPadding(Rect rect0) {
        return super.getPadding(rect0);
    }

    public float getPixelSize() {
        return this.mVectorState == null || this.mVectorState.mVPathRenderer == null || this.mVectorState.mVPathRenderer.mBaseWidth == 0.0f || this.mVectorState.mVPathRenderer.mBaseHeight == 0.0f || this.mVectorState.mVPathRenderer.mViewportHeight == 0.0f || this.mVectorState.mVPathRenderer.mViewportWidth == 0.0f ? 1.0f : Math.min(this.mVectorState.mVPathRenderer.mViewportWidth / this.mVectorState.mVPathRenderer.mBaseWidth, this.mVectorState.mVPathRenderer.mViewportHeight / this.mVectorState.mVPathRenderer.mBaseHeight);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int[] getState() {
        return super.getState();
    }

    Object getTargetByName(String s) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(s);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override  // android.graphics.drawable.Drawable
    public void inflate(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0) throws XmlPullParserException, IOException {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.inflate(resources0, xmlPullParser0, attributeSet0);
            return;
        }
        this.inflate(resources0, xmlPullParser0, attributeSet0, null);
    }

    @Override  // android.graphics.drawable.Drawable
    public void inflate(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer = new VPathRenderer();
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
        this.updateStateFromTypedArray(typedArray0, xmlPullParser0, resources$Theme0);
        typedArray0.recycle();
        vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations = this.getChangingConfigurations();
        vectorDrawableCompat$VectorDrawableCompatState0.mCacheDirty = true;
        this.inflateInternal(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, vectorDrawableCompat$VectorDrawableCompatState0.mTint, vectorDrawableCompat$VectorDrawableCompatState0.mTintMode);
    }

    private void inflateInternal(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        VPathRenderer vectorDrawableCompat$VPathRenderer0 = vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer;
        ArrayDeque arrayDeque0 = new ArrayDeque();
        arrayDeque0.push(vectorDrawableCompat$VPathRenderer0.mRootGroup);
        int v = xmlPullParser0.getEventType();
        int v1 = xmlPullParser0.getDepth();
        boolean z = true;
        while(v != 1 && (xmlPullParser0.getDepth() >= v1 + 1 || v != 3)) {
            switch(v) {
                case 2: {
                    String s = xmlPullParser0.getName();
                    VGroup vectorDrawableCompat$VGroup0 = (VGroup)arrayDeque0.peek();
                    if("path".equals(s)) {
                        VFullPath vectorDrawableCompat$VFullPath0 = new VFullPath();
                        vectorDrawableCompat$VFullPath0.inflate(resources0, attributeSet0, resources$Theme0, xmlPullParser0);
                        vectorDrawableCompat$VGroup0.mChildren.add(vectorDrawableCompat$VFullPath0);
                        if(vectorDrawableCompat$VFullPath0.getPathName() != null) {
                            String s1 = vectorDrawableCompat$VFullPath0.getPathName();
                            vectorDrawableCompat$VPathRenderer0.mVGTargetsMap.put(s1, vectorDrawableCompat$VFullPath0);
                        }
                        vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations |= vectorDrawableCompat$VFullPath0.mChangingConfigurations;
                        z = false;
                    }
                    else if("clip-path".equals(s)) {
                        VClipPath vectorDrawableCompat$VClipPath0 = new VClipPath();
                        vectorDrawableCompat$VClipPath0.inflate(resources0, attributeSet0, resources$Theme0, xmlPullParser0);
                        vectorDrawableCompat$VGroup0.mChildren.add(vectorDrawableCompat$VClipPath0);
                        if(vectorDrawableCompat$VClipPath0.getPathName() != null) {
                            String s2 = vectorDrawableCompat$VClipPath0.getPathName();
                            vectorDrawableCompat$VPathRenderer0.mVGTargetsMap.put(s2, vectorDrawableCompat$VClipPath0);
                        }
                        vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations |= vectorDrawableCompat$VClipPath0.mChangingConfigurations;
                    }
                    else if("group".equals(s)) {
                        VGroup vectorDrawableCompat$VGroup1 = new VGroup();
                        vectorDrawableCompat$VGroup1.inflate(resources0, attributeSet0, resources$Theme0, xmlPullParser0);
                        vectorDrawableCompat$VGroup0.mChildren.add(vectorDrawableCompat$VGroup1);
                        arrayDeque0.push(vectorDrawableCompat$VGroup1);
                        if(vectorDrawableCompat$VGroup1.getGroupName() != null) {
                            vectorDrawableCompat$VPathRenderer0.mVGTargetsMap.put(vectorDrawableCompat$VGroup1.getGroupName(), vectorDrawableCompat$VGroup1);
                        }
                        vectorDrawableCompat$VectorDrawableCompatState0.mChangingConfigurations |= vectorDrawableCompat$VGroup1.mChangingConfigurations;
                    }
                    break;
                }
                case 3: {
                    if("group".equals(xmlPullParser0.getName())) {
                        arrayDeque0.pop();
                    }
                }
            }
            v = xmlPullParser0.next();
        }
        if(z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.invalidateSelf();
            return;
        }
        super.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.mDelegateDrawable == null ? this.mVectorState.mAutoMirrored : DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
    }

    // 去混淆评级： 中等(50)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDelegateDrawable == null ? super.isStateful() || this.mVectorState != null && (this.mVectorState.isStateful() || this.mVectorState.mTint != null && this.mVectorState.mTint.isStateful()) : this.mDelegateDrawable.isStateful();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
            return this;
        }
        if(!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }
        return this;
    }

    private boolean needMirroring() {
        return this.isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    protected void onBoundsChange(Rect rect0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(rect0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        boolean z;
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(arr_v);
        }
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        if(vectorDrawableCompat$VectorDrawableCompatState0.mTint == null || vectorDrawableCompat$VectorDrawableCompatState0.mTintMode == null) {
            z = false;
        }
        else {
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, vectorDrawableCompat$VectorDrawableCompatState0.mTint, vectorDrawableCompat$VectorDrawableCompatState0.mTintMode);
            this.invalidateSelf();
            z = true;
        }
        if(vectorDrawableCompat$VectorDrawableCompatState0.isStateful() && vectorDrawableCompat$VectorDrawableCompatState0.onStateChanged(arr_v)) {
            this.invalidateSelf();
            return true;
        }
        return z;
    }

    private static PorterDuff.Mode parseTintModeCompat(int v, PorterDuff.Mode porterDuff$Mode0) {
        switch(v) {
            case 3: {
                return PorterDuff.Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff.Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff.Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff.Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff.Mode.SCREEN;
            }
            case 16: {
                return PorterDuff.Mode.ADD;
            }
            default: {
                return porterDuff$Mode0;
            }
        }
    }

    private void printGroupTree(VGroup vectorDrawableCompat$VGroup0, int v) {
        String s = "";
        for(int v2 = 0; v2 < v; ++v2) {
            s = s + "    ";
        }
        Log.v("VectorDrawableCompat", s + "current group is :" + vectorDrawableCompat$VGroup0.getGroupName() + " rotation is " + vectorDrawableCompat$VGroup0.mRotate);
        Log.v("VectorDrawableCompat", s + "matrix is :" + vectorDrawableCompat$VGroup0.getLocalMatrix().toString());
        for(int v1 = 0; v1 < vectorDrawableCompat$VGroup0.mChildren.size(); ++v1) {
            VObject vectorDrawableCompat$VObject0 = (VObject)vectorDrawableCompat$VGroup0.mChildren.get(v1);
            if(vectorDrawableCompat$VObject0 instanceof VGroup) {
                this.printGroupTree(((VGroup)vectorDrawableCompat$VObject0), v + 1);
            }
            else {
                ((VPath)vectorDrawableCompat$VObject0).printVPath(v + 1);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable0, long v) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.scheduleSelf(runnable0, v);
            return;
        }
        super.scheduleSelf(runnable0, v);
    }

    void setAllowCaching(boolean z) {
        this.mAllowCaching = z;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(v);
            return;
        }
        if(this.mVectorState.mVPathRenderer.getRootAlpha() != v) {
            this.mVectorState.mVPathRenderer.setRootAlpha(v);
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, z);
            return;
        }
        this.mVectorState.mAutoMirrored = z;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setChangingConfigurations(int v) {
        super.setChangingConfigurations(v);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setColorFilter(int v, PorterDuff.Mode porterDuff$Mode0) {
        super.setColorFilter(v, porterDuff$Mode0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter0);
            return;
        }
        this.mColorFilter = colorFilter0;
        this.invalidateSelf();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setHotspot(float f, float f1) {
        super.setHotspot(f, f1);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        super.setHotspotBounds(v, v1, v2, v3);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public boolean setState(int[] arr_v) {
        return super.setState(arr_v);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, v);
            return;
        }
        this.setTintList(ColorStateList.valueOf(v));
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, colorStateList0);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        if(vectorDrawableCompat$VectorDrawableCompatState0.mTint != colorStateList0) {
            vectorDrawableCompat$VectorDrawableCompatState0.mTint = colorStateList0;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, colorStateList0, vectorDrawableCompat$VectorDrawableCompatState0.mTintMode);
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, porterDuff$Mode0);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        if(vectorDrawableCompat$VectorDrawableCompatState0.mTintMode != porterDuff$Mode0) {
            vectorDrawableCompat$VectorDrawableCompatState0.mTintMode = porterDuff$Mode0;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, vectorDrawableCompat$VectorDrawableCompatState0.mTint, porterDuff$Mode0);
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        return this.mDelegateDrawable == null ? super.setVisible(z, z1) : this.mDelegateDrawable.setVisible(z, z1);
    }

    @Override  // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.unscheduleSelf(runnable0);
            return;
        }
        super.unscheduleSelf(runnable0);
    }

    private void updateStateFromTypedArray(TypedArray typedArray0, XmlPullParser xmlPullParser0, Resources.Theme resources$Theme0) throws XmlPullParserException {
        VectorDrawableCompatState vectorDrawableCompat$VectorDrawableCompatState0 = this.mVectorState;
        VPathRenderer vectorDrawableCompat$VPathRenderer0 = vectorDrawableCompat$VectorDrawableCompatState0.mVPathRenderer;
        vectorDrawableCompat$VectorDrawableCompatState0.mTintMode = VectorDrawableCompat.parseTintModeCompat(TypedArrayUtils.getNamedInt(typedArray0, xmlPullParser0, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateList0 = TypedArrayUtils.getNamedColorStateList(typedArray0, xmlPullParser0, resources$Theme0, "tint", 1);
        if(colorStateList0 != null) {
            vectorDrawableCompat$VectorDrawableCompatState0.mTint = colorStateList0;
        }
        vectorDrawableCompat$VectorDrawableCompatState0.mAutoMirrored = TypedArrayUtils.getNamedBoolean(typedArray0, xmlPullParser0, "autoMirrored", 5, vectorDrawableCompat$VectorDrawableCompatState0.mAutoMirrored);
        vectorDrawableCompat$VPathRenderer0.mViewportWidth = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "viewportWidth", 7, vectorDrawableCompat$VPathRenderer0.mViewportWidth);
        vectorDrawableCompat$VPathRenderer0.mViewportHeight = TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "viewportHeight", 8, vectorDrawableCompat$VPathRenderer0.mViewportHeight);
        if(vectorDrawableCompat$VPathRenderer0.mViewportWidth <= 0.0f) {
            throw new XmlPullParserException(typedArray0.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if(vectorDrawableCompat$VPathRenderer0.mViewportHeight <= 0.0f) {
            throw new XmlPullParserException(typedArray0.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        vectorDrawableCompat$VPathRenderer0.mBaseWidth = typedArray0.getDimension(3, vectorDrawableCompat$VPathRenderer0.mBaseWidth);
        vectorDrawableCompat$VPathRenderer0.mBaseHeight = typedArray0.getDimension(2, vectorDrawableCompat$VPathRenderer0.mBaseHeight);
        if(vectorDrawableCompat$VPathRenderer0.mBaseWidth <= 0.0f) {
            throw new XmlPullParserException(typedArray0.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if(vectorDrawableCompat$VPathRenderer0.mBaseHeight <= 0.0f) {
            throw new XmlPullParserException(typedArray0.getPositionDescription() + "<vector> tag requires height > 0");
        }
        vectorDrawableCompat$VPathRenderer0.setAlpha(TypedArrayUtils.getNamedFloat(typedArray0, xmlPullParser0, "alpha", 4, vectorDrawableCompat$VPathRenderer0.getAlpha()));
        String s = typedArray0.getString(0);
        if(s != null) {
            vectorDrawableCompat$VPathRenderer0.mRootName = s;
            vectorDrawableCompat$VPathRenderer0.mVGTargetsMap.put(s, vectorDrawableCompat$VPathRenderer0);
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        return colorStateList0 == null || porterDuff$Mode0 == null ? null : new PorterDuffColorFilter(colorStateList0.getColorForState(this.getState(), 0), porterDuff$Mode0);
    }

    class androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.1 {
    }

}

