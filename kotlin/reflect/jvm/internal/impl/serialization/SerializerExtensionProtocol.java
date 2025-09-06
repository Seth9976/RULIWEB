package kotlin.reflect.jvm.internal.impl.serialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;

public class SerializerExtensionProtocol {
    private final GeneratedExtension classAnnotation;
    private final GeneratedExtension compileTimeValue;
    private final GeneratedExtension constructorAnnotation;
    private final GeneratedExtension enumEntryAnnotation;
    private final ExtensionRegistryLite extensionRegistry;
    private final GeneratedExtension functionAnnotation;
    private final GeneratedExtension functionExtensionReceiverAnnotation;
    private final GeneratedExtension packageFqName;
    private final GeneratedExtension parameterAnnotation;
    private final GeneratedExtension propertyAnnotation;
    private final GeneratedExtension propertyBackingFieldAnnotation;
    private final GeneratedExtension propertyDelegatedFieldAnnotation;
    private final GeneratedExtension propertyExtensionReceiverAnnotation;
    private final GeneratedExtension propertyGetterAnnotation;
    private final GeneratedExtension propertySetterAnnotation;
    private final GeneratedExtension typeAnnotation;
    private final GeneratedExtension typeParameterAnnotation;

    public SerializerExtensionProtocol(ExtensionRegistryLite extensionRegistryLite0, GeneratedExtension generatedMessageLite$GeneratedExtension0, GeneratedExtension generatedMessageLite$GeneratedExtension1, GeneratedExtension generatedMessageLite$GeneratedExtension2, GeneratedExtension generatedMessageLite$GeneratedExtension3, GeneratedExtension generatedMessageLite$GeneratedExtension4, GeneratedExtension generatedMessageLite$GeneratedExtension5, GeneratedExtension generatedMessageLite$GeneratedExtension6, GeneratedExtension generatedMessageLite$GeneratedExtension7, GeneratedExtension generatedMessageLite$GeneratedExtension8, GeneratedExtension generatedMessageLite$GeneratedExtension9, GeneratedExtension generatedMessageLite$GeneratedExtension10, GeneratedExtension generatedMessageLite$GeneratedExtension11, GeneratedExtension generatedMessageLite$GeneratedExtension12, GeneratedExtension generatedMessageLite$GeneratedExtension13, GeneratedExtension generatedMessageLite$GeneratedExtension14, GeneratedExtension generatedMessageLite$GeneratedExtension15) {
        Intrinsics.checkNotNullParameter(extensionRegistryLite0, "extensionRegistry");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension0, "packageFqName");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension1, "constructorAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension2, "classAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension3, "functionAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension5, "propertyAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension6, "propertyGetterAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension7, "propertySetterAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension11, "enumEntryAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension12, "compileTimeValue");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension13, "parameterAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension14, "typeAnnotation");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension15, "typeParameterAnnotation");
        super();
        this.extensionRegistry = extensionRegistryLite0;
        this.packageFqName = generatedMessageLite$GeneratedExtension0;
        this.constructorAnnotation = generatedMessageLite$GeneratedExtension1;
        this.classAnnotation = generatedMessageLite$GeneratedExtension2;
        this.functionAnnotation = generatedMessageLite$GeneratedExtension3;
        this.functionExtensionReceiverAnnotation = generatedMessageLite$GeneratedExtension4;
        this.propertyAnnotation = generatedMessageLite$GeneratedExtension5;
        this.propertyGetterAnnotation = generatedMessageLite$GeneratedExtension6;
        this.propertySetterAnnotation = generatedMessageLite$GeneratedExtension7;
        this.propertyExtensionReceiverAnnotation = generatedMessageLite$GeneratedExtension8;
        this.propertyBackingFieldAnnotation = generatedMessageLite$GeneratedExtension9;
        this.propertyDelegatedFieldAnnotation = generatedMessageLite$GeneratedExtension10;
        this.enumEntryAnnotation = generatedMessageLite$GeneratedExtension11;
        this.compileTimeValue = generatedMessageLite$GeneratedExtension12;
        this.parameterAnnotation = generatedMessageLite$GeneratedExtension13;
        this.typeAnnotation = generatedMessageLite$GeneratedExtension14;
        this.typeParameterAnnotation = generatedMessageLite$GeneratedExtension15;
    }

    public final GeneratedExtension getClassAnnotation() {
        return this.classAnnotation;
    }

    public final GeneratedExtension getCompileTimeValue() {
        return this.compileTimeValue;
    }

    public final GeneratedExtension getConstructorAnnotation() {
        return this.constructorAnnotation;
    }

    public final GeneratedExtension getEnumEntryAnnotation() {
        return this.enumEntryAnnotation;
    }

    public final ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public final GeneratedExtension getFunctionAnnotation() {
        return this.functionAnnotation;
    }

    public final GeneratedExtension getFunctionExtensionReceiverAnnotation() {
        return this.functionExtensionReceiverAnnotation;
    }

    public final GeneratedExtension getParameterAnnotation() {
        return this.parameterAnnotation;
    }

    public final GeneratedExtension getPropertyAnnotation() {
        return this.propertyAnnotation;
    }

    public final GeneratedExtension getPropertyBackingFieldAnnotation() {
        return this.propertyBackingFieldAnnotation;
    }

    public final GeneratedExtension getPropertyDelegatedFieldAnnotation() {
        return this.propertyDelegatedFieldAnnotation;
    }

    public final GeneratedExtension getPropertyExtensionReceiverAnnotation() {
        return this.propertyExtensionReceiverAnnotation;
    }

    public final GeneratedExtension getPropertyGetterAnnotation() {
        return this.propertyGetterAnnotation;
    }

    public final GeneratedExtension getPropertySetterAnnotation() {
        return this.propertySetterAnnotation;
    }

    public final GeneratedExtension getTypeAnnotation() {
        return this.typeAnnotation;
    }

    public final GeneratedExtension getTypeParameterAnnotation() {
        return this.typeParameterAnnotation;
    }
}

