package com.aail.weatherapplicationv1;

import java.util.List;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyRecyclerAdapter extends
		RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
	private List<Data> lists;
	private Context mContext;

	SharedPreferences sharedPreference;

	public MyRecyclerAdapter(Context context, List<Data> lists) {
		this.lists = lists;
		this.mContext = context;
	}

	@Override
	public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.lists, null);

		CustomViewHolder viewHolder = new CustomViewHolder(view);
		return viewHolder;
	}

	public class CustomViewHolder extends RecyclerView.ViewHolder {
		protected ImageView imageView;
		protected TextView textView1;
		protected TextView textView2;
		protected TextView textView3;
		protected TextView texthigh;
		protected TextView textlow;
		// CardView card;
		RelativeLayout relativeLayout;
		SwipeLayout swipeLayout;
		Button buttonDelete;

		public CustomViewHolder(View view) {
			super(view);
			this.imageView = (ImageView) view.findViewById(R.id.imageView);
			this.textView1 = (TextView) view.findViewById(R.id.textView1);
			this.textView2 = (TextView) view.findViewById(R.id.textView2);
			this.textView3 = (TextView) view.findViewById(R.id.textView3);
			this.texthigh = (TextView) view.findViewById(R.id.texthigh);
			this.textlow = (TextView) view.findViewById(R.id.textlow);
			// this.card = (CardView)view.findViewById(R.id.cardview);
			relativeLayout = (RelativeLayout) view.findViewById(R.id.relative);
			swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
			buttonDelete = (Button) itemView.findViewById(R.id.delete);
			/*
			 * sharedPreference = mContext.getSharedPreferences("PREFS_NAME",
			 * Context.MODE_PRIVATE); SharedPreferences.Editor edit =
			 * sharedPreference.edit(); edit.putBoolean("FAVORITES", false);
			 * edit.apply();
			 */

			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					Log.d(getClass().getSimpleName(), "onItemSelected: "
							+ textView3.getText().toString());
					Toast.makeText(v.getContext(),
							"Location: " + textView3.getText().toString(),
							Toast.LENGTH_SHORT).show();

				}
			});
		}
	}

	@Override
	public void onBindViewHolder(final CustomViewHolder customViewHolder,
			final int i) {

		Data element = lists.get(i);

		/*
		 * Glide.with(mContext).load("http://i66.tinypic.com/2liivbo.png").
		 * centerCrop().placeholder(R.drawable.bg)
		 * .crossFade().into(customViewHolder.imageView);
		 */
		String image = element.getLocation();

		if ("Vijayawada".equals(image)) {
			Picasso.with(mContext)
					.load("http://media-cdn.tripadvisor.com/media/photo-s/03/a8/a7/f2/view-from-nh-5.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Tirupati".equals(image)) {
			Picasso.with(mContext)
					.load("http://wikitravel.org/upload/shared/9/93/Tirupathi.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}
		if ("Gannavaram".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.thehindu.com/multimedia/dynamic/01577/08VZ_VIJ_P2_FLIGHT_1577738f.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl)
					.into(customViewHolder.imageView);
		}

		if ("Mumbai".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://wikitravel.org/upload/en/e/ed/Gateway_of_India.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Guntur".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://wikitravel.org/shared/File:Guntur_Banner.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Visakhapatnam".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.urbannewsdigest.in/wp-content/uploads/2013/09/DSC_0017.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Nellore".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/5/53/Nellore_Montage.png")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Kurnool".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/c/c8/KurnoolSkyLine.jpeg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Rajahmundry".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/9/91/Rail_Cum_Road_Bridge_on_Godavari_at_Rajahmundry.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Kadapa".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/f/f4/Ameen_Peer_Dargah_in_the_City_of_Kadapa.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Kakinada".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/d/de/Godavari_fertilizers.JPG")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Anantapur".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/c/ca/Ananthapur_ISKCON.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Vizianagaram".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.onefivenine.com/images/districtimages/Andhra%20Pradesh/Vizianagaram/3.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Eluru".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.yatrastotemples.com/wp-content/gallery/sri-venkateswara-swamy-temple-chinna-tirupati-in-dwaraka-tirumala/Sri-Venkateswara-Swamy-Temple-Chinna-Tirupati-in-Dwaraka-Tirumala-4.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Ongole".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.veethi.com/watermark.php?path=images/city-images/fullsize/Ongole-10918.jpeg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Nandyal".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.aptdc.gov.in/LargeImage/Karnool/MahaNandi1.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Machilipatnam".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("http://www.thehindu.com/multimedia/dynamic/01441/26VJTAN01-MANGI_27_1441058e.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Hyderabad".equals(image)) {

			Picasso.with(mContext)
					.load("http://www.csicon2014.com/uploads/hyderabad.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}
		if ("New York".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/d/d3/Statue_of_Liberty%2C_NY.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Los Angeles".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/5/57/LA_Skyline_Mountains2.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Chicago".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/0/0b/ChicagovanafSearsTower.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Houston".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/4/44/Panoramic_Houston_skyline.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("San Diego".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/7/7c/SanDiegoSkylineBay.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("Dallas".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/f/f7/Xvisionx_Dallas_Stemmons.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("San Jose".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/en/1/1c/Downtown_San_Jose.PNG")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		}

		if ("San Francisco".equals(image)) {
			// Download image using picasso library
			Picasso.with(mContext)
					.load("https://upload.wikimedia.org/wikipedia/commons/0/07/FinancialNorth.jpg")
					.error(R.drawable.sl).placeholder(R.drawable.sl).fit()
					.into(customViewHolder.imageView);
		} else {
			customViewHolder.relativeLayout
					.setBackgroundResource(R.drawable.sl);
		}

		// Setting text view title
		customViewHolder.textView1.setText(element.getDescription());
		customViewHolder.textView2.setText(element.getTemperature() + "℃");
		customViewHolder.textView3.setText(element.getLocation());
		customViewHolder.texthigh.setText(element.getMax() + "℃");
		customViewHolder.textlow.setText(element.getMin() + "℃");

		// setting Background Resource
		//customViewHolder.imageView.setBackgroundResource(R.drawable.bg);

		customViewHolder.swipeLayout
				.addSwipeListener(new SwipeLayout.SwipeListener() {
					@Override
					public void onClose(SwipeLayout layout) {
						// when the SurfaceView totally cover the BottomView.
					}

					@Override
					public void onUpdate(SwipeLayout layout, int leftOffset,
							int topOffset) {
						// you are swiping.
					}

					@Override
					public void onStartOpen(SwipeLayout layout) {

					}

					@Override
					public void onOpen(SwipeLayout layout) {
						// when the BottomView totally show.
					}

					@Override
					public void onStartClose(SwipeLayout layout) {

					}

					@Override
					public void onHandRelease(SwipeLayout layout, float xvel,
							float yvel) {
						// when user's hand released.
					}
				});

		customViewHolder.buttonDelete
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						lists.remove(i);
						notifyItemRemoved(i);
						notifyItemRangeChanged(i, lists.size());
						Toast.makeText(
								view.getContext(),
								"Deleted "
										+ customViewHolder.textView3.getText()
												.toString() + "!",
								Toast.LENGTH_SHORT).show();
					}
				});

	}

	private void elseif(boolean equals) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getItemCount() {
		return (null != lists ? lists.size() : 0);
	}
}