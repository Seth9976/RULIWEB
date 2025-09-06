package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public class ViewUtils {
    public static String getXmlAttributeString(String s, String s1, Context context0, AttributeSet attributeSet0, boolean z, boolean z1, String s2) {
        String s3 = attributeSet0 == null ? null : attributeSet0.getAttributeValue(s, s1);
        if(s3 != null && s3.startsWith("@string/") && z) {
            TypedValue typedValue0 = new TypedValue();
            try {
                context0.getResources().getValue("com.ruliweb.www.ruliapp:string/" + s3.substring(8), typedValue0, true);
            }
            catch(Resources.NotFoundException unused_ex) {
                Log.w(s2, "Could not find resource for " + s1 + ": " + s3);
            }
            if(typedValue0.string == null) {
                Log.w(s2, "Resource " + s1 + " was not a string: " + typedValue0.toString());
            }
            else {
                s3 = typedValue0.string.toString();
            }
        }
        if(z1 && s3 == null) {
            Log.w(s2, "Required XML attribute \"" + s1 + "\" missing");
        }
        return s3;
    }
}

