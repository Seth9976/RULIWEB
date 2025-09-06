package com.airbnb.lottie;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.BaseSavedState;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottieAnimationView extends AppCompatImageView {
    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        String animationName;
        int animationResId;
        String imageAssetsFolder;
        boolean isAnimating;
        float progress;
        int repeatCount;
        int repeatMode;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        private SavedState(Parcel parcel0) {
            super(parcel0);
            this.animationName = parcel0.readString();
            this.progress = parcel0.readFloat();
            this.isAnimating = parcel0.readInt() == 1;
            this.imageAssetsFolder = parcel0.readString();
            this.repeatMode = parcel0.readInt();
            this.repeatCount = parcel0.readInt();
        }

        SavedState(Parcel parcel0, com.airbnb.lottie.LottieAnimationView.1 lottieAnimationView$10) {
            this(parcel0);
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.animationName);
            parcel0.writeFloat(this.progress);
            parcel0.writeInt(((int)this.isAnimating));
            parcel0.writeString(this.imageAssetsFolder);
            parcel0.writeInt(this.repeatMode);
            parcel0.writeInt(this.repeatCount);
        }
    }

    private static final LottieListener DEFAULT_FAILURE_LISTENER = null;
    private static final String TAG = "LottieAnimationView";
    private String animationName;
    private int animationResId;
    private boolean autoPlay;
    private int buildDrawingCacheDepth;
    private LottieComposition composition;
    private LottieTask compositionTask;
    private LottieListener failureListener;
    private int fallbackResource;
    private boolean isInitialized;
    private final LottieListener loadedListener;
    private final LottieDrawable lottieDrawable;
    private Set lottieOnCompositionLoadedListeners;
    private RenderMode renderMode;
    private boolean wasAnimatingWhenDetached;
    private boolean wasAnimatingWhenNotShown;
    private final LottieListener wrappedFailureListener;

    static {
        LottieAnimationView.DEFAULT_FAILURE_LISTENER = new LottieListener() {
            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((Throwable)object0));
            }

            public void onResult(Throwable throwable0) {
                if(!Utils.isNetworkException(throwable0)) {
                    throw new IllegalStateException("Unable to parse composition", throwable0);
                }
                Logger.warning("Unable to load composition.", throwable0);
            }
        };
    }

    public LottieAnimationView(Context context0) {
        super(context0);
        this.loadedListener = new LottieListener() {
            public void onResult(LottieComposition lottieComposition0) {
                LottieAnimationView.this.setComposition(lottieComposition0);
            }

            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((LottieComposition)object0));
            }
        };
        this.wrappedFailureListener = new LottieListener() {
            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((Throwable)object0));
            }

            public void onResult(Throwable throwable0) {
                if(LottieAnimationView.this.fallbackResource != 0) {
                    LottieAnimationView.this.setImageResource(LottieAnimationView.this.fallbackResource);
                }
                (LottieAnimationView.this.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : LottieAnimationView.this.failureListener).onResult(throwable0);
            }
        };
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.wasAnimatingWhenNotShown = false;
        this.wasAnimatingWhenDetached = false;
        this.autoPlay = false;
        this.renderMode = RenderMode.AUTOMATIC;
        this.lottieOnCompositionLoadedListeners = new HashSet();
        this.buildDrawingCacheDepth = 0;
        this.init(null);
    }

    public LottieAnimationView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.loadedListener = new LottieListener() {
            public void onResult(LottieComposition lottieComposition0) {
                LottieAnimationView.this.setComposition(lottieComposition0);
            }

            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((LottieComposition)object0));
            }
        };
        this.wrappedFailureListener = new LottieListener() {
            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((Throwable)object0));
            }

            public void onResult(Throwable throwable0) {
                if(LottieAnimationView.this.fallbackResource != 0) {
                    LottieAnimationView.this.setImageResource(LottieAnimationView.this.fallbackResource);
                }
                (LottieAnimationView.this.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : LottieAnimationView.this.failureListener).onResult(throwable0);
            }
        };
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.wasAnimatingWhenNotShown = false;
        this.wasAnimatingWhenDetached = false;
        this.autoPlay = false;
        this.renderMode = RenderMode.AUTOMATIC;
        this.lottieOnCompositionLoadedListeners = new HashSet();
        this.buildDrawingCacheDepth = 0;
        this.init(attributeSet0);
    }

    public LottieAnimationView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.loadedListener = new LottieListener() {
            public void onResult(LottieComposition lottieComposition0) {
                LottieAnimationView.this.setComposition(lottieComposition0);
            }

            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((LottieComposition)object0));
            }
        };
        this.wrappedFailureListener = new LottieListener() {
            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((Throwable)object0));
            }

            public void onResult(Throwable throwable0) {
                if(LottieAnimationView.this.fallbackResource != 0) {
                    LottieAnimationView.this.setImageResource(LottieAnimationView.this.fallbackResource);
                }
                (LottieAnimationView.this.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : LottieAnimationView.this.failureListener).onResult(throwable0);
            }
        };
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.wasAnimatingWhenNotShown = false;
        this.wasAnimatingWhenDetached = false;
        this.autoPlay = false;
        this.renderMode = RenderMode.AUTOMATIC;
        this.lottieOnCompositionLoadedListeners = new HashSet();
        this.buildDrawingCacheDepth = 0;
        this.init(attributeSet0);
    }

    public void addAnimatorListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.lottieDrawable.addAnimatorListener(animator$AnimatorListener0);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.lottieDrawable.addAnimatorUpdateListener(valueAnimator$AnimatorUpdateListener0);
    }

    public boolean addLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener0) {
        LottieComposition lottieComposition0 = this.composition;
        if(lottieComposition0 != null) {
            lottieOnCompositionLoadedListener0.onCompositionLoaded(lottieComposition0);
        }
        return this.lottieOnCompositionLoadedListeners.add(lottieOnCompositionLoadedListener0);
    }

    public void addValueCallback(KeyPath keyPath0, Object object0, LottieValueCallback lottieValueCallback0) {
        this.lottieDrawable.addValueCallback(keyPath0, object0, lottieValueCallback0);
    }

    public void addValueCallback(KeyPath keyPath0, Object object0, SimpleLottieValueCallback simpleLottieValueCallback0) {
        com.airbnb.lottie.LottieAnimationView.4 lottieAnimationView$40 = new LottieValueCallback() {
            @Override  // com.airbnb.lottie.value.LottieValueCallback
            public Object getValue(LottieFrameInfo lottieFrameInfo0) {
                return simpleLottieValueCallback0.getValue(lottieFrameInfo0);
            }
        };
        this.lottieDrawable.addValueCallback(keyPath0, object0, lottieAnimationView$40);
    }

    @Override  // android.view.View
    public void buildDrawingCache(boolean z) {
        L.beginSection("buildDrawingCache");
        ++this.buildDrawingCacheDepth;
        super.buildDrawingCache(z);
        if(this.buildDrawingCacheDepth == 1 && this.getWidth() > 0 && this.getHeight() > 0 && this.getLayerType() == 1 && this.getDrawingCache(z) == null) {
            this.setRenderMode(RenderMode.HARDWARE);
        }
        --this.buildDrawingCacheDepth;
        L.endSection("buildDrawingCache");
    }

    public void cancelAnimation() {
        this.wasAnimatingWhenNotShown = false;
        this.lottieDrawable.cancelAnimation();
        this.enableOrDisableHardwareLayer();
    }

    private void cancelLoaderTask() {
        LottieTask lottieTask0 = this.compositionTask;
        if(lottieTask0 != null) {
            lottieTask0.removeListener(this.loadedListener);
            this.compositionTask.removeFailureListener(this.wrappedFailureListener);
        }
    }

    private void clearComposition() {
        this.composition = null;
        this.lottieDrawable.clearComposition();
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        this.lottieDrawable.enableMergePathsForKitKatAndAbove(z);
    }

    private void enableOrDisableHardwareLayer() {
        int v = 2;
        switch(com.airbnb.lottie.LottieAnimationView.5.$SwitchMap$com$airbnb$lottie$RenderMode[this.renderMode.ordinal()]) {
            case 1: {
                break;
            }
            case 3: {
                if(this.composition != null && this.composition.hasDashPattern() && Build.VERSION.SDK_INT < 28 || this.composition != null && this.composition.getMaskAndMatteCount() > 4) {
                    v = 1;
                }
                break;
            }
            default: {
                v = 1;
            }
        }
        if(v != this.getLayerType()) {
            this.setLayerType(v, null);
        }
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public long getDuration() {
        return this.composition == null ? 0L : ((long)this.composition.getDuration());
    }

    public int getFrame() {
        return this.lottieDrawable.getFrame();
    }

    public String getImageAssetsFolder() {
        return this.lottieDrawable.getImageAssetsFolder();
    }

    public float getMaxFrame() {
        return this.lottieDrawable.getMaxFrame();
    }

    public float getMinFrame() {
        return this.lottieDrawable.getMinFrame();
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.lottieDrawable.getPerformanceTracker();
    }

    public float getProgress() {
        return this.lottieDrawable.getProgress();
    }

    public int getRepeatCount() {
        return this.lottieDrawable.getRepeatCount();
    }

    public int getRepeatMode() {
        return this.lottieDrawable.getRepeatMode();
    }

    public float getScale() {
        return this.lottieDrawable.getScale();
    }

    public float getSpeed() {
        return this.lottieDrawable.getSpeed();
    }

    public boolean hasMasks() {
        return this.lottieDrawable.hasMasks();
    }

    public boolean hasMatte() {
        return this.lottieDrawable.hasMatte();
    }

    private void init(AttributeSet attributeSet0) {
        TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.LottieAnimationView);
        boolean z = false;
        if(!this.isInEditMode()) {
            boolean z1 = typedArray0.hasValue(styleable.LottieAnimationView_lottie_rawRes);
            boolean z2 = typedArray0.hasValue(styleable.LottieAnimationView_lottie_fileName);
            boolean z3 = typedArray0.hasValue(styleable.LottieAnimationView_lottie_url);
            if(z1 && z2) {
                throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
            }
            if(z1) {
                int v = typedArray0.getResourceId(styleable.LottieAnimationView_lottie_rawRes, 0);
                if(v != 0) {
                    this.setAnimation(v);
                }
            }
            else if(z2) {
                String s = typedArray0.getString(styleable.LottieAnimationView_lottie_fileName);
                if(s != null) {
                    this.setAnimation(s);
                }
            }
            else if(z3) {
                String s1 = typedArray0.getString(styleable.LottieAnimationView_lottie_url);
                if(s1 != null) {
                    this.setAnimationFromUrl(s1);
                }
            }
            this.setFallbackResource(typedArray0.getResourceId(styleable.LottieAnimationView_lottie_fallbackRes, 0));
        }
        if(typedArray0.getBoolean(styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.wasAnimatingWhenDetached = true;
            this.autoPlay = true;
        }
        if(typedArray0.getBoolean(styleable.LottieAnimationView_lottie_loop, false)) {
            this.lottieDrawable.setRepeatCount(-1);
        }
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_repeatMode)) {
            this.setRepeatMode(typedArray0.getInt(styleable.LottieAnimationView_lottie_repeatMode, 1));
        }
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_repeatCount)) {
            this.setRepeatCount(typedArray0.getInt(styleable.LottieAnimationView_lottie_repeatCount, -1));
        }
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_speed)) {
            this.setSpeed(typedArray0.getFloat(styleable.LottieAnimationView_lottie_speed, 1.0f));
        }
        this.setImageAssetsFolder(typedArray0.getString(styleable.LottieAnimationView_lottie_imageAssetsFolder));
        this.setProgress(typedArray0.getFloat(styleable.LottieAnimationView_lottie_progress, 0.0f));
        this.enableMergePathsForKitKatAndAbove(typedArray0.getBoolean(styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_colorFilter)) {
            SimpleColorFilter simpleColorFilter0 = new SimpleColorFilter(typedArray0.getColor(styleable.LottieAnimationView_lottie_colorFilter, 0));
            KeyPath keyPath0 = new KeyPath(new String[]{"**"});
            LottieValueCallback lottieValueCallback0 = new LottieValueCallback(simpleColorFilter0);
            this.addValueCallback(keyPath0, LottieProperty.COLOR_FILTER, lottieValueCallback0);
        }
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_scale)) {
            float f = typedArray0.getFloat(styleable.LottieAnimationView_lottie_scale, 1.0f);
            this.lottieDrawable.setScale(f);
        }
        if(typedArray0.hasValue(styleable.LottieAnimationView_lottie_renderMode)) {
            int v1 = typedArray0.getInt(styleable.LottieAnimationView_lottie_renderMode, RenderMode.AUTOMATIC.ordinal());
            if(v1 >= RenderMode.values().length) {
                v1 = RenderMode.AUTOMATIC.ordinal();
            }
            this.setRenderMode(RenderMode.values()[v1]);
        }
        typedArray0.recycle();
        LottieDrawable lottieDrawable0 = this.lottieDrawable;
        if(Utils.getAnimationScale(this.getContext()) != 0.0f) {
            z = true;
        }
        lottieDrawable0.setSystemAnimationsAreEnabled(Boolean.valueOf(z));
        this.enableOrDisableHardwareLayer();
        this.isInitialized = true;
    }

    @Override  // android.widget.ImageView
    public void invalidateDrawable(Drawable drawable0) {
        Drawable drawable1 = this.getDrawable();
        LottieDrawable lottieDrawable0 = this.lottieDrawable;
        if(drawable1 == lottieDrawable0) {
            super.invalidateDrawable(lottieDrawable0);
            return;
        }
        super.invalidateDrawable(drawable0);
    }

    public boolean isAnimating() {
        return this.lottieDrawable.isAnimating();
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.lottieDrawable.isMergePathsEnabledForKitKatAndAbove();
    }

    @Deprecated
    public void loop(boolean z) {
        this.lottieDrawable.setRepeatCount((z ? -1 : 0));
    }

    @Override  // android.widget.ImageView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.autoPlay || this.wasAnimatingWhenDetached) {
            this.playAnimation();
            this.autoPlay = false;
            this.wasAnimatingWhenDetached = false;
        }
        if(Build.VERSION.SDK_INT < 23) {
            this.onVisibilityChanged(this, this.getVisibility());
        }
    }

    @Override  // android.widget.ImageView
    protected void onDetachedFromWindow() {
        if(this.isAnimating()) {
            this.cancelAnimation();
            this.wasAnimatingWhenDetached = true;
        }
        super.onDetachedFromWindow();
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        String s = ((SavedState)parcelable0).animationName;
        this.animationName = s;
        if(!TextUtils.isEmpty(s)) {
            this.setAnimation(this.animationName);
        }
        int v = ((SavedState)parcelable0).animationResId;
        this.animationResId = v;
        if(v != 0) {
            this.setAnimation(v);
        }
        this.setProgress(((SavedState)parcelable0).progress);
        if(((SavedState)parcelable0).isAnimating) {
            this.playAnimation();
        }
        this.lottieDrawable.setImagesAssetsFolder(((SavedState)parcelable0).imageAssetsFolder);
        this.setRepeatMode(((SavedState)parcelable0).repeatMode);
        this.setRepeatCount(((SavedState)parcelable0).repeatCount);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.animationName = this.animationName;
        parcelable0.animationResId = this.animationResId;
        parcelable0.progress = this.lottieDrawable.getProgress();
        parcelable0.isAnimating = this.lottieDrawable.isAnimating();
        parcelable0.imageAssetsFolder = this.lottieDrawable.getImageAssetsFolder();
        parcelable0.repeatMode = this.lottieDrawable.getRepeatMode();
        parcelable0.repeatCount = this.lottieDrawable.getRepeatCount();
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onVisibilityChanged(View view0, int v) {
        if(this.isInitialized) {
            if(this.isShown()) {
                if(this.wasAnimatingWhenNotShown) {
                    this.resumeAnimation();
                    this.wasAnimatingWhenNotShown = false;
                }
            }
            else if(this.isAnimating()) {
                this.pauseAnimation();
                this.wasAnimatingWhenNotShown = true;
            }
        }
    }

    public void pauseAnimation() {
        this.autoPlay = false;
        this.wasAnimatingWhenDetached = false;
        this.wasAnimatingWhenNotShown = false;
        this.lottieDrawable.pauseAnimation();
        this.enableOrDisableHardwareLayer();
    }

    public void playAnimation() {
        if(this.isShown()) {
            this.lottieDrawable.playAnimation();
            this.enableOrDisableHardwareLayer();
            return;
        }
        this.wasAnimatingWhenNotShown = true;
    }

    public void removeAllAnimatorListeners() {
        this.lottieDrawable.removeAllAnimatorListeners();
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.lottieOnCompositionLoadedListeners.clear();
    }

    public void removeAllUpdateListeners() {
        this.lottieDrawable.removeAllUpdateListeners();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.lottieDrawable.removeAnimatorListener(animator$AnimatorListener0);
    }

    public boolean removeLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener0) {
        return this.lottieOnCompositionLoadedListeners.remove(lottieOnCompositionLoadedListener0);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.lottieDrawable.removeAnimatorUpdateListener(valueAnimator$AnimatorUpdateListener0);
    }

    public List resolveKeyPath(KeyPath keyPath0) {
        return this.lottieDrawable.resolveKeyPath(keyPath0);
    }

    public void resumeAnimation() {
        if(this.isShown()) {
            this.lottieDrawable.resumeAnimation();
            this.enableOrDisableHardwareLayer();
            return;
        }
        this.wasAnimatingWhenNotShown = true;
    }

    public void reverseAnimationSpeed() {
        this.lottieDrawable.reverseAnimationSpeed();
    }

    public void setAnimation(int v) {
        this.animationResId = v;
        this.animationName = null;
        this.setCompositionTask(LottieCompositionFactory.fromRawRes(this.getContext(), v));
    }

    public void setAnimation(InputStream inputStream0, String s) {
        this.setCompositionTask(LottieCompositionFactory.fromJsonInputStream(inputStream0, s));
    }

    public void setAnimation(String s) {
        this.animationName = s;
        this.animationResId = 0;
        this.setCompositionTask(LottieCompositionFactory.fromAsset(this.getContext(), s));
    }

    @Deprecated
    public void setAnimationFromJson(String s) {
        this.setAnimationFromJson(s, null);
    }

    public void setAnimationFromJson(String s, String s1) {
        this.setAnimation(new ByteArrayInputStream(s.getBytes()), s1);
    }

    public void setAnimationFromUrl(String s) {
        this.setCompositionTask(LottieCompositionFactory.fromUrl(this.getContext(), s));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.lottieDrawable.setApplyingOpacityToLayersEnabled(z);
    }

    public void setComposition(LottieComposition lottieComposition0) {
        if(L.DBG) {
            Log.v(LottieAnimationView.TAG, "Set Composition \n" + lottieComposition0);
        }
        this.lottieDrawable.setCallback(this);
        this.composition = lottieComposition0;
        boolean z = this.lottieDrawable.setComposition(lottieComposition0);
        this.enableOrDisableHardwareLayer();
        if(this.getDrawable() != this.lottieDrawable || z) {
            this.setImageDrawable(null);
            this.setImageDrawable(this.lottieDrawable);
            this.onVisibilityChanged(this, this.getVisibility());
            this.requestLayout();
            for(Object object0: this.lottieOnCompositionLoadedListeners) {
                ((LottieOnCompositionLoadedListener)object0).onCompositionLoaded(lottieComposition0);
            }
        }
    }

    private void setCompositionTask(LottieTask lottieTask0) {
        this.clearComposition();
        this.cancelLoaderTask();
        this.compositionTask = lottieTask0.addListener(this.loadedListener).addFailureListener(this.wrappedFailureListener);
    }

    public void setFailureListener(LottieListener lottieListener0) {
        this.failureListener = lottieListener0;
    }

    public void setFallbackResource(int v) {
        this.fallbackResource = v;
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate0) {
        this.lottieDrawable.setFontAssetDelegate(fontAssetDelegate0);
    }

    public void setFrame(int v) {
        this.lottieDrawable.setFrame(v);
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate0) {
        this.lottieDrawable.setImageAssetDelegate(imageAssetDelegate0);
    }

    public void setImageAssetsFolder(String s) {
        this.lottieDrawable.setImagesAssetsFolder(s);
    }

    @Override  // androidx.appcompat.widget.AppCompatImageView
    public void setImageBitmap(Bitmap bitmap0) {
        this.cancelLoaderTask();
        super.setImageBitmap(bitmap0);
    }

    @Override  // androidx.appcompat.widget.AppCompatImageView
    public void setImageDrawable(Drawable drawable0) {
        this.cancelLoaderTask();
        super.setImageDrawable(drawable0);
    }

    @Override  // androidx.appcompat.widget.AppCompatImageView
    public void setImageResource(int v) {
        this.cancelLoaderTask();
        super.setImageResource(v);
    }

    public void setMaxFrame(int v) {
        this.lottieDrawable.setMaxFrame(v);
    }

    public void setMaxFrame(String s) {
        this.lottieDrawable.setMaxFrame(s);
    }

    public void setMaxProgress(float f) {
        this.lottieDrawable.setMaxProgress(f);
    }

    public void setMinAndMaxFrame(int v, int v1) {
        this.lottieDrawable.setMinAndMaxFrame(v, v1);
    }

    public void setMinAndMaxFrame(String s) {
        this.lottieDrawable.setMinAndMaxFrame(s);
    }

    public void setMinAndMaxProgress(float f, float f1) {
        this.lottieDrawable.setMinAndMaxProgress(f, f1);
    }

    public void setMinFrame(int v) {
        this.lottieDrawable.setMinFrame(v);
    }

    public void setMinFrame(String s) {
        this.lottieDrawable.setMinFrame(s);
    }

    public void setMinProgress(float f) {
        this.lottieDrawable.setMinProgress(f);
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.lottieDrawable.setPerformanceTrackingEnabled(z);
    }

    public void setProgress(float f) {
        this.lottieDrawable.setProgress(f);
    }

    public void setRenderMode(RenderMode renderMode0) {
        this.renderMode = renderMode0;
        this.enableOrDisableHardwareLayer();
    }

    public void setRepeatCount(int v) {
        this.lottieDrawable.setRepeatCount(v);
    }

    public void setRepeatMode(int v) {
        this.lottieDrawable.setRepeatMode(v);
    }

    public void setScale(float f) {
        this.lottieDrawable.setScale(f);
        if(this.getDrawable() == this.lottieDrawable) {
            this.setImageDrawable(null);
            this.setImageDrawable(this.lottieDrawable);
        }
    }

    public void setSpeed(float f) {
        this.lottieDrawable.setSpeed(f);
    }

    public void setTextDelegate(TextDelegate textDelegate0) {
        this.lottieDrawable.setTextDelegate(textDelegate0);
    }

    public Bitmap updateBitmap(String s, Bitmap bitmap0) {
        return this.lottieDrawable.updateBitmap(s, bitmap0);
    }
}

