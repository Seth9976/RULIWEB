package com.google.android.datatransport;

public abstract class Event {
    public abstract Integer getCode();

    public abstract Object getPayload();

    public abstract Priority getPriority();

    public static Event ofData(int v, Object object0) {
        return new AutoValue_Event(v, object0, Priority.DEFAULT);
    }

    public static Event ofData(Object object0) {
        return new AutoValue_Event(null, object0, Priority.DEFAULT);
    }

    public static Event ofTelemetry(int v, Object object0) {
        return new AutoValue_Event(v, object0, Priority.VERY_LOW);
    }

    public static Event ofTelemetry(Object object0) {
        return new AutoValue_Event(null, object0, Priority.VERY_LOW);
    }

    public static Event ofUrgent(int v, Object object0) {
        return new AutoValue_Event(v, object0, Priority.HIGHEST);
    }

    public static Event ofUrgent(Object object0) {
        return new AutoValue_Event(null, object0, Priority.HIGHEST);
    }
}

