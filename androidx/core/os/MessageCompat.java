package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Message;

public final class MessageCompat {
    static class Api22Impl {
        static boolean isAsynchronous(Message message0) {
            return message0.isAsynchronous();
        }

        static void setAsynchronous(Message message0, boolean z) {
            message0.setAsynchronous(z);
        }
    }

    private static boolean sTryIsAsynchronous = true;
    private static boolean sTrySetAsynchronous = true;

    static {
    }

    public static boolean isAsynchronous(Message message0) {
        if(Build.VERSION.SDK_INT >= 22) {
            return Api22Impl.isAsynchronous(message0);
        }
        if(MessageCompat.sTryIsAsynchronous) {
            try {
                return Api22Impl.isAsynchronous(message0);
            }
            catch(NoSuchMethodError unused_ex) {
                MessageCompat.sTryIsAsynchronous = false;
            }
        }
        return false;
    }

    public static void setAsynchronous(Message message0, boolean z) {
        if(Build.VERSION.SDK_INT >= 22) {
            Api22Impl.setAsynchronous(message0, z);
            return;
        }
        if(MessageCompat.sTrySetAsynchronous) {
            try {
                Api22Impl.setAsynchronous(message0, z);
            }
            catch(NoSuchMethodError unused_ex) {
                MessageCompat.sTrySetAsynchronous = false;
            }
        }
    }
}

