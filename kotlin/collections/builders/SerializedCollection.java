package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0019\u0012\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\rH\u0002J\u0010\u0010\u000E\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016R\u0012\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlin/collections/builders/SerializedCollection;", "Ljava/io/Externalizable;", "()V", "collection", "", "tag", "", "(Ljava/util/Collection;I)V", "readExternal", "", "input", "Ljava/io/ObjectInput;", "readResolve", "", "writeExternal", "output", "Ljava/io/ObjectOutput;", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class SerializedCollection implements Externalizable {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/collections/builders/SerializedCollection$Companion;", "", "()V", "serialVersionUID", "", "tagList", "", "tagSet", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    private Collection collection;
    private static final long serialVersionUID = 0L;
    private final int tag;
    public static final int tagList = 0;
    public static final int tagSet = 1;

    static {
        SerializedCollection.Companion = new Companion(null);
    }

    public SerializedCollection() {
        this(CollectionsKt.emptyList(), 0);
    }

    public SerializedCollection(Collection collection0, int v) {
        Intrinsics.checkNotNullParameter(collection0, "collection");
        super();
        this.collection = collection0;
        this.tag = v;
    }

    @Override
    public void readExternal(ObjectInput objectInput0) {
        Collection collection0;
        Intrinsics.checkNotNullParameter(objectInput0, "input");
        int v = objectInput0.readByte();
        if((v & -2) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + v + '.');
        }
        int v1 = 0;
        int v2 = objectInput0.readInt();
        if(v2 < 0) {
            throw new InvalidObjectException("Illegal size value: " + v2 + '.');
        }
        switch(v & 1) {
            case 0: {
                List list0 = CollectionsKt.createListBuilder(v2);
                while(v1 < v2) {
                    list0.add(objectInput0.readObject());
                    ++v1;
                }
                collection0 = CollectionsKt.build(list0);
                break;
            }
            case 1: {
                Set set0 = SetsKt.createSetBuilder(v2);
                while(v1 < v2) {
                    set0.add(objectInput0.readObject());
                    ++v1;
                }
                collection0 = SetsKt.build(set0);
                break;
            }
            default: {
                throw new InvalidObjectException("Unsupported collection type tag: " + (v & 1) + '.');
            }
        }
        this.collection = collection0;
    }

    private final Object readResolve() {
        return this.collection;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput0) {
        Intrinsics.checkNotNullParameter(objectOutput0, "output");
        objectOutput0.writeByte(this.tag);
        objectOutput0.writeInt(this.collection.size());
        for(Object object0: this.collection) {
            objectOutput0.writeObject(object0);
        }
    }
}

