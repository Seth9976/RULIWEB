package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0010\u0012\u000E\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003R\u0017\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/SubclassOptInRequired;", "", "markerClass", "Lkotlin/reflect/KClass;", "()Ljava/lang/Class;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
public @interface SubclassOptInRequired {
    Class markerClass();
}

