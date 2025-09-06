package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.text.StringsKt;

public final class BuiltInSerializerProtocol extends SerializerExtensionProtocol {
    public static final BuiltInSerializerProtocol INSTANCE;

    static {
        BuiltInSerializerProtocol.INSTANCE = new BuiltInSerializerProtocol();
    }

    private BuiltInSerializerProtocol() {
        ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.newInstance();
        BuiltInsProtoBuf.registerAllExtensions(extensionRegistryLite0);
        Intrinsics.checkNotNullExpressionValue(extensionRegistryLite0, "newInstance().apply(Buil…f::registerAllExtensions)");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.packageFqName, "packageFqName");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.constructorAnnotation, "constructorAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.classAnnotation, "classAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.functionAnnotation, "functionAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.propertyAnnotation, "propertyAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.propertyGetterAnnotation, "propertyGetterAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.propertySetterAnnotation, "propertySetterAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.enumEntryAnnotation, "enumEntryAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.compileTimeValue, "compileTimeValue");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.parameterAnnotation, "parameterAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.typeAnnotation, "typeAnnotation");
        Intrinsics.checkNotNullExpressionValue(BuiltInsProtoBuf.typeParameterAnnotation, "typeParameterAnnotation");
        super(extensionRegistryLite0, BuiltInsProtoBuf.packageFqName, BuiltInsProtoBuf.constructorAnnotation, BuiltInsProtoBuf.classAnnotation, BuiltInsProtoBuf.functionAnnotation, null, BuiltInsProtoBuf.propertyAnnotation, BuiltInsProtoBuf.propertyGetterAnnotation, BuiltInsProtoBuf.propertySetterAnnotation, null, null, null, BuiltInsProtoBuf.enumEntryAnnotation, BuiltInsProtoBuf.compileTimeValue, BuiltInsProtoBuf.parameterAnnotation, BuiltInsProtoBuf.typeAnnotation, BuiltInsProtoBuf.typeParameterAnnotation);
    }

    public final String getBuiltInsFileName(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return this.shortName(fqName0) + ".kotlin_builtins";
    }

    public final String getBuiltInsFilePath(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        String s = fqName0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "fqName.asString()");
        return StringsKt.replace$default(s, '.', '/', false, 4, null) + '/' + this.getBuiltInsFileName(fqName0);
    }

    private final String shortName(FqName fqName0) {
        if(fqName0.isRoot()) {
            return "default-package";
        }
        String s = fqName0.shortName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "fqName.shortName().asString()");
        return s;
    }
}

