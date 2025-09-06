package kotlin.io.path;

import android.animation.AnimatorSet;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.ApplicationInfoFlags;
import android.content.pm.PackageManager.PackageInfoFlags;
import android.content.pm.PackageManager.ResolveInfoFlags;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.view.autofill.AutofillId;
import android.widget.EditText;
import android.window.OnBackInvokedDispatcher;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.search.SearchBar;
import com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout;
import com.google.android.material.textfield.TextInputLayout;
import java.io.File;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

public final class LinkFollowing..ExternalSyntheticApiModelOutline0 {
    public static int m(ApplicationInfo applicationInfo0) {
        return applicationInfo0.minSdkVersion;
    }

    public static int m(Configuration configuration0) {
        return configuration0.fontWeightAdjustment;
    }

    public static int m(Typeface typeface0) {
        return typeface0.getWeight();
    }

    public static int m(TypedValue typedValue0) {
        return typedValue0.getComplexUnit();
    }

    public static int m(AccessibilityManager accessibilityManager0, int v, int v1) {
        return accessibilityManager0.getRecommendedTimeoutMillis(v, v1);
    }

    public static int m(SeekableByteChannel seekableByteChannel0, ByteBuffer byteBuffer0) {
        return seekableByteChannel0.read(byteBuffer0);
    }

    public static int m(Matcher matcher0, String s) {
        return matcher0.start(s);
    }

    public static long m(AnimatorSet animatorSet0) {
        return animatorSet0.getTotalDuration();
    }

    public static long m(PackageInfo packageInfo0) {
        return packageInfo0.getLongVersionCode();
    }

    public static long m(SeekableByteChannel seekableByteChannel0) {
        return seekableByteChannel0.size();
    }

    public static long m(Instant instant0) {
        return instant0.getEpochSecond();
    }

    public static ApplicationInfo m(PackageManager packageManager0, String s, PackageManager.ApplicationInfoFlags packageManager$ApplicationInfoFlags0) {
        return packageManager0.getApplicationInfo(s, packageManager$ApplicationInfoFlags0);
    }

    public static PackageInfo m(PackageManager packageManager0, String s, PackageManager.PackageInfoFlags packageManager$PackageInfoFlags0) {
        return packageManager0.getPackageInfo(s, packageManager$PackageInfoFlags0);
    }

    public static PackageManager.ApplicationInfoFlags m(long v) {
        return PackageManager.ApplicationInfoFlags.of(v);
    }

    public static PackageManager.PackageInfoFlags m(long v) {
        return PackageManager.PackageInfoFlags.of(v);
    }

    public static PackageManager.ResolveInfoFlags m(long v) {
        return PackageManager.ResolveInfoFlags.of(v);
    }

    public static ResolveInfo m(PackageManager packageManager0, Intent intent0, PackageManager.ResolveInfoFlags packageManager$ResolveInfoFlags0) {
        return packageManager0.resolveActivity(intent0, packageManager$ResolveInfoFlags0);
    }

    public static ColorStateList m(ColorStateListDrawable colorStateListDrawable0) {
        return colorStateListDrawable0.getColorStateList();
    }

    public static Typeface m(Typeface typeface0, int v, boolean z) {
        return Typeface.create(typeface0, v, z);
    }

    public static ColorStateListDrawable m(Object object0) [...] // Inlined contents

    public static Drawable m(EditText editText0) {
        return editText0.getTextCursorDrawable();
    }

    public static StrictMode.ThreadPolicy.Builder m(StrictMode.ThreadPolicy.Builder strictMode$ThreadPolicy$Builder0) {
        return strictMode$ThreadPolicy$Builder0.detectResourceMismatches();
    }

    public static StaticLayout.Builder m(StaticLayout.Builder staticLayout$Builder0, float f, float f1) {
        return staticLayout$Builder0.setLineSpacing(f, f1);
    }

    public static StaticLayout.Builder m(StaticLayout.Builder staticLayout$Builder0, int v) {
        return staticLayout$Builder0.setMaxLines(v);
    }

