package com.wzhy.customviewdemos.customviews.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by techfit on 2017/5/19.
 */

public class DrawTextView extends View {

    private Paint mPaint;

    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //drawTextBase(canvas);
        //drawText_textAlign(canvas, 300, 200);
        //drawText_4Lines(canvas);
        //drawText_textBounds(canvas);
        //drawText_leftTop(canvas);
        drawText_centerLine(canvas);

    }


    private void drawBaseLine(Canvas canvas, int baseLineX, int baseLineY) {
        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);
    }


    private void drawTextBase(Canvas canvas) {
        int baseX = 120;
        int baseLineY = 200;
        //写文字
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(120f);
        canvas.drawText("changes", baseX, baseLineY, mPaint);
        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseLineY, 3000, baseLineY, mPaint);

        //绘制起点
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(8f);
        canvas.drawPoint(baseX, baseLineY, mPaint);
    }

    private void drawText_textAlign(Canvas canvas, int baseX, int baseY) {
        String text = "Align";
        String text1 = "AlignÂǎêňЙ";
        //文字大小和对齐
        mPaint.setTextSize(200);
        mPaint.setTextAlign(Paint.Align.LEFT);
        //mPaint.setTextAlign(Paint.Align.CENTER);
        //mPaint.setTextAlign(Paint.Align.RIGHT);
        //绘制文字
        mPaint.setColor(Color.BLACK);
        canvas.drawText(text1, baseX, baseY, mPaint);
        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseY, 3000, baseY, mPaint);

        //画起始线
        canvas.drawLine(baseX, 0, baseX, baseY + 60, mPaint);

        //绘制原点
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(8f);
        canvas.drawPoint(baseX, baseY, mPaint);
        mPaint.setStrokeWidth(2f);

    }

    private void drawText_4Lines(Canvas canvas) {
        /*
            //基线和四条线
            top        可绘制最高线     系统建议的，绘制单个字符时，字符应当的最高高度所在线
            ascent     建议绘制最高线     系统建议的，绘制单个字符时，字符应当的最低高度所在线
            baseLine   基线
            descent    建议绘制最低线     可绘制的最高高度所在线
            bottom     可绘制最低线     可绘制的最低高度所在线

            //FontMetrics
                fontMetrics.ascent      ascent = Y(ascent) - Y(baseLine)
                fontMetrics.descent     decent = Y(decent) - Y(baseLine)
                fontMetrics.top            top = Y(top)    - Y(baseLine)
                fontMetrics.bottom      bottom = Y(bottom) - Y(baseLine)

            //获得FontMetrics
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
         */
        int baseX = 60;
        int baseLineY = 300;

        drawText_textAlign(canvas, baseX, baseLineY);

        //计算四条线的位置
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float ascentY = fontMetrics.ascent + baseLineY;
        float descentY = fontMetrics.descent + baseLineY;
        float topY = fontMetrics.top + baseLineY;
        float bottomY = fontMetrics.bottom + baseLineY;

        //画top
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, topY, 3000, topY, mPaint);
        //画ascent
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, ascentY, 3000, ascentY, mPaint);
        //画descent
        mPaint.setColor(Color.MAGENTA);
        canvas.drawLine(0, descentY, 3000, descentY, mPaint);
        //画bottom
        mPaint.setColor(Color.CYAN);
        canvas.drawLine(0, bottomY, 3000, bottomY, mPaint);

    }

    private void drawText_textBounds(Canvas canvas) {
        //定义原点
        int baseX = 300;
        int baseLineY = 300;

        //定义要绘制的文字
        String text = "AgeÂǎЙ";

        //设置文字的大小和对齐方式
        mPaint.setTextSize(160f);
        mPaint.setTextAlign(Paint.Align.LEFT);


        /*字符串所占的高度和宽度*/
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int bottomY = fontMetricsInt.bottom + baseLineY;
        int topY = fontMetricsInt.top + baseLineY;
        //所占高度  int height = bottomY - topY;
        //宽度
        int width = (int) mPaint.measureText(text);

        //绘制所占区域
        Rect rect = new Rect(baseX, topY, baseX + width, bottomY);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect, mPaint);

        /*最小矩形*/
        Rect minRect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), minRect);
        Log.i("Rect", "minRect: " + minRect.toShortString());
        minRect.left += baseX;
        minRect.top += baseLineY;
        minRect.bottom += baseLineY;
        minRect.right += baseX;
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(minRect, mPaint);

        //绘制文字
        mPaint.setColor(Color.BLACK);
        canvas.drawText(text, baseX, baseLineY, mPaint);
        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseLineY, 3000, baseLineY, mPaint);

        //画起始线
        canvas.drawLine(baseX, 0, baseX, baseLineY + 60, mPaint);

        //绘制原点
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(8f);
        canvas.drawPoint(baseX, baseLineY, mPaint);
        mPaint.setStrokeWidth(2f);


