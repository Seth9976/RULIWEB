package androidx.transition;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
    private static final ArrayMap CONSTRUCTORS;
    private static final Class[] CONSTRUCTOR_SIGNATURE;
    private final Context mContext;

    static {
        TransitionInflater.CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
        TransitionInflater.CONSTRUCTORS = new ArrayMap();
    }

    private TransitionInflater(Context context0) {
        this.mContext = context0;
    }

    private Object createCustom(AttributeSet attributeSet0, Class class0, String s) {
        Constructor constructor0;
        String s1 = attributeSet0.getAttributeValue(null, "class");
        if(s1 != null) {
            try {
                ArrayMap arrayMap0 = TransitionInflater.CONSTRUCTORS;
                synchronized(arrayMap0) {
                    constructor0 = (Constructor)arrayMap0.get(s1);
                    if(constructor0 == null) {
                        Class class1 = Class.forName(s1, false, this.mContext.getClassLoader()).asSubclass(class0);
                        if(class1 != null) {
                            constructor0 = class1.getConstructor(TransitionInflater.CONSTRUCTOR_SIGNATURE);
                            constructor0.setAccessible(true);
                            arrayMap0.put(s1, constructor0);
                        }
                    }
                }
                return constructor0.newInstance(this.mContext, attributeSet0);
            }
            catch(Exception exception0) {
                throw new InflateException("Could not instantiate " + class0 + " class " + s1, exception0);
            }
        }
        throw new InflateException(s + " tag must have a \'class\' attribute");
    }

    private Transition createTransitionFromXml(XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Transition transition0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        TransitionSet transitionSet0 = transition0 instanceof TransitionSet ? ((TransitionSet)transition0) : null;
        while(true) {
            Transition transition1 = null;
            do {
            label_3:
                int v1 = xmlPullParser0.next();
                if(v1 == 3 && xmlPullParser0.getDepth() <= v || v1 == 1) {
                    return transition1;
                }
                if(v1 != 2) {
                    goto label_3;
                }
                String s = xmlPullParser0.getName();
                if("fade".equals(s)) {
                    transition1 = new Fade(this.mContext, attributeSet0);
                }
                else if("changeBounds".equals(s)) {
                    transition1 = new ChangeBounds(this.mContext, attributeSet0);
                }
                else if("slide".equals(s)) {
                    transition1 = new Slide(this.mContext, attributeSet0);
                }
                else if("explode".equals(s)) {
                    transition1 = new Explode(this.mContext, attributeSet0);
                }
                else if("changeImageTransform".equals(s)) {
                    transition1 = new ChangeImageTransform(this.mContext, attributeSet0);
                }
                else if("changeTransform".equals(s)) {
                    transition1 = new ChangeTransform(this.mContext, attributeSet0);
                }
                else if("changeClipBounds".equals(s)) {
                    transition1 = new ChangeClipBounds(this.mContext, attributeSet0);
                }
                else if("autoTransition".equals(s)) {
                    transition1 = new AutoTransition(this.mContext, attributeSet0);
                }
                else if("changeScroll".equals(s)) {
                    transition1 = new ChangeScroll(this.mContext, attributeSet0);
                }
                else if("transitionSet".equals(s)) {
                    transition1 = new TransitionSet(this.mContext, attributeSet0);
                }
                else if("transition".equals(s)) {
                    transition1 = (Transition)this.createCustom(attributeSet0, Transition.class, "transition");
                }
                else if("targets".equals(s)) {
                    this.getTargetIds(xmlPullParser0, attributeSet0, transition0);
                }
                else if("arcMotion".equals(s)) {
                    if(transition0 == null) {
                        throw new RuntimeException("Invalid use of arcMotion element");
                    }
                    transition0.setPathMotion(new ArcMotion(this.mContext, attributeSet0));
                    continue;
                }
                else if("pathMotion".equals(s)) {
                    if(transition0 == null) {
                        throw new RuntimeException("Invalid use of pathMotion element");
                    }
                    transition0.setPathMotion(((PathMotion)this.createCustom(attributeSet0, PathMotion.class, "pathMotion")));
                    continue;
                }
                else {
                    if(!"patternPathMotion".equals(s)) {
                        throw new RuntimeException("Unknown scene name: " + xmlPullParser0.getName());
                    }
                    if(transition0 == null) {
                        throw new RuntimeException("Invalid use of patternPathMotion element");
                    }
                    transition0.setPathMotion(new PatternPathMotion(this.mContext, attributeSet0));
                }
            }
            while(transition1 == null);
            if(!xmlPullParser0.isEmptyElementTag()) {
                this.createTransitionFromXml(xmlPullParser0, attributeSet0, transition1);
            }
            if(transitionSet0 == null) {
                break;
            }
            transitionSet0.addTransition(transition1);
        }
        if(transition0 != null) {
            throw new InflateException("Could not add transition to another transition.");
        }
        goto label_3;
    }

    private TransitionManager createTransitionManagerFromXml(XmlPullParser xmlPullParser0, AttributeSet attributeSet0, ViewGroup viewGroup0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        TransitionManager transitionManager0 = null;
        while(true) {
            int v1 = xmlPullParser0.next();
            if(v1 == 3 && xmlPullParser0.getDepth() <= v || v1 == 1) {
                break;
            }
            if(v1 == 2) {
                String s = xmlPullParser0.getName();
                if(s.equals("transitionManager")) {
                    transitionManager0 = new TransitionManager();
                }
                else {
                    if(!s.equals("transition") || transitionManager0 == null) {
                        throw new RuntimeException("Unknown scene name: " + xmlPullParser0.getName());
                    }
                    this.loadTransition(attributeSet0, xmlPullParser0, viewGroup0, transitionManager0);
                }
            }
        }
        return transitionManager0;
    }

    public static TransitionInflater from(Context context0) {
        return new TransitionInflater(context0);
    }

    private void getTargetIds(XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Transition transition0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        while(true) {
            int v1 = xmlPullParser0.next();
            if(v1 == 3 && xmlPullParser0.getDepth() <= v || v1 == 1) {
                break;
            }
            if(v1 == 2) {
                if(!xmlPullParser0.getName().equals("target")) {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser0.getName());
                }
                TypedArray typedArray0 = this.mContext.obtainStyledAttributes(attributeSet0, Styleable.TRANSITION_TARGET);
                int v2 = TypedArrayUtils.getNamedResourceId(typedArray0, xmlPullParser0, "targetId", 1, 0);
                if(v2 == 0) {
                    int v3 = TypedArrayUtils.getNamedResourceId(typedArray0, xmlPullParser0, "excludeId", 2, 0);
                    if(v3 == 0) {
                        String s = TypedArrayUtils.getNamedString(typedArray0, xmlPullParser0, "targetName", 4);
                        if(s == null) {
                            String s1 = TypedArrayUtils.getNamedString(typedArray0, xmlPullParser0, "excludeName", 5);
                            if(s1 == null) {
                                String s2 = TypedArrayUtils.getNamedString(typedArray0, xmlPullParser0, "excludeClass", 3);
                                try {
                                    if(s2 == null) {
                                        s2 = TypedArrayUtils.getNamedString(typedArray0, xmlPullParser0, "targetClass", 0);
                                        if(s2 != null) {
                                            transition0.addTarget(Class.forName(s2));
                                        }
                                    }
                                    else {
                                        transition0.excludeTarget(Class.forName(s2), true);
                                    }
                                }
                                catch(ClassNotFoundException classNotFoundException0) {
                                    typedArray0.recycle();
                                    throw new RuntimeException("Could not create " + s2, classNotFoundException0);
                                }
                            }
                            else {
                                transition0.excludeTarget(s1, true);
                            }
                        }
                        else {
                            transition0.addTarget(s);
                        }
                    }
                    else {
                        transition0.excludeTarget(v3, true);
                    }
                }
                else {
                    transition0.addTarget(v2);
                }
                typedArray0.recycle();
            }
        }
    }

    public Transition inflateTransition(int v) {
        try(XmlResourceParser xmlResourceParser0 = this.mContext.getResources().getXml(v)) {
            try {
                return this.createTransitionFromXml(xmlResourceParser0, Xml.asAttributeSet(xmlResourceParser0), null);
            }
            catch(XmlPullParserException xmlPullParserException0) {
                throw new InflateException(xmlPullParserException0.getMessage(), xmlPullParserException0);
            }
            catch(IOException iOException0) {
                throw new InflateException(xmlResourceParser0.getPositionDescription() + ": " + iOException0.getMessage(), iOException0);
            }
        }
    }

    public TransitionManager inflateTransitionManager(int v, ViewGroup viewGroup0) {
        try(XmlResourceParser xmlResourceParser0 = this.mContext.getResources().getXml(v)) {
            try {
                return this.createTransitionManagerFromXml(xmlResourceParser0, Xml.asAttributeSet(xmlResourceParser0), viewGroup0);
            }
            catch(XmlPullParserException xmlPullParserException0) {
                InflateException inflateException0 = new InflateException(xmlPullParserException0.getMessage());
                inflateException0.initCause(xmlPullParserException0);
                throw inflateException0;
            }
            catch(IOException iOException0) {
                InflateException inflateException1 = new InflateException(xmlResourceParser0.getPositionDescription() + ": " + iOException0.getMessage());
                inflateException1.initCause(iOException0);
                throw inflateException1;
            }
        }
    }

    private void loadTransition(AttributeSet attributeSet0, XmlPullParser xmlPullParser0, ViewGroup viewGroup0, TransitionManager transitionManager0) throws Resources.NotFoundException {
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(attributeSet0, Styleable.TRANSITION_MANAGER);
        int v = TypedArrayUtils.getNamedResourceId(typedArray0, xmlPullParser0, "transition", 2, -1);
        int v1 = TypedArrayUtils.getNamedResourceId(typedArray0, xmlPullParser0, "fromScene", 0, -1);
        Scene scene0 = null;
        Scene scene1 = v1 >= 0 ? Scene.getSceneForLayout(viewGroup0, v1, this.mContext) : null;
        int v2 = TypedArrayUtils.getNamedResourceId(typedArray0, xmlPullParser0, "toScene", 1, -1);
        if(v2 >= 0) {
            scene0 = Scene.getSceneForLayout(viewGroup0, v2, this.mContext);
        }
        if(v >= 0) {
            Transition transition0 = this.inflateTransition(v);
            if(transition0 != null) {
                if(scene0 == null) {
                    throw new RuntimeException("No toScene for transition ID " + v);
                }
                if(scene1 == null) {
                    transitionManager0.setTransition(scene0, transition0);
                }
                else {
                    transitionManager0.setTransition(scene1, scene0, transition0);
                }
            }
        }
        typedArray0.recycle();
    }
}

