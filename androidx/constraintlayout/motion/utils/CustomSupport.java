package androidx.constraintlayout.motion.utils;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomSupport {
    private static final boolean DEBUG = false;
    private static final String TAG = "CustomSupport";

    private static int clamp(int v) {
        int v1 = (v & ~(v >> 0x1F)) - 0xFF;
        return (v1 & v1 >> 0x1F) + 0xFF;
    }

    public static void setInterpolatedValue(ConstraintAttribute constraintAttribute0, View view0, float[] arr_f) {
        Class class0 = view0.getClass();
        String s = "set" + constraintAttribute0.getName();
        try {
            switch(constraintAttribute0.getType()) {
                case 1: {
                    class0.getMethod(s, Integer.TYPE).invoke(view0, ((int)arr_f[0]));
                    return;
                }
                case 2: {
                    class0.getMethod(s, Float.TYPE).invoke(view0, ((float)arr_f[0]));
                    return;
                }
                case 3: {
                    Method method0 = class0.getMethod(s, Drawable.class);
                    int v = CustomSupport.clamp(((int)(((float)Math.pow(arr_f[0], 0.454545)) * 255.0f)));
                    int v1 = CustomSupport.clamp(((int)(((float)Math.pow(arr_f[1], 0.454545)) * 255.0f)));
                    int v2 = CustomSupport.clamp(((int)(((float)Math.pow(arr_f[2], 0.454545)) * 255.0f)));
                    int v3 = CustomSupport.clamp(((int)(arr_f[3] * 255.0f)));
                    ColorDrawable colorDrawable0 = new ColorDrawable();
                    colorDrawable0.setColor(v << 16 | v3 << 24 | v1 << 8 | v2);
                    method0.invoke(view0, colorDrawable0);
                    return;
                }
                case 4: {
                    class0.getMethod(s, Integer.TYPE).invoke(view0, ((int)(CustomSupport.clamp(((int)(((float)Math.pow(arr_f[0], 0.454545)) * 255.0f))) << 16 | CustomSupport.clamp(((int)(arr_f[3] * 255.0f))) << 24 | CustomSupport.clamp(((int)(((float)Math.pow(arr_f[1], 0.454545)) * 255.0f))) << 8 | CustomSupport.clamp(((int)(((float)Math.pow(arr_f[2], 0.454545)) * 255.0f))))));
                    return;
                }
                case 5: {
                    throw new RuntimeException("unable to interpolate strings " + constraintAttribute0.getName());
                }
                case 6: {
                    class0.getMethod(s, Boolean.TYPE).invoke(view0, Boolean.valueOf(arr_f[0] > 0.5f));
                    return;
                }
                case 7: {
                    class0.getMethod(s, Float.TYPE).invoke(view0, ((float)arr_f[0]));
                }
            }
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("CustomSupport", "No method " + s + " on View \"" + Debug.getName(view0) + "\"", noSuchMethodException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("CustomSupport", "Cannot access method " + s + " on View \"" + Debug.getName(view0) + "\"", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("CustomSupport", "Cannot invoke method " + s + " on View \"" + Debug.getName(view0) + "\"", invocationTargetException0);
        }
    }
}

