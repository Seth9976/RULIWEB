package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class PreferenceInflater {
    private static final HashMap CONSTRUCTOR_MAP = null;
    private static final Class[] CONSTRUCTOR_SIGNATURE = null;
    private static final String EXTRA_TAG_NAME = "extra";
    private static final String INTENT_TAG_NAME = "intent";
    private final Object[] mConstructorArgs;
    private final Context mContext;
    private String[] mDefaultPackages;
    private PreferenceManager mPreferenceManager;

    static {
        PreferenceInflater.CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
        PreferenceInflater.CONSTRUCTOR_MAP = new HashMap();
    }

    public PreferenceInflater(Context context0, PreferenceManager preferenceManager0) {
        this.mConstructorArgs = new Object[2];
        this.mContext = context0;
        this.init(preferenceManager0);
    }

    private Preference createItem(String s, String[] arr_s, AttributeSet attributeSet0) throws ClassNotFoundException, InflateException {
        Class class0;
        Constructor constructor0 = (Constructor)PreferenceInflater.CONSTRUCTOR_MAP.get(s);
        try {
            if(constructor0 == null) {
                ClassLoader classLoader0 = this.mContext.getClassLoader();
                if(arr_s != null && arr_s.length != 0) {
                    class0 = null;
                    ClassNotFoundException classNotFoundException1 = null;
                    int v = 0;
                    while(v < arr_s.length) {
                        String s1 = arr_s[v];
                        try {
                            class0 = Class.forName((s1 + s), false, classLoader0);
                            break;
                        }
                        catch(ClassNotFoundException classNotFoundException1) {
                            ++v;
                        }
                    }
                    if(class0 == null) {
                        if(classNotFoundException1 == null) {
                            throw new InflateException(attributeSet0.getPositionDescription() + ": Error inflating class " + s);
                        }
                        throw classNotFoundException1;
                    }
                }
                else {
                    class0 = Class.forName(s, false, classLoader0);
                }
                constructor0 = class0.getConstructor(PreferenceInflater.CONSTRUCTOR_SIGNATURE);
                constructor0.setAccessible(true);
                PreferenceInflater.CONSTRUCTOR_MAP.put(s, constructor0);
            }
            this.mConstructorArgs[1] = attributeSet0;
            return (Preference)constructor0.newInstance(this.mConstructorArgs);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw classNotFoundException0;
        }
        catch(Exception exception0) {
            InflateException inflateException0 = new InflateException(attributeSet0.getPositionDescription() + ": Error inflating class " + s);
            inflateException0.initCause(exception0);
            throw inflateException0;
        }
    }

    private Preference createItemFromTag(String s, AttributeSet attributeSet0) {
        try {
            return -1 == s.indexOf(46) ? this.onCreateItem(s, attributeSet0) : this.createItem(s, null, attributeSet0);
        }
        catch(InflateException inflateException0) {
            throw inflateException0;
        }
        catch(ClassNotFoundException classNotFoundException0) {
            InflateException inflateException1 = new InflateException(attributeSet0.getPositionDescription() + ": Error inflating class (not found)" + s);
            inflateException1.initCause(classNotFoundException0);
            throw inflateException1;
        }
        catch(Exception exception0) {
            InflateException inflateException2 = new InflateException(attributeSet0.getPositionDescription() + ": Error inflating class " + s);
            inflateException2.initCause(exception0);
            throw inflateException2;
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public String[] getDefaultPackages() {
        return this.mDefaultPackages;
    }

    public Preference inflate(int v, PreferenceGroup preferenceGroup0) {
        try(XmlResourceParser xmlResourceParser0 = this.getContext().getResources().getXml(v)) {
            return this.inflate(xmlResourceParser0, preferenceGroup0);
        }
    }

    public Preference inflate(XmlPullParser xmlPullParser0, PreferenceGroup preferenceGroup0) {
        Preference preference0;
        synchronized(this.mConstructorArgs) {
            AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
            this.mConstructorArgs[0] = this.mContext;
            try {
                do {
                    int v1 = xmlPullParser0.next();
                }
                while(v1 != 1 && v1 != 2);
                if(v1 != 2) {
                    throw new InflateException(xmlPullParser0.getPositionDescription() + ": No start tag found!");
                }
                preference0 = this.onMergeRoots(preferenceGroup0, ((PreferenceGroup)this.createItemFromTag(xmlPullParser0.getName(), attributeSet0)));
                this.rInflate(xmlPullParser0, preference0, attributeSet0);
                return preference0;
            }
            catch(InflateException inflateException0) {
                throw inflateException0;
            }
            catch(XmlPullParserException xmlPullParserException0) {
                InflateException inflateException1 = new InflateException(xmlPullParserException0.getMessage());
                inflateException1.initCause(xmlPullParserException0);
                throw inflateException1;
            }
            catch(IOException iOException0) {
                InflateException inflateException2 = new InflateException(xmlPullParser0.getPositionDescription() + ": " + iOException0.getMessage());
                inflateException2.initCause(iOException0);
                throw inflateException2;
            }
        }
    }

    private void init(PreferenceManager preferenceManager0) {
        this.mPreferenceManager = preferenceManager0;
        this.setDefaultPackages(new String[]{Preference.class.getPackage().getName() + ".", SwitchPreference.class.getPackage().getName() + "."});
    }

    protected Preference onCreateItem(String s, AttributeSet attributeSet0) throws ClassNotFoundException {
        return this.createItem(s, this.mDefaultPackages, attributeSet0);
    }

    private PreferenceGroup onMergeRoots(PreferenceGroup preferenceGroup0, PreferenceGroup preferenceGroup1) {
        if(preferenceGroup0 == null) {
            preferenceGroup1.onAttachedToHierarchy(this.mPreferenceManager);
            return preferenceGroup1;
        }
        return preferenceGroup0;
    }

    private void rInflate(XmlPullParser xmlPullParser0, Preference preference0, AttributeSet attributeSet0) throws XmlPullParserException, IOException {
        Intent intent0;
        int v = xmlPullParser0.getDepth();
        while(true) {
            int v1 = xmlPullParser0.next();
            if(v1 == 3 && xmlPullParser0.getDepth() <= v || v1 == 1) {
                break;
            }
            if(v1 == 2) {
                String s = xmlPullParser0.getName();
                if("intent".equals(s)) {
                    try {
                        intent0 = Intent.parseIntent(this.getContext().getResources(), xmlPullParser0, attributeSet0);
                    }
                    catch(IOException iOException0) {
                        XmlPullParserException xmlPullParserException0 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException0.initCause(iOException0);
                        throw xmlPullParserException0;
                    }
                    preference0.setIntent(intent0);
                }
                else {
                    if("extra".equals(s)) {
                        this.getContext().getResources().parseBundleExtra("extra", attributeSet0, preference0.getExtras());
                        try {
                            PreferenceInflater.skipCurrentTag(xmlPullParser0);
                            continue;
                        }
                        catch(IOException iOException1) {
                            XmlPullParserException xmlPullParserException1 = new XmlPullParserException("Error parsing preference");
                            xmlPullParserException1.initCause(iOException1);
                            throw xmlPullParserException1;
                        }
                    }
                    Preference preference1 = this.createItemFromTag(s, attributeSet0);
                    ((PreferenceGroup)preference0).addItemFromInflater(preference1);
                    this.rInflate(xmlPullParser0, preference1, attributeSet0);
                }
            }
        }
    }

    public void setDefaultPackages(String[] arr_s) {
        this.mDefaultPackages = arr_s;
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        do {
            int v1 = xmlPullParser0.next();
        }
        while(v1 != 1 && (v1 != 3 || xmlPullParser0.getDepth() > v));
    }
}

