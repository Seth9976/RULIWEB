package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import java.util.Arrays;
import java.util.Set;

final class EmojiProcessor {
    static final class CodepointIndexFinder {
        private static final int INVALID_INDEX = -1;

        static int findIndexBackward(CharSequence charSequence0, int v, int v1) {
            boolean z;
            if(v < 0 || charSequence0.length() < v || v1 < 0) {
                return -1;
            }
            while(true) {
                z = false;
            label_3:
                if(v1 == 0) {
                    return v;
                }
                --v;
                if(v < 0) {
                    return z ? -1 : 0;
                }
                int v2 = charSequence0.charAt(v);
                if(!z) {
                    break;
                }
                if(!Character.isHighSurrogate(((char)v2))) {
                    return -1;
                }
                --v1;
            }
            if(!Character.isSurrogate(((char)v2))) {
                --v1;
                goto label_3;
            }
            if(!Character.isHighSurrogate(((char)v2))) {
                z = true;
                goto label_3;
            }
            return -1;
        }

        static int findIndexForward(CharSequence charSequence0, int v, int v1) {
            boolean z;
            int v2 = charSequence0.length();
            if(v < 0 || v2 < v || v1 < 0) {
                return -1;
            }
            while(true) {
                z = false;
            label_4:
                if(v1 == 0) {
                    return v;
                }
                if(v >= v2) {
                    return z ? -1 : v2;
                }
                int v3 = charSequence0.charAt(v);
                if(!z) {
                    break;
                }
                if(!Character.isLowSurrogate(((char)v3))) {
                    return -1;
                }
                --v1;
                ++v;
            }
            if(!Character.isSurrogate(((char)v3))) {
                --v1;
                ++v;
                goto label_4;
            }
            if(!Character.isLowSurrogate(((char)v3))) {
                ++v;
                z = true;
                goto label_4;
            }
            return -1;
        }
    }

