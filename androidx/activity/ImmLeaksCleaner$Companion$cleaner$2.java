package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class ImmLeaksCleaner.Companion.cleaner.2 extends Lambda implements Function0 {
    public static final ImmLeaksCleaner.Companion.cleaner.2 INSTANCE;

    static {
        ImmLeaksCleaner.Companion.cleaner.2.INSTANCE = new ImmLeaksCleaner.Companion.cleaner.2();
    }

    ImmLeaksCleaner.Companion.cleaner.2() {
        super(0);
    }

    public final Cleaner invoke() {
        try {
            Field field0 = InputMethodManager.class.getDeclaredField("mServedView");
            field0.setAccessible(true);
            Field field1 = InputMethodManager.class.getDeclaredField("mNextServedView");
            field1.setAccessible(true);
            Field field2 = InputMethodManager.class.getDeclaredField("mH");
            field2.setAccessible(true);
            Intrinsics.checkNotNullExpressionValue(field2, "hField");
            Intrinsics.checkNotNullExpressionValue(field0, "servedViewField");
            Intrinsics.checkNotNullExpressionValue(field1, "nextServedViewField");
            return new ValidCleaner(field2, field0, field1);
        }
        catch(NoSuchFieldException unused_ex) {
            return FailedInitialization.INSTANCE;
        }
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

