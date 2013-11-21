package cse.gui.paintapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * You can either copy this into your activity and use it as an inner class or leave it here your use this class externally.
 *
 */
public class BrushSizeAdapter extends ArrayAdapter<Object> {
	ArrayList<Drawable> mDrawables;

	public BrushSizeAdapter(Context context, int resource, int textViewResourceId,
			Object[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//do something here
		//If you want to set an image into a TextView programmatically, you can use the following method.
		//The 4 parameters are Drawables for left, top, right, and bottom of the text.
		//If you don't want to provide a drawable for one side, use null
		//Drawable d;
		//setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
		return convertView;
	}
}
