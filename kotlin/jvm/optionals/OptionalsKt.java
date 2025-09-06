package kotlin.jvm.optionals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001F\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\u001A$\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u001A,\u0010\u0005\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\f\u0012\b\b\u0001\u0012\u0004\b\u0002H\u00020\u00042\u0006\u0010\u0006\u001A\u0002H\u0002H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001A6\u0010\b\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\f\u0012\b\b\u0001\u0012\u0004\b\u0002H\u00020\u00042\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00020\tH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010\n\u001A#\u0010\u000B\u001A\u0004\u0018\u0001H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007¢\u0006\u0002\u0010\f\u001A;\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0010\b\u0001\u0010\u000E*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000F*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0010\u001A\u0002H\u000EH\u0007¢\u0006\u0002\u0010\u0011\u001A$\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0013\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u001A$\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u0082\u0002\u000B\n\u0002\b9\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "", "Ljava/util/Optional;", "getOrDefault", "defaultValue", "(Ljava/util/Optional;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Ljava/util/Optional;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrNull", "(Ljava/util/Optional;)Ljava/lang/Object;", "toCollection", "C", "", "destination", "(Ljava/util/Optional;Ljava/util/Collection;)Ljava/util/Collection;", "toList", "", "toSet", "", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class OptionalsKt {
    public static final Sequence asSequence(Optional optional0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        return optional0.isPresent() ? SequencesKt.sequenceOf(new Object[]{optional0.get()}) : SequencesKt.emptySequence();
    }

    public static final Object getOrDefault(Optional optional0, Object object0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        return optional0.isPresent() ? optional0.get() : object0;
    }

    public static final Object getOrElse(Optional optional0, Function0 function00) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        return optional0.isPresent() ? optional0.get() : function00.invoke();
    }

    public static final Object getOrNull(Optional optional0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        return optional0.orElse(null);
    }

    public static final Collection toCollection(Optional optional0, Collection collection0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        if(optional0.isPresent()) {
            Object object0 = optional0.get();
            Intrinsics.checkNotNullExpressionValue(object0, "get()");
            collection0.add(object0);
        }
        return collection0;
    }

    public static final List toList(Optional optional0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        return optional0.isPresent() ? CollectionsKt.listOf(optional0.get()) : CollectionsKt.emptyList();
    }

    public static final Set toSet(Optional optional0) {
        Intrinsics.checkNotNullParameter(optional0, "<this>");
        return optional0.isPresent() ? SetsKt.setOf(optional0.get()) : SetsKt.emptySet();
    }
}

