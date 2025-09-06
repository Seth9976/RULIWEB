package com.google.firebase.messaging.reporting;

import com.google.firebase.encoders.proto.ProtoEnum;

public final class MessagingClientEvent {
    public static final class Builder {
        private String analytics_label_;
        private long bulk_id_;
        private long campaign_id_;
        private String collapse_key_;
        private String composer_label_;
        private Event event_;
        private String instance_id_;
        private String message_id_;
        private MessageType message_type_;
        private String package_name_;
        private int priority_;
        private long project_number_;
        private SDKPlatform sdk_platform_;
        private String topic_;
        private int ttl_;

        Builder() {
            this.project_number_ = 0L;
            this.message_id_ = "";
            this.instance_id_ = "";
            this.message_type_ = MessageType.UNKNOWN;
            this.sdk_platform_ = SDKPlatform.UNKNOWN_OS;
            this.package_name_ = "";
            this.collapse_key_ = "";
            this.priority_ = 0;
            this.ttl_ = 0;
            this.topic_ = "";
            this.bulk_id_ = 0L;
            this.event_ = Event.UNKNOWN_EVENT;
            this.analytics_label_ = "";
            this.campaign_id_ = 0L;
            this.composer_label_ = "";
        }

        public MessagingClientEvent build() {
            return new MessagingClientEvent(this.project_number_, this.message_id_, this.instance_id_, this.message_type_, this.sdk_platform_, this.package_name_, this.collapse_key_, this.priority_, this.ttl_, this.topic_, this.bulk_id_, this.event_, this.analytics_label_, this.campaign_id_, this.composer_label_);
        }

        public Builder setAnalyticsLabel(String s) {
            this.analytics_label_ = s;
            return this;
        }

        public Builder setBulkId(long v) {
            this.bulk_id_ = v;
            return this;
        }

        public Builder setCampaignId(long v) {
            this.campaign_id_ = v;
            return this;
        }

        public Builder setCollapseKey(String s) {
            this.collapse_key_ = s;
            return this;
        }

        public Builder setComposerLabel(String s) {
            this.composer_label_ = s;
            return this;
        }

        public Builder setEvent(Event messagingClientEvent$Event0) {
            this.event_ = messagingClientEvent$Event0;
            return this;
        }

        public Builder setInstanceId(String s) {
            this.instance_id_ = s;
            return this;
        }

        public Builder setMessageId(String s) {
            this.message_id_ = s;
            return this;
        }

        public Builder setMessageType(MessageType messagingClientEvent$MessageType0) {
            this.message_type_ = messagingClientEvent$MessageType0;
            return this;
        }

        public Builder setPackageName(String s) {
            this.package_name_ = s;
            return this;
        }

        public Builder setPriority(int v) {
            this.priority_ = v;
            return this;
        }

        public Builder setProjectNumber(long v) {
            this.project_number_ = v;
            return this;
        }

        public Builder setSdkPlatform(SDKPlatform messagingClientEvent$SDKPlatform0) {
            this.sdk_platform_ = messagingClientEvent$SDKPlatform0;
            return this;
        }

        public Builder setTopic(String s) {
            this.topic_ = s;
            return this;
        }

        public Builder setTtl(int v) {
            this.ttl_ = v;
            return this;
        }
    }

    public static enum Event implements ProtoEnum {
        UNKNOWN_EVENT(0),
        MESSAGE_DELIVERED(1),
        MESSAGE_OPEN(2);

        private final int number_;

        private Event(int v1) {
            this.number_ = v1;
        }

        @Override  // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public static enum MessageType implements ProtoEnum {
        UNKNOWN(0),
        DATA_MESSAGE(1),
        TOPIC(2),
        DISPLAY_NOTIFICATION(3);

        private final int number_;

        private MessageType(int v1) {
            this.number_ = v1;
        }

        @Override  // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    public static enum SDKPlatform implements ProtoEnum {
        UNKNOWN_OS(0),
        ANDROID(1),
        IOS(2),
        WEB(3);

        private final int number_;

        private SDKPlatform(int v1) {
            this.number_ = v1;
        }

        @Override  // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    private static final MessagingClientEvent DEFAULT_INSTANCE;
    private final String analytics_label_;
    private final long bulk_id_;
    private final long campaign_id_;
    private final String collapse_key_;
    private final String composer_label_;
    private final Event event_;
    private final String instance_id_;
    private final String message_id_;
    private final MessageType message_type_;
    private final String package_name_;
    private final int priority_;
    private final long project_number_;
    private final SDKPlatform sdk_platform_;
    private final String topic_;
    private final int ttl_;

    static {
        MessagingClientEvent.DEFAULT_INSTANCE = new Builder().build();
    }

    MessagingClientEvent(long v, String s, String s1, MessageType messagingClientEvent$MessageType0, SDKPlatform messagingClientEvent$SDKPlatform0, String s2, String s3, int v1, int v2, String s4, long v3, Event messagingClientEvent$Event0, String s5, long v4, String s6) {
        this.project_number_ = v;
        this.message_id_ = s;
        this.instance_id_ = s1;
        this.message_type_ = messagingClientEvent$MessageType0;
        this.sdk_platform_ = messagingClientEvent$SDKPlatform0;
        this.package_name_ = s2;
        this.collapse_key_ = s3;
        this.priority_ = v1;
        this.ttl_ = v2;
        this.topic_ = s4;
        this.bulk_id_ = v3;
        this.event_ = messagingClientEvent$Event0;
        this.analytics_label_ = s5;
        this.campaign_id_ = v4;
        this.composer_label_ = s6;
    }

    public String getAnalyticsLabel() {
        return this.analytics_label_;
    }

    public long getBulkId() {
        return this.bulk_id_;
    }

    public long getCampaignId() {
        return this.campaign_id_;
    }

    public String getCollapseKey() {
        return this.collapse_key_;
    }

    public String getComposerLabel() {
        return this.composer_label_;
    }

    public static MessagingClientEvent getDefaultInstance() {
        return MessagingClientEvent.DEFAULT_INSTANCE;
    }

    public Event getEvent() {
        return this.event_;
    }

    public String getInstanceId() {
        return this.instance_id_;
    }

    public String getMessageId() {
        return this.message_id_;
    }

    public MessageType getMessageType() {
        return this.message_type_;
    }

    public String getPackageName() {
        return this.package_name_;
    }

    public int getPriority() {
        return this.priority_;
    }

    public long getProjectNumber() {
        return this.project_number_;
    }

    public SDKPlatform getSdkPlatform() {
        return this.sdk_platform_;
    }

    public String getTopic() {
        return this.topic_;
    }

    public int getTtl() {
        return this.ttl_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

