package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.content.res.ResourcesCompat;
import com.google.android.material.R.styleable;

public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    private Typeface font;
    public final String fontFamily;
    private final int fontFamilyResourceId;
    private boolean fontResolved;
    public final boolean hasLetterSpacing;
    public final float letterSpacing;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    private ColorStateList textColor;
    public final ColorStateList textColorHint;
    public final ColorStateList textColorLink;
    private float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context0, int v) {
        this.fontResolved = false;
        TypedArray typedArray0 = context0.obtainStyledAttributes(v, styleable.TextAppearance);
        this.setTextSize(typedArray0.getDimension(styleable.TextAppearance_android_textSize, 0.0f));
        this.setTextColor(MaterialResources.getColorStateList(context0, typedArray0, styleable.TextAppearance_android_textColor));
        this.textColorHint = MaterialResources.getColorStateList(context0, typedArray0, styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context0, typedArray0, styleable.TextAppearance_android_textColorLink);
        this.textStyle = typedArray0.getInt(styleable.TextAppearance_android_textStyle, 0);
        this.typeface = typedArray0.getInt(styleable.TextAppearance_android_typeface, 1);
        int v1 = MaterialResources.getIndexWithValue(typedArray0, styleable.TextAppearance_fontFamily, styleable.TextAppearance_android_fontFamily);
        this.fontFamilyResourceId = typedArray0.getResourceId(v1, 0);
        this.fontFamily = typedArray0.getString(v1);
        this.textAllCaps = typedArray0.getBoolean(styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context0, typedArray0, styleable.TextAppearance_android_shadowColor);
        this.shadowDx = typedArray0.getFloat(styleable.TextAppearance_android_shadowDx, 0.0f);
        this.shadowDy = typedArray0.getFloat(styleable.TextAppearance_android_shadowDy, 0.0f);
        this.shadowRadius = typedArray0.getFloat(styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArray0.recycle();
        TypedArray typedArray1 = context0.obtainStyledAttributes(v, styleable.MaterialTextAppearance);
        this.hasLetterSpacing = typedArray1.hasValue(styleable.MaterialTextAppearance_android_letterSpacing);
        this.letterSpacing = typedArray1.getFloat(styleable.MaterialTextAppearance_android_letterSpacing, 0.0f);
        typedArray1.recycle();
    }

    private void createFallbackFont() {
        if(this.font == null) {
            String s = this.fontFamily;
            if(s != null) {
                this.font = Typeface.create(s, this.textStyle);
            }
        }
        if(this.font == null) {
            switch(this.typeface) {
                case 1: {
                    this.font = Typeface.SANS_SERIF;
                    break;
                }
                case 2: {
                    this.font = Typeface.SERIF;
                    break;
                }
                case 3: {
                    this.font = Typeface.MONOSPACE;
                    break;
                }
                default: {
                    this.font = Typeface.DEFAULT;
                }
            }
            this.font = Typeface.create(this.font, this.textStyle);
        }
    }

    public Typeface getFallbackFont() {
        this.createFallbackFont();
        return this.font;
    }

    public Typeface getFont(Context context0) {
        if(this.fontResolved) {
            return this.font;
        }
        if(!context0.isRestricted()) {
            try {
                Typeface typeface0 = ResourcesCompat.getFont(context0, this.fontFamilyResourceId);
                this.font = typeface0;
                if(typeface0 != null) {
                    this.font = Typeface.create(typeface0, this.textStyle);
                }
                goto label_10;
            }
            catch(UnsupportedOperationException exception0) {
            }
            catch(Resources.NotFoundException | Exception unused_ex) {
                goto label_10;
            }
            Log.d("TextAppearance", "Error loading font " + this.fontFamily, exception0);
        }
    label_10:
        this.createFallbackFont();
        this.fontResolved = true;
        return this.font;
    }

    public void getFontAsync(Context context0, TextPaint textPaint0, TextAppearanceFontCallback textAppearanceFontCallback0) {
        this.updateTextPaintMeasureState(context0, textPaint0, this.getFallbackFont());
        this.getFontAsync(context0, new TextAppearanceFontCallback() {
            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrievalFailed(int v) {
                textAppearanceFontCallback0.onFontRetrievalFailed(v);
            }

            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrieved(Typeface typeface0, boolean z) {
                TextAppearance.this.updateTextPaintMeasureState(context0, textPaint0, typeface0);
                textAppearanceFontCallback0.onFontRetrieved(typeface0, z);
            }
        });
    }

    public void getFontAsync(Context context0, TextAppearanceFontCallback textAppearanceFontCallback0) {
        if(this.shouldLoadFontSynchronously(context0)) {
            this.getFont(context0);
        }
        else {
            this.createFallbackFont();
        }
        int v = this.fontFamilyResourceId;
        if(v == 0) {
            this.fontResolved = true;
        }
        if(this.fontResolved) {
            textAppearanceFontCallback0.onFontRetrieved(this.font, true);
            return;
        }
        try {
            ResourcesCompat.getFont(context0, v, new FontCallback() {
                @Override  // androidx.core.content.res.ResourcesCompat$FontCallback
                public void onFontRetrievalFailed(int v) {
                    TextAppearance.this.fontResolved = true;
                    textAppearanceFontCallback0.onFontRetrievalFailed(v);
                }

                @Override  // androidx.core.content.res.ResourcesCompat$FontCallback
                public void onFontRetrieved(Typeface typeface0) {
                    Typeface typeface1 = Typeface.create(typeface0, TextAppearance.this.textStyle);
                    TextAppearance.this.font = typeface1;
                    TextAppearance.this.fontResolved = true;
                    Typeface typeface2 = TextAppearance.this.font;
                    textAppearanceFontCallback0.onFontRetrieved(typeface2, false);
                }
            }, null);
        }
        catch(Resources.NotFoundException unused_ex) {
            this.fontResolved = true;
            textAppearanceFontCallback0.onFontRetrievalFailed(1);
        }
        catch(Exception exception0) {
            Log.d("TextAppearance", "Error loading font " + this.fontFamily, exception0);
            this.fontResolved = true;
            textAppearanceFontCallback0.onFontRetrievalFailed(-3);
        }
    }

    public ColorStateList getTextColor() {
        return this.textColor;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextColor(ColorStateList colorStateList0) {
        this.textColor = colorStateList0;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    // 去混淆评级： 低(30)
    private boolean shouldLoadFontSynchronously(Context context0) {
        return (this.fontFamilyResourceId == 0 ? null : ResourcesCompat.getCachedFont(context0, this.fontFamilyResourceId)) != null;
    }

    public void updateDrawState(Context context0, TextPaint textPaint0, TextAppearanceFontCallback textAppearanceFontCallback0) {
        this.updateMeasureState(context0, textPaint0, textAppearanceFontCallback0);
        textPaint0.setColor((this.textColor == null ? 0xFF000000 : this.textColor.getColorForState(textPaint0.drawableState, this.textColor.getDefaultColor())));
        int v = this.shadowColor == null ? 0 : this.shadowColor.getColorForState(textPaint0.drawableState, this.shadowColor.getDefaultColor());
        textPaint0.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, v);
    }

    public void updateMeasureState(Context context0, TextPaint textPaint0, TextAppearanceFontCallback textAppearanceFontCallback0) {
        if(this.shouldLoadFontSynchronously(context0)) {
            this.updateTextPaintMeasureState(context0, textPaint0, this.getFont(context0));
            return;
        }
        this.getFontAsync(context0, textPaint0, textAppearanceFontCallback0);
    }

    public void updateTextPaintMeasureState(Context context0, TextPaint textPaint0, Typeface typeface0) {
        Typeface typeface1 = TypefaceUtils.maybeCopyWithFontWeightAdjustment(context0, typeface0);
        if(typeface1 != null) {
            typeface0 = typeface1;
        }
        textPaint0.setTypeface(typeface0);
        int v = typeface0.getStyle();
        int v1 = this.textStyle & ~v;
        textPaint0.setFakeBoldText((v1 & 1) != 0);
        textPaint0.setTextSkewX(((v1 & 2) == 0 ? 0.0f : -0.25f));
        textPaint0.setTextSize(this.textSize);
        if(this.hasLetterSpacing) {
            textPaint0.setLetterSpacing(this.letterSpacing);
        }
    }
}

