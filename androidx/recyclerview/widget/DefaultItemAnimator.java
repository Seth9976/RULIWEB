package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;

public class DefaultItemAnimator extends SimpleItemAnimator {
    static class ChangeInfo {
        public int fromX;
        public int fromY;
        public ViewHolder newHolder;
        public ViewHolder oldHolder;
        public int toX;
        public int toY;

        private ChangeInfo(ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1) {
            this.oldHolder = recyclerView$ViewHolder0;
            this.newHolder = recyclerView$ViewHolder1;
        }

        ChangeInfo(ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1, int v, int v1, int v2, int v3) {
            this(recyclerView$ViewHolder0, recyclerView$ViewHolder1);
            this.fromX = v;
            this.fromY = v1;
            this.toX = v2;
            this.toY = v3;
        }

        @Override
        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    static class MoveInfo {
        public int fromX;
        public int fromY;
        public ViewHolder holder;
        public int toX;
        public int toY;

        MoveInfo(ViewHolder recyclerView$ViewHolder0, int v, int v1, int v2, int v3) {
            this.holder = recyclerView$ViewHolder0;
            this.fromX = v;
            this.fromY = v1;
            this.toX = v2;
            this.toY = v3;
        }
    }

    private static final boolean DEBUG = false;
    ArrayList mAddAnimations;
    ArrayList mAdditionsList;
    ArrayList mChangeAnimations;
    ArrayList mChangesList;
    ArrayList mMoveAnimations;
    ArrayList mMovesList;
    private ArrayList mPendingAdditions;
    private ArrayList mPendingChanges;
    private ArrayList mPendingMoves;
    private ArrayList mPendingRemovals;
    ArrayList mRemoveAnimations;
    private static TimeInterpolator sDefaultInterpolator;

    public DefaultItemAnimator() {
        this.mPendingRemovals = new ArrayList();
        this.mPendingAdditions = new ArrayList();
        this.mPendingMoves = new ArrayList();
        this.mPendingChanges = new ArrayList();
        this.mAdditionsList = new ArrayList();
        this.mMovesList = new ArrayList();
        this.mChangesList = new ArrayList();
        this.mAddAnimations = new ArrayList();
        this.mMoveAnimations = new ArrayList();
        this.mRemoveAnimations = new ArrayList();
        this.mChangeAnimations = new ArrayList();
    }

    @Override  // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(ViewHolder recyclerView$ViewHolder0) {
        this.resetAnimation(recyclerView$ViewHolder0);
        recyclerView$ViewHolder0.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add(recyclerView$ViewHolder0);
        return true;
    }

    void animateAddImpl(ViewHolder recyclerView$ViewHolder0) {
        ViewPropertyAnimator viewPropertyAnimator0 = recyclerView$ViewHolder0.itemView.animate();
        this.mAddAnimations.add(recyclerView$ViewHolder0);
        viewPropertyAnimator0.alpha(1.0f).setDuration(this.getAddDuration()).setListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationCancel(Animator animator0) {
                recyclerView$ViewHolder0.itemView.setAlpha(1.0f);
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                viewPropertyAnimator0.setListener(null);
                DefaultItemAnimator.this.dispatchAddFinished(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.mAddAnimations.remove(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
            }
        }).start();
    }

