package com.dhvc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dhvc.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClockView extends View {

    private final Context mContext;
    private Paint clockPaint;// 表盘画笔
    private Paint textPaint;// 文字画笔
    private Paint secondPaint;// 秒针画笔
    /* 通知视图刷新的线程 */
    private Thread refreshThread;
    private boolean isStop = false;
    /* 用于获取当前秒数 */
    Date currentDate = new Date();
    SimpleDateFormat sp = new SimpleDateFormat("ss", Locale.getDefault());
    /* 更新视图 */
    private final RefreshHandler mHandler = new RefreshHandler(Looper.getMainLooper(),this);

    public ClockView(Context context) {
        this(context,null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置宽高一致
        int width = reMeasure(getMeasuredHeight(),widthMeasureSpec);
        int height = reMeasure(getMeasuredWidth(),heightMeasureSpec);
        setMeasuredDimension(width,height);
        initPaint(width);
    }

    private int reMeasure(int size,int measureSpec){
        int measureMode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        switch (measureMode){
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                return Math.min(size, measureSize);
            case MeasureSpec.UNSPECIFIED:
                return measureSize;
            default:
                break;
        }
        return measureSpec;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initPaint(int width){
        // 表盘画笔
        clockPaint = new Paint();
        clockPaint.setColor(mContext.getColor(R.color.black));// 颜色
        clockPaint.setAntiAlias(true);// 抗锯齿
        clockPaint.setStyle(Paint.Style.STROKE);// 样式
        clockPaint.setStrokeWidth(width/80f);// 宽度

        //字体画笔
        textPaint = new Paint();
        textPaint.setColor(mContext.getColor(R.color.black));
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(width/16f);
        textPaint.setTextAlign(Paint.Align.CENTER);

        // 秒针画笔
        secondPaint = new Paint();
        secondPaint.setColor(mContext.getColor(R.color.purple_200));
        secondPaint.setAntiAlias(true);
        secondPaint.setStyle(Paint.Style.FILL);
        secondPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取画布中心坐标
        int x = getWidth() / 2;
        // 表中心坐标
        int y = getHeight() / 2;

        // 绘制表盘
        canvas.drawCircle(x, y,getWidth()/40f, clockPaint);// 表盘中心
        canvas.drawCircle(x, y, x -getWidth()/40f, clockPaint);// 表盘边框

        // 绘制刻度
        for(int i = 1;i <= 60;i++){
            canvas.rotate(6, x, y);
            if(i%5 == 0){
                // 绘制大刻度
                canvas.drawLine(x, getWidth()*3/80f, x, getWidth()*5/80f, clockPaint);
            }else{
                // 绘制小刻度
                canvas.drawLine(x, getWidth()*3/80f, x, getWidth()/20f, clockPaint);
            }
        }

        // 绘制 1-12小时 字体
        for(int i = 1;i <= 60;i++){
            if(i%5 == 0){
                float x1 = (float) Math.sin(Math.toRadians(6 * i)) * (y * 3 / 4f) + x;
                float y1 = y - (float) Math.cos(Math.toRadians(6 * i)) * (y * 3 / 4f) + getWidth()/40f;
                canvas.drawText(String.valueOf(i/5), x1, y1, textPaint);
            }
        }

        canvas.drawCircle(x, y,getWidth()/80f, secondPaint);
        // 获取当前秒数
        currentDate.setTime(System.currentTimeMillis());
        int ss = Integer.parseInt(sp.format(currentDate));
        // 绘制秒针
        float sin = (float) Math.sin(Math.toRadians(6 * ss));
        float cos = (float) Math.cos(Math.toRadians(6 * ss));
        float x0 = x - sin * (y / 10f);
        float y0 = y + cos * (y / 10f);
        float x1 = x + sin * (y * 13 / 20f);
        float y1 = y - cos * (y * 13 / 20f);
        canvas.drawLine(x0, y0, x1, y1, secondPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (!isStop) {
                    SystemClock.sleep(1000);
                    mHandler.sendEmptyMessage(0);
                }
            }
        };
        refreshThread.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
        isStop = true;
        refreshThread = null;
    }

    private static class RefreshHandler extends Handler {
        WeakReference<View> reference;

        public RefreshHandler(@NonNull Looper looper, View view) {
            super(looper);
            reference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(reference != null){
                if(msg.what == 0){
                    View view = reference.get();
                    view.invalidate();
                }
            }
        }
    }
}
