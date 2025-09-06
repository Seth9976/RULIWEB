package com.google.crypto.tink.shaded.protobuf;

class GeneratedMessageInfoFactory implements MessageInfoFactory {
    private static final GeneratedMessageInfoFactory instance;

    static {
        GeneratedMessageInfoFactory.instance = new GeneratedMessageInfoFactory();
    }

    public static GeneratedMessageInfoFactory getInstance() {
        return GeneratedMessageInfoFactory.instance;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
    public boolean isSupported(Class class0) {
        return GeneratedMessageLite.class.isAssignableFrom(class0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfoFactory
    public MessageInfo messageInfoFor(Class class0) {
        if(GeneratedMessageLite.class.isAssignableFrom(class0)) {
            try {
                return (MessageInfo)GeneratedMessageLite.getDefaultInstance(class0.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
            }
            catch(Exception exception0) {
                throw new RuntimeException("Unable to get message info for " + class0.getName(), exception0);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: " + class0.getName());
    }
}

