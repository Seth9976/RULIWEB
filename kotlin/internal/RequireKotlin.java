package kotlin.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.internal.RepeatableContainer;

@Repeatable(Container.class)
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0081\u0002\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b\u0012\b\b\u0002\u0010\t\u001A\u00020\nR\u000F\u0010\t\u001A\u00020\n¢\u0006\u0006\u001A\u0004\b\t\u0010\u000BR\u000F\u0010\u0005\u001A\u00020\u0006¢\u0006\u0006\u001A\u0004\b\u0005\u0010\fR\u000F\u0010\u0004\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0004\u0010\rR\u000F\u0010\u0002\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\rR\u000F\u0010\u0007\u001A\u00020\b¢\u0006\u0006\u001A\u0004\b\u0007\u0010\u000E¨\u0006\u000F"}, d2 = {"Lkotlin/internal/RequireKotlin;", "", "version", "", "message", "level", "Lkotlin/DeprecationLevel;", "versionKind", "Lkotlin/internal/RequireKotlinVersionKind;", "errorCode", "", "()I", "()Lkotlin/DeprecationLevel;", "()Ljava/lang/String;", "()Lkotlin/internal/RequireKotlinVersionKind;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@kotlin.annotation.Repeatable
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.TYPEALIAS})
public @interface RequireKotlin {
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.TYPEALIAS})
    @RepeatableContainer
    public @interface Container {
        RequireKotlin[] value();
    }

    int errorCode() default -1;

    DeprecationLevel level() default DeprecationLevel.ERROR;

    String message() default "";

    String version();

    RequireKotlinVersionKind versionKind() default RequireKotlinVersionKind.LANGUAGE_VERSION;
}

