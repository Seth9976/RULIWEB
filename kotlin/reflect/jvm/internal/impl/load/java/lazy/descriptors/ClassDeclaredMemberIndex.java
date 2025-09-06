package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLoadingKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

public class ClassDeclaredMemberIndex implements DeclaredMemberIndex {
    private final Map components;
    private final Map fields;
    private final JavaClass jClass;
    private final Function1 memberFilter;
    private final Function1 methodFilter;
    private final Map methods;

    public ClassDeclaredMemberIndex(JavaClass javaClass0, Function1 function10) {
        Intrinsics.checkNotNullParameter(javaClass0, "jClass");
        Intrinsics.checkNotNullParameter(function10, "memberFilter");
        super();
        this.jClass = javaClass0;
        this.memberFilter = function10;
        Function1 function11 = new Function1() {
            {
                ClassDeclaredMemberIndex.this = classDeclaredMemberIndex0;
                super(1);
            }

            public final Boolean invoke(JavaMethod javaMethod0) {
                Intrinsics.checkNotNullParameter(javaMethod0, "m");
                return !((Boolean)ClassDeclaredMemberIndex.this.memberFilter.invoke(javaMethod0)).booleanValue() || JavaLoadingKt.isObjectMethodInInterface(javaMethod0) ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((JavaMethod)object0));
            }
        };
        this.methodFilter = function11;
        Sequence sequence0 = SequencesKt.filter(CollectionsKt.asSequence(javaClass0.getMethods()), function11);
        Map map0 = new LinkedHashMap();
        for(Object object0: sequence0) {
            Name name0 = ((JavaMethod)object0).getName();
            ArrayList arrayList0 = map0.get(name0);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
                map0.put(name0, arrayList0);
            }
            arrayList0.add(object0);
        }
        this.methods = map0;
        Sequence sequence1 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        Map map1 = new LinkedHashMap();
        for(Object object1: sequence1) {
            map1.put(((JavaField)object1).getName(), object1);
        }
        this.fields = map1;
        Iterable iterable0 = this.jClass.getRecordComponents();
        Function1 function12 = this.memberFilter;
        Collection collection0 = new ArrayList();
        for(Object object2: iterable0) {
            if(((Boolean)function12.invoke(object2)).booleanValue()) {
                collection0.add(object2);
            }
        }
        Map map2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10)), 16));
        for(Object object3: ((List)collection0)) {
            map2.put(((JavaRecordComponent)object3).getName(), object3);
        }
        this.components = map2;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public JavaField findFieldByName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return (JavaField)this.fields.get(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Collection findMethodsByName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        List list0 = (List)this.methods.get(name0);
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public JavaRecordComponent findRecordComponentByName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return (JavaRecordComponent)this.components.get(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getFieldNames() {
        Sequence sequence0 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        Collection collection0 = new LinkedHashSet();
        for(Object object0: sequence0) {
            collection0.add(((JavaField)object0).getName());
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getMethodNames() {
        Sequence sequence0 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        Collection collection0 = new LinkedHashSet();
        for(Object object0: sequence0) {
            collection0.add(((JavaMethod)object0).getName());
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getRecordComponentNames() {
        return this.components.keySet();
    }
}

