package com.soapgu.drawboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawingView extends View {
    private Paint brush;
    private List<InkStrokeEntity> strokes = new ArrayList<>();

    private InkStrokeEntity current;

    private int color = Color.RED;
    private float thickness = 20f;

    public DrawingView(Context context) {
        super(context);
        setupPaint();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupPaint();
    }

    private void setupPaint() {
        brush = new Paint();
        brush.setColor(Color.RED);
        brush.setAntiAlias(true);
        brush.setStrokeWidth(5f); // 设置笔触宽度
        brush.setStyle(Paint.Style.STROKE); // 设置绘制样式为描边
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for( InkStrokeEntity stroke : this.strokes ){
            Path path = stroke.createPath();
            brush.setColor( stroke.getColor() );
            brush.setStrokeWidth( stroke.getThickness() );
            canvas.drawPath(path, brush);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                current = new InkStrokeEntity(this.color,this.thickness);
                strokes.add( current );
                current.add( touchX,touchY );
                break;
            case MotionEvent.ACTION_MOVE:
                current.add( touchX,touchY );
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        // 重绘视图
        invalidate();
        return true;
    }

    public void setBrushColor(int color) {
        this.color = color;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}
