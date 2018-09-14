package ru.sequenia.bouncece_lib;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Слушатель на изменение скорости
 */
class BounceOnTouchListener implements View.OnTouchListener {

    private VelocityTracker velocityTracker;
    private VelocityListener velocityListener;

    BounceOnTouchListener(VelocityListener listener) {
        velocityListener = listener;
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (velocityListener == null) {
            return false;
        }

        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                velocityTracker.clear();
                velocityTracker.addMovement(event);
                velocityListener.onStart();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                velocityListener.setValue(
                        (int) velocityTracker.getXVelocity(pointerId),
                        (int) velocityTracker.getYVelocity(pointerId)
                );
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.clear();
                velocityListener.onStop();
                break;
        }
        return false;
    }
}
