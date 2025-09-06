package com.google.firebase.events;

import com.google.firebase.components.Preconditions;

public class Event {
    private final Object payload;
    private final Class type;

    public Event(Class class0, Object object0) {
        this.type = (Class)Preconditions.checkNotNull(class0);
        this.payload = Preconditions.checkNotNull(object0);
    }

    public Object getPayload() {
        return this.payload;
    }

    public Class getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.type, this.payload);
    }
}

