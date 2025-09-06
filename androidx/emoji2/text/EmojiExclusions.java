package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.text.EmojiConsistency;
import java.util.Collections;
import java.util.Set;

class EmojiExclusions {
    static class EmojiExclusions_Api34 {
        static Set getExclusions() {
            return EmojiExclusions_Reflections.getExclusions();
        }
    }

    static class EmojiExclusions_Reflections {
        static Set getExclusions() {
            Set set0 = EmojiConsistency.getEmojiConsistencySet();
            if(set0 == null) {
                return Collections.EMPTY_SET;
            }
            for(Object object0: set0) {
                if(!(object0 instanceof int[])) {
                    return Collections.EMPTY_SET;
                }
                if(false) {
                    break;
                }
            }
            return set0;
        }
    }

    static Set getEmojiExclusions() {
        return Build.VERSION.SDK_INT < 34 ? EmojiExclusions_Reflections.getExclusions() : EmojiExclusions_Api34.getExclusions();
    }
}

