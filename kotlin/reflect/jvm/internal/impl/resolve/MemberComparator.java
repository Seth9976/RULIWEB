package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Comparator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.AnnotationArgumentsRenderingPolicy;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererModifier;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public class MemberComparator implements Comparator {
    public static class NameAndTypeMemberComparator implements Comparator {
        public static final NameAndTypeMemberComparator INSTANCE;

        static {
            NameAndTypeMemberComparator.INSTANCE = new NameAndTypeMemberComparator();
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((DeclarationDescriptor)object0), ((DeclarationDescriptor)object1));
        }

        public int compare(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
            Integer integer0 = NameAndTypeMemberComparator.compareInternal(declarationDescriptor0, declarationDescriptor1);
            return integer0 == null ? 0 : ((int)integer0);
        }

        private static Integer compareInternal(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
            int v = NameAndTypeMemberComparator.getDeclarationPriority(declarationDescriptor1) - NameAndTypeMemberComparator.getDeclarationPriority(declarationDescriptor0);
            if(v != 0) {
                return v;
            }
            if(DescriptorUtils.isEnumEntry(declarationDescriptor0) && DescriptorUtils.isEnumEntry(declarationDescriptor1)) {
                return 0;
            }
            int v1 = declarationDescriptor0.getName().compareTo(declarationDescriptor1.getName());
            return v1 == 0 ? null : v1;
        }

        private static int getDeclarationPriority(DeclarationDescriptor declarationDescriptor0) {
            if(DescriptorUtils.isEnumEntry(declarationDescriptor0)) {
                return 8;
            }
            if(declarationDescriptor0 instanceof ConstructorDescriptor) {
                return 7;
            }
            if(declarationDescriptor0 instanceof PropertyDescriptor) {
                return ((PropertyDescriptor)declarationDescriptor0).getExtensionReceiverParameter() == null ? 6 : 5;
            }
            if(declarationDescriptor0 instanceof FunctionDescriptor) {
                return ((FunctionDescriptor)declarationDescriptor0).getExtensionReceiverParameter() == null ? 4 : 3;
            }
            if(declarationDescriptor0 instanceof ClassDescriptor) {
                return 2;
            }
            return declarationDescriptor0 instanceof TypeAliasDescriptor ? 1 : 0;
        }
    }

    static final boolean $assertionsDisabled;
    public static final MemberComparator INSTANCE;
    private static final DescriptorRenderer RENDERER;

    static {
        MemberComparator.INSTANCE = new MemberComparator();
        kotlin.reflect.jvm.internal.impl.resolve.MemberComparator.1 memberComparator$10 = new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DescriptorRendererOptions)object0));
            }

            public Unit invoke(DescriptorRendererOptions descriptorRendererOptions0) {
                descriptorRendererOptions0.setWithDefinedIn(false);
                descriptorRendererOptions0.setVerbose(true);
                descriptorRendererOptions0.setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy.UNLESS_EMPTY);
                descriptorRendererOptions0.setModifiers(DescriptorRendererModifier.ALL);
                return Unit.INSTANCE;
            }
        };
        MemberComparator.RENDERER = DescriptorRenderer.Companion.withOptions(memberComparator$10);
    }

    @Override
    public int compare(Object object0, Object object1) {
        return this.compare(((DeclarationDescriptor)object0), ((DeclarationDescriptor)object1));
    }

    public int compare(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
        Integer integer0 = NameAndTypeMemberComparator.compareInternal(declarationDescriptor0, declarationDescriptor1);
        if(integer0 != null) {
            return (int)integer0;
        }
        if(declarationDescriptor0 instanceof TypeAliasDescriptor && declarationDescriptor1 instanceof TypeAliasDescriptor) {
            SimpleType simpleType0 = ((TypeAliasDescriptor)declarationDescriptor0).getUnderlyingType();
            String s = MemberComparator.RENDERER.renderType(simpleType0);
            SimpleType simpleType1 = ((TypeAliasDescriptor)declarationDescriptor1).getUnderlyingType();
            int v = s.compareTo(MemberComparator.RENDERER.renderType(simpleType1));
            if(v == 0) {
                goto label_68;
            }
            return v;
        }
        if(declarationDescriptor0 instanceof CallableDescriptor && declarationDescriptor1 instanceof CallableDescriptor) {
            ReceiverParameterDescriptor receiverParameterDescriptor0 = ((CallableDescriptor)declarationDescriptor0).getExtensionReceiverParameter();
            ReceiverParameterDescriptor receiverParameterDescriptor1 = ((CallableDescriptor)declarationDescriptor1).getExtensionReceiverParameter();
            if(receiverParameterDescriptor0 != null) {
                KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
                String s1 = MemberComparator.RENDERER.renderType(kotlinType0);
                KotlinType kotlinType1 = receiverParameterDescriptor1.getType();
                int v1 = s1.compareTo(MemberComparator.RENDERER.renderType(kotlinType1));
                if(v1 != 0) {
                    return v1;
                }
            }
            List list0 = ((CallableDescriptor)declarationDescriptor0).getValueParameters();
            List list1 = ((CallableDescriptor)declarationDescriptor1).getValueParameters();
            for(int v2 = 0; v2 < Math.min(list0.size(), list1.size()); ++v2) {
                KotlinType kotlinType2 = ((ValueParameterDescriptor)list0.get(v2)).getType();
                String s2 = MemberComparator.RENDERER.renderType(kotlinType2);
                KotlinType kotlinType3 = ((ValueParameterDescriptor)list1.get(v2)).getType();
                int v3 = s2.compareTo(MemberComparator.RENDERER.renderType(kotlinType3));
                if(v3 != 0) {
                    return v3;
                }
            }
            int v4 = list0.size() - list1.size();
            if(v4 != 0) {
                return v4;
            }
            List list2 = ((CallableDescriptor)declarationDescriptor0).getTypeParameters();
            List list3 = ((CallableDescriptor)declarationDescriptor1).getTypeParameters();
            for(int v5 = 0; v5 < Math.min(list2.size(), list3.size()); ++v5) {
                List list4 = ((TypeParameterDescriptor)list2.get(v5)).getUpperBounds();
                List list5 = ((TypeParameterDescriptor)list3.get(v5)).getUpperBounds();
                int v6 = list4.size() - list5.size();
                if(v6 != 0) {
                    return v6;
                }
                for(int v7 = 0; v7 < list4.size(); ++v7) {
                    KotlinType kotlinType4 = (KotlinType)list4.get(v7);
                    String s3 = MemberComparator.RENDERER.renderType(kotlinType4);
                    KotlinType kotlinType5 = (KotlinType)list5.get(v7);
                    int v8 = s3.compareTo(MemberComparator.RENDERER.renderType(kotlinType5));
                    if(v8 != 0) {
                        return v8;
                    }
                }
            }
            int v9 = list2.size() - list3.size();
            if(v9 != 0) {
                return v9;
            }
            if(!(((CallableDescriptor)declarationDescriptor0) instanceof CallableMemberDescriptor) || !(((CallableDescriptor)declarationDescriptor1) instanceof CallableMemberDescriptor)) {
                goto label_68;
            }
            int v10 = ((CallableMemberDescriptor)(((CallableDescriptor)declarationDescriptor0))).getKind().ordinal() - ((CallableMemberDescriptor)(((CallableDescriptor)declarationDescriptor1))).getKind().ordinal();
            if(v10 == 0) {
                goto label_68;
            }
            return v10;
        }
        if(!(declarationDescriptor0 instanceof ClassDescriptor) || !(declarationDescriptor1 instanceof ClassDescriptor)) {
            throw new AssertionError(String.format("Unsupported pair of descriptors:\n\'%s\' Class: %s\n%s\' Class: %s", declarationDescriptor0, declarationDescriptor0.getClass(), declarationDescriptor1, declarationDescriptor1.getClass()));
        }
        if(((ClassDescriptor)declarationDescriptor0).getKind().ordinal() != ((ClassDescriptor)declarationDescriptor1).getKind().ordinal()) {
            return ((ClassDescriptor)declarationDescriptor0).getKind().ordinal() - ((ClassDescriptor)declarationDescriptor1).getKind().ordinal();
        }
        if(((ClassDescriptor)declarationDescriptor0).isCompanionObject() != ((ClassDescriptor)declarationDescriptor1).isCompanionObject()) {
            return ((ClassDescriptor)declarationDescriptor0).isCompanionObject() ? 1 : -1;
        }
    label_68:
        int v11 = MemberComparator.RENDERER.render(declarationDescriptor0).compareTo(MemberComparator.RENDERER.render(declarationDescriptor1));
        return v11 == 0 ? DescriptorUtils.getContainingModule(declarationDescriptor0).getName().compareTo(DescriptorUtils.getContainingModule(declarationDescriptor1).getName()) : v11;
    }
}

