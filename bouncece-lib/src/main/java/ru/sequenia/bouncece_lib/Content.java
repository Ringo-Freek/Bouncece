package ru.sequenia.bouncece_lib;

import android.view.View;

/**
 * Интерфес, который необходимо реализовать
 * у обертки над view со scroll
 */
interface Content {

    /**
     * Задать слушатели для view
     *
     * @param listener - случатель
     */
    void setContentListener(final ContentListener listener);

    /**
     * Задать слушатель на прикосновения
     *
     * @param listener - слушатель на прикосновения
     */
    void setOnTouchListener(BounceOnTouchListener listener);

    /**
     * View, которая оборачивается
     *
     * @return - view
     */
    View getView();

    /**
     * Максимальное значение скорости, берется из View
     *
     * @return - максимальная скорость
     */
    float getMaxFlingVelocity();

    /**
     * Разрешить прокручивать контент
     *
     * @param enable - true скролл не блокируется
     */
    void setEnableScroll(boolean enable);
}
