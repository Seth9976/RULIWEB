package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0087\u0002\u0018\u00002\u00020\u0001B(\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0006R\u000F\u0010\u0002\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\bR\u000F\u0010\u0005\u001A\u00020\u0006¢\u0006\u0006\u001A\u0004\b\u0005\u0010\tR\u000F\u0010\u0004\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0004\u0010\bR\u000F\u0010\u0007\u001A\u00020\u0006¢\u0006\u0006\u001A\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Landroidx/annotation/FloatRange;", "", "from", "", "to", "fromInclusive", "", "toInclusive", "()D", "()Z", "annotation"}, k = 1, mv = {1, 7, 0}, xi = 0x30)
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.ANNOTATION_CLASS})
public @interface FloatRange {
    double from() default -Infinity;

    boolean fromInclusive() default true;

    double to() default Infinity;

    boolean toInclusive() default true;
}

