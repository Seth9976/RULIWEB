package com.airbnb.lottie;

import java.util.HashMap;
import java.util.Map;

public class TextDelegate {
    private final LottieAnimationView animationView;
    private boolean cacheText;
    private final LottieDrawable drawable;
    private final Map stringMap;

    TextDelegate() {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.animationView = null;
        this.drawable = null;
    }

    public TextDelegate(LottieAnimationView lottieAnimationView0) {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.animationView = lottieAnimationView0;
        this.drawable = null;
    }

    public TextDelegate(LottieDrawable lottieDrawable0) {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.drawable = lottieDrawable0;
        this.animationView = null;
    }

    private String getText(String s) [...] // Inlined contents

    public final String getTextInternal(String s) {
        if(this.cacheText && this.stringMap.containsKey(s)) {
            return (String)this.stringMap.get(s);
        }
        if(this.cacheText) {
            this.stringMap.put(s, s);
        }
        return s;
    }

    private void invalidate() {
        LottieAnimationView lottieAnimationView0 = this.animationView;
        if(lottieAnimationView0 != null) {
            lottieAnimationView0.invalidate();
        }
        LottieDrawable lottieDrawable0 = this.drawable;
        if(lottieDrawable0 != null) {
            lottieDrawable0.invalidateSelf();
        }
    }

    public void invalidateAllText() {
        this.stringMap.clear();
        this.invalidate();
    }

    public void invalidateText(String s) {
        this.stringMap.remove(s);
        this.invalidate();
    }

    public void setCacheText(boolean z) {
        this.cacheText = z;
    }

    public void setText(String s, String s1) {
        this.stringMap.put(s, s1);
        this.invalidate();
    }
}

