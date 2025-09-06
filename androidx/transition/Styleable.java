package androidx.transition;

class Styleable {
    interface ArcMotion {
        public static final int MAXIMUM_ANGLE = 2;
        public static final int MINIMUM_HORIZONTAL_ANGLE = 0;
        public static final int MINIMUM_VERTICAL_ANGLE = 1;

    }

    interface ChangeBounds {
        public static final int RESIZE_CLIP;

    }

    interface ChangeTransform {
        public static final int REPARENT = 0;
        public static final int REPARENT_WITH_OVERLAY = 1;

    }

    interface Fade {
        public static final int FADING_MODE;

    }

    interface PatternPathMotion {
        public static final int PATTERN_PATH_DATA;

    }

    interface Slide {
        public static final int SLIDE_EDGE;

    }

    interface Transition {
        public static final int DURATION = 1;
        public static final int INTERPOLATOR = 0;
        public static final int MATCH_ORDER = 3;
        public static final int START_DELAY = 2;

    }

    interface TransitionManager {
        public static final int FROM_SCENE = 0;
        public static final int TO_SCENE = 1;
        public static final int TRANSITION = 2;

    }

    interface TransitionSet {
        public static final int TRANSITION_ORDERING;

    }

    interface TransitionTarget {
        public static final int EXCLUDE_CLASS = 3;
        public static final int EXCLUDE_ID = 2;
        public static final int EXCLUDE_NAME = 5;
        public static final int TARGET_CLASS = 0;
        public static final int TARGET_ID = 1;
        public static final int TARGET_NAME = 4;

    }

    interface VisibilityTransition {
        public static final int TRANSITION_VISIBILITY_MODE;

    }

    static final int[] ARC_MOTION;
    static final int[] CHANGE_BOUNDS;
    static final int[] CHANGE_TRANSFORM;
    static final int[] FADE;
    static final int[] PATTERN_PATH_MOTION;
    static final int[] SLIDE;
    static final int[] TRANSITION;
    static final int[] TRANSITION_MANAGER;
    static final int[] TRANSITION_SET;
    static final int[] TRANSITION_TARGET;
    static final int[] VISIBILITY_TRANSITION;

    static {
        Styleable.TRANSITION_TARGET = new int[]{0x101002F, 0x10103DC, 0x1010441, 0x1010442, 0x101044D, 0x101044E};
        Styleable.TRANSITION_MANAGER = new int[]{0x10103DD, 0x10103DE, 0x10103DF};
        Styleable.TRANSITION = new int[]{0x1010141, 0x1010198, 0x10103E2, 0x101044F};
        Styleable.CHANGE_BOUNDS = new int[]{0x10104CF};
        Styleable.VISIBILITY_TRANSITION = new int[]{0x101047C};
        Styleable.FADE = new int[]{0x10103E1};
        Styleable.CHANGE_TRANSFORM = new int[]{0x10104BC, 0x10104BD};
        Styleable.SLIDE = new int[]{0x1010430};
        Styleable.TRANSITION_SET = new int[]{0x10103E0};
        Styleable.ARC_MOTION = new int[]{0x101047D, 0x101047E, 0x101047F};
        Styleable.PATTERN_PATH_MOTION = new int[]{0x10104CA};
    }
}

