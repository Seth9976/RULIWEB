package androidx.constraintlayout.core.motion.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Utils {
    public interface DebugHandle {
        void message(String arg1);
    }

    static DebugHandle sOurHandle;

    private static int clamp(int v) {
        int v1 = (v & ~(v >> 0x1F)) - 0xFF;
        return (v1 & v1 >> 0x1F) + 0xFF;
    }

    public int getInterpolatedColor(float[] arr_f) {
        return Utils.clamp(((int)(arr_f[3] * 255.0f))) << 24 | Utils.clamp(((int)(((float)Math.pow(arr_f[0], 0.454545)) * 255.0f))) << 16 | Utils.clamp(((int)(((float)Math.pow(arr_f[1], 0.454545)) * 255.0f))) << 8 | Utils.clamp(((int)(((float)Math.pow(arr_f[2], 0.454545)) * 255.0f)));
    }

    // 去混淆评级： 低(20)
    public static void log(String s) {
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[1];
        String s1 = ".(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ")" + "  " + "log              ";
        System.out.println(s1 + " " + s);
        DebugHandle utils$DebugHandle0 = Utils.sOurHandle;
        if(utils$DebugHandle0 != null) {
            utils$DebugHandle0.message(s1 + " " + s);
        }
    }

    public static void log(String s, String s1) {
        System.out.println(s + " : " + s1);
    }

    public static void logStack(String s, int v) {
        StackTraceElement[] arr_stackTraceElement = new Throwable().getStackTrace();
        int v2 = Math.min(v, arr_stackTraceElement.length - 1);
        String s1 = " ";
        for(int v1 = 1; v1 <= v2; ++v1) {
            StackTraceElement stackTraceElement0 = arr_stackTraceElement[v1];
            s1 = s1 + " ";
            System.out.println(s + s1 + (".(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ") " + stackTraceElement0.getMethodName()) + s1);
        }
    }

    public static void loge(String s, String s1) {
        System.err.println(s + " : " + s1);
    }

    public static int rgbaTocColor(float f, float f1, float f2, float f3) {
        return Utils.clamp(((int)(f * 255.0f))) << 16 | Utils.clamp(((int)(f3 * 255.0f))) << 24 | Utils.clamp(((int)(f1 * 255.0f))) << 8 | Utils.clamp(((int)(f2 * 255.0f)));
    }

    public static void setDebugHandle(DebugHandle utils$DebugHandle0) {
        Utils.sOurHandle = utils$DebugHandle0;
    }

    public static void socketSend(String s) {
        try {
            OutputStream outputStream0 = new Socket("127.0.0.1", 0x14CF).getOutputStream();
            outputStream0.write(s.getBytes());
            outputStream0.close();
        }
        catch(IOException iOException0) {
            System.err.println(iOException0.toString() + "\n" + Arrays.toString(iOException0.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
    }
}

