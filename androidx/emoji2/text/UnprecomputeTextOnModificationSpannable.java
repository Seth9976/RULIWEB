package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import java.util.stream.IntStream;

class UnprecomputeTextOnModificationSpannable implements Spannable {
    static class CharSequenceHelper_API24 {
        static IntStream chars(CharSequence charSequence0) {
            return charSequence0.chars();
        }

        static IntStream codePoints(CharSequence charSequence0) {
            return charSequence0.codePoints();
        }
    }

    static class PrecomputedTextDetector {
        boolean isPrecomputedText(CharSequence charSequence0) {
            return charSequence0 instanceof PrecomputedTextCompat;
        }
    }

    static class PrecomputedTextDetector_28 extends PrecomputedTextDetector {
        // 去混淆评级： 低(20)
        @Override  // androidx.emoji2.text.UnprecomputeTextOnModificationSpannable$PrecomputedTextDetector
        boolean isPrecomputedText(CharSequence charSequence0) {
            return HalfKt..ExternalSyntheticApiModelOutline0.m(charSequence0) || charSequence0 instanceof PrecomputedTextCompat;
        }
    }

    private Spannable mDelegate;
    private boolean mSafeToWrite;

    UnprecomputeTextOnModificationSpannable(Spannable spannable0) {
        this.mSafeToWrite = false;
        this.mDelegate = spannable0;
    }

    UnprecomputeTextOnModificationSpannable(Spanned spanned0) {
        this.mSafeToWrite = false;
        this.mDelegate = new SpannableString(spanned0);
    }

    UnprecomputeTextOnModificationSpannable(CharSequence charSequence0) {
        this.mSafeToWrite = false;
        this.mDelegate = new SpannableString(charSequence0);
    }

    @Override
    public char charAt(int v) {
        return this.mDelegate.charAt(v);
    }

    @Override
    public IntStream chars() {
        return CharSequenceHelper_API24.chars(this.mDelegate);
    }

    @Override
    public IntStream codePoints() {
        return CharSequenceHelper_API24.codePoints(this.mDelegate);
    }

    private void ensureSafeWrites() {
        Spannable spannable0 = this.mDelegate;
        if(!this.mSafeToWrite && UnprecomputeTextOnModificationSpannable.precomputedTextDetector().isPrecomputedText(spannable0)) {
            this.mDelegate = new SpannableString(spannable0);
        }
        this.mSafeToWrite = true;
    }

    @Override  // android.text.Spanned
    public int getSpanEnd(Object object0) {
        return this.mDelegate.getSpanEnd(object0);
    }

    @Override  // android.text.Spanned
    public int getSpanFlags(Object object0) {
        return this.mDelegate.getSpanFlags(object0);
    }

    @Override  // android.text.Spanned
    public int getSpanStart(Object object0) {
        return this.mDelegate.getSpanStart(object0);
    }

    @Override  // android.text.Spanned
    public Object[] getSpans(int v, int v1, Class class0) {
        return this.mDelegate.getSpans(v, v1, class0);
    }

    Spannable getUnwrappedSpannable() {
        return this.mDelegate;
    }

    @Override
    public int length() {
        return this.mDelegate.length();
    }

    @Override  // android.text.Spanned
    public int nextSpanTransition(int v, int v1, Class class0) {
        return this.mDelegate.nextSpanTransition(v, v1, class0);
    }

    static PrecomputedTextDetector precomputedTextDetector() {
        return Build.VERSION.SDK_INT < 28 ? new PrecomputedTextDetector() : new PrecomputedTextDetector_28();
    }

    @Override  // android.text.Spannable
    public void removeSpan(Object object0) {
        this.ensureSafeWrites();
        this.mDelegate.removeSpan(object0);
    }

    @Override  // android.text.Spannable
    public void setSpan(Object object0, int v, int v1, int v2) {
        this.ensureSafeWrites();
        this.mDelegate.setSpan(object0, v, v1, v2);
    }

    @Override
    public CharSequence subSequence(int v, int v1) {
        return this.mDelegate.subSequence(v, v1);
    }

    @Override
    public String toString() {
        return this.mDelegate.toString();
    }
}

