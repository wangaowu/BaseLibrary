package com.unistrong.baselibs.ui.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

import com.unistrong.baselibs.utils.DensityUtils;

/**
 * stateListDrawable list背景图层
 */
public class SelectorDrawable extends StateListDrawable {

    public enum ShapeType {
        ROUND_RECT, OVAL, NONE
    }

    public SelectorDrawable(int normalResID, int pressedResID, Context context) {
        this(context.getResources().getDrawable(normalResID),
                context.getResources().getDrawable(pressedResID));
    }

    public SelectorDrawable(Drawable normalDrawable, Drawable pressedDrawable) {
        addToStateList(normalDrawable, pressedDrawable);
    }

    public SelectorDrawable(int normalColor, int pressedColor) {
        this(normalColor, pressedColor, ShapeType.NONE, null);
    }

    public SelectorDrawable(int normalColor, int pressedColor, ShapeType shapeType, Context context) {
        if (shapeType == ShapeType.NONE) {
            addToStateList(new ColorDrawable(normalColor), new ColorDrawable(pressedColor));
        } else {
            addToStateList(createShapeDrawable(normalColor, shapeType, context),
                    createShapeDrawable(pressedColor, shapeType, context));
        }
    }

    private RectF initBounds() {
        return new RectF(getBounds());
    }

    private float[] initRadius(Context context) {
        float[] floats = new float[8];  //length must be 8
        int radius = DensityUtils.dp2px(context, 10);
        for (int i = 0; i < 8; i++) {
            floats[i] = radius;
        }
        return floats;
    }

    @SuppressLint("NewApi")
    private Drawable createShapeDrawable(int color, ShapeType shapeType, Context context) {
        Shape shape;
        float[] radius = initRadius(context);
        switch (shapeType) {
            case ROUND_RECT:
                shape = new RoundRectShape(radius, initBounds(), radius);
                break;
            case OVAL:
                shape = new OvalShape();
                break;
            default:
                shape = new RectShape();
                break;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return shapeDrawable;
    }

    private void addToStateList(Drawable normalDrawable, Drawable pressedDrawable) {
        addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        addState(new int[]{}, normalDrawable);
    }
}
