package androidx.window.layout;

import android.graphics.Rect;
import androidx.window.core.Bounds;
import androidx.window.core.SpecificationComputer.VerificationMode;
import androidx.window.core.SpecificationComputer;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\b\u0010\t\u001A\u0004\u0018\u00010\bJ\u001C\u0010\n\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\u000B2\b\u0010\t\u001A\u0004\u0018\u00010\u000BH\u0002J(\u0010\f\u001A\u00020\u00062\u000E\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\r2\u000E\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\rH\u0002J\u001A\u0010\u000E\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\u000F2\b\u0010\t\u001A\u0004\u0018\u00010\u000FJ\u001F\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001A\u00020\u000B2\u0006\u0010\u0013\u001A\u00020\bH\u0000¢\u0006\u0002\b\u0014J\u0018\u0010\u0010\u001A\u00020\u00152\b\u0010\u0016\u001A\u0004\u0018\u00010\u000F2\u0006\u0010\u0017\u001A\u00020\bJ\"\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00110\r2\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u000B0\r2\u0006\u0010\u0013\u001A\u00020\bR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Landroidx/window/layout/SidecarAdapter;", "", "verificationMode", "Landroidx/window/core/SpecificationComputer$VerificationMode;", "(Landroidx/window/core/SpecificationComputer$VerificationMode;)V", "isEqualSidecarDeviceState", "", "first", "Landroidx/window/sidecar/SidecarDeviceState;", "second", "isEqualSidecarDisplayFeature", "Landroidx/window/sidecar/SidecarDisplayFeature;", "isEqualSidecarDisplayFeatures", "", "isEqualSidecarWindowLayoutInfo", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "translate", "Landroidx/window/layout/DisplayFeature;", "feature", "deviceState", "translate$window_release", "Landroidx/window/layout/WindowLayoutInfo;", "extensionInfo", "state", "sidecarDisplayFeatures", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SidecarAdapter {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0007J\u0015\u0010\n\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0000¢\u0006\u0002\b\u000BJ\u0016\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\u0006\u0010\u000F\u001A\u00020\u0010H\u0007J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u0013\u001A\u00020\u0007H\u0007J \u0010\u0014\u001A\u00020\u00122\u0006\u0010\u000F\u001A\u00020\u00102\u000E\u0010\u0015\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u000E0\rH\u0007R\u0016\u0010\u0003\u001A\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/window/layout/SidecarAdapter$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getRawSidecarDevicePosture", "", "sidecarDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "getSidecarDevicePosture", "getSidecarDevicePosture$window_release", "getSidecarDisplayFeatures", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "info", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "setSidecarDevicePosture", "", "posture", "setSidecarDisplayFeatures", "displayFeatures", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final int getRawSidecarDevicePosture(SidecarDeviceState sidecarDeviceState0) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState0, "sidecarDeviceState");
            return sidecarDeviceState0.posture;
        }

        public final int getSidecarDevicePosture$window_release(SidecarDeviceState sidecarDeviceState0) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState0, "sidecarDeviceState");
            int v = this.getRawSidecarDevicePosture(sidecarDeviceState0);
            return v < 0 || v > 4 ? 0 : v;
        }

        public final List getSidecarDisplayFeatures(SidecarWindowLayoutInfo sidecarWindowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(sidecarWindowLayoutInfo0, "info");
            try {
                return sidecarWindowLayoutInfo0.displayFeatures == null ? CollectionsKt.emptyList() : sidecarWindowLayoutInfo0.displayFeatures;
            }
            catch(NoSuchFieldError unused_ex) {
                try {
                    Object object0 = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", null).invoke(sidecarWindowLayoutInfo0, null);
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>");
                    }
                    return (List)object0;
                }
                catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException unused_ex) {
                    return CollectionsKt.emptyList();
                }
            }
        }

        public final void setSidecarDevicePosture(SidecarDeviceState sidecarDeviceState0, int v) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState0, "sidecarDeviceState");
            sidecarDeviceState0.posture = v;
        }

        public final void setSidecarDisplayFeatures(SidecarWindowLayoutInfo sidecarWindowLayoutInfo0, List list0) {
            Intrinsics.checkNotNullParameter(sidecarWindowLayoutInfo0, "info");
            Intrinsics.checkNotNullParameter(list0, "displayFeatures");
            sidecarWindowLayoutInfo0.displayFeatures = list0;
        }
    }

    public static final Companion Companion;
    private static final String TAG;
    private final VerificationMode verificationMode;

    static {
        SidecarAdapter.Companion = new Companion(null);
        SidecarAdapter.TAG = "SidecarAdapter";
    }

    public SidecarAdapter() {
        this(null, 1, null);
    }

    public SidecarAdapter(VerificationMode specificationComputer$VerificationMode0) {
        Intrinsics.checkNotNullParameter(specificationComputer$VerificationMode0, "verificationMode");
        super();
        this.verificationMode = specificationComputer$VerificationMode0;
    }

    public SidecarAdapter(VerificationMode specificationComputer$VerificationMode0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            specificationComputer$VerificationMode0 = VerificationMode.QUIET;
        }
        this(specificationComputer$VerificationMode0);
    }

    public final boolean isEqualSidecarDeviceState(SidecarDeviceState sidecarDeviceState0, SidecarDeviceState sidecarDeviceState1) {
        if(Intrinsics.areEqual(sidecarDeviceState0, sidecarDeviceState1)) {
            return true;
        }
        if(sidecarDeviceState0 == null) {
            return false;
        }
        return sidecarDeviceState1 == null ? false : SidecarAdapter.Companion.getSidecarDevicePosture$window_release(sidecarDeviceState0) == SidecarAdapter.Companion.getSidecarDevicePosture$window_release(sidecarDeviceState1);
    }

    private final boolean isEqualSidecarDisplayFeature(SidecarDisplayFeature sidecarDisplayFeature0, SidecarDisplayFeature sidecarDisplayFeature1) {
        if(Intrinsics.areEqual(sidecarDisplayFeature0, sidecarDisplayFeature1)) {
            return true;
        }
        if(sidecarDisplayFeature0 == null) {
            return false;
        }
        if(sidecarDisplayFeature1 == null) {
            return false;
        }
        return sidecarDisplayFeature0.getType() == sidecarDisplayFeature1.getType() ? Intrinsics.areEqual(sidecarDisplayFeature0.getRect(), sidecarDisplayFeature1.getRect()) : false;
    }

    private final boolean isEqualSidecarDisplayFeatures(List list0, List list1) {
        if(list0 == list1) {
            return true;
        }
        if(list0 == null) {
            return false;
        }
        if(list1 == null) {
            return false;
        }
        if(list0.size() != list1.size()) {
            return false;
        }
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!this.isEqualSidecarDisplayFeature(((SidecarDisplayFeature)list0.get(v1)), ((SidecarDisplayFeature)list1.get(v1)))) {
                return false;
            }
        }
        return true;
    }

    public final boolean isEqualSidecarWindowLayoutInfo(SidecarWindowLayoutInfo sidecarWindowLayoutInfo0, SidecarWindowLayoutInfo sidecarWindowLayoutInfo1) {
        if(Intrinsics.areEqual(sidecarWindowLayoutInfo0, sidecarWindowLayoutInfo1)) {
            return true;
        }
        if(sidecarWindowLayoutInfo0 == null) {
            return false;
        }
        return sidecarWindowLayoutInfo1 == null ? false : this.isEqualSidecarDisplayFeatures(SidecarAdapter.Companion.getSidecarDisplayFeatures(sidecarWindowLayoutInfo0), SidecarAdapter.Companion.getSidecarDisplayFeatures(sidecarWindowLayoutInfo1));
    }

    public final WindowLayoutInfo translate(SidecarWindowLayoutInfo sidecarWindowLayoutInfo0, SidecarDeviceState sidecarDeviceState0) {
        Intrinsics.checkNotNullParameter(sidecarDeviceState0, "state");
        if(sidecarWindowLayoutInfo0 == null) {
            return new WindowLayoutInfo(CollectionsKt.emptyList());
        }
        SidecarDeviceState sidecarDeviceState1 = new SidecarDeviceState();
        int v = SidecarAdapter.Companion.getSidecarDevicePosture$window_release(sidecarDeviceState0);
        SidecarAdapter.Companion.setSidecarDevicePosture(sidecarDeviceState1, v);
        return new WindowLayoutInfo(this.translate(SidecarAdapter.Companion.getSidecarDisplayFeatures(sidecarWindowLayoutInfo0), sidecarDeviceState1));
    }

    public final List translate(List list0, SidecarDeviceState sidecarDeviceState0) {
        Intrinsics.checkNotNullParameter(list0, "sidecarDisplayFeatures");
        Intrinsics.checkNotNullParameter(sidecarDeviceState0, "deviceState");
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            DisplayFeature displayFeature0 = this.translate$window_release(((SidecarDisplayFeature)object0), sidecarDeviceState0);
            if(displayFeature0 != null) {
                collection0.add(displayFeature0);
            }
        }
        return (List)collection0;
    }

    public final DisplayFeature translate$window_release(SidecarDisplayFeature sidecarDisplayFeature0, SidecarDeviceState sidecarDeviceState0) {
        State foldingFeature$State0;
        Type hardwareFoldingFeature$Type0;
        Intrinsics.checkNotNullParameter(sidecarDisplayFeature0, "feature");
        Intrinsics.checkNotNullParameter(sidecarDeviceState0, "deviceState");
        Intrinsics.checkNotNullExpressionValue("SidecarAdapter", "TAG");
        SidecarDisplayFeature sidecarDisplayFeature1 = (SidecarDisplayFeature)androidx.window.core.SpecificationComputer.Companion.startSpecification$default(SpecificationComputer.Companion, sidecarDisplayFeature0, "SidecarAdapter", this.verificationMode, null, 4, null).require("Type must be either TYPE_FOLD or TYPE_HINGE", androidx.window.layout.SidecarAdapter.translate.checkedFeature.1.INSTANCE).require("Feature bounds must not be 0", androidx.window.layout.SidecarAdapter.translate.checkedFeature.2.INSTANCE).require("TYPE_FOLD must have 0 area", androidx.window.layout.SidecarAdapter.translate.checkedFeature.3.INSTANCE).require("Feature be pinned to either left or top", androidx.window.layout.SidecarAdapter.translate.checkedFeature.4.INSTANCE).compute();
        if(sidecarDisplayFeature1 == null) {
            return null;
        }
        switch(sidecarDisplayFeature1.getType()) {
            case 1: {
                hardwareFoldingFeature$Type0 = Type.Companion.getFOLD();
                break;
            }
            case 2: {
                hardwareFoldingFeature$Type0 = Type.Companion.getHINGE();
                break;
            }
            default: {
                return null;
            }
        }
        switch(SidecarAdapter.Companion.getSidecarDevicePosture$window_release(sidecarDeviceState0)) {
            case 2: {
                foldingFeature$State0 = State.HALF_OPENED;
                break;
            }
            case 3: {
                foldingFeature$State0 = State.FLAT;
                break;
            }
            case 0: 
            case 1: 
            case 4: {
                return null;
            }
            default: {
                foldingFeature$State0 = State.FLAT;
                break;
            }
        }
        Rect rect0 = sidecarDisplayFeature0.getRect();
        Intrinsics.checkNotNullExpressionValue(rect0, "feature.rect");
        return new HardwareFoldingFeature(new Bounds(rect0), hardwareFoldingFeature$Type0, foldingFeature$State0);

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "invoke", "(Landroidx/window/sidecar/SidecarDisplayFeature;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
        final class androidx.window.layout.SidecarAdapter.translate.checkedFeature.1 extends Lambda implements Function1 {
            public static final androidx.window.layout.SidecarAdapter.translate.checkedFeature.1 INSTANCE;

            static {
                androidx.window.layout.SidecarAdapter.translate.checkedFeature.1.INSTANCE = new androidx.window.layout.SidecarAdapter.translate.checkedFeature.1();
            }

            androidx.window.layout.SidecarAdapter.translate.checkedFeature.1() {
                super(1);
            }

            public final Boolean invoke(SidecarDisplayFeature sidecarDisplayFeature0) {
                Intrinsics.checkNotNullParameter(sidecarDisplayFeature0, "$this$require");
                boolean z = true;
                switch(sidecarDisplayFeature0.getType()) {
                    case 1: 
                    case 2: {
                        break;
                    }
                    default: {
                        z = false;
                    }
                }
                return Boolean.valueOf(z);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SidecarDisplayFeature)object0));
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "invoke", "(Landroidx/window/sidecar/SidecarDisplayFeature;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
        final class androidx.window.layout.SidecarAdapter.translate.checkedFeature.2 extends Lambda implements Function1 {
            public static final androidx.window.layout.SidecarAdapter.translate.checkedFeature.2 INSTANCE;

            static {
                androidx.window.layout.SidecarAdapter.translate.checkedFeature.2.INSTANCE = new androidx.window.layout.SidecarAdapter.translate.checkedFeature.2();
            }

            androidx.window.layout.SidecarAdapter.translate.checkedFeature.2() {
                super(1);
            }

            public final Boolean invoke(SidecarDisplayFeature sidecarDisplayFeature0) {
                Intrinsics.checkNotNullParameter(sidecarDisplayFeature0, "$this$require");
                return sidecarDisplayFeature0.getRect().width() != 0 || sidecarDisplayFeature0.getRect().height() != 0;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SidecarDisplayFeature)object0));
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "invoke", "(Landroidx/window/sidecar/SidecarDisplayFeature;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
        final class androidx.window.layout.SidecarAdapter.translate.checkedFeature.3 extends Lambda implements Function1 {
            public static final androidx.window.layout.SidecarAdapter.translate.checkedFeature.3 INSTANCE;

            static {
                androidx.window.layout.SidecarAdapter.translate.checkedFeature.3.INSTANCE = new androidx.window.layout.SidecarAdapter.translate.checkedFeature.3();
            }

            androidx.window.layout.SidecarAdapter.translate.checkedFeature.3() {
                super(1);
            }

            public final Boolean invoke(SidecarDisplayFeature sidecarDisplayFeature0) {
                Intrinsics.checkNotNullParameter(sidecarDisplayFeature0, "$this$require");
                return Boolean.valueOf(sidecarDisplayFeature0.getType() != 1 || sidecarDisplayFeature0.getRect().width() == 0 || sidecarDisplayFeature0.getRect().height() == 0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SidecarDisplayFeature)object0));
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "invoke", "(Landroidx/window/sidecar/SidecarDisplayFeature;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
        final class androidx.window.layout.SidecarAdapter.translate.checkedFeature.4 extends Lambda implements Function1 {
            public static final androidx.window.layout.SidecarAdapter.translate.checkedFeature.4 INSTANCE;

            static {
                androidx.window.layout.SidecarAdapter.translate.checkedFeature.4.INSTANCE = new androidx.window.layout.SidecarAdapter.translate.checkedFeature.4();
            }

            androidx.window.layout.SidecarAdapter.translate.checkedFeature.4() {
                super(1);
            }

            public final Boolean invoke(SidecarDisplayFeature sidecarDisplayFeature0) {
                Intrinsics.checkNotNullParameter(sidecarDisplayFeature0, "$this$require");
                return sidecarDisplayFeature0.getRect().left == 0 || sidecarDisplayFeature0.getRect().top == 0;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SidecarDisplayFeature)object0));
            }
        }

    }
}

