package kotlin.reflect;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001A\u00020\u0006X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u000B0\nX¦\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\rR\u0012\u0010\u000E\u001A\u00020\u000FX¦\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lkotlin/reflect/KTypeParameter;", "Lkotlin/reflect/KClassifier;", "isReified", "", "()Z", "name", "", "getName", "()Ljava/lang/String;", "upperBounds", "", "Lkotlin/reflect/KType;", "getUpperBounds", "()Ljava/util/List;", "variance", "Lkotlin/reflect/KVariance;", "getVariance", "()Lkotlin/reflect/KVariance;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KTypeParameter extends KClassifier {
    String getName();

    List getUpperBounds();

    KVariance getVariance();

    boolean isReified();
}

