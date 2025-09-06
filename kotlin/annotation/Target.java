package kotlin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0014\u0012\u0012\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004R\u0017\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/annotation/Target;", "", "allowedTargets", "", "Lkotlin/annotation/AnnotationTarget;", "()[Lkotlin/annotation/AnnotationTarget;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@MustBeDocumented
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.ANNOTATION_CLASS})
public @interface kotlin.annotation.Target {
    AnnotationTarget[] allowedTargets();
}

