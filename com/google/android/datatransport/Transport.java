package com.google.android.datatransport;

public interface Transport {
    void schedule(Event arg1, TransportScheduleCallback arg2);

    void send(Event arg1);
}

