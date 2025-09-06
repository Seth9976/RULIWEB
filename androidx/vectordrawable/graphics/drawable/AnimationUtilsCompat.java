package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimationUtilsCompat {
    private static Interpolator createInterpolatorFromXml(Context context0, Resources resources0, Resources.Theme resources$Theme0, XmlPullParser xmlPullParser0) throws XmlPullParserException, IOException {
        AccelerateInterpolator accelerateInterpolator0;
        int v = xmlPullParser0.getDepth();
        Interpolator interpolator0 = null;
        while(true) {
            int v1 = xmlPullParser0.next();
            if(v1 == 3 && xmlPullParser0.getDepth() <= v || v1 == 1) {
                break;
            }
            if(v1 == 2) {
                AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
                String s = xmlPullParser0.getName();
                if(s.equals("linearInterpolator")) {
                    interpolator0 = new LinearInterpolator();
                }
                else {
                    if(s.equals("accelerateInterpolator")) {
                        accelerateInterpolator0 = new AccelerateInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("decelerateInterpolator")) {
                        accelerateInterpolator0 = new DecelerateInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("accelerateDecelerateInterpolator")) {
                        interpolator0 = new AccelerateDecelerateInterpolator();
                        continue;
                    }
                    else if(s.equals("cycleInterpolator")) {
                        accelerateInterpolator0 = new CycleInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("anticipateInterpolator")) {
                        accelerateInterpolator0 = new AnticipateInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("overshootInterpolator")) {
                        accelerateInterpolator0 = new OvershootInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("anticipateOvershootInterpolator")) {
                        accelerateInterpolator0 = new AnticipateOvershootInterpolator(context0, attributeSet0);
                    }
                    else if(s.equals("bounceInterpolator")) {
                        interpolator0 = new BounceInterpolator();
                        continue;
                    }
                    else {
                        if(!s.equals("pathInterpolator")) {
                            throw new RuntimeException("Unknown interpolator name: " + xmlPullParser0.getName());
                        }
                        accelerateInterpolator0 = new PathInterpolatorCompat(context0, attributeSet0, xmlPullParser0);
                    }
                    interpolator0 = accelerateInterpolator0;
                }
            }
        }
        return interpolator0;
    }

    public static Interpolator loadInterpolator(Context context0, int v) throws Resources.NotFoundException {
        return AnimationUtils.loadInterpolator(context0, v);
    }
}

