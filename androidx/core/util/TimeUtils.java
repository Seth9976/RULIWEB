package androidx.core.util;

import java.io.PrintWriter;

public final class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr;
    private static final Object sFormatSync;

    static {
        TimeUtils.sFormatSync = new Object();
        TimeUtils.sFormatStr = new char[24];
    }

    private static int accumField(int v, int v1, boolean z, int v2) {
        if(v <= 99 && (!z || v2 < 3)) {
            if(v <= 9 && (!z || v2 < 2)) {
                return z || v > 0 ? v1 + 1 : 0;
            }
            return v1 + 2;
        }
        return v1 + 3;
    }

    public static void formatDuration(long v, long v1, PrintWriter printWriter0) {
        if(v == 0L) {
            printWriter0.print("--");
            return;
        }
        TimeUtils.formatDuration(v - v1, printWriter0, 0);
    }

    public static void formatDuration(long v, PrintWriter printWriter0) {
        TimeUtils.formatDuration(v, printWriter0, 0);
    }

    public static void formatDuration(long v, PrintWriter printWriter0, int v1) {
        synchronized(TimeUtils.sFormatSync) {
            int v3 = TimeUtils.formatDurationLocked(v, v1);
            printWriter0.print(new String(TimeUtils.sFormatStr, 0, v3));
        }
    }

    public static void formatDuration(long v, StringBuilder stringBuilder0) {
        synchronized(TimeUtils.sFormatSync) {
            int v2 = TimeUtils.formatDurationLocked(v, 0);
            stringBuilder0.append(TimeUtils.sFormatStr, 0, v2);
        }
    }

    private static int formatDurationLocked(long v, int v1) {
        int v13;
        int v7;
        int v5;
        int v4;
        char c;
        if(TimeUtils.sFormatStr.length < v1) {
            TimeUtils.sFormatStr = new char[v1];
        }
        char[] arr_c = TimeUtils.sFormatStr;
        int v2 = Long.compare(v, 0L);
        if(v2 == 0) {
            while(v1 - 1 > 0) {
                arr_c[0] = ' ';
            }
            arr_c[0] = '0';
            return 1;
        }
        if(v2 > 0) {
            c = '+';
        }
        else {
            v = -v;
            c = '-';
        }
        int v3 = (int)Math.floor(v / 1000L);
        if(v3 > 86400) {
            v4 = v3 / 86400;
            v3 -= 86400 * v4;
        }
        else {
            v4 = 0;
        }
        if(v3 > 3600) {
            v5 = v3 / 3600;
            v3 -= v5 * 3600;
        }
        else {
            v5 = 0;
        }
        if(v3 > 60) {
            int v6 = v3 / 60;
            v3 -= v6 * 60;
            v7 = v6;
        }
        else {
            v7 = 0;
        }
        if(v1 == 0) {
            v13 = 0;
        }
        else {
            int v8 = TimeUtils.accumField(v4, 1, false, 0);
            int v9 = v8 + TimeUtils.accumField(v5, 1, v8 > 0, 2);
            int v10 = v9 + TimeUtils.accumField(v7, 1, v9 > 0, 2);
            int v11 = v10 + TimeUtils.accumField(v3, 1, v10 > 0, 2);
            int v12 = v11 + (TimeUtils.accumField(((int)(v % 1000L)), 2, true, (v11 <= 0 ? 0 : 3)) + 1);
            v13 = 0;
            while(v12 < v1) {
                arr_c[v13] = ' ';
                ++v13;
                ++v12;
            }
        }
        arr_c[v13] = c;
        int v14 = TimeUtils.printField(arr_c, v4, 'd', v13 + 1, false, 0);
        int v15 = TimeUtils.printField(arr_c, v5, 'h', v14, v14 != v13 + 1, (v1 == 0 ? 2 : 0));
        int v16 = TimeUtils.printField(arr_c, v7, 'm', v15, v15 != v13 + 1, (v1 == 0 ? 2 : 0));
        int v17 = TimeUtils.printField(arr_c, v3, 's', v16, v16 != v13 + 1, (v1 == 0 ? 2 : 0));
        int v18 = TimeUtils.printField(arr_c, ((int)(v % 1000L)), 'm', v17, true, (v1 == 0 || v17 == v13 + 1 ? 0 : 3));
        arr_c[v18] = 's';
        return v18 + 1;
    }

    private static int printField(char[] arr_c, int v, char c, int v1, boolean z, int v2) {
        int v3;
        if(!z && v <= 0) {
            return v1;
        }
        if((!z || v2 < 3) && v <= 99) {
            v3 = v1;
        }
        else {
            arr_c[v1] = (char)(v / 100 + 0x30);
            v3 = v1 + 1;
            v -= v / 100 * 100;
        }
        if(z && v2 >= 2 || v > 9 || v1 != v3) {
            arr_c[v3] = (char)(v / 10 + 0x30);
            ++v3;
            v -= v / 10 * 10;
        }
        arr_c[v3] = (char)(v + 0x30);
        arr_c[v3 + 1] = c;
        return v3 + 2;
    }
}

