package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.core.util.Preconditions;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class SpannableBuilder extends SpannableStringBuilder {
    static class WatcherWrapper implements SpanWatcher, TextWatcher {
        private final AtomicInteger mBlockCalls;
        final Object mObject;

        WatcherWrapper(Object object0) {
            this.mBlockCalls = new AtomicInteger(0);
            this.mObject = object0;
        }

        @Override  // android.text.TextWatcher
        public void afterTextChanged(Editable editable0) {
            ((TextWatcher)this.mObject).afterTextChanged(editable0);
        }

        @Override  // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            ((TextWatcher)this.mObject).beforeTextChanged(charSequence0, v, v1, v2);
        }

        final void blockCalls() {
            this.mBlockCalls.incrementAndGet();
        }

        private boolean isEmojiSpan(Object object0) {
            return object0 instanceof EmojiSpan;
        }

        @Override  // android.text.SpanWatcher
        public void onSpanAdded(Spannable spannable0, Object object0, int v, int v1) {
            if(this.mBlockCalls.get() > 0 && this.isEmojiSpan(object0)) {
                return;
            }
            ((SpanWatcher)this.mObject).onSpanAdded(spannable0, object0, v, v1);
        }

        @Override  // android.text.SpanWatcher
        public void onSpanChanged(Spannable spannable0, Object object0, int v, int v1, int v2, int v3) {
            int v5;
            int v4;
            if(this.mBlockCalls.get() > 0 && this.isEmojiSpan(object0)) {
                return;
            }
            if(Build.VERSION.SDK_INT < 28) {
                if(v > v1) {
                    v = 0;
                }
                if(v2 > v3) {
                    v4 = v;
                    v5 = 0;
                }
                else {
                    v4 = v;
                    v5 = v2;
                }
            }
            else {
                v4 = v;
                v5 = v2;
            }
            ((SpanWatcher)this.mObject).onSpanChanged(spannable0, object0, v4, v1, v5, v3);
        }

        @Override  // android.text.SpanWatcher
        public void onSpanRemoved(Spannable spannable0, Object object0, int v, int v1) {
            if(this.mBlockCalls.get() > 0 && this.isEmojiSpan(object0)) {
                return;
            }
            ((SpanWatcher)this.mObject).onSpanRemoved(spannable0, object0, v, v1);
        }

        @Override  // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            ((TextWatcher)this.mObject).onTextChanged(charSequence0, v, v1, v2);
        }

        final void unblockCalls() {
            this.mBlockCalls.decrementAndGet();
        }
    }

    private final Class mWatcherClass;
    private final List mWatchers;

    SpannableBuilder(Class class0) {
        this.mWatchers = new ArrayList();
        Preconditions.checkNotNull(class0, "watcherClass cannot be null");
        this.mWatcherClass = class0;
    }

    SpannableBuilder(Class class0, CharSequence charSequence0) {
        super(charSequence0);
        this.mWatchers = new ArrayList();
        Preconditions.checkNotNull(class0, "watcherClass cannot be null");
        this.mWatcherClass = class0;
    }

    SpannableBuilder(Class class0, CharSequence charSequence0, int v, int v1) {
        super(charSequence0, v, v1);
        this.mWatchers = new ArrayList();
        Preconditions.checkNotNull(class0, "watcherClass cannot be null");
        this.mWatcherClass = class0;
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable append(char c) {
        return this.append(c);
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable append(CharSequence charSequence0) {
        return this.append(charSequence0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable append(CharSequence charSequence0, int v, int v1) {
        return this.append(charSequence0, v, v1);
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(char c) {
        super.append(c);
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(CharSequence charSequence0) {
        super.append(charSequence0);
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(CharSequence charSequence0, int v, int v1) {
        super.append(charSequence0, v, v1);
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder append(CharSequence charSequence0, Object object0, int v) {
        super.append(charSequence0, object0, v);
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public Appendable append(char c) throws IOException {
        return this.append(c);
    }

    @Override  // android.text.SpannableStringBuilder
    public Appendable append(CharSequence charSequence0) throws IOException {
        return this.append(charSequence0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Appendable append(CharSequence charSequence0, int v, int v1) throws IOException {
        return this.append(charSequence0, v, v1);
    }

    public void beginBatchEdit() {
        this.blockWatchers();
    }

    private void blockWatchers() {
        for(int v = 0; v < this.mWatchers.size(); ++v) {
            ((WatcherWrapper)this.mWatchers.get(v)).blockCalls();
        }
    }

    public static SpannableBuilder create(Class class0, CharSequence charSequence0) {
        return new SpannableBuilder(class0, charSequence0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable delete(int v, int v1) {
        return this.delete(v, v1);
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder delete(int v, int v1) {
        super.delete(v, v1);
        return this;
    }

    public void endBatchEdit() {
        this.unblockwatchers();
        this.fireWatchers();
    }

    private void fireWatchers() {
        for(int v = 0; v < this.mWatchers.size(); ++v) {
            ((WatcherWrapper)this.mWatchers.get(v)).onTextChanged(this, 0, this.length(), this.length());
        }
    }

    @Override  // android.text.SpannableStringBuilder
    public int getSpanEnd(Object object0) {
        if(this.isWatcher(object0)) {
            WatcherWrapper spannableBuilder$WatcherWrapper0 = this.getWatcherFor(object0);
            if(spannableBuilder$WatcherWrapper0 != null) {
                object0 = spannableBuilder$WatcherWrapper0;
            }
        }
        return super.getSpanEnd(object0);
    }

    @Override  // android.text.SpannableStringBuilder
    public int getSpanFlags(Object object0) {
        if(this.isWatcher(object0)) {
            WatcherWrapper spannableBuilder$WatcherWrapper0 = this.getWatcherFor(object0);
            if(spannableBuilder$WatcherWrapper0 != null) {
                object0 = spannableBuilder$WatcherWrapper0;
            }
        }
        return super.getSpanFlags(object0);
    }

    @Override  // android.text.SpannableStringBuilder
    public int getSpanStart(Object object0) {
        if(this.isWatcher(object0)) {
            WatcherWrapper spannableBuilder$WatcherWrapper0 = this.getWatcherFor(object0);
            if(spannableBuilder$WatcherWrapper0 != null) {
                object0 = spannableBuilder$WatcherWrapper0;
            }
        }
        return super.getSpanStart(object0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Object[] getSpans(int v, int v1, Class class0) {
        if(this.isWatcher(class0)) {
            WatcherWrapper[] arr_spannableBuilder$WatcherWrapper = (WatcherWrapper[])super.getSpans(v, v1, WatcherWrapper.class);
            Object[] arr_object = (Object[])Array.newInstance(class0, arr_spannableBuilder$WatcherWrapper.length);
            for(int v2 = 0; v2 < arr_spannableBuilder$WatcherWrapper.length; ++v2) {
                arr_object[v2] = arr_spannableBuilder$WatcherWrapper[v2].mObject;
            }
            return arr_object;
        }
        return super.getSpans(v, v1, class0);
    }

    private WatcherWrapper getWatcherFor(Object object0) {
        for(int v = 0; v < this.mWatchers.size(); ++v) {
            WatcherWrapper spannableBuilder$WatcherWrapper0 = (WatcherWrapper)this.mWatchers.get(v);
            if(spannableBuilder$WatcherWrapper0.mObject == object0) {
                return spannableBuilder$WatcherWrapper0;
            }
        }
        return null;
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable insert(int v, CharSequence charSequence0) {
        return this.insert(v, charSequence0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable insert(int v, CharSequence charSequence0, int v1, int v2) {
        return this.insert(v, charSequence0, v1, v2);
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder insert(int v, CharSequence charSequence0) {
        super.insert(v, charSequence0);
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder insert(int v, CharSequence charSequence0, int v1, int v2) {
        super.insert(v, charSequence0, v1, v2);
        return this;
    }

    private boolean isWatcher(Class class0) {
        return this.mWatcherClass == class0;
    }

    private boolean isWatcher(Object object0) {
        return object0 != null && this.isWatcher(object0.getClass());
    }

    @Override  // android.text.SpannableStringBuilder
    public int nextSpanTransition(int v, int v1, Class class0) {
        if(class0 == null || this.isWatcher(class0)) {
            class0 = WatcherWrapper.class;
        }
        return super.nextSpanTransition(v, v1, class0);
    }

    @Override  // android.text.SpannableStringBuilder
    public void removeSpan(Object object0) {
        WatcherWrapper spannableBuilder$WatcherWrapper0;
        if(this.isWatcher(object0)) {
            spannableBuilder$WatcherWrapper0 = this.getWatcherFor(object0);
            if(spannableBuilder$WatcherWrapper0 != null) {
                object0 = spannableBuilder$WatcherWrapper0;
            }
        }
        else {
            spannableBuilder$WatcherWrapper0 = null;
        }
        super.removeSpan(object0);
        if(spannableBuilder$WatcherWrapper0 != null) {
            this.mWatchers.remove(spannableBuilder$WatcherWrapper0);
        }
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable replace(int v, int v1, CharSequence charSequence0) {
        return this.replace(v, v1, charSequence0);
    }

    @Override  // android.text.SpannableStringBuilder
    public Editable replace(int v, int v1, CharSequence charSequence0, int v2, int v3) {
        return this.replace(v, v1, charSequence0, v2, v3);
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder replace(int v, int v1, CharSequence charSequence0) {
        this.blockWatchers();
        super.replace(v, v1, charSequence0);
        this.unblockwatchers();
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public SpannableStringBuilder replace(int v, int v1, CharSequence charSequence0, int v2, int v3) {
        this.blockWatchers();
        super.replace(v, v1, charSequence0, v2, v3);
        this.unblockwatchers();
        return this;
    }

    @Override  // android.text.SpannableStringBuilder
    public void setSpan(Object object0, int v, int v1, int v2) {
        if(this.isWatcher(object0)) {
            WatcherWrapper spannableBuilder$WatcherWrapper0 = new WatcherWrapper(object0);
            this.mWatchers.add(spannableBuilder$WatcherWrapper0);
            object0 = spannableBuilder$WatcherWrapper0;
        }
        super.setSpan(object0, v, v1, v2);
    }

    @Override  // android.text.SpannableStringBuilder
    public CharSequence subSequence(int v, int v1) {
        return new SpannableBuilder(this.mWatcherClass, this, v, v1);
    }

    private void unblockwatchers() {
        for(int v = 0; v < this.mWatchers.size(); ++v) {
            ((WatcherWrapper)this.mWatchers.get(v)).unblockCalls();
        }
    }
}

