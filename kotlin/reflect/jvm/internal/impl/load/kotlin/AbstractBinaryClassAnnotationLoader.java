package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Package;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.text.StringsKt;

public abstract class AbstractBinaryClassAnnotationLoader implements AnnotationLoader {
    public static abstract class AnnotationsContainer {
        public abstract Map getMemberAnnotations();
    }

    static enum PropertyRelatedElement {
        PROPERTY,
        BACKING_FIELD,
        DELEGATE_FIELD;

        private static final PropertyRelatedElement[] $values() [...] // Inlined contents
    }

    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[AnnotatedCallableKind.values().length];
            try {
                arr_v[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[AnnotatedCallableKind.PROPERTY.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final KotlinClassFinder kotlinClassFinder;

    public AbstractBinaryClassAnnotationLoader(KotlinClassFinder kotlinClassFinder0) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "kotlinClassFinder");
        super();
        this.kotlinClassFinder = kotlinClassFinder0;
    }

    private final int computeJvmParameterIndexShift(ProtoContainer protoContainer0, MessageLite messageLite0) {
        if(messageLite0 instanceof Function) {
            return ProtoTypeTableUtilKt.hasReceiver(((Function)messageLite0)) ? 1 : 0;
        }
        if(messageLite0 instanceof Property) {
            return ProtoTypeTableUtilKt.hasReceiver(((Property)messageLite0)) ? 1 : 0;
        }
        if(!(messageLite0 instanceof Constructor)) {
            throw new UnsupportedOperationException("Unsupported message: " + messageLite0.getClass());
        }
        Intrinsics.checkNotNull(protoContainer0, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.ProtoContainer.Class");
        if(((Class)protoContainer0).getKind() == Kind.ENUM_CLASS) {
            return 2;
        }
        return ((Class)protoContainer0).isInner() ? 1 : 0;
    }

    private final List findClassAndLoadMemberAnnotations(ProtoContainer protoContainer0, MemberSignature memberSignature0, boolean z, boolean z1, Boolean boolean0, boolean z2) {
        KotlinJvmBinaryClass kotlinJvmBinaryClass0 = this.findClassWithAnnotationsAndInitializers(protoContainer0, this.getSpecialCaseContainerClass(protoContainer0, z, z1, boolean0, z2));
        if(kotlinJvmBinaryClass0 == null) {
            return CollectionsKt.emptyList();
        }
        List list0 = (List)this.getAnnotationsContainer(kotlinJvmBinaryClass0).getMemberAnnotations().get(memberSignature0);
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    static List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader0, ProtoContainer protoContainer0, MemberSignature memberSignature0, boolean z, boolean z1, Boolean boolean0, boolean z2, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
        }
        if((v & 4) != 0) {
            z = false;
        }
        if((v & 8) != 0) {
            z1 = false;
        }
        if((v & 16) != 0) {
            boolean0 = null;
        }
        if((v & 0x20) != 0) {
            z2 = false;
        }
        return abstractBinaryClassAnnotationLoader0.findClassAndLoadMemberAnnotations(protoContainer0, memberSignature0, z, z1, boolean0, z2);
    }

    protected final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer protoContainer0, KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        if(kotlinJvmBinaryClass0 == null) {
            return protoContainer0 instanceof Class ? this.toBinaryClass(((Class)protoContainer0)) : null;
        }
        return kotlinJvmBinaryClass0;
    }

    protected abstract AnnotationsContainer getAnnotationsContainer(KotlinJvmBinaryClass arg1);

    protected byte[] getCachedFileContent(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
        return null;
    }

