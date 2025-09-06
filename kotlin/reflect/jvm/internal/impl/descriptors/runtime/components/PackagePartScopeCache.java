package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;

public final class PackagePartScopeCache {
    private final ConcurrentHashMap cache;
    private final ReflectKotlinClassFinder kotlinClassFinder;
    private final DeserializedDescriptorResolver resolver;

    public PackagePartScopeCache(DeserializedDescriptorResolver deserializedDescriptorResolver0, ReflectKotlinClassFinder reflectKotlinClassFinder0) {
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver0, "resolver");
        Intrinsics.checkNotNullParameter(reflectKotlinClassFinder0, "kotlinClassFinder");
        super();
        this.resolver = deserializedDescriptorResolver0;
        this.kotlinClassFinder = reflectKotlinClassFinder0;
        this.cache = new ConcurrentHashMap();
    }

    public final MemberScope getPackagePartScope(ReflectKotlinClass reflectKotlinClass0) {
        List list0;
        Intrinsics.checkNotNullParameter(reflectKotlinClass0, "fileClass");
        ConcurrentMap concurrentMap0 = this.cache;
        ClassId classId0 = reflectKotlinClass0.getClassId();
        MemberScope memberScope0 = concurrentMap0.get(classId0);
        if(memberScope0 == null) {
            FqName fqName0 = reflectKotlinClass0.getClassId().getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(fqName0, "fileClass.classId.packageFqName");
            if(reflectKotlinClass0.getClassHeader().getKind() == Kind.MULTIFILE_CLASS) {
                Iterable iterable0 = reflectKotlinClass0.getClassHeader().getMultifilePartNames();
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    ClassId classId1 = ClassId.topLevel(JvmClassName.byInternalName(((String)object0)).getFqNameForTopLevelClassMaybeWithDollars());
                    Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(JvmClassName.by…velClassMaybeWithDollars)");
                    JvmMetadataVersion jvmMetadataVersion0 = DeserializationHelpersKt.jvmMetadataVersionOrDefault(this.resolver.getComponents().getConfiguration());
                    KotlinJvmBinaryClass kotlinJvmBinaryClass0 = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId1, jvmMetadataVersion0);
                    if(kotlinJvmBinaryClass0 != null) {
                        collection0.add(kotlinJvmBinaryClass0);
                    }
                }
                list0 = (List)collection0;
            }
            else {
                list0 = CollectionsKt.listOf(reflectKotlinClass0);
            }
            EmptyPackageFragmentDescriptor emptyPackageFragmentDescriptor0 = new EmptyPackageFragmentDescriptor(this.resolver.getComponents().getModuleDescriptor(), fqName0);
            Collection collection1 = new ArrayList();
            for(Object object1: list0) {
                MemberScope memberScope1 = this.resolver.createKotlinPackagePartScope(emptyPackageFragmentDescriptor0, ((KotlinJvmBinaryClass)object1));
                if(memberScope1 != null) {
                    collection1.add(memberScope1);
                }
            }
            List list1 = CollectionsKt.toList(((List)collection1));
            MemberScope memberScope2 = ChainedMemberScope.Companion.create("package " + fqName0 + " (" + reflectKotlinClass0 + ')', list1);
            Object object2 = concurrentMap0.putIfAbsent(classId0, memberScope2);
            memberScope0 = object2 == null ? memberScope2 : object2;
        }
        Intrinsics.checkNotNullExpressionValue(memberScope0, "cache.getOrPut(fileClass…ileClass)\", scopes)\n    }");
        return memberScope0;
    }
}

