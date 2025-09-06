package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\u0002\u0018\u00002\u00020\u0001B`\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\u000E\b\u0002\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\b\u0002\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\u0003\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\u000E\b\u0002\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\f\u001A\u00020\r\u0012\b\b\u0002\u0010\u000E\u001A\u00020\u000FR\u0013\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0010R\u000F\u0010\b\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\b\u0010\u0011R\u000F\u0010\t\u001A\u00020\n¢\u0006\u0006\u001A\u0004\b\t\u0010\u0012R\u0015\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0006\u001A\u0004\b\u000B\u0010\u0013R\u000F\u0010\u000E\u001A\u00020\u000F¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u0014R\u000F\u0010\f\u001A\u00020\r¢\u0006\u0006\u001A\u0004\b\f\u0010\u0015R\u000F\u0010\u0002\u001A\u00020\u0003¢\u0006\u0006\u001A\u0004\b\u0002\u0010\u0011R\u0015\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/room/Fts4;", "", "tokenizer", "", "tokenizerArgs", "", "contentEntity", "Lkotlin/reflect/KClass;", "languageId", "matchInfo", "Landroidx/room/FtsOptions$MatchInfo;", "notIndexed", "prefix", "", "order", "Landroidx/room/FtsOptions$Order;", "()Ljava/lang/Class;", "()Ljava/lang/String;", "()Landroidx/room/FtsOptions$MatchInfo;", "()[Ljava/lang/String;", "()Landroidx/room/FtsOptions$Order;", "()[I", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
public @interface Fts4 {
    Class contentEntity() default Object.class;

    String languageId() default "";

    MatchInfo matchInfo() default MatchInfo.FTS4;

    String[] notIndexed() default {};

    Order order() default Order.ASC;

    int[] prefix() default {};

    String tokenizer() default "simple";

    String[] tokenizerArgs() default {};
}