    protected final MemberSignature getCallableSignature(MessageLite messageLite0, NameResolver nameResolver0, TypeTable typeTable0, AnnotatedCallableKind annotatedCallableKind0, boolean z) {
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        if(messageLite0 instanceof Constructor) {
            Method jvmMemberSignature$Method0 = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature(((Constructor)messageLite0), nameResolver0, typeTable0);
            return jvmMemberSignature$Method0 == null ? null : MemberSignature.Companion.fromJvmMemberSignature(jvmMemberSignature$Method0);
        }
        if(messageLite0 instanceof Function) {
            Method jvmMemberSignature$Method1 = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature(((Function)messageLite0), nameResolver0, typeTable0);
            return jvmMemberSignature$Method1 == null ? null : MemberSignature.Companion.fromJvmMemberSignature(jvmMemberSignature$Method1);
        }
        if(messageLite0 instanceof Property) {
            Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.propertySignature, "propertySignature");
            JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = (JvmPropertySignature)ProtoBufUtilKt.getExtensionOrNull(((ExtendableMessage)messageLite0), JvmProtoBuf.propertySignature);
            if(jvmProtoBuf$JvmPropertySignature0 == null) {
                return null;
            }
            switch(WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind0.ordinal()]) {
                case 1: {
                    if(jvmProtoBuf$JvmPropertySignature0.hasGetter()) {
                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature1 = jvmProtoBuf$JvmPropertySignature0.getGetter();
                        Intrinsics.checkNotNullExpressionValue(jvmProtoBuf$JvmMethodSignature1, "signature.getter");
                        return MemberSignature.Companion.fromMethod(nameResolver0, jvmProtoBuf$JvmMethodSignature1);
                    }
                    break;
                }
                case 2: {
                    if(jvmProtoBuf$JvmPropertySignature0.hasSetter()) {
                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = jvmProtoBuf$JvmPropertySignature0.getSetter();
                        Intrinsics.checkNotNullExpressionValue(jvmProtoBuf$JvmMethodSignature0, "signature.setter");
                        return MemberSignature.Companion.fromMethod(nameResolver0, jvmProtoBuf$JvmMethodSignature0);
                    }
                    return null;
                }
                case 3: {
                    return AbstractBinaryClassAnnotationLoaderKt.getPropertySignature(((Property)messageLite0), nameResolver0, typeTable0, true, true, z);
                }
                default: {
                    return null;
                }
            }
        }
        return null;
    }

    public static MemberSignature getCallableSignature$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader0, MessageLite messageLite0, NameResolver nameResolver0, TypeTable typeTable0, AnnotatedCallableKind annotatedCallableKind0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallableSignature");
        }
        return (v & 16) == 0 ? abstractBinaryClassAnnotationLoader0.getCallableSignature(messageLite0, nameResolver0, typeTable0, annotatedCallableKind0, z) : abstractBinaryClassAnnotationLoader0.getCallableSignature(messageLite0, nameResolver0, typeTable0, annotatedCallableKind0, false);
    }

    public abstract JvmMetadataVersion getJvmMetadataVersion();

    protected final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer protoContainer0, boolean z, boolean z1, Boolean boolean0, boolean z2) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        if(z) {
            if(boolean0 == null) {
                throw new IllegalStateException(("isConst should not be null for property (container=" + protoContainer0 + ')').toString());
            }
            if(protoContainer0 instanceof Class && ((Class)protoContainer0).getKind() == Kind.INTERFACE) {
                ClassId classId0 = ((Class)protoContainer0).getClassId().createNestedClassId(Name.identifier("DefaultImpls"));
                Intrinsics.checkNotNullExpressionValue(classId0, "container.classId.create…EFAULT_IMPLS_CLASS_NAME))");
                JvmMetadataVersion jvmMetadataVersion0 = this.getJvmMetadataVersion();
                return KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId0, jvmMetadataVersion0);
            }
            if(boolean0.booleanValue() && protoContainer0 instanceof Package) {
                SourceElement sourceElement0 = protoContainer0.getSource();
                JvmPackagePartSource jvmPackagePartSource0 = sourceElement0 instanceof JvmPackagePartSource ? ((JvmPackagePartSource)sourceElement0) : null;
                JvmClassName jvmClassName0 = jvmPackagePartSource0 == null ? null : jvmPackagePartSource0.getFacadeClassName();
                if(jvmClassName0 != null) {
                    String s = jvmClassName0.getInternalName();
                    Intrinsics.checkNotNullExpressionValue(s, "facadeClassName.internalName");
                    ClassId classId1 = ClassId.topLevel(new FqName(StringsKt.replace$default(s, '/', '.', false, 4, null)));
                    Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(FqName(facadeCl…lName.replace(\'/\', \'.\')))");
                    JvmMetadataVersion jvmMetadataVersion1 = this.getJvmMetadataVersion();
                    return KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId1, jvmMetadataVersion1);
                }
            }
        }
        if(z1 && protoContainer0 instanceof Class && ((Class)protoContainer0).getKind() == Kind.COMPANION_OBJECT) {
            Class protoContainer$Class0 = ((Class)protoContainer0).getOuterClass();
            if(protoContainer$Class0 != null && (protoContainer$Class0.getKind() == Kind.CLASS || protoContainer$Class0.getKind() == Kind.ENUM_CLASS || z2 && (protoContainer$Class0.getKind() == Kind.INTERFACE || protoContainer$Class0.getKind() == Kind.ANNOTATION_CLASS))) {
                return this.toBinaryClass(protoContainer$Class0);
            }
        }
        if(protoContainer0 instanceof Package && protoContainer0.getSource() instanceof JvmPackagePartSource) {
            SourceElement sourceElement1 = protoContainer0.getSource();
            Intrinsics.checkNotNull(sourceElement1, "null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
            KotlinJvmBinaryClass kotlinJvmBinaryClass0 = ((JvmPackagePartSource)sourceElement1).getKnownJvmBinaryClass();
            if(kotlinJvmBinaryClass0 == null) {
                ClassId classId2 = ((JvmPackagePartSource)sourceElement1).getClassId();
                JvmMetadataVersion jvmMetadataVersion2 = this.getJvmMetadataVersion();
                return KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId2, jvmMetadataVersion2);
            }
            return kotlinJvmBinaryClass0;
        }
        return null;
    }

    protected final boolean isImplicitRepeatableContainer(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        if(classId0.getOuterClassId() != null && Intrinsics.areEqual(classId0.getShortClassName().asString(), "Container")) {
            JvmMetadataVersion jvmMetadataVersion0 = this.getJvmMetadataVersion();
            KotlinJvmBinaryClass kotlinJvmBinaryClass0 = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId0, jvmMetadataVersion0);
            return kotlinJvmBinaryClass0 != null && SpecialJvmAnnotations.INSTANCE.isAnnotatedWithContainerMetaAnnotation(kotlinJvmBinaryClass0);
        }
        return false;
    }

    protected abstract AnnotationArgumentVisitor loadAnnotation(ClassId arg1, SourceElement arg2, List arg3);

    protected final AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId classId0, SourceElement sourceElement0, List list0) {
        Intrinsics.checkNotNullParameter(classId0, "annotationClassId");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        Intrinsics.checkNotNullParameter(list0, "result");
        return SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS().contains(classId0) ? null : this.loadAnnotation(classId0, sourceElement0, list0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadCallableAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        if(annotatedCallableKind0 == AnnotatedCallableKind.PROPERTY) {
            return this.loadPropertyAnnotations(protoContainer0, ((Property)messageLite0), PropertyRelatedElement.PROPERTY);
        }
        MemberSignature memberSignature0 = AbstractBinaryClassAnnotationLoader.getCallableSignature$default(this, messageLite0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), annotatedCallableKind0, false, 16, null);
        return memberSignature0 == null ? CollectionsKt.emptyList() : AbstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations$default(this, protoContainer0, memberSignature0, false, false, null, false, 60, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadClassAnnotations(Class protoContainer$Class0) {
        Intrinsics.checkNotNullParameter(protoContainer$Class0, "container");
        KotlinJvmBinaryClass kotlinJvmBinaryClass0 = this.toBinaryClass(protoContainer$Class0);
        if(kotlinJvmBinaryClass0 == null) {
            throw new IllegalStateException(("Class for loading annotations is not found: " + protoContainer$Class0.debugFqName()).toString());
        }
        ArrayList arrayList0 = new ArrayList(1);
        kotlinJvmBinaryClass0.loadClassAnnotations(new AnnotationVisitor() {
            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
            public AnnotationArgumentVisitor visitAnnotation(ClassId classId0, SourceElement sourceElement0) {
                Intrinsics.checkNotNullParameter(classId0, "classId");
                Intrinsics.checkNotNullParameter(sourceElement0, "source");
                return arrayList0.loadAnnotationIfNotSpecial(classId0, sourceElement0, this.$result);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
            public void visitEnd() {
            }
        }, this.getCachedFileContent(kotlinJvmBinaryClass0));
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadEnumEntryAnnotations(ProtoContainer protoContainer0, EnumEntry protoBuf$EnumEntry0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$EnumEntry0, "proto");
        String s = protoContainer0.getNameResolver().getString(protoBuf$EnumEntry0.getName());
        String s1 = ((Class)protoContainer0).getClassId().asString();
        Intrinsics.checkNotNullExpressionValue(s1, "container as ProtoContai…Class).classId.asString()");
        String s2 = ClassMapperLite.mapClass(s1);
        return AbstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations$default(this, protoContainer0, MemberSignature.Companion.fromFieldNameAndDesc(s, s2), false, false, null, false, 60, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadExtensionReceiverParameterAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        MemberSignature memberSignature0 = AbstractBinaryClassAnnotationLoader.getCallableSignature$default(this, messageLite0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), annotatedCallableKind0, false, 16, null);
        return memberSignature0 == null ? CollectionsKt.emptyList() : AbstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations$default(this, protoContainer0, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(memberSignature0, 0), false, false, null, false, 60, null);
    }

    private final List loadPropertyAnnotations(ProtoContainer protoContainer0, Property protoBuf$Property0, PropertyRelatedElement abstractBinaryClassAnnotationLoader$PropertyRelatedElement0) {
        Boolean boolean0 = Flags.IS_CONST.get(protoBuf$Property0.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_CONST.get(proto.flags)");
        boolean0.booleanValue();
        boolean z = JvmProtoBufUtil.isMovedFromInterfaceCompanion(protoBuf$Property0);
        if(abstractBinaryClassAnnotationLoader$PropertyRelatedElement0 == PropertyRelatedElement.PROPERTY) {
            MemberSignature memberSignature0 = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(protoBuf$Property0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), false, true, false, 40, null);
            return memberSignature0 == null ? CollectionsKt.emptyList() : AbstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations$default(this, protoContainer0, memberSignature0, true, false, boolean0, z, 8, null);
        }
        MemberSignature memberSignature1 = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(protoBuf$Property0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), true, false, false, 0x30, null);
        if(memberSignature1 == null) {
            return CollectionsKt.emptyList();
        }
        boolean z1 = false;
        boolean z2 = StringsKt.contains$default(memberSignature1.getSignature(), "$delegate", false, 2, null);
        if(abstractBinaryClassAnnotationLoader$PropertyRelatedElement0 == PropertyRelatedElement.DELEGATE_FIELD) {
            z1 = true;
        }
        return z2 == z1 ? this.findClassAndLoadMemberAnnotations(protoContainer0, memberSignature1, true, true, boolean0, z) : CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadPropertyBackingFieldAnnotations(ProtoContainer protoContainer0, Property protoBuf$Property0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        return this.loadPropertyAnnotations(protoContainer0, protoBuf$Property0, PropertyRelatedElement.BACKING_FIELD);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadPropertyDelegateFieldAnnotations(ProtoContainer protoContainer0, Property protoBuf$Property0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        return this.loadPropertyAnnotations(protoContainer0, protoBuf$Property0, PropertyRelatedElement.DELEGATE_FIELD);
    }

    protected abstract Object loadTypeAnnotation(Annotation arg1, NameResolver arg2);

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadTypeAnnotations(Type protoBuf$Type0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Object object0 = protoBuf$Type0.getExtension(JvmProtoBuf.typeAnnotation);
        Intrinsics.checkNotNullExpressionValue(object0, "proto.getExtension(JvmProtoBuf.typeAnnotation)");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((Iterable)object0), 10));
        for(Object object1: ((Iterable)object0)) {
            Intrinsics.checkNotNullExpressionValue(((Annotation)object1), "it");
            arrayList0.add(this.loadTypeAnnotation(((Annotation)object1), nameResolver0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadTypeParameterAnnotations(TypeParameter protoBuf$TypeParameter0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Object object0 = protoBuf$TypeParameter0.getExtension(JvmProtoBuf.typeParameterAnnotation);
        Intrinsics.checkNotNullExpressionValue(object0, "proto.getExtension(JvmPr….typeParameterAnnotation)");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((Iterable)object0), 10));
        for(Object object1: ((Iterable)object0)) {
            Intrinsics.checkNotNullExpressionValue(((Annotation)object1), "it");
            arrayList0.add(this.loadTypeAnnotation(((Annotation)object1), nameResolver0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadValueParameterAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0, int v, ValueParameter protoBuf$ValueParameter0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "callableProto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter0, "proto");
        MemberSignature memberSignature0 = AbstractBinaryClassAnnotationLoader.getCallableSignature$default(this, messageLite0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), annotatedCallableKind0, false, 16, null);
        if(memberSignature0 != null) {
            int v1 = this.computeJvmParameterIndexShift(protoContainer0, messageLite0);
            return AbstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations$default(this, protoContainer0, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(memberSignature0, v + v1), false, false, null, false, 60, null);
        }
        return CollectionsKt.emptyList();
    }

    private final KotlinJvmBinaryClass toBinaryClass(Class protoContainer$Class0) {
        SourceElement sourceElement0 = protoContainer$Class0.getSource();
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement0 = sourceElement0 instanceof KotlinJvmBinarySourceElement ? ((KotlinJvmBinarySourceElement)sourceElement0) : null;
        return kotlinJvmBinarySourceElement0 == null ? null : kotlinJvmBinarySourceElement0.getBinaryClass();
    }
}

