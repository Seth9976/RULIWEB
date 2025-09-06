package androidx.core.text;

import android.os.Build.VERSION;
import android.os.Trace;
import android.text.Layout.Alignment;
import android.text.PrecomputedText.Params;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout.Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat implements Spannable {
    static class Api28Impl {
        static Spannable castToSpannable(PrecomputedText precomputedText0) [...] // Inlined contents
    }

    public static final class Params {
        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(TextPaint textPaint0) {
                this.mPaint = textPaint0;
                if(Build.VERSION.SDK_INT >= 23) {
                    this.mBreakStrategy = 1;
                    this.mHyphenationFrequency = 1;
                }
                else {
                    this.mHyphenationFrequency = 0;
                    this.mBreakStrategy = 0;
                }
                this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }

            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }

            public Builder setBreakStrategy(int v) {
                this.mBreakStrategy = v;
                return this;
            }

            public Builder setHyphenationFrequency(int v) {
                this.mHyphenationFrequency = v;
                return this;
            }

            public Builder setTextDirection(TextDirectionHeuristic textDirectionHeuristic0) {
                this.mTextDir = textDirectionHeuristic0;
                return this;
            }
        }

        private final int mBreakStrategy;
        private final int mHyphenationFrequency;
        private final TextPaint mPaint;
        private final TextDirectionHeuristic mTextDir;
        final PrecomputedText.Params mWrapped;

        public Params(PrecomputedText.Params precomputedText$Params0) {
            this.mPaint = precomputedText$Params0.getTextPaint();
            this.mTextDir = precomputedText$Params0.getTextDirection();
            this.mBreakStrategy = precomputedText$Params0.getBreakStrategy();
            this.mHyphenationFrequency = precomputedText$Params0.getHyphenationFrequency();
            if(Build.VERSION.SDK_INT < 29) {
                precomputedText$Params0 = null;
            }
            this.mWrapped = precomputedText$Params0;
        }

        Params(TextPaint textPaint0, TextDirectionHeuristic textDirectionHeuristic0, int v, int v1) {
            this.mWrapped = Build.VERSION.SDK_INT >= 29 ? HalfKt..ExternalSyntheticApiModelOutline0.m(textPaint0).setBreakStrategy(v).setHyphenationFrequency(v1).setTextDirection(textDirectionHeuristic0).build() : null;
            this.mPaint = textPaint0;
            this.mTextDir = textDirectionHeuristic0;
            this.mBreakStrategy = v;
            this.mHyphenationFrequency = v1;
        }

        @Override
        public boolean equals(Object object0) {
            if(object0 == this) {
                return true;
            }
            if(!(object0 instanceof Params)) {
                return false;
            }
            return this.equalsWithoutTextDirection(((Params)object0)) ? this.mTextDir == ((Params)object0).getTextDirection() : false;
        }

        public boolean equalsWithoutTextDirection(Params precomputedTextCompat$Params0) {
            if(Build.VERSION.SDK_INT >= 23) {
                if(this.mBreakStrategy != precomputedTextCompat$Params0.getBreakStrategy()) {
                    return false;
                }
                if(this.mHyphenationFrequency != precomputedTextCompat$Params0.getHyphenationFrequency()) {
                    return false;
                }
            }
            if(this.mPaint.getTextSize() != precomputedTextCompat$Params0.getTextPaint().getTextSize()) {
                return false;
            }
            if(this.mPaint.getTextScaleX() != precomputedTextCompat$Params0.getTextPaint().getTextScaleX()) {
                return false;
            }
            if(this.mPaint.getTextSkewX() != precomputedTextCompat$Params0.getTextPaint().getTextSkewX()) {
                return false;
            }
            if(this.mPaint.getLetterSpacing() != precomputedTextCompat$Params0.getTextPaint().getLetterSpacing()) {
                return false;
            }
            if(!TextUtils.equals(this.mPaint.getFontFeatureSettings(), precomputedTextCompat$Params0.getTextPaint().getFontFeatureSettings())) {
                return false;
            }
            if(this.mPaint.getFlags() != precomputedTextCompat$Params0.getTextPaint().getFlags()) {
                return false;
            }
            if(Build.VERSION.SDK_INT >= 24) {
                if(!this.mPaint.getTextLocales().equals(precomputedTextCompat$Params0.getTextPaint().getTextLocales())) {
                    return false;
                }
            }
            else if(!this.mPaint.getTextLocale().equals(precomputedTextCompat$Params0.getTextPaint().getTextLocale())) {
                return false;
            }
            return this.mPaint.getTypeface() == null ? precomputedTextCompat$Params0.getTextPaint().getTypeface() == null : this.mPaint.getTypeface().equals(precomputedTextCompat$Params0.getTextPaint().getTypeface());
        }

        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        @Override
        public int hashCode() {
            return Build.VERSION.SDK_INT < 24 ? ObjectsCompat.hash(new Object[]{this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getLetterSpacing(), this.mPaint.getFlags(), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency}) : ObjectsCompat.hash(new Object[]{this.mPaint.getTextSize(), this.mPaint.getTextScaleX(), this.mPaint.getTextSkewX(), this.mPaint.getLetterSpacing(), this.mPaint.getFlags(), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency});
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder("{");
            stringBuilder0.append("textSize=" + this.mPaint.getTextSize());
            stringBuilder0.append(", textScaleX=" + this.mPaint.getTextScaleX());
            stringBuilder0.append(", textSkewX=" + this.mPaint.getTextSkewX());
            stringBuilder0.append(", letterSpacing=" + this.mPaint.getLetterSpacing());
            stringBuilder0.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
            if(Build.VERSION.SDK_INT >= 24) {
                stringBuilder0.append(", textLocale=" + this.mPaint.getTextLocales());
            }
            else {
                stringBuilder0.append(", textLocale=" + this.mPaint.getTextLocale());
            }
            stringBuilder0.append(", typeface=" + this.mPaint.getTypeface());
            if(Build.VERSION.SDK_INT >= 26) {
                stringBuilder0.append(", variationSettings=" + this.mPaint.getFontVariationSettings());
            }
            stringBuilder0.append(", textDir=" + this.mTextDir);
            stringBuilder0.append(", breakStrategy=" + this.mBreakStrategy);
            stringBuilder0.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
            stringBuilder0.append("}");
            return stringBuilder0.toString();
        }
    }

    static class PrecomputedTextFutureTask extends FutureTask {
        static class PrecomputedTextCallback implements Callable {
            private Params mParams;
            private CharSequence mText;

            PrecomputedTextCallback(Params precomputedTextCompat$Params0, CharSequence charSequence0) {
                this.mParams = precomputedTextCompat$Params0;
                this.mText = charSequence0;
            }

            public PrecomputedTextCompat call() throws Exception {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        }

        PrecomputedTextFutureTask(Params precomputedTextCompat$Params0, CharSequence charSequence0) {
            super(new PrecomputedTextCallback(precomputedTextCompat$Params0, charSequence0));
        }
    }

    private static final char LINE_FEED = '\n';
    private final int[] mParagraphEnds;
    private final Params mParams;
    private final Spannable mText;
    private final PrecomputedText mWrapped;
    private static Executor sExecutor;
    private static final Object sLock;

    static {
        PrecomputedTextCompat.sLock = new Object();
    }

    private PrecomputedTextCompat(PrecomputedText precomputedText0, Params precomputedTextCompat$Params0) {
        this.mText = precomputedText0;
        this.mParams = precomputedTextCompat$Params0;
        this.mParagraphEnds = null;
        if(Build.VERSION.SDK_INT < 29) {
            precomputedText0 = null;
        }
        this.mWrapped = precomputedText0;
    }

    private PrecomputedTextCompat(CharSequence charSequence0, Params precomputedTextCompat$Params0, int[] arr_v) {
        this.mText = new SpannableString(charSequence0);
        this.mParams = precomputedTextCompat$Params0;
        this.mParagraphEnds = arr_v;
        this.mWrapped = null;
    }

    @Override
    public char charAt(int v) {
        return this.mText.charAt(v);
    }

    public static PrecomputedTextCompat create(CharSequence charSequence0, Params precomputedTextCompat$Params0) {
        Preconditions.checkNotNull(charSequence0);
        Preconditions.checkNotNull(precomputedTextCompat$Params0);
        try {
            Trace.beginSection("PrecomputedText");
            if(Build.VERSION.SDK_INT >= 29 && precomputedTextCompat$Params0.mWrapped != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(charSequence0, precomputedTextCompat$Params0.mWrapped), precomputedTextCompat$Params0);
            }
            ArrayList arrayList0 = new ArrayList();
            int v1 = charSequence0.length();
            int v2 = 0;
            while(v2 < v1) {
                int v3 = TextUtils.indexOf(charSequence0, '\n', v2, v1);
                v2 = v3 >= 0 ? v3 + 1 : v1;
                arrayList0.add(v2);
            }
            int[] arr_v = new int[arrayList0.size()];
            for(int v4 = 0; v4 < arrayList0.size(); ++v4) {
                arr_v[v4] = (int)(((Integer)arrayList0.get(v4)));
            }
            if(Build.VERSION.SDK_INT >= 23) {
                StaticLayout.Builder.obtain(charSequence0, 0, charSequence0.length(), precomputedTextCompat$Params0.getTextPaint(), 0x7FFFFFFF).setBreakStrategy(precomputedTextCompat$Params0.getBreakStrategy()).setHyphenationFrequency(precomputedTextCompat$Params0.getHyphenationFrequency()).setTextDirection(precomputedTextCompat$Params0.getTextDirection()).build();
            }
            else {
                new StaticLayout(charSequence0, precomputedTextCompat$Params0.getTextPaint(), 0x7FFFFFFF, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
            return new PrecomputedTextCompat(charSequence0, precomputedTextCompat$Params0, arr_v);
        }
        finally {
            Trace.endSection();
        }
    }

    public int getParagraphCount() {
        return Build.VERSION.SDK_INT < 29 ? this.mParagraphEnds.length : this.mWrapped.getParagraphCount();
    }

    public int getParagraphEnd(int v) {
        Preconditions.checkArgumentInRange(v, 0, this.getParagraphCount(), "paraIndex");
        return Build.VERSION.SDK_INT < 29 ? this.mParagraphEnds[v] : this.mWrapped.getParagraphEnd(v);
    }

    public int getParagraphStart(int v) {
        Preconditions.checkArgumentInRange(v, 0, this.getParagraphCount(), "paraIndex");
        if(Build.VERSION.SDK_INT >= 29) {
            return this.mWrapped.getParagraphStart(v);
        }
        return v == 0 ? 0 : this.mParagraphEnds[v - 1];
    }

    public Params getParams() {
        return this.mParams;
    }

    // 去混淆评级： 低(20)
    public PrecomputedText getPrecomputedText() {
        return HalfKt..ExternalSyntheticApiModelOutline0.m(this.mText) ? ((PrecomputedText)this.mText) : null;
    }

    @Override  // android.text.Spanned
    public int getSpanEnd(Object object0) {
        return this.mText.getSpanEnd(object0);
    }

    @Override  // android.text.Spanned
    public int getSpanFlags(Object object0) {
        return this.mText.getSpanFlags(object0);
    }

    @Override  // android.text.Spanned
    public int getSpanStart(Object object0) {
        return this.mText.getSpanStart(object0);
    }

    @Override  // android.text.Spanned
    public Object[] getSpans(int v, int v1, Class class0) {
        return Build.VERSION.SDK_INT < 29 ? this.mText.getSpans(v, v1, class0) : this.mWrapped.getSpans(v, v1, class0);
    }

    public static Future getTextFuture(CharSequence charSequence0, Params precomputedTextCompat$Params0, Executor executor0) {
        Future future0 = new PrecomputedTextFutureTask(precomputedTextCompat$Params0, charSequence0);
        if(executor0 == null) {
            synchronized(PrecomputedTextCompat.sLock) {
                if(PrecomputedTextCompat.sExecutor == null) {
                    PrecomputedTextCompat.sExecutor = Executors.newFixedThreadPool(1);
                }
                executor0 = PrecomputedTextCompat.sExecutor;
            }
        }
        executor0.execute(((Runnable)future0));
        return future0;
    }

    @Override
    public int length() {
        return this.mText.length();
    }

    @Override  // android.text.Spanned
    public int nextSpanTransition(int v, int v1, Class class0) {
        return this.mText.nextSpanTransition(v, v1, class0);
    }

    @Override  // android.text.Spannable
    public void removeSpan(Object object0) {
        if(object0 instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if(Build.VERSION.SDK_INT >= 29) {
            this.mWrapped.removeSpan(object0);
            return;
        }
        this.mText.removeSpan(object0);
    }

    @Override  // android.text.Spannable
    public void setSpan(Object object0, int v, int v1, int v2) {
        if(object0 instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if(Build.VERSION.SDK_INT >= 29) {
            this.mWrapped.setSpan(object0, v, v1, v2);
            return;
        }
        this.mText.setSpan(object0, v, v1, v2);
    }

    @Override
    public CharSequence subSequence(int v, int v1) {
        return this.mText.subSequence(v, v1);
    }

    @Override
    public String toString() {
        return this.mText.toString();
    }
}

