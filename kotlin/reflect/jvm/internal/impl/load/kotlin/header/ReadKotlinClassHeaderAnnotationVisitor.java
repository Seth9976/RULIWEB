package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.BitEncoding;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;

public class ReadKotlinClassHeaderAnnotationVisitor implements AnnotationVisitor {
    static abstract class CollectStringArrayAnnotationVisitor implements AnnotationArrayArgumentVisitor {
        private final List strings;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            switch(v) {
                case 1: {
                    arr_object[0] = "enumEntryName";
                    break;
                }
                case 2: {
                    arr_object[0] = "classLiteralValue";
                    break;
                }
                case 3: {
                    arr_object[0] = "classId";
                    break;
                }
                default: {
                    arr_object[0] = "enumClassId";
                }
            }
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor";
            switch(v) {
                case 2: {
                    arr_object[2] = "visitClassLiteral";
                    break;
                }
                case 3: {
                    arr_object[2] = "visitAnnotation";
                    break;
                }
                default: {
                    arr_object[2] = "visitEnum";
                }
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        public CollectStringArrayAnnotationVisitor() {
            this.strings = new ArrayList();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
        public void visit(Object object0) {
            if(object0 instanceof String) {
                this.strings.add(((String)object0));
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
        public AnnotationArgumentVisitor visitAnnotation(ClassId classId0) {
            if(classId0 == null) {
                CollectStringArrayAnnotationVisitor.$$$reportNull$$$0(3);
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
        public void visitClassLiteral(ClassLiteralValue classLiteralValue0) {
            if(classLiteralValue0 == null) {
                CollectStringArrayAnnotationVisitor.$$$reportNull$$$0(2);
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
        public void visitEnd() {
            this.visitEnd(((String[])this.strings.toArray(new String[0])));
        }

        protected abstract void visitEnd(String[] arg1);

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor
        public void visitEnum(ClassId classId0, Name name0) {
            if(classId0 == null) {
                CollectStringArrayAnnotationVisitor.$$$reportNull$$$0(0);
            }
            if(name0 == null) {
                CollectStringArrayAnnotationVisitor.$$$reportNull$$$0(1);
            }
        }
    }

    class KotlinMetadataArgumentVisitor implements AnnotationArgumentVisitor {
        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            switch(v) {
                case 1: {
                    arr_object[0] = "enumClassId";
                    break;
                }
                case 2: {
                    arr_object[0] = "enumEntryName";
                    break;
                }
                case 3: {
                    arr_object[0] = "classId";
                    break;
                }
                default: {
                    arr_object[0] = "classLiteralValue";
                }
            }
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor";
            switch(v) {
                case 1: 
                case 2: {
                    arr_object[2] = "visitEnum";
                    break;
                }
                case 3: {
                    arr_object[2] = "visitAnnotation";
                    break;
                }
                default: {
                    arr_object[2] = "visitClassLiteral";
                }
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        private KotlinMetadataArgumentVisitor() {
        }

        KotlinMetadataArgumentVisitor(kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.1 readKotlinClassHeaderAnnotationVisitor$10) {
        }

        private AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'result\' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$1.visitEnd must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] arr_s) {
                    if(arr_s == null) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.1.$$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = arr_s;
                }
            };
        }

        private AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'result\' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$2.visitEnd must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] arr_s) {
                    if(arr_s == null) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.2.$$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = arr_s;
                }
            };
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visit(Name name0, Object object0) {
            if(name0 != null) {
                String s = name0.asString();
                if("k".equals(s)) {
                    if(object0 instanceof Integer) {
                        Kind kotlinClassHeader$Kind0 = Kind.getById(((int)(((Integer)object0))));
                        ReadKotlinClassHeaderAnnotationVisitor.this.headerKind = kotlinClassHeader$Kind0;
                    }
                }
                else if("mv".equals(s)) {
                    if(object0 instanceof int[]) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = (int[])object0;
                    }
                }
                else if("xs".equals(s)) {
                    if(object0 instanceof String && !((String)object0).isEmpty()) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.extraString = (String)object0;
                    }
                }
                else if("xi".equals(s)) {
                    if(object0 instanceof Integer) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.extraInt = (int)(((Integer)object0));
                    }
                }
                else if("pn".equals(s) && object0 instanceof String && !((String)object0).isEmpty()) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.packageName = (String)object0;
                }
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
            if(classId0 == null) {
                KotlinMetadataArgumentVisitor.$$$reportNull$$$0(3);
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArrayArgumentVisitor visitArray(Name name0) {
            String s = name0 == null ? null : name0.asString();
            if("d1".equals(s)) {
                return this.dataArrayVisitor();
            }
            return "d2".equals(s) ? this.stringsArrayVisitor() : null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
            if(classLiteralValue0 == null) {
                KotlinMetadataArgumentVisitor.$$$reportNull$$$0(0);
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnd() {
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnum(Name name0, ClassId classId0, Name name1) {
            if(classId0 == null) {
                KotlinMetadataArgumentVisitor.$$$reportNull$$$0(1);
            }
            if(name1 == null) {
                KotlinMetadataArgumentVisitor.$$$reportNull$$$0(2);
            }
        }
    }

    class KotlinSerializedIrArgumentVisitor implements AnnotationArgumentVisitor {
        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            switch(v) {
                case 1: {
                    arr_object[0] = "enumClassId";
                    break;
                }
                case 2: {
                    arr_object[0] = "enumEntryName";
                    break;
                }
                case 3: {
                    arr_object[0] = "classId";
                    break;
                }
                default: {
                    arr_object[0] = "classLiteralValue";
                }
            }
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinSerializedIrArgumentVisitor";
            switch(v) {
                case 1: 
                case 2: {
                    arr_object[2] = "visitEnum";
                    break;
                }
                case 3: {
                    arr_object[2] = "visitAnnotation";
                    break;
                }
                default: {
                    arr_object[2] = "visitClassLiteral";
                }
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        private KotlinSerializedIrArgumentVisitor() {
        }

        KotlinSerializedIrArgumentVisitor(kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.1 readKotlinClassHeaderAnnotationVisitor$10) {
        }

        private AnnotationArrayArgumentVisitor serializedIrArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'result\' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinSerializedIrArgumentVisitor$1.visitEnd must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] arr_s) {
                    if(arr_s == null) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinSerializedIrArgumentVisitor.1.$$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.serializedIrFields = arr_s;
                }
            };
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visit(Name name0, Object object0) {
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
            if(classId0 == null) {
                KotlinSerializedIrArgumentVisitor.$$$reportNull$$$0(3);
            }
            return null;
        }

        // 去混淆评级： 低(20)
        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArrayArgumentVisitor visitArray(Name name0) {
            return "b".equals((name0 == null ? null : name0.asString())) ? this.serializedIrArrayVisitor() : null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
            if(classLiteralValue0 == null) {
                KotlinSerializedIrArgumentVisitor.$$$reportNull$$$0(0);
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnd() {
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnum(Name name0, ClassId classId0, Name name1) {
            if(classId0 == null) {
                KotlinSerializedIrArgumentVisitor.$$$reportNull$$$0(1);
            }
            if(name1 == null) {
                KotlinSerializedIrArgumentVisitor.$$$reportNull$$$0(2);
            }
        }
    }

    class OldDeprecatedAnnotationArgumentVisitor implements AnnotationArgumentVisitor {
        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            switch(v) {
                case 1: {
                    arr_object[0] = "enumClassId";
                    break;
                }
                case 2: {
                    arr_object[0] = "enumEntryName";
                    break;
                }
                case 3: {
                    arr_object[0] = "classId";
                    break;
                }
                default: {
                    arr_object[0] = "classLiteralValue";
                }
            }
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor";
            switch(v) {
                case 1: 
                case 2: {
                    arr_object[2] = "visitEnum";
                    break;
                }
                case 3: {
                    arr_object[2] = "visitAnnotation";
                    break;
                }
                default: {
                    arr_object[2] = "visitClassLiteral";
                }
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        private OldDeprecatedAnnotationArgumentVisitor() {
        }

        OldDeprecatedAnnotationArgumentVisitor(kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.1 readKotlinClassHeaderAnnotationVisitor$10) {
        }

        private AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'data\' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$1.visitEnd must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] arr_s) {
                    if(arr_s == null) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.1.$$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = arr_s;
                }
            };
        }

        private AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'data\' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$2.visitEnd must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor
                protected void visitEnd(String[] arr_s) {
                    if(arr_s == null) {
                        kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.2.$$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = arr_s;
                }
            };
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visit(Name name0, Object object0) {
            if(name0 != null) {
                String s = name0.asString();
                if("version".equals(s)) {
                    if(object0 instanceof int[]) {
                        ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = (int[])object0;
                    }
                }
                else if("multifileClassName".equals(s)) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraString = object0 instanceof String ? ((String)object0) : null;
                }
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArgumentVisitor visitAnnotation(Name name0, ClassId classId0) {
            if(classId0 == null) {
                OldDeprecatedAnnotationArgumentVisitor.$$$reportNull$$$0(3);
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public AnnotationArrayArgumentVisitor visitArray(Name name0) {
            String s = name0 == null ? null : name0.asString();
            if(!"data".equals(s) && !"filePartClassNames".equals(s)) {
                return "strings".equals(s) ? this.stringsArrayVisitor() : null;
            }
            return this.dataArrayVisitor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitClassLiteral(Name name0, ClassLiteralValue classLiteralValue0) {
            if(classLiteralValue0 == null) {
                OldDeprecatedAnnotationArgumentVisitor.$$$reportNull$$$0(0);
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnd() {
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationArgumentVisitor
        public void visitEnum(Name name0, ClassId classId0, Name name1) {
            if(classId0 == null) {
                OldDeprecatedAnnotationArgumentVisitor.$$$reportNull$$$0(1);
            }
            if(name1 == null) {
                OldDeprecatedAnnotationArgumentVisitor.$$$reportNull$$$0(2);
            }
        }
    }

    private static final Map HEADER_KINDS;
    private static final boolean IGNORE_OLD_METADATA;
    private String[] data;
    private int extraInt;
    private String extraString;
    private Kind headerKind;
    private String[] incompatibleData;
    private int[] metadataVersionArray;
    private String packageName;
    private String[] serializedIrFields;
    private String[] strings;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        arr_object[0] = v == 1 ? "source" : "classId";
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor";
        arr_object[2] = "visitAnnotation";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    static {
        ReadKotlinClassHeaderAnnotationVisitor.IGNORE_OLD_METADATA = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));
        HashMap hashMap0 = new HashMap();
        ReadKotlinClassHeaderAnnotationVisitor.HEADER_KINDS = hashMap0;
        hashMap0.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinClass")), Kind.CLASS);
        hashMap0.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinFileFacade")), Kind.FILE_FACADE);
        hashMap0.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClass")), Kind.MULTIFILE_CLASS);
        hashMap0.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClassPart")), Kind.MULTIFILE_CLASS_PART);
        hashMap0.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinSyntheticClass")), Kind.SYNTHETIC_CLASS);
    }

    public ReadKotlinClassHeaderAnnotationVisitor() {
        this.metadataVersionArray = null;
        this.extraString = null;
        this.extraInt = 0;
        this.packageName = null;
        this.data = null;
        this.strings = null;
        this.incompatibleData = null;
        this.headerKind = null;
        this.serializedIrFields = null;
    }

    public KotlinClassHeader createHeader(JvmMetadataVersion jvmMetadataVersion0) {
        byte[] arr_b = null;
        if(this.headerKind != null && this.metadataVersionArray != null) {
            JvmMetadataVersion jvmMetadataVersion1 = new JvmMetadataVersion(this.metadataVersionArray, (this.extraInt & 8) != 0);
            if(!jvmMetadataVersion1.isCompatible(jvmMetadataVersion0)) {
                this.incompatibleData = this.data;
                this.data = null;
            }
            else if(this.shouldHaveData() && this.data == null) {
                return null;
            }
            String[] arr_s = this.serializedIrFields;
            if(arr_s != null) {
                arr_b = BitEncoding.decodeBytes(arr_s);
            }
            return new KotlinClassHeader(this.headerKind, jvmMetadataVersion1, this.data, this.incompatibleData, this.strings, this.extraString, this.extraInt, this.packageName, arr_b);
        }
        return null;
    }

    public KotlinClassHeader createHeaderWithDefaultMetadataVersion() {
        return this.createHeader(JvmMetadataVersion.INSTANCE);
    }

    private boolean shouldHaveData() {
        return this.headerKind == Kind.CLASS || this.headerKind == Kind.FILE_FACADE || this.headerKind == Kind.MULTIFILE_CLASS_PART;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
    public AnnotationArgumentVisitor visitAnnotation(ClassId classId0, SourceElement sourceElement0) {
        if(classId0 == null) {
            ReadKotlinClassHeaderAnnotationVisitor.$$$reportNull$$$0(0);
        }
        if(sourceElement0 == null) {
            ReadKotlinClassHeaderAnnotationVisitor.$$$reportNull$$$0(1);
        }
        FqName fqName0 = classId0.asSingleFqName();
        if(fqName0.equals(JvmAnnotationNames.METADATA_FQ_NAME)) {
            return new KotlinMetadataArgumentVisitor(this, null);
        }
        if(fqName0.equals(JvmAnnotationNames.SERIALIZED_IR_FQ_NAME)) {
            return new KotlinSerializedIrArgumentVisitor(this, null);
        }
        if(ReadKotlinClassHeaderAnnotationVisitor.IGNORE_OLD_METADATA) {
            return null;
        }
        if(this.headerKind != null) {
            return null;
        }
        Kind kotlinClassHeader$Kind0 = (Kind)ReadKotlinClassHeaderAnnotationVisitor.HEADER_KINDS.get(classId0);
        if(kotlinClassHeader$Kind0 != null) {
            this.headerKind = kotlinClassHeader$Kind0;
            return new OldDeprecatedAnnotationArgumentVisitor(this, null);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
    public void visitEnd() {
    }

    class kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.1 {
    }

}

