package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000F2\u00020\u0001:\u0001\u000FB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0015\u0012\u000E\u0010\u0003\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0002J\u0010\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u000EH\u0016R\u0016\u0010\u0003\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/collections/builders/SerializedMap;", "Ljava/io/Externalizable;", "()V", "map", "", "(Ljava/util/Map;)V", "readExternal", "", "input", "Ljava/io/ObjectInput;", "readResolve", "", "writeExternal", "output", "Ljava/io/ObjectOutput;", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class SerializedMap implements Externalizable {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/collections/builders/SerializedMap$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private Map map;
    private static final long serialVersionUID;

    static {
        SerializedMap.Companion = new Companion(null);
    }

    public SerializedMap() {
        this(MapsKt.emptyMap());
    }

    public SerializedMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "map");
        super();
        this.map = map0;
    }

    @Override
    public void readExternal(ObjectInput objectInput0) {
        Intrinsics.checkNotNullParameter(objectInput0, "input");
        int v = objectInput0.readByte();
        if(v != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + v);
        }
        int v1 = objectInput0.readInt();
        if(v1 < 0) {
            throw new InvalidObjectException("Illegal size value: " + v1 + '.');
        }
        Map map0 = MapsKt.createMapBuilder(v1);
        for(int v2 = 0; v2 < v1; ++v2) {
            map0.put(objectInput0.readObject(), objectInput0.readObject());
        }
        this.map = MapsKt.build(map0);
    }

    private final Object readResolve() {
        return this.map;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput0) {
        Intrinsics.checkNotNullParameter(objectOutput0, "output");
        objectOutput0.writeByte(0);
        objectOutput0.writeInt(this.map.size());
        for(Object object0: this.map.entrySet()) {
            objectOutput0.writeObject(((Map.Entry)object0).getKey());
            objectOutput0.writeObject(((Map.Entry)object0).getValue());
        }
    }
}

