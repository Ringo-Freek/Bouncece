package ru.sequenia.bouncece_lib;

/**
 * Слушатель изменения скорости
 */
interface VelocityListener {

    /**
     * Начинается определение скорости
     */
    void onStart();

    /**
     * Завершение определения скорости
     */
    void onStop();

    /**
     * Установить значения скорости
     *
     * @param velocityX - по x
     * @param velocityY - по y
     */
    void setValue(int velocityX, int velocityY);
}

