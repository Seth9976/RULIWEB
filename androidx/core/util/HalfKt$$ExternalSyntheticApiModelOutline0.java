package androidx.core.util;

import android.content.ClipData;
import android.graphics.Insets;
import android.icu.text.DateFormat.HourCycle;
import android.location.GnssMeasurementsEvent.Callback;
import android.location.LocationRequest;
import android.os.LocaleList;
import android.os.OutcomeReceiver;
import android.os.ProfilingManager;
import android.text.PrecomputedText.Params.Builder;
import android.text.PrecomputedText;
import android.text.TextPaint;
import android.view.ContentInfo.Builder;
import android.view.ContentInfo;
import android.view.PointerIcon;
import android.view.ViewStructure;
import android.view.WindowInsetsAnimation.Bounds;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;
import java.util.function.Consumer;

public final class HalfKt..ExternalSyntheticApiModelOutline0 {
    public static int m() {
        return 8;
    }

    public static int m(DateFormat.HourCycle dateFormat$HourCycle0) [...] // 潜在的解密器

    public static DateFormat.HourCycle m() {
        return DateFormat.HourCycle.HOUR_CYCLE_11;
    }

    public static GnssMeasurementsEvent.Callback m(Object object0) {
        return (GnssMeasurementsEvent.Callback)object0;
    }

    public static LocationRequest m(Object object0) {
        return (LocationRequest)object0;
    }

    public static LocaleList m(Object object0) [...] // Inlined contents

    public static OutcomeReceiver m(Object object0) {
        return (OutcomeReceiver)object0;
    }

    public static ProfilingManager m(Object object0) {
        return (ProfilingManager)object0;
    }

    public static PrecomputedText.Params.Builder m(TextPaint textPaint0) {
        return new PrecomputedText.Params.Builder(textPaint0);
    }

    public static PrecomputedText m(Object object0) [...] // Inlined contents

    public static ContentInfo.Builder m(ClipData clipData0, int v) {
        return new ContentInfo.Builder(clipData0, v);
    }

    public static ContentInfo.Builder m(ContentInfo contentInfo0) {
        return new ContentInfo.Builder(contentInfo0);
    }

    public static ContentInfo m(Object object0) {
        return (ContentInfo)object0;
    }

    public static PointerIcon m(Object object0) [...] // Inlined contents

    public static ViewStructure m(Object object0) [...] // Inlined contents

    public static WindowInsetsAnimation.Bounds m(Insets insets0, Insets insets1) {
        return new WindowInsetsAnimation.Bounds(insets0, insets1);
    }

    public static WindowInsetsAnimation m(int v, Interpolator interpolator0, long v1) {
        return new WindowInsetsAnimation(v, interpolator0, v1);
    }

    public static WindowInsetsAnimation m(Object object0) {
        return (WindowInsetsAnimation)object0;
    }

    public static Consumer m(Object object0) [...] // Inlined contents

    public static void m() {
    }

    public static boolean m(Object object0) {
        return object0 instanceof PrecomputedText;
    }

    public static DateFormat.HourCycle m$1() {
        return DateFormat.HourCycle.HOUR_CYCLE_12;
    }

    public static void m$1() {
    }

    public static DateFormat.HourCycle m$2() {
        return DateFormat.HourCycle.HOUR_CYCLE_23;
    }

    public static DateFormat.HourCycle m$3() {
        return DateFormat.HourCycle.HOUR_CYCLE_24;
    }
}

