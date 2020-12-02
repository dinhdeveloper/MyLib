package com.canhdinh.lib.animatedcircleloadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.canhdinh.lib.R;
import com.canhdinh.lib.animatedcircleloadingview.animator.ViewAnimator;
import com.canhdinh.lib.animatedcircleloadingview.component.InitialCenterCircleView;
import com.canhdinh.lib.animatedcircleloadingview.component.MainCircleView;
import com.canhdinh.lib.animatedcircleloadingview.component.PercentIndicatorView;
import com.canhdinh.lib.animatedcircleloadingview.component.RightCircleView;
import com.canhdinh.lib.animatedcircleloadingview.component.SideArcsView;
import com.canhdinh.lib.animatedcircleloadingview.component.TopCircleBorderView;
import com.canhdinh.lib.animatedcircleloadingview.component.finish.FinishedFailureView;
import com.canhdinh.lib.animatedcircleloadingview.component.finish.FinishedOkView;


/**
 * @author jlmd
 */
public class AnimatedCircleLoadingView extends FrameLayout {

  private static final String DEFAULT_HEX_MAIN_COLOR = "#FF9A00";
  private static final String DEFAULT_HEX_SECONDARY_COLOR = "#BDBDBD";
  private static final String DEFAULT_HEX_TINT_COLOR = "#FFFFFF";
  private static final String DEFAULT_HEX_TEXT_COLOR = "#FFFFFF";
  private final Context context;
  private InitialCenterCircleView initialCenterCircleView;
  private MainCircleView mainCircleView;
  private RightCircleView rightCircleView;
  private SideArcsView sideArcsView;
  private TopCircleBorderView topCircleBorderView;
  private FinishedOkView finishedOkView;
  private FinishedFailureView finishedFailureView;
  private PercentIndicatorView percentIndicatorView;
  private ViewAnimator viewAnimator;
  private AnimationListener animationListener;
  private boolean startAnimationIndeterminate;
  private boolean startAnimationDeterminate;
  private boolean stopAnimationOk;
  private boolean stopAnimationFailure;
  private int mainColor;
  private int secondaryColor;
  private int checkMarkTintColor;
  private int failureMarkTintColor;
  private int textColor;

  public AnimatedCircleLoadingView(Context context) {
    super(context);
    this.context = context;
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    initAttributes(attrs);
  }

  public AnimatedCircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    initAttributes(attrs);
  }

  private void initAttributes(AttributeSet attrs) {
    TypedArray attributes =
        getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedCircleLoadingView);
    mainColor = attributes.getColor(R.styleable.AnimatedCircleLoadingView_animCircleLoadingView_mainColor,
        Color.parseColor(DEFAULT_HEX_MAIN_COLOR));
    secondaryColor = attributes.getColor(R.styleable.AnimatedCircleLoadingView_animCircleLoadingView_secondaryColor,
        Color.parseColor(DEFAULT_HEX_SECONDARY_COLOR));
    checkMarkTintColor =
        attributes.getColor(R.styleable.AnimatedCircleLoadingView_animCircleLoadingView_checkMarkTintColor,
            Color.parseColor(DEFAULT_HEX_TINT_COLOR));
    failureMarkTintColor =
        attributes.getColor(R.styleable.AnimatedCircleLoadingView_animCircleLoadingView_failureMarkTintColor,
            Color.parseColor(DEFAULT_HEX_TINT_COLOR));
    textColor = attributes.getColor(R.styleable.AnimatedCircleLoadingView_animCircleLoadingView_textColor,
        Color.parseColor(DEFAULT_HEX_TEXT_COLOR));
    attributes.recycle();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    init();
    startAnimation();
  }

  private void startAnimation() {
    if (getWidth() != 0 && getHeight() != 0) {
      if (startAnimationIndeterminate) {
        viewAnimator.startAnimator();
        startAnimationIndeterminate = false;
      }
      if (startAnimationDeterminate) {
        addView(percentIndicatorView);
        viewAnimator.startAnimator();
        startAnimationDeterminate = false;
      }
      if (stopAnimationOk) {
        stopOk();
      }
      if (stopAnimationFailure) {
        stopFailure();
      }
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // Force view to be a square
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }

  private void init() {
    initComponents();
    addComponentsViews();
    initAnimatorHelper();
  }

  private void initComponents() {
    int width = getWidth();
    initialCenterCircleView =
        new InitialCenterCircleView(context, width, mainColor, secondaryColor);
    rightCircleView = new RightCircleView(context, width, mainColor, secondaryColor);
    sideArcsView = new SideArcsView(context, width, mainColor, secondaryColor);
    topCircleBorderView = new TopCircleBorderView(context, width, mainColor, secondaryColor);
    mainCircleView = new MainCircleView(context, width, mainColor, secondaryColor);
    finishedOkView =
        new FinishedOkView(context, width, mainColor, secondaryColor, checkMarkTintColor);
    finishedFailureView =
        new FinishedFailureView(context, width, mainColor, secondaryColor, failureMarkTintColor);
    percentIndicatorView = new PercentIndicatorView(context, width, textColor);
  }

  private void addComponentsViews() {
    addView(initialCenterCircleView);
    addView(rightCircleView);
    addView(sideArcsView);
    addView(topCircleBorderView);
    addView(mainCircleView);
    addView(finishedOkView);
    addView(finishedFailureView);
  }

  private void initAnimatorHelper() {
    viewAnimator = new ViewAnimator();
    viewAnimator.setAnimationListener(animationListener);
    viewAnimator.setComponentViewAnimations(initialCenterCircleView, rightCircleView, sideArcsView,
        topCircleBorderView, mainCircleView, finishedOkView, finishedFailureView,
        percentIndicatorView);
  }

  public void startIndeterminate() {
    startAnimationIndeterminate = true;
    startAnimation();
  }

  public void startDeterminate() {
    startAnimationDeterminate = true;
    startAnimation();
  }

  public void setPercent(int percent) {
    if (percentIndicatorView != null) {
      percentIndicatorView.setPercent(percent);
      if (percent == 100) {
        viewAnimator.finishOk();
      }
    }
  }

  public void stopOk() {
    if (viewAnimator == null) {
      stopAnimationOk = true;
    } else {
      viewAnimator.finishOk();
    }
  }

  public void stopFailure() {
    if (viewAnimator == null) {
      stopAnimationFailure = true;
    } else {
      viewAnimator.finishFailure();
    }
  }

  public void resetLoading() {
    if (viewAnimator != null) {
      viewAnimator.resetAnimator();
    }
    setPercent(0);
  }

  public void setAnimationListener(AnimationListener animationListener) {
    this.animationListener = animationListener;
  }

  public interface AnimationListener {

    void onAnimationEnd(boolean success);
  }
}
/*
*  <com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:id="@+id/circle_loading_view"
      android:layout_width="250dp"
      android:layout_height="250dp"
      android:background="@color/background"
      android:layout_centerInParent="true"
      app:animCircleLoadingView_mainColor="@color/main_color"
      app:animCircleLoadingView_secondaryColor="@color/secondary_color"
      app:animCircleLoadingView_textColor="@android:color/white"
  />
  *
  *private void startLoading() {
    animatedCircleLoadingView.startDeterminate();
  }

  private void startPercentMockThread() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1500);
          for (int i = 0; i <= 100; i++) {
            Thread.sleep(65);
            changePercent(i);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(runnable).start();
  }

  private void changePercent(final int percent) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        animatedCircleLoadingView.setPercent(percent);
      }
    });
  }

  public void resetLoading() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        animatedCircleLoadingView.resetLoading();
      }
    });
  }
* */