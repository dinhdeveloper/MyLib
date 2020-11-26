package com.canhdinh.lib.grav.generator;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.canhdinh.lib.grav.generator.animation.GravAnimatorGenerator;
import com.canhdinh.lib.grav.generator.grav.BallGenerator;
import com.canhdinh.lib.grav.generator.grav.GravGenerator;
import com.canhdinh.lib.grav.generator.paint.PaintGenerator;
import com.canhdinh.lib.grav.generator.paint.RandomColorGenerator;
import com.canhdinh.lib.grav.generator.point.PointGenerator;
import com.canhdinh.lib.grav.generator.point.RegularPointGenerator;
import com.canhdinh.lib.grav.util.ClassUtil;


public class GeneratorFactory {
  private Context context;

  public GeneratorFactory(Context context) {
    this.context = context;
  }

  public PaintGenerator createPaint(String className, AttributeSet attrs) {
    if (className == null || className.isEmpty()) {
      return new RandomColorGenerator();
    }
    PaintGenerator paintGenerator = ClassUtil.getClassByName(className, PaintGenerator.class);
    paintGenerator.configure(attrs, context);
    return paintGenerator;
  }

  public PointGenerator createPoint(String className, AttributeSet attributeSet) {
    if (className == null || className.isEmpty()) {
      return new RegularPointGenerator();
    }
    PointGenerator generator = ClassUtil.getClassByName(className, PointGenerator.class);
    generator.configure(attributeSet, context);
    return generator;
  }

  public GravGenerator createGrav(String className, AttributeSet attrs) {
    if (className == null || className.isEmpty()) {
      return new BallGenerator();
    }
    GravGenerator generator = ClassUtil.getClassByName(className, GravGenerator.class);
    generator.configure(attrs, context);
    return generator;
  }

  @Nullable
  public GravAnimatorGenerator createAnimator(String className, AttributeSet attributeSet) {
    if (className == null || className.isEmpty()) {
      return null;
    }
    GravAnimatorGenerator generator = ClassUtil.getClassByName(className, GravAnimatorGenerator.class);
    generator.configure(attributeSet, context);
    return generator;
  }
}
