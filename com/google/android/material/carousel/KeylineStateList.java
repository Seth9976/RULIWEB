package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    private KeylineStateList(KeylineState keylineState0, List list0, List list1) {
        this.defaultState = keylineState0;
        this.startStateSteps = Collections.unmodifiableList(list0);
        this.endStateSteps = Collections.unmodifiableList(list1);
        Keyline keylineState$Keyline0 = ((KeylineState)list0.get(list0.size() - 1)).getFirstKeyline();
        Keyline keylineState$Keyline1 = keylineState0.getFirstKeyline();
        float f = keylineState$Keyline0.loc - keylineState$Keyline1.loc;
        this.startShiftRange = f;
        Keyline keylineState$Keyline2 = keylineState0.getLastKeyline();
        Keyline keylineState$Keyline3 = ((KeylineState)list1.get(list1.size() - 1)).getLastKeyline();
        float f1 = keylineState$Keyline2.loc - keylineState$Keyline3.loc;
        this.endShiftRange = f1;
        this.startStateStepsInterpolationPoints = KeylineStateList.getStateStepInterpolationPoints(f, list0, true);
        this.endStateStepsInterpolationPoints = KeylineStateList.getStateStepInterpolationPoints(f1, list1, false);
    }

    private KeylineState closestStateStepFromInterpolation(List list0, float f, float[] arr_f) {
        float[] arr_f1 = KeylineStateList.getStateStepsRange(list0, f, arr_f);
        return arr_f1[0] >= 0.5f ? ((KeylineState)list0.get(((int)arr_f1[2]))) : ((KeylineState)list0.get(((int)arr_f1[1])));
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState keylineState0, float f) {
        for(int v = keylineState0.getLastFocalKeylineIndex(); v < keylineState0.getKeylines().size(); ++v) {
            if(f == ((Keyline)keylineState0.getKeylines().get(v)).mask) {
                return v;
            }
        }
        return keylineState0.getKeylines().size() - 1;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState keylineState0) {
        for(int v = 0; v < keylineState0.getKeylines().size(); ++v) {
            if(!((Keyline)keylineState0.getKeylines().get(v)).isAnchor) {
                return v;
            }
        }
        return -1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState keylineState0, float f) {
        for(int v = keylineState0.getFirstFocalKeylineIndex() - 1; v >= 0; --v) {
            if(f == ((Keyline)keylineState0.getKeylines().get(v)).mask) {
                return v;
            }
        }
        return 0;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState keylineState0) {
        for(int v = keylineState0.getKeylines().size() - 1; v >= 0; --v) {
            if(!((Keyline)keylineState0.getKeylines().get(v)).isAnchor) {
                return v;
            }
        }
        return -1;
    }

    static KeylineStateList from(Carousel carousel0, KeylineState keylineState0, float f, float f1, float f2) {
        return new KeylineStateList(keylineState0, KeylineStateList.getStateStepsStart(carousel0, keylineState0, f, f1), KeylineStateList.getStateStepsEnd(carousel0, keylineState0, f, f2));
    }

    KeylineState getDefaultState() {
        return this.defaultState;
    }

    KeylineState getEndState() {
        return (KeylineState)this.endStateSteps.get(this.endStateSteps.size() - 1);
    }

    Map getKeylineStateForPositionMap(int v, int v1, int v2, boolean z) {
        float f = this.defaultState.getItemSize();
        Map map0 = new HashMap();
        int v4 = 0;
        for(int v3 = 0; true; ++v3) {
            int v5 = -1;
            if(v3 >= v) {
                break;
            }
            int v6 = z ? v - v3 - 1 : v3;
            if(!z) {
                v5 = 1;
            }
            if(((float)v6) * f * ((float)v5) > ((float)v2) - this.endShiftRange || v3 >= v - this.endStateSteps.size()) {
                map0.put(v6, ((KeylineState)this.endStateSteps.get(MathUtils.clamp(v4, 0, this.endStateSteps.size() - 1))));
                ++v4;
            }
        }
        int v7 = v - 1;
        int v8 = 0;
        while(v7 >= 0) {
            int v9 = z ? v - v7 - 1 : v7;
            if(((float)v9) * f * ((float)(z ? -1 : 1)) < ((float)v1) + this.startShiftRange || v7 < this.startStateSteps.size()) {
                map0.put(v9, ((KeylineState)this.startStateSteps.get(MathUtils.clamp(v8, 0, this.startStateSteps.size() - 1))));
                ++v8;
            }
            --v7;
        }
        return map0;
    }

    public KeylineState getShiftedState(float f, float f1, float f2) {
        return this.getShiftedState(f, f1, f2, false);
    }

    KeylineState getShiftedState(float f, float f1, float f2, boolean z) {
        float f7;
        float f3 = this.startShiftRange + f1;
        float f4 = f2 - this.endShiftRange;
        float f5 = this.getStartState().getFirstFocalKeyline().leftOrTopPaddingShift;
        float f6 = this.getEndState().getLastFocalKeyline().rightOrBottomPaddingShift;
        if(this.startShiftRange == f5) {
            f3 += f5;
        }
        if(this.endShiftRange == f6) {
            f4 -= f6;
        }
        if(f < f3) {
            f7 = AnimationUtils.lerp(1.0f, 0.0f, f1, f3, f);
            return z ? this.closestStateStepFromInterpolation(this.startStateSteps, f7, this.startStateStepsInterpolationPoints) : KeylineStateList.lerp(this.startStateSteps, f7, this.startStateStepsInterpolationPoints);
        }
        if(f > f4) {
            f7 = AnimationUtils.lerp(0.0f, 1.0f, f4, f2, f);
            return z ? this.closestStateStepFromInterpolation(this.endStateSteps, f7, this.endStateStepsInterpolationPoints) : KeylineStateList.lerp(this.endStateSteps, f7, this.endStateStepsInterpolationPoints);
        }
        return this.defaultState;
    }

    KeylineState getStartState() {
        return (KeylineState)this.startStateSteps.get(this.startStateSteps.size() - 1);
    }

    private static float[] getStateStepInterpolationPoints(float f, List list0, boolean z) {
        float f1;
        int v = list0.size();
        float[] arr_f = new float[v];
        for(int v1 = 1; v1 < v; ++v1) {
            KeylineState keylineState0 = (KeylineState)list0.get(v1 - 1);
            KeylineState keylineState1 = (KeylineState)list0.get(v1);
            if(z) {
                Keyline keylineState$Keyline0 = keylineState1.getFirstKeyline();
                Keyline keylineState$Keyline1 = keylineState0.getFirstKeyline();
                f1 = keylineState$Keyline0.loc - keylineState$Keyline1.loc;
            }
            else {
                Keyline keylineState$Keyline2 = keylineState0.getLastKeyline();
                Keyline keylineState$Keyline3 = keylineState1.getLastKeyline();
                f1 = keylineState$Keyline2.loc - keylineState$Keyline3.loc;
            }
            arr_f[v1] = v1 == v - 1 ? 1.0f : arr_f[v1 - 1] + f1 / f;
        }
        return arr_f;
    }

    private static List getStateStepsEnd(Carousel carousel0, KeylineState keylineState0, float f, float f1) {
        List list0 = new ArrayList();
        list0.add(keylineState0);
        int v = KeylineStateList.findLastNonAnchorKeylineIndex(keylineState0);
        int v1 = carousel0.isHorizontal() ? carousel0.getContainerWidth() : carousel0.getContainerHeight();
        if(!KeylineStateList.isLastFocalItemVisibleAtRightOfContainer(carousel0, keylineState0) && v != -1) {
            int v2 = v - keylineState0.getLastFocalKeylineIndex();
            Keyline keylineState$Keyline0 = keylineState0.getFirstKeyline();
            Keyline keylineState$Keyline1 = keylineState0.getFirstKeyline();
            float f2 = keylineState$Keyline0.locOffset - keylineState$Keyline1.maskedItemSize / 2.0f;
            if(v2 <= 0 && keylineState0.getLastFocalKeyline().cutoff > 0.0f) {
                list0.add(KeylineStateList.shiftKeylinesAndCreateKeylineState(keylineState0, f2 - keylineState0.getLastFocalKeyline().cutoff, ((float)v1)));
                return list0;
            }
            int v3 = 0;
            for(float f3 = 0.0f; v3 < v2; f3 = f4) {
                KeylineState keylineState1 = (KeylineState)list0.get(list0.size() - 1);
                int v4 = v - v3;
                float f4 = f3 + ((Keyline)keylineState0.getKeylines().get(v4)).cutoff;
                int v5 = v4 + 1;
                KeylineState keylineState2 = KeylineStateList.moveKeylineAndCreateKeylineState(keylineState1, v, (v5 >= keylineState0.getKeylines().size() ? 0 : KeylineStateList.findLastIndexBeforeFirstFocalKeylineWithMask(keylineState1, ((Keyline)keylineState0.getKeylines().get(v5)).mask) + 1), f2 - f4, keylineState0.getFirstFocalKeylineIndex() + v3 + 1, keylineState0.getLastFocalKeylineIndex() + v3 + 1, ((float)v1));
                if(v3 == v2 - 1 && f1 > 0.0f) {
                    keylineState2 = KeylineStateList.shiftKeylineStateForPadding(keylineState2, f1, ((float)v1), false, f);
                }
                list0.add(keylineState2);
                ++v3;
            }
            return list0;
        }
        if(f1 > 0.0f) {
            list0.add(KeylineStateList.shiftKeylineStateForPadding(keylineState0, f1, ((float)v1), false, f));
        }
        return list0;
    }

    private static float[] getStateStepsRange(List list0, float f, float[] arr_f) {
        int v = list0.size();
        float f1 = arr_f[0];
        int v1 = 1;
        while(v1 < v) {
            float f2 = arr_f[v1];
            if(f <= f2) {
                return new float[]{AnimationUtils.lerp(0.0f, 1.0f, f1, f2, f), ((float)(v1 - 1)), ((float)v1)};
            }
            ++v1;
            f1 = f2;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private static List getStateStepsStart(Carousel carousel0, KeylineState keylineState0, float f, float f1) {
        List list0 = new ArrayList();
        list0.add(keylineState0);
        int v = KeylineStateList.findFirstNonAnchorKeylineIndex(keylineState0);
        int v1 = carousel0.isHorizontal() ? carousel0.getContainerWidth() : carousel0.getContainerHeight();
        if(!KeylineStateList.isFirstFocalItemAtLeftOfContainer(keylineState0) && v != -1) {
            int v2 = keylineState0.getFirstFocalKeylineIndex() - v;
            Keyline keylineState$Keyline0 = keylineState0.getFirstKeyline();
            Keyline keylineState$Keyline1 = keylineState0.getFirstKeyline();
            float f2 = keylineState$Keyline0.locOffset - keylineState$Keyline1.maskedItemSize / 2.0f;
            if(v2 <= 0 && keylineState0.getFirstFocalKeyline().cutoff > 0.0f) {
                list0.add(KeylineStateList.shiftKeylinesAndCreateKeylineState(keylineState0, f2 + keylineState0.getFirstFocalKeyline().cutoff, ((float)v1)));
                return list0;
            }
            int v3 = 0;
            for(float f3 = 0.0f; v3 < v2; f3 = f4) {
                KeylineState keylineState1 = (KeylineState)list0.get(list0.size() - 1);
                int v4 = v + v3;
                int v5 = keylineState0.getKeylines().size() - 1;
                float f4 = f3 + ((Keyline)keylineState0.getKeylines().get(v4)).cutoff;
                int v6 = v4 - 1;
                if(v6 >= 0) {
                    v5 = KeylineStateList.findFirstIndexAfterLastFocalKeylineWithMask(keylineState1, ((Keyline)keylineState0.getKeylines().get(v6)).mask) - 1;
                }
                KeylineState keylineState2 = KeylineStateList.moveKeylineAndCreateKeylineState(keylineState1, v, v5, f2 + f4, keylineState0.getFirstFocalKeylineIndex() - v3 - 1, keylineState0.getLastFocalKeylineIndex() - v3 - 1, ((float)v1));
                if(v3 == v2 - 1 && f1 > 0.0f) {
                    keylineState2 = KeylineStateList.shiftKeylineStateForPadding(keylineState2, f1, ((float)v1), true, f);
                }
                list0.add(keylineState2);
                ++v3;
            }
            return list0;
        }
        if(f1 > 0.0f) {
            list0.add(KeylineStateList.shiftKeylineStateForPadding(keylineState0, f1, ((float)v1), true, f));
        }
        return list0;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState keylineState0) {
        Keyline keylineState$Keyline0 = keylineState0.getFirstFocalKeyline();
        Keyline keylineState$Keyline1 = keylineState0.getFirstFocalKeyline();
        return keylineState$Keyline0.locOffset - keylineState$Keyline1.maskedItemSize / 2.0f >= 0.0f && keylineState0.getFirstFocalKeyline() == keylineState0.getFirstNonAnchorKeyline();
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel carousel0, KeylineState keylineState0) {
        int v = carousel0.getContainerHeight();
        if(carousel0.isHorizontal()) {
            v = carousel0.getContainerWidth();
        }
        Keyline keylineState$Keyline0 = keylineState0.getLastFocalKeyline();
        Keyline keylineState$Keyline1 = keylineState0.getLastFocalKeyline();
        return keylineState$Keyline0.locOffset + keylineState$Keyline1.maskedItemSize / 2.0f <= ((float)v) && keylineState0.getLastFocalKeyline() == keylineState0.getLastNonAnchorKeyline();
    }

    private static KeylineState lerp(List list0, float f, float[] arr_f) {
        float[] arr_f1 = KeylineStateList.getStateStepsRange(list0, f, arr_f);
        return KeylineState.lerp(((KeylineState)list0.get(((int)arr_f1[1]))), ((KeylineState)list0.get(((int)arr_f1[2]))), arr_f1[0]);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState keylineState0, int v, int v1, float f, int v2, int v3, float f1) {
        ArrayList arrayList0 = new ArrayList(keylineState0.getKeylines());
        arrayList0.add(v1, ((Keyline)arrayList0.remove(v)));
        Builder keylineState$Builder0 = new Builder(keylineState0.getItemSize(), f1);
        for(int v4 = 0; v4 < arrayList0.size(); ++v4) {
            Keyline keylineState$Keyline0 = (Keyline)arrayList0.get(v4);
            keylineState$Builder0.addKeyline(f + keylineState$Keyline0.maskedItemSize / 2.0f, keylineState$Keyline0.mask, keylineState$Keyline0.maskedItemSize, v4 >= v2 && v4 <= v3, keylineState$Keyline0.isAnchor, keylineState$Keyline0.cutoff);
            f += keylineState$Keyline0.maskedItemSize;
        }
        return keylineState$Builder0.build();
    }

    private static KeylineState shiftKeylineStateForPadding(KeylineState keylineState0, float f, float f1, boolean z, float f2) {
        ArrayList arrayList0 = new ArrayList(keylineState0.getKeylines());
        Builder keylineState$Builder0 = new Builder(keylineState0.getItemSize(), f1);
        float f3 = f / ((float)keylineState0.getNumberOfNonAnchorKeylines());
        float f4 = z ? f : 0.0f;
        for(int v = 0; v < arrayList0.size(); ++v) {
            Keyline keylineState$Keyline0 = (Keyline)arrayList0.get(v);
            if(keylineState$Keyline0.isAnchor) {
                keylineState$Builder0.addKeyline(keylineState$Keyline0.locOffset, keylineState$Keyline0.mask, keylineState$Keyline0.maskedItemSize, false, true, keylineState$Keyline0.cutoff);
            }
            else {
                float f5 = keylineState$Keyline0.maskedItemSize - f3;
                float f6 = f5 / 2.0f + f4;
                float f7 = f6 - keylineState$Keyline0.locOffset;
                keylineState$Builder0.addKeyline(f6, 1.0f - (f5 - f2) / (keylineState0.getItemSize() - f2), f5, v >= keylineState0.getFirstFocalKeylineIndex() && v <= keylineState0.getLastFocalKeylineIndex(), false, keylineState$Keyline0.cutoff, (z ? f7 : 0.0f), (z ? 0.0f : f7));
                f4 += f5;
            }
        }
        return keylineState$Builder0.build();
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState keylineState0, float f, float f1) {
        return KeylineStateList.moveKeylineAndCreateKeylineState(keylineState0, 0, 0, f, keylineState0.getFirstFocalKeylineIndex(), keylineState0.getLastFocalKeylineIndex(), f1);
    }
}

