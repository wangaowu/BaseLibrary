package com.unistrong.baselibs.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.unistrong.baselibs.utils.DensityUtils;

@SuppressLint("AppCompatCustomView")
public class UnderLineTextView extends TextView {

    private int width;
    private int height;
    private boolean enableUnderLine;
    private int padding_hor;
    private int underline_height;


    public UnderLineTextView(Context context) {
        this(context, null);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUnderLineParams();
    }

    private void initUnderLineParams() {
        padding_hor = DensityUtils.dp2px(getContext(), 20);
        underline_height = DensityUtils.dp2px(getContext(), 5);
    }

    /**
     * 设置启用下划线
     *
     * @param enable
     */
    public void setEnableUnderLine(boolean enable) {
        this.enableUnderLine = enable;
        postInvalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Paint paint = getPaint();
        if (enableUnderLine) {
            RectF rectF = new RectF(padding_hor, height - underline_height,
                    width - padding_hor, height);
            canvas.drawRect(rectF, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getSize(widthMeasureSpec, DensityUtils.dp2px(getContext(), 150));
        height = getSize(heightMeasureSpec, DensityUtils.dp2px(getContext(), 30));
    }

    private int getSize(int sizeMeasureSpec, int atMost) {
        int size = 0;
        switch (MeasureSpec.getMode(sizeMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                size = MeasureSpec.getSize(sizeMeasureSpec);
                break;
            case MeasureSpec.UNSPECIFIED:
                size = atMost;
                DensityUtils.dp2px(getContext(), 30);
                break;
        }
        return size;
    }


}
