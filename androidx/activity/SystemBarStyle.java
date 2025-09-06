package androidx.activity;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\r\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B3\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u0015\u0010\u0010\u001A\u00020\u00032\u0006\u0010\u0011\u001A\u00020\tH\u0000¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001A\u00020\u00032\u0006\u0010\u0011\u001A\u00020\tH\u0000¢\u0006\u0002\b\u0014R\u0014\u0010\u0004\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR \u0010\u0006\u001A\u000E\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\f¨\u0006\u0016"}, d2 = {"Landroidx/activity/SystemBarStyle;", "", "lightScrim", "", "darkScrim", "nightMode", "detectDarkMode", "Lkotlin/Function1;", "Landroid/content/res/Resources;", "", "(IIILkotlin/jvm/functions/Function1;)V", "getDarkScrim$activity_release", "()I", "getDetectDarkMode$activity_release", "()Lkotlin/jvm/functions/Function1;", "getNightMode$activity_release", "getScrim", "isDark", "getScrim$activity_release", "getScrimWithEnforcedContrast", "getScrimWithEnforcedContrast$activity_release", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SystemBarStyle {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001A\u00020\u00042\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u00062\u0014\b\u0002\u0010\b\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000B0\tH\u0007J\u0012\u0010\f\u001A\u00020\u00042\b\b\u0001\u0010\r\u001A\u00020\u0006H\u0007J\u001C\u0010\u000E\u001A\u00020\u00042\b\b\u0001\u0010\r\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u0006H\u0007¨\u0006\u000F"}, d2 = {"Landroidx/activity/SystemBarStyle$Companion;", "", "()V", "auto", "Landroidx/activity/SystemBarStyle;", "lightScrim", "", "darkScrim", "detectDarkMode", "Lkotlin/Function1;", "Landroid/content/res/Resources;", "", "dark", "scrim", "light", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SystemBarStyle auto(int v, int v1) {
            return Companion.auto$default(this, v, v1, null, 4, null);
        }

        @JvmStatic
        public final SystemBarStyle auto(int v, int v1, Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "detectDarkMode");
            return new SystemBarStyle(v, v1, 0, function10, null);
        }

        public static SystemBarStyle auto$default(Companion systemBarStyle$Companion0, int v, int v1, Function1 function10, int v2, Object object0) {
            if((v2 & 4) != 0) {
                function10 = androidx.activity.SystemBarStyle.Companion.auto.1.INSTANCE;
            }
            return systemBarStyle$Companion0.auto(v, v1, function10);

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "resources", "Landroid/content/res/Resources;", "invoke", "(Landroid/content/res/Resources;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.activity.SystemBarStyle.Companion.auto.1 extends Lambda implements Function1 {
                public static final androidx.activity.SystemBarStyle.Companion.auto.1 INSTANCE;

                static {
                    androidx.activity.SystemBarStyle.Companion.auto.1.INSTANCE = new androidx.activity.SystemBarStyle.Companion.auto.1();
                }

                androidx.activity.SystemBarStyle.Companion.auto.1() {
                    super(1);
                }

                public final Boolean invoke(Resources resources0) {
                    Intrinsics.checkNotNullParameter(resources0, "resources");
                    return (resources0.getConfiguration().uiMode & 0x30) == 0x20;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Resources)object0));
                }
            }

        }

        @JvmStatic
        public final SystemBarStyle dark(int v) {
            return new SystemBarStyle(v, v, 2, androidx.activity.SystemBarStyle.Companion.dark.1.INSTANCE, null);

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/res/Resources;", "invoke", "(Landroid/content/res/Resources;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.activity.SystemBarStyle.Companion.dark.1 extends Lambda implements Function1 {
                public static final androidx.activity.SystemBarStyle.Companion.dark.1 INSTANCE;

                static {
                    androidx.activity.SystemBarStyle.Companion.dark.1.INSTANCE = new androidx.activity.SystemBarStyle.Companion.dark.1();
                }

                androidx.activity.SystemBarStyle.Companion.dark.1() {
                    super(1);
                }

                public final Boolean invoke(Resources resources0) {
                    Intrinsics.checkNotNullParameter(resources0, "<anonymous parameter 0>");
                    return true;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Resources)object0));
                }
            }

        }

        @JvmStatic
        public final SystemBarStyle light(int v, int v1) {
            return new SystemBarStyle(v, v1, 1, androidx.activity.SystemBarStyle.Companion.light.1.INSTANCE, null);

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/res/Resources;", "invoke", "(Landroid/content/res/Resources;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class androidx.activity.SystemBarStyle.Companion.light.1 extends Lambda implements Function1 {
                public static final androidx.activity.SystemBarStyle.Companion.light.1 INSTANCE;

                static {
                    androidx.activity.SystemBarStyle.Companion.light.1.INSTANCE = new androidx.activity.SystemBarStyle.Companion.light.1();
                }

                androidx.activity.SystemBarStyle.Companion.light.1() {
                    super(1);
                }

                public final Boolean invoke(Resources resources0) {
                    Intrinsics.checkNotNullParameter(resources0, "<anonymous parameter 0>");
                    return false;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Resources)object0));
                }
            }

        }
    }

    public static final Companion Companion;
    private final int darkScrim;
    private final Function1 detectDarkMode;
    private final int lightScrim;
    private final int nightMode;

    static {
        SystemBarStyle.Companion = new Companion(null);
    }

    private SystemBarStyle(int v, int v1, int v2, Function1 function10) {
        this.lightScrim = v;
        this.darkScrim = v1;
        this.nightMode = v2;
        this.detectDarkMode = function10;
    }

    public SystemBarStyle(int v, int v1, int v2, Function1 function10, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v, v1, v2, function10);
    }

    @JvmStatic
    public static final SystemBarStyle auto(int v, int v1) {
        return SystemBarStyle.Companion.auto(v, v1);
    }

    @JvmStatic
    public static final SystemBarStyle auto(int v, int v1, Function1 function10) {
        return SystemBarStyle.Companion.auto(v, v1, function10);
    }

    @JvmStatic
    public static final SystemBarStyle dark(int v) {
        return SystemBarStyle.Companion.dark(v);
    }

    public final int getDarkScrim$activity_release() {
        return this.darkScrim;
    }

    public final Function1 getDetectDarkMode$activity_release() {
        return this.detectDarkMode;
    }

    public final int getNightMode$activity_release() {
        return this.nightMode;
    }

    // 去混淆评级： 低(20)
    public final int getScrim$activity_release(boolean z) {
        return z ? this.darkScrim : this.lightScrim;
    }

    public final int getScrimWithEnforcedContrast$activity_release(boolean z) {
        if(this.nightMode == 0) {
            return 0;
        }
        return z ? this.darkScrim : this.lightScrim;
    }

    @JvmStatic
    public static final SystemBarStyle light(int v, int v1) {
        return SystemBarStyle.Companion.light(v, v1);
    }
}

