package com.google.firebase.analytics.connector;

import android.os.Bundle;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AnalyticsConnector {
    public interface AnalyticsConnectorHandle {
        void registerEventNames(Set arg1);

        void unregister();

        void unregisterEventNames();
    }

    public interface AnalyticsConnectorListener {
        void onMessageTriggered(int arg1, Bundle arg2);
    }

    public static class ConditionalUserProperty {
        public boolean active;
        public long creationTimestamp;
        public String expiredEventName;
        public Bundle expiredEventParams;
        public String name;
        public String origin;
        public long timeToLive;
        public String timedOutEventName;
        public Bundle timedOutEventParams;
        public String triggerEventName;
        public long triggerTimeout;
        public String triggeredEventName;
        public Bundle triggeredEventParams;
        public long triggeredTimestamp;
        public Object value;

    }

    void clearConditionalUserProperty(String arg1, String arg2, Bundle arg3);

    List getConditionalUserProperties(String arg1, String arg2);

    int getMaxUserProperties(String arg1);

    Map getUserProperties(boolean arg1);

    void logEvent(String arg1, String arg2, Bundle arg3);

    AnalyticsConnectorHandle registerAnalyticsConnectorListener(String arg1, AnalyticsConnectorListener arg2);

    void setConditionalUserProperty(ConditionalUserProperty arg1);

    void setUserProperty(String arg1, String arg2, Object arg3);
}

