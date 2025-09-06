package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType;

public final class BuiltInsProtoBuf {
    public static final GeneratedExtension classAnnotation;
    public static final GeneratedExtension compileTimeValue;
    public static final GeneratedExtension constructorAnnotation;
    public static final GeneratedExtension enumEntryAnnotation;
    public static final GeneratedExtension functionAnnotation;
    public static final GeneratedExtension packageFqName;
    public static final GeneratedExtension parameterAnnotation;
    public static final GeneratedExtension propertyAnnotation;
    public static final GeneratedExtension propertyGetterAnnotation;
    public static final GeneratedExtension propertySetterAnnotation;
    public static final GeneratedExtension typeAnnotation;
    public static final GeneratedExtension typeParameterAnnotation;

    static {
        BuiltInsProtoBuf.packageFqName = GeneratedMessageLite.newSingularGeneratedExtension(Package.getDefaultInstance(), 0, null, null, 0x97, FieldType.INT32, Integer.class);
        BuiltInsProtoBuf.classAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Class.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.constructorAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Constructor.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.functionAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Function.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.propertyAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Property.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.propertyGetterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Property.getDefaultInstance(), Annotation.getDefaultInstance(), null, 0x98, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.propertySetterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Property.getDefaultInstance(), Annotation.getDefaultInstance(), null, 0x99, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.compileTimeValue = GeneratedMessageLite.newSingularGeneratedExtension(Property.getDefaultInstance(), Value.getDefaultInstance(), Value.getDefaultInstance(), null, 0x97, FieldType.MESSAGE, Value.class);
        BuiltInsProtoBuf.enumEntryAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(EnumEntry.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.parameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ValueParameter.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.typeAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Type.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
        BuiltInsProtoBuf.typeParameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(TypeParameter.getDefaultInstance(), Annotation.getDefaultInstance(), null, 150, FieldType.MESSAGE, false, Annotation.class);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite0) {
        extensionRegistryLite0.add(BuiltInsProtoBuf.packageFqName);
        extensionRegistryLite0.add(BuiltInsProtoBuf.classAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.constructorAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.functionAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.propertyAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.propertyGetterAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.propertySetterAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.compileTimeValue);
        extensionRegistryLite0.add(BuiltInsProtoBuf.enumEntryAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.parameterAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.typeAnnotation);
        extensionRegistryLite0.add(BuiltInsProtoBuf.typeParameterAnnotation);
    }
}

