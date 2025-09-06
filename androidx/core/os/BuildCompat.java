package androidx.core.os;

import android.os.Build.VERSION;
import android.os.ext.SdkExtensions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u000B\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001A\u001BB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001A\u00020\tH\u0007J\b\u0010\n\u001A\u00020\tH\u0007J\b\u0010\u000B\u001A\u00020\tH\u0007J\b\u0010\f\u001A\u00020\tH\u0007J\b\u0010\r\u001A\u00020\tH\u0007J\b\u0010\u000E\u001A\u00020\tH\u0007J\u0018\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0011H\u0007J\b\u0010\u0013\u001A\u00020\tH\u0007J\b\u0010\u0014\u001A\u00020\tH\u0007J\b\u0010\u0015\u001A\u00020\tH\u0007J\b\u0010\u0016\u001A\u00020\tH\u0007J\b\u0010\u0017\u001A\u00020\tH\u0007J\b\u0010\u0018\u001A\u00020\tH\u0007J\b\u0010\u0019\u001A\u00020\tH\u0007R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Landroidx/core/os/BuildCompat;", "", "()V", "AD_SERVICES_EXTENSION_INT", "", "R_EXTENSION_INT", "S_EXTENSION_INT", "T_EXTENSION_INT", "isAtLeastB", "", "isAtLeastN", "isAtLeastNMR1", "isAtLeastO", "isAtLeastOMR1", "isAtLeastP", "isAtLeastPreReleaseCodename", "codename", "", "buildCodename", "isAtLeastQ", "isAtLeastR", "isAtLeastS", "isAtLeastSv2", "isAtLeastT", "isAtLeastU", "isAtLeastV", "Api30Impl", "PrereleaseSdkCheck", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class BuildCompat {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004¨\u0006\u0006"}, d2 = {"Landroidx/core/os/BuildCompat$Api30Impl;", "", "()V", "getExtensionVersion", "", "extension", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Api30Impl {
        public static final Api30Impl INSTANCE;

        static {
            Api30Impl.INSTANCE = new Api30Impl();
        }

        public final int getExtensionVersion(int v) {
            return SdkExtensions.getExtensionVersion(v);
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/core/os/BuildCompat$PrereleaseSdkCheck;", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    public @interface PrereleaseSdkCheck {
    }

    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE;
    public static final int R_EXTENSION_INT;
    public static final int S_EXTENSION_INT;
    public static final int T_EXTENSION_INT;

    static {
        BuildCompat.INSTANCE = new BuildCompat();
        int v = 0;
        BuildCompat.R_EXTENSION_INT = Build.VERSION.SDK_INT < 30 ? 0 : Api30Impl.INSTANCE.getExtensionVersion(30);
        BuildCompat.S_EXTENSION_INT = Build.VERSION.SDK_INT < 30 ? 0 : Api30Impl.INSTANCE.getExtensionVersion(0x1F);
        BuildCompat.T_EXTENSION_INT = Build.VERSION.SDK_INT < 30 ? 0 : Api30Impl.INSTANCE.getExtensionVersion(33);
        if(Build.VERSION.SDK_INT >= 30) {
            v = Api30Impl.INSTANCE.getExtensionVersion(1000000);
        }
        BuildCompat.AD_SERVICES_EXTENSION_INT = v;
    }

    @JvmStatic
    public static final boolean isAtLeastB() {
        if(Build.VERSION.SDK_INT < 36) {
            if(Build.VERSION.SDK_INT >= 35) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("Baklava", s);
            }
            return false;
        }
        return true;
    }

    @Deprecated(message = "Android N is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 24`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 24", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    @Deprecated(message = "Android N MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 25`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 25", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    @Deprecated(message = "Android O is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead use `Build.VERSION.SDK_INT >= 26`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 26", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Deprecated(message = "Android O MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 27`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 27", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @Deprecated(message = "Android P is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 28`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 28", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @JvmStatic
    public static final boolean isAtLeastPreReleaseCodename(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "codename");
        Intrinsics.checkNotNullParameter(s1, "buildCodename");
        if(Intrinsics.areEqual("REL", s1)) {
            return false;
        }
        Integer integer0 = BuildCompat.isAtLeastPreReleaseCodename$codenameToInt(s1);
        Integer integer1 = BuildCompat.isAtLeastPreReleaseCodename$codenameToInt(s);
        if(integer0 != null && integer1 != null) {
            return ((int)integer0) >= ((int)integer1);
        }
        if(integer0 == null && integer1 == null) {
            String s2 = s1.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String s3 = s.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            return s2.compareTo(s3) >= 0;
        }
        return integer0 != null;
    }

    private static final Integer isAtLeastPreReleaseCodename$codenameToInt(String s) {
        String s1 = s.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return Intrinsics.areEqual(s1, "BAKLAVA") ? 0 : null;
    }

    @Deprecated(message = "Android Q is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 29`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 29", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @Deprecated(message = "Android R is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 30`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 30", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @Deprecated(message = "Android S is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 31`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 31", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastS() {
        if(Build.VERSION.SDK_INT < 0x1F) {
            if(Build.VERSION.SDK_INT >= 30) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("S", s);
            }
            return false;
        }
        return true;
    }

    @Deprecated(message = "Android Sv2 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 32`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 32", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastSv2() {
        if(Build.VERSION.SDK_INT < 0x20) {
            if(Build.VERSION.SDK_INT >= 0x1F) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("Sv2", s);
            }
            return false;
        }
        return true;
    }

    @Deprecated(message = "Android Tiramisu is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 33`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 33", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastT() {
        if(Build.VERSION.SDK_INT < 33) {
            if(Build.VERSION.SDK_INT >= 0x20) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("Tiramisu", s);
            }
            return false;
        }
        return true;
    }

    @Deprecated(message = "Android UpsideDownCase is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 34`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 34", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastU() {
        if(Build.VERSION.SDK_INT < 34) {
            if(Build.VERSION.SDK_INT >= 33) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("UpsideDownCake", s);
            }
            return false;
        }
        return true;
    }

    @Deprecated(message = "Android VanillaIceCream is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 35`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 35", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastV() {
        if(Build.VERSION.SDK_INT < 35) {
            if(Build.VERSION.SDK_INT >= 34) {
                String s = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(s, "CODENAME");
                return BuildCompat.isAtLeastPreReleaseCodename("VanillaIceCream", s);
            }
            return false;
        }
        return true;
    }
}

