package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ComplexColorCompat {
    private static final String LOG_TAG = "ComplexColorCompat";
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private ComplexColorCompat(Shader shader0, ColorStateList colorStateList0, int v) {
        this.mShader = shader0;
        this.mColorStateList = colorStateList0;
        this.mColor = v;
    }

    private static ComplexColorCompat createFromXml(Resources resources0, int v, Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
        XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
        do {
            int v1 = xmlResourceParser0.next();
        }
        while(v1 != 1 && v1 != 2);
        if(v1 != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String s = xmlResourceParser0.getName();
        s.hashCode();
        if(!s.equals("gradient")) {
            if(!s.equals("selector")) {
                throw new XmlPullParserException(xmlResourceParser0.getPositionDescription() + ": unsupported complex color tag " + s);
            }
            return ComplexColorCompat.from(ColorStateListInflaterCompat.createFromXmlInner(resources0, xmlResourceParser0, attributeSet0, resources$Theme0));
        }
        return ComplexColorCompat.from(GradientColorInflaterCompat.createFromXmlInner(resources0, xmlResourceParser0, attributeSet0, resources$Theme0));
    }

    static ComplexColorCompat from(int v) {
        return new ComplexColorCompat(null, null, v);
    }

    static ComplexColorCompat from(ColorStateList colorStateList0) {
        return new ComplexColorCompat(null, colorStateList0, colorStateList0.getDefaultColor());
    }

    static ComplexColorCompat from(Shader shader0) {
        return new ComplexColorCompat(shader0, null, 0);
    }

    public int getColor() {
        return this.mColor;
    }

    public Shader getShader() {
        return this.mShader;
    }

    public static ComplexColorCompat inflate(Resources resources0, int v, Resources.Theme resources$Theme0) {
        try {
            return ComplexColorCompat.createFromXml(resources0, v, resources$Theme0);
        }
        catch(Exception exception0) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", exception0);
            return null;
        }
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        return this.mShader == null && (this.mColorStateList != null && this.mColorStateList.isStateful());
    }

    public boolean onStateChanged(int[] arr_v) {
        if(this.isStateful()) {
            int v = this.mColorStateList.getDefaultColor();
            int v1 = this.mColorStateList.getColorForState(arr_v, v);
            if(v1 != this.mColor) {
                this.mColor = v1;
                return true;
            }
        }
        return false;
    }

    public void setColor(int v) {
        this.mColor = v;
    }

    public boolean willDraw() {
        return this.isGradient() || this.mColor != 0;
    }
}

