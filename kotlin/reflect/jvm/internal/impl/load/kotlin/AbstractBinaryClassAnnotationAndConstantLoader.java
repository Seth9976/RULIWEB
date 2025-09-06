package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value.NormalClass;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class AbstractBinaryClassAnnotationAndConstantLoader extends AbstractBinaryClassAnnotationLoader implements AnnotationAndConstantLoader {
    public static final class AnnotationsContainerWithConstants extends AnnotationsContainer {
        private final Map annotationParametersDefaultValues;
        private final Map memberAnnotations;
        private final Map propertyConstants;

        public AnnotationsContainerWithConstants(Map map0, Map map1, Map map2) {
            Intrinsics.checkNotNullParameter(map0, "memberAnnotations");
            Intrinsics.checkNotNullParameter(map1, "propertyConstants");
            Intrinsics.checkNotNullParameter(map2, "annotationParametersDefaultValues");
            super();
            this.memberAnnotations = map0;
            this.propertyConstants = map1;
            this.annotationParametersDefaultValues = map2;
        }

        public final Map getAnnotationParametersDefaultValues() {
            return this.annotationParametersDefaultValues;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader$AnnotationsContainer
        public Map getMemberAnnotations() {
            return this.memberAnnotations;
        }

        public final Map getPropertyConstants() {
            return this.propertyConstants;
        }
    }

    private final MemoizedFunctionToNotNull storage;

    public AbstractBinaryClassAnnotationAndConstantLoader(StorageManager storageManager0, KotlinClassFinder kotlinClassFinder0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "kotlinClassFinder");
        super(kotlinClassFinder0);
        this.storage = storageManager0.createMemoizedFunction(new Function1() {
            {
                AbstractBinaryClassAnnotationAndConstantLoader.this = abstractBinaryClassAnnotationAndConstantLoader0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinJvmBinaryClass)object0));
            }

            public final AnnotationsContainerWithConstants invoke(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
                Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
                return AbstractBinaryClassAnnotationAndConstantLoader.access$loadAnnotationsAndInitializers(AbstractBinaryClassAnnotationAndConstantLoader.this, kotlinJvmBinaryClass0);
            }
        });
    }

    public static final AnnotationsContainerWithConstants access$loadAnnotationsAndInitializers(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader0, KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        return abstractBinaryClassAnnotationAndConstantLoader0.loadAnnotationsAndInitializers(kotlinJvmBinaryClass0);
    }

    protected AnnotationsContainerWithConstants getAnnotationsContainer(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "binaryClass");
        return (AnnotationsContainerWithConstants)this.storage.invoke(kotlinJvmBinaryClass0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader
    public AnnotationsContainer getAnnotationsContainer(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        return this.getAnnotationsContainer(kotlinJvmBinaryClass0);
    }

    protected final boolean isRepeatableWithImplicitContainer(ClassId classId0, Map map0) {
        Intrinsics.checkNotNullParameter(classId0, "annotationClassId");
        Intrinsics.checkNotNullParameter(map0, "arguments");
        if(!Intrinsics.areEqual(classId0, SpecialJvmAnnotations.INSTANCE.getJAVA_LANG_ANNOTATION_REPEATABLE())) {
            return false;
        }
        Object object0 = map0.get(Name.identifier("value"));
        NormalClass kClassValue$Value$NormalClass0 = null;
        KClassValue kClassValue0 = object0 instanceof KClassValue ? ((KClassValue)object0) : null;
        if(kClassValue0 == null) {
            return false;
        }
        Object object1 = kClassValue0.getValue();
        if(object1 instanceof NormalClass) {
            kClassValue$Value$NormalClass0 = (NormalClass)object1;
        }
        return kClassValue$Value$NormalClass0 == null ? false : this.isImplicitRepeatableContainer(kClassValue$Value$NormalClass0.getClassId());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadAnnotationDefaultValue(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(kotlinType0, "expectedType");
        return this.loadConstantFromProperty(protoContainer0, protoBuf$Property0, AnnotatedCallableKind.PROPERTY_GETTER, kotlinType0, kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1 extends Lambda implements Function2 {
            public static final kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1();
            }

            kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((AnnotationsContainerWithConstants)object0), ((MemberSignature)object1));
            }

            public final Object invoke(AnnotationsContainerWithConstants abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0, MemberSignature memberSignature0) {
                Intrinsics.checkNotNullParameter(abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0, "$this$loadConstantFromProperty");
                Intrinsics.checkNotNullParameter(memberSignature0, "it");
                return abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0.getAnnotationParametersDefaultValues().get(memberSignature0);
            }
        }

    }

    private final AnnotationsContainerWithConstants loadAnnotationsAndInitializers(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        HashMap hashMap0 = new HashMap();
        HashMap hashMap1 = new HashMap();
        HashMap hashMap2 = new HashMap();
        kotlinJvmBinaryClass0.visitMembers(new MemberVisitor() {
            public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements MethodAnnotationVisitor {
                public AnnotationVisitorForMethod(MemberSignature memberSignature0) {
                    Intrinsics.checkNotNullParameter(memberSignature0, "signature");
                    kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1.this = abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$10;
                    super(memberSignature0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$MethodAnnotationVisitor
                public AnnotationArgumentVisitor visitParameterAnnotation(int v, ClassId classId0, SourceElement sourceElement0) {
                    Intrinsics.checkNotNullParameter(classId0, "classId");
                    Intrinsics.checkNotNullParameter(sourceElement0, "source");
                    MemberSignature memberSignature0 = this.getSignature();
                    MemberSignature memberSignature1 = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(memberSignature0, v);
                    List list0 = (List)kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1.this.$memberAnnotations.get(memberSignature1);
                    if(list0 == null) {
                        list0 = new ArrayList();
                        kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1.this.$memberAnnotations.put(memberSignature1, list0);
                    }
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId0, sourceElement0, list0);
                }
            }

            public class MemberAnnotationVisitor implements AnnotationVisitor {
                private final ArrayList result;
                private final MemberSignature signature;

                public MemberAnnotationVisitor(MemberSignature memberSignature0) {
                    Intrinsics.checkNotNullParameter(memberSignature0, "signature");
                    kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1.this = abstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$10;
                    super();
                    this.signature = memberSignature0;
                    this.result = new ArrayList();
                }

                protected final MemberSignature getSignature() {
                    return this.signature;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
                public AnnotationArgumentVisitor visitAnnotation(ClassId classId0, SourceElement sourceElement0) {
                    Intrinsics.checkNotNullParameter(classId0, "classId");
                    Intrinsics.checkNotNullParameter(sourceElement0, "source");
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId0, sourceElement0, this.result);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
                public void visitEnd() {
                    if(!this.result.isEmpty()) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1.this.$memberAnnotations.put(this.signature, this.result);
                    }
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$MemberVisitor
            public AnnotationVisitor visitField(Name name0, String s, Object object0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                Intrinsics.checkNotNullParameter(s, "desc");
                String s1 = name0.asString();
                Intrinsics.checkNotNullExpressionValue(s1, "name.asString()");
                MemberSignature memberSignature0 = MemberSignature.Companion.fromFieldNameAndDesc(s1, s);
                if(object0 != null) {
                    Object object1 = hashMap0.loadConstant(s, object0);
                    if(object1 != null) {
                        this.$propertyConstants.put(memberSignature0, object1);
                    }
                }
                return new MemberAnnotationVisitor(this, memberSignature0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$MemberVisitor
            public MethodAnnotationVisitor visitMethod(Name name0, String s) {
                Intrinsics.checkNotNullParameter(name0, "name");
                Intrinsics.checkNotNullParameter(s, "desc");
                String s1 = name0.asString();
                Intrinsics.checkNotNullExpressionValue(s1, "name.asString()");
                return new AnnotationVisitorForMethod(this, MemberSignature.Companion.fromMethodNameAndDesc(s1, s));
            }
        }, this.getCachedFileContent(kotlinJvmBinaryClass0));
        return new AnnotationsContainerWithConstants(hashMap0, hashMap1, hashMap2);
    }

    protected abstract Object loadConstant(String arg1, Object arg2);

    private final Object loadConstantFromProperty(ProtoContainer protoContainer0, Property protoBuf$Property0, AnnotatedCallableKind annotatedCallableKind0, KotlinType kotlinType0, Function2 function20) {
        KotlinJvmBinaryClass kotlinJvmBinaryClass0 = this.findClassWithAnnotationsAndInitializers(protoContainer0, this.getSpecialCaseContainerClass(protoContainer0, true, true, Flags.IS_CONST.get(protoBuf$Property0.getFlags()), JvmProtoBufUtil.isMovedFromInterfaceCompanion(protoBuf$Property0)));
        if(kotlinJvmBinaryClass0 == null) {
            return null;
        }
        MemberSignature memberSignature0 = this.getCallableSignature(protoBuf$Property0, protoContainer0.getNameResolver(), protoContainer0.getTypeTable(), annotatedCallableKind0, kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion().isAtLeast(DeserializedDescriptorResolver.Companion.getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm()));
        if(memberSignature0 == null) {
            return null;
        }
        Object object0 = function20.invoke(this.storage.invoke(kotlinJvmBinaryClass0), memberSignature0);
        if(object0 == null) {
            return null;
        }
        return UnsignedTypes.isUnsignedType(kotlinType0) ? this.transformToUnsignedConstant(object0) : object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadPropertyConstant(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(kotlinType0, "expectedType");
        return this.loadConstantFromProperty(protoContainer0, protoBuf$Property0, AnnotatedCallableKind.PROPERTY, kotlinType0, kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1 extends Lambda implements Function2 {
            public static final kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1();
            }

            kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((AnnotationsContainerWithConstants)object0), ((MemberSignature)object1));
            }

            public final Object invoke(AnnotationsContainerWithConstants abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0, MemberSignature memberSignature0) {
                Intrinsics.checkNotNullParameter(abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0, "$this$loadConstantFromProperty");
                Intrinsics.checkNotNullParameter(memberSignature0, "it");
                return abstractBinaryClassAnnotationAndConstantLoader$AnnotationsContainerWithConstants0.getPropertyConstants().get(memberSignature0);
            }
        }

    }

    protected abstract Object transformToUnsignedConstant(Object arg1);
}

