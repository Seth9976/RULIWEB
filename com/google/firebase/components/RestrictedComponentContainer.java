package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class RestrictedComponentContainer implements ComponentContainer {
    static class RestrictedPublisher implements Publisher {
        private final Set allowedPublishedEvents;
        private final Publisher delegate;

        public RestrictedPublisher(Set set0, Publisher publisher0) {
            this.allowedPublishedEvents = set0;
            this.delegate = publisher0;
        }

        @Override  // com.google.firebase.events.Publisher
        public void publish(Event event0) {
            if(!this.allowedPublishedEvents.contains(event0.getType())) {
                throw new DependencyException(String.format("Attempting to publish an undeclared event %s.", event0));
            }
            this.delegate.publish(event0);
        }
    }

    private final Set allowedDeferredInterfaces;
    private final Set allowedDirectInterfaces;
    private final Set allowedProviderInterfaces;
    private final Set allowedPublishedEvents;
    private final Set allowedSetDirectInterfaces;
    private final Set allowedSetProviderInterfaces;
    private final ComponentContainer delegateContainer;

    RestrictedComponentContainer(Component component0, ComponentContainer componentContainer0) {
        HashSet hashSet0 = new HashSet();
        HashSet hashSet1 = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        for(Object object0: component0.getDependencies()) {
            Dependency dependency0 = (Dependency)object0;
            if(dependency0.isDirectInjection()) {
                if(dependency0.isSet()) {
                    hashSet3.add(dependency0.getInterface());
                }
                else {
                    hashSet0.add(dependency0.getInterface());
                }
            }
            else if(dependency0.isDeferred()) {
                hashSet2.add(dependency0.getInterface());
            }
            else if(dependency0.isSet()) {
                hashSet4.add(dependency0.getInterface());
            }
            else {
                hashSet1.add(dependency0.getInterface());
            }
        }
        if(!component0.getPublishedEvents().isEmpty()) {
            hashSet0.add(Qualified.unqualified(Publisher.class));
        }
        this.allowedDirectInterfaces = Collections.unmodifiableSet(hashSet0);
        this.allowedProviderInterfaces = Collections.unmodifiableSet(hashSet1);
        this.allowedDeferredInterfaces = Collections.unmodifiableSet(hashSet2);
        this.allowedSetDirectInterfaces = Collections.unmodifiableSet(hashSet3);
        this.allowedSetProviderInterfaces = Collections.unmodifiableSet(hashSet4);
        this.allowedPublishedEvents = component0.getPublishedEvents();
        this.delegateContainer = componentContainer0;
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Object get(Qualified qualified0) {
        if(!this.allowedDirectInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", qualified0));
        }
        return this.delegateContainer.get(qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Object get(Class class0) {
        Qualified qualified0 = Qualified.unqualified(class0);
        if(!this.allowedDirectInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", class0));
        }
        Object object0 = this.delegateContainer.get(class0);
        return !class0.equals(Publisher.class) ? object0 : new RestrictedPublisher(this.allowedPublishedEvents, ((Publisher)object0));
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Deferred getDeferred(Qualified qualified0) {
        if(!this.allowedDeferredInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency Deferred<%s>.", qualified0));
        }
        return this.delegateContainer.getDeferred(qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Deferred getDeferred(Class class0) {
        return this.getDeferred(Qualified.unqualified(class0));
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider getProvider(Qualified qualified0) {
        if(!this.allowedProviderInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", qualified0));
        }
        return this.delegateContainer.getProvider(qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider getProvider(Class class0) {
        return this.getProvider(Qualified.unqualified(class0));
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Set setOf(Qualified qualified0) {
        if(!this.allowedSetDirectInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", qualified0));
        }
        return this.delegateContainer.setOf(qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Set setOf(Class class0) {
        return ComponentContainer.-CC.$default$setOf(this, class0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider setOfProvider(Qualified qualified0) {
        if(!this.allowedSetProviderInterfaces.contains(qualified0)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", qualified0));
        }
        return this.delegateContainer.setOfProvider(qualified0);
    }

    @Override  // com.google.firebase.components.ComponentContainer
    public Provider setOfProvider(Class class0) {
        return this.setOfProvider(Qualified.unqualified(class0));
    }
}

