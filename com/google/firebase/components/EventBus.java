package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class EventBus implements Publisher, Subscriber {
    private final Executor defaultExecutor;
    private final Map handlerMap;
    private Queue pendingEvents;

    EventBus(Executor executor0) {
        this.handlerMap = new HashMap();
        this.pendingEvents = new ArrayDeque();
        this.defaultExecutor = executor0;
    }

    void enablePublishingAndFlushPending() {
        synchronized(this) {
            Queue queue0 = this.pendingEvents;
            if(queue0 == null) {
                queue0 = null;
            }
            else {
                this.pendingEvents = null;
            }
        }
        if(queue0 != null) {
            for(Object object0: queue0) {
                this.publish(((Event)object0));
            }
        }
    }

    private Set getHandlers(Event event0) {
        synchronized(this) {
            Map map0 = (Map)this.handlerMap.get(event0.getType());
            return map0 == null ? Collections.EMPTY_SET : map0.entrySet();
        }
    }

    // 检测为 Lambda 实现
    static void lambda$publish$0(Map.Entry map$Entry0, Event event0) [...]

    @Override  // com.google.firebase.events.Publisher
    public void publish(Event event0) {
        Preconditions.checkNotNull(event0);
        synchronized(this) {
            Queue queue0 = this.pendingEvents;
            if(queue0 != null) {
                queue0.add(event0);
                return;
            }
        }
        for(Object object0: this.getHandlers(event0)) {
            ((Executor)((Map.Entry)object0).getValue()).execute(() -> ((EventHandler)((Map.Entry)object0).getKey()).handle(event0));
        }
    }

    @Override  // com.google.firebase.events.Subscriber
    public void subscribe(Class class0, EventHandler eventHandler0) {
        this.subscribe(class0, this.defaultExecutor, eventHandler0);
    }

    @Override  // com.google.firebase.events.Subscriber
    public void subscribe(Class class0, Executor executor0, EventHandler eventHandler0) {
        synchronized(this) {
            Preconditions.checkNotNull(class0);
            Preconditions.checkNotNull(eventHandler0);
            Preconditions.checkNotNull(executor0);
            if(!this.handlerMap.containsKey(class0)) {
                ConcurrentHashMap concurrentHashMap0 = new ConcurrentHashMap();
                this.handlerMap.put(class0, concurrentHashMap0);
            }
            ((ConcurrentHashMap)this.handlerMap.get(class0)).put(eventHandler0, executor0);
        }
    }

    @Override  // com.google.firebase.events.Subscriber
    public void unsubscribe(Class class0, EventHandler eventHandler0) {
        synchronized(this) {
            Preconditions.checkNotNull(class0);
            Preconditions.checkNotNull(eventHandler0);
            if(!this.handlerMap.containsKey(class0)) {
                return;
            }
            ConcurrentHashMap concurrentHashMap0 = (ConcurrentHashMap)this.handlerMap.get(class0);
            concurrentHashMap0.remove(eventHandler0);
            if(concurrentHashMap0.isEmpty()) {
                this.handlerMap.remove(class0);
            }
        }
    }
}