    public static StaticLayout.Builder m(StaticLayout.Builder staticLayout$Builder0, Layout.Alignment layout$Alignment0) {
        return staticLayout$Builder0.setAlignment(layout$Alignment0);
    }

    public static StaticLayout.Builder m(StaticLayout.Builder staticLayout$Builder0, TextUtils.TruncateAt textUtils$TruncateAt0) {
        return staticLayout$Builder0.setEllipsize(textUtils$TruncateAt0);
    }

    public static StaticLayout.Builder m(StaticLayout.Builder staticLayout$Builder0, boolean z) {
        return staticLayout$Builder0.setIncludePad(z);
    }

    public static ViewStructure m(ViewStructure viewStructure0, int v) {
        return viewStructure0.newChild(v);
    }

    public static WindowInsets m(SnackbarBaseLayout baseTransientBottomBar$SnackbarBaseLayout0) {
        return baseTransientBottomBar$SnackbarBaseLayout0.getRootWindowInsets();
    }

    public static AutofillId m(TextInputLayout textInputLayout0) {
        return textInputLayout0.getAutofillId();
    }

    public static OnBackInvokedDispatcher m(View view0) {
        return view0.findOnBackInvokedDispatcher();
    }

    public static File m(Path path0) {
        return path0.toFile();
    }

    public static Serializable m(Bundle bundle0, String s, Class class0) {
        return bundle0.getSerializable(s, class0);
    }

    public static Object m(Bundle bundle0, String s, Class class0) {
        return bundle0.getParcelable(s, class0);
    }

    public static Object m(BasicFileAttributes basicFileAttributes0) {
        return basicFileAttributes0.fileKey();
    }

    public static Object m(Map map0, Object object0, Object object1) {
        return map0.getOrDefault(object0, object1);
    }

    public static String m(NotificationManager notificationManager0) {
        return notificationManager0.getNotificationDelegate();
    }

    public static String m(LocalDateTime localDateTime0, DateTimeFormatter dateTimeFormatter0) {
        return localDateTime0.format(dateTimeFormatter0);
    }

    public static String m(Matcher matcher0, String s) {
        return matcher0.group(s);
    }

    public static Throwable m(FileSystemException fileSystemException0, Throwable throwable0) {
        return fileSystemException0.initCause(throwable0);
    }

    public static SeekableByteChannel m(SeekableByteChannel seekableByteChannel0, long v) {
        return seekableByteChannel0.position(v);
    }

    public static FileSystemException m(Object object0) [...] // Inlined contents

    public static FileSystemException m(String s) {
        return new FileSystemException(s);
    }

    public static FileVisitResult m(Object object0) {
        return (FileVisitResult)object0;
    }

    public static FileVisitor m(Object object0) [...] // Inlined contents

    public static Path m(Object object0) [...] // Inlined contents

    public static Clock m() {
        return Clock.systemUTC();
    }

    public static Duration m(long v) {
        return Duration.ofMinutes(v);
    }

    public static Instant m(long v) {
        return Instant.ofEpochMilli(v);
    }

    public static Instant m(Clock clock0) {
        return clock0.instant();
    }

    public static Instant m(Instant instant0, TemporalAmount temporalAmount0) {
        return instant0.minus(temporalAmount0);
    }

    public static LocalDateTime m(OffsetDateTime offsetDateTime0) {
        return offsetDateTime0.toLocalDateTime();
    }

    public static OffsetDateTime m(Instant instant0, ZoneOffset zoneOffset0) {
        return instant0.atOffset(zoneOffset0);
    }

    public static ZoneOffset m() {
        return ZoneOffset.UTC;
    }

    public static DateTimeFormatter m() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public static List m(PackageManager packageManager0, Intent intent0, PackageManager.ResolveInfoFlags packageManager$ResolveInfoFlags0) {
        return packageManager0.queryIntentServices(intent0, packageManager$ResolveInfoFlags0);
    }

