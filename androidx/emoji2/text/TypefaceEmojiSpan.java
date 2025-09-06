package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

public final class TypefaceEmojiSpan extends EmojiSpan {
    private TextPaint mWorkingPaint;
    private static Paint sDebugPaint;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
        super(typefaceEmojiRasterizer0);
    }

    private TextPaint applyCharacterSpanStyles(CharSequence charSequence0, int v, int v1, Paint paint0) {
        if(charSequence0 instanceof Spanned) {
            CharacterStyle[] arr_characterStyle = (CharacterStyle[])((Spanned)charSequence0).getSpans(v, v1, CharacterStyle.class);
            if(arr_characterStyle.length != 0 && (arr_characterStyle.length != 1 || arr_characterStyle[0] != this)) {
                TextPaint textPaint0 = this.mWorkingPaint;
                if(textPaint0 == null) {
                    textPaint0 = new TextPaint();
                    this.mWorkingPaint = textPaint0;
                }
                textPaint0.set(paint0);
                for(int v2 = 0; v2 < arr_characterStyle.length; ++v2) {
                    arr_characterStyle[v2].updateDrawState(textPaint0);
                }
                return textPaint0;
            }
            return paint0 instanceof TextPaint ? ((TextPaint)paint0) : null;
        }
        return paint0 instanceof TextPaint ? ((TextPaint)paint0) : null;
    }

    @Override  // android.text.style.ReplacementSpan
    public void draw(Canvas canvas0, CharSequence charSequence0, int v, int v1, float f, int v2, int v3, int v4, Paint paint0) {
        TextPaint textPaint0 = this.applyCharacterSpanStyles(charSequence0, v, v1, paint0);
        if(textPaint0 != null && textPaint0.bgColor != 0) {
            this.drawBackground(canvas0, textPaint0, f, f + ((float)this.getWidth()), ((float)v2), ((float)v4));
        }
        Paint paint1 = textPaint0;
        if(EmojiCompat.get().isEmojiSpanIndicatorEnabled()) {
            canvas0.drawRect(f, ((float)v2), f + ((float)this.getWidth()), ((float)v4), TypefaceEmojiSpan.getDebugPaint());
        }
        TypefaceEmojiRasterizer typefaceEmojiRasterizer0 = this.getTypefaceRasterizer();
        if(paint1 == null) {
            paint1 = paint0;
        }
        typefaceEmojiRasterizer0.draw(canvas0, f, ((float)v3), paint1);
    }

    void drawBackground(Canvas canvas0, TextPaint textPaint0, float f, float f1, float f2, float f3) {
        int v = textPaint0.getColor();
        Paint.Style paint$Style0 = textPaint0.getStyle();
        textPaint0.setColor(textPaint0.bgColor);
        textPaint0.setStyle(Paint.Style.FILL);
        canvas0.drawRect(f, f2, f1, f3, textPaint0);
        textPaint0.setStyle(paint$Style0);
        textPaint0.setColor(v);
    }

    private static Paint getDebugPaint() {
        if(TypefaceEmojiSpan.sDebugPaint == null) {
            TextPaint textPaint0 = new TextPaint();
            TypefaceEmojiSpan.sDebugPaint = textPaint0;
            textPaint0.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
            TypefaceEmojiSpan.sDebugPaint.setStyle(Paint.Style.FILL);
        }
        return TypefaceEmojiSpan.sDebugPaint;
    }
}