    static class EmojiProcessAddSpanCallback implements EmojiProcessCallback {
        private final SpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable0, SpanFactory emojiCompat$SpanFactory0) {
            this.spannable = unprecomputeTextOnModificationSpannable0;
            this.mSpanFactory = emojiCompat$SpanFactory0;
        }

        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.spannable;
        }

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public Object getResult() {
            return this.getResult();
        }

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public boolean handleEmoji(CharSequence charSequence0, int v, int v1, TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
            if(typefaceEmojiRasterizer0.isPreferredSystemRender()) {
                return true;
            }
            if(this.spannable == null) {
                Spannable spannable0 = charSequence0 instanceof Spannable ? ((Spannable)charSequence0) : new SpannableString(charSequence0);
                this.spannable = new UnprecomputeTextOnModificationSpannable(spannable0);
            }
            EmojiSpan emojiSpan0 = this.mSpanFactory.createSpan(typefaceEmojiRasterizer0);
            this.spannable.setSpan(emojiSpan0, v, v1, 33);
            return true;
        }
    }

    interface EmojiProcessCallback {
        Object getResult();

        boolean handleEmoji(CharSequence arg1, int arg2, int arg3, TypefaceEmojiRasterizer arg4);
    }

    static class EmojiProcessLookupCallback implements EmojiProcessCallback {
        public int end;
        private final int mOffset;
        public int start;

        EmojiProcessLookupCallback(int v) {
            this.start = -1;
            this.end = -1;
            this.mOffset = v;
        }

        public EmojiProcessLookupCallback getResult() [...] // Inlined contents

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public Object getResult() {
            return this;
        }

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public boolean handleEmoji(CharSequence charSequence0, int v, int v1, TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
            int v2 = this.mOffset;
            if(v <= v2 && v2 < v1) {
                this.start = v;
                this.end = v1;
                return false;
            }
            return v1 <= v2;
        }
    }

    static class MarkExclusionCallback implements EmojiProcessCallback {
        private final String mExclusion;

        MarkExclusionCallback(String s) {
            this.mExclusion = s;
        }

        public MarkExclusionCallback getResult() [...] // Inlined contents

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public Object getResult() {
            return this;
        }

        @Override  // androidx.emoji2.text.EmojiProcessor$EmojiProcessCallback
        public boolean handleEmoji(CharSequence charSequence0, int v, int v1, TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
            if(TextUtils.equals(charSequence0.subSequence(v, v1), this.mExclusion)) {
                typefaceEmojiRasterizer0.setExclusion(true);
                return false;
            }
            return true;
        }
    }

    static final class ProcessorSm {
        private static final int STATE_DEFAULT = 1;
        private static final int STATE_WALKING = 2;
        private int mCurrentDepth;
        private Node mCurrentNode;
        private final int[] mEmojiAsDefaultStyleExceptions;
        private Node mFlushNode;
        private int mLastCodepoint;
        private final Node mRootNode;
        private int mState;
        private final boolean mUseEmojiAsDefaultStyle;

        ProcessorSm(Node metadataRepo$Node0, boolean z, int[] arr_v) {
            this.mState = 1;
            this.mRootNode = metadataRepo$Node0;
            this.mCurrentNode = metadataRepo$Node0;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = arr_v;
        }

        int check(int v) {
            Node metadataRepo$Node0 = this.mCurrentNode.get(v);
            int v1 = 2;
            if(this.mState == 2) {
                if(metadataRepo$Node0 != null) {
                    this.mCurrentNode = metadataRepo$Node0;
                    ++this.mCurrentDepth;
                }
                else if(ProcessorSm.isTextStyle(v)) {
                    v1 = this.reset();
                }
                else if(!ProcessorSm.isEmojiStyle(v)) {
                    v1 = 3;
                    if(this.mCurrentNode.getData() == null) {
                        v1 = this.reset();
                    }
                    else if(this.mCurrentDepth != 1) {
                        this.mFlushNode = this.mCurrentNode;
                        this.reset();
                    }
                    else if(this.shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                        this.mFlushNode = this.mCurrentNode;
                        this.reset();
                    }
                    else {
                        v1 = this.reset();
                    }
                }
            }
            else if(metadataRepo$Node0 == null) {
                v1 = this.reset();
            }
            else {
                this.mState = 2;
                this.mCurrentNode = metadataRepo$Node0;
                this.mCurrentDepth = 1;
            }
            this.mLastCodepoint = v;
            return v1;
        }

        TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.getData();
        }

        TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.getData();
        }

        private static boolean isEmojiStyle(int v) {
            return v == 0xFE0F;
        }

        // 去混淆评级： 低(20)
        boolean isInFlushableState() {
            return this.mState == 2 && this.mCurrentNode.getData() != null && (this.mCurrentDepth > 1 || this.shouldUseEmojiPresentationStyleForSingleCodepoint());
        }

        private static boolean isTextStyle(int v) {
            return v == 0xFE0E;
        }

        private int reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
            return 1;
        }

        private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            if(this.mCurrentNode.getData().isDefaultEmoji()) {
                return true;
            }
            if(ProcessorSm.isEmojiStyle(this.mLastCodepoint)) {
                return true;
            }
            if(this.mUseEmojiAsDefaultStyle) {
                if(this.mEmojiAsDefaultStyleExceptions == null) {
                    return true;
                }
                int v = this.mCurrentNode.getData().getCodepointAt(0);
                return Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, v) < 0;
            }
            return false;
        }
    }

    private static final int ACTION_ADVANCE_BOTH = 1;
    private static final int ACTION_ADVANCE_END = 2;
    private static final int ACTION_FLUSH = 3;
    private static final int MAX_LOOK_AROUND_CHARACTER = 16;
    private final int[] mEmojiAsDefaultStyleExceptions;
    private GlyphChecker mGlyphChecker;
    private final MetadataRepo mMetadataRepo;
    private final SpanFactory mSpanFactory;
    private final boolean mUseEmojiAsDefaultStyle;

    EmojiProcessor(MetadataRepo metadataRepo0, SpanFactory emojiCompat$SpanFactory0, GlyphChecker emojiCompat$GlyphChecker0, boolean z, int[] arr_v, Set set0) {
        this.mSpanFactory = emojiCompat$SpanFactory0;
        this.mMetadataRepo = metadataRepo0;
        this.mGlyphChecker = emojiCompat$GlyphChecker0;
        this.mUseEmojiAsDefaultStyle = z;
        this.mEmojiAsDefaultStyleExceptions = arr_v;
        this.initExclusions(set0);
    }

    private static boolean delete(Editable editable0, KeyEvent keyEvent0, boolean z) {
        if(EmojiProcessor.hasModifiers(keyEvent0)) {
            return false;
        }
        int v = Selection.getSelectionStart(editable0);
        int v1 = Selection.getSelectionEnd(editable0);
        if(EmojiProcessor.hasInvalidSelection(v, v1)) {
            return false;
        }
        EmojiSpan[] arr_emojiSpan = (EmojiSpan[])editable0.getSpans(v, v1, EmojiSpan.class);
        if(arr_emojiSpan != null && arr_emojiSpan.length > 0) {
            for(int v2 = 0; v2 < arr_emojiSpan.length; ++v2) {
                EmojiSpan emojiSpan0 = arr_emojiSpan[v2];
                int v3 = editable0.getSpanStart(emojiSpan0);
                int v4 = editable0.getSpanEnd(emojiSpan0);
                if(z && v3 == v || !z && v4 == v || v > v3 && v < v4) {
                    editable0.delete(v3, v4);
                    return true;
                }
            }
        }
        return false;
    }

    int getEmojiEnd(CharSequence charSequence0, int v) {
        if(v >= 0 && v < charSequence0.length()) {
            if(charSequence0 instanceof Spanned) {
                EmojiSpan[] arr_emojiSpan = (EmojiSpan[])((Spanned)charSequence0).getSpans(v, v + 1, EmojiSpan.class);
                return arr_emojiSpan.length <= 0 ? ((EmojiProcessLookupCallback)this.process(charSequence0, Math.max(0, v - 16), Math.min(charSequence0.length(), v + 16), 0x7FFFFFFF, true, new EmojiProcessLookupCallback(v))).end : ((Spanned)charSequence0).getSpanEnd(arr_emojiSpan[0]);
            }
            return ((EmojiProcessLookupCallback)this.process(charSequence0, Math.max(0, v - 16), Math.min(charSequence0.length(), v + 16), 0x7FFFFFFF, true, new EmojiProcessLookupCallback(v))).end;
        }
        return -1;
    }

    int getEmojiMatch(CharSequence charSequence0) {
        return this.getEmojiMatch(charSequence0, this.mMetadataRepo.getMetadataVersion());
    }

    int getEmojiMatch(CharSequence charSequence0, int v) {
        ProcessorSm emojiProcessor$ProcessorSm0 = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int v1 = charSequence0.length();
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        while(v2 < v1) {
            int v5 = Character.codePointAt(charSequence0, v2);
            int v6 = emojiProcessor$ProcessorSm0.check(v5);
            TypefaceEmojiRasterizer typefaceEmojiRasterizer0 = emojiProcessor$ProcessorSm0.getCurrentMetadata();
            switch(v6) {
                case 1: {
                    v2 += Character.charCount(v5);
                    v4 = 0;
                    break;
                }
                case 2: {
                    v2 += Character.charCount(v5);
                    break;
                }
                default: {
                    if(v6 == 3) {
                        typefaceEmojiRasterizer0 = emojiProcessor$ProcessorSm0.getFlushMetadata();
                        if(typefaceEmojiRasterizer0.getCompatAdded() <= v) {
                            ++v3;
                        }
                    }
                }
            }
            if(typefaceEmojiRasterizer0 != null && typefaceEmojiRasterizer0.getCompatAdded() <= v) {
                ++v4;
            }
        }
        if(v3 != 0) {
            return 2;
        }
        if(emojiProcessor$ProcessorSm0.isInFlushableState() && emojiProcessor$ProcessorSm0.getCurrentMetadata().getCompatAdded() <= v) {
            return 1;
        }
        return v4 == 0 ? 0 : 2;
    }

    int getEmojiStart(CharSequence charSequence0, int v) {
        if(v >= 0 && v < charSequence0.length()) {
            if(charSequence0 instanceof Spanned) {
                EmojiSpan[] arr_emojiSpan = (EmojiSpan[])((Spanned)charSequence0).getSpans(v, v + 1, EmojiSpan.class);
                return arr_emojiSpan.length <= 0 ? ((EmojiProcessLookupCallback)this.process(charSequence0, Math.max(0, v - 16), Math.min(charSequence0.length(), v + 16), 0x7FFFFFFF, true, new EmojiProcessLookupCallback(v))).start : ((Spanned)charSequence0).getSpanStart(arr_emojiSpan[0]);
            }
            return ((EmojiProcessLookupCallback)this.process(charSequence0, Math.max(0, v - 16), Math.min(charSequence0.length(), v + 16), 0x7FFFFFFF, true, new EmojiProcessLookupCallback(v))).start;
        }
        return -1;
    }

    static boolean handleDeleteSurroundingText(InputConnection inputConnection0, Editable editable0, int v, int v1, boolean z) {
        int v5;
        int v4;
        if(editable0 != null && inputConnection0 != null && v >= 0 && v1 >= 0) {
            int v2 = Selection.getSelectionStart(editable0);
            int v3 = Selection.getSelectionEnd(editable0);
            if(EmojiProcessor.hasInvalidSelection(v2, v3)) {
                return false;
            }
            if(z) {
                v4 = CodepointIndexFinder.findIndexBackward(editable0, v2, Math.max(v, 0));
                v5 = CodepointIndexFinder.findIndexForward(editable0, v3, Math.max(v1, 0));
                if(v4 == -1 || v5 == -1) {
                    return false;
                }
            }
            else {
                v4 = Math.max(v2 - v, 0);
                v5 = Math.min(v3 + v1, editable0.length());
            }
            EmojiSpan[] arr_emojiSpan = (EmojiSpan[])editable0.getSpans(v4, v5, EmojiSpan.class);
            if(arr_emojiSpan != null && arr_emojiSpan.length > 0) {
                for(int v6 = 0; v6 < arr_emojiSpan.length; ++v6) {
                    EmojiSpan emojiSpan0 = arr_emojiSpan[v6];
                    v4 = Math.min(editable0.getSpanStart(emojiSpan0), v4);
                    v5 = Math.max(editable0.getSpanEnd(emojiSpan0), v5);
                }
                int v7 = Math.min(v5, editable0.length());
                inputConnection0.beginBatchEdit();
                editable0.delete(Math.max(v4, 0), v7);
                inputConnection0.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    static boolean handleOnKeyDown(Editable editable0, int v, KeyEvent keyEvent0) {
        boolean z;
        switch(v) {
            case 67: {
                z = EmojiProcessor.delete(editable0, keyEvent0, false);
                break;
            }
            case 0x70: {
                z = EmojiProcessor.delete(editable0, keyEvent0, true);
                break;
            }
            default: {
                z = false;
            }
        }
        if(z) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable0);
            return true;
        }
        return false;
    }

    private boolean hasGlyph(CharSequence charSequence0, int v, int v1, TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
        if(typefaceEmojiRasterizer0.getHasGlyph() == 0) {
            typefaceEmojiRasterizer0.setHasGlyph(this.mGlyphChecker.hasGlyph(charSequence0, v, v1, typefaceEmojiRasterizer0.getSdkAdded()));
        }
        return typefaceEmojiRasterizer0.getHasGlyph() == 2;
    }

    private static boolean hasInvalidSelection(int v, int v1) {
        return v == -1 || v1 == -1 || v != v1;
    }

    private static boolean hasModifiers(KeyEvent keyEvent0) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent0.getMetaState());
    }

    private void initExclusions(Set set0) {
        if(!set0.isEmpty()) {
            for(Object object0: set0) {
                String s = new String(((int[])object0), 0, ((int[])object0).length);
                this.process(s, 0, s.length(), 1, true, new MarkExclusionCallback(s));
            }
        }
    }

    private Object process(CharSequence charSequence0, int v, int v1, int v2, boolean z, EmojiProcessCallback emojiProcessor$EmojiProcessCallback0) {
        int v5;
        ProcessorSm emojiProcessor$ProcessorSm0 = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int v3 = Character.codePointAt(charSequence0, v);
        int v4 = 0;
        boolean z1 = true;
    alab1:
        while(true) {
            v5 = v;
            while(true) {
                if(v >= v1 || v4 >= v2 || !z1) {
                    break alab1;
                }
                switch(emojiProcessor$ProcessorSm0.check(v3)) {
                    case 1: {
                        v5 += Character.charCount(Character.codePointAt(charSequence0, v5));
                        if(v5 < v1) {
                            v3 = Character.codePointAt(charSequence0, v5);
                        }
                        v = v5;
                        continue;
                    }
                    case 2: {
                        v += Character.charCount(v3);
                        if(v >= v1) {
                            continue;
                        }
                        v3 = Character.codePointAt(charSequence0, v);
                        continue;
                    }
                    case 3: {
                        break;
                    }
                    default: {
                        continue;
                    }
                }
                if(!z && this.hasGlyph(charSequence0, v5, v, emojiProcessor$ProcessorSm0.getFlushMetadata())) {
                    break;
                }
                ++v4;
                z1 = emojiProcessor$EmojiProcessCallback0.handleEmoji(charSequence0, v5, v, emojiProcessor$ProcessorSm0.getFlushMetadata());
                break;
            }
        }
        if(emojiProcessor$ProcessorSm0.isInFlushableState() && v4 < v2 && z1 && (z || !this.hasGlyph(charSequence0, v5, v, emojiProcessor$ProcessorSm0.getCurrentMetadata()))) {
            emojiProcessor$EmojiProcessCallback0.handleEmoji(charSequence0, v5, v, emojiProcessor$ProcessorSm0.getCurrentMetadata());
        }
        return emojiProcessor$EmojiProcessCallback0.getResult();
    }

    CharSequence process(CharSequence charSequence0, int v, int v1, int v2, boolean z) {
        Throwable throwable2;
        CharSequence charSequence1;
        UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable0;
        if(charSequence0 instanceof SpannableBuilder) {
            ((SpannableBuilder)charSequence0).beginBatchEdit();
        }
        if(charSequence0 instanceof SpannableBuilder) {
            goto label_11;
        }
        else {
            try {
                if(charSequence0 instanceof Spannable) {
                    unprecomputeTextOnModificationSpannable0 = new UnprecomputeTextOnModificationSpannable(((Spannable)charSequence0));
                }
                else if(charSequence0 instanceof Spanned && ((Spanned)charSequence0).nextSpanTransition(v - 1, v1 + 1, EmojiSpan.class) <= v1) {
                    unprecomputeTextOnModificationSpannable0 = new UnprecomputeTextOnModificationSpannable(charSequence0);
                }
                else {
                    unprecomputeTextOnModificationSpannable0 = null;
                }
                goto label_12;
            }
            catch(Throwable throwable0) {
                goto label_32;
            }
            try {
                unprecomputeTextOnModificationSpannable0 = new UnprecomputeTextOnModificationSpannable(((Spannable)charSequence0));
                goto label_12;
            label_11:
                unprecomputeTextOnModificationSpannable0 = new UnprecomputeTextOnModificationSpannable(((Spannable)charSequence0));
            }
            catch(Throwable throwable1) {
                goto label_39;
            }
        }
    label_12:
        if(unprecomputeTextOnModificationSpannable0 != null) {
            try {
                EmojiSpan[] arr_emojiSpan = (EmojiSpan[])unprecomputeTextOnModificationSpannable0.getSpans(v, v1, EmojiSpan.class);
                if(arr_emojiSpan != null && arr_emojiSpan.length > 0) {
                    for(int v3 = 0; v3 < arr_emojiSpan.length; ++v3) {
                        EmojiSpan emojiSpan0 = arr_emojiSpan[v3];
                        int v4 = unprecomputeTextOnModificationSpannable0.getSpanStart(emojiSpan0);
                        int v5 = unprecomputeTextOnModificationSpannable0.getSpanEnd(emojiSpan0);
                        if(v4 != v1) {
                            unprecomputeTextOnModificationSpannable0.removeSpan(emojiSpan0);
                        }
                        v = Math.min(v4, v);
                        v1 = Math.max(v5, v1);
                    }
                }
            }
            catch(Throwable throwable0) {
                goto label_32;
            }
        }
        if(v == v1) {
        label_48:
            if(!(charSequence0 instanceof SpannableBuilder)) {
                return charSequence0;
            }
        }
        else {
            try {
                if(v < charSequence0.length()) {
                    goto label_28;
                }
                else {
                    goto label_48;
                }
                goto label_50;
            }
            catch(Throwable throwable1) {
                goto label_39;
            }
        label_28:
            if(v2 == 0x7FFFFFFF || unprecomputeTextOnModificationSpannable0 == null) {
                try {
                label_34:
                    UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable1 = (UnprecomputeTextOnModificationSpannable)this.process(charSequence0, v, v1, v2, z, new EmojiProcessAddSpanCallback(unprecomputeTextOnModificationSpannable0, this.mSpanFactory));
                    if(unprecomputeTextOnModificationSpannable1 != null) {
                        charSequence1 = unprecomputeTextOnModificationSpannable1.getUnwrappedSpannable();
                        goto label_43;
                    }
                    goto label_46;
                }
                catch(Throwable throwable1) {
                label_39:
                    throwable2 = throwable1;
                }
            }
            else {
                try {
                    v2 -= ((EmojiSpan[])unprecomputeTextOnModificationSpannable0.getSpans(0, unprecomputeTextOnModificationSpannable0.length(), EmojiSpan.class)).length;
                    goto label_34;
                }
                catch(Throwable throwable0) {
                label_32:
                    throwable2 = throwable0;
                }
            }
            if(charSequence0 instanceof SpannableBuilder) {
                ((SpannableBuilder)charSequence0).endBatchEdit();
            }
            throw throwable2;
        label_43:
            if(charSequence0 instanceof SpannableBuilder) {
                ((SpannableBuilder)charSequence0).endBatchEdit();
            }
            return charSequence1;
        label_46:
            if(!(charSequence0 instanceof SpannableBuilder)) {
                return charSequence0;
            }
        }
    label_50:
        ((SpannableBuilder)charSequence0).endBatchEdit();
        return charSequence0;
    }
}

