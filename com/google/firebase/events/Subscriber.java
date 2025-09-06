package com.google.firebase.events;

import java.util.concurrent.Executor;

public interface Subscriber {
    void subscribe(Class arg1, EventHandler arg2);

    void subscribe(Class arg1, Executor arg2, EventHandler arg3);

    void unsubscribe(Class arg1, EventHandler arg2);
}

