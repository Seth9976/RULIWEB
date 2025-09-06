package com.google.android.material.internal;

import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import java.lang.reflect.Constructor;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

final class StaticLayoutBuilderCompat {
    static class StaticLayoutBuilderCompatException extends Exception {
        StaticLayoutBuilderCompatException(Throwable throwable0) {
            super("Error thrown initializing StaticLayout " + throwable0.getMessage(), throwable0);
        }
    }

    static final int DEFAULT_HYPHENATION_FREQUENCY = 0;
    static final float DEFAULT_LINE_SPACING_ADD = 0.0f;
    static final float DEFAULT_LINE_SPACING_MULTIPLIER = 1.0f;
    private static final String TEXT_DIRS_CLASS = "android.text.TextDirectionHeuristics";
    private static final String TEXT_DIR_CLASS = "android.text.TextDirectionHeuristic";
    private static final String TEXT_DIR_CLASS_LTR = "LTR";
    private static final String TEXT_DIR_CLASS_RTL = "RTL";
    private Layout.Alignment alignment;
    private static Constructor constructor;
    private TextUtils.TruncateAt ellipsize;
    private int end;
    private int hyphenationFrequency;
    private boolean includePad;
    private static boolean initialized;
    private boolean isRtl;
    private float lineSpacingAdd;
    private float lineSpacingMultiplier;
    private int maxLines;
    private final TextPaint paint;
    private CharSequence source;
    private int start;
    private StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    private static Object textDirection;
    private final int width;

    static {
        StaticLayoutBuilderCompat.DEFAULT_HYPHENATION_FREQUENCY = Build.VERSION.SDK_INT < 23 ? 0 : 1;
    }

    private StaticLayoutBuilderCompat(CharSequence charSequence0, TextPaint textPaint0, int v) {
        this.source = charSequence0;
        this.paint = textPaint0;
        this.width = v;
        this.start = 0;
        this.end = charSequence0.length();
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.maxLines = 0x7FFFFFFF;
        this.lineSpacingAdd = 0.0f;
        this.lineSpacingMultiplier = 1.0f;
        this.hyphenationFrequency = StaticLayoutBuilderCompat.DEFAULT_HYPHENATION_FREQUENCY;
        this.includePad = true;
        this.ellipsize = null;
    }

    public StaticLayout build() throws StaticLayoutBuilderCompatException {
        if(this.source == null) {
            this.source = "";
        }
        int v = Math.max(0, this.width);
        CharSequence charSequence0 = this.maxLines == 1 ? TextUtils.ellipsize(this.source, this.paint, ((float)v), this.ellipsize) : this.source;
        this.end = Math.min(charSequence0.length(), this.end);
        if(Build.VERSION.SDK_INT >= 23) {
            if(this.isRtl && this.maxLines == 1) {
                this.alignment = Layout.Alignment.ALIGN_OPPOSITE;
            }
            StaticLayout.Builder staticLayout$Builder0 = StaticLayout.Builder.obtain(charSequence0, this.start, this.end, this.paint, v);
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(staticLayout$Builder0, this.alignment);
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(staticLayout$Builder0, this.includePad);
            staticLayout$Builder0.setTextDirection((this.isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR));
            TextUtils.TruncateAt textUtils$TruncateAt0 = this.ellipsize;
            if(textUtils$TruncateAt0 != null) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(staticLayout$Builder0, textUtils$TruncateAt0);
            }
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(staticLayout$Builder0, this.maxLines);
            float f = this.lineSpacingAdd;
            if(f != 0.0f || this.lineSpacingMultiplier != 1.0f) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(staticLayout$Builder0, f, this.lineSpacingMultiplier);
            }
            if(this.maxLines > 1) {
                staticLayout$Builder0.setHyphenationFrequency(this.hyphenationFrequency);
            }
            StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer0 = this.staticLayoutBuilderConfigurer;
            if(staticLayoutBuilderConfigurer0 != null) {
                staticLayoutBuilderConfigurer0.configure(staticLayout$Builder0);
            }
            return staticLayout$Builder0.build();
        }
        this.createConstructorWithReflection();
        try {
            Constructor constructor0 = (Constructor)Preconditions.checkNotNull(StaticLayoutBuilderCompat.constructor);
            Integer integer0 = this.start;
            Integer integer1 = this.end;
            Layout.Alignment layout$Alignment0 = this.alignment;
            Object object0 = Preconditions.checkNotNull(StaticLayoutBuilderCompat.textDirection);
            return (StaticLayout)constructor0.newInstance(charSequence0, integer0, integer1, this.paint, v, layout$Alignment0, object0, 1.0f, 0.0f, Boolean.valueOf(this.includePad), null, v, this.maxLines);
        }
        catch(Exception exception0) {
            throw new StaticLayoutBuilderCompatException(exception0);
        }
    }

    private void createConstructorWithReflection() throws StaticLayoutBuilderCompatException {
        if(StaticLayoutBuilderCompat.initialized) {
            return;
        }
        try {
            StaticLayoutBuilderCompat.textDirection = !this.isRtl || Build.VERSION.SDK_INT < 23 ? TextDirectionHeuristics.LTR : TextDirectionHeuristics.RTL;
            Constructor constructor0 = StaticLayout.class.getDeclaredConstructor(CharSequence.class, Integer.TYPE, Integer.TYPE, TextPaint.class, Integer.TYPE, Layout.Alignment.class, TextDirectionHeuristic.class, Float.TYPE, Float.TYPE, Boolean.TYPE, TextUtils.TruncateAt.class, Integer.TYPE, Integer.TYPE);
            StaticLayoutBuilderCompat.constructor = constructor0;
            constructor0.setAccessible(true);
            StaticLayoutBuilderCompat.initialized = true;
        }
        catch(Exception exception0) {
            throw new StaticLayoutBuilderCompatException(exception0);
        }
    }

    public static StaticLayoutBuilderCompat obtain(CharSequence charSequence0, TextPaint textPaint0, int v) {
        return new StaticLayoutBuilderCompat(charSequence0, textPaint0, v);
    }

    public StaticLayoutBuilderCompat setAlignment(Layout.Alignment layout$Alignment0) {
        this.alignment = layout$Alignment0;
        return this;
    }

    public StaticLayoutBuilderCompat setEllipsize(TextUtils.TruncateAt textUtils$TruncateAt0) {
        this.ellipsize = textUtils$TruncateAt0;
        return this;
    }

    public StaticLayoutBuilderCompat setEnd(int v) {
        this.end = v;
        return this;
    }

    public StaticLayoutBuilderCompat setHyphenationFrequency(int v) {
        this.hyphenationFrequency = v;
        return this;
    }

    public StaticLayoutBuilderCompat setIncludePad(boolean z) {
        this.includePad = z;
        return this;
    }

    public StaticLayoutBuilderCompat setIsRtl(boolean z) {
        this.isRtl = z;
        return this;
    }

    public StaticLayoutBuilderCompat setLineSpacing(float f, float f1) {
        this.lineSpacingAdd = f;
        this.lineSpacingMultiplier = f1;
        return this;
    }

    public StaticLayoutBuilderCompat setMaxLines(int v) {
        this.maxLines = v;
        return this;
    }

    public StaticLayoutBuilderCompat setStart(int v) {
        this.start = v;
        return this;
    }

    public StaticLayoutBuilderCompat setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer0) {
        this.staticLayoutBuilderConfigurer = staticLayoutBuilderConfigurer0;
        return this;
    }
}