//        float centerY = baseLineY + (fontMetricsInt.top + fontMetricsInt.bottom) / 2f;
//        canvas.drawLine(20, centerY, 3000, centerY, mPaint);
//
//        float centerY1 = baseLineY + (fontMetricsInt.ascent + fontMetricsInt.descent) / 2f;
//        canvas.drawLine(50, centerY1, 3000, centerY1, mPaint);
//
//        float centerY2 = (minRect.top + minRect.bottom) / 2f;
//        canvas.drawLine(80, centerY2, 3000, centerY2, mPaint);


    }

    private void drawText_leftTop(Canvas canvas) {
        String text = "AngelÂ";

        int topX = 60;
        int topY = 60;

        //设置paint
        mPaint.setTextSize(200);//单位：px
        mPaint.setTextAlign(Paint.Align.LEFT);

        //画左上顶点
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(8f);
        canvas.drawPoint(topX, topY, mPaint);
        mPaint.setStrokeWidth(2f);

        //画top线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, topY, 3000, topY, mPaint);

        //找到基线位置
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int baseLineY = topY - fontMetricsInt.top;

        //画基线
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, baseLineY, 3000, baseLineY, mPaint);

        /*写文字*/
        mPaint.setColor(Color.BLACK);
        canvas.drawText(text, topX, baseLineY, mPaint);
    }

    private void drawText_centerLine(Canvas canvas) {
        String text = "AngelÂ";

        int baseX = 120;
        int centerLineY = 200;

        //设置文字大小和文字排列
        mPaint.setTextSize(200);//单位px
        mPaint.setTextAlign(Paint.Align.LEFT);

        //画中线
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, centerLineY, 3000, centerLineY, mPaint);

        //计算基线位置
        /*
        * centerLineY  = (ascentLineY + descentLineY)/2
        * <=> centerLineY = (ascent + baselineY + descent + baselineY)/2
        * <=> centerLineY = baselineY + (ascent + descent)/2
        * <=>baselineY = centerLineY - (ascent + descent)/2
        * ∵ ascent = fontMetrics.ascent, descent = fontMetrics.descent
        * ∴ baseLineY = centerLineY - (fontMetrics.ascent + fontMetrics.descent)/2
        * */
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baselineY = centerLineY - (fontMetrics.top + fontMetrics.bottom) / 2;
        //float baselineY = centerLineY - (fontMetrics.ascent + fontMetrics.descent) / 2;

        //画基线
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, baselineY, 3000, baselineY, mPaint);

        //画文字
        mPaint.setColor(Color.BLACK);
        canvas.drawText(text, baseX, baselineY, mPaint);


        //画出其他几条线
        mPaint.setColor(Color.MAGENTA);
        float topY = baselineY + fontMetrics.top;
        float bottomY = baselineY + fontMetrics.bottom;
        float ascentY = baselineY + fontMetrics.ascent;
        float decentY = baselineY + fontMetrics.descent;

        canvas.drawLine(0, topY, 3000, topY, mPaint);
        canvas.drawLine(0, bottomY, 3000, bottomY, mPaint);
        canvas.drawLine(0, ascentY, 3000, ascentY, mPaint);
        canvas.drawLine(0, decentY, 3000, decentY, mPaint);
    }

}
