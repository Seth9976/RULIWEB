package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

public interface ComponentContainer {
    Object get(Qualified arg1);

    Object get(Class arg1);

    Deferred getDeferred(Qualified arg1);

    Deferred getDeferred(Class arg1);

    Provider getProvider(Qualified arg1);

    Provider getProvider(Class arg1);

    Set setOf(Qualified arg1);

    Set setOf(Class arg1);

    Provider setOfProvider(Qualified arg1);

    Provider setOfProvider(Class arg1);
}

