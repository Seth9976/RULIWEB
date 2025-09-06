package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

public class TextDrawableHelper {
    public interface TextDrawableDelegate {
        int[] getState();

        boolean onStateChange(int[] arg1);

        void onTextSizeChange();
    }

    private WeakReference delegate;
    private final TextAppearanceFontCallback fontCallback;
    private TextAppearance textAppearance;
    private float textHeight;
    private final TextPaint textPaint;
    private boolean textSizeDirty;
    private float textWidth;

    public TextDrawableHelper(TextDrawableDelegate textDrawableHelper$TextDrawableDelegate0) {
        this.textPaint = new TextPaint(1);
        this.fontCallback = new TextAppearanceFontCallback() {
            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrievalFailed(int v) {
                TextDrawableHelper.this.textSizeDirty = true;
                TextDrawableDelegate textDrawableHelper$TextDrawableDelegate0 = (TextDrawableDelegate)TextDrawableHelper.this.delegate.get();
                if(textDrawableHelper$TextDrawableDelegate0 != null) {
                    textDrawableHelper$TextDrawableDelegate0.onTextSizeChange();
                }
            }

            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrieved(Typeface typeface0, boolean z) {
                if(!z) {
                    TextDrawableHelper.this.textSizeDirty = true;
                    TextDrawableDelegate textDrawableHelper$TextDrawableDelegate0 = (TextDrawableDelegate)TextDrawableHelper.this.delegate.get();
                    if(textDrawableHelper$TextDrawableDelegate0 != null) {
                        textDrawableHelper$TextDrawableDelegate0.onTextSizeChange();
                    }
                }
            }
        };
        this.textSizeDirty = true;
        this.delegate = new WeakReference(null);
        this.setDelegate(textDrawableHelper$TextDrawableDelegate0);
    }

    private float calculateTextHeight(String s) {
        return s == null ? 0.0f : Math.abs(this.textPaint.getFontMetrics().ascent);
    }

    private float calculateTextWidth(CharSequence charSequence0) {
        if(charSequence0 == null) {
            return 0.0f;
        }
        int v = charSequence0.length();
        return this.textPaint.measureText(charSequence0, 0, v);
    }

    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public float getTextHeight(String s) {
        if(!this.textSizeDirty) {
            return this.textHeight;
        }
        this.refreshTextDimens(s);
        return this.textHeight;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public float getTextWidth(String s) {
        if(!this.textSizeDirty) {
            return this.textWidth;
        }
        this.refreshTextDimens(s);
        return this.textWidth;
    }

    public boolean isTextWidthDirty() {
        return this.textSizeDirty;
    }

    private void refreshTextDimens(String s) {
        this.textWidth = this.calculateTextWidth(s);
        this.textHeight = this.calculateTextHeight(s);
        this.textSizeDirty = false;
    }

    public void setDelegate(TextDrawableDelegate textDrawableHelper$TextDrawableDelegate0) {
        this.delegate = new WeakReference(textDrawableHelper$TextDrawableDelegate0);
    }

    public void setTextAppearance(TextAppearance textAppearance0, Context context0) {
        if(this.textAppearance != textAppearance0) {
            this.textAppearance = textAppearance0;
            if(textAppearance0 != null) {
                textAppearance0.updateMeasureState(context0, this.textPaint, this.fontCallback);
                TextDrawableDelegate textDrawableHelper$TextDrawableDelegate0 = (TextDrawableDelegate)this.delegate.get();
                if(textDrawableHelper$TextDrawableDelegate0 != null) {
                    this.textPaint.drawableState = textDrawableHelper$TextDrawableDelegate0.getState();
                }
                textAppearance0.updateDrawState(context0, this.textPaint, this.fontCallback);
                this.textSizeDirty = true;
            }
            TextDrawableDelegate textDrawableHelper$TextDrawableDelegate1 = (TextDrawableDelegate)this.delegate.get();
            if(textDrawableHelper$TextDrawableDelegate1 != null) {
                textDrawableHelper$TextDrawableDelegate1.onTextSizeChange();
                textDrawableHelper$TextDrawableDelegate1.onStateChange(textDrawableHelper$TextDrawableDelegate1.getState());
            }
        }
    }

    public void setTextSizeDirty(boolean z) {
        this.textSizeDirty = z;
    }

    public void setTextWidthDirty(boolean z) {
        this.textSizeDirty = z;
    }

    public void updateTextPaintDrawState(Context context0) {
        this.textAppearance.updateDrawState(context0, this.textPaint, this.fontCallback);
    }
}

