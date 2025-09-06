package com.google.firebase.components;

import android.util.Log;
import androidx.lifecycle.LifecycleKt..ExternalSyntheticBackportWithForwarding0;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public class ComponentRuntime implements ComponentContainer, ComponentLoader {
    public static final class Builder {
        private final List additionalComponents;
        private ComponentRegistrarProcessor componentRegistrarProcessor;
        private final Executor defaultExecutor;
        private final List lazyRegistrars;

        Builder(Executor executor0) {
            this.lazyRegistrars = new ArrayList();
            this.additionalComponents = new ArrayList();
            this.componentRegistrarProcessor = ComponentRegistrarProcessor.NOOP;
            this.defaultExecutor = executor0;
        }

        public Builder addComponent(Component component0) {
            this.additionalComponents.add(component0);
            return this;
        }

        public Builder addComponentRegistrar(ComponentRegistrar componentRegistrar0) {
            ComponentRuntime.Builder..ExternalSyntheticLambda0 componentRuntime$Builder$$ExternalSyntheticLambda00 = new ComponentRuntime.Builder..ExternalSyntheticLambda0(componentRegistrar0);
            this.lazyRegistrars.add(componentRuntime$Builder$$ExternalSyntheticLambda00);
            return this;
        }

        public Builder addLazyComponentRegistrars(Collection collection0) {
            this.lazyRegistrars.addAll(collection0);
            return this;
        }

        public ComponentRuntime build() {
            return new ComponentRuntime(this.defaultExecutor, this.lazyRegistrars, this.additionalComponents, this.componentRegistrarProcessor, null);
        }

        static ComponentRegistrar lambda$addComponentRegistrar$0(ComponentRegistrar componentRegistrar0) [...] // Inlined contents

        public Builder setProcessor(ComponentRegistrarProcessor componentRegistrarProcessor0) {
            this.componentRegistrarProcessor = componentRegistrarProcessor0;
            return this;
        }
    }

    private static final Provider EMPTY_PROVIDER;
    private final ComponentRegistrarProcessor componentRegistrarProcessor;
    private final Map components;
    private final AtomicReference eagerComponentsInitializedWith;
    private final EventBus eventBus;
    private final Map lazyInstanceMap;
    private final Map lazySetMap;
    private final List unprocessedRegistrarProviders;

    static {
        ComponentRuntime.EMPTY_PROVIDER = new ComponentRuntime..ExternalSyntheticLambda1();
    }

    private ComponentRuntime(Executor executor0, Iterable iterable0, Collection collection0, ComponentRegistrarProcessor componentRegistrarProcessor0) {
        this.components = new HashMap();
        this.lazyInstanceMap = new HashMap();
        this.lazySetMap = new HashMap();
        this.eagerComponentsInitializedWith = new AtomicReference();
        EventBus eventBus0 = new EventBus(executor0);
        this.eventBus = eventBus0;
        this.componentRegistrarProcessor = componentRegistrarProcessor0;
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(Component.of(eventBus0, EventBus.class, new Class[]{Subscriber.class, Publisher.class}));
        arrayList0.add(Component.of(this, ComponentLoader.class, new Class[0]));
        for(Object object0: collection0) {
            Component component0 = (Component)object0;
            if(component0 != null) {
                arrayList0.add(component0);
            }
        }
        this.unprocessedRegistrarProviders = ComponentRuntime.iterableToList(iterable0);
        this.discoverComponents(arrayList0);
    }

    ComponentRuntime(Executor executor0, Iterable iterable0, Collection collection0, ComponentRegistrarProcessor componentRegistrarProcessor0, com.google.firebase.components.ComponentRuntime.1 componentRuntime$10) {
        this(executor0, iterable0, collection0, componentRegistrarProcessor0);
    }

    @Deprecated
    public ComponentRuntime(Executor executor0, Iterable iterable0, Component[] arr_component) {
        this(executor0, ComponentRuntime.toProviders(iterable0), Arrays.asList(arr_component), ComponentRegistrarProcessor.NOOP);
    }

    public static Builder builder(Executor executor0) {
        return new Builder(executor0);
    }

    private void discoverComponents(List list0) {
        ArrayList arrayList0 = new ArrayList();
        synchronized(this) {
            Iterator iterator0 = this.unprocessedRegistrarProviders.iterator();
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                Provider provider0 = (Provider)object0;
                try {
                    ComponentRegistrar componentRegistrar0 = (ComponentRegistrar)provider0.get();
                    if(componentRegistrar0 == null) {
                        continue;
                    }
                    list0.addAll(this.componentRegistrarProcessor.processRegistrar(componentRegistrar0));
                    iterator0.remove();
                }
                catch(InvalidRegistrarException invalidRegistrarException0) {
                    iterator0.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", invalidRegistrarException0);
                }
            }
            if(this.components.isEmpty()) {
                CycleDetector.detect(list0);
            }
            else {
                ArrayList arrayList1 = new ArrayList(this.components.keySet());
                arrayList1.addAll(list0);
                CycleDetector.detect(arrayList1);
            }
            for(Object object1: list0) {
                Lazy lazy0 = new Lazy(() -> ((Component)object1).getFactory().create(new RestrictedComponentContainer(((Component)object1), this)));
                this.components.put(((Component)object1), lazy0);
            }
            arrayList0.addAll(this.processInstanceComponents(list0));
            arrayList0.addAll(this.processSetComponents());
            this.processDependencies();
        }
        for(Object object2: arrayList0) {
            ((Runnable)object2).run();
        }
        this.maybeInitializeEagerComponents();
    }

    @Override  // com.google.firebase.dynamicloading.ComponentLoader
    public void discoverComponents() {
        synchronized(this) {
            if(this.unprocessedRegistrarProviders.isEmpty()) {
                return;
            }
        }
        this.discoverComponents(new ArrayList());
    }

    private void doInitializeEagerComponents(Map map0, boolean z) {
        for(Object object0: map0.entrySet()) {
            Component component0 = (Component)((Map.Entry)object0).getKey();
            Provider provider0 = (Provider)((Map.Entry)object0).getValue();
            if(component0.isAlwaysEager() || component0.isEagerInDefaultApp() && z) {
                provider0.get();
            }
        }
        this.eventBus.enablePublishingAndFlushPending();
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Object get(Qualified qualified0) {
        return ComponentContainer.-CC.$default$get(this, qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Object get(Class class0) {
        return ComponentContainer.-CC.$default$get(this, class0);
    }

    Collection getAllComponentsForTest() {
        return this.components.keySet();
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Deferred getDeferred(Qualified qualified0) {
        Provider provider0 = this.getProvider(qualified0);
        if(provider0 == null) {
            return OptionalProvider.empty();
        }
        return provider0 instanceof OptionalProvider ? ((OptionalProvider)provider0) : OptionalProvider.of(provider0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Deferred getDeferred(Class class0) {
        return ComponentContainer.-CC.$default$getDeferred(this, class0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider getProvider(Qualified qualified0) {
        synchronized(this) {
            Preconditions.checkNotNull(qualified0, "Null interface requested.");
            return (Provider)this.lazyInstanceMap.get(qualified0);
        }
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider getProvider(Class class0) {
        return ComponentContainer.-CC.$default$getProvider(this, class0);
    }

    public void initializeAllComponentsForTests() {
        for(Object object0: this.components.values()) {
            ((Provider)object0).get();
        }
    }

    public void initializeEagerComponents(boolean z) {
        HashMap hashMap0;
        if(!LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(this.eagerComponentsInitializedWith, null, Boolean.valueOf(z))) {
            return;
        }
        synchronized(this) {
            hashMap0 = new HashMap(this.components);
        }
        this.doInitializeEagerComponents(hashMap0, z);
    }

    private static List iterableToList(Iterable iterable0) {
        List list0 = new ArrayList();
        for(Object object0: iterable0) {
            ((ArrayList)list0).add(object0);
        }
        return list0;
    }

    // 检测为 Lambda 实现
    Object lambda$discoverComponents$0$com-google-firebase-components-ComponentRuntime(Component component0) [...]

    // 检测为 Lambda 实现
    static void lambda$processInstanceComponents$2(OptionalProvider optionalProvider0, Provider provider0) [...]

    // 检测为 Lambda 实现
    static void lambda$processSetComponents$3(LazySet lazySet0, Provider provider0) [...]

    static ComponentRegistrar lambda$toProviders$1(ComponentRegistrar componentRegistrar0) [...] // Inlined contents

    private void maybeInitializeEagerComponents() {
        Boolean boolean0 = (Boolean)this.eagerComponentsInitializedWith.get();
        if(boolean0 != null) {
            this.doInitializeEagerComponents(this.components, boolean0.booleanValue());
        }
    }

    private void processDependencies() {
        for(Object object0: this.components.keySet()) {
            Component component0 = (Component)object0;
            for(Object object1: component0.getDependencies()) {
                Dependency dependency0 = (Dependency)object1;
                if(dependency0.isSet() && !this.lazySetMap.containsKey(dependency0.getInterface())) {
                    LazySet lazySet0 = LazySet.fromCollection(Collections.EMPTY_SET);
                    this.lazySetMap.put(dependency0.getInterface(), lazySet0);
                }
                else if(!this.lazyInstanceMap.containsKey(dependency0.getInterface())) {
                    if(dependency0.isRequired()) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component0, dependency0.getInterface()));
                    }
                    if(!dependency0.isSet()) {
                        OptionalProvider optionalProvider0 = OptionalProvider.empty();
                        this.lazyInstanceMap.put(dependency0.getInterface(), optionalProvider0);
                    }
                }
            }
        }
    }

    private List processInstanceComponents(List list0) {
        List list1 = new ArrayList();
        for(Object object0: list0) {
            Component component0 = (Component)object0;
            if(component0.isValue()) {
                Provider provider0 = (Provider)this.components.get(component0);
                for(Object object1: component0.getProvidedInterfaces()) {
                    Qualified qualified0 = (Qualified)object1;
                    if(this.lazyInstanceMap.containsKey(qualified0)) {
                        ((ArrayList)list1).add(() -> ((OptionalProvider)(((Provider)this.lazyInstanceMap.get(qualified0)))).set(provider0));
                    }
                    else {
                        this.lazyInstanceMap.put(qualified0, provider0);
                    }
                }
            }
        }
        return list1;
    }

    private List processSetComponents() {
        List list0 = new ArrayList();
        HashMap hashMap0 = new HashMap();
        for(Object object0: this.components.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            Component component0 = (Component)map$Entry0.getKey();
            if(!component0.isValue()) {
                Provider provider0 = (Provider)map$Entry0.getValue();
                for(Object object1: component0.getProvidedInterfaces()) {
                    Qualified qualified0 = (Qualified)object1;
                    if(!hashMap0.containsKey(qualified0)) {
                        hashMap0.put(qualified0, new HashSet());
                    }
                    ((Set)hashMap0.get(qualified0)).add(provider0);
                }
            }
        }
        for(Object object2: hashMap0.entrySet()) {
            Map.Entry map$Entry1 = (Map.Entry)object2;
            Object object3 = map$Entry1.getKey();
            if(this.lazySetMap.containsKey(object3)) {
                Object object4 = map$Entry1.getKey();
                LazySet lazySet1 = (LazySet)this.lazySetMap.get(object4);
                for(Object object5: ((Set)map$Entry1.getValue())) {
                    ((ArrayList)list0).add(() -> lazySet1.add(((Provider)object5)));
                }
            }
            else {
                Qualified qualified1 = (Qualified)map$Entry1.getKey();
                LazySet lazySet0 = LazySet.fromCollection(((Collection)map$Entry1.getValue()));
                this.lazySetMap.put(qualified1, lazySet0);
            }
        }
        return list0;
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Set setOf(Qualified qualified0) {
        return ComponentContainer.-CC.$default$setOf(this, qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Set setOf(Class class0) {
        return ComponentContainer.-CC.$default$setOf(this, class0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider setOfProvider(Qualified qualified0) {
        synchronized(this) {
            Provider provider0 = (LazySet)this.lazySetMap.get(qualified0);
            return provider0 != null ? provider0 : ComponentRuntime.EMPTY_PROVIDER;
        }
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider setOfProvider(Class class0) {
        return ComponentContainer.-CC.$default$setOfProvider(this, class0);
    }

    private static Iterable toProviders(Iterable iterable0) {
        Iterable iterable1 = new ArrayList();
        for(Object object0: iterable0) {
            ((List)iterable1).add(new ComponentRuntime..ExternalSyntheticLambda0(((ComponentRegistrar)object0)));
        }
        return iterable1;
    }

    class com.google.firebase.components.ComponentRuntime.1 {
    }

}

