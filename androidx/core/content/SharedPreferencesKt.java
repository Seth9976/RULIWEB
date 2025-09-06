package androidx.core.content;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A0\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\u0087\b¨\u0006\t"}, d2 = {"edit", "", "Landroid/content/SharedPreferences;", "commit", "", "action", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SharedPreferencesKt {
    public static final void edit(SharedPreferences sharedPreferences0, boolean z, Function1 function10) {
        SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
        function10.invoke(sharedPreferences$Editor0);
        if(z) {
            sharedPreferences$Editor0.commit();
            return;
        }
        sharedPreferences$Editor0.apply();
    }

    public static void edit$default(SharedPreferences sharedPreferences0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
        function10.invoke(sharedPreferences$Editor0);
        if(z) {
            sharedPreferences$Editor0.commit();
            return;
        }
        sharedPreferences$Editor0.apply();
    }
}

