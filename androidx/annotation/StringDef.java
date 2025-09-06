package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE})
@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001B \u0012\u0014\b\u0002\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006R\u000F\u0010\u0005\u001A\u00020\u0006¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0007R\u0017\u0010\u0002\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\b¨\u0006\t"}, d2 = {"Landroidx/annotation/StringDef;", "", "value", "", "", "open", "", "()Z", "()[Ljava/lang/String;", "annotation"}, k = 1, mv = {1, 7, 0}, xi = 0x30)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.ANNOTATION_CLASS})
public @interface StringDef {
    boolean open() default false;

    String[] value() default {};
}

