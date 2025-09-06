package androidx.core.content.pm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jeb.synthetic.TWR;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ShortcutXmlParser {
    private static final String ATTR_SHORTCUT_ID = "shortcutId";
    private static final Object GET_INSTANCE_LOCK = null;
    private static final String META_DATA_APP_SHORTCUTS = "android.app.shortcuts";
    private static final String TAG = "ShortcutXmlParser";
    private static final String TAG_SHORTCUT = "shortcut";
    private static volatile ArrayList sShortcutIds;

    static {
        ShortcutXmlParser.GET_INSTANCE_LOCK = new Object();
    }

    private static String getAttributeValue(XmlPullParser xmlPullParser0, String s) {
        String s1 = xmlPullParser0.getAttributeValue("http://schemas.android.com/apk/res/android", s);
        return s1 == null ? xmlPullParser0.getAttributeValue(null, s) : s1;
    }

    public static List getShortcutIds(Context context0) {
        if(ShortcutXmlParser.sShortcutIds == null) {
            Object object0 = ShortcutXmlParser.GET_INSTANCE_LOCK;
            synchronized(object0) {
                if(ShortcutXmlParser.sShortcutIds == null) {
                    ShortcutXmlParser.sShortcutIds = new ArrayList();
                    ShortcutXmlParser.sShortcutIds.addAll(ShortcutXmlParser.parseShortcutIds(context0));
                }
                return ShortcutXmlParser.sShortcutIds;
            }
        }
        return ShortcutXmlParser.sShortcutIds;
    }

    private static XmlResourceParser getXmlResourceParser(Context context0, ActivityInfo activityInfo0) {
        XmlResourceParser xmlResourceParser0 = activityInfo0.loadXmlMetaData(context0.getPackageManager(), "android.app.shortcuts");
        if(xmlResourceParser0 == null) {
            throw new IllegalArgumentException("Failed to open android.app.shortcuts meta-data resource of " + activityInfo0.name);
        }
        return xmlResourceParser0;
    }

    public static List parseShortcutIds(XmlPullParser xmlPullParser0) throws IOException, XmlPullParserException {
        List list0 = new ArrayList(1);
        while(true) {
            int v = xmlPullParser0.next();
            if(v == 1 || v == 3 && xmlPullParser0.getDepth() <= 0) {
                break;
            }
            if(v == 2 && xmlPullParser0.getDepth() == 2 && "shortcut".equals(xmlPullParser0.getName())) {
                String s = ShortcutXmlParser.getAttributeValue(xmlPullParser0, "shortcutId");
                if(s != null) {
                    list0.add(s);
                }
            }
        }
        return list0;
    }

    private static Set parseShortcutIds(Context context0) {
        Set set0 = new HashSet();
        Intent intent0 = new Intent("android.intent.action.MAIN");
        intent0.addCategory("android.intent.category.LAUNCHER");
        intent0.setPackage("com.ruliweb.www.ruliapp");
        List list0 = context0.getPackageManager().queryIntentActivities(intent0, 0x80);
        if(list0 != null && list0.size() != 0) {
            try {
                for(Object object0: list0) {
                    ActivityInfo activityInfo0 = ((ResolveInfo)object0).activityInfo;
                    if(activityInfo0.metaData != null && activityInfo0.metaData.containsKey("android.app.shortcuts")) {
                        XmlResourceParser xmlResourceParser0 = ShortcutXmlParser.getXmlResourceParser(context0, activityInfo0);
                        TWR.declareResource(xmlResourceParser0);
                        TWR.useResource$NT(xmlResourceParser0);
                        set0.addAll(ShortcutXmlParser.parseShortcutIds(xmlResourceParser0));
                    }
                }
            }
            catch(Exception exception0) {
                Log.e("ShortcutXmlParser", "Failed to parse the Xml resource: ", exception0);
            }
        }
        return set0;
    }
}

