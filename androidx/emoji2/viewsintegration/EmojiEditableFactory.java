package androidx.emoji2.viewsintegration;

import android.text.Editable.Factory;
import android.text.Editable;
import androidx.emoji2.text.SpannableBuilder;

final class EmojiEditableFactory extends Editable.Factory {
    private static final Object INSTANCE_LOCK;
    private static volatile Editable.Factory sInstance;
    private static Class sWatcherClass;

    static {
        EmojiEditableFactory.INSTANCE_LOCK = new Object();
    }

    private EmojiEditableFactory() {
        try {
            EmojiEditableFactory.sWatcherClass = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, this.getClass().getClassLoader());
        }
        catch(Throwable unused_ex) {
        }
    }

    @Override  // android.text.Editable$Factory
    public static Editable.Factory getInstance() {
        if(EmojiEditableFactory.sInstance == null) {
            Object object0 = EmojiEditableFactory.INSTANCE_LOCK;
            synchronized(object0) {
                if(EmojiEditableFactory.sInstance == null) {
                    EmojiEditableFactory.sInstance = new EmojiEditableFactory();
                }
                return EmojiEditableFactory.sInstance;
            }
        }
        return EmojiEditableFactory.sInstance;
    }

    @Override  // android.text.Editable$Factory
    public Editable newEditable(CharSequence charSequence0) {
        Class class0 = EmojiEditableFactory.sWatcherClass;
        return class0 != null ? SpannableBuilder.create(class0, charSequence0) : super.newEditable(charSequence0);
    }
}

