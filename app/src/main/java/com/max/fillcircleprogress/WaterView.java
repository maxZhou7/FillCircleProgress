package com.max.fillcircleprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class WaterView extends View {

    private RectF rectF;
    private Paint mPaint;
    private int width, height;
    private int circleWidth = 2;
    private int maxProgress = 100;
    private int currentProgress = 30;

    public WaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public void setProgress(int progress) {
        this.currentProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        int min = Math.min(width, height);
        width = height = min;
        rectF.left = rectF.top = circleWidth / 2;
        rectF.bottom = rectF.right = min - circleWidth;
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        float ratio = (float) currentProgress / maxProgress;
        float radius = (float) (min - circleWidth) / 2;
        float sY = 2 * radius * ratio;
        canvas.drawCircle(min / 2, min / 2, radius, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < sY; i++) {
            canvas.drawLine(radius - (float) Math.sqrt(radius * radius - (radius - i) * (radius - i)) + circleWidth,
                    radius + (radius - i), radius + (float) Math.sqrt(radius * radius - (radius - i) * (radius - i)), radius + (radius - i), mPaint);
        }

    }

}
