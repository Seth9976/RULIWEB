package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.MultiFieldValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

public final class ValueClassUtilKt {
    public static final ValueClassRepresentation loadValueClassRepresentation(Class protoBuf$Class0, NameResolver nameResolver0, TypeTable typeTable0, Function1 function10, Function1 function11) {
        SimpleTypeMarker simpleTypeMarker0;
        List list2;
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "<this>");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(function10, "typeDeserializer");
        Intrinsics.checkNotNullParameter(function11, "typeOfPublicProperty");
        if(protoBuf$Class0.getMultiFieldValueClassUnderlyingNameCount() > 0) {
            List list0 = protoBuf$Class0.getMultiFieldValueClassUnderlyingNameList();
            Intrinsics.checkNotNullExpressionValue(list0, "multiFieldValueClassUnderlyingNameList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(NameResolverUtilKt.getName(nameResolver0, ((int)(((Integer)object0)))));
            }
            Pair pair0 = TuplesKt.to(protoBuf$Class0.getMultiFieldValueClassUnderlyingTypeIdCount(), protoBuf$Class0.getMultiFieldValueClassUnderlyingTypeCount());
            if(Intrinsics.areEqual(pair0, TuplesKt.to(arrayList0.size(), 0))) {
                List list1 = protoBuf$Class0.getMultiFieldValueClassUnderlyingTypeIdList();
                Intrinsics.checkNotNullExpressionValue(list1, "multiFieldValueClassUnderlyingTypeIdList");
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
                for(Object object1: list1) {
                    Intrinsics.checkNotNullExpressionValue(((Integer)object1), "it");
                    arrayList1.add(typeTable0.get(((int)(((Integer)object1)))));
                }
                list2 = arrayList1;
            }
            else if(Intrinsics.areEqual(pair0, TuplesKt.to(0, arrayList0.size()))) {
                list2 = protoBuf$Class0.getMultiFieldValueClassUnderlyingTypeList();
            }
            else {
                throw new IllegalStateException(("class " + NameResolverUtilKt.getName(nameResolver0, protoBuf$Class0.getFqName()) + " has illegal multi-field value class representation").toString());
            }
            Intrinsics.checkNotNullExpressionValue(list2, "when (typeIdCount to typ…epresentation\")\n        }");
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for(Object object2: list2) {
                arrayList2.add(function10.invoke(object2));
            }
            return new MultiFieldValueClassRepresentation(CollectionsKt.zip(arrayList0, arrayList2));
        }
        if(protoBuf$Class0.hasInlineClassUnderlyingPropertyName()) {
            Name name0 = NameResolverUtilKt.getName(nameResolver0, protoBuf$Class0.getInlineClassUnderlyingPropertyName());
            Type protoBuf$Type0 = ProtoTypeTableUtilKt.inlineClassUnderlyingType(protoBuf$Class0, typeTable0);
            if(protoBuf$Type0 == null) {
            label_45:
                simpleTypeMarker0 = (SimpleTypeMarker)function11.invoke(name0);
                if(simpleTypeMarker0 == null) {
                    throw new IllegalStateException(("cannot determine underlying type for value class " + NameResolverUtilKt.getName(nameResolver0, protoBuf$Class0.getFqName()) + " with property " + name0).toString());
                }
            }
            else {
                simpleTypeMarker0 = (SimpleTypeMarker)function10.invoke(protoBuf$Type0);
                if(simpleTypeMarker0 == null) {
                    goto label_45;
                }
            }
            return new InlineClassRepresentation(name0, simpleTypeMarker0);
        }
        return null;
    }
}

