package ru.sequenia.bouncece_lib;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Обертка для View типа RecyclerView
 */
class RecyclerViewContent implements Content {

    private RecyclerView view;
    private boolean enable = true;

    RecyclerViewContent(RecyclerView view) {
        this.view = view;

        // Слушатель, который позволяет выключить scroll
        view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return !enable;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void setContentListener(final ContentListener listener) {
        if (listener == null || view == null) {
            return;
        }

        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                listener.onScrolled(dx, dy);
            }
        });

        view.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                listener.onFling(velocityX, velocityY);
                return false;
            }
        });
    }

    @Override
    public void setOnTouchListener(BounceOnTouchListener listener) {
        if (view == null) {
            return;
        }
        view.setOnTouchListener(listener);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public float getMaxFlingVelocity() {
        return view != null ? view.getMaxFlingVelocity() : 1;
    }

    @Override
    public void setEnableScroll(final boolean enable) {
        this.enable = enable;
    }
}
