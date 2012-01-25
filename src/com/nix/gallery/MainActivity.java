package com.nix.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity  extends Activity {
	/** Called when the activity is first created. */

	private int PicPosition;
	private Handler handler;
	private Gallery gallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		handler = new Handler();

		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setGravity(Gravity.CENTER_VERTICAL);
		final ImageView imageView = (ImageView) findViewById(R.id.imageView);
		gallery.setAdapter(new ImageAdapter(this, pics));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				imageView.setImageResource(pics[position]);
			}
		});
		(new Thread() {

			public void run() {

				myslideshow();
				handler.postDelayed(this, 2000); // execute every second.
			}
		}
		).start();

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageView.setImageResource(pics[position]);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// imageView.setImageResource(pics[position+1]);

			}
		});
	}

	private void myslideshow() {
		PicPosition = gallery.getSelectedItemPosition() + 1;
		if (PicPosition >= pics.length)
			gallery.setSelection(0); // stop
		else
			gallery.setSelection(PicPosition);// move to the next gallery
												// element.
	}

	Integer[] pics = { R.drawable.sp_1, R.drawable.sp_2,
			R.drawable.sp_3, R.drawable.sp_4, R.drawable.sp_5,
			R.drawable.sp_6, R.drawable.sp_7, R.drawable.sp_8};

}