package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.text.StringsKt;

public final class RawTypeImpl extends FlexibleType implements RawType {
    public RawTypeImpl(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
        Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
        this(simpleType0, simpleType1, false);
    }

    private RawTypeImpl(SimpleType simpleType0, SimpleType simpleType1, boolean z) {
        super(simpleType0, simpleType1);
        if(!z) {
            KotlinTypeChecker.DEFAULT.isSubtypeOf(simpleType0, simpleType1);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        return this.getLowerBound();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public MemberScope getMemberScope() {
        ClassifierDescriptor classifierDescriptor0 = this.getConstructor().getDeclarationDescriptor();
        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        if(classDescriptor0 == null) {
            throw new IllegalStateException(("Incorrect classifier: " + this.getConstructor().getDeclarationDescriptor()).toString());
        }
        MemberScope memberScope0 = classDescriptor0.getMemberScope(new RawSubstitution(null, 1, null));
        Intrinsics.checkNotNullExpressionValue(memberScope0, "classDescriptor.getMemberScope(RawSubstitution())");
        return memberScope0;
    }

    public RawTypeImpl makeNullableAsSpecified(boolean z) {
        return new RawTypeImpl(this.getLowerBound().makeNullableAsSpecified(z), this.getUpperBound().makeNullableAsSpecified(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    public FlexibleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getLowerBound());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        KotlinType kotlinType1 = kotlinTypeRefiner0.refineType(this.getUpperBound());
        Intrinsics.checkNotNull(kotlinType1, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return new RawTypeImpl(((SimpleType)kotlinType0), ((SimpleType)kotlinType1), true);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String render(DescriptorRenderer descriptorRenderer0, DescriptorRendererOptions descriptorRendererOptions0) {
        Intrinsics.checkNotNullParameter(descriptorRenderer0, "renderer");
        Intrinsics.checkNotNullParameter(descriptorRendererOptions0, "options");
        String s = descriptorRenderer0.renderType(this.getLowerBound());
        String s1 = descriptorRenderer0.renderType(this.getUpperBound());
        if(descriptorRendererOptions0.getDebugMode()) {
            return "raw (" + s + ".." + s1 + ')';
        }
        if(this.getUpperBound().getArguments().isEmpty()) {
            return descriptorRenderer0.renderFlexibleType(s, s1, TypeUtilsKt.getBuiltIns(this));
        }
        List list0 = RawTypeImpl.render$renderArguments(descriptorRenderer0, this.getLowerBound());
        List list1 = RawTypeImpl.render$renderArguments(descriptorRenderer0, this.getUpperBound());
        String s2 = CollectionsKt.joinToString$default(list0, ", ", null, null, 0, null, kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1.INSTANCE, 30, null);
        Iterable iterable0 = CollectionsKt.zip(list0, list1);
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                if(RawTypeImpl.render$onlyOutDiffers(((String)((Pair)object0).getFirst()), ((String)((Pair)object0).getSecond()))) {
                    continue;
                }
                goto label_19;
            }
        }
        s1 = RawTypeImpl.render$replaceArgs(s1, s2);
    label_19:
        String s3 = RawTypeImpl.render$replaceArgs(s, s2);
        return Intrinsics.areEqual(s3, s1) ? s3 : descriptorRenderer0.renderFlexibleType(s3, s1, TypeUtilsKt.getBuiltIns(this));

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl.render.newArgs.1() {
                super(1);
            }

            public final CharSequence invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "it");
                return "(raw) " + s;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }
        }

    }

    // 去混淆评级： 低(20)
    private static final boolean render$onlyOutDiffers(String s, String s1) {
        return Intrinsics.areEqual(s, StringsKt.removePrefix(s1, "out ")) || Intrinsics.areEqual(s1, "*");
    }

    private static final List render$renderArguments(DescriptorRenderer descriptorRenderer0, KotlinType kotlinType0) {
        Iterable iterable0 = kotlinType0.getArguments();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(descriptorRenderer0.renderTypeProjection(((TypeProjection)object0)));
        }
        return arrayList0;
    }

    private static final String render$replaceArgs(String s, String s1) {
        return StringsKt.contains$default(s, '<', false, 2, null) ? StringsKt.substringBefore$default(s, '<', null, 2, null) + '<' + s1 + '>' + StringsKt.substringAfterLast$default(s, '>', null, 2, null) : s;
    }

    public RawTypeImpl replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new RawTypeImpl(this.getLowerBound().replaceAttributes(typeAttributes0), this.getUpperBound().replaceAttributes(typeAttributes0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

