package com.google.firebase.heartbeatinfo;

public interface HeartBeatInfo {
    public static enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        private HeartBeat(int v1) {
            this.code = v1;
        }

        public int getCode() {
            return this.code;
        }
    }

    HeartBeat getHeartBeatCode(String arg1);
}

