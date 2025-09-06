package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.util.AttributeArrayOwner;
import kotlin.reflect.jvm.internal.impl.util.TypeRegistry;

public final class TypeAttributes extends AttributeArrayOwner implements Iterable, KMappedMarker {
    public static final class Companion extends TypeRegistry {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final Collection access$getIndices(Companion typeAttributes$Companion0) {
            return typeAttributes$Companion0.getIndices();
        }

        public final TypeAttributes create(List list0) {
            Intrinsics.checkNotNullParameter(list0, "attributes");
            return list0.isEmpty() ? this.getEmpty() : new TypeAttributes(list0, null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.TypeRegistry
        public int customComputeIfAbsent(ConcurrentHashMap concurrentHashMap0, String s, Function1 function10) {
            Integer integer1;
            Intrinsics.checkNotNullParameter(concurrentHashMap0, "<this>");
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(function10, "compute");
            Integer integer0 = (Integer)concurrentHashMap0.get(s);
            if(integer0 == null) {
                synchronized(concurrentHashMap0) {
                    integer1 = (Integer)concurrentHashMap0.get(s);
                    if(integer1 == null) {
                        Object object0 = function10.invoke(s);
                        concurrentHashMap0.putIfAbsent(s, ((Number)object0).intValue());
                        integer1 = (Integer)object0;
                    }
                    Intrinsics.checkNotNullExpressionValue(integer1, "this[key] ?: compute(key…is.putIfAbsent(key, it) }");
                }
                return integer1.intValue();
            }
            return (int)integer0;
        }

        public final TypeAttributes getEmpty() {
            return TypeAttributes.Empty;
        }
    }

    public static final Companion Companion;
    private static final TypeAttributes Empty;

    static {
        TypeAttributes.Companion = new Companion(null);
        TypeAttributes.Empty = new TypeAttributes(CollectionsKt.emptyList());
    }

    private TypeAttributes(List list0) {
        for(Object object0: list0) {
            this.registerComponent(((TypeAttribute)object0).getKey(), ((TypeAttribute)object0));
        }
    }

    public TypeAttributes(List list0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(list0);
    }

    private TypeAttributes(TypeAttribute typeAttribute0) {
        this(CollectionsKt.listOf(typeAttribute0));
    }

    public final TypeAttributes add(TypeAttributes typeAttributes0) {
        TypeAttribute typeAttribute2;
        Intrinsics.checkNotNullParameter(typeAttributes0, "other");
        if(this.isEmpty() && typeAttributes0.isEmpty()) {
            return this;
        }
        List list0 = new ArrayList();
        for(Object object0: Companion.access$getIndices(TypeAttributes.Companion)) {
            int v = ((Number)object0).intValue();
            TypeAttribute typeAttribute0 = (TypeAttribute)this.getArrayMap().get(v);
            TypeAttribute typeAttribute1 = (TypeAttribute)typeAttributes0.getArrayMap().get(v);
            if(typeAttribute0 != null) {
                typeAttribute2 = typeAttribute0.add(typeAttribute1);
            }
            else if(typeAttribute1 == null) {
                typeAttribute2 = null;
            }
            else {
                typeAttribute2 = typeAttribute1.add(null);
            }
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(list0, typeAttribute2);
        }
        return TypeAttributes.Companion.create(list0);
    }

    public final boolean contains(TypeAttribute typeAttribute0) {
        Intrinsics.checkNotNullParameter(typeAttribute0, "attribute");
        KClass kClass0 = typeAttribute0.getKey();
        int v = TypeAttributes.Companion.getId(kClass0);
        return this.getArrayMap().get(v) != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.AbstractArrayMapOwner
    protected TypeRegistry getTypeRegistry() {
        return TypeAttributes.Companion;
    }

    public final TypeAttributes intersect(TypeAttributes typeAttributes0) {
        TypeAttribute typeAttribute2;
        Intrinsics.checkNotNullParameter(typeAttributes0, "other");
        if(this.isEmpty() && typeAttributes0.isEmpty()) {
            return this;
        }
        List list0 = new ArrayList();
        for(Object object0: Companion.access$getIndices(TypeAttributes.Companion)) {
            int v = ((Number)object0).intValue();
            TypeAttribute typeAttribute0 = (TypeAttribute)this.getArrayMap().get(v);
            TypeAttribute typeAttribute1 = (TypeAttribute)typeAttributes0.getArrayMap().get(v);
            if(typeAttribute0 != null) {
                typeAttribute2 = typeAttribute0.intersect(typeAttribute1);
            }
            else if(typeAttribute1 == null) {
                typeAttribute2 = null;
            }
            else {
                typeAttribute2 = typeAttribute1.intersect(null);
            }
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(list0, typeAttribute2);
        }
        return TypeAttributes.Companion.create(list0);
    }

    public final TypeAttributes plus(TypeAttribute typeAttribute0) {
        Intrinsics.checkNotNullParameter(typeAttribute0, "attribute");
        if(this.contains(typeAttribute0)) {
            return this;
        }
        if(this.isEmpty()) {
            return new TypeAttributes(typeAttribute0);
        }
        List list0 = CollectionsKt.plus(CollectionsKt.toList(this), typeAttribute0);
        return TypeAttributes.Companion.create(list0);
    }

    public final TypeAttributes remove(TypeAttribute typeAttribute0) {
        Intrinsics.checkNotNullParameter(typeAttribute0, "attribute");
        if(!this.isEmpty()) {
            Iterable iterable0 = this.getArrayMap();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                if(!Intrinsics.areEqual(((TypeAttribute)object0), typeAttribute0)) {
                    collection0.add(object0);
                }
            }
            return ((List)collection0).size() == this.getArrayMap().getSize() ? this : TypeAttributes.Companion.create(((List)collection0));
        }
        return this;
    }
}

