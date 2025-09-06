package com.google.firebase.messaging;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import java.util.regex.Pattern;

final class TopicOperation {
    private static final String OLD_TOPIC_PREFIX = "/topics/";
    static final String OPERATION_PAIR_DIVIDER = "!";
    private static final String TOPIC_NAME_PATTERN = "[a-zA-Z0-9-_.~%]{1,900}";
    private static final Pattern TOPIC_NAME_REGEXP;
    private final String operation;
    private final String serializedString;
    private final String topic;

    static {
        TopicOperation.TOPIC_NAME_REGEXP = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    }

    private TopicOperation(String s, String s1) {
        this.topic = TopicOperation.normalizeTopicOrThrow(s1, s);
        this.operation = s;
        this.serializedString = s + "!" + s1;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TopicOperation ? this.topic.equals(((TopicOperation)object0).topic) && this.operation.equals(((TopicOperation)object0).operation) : false;
    }

    static TopicOperation from(String s) {
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        String[] arr_s = s.split("!", -1);
        return arr_s.length == 2 ? new TopicOperation(arr_s[0], arr_s[1]) : null;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getTopic() {
        return this.topic;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.operation, this.topic});
    }

    private static String normalizeTopicOrThrow(String s, String s1) {
        if(s != null && s.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", String.format("Format /topics/topic-name is deprecated. Only \'topic-name\' should be used in %s.", s1));
            s = s.substring(8);
        }
        if(s == null || !TopicOperation.TOPIC_NAME_REGEXP.matcher(s).matches()) {
            throw new IllegalArgumentException(String.format("Invalid topic name: %s does not match the allowed format %s.", s, "[a-zA-Z0-9-_.~%]{1,900}"));
        }
        return s;
    }

    public String serialize() {
        return this.serializedString;
    }

    public static TopicOperation subscribe(String s) {
        return new TopicOperation("S", s);
    }

    public static TopicOperation unsubscribe(String s) {
        return new TopicOperation("U", s);
    }
}

