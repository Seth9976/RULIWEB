package androidx.appcompat.resources;

import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Compatibility {
    public static class Api21Impl {
        public static Drawable createFromXmlInner(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
            return Drawable.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        }

        public static int getChangingConfigurations(TypedArray typedArray0) {
            return typedArray0.getChangingConfigurations();
        }

        public static void inflate(Drawable drawable0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
            drawable0.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        }
    }

}

