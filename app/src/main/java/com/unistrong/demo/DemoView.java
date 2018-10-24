package com.unistrong.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.unistrong.baselibs.utils.DensityUtils;

public class DemoView extends View {
    private static final String TAG = "DemoView";
    private int height;
    private int width;
    private int padding;
    private Paint paint;
    private Camera camera;
    private Matrix matrix = new Matrix();
    private Bitmap bitmap;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObject();
    }

    private void initObject() {
        paint = new Paint();
        paint.setColor(0xfff00000);
        camera = new Camera();
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.demo_bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        camera.save();
        camera.setLocation(0, 0, -8);//默认 ,该处单位为inch英尺
//        camera.rotateZ(90);
//        camera.translate(width / 2, -height / 2, 0);
//        camera.rotateY(30);
        camera.rotateZ(30);
// 1.       camera.getMatrix(matrix);
// 2.      canvas.concat(matrix);
        camera.applyToCanvas(canvas);  //等价1+2
        camera.restore();

//        canvas.drawRect(new RectF(rect), paint);
        Rect srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //  Rect destRect = new Rect(padding, padding, width - padding, height - padding);
        Rect destRect = new Rect(0, 0, width, height);
        canvas.drawBitmap(bitmap, srcRect, destRect, paint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            this.width = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            this.width = DensityUtils.dp2px(getContext(), 100);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            this.height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            this.height = DensityUtils.dp2px(getContext(), 100);
        }
        this.padding = DensityUtils.dp2px(getContext(), 10);
    }
}
