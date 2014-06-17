package com.example.simpleimageswitch;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private ImageButton imageButton;
		private ImageSwitcher imageSwitcher;

		int imageIds[] = { R.drawable.image1, R.drawable.image2,
				R.drawable.image3, R.drawable.image4, R.drawable.image5,
				R.drawable.image6, };
		int messageCount = imageIds.length;
		// to keep current Index of ImageID array
		int currentIndex = -1;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			imageButton = (ImageButton) rootView
					.findViewById(R.id.imageButton1);
			imageSwitcher = (ImageSwitcher) rootView
					.findViewById(R.id.imageSwitcher1);
			// imageSwitcher.setImageResource(R.drawable.ic_launcher);

			imageSwitcher.setFactory(new ViewFactory() {

				@Override
				public View makeView() {
					// TODO Auto-generated method stub
					ImageView myView = new ImageView(getActivity());
					myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
					myView.setLayoutParams(new ImageSwitcher.LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT));
					return myView;
				}
			});

			Animation in = AnimationUtils.loadAnimation(getActivity(),
					android.R.anim.slide_in_left);
			Animation out = AnimationUtils.loadAnimation(getActivity(),
					android.R.anim.slide_out_right);

			imageSwitcher.setInAnimation(in);
			imageSwitcher.setOutAnimation(out);

			imageButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					currentIndex++;

					if(currentIndex == messageCount)
						currentIndex = 0;
					imageSwitcher.setImageResource(imageIds[currentIndex]);
				}
			});

			return rootView;
		}
	}

}
