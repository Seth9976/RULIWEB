package androidx.emoji2.text;

import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.core.util.Preconditions;

public abstract class EmojiSpan extends ReplacementSpan {
    private short mHeight;
    private final TypefaceEmojiRasterizer mRasterizer;
    private float mRatio;
    private final Paint.FontMetricsInt mTmpFontMetrics;
    private short mWidth;

    EmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
        this.mTmpFontMetrics = new Paint.FontMetricsInt();
        this.mWidth = -1;
        this.mHeight = -1;
        this.mRatio = 1.0f;
        Preconditions.checkNotNull(typefaceEmojiRasterizer0, "rasterizer cannot be null");
        this.mRasterizer = typefaceEmojiRasterizer0;
    }

    public final int getHeight() {
        return this.mHeight;
    }

    public final int getId() {
        return this.getTypefaceRasterizer().getId();
    }

    final float getRatio() {
        return this.mRatio;
    }

    @Override  // android.text.style.ReplacementSpan
    public int getSize(Paint paint0, CharSequence charSequence0, int v, int v1, Paint.FontMetricsInt paint$FontMetricsInt0) {
        paint0.getFontMetricsInt(this.mTmpFontMetrics);
        this.mRatio = ((float)Math.abs(this.mTmpFontMetrics.descent - this.mTmpFontMetrics.ascent)) * 1.0f / ((float)this.mRasterizer.getHeight());
        this.mHeight = (short)(((int)(((float)this.mRasterizer.getHeight()) * this.mRatio)));
        this.mWidth = (short)(((int)(((float)this.mRasterizer.getWidth()) * this.mRatio)));
        if(paint$FontMetricsInt0 != null) {
            paint$FontMetricsInt0.ascent = this.mTmpFontMetrics.ascent;
            paint$FontMetricsInt0.descent = this.mTmpFontMetrics.descent;
            paint$FontMetricsInt0.top = this.mTmpFontMetrics.top;
            paint$FontMetricsInt0.bottom = this.mTmpFontMetrics.bottom;
        }
        return this.mWidth;
    }

    public final TypefaceEmojiRasterizer getTypefaceRasterizer() {
        return this.mRasterizer;
    }

    final int getWidth() {
        return this.mWidth;
    }
}

