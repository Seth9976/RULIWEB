package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface DeclaredMemberIndex {
    public static final class Empty implements DeclaredMemberIndex {
        public static final Empty INSTANCE;

        static {
            Empty.INSTANCE = new Empty();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public JavaField findFieldByName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public Collection findMethodsByName(Name name0) {
            return this.findMethodsByName(name0);
        }

        public List findMethodsByName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return CollectionsKt.emptyList();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public JavaRecordComponent findRecordComponentByName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public Set getFieldNames() {
            return SetsKt.emptySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public Set getMethodNames() {
            return SetsKt.emptySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
        public Set getRecordComponentNames() {
            return SetsKt.emptySet();
        }
    }

    JavaField findFieldByName(Name arg1);

    Collection findMethodsByName(Name arg1);

    JavaRecordComponent findRecordComponentByName(Name arg1);

    Set getFieldNames();

    Set getMethodNames();

    Set getRecordComponentNames();
}