    @Override  // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1, int v, int v1, int v2, int v3) {
        if(recyclerView$ViewHolder0 == recyclerView$ViewHolder1) {
            return this.animateMove(recyclerView$ViewHolder0, v, v1, v2, v3);
        }
        float f = recyclerView$ViewHolder0.itemView.getTranslationX();
        float f1 = recyclerView$ViewHolder0.itemView.getTranslationY();
        float f2 = recyclerView$ViewHolder0.itemView.getAlpha();
        this.resetAnimation(recyclerView$ViewHolder0);
        recyclerView$ViewHolder0.itemView.setTranslationX(f);
        recyclerView$ViewHolder0.itemView.setTranslationY(f1);
        recyclerView$ViewHolder0.itemView.setAlpha(f2);
        if(recyclerView$ViewHolder1 != null) {
            this.resetAnimation(recyclerView$ViewHolder1);
            recyclerView$ViewHolder1.itemView.setTranslationX(((float)(-((int)(((float)(v2 - v)) - f)))));
            recyclerView$ViewHolder1.itemView.setTranslationY(((float)(-((int)(((float)(v3 - v1)) - f1)))));
            recyclerView$ViewHolder1.itemView.setAlpha(0.0f);
        }
        this.mPendingChanges.add(new ChangeInfo(recyclerView$ViewHolder0, recyclerView$ViewHolder1, v, v1, v2, v3));
        return true;
    }

    void animateChangeImpl(ChangeInfo defaultItemAnimator$ChangeInfo0) {
        View view0 = null;
        View view1 = defaultItemAnimator$ChangeInfo0.oldHolder == null ? null : defaultItemAnimator$ChangeInfo0.oldHolder.itemView;
        ViewHolder recyclerView$ViewHolder0 = defaultItemAnimator$ChangeInfo0.newHolder;
        if(recyclerView$ViewHolder0 != null) {
            view0 = recyclerView$ViewHolder0.itemView;
        }
        if(view1 != null) {
            ViewPropertyAnimator viewPropertyAnimator0 = view1.animate().setDuration(this.getChangeDuration());
            this.mChangeAnimations.add(defaultItemAnimator$ChangeInfo0.oldHolder);
            viewPropertyAnimator0.translationX(((float)(defaultItemAnimator$ChangeInfo0.toX - defaultItemAnimator$ChangeInfo0.fromX)));
            viewPropertyAnimator0.translationY(((float)(defaultItemAnimator$ChangeInfo0.toY - defaultItemAnimator$ChangeInfo0.fromY)));
            viewPropertyAnimator0.alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    viewPropertyAnimator0.setListener(null);
                    view1.setAlpha(1.0f);
                    view1.setTranslationX(0.0f);
                    view1.setTranslationY(0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(defaultItemAnimator$ChangeInfo0.oldHolder, true);
                    DefaultItemAnimator.this.mChangeAnimations.remove(defaultItemAnimator$ChangeInfo0.oldHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                }
            }).start();
        }
        if(view0 != null) {
            ViewPropertyAnimator viewPropertyAnimator1 = view0.animate();
            this.mChangeAnimations.add(defaultItemAnimator$ChangeInfo0.newHolder);
            viewPropertyAnimator1.translationX(0.0f).translationY(0.0f).setDuration(this.getChangeDuration()).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    viewPropertyAnimator1.setListener(null);
                    view0.setAlpha(1.0f);
                    view0.setTranslationX(0.0f);
                    view0.setTranslationY(0.0f);
                    DefaultItemAnimator.this.dispatchChangeFinished(defaultItemAnimator$ChangeInfo0.newHolder, false);
                    DefaultItemAnimator.this.mChangeAnimations.remove(defaultItemAnimator$ChangeInfo0.newHolder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                }
            }).start();
        }
    }

    @Override  // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(ViewHolder recyclerView$ViewHolder0, int v, int v1, int v2, int v3) {
        View view0 = recyclerView$ViewHolder0.itemView;
        int v4 = v + ((int)recyclerView$ViewHolder0.itemView.getTranslationX());
        int v5 = v1 + ((int)recyclerView$ViewHolder0.itemView.getTranslationY());
        this.resetAnimation(recyclerView$ViewHolder0);
        int v6 = v2 - v4;
        int v7 = v3 - v5;
        if(v6 == 0 && v7 == 0) {
            this.dispatchMoveFinished(recyclerView$ViewHolder0);
            return false;
        }
        if(v6 != 0) {
            view0.setTranslationX(((float)(-v6)));
        }
        if(v7 != 0) {
            view0.setTranslationY(((float)(-v7)));
        }
        this.mPendingMoves.add(new MoveInfo(recyclerView$ViewHolder0, v4, v5, v2, v3));
        return true;
    }

    void animateMoveImpl(ViewHolder recyclerView$ViewHolder0, int v, int v1, int v2, int v3) {
        View view0 = recyclerView$ViewHolder0.itemView;
        int v4 = v2 - v;
        int v5 = v3 - v1;
        if(v4 != 0) {
            view0.animate().translationX(0.0f);
        }
        if(v5 != 0) {
            view0.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimator0 = view0.animate();
        this.mMoveAnimations.add(recyclerView$ViewHolder0);
        viewPropertyAnimator0.setDuration(this.getMoveDuration()).setListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationCancel(Animator animator0) {
                if(v4 != 0) {
                    view0.setTranslationX(0.0f);
                }
                if(v5 != 0) {
                    view0.setTranslationY(0.0f);
                }
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                viewPropertyAnimator0.setListener(null);
                DefaultItemAnimator.this.dispatchMoveFinished(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.mMoveAnimations.remove(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
            }
        }).start();
    }

    @Override  // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(ViewHolder recyclerView$ViewHolder0) {
        this.resetAnimation(recyclerView$ViewHolder0);
        this.mPendingRemovals.add(recyclerView$ViewHolder0);
        return true;
    }

    private void animateRemoveImpl(ViewHolder recyclerView$ViewHolder0) {
        ViewPropertyAnimator viewPropertyAnimator0 = recyclerView$ViewHolder0.itemView.animate();
        this.mRemoveAnimations.add(recyclerView$ViewHolder0);
        viewPropertyAnimator0.setDuration(this.getRemoveDuration()).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                viewPropertyAnimator0.setListener(null);
                recyclerView$ViewHolder0.itemView.setAlpha(1.0f);
                DefaultItemAnimator.this.dispatchRemoveFinished(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.mRemoveAnimations.remove(recyclerView$ViewHolder0);
                DefaultItemAnimator.this.dispatchFinishedWhenDone();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
            }
        }).start();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean canReuseUpdatedViewHolder(ViewHolder recyclerView$ViewHolder0, List list0) {
        return !list0.isEmpty() || super.canReuseUpdatedViewHolder(recyclerView$ViewHolder0, list0);
    }

    void cancelAll(List list0) {
        for(int v = list0.size() - 1; v >= 0; --v) {
            ((ViewHolder)list0.get(v)).itemView.animate().cancel();
        }
    }

    void dispatchFinishedWhenDone() {
        if(!this.isRunning()) {
            this.dispatchAnimationsFinished();
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public void endAnimation(ViewHolder recyclerView$ViewHolder0) {
        View view0 = recyclerView$ViewHolder0.itemView;
        view0.animate().cancel();
        for(int v = this.mPendingMoves.size() - 1; v >= 0; --v) {
            if(((MoveInfo)this.mPendingMoves.get(v)).holder == recyclerView$ViewHolder0) {
                view0.setTranslationY(0.0f);
                view0.setTranslationX(0.0f);
                this.dispatchMoveFinished(recyclerView$ViewHolder0);
                this.mPendingMoves.remove(v);
            }
        }
        this.endChangeAnimation(this.mPendingChanges, recyclerView$ViewHolder0);
        if(this.mPendingRemovals.remove(recyclerView$ViewHolder0)) {
            view0.setAlpha(1.0f);
            this.dispatchRemoveFinished(recyclerView$ViewHolder0);
        }
        if(this.mPendingAdditions.remove(recyclerView$ViewHolder0)) {
            view0.setAlpha(1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder0);
        }
        for(int v1 = this.mChangesList.size() - 1; v1 >= 0; --v1) {
            ArrayList arrayList0 = (ArrayList)this.mChangesList.get(v1);
            this.endChangeAnimation(arrayList0, recyclerView$ViewHolder0);
            if(arrayList0.isEmpty()) {
                this.mChangesList.remove(v1);
            }
        }
        for(int v2 = this.mMovesList.size() - 1; v2 >= 0; --v2) {
            ArrayList arrayList1 = (ArrayList)this.mMovesList.get(v2);
            for(int v3 = arrayList1.size() - 1; v3 >= 0; --v3) {
                if(((MoveInfo)arrayList1.get(v3)).holder == recyclerView$ViewHolder0) {
                    view0.setTranslationY(0.0f);
                    view0.setTranslationX(0.0f);
                    this.dispatchMoveFinished(recyclerView$ViewHolder0);
                    arrayList1.remove(v3);
                    if(arrayList1.isEmpty()) {
                        this.mMovesList.remove(v2);
                    }
                    break;
                }
            }
        }
        for(int v4 = this.mAdditionsList.size() - 1; v4 >= 0; --v4) {
            ArrayList arrayList2 = (ArrayList)this.mAdditionsList.get(v4);
            if(arrayList2.remove(recyclerView$ViewHolder0)) {
                view0.setAlpha(1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder0);
                if(arrayList2.isEmpty()) {
                    this.mAdditionsList.remove(v4);
                }
            }
        }
        this.mRemoveAnimations.remove(recyclerView$ViewHolder0);
        this.mAddAnimations.remove(recyclerView$ViewHolder0);
        this.mChangeAnimations.remove(recyclerView$ViewHolder0);
        this.mMoveAnimations.remove(recyclerView$ViewHolder0);
        this.dispatchFinishedWhenDone();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public void endAnimations() {
        for(int v = this.mPendingMoves.size() - 1; v >= 0; --v) {
            MoveInfo defaultItemAnimator$MoveInfo0 = (MoveInfo)this.mPendingMoves.get(v);
            View view0 = defaultItemAnimator$MoveInfo0.holder.itemView;
            view0.setTranslationY(0.0f);
            view0.setTranslationX(0.0f);
            this.dispatchMoveFinished(defaultItemAnimator$MoveInfo0.holder);
            this.mPendingMoves.remove(v);
        }
        for(int v1 = this.mPendingRemovals.size() - 1; v1 >= 0; --v1) {
            this.dispatchRemoveFinished(((ViewHolder)this.mPendingRemovals.get(v1)));
            this.mPendingRemovals.remove(v1);
        }
        for(int v2 = this.mPendingAdditions.size() - 1; v2 >= 0; --v2) {
            ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mPendingAdditions.get(v2);
            recyclerView$ViewHolder0.itemView.setAlpha(1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder0);
            this.mPendingAdditions.remove(v2);
        }
        for(int v3 = this.mPendingChanges.size() - 1; v3 >= 0; --v3) {
            this.endChangeAnimationIfNecessary(((ChangeInfo)this.mPendingChanges.get(v3)));
        }
        this.mPendingChanges.clear();
        if(!this.isRunning()) {
            return;
        }
        for(int v4 = this.mMovesList.size() - 1; v4 >= 0; --v4) {
            ArrayList arrayList0 = (ArrayList)this.mMovesList.get(v4);
            for(int v5 = arrayList0.size() - 1; v5 >= 0; --v5) {
                MoveInfo defaultItemAnimator$MoveInfo1 = (MoveInfo)arrayList0.get(v5);
                View view1 = defaultItemAnimator$MoveInfo1.holder.itemView;
                view1.setTranslationY(0.0f);
                view1.setTranslationX(0.0f);
                this.dispatchMoveFinished(defaultItemAnimator$MoveInfo1.holder);
                arrayList0.remove(v5);
                if(arrayList0.isEmpty()) {
                    this.mMovesList.remove(arrayList0);
                }
            }
        }
        for(int v6 = this.mAdditionsList.size() - 1; v6 >= 0; --v6) {
            ArrayList arrayList1 = (ArrayList)this.mAdditionsList.get(v6);
            for(int v7 = arrayList1.size() - 1; v7 >= 0; --v7) {
                ViewHolder recyclerView$ViewHolder1 = (ViewHolder)arrayList1.get(v7);
                recyclerView$ViewHolder1.itemView.setAlpha(1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder1);
                arrayList1.remove(v7);
                if(arrayList1.isEmpty()) {
                    this.mAdditionsList.remove(arrayList1);
                }
            }
        }
        for(int v8 = this.mChangesList.size() - 1; v8 >= 0; --v8) {
            ArrayList arrayList2 = (ArrayList)this.mChangesList.get(v8);
            for(int v9 = arrayList2.size() - 1; v9 >= 0; --v9) {
                this.endChangeAnimationIfNecessary(((ChangeInfo)arrayList2.get(v9)));
                if(arrayList2.isEmpty()) {
                    this.mChangesList.remove(arrayList2);
                }
            }
        }
        this.cancelAll(this.mRemoveAnimations);
        this.cancelAll(this.mMoveAnimations);
        this.cancelAll(this.mAddAnimations);
        this.cancelAll(this.mChangeAnimations);
        this.dispatchAnimationsFinished();
    }

    private void endChangeAnimation(List list0, ViewHolder recyclerView$ViewHolder0) {
        for(int v = list0.size() - 1; v >= 0; --v) {
            ChangeInfo defaultItemAnimator$ChangeInfo0 = (ChangeInfo)list0.get(v);
            if(this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo0, recyclerView$ViewHolder0) && defaultItemAnimator$ChangeInfo0.oldHolder == null && defaultItemAnimator$ChangeInfo0.newHolder == null) {
                list0.remove(defaultItemAnimator$ChangeInfo0);
            }
        }
    }

    private void endChangeAnimationIfNecessary(ChangeInfo defaultItemAnimator$ChangeInfo0) {
        if(defaultItemAnimator$ChangeInfo0.oldHolder != null) {
            this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo0, defaultItemAnimator$ChangeInfo0.oldHolder);
        }
        if(defaultItemAnimator$ChangeInfo0.newHolder != null) {
            this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo0, defaultItemAnimator$ChangeInfo0.newHolder);
        }
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo defaultItemAnimator$ChangeInfo0, ViewHolder recyclerView$ViewHolder0) {
        boolean z = false;
        if(defaultItemAnimator$ChangeInfo0.newHolder == recyclerView$ViewHolder0) {
            defaultItemAnimator$ChangeInfo0.newHolder = null;
        }
        else if(defaultItemAnimator$ChangeInfo0.oldHolder == recyclerView$ViewHolder0) {
            defaultItemAnimator$ChangeInfo0.oldHolder = null;
            z = true;
        }
        else {
            return false;
        }
        recyclerView$ViewHolder0.itemView.setAlpha(1.0f);
        recyclerView$ViewHolder0.itemView.setTranslationX(0.0f);
        recyclerView$ViewHolder0.itemView.setTranslationY(0.0f);
        this.dispatchChangeFinished(recyclerView$ViewHolder0, z);
        return true;
    }

    // 去混淆评级： 中等(110)
    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }

    private void resetAnimation(ViewHolder recyclerView$ViewHolder0) {
        if(DefaultItemAnimator.sDefaultInterpolator == null) {
            DefaultItemAnimator.sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        recyclerView$ViewHolder0.itemView.animate().setInterpolator(DefaultItemAnimator.sDefaultInterpolator);
        this.endAnimation(recyclerView$ViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public void runPendingAnimations() {
        boolean z = this.mPendingRemovals.isEmpty();
        boolean z1 = this.mPendingMoves.isEmpty();
        boolean z2 = this.mPendingChanges.isEmpty();
        boolean z3 = this.mPendingAdditions.isEmpty();
        if(!z || !z1 || !z3 || !z2) {
            for(Object object0: this.mPendingRemovals) {
                this.animateRemoveImpl(((ViewHolder)object0));
            }
            this.mPendingRemovals.clear();
            if(!z1) {
                ArrayList arrayList0 = new ArrayList();
                arrayList0.addAll(this.mPendingMoves);
                this.mMovesList.add(arrayList0);
                this.mPendingMoves.clear();
                androidx.recyclerview.widget.DefaultItemAnimator.1 defaultItemAnimator$10 = new Runnable() {
                    @Override
                    public void run() {
                        for(Object object0: arrayList0) {
                            DefaultItemAnimator.this.animateMoveImpl(((MoveInfo)object0).holder, ((MoveInfo)object0).fromX, ((MoveInfo)object0).fromY, ((MoveInfo)object0).toX, ((MoveInfo)object0).toY);
                        }
                        arrayList0.clear();
                        DefaultItemAnimator.this.mMovesList.remove(arrayList0);
                    }
                };
                if(z) {
                    defaultItemAnimator$10.run();
                }
                else {
                    ViewCompat.postOnAnimationDelayed(((MoveInfo)arrayList0.get(0)).holder.itemView, defaultItemAnimator$10, this.getRemoveDuration());
                }
            }
            if(!z2) {
                ArrayList arrayList1 = new ArrayList();
                arrayList1.addAll(this.mPendingChanges);
                this.mChangesList.add(arrayList1);
                this.mPendingChanges.clear();
                androidx.recyclerview.widget.DefaultItemAnimator.2 defaultItemAnimator$20 = new Runnable() {
                    @Override
                    public void run() {
                        for(Object object0: arrayList1) {
                            DefaultItemAnimator.this.animateChangeImpl(((ChangeInfo)object0));
                        }
                        arrayList1.clear();
                        DefaultItemAnimator.this.mChangesList.remove(arrayList1);
                    }
                };
                if(z) {
                    defaultItemAnimator$20.run();
                }
                else {
                    ViewCompat.postOnAnimationDelayed(((ChangeInfo)arrayList1.get(0)).oldHolder.itemView, defaultItemAnimator$20, this.getRemoveDuration());
                }
            }
            if(!z3) {
                long v = 0L;
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(arrayList2);
                this.mPendingAdditions.clear();
                androidx.recyclerview.widget.DefaultItemAnimator.3 defaultItemAnimator$30 = new Runnable() {
                    @Override
                    public void run() {
                        for(Object object0: arrayList2) {
                            DefaultItemAnimator.this.animateAddImpl(((ViewHolder)object0));
                        }
                        arrayList2.clear();
                        DefaultItemAnimator.this.mAdditionsList.remove(arrayList2);
                    }
                };
                if(z && z1 && z2) {
                    defaultItemAnimator$30.run();
                    return;
                }
                long v1 = z ? 0L : this.getRemoveDuration();
                long v2 = z1 ? 0L : this.getMoveDuration();
                if(!z2) {
                    v = this.getChangeDuration();
                }
                ViewCompat.postOnAnimationDelayed(((ViewHolder)arrayList2.get(0)).itemView, defaultItemAnimator$30, v1 + Math.max(v2, v));
            }
        }
    }
}

