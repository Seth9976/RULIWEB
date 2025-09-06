package androidx.work.impl.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000E\u001A\u00020\u0003HÆ\u0003J\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ$\u0010\u0010\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001A\u00020\u00052\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001A\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001A\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u001A\u0010\u0004\u001A\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001A\u0004\b\u000B\u0010\f¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/model/Preference;", "", "key", "", "value", "", "(Ljava/lang/String;Z)V", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "getKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Long;)Landroidx/work/impl/model/Preference;", "equals", "other", "hashCode", "", "toString", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Preference {
    private final String key;
    private final Long value;

    public Preference(String s, Long long0) {
        Intrinsics.checkNotNullParameter(s, "key");
        super();
        this.key = s;
        this.value = long0;
    }

    public Preference(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "key");
        this(s, ((long)(z ? 1L : 0L)));
    }

    public final String component1() {
        return this.key;
    }

    public final Long component2() {
        return this.value;
    }

    public final Preference copy(String s, Long long0) {
        Intrinsics.checkNotNullParameter(s, "key");
        return new Preference(s, long0);
    }

    public static Preference copy$default(Preference preference0, String s, Long long0, int v, Object object0) {
        if((v & 1) != 0) {
            s = preference0.key;
        }
        if((v & 2) != 0) {
            long0 = preference0.value;
        }
        return preference0.copy(s, long0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Preference)) {
            return false;
        }
        return Intrinsics.areEqual(this.key, ((Preference)object0).key) ? Intrinsics.areEqual(this.value, ((Preference)object0).value) : false;
    }

    public final String getKey() {
        return this.key;
    }

    public final Long getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int v = this.key.hashCode();
        return this.value == null ? v * 0x1F : v * 0x1F + this.value.hashCode();
    }

    @Override
    public String toString() {
        return "Preference(key=" + this.key + ", value=" + this.value + ')';
    }
}

