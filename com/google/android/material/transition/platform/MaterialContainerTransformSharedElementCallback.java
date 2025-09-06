package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.Window;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.R.id;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {
    public interface ShapeProvider {
        ShapeAppearanceModel provideShape(View arg1);
    }

    public static class ShapeableViewShapeProvider implements ShapeProvider {
        // 去混淆评级： 低(20)
        @Override  // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback$ShapeProvider
        public ShapeAppearanceModel provideShape(View view0) {
            return view0 instanceof Shapeable ? ((Shapeable)view0).getShapeAppearanceModel() : null;
        }
    }

    private static WeakReference capturedSharedElement;
    private boolean entering;
    private Rect returnEndBounds;
    private ShapeProvider shapeProvider;
    private boolean sharedElementReenterTransitionEnabled;
    private boolean transparentWindowBackgroundEnabled;

    public MaterialContainerTransformSharedElementCallback() {
        this.entering = true;
        this.transparentWindowBackgroundEnabled = true;
        this.sharedElementReenterTransitionEnabled = false;
        this.shapeProvider = new ShapeableViewShapeProvider();
    }

    public ShapeProvider getShapeProvider() {
        return this.shapeProvider;
    }

    private static Drawable getWindowBackground(Window window0) {
        return window0.getDecorView().getBackground();
    }

    public boolean isSharedElementReenterTransitionEnabled() {
        return this.sharedElementReenterTransitionEnabled;
    }

    public boolean isTransparentWindowBackgroundEnabled() {
        return this.transparentWindowBackgroundEnabled;
    }

    @Override  // android.app.SharedElementCallback
    public Parcelable onCaptureSharedElementSnapshot(View view0, Matrix matrix0, RectF rectF0) {
        MaterialContainerTransformSharedElementCallback.capturedSharedElement = new WeakReference(view0);
        return super.onCaptureSharedElementSnapshot(view0, matrix0, rectF0);
    }

    @Override  // android.app.SharedElementCallback
    public View onCreateSnapshotView(Context context0, Parcelable parcelable0) {
        View view0 = super.onCreateSnapshotView(context0, parcelable0);
        if(view0 != null) {
            WeakReference weakReference0 = MaterialContainerTransformSharedElementCallback.capturedSharedElement;
            if(weakReference0 != null && this.shapeProvider != null) {
                View view1 = (View)weakReference0.get();
                if(view1 != null) {
                    ShapeAppearanceModel shapeAppearanceModel0 = this.shapeProvider.provideShape(view1);
                    if(shapeAppearanceModel0 != null) {
                        view0.setTag(id.mtrl_motion_snapshot_view, shapeAppearanceModel0);
                    }
                }
            }
        }
        return view0;
    }

    @Override  // android.app.SharedElementCallback
    public void onMapSharedElements(List list0, Map map0) {
        if(!list0.isEmpty() && !map0.isEmpty()) {
            View view0 = (View)map0.get(list0.get(0));
            if(view0 != null) {
                Activity activity0 = ContextUtils.getActivity(view0.getContext());
                if(activity0 != null) {
                    Window window0 = activity0.getWindow();
                    if(this.entering) {
                        this.setUpEnterTransform(window0);
                        return;
                    }
                    this.setUpReturnTransform(activity0, window0);
                }
            }
        }
    }

    @Override  // android.app.SharedElementCallback
    public void onSharedElementEnd(List list0, List list1, List list2) {
        if(!list1.isEmpty() && ((View)list1.get(0)).getTag(id.mtrl_motion_snapshot_view) instanceof View) {
            ((View)list1.get(0)).setTag(id.mtrl_motion_snapshot_view, null);
        }
        if(!this.entering && !list1.isEmpty()) {
            this.returnEndBounds = TransitionUtils.getRelativeBoundsRect(((View)list1.get(0)));
        }
        this.entering = false;
    }

    @Override  // android.app.SharedElementCallback
    public void onSharedElementStart(List list0, List list1, List list2) {
        if(!list1.isEmpty() && !list2.isEmpty()) {
            View view0 = (View)list1.get(0);
            Object object0 = list2.get(0);
            view0.setTag(id.mtrl_motion_snapshot_view, object0);
        }
        if(!this.entering && !list1.isEmpty() && this.returnEndBounds != null) {
            View view1 = (View)list1.get(0);
            view1.measure(View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.width(), 0x40000000), View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.height(), 0x40000000));
            view1.layout(this.returnEndBounds.left, this.returnEndBounds.top, this.returnEndBounds.right, this.returnEndBounds.bottom);
        }
    }

    private static void removeWindowBackground(Window window0) {
        Drawable drawable0 = MaterialContainerTransformSharedElementCallback.getWindowBackground(window0);
        if(drawable0 == null) {
            return;
        }
        drawable0.mutate().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(0, BlendModeCompat.CLEAR));
    }

    private static void restoreWindowBackground(Window window0) {
        Drawable drawable0 = MaterialContainerTransformSharedElementCallback.getWindowBackground(window0);
        if(drawable0 == null) {
            return;
        }
        drawable0.mutate().clearColorFilter();
    }

    public void setShapeProvider(ShapeProvider materialContainerTransformSharedElementCallback$ShapeProvider0) {
        this.shapeProvider = materialContainerTransformSharedElementCallback$ShapeProvider0;
    }

    public void setSharedElementReenterTransitionEnabled(boolean z) {
        this.sharedElementReenterTransitionEnabled = z;
    }

    public void setTransparentWindowBackgroundEnabled(boolean z) {
        this.transparentWindowBackgroundEnabled = z;
    }

    private void setUpEnterTransform(Window window0) {
        Transition transition0 = window0.getSharedElementEnterTransition();
        if(transition0 instanceof MaterialContainerTransform) {
            if(!this.sharedElementReenterTransitionEnabled) {
                window0.setSharedElementReenterTransition(null);
            }
            if(this.transparentWindowBackgroundEnabled) {
                MaterialContainerTransformSharedElementCallback.updateBackgroundFadeDuration(window0, ((MaterialContainerTransform)transition0));
                ((MaterialContainerTransform)transition0).addListener(new TransitionListenerAdapter() {
                    @Override  // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionEnd(Transition transition0) {
                        MaterialContainerTransformSharedElementCallback.restoreWindowBackground(window0);
                    }

                    @Override  // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionStart(Transition transition0) {
                        MaterialContainerTransformSharedElementCallback.removeWindowBackground(window0);
                    }
                });
            }
        }
    }

    private void setUpReturnTransform(Activity activity0, Window window0) {
        Transition transition0 = window0.getSharedElementReturnTransition();
        if(transition0 instanceof MaterialContainerTransform) {
            ((MaterialContainerTransform)transition0).setHoldAtEndEnabled(true);
            ((MaterialContainerTransform)transition0).addListener(new TransitionListenerAdapter() {
                @Override  // com.google.android.material.transition.platform.TransitionListenerAdapter
                public void onTransitionEnd(Transition transition0) {
                    if(MaterialContainerTransformSharedElementCallback.capturedSharedElement != null) {
                        View view0 = (View)MaterialContainerTransformSharedElementCallback.capturedSharedElement.get();
                        if(view0 != null) {
                            view0.setAlpha(1.0f);
                            MaterialContainerTransformSharedElementCallback.capturedSharedElement = null;
                        }
                    }
                    activity0.finish();
                    activity0.overridePendingTransition(0, 0);
                }
            });
            if(this.transparentWindowBackgroundEnabled) {
                MaterialContainerTransformSharedElementCallback.updateBackgroundFadeDuration(window0, ((MaterialContainerTransform)transition0));
                ((MaterialContainerTransform)transition0).addListener(new TransitionListenerAdapter() {
                    @Override  // com.google.android.material.transition.platform.TransitionListenerAdapter
                    public void onTransitionStart(Transition transition0) {
                        MaterialContainerTransformSharedElementCallback.removeWindowBackground(window0);
                    }
                });
            }
        }
    }

    private static void updateBackgroundFadeDuration(Window window0, MaterialContainerTransform materialContainerTransform0) {
        if(materialContainerTransform0.getDuration() >= 0L) {
            window0.setTransitionBackgroundFadeDuration(materialContainerTransform0.getDuration());
        }
    }
}

