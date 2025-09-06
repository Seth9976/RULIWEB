package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PredefinedFunctionEnhancementInfo {
    private final List parametersInfo;
    private final TypeEnhancementInfo returnTypeInfo;

    public PredefinedFunctionEnhancementInfo() {
        this(null, null, 3, null);
    }

    public PredefinedFunctionEnhancementInfo(TypeEnhancementInfo typeEnhancementInfo0, List list0) {
        Intrinsics.checkNotNullParameter(list0, "parametersInfo");
        super();
        this.returnTypeInfo = typeEnhancementInfo0;
        this.parametersInfo = list0;
    }

    public PredefinedFunctionEnhancementInfo(TypeEnhancementInfo typeEnhancementInfo0, List list0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            typeEnhancementInfo0 = null;
        }
        if((v & 2) != 0) {
            list0 = CollectionsKt.emptyList();
        }
        this(typeEnhancementInfo0, list0);
    }

    public final List getParametersInfo() {
        return this.parametersInfo;
    }

    public final TypeEnhancementInfo getReturnTypeInfo() {
        return this.returnTypeInfo;
    }
}

