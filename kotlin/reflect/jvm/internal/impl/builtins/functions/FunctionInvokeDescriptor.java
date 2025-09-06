package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl.CopyConfiguration;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.util.OperatorNameConventions;

public final class FunctionInvokeDescriptor extends SimpleFunctionDescriptorImpl {
    public static final class Factory {
        private Factory() {
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final FunctionInvokeDescriptor create(FunctionClassDescriptor functionClassDescriptor0, boolean z) {
            Intrinsics.checkNotNullParameter(functionClassDescriptor0, "functionClass");
            List list0 = functionClassDescriptor0.getDeclaredTypeParameters();
            FunctionInvokeDescriptor functionInvokeDescriptor0 = new FunctionInvokeDescriptor(functionClassDescriptor0, null, Kind.DECLARATION, z, null);
            ReceiverParameterDescriptor receiverParameterDescriptor0 = functionClassDescriptor0.getThisAsReceiverParameter();
            List list1 = CollectionsKt.emptyList();
            List list2 = CollectionsKt.emptyList();
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: list0) {
                if(((TypeParameterDescriptor)object0).getVariance() != Variance.IN_VARIANCE) {
                    break;
                }
                arrayList0.add(object0);
            }
            Iterable iterable0 = CollectionsKt.withIndex(arrayList0);
            ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            for(Object object1: iterable0) {
                int v = ((IndexedValue)object1).getIndex();
                TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)((IndexedValue)object1).getValue();
                arrayList1.add(FunctionInvokeDescriptor.Factory.createValueParameter(functionInvokeDescriptor0, v, typeParameterDescriptor0));
            }
            functionInvokeDescriptor0.initialize(null, receiverParameterDescriptor0, list1, list2, arrayList1, ((TypeParameterDescriptor)CollectionsKt.last(list0)).getDefaultType(), Modality.ABSTRACT, DescriptorVisibilities.PUBLIC);
            functionInvokeDescriptor0.setHasSynthesizedParameterNames(true);
            return functionInvokeDescriptor0;
        }

        private final ValueParameterDescriptor createValueParameter(FunctionInvokeDescriptor functionInvokeDescriptor0, int v, TypeParameterDescriptor typeParameterDescriptor0) {
            String s1;
            String s = typeParameterDescriptor0.getName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "typeParameter.name.asString()");
            if(Intrinsics.areEqual(s, "T")) {
                s1 = "instance";
            }
            else if(Intrinsics.areEqual(s, "E")) {
                s1 = "receiver";
            }
            else {
                s1 = s.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            }
            Name name0 = Name.identifier(s1);
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(name)");
            SimpleType simpleType0 = typeParameterDescriptor0.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(simpleType0, "typeParameter.defaultType");
            Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
            return new ValueParameterDescriptorImpl(functionInvokeDescriptor0, null, v, Annotations.Companion.getEMPTY(), name0, simpleType0, false, false, false, null, SourceElement.NO_SOURCE);
        }
    }

    public static final Factory Factory;

    static {
        FunctionInvokeDescriptor.Factory = new Factory(null);
    }

    private FunctionInvokeDescriptor(DeclarationDescriptor declarationDescriptor0, FunctionInvokeDescriptor functionInvokeDescriptor0, Kind callableMemberDescriptor$Kind0, boolean z) {
        super(declarationDescriptor0, functionInvokeDescriptor0, Annotations.Companion.getEMPTY(), OperatorNameConventions.INVOKE, callableMemberDescriptor$Kind0, SourceElement.NO_SOURCE);
        this.setOperator(true);
        this.setSuspend(z);
        this.setHasStableParameterNames(false);
    }

    public FunctionInvokeDescriptor(DeclarationDescriptor declarationDescriptor0, FunctionInvokeDescriptor functionInvokeDescriptor0, Kind callableMemberDescriptor$Kind0, boolean z, DefaultConstructorMarker defaultConstructorMarker0) {
        this(declarationDescriptor0, functionInvokeDescriptor0, callableMemberDescriptor$Kind0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        return new FunctionInvokeDescriptor(declarationDescriptor0, ((FunctionInvokeDescriptor)functionDescriptor0), callableMemberDescriptor$Kind0, this.isSuspend());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    protected FunctionDescriptor doSubstitute(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
        Intrinsics.checkNotNullParameter(functionDescriptorImpl$CopyConfiguration0, "configuration");
        FunctionInvokeDescriptor functionInvokeDescriptor0 = (FunctionInvokeDescriptor)super.doSubstitute(functionDescriptorImpl$CopyConfiguration0);
        if(functionInvokeDescriptor0 == null) {
            return null;
        }
        List list0 = functionInvokeDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "substituted.valueParameters");
        if(!(list0 instanceof Collection) || !list0.isEmpty()) {
            for(Object object0: list0) {
                KotlinType kotlinType0 = ((ValueParameterDescriptor)object0).getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
                if(FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(kotlinType0) != null) {
                    List list1 = functionInvokeDescriptor0.getValueParameters();
                    Intrinsics.checkNotNullExpressionValue(list1, "substituted.valueParameters");
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
                    for(Object object1: list1) {
                        KotlinType kotlinType1 = ((ValueParameterDescriptor)object1).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType1, "it.type");
                        arrayList0.add(FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(kotlinType1));
                    }
                    return functionInvokeDescriptor0.replaceParameterNames(arrayList0);
                }
                if(false) {
                    break;
                }
            }
        }
        return functionInvokeDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInline() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isTailrec() {
        return false;
    }

    private final FunctionDescriptor replaceParameterNames(List list0) {
        int v = this.getValueParameters().size() - list0.size();
        boolean z = true;
        if(v == 0) {
            List list1 = this.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "valueParameters");
            Iterable iterable0 = CollectionsKt.zip(list0, list1);
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object0: iterable0) {
                    if(Intrinsics.areEqual(((Name)((Pair)object0).component1()), ((ValueParameterDescriptor)((Pair)object0).component2()).getName())) {
                        continue;
                    }
                    goto label_13;
                }
            }
            return this;
        }
    label_13:
        List list2 = this.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list2, "valueParameters");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for(Object object1: list2) {
            Name name0 = ((ValueParameterDescriptor)object1).getName();
            Intrinsics.checkNotNullExpressionValue(name0, "it.name");
            int v1 = ((ValueParameterDescriptor)object1).getIndex();
            int v2 = v1 - v;
            if(v2 >= 0) {
                Name name1 = (Name)list0.get(v2);
                if(name1 != null) {
                    name0 = name1;
                }
            }
            arrayList0.add(((ValueParameterDescriptor)object1).copy(this, name0, v1));
        }
        CopyConfiguration functionDescriptorImpl$CopyConfiguration0 = this.newCopyBuilder(TypeSubstitutor.EMPTY);
        if(!(list0 instanceof Collection) || !list0.isEmpty()) {
            for(Object object2: list0) {
                if(((Name)object2) != null) {
                    continue;
                }
                goto label_37;
            }
        }
        z = false;
    label_37:
        CopyConfiguration functionDescriptorImpl$CopyConfiguration1 = functionDescriptorImpl$CopyConfiguration0.setHasSynthesizedParameterNames(z).setValueParameters(arrayList0).setOriginal(this.getOriginal());
        Intrinsics.checkNotNullExpressionValue(functionDescriptorImpl$CopyConfiguration1, "newCopyBuilder(TypeSubst…   .setOriginal(original)");
        FunctionDescriptor functionDescriptor0 = super.doSubstitute(functionDescriptorImpl$CopyConfiguration1);
        Intrinsics.checkNotNull(functionDescriptor0);
        return functionDescriptor0;
    }
}

