package com.google.android.material.carousel;

import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class KeylineState {
    static final class Builder {
        private static final int NO_INDEX = -1;
        private static final float UNKNOWN_LOC = 1.401298E-45f;
        private final float availableSpace;
        private int firstFocalKeylineIndex;
        private final float itemSize;
        private int lastFocalKeylineIndex;
        private float lastKeylineMaskedSize;
        private int latestAnchorKeylineIndex;
        private Keyline tmpFirstFocalKeyline;
        private final List tmpKeylines;
        private Keyline tmpLastFocalKeyline;

        Builder(float f, float f1) {
            this.tmpKeylines = new ArrayList();
            this.firstFocalKeylineIndex = -1;
            this.lastFocalKeylineIndex = -1;
            this.lastKeylineMaskedSize = 0.0f;
            this.latestAnchorKeylineIndex = -1;
            this.itemSize = f;
            this.availableSpace = f1;
        }

        Builder addAnchorKeyline(float f, float f1, float f2) {
            return this.addKeyline(f, f1, f2, false, true);
        }

        Builder addKeyline(float f, float f1, float f2) {
            return this.addKeyline(f, f1, f2, false);
        }

        Builder addKeyline(float f, float f1, float f2, boolean z) {
            return this.addKeyline(f, f1, f2, z, false);
        }

        Builder addKeyline(float f, float f1, float f2, boolean z, boolean z1) {
            float f3 = f - f2 / 2.0f;
            float f4 = f2 / 2.0f + f;
            float f5 = this.availableSpace;
            if(f4 > f5) {
                return this.addKeyline(f, f1, f2, z, z1, Math.abs(f4 - Math.max(f4 - f2, f5)));
            }
            return f3 < 0.0f ? this.addKeyline(f, f1, f2, z, z1, Math.abs(f3 - Math.min(f3 + f2, 0.0f))) : this.addKeyline(f, f1, f2, z, z1, 0.0f);
        }

        Builder addKeyline(float f, float f1, float f2, boolean z, boolean z1, float f3) {
            return this.addKeyline(f, f1, f2, z, z1, f3, 0.0f, 0.0f);
        }

        Builder addKeyline(float f, float f1, float f2, boolean z, boolean z1, float f3, float f4, float f5) {
            if(f2 <= 0.0f) {
                return this;
            }
            if(z1) {
                if(z) {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
                if(this.latestAnchorKeylineIndex != -1 && this.latestAnchorKeylineIndex != 0) {
                    throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                }
                this.latestAnchorKeylineIndex = this.tmpKeylines.size();
            }
            Keyline keylineState$Keyline0 = new Keyline(1.401298E-45f, f, f1, f2, z1, f3, f4, f5);
            if(z) {
                if(this.tmpFirstFocalKeyline == null) {
                    this.tmpFirstFocalKeyline = keylineState$Keyline0;
                    this.firstFocalKeylineIndex = this.tmpKeylines.size();
                }
                if(this.lastFocalKeylineIndex != -1 && this.tmpKeylines.size() - this.lastFocalKeylineIndex > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                }
                if(f2 != this.tmpFirstFocalKeyline.maskedItemSize) {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
                this.tmpLastFocalKeyline = keylineState$Keyline0;
                this.lastFocalKeylineIndex = this.tmpKeylines.size();
            }
            else {
                if(this.tmpFirstFocalKeyline == null && keylineState$Keyline0.maskedItemSize < this.lastKeylineMaskedSize) {
                    throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
                }
                if(this.tmpLastFocalKeyline != null && keylineState$Keyline0.maskedItemSize > this.lastKeylineMaskedSize) {
                    throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
                }
            }
            this.lastKeylineMaskedSize = keylineState$Keyline0.maskedItemSize;
            this.tmpKeylines.add(keylineState$Keyline0);
            return this;
        }

        Builder addKeylineRange(float f, float f1, float f2, int v) {
            return this.addKeylineRange(f, f1, f2, v, false);
        }

        Builder addKeylineRange(float f, float f1, float f2, int v, boolean z) {
            if(v > 0 && f2 > 0.0f) {
                for(int v1 = 0; v1 < v; ++v1) {
                    this.addKeyline(((float)v1) * f2 + f, f1, f2, z);
                }
            }
            return this;
        }

        KeylineState build() {
            if(this.tmpFirstFocalKeyline == null) {
                throw new IllegalStateException("There must be a keyline marked as focal.");
            }
            ArrayList arrayList0 = new ArrayList();
            for(int v = 0; v < this.tmpKeylines.size(); ++v) {
                Keyline keylineState$Keyline0 = (Keyline)this.tmpKeylines.get(v);
                arrayList0.add(new Keyline(this.tmpFirstFocalKeyline.locOffset - ((float)this.firstFocalKeylineIndex) * this.itemSize + ((float)v) * this.itemSize, keylineState$Keyline0.locOffset, keylineState$Keyline0.mask, keylineState$Keyline0.maskedItemSize, keylineState$Keyline0.isAnchor, keylineState$Keyline0.cutoff, keylineState$Keyline0.leftOrTopPaddingShift, keylineState$Keyline0.rightOrBottomPaddingShift));
            }
            return new KeylineState(this.itemSize, arrayList0, this.firstFocalKeylineIndex, this.lastFocalKeylineIndex, null);
        }

        private static float calculateKeylineLocationForItemPosition(float f, float f1, int v, int v1) [...] // Inlined contents
    }

    static final class Keyline {
        final float cutoff;
        final boolean isAnchor;
        final float leftOrTopPaddingShift;
        final float loc;
        final float locOffset;
        final float mask;
        final float maskedItemSize;
        final float rightOrBottomPaddingShift;

        Keyline(float f, float f1, float f2, float f3) {
            this(f, f1, f2, f3, false, 0.0f, 0.0f, 0.0f);
        }

        Keyline(float f, float f1, float f2, float f3, boolean z, float f4, float f5, float f6) {
            this.loc = f;
            this.locOffset = f1;
            this.mask = f2;
            this.maskedItemSize = f3;
            this.isAnchor = z;
            this.cutoff = f4;
            this.leftOrTopPaddingShift = f5;
            this.rightOrBottomPaddingShift = f6;
        }

        static Keyline lerp(Keyline keylineState$Keyline0, Keyline keylineState$Keyline1, float f) {
            return new Keyline(keylineState$Keyline0.loc + f * (keylineState$Keyline1.loc - keylineState$Keyline0.loc), keylineState$Keyline0.locOffset + f * (keylineState$Keyline1.locOffset - keylineState$Keyline0.locOffset), keylineState$Keyline0.mask + f * (keylineState$Keyline1.mask - keylineState$Keyline0.mask), keylineState$Keyline0.maskedItemSize + f * (keylineState$Keyline1.maskedItemSize - keylineState$Keyline0.maskedItemSize));
        }
    }

    private final int firstFocalKeylineIndex;
    private final float itemSize;
    private final List keylines;
    private final int lastFocalKeylineIndex;

    private KeylineState(float f, List list0, int v, int v1) {
        this.itemSize = f;
        this.keylines = Collections.unmodifiableList(list0);
        this.firstFocalKeylineIndex = v;
        this.lastFocalKeylineIndex = v1;
    }

    KeylineState(float f, List list0, int v, int v1, com.google.android.material.carousel.KeylineState.1 keylineState$10) {
        this(f, list0, v, v1);
    }

    Keyline getFirstFocalKeyline() {
        return (Keyline)this.keylines.get(this.firstFocalKeylineIndex);
    }

    int getFirstFocalKeylineIndex() {
        return this.firstFocalKeylineIndex;
    }

    Keyline getFirstKeyline() {
        return (Keyline)this.keylines.get(0);
    }

    Keyline getFirstNonAnchorKeyline() {
        for(int v = 0; v < this.keylines.size(); ++v) {
            Keyline keylineState$Keyline0 = (Keyline)this.keylines.get(v);
            if(!keylineState$Keyline0.isAnchor) {
                return keylineState$Keyline0;
            }
        }
        return null;
    }

    List getFocalKeylines() {
        return this.keylines.subList(this.firstFocalKeylineIndex, this.lastFocalKeylineIndex + 1);
    }

    float getItemSize() {
        return this.itemSize;
    }

    List getKeylines() {
        return this.keylines;
    }

    Keyline getLastFocalKeyline() {
        return (Keyline)this.keylines.get(this.lastFocalKeylineIndex);
    }

    int getLastFocalKeylineIndex() {
        return this.lastFocalKeylineIndex;
    }

    Keyline getLastKeyline() {
        return (Keyline)this.keylines.get(this.keylines.size() - 1);
    }

    Keyline getLastNonAnchorKeyline() {
        for(int v = this.keylines.size() - 1; v >= 0; --v) {
            Keyline keylineState$Keyline0 = (Keyline)this.keylines.get(v);
            if(!keylineState$Keyline0.isAnchor) {
                return keylineState$Keyline0;
            }
        }
        return null;
    }

    int getNumberOfNonAnchorKeylines() {
        int v = 0;
        for(Object object0: this.keylines) {
            if(((Keyline)object0).isAnchor) {
                ++v;
            }
        }
        return this.keylines.size() - v;
    }

    static KeylineState lerp(KeylineState keylineState0, KeylineState keylineState1, float f) {
        if(keylineState0.getItemSize() != keylineState1.getItemSize()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List list0 = keylineState0.getKeylines();
        List list1 = keylineState1.getKeylines();
        if(list0.size() != list1.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList0 = new ArrayList();
        for(int v = 0; v < keylineState0.getKeylines().size(); ++v) {
            arrayList0.add(Keyline.lerp(((Keyline)list0.get(v)), ((Keyline)list1.get(v)), f));
        }
        return new KeylineState(keylineState0.getItemSize(), arrayList0, AnimationUtils.lerp(keylineState0.getFirstFocalKeylineIndex(), keylineState1.getFirstFocalKeylineIndex(), f), AnimationUtils.lerp(keylineState0.getLastFocalKeylineIndex(), keylineState1.getLastFocalKeylineIndex(), f));
    }

    static KeylineState reverse(KeylineState keylineState0, float f) {
        Builder keylineState$Builder0 = new Builder(keylineState0.getItemSize(), f);
        Keyline keylineState$Keyline0 = keylineState0.getLastKeyline();
        Keyline keylineState$Keyline1 = keylineState0.getLastKeyline();
        float f1 = f - keylineState$Keyline0.locOffset - keylineState$Keyline1.maskedItemSize / 2.0f;
        for(int v = keylineState0.getKeylines().size() - 1; v >= 0; --v) {
            Object object0 = keylineState0.getKeylines().get(v);
            keylineState$Builder0.addKeyline(((Keyline)object0).maskedItemSize / 2.0f + f1, ((Keyline)object0).mask, ((Keyline)object0).maskedItemSize, v >= keylineState0.getFirstFocalKeylineIndex() && v <= keylineState0.getLastFocalKeylineIndex(), ((Keyline)object0).isAnchor);
            f1 += ((Keyline)object0).maskedItemSize;
        }
        return keylineState$Builder0.build();
    }

    class com.google.android.material.carousel.KeylineState.1 {
    }

}

