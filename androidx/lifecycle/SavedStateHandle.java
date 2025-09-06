package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\t\u0018\u0000 *2\u00020\u0001:\u0002*+B\u001D\b\u0016\u0012\u0014\u0010\u0002\u001A\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u00A2\u0006\u0002\u0010\u0005B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0004H\u0007J\u0011\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0012\u001A\u00020\u0004H\u0087\u0002J\u001E\u0010\u0015\u001A\u0004\u0018\u0001H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u0004H\u0087\u0002\u00A2\u0006\u0002\u0010\u0017J\u001C\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0019\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u0004H\u0007J)\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0019\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u0002H\u0016H\u0007\u00A2\u0006\u0002\u0010\u001BJ1\u0010\u001C\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0019\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u001D\u001A\u00020\u00142\u0006\u0010\u001A\u001A\u0002H\u0016H\u0002\u00A2\u0006\u0002\u0010\u001EJ)\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00160 \"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u0002H\u0016H\u0007\u00A2\u0006\u0002\u0010!J\u000E\u0010\"\u001A\b\u0012\u0004\u0012\u00020\u00040#H\u0007J\u001D\u0010$\u001A\u0004\u0018\u0001H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u0004H\u0007\u00A2\u0006\u0002\u0010\u0017J\b\u0010\r\u001A\u00020\u000EH\u0007J&\u0010%\u001A\u00020\u0011\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0012\u001A\u00020\u00042\b\u0010&\u001A\u0004\u0018\u0001H\u0016H\u0087\u0002\u00A2\u0006\u0002\u0010\'J\u0018\u0010(\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00042\u0006\u0010)\u001A\u00020\u000EH\u0007R\"\u0010\u0007\u001A\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t0\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001E\u0010\n\u001A\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000B0\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\f\u001A\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u000F\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000E0\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006,"}, d2 = {"Landroidx/lifecycle/SavedStateHandle;", "", "initialState", "", "", "(Ljava/util/Map;)V", "()V", "flows", "", "Lkotlinx/coroutines/flow/MutableStateFlow;", "liveDatas", "Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;", "regular", "savedStateProvider", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "savedStateProviders", "clearSavedStateProvider", "", "key", "contains", "", "get", "T", "(Ljava/lang/String;)Ljava/lang/Object;", "getLiveData", "Landroidx/lifecycle/MutableLiveData;", "initialValue", "(Ljava/lang/String;Ljava/lang/Object;)Landroidx/lifecycle/MutableLiveData;", "getLiveDataInternal", "hasInitialValue", "(Ljava/lang/String;ZLjava/lang/Object;)Landroidx/lifecycle/MutableLiveData;", "getStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "(Ljava/lang/String;Ljava/lang/Object;)Lkotlinx/coroutines/flow/StateFlow;", "keys", "", "remove", "set", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "setSavedStateProvider", "provider", "Companion", "SavingStateLiveData", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateHandle {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001C\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\rH\u0007J\u0012\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0001H\u0007R \u0010\u0003\u001A\u0012\u0012\u000E\u0012\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000E\u0010\u0007\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/lifecycle/SavedStateHandle$Companion;", "", "()V", "ACCEPTABLE_CLASSES", "", "Ljava/lang/Class;", "[Ljava/lang/Class;", "KEYS", "", "VALUES", "createHandle", "Landroidx/lifecycle/SavedStateHandle;", "restoredState", "Landroid/os/Bundle;", "defaultState", "validateValue", "", "value", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SavedStateHandle createHandle(Bundle bundle0, Bundle bundle1) {
            if(bundle0 == null) {
                if(bundle1 == null) {
                    return new SavedStateHandle();
                }
                Map map0 = new HashMap();
                for(Object object0: bundle1.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(((String)object0), "key");
                    map0.put(((String)object0), bundle1.get(((String)object0)));
                }
                return new SavedStateHandle(map0);
            }
            ArrayList arrayList0 = bundle0.getParcelableArrayList("keys");
            ArrayList arrayList1 = bundle0.getParcelableArrayList("values");
            if(arrayList0 == null || arrayList1 == null || arrayList0.size() != arrayList1.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state");
            }
            Map map1 = new LinkedHashMap();
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Object object1 = arrayList0.get(v1);
                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.String");
                map1.put(((String)object1), arrayList1.get(v1));
            }
            return new SavedStateHandle(map1);
        }

        public final boolean validateValue(Object object0) {
            if(object0 == null) {
                return true;
            }
            Class[] arr_class = SavedStateHandle.ACCEPTABLE_CLASSES;
            for(int v = 0; v < arr_class.length; ++v) {
                Class class0 = arr_class[v];
                Intrinsics.checkNotNull(class0);
                if(class0.isInstance(object0)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\b\u0016\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00028\u0000¢\u0006\u0002\u0010\bB\u0019\b\u0016\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001A\u00020\u000BJ\u0015\u0010\f\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;", "T", "Landroidx/lifecycle/MutableLiveData;", "handle", "Landroidx/lifecycle/SavedStateHandle;", "key", "", "value", "(Landroidx/lifecycle/SavedStateHandle;Ljava/lang/String;Ljava/lang/Object;)V", "(Landroidx/lifecycle/SavedStateHandle;Ljava/lang/String;)V", "detach", "", "setValue", "(Ljava/lang/Object;)V", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class SavingStateLiveData extends MutableLiveData {
        private SavedStateHandle handle;
        private String key;

        public SavingStateLiveData(SavedStateHandle savedStateHandle0, String s) {
            Intrinsics.checkNotNullParameter(s, "key");
            super();
            this.key = s;
            this.handle = savedStateHandle0;
        }

        public SavingStateLiveData(SavedStateHandle savedStateHandle0, String s, Object object0) {
            Intrinsics.checkNotNullParameter(s, "key");
            super(object0);
            this.key = s;
            this.handle = savedStateHandle0;
        }

        public final void detach() {
            this.handle = null;
        }

        @Override  // androidx.lifecycle.MutableLiveData
        public void setValue(Object object0) {
            SavedStateHandle savedStateHandle0 = this.handle;
            if(savedStateHandle0 != null) {
                savedStateHandle0.regular.put(this.key, object0);
                MutableStateFlow mutableStateFlow0 = (MutableStateFlow)savedStateHandle0.flows.get(this.key);
                if(mutableStateFlow0 != null) {
                    mutableStateFlow0.setValue(object0);
                }
            }
            super.setValue(object0);
        }
    }

    private static final Class[] ACCEPTABLE_CLASSES = null;
    public static final Companion Companion = null;
    private static final String KEYS = "keys";
    private static final String VALUES = "values";
    private final Map flows;
    private final Map liveDatas;
    private final Map regular;
    private final SavedStateProvider savedStateProvider;
    private final Map savedStateProviders;

    static {
        SavedStateHandle.Companion = new Companion(null);
        SavedStateHandle.ACCEPTABLE_CLASSES = new Class[]{Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = () -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            for(Object object0: MapsKt.toMap(this.savedStateProviders).entrySet()) {
                this.set(((String)((Map.Entry)object0).getKey()), ((SavedStateProvider)((Map.Entry)object0).getValue()).saveState());
            }
            Set set0 = this.regular.keySet();
            ArrayList arrayList0 = new ArrayList(set0.size());
            ArrayList arrayList1 = new ArrayList(arrayList0.size());
            for(Object object1: set0) {
                arrayList0.add(((String)object1));
                arrayList1.add(this.regular.get(((String)object1)));
            }
            return BundleKt.bundleOf(new Pair[]{TuplesKt.to("keys", arrayList0), TuplesKt.to("values", arrayList1)});
        };
    }

    public SavedStateHandle(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "initialState");
        super();
        Map map1 = new LinkedHashMap();
        this.regular = map1;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = () -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            for(Object object0: MapsKt.toMap(this.savedStateProviders).entrySet()) {
                this.set(((String)((Map.Entry)object0).getKey()), ((SavedStateProvider)((Map.Entry)object0).getValue()).saveState());
            }
            Set set0 = this.regular.keySet();
            ArrayList arrayList0 = new ArrayList(set0.size());
            ArrayList arrayList1 = new ArrayList(arrayList0.size());
            for(Object object1: set0) {
                arrayList0.add(((String)object1));
                arrayList1.add(this.regular.get(((String)object1)));
            }
            return BundleKt.bundleOf(new Pair[]{TuplesKt.to("keys", arrayList0), TuplesKt.to("values", arrayList1)});
        };
        map1.putAll(map0);
    }

    public final void clearSavedStateProvider(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        this.savedStateProviders.remove(s);
    }

    public final boolean contains(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        return this.regular.containsKey(s);
    }

    @JvmStatic
    public static final SavedStateHandle createHandle(Bundle bundle0, Bundle bundle1) {
        return SavedStateHandle.Companion.createHandle(bundle0, bundle1);
    }

    public final Object get(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        try {
            return this.regular.get(s);
        }
        catch(ClassCastException unused_ex) {
            this.remove(s);
            return null;
        }
    }

    public final MutableLiveData getLiveData(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        MutableLiveData mutableLiveData0 = this.getLiveDataInternal(s, false, null);
        Intrinsics.checkNotNull(mutableLiveData0, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<T of androidx.lifecycle.SavedStateHandle.getLiveData>");
        return mutableLiveData0;
    }

    public final MutableLiveData getLiveData(String s, Object object0) {
        Intrinsics.checkNotNullParameter(s, "key");
        return this.getLiveDataInternal(s, true, object0);
    }

    private final MutableLiveData getLiveDataInternal(String s, boolean z, Object object0) {
        SavingStateLiveData savedStateHandle$SavingStateLiveData0;
        Object object1 = this.liveDatas.get(s);
        MutableLiveData mutableLiveData0 = object1 instanceof MutableLiveData ? ((MutableLiveData)object1) : null;
        if(mutableLiveData0 != null) {
            return mutableLiveData0;
        }
        if(this.regular.containsKey(s)) {
            savedStateHandle$SavingStateLiveData0 = new SavingStateLiveData(this, s, this.regular.get(s));
        }
        else if(z) {
            this.regular.put(s, object0);
            savedStateHandle$SavingStateLiveData0 = new SavingStateLiveData(this, s, object0);
        }
        else {
            savedStateHandle$SavingStateLiveData0 = new SavingStateLiveData(this, s);
        }
        this.liveDatas.put(s, savedStateHandle$SavingStateLiveData0);
        return savedStateHandle$SavingStateLiveData0;
    }

    public final StateFlow getStateFlow(String s, Object object0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Map map0 = this.flows;
        MutableStateFlow mutableStateFlow0 = map0.get(s);
        if(mutableStateFlow0 == null) {
            if(!this.regular.containsKey(s)) {
                this.regular.put(s, object0);
            }
            mutableStateFlow0 = StateFlowKt.MutableStateFlow(this.regular.get(s));
            this.flows.put(s, mutableStateFlow0);
            map0.put(s, mutableStateFlow0);
        }
        StateFlow stateFlow0 = FlowKt.asStateFlow(mutableStateFlow0);
        Intrinsics.checkNotNull(stateFlow0, "null cannot be cast to non-null type kotlinx.coroutines.flow.StateFlow<T of androidx.lifecycle.SavedStateHandle.getStateFlow>");
        return stateFlow0;
    }

    public final Set keys() {
        return SetsKt.plus(SetsKt.plus(this.regular.keySet(), this.savedStateProviders.keySet()), this.liveDatas.keySet());
    }

    public final Object remove(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.regular.remove(s);
        SavingStateLiveData savedStateHandle$SavingStateLiveData0 = (SavingStateLiveData)this.liveDatas.remove(s);
        if(savedStateHandle$SavingStateLiveData0 != null) {
            savedStateHandle$SavingStateLiveData0.detach();
        }
        this.flows.remove(s);
        return object0;
    }

    public final SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    // 检测为 Lambda 实现
    private static final Bundle savedStateProvider$lambda$0(SavedStateHandle savedStateHandle0) [...]

    public final void set(String s, Object object0) {
        Intrinsics.checkNotNullParameter(s, "key");
        if(SavedStateHandle.Companion.validateValue(object0)) {
            Object object1 = this.liveDatas.get(s);
            MutableLiveData mutableLiveData0 = object1 instanceof MutableLiveData ? ((MutableLiveData)object1) : null;
            if(mutableLiveData0 == null) {
                this.regular.put(s, object0);
            }
            else {
                mutableLiveData0.setValue(object0);
            }
            MutableStateFlow mutableStateFlow0 = (MutableStateFlow)this.flows.get(s);
            if(mutableStateFlow0 == null) {
                return;
            }
            mutableStateFlow0.setValue(object0);
            return;
        }
        Intrinsics.checkNotNull(object0);
        throw new IllegalArgumentException("Can\'t put value with type " + object0.getClass() + " into saved state");
    }

    public final void setSavedStateProvider(String s, SavedStateProvider savedStateRegistry$SavedStateProvider0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(savedStateRegistry$SavedStateProvider0, "provider");
        this.savedStateProviders.put(s, savedStateRegistry$SavedStateProvider0);
    }
}

