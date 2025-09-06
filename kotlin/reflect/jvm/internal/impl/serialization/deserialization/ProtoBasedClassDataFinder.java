package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class ProtoBasedClassDataFinder implements ClassDataFinder {
    private final Map classIdToProto;
    private final Function1 classSource;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;

    public ProtoBasedClassDataFinder(PackageFragment protoBuf$PackageFragment0, NameResolver nameResolver0, BinaryVersion binaryVersion0, Function1 function10) {
        Intrinsics.checkNotNullParameter(protoBuf$PackageFragment0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(function10, "classSource");
        super();
        this.nameResolver = nameResolver0;
        this.metadataVersion = binaryVersion0;
        this.classSource = function10;
        List list0 = protoBuf$PackageFragment0.getClass_List();
        Intrinsics.checkNotNullExpressionValue(list0, "proto.class_List");
        Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list0, 10)), 16));
        for(Object object0: list0) {
            map0.put(NameResolverUtilKt.getClassId(this.nameResolver, ((Class)object0).getFqName()), object0);
        }
        this.classIdToProto = map0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder
    public ClassData findClassData(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        Class protoBuf$Class0 = (Class)this.classIdToProto.get(classId0);
        if(protoBuf$Class0 == null) {
            return null;
        }
        SourceElement sourceElement0 = (SourceElement)this.classSource.invoke(classId0);
        return new ClassData(this.nameResolver, protoBuf$Class0, this.metadataVersion, sourceElement0);
    }

    public final Collection getAllClassIds() {
        return this.classIdToProto.keySet();
    }
}

