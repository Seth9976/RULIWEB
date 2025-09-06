package com.google.firebase.messaging.reporting;

import com.google.firebase.encoders.annotations.Encodable.Field;
import com.google.firebase.encoders.annotations.Encodable.Ignore;
import com.google.firebase.messaging.ProtoEncoderDoNotUse;
import java.io.IOException;
import java.io.OutputStream;

public final class MessagingClientEventExtension {
    public static final class Builder {
        private MessagingClientEvent messaging_client_event_;

        Builder() {
            this.messaging_client_event_ = null;
        }

        public MessagingClientEventExtension build() {
            return new MessagingClientEventExtension(this.messaging_client_event_);
        }

        public Builder setMessagingClientEvent(MessagingClientEvent messagingClientEvent0) {
            this.messaging_client_event_ = messagingClientEvent0;
            return this;
        }
    }

    private static final MessagingClientEventExtension DEFAULT_INSTANCE;
    private final MessagingClientEvent messaging_client_event_;

    static {
        MessagingClientEventExtension.DEFAULT_INSTANCE = new Builder().build();
    }

    MessagingClientEventExtension(MessagingClientEvent messagingClientEvent0) {
        this.messaging_client_event_ = messagingClientEvent0;
    }

    public static MessagingClientEventExtension getDefaultInstance() {
        return MessagingClientEventExtension.DEFAULT_INSTANCE;
    }

    @Ignore
    public MessagingClientEvent getMessagingClientEvent() {
        return this.messaging_client_event_ == null ? MessagingClientEvent.getDefaultInstance() : this.messaging_client_event_;
    }

    @Field(name = "messagingClientEvent")
    public MessagingClientEvent getMessagingClientEventInternal() {
        return this.messaging_client_event_;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] toByteArray() [...] // 潜在的解密器

    public void writeTo(OutputStream outputStream0) throws IOException {
        ProtoEncoderDoNotUse.encode(this, outputStream0);
    }
}

