package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableContainerCompat extends Drawable implements Drawable.Callback {
    static class Api21Impl {
        public static boolean canApplyTheme(Drawable.ConstantState drawable$ConstantState0) {
            return drawable$ConstantState0.canApplyTheme();
        }

        public static void getOutline(Drawable drawable0, Outline outline0) {
            drawable0.getOutline(outline0);
        }

        public static Resources getResources(Resources.Theme resources$Theme0) {
            return resources$Theme0.getResources();
        }
    }

    static class BlockInvalidateCallback implements Drawable.Callback {
        private Drawable.Callback mCallback;

        @Override  // android.graphics.drawable.Drawable$Callback
        public void invalidateDrawable(Drawable drawable0) {
        }

        @Override  // android.graphics.drawable.Drawable$Callback
        public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
            Drawable.Callback drawable$Callback0 = this.mCallback;
            if(drawable$Callback0 != null) {
                drawable$Callback0.scheduleDrawable(drawable0, runnable0, v);
            }
        }

        @Override  // android.graphics.drawable.Drawable$Callback
        public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
            Drawable.Callback drawable$Callback0 = this.mCallback;
            if(drawable$Callback0 != null) {
                drawable$Callback0.unscheduleDrawable(drawable0, runnable0);
            }
        }

        public Drawable.Callback unwrap() {
            Drawable.Callback drawable$Callback0 = this.mCallback;
            this.mCallback = null;
            return drawable$Callback0;
        }

        public BlockInvalidateCallback wrap(Drawable.Callback drawable$Callback0) {
            this.mCallback = drawable$Callback0;
            return this;
        }
    }

    static abstract class DrawableContainerState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCanConstantState;
        int mChangingConfigurations;
        boolean mCheckedConstantSize;
        boolean mCheckedConstantState;
        boolean mCheckedOpacity;
        boolean mCheckedPadding;
        boolean mCheckedStateful;
        int mChildrenChangingConfigurations;
        ColorFilter mColorFilter;
        int mConstantHeight;
        int mConstantMinimumHeight;
        int mConstantMinimumWidth;
        Rect mConstantPadding;
        boolean mConstantSize;
        int mConstantWidth;
        int mDensity;
        boolean mDither;
        SparseArray mDrawableFutures;
        Drawable[] mDrawables;
        int mEnterFadeDuration;
        int mExitFadeDuration;
        boolean mHasColorFilter;
        boolean mHasTintList;
        boolean mHasTintMode;
        int mLayoutDirection;
        boolean mMutated;
        int mNumChildren;
        int mOpacity;
        final DrawableContainerCompat mOwner;
        Resources mSourceRes;
        boolean mStateful;
        ColorStateList mTintList;
        PorterDuff.Mode mTintMode;
        boolean mVariablePadding;

        DrawableContainerState(DrawableContainerState drawableContainerCompat$DrawableContainerState0, DrawableContainerCompat drawableContainerCompat0, Resources resources0) {
            Resources resources1;
            this.mVariablePadding = false;
            this.mConstantSize = false;
            this.mDither = true;
            this.mEnterFadeDuration = 0;
            this.mExitFadeDuration = 0;
            this.mOwner = drawableContainerCompat0;
            Rect rect0 = null;
            if(resources0 == null) {
                resources1 = drawableContainerCompat$DrawableContainerState0 == null ? null : drawableContainerCompat$DrawableContainerState0.mSourceRes;
            }
            else {
                resources1 = resources0;
            }
            this.mSourceRes = resources1;
            int v1 = DrawableContainerCompat.resolveDensity(resources0, (drawableContainerCompat$DrawableContainerState0 == null ? 0 : drawableContainerCompat$DrawableContainerState0.mDensity));
            this.mDensity = v1;
            if(drawableContainerCompat$DrawableContainerState0 != null) {
                this.mChangingConfigurations = drawableContainerCompat$DrawableContainerState0.mChangingConfigurations;
                this.mChildrenChangingConfigurations = drawableContainerCompat$DrawableContainerState0.mChildrenChangingConfigurations;
                this.mCheckedConstantState = true;
                this.mCanConstantState = true;
                this.mVariablePadding = drawableContainerCompat$DrawableContainerState0.mVariablePadding;
                this.mConstantSize = drawableContainerCompat$DrawableContainerState0.mConstantSize;
                this.mDither = drawableContainerCompat$DrawableContainerState0.mDither;
                this.mMutated = drawableContainerCompat$DrawableContainerState0.mMutated;
                this.mLayoutDirection = drawableContainerCompat$DrawableContainerState0.mLayoutDirection;
                this.mEnterFadeDuration = drawableContainerCompat$DrawableContainerState0.mEnterFadeDuration;
                this.mExitFadeDuration = drawableContainerCompat$DrawableContainerState0.mExitFadeDuration;
                this.mAutoMirrored = drawableContainerCompat$DrawableContainerState0.mAutoMirrored;
                this.mColorFilter = drawableContainerCompat$DrawableContainerState0.mColorFilter;
                this.mHasColorFilter = drawableContainerCompat$DrawableContainerState0.mHasColorFilter;
                this.mTintList = drawableContainerCompat$DrawableContainerState0.mTintList;
                this.mTintMode = drawableContainerCompat$DrawableContainerState0.mTintMode;
                this.mHasTintList = drawableContainerCompat$DrawableContainerState0.mHasTintList;
                this.mHasTintMode = drawableContainerCompat$DrawableContainerState0.mHasTintMode;
                if(drawableContainerCompat$DrawableContainerState0.mDensity == v1) {
                    if(drawableContainerCompat$DrawableContainerState0.mCheckedPadding) {
                        if(drawableContainerCompat$DrawableContainerState0.mConstantPadding != null) {
                            rect0 = new Rect(drawableContainerCompat$DrawableContainerState0.mConstantPadding);
                        }
                        this.mConstantPadding = rect0;
                        this.mCheckedPadding = true;
                    }
                    if(drawableContainerCompat$DrawableContainerState0.mCheckedConstantSize) {
                        this.mConstantWidth = drawableContainerCompat$DrawableContainerState0.mConstantWidth;
                        this.mConstantHeight = drawableContainerCompat$DrawableContainerState0.mConstantHeight;
                        this.mConstantMinimumWidth = drawableContainerCompat$DrawableContainerState0.mConstantMinimumWidth;
                        this.mConstantMinimumHeight = drawableContainerCompat$DrawableContainerState0.mConstantMinimumHeight;
                        this.mCheckedConstantSize = true;
                    }
                }
                if(drawableContainerCompat$DrawableContainerState0.mCheckedOpacity) {
                    this.mOpacity = drawableContainerCompat$DrawableContainerState0.mOpacity;
                    this.mCheckedOpacity = true;
                }
                if(drawableContainerCompat$DrawableContainerState0.mCheckedStateful) {
                    this.mStateful = drawableContainerCompat$DrawableContainerState0.mStateful;
                    this.mCheckedStateful = true;
                }
                Drawable[] arr_drawable = drawableContainerCompat$DrawableContainerState0.mDrawables;
                this.mDrawables = new Drawable[arr_drawable.length];
                this.mNumChildren = drawableContainerCompat$DrawableContainerState0.mNumChildren;
                SparseArray sparseArray0 = drawableContainerCompat$DrawableContainerState0.mDrawableFutures;
                this.mDrawableFutures = sparseArray0 == null ? new SparseArray(this.mNumChildren) : sparseArray0.clone();
                int v2 = this.mNumChildren;
                for(int v = 0; v < v2; ++v) {
                    Drawable drawable0 = arr_drawable[v];
                    if(drawable0 != null) {
                        Drawable.ConstantState drawable$ConstantState0 = drawable0.getConstantState();
                        if(drawable$ConstantState0 == null) {
                            this.mDrawables[v] = arr_drawable[v];
                        }
                        else {
                            this.mDrawableFutures.put(v, drawable$ConstantState0);
                        }
                    }
                }
                return;
            }
            this.mDrawables = new Drawable[10];
            this.mNumChildren = 0;
        }

        public final int addChild(Drawable drawable0) {
            int v = this.mNumChildren;
            if(v >= this.mDrawables.length) {
                this.growArray(v, v + 10);
            }
            drawable0.mutate();
            drawable0.setVisible(false, true);
            drawable0.setCallback(this.mOwner);
            this.mDrawables[v] = drawable0;
            ++this.mNumChildren;
            int v1 = this.mChildrenChangingConfigurations;
            this.mChildrenChangingConfigurations = drawable0.getChangingConfigurations() | v1;
            this.invalidateCache();
            this.mConstantPadding = null;
            this.mCheckedPadding = false;
            this.mCheckedConstantSize = false;
            this.mCheckedConstantState = false;
            return v;
        }

        final void applyTheme(Resources.Theme resources$Theme0) {
            if(resources$Theme0 != null) {
                this.createAllFutures();
                int v = this.mNumChildren;
                Drawable[] arr_drawable = this.mDrawables;
                for(int v1 = 0; v1 < v; ++v1) {
                    Drawable drawable0 = arr_drawable[v1];
                    if(drawable0 != null && DrawableCompat.canApplyTheme(drawable0)) {
                        DrawableCompat.applyTheme(arr_drawable[v1], resources$Theme0);
                        this.mChildrenChangingConfigurations |= arr_drawable[v1].getChangingConfigurations();
                    }
                }
                this.updateDensity(Api21Impl.getResources(resources$Theme0));
            }
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public boolean canApplyTheme() {
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            for(int v1 = 0; v1 < v; ++v1) {
                Drawable drawable0 = arr_drawable[v1];
                if(drawable0 == null) {
                    Drawable.ConstantState drawable$ConstantState0 = (Drawable.ConstantState)this.mDrawableFutures.get(v1);
                    if(drawable$ConstantState0 != null && Api21Impl.canApplyTheme(drawable$ConstantState0)) {
                        return true;
                    }
                }
                else if(DrawableCompat.canApplyTheme(drawable0)) {
                    return true;
                }
            }
            return false;
        }

        public boolean canConstantState() {
            if(this.mCheckedConstantState) {
                return this.mCanConstantState;
            }
            this.createAllFutures();
            this.mCheckedConstantState = true;
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            for(int v1 = 0; v1 < v; ++v1) {
                if(arr_drawable[v1].getConstantState() == null) {
                    this.mCanConstantState = false;
                    return false;
                }
            }
            this.mCanConstantState = true;
            return true;
        }

        final void clearMutated() {
            this.mMutated = false;
        }

        protected void computeConstantSize() {
            this.mCheckedConstantSize = true;
            this.createAllFutures();
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            this.mConstantHeight = -1;
            this.mConstantWidth = -1;
            this.mConstantMinimumHeight = 0;
            this.mConstantMinimumWidth = 0;
            for(int v1 = 0; v1 < v; ++v1) {
                Drawable drawable0 = arr_drawable[v1];
                int v2 = drawable0.getIntrinsicWidth();
                if(v2 > this.mConstantWidth) {
                    this.mConstantWidth = v2;
                }
                int v3 = drawable0.getIntrinsicHeight();
                if(v3 > this.mConstantHeight) {
                    this.mConstantHeight = v3;
                }
                int v4 = drawable0.getMinimumWidth();
                if(v4 > this.mConstantMinimumWidth) {
                    this.mConstantMinimumWidth = v4;
                }
                int v5 = drawable0.getMinimumHeight();
                if(v5 > this.mConstantMinimumHeight) {
                    this.mConstantMinimumHeight = v5;
                }
            }
        }

        private void createAllFutures() {
            SparseArray sparseArray0 = this.mDrawableFutures;
            if(sparseArray0 != null) {
                int v = sparseArray0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    int v2 = this.mDrawableFutures.keyAt(v1);
                    Drawable.ConstantState drawable$ConstantState0 = (Drawable.ConstantState)this.mDrawableFutures.valueAt(v1);
                    Drawable[] arr_drawable = this.mDrawables;
                    arr_drawable[v2] = this.prepareDrawable(drawable$ConstantState0.newDrawable(this.mSourceRes));
                }
                this.mDrawableFutures = null;
            }
        }

        final int getCapacity() {
            return this.mDrawables.length;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
        }

        public final Drawable getChild(int v) {
            Drawable drawable0 = this.mDrawables[v];
            if(drawable0 != null) {
                return drawable0;
            }
            SparseArray sparseArray0 = this.mDrawableFutures;
            if(sparseArray0 != null) {
                int v1 = sparseArray0.indexOfKey(v);
                if(v1 >= 0) {
                    Drawable drawable1 = this.prepareDrawable(((Drawable.ConstantState)this.mDrawableFutures.valueAt(v1)).newDrawable(this.mSourceRes));
                    this.mDrawables[v] = drawable1;
                    this.mDrawableFutures.removeAt(v1);
                    if(this.mDrawableFutures.size() == 0) {
                        this.mDrawableFutures = null;
                    }
                    return drawable1;
                }
            }
            return null;
        }

        public final int getChildCount() {
            return this.mNumChildren;
        }

        public final int getConstantHeight() {
            if(!this.mCheckedConstantSize) {
                this.computeConstantSize();
            }
            return this.mConstantHeight;
        }

        public final int getConstantMinimumHeight() {
            if(!this.mCheckedConstantSize) {
                this.computeConstantSize();
            }
            return this.mConstantMinimumHeight;
        }

        public final int getConstantMinimumWidth() {
            if(!this.mCheckedConstantSize) {
                this.computeConstantSize();
            }
            return this.mConstantMinimumWidth;
        }

        public final Rect getConstantPadding() {
            Rect rect0 = null;
            if(this.mVariablePadding) {
                return null;
            }
            Rect rect1 = this.mConstantPadding;
            if(rect1 == null && !this.mCheckedPadding) {
                this.createAllFutures();
                Rect rect2 = new Rect();
                int v = this.mNumChildren;
                Drawable[] arr_drawable = this.mDrawables;
                for(int v1 = 0; v1 < v; ++v1) {
                    if(arr_drawable[v1].getPadding(rect2)) {
                        if(rect0 == null) {
                            rect0 = new Rect(0, 0, 0, 0);
                        }
                        if(rect2.left > rect0.left) {
                            rect0.left = rect2.left;
                        }
                        if(rect2.top > rect0.top) {
                            rect0.top = rect2.top;
                        }
                        if(rect2.right > rect0.right) {
                            rect0.right = rect2.right;
                        }
                        if(rect2.bottom > rect0.bottom) {
                            rect0.bottom = rect2.bottom;
                        }
                    }
                }
                this.mCheckedPadding = true;
                this.mConstantPadding = rect0;
                return rect0;
            }
            return rect1;
        }

        public final int getConstantWidth() {
            if(!this.mCheckedConstantSize) {
                this.computeConstantSize();
            }
            return this.mConstantWidth;
        }

        public final int getEnterFadeDuration() {
            return this.mEnterFadeDuration;
        }

        public final int getExitFadeDuration() {
            return this.mExitFadeDuration;
        }

        public final int getOpacity() {
            if(this.mCheckedOpacity) {
                return this.mOpacity;
            }
            this.createAllFutures();
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            int v1 = v <= 0 ? -2 : arr_drawable[0].getOpacity();
            for(int v2 = 1; v2 < v; ++v2) {
                v1 = Drawable.resolveOpacity(v1, arr_drawable[v2].getOpacity());
            }
            this.mOpacity = v1;
            this.mCheckedOpacity = true;
            return v1;
        }

        public void growArray(int v, int v1) {
            Drawable[] arr_drawable = new Drawable[v1];
            Drawable[] arr_drawable1 = this.mDrawables;
            if(arr_drawable1 != null) {
                System.arraycopy(arr_drawable1, 0, arr_drawable, 0, v);
            }
            this.mDrawables = arr_drawable;
        }

        void invalidateCache() {
            this.mCheckedOpacity = false;
            this.mCheckedStateful = false;
        }

        public final boolean isConstantSize() {
            return this.mConstantSize;
        }

        public final boolean isStateful() {
            if(this.mCheckedStateful) {
                return this.mStateful;
            }
            this.createAllFutures();
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            boolean z = false;
            for(int v1 = 0; v1 < v; ++v1) {
                if(arr_drawable[v1].isStateful()) {
                    z = true;
                    break;
                }
            }
            this.mStateful = z;
            this.mCheckedStateful = true;
            return z;
        }

        void mutate() {
            int v = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            for(int v1 = 0; v1 < v; ++v1) {
                Drawable drawable0 = arr_drawable[v1];
                if(drawable0 != null) {
                    drawable0.mutate();
                }
            }
            this.mMutated = true;
        }

        private Drawable prepareDrawable(Drawable drawable0) {
            if(Build.VERSION.SDK_INT >= 23) {
                DrawableCompat.setLayoutDirection(drawable0, this.mLayoutDirection);
            }
            Drawable drawable1 = drawable0.mutate();
            drawable1.setCallback(this.mOwner);
            return drawable1;
        }

        public final void setConstantSize(boolean z) {
            this.mConstantSize = z;
        }

        public final void setEnterFadeDuration(int v) {
            this.mEnterFadeDuration = v;
        }

        public final void setExitFadeDuration(int v) {
            this.mExitFadeDuration = v;
        }

        final boolean setLayoutDirection(int v, int v1) {
            int v2 = this.mNumChildren;
            Drawable[] arr_drawable = this.mDrawables;
            boolean z = false;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(arr_drawable[v3] != null) {
                    boolean z1 = Build.VERSION.SDK_INT < 23 ? false : DrawableCompat.setLayoutDirection(arr_drawable[v3], v);
                    if(v3 == v1) {
                        z = z1;
                    }
                }
            }
            this.mLayoutDirection = v;
            return z;
        }

        public final void setVariablePadding(boolean z) {
            this.mVariablePadding = z;
        }

        final void updateDensity(Resources resources0) {
            if(resources0 != null) {
                this.mSourceRes = resources0;
                int v = DrawableContainerCompat.resolveDensity(resources0, this.mDensity);
                int v1 = this.mDensity;
                this.mDensity = v;
                if(v1 != v) {
                    this.mCheckedConstantSize = false;
                    this.mCheckedPadding = false;
                }
            }
        }
    }

    private static final boolean DEBUG = false;
    private static final boolean DEFAULT_DITHER = true;
    private static final String TAG = "DrawableContainerCompat";
    private int mAlpha;
    private Runnable mAnimationRunnable;
    private BlockInvalidateCallback mBlockInvalidateCallback;
    private int mCurIndex;
    private Drawable mCurrDrawable;
    private DrawableContainerState mDrawableContainerState;
    private long mEnterAnimationEnd;
    private long mExitAnimationEnd;
    private boolean mHasAlpha;
    private Rect mHotspotBounds;
    private Drawable mLastDrawable;
    private boolean mMutated;

    public DrawableContainerCompat() {
        this.mAlpha = 0xFF;
        this.mCurIndex = -1;
    }

    void animate(boolean z) {
        int v3;
        int v = 1;
        this.mHasAlpha = true;
        long v1 = SystemClock.uptimeMillis();
        Drawable drawable0 = this.mCurrDrawable;
        if(drawable0 == null) {
            this.mEnterAnimationEnd = 0L;
            v3 = 0;
        }
        else {
            long v2 = this.mEnterAnimationEnd;
            if(v2 == 0L) {
                v3 = 0;
            }
            else if(v2 <= v1) {
                drawable0.setAlpha(this.mAlpha);
                this.mEnterAnimationEnd = 0L;
                v3 = 0;
            }
            else {
                this.mCurrDrawable.setAlpha((0xFF - ((int)((v2 - v1) * 0xFFL)) / this.mDrawableContainerState.mEnterFadeDuration) * this.mAlpha / 0xFF);
                v3 = 1;
            }
        }
        Drawable drawable1 = this.mLastDrawable;
        if(drawable1 == null) {
            this.mExitAnimationEnd = 0L;
            v = v3;
        }
        else {
            long v4 = this.mExitAnimationEnd;
            if(v4 == 0L) {
                v = v3;
            }
            else if(v4 <= v1) {
                drawable1.setVisible(false, false);
                this.mLastDrawable = null;
                this.mExitAnimationEnd = 0L;
                v = v3;
            }
            else {
                this.mLastDrawable.setAlpha(((int)((v4 - v1) * 0xFFL)) / this.mDrawableContainerState.mExitFadeDuration * this.mAlpha / 0xFF);
            }
        }
        if(z && v != 0) {
            this.scheduleSelf(this.mAnimationRunnable, v1 + 16L);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme resources$Theme0) {
        this.mDrawableContainerState.applyTheme(resources$Theme0);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.mDrawableContainerState.canApplyTheme();
    }

    void clearMutated() {
        this.mDrawableContainerState.clearMutated();
        this.mMutated = false;
    }

    DrawableContainerState cloneConstantState() {
        return this.mDrawableContainerState;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        Drawable drawable0 = this.mCurrDrawable;
        if(drawable0 != null) {
            drawable0.draw(canvas0);
        }
        Drawable drawable1 = this.mLastDrawable;
        if(drawable1 != null) {
            drawable1.draw(canvas0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if(this.mDrawableContainerState.canConstantState()) {
            DrawableContainerState drawableContainerCompat$DrawableContainerState0 = this.mDrawableContainerState;
            drawableContainerCompat$DrawableContainerState0.mChangingConfigurations = this.getChangingConfigurations();
            return this.mDrawableContainerState;
        }
        return null;
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mCurrDrawable;
    }

    int getCurrentIndex() {
        return this.mCurIndex;
    }

    @Override  // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect0) {
        Rect rect1 = this.mHotspotBounds;
        if(rect1 != null) {
            rect0.set(rect1);
            return;
        }
        super.getHotspotBounds(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if(this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantHeight();
        }
        return this.mCurrDrawable == null ? -1 : this.mCurrDrawable.getIntrinsicHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if(this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantWidth();
        }
        return this.mCurrDrawable == null ? -1 : this.mCurrDrawable.getIntrinsicWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if(this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumHeight();
        }
        return this.mCurrDrawable == null ? 0 : this.mCurrDrawable.getMinimumHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if(this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumWidth();
        }
        return this.mCurrDrawable == null ? 0 : this.mCurrDrawable.getMinimumWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mCurrDrawable == null || !this.mCurrDrawable.isVisible() ? -2 : this.mDrawableContainerState.getOpacity();
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        Drawable drawable0 = this.mCurrDrawable;
        if(drawable0 != null) {
            Api21Impl.getOutline(drawable0, outline0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect0) {
        boolean z;
        Rect rect1 = this.mDrawableContainerState.getConstantPadding();
        if(rect1 == null) {
            Drawable drawable0 = this.mCurrDrawable;
            z = drawable0 == null ? super.getPadding(rect0) : drawable0.getPadding(rect0);
        }
        else {
            rect0.set(rect1);
            z = (rect1.right | (rect1.left | rect1.top | rect1.bottom)) == 0 ? false : true;
        }
        if(this.needsMirroring()) {
            int v = rect0.left;
            rect0.left = rect0.right;
            rect0.right = v;
        }
        return z;
    }

    private void initializeDrawableForDisplay(Drawable drawable0) {
        if(this.mBlockInvalidateCallback == null) {
            this.mBlockInvalidateCallback = new BlockInvalidateCallback();
        }
        drawable0.setCallback(this.mBlockInvalidateCallback.wrap(drawable0.getCallback()));
        try {
            if(this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha) {
                drawable0.setAlpha(this.mAlpha);
            }
            if(this.mDrawableContainerState.mHasColorFilter) {
                drawable0.setColorFilter(this.mDrawableContainerState.mColorFilter);
            }
            else {
                if(this.mDrawableContainerState.mHasTintList) {
                    DrawableCompat.setTintList(drawable0, this.mDrawableContainerState.mTintList);
                }
                if(this.mDrawableContainerState.mHasTintMode) {
                    DrawableCompat.setTintMode(drawable0, this.mDrawableContainerState.mTintMode);
                }
            }
            drawable0.setVisible(this.isVisible(), true);
            drawable0.setDither(this.mDrawableContainerState.mDither);
            drawable0.setState(this.getState());
            drawable0.setLevel(this.getLevel());
            drawable0.setBounds(this.getBounds());
            if(Build.VERSION.SDK_INT >= 23) {
                DrawableCompat.setLayoutDirection(drawable0, DrawableCompat.getLayoutDirection(this));
            }
            DrawableCompat.setAutoMirrored(drawable0, this.mDrawableContainerState.mAutoMirrored);
            Rect rect0 = this.mHotspotBounds;
            if(rect0 != null) {
                DrawableCompat.setHotspotBounds(drawable0, rect0.left, rect0.top, rect0.right, rect0.bottom);
            }
        }
        finally {
            drawable0.setCallback(this.mBlockInvalidateCallback.unwrap());
        }
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(Drawable drawable0) {
        DrawableContainerState drawableContainerCompat$DrawableContainerState0 = this.mDrawableContainerState;
        if(drawableContainerCompat$DrawableContainerState0 != null) {
            drawableContainerCompat$DrawableContainerState0.invalidateCache();
        }
        if(drawable0 == this.mCurrDrawable && this.getCallback() != null) {
            this.getCallback().invalidateDrawable(this);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.mDrawableContainerState.mAutoMirrored;
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDrawableContainerState.isStateful();
    }

    @Override  // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        int v1;
        Drawable drawable0 = this.mLastDrawable;
        int v = 1;
        if(drawable0 == null) {
            v1 = 0;
        }
        else {
            drawable0.jumpToCurrentState();
            this.mLastDrawable = null;
            v1 = 1;
        }
        Drawable drawable1 = this.mCurrDrawable;
        if(drawable1 != null) {
            drawable1.jumpToCurrentState();
            if(this.mHasAlpha) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
            }
        }
        if(this.mExitAnimationEnd != 0L) {
            this.mExitAnimationEnd = 0L;
            v1 = 1;
        }
        if(this.mEnterAnimationEnd == 0L) {
            v = v1;
        }
        else {
            this.mEnterAnimationEnd = 0L;
        }
        if(v != 0) {
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if(!this.mMutated && super.mutate() == this) {
            DrawableContainerState drawableContainerCompat$DrawableContainerState0 = this.cloneConstantState();
            drawableContainerCompat$DrawableContainerState0.mutate();
            this.setConstantState(drawableContainerCompat$DrawableContainerState0);
            this.mMutated = true;
        }
        return this;
    }

    private boolean needsMirroring() {
        return this.isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        Drawable drawable0 = this.mLastDrawable;
        if(drawable0 != null) {
            drawable0.setBounds(rect0);
        }
        Drawable drawable1 = this.mCurrDrawable;
        if(drawable1 != null) {
            drawable1.setBounds(rect0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int v) {
        return this.mDrawableContainerState.setLayoutDirection(v, this.getCurrentIndex());
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int v) {
        Drawable drawable0 = this.mLastDrawable;
        if(drawable0 != null) {
            return drawable0.setLevel(v);
        }
        return this.mCurrDrawable == null ? false : this.mCurrDrawable.setLevel(v);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        Drawable drawable0 = this.mLastDrawable;
        if(drawable0 != null) {
            return drawable0.setState(arr_v);
        }
        return this.mCurrDrawable == null ? false : this.mCurrDrawable.setState(arr_v);
    }

    static int resolveDensity(Resources resources0, int v) {
        if(resources0 != null) {
            v = resources0.getDisplayMetrics().densityDpi;
        }
        return v == 0 ? 0xA0 : v;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
        if(drawable0 == this.mCurrDrawable && this.getCallback() != null) {
            this.getCallback().scheduleDrawable(this, runnable0, v);
        }
    }

    boolean selectDrawable(int v) {
        if(v == this.mCurIndex) {
            return false;
        }
        long v1 = SystemClock.uptimeMillis();
        if(this.mDrawableContainerState.mExitFadeDuration > 0) {
            Drawable drawable0 = this.mLastDrawable;
            if(drawable0 != null) {
                drawable0.setVisible(false, false);
            }
            Drawable drawable1 = this.mCurrDrawable;
            if(drawable1 == null) {
                this.mLastDrawable = null;
                this.mExitAnimationEnd = 0L;
            }
            else {
                this.mLastDrawable = drawable1;
                this.mExitAnimationEnd = ((long)this.mDrawableContainerState.mExitFadeDuration) + v1;
            }
        }
        else {
            Drawable drawable2 = this.mCurrDrawable;
            if(drawable2 != null) {
                drawable2.setVisible(false, false);
            }
        }
        if(v < 0 || v >= this.mDrawableContainerState.mNumChildren) {
            this.mCurrDrawable = null;
            this.mCurIndex = -1;
        }
        else {
            Drawable drawable3 = this.mDrawableContainerState.getChild(v);
            this.mCurrDrawable = drawable3;
            this.mCurIndex = v;
            if(drawable3 != null) {
                if(this.mDrawableContainerState.mEnterFadeDuration > 0) {
                    this.mEnterAnimationEnd = v1 + ((long)this.mDrawableContainerState.mEnterFadeDuration);
                }
                this.initializeDrawableForDisplay(drawable3);
            }
        }
        if(this.mEnterAnimationEnd != 0L || this.mExitAnimationEnd != 0L) {
            Runnable runnable0 = this.mAnimationRunnable;
            if(runnable0 == null) {
                this.mAnimationRunnable = new Runnable() {
                    @Override
                    public void run() {
                        DrawableContainerCompat.this.animate(true);
                        DrawableContainerCompat.this.invalidateSelf();
                    }
                };
            }
            else {
                this.unscheduleSelf(runnable0);
            }
            this.animate(true);
        }
        this.invalidateSelf();
        return true;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(!this.mHasAlpha || this.mAlpha != v) {
            this.mHasAlpha = true;
            this.mAlpha = v;
            Drawable drawable0 = this.mCurrDrawable;
            if(drawable0 != null) {
                if(this.mEnterAnimationEnd == 0L) {
                    drawable0.setAlpha(v);
                    return;
                }
                this.animate(false);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if(this.mDrawableContainerState.mAutoMirrored != z) {
            this.mDrawableContainerState.mAutoMirrored = z;
            Drawable drawable0 = this.mCurrDrawable;
            if(drawable0 != null) {
                DrawableCompat.setAutoMirrored(drawable0, this.mDrawableContainerState.mAutoMirrored);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mDrawableContainerState.mHasColorFilter = true;
        if(this.mDrawableContainerState.mColorFilter != colorFilter0) {
            this.mDrawableContainerState.mColorFilter = colorFilter0;
            Drawable drawable0 = this.mCurrDrawable;
            if(drawable0 != null) {
                drawable0.setColorFilter(colorFilter0);
            }
        }
    }

    void setConstantState(DrawableContainerState drawableContainerCompat$DrawableContainerState0) {
        this.mDrawableContainerState = drawableContainerCompat$DrawableContainerState0;
        int v = this.mCurIndex;
        if(v >= 0) {
            Drawable drawable0 = drawableContainerCompat$DrawableContainerState0.getChild(v);
            this.mCurrDrawable = drawable0;
            if(drawable0 != null) {
                this.initializeDrawableForDisplay(drawable0);
            }
        }
        this.mLastDrawable = null;
    }

    void setCurrentIndex(int v) {
        this.selectDrawable(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        if(this.mDrawableContainerState.mDither != z) {
            this.mDrawableContainerState.mDither = z;
            Drawable drawable0 = this.mCurrDrawable;
            if(drawable0 != null) {
                drawable0.setDither(this.mDrawableContainerState.mDither);
            }
        }
    }

    public void setEnterFadeDuration(int v) {
        this.mDrawableContainerState.mEnterFadeDuration = v;
    }

    public void setExitFadeDuration(int v) {
        this.mDrawableContainerState.mExitFadeDuration = v;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f1) {
        Drawable drawable0 = this.mCurrDrawable;
        if(drawable0 != null) {
            DrawableCompat.setHotspot(drawable0, f, f1);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        Rect rect0 = this.mHotspotBounds;
        if(rect0 == null) {
            this.mHotspotBounds = new Rect(v, v1, v2, v3);
        }
        else {
            rect0.set(v, v1, v2, v3);
        }
        Drawable drawable0 = this.mCurrDrawable;
        if(drawable0 != null) {
            DrawableCompat.setHotspotBounds(drawable0, v, v1, v2, v3);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTint(int v) {
        this.setTintList(ColorStateList.valueOf(v));
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList0) {
        this.mDrawableContainerState.mHasTintList = true;
        if(this.mDrawableContainerState.mTintList != colorStateList0) {
            this.mDrawableContainerState.mTintList = colorStateList0;
            DrawableCompat.setTintList(this.mCurrDrawable, colorStateList0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mDrawableContainerState.mHasTintMode = true;
        if(this.mDrawableContainerState.mTintMode != porterDuff$Mode0) {
            this.mDrawableContainerState.mTintMode = porterDuff$Mode0;
            DrawableCompat.setTintMode(this.mCurrDrawable, porterDuff$Mode0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        boolean z2 = super.setVisible(z, z1);
        Drawable drawable0 = this.mLastDrawable;
        if(drawable0 != null) {
            drawable0.setVisible(z, z1);
        }
        Drawable drawable1 = this.mCurrDrawable;
        if(drawable1 != null) {
            drawable1.setVisible(z, z1);
        }
        return z2;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        if(drawable0 == this.mCurrDrawable && this.getCallback() != null) {
            this.getCallback().unscheduleDrawable(this, runnable0);
        }
    }

    final void updateDensity(Resources resources0) {
        this.mDrawableContainerState.updateDensity(resources0);
    }
}

