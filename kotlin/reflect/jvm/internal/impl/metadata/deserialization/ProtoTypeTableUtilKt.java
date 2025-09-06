package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;

public final class ProtoTypeTableUtilKt {
    public static final Type abbreviatedType(Type protoBuf$Type0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Type0.hasAbbreviatedType()) {
            return protoBuf$Type0.getAbbreviatedType();
        }
        return protoBuf$Type0.hasAbbreviatedTypeId() ? typeTable0.get(protoBuf$Type0.getAbbreviatedTypeId()) : null;
    }

    public static final List contextReceiverTypes(Class protoBuf$Class0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        List list0 = protoBuf$Class0.getContextReceiverTypeList();
        if(list0.isEmpty()) {
            list0 = null;
        }
        if(list0 == null) {
            List list1 = protoBuf$Class0.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(list1, "contextReceiverTypeIdList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(typeTable0.get(((int)(((Integer)object0)))));
            }
            return arrayList0;
        }
        return list0;
    }

    public static final List contextReceiverTypes(Function protoBuf$Function0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        List list0 = protoBuf$Function0.getContextReceiverTypeList();
        if(list0.isEmpty()) {
            list0 = null;
        }
        if(list0 == null) {
            List list1 = protoBuf$Function0.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(list1, "contextReceiverTypeIdList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(typeTable0.get(((int)(((Integer)object0)))));
            }
            return arrayList0;
        }
        return list0;
    }

    public static final List contextReceiverTypes(Property protoBuf$Property0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        List list0 = protoBuf$Property0.getContextReceiverTypeList();
        if(list0.isEmpty()) {
            list0 = null;
        }
        if(list0 == null) {
            List list1 = protoBuf$Property0.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(list1, "contextReceiverTypeIdList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(typeTable0.get(((int)(((Integer)object0)))));
            }
            return arrayList0;
        }
        return list0;
    }

    public static final Type expandedType(TypeAlias protoBuf$TypeAlias0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$TypeAlias0.hasExpandedType()) {
            Type protoBuf$Type0 = protoBuf$TypeAlias0.getExpandedType();
            Intrinsics.checkNotNullExpressionValue(protoBuf$Type0, "expandedType");
            return protoBuf$Type0;
        }
        if(!protoBuf$TypeAlias0.hasExpandedTypeId()) {
            throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias");
        }
        return typeTable0.get(protoBuf$TypeAlias0.getExpandedTypeId());
    }

    public static final Type flexibleUpperBound(Type protoBuf$Type0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Type0.hasFlexibleUpperBound()) {
            return protoBuf$Type0.getFlexibleUpperBound();
        }
        return protoBuf$Type0.hasFlexibleUpperBoundId() ? typeTable0.get(protoBuf$Type0.getFlexibleUpperBoundId()) : null;
    }

    public static final boolean hasReceiver(Function protoBuf$Function0) {
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "<this>");
        return protoBuf$Function0.hasReceiverType() || protoBuf$Function0.hasReceiverTypeId();
    }

    public static final boolean hasReceiver(Property protoBuf$Property0) {
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "<this>");
        return protoBuf$Property0.hasReceiverType() || protoBuf$Property0.hasReceiverTypeId();
    }

    public static final Type inlineClassUnderlyingType(Class protoBuf$Class0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Class0.hasInlineClassUnderlyingType()) {
            return protoBuf$Class0.getInlineClassUnderlyingType();
        }
        return protoBuf$Class0.hasInlineClassUnderlyingTypeId() ? typeTable0.get(protoBuf$Class0.getInlineClassUnderlyingTypeId()) : null;
    }

    public static final Type outerType(Type protoBuf$Type0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Type0.hasOuterType()) {
            return protoBuf$Type0.getOuterType();
        }
        return protoBuf$Type0.hasOuterTypeId() ? typeTable0.get(protoBuf$Type0.getOuterTypeId()) : null;
    }

    public static final Type receiverType(Function protoBuf$Function0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Function0.hasReceiverType()) {
            return protoBuf$Function0.getReceiverType();
        }
        return protoBuf$Function0.hasReceiverTypeId() ? typeTable0.get(protoBuf$Function0.getReceiverTypeId()) : null;
    }

    public static final Type receiverType(Property protoBuf$Property0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Property0.hasReceiverType()) {
            return protoBuf$Property0.getReceiverType();
        }
        return protoBuf$Property0.hasReceiverTypeId() ? typeTable0.get(protoBuf$Property0.getReceiverTypeId()) : null;
    }

    public static final Type returnType(Function protoBuf$Function0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Function0.hasReturnType()) {
            Type protoBuf$Type0 = protoBuf$Function0.getReturnType();
            Intrinsics.checkNotNullExpressionValue(protoBuf$Type0, "returnType");
            return protoBuf$Type0;
        }
        if(!protoBuf$Function0.hasReturnTypeId()) {
            throw new IllegalStateException("No returnType in ProtoBuf.Function");
        }
        return typeTable0.get(protoBuf$Function0.getReturnTypeId());
    }

    public static final Type returnType(Property protoBuf$Property0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Property0.hasReturnType()) {
            Type protoBuf$Type0 = protoBuf$Property0.getReturnType();
            Intrinsics.checkNotNullExpressionValue(protoBuf$Type0, "returnType");
            return protoBuf$Type0;
        }
        if(!protoBuf$Property0.hasReturnTypeId()) {
            throw new IllegalStateException("No returnType in ProtoBuf.Property");
        }
        return typeTable0.get(protoBuf$Property0.getReturnTypeId());
    }

    public static final List supertypes(Class protoBuf$Class0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        List list0 = protoBuf$Class0.getSupertypeList();
        if(list0.isEmpty()) {
            list0 = null;
        }
        if(list0 == null) {
            List list1 = protoBuf$Class0.getSupertypeIdList();
            Intrinsics.checkNotNullExpressionValue(list1, "supertypeIdList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(typeTable0.get(((int)(((Integer)object0)))));
            }
            return arrayList0;
        }
        return list0;
    }

    public static final Type type(Argument protoBuf$Type$Argument0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type$Argument0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$Type$Argument0.hasType()) {
            return protoBuf$Type$Argument0.getType();
        }
        return protoBuf$Type$Argument0.hasTypeId() ? typeTable0.get(protoBuf$Type$Argument0.getTypeId()) : null;
    }

    public static final Type type(ValueParameter protoBuf$ValueParameter0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$ValueParameter0.hasType()) {
            Type protoBuf$Type0 = protoBuf$ValueParameter0.getType();
            Intrinsics.checkNotNullExpressionValue(protoBuf$Type0, "type");
            return protoBuf$Type0;
        }
        if(!protoBuf$ValueParameter0.hasTypeId()) {
            throw new IllegalStateException("No type in ProtoBuf.ValueParameter");
        }
        return typeTable0.get(protoBuf$ValueParameter0.getTypeId());
    }

    public static final Type underlyingType(TypeAlias protoBuf$TypeAlias0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$TypeAlias0.hasUnderlyingType()) {
            Type protoBuf$Type0 = protoBuf$TypeAlias0.getUnderlyingType();
            Intrinsics.checkNotNullExpressionValue(protoBuf$Type0, "underlyingType");
            return protoBuf$Type0;
        }
        if(!protoBuf$TypeAlias0.hasUnderlyingTypeId()) {
            throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias");
        }
        return typeTable0.get(protoBuf$TypeAlias0.getUnderlyingTypeId());
    }

    public static final List upperBounds(TypeParameter protoBuf$TypeParameter0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        List list0 = protoBuf$TypeParameter0.getUpperBoundList();
        if(list0.isEmpty()) {
            list0 = null;
        }
        if(list0 == null) {
            List list1 = protoBuf$TypeParameter0.getUpperBoundIdList();
            Intrinsics.checkNotNullExpressionValue(list1, "upperBoundIdList");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "it");
                arrayList0.add(typeTable0.get(((int)(((Integer)object0)))));
            }
            return arrayList0;
        }
        return list0;
    }

    public static final Type varargElementType(ValueParameter protoBuf$ValueParameter0, TypeTable typeTable0) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter0, "<this>");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        if(protoBuf$ValueParameter0.hasVarargElementType()) {
            return protoBuf$ValueParameter0.getVarargElementType();
        }
        return protoBuf$ValueParameter0.hasVarargElementTypeId() ? typeTable0.get(protoBuf$ValueParameter0.getVarargElementTypeId()) : null;
    }
}

