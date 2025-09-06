package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000B\u001A\u00020\u0003HÆ\u0003J\t\u0010\f\u001A\u00020\u0005HÆ\u0003J\u001D\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001A\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lkotlin/text/MatchGroup;", "", "value", "", "range", "Lkotlin/ranges/IntRange;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)V", "getRange", "()Lkotlin/ranges/IntRange;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MatchGroup {
    private final IntRange range;
    private final String value;

    public MatchGroup(String s, IntRange intRange0) {
        Intrinsics.checkNotNullParameter(s, "value");
        Intrinsics.checkNotNullParameter(intRange0, "range");
        super();
        this.value = s;
        this.range = intRange0;
    }

    public final String component1() {
        return this.value;
    }

    public final IntRange component2() {
        return this.range;
    }

    public final MatchGroup copy(String s, IntRange intRange0) {
        Intrinsics.checkNotNullParameter(s, "value");
        Intrinsics.checkNotNullParameter(intRange0, "range");
        return new MatchGroup(s, intRange0);
    }

    public static MatchGroup copy$default(MatchGroup matchGroup0, String s, IntRange intRange0, int v, Object object0) {
        if((v & 1) != 0) {
            s = matchGroup0.value;
        }
        if((v & 2) != 0) {
            intRange0 = matchGroup0.range;
        }
        return matchGroup0.copy(s, intRange0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof MatchGroup)) {
            return false;
        }
        return Intrinsics.areEqual(this.value, ((MatchGroup)object0).value) ? Intrinsics.areEqual(this.range, ((MatchGroup)object0).range) : false;
    }

    public final IntRange getRange() {
        return this.range;
    }

    public final String getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode() * 0x1F + this.range.hashCode();
    }

    @Override
    public String toString() {
        return "MatchGroup(value=" + this.value + ", range=" + this.range + ')';
    }
}

