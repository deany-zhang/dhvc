package com.dhvc.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MenuImageView extends androidx.appcompat.widget.AppCompatImageView {
	/**
	 * MenuImageView对象的宽度
	 */
	private int vWidth;
	/**
	 * MenuImageView对象的高度
	 */
	private int vHeight;
	/**
	 * 正在缩小标志过程进行标志
	 */
	private boolean isReduce = false;
	/**
	 * 从缩小恢复到正常过程进行标志
	 */
	private boolean isRecover = false;
	/**
	 * 缩放倍数
	 */
	private float minScal = 0.9f;

	private ScaleStateListener listener;

	public ScaleStateListener getListener() {
		return listener;
	}

	public void setListener(ScaleStateListener listener) {
		this.listener = listener;
	}

	public MenuImageView(Context context) {
		super(context);
	}

	/**
	 * 在XML文件布置空间的时候需要含有AttributeSet参数的这个构造器，不然会报错
	 * 
	 * @param context
	 * @param attrs
	 */
	public MenuImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		initi();

		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG));
		super.onDraw(canvas);
	}

	private void initi() {
		vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
		vHeight = getHeight() - getPaddingTop() - getPaddingBottom();
	}

	private boolean isDo = false;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			sendMessage1(1);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			int width = MenuImageView.this.getWidth();
			int height = MenuImageView.this.getHeight();

            isDo = event.getY() <= height && event.getY() >= 0
                    && event.getX() >= 0 && event.getX() <= width;
			sendMessage1(2);
			break;
		default:
			break;
		}
		return true;// return super.onTouchEvent(event);的话捕捉不到UP动作
	}
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		float s = 0;// 缩放倍数
		int count = 0;// 计数器
		Matrix matrix = new Matrix();

		@Override
		public void handleMessage(Message msg) {
			matrix.set(getImageMatrix());
			View view = (View) msg.obj;
			super.handleMessage(msg);
			if (msg.what == 1) {
				if (isRecover) {
					// 如果ImageView处于从小图恢复到大图时候，进入等待
					sendMessage1(1);
				} else {
					isReduce = true;
					// 进行两次开方，为了是把minScal分四次缩放，是的缩放过程变慢
					count = 0;
					s = (float) Math.sqrt(Math.sqrt(minScal));
					doScale(s, matrix);
					sendMessage1(3);
				}

			} else if (msg.what == 2) {
				// 用1去除，求得倒数，用来还原
				if (isReduce) {
					sendMessage1(2);
				} else {
					isRecover = true;
					count = 0;
					s = (float) (Math.sqrt(Math.sqrt(1f / minScal)));
					doScale(s, matrix);
					sendMessage1(3);
				}

			} else if (msg.what == 3) {
				// 对缩分四次进行，达到更明显的缩放效果。
				doScale(s, matrix);
				if (count < 4) {
					sendMessage1(3);
				} else {
					// 缩放结束后，把两个状态量都设置为false，让另一个相反操作--缩（放）可以进入操作
					if (isRecover) {
						if (isDo)
							listener.onAnimationStop(view.getTag().toString());
					}
					isReduce = false;
					isRecover = false;
				}
				count++;
			}
		}

	};

	private void doScale(float size, Matrix matrix) {
		matrix.postScale(size, size, vWidth * 0.5f, vHeight * 0.5f);
		MenuImageView.this.setImageMatrix(matrix);
	}

	public void sendMessage1(int what) {
		Message msg = handler.obtainMessage(what);
		msg.obj = this;
		handler.sendMessage(msg);
	}

	/**
	 * 动画播放状态接口
	 * 
	 * @author t E-mail:wangh@appsino.com
	 * @version 创建时间：2013-3-19 下午2:12:21
	 * @Copyright (c) 2013 风语者科技（北京）.Co.Ltd. All rights reserved.
	 * 
	 */
	public interface ScaleStateListener {
		void onAnimationStop(String tag);
	}

}
