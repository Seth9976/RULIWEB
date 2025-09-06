package kotlin.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0010\'\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001F\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B<\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012!\u0010\u0006\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00010\u0007\u00A2\u0006\u0002\u0010\u000BJ\b\u0010\u001D\u001A\u00020\u001EH\u0016J\u0015\u0010\u001F\u001A\u00020 2\u0006\u0010\n\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010!J\u0015\u0010\"\u001A\u00020 2\u0006\u0010#\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010!J\u0013\u0010$\u001A\u00020 2\b\u0010%\u001A\u0004\u0018\u00010&H\u0096\u0002J\u0018\u0010\'\u001A\u0004\u0018\u00018\u00012\u0006\u0010\n\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010(J\u0015\u0010)\u001A\u00028\u00012\u0006\u0010\n\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010(J\b\u0010*\u001A\u00020\u0016H\u0016J\b\u0010+\u001A\u00020 H\u0016J\u001F\u0010,\u001A\u0004\u0018\u00018\u00012\u0006\u0010\n\u001A\u00028\u00002\u0006\u0010#\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010-J\u001E\u0010.\u001A\u00020\u001E2\u0014\u0010/\u001A\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0016J\u0017\u00101\u001A\u0004\u0018\u00018\u00012\u0006\u0010\n\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010(J\b\u00102\u001A\u000203H\u0016R)\u0010\u0006\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R&\u0010\f\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000E0\r8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u001A\u0010\u0011\u001A\b\u0012\u0004\u0012\u00028\u00000\r8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0010R \u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001A\u00020\u00168VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018R\u001A\u0010\u0019\u001A\b\u0012\u0004\u0012\u00028\u00010\u001A8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u001C\u00A8\u00064"}, d2 = {"Lkotlin/collections/MutableMapWithDefaultImpl;", "K", "V", "Lkotlin/collections/MutableMapWithDefault;", "map", "", "default", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "key", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "getMap", "()Ljava/util/Map;", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "containsKey", "", "(Ljava/lang/Object;)Z", "containsValue", "value", "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrImplicitDefault", "hashCode", "isEmpty", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "", "remove", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class MutableMapWithDefaultImpl implements MutableMapWithDefault {
    private final Function1 default;
    private final Map map;

    public MutableMapWithDefaultImpl(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "map");
        Intrinsics.checkNotNullParameter(function10, "default");
        super();
        this.map = map0;
        this.default = function10;
    }

    @Override
    public void clear() {
        this.getMap().clear();
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.getMap().containsKey(object0);
    }

    @Override
    public boolean containsValue(Object object0) {
        return this.getMap().containsValue(object0);
    }

    @Override
    public final Set entrySet() {
        return this.getEntries();
    }

    @Override
    public boolean equals(Object object0) {
        return this.getMap().equals(object0);
    }

    @Override
    public Object get(Object object0) {
        return this.getMap().get(object0);
    }

    public Set getEntries() {
        return this.getMap().entrySet();
    }

    public Set getKeys() {
        return this.getMap().keySet();
    }

    @Override  // kotlin.collections.MutableMapWithDefault
    public Map getMap() {
        return this.map;
    }

    @Override  // kotlin.collections.MapWithDefault
    public Object getOrImplicitDefault(Object object0) {
        Map map0 = this.getMap();
        Object object1 = map0.get(object0);
        return object1 != null || map0.containsKey(object0) ? object1 : this.default.invoke(object0);
    }

    public int getSize() {
        return this.getMap().size();
    }

    public Collection getValues() {
        return this.getMap().values();
    }

    @Override
    public int hashCode() {
        return this.getMap().hashCode();
    }

    @Override
    public boolean isEmpty() {
        return this.getMap().isEmpty();
    }

    @Override
    public final Set keySet() {
        return this.getKeys();
    }

    @Override
    public Object put(Object object0, Object object1) {
        return this.getMap().put(object0, object1);
    }

    @Override
    public void putAll(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "from");
        this.getMap().putAll(map0);
    }

    @Override
    public Object remove(Object object0) {
        return this.getMap().remove(object0);
    }

    @Override
    public final int size() {
        return this.getSize();
    }

    @Override
    public String toString() {
        return this.getMap().toString();
    }

    @Override
    public final Collection values() {
        return this.getValues();
    }
}

