package ru.sequenia.bouncece_lib;

public class BounceceBuilder {

    /**
     * Максимальная высота, на которую можно опускать контент
     */
    private int maxContentOffset = 500;

    /**
     * Скороть анимации для возвращения позации y=0
     */
    private int durationTranslate = 200;

    /**
     * Скорость анимации BOUNCE (двойная, делим попалам)
     */
    private int durationBounce = 300;

    /**
     * Амплитуда колебайнийй для анимации BOUNCE
     */
    private float amplitude = 0.7f;

    /**
     * Частота колебайний для анимации BOUNCE
     */
    private float frequency = 1;

    /**
     * Начальная позиция прокрутки, с которой начинается анимация (в идеале 0)
     */
    private float startContentPosition = 0;

    /**
     * Метод для завершения задания параметров
     *
     * @return - возвращает объект Bouncece
     */
    public Bouncece build() {
        return new Bouncece(this);
    }

    /**
     * Задание макисмальной высоты, на которую можно опустить контент
     *
     * @param maxContentOffset - максимальная высота
     */
    public void setMaxContentOffset(int maxContentOffset) {
        this.maxContentOffset = maxContentOffset;
    }

    /**
     * Задание скорости анимации возвращения контента в исходное положение
     *
     * @param duration - скорость
     */
    public BounceceBuilder setDurationTranslate(int duration) {
        durationTranslate = duration;
        return this;
    }

    /**
     * Задание скорости анимации BOUNCE
     *
     * @param duration - скорость
     */
    public BounceceBuilder setDurationBounce(int duration) {
        durationBounce = duration;
        return this;
    }

    /**
     * Задание амплитуды колебаний
     *
     * @param amplitude - амплитуда
     */
    public BounceceBuilder setAmplitude(float amplitude) {
        this.amplitude = amplitude;
        return this;
    }

    /**
     * Задание частоты колебаний
     *
     * @param frequency - частота
     */
    public BounceceBuilder setFrequency(float frequency) {
        this.frequency = frequency;
        return this;
    }

    /**
     * @return Максимальная высота, на которую можно опускать контент
     */
    public int getMaxContentOffset() {
        return maxContentOffset;
    }

    /**
     * @return Скороть анимации для возвращения позации y=0
     */
    public int getDurationTranslate() {
        return durationTranslate;
    }

    /**
     * @return Скорость анимации BOUNCE (двойная, делим попалам)
     */
    public int getDurationBounce() {
        return durationBounce;
    }

    /**
     * @return Амплитуда колебайнийй для анимации BOUNCE
     */
    public float getAmplitude() {
        return amplitude;
    }

    /**
     * @return Частота колебайний для анимации BOUNCE
     */
    public float getFrequency() {
        return frequency;
    }

    /**
     * @return Начальное положение контента
     */
    public float getStartContentPosition() {
        return startContentPosition;
    }

    /**
     * Задание начального положения контента
     *
     * @param startContentPosition - положение контента
     */
    public void setStartContentPosition(float startContentPosition) {
        this.startContentPosition = startContentPosition;
    }
}
