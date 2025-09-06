package androidx.core.telephony;

import android.os.Build.VERSION;
import android.telephony.SubscriptionManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SubscriptionManagerCompat {
    static class Api29Impl {
        static int getSlotIndex(int v) {
            return SubscriptionManager.getSlotIndex(v);
        }
    }

    private static Method sGetSlotIndexMethod;

    public static int getSlotIndex(int v) {
        if(v == -1) {
            return -1;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getSlotIndex(v);
        }
        try {
            if(SubscriptionManagerCompat.sGetSlotIndexMethod == null) {
                SubscriptionManagerCompat.sGetSlotIndexMethod = Build.VERSION.SDK_INT >= 26 ? SubscriptionManager.class.getDeclaredMethod("getSlotIndex", Integer.TYPE) : SubscriptionManager.class.getDeclaredMethod("getSlotId", Integer.TYPE);
                SubscriptionManagerCompat.sGetSlotIndexMethod.setAccessible(true);
            }
            Integer integer0 = (Integer)SubscriptionManagerCompat.sGetSlotIndexMethod.invoke(null, v);
            return integer0 == null ? -1 : ((int)integer0);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException unused_ex) {
        }
        return -1;
    }
}