    public static Optional m() {
        return Optional.empty();
    }

    public static Optional m(Object object0) {
        return Optional.of(object0);
    }

    public static void m() {
    }

    public static void m(AnimatorSet animatorSet0) {
        animatorSet0.reverse();
    }

    public static void m(NotificationManager notificationManager0, String s) {
        notificationManager0.setNotificationDelegate(s);
    }

    public static void m(Outline outline0, android.graphics.Path path0) {
        outline0.setPath(path0);
    }

    public static void m(RippleDrawable rippleDrawable0, int v) {
        rippleDrawable0.setRadius(v);
    }

    public static void m(View view0, ViewStructure viewStructure0, int v) {
        view0.dispatchProvideAutofillStructure(viewStructure0, v);
    }

    public static void m(ViewStructure viewStructure0, int v) {
        viewStructure0.setChildCount(v);
    }

    public static void m(ViewStructure viewStructure0, AutofillId autofillId0) {
        viewStructure0.setAutofillId(autofillId0);
    }

    public static void m(ViewStructure viewStructure0, CharSequence charSequence0) {
        viewStructure0.setHint(charSequence0);
    }

    public static void m(EditText editText0, LocaleList localeList0) {
        editText0.setImeHintLocales(localeList0);
    }

    public static void m(EditText editText0, boolean z) {
        editText0.setIsHandwritingDelegate(z);
    }

    public static void m(NavigationBarItemView navigationBarItemView0, boolean z) {
        navigationBarItemView0.setDefaultFocusHighlightEnabled(z);
    }

    public static void m(SearchBar searchBar0, float f, float f1, float f2, float f3) {
        searchBar0.setHandwritingBoundsOffsets(f, f1, f2, f3);
    }

    public static void m(SearchBar searchBar0, Runnable runnable0) {
        searchBar0.setHandwritingDelegatorCallback(runnable0);
    }

    public static void m(TextInputLayout textInputLayout0, ViewStructure viewStructure0, int v) {
        textInputLayout0.onProvideAutofillStructure(viewStructure0, v);
    }

    public static void m(SeekableByteChannel seekableByteChannel0) {
        seekableByteChannel0.close();
    }

    public static boolean m(Context context0, String s) {
        return context0.deleteSharedPreferences(s);
    }

    public static boolean m(Canvas canvas0, RectF rectF0) {
        return canvas0.clipOutRect(rectF0);
    }

    public static boolean m(Object object0) {
        return object0 instanceof AdaptiveIconDrawable;
    }

    public static boolean m(SeekableByteChannel seekableByteChannel0) {
        return seekableByteChannel0.isOpen();
    }

    public static boolean m(Duration duration0) {
        return duration0.isZero();
    }

    public static boolean m(Instant instant0, Instant instant1) {
        return instant0.isAfter(instant1);
    }

    public static boolean m(Map map0, Object object0, Object object1) {
        return map0.remove(object0, object1);
    }

    public static Signature[] m(SigningInfo signingInfo0) {
        return signingInfo0.getApkContentsSigners();
    }

    public static int m$1(Matcher matcher0, String s) {
        return matcher0.end(s);
    }

    public static long m$1(SeekableByteChannel seekableByteChannel0) {
        return seekableByteChannel0.position();
    }

    public static StrictMode.ThreadPolicy.Builder m$1(StrictMode.ThreadPolicy.Builder strictMode$ThreadPolicy$Builder0) {
        return strictMode$ThreadPolicy$Builder0.detectUnbufferedIo();
    }

    public static Path m$1(Path path0) {
        return path0.getParent();
    }

    public static Instant m$1(Instant instant0, TemporalAmount temporalAmount0) {
        return instant0.plus(temporalAmount0);
    }

    public static void m$1(TextInputLayout textInputLayout0, ViewStructure viewStructure0, int v) {
        textInputLayout0.onProvideAutofillVirtualStructure(viewStructure0, v);
    }
}

