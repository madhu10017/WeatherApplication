package com.aail.weatherapplicationv1;

import java.util.ArrayList;
import java.util.List;

import com.daimajia.swipe.SwipeLayout;
import com.aail.weatherapplicationv1.MainActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewAdapter extends 
RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

private List<String> itemsName;
private OnItemClickListener onItemClickListener;
private LayoutInflater layoutInflater;
TextView textItemName,grid_temp;
String gtemp=MainActivity.g_temp;


// protected SwipeItemRecyclerMangerImpl mItemManger = new
// SwipeItemRecyclerMangerImpl(this);

public RecyclerViewAdapter(Context context) {
layoutInflater = LayoutInflater.from(context);
itemsName = new ArrayList<String>();
}

public void removeItem(int position) {
itemsName.remove(position);
notifyItemRemoved(position);
}

@Override
public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup parent,
	int viewType) {
View itemView = layoutInflater.inflate(R.layout.grid_layout_item, parent,
		false);
return new ItemHolder(itemView, this);
}

@Override
public void onBindViewHolder(final RecyclerViewAdapter.ItemHolder holder,
	final int position) {
holder.setItemName(itemsName.get(position));

holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {

		itemsName.remove(position);
		notifyItemRemoved(position);
		notifyItemRangeChanged(position, itemsName.size());

		Toast.makeText(
				view.getContext(),
				"Deleted " + holder.textItemName.getText().toString()
						+ "!", Toast.LENGTH_SHORT).show();
	}
});

}

@Override
public int getItemCount() {
return itemsName.size();
}

public void setOnItemClickListener(OnItemClickListener listener) {
onItemClickListener = listener;
}

public OnItemClickListener getOnItemClickListener() {
return onItemClickListener;
}

public interface OnItemClickListener {
public void onItemClick(ItemHolder item, int position);
}

public void add(int location, String iName) {
itemsName.add(location, iName);
notifyItemInserted(location);
}

public void remove(int location) {
if (location >= itemsName.size())
	return;

itemsName.remove(location);
notifyItemRemoved(location);
}

public class ItemHolder extends RecyclerView.ViewHolder implements
	View.OnClickListener {

private RecyclerViewAdapter parent;
TextView textItemName;
Button buttonDelete;
SwipeLayout swipeLayout;

public void RecyclerArrayAdapter() {
	setHasStableIds(true);
}

public ItemHolder(View itemView, RecyclerViewAdapter parent) {
	super(itemView);
	itemView.setOnClickListener(this);
	this.parent = parent;
	
	grid_temp=(TextView) itemView.findViewById(R.id.grid_temp);
	grid_temp.setText(gtemp +" F");
	textItemName = (TextView) itemView.findViewById(R.id.grid_item_name);
	buttonDelete = (Button) itemView.findViewById(R.id.delete);

	itemView.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(getClass().getSimpleName(), "onItemSelected: "
					+ textItemName.getText().toString());
			Toast.makeText(
					v.getContext(),
					"onItemSelected: "
							+ textItemName.getText().toString(),
					Toast.LENGTH_SHORT).show();

		}
	});
}

public void setItemName(CharSequence name) {
	textItemName.setText(name);
}

public CharSequence getItemName() {
	return textItemName.getText();
}

@Override
public void onClick(View v) {
	final OnItemClickListener listener = parent
			.getOnItemClickListener();
	if (listener != null) {
		listener.onItemClick(this, getPosition());
	}
}

}
}
