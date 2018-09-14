package ru.sequenia.bouncece_lib;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import static android.view.View.TRANSLATION_Y;

public class Bouncece implements ContentListener, VelocityListener {

    /**
     * Слушатель прикосновений пользовательский
     */
    private View.OnTouchListener onTouchListener;

    /**
     * Максимальная высота, на которую можно опускать контент
     */
    private int maxContentOffset;

    /**
     * Скороть анимации для возвращения позации y=0
     */
    private int durationTranslate;

    /**
     * Скорость анимации BOUNCE (двойная, делим попалам)
     */
    private int durationBounce;

    /**
     * Амплитуда колебайнийй для анимации BOUNCE
     */
    private float amplitude;

    /**
     * Частота колебайний для анимации BOUNCE
     */
    private float frequency;

    /**
     * Начальная позиция прокрутки, с которой начинается анимация (в идеале 0)
     */
    private float startScrollPosition = 0;

    /**
     * Текущий уровень прокрутки
     */
    private float scrollPosition = 0;

    /**
     * Начальное положение контента
     */
    private float startContentPosition;

    /**
     * Текущее положение контента
     */
    private float contentPosition;

    /**
     * Максимальная скорость при Fling
     */
    private float maxFlingVelocity;

    /**
     * Смещение по y для анимации BOUNCE
     */
    private float bounceDY;

    /**
     * Была ли запущена анимация
     */
    private boolean animationStarted = false;

    /**
     * Был ли флинг
     */
    private boolean needFlingAnimation = false;

    /**
     * Контент, который анимируем
     */
    private Content content;

    Bouncece() {
    }

    Bouncece(BounceceBuilder builder) {
        if (builder == null) {
            return;
        }

        this.durationBounce = builder.getDurationBounce();
        this.durationTranslate = builder.getDurationTranslate();
        this.amplitude = builder.getAmplitude();
        this.frequency = builder.getFrequency();
        this.maxContentOffset = builder.getMaxContentOffset();
        this.startContentPosition = builder.getStartContentPosition();
        this.contentPosition = this.startContentPosition;
    }

    /**
     * Задание контента, который будем двигать
     *
     * @param view - контента
     */
    public void setContent(RecyclerView view) {
        if (view == null) {
            return;
        }
        content = new RecyclerViewContent(view);
        content.setContentListener(this);
        content.setOnTouchListener(getBounceOnTouchListener());
        maxFlingVelocity = content.getMaxFlingVelocity();
    }

    /**
     * Слушатель на прикосновения, который нужен дополнительно пользователю
     *
     * @param onTouchListener - слушатель прикосновений
     */
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    /**
     * Запуск анимации для возвращение в исходное положение контента
     *
     * @param y - текущее положение контента
     */
    private void startReturnPositionAnimation(float y) {
        if (content == null || content.getView() == null) {
            return;
        }

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(content.getView(), TRANSLATION_Y, y,
                startContentPosition);
        objectAnimator.setDuration(durationTranslate);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animationStarted = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationStarted = false;
                if (scrollPosition == startScrollPosition && needFlingAnimation) {
                    startFlingAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.start();
    }

    /**
     * Запуск анимации для Fling
     */
    private void startFlingAnimation() {
        if (animationStarted) {
            return;
        }

        if (content == null || content.getView() == null) {
            return;
        }

        needFlingAnimation = false;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(content.getView(), TRANSLATION_Y,
                startContentPosition, bounceDY);
        objectAnimator.setDuration(durationBounce / 2);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(new BounceInterpolator(amplitude, frequency));
        objectAnimator.start();
    }

    /**
     * Вычиследние смещения по Y для BOUNCE
     *
     * @param velocityY - скорость
     */
    private void setBounceDY(int velocityY) {
        bounceDY = getOffset(Math.abs(velocityY));
    }

    /**
     * Вычисление смещения в зависимости от величины VELOCITY
     *
     * @param velocity - скорость
     * @return - смещение
     */
    private float getOffset(int velocity) {
        return 100 * velocity / maxFlingVelocity;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        scrollPosition += dy;
    }

    @Override
    public void onFling(int velocityX, int velocityY) {
        if (velocityY < 0) {
            setBounceDY(velocityY);
            needFlingAnimation = true;
        }
    }

    @Override
    public void onStart() {
        contentPosition = startContentPosition;
    }

    @Override
    public void onStop() {
        startReturnPositionAnimation(contentPosition);
        contentPosition = startContentPosition;
    }

    @Override
    public void setValue(int velocityX, int velocityY) {
        setBounceDY(velocityY);

        if (content == null || content.getView() == null) {
            return;
        }

        if (scrollPosition == 0) {
            contentPosition += getOffset(velocityY);
            contentPosition = Math.max(startContentPosition, contentPosition);
            contentPosition = Math.min(maxContentOffset, contentPosition);
            content.getView().setTranslationY(contentPosition);
        }

        content.setEnableScroll(contentPosition <= 0);
    }

    /**
     * Создание BounceOnTouchListener и вызов метода onTouch слушателя пользователя
     *
     * @return - BounceOnTouchListener
     */
    private BounceOnTouchListener getBounceOnTouchListener() {
        return new BounceOnTouchListener(this) {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (onTouchListener != null) {
                    onTouchListener.onTouch(view, event);
                }
                return super.onTouch(view, event);
            }
        };
    }
}

