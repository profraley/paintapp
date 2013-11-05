package cse.gui.paintapp;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class PaintActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		final View v = findViewById(R.id.canvas);
		
		//TODO Remove this code. It's purpose is only to demonstrate the demo.
		v.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				v.getViewTreeObserver().removeOnPreDrawListener(this);
				demoFunction();
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paint, menu);
		return true;
	}
	
	private class CanvasTouchListener implements View.OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			final int x = (int) event.getX();
			final int y = (int) event.getY();
			switch (action) {
				case MotionEvent.ACTION_DOWN :
					//notify the view a down event has happened. This is the
					//mechanism that triggers the beginning of a click and long press.
					break;
				case MotionEvent.ACTION_MOVE :
					//notify the view a move event has happened. This could
					//be used to scroll a view.
					break;
				case MotionEvent.ACTION_UP :
				case MotionEvent.ACTION_CANCEL :
					//notify the view the touch event has ended
					//usually you'll process the up and the cancel as if they
					//were the same event.
					break;
			}
			
			//return true if the view consumed the event and false if not
			//so one of this view's parent can receive the touch event and
			//process.
			return true;
		}
	}
	

	//TODO Remove this code. It is only used for demonstration purposes to show you
	//how to use the CanvasView API
	private void demoFunction() {
		CanvasView canvas = (CanvasView) findViewById(R.id.canvas);
		final int w = canvas.getWidth();
		final int h = canvas.getHeight();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			canvas.recordBrushPoint(x, y);
		}
		
		canvas.applyBrushStrokeToCanvas();
		
	}

}
