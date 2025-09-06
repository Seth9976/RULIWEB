package com.airbnb.lottie;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LottieDrawable extends Drawable implements Animatable, Drawable.Callback {
    static class ColorFilterData {
        final ColorFilter colorFilter;
        final String contentName;
        final String layerName;

        ColorFilterData(String s, String s1, ColorFilter colorFilter0) {
            this.layerName = s;
            this.contentName = s1;
            this.colorFilter = colorFilter0;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof ColorFilterData ? this.hashCode() == ((ColorFilterData)object0).hashCode() && this.colorFilter == ((ColorFilterData)object0).colorFilter : false;
        }

        @Override
        public int hashCode() {
            int v = this.layerName == null ? 17 : 0x20F * this.layerName.hashCode();
            return this.contentName == null ? v : v * 0x1F * this.contentName.hashCode();
        }
    }

    interface LazyCompositionTask {
        void run(LottieComposition arg1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static final String TAG = "LottieDrawable";
    private int alpha;
    private final LottieValueAnimator animator;
    private final Set colorFilterData;
    private LottieComposition composition;
    private CompositionLayer compositionLayer;
    private boolean enableMergePaths;
    FontAssetDelegate fontAssetDelegate;
    private FontAssetManager fontAssetManager;
    private ImageAssetDelegate imageAssetDelegate;
    private ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isDirty;
    private final ArrayList lazyCompositionTasks;
    private final Matrix matrix;
    private boolean performanceTrackingEnabled;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    private float scale;
    private boolean systemAnimationsEnabled;
    TextDelegate textDelegate;

    static {
    }

    public LottieDrawable() {
        this.matrix = new Matrix();
        LottieValueAnimator lottieValueAnimator0 = new LottieValueAnimator();
        this.animator = lottieValueAnimator0;
        this.scale = 1.0f;
        this.systemAnimationsEnabled = true;
        this.colorFilterData = new HashSet();
        this.lazyCompositionTasks = new ArrayList();
        com.airbnb.lottie.LottieDrawable.1 lottieDrawable$10 = new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                if(LottieDrawable.this.compositionLayer != null) {
                    LottieDrawable.this.compositionLayer.setProgress(LottieDrawable.this.animator.getAnimatedValueAbsolute());
                }
            }
        };
        this.progressUpdateListener = lottieDrawable$10;
        this.alpha = 0xFF;
        this.isDirty = false;
        lottieValueAnimator0.addUpdateListener(lottieDrawable$10);
    }

    public void addAnimatorListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.animator.addListener(animator$AnimatorListener0);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.animator.addUpdateListener(valueAnimator$AnimatorUpdateListener0);
    }

    // 检测为 Lambda 实现
    public void addValueCallback(KeyPath keyPath0, Object object0, LottieValueCallback lottieValueCallback0) [...]

    public void addValueCallback(KeyPath keyPath0, Object object0, SimpleLottieValueCallback simpleLottieValueCallback0) {
        this.addValueCallback(keyPath0, object0, new LottieValueCallback() {
            @Override  // com.airbnb.lottie.value.LottieValueCallback
            public Object getValue(LottieFrameInfo lottieFrameInfo0) {
                return simpleLottieValueCallback0.getValue(lottieFrameInfo0);
            }
        });
    }

    private void buildCompositionLayer() {
        this.compositionLayer = new CompositionLayer(this, LayerParser.parse(this.composition), this.composition.getLayers(), this.composition);
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
    }

    public void clearComposition() {
        if(this.animator.isRunning()) {
            this.animator.cancel();
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.animator.clearComposition();
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        int v;
        float f2;
        this.isDirty = false;
        L.beginSection("Drawable#draw");
        if(this.compositionLayer != null) {
            float f = this.scale;
            float f1 = this.getMaxScale(canvas0);
            if(f > f1) {
                f2 = this.scale / f1;
            }
            else {
                f1 = f;
                f2 = 1.0f;
            }
            if(f2 > 1.0f) {
                v = canvas0.save();
                float f3 = ((float)this.composition.getBounds().width()) / 2.0f;
                float f4 = ((float)this.composition.getBounds().height()) / 2.0f;
                float f5 = f3 * f1;
                float f6 = f4 * f1;
                canvas0.translate(this.getScale() * f3 - f5, this.getScale() * f4 - f6);
                canvas0.scale(f2, f2, f5, f6);
            }
            else {
                v = -1;
            }
            this.matrix.reset();
            this.matrix.preScale(f1, f1);
            this.compositionLayer.draw(canvas0, this.matrix, this.alpha);
            L.endSection("Drawable#draw");
            if(v > 0) {
                canvas0.restoreToCount(v);
            }
        }
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        if(this.enableMergePaths != z) {
            this.enableMergePaths = z;
            if(this.composition != null) {
                this.buildCompositionLayer();
            }
        }
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    private Context getContext() {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 == null) {
            return null;
        }
        return drawable$Callback0 instanceof View ? ((View)drawable$Callback0).getContext() : null;
    }

    private FontAssetManager getFontAssetManager() {
        if(this.getCallback() == null) {
            return null;
        }
        if(this.fontAssetManager == null) {
            this.fontAssetManager = new FontAssetManager(this.getCallback(), this.fontAssetDelegate);
        }
        return this.fontAssetManager;
    }

    public int getFrame() {
        return (int)this.animator.getFrame();
    }

    public Bitmap getImageAsset(String s) {
        ImageAssetManager imageAssetManager0 = this.getImageAssetManager();
        return imageAssetManager0 == null ? null : imageAssetManager0.bitmapForId(s);
    }

    private ImageAssetManager getImageAssetManager() {
        if(this.getCallback() == null) {
            return null;
        }
        if(this.imageAssetManager != null && !this.imageAssetManager.hasSameContext(this.getContext())) {
            this.imageAssetManager = null;
        }
        if(this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(this.getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.composition == null ? -1 : ((int)(((float)this.composition.getBounds().height()) * this.getScale()));
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.composition == null ? -1 : ((int)(((float)this.composition.getBounds().width()) * this.getScale()));
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    private float getMaxScale(Canvas canvas0) {
        return Math.min(((float)canvas0.getWidth()) / ((float)this.composition.getBounds().width()), ((float)canvas0.getHeight()) / ((float)this.composition.getBounds().height()));
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.composition == null ? null : this.composition.getPerformanceTracker();
    }

    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public float getScale() {
        return this.scale;
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public Typeface getTypeface(String s, String s1) {
        FontAssetManager fontAssetManager0 = this.getFontAssetManager();
        return fontAssetManager0 == null ? null : fontAssetManager0.getTypeface(s, s1);
    }

    public boolean hasMasks() {
        return this.compositionLayer != null && this.compositionLayer.hasMasks();
    }

    public boolean hasMatte() {
        return this.compositionLayer != null && this.compositionLayer.hasMatte();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(Drawable drawable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 == null) {
            return;
        }
        drawable$Callback0.invalidateDrawable(this);
    }

    @Override  // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if(!this.isDirty) {
            this.isDirty = true;
            Drawable.Callback drawable$Callback0 = this.getCallback();
            if(drawable$Callback0 != null) {
                drawable$Callback0.invalidateDrawable(this);
            }
        }
    }

    public boolean isAnimating() {
        return this.animator.isRunning();
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    @Override  // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.isAnimating();
    }

    @Deprecated
    public void loop(boolean z) {
        this.animator.setRepeatCount((z ? -1 : 0));
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
    }

    // 检测为 Lambda 实现
    public void playAnimation() [...]

    public void removeAllAnimatorListeners() {
        this.animator.removeAllListeners();
    }

    public void removeAllUpdateListeners() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.animator.removeListener(animator$AnimatorListener0);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.animator.removeUpdateListener(valueAnimator$AnimatorUpdateListener0);
    }

    public List resolveKeyPath(KeyPath keyPath0) {
        if(this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.EMPTY_LIST;
        }
        List list0 = new ArrayList();
        this.compositionLayer.resolveKeyPath(keyPath0, 0, list0, new KeyPath(new String[0]));
        return list0;
    }

    // 检测为 Lambda 实现
    public void resumeAnimation() [...]

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 == null) {
            return;
        }
        drawable$Callback0.scheduleDrawable(this, runnable0, v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.alpha = v;
        this.invalidateSelf();
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.isApplyingOpacityToLayersEnabled = z;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        Logger.warning("Use addColorFilter instead.");
    }

    public boolean setComposition(LottieComposition lottieComposition0) {
        if(this.composition == lottieComposition0) {
            return false;
        }
        this.isDirty = false;
        this.clearComposition();
        this.composition = lottieComposition0;
        this.buildCompositionLayer();
        this.animator.setComposition(lottieComposition0);
        this.setProgress(this.animator.getAnimatedFraction());
        this.setScale(this.scale);
        this.updateBounds();
        Iterator iterator0 = new ArrayList(this.lazyCompositionTasks).iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            ((LazyCompositionTask)object0).run(lottieComposition0);
            iterator0.remove();
        }
        this.lazyCompositionTasks.clear();
        lottieComposition0.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        return true;
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate0) {
        this.fontAssetDelegate = fontAssetDelegate0;
        FontAssetManager fontAssetManager0 = this.fontAssetManager;
        if(fontAssetManager0 != null) {
            fontAssetManager0.setDelegate(fontAssetDelegate0);
        }
    }

    // 检测为 Lambda 实现
    public void setFrame(int v) [...]

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate0) {
        this.imageAssetDelegate = imageAssetDelegate0;
        ImageAssetManager imageAssetManager0 = this.imageAssetManager;
        if(imageAssetManager0 != null) {
            imageAssetManager0.setDelegate(imageAssetDelegate0);
        }
    }

    public void setImagesAssetsFolder(String s) {
        this.imageAssetsFolder = s;
    }

    // 检测为 Lambda 实现
    public void setMaxFrame(int v) [...]

    // 检测为 Lambda 实现
    public void setMaxFrame(String s) [...]

    // 检测为 Lambda 实现
    public void setMaxProgress(float f) [...]

    // 检测为 Lambda 实现
    public void setMinAndMaxFrame(int v, int v1) [...]

    // 检测为 Lambda 实现
    public void setMinAndMaxFrame(String s) [...]

    // 检测为 Lambda 实现
    public void setMinAndMaxProgress(float f, float f1) [...]

    // 检测为 Lambda 实现
    public void setMinFrame(int v) [...]

    // 检测为 Lambda 实现
    public void setMinFrame(String s) [...]

    // 检测为 Lambda 实现
    public void setMinProgress(float f) [...]

    public void setPerformanceTrackingEnabled(boolean z) {
        this.performanceTrackingEnabled = z;
        LottieComposition lottieComposition0 = this.composition;
        if(lottieComposition0 != null) {
            lottieComposition0.setPerformanceTrackingEnabled(z);
        }
    }

    // 检测为 Lambda 实现
    public void setProgress(float f) [...]

    public void setRepeatCount(int v) {
        this.animator.setRepeatCount(v);
    }

    public void setRepeatMode(int v) {
        this.animator.setRepeatMode(v);
    }

    public void setScale(float f) {
        this.scale = f;
        this.updateBounds();
    }

    public void setSpeed(float f) {
        this.animator.setSpeed(f);
    }

    void setSystemAnimationsAreEnabled(Boolean boolean0) {
        this.systemAnimationsEnabled = boolean0.booleanValue();
    }

    public void setTextDelegate(TextDelegate textDelegate0) {
        this.textDelegate = textDelegate0;
    }

    @Override  // android.graphics.drawable.Animatable
    public void start() {
        this.playAnimation();
    }

    @Override  // android.graphics.drawable.Animatable
    public void stop() {
        this.endAnimation();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 == null) {
            return;
        }
        drawable$Callback0.unscheduleDrawable(this, runnable0);
    }

    public Bitmap updateBitmap(String s, Bitmap bitmap0) {
        ImageAssetManager imageAssetManager0 = this.getImageAssetManager();
        if(imageAssetManager0 == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap bitmap1 = imageAssetManager0.updateBitmap(s, bitmap0);
        this.invalidateSelf();
        return bitmap1;
    }

    private void updateBounds() {
        if(this.composition == null) {
            return;
        }
        float f = this.getScale();
        this.setBounds(0, 0, ((int)(((float)this.composition.getBounds().width()) * f)), ((int)(((float)this.composition.getBounds().height()) * f)));
    }

    public boolean useTextGlyphs() {
        return this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }
}

