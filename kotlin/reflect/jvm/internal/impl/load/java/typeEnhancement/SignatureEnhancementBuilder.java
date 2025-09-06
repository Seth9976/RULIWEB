package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

final class SignatureEnhancementBuilder {
    public final class ClassEnhancementBuilder {
        public final class FunctionEnhancementBuilder {
            private final String functionName;
            private final List parameters;
            private Pair returnType;

            public FunctionEnhancementBuilder(String s) {
                Intrinsics.checkNotNullParameter(s, "functionName");
                ClassEnhancementBuilder.this = signatureEnhancementBuilder$ClassEnhancementBuilder0;
                super();
                this.functionName = s;
                this.parameters = new ArrayList();
                this.returnType = TuplesKt.to("V", null);
            }

            public final Pair build() {
                SignatureBuildingComponents signatureBuildingComponents0 = SignatureBuildingComponents.INSTANCE;
                String s = ClassEnhancementBuilder.this.getClassName();
                String s1 = this.functionName;
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.parameters, 10));
                for(Object object0: this.parameters) {
                    arrayList0.add(((String)((Pair)object0).getFirst()));
                }
                String s2 = signatureBuildingComponents0.signature(s, signatureBuildingComponents0.jvmDescriptor(s1, arrayList0, ((String)this.returnType.getFirst())));
                TypeEnhancementInfo typeEnhancementInfo0 = (TypeEnhancementInfo)this.returnType.getSecond();
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.parameters, 10));
                for(Object object1: this.parameters) {
                    arrayList1.add(((TypeEnhancementInfo)((Pair)object1).getSecond()));
                }
                return TuplesKt.to(s2, new PredefinedFunctionEnhancementInfo(typeEnhancementInfo0, arrayList1));
            }

            public final void parameter(String s, JavaTypeQualifiers[] arr_javaTypeQualifiers) {
                TypeEnhancementInfo typeEnhancementInfo0;
                Intrinsics.checkNotNullParameter(s, "type");
                Intrinsics.checkNotNullParameter(arr_javaTypeQualifiers, "qualifiers");
                Collection collection0 = this.parameters;
                if(arr_javaTypeQualifiers.length == 0) {
                    typeEnhancementInfo0 = null;
                }
                else {
                    Iterable iterable0 = ArraysKt.withIndex(arr_javaTypeQualifiers);
                    Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
                    for(Object object0: iterable0) {
                        map0.put(((IndexedValue)object0).getIndex(), ((JavaTypeQualifiers)((IndexedValue)object0).getValue()));
                    }
                    typeEnhancementInfo0 = new TypeEnhancementInfo(map0);
                }
                collection0.add(TuplesKt.to(s, typeEnhancementInfo0));
            }

            public final void returns(String s, JavaTypeQualifiers[] arr_javaTypeQualifiers) {
                Intrinsics.checkNotNullParameter(s, "type");
                Intrinsics.checkNotNullParameter(arr_javaTypeQualifiers, "qualifiers");
                Iterable iterable0 = ArraysKt.withIndex(arr_javaTypeQualifiers);
                Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
                for(Object object0: iterable0) {
                    map0.put(((IndexedValue)object0).getIndex(), ((JavaTypeQualifiers)((IndexedValue)object0).getValue()));
                }
                this.returnType = TuplesKt.to(s, new TypeEnhancementInfo(map0));
            }

            public final void returns(JvmPrimitiveType jvmPrimitiveType0) {
                Intrinsics.checkNotNullParameter(jvmPrimitiveType0, "type");
                String s = jvmPrimitiveType0.getDesc();
                Intrinsics.checkNotNullExpressionValue(s, "type.desc");
                this.returnType = TuplesKt.to(s, null);
            }
        }

        private final String className;

        public ClassEnhancementBuilder(String s) {
            Intrinsics.checkNotNullParameter(s, "className");
            SignatureEnhancementBuilder.this = signatureEnhancementBuilder0;
            super();
            this.className = s;
        }

        public final void function(String s, Function1 function10) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(function10, "block");
            Map map0 = SignatureEnhancementBuilder.this.signatures;
            FunctionEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0 = new FunctionEnhancementBuilder(this, s);
            function10.invoke(signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0);
            Pair pair0 = signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0.build();
            map0.put(pair0.getFirst(), pair0.getSecond());
        }

        public final String getClassName() {
            return this.className;
        }
    }

    private final Map signatures;

    public SignatureEnhancementBuilder() {
        this.signatures = new LinkedHashMap();
    }

    public final Map build() {
        return this.signatures;
    }
}

