package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.text.TextPaint;
import androidx.core.graphics.PaintCompat;

class DefaultGlyphChecker implements GlyphChecker {
    private static final int PAINT_TEXT_SIZE = 10;
    private final TextPaint mTextPaint;
    private static final ThreadLocal sStringBuilder;

    static {
        DefaultGlyphChecker.sStringBuilder = new ThreadLocal();
    }

    DefaultGlyphChecker() {
        TextPaint textPaint0 = new TextPaint();
        this.mTextPaint = textPaint0;
        textPaint0.setTextSize(10.0f);
    }

    private static StringBuilder getStringBuilder() {
        ThreadLocal threadLocal0 = DefaultGlyphChecker.sStringBuilder;
        if(threadLocal0.get() == null) {
            threadLocal0.set(new StringBuilder());
        }
        return (StringBuilder)threadLocal0.get();
    }

    @Override  // androidx.emoji2.text.EmojiCompat$GlyphChecker
    public boolean hasGlyph(CharSequence charSequence0, int v, int v1, int v2) {
        if(Build.VERSION.SDK_INT < 23 && v2 > Build.VERSION.SDK_INT) {
            return false;
        }
        StringBuilder stringBuilder0 = DefaultGlyphChecker.getStringBuilder();
        stringBuilder0.setLength(0);
        while(v < v1) {
            stringBuilder0.append(charSequence0.charAt(v));
            ++v;
        }
        return PaintCompat.hasGlyph(this.mTextPaint, stringBuilder0.toString());
    }
}

