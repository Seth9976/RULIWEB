package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class KeyFrames {
    private static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    private static final String CUSTOM_METHOD = "CustomMethod";
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;
    private HashMap mFramesMap;
    static HashMap sKeyMakers;

    static {
        HashMap hashMap0 = new HashMap();
        KeyFrames.sKeyMakers = hashMap0;
        try {
            hashMap0.put("KeyAttribute", KeyAttributes.class.getConstructor(null));
            KeyFrames.sKeyMakers.put("KeyPosition", KeyPosition.class.getConstructor(null));
            KeyFrames.sKeyMakers.put("KeyCycle", KeyCycle.class.getConstructor(null));
            KeyFrames.sKeyMakers.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(null));
            KeyFrames.sKeyMakers.put("KeyTrigger", KeyTrigger.class.getConstructor(null));
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("KeyFrames", "unable to load", noSuchMethodException0);
        }
    }

    public KeyFrames() {
        this.mFramesMap = new HashMap();
    }

    // This method was un-flattened
    public KeyFrames(Context context0, XmlPullParser xmlPullParser0) {
        KeyAttributes keyAttributes1;
        this.mFramesMap = new HashMap();
        try {
            int v = xmlPullParser0.getEventType();
            KeyAttributes keyAttributes0 = null;
            while(v != 1) {
                switch(v) {
                    case 2: {
                        String s = xmlPullParser0.getName();
                        if(KeyFrames.sKeyMakers.containsKey(s)) {
                            switch(s) {
                                case "KeyAttribute": {
                                    keyAttributes1 = new KeyAttributes();
                                    break;
                                }
                                case "KeyCycle": {
                                    keyAttributes1 = new KeyCycle();
                                    break;
                                }
                                case "KeyPosition": {
                                    keyAttributes1 = new KeyPosition();
                                    break;
                                }
                                case "KeyTimeCycle": {
                                    keyAttributes1 = new KeyTimeCycle();
                                    break;
                                }
                                case "KeyTrigger": {
                                    keyAttributes1 = new KeyTrigger();
                                    break;
                                }
                                default: {
                                    throw new NullPointerException("Key " + s + " not found");
                                }
                            }
                            keyAttributes1.load(context0, Xml.asAttributeSet(xmlPullParser0));
                            this.addKey(keyAttributes1);
                            keyAttributes0 = keyAttributes1;
                        }
                        else if(!s.equalsIgnoreCase("CustomAttribute")) {
                            if(s.equalsIgnoreCase("CustomMethod") && keyAttributes0 != null && keyAttributes0.mCustomConstraints != null) {
                                ConstraintAttribute.parse(context0, xmlPullParser0, keyAttributes0.mCustomConstraints);
                            }
                        }
                        else if(keyAttributes0 != null && keyAttributes0.mCustomConstraints != null) {
                            ConstraintAttribute.parse(context0, xmlPullParser0, keyAttributes0.mCustomConstraints);
                        }
                        break;
                    }
                    case 3: {
                        if("KeyFrameSet".equals(xmlPullParser0.getName())) {
                            return;
                        }
                    }
                }
                v = xmlPullParser0.next();
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("KeyFrames", "Error parsing XML resource", xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("KeyFrames", "Error parsing XML resource", iOException0);
        }
    }

    public void addAllFrames(MotionController motionController0) {
        ArrayList arrayList0 = (ArrayList)this.mFramesMap.get(-1);
        if(arrayList0 != null) {
            motionController0.addKeys(arrayList0);
        }
    }

    public void addFrames(MotionController motionController0) {
        ArrayList arrayList0 = (ArrayList)this.mFramesMap.get(motionController0.mId);
        if(arrayList0 != null) {
            motionController0.addKeys(arrayList0);
        }
        ArrayList arrayList1 = (ArrayList)this.mFramesMap.get(-1);
        if(arrayList1 != null) {
            for(Object object0: arrayList1) {
                Key key0 = (Key)object0;
                if(key0.matches(((LayoutParams)motionController0.mView.getLayoutParams()).constraintTag)) {
                    motionController0.addKey(key0);
                }
            }
        }
    }

    public void addKey(Key key0) {
        if(!this.mFramesMap.containsKey(key0.mTargetId)) {
            this.mFramesMap.put(key0.mTargetId, new ArrayList());
        }
        ArrayList arrayList0 = (ArrayList)this.mFramesMap.get(key0.mTargetId);
        if(arrayList0 != null) {
            arrayList0.add(key0);
        }
    }

    public ArrayList getKeyFramesForView(int v) {
        return (ArrayList)this.mFramesMap.get(v);
    }

    public Set getKeys() {
        return this.mFramesMap.keySet();
    }

    static String name(int v, Context context0) {
        return context0.getResources().getResourceEntryName(v);
    }
}

