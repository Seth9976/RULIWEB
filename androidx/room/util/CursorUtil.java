package androidx.room.util;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.os.Build.VERSION;
import android.util.Log;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u000E\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001\u001A\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007H\u0002\u001A#\u0010\u0003\u001A\u00020\u00042\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0006\u001A\u00020\u0007H\u0007¢\u0006\u0002\u0010\n\u001A\u0016\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007\u001A\u0016\u0010\f\u001A\u00020\u00042\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0007\u001A)\u0010\r\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u000E\u001A\u00020\u000F¢\u0006\u0002\u0010\u0010\u001A/\u0010\u0011\u001A\u0002H\u0012\"\u0004\b\u0000\u0010\u0012*\u00020\u00012\u0012\u0010\u0013\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u00120\u0014H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"copyAndClose", "Landroid/database/Cursor;", "c", "findColumnIndexBySuffix", "", "cursor", "name", "", "columnNames", "", "([Ljava/lang/String;Ljava/lang/String;)I", "getColumnIndex", "getColumnIndexOrThrow", "wrapMappedColumns", "mapping", "", "(Landroid/database/Cursor;[Ljava/lang/String;[I)Landroid/database/Cursor;", "useCursor", "R", "block", "Lkotlin/Function1;", "(Landroid/database/Cursor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CursorUtil {
    public static final Cursor copyAndClose(Cursor cursor0) {
        MatrixCursor matrixCursor0;
        Intrinsics.checkNotNullParameter(cursor0, "c");
        try {
            matrixCursor0 = new MatrixCursor(cursor0.getColumnNames(), cursor0.getCount());
            while(cursor0.moveToNext()) {
                Object[] arr_object = new Object[cursor0.getColumnCount()];
                int v = cursor0.getColumnCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    switch(cursor0.getType(v1)) {
                        case 0: {
                            arr_object[v1] = null;
                            break;
                        }
                        case 1: {
                            arr_object[v1] = cursor0.getLong(v1);
                            break;
                        }
                        case 2: {
                            arr_object[v1] = cursor0.getDouble(v1);
                            break;
                        }
                        case 3: {
                            arr_object[v1] = cursor0.getString(v1);
                            break;
                        }
                        case 4: {
                            arr_object[v1] = cursor0.getBlob(v1);
                            break;
                        }
                        default: {
                            throw new IllegalStateException();
                        }
                    }
                }
                matrixCursor0.addRow(arr_object);
            }
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(cursor0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(cursor0, null);
        return matrixCursor0;
    }

    private static final int findColumnIndexBySuffix(Cursor cursor0, String s) {
        if(Build.VERSION.SDK_INT > 25) {
            return -1;
        }
        if(s.length() == 0) {
            return -1;
        }
        String[] arr_s = cursor0.getColumnNames();
        Intrinsics.checkNotNullExpressionValue(arr_s, "columnNames");
        return CursorUtil.findColumnIndexBySuffix(arr_s, s);
    }

    public static final int findColumnIndexBySuffix(String[] arr_s, String s) {
        Intrinsics.checkNotNullParameter(arr_s, "columnNames");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = 0;
        for(int v1 = 0; v < arr_s.length; ++v1) {
            String s1 = arr_s[v];
            if(s1.length() >= s.length() + 2 && (StringsKt.endsWith$default(s1, "." + s, false, 2, null) || s1.charAt(0) == 0x60 && StringsKt.endsWith$default(s1, "." + s + '`', false, 2, null))) {
                return v1;
            }
            ++v;
        }
        return -1;
    }

    public static final int getColumnIndex(Cursor cursor0, String s) {
        Intrinsics.checkNotNullParameter(cursor0, "c");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = cursor0.getColumnIndex(s);
        if(v >= 0) {
            return v;
        }
        int v1 = cursor0.getColumnIndex("`" + s + '`');
        return v1 < 0 ? CursorUtil.findColumnIndexBySuffix(cursor0, s) : v1;
    }

    public static final int getColumnIndexOrThrow(Cursor cursor0, String s) {
        String s1;
        Intrinsics.checkNotNullParameter(cursor0, "c");
        Intrinsics.checkNotNullParameter(s, "name");
        int v = CursorUtil.getColumnIndex(cursor0, s);
        if(v >= 0) {
            return v;
        }
        try {
            String[] arr_s = cursor0.getColumnNames();
            Intrinsics.checkNotNullExpressionValue(arr_s, "c.columnNames");
            s1 = ArraysKt.joinToString$default(arr_s, null, null, null, 0, null, null, 0x3F, null);
        }
        catch(Exception exception0) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", exception0);
            s1 = "unknown";
        }
        throw new IllegalArgumentException("column \'" + s + "\' does not exist. Available columns: " + s1);
    }

    public static final Object useCursor(Cursor cursor0, Function1 function10) {
        Object object0;
        Intrinsics.checkNotNullParameter(cursor0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "block");
        try {
            object0 = function10.invoke(cursor0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(cursor0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(cursor0, null);
        return object0;
    }

    public static final Cursor wrapMappedColumns(Cursor cursor0, String[] arr_s, int[] arr_v) {
        Intrinsics.checkNotNullParameter(cursor0, "cursor");
        Intrinsics.checkNotNullParameter(arr_s, "columnNames");
        Intrinsics.checkNotNullParameter(arr_v, "mapping");
        if(arr_s.length != arr_v.length) {
            throw new IllegalStateException("Expected columnNames.length == mapping.length");
        }
        return new CursorWrapper(cursor0) {
            @Override  // android.database.CursorWrapper
            public int getColumnIndex(String s) {
                Intrinsics.checkNotNullParameter(s, "columnName");
                String[] arr_s = arr_s;
                int[] arr_v = arr_v;
                int v = 0;
                for(int v1 = 0; v < arr_s.length; ++v1) {
                    if(StringsKt.equals(arr_s[v], s, true)) {
                        return arr_v[v1];
                    }
                    ++v;
                }
                return super.getColumnIndex(s);
            }
        };
    }
}

