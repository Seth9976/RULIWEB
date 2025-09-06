package kotlinx.coroutines.selects;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J6\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u001C\u0010\u0007\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\nJ3\u0010\u000B\u001A\u00020\u0004*\u00020\f2\u001C\u0010\u0007\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bH¦\u0002ø\u0001\u0000¢\u0006\u0002\u0010\rJE\u0010\u000B\u001A\u00020\u0004\"\u0004\b\u0001\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\u000F2\"\u0010\u0007\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010H¦\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0011JY\u0010\u000B\u001A\u00020\u0004\"\u0004\b\u0001\u0010\u0012\"\u0004\b\u0002\u0010\u000E*\u000E\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u000E0\u00132\u0006\u0010\u0014\u001A\u0002H\u00122\"\u0010\u0007\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010H¦\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0015JS\u0010\u000B\u001A\u00020\u0004\"\u0004\b\u0001\u0010\u0012\"\u0004\b\u0002\u0010\u000E*\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0012\u0012\u0004\u0012\u0002H\u000E0\u00132\"\u0010\u0007\u001A\u001E\b\u0001\u0012\u0004\u0012\u0002H\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0010H\u0096\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0082\u0001\u0001\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilder;", "R", "", "onTimeout", "", "timeMillis", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(JLkotlin/jvm/functions/Function1;)V", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Lkotlinx/coroutines/selects/SelectClause2;Lkotlin/jvm/functions/Function2;)V", "Lkotlinx/coroutines/selects/SelectImplementation;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SelectBuilder {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void invoke(SelectBuilder selectBuilder0, SelectClause2 selectClause20, Function2 function20) {
            selectBuilder0.invoke(selectClause20, null, function20);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
        public static void onTimeout(SelectBuilder selectBuilder0, long v, Function1 function10) {
            OnTimeoutKt.onTimeout(selectBuilder0, v, function10);
        }
    }

    void invoke(SelectClause0 arg1, Function1 arg2);

    void invoke(SelectClause1 arg1, Function2 arg2);

    void invoke(SelectClause2 arg1, Object arg2, Function2 arg3);

    void invoke(SelectClause2 arg1, Function2 arg2);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
    void onTimeout(long arg1, Function1 arg2);
}

