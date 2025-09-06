package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;

public final class BinaryClassAnnotationAndConstantLoaderImpl extends AbstractBinaryClassAnnotationAndConstantLoader {
    abstract class AbstractAnnotationArgumentVisitor implements AnnotationArgumentVisitor {
        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visit(Name name0, Object object0) {
            this.visitConstantValue(name0, BinaryClassAnnotationAndConstantLoaderImpl.this.createConstant(name0, object0));
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            ArrayList arrayList0 = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
            AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = BinaryClassAnnotationAndConstantLoaderImpl.this.loadAnnotation(classId0, SourceElement.NO_SOURCE, arrayList0);
            Intrinsics.checkNotNull(kotlinJvmBinaryClass$AnnotationArgumentVisitor0);
            return new AnnotationArgumentVisitor() {
                private final AnnotationArgumentVisitor $$delegate_0;

                {
                    AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = this;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                    AbstractAnnotationArgumentVisitor binaryClassAnnotationAndConstantLoaderImpl$AbstractAnnotationArgumentVisitor0 = name0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                    Name name0 = arrayList0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                    this.$$delegate_0 = kotlinJvmBinaryClass$AnnotationArgumentVisitor0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public void visit(Name name0, Object object0) {
                    this.$$delegate_0.visit(name0, object0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
                    Intrinsics.checkNotNullParameter(classId0, "classId");
                    return this.$$delegate_0.visitAnnotation(name0, classId0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public AnnotationArrayArgumentVisitor visitArray(Name name0) {
                    return this.$$delegate_0.visitArray(name0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
                    Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
                    this.$$delegate_0.visitClassLiteral(name0, classLiteralValue0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public void visitEnd() {
                    this.visitEnd();
                    AnnotationValue annotationValue0 = new AnnotationValue(((AnnotationDescriptor)CollectionsKt.single(this.$list)));
                    name0.visitConstantValue(arrayList0, annotationValue0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                public void visitEnum(Name name0, ClassId classId0, Name name1) {
                    Intrinsics.checkNotNullParameter(classId0, "enumClassId");
                    Intrinsics.checkNotNullParameter(name1, "enumEntryName");
                    this.$$delegate_0.visitEnum(name0, classId0, name1);
                }
            };
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArrayArgumentVisitor visitArray(Name name0) {
            return new AnnotationArrayArgumentVisitor() {
                private final ArrayList elements;

                {
                    BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl0 = name0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                    Name name0 = this;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                    this.elements = new ArrayList();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
                public void visit(Object object0) {
                    ConstantValue constantValue0 = name0.createConstant(this, object0);
                    this.elements.add(constantValue0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
                public AnnotationArgumentVisitor visitAnnotation(ClassId classId0) {
                    Intrinsics.checkNotNullParameter(classId0, "classId");
                    ArrayList arrayList0 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
                    AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = name0.loadAnnotation(classId0, SourceElement.NO_SOURCE, arrayList0);
                    Intrinsics.checkNotNull(kotlinJvmBinaryClass$AnnotationArgumentVisitor0);
                    return new AnnotationArgumentVisitor() {
                        private final AnnotationArgumentVisitor $$delegate_0;

                        {
                            AnnotationArgumentVisitor kotlinJvmBinaryClass$AnnotationArgumentVisitor0 = this;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                            kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl.AbstractAnnotationArgumentVisitor.visitArray.1 binaryClassAnnotationAndConstantLoaderImpl$AbstractAnnotationArgumentVisitor$visitArray$10 = arrayList0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                            this.$$delegate_0 = kotlinJvmBinaryClass$AnnotationArgumentVisitor0;
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public void visit(Name name0, Object object0) {
                            this.$$delegate_0.visit(name0, object0);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
                            Intrinsics.checkNotNullParameter(classId0, "classId");
                            return this.$$delegate_0.visitAnnotation(name0, classId0);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public AnnotationArrayArgumentVisitor visitArray(Name name0) {
                            return this.$$delegate_0.visitArray(name0);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
                            Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
                            this.$$delegate_0.visitClassLiteral(name0, classLiteralValue0);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public void visitEnd() {
                            this.visitEnd();
                            arrayList0.elements.add(new AnnotationValue(((AnnotationDescriptor)CollectionsKt.single(this.$list))));
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
                        public void visitEnum(Name name0, ClassId classId0, Name name1) {
                            Intrinsics.checkNotNullParameter(classId0, "enumClassId");
                            Intrinsics.checkNotNullParameter(name1, "enumEntryName");
                            this.$$delegate_0.visitEnum(name0, classId0, name1);
                        }
                    };
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
                public void visitClassLiteral(ClassLiteralValue classLiteralValue0) {
                    Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
                    KClassValue kClassValue0 = new KClassValue(classLiteralValue0);
                    this.elements.add(kClassValue0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
                public void visitEnd() {
                    AbstractAnnotationArgumentVisitor.this.visitArrayValue(this, this.elements);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
                public void visitEnum(ClassId classId0, Name name0) {
                    Intrinsics.checkNotNullParameter(classId0, "enumClassId");
                    Intrinsics.checkNotNullParameter(name0, "enumEntryName");
                    EnumValue enumValue0 = new EnumValue(classId0, name0);
                    this.elements.add(enumValue0);
                }
            };
        }

        public abstract void visitArrayValue(Name arg1, ArrayList arg2);

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
            Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
            this.visitConstantValue(name0, new KClassValue(classLiteralValue0));
        }

        public abstract void visitConstantValue(Name arg1, ConstantValue arg2);

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnum(Name name0, ClassId classId0, Name name1) {
            Intrinsics.checkNotNullParameter(classId0, "enumClassId");
            Intrinsics.checkNotNullParameter(name1, "enumEntryName");
            this.visitConstantValue(name0, new EnumValue(classId0, name1));
        }
    }

    private final AnnotationDeserializer annotationDeserializer;
    private JvmMetadataVersion jvmMetadataVersion;
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public BinaryClassAnnotationAndConstantLoaderImpl(ModuleDescriptor moduleDescriptor0, NotFoundClasses notFoundClasses0, StorageManager storageManager0, KotlinClassFinder kotlinClassFinder0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "kotlinClassFinder");
        super(storageManager0, kotlinClassFinder0);
        this.module = moduleDescriptor0;
        this.notFoundClasses = notFoundClasses0;
        this.annotationDeserializer = new AnnotationDeserializer(moduleDescriptor0, notFoundClasses0);
        this.jvmMetadataVersion = JvmMetadataVersion.INSTANCE;
    }

    private final ConstantValue createConstant(Name name0, Object object0) {
        ConstantValue constantValue0 = ConstantValueFactory.INSTANCE.createConstantValue(object0, this.module);
        return constantValue0 == null ? ErrorValue.Companion.create("Unsupported annotation argument: " + name0) : constantValue0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader
    public JvmMetadataVersion getJvmMetadataVersion() {
        return this.jvmMetadataVersion;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader
    protected AnnotationArgumentVisitor loadAnnotation(ClassId classId0, SourceElement sourceElement0, List list0) {
        Intrinsics.checkNotNullParameter(classId0, "annotationClassId");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        Intrinsics.checkNotNullParameter(list0, "result");
        return new AbstractAnnotationArgumentVisitor() {
            private final HashMap arguments;

            {
                BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl0 = this.resolveClass(classId0);  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                ClassDescriptor classDescriptor0 = classId0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                ClassId classId0 = list0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                List list0 = sourceElement0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.arguments = new HashMap();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl$AbstractAnnotationArgumentVisitor
            public void visitArrayValue(Name name0, ArrayList arrayList0) {
                Intrinsics.checkNotNullParameter(arrayList0, "elements");
                if(name0 != null) {
                    ValueParameterDescriptor valueParameterDescriptor0 = DescriptorResolverUtils.getAnnotationParameterByName(name0, classId0);
                    if(valueParameterDescriptor0 != null) {
                        List list0 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
                        KotlinType kotlinType0 = valueParameterDescriptor0.getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType0, "parameter.type");
                        ArrayValue arrayValue0 = ConstantValueFactory.INSTANCE.createArrayValue(list0, kotlinType0);
                        this.arguments.put(name0, arrayValue0);
                        return;
                    }
                    if(this.resolveClass(classId0).isImplicitRepeatableContainer(list0) && Intrinsics.areEqual(name0.asString(), "value")) {
                        Collection collection0 = new ArrayList();
                        for(Object object0: arrayList0) {
                            if(object0 instanceof AnnotationValue) {
                                collection0.add(object0);
                            }
                        }
                        Collection collection1 = sourceElement0;
                        for(Object object1: ((List)collection0)) {
                            collection1.add(((AnnotationDescriptor)((AnnotationValue)object1).getValue()));
                        }
                    }
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl$AbstractAnnotationArgumentVisitor
            public void visitConstantValue(Name name0, ConstantValue constantValue0) {
                Intrinsics.checkNotNullParameter(constantValue0, "value");
                if(name0 != null) {
                    this.arguments.put(name0, constantValue0);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
            public void visitEnd() {
                if(this.resolveClass(classId0).isRepeatableWithImplicitContainer(list0, this.arguments) || this.resolveClass(classId0).isImplicitRepeatableContainer(list0)) {
                    return;
                }
                AnnotationDescriptorImpl annotationDescriptorImpl0 = new AnnotationDescriptorImpl(classId0.getDefaultType(), this.arguments, this.$source);
                sourceElement0.add(annotationDescriptorImpl0);
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader
    public Object loadConstant(String s, Object object0) {
        return this.loadConstant(s, object0);
    }

    protected ConstantValue loadConstant(String s, Object object0) {
        Intrinsics.checkNotNullParameter(s, "desc");
        Intrinsics.checkNotNullParameter(object0, "initializer");
        boolean z = false;
        if(StringsKt.contains$default("ZBCS", s, false, 2, null)) {
            int v = (int)(((Integer)object0));
            switch(s) {
                case "B": {
                    return ConstantValueFactory.INSTANCE.createConstantValue(((byte)v), this.module);
                }
                case "C": {
                    return ConstantValueFactory.INSTANCE.createConstantValue(Character.valueOf(((char)v)), this.module);
                }
                case "S": {
                    return ConstantValueFactory.INSTANCE.createConstantValue(((short)v), this.module);
                }
                case "Z": {
                    if(v != 0) {
                        z = true;
                    }
                    return ConstantValueFactory.INSTANCE.createConstantValue(Boolean.valueOf(z), this.module);
                }
                default: {
                    throw new AssertionError(s);
                }
            }
        }
        return ConstantValueFactory.INSTANCE.createConstantValue(object0, this.module);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader
    public Object loadTypeAnnotation(Annotation protoBuf$Annotation0, NameResolver nameResolver0) {
        return this.loadTypeAnnotation(protoBuf$Annotation0, nameResolver0);
    }

    protected AnnotationDescriptor loadTypeAnnotation(Annotation protoBuf$Annotation0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$Annotation0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        return this.annotationDeserializer.deserializeAnnotation(protoBuf$Annotation0, nameResolver0);
    }

    private final ClassDescriptor resolveClass(ClassId classId0) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId0, this.notFoundClasses);
    }

    public void setJvmMetadataVersion(JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "<set-?>");
        this.jvmMetadataVersion = jvmMetadataVersion0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader
    public Object transformToUnsignedConstant(Object object0) {
        return this.transformToUnsignedConstant(((ConstantValue)object0));
    }

    protected ConstantValue transformToUnsignedConstant(ConstantValue constantValue0) {
        Intrinsics.checkNotNullParameter(constantValue0, "constant");
        if(constantValue0 instanceof ByteValue) {
            return new UByteValue(((Number)((ByteValue)constantValue0).getValue()).byteValue());
        }
        if(constantValue0 instanceof ShortValue) {
            return new UShortValue(((Number)((ShortValue)constantValue0).getValue()).shortValue());
        }
        if(constantValue0 instanceof IntValue) {
            return new UIntValue(((Number)((IntValue)constantValue0).getValue()).intValue());
        }
        return constantValue0 instanceof LongValue ? new ULongValue(((Number)((LongValue)constantValue0).getValue()).longValue()) : constantValue0;
    }
}

