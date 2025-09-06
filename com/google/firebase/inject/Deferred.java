package com.google.firebase.inject;

public interface Deferred {
    public interface DeferredHandler {
        void handle(Provider arg1);
    }

    void whenAvailable(DeferredHandler arg1);
}

