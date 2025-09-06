package com.google.android.material.snackbar;

import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class SnackbarManager {
    interface Callback {
        void dismiss(int arg1);

        void show();
    }

    static class SnackbarRecord {
        final WeakReference callback;
        int duration;
        boolean paused;

        SnackbarRecord(int v, Callback snackbarManager$Callback0) {
            this.callback = new WeakReference(snackbarManager$Callback0);
            this.duration = v;
        }

        boolean isSnackbar(Callback snackbarManager$Callback0) {
            return snackbarManager$Callback0 != null && this.callback.get() == snackbarManager$Callback0;
        }
    }

    private static final int LONG_DURATION_MS = 2750;
    static final int MSG_TIMEOUT = 0;
    private static final int SHORT_DURATION_MS = 1500;
    private SnackbarRecord currentSnackbar;
    private final Handler handler;
    private final Object lock;
    private SnackbarRecord nextSnackbar;
    private static SnackbarManager snackbarManager;

    private SnackbarManager() {
        this.lock = new Object();
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override  // android.os.Handler$Callback
            public boolean handleMessage(Message message0) {
                if(message0.what != 0) {
                    return false;
                }
                SnackbarManager.this.handleTimeout(((SnackbarRecord)message0.obj));
                return true;
            }
        });
    }

    private boolean cancelSnackbarLocked(SnackbarRecord snackbarManager$SnackbarRecord0, int v) {
        Callback snackbarManager$Callback0 = (Callback)snackbarManager$SnackbarRecord0.callback.get();
        if(snackbarManager$Callback0 != null) {
            this.handler.removeCallbacksAndMessages(snackbarManager$SnackbarRecord0);
            snackbarManager$Callback0.dismiss(v);
            return true;
        }
        return false;
    }

    public void dismiss(Callback snackbarManager$Callback0, int v) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0)) {
                this.cancelSnackbarLocked(this.currentSnackbar, v);
            }
            else if(this.isNextSnackbarLocked(snackbarManager$Callback0)) {
                this.cancelSnackbarLocked(this.nextSnackbar, v);
            }
        }
    }

    static SnackbarManager getInstance() {
        if(SnackbarManager.snackbarManager == null) {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        return SnackbarManager.snackbarManager;
    }

    void handleTimeout(SnackbarRecord snackbarManager$SnackbarRecord0) {
        synchronized(this.lock) {
            if(this.currentSnackbar == snackbarManager$SnackbarRecord0 || this.nextSnackbar == snackbarManager$SnackbarRecord0) {
                this.cancelSnackbarLocked(snackbarManager$SnackbarRecord0, 2);
            }
        }
    }

    public boolean isCurrent(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
        }
        return this.isCurrentSnackbarLocked(snackbarManager$Callback0);
    }

    public boolean isCurrentOrNext(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            return this.isCurrentSnackbarLocked(snackbarManager$Callback0) || this.isNextSnackbarLocked(snackbarManager$Callback0);
        }
    }

    private boolean isCurrentSnackbarLocked(Callback snackbarManager$Callback0) {
        return this.currentSnackbar != null && this.currentSnackbar.isSnackbar(snackbarManager$Callback0);
    }

    private boolean isNextSnackbarLocked(Callback snackbarManager$Callback0) {
        return this.nextSnackbar != null && this.nextSnackbar.isSnackbar(snackbarManager$Callback0);
    }

    public void onDismissed(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0)) {
                this.currentSnackbar = null;
                if(this.nextSnackbar != null) {
                    this.showNextSnackbarLocked();
                }
            }
        }
    }

    public void onShown(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0)) {
                this.scheduleTimeoutLocked(this.currentSnackbar);
            }
        }
    }

    public void pauseTimeout(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0) && !this.currentSnackbar.paused) {
                this.currentSnackbar.paused = true;
                this.handler.removeCallbacksAndMessages(this.currentSnackbar);
            }
        }
    }

    public void restoreTimeoutIfPaused(Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0) && this.currentSnackbar.paused) {
                this.currentSnackbar.paused = false;
                this.scheduleTimeoutLocked(this.currentSnackbar);
            }
        }
    }

    private void scheduleTimeoutLocked(SnackbarRecord snackbarManager$SnackbarRecord0) {
        int v;
        if(snackbarManager$SnackbarRecord0.duration == -2) {
            return;
        }
        if(snackbarManager$SnackbarRecord0.duration > 0) {
            v = snackbarManager$SnackbarRecord0.duration;
        }
        else {
            v = snackbarManager$SnackbarRecord0.duration == -1 ? 1500 : 2750;
        }
        this.handler.removeCallbacksAndMessages(snackbarManager$SnackbarRecord0);
        Message message0 = Message.obtain(this.handler, 0, snackbarManager$SnackbarRecord0);
        this.handler.sendMessageDelayed(message0, ((long)v));
    }

    public void show(int v, Callback snackbarManager$Callback0) {
        synchronized(this.lock) {
            if(this.isCurrentSnackbarLocked(snackbarManager$Callback0)) {
                this.currentSnackbar.duration = v;
                this.handler.removeCallbacksAndMessages(this.currentSnackbar);
                this.scheduleTimeoutLocked(this.currentSnackbar);
                return;
            }
            if(this.isNextSnackbarLocked(snackbarManager$Callback0)) {
                this.nextSnackbar.duration = v;
            }
            else {
                this.nextSnackbar = new SnackbarRecord(v, snackbarManager$Callback0);
            }
            if(this.currentSnackbar != null && this.cancelSnackbarLocked(this.currentSnackbar, 4)) {
                return;
            }
            this.currentSnackbar = null;
            this.showNextSnackbarLocked();
        }
    }

    private void showNextSnackbarLocked() {
        SnackbarRecord snackbarManager$SnackbarRecord0 = this.nextSnackbar;
        if(snackbarManager$SnackbarRecord0 != null) {
            this.currentSnackbar = snackbarManager$SnackbarRecord0;
            this.nextSnackbar = null;
            Callback snackbarManager$Callback0 = (Callback)snackbarManager$SnackbarRecord0.callback.get();
            if(snackbarManager$Callback0 != null) {
                snackbarManager$Callback0.show();
                return;
            }
            this.currentSnackbar = null;
        }
    }
}

