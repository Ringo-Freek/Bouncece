package ru.sequenia.bouncece_lib;

interface ContentListener {
    /**
     * Слушатель на изменения положения скролла контента
     *
     * @param dx - изменение по x
     * @param dy - изменение по y
     */
    void onScrolled(int dx, int dy);

    /**
     * Слушатель на fling
     *
     * @param velocityX - скорость по X
     * @param velocityY - скорость по Y
     */
    void onFling(int velocityX, int velocityY);
}

