package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;

public final class ComparisonsKt__ComparisonsKt..ExternalSyntheticLambda3 implements Comparator {
    public final Function1[] f$0;

    public ComparisonsKt__ComparisonsKt..ExternalSyntheticLambda3(Function1[] arr_function1) {
        this.f$0 = arr_function1;
    }

    @Override
    public final int compare(Object object0, Object object1) {
        return ComparisonsKt__ComparisonsKt.compareBy$lambda$0$ComparisonsKt__ComparisonsKt(this.f$0, object0, object1);
    }
}

