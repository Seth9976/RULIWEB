package kotlin.reflect.jvm.internal;

import java.util.Comparator;
import kotlin.jvm.functions.Function2;

class KDeclarationContainerImpl..Lambda.0 implements Comparator {
    private final Function2 arg$0;

    public KDeclarationContainerImpl..Lambda.0(Function2 function20) {
        this.arg$0 = function20;
    }

    @Override
    public int compare(Object object0, Object object1) {
        return KDeclarationContainerImpl.findPropertyDescriptor$lambda$3(this.arg$0, object0, object1);
    }
}

