package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.Compatibility.Api21Impl;
import androidx.appcompat.resources.R.styleable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StateListDrawableCompat extends DrawableContainerCompat {
    static class StateListState extends DrawableContainerState {
        int[][] mStateSets;

        StateListState(StateListState stateListDrawableCompat$StateListState0, StateListDrawableCompat stateListDrawableCompat0, Resources resources0) {
            super(stateListDrawableCompat$StateListState0, stateListDrawableCompat0, resources0);
            if(stateListDrawableCompat$StateListState0 != null) {
                this.mStateSets = stateListDrawableCompat$StateListState0.mStateSets;
                return;
            }
            this.mStateSets = new int[this.getCapacity()][];
        }

        int addStateSet(int[] arr_v, Drawable drawable0) {
            int v = this.addChild(drawable0);
            this.mStateSets[v] = arr_v;
            return v;
        }

        @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState
        public void growArray(int v, int v1) {
            super.growArray(v, v1);
            int[][] arr2_v = new int[v1][];
            System.arraycopy(this.mStateSets, 0, arr2_v, 0, v);
            this.mStateSets = arr2_v;
        }

        int indexOfStateSet(int[] arr_v) {
            int[][] arr2_v = this.mStateSets;
            int v = this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                if(StateSet.stateSetMatches(arr2_v[v1], arr_v)) {
                    return v1;
                }
            }
            return -1;
        }

        @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState
        void mutate() {
            int[][] arr2_v = this.mStateSets;
            int[][] arr2_v1 = new int[arr2_v.length][];
            for(int v = arr2_v.length - 1; v >= 0; --v) {
                int[] arr_v = this.mStateSets[v];
                arr2_v1[v] = arr_v == null ? null : ((int[])arr_v.clone());
            }
            this.mStateSets = arr2_v1;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            return new StateListDrawableCompat(this, null);
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0) {
            return new StateListDrawableCompat(this, resources0);
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawableCompat";
    private boolean mMutated;
    private StateListState mStateListState;

    public StateListDrawableCompat() {
        this(null, null);
    }

    StateListDrawableCompat(StateListState stateListDrawableCompat$StateListState0) {
        if(stateListDrawableCompat$StateListState0 != null) {
            this.setConstantState(stateListDrawableCompat$StateListState0);
        }
    }

    StateListDrawableCompat(StateListState stateListDrawableCompat$StateListState0, Resources resources0) {
        this.setConstantState(new StateListState(stateListDrawableCompat$StateListState0, this, resources0));
        this.onStateChange(this.getState());
    }

    public void addState(int[] arr_v, Drawable drawable0) {
        if(drawable0 != null) {
            this.mStateListState.addStateSet(arr_v, drawable0);
            this.onStateChange(this.getState());
        }
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public void applyTheme(Resources.Theme resources$Theme0) {
        super.applyTheme(resources$Theme0);
        this.onStateChange(this.getState());
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    DrawableContainerState cloneConstantState() {
        return this.cloneConstantState();
    }

    StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    int[] extractStateSet(AttributeSet attributeSet0) {
        int v = attributeSet0.getAttributeCount();
        int[] arr_v = new int[v];
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            int v3 = attributeSet0.getAttributeNameResource(v1);
            if(v3 != 0 && v3 != 0x10100D0 && v3 != 0x1010199) {
                if(!attributeSet0.getAttributeBooleanValue(v1, false)) {
                    v3 = -v3;
                }
                arr_v[v2] = v3;
                ++v2;
            }
        }
        return StateSet.trimStateSet(arr_v, v2);
    }

    int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    Drawable getStateDrawable(int v) {
        return this.mStateListState.getChild(v);
    }

    int getStateDrawableIndex(int[] arr_v) {
        return this.mStateListState.indexOfStateSet(arr_v);
    }

    StateListState getStateListState() {
        return this.mStateListState;
    }

    int[] getStateSet(int v) {
        return this.mStateListState.mStateSets[v];
    }

    public void inflate(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.StateListDrawable);
        this.setVisible(typedArray0.getBoolean(styleable.StateListDrawable_android_visible, true), true);
        this.updateStateFromTypedArray(typedArray0);
        this.updateDensity(resources0);
        typedArray0.recycle();
        this.inflateChildElements(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        this.onStateChange(this.getState());
    }

    private void inflateChildElements(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        StateListState stateListDrawableCompat$StateListState0 = this.mStateListState;
        int v = xmlPullParser0.getDepth();
        int v1;
        while((v1 = xmlPullParser0.next()) != 1) {
            int v2 = xmlPullParser0.getDepth();
            if(v2 < v + 1 && v1 == 3) {
                break;
            }
            if(v1 == 2 && v2 <= v + 1 && xmlPullParser0.getName().equals("item")) {
                TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.StateListDrawableItem);
                int v3 = typedArray0.getResourceId(styleable.StateListDrawableItem_android_drawable, -1);
                Drawable drawable0 = v3 <= 0 ? null : ResourceManagerInternal.get().getDrawable(context0, v3);
                typedArray0.recycle();
                int[] arr_v = this.extractStateSet(attributeSet0);
                if(drawable0 == null) {
                alab1:
                    while(true) {
                        switch(xmlPullParser0.next()) {
                            case 2: {
                                drawable0 = Api21Impl.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
                                break alab1;
                            }
                            case 4: {
                                break;
                            }
                            default: {
                                throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <item> tag requires a \'drawable\' attribute or child tag defining a drawable");
                            }
                        }
                    }
                }
                stateListDrawableCompat$StateListState0.addStateSet(arr_v, drawable0);
            }
        }
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public boolean isStateful() {
        return true;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public Drawable mutate() {
        if(!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    protected boolean onStateChange(int[] arr_v) {
        boolean z = super.onStateChange(arr_v);
        int v = this.mStateListState.indexOfStateSet(arr_v);
        if(v < 0) {
            v = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return this.selectDrawable(v) || z;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    void setConstantState(DrawableContainerState drawableContainerCompat$DrawableContainerState0) {
        super.setConstantState(drawableContainerCompat$DrawableContainerState0);
        if(drawableContainerCompat$DrawableContainerState0 instanceof StateListState) {
            this.mStateListState = (StateListState)drawableContainerCompat$DrawableContainerState0;
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray0) {
        StateListState stateListDrawableCompat$StateListState0 = this.mStateListState;
        stateListDrawableCompat$StateListState0.mChangingConfigurations |= Api21Impl.getChangingConfigurations(typedArray0);
        stateListDrawableCompat$StateListState0.mVariablePadding = typedArray0.getBoolean(styleable.StateListDrawable_android_variablePadding, stateListDrawableCompat$StateListState0.mVariablePadding);
        stateListDrawableCompat$StateListState0.mConstantSize = typedArray0.getBoolean(styleable.StateListDrawable_android_constantSize, stateListDrawableCompat$StateListState0.mConstantSize);
        stateListDrawableCompat$StateListState0.mEnterFadeDuration = typedArray0.getInt(styleable.StateListDrawable_android_enterFadeDuration, stateListDrawableCompat$StateListState0.mEnterFadeDuration);
        stateListDrawableCompat$StateListState0.mExitFadeDuration = typedArray0.getInt(styleable.StateListDrawable_android_exitFadeDuration, stateListDrawableCompat$StateListState0.mExitFadeDuration);
        stateListDrawableCompat$StateListState0.mDither = typedArray0.getBoolean(styleable.StateListDrawable_android_dither, stateListDrawableCompat$StateListState0.mDither);
    }
}

