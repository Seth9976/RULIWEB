package androidx.core.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets.Builder;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class WindowInsetsCompat {
    static class Api21ReflectionHolder {
        private static Field sContentInsets;
        private static boolean sReflectionSucceeded;
        private static Field sStableInsets;
        private static Field sViewAttachInfoField;

        static {
            try {
                Field field0 = View.class.getDeclaredField("mAttachInfo");
                Api21ReflectionHolder.sViewAttachInfoField = field0;
                field0.setAccessible(true);
                Class class0 = Class.forName("android.view.View$AttachInfo");
                Field field1 = class0.getDeclaredField("mStableInsets");
                Api21ReflectionHolder.sStableInsets = field1;
                field1.setAccessible(true);
                Field field2 = class0.getDeclaredField("mContentInsets");
                Api21ReflectionHolder.sContentInsets = field2;
                field2.setAccessible(true);
                Api21ReflectionHolder.sReflectionSucceeded = true;
            }
            catch(ReflectiveOperationException reflectiveOperationException0) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + reflectiveOperationException0.getMessage(), reflectiveOperationException0);
            }
        }

        public static WindowInsetsCompat getRootWindowInsets(View view0) {
            if(Api21ReflectionHolder.sReflectionSucceeded && view0.isAttachedToWindow()) {
                View view1 = view0.getRootView();
                try {
                    Object object0 = Api21ReflectionHolder.sViewAttachInfoField.get(view1);
                    if(object0 != null) {
                        Rect rect0 = (Rect)Api21ReflectionHolder.sStableInsets.get(object0);
                        Rect rect1 = (Rect)Api21ReflectionHolder.sContentInsets.get(object0);
                        if(rect0 != null && rect1 != null) {
                            WindowInsetsCompat windowInsetsCompat0 = new Builder().setStableInsets(Insets.of(rect0)).setSystemWindowInsets(Insets.of(rect1)).build();
                            windowInsetsCompat0.setRootWindowInsets(windowInsetsCompat0);
                            windowInsetsCompat0.copyRootViewBounds(view0.getRootView());
                            return windowInsetsCompat0;
                        }
                    }
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + illegalAccessException0.getMessage(), illegalAccessException0);
                }
            }
            return null;
        }
    }

    public static final class Builder {
        private final BuilderImpl mImpl;

        public Builder() {
            if(Build.VERSION.SDK_INT >= 34) {
                this.mImpl = new BuilderImpl34();
                return;
            }
            if(Build.VERSION.SDK_INT >= 30) {
                this.mImpl = new BuilderImpl30();
                return;
            }
            if(Build.VERSION.SDK_INT >= 29) {
                this.mImpl = new BuilderImpl29();
                return;
            }
            this.mImpl = new BuilderImpl20();
        }

        public Builder(WindowInsetsCompat windowInsetsCompat0) {
            if(Build.VERSION.SDK_INT >= 34) {
                this.mImpl = new BuilderImpl34(windowInsetsCompat0);
                return;
            }
            if(Build.VERSION.SDK_INT >= 30) {
                this.mImpl = new BuilderImpl30(windowInsetsCompat0);
                return;
            }
            if(Build.VERSION.SDK_INT >= 29) {
                this.mImpl = new BuilderImpl29(windowInsetsCompat0);
                return;
            }
            this.mImpl = new BuilderImpl20(windowInsetsCompat0);
        }

        public WindowInsetsCompat build() {
            return this.mImpl.build();
        }

        public Builder setDisplayCutout(DisplayCutoutCompat displayCutoutCompat0) {
            this.mImpl.setDisplayCutout(displayCutoutCompat0);
            return this;
        }

        public Builder setInsets(int v, Insets insets0) {
            this.mImpl.setInsets(v, insets0);
            return this;
        }

        public Builder setInsetsIgnoringVisibility(int v, Insets insets0) {
            this.mImpl.setInsetsIgnoringVisibility(v, insets0);
            return this;
        }

        @Deprecated
        public Builder setMandatorySystemGestureInsets(Insets insets0) {
            this.mImpl.setMandatorySystemGestureInsets(insets0);
            return this;
        }

        @Deprecated
        public Builder setStableInsets(Insets insets0) {
            this.mImpl.setStableInsets(insets0);
            return this;
        }

        @Deprecated
        public Builder setSystemGestureInsets(Insets insets0) {
            this.mImpl.setSystemGestureInsets(insets0);
            return this;
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets insets0) {
            this.mImpl.setSystemWindowInsets(insets0);
            return this;
        }

        @Deprecated
        public Builder setTappableElementInsets(Insets insets0) {
            this.mImpl.setTappableElementInsets(insets0);
            return this;
        }

        public Builder setVisible(int v, boolean z) {
            this.mImpl.setVisible(v, z);
            return this;
        }
    }

    static class BuilderImpl20 extends BuilderImpl {
        private WindowInsets mPlatformInsets;
        private Insets mStableInsets;
        private static Constructor sConstructor = null;
        private static boolean sConstructorFetched = false;
        private static Field sConsumedField = null;
        private static boolean sConsumedFieldFetched = false;

        static {
        }

        BuilderImpl20() {
            this.mPlatformInsets = BuilderImpl20.createWindowInsetsInstance();
        }

        BuilderImpl20(WindowInsetsCompat windowInsetsCompat0) {
            super(windowInsetsCompat0);
            this.mPlatformInsets = windowInsetsCompat0.toWindowInsets();
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        WindowInsetsCompat build() {
            this.applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
            windowInsetsCompat0.setOverriddenInsets(this.mInsetsTypeMask);
            windowInsetsCompat0.setStableInsets(this.mStableInsets);
            return windowInsetsCompat0;
        }

        private static WindowInsets createWindowInsetsInstance() {
            if(!BuilderImpl20.sConsumedFieldFetched) {
                try {
                    BuilderImpl20.sConsumedField = WindowInsets.class.getDeclaredField("CONSUMED");
                }
                catch(ReflectiveOperationException reflectiveOperationException0) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", reflectiveOperationException0);
                }
                BuilderImpl20.sConsumedFieldFetched = true;
            }
            Field field0 = BuilderImpl20.sConsumedField;
            if(field0 != null) {
                try {
                    WindowInsets windowInsets0 = (WindowInsets)field0.get(null);
                    if(windowInsets0 != null) {
                        return new WindowInsets(windowInsets0);
                    }
                }
                catch(ReflectiveOperationException reflectiveOperationException1) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", reflectiveOperationException1);
                }
            }
            if(!BuilderImpl20.sConstructorFetched) {
                try {
                    BuilderImpl20.sConstructor = WindowInsets.class.getConstructor(Rect.class);
                }
                catch(ReflectiveOperationException reflectiveOperationException2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", reflectiveOperationException2);
                }
                BuilderImpl20.sConstructorFetched = true;
            }
            Constructor constructor0 = BuilderImpl20.sConstructor;
            if(constructor0 != null) {
                try {
                    return (WindowInsets)constructor0.newInstance(new Rect());
                }
                catch(ReflectiveOperationException reflectiveOperationException3) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", reflectiveOperationException3);
                }
            }
            return null;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setStableInsets(Insets insets0) {
            this.mStableInsets = insets0;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setSystemWindowInsets(Insets insets0) {
            WindowInsets windowInsets0 = this.mPlatformInsets;
            if(windowInsets0 != null) {
                this.mPlatformInsets = windowInsets0.replaceSystemWindowInsets(insets0.left, insets0.top, insets0.right, insets0.bottom);
            }
        }
    }

    static class BuilderImpl29 extends BuilderImpl {
        final WindowInsets.Builder mPlatBuilder;

        BuilderImpl29() {
            this.mPlatBuilder = WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m();
        }

        BuilderImpl29(WindowInsetsCompat windowInsetsCompat0) {
            super(windowInsetsCompat0);
            WindowInsets windowInsets0 = windowInsetsCompat0.toWindowInsets();
            this.mPlatBuilder = windowInsets0 == null ? WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m() : WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m(windowInsets0);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        WindowInsetsCompat build() {
            this.applyInsetTypes();
            WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
            windowInsetsCompat0.setOverriddenInsets(this.mInsetsTypeMask);
            return windowInsetsCompat0;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setDisplayCutout(DisplayCutoutCompat displayCutoutCompat0) {
            this.mPlatBuilder.setDisplayCutout((displayCutoutCompat0 == null ? null : displayCutoutCompat0.unwrap()));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setMandatorySystemGestureInsets(Insets insets0) {
            android.graphics.Insets insets1 = insets0.toPlatformInsets();
            this.mPlatBuilder.setMandatorySystemGestureInsets(insets1);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setStableInsets(Insets insets0) {
            android.graphics.Insets insets1 = insets0.toPlatformInsets();
            this.mPlatBuilder.setStableInsets(insets1);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setSystemGestureInsets(Insets insets0) {
            android.graphics.Insets insets1 = insets0.toPlatformInsets();
            this.mPlatBuilder.setSystemGestureInsets(insets1);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setSystemWindowInsets(Insets insets0) {
            android.graphics.Insets insets1 = insets0.toPlatformInsets();
            this.mPlatBuilder.setSystemWindowInsets(insets1);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setTappableElementInsets(Insets insets0) {
            android.graphics.Insets insets1 = insets0.toPlatformInsets();
            this.mPlatBuilder.setTappableElementInsets(insets1);
        }
    }

    static class BuilderImpl30 extends BuilderImpl29 {
        BuilderImpl30() {
        }

        BuilderImpl30(WindowInsetsCompat windowInsetsCompat0) {
            super(windowInsetsCompat0);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setInsets(int v, Insets insets0) {
            this.mPlatBuilder.setInsets(TypeImpl30.toPlatformType(v), insets0.toPlatformInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setInsetsIgnoringVisibility(int v, Insets insets0) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl30.toPlatformType(v), insets0.toPlatformInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl
        void setVisible(int v, boolean z) {
            this.mPlatBuilder.setVisible(TypeImpl30.toPlatformType(v), z);
        }
    }

    static class BuilderImpl34 extends BuilderImpl30 {
        BuilderImpl34() {
        }

        BuilderImpl34(WindowInsetsCompat windowInsetsCompat0) {
            super(windowInsetsCompat0);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl30
        void setInsets(int v, Insets insets0) {
            this.mPlatBuilder.setInsets(TypeImpl34.toPlatformType(v), insets0.toPlatformInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl30
        void setInsetsIgnoringVisibility(int v, Insets insets0) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl34.toPlatformType(v), insets0.toPlatformInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$BuilderImpl30
        void setVisible(int v, boolean z) {
            this.mPlatBuilder.setVisible(TypeImpl34.toPlatformType(v), z);
        }
    }

    static class BuilderImpl {
        private final WindowInsetsCompat mInsets;
        Insets[] mInsetsTypeMask;

        BuilderImpl() {
            this(new WindowInsetsCompat(null));
        }

        BuilderImpl(WindowInsetsCompat windowInsetsCompat0) {
            this.mInsets = windowInsetsCompat0;
        }

        protected final void applyInsetTypes() {
            Insets[] arr_insets = this.mInsetsTypeMask;
            if(arr_insets != null) {
                Insets insets0 = arr_insets[Type.indexOf(1)];
                Insets[] arr_insets1 = this.mInsetsTypeMask;
                Insets insets1 = arr_insets1[Type.indexOf(2)];
                if(insets1 == null) {
                    insets1 = this.mInsets.getInsets(2);
                }
                if(insets0 == null) {
                    insets0 = this.mInsets.getInsets(1);
                }
                this.setSystemWindowInsets(Insets.max(insets0, insets1));
                Insets[] arr_insets2 = this.mInsetsTypeMask;
                Insets insets2 = arr_insets2[Type.indexOf(16)];
                if(insets2 != null) {
                    this.setSystemGestureInsets(insets2);
                }
                Insets[] arr_insets3 = this.mInsetsTypeMask;
                Insets insets3 = arr_insets3[Type.indexOf(0x20)];
                if(insets3 != null) {
                    this.setMandatorySystemGestureInsets(insets3);
                }
                Insets[] arr_insets4 = this.mInsetsTypeMask;
                Insets insets4 = arr_insets4[Type.indexOf(0x40)];
                if(insets4 != null) {
                    this.setTappableElementInsets(insets4);
                }
            }
        }

        WindowInsetsCompat build() {
            this.applyInsetTypes();
            return this.mInsets;
        }

        void setDisplayCutout(DisplayCutoutCompat displayCutoutCompat0) {
        }

        void setInsets(int v, Insets insets0) {
            if(this.mInsetsTypeMask == null) {
                this.mInsetsTypeMask = new Insets[10];
            }
            for(int v1 = 1; v1 <= 0x200; v1 <<= 1) {
                if((v & v1) != 0) {
                    Insets[] arr_insets = this.mInsetsTypeMask;
                    arr_insets[Type.indexOf(v1)] = insets0;
                }
            }
        }

        void setInsetsIgnoringVisibility(int v, Insets insets0) {
            if(v == 8) {
                throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
            }
        }

        void setMandatorySystemGestureInsets(Insets insets0) {
        }

        void setStableInsets(Insets insets0) {
        }

        void setSystemGestureInsets(Insets insets0) {
        }

        void setSystemWindowInsets(Insets insets0) {
        }

        void setTappableElementInsets(Insets insets0) {
        }

        void setVisible(int v, boolean z) {
        }
    }

    static class Impl20 extends Impl {
        private static final int SYSTEM_BAR_VISIBILITY_MASK = 6;
        private Insets[] mOverriddenInsets;
        final WindowInsets mPlatformInsets;
        Insets mRootViewVisibleInsets;
        private WindowInsetsCompat mRootWindowInsets;
        int mSystemUiVisibility;
        private Insets mSystemWindowInsets;
        private static Class sAttachInfoClass = null;
        private static Field sAttachInfoField = null;
        private static Method sGetViewRootImplMethod = null;
        private static Field sVisibleInsetsField = null;
        private static boolean sVisibleRectReflectionFetched = false;

        static {
        }

        Impl20(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0);
            this.mSystemWindowInsets = null;
            this.mPlatformInsets = windowInsets0;
        }

        Impl20(WindowInsetsCompat windowInsetsCompat0, Impl20 windowInsetsCompat$Impl200) {
            this(windowInsetsCompat0, new WindowInsets(windowInsetsCompat$Impl200.mPlatformInsets));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        void copyRootViewBounds(View view0) {
            Insets insets0 = this.getVisibleInsets(view0);
            if(insets0 == null) {
                insets0 = Insets.NONE;
            }
            this.setRootViewData(insets0);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat0) {
            windowInsetsCompat0.setRootWindowInsets(this.mRootWindowInsets);
            windowInsetsCompat0.setRootViewData(this.mRootViewVisibleInsets);
            windowInsetsCompat0.setSystemUiVisibility(this.mSystemUiVisibility);
        }

        // 去混淆评级： 低(30)
        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public boolean equals(Object object0) {
            return super.equals(object0) ? Objects.equals(this.mRootViewVisibleInsets, ((Impl20)object0).mRootViewVisibleInsets) && Impl20.systemBarVisibilityEquals(this.mSystemUiVisibility, ((Impl20)object0).mSystemUiVisibility) : false;
        }

        private Insets getInsets(int v, boolean z) {
            Insets insets0 = Insets.NONE;
            for(int v1 = 1; v1 <= 0x200; v1 <<= 1) {
                if((v & v1) != 0) {
                    insets0 = Insets.max(insets0, this.getInsetsForType(v1, z));
                }
            }
            return insets0;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public Insets getInsets(int v) {
            return this.getInsets(v, false);
        }

        protected Insets getInsetsForType(int v, boolean z) {
            Insets insets0 = null;
            switch(v) {
                case 1: {
                    if(z) {
                        Insets insets1 = this.getRootStableInsets();
                        Insets insets2 = this.getSystemWindowInsets();
                        return Insets.of(0, Math.max(insets1.top, insets2.top), 0, 0);
                    }
                    return (this.mSystemUiVisibility & 4) == 0 ? Insets.of(0, this.getSystemWindowInsets().top, 0, 0) : Insets.NONE;
                }
                case 2: {
                    if(z) {
                        Insets insets3 = this.getRootStableInsets();
                        Insets insets4 = this.getStableInsets();
                        return Insets.of(Math.max(insets3.left, insets4.left), 0, Math.max(insets3.right, insets4.right), Math.max(insets3.bottom, insets4.bottom));
                    }
                    if((this.mSystemUiVisibility & 2) != 0) {
                        return Insets.NONE;
                    }
                    Insets insets5 = this.getSystemWindowInsets();
                    WindowInsetsCompat windowInsetsCompat0 = this.mRootWindowInsets;
                    if(windowInsetsCompat0 != null) {
                        insets0 = windowInsetsCompat0.getStableInsets();
                    }
                    return Insets.of(insets5.left, 0, insets5.right, (insets0 == null ? insets5.bottom : Math.min(insets5.bottom, insets0.bottom)));
                }
                case 8: {
                    Insets[] arr_insets = this.mOverriddenInsets;
                    if(arr_insets != null) {
                        insets0 = arr_insets[Type.indexOf(8)];
                    }
                    if(insets0 != null) {
                        return insets0;
                    }
                    Insets insets6 = this.getSystemWindowInsets();
                    Insets insets7 = this.getRootStableInsets();
                    if(insets6.bottom > insets7.bottom) {
                        return Insets.of(0, 0, 0, insets6.bottom);
                    }
                    return this.mRootViewVisibleInsets == null || this.mRootViewVisibleInsets.equals(Insets.NONE) || this.mRootViewVisibleInsets.bottom <= insets7.bottom ? Insets.NONE : Insets.of(0, 0, 0, this.mRootViewVisibleInsets.bottom);
                }
                case 16: {
                    return this.getSystemGestureInsets();
                }
                case 0x20: {
                    return this.getMandatorySystemGestureInsets();
                }
                case 0x40: {
                    return this.getTappableElementInsets();
                }
                case 0x80: {
                    WindowInsetsCompat windowInsetsCompat1 = this.mRootWindowInsets;
                    DisplayCutoutCompat displayCutoutCompat0 = windowInsetsCompat1 == null ? this.getDisplayCutout() : windowInsetsCompat1.getDisplayCutout();
                    return displayCutoutCompat0 == null ? Insets.NONE : Insets.of(displayCutoutCompat0.getSafeInsetLeft(), displayCutoutCompat0.getSafeInsetTop(), displayCutoutCompat0.getSafeInsetRight(), displayCutoutCompat0.getSafeInsetBottom());
                }
                default: {
                    return Insets.NONE;
                }
            }
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public Insets getInsetsIgnoringVisibility(int v) {
            return this.getInsets(v, true);
        }

        private Insets getRootStableInsets() {
            return this.mRootWindowInsets == null ? Insets.NONE : this.mRootWindowInsets.getStableInsets();
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        final Insets getSystemWindowInsets() {
            if(this.mSystemWindowInsets == null) {
                this.mSystemWindowInsets = Insets.of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
            }
            return this.mSystemWindowInsets;
        }

        private Insets getVisibleInsets(View view0) {
            if(Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if(!Impl20.sVisibleRectReflectionFetched) {
                Impl20.loadReflectionField();
            }
            Method method0 = Impl20.sGetViewRootImplMethod;
            if(method0 != null && Impl20.sAttachInfoClass != null && Impl20.sVisibleInsetsField != null) {
                try {
                    Object object0 = method0.invoke(view0, null);
                    if(object0 == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Object object1 = Impl20.sAttachInfoField.get(object0);
                    Rect rect0 = (Rect)Impl20.sVisibleInsetsField.get(object1);
                    return rect0 == null ? null : Insets.of(rect0);
                }
                catch(ReflectiveOperationException reflectiveOperationException0) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + reflectiveOperationException0.getMessage(), reflectiveOperationException0);
                }
            }
            return null;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        WindowInsetsCompat inset(int v, int v1, int v2, int v3) {
            Builder windowInsetsCompat$Builder0 = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets));
            windowInsetsCompat$Builder0.setSystemWindowInsets(WindowInsetsCompat.insetInsets(this.getSystemWindowInsets(), v, v1, v2, v3));
            windowInsetsCompat$Builder0.setStableInsets(WindowInsetsCompat.insetInsets(this.getStableInsets(), v, v1, v2, v3));
            return windowInsetsCompat$Builder0.build();
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        boolean isRound() {
            return this.mPlatformInsets.isRound();
        }

        protected boolean isTypeVisible(int v) {
            switch(v) {
                case 4: {
                    return false;
                }
                case 1: 
                case 2: 
                case 8: 
                case 0x80: {
                    return !this.getInsetsForType(v, false).equals(Insets.NONE);
                }
                default: {
                    return true;
                }
            }
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        boolean isVisible(int v) {
            for(int v1 = 1; v1 <= 0x200; v1 <<= 1) {
                if((v & v1) != 0 && !this.isTypeVisible(v1)) {
                    return false;
                }
            }
            return true;
        }

        private static void loadReflectionField() {
            try {
                Impl20.sGetViewRootImplMethod = View.class.getDeclaredMethod("getViewRootImpl", null);
                Class class0 = Class.forName("android.view.View$AttachInfo");
                Impl20.sAttachInfoClass = class0;
                Impl20.sVisibleInsetsField = class0.getDeclaredField("mVisibleInsets");
                Impl20.sAttachInfoField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
                Impl20.sVisibleInsetsField.setAccessible(true);
                Impl20.sAttachInfoField.setAccessible(true);
            }
            catch(ReflectiveOperationException reflectiveOperationException0) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + reflectiveOperationException0.getMessage(), reflectiveOperationException0);
            }
            Impl20.sVisibleRectReflectionFetched = true;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public void setOverriddenInsets(Insets[] arr_insets) {
            this.mOverriddenInsets = arr_insets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        void setRootViewData(Insets insets0) {
            this.mRootViewVisibleInsets = insets0;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat0) {
            this.mRootWindowInsets = windowInsetsCompat0;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        void setSystemUiVisibility(int v) {
            this.mSystemUiVisibility = v;
        }

        static boolean systemBarVisibilityEquals(int v, int v1) {
            return (v & 6) == (v1 & 6);
        }
    }

    static class Impl21 extends Impl20 {
        private Insets mStableInsets;

        Impl21(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0, windowInsets0);
            this.mStableInsets = null;
        }

        Impl21(WindowInsetsCompat windowInsetsCompat0, Impl21 windowInsetsCompat$Impl210) {
            super(windowInsetsCompat0, windowInsetsCompat$Impl210);
            this.mStableInsets = windowInsetsCompat$Impl210.mStableInsets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        WindowInsetsCompat consumeStableInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        WindowInsetsCompat consumeSystemWindowInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        final Insets getStableInsets() {
            if(this.mStableInsets == null) {
                this.mStableInsets = Insets.of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
            }
            return this.mStableInsets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        boolean isConsumed() {
            return this.mPlatformInsets.isConsumed();
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public void setStableInsets(Insets insets0) {
            this.mStableInsets = insets0;
        }
    }

    static class Impl28 extends Impl21 {
        Impl28(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0, windowInsets0);
        }

        Impl28(WindowInsetsCompat windowInsetsCompat0, Impl28 windowInsetsCompat$Impl280) {
            super(windowInsetsCompat0, windowInsetsCompat$Impl280);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        WindowInsetsCompat consumeDisplayCutout() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof Impl28 ? Objects.equals(this.mPlatformInsets, ((Impl28)object0).mPlatformInsets) && Objects.equals(this.mRootViewVisibleInsets, ((Impl28)object0).mRootViewVisibleInsets) && Impl28.systemBarVisibilityEquals(this.mSystemUiVisibility, ((Impl28)object0).mSystemUiVisibility) : false;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        DisplayCutoutCompat getDisplayCutout() {
            return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        public int hashCode() {
            return this.mPlatformInsets.hashCode();
        }
    }

    static class Impl29 extends Impl28 {
        private Insets mMandatorySystemGestureInsets;
        private Insets mSystemGestureInsets;
        private Insets mTappableElementInsets;

        Impl29(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0, windowInsets0);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }

        Impl29(WindowInsetsCompat windowInsetsCompat0, Impl29 windowInsetsCompat$Impl290) {
            super(windowInsetsCompat0, windowInsetsCompat$Impl290);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        Insets getMandatorySystemGestureInsets() {
            if(this.mMandatorySystemGestureInsets == null) {
                this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
            }
            return this.mMandatorySystemGestureInsets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        Insets getSystemGestureInsets() {
            if(this.mSystemGestureInsets == null) {
                this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
            }
            return this.mSystemGestureInsets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl
        Insets getTappableElementInsets() {
            if(this.mTappableElementInsets == null) {
                this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
            }
            return this.mTappableElementInsets;
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        WindowInsetsCompat inset(int v, int v1, int v2, int v3) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(v, v1, v2, v3));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl21
        public void setStableInsets(Insets insets0) {
        }
    }

    static class Impl30 extends Impl29 {
        static final WindowInsetsCompat CONSUMED;

        static {
            Impl30.CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);
        }

        Impl30(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0, windowInsets0);
        }

        Impl30(WindowInsetsCompat windowInsetsCompat0, Impl30 windowInsetsCompat$Impl300) {
            super(windowInsetsCompat0, windowInsetsCompat$Impl300);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        final void copyRootViewBounds(View view0) {
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        public Insets getInsets(int v) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl30.toPlatformType(v)));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        public Insets getInsetsIgnoringVisibility(int v) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl30.toPlatformType(v)));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl20
        public boolean isVisible(int v) {
            return this.mPlatformInsets.isVisible(TypeImpl30.toPlatformType(v));
        }
    }

    static class Impl34 extends Impl30 {
        static final WindowInsetsCompat CONSUMED;

        static {
            Impl34.CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);
        }

        Impl34(WindowInsetsCompat windowInsetsCompat0, WindowInsets windowInsets0) {
            super(windowInsetsCompat0, windowInsets0);
        }

        Impl34(WindowInsetsCompat windowInsetsCompat0, Impl34 windowInsetsCompat$Impl340) {
            super(windowInsetsCompat0, windowInsetsCompat$Impl340);
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl30
        public Insets getInsets(int v) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl34.toPlatformType(v)));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl30
        public Insets getInsetsIgnoringVisibility(int v) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl34.toPlatformType(v)));
        }

        @Override  // androidx.core.view.WindowInsetsCompat$Impl30
        public boolean isVisible(int v) {
            return this.mPlatformInsets.isVisible(TypeImpl34.toPlatformType(v));
        }
    }

    static class Impl {
        static final WindowInsetsCompat CONSUMED;
        final WindowInsetsCompat mHost;

        static {
            Impl.CONSUMED = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        }

        Impl(WindowInsetsCompat windowInsetsCompat0) {
            this.mHost = windowInsetsCompat0;
        }

        WindowInsetsCompat consumeDisplayCutout() {
            return this.mHost;
        }

        WindowInsetsCompat consumeStableInsets() {
            return this.mHost;
        }

        WindowInsetsCompat consumeSystemWindowInsets() {
            return this.mHost;
        }

        void copyRootViewBounds(View view0) {
        }

        void copyWindowDataInto(WindowInsetsCompat windowInsetsCompat0) {
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof Impl ? this.isRound() == ((Impl)object0).isRound() && this.isConsumed() == ((Impl)object0).isConsumed() && ObjectsCompat.equals(this.getSystemWindowInsets(), ((Impl)object0).getSystemWindowInsets()) && ObjectsCompat.equals(this.getStableInsets(), ((Impl)object0).getStableInsets()) && ObjectsCompat.equals(this.getDisplayCutout(), ((Impl)object0).getDisplayCutout()) : false;
        }

        DisplayCutoutCompat getDisplayCutout() {
            return null;
        }

        Insets getInsets(int v) {
            return Insets.NONE;
        }

        Insets getInsetsIgnoringVisibility(int v) {
            if((v & 8) != 0) {
                throw new IllegalArgumentException("Unable to query the maximum insets for IME");
            }
            return Insets.NONE;
        }

        Insets getMandatorySystemGestureInsets() {
            return this.getSystemWindowInsets();
        }

        Insets getStableInsets() {
            return Insets.NONE;
        }

        Insets getSystemGestureInsets() {
            return this.getSystemWindowInsets();
        }

        Insets getSystemWindowInsets() {
            return Insets.NONE;
        }

        Insets getTappableElementInsets() {
            return this.getSystemWindowInsets();
        }

        @Override
        public int hashCode() {
            Insets insets0 = this.getSystemWindowInsets();
            Insets insets1 = this.getStableInsets();
            return ObjectsCompat.hash(new Object[]{Boolean.valueOf(this.isRound()), Boolean.valueOf(this.isConsumed()), insets0, insets1, this.getDisplayCutout()});
        }

        WindowInsetsCompat inset(int v, int v1, int v2, int v3) {
            return Impl.CONSUMED;
        }

        boolean isConsumed() {
            return false;
        }

        boolean isRound() {
            return false;
        }

        boolean isVisible(int v) {
            return true;
        }

        public void setOverriddenInsets(Insets[] arr_insets) {
        }

        void setRootViewData(Insets insets0) {
        }

        void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat0) {
        }

        public void setStableInsets(Insets insets0) {
        }

        void setSystemUiVisibility(int v) {
        }
    }

    public static final class Side {
        @Retention(RetentionPolicy.SOURCE)
        public @interface InsetsSide {
        }

        public static final int BOTTOM = 8;
        public static final int LEFT = 1;
        public static final int RIGHT = 4;
        public static final int TOP = 2;

        public static int all() {
            return 15;
        }
    }

    public static final class Type {
        @Retention(RetentionPolicy.SOURCE)
        public @interface InsetsType {
        }

        static final int CAPTION_BAR = 4;
        static final int DISPLAY_CUTOUT = 0x80;
        static final int FIRST = 1;
        static final int IME = 8;
        static final int LAST = 0x200;
        static final int MANDATORY_SYSTEM_GESTURES = 0x20;
        static final int NAVIGATION_BARS = 2;
        static final int SIZE = 10;
        static final int STATUS_BARS = 1;
        static final int SYSTEM_GESTURES = 16;
        static final int SYSTEM_OVERLAYS = 0x200;
        static final int TAPPABLE_ELEMENT = 0x40;
        static final int WINDOW_DECOR = 0x100;

        static int all() [...] // Inlined contents

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 0x80;
        }

        public static int ime() [...] // Inlined contents

        static int indexOf(int v) {
            switch(v) {
                case 1: {
                    return 0;
                }
                case 2: {
                    return 1;
                }
                case 4: {
                    return 2;
                }
                case 8: {
                    return 3;
                }
                case 16: {
                    return 4;
                }
                case 0x20: {
                    return 5;
                }
                case 0x40: {
                    return 6;
                }
                case 0x80: {
                    return 7;
                }
                case 0x100: {
                    return 8;
                }
                case 0x200: {
                    return 9;
                }
                default: {
                    throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + v);
                }
            }
        }

        public static int mandatorySystemGestures() [...] // Inlined contents

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() [...] // Inlined contents

        public static int systemGestures() {
            return 16;
        }

        public static int systemOverlays() {
            return 0x200;
        }

        public static int tappableElement() [...] // Inlined contents
    }

    static final class TypeImpl30 {
        static int toPlatformType(int v) {
            int v1 = 0;
            for(int v2 = 1; v2 <= 0x200; v2 <<= 1) {
                if((v & v2) != 0) {
                    switch(v2) {
                        case 1: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m();
                            break;
                        }
                        case 2: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$1();
                            break;
                        }
                        case 4: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$2();
                            break;
                        }
                        case 8: {
                            v1 |= HalfKt..ExternalSyntheticApiModelOutline0.m();
                            break;
                        }
                        case 16: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$3();
                            break;
                        }
                        case 0x20: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$4();
                            break;
                        }
                        case 0x40: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$5();
                            break;
                        }
                        case 0x80: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$6();
                        }
                    }
                }
            }
            return v1;
        }
    }

    static final class TypeImpl34 {
        static int toPlatformType(int v) {
            int v1 = 0;
            for(int v2 = 1; v2 <= 0x200; v2 <<= 1) {
                if((v & v2) != 0) {
                    switch(v2) {
                        case 1: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m();
                            break;
                        }
                        case 2: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$1();
                            break;
                        }
                        case 4: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$2();
                            break;
                        }
                        case 8: {
                            v1 |= HalfKt..ExternalSyntheticApiModelOutline0.m();
                            break;
                        }
                        case 16: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$3();
                            break;
                        }
                        case 0x20: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$4();
                            break;
                        }
                        case 0x40: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$5();
                            break;
                        }
                        case 0x80: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$6();
                            break;
                        }
                        case 0x200: {
                            v1 |= WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m$7();
                        }
                    }
                }
            }
            return v1;
        }
    }

    public static final WindowInsetsCompat CONSUMED = null;
    private static final String TAG = "WindowInsetsCompat";
    private final Impl mImpl;

    static {
        if(Build.VERSION.SDK_INT >= 34) {
            WindowInsetsCompat.CONSUMED = Impl34.CONSUMED;
            return;
        }
        if(Build.VERSION.SDK_INT >= 30) {
            WindowInsetsCompat.CONSUMED = Impl30.CONSUMED;
            return;
        }
        WindowInsetsCompat.CONSUMED = Impl.CONSUMED;
    }

    private WindowInsetsCompat(WindowInsets windowInsets0) {
        if(Build.VERSION.SDK_INT >= 34) {
            this.mImpl = new Impl34(this, windowInsets0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(this, windowInsets0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            this.mImpl = new Impl29(this, windowInsets0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new Impl28(this, windowInsets0);
            return;
        }
        this.mImpl = new Impl21(this, windowInsets0);
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat0) {
        if(windowInsetsCompat0 != null) {
            Impl windowInsetsCompat$Impl0 = windowInsetsCompat0.mImpl;
            if(Build.VERSION.SDK_INT >= 34 && windowInsetsCompat$Impl0 instanceof Impl34) {
                this.mImpl = new Impl34(this, ((Impl34)windowInsetsCompat$Impl0));
            }
            else if(Build.VERSION.SDK_INT >= 30 && windowInsetsCompat$Impl0 instanceof Impl30) {
                this.mImpl = new Impl30(this, ((Impl30)windowInsetsCompat$Impl0));
            }
            else if(Build.VERSION.SDK_INT >= 29 && windowInsetsCompat$Impl0 instanceof Impl29) {
                this.mImpl = new Impl29(this, ((Impl29)windowInsetsCompat$Impl0));
            }
            else if(Build.VERSION.SDK_INT >= 28 && windowInsetsCompat$Impl0 instanceof Impl28) {
                this.mImpl = new Impl28(this, ((Impl28)windowInsetsCompat$Impl0));
            }
            else if(windowInsetsCompat$Impl0 instanceof Impl21) {
                this.mImpl = new Impl21(this, ((Impl21)windowInsetsCompat$Impl0));
            }
            else if(windowInsetsCompat$Impl0 instanceof Impl20) {
                this.mImpl = new Impl20(this, ((Impl20)windowInsetsCompat$Impl0));
            }
            else {
                this.mImpl = new Impl(this);
            }
            windowInsetsCompat$Impl0.copyWindowDataInto(this);
            return;
        }
        this.mImpl = new Impl(this);
    }

    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.mImpl.consumeDisplayCutout();
    }

    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.mImpl.consumeStableInsets();
    }

    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.mImpl.consumeSystemWindowInsets();
    }

    void copyRootViewBounds(View view0) {
        this.mImpl.copyRootViewBounds(view0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof WindowInsetsCompat ? ObjectsCompat.equals(this.mImpl, ((WindowInsetsCompat)object0).mImpl) : false;
    }

    public DisplayCutoutCompat getDisplayCutout() {
        return this.mImpl.getDisplayCutout();
    }

    public Insets getInsets(int v) {
        return this.mImpl.getInsets(v);
    }

    public Insets getInsetsIgnoringVisibility(int v) {
        return this.mImpl.getInsetsIgnoringVisibility(v);
    }

    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return this.mImpl.getMandatorySystemGestureInsets();
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.mImpl.getStableInsets().bottom;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.mImpl.getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.mImpl.getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.mImpl.getStableInsets().top;
    }

    @Deprecated
    public Insets getStableInsets() {
        return this.mImpl.getStableInsets();
    }

    @Deprecated
    public Insets getSystemGestureInsets() {
        return this.mImpl.getSystemGestureInsets();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.mImpl.getSystemWindowInsets().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.mImpl.getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.mImpl.getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.mImpl.getSystemWindowInsets().top;
    }

    @Deprecated
    public Insets getSystemWindowInsets() {
        return this.mImpl.getSystemWindowInsets();
    }

    @Deprecated
    public Insets getTappableElementInsets() {
        return this.mImpl.getTappableElementInsets();
    }

    // 去混淆评级： 低(20)
    public boolean hasInsets() {
        return !this.getInsets(-1).equals(Insets.NONE) || !this.getInsetsIgnoringVisibility(-9).equals(Insets.NONE) || this.getDisplayCutout() != null;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !this.mImpl.getStableInsets().equals(Insets.NONE);
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.mImpl.getSystemWindowInsets().equals(Insets.NONE);
    }

    @Override
    public int hashCode() {
        return this.mImpl == null ? 0 : this.mImpl.hashCode();
    }

    public WindowInsetsCompat inset(int v, int v1, int v2, int v3) {
        return this.mImpl.inset(v, v1, v2, v3);
    }

    public WindowInsetsCompat inset(Insets insets0) {
        return this.inset(insets0.left, insets0.top, insets0.right, insets0.bottom);
    }

    static Insets insetInsets(Insets insets0, int v, int v1, int v2, int v3) {
        int v4 = Math.max(0, insets0.left - v);
        int v5 = Math.max(0, insets0.top - v1);
        int v6 = Math.max(0, insets0.right - v2);
        int v7 = Math.max(0, insets0.bottom - v3);
        return v4 != v || v5 != v1 || v6 != v2 || v7 != v3 ? Insets.of(v4, v5, v6, v7) : insets0;
    }

    public boolean isConsumed() {
        return this.mImpl.isConsumed();
    }

    public boolean isRound() {
        return this.mImpl.isRound();
    }

    public boolean isVisible(int v) {
        return this.mImpl.isVisible(v);
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int v, int v1, int v2, int v3) {
        return new Builder(this).setSystemWindowInsets(Insets.of(v, v1, v2, v3)).build();
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect0) {
        return new Builder(this).setSystemWindowInsets(Insets.of(rect0)).build();
    }

    void setOverriddenInsets(Insets[] arr_insets) {
        this.mImpl.setOverriddenInsets(arr_insets);
    }

    void setRootViewData(Insets insets0) {
        this.mImpl.setRootViewData(insets0);
    }

    void setRootWindowInsets(WindowInsetsCompat windowInsetsCompat0) {
        this.mImpl.setRootWindowInsets(windowInsetsCompat0);
    }

    void setStableInsets(Insets insets0) {
        this.mImpl.setStableInsets(insets0);
    }

    void setSystemUiVisibility(int v) {
        this.mImpl.setSystemUiVisibility(v);
    }

    // 去混淆评级： 低(20)
    public WindowInsets toWindowInsets() {
        return this.mImpl instanceof Impl20 ? ((Impl20)this.mImpl).mPlatformInsets : null;
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets0) {
        return WindowInsetsCompat.toWindowInsetsCompat(windowInsets0, null);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets windowInsets0, View view0) {
        WindowInsetsCompat windowInsetsCompat0 = new WindowInsetsCompat(((WindowInsets)Preconditions.checkNotNull(windowInsets0)));
        if(view0 != null && view0.isAttachedToWindow()) {
            windowInsetsCompat0.setRootWindowInsets(ViewCompat.getRootWindowInsets(view0));
            windowInsetsCompat0.copyRootViewBounds(view0.getRootView());
            windowInsetsCompat0.setSystemUiVisibility(view0.getWindowSystemUiVisibility());
        }
        return windowInsetsCompat0;
    }
}

