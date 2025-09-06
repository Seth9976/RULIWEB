package androidx.core.content;

import android.content.ContentValues;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A;\u0010\u0000\u001A\u00020\u00012.\u0010\u0002\u001A\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"contentValuesOf", "Landroid/content/ContentValues;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ContentValuesKt {
    public static final ContentValues contentValuesOf(Pair[] arr_pair) {
        ContentValues contentValues0 = new ContentValues(arr_pair.length);
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            String s = (String)pair0.component1();
            Object object0 = pair0.component2();
            if(object0 == null) {
                contentValues0.putNull(s);
            }
            else if(object0 instanceof String) {
                contentValues0.put(s, ((String)object0));
            }
            else if(object0 instanceof Integer) {
                contentValues0.put(s, ((Integer)object0));
            }
            else if(object0 instanceof Long) {
                contentValues0.put(s, ((Long)object0));
            }
            else if(object0 instanceof Boolean) {
                contentValues0.put(s, ((Boolean)object0));
            }
            else if(object0 instanceof Float) {
                contentValues0.put(s, ((Float)object0));
            }
            else if(object0 instanceof Double) {
                contentValues0.put(s, ((Double)object0));
            }
            else if(object0 instanceof byte[]) {
                contentValues0.put(s, ((byte[])object0));
            }
            else if(object0 instanceof Byte) {
                contentValues0.put(s, ((Byte)object0));
            }
            else {
                if(!(object0 instanceof Short)) {
                    throw new IllegalArgumentException("Illegal value type " + object0.getClass().getCanonicalName() + " for key \"" + s + '\"');
                }
                contentValues0.put(s, ((Short)object0));
            }
        }
        return contentValues0;
    }
}

