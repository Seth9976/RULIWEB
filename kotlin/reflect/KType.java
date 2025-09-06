package kotlin.reflect;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R \u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0007\u0010\bR\u001C\u0010\t\u001A\u0004\u0018\u00010\n8&X§\u0004¢\u0006\f\u0012\u0004\b\u000B\u0010\u0006\u001A\u0004\b\f\u0010\rR\u0012\u0010\u000E\u001A\u00020\u000FX¦\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u0010¨\u0006\u0011"}, d2 = {"Lkotlin/reflect/KType;", "Lkotlin/reflect/KAnnotatedElement;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "getArguments$annotations", "()V", "getArguments", "()Ljava/util/List;", "classifier", "Lkotlin/reflect/KClassifier;", "getClassifier$annotations", "getClassifier", "()Lkotlin/reflect/KClassifier;", "isMarkedNullable", "", "()Z", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KType extends KAnnotatedElement {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void getArguments$annotations() {
        }

        public static void getClassifier$annotations() {
        }
    }

    List getArguments();

    KClassifier getClassifier();

    boolean isMarkedNullable();
}

