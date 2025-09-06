package com.kakao.sdk.common.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001A\u00020\u0003R\u000F\u0010\u0002\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/kakao/sdk/common/model/Description;", "", "value", "", "()Ljava/lang/String;", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
public @interface Description {
    String value();
}

