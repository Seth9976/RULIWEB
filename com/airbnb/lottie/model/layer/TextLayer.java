package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData.Justification;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextLayer extends BaseLayer {
    private final LongSparseArray codePointCache;
    private BaseKeyframeAnimation colorAnimation;
    private final LottieComposition composition;
    private final Map contentsForCharacter;
    private final Paint fillPaint;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final RectF rectF;
    private final StringBuilder stringBuilder;
    private BaseKeyframeAnimation strokeColorAnimation;
    private final Paint strokePaint;
    private BaseKeyframeAnimation strokeWidthAnimation;
    private final TextKeyframeAnimation textAnimation;
    private BaseKeyframeAnimation trackingAnimation;

    TextLayer(LottieDrawable lottieDrawable0, Layer layer0) {
        super(lottieDrawable0, layer0);
        this.stringBuilder = new StringBuilder(2);
        this.rectF = new RectF();
        this.matrix = new Matrix();
        this.fillPaint = new Paint(1) {
            {
                int v = 1;  // 捕获的参数
                this.setStyle(Paint.Style.FILL);
            }
        };
        this.strokePaint = new Paint(1) {
            {
                int v = 1;  // 捕获的参数
                this.setStyle(Paint.Style.STROKE);
            }
        };
        this.contentsForCharacter = new HashMap();
        this.codePointCache = new LongSparseArray();
        this.lottieDrawable = lottieDrawable0;
        this.composition = layer0.getComposition();
        TextKeyframeAnimation textKeyframeAnimation0 = layer0.getText().createAnimation();
        this.textAnimation = textKeyframeAnimation0;
        textKeyframeAnimation0.addUpdateListener(this);
        this.addAnimation(textKeyframeAnimation0);
        AnimatableTextProperties animatableTextProperties0 = layer0.getTextProperties();
        if(animatableTextProperties0 != null && animatableTextProperties0.color != null) {
            BaseKeyframeAnimation baseKeyframeAnimation0 = animatableTextProperties0.color.createAnimation();
            this.colorAnimation = baseKeyframeAnimation0;
            baseKeyframeAnimation0.addUpdateListener(this);
            this.addAnimation(this.colorAnimation);
        }
        if(animatableTextProperties0 != null && animatableTextProperties0.stroke != null) {
            BaseKeyframeAnimation baseKeyframeAnimation1 = animatableTextProperties0.stroke.createAnimation();
            this.strokeColorAnimation = baseKeyframeAnimation1;
            baseKeyframeAnimation1.addUpdateListener(this);
            this.addAnimation(this.strokeColorAnimation);
        }
        if(animatableTextProperties0 != null && animatableTextProperties0.strokeWidth != null) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = animatableTextProperties0.strokeWidth.createAnimation();
            this.strokeWidthAnimation = baseKeyframeAnimation2;
            baseKeyframeAnimation2.addUpdateListener(this);
            this.addAnimation(this.strokeWidthAnimation);
        }
        if(animatableTextProperties0 != null && animatableTextProperties0.tracking != null) {
            BaseKeyframeAnimation baseKeyframeAnimation3 = animatableTextProperties0.tracking.createAnimation();
            this.trackingAnimation = baseKeyframeAnimation3;
            baseKeyframeAnimation3.addUpdateListener(this);
            this.addAnimation(this.trackingAnimation);
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void addValueCallback(Object object0, LottieValueCallback lottieValueCallback0) {
        super.addValueCallback(object0, lottieValueCallback0);
        if(object0 == LottieProperty.COLOR) {
            BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorAnimation;
            if(baseKeyframeAnimation0 != null) {
                baseKeyframeAnimation0.setValueCallback(lottieValueCallback0);
                return;
            }
            if(lottieValueCallback0 == null) {
                this.colorAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation0 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.colorAnimation = valueCallbackKeyframeAnimation0;
            valueCallbackKeyframeAnimation0.addUpdateListener(this);
            this.addAnimation(this.colorAnimation);
            return;
        }
        if(object0 == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation baseKeyframeAnimation1 = this.strokeColorAnimation;
            if(baseKeyframeAnimation1 != null) {
                baseKeyframeAnimation1.setValueCallback(lottieValueCallback0);
                return;
            }
            if(lottieValueCallback0 == null) {
                this.strokeColorAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation1 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.strokeColorAnimation = valueCallbackKeyframeAnimation1;
            valueCallbackKeyframeAnimation1.addUpdateListener(this);
            this.addAnimation(this.strokeColorAnimation);
            return;
        }
        if(object0 == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.strokeWidthAnimation;
            if(baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(lottieValueCallback0);
                return;
            }
            if(lottieValueCallback0 == null) {
                this.strokeWidthAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.strokeWidthAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.addAnimation(this.strokeWidthAnimation);
            return;
        }
        if(object0 == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation baseKeyframeAnimation3 = this.trackingAnimation;
            if(baseKeyframeAnimation3 != null) {
                baseKeyframeAnimation3.setValueCallback(lottieValueCallback0);
                return;
            }
            if(lottieValueCallback0 == null) {
                this.trackingAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback0);
            this.trackingAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            this.addAnimation(this.trackingAnimation);
        }
    }

    private void applyJustification(Justification documentData$Justification0, Canvas canvas0, float f) {
        switch(com.airbnb.lottie.model.layer.TextLayer.3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[documentData$Justification0.ordinal()]) {
            case 2: {
                canvas0.translate(-f, 0.0f);
                return;
            }
            case 3: {
                canvas0.translate(-f / 2.0f, 0.0f);
            }
        }
    }

    private String codePointToString(String s, int v) {
        int v1 = s.codePointAt(v);
        int v2 = Character.charCount(v1) + v;
        while(v2 < s.length()) {
            int v3 = s.codePointAt(v2);
            if(!this.isModifier(v3)) {
                break;
            }
            v2 += Character.charCount(v3);
            v1 = v1 * 0x1F + v3;
        }
        if(this.codePointCache.containsKey(((long)v1))) {
            return (String)this.codePointCache.get(((long)v1));
        }
        this.stringBuilder.setLength(0);
        while(v < v2) {
            int v4 = s.codePointAt(v);
            this.stringBuilder.appendCodePoint(v4);
            v += Character.charCount(v4);
        }
        String s1 = this.stringBuilder.toString();
        this.codePointCache.put(((long)v1), s1);
        return s1;
    }

    private void drawCharacter(String s, Paint paint0, Canvas canvas0) {
        if(paint0.getColor() == 0 || paint0.getStyle() == Paint.Style.STROKE && paint0.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas0.drawText(s, 0, s.length(), 0.0f, 0.0f, paint0);
    }

    private void drawCharacterAsGlyph(FontCharacter fontCharacter0, Matrix matrix0, float f, DocumentData documentData0, Canvas canvas0) {
        List list0 = this.getContentsForCharacter(fontCharacter0);
        for(int v = 0; v < list0.size(); ++v) {
            Path path0 = ((ContentGroup)list0.get(v)).getPath();
            path0.computeBounds(this.rectF, false);
            this.matrix.set(matrix0);
            float f1 = Utils.dpScale();
            this.matrix.preTranslate(0.0f, ((float)(-documentData0.baselineShift)) * f1);
            this.matrix.preScale(f, f);
            path0.transform(this.matrix);
            if(documentData0.strokeOverFill) {
                this.drawGlyph(path0, this.fillPaint, canvas0);
                this.drawGlyph(path0, this.strokePaint, canvas0);
            }
            else {
                this.drawGlyph(path0, this.strokePaint, canvas0);
                this.drawGlyph(path0, this.fillPaint, canvas0);
            }
        }
    }

    private void drawCharacterFromFont(String s, DocumentData documentData0, Canvas canvas0) {
        if(documentData0.strokeOverFill) {
            this.drawCharacter(s, this.fillPaint, canvas0);
            this.drawCharacter(s, this.strokePaint, canvas0);
            return;
        }
        this.drawCharacter(s, this.strokePaint, canvas0);
        this.drawCharacter(s, this.fillPaint, canvas0);
    }

    private void drawFontTextLine(String s, DocumentData documentData0, Canvas canvas0, float f) {
        int v = 0;
        while(v < s.length()) {
            String s1 = this.codePointToString(s, v);
            v += s1.length();
            this.drawCharacterFromFont(s1, documentData0, canvas0);
            float f1 = this.fillPaint.measureText(s1, 0, 1);
            float f2 = ((float)documentData0.tracking) / 10.0f;
            BaseKeyframeAnimation baseKeyframeAnimation0 = this.trackingAnimation;
            if(baseKeyframeAnimation0 != null) {
                f2 += (float)(((Float)baseKeyframeAnimation0.getValue()));
            }
            canvas0.translate(f1 + f2 * f, 0.0f);
        }
    }

    private void drawGlyph(Path path0, Paint paint0, Canvas canvas0) {
        if(paint0.getColor() == 0 || paint0.getStyle() == Paint.Style.STROKE && paint0.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas0.drawPath(path0, paint0);
    }

    private void drawGlyphTextLine(String s, DocumentData documentData0, Matrix matrix0, Font font0, Canvas canvas0, float f, float f1) {
        for(int v = 0; v < s.length(); ++v) {
            FontCharacter fontCharacter0 = (FontCharacter)this.composition.getCharacters().get(FontCharacter.hashFor(s.charAt(v), font0.getFamily(), font0.getStyle()));
            if(fontCharacter0 != null) {
                this.drawCharacterAsGlyph(fontCharacter0, matrix0, f1, documentData0, canvas0);
                float f2 = ((float)fontCharacter0.getWidth()) * f1;
                float f3 = Utils.dpScale();
                float f4 = ((float)documentData0.tracking) / 10.0f;
                BaseKeyframeAnimation baseKeyframeAnimation0 = this.trackingAnimation;
                if(baseKeyframeAnimation0 != null) {
                    f4 += (float)(((Float)baseKeyframeAnimation0.getValue()));
                }
                canvas0.translate(f2 * f3 * f + f4 * f, 0.0f);
            }
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    void drawLayer(Canvas canvas0, Matrix matrix0, int v) {
        canvas0.save();
        if(!this.lottieDrawable.useTextGlyphs()) {
            canvas0.setMatrix(matrix0);
        }
        DocumentData documentData0 = (DocumentData)this.textAnimation.getValue();
        Font font0 = (Font)this.composition.getFonts().get(documentData0.fontName);
        if(font0 == null) {
            canvas0.restore();
            return;
        }
        BaseKeyframeAnimation baseKeyframeAnimation0 = this.colorAnimation;
        if(baseKeyframeAnimation0 == null) {
            this.fillPaint.setColor(documentData0.color);
        }
        else {
            int v1 = (int)(((Integer)baseKeyframeAnimation0.getValue()));
            this.fillPaint.setColor(v1);
        }
        BaseKeyframeAnimation baseKeyframeAnimation1 = this.strokeColorAnimation;
        if(baseKeyframeAnimation1 == null) {
            this.strokePaint.setColor(documentData0.strokeColor);
        }
        else {
            int v2 = (int)(((Integer)baseKeyframeAnimation1.getValue()));
            this.strokePaint.setColor(v2);
        }
        int v3 = (this.transform.getOpacity() == null ? 100 : ((int)(((Integer)this.transform.getOpacity().getValue())))) * 0xFF / 100;
        this.fillPaint.setAlpha(v3);
        this.strokePaint.setAlpha(v3);
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.strokeWidthAnimation;
        if(baseKeyframeAnimation2 == null) {
            float f1 = Utils.getScale(matrix0);
            double f2 = (double)Utils.dpScale();
            this.strokePaint.setStrokeWidth(((float)(documentData0.strokeWidth * f2 * ((double)f1))));
        }
        else {
            float f = (float)(((Float)baseKeyframeAnimation2.getValue()));
            this.strokePaint.setStrokeWidth(f);
        }
        if(this.lottieDrawable.useTextGlyphs()) {
            this.drawTextGlyphs(documentData0, matrix0, font0, canvas0);
        }
        else {
            this.drawTextWithFont(documentData0, font0, matrix0, canvas0);
        }
        canvas0.restore();
    }

    private void drawTextGlyphs(DocumentData documentData0, Matrix matrix0, Font font0, Canvas canvas0) {
        float f = ((float)documentData0.size) / 100.0f;
        float f1 = Utils.getScale(matrix0);
        float f2 = Utils.dpScale();
        float f3 = ((float)documentData0.lineHeight) * f2;
        List list0 = this.getTextLines(documentData0.text);
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = list0.get(v1);
            float f4 = this.getTextLineWidthForGlyphs(((String)object0), font0, f, f1);
            canvas0.save();
            this.applyJustification(documentData0.justification, canvas0, f4);
            canvas0.translate(0.0f, ((float)v1) * f3 - ((float)(v - 1)) * f3 / 2.0f);
            this.drawGlyphTextLine(((String)object0), documentData0, matrix0, font0, canvas0, f1, f);
            canvas0.restore();
        }
    }

    private void drawTextWithFont(DocumentData documentData0, Font font0, Matrix matrix0, Canvas canvas0) {
        float f = Utils.getScale(matrix0);
        Typeface typeface0 = this.lottieDrawable.getTypeface(font0.getFamily(), font0.getStyle());
        if(typeface0 != null) {
            String s = documentData0.text;
            TextDelegate textDelegate0 = this.lottieDrawable.getTextDelegate();
            if(textDelegate0 != null) {
                s = textDelegate0.getTextInternal(s);
            }
            this.fillPaint.setTypeface(typeface0);
            double f1 = (double)Utils.dpScale();
            this.fillPaint.setTextSize(((float)(documentData0.size * f1)));
            Typeface typeface1 = this.fillPaint.getTypeface();
            this.strokePaint.setTypeface(typeface1);
            float f2 = this.fillPaint.getTextSize();
            this.strokePaint.setTextSize(f2);
            float f3 = Utils.dpScale();
            float f4 = ((float)documentData0.lineHeight) * f3;
            List list0 = this.getTextLines(s);
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                String s1 = (String)list0.get(v1);
                float f5 = this.strokePaint.measureText(s1);
                this.applyJustification(documentData0.justification, canvas0, f5);
                canvas0.translate(0.0f, ((float)v1) * f4 - ((float)(v - 1)) * f4 / 2.0f);
                this.drawFontTextLine(s1, documentData0, canvas0, f);
                canvas0.setMatrix(matrix0);
            }
        }
    }

    @Override  // com.airbnb.lottie.model.layer.BaseLayer
    public void getBounds(RectF rectF0, Matrix matrix0, boolean z) {
        super.getBounds(rectF0, matrix0, z);
        rectF0.set(0.0f, 0.0f, ((float)this.composition.getBounds().width()), ((float)this.composition.getBounds().height()));
    }

    private List getContentsForCharacter(FontCharacter fontCharacter0) {
        if(this.contentsForCharacter.containsKey(fontCharacter0)) {
            return (List)this.contentsForCharacter.get(fontCharacter0);
        }
        List list0 = fontCharacter0.getShapes();
        int v = list0.size();
        List list1 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            ShapeGroup shapeGroup0 = (ShapeGroup)list0.get(v1);
            list1.add(new ContentGroup(this.lottieDrawable, this, shapeGroup0));
        }
        this.contentsForCharacter.put(fontCharacter0, list1);
        return list1;
    }

    private float getTextLineWidthForGlyphs(String s, Font font0, float f, float f1) {
        float f2 = 0.0f;
        for(int v = 0; v < s.length(); ++v) {
            FontCharacter fontCharacter0 = (FontCharacter)this.composition.getCharacters().get(FontCharacter.hashFor(s.charAt(v), font0.getFamily(), font0.getStyle()));
            if(fontCharacter0 != null) {
                f2 = (float)(((double)f2) + fontCharacter0.getWidth() * ((double)f) * ((double)Utils.dpScale()) * ((double)f1));
            }
        }
        return f2;
    }

    private List getTextLines(String s) {
        return Arrays.asList(s.replaceAll("\r\n", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private boolean isModifier(int v) {
        return Character.getType(v) == 6 || Character.getType(v) == 16 || Character.getType(v) == 19 || Character.getType(v) == 27 || Character.getType(v) == 28;
    }
}

