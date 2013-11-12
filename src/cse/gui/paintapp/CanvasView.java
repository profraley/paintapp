package cse.gui.paintapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {
	private ArrayList<Point> mSavedPoints;
	private ArrayList<Point> mRecordPoints;
	private Paint mPaint;
	private Bitmap mBitmap;
	private static final int TRACE_COLOR = 0xFFf624c8;
	private boolean mDrawBg = true;
	
	public CanvasView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mSavedPoints = new ArrayList<Point>();
		mRecordPoints = new ArrayList<Point>();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStrokeWidth(5);
		mPaint.setStrokeCap(Cap.ROUND);
	}
	
	/**
	 * Records a brush point for where the user is currently drawing. This is used to show
	 * the users current draw stroke. Call this during a touch gesture.
	 * @param x The X coordinate for the trace point
	 * @param y The Y coordinate for the trace point
	 */
	public void recordBrushPoint(int x, int y) {
		mRecordPoints.add(new Point(x, y));
		invalidate(x-10, y-10, x+10, y+10);
	}
	
	/**
	 * Saves any recorded points to the canvas. Call this after a touch gesture has
	 * completed.
	 */
	public void applyBrushStrokeToCanvas() {
		for (Point point : mRecordPoints) {
			mSavedPoints.add(point);
		}
		mRecordPoints.clear();
		invalidate();
	}
	
	/**
	 * Erases everything on the Canvas and returns it to its original state.
	 */
	public void clearCanvas() {
		mSavedPoints.clear();
		mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		invalidate();
	}
	
	@Override
	public void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
		if (changed) {
			mBitmap = Bitmap.createBitmap(right-left, bottom-top, Bitmap.Config.ARGB_8888);
		}
	}
	
	@Override
	public void dispatchDraw(android.graphics.Canvas canvas) {
		super.dispatchDraw(canvas);
		Canvas can = new Canvas(mBitmap);
		final int color = mPaint.getColor();

		for (int i = 0; i < mSavedPoints.size() - 1; i++) {
			Point p1 = mSavedPoints.get(i);
			Point p2 = mSavedPoints.get(i+1);
			can.drawLine(p1.x, p1.y, p2.x, p2.y, mPaint);
		}
		
		mSavedPoints.clear();
		canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		
		mPaint.setColor(TRACE_COLOR);
		
		for (int i = 0; i < mRecordPoints.size() - 1; i++) {
			Point p1 = mRecordPoints.get(i);
			Point p2 = mRecordPoints.get(i+1);
			canvas.drawLine(p1.x, p1.y, p2.x, p2.y, mPaint);
		}
		
		mPaint.setColor(color);
	}
	
	/**
	 * Set the color of the paint for the brush.
	 * @param color An ARGB representation of a color.
	 */
	public void setPaintColor(int color) {
		mPaint.setColor(color);
	}
	
	public void setBgVisible(boolean visible) {
		mDrawBg = visible;
		//clearBitmap(getWidth(), getHeight());
		final int alpha = visible ? 255 : 0;
		getBackground().setAlpha(alpha);
		invalidate();
	}
	
	public boolean getBgVisibility() {
		return mDrawBg;
	}
}
