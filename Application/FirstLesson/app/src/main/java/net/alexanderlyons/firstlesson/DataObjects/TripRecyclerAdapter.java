package net.alexanderlyons.firstlesson.DataObjects;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.alexanderlyons.firstlesson.R;

import butterknife.Bind;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by PyroticBlaziken on 1/17/2016.
 */
public class TripRecyclerAdapter extends RealmBasedRecyclerViewAdapter<Trip, ViewHolder> {

    public TripRecyclerAdapter(Context context, RealmResults<Trip> realmResults) {
        super(context, realmResults, true, true);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.trip_adapter, viewGroup, false);
        return new ViewHolder((LinearLayout)view);
    }

    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Trip item = realmResults.get(position);

        String dateFormat = "Date: %tF";
        viewHolder.dateText.setText(String.format(dateFormat, item.getDate()));

        String distanceFormat = "Miles: %.2f";
        viewHolder.distanceText.setText(String.format(distanceFormat, item.getDistance()));

        String amountFormat = "Gallons: %.3f";
        viewHolder.amountText.setText(String.format(amountFormat, item.getFuelPurchased()));

        String priceFormat = "Price: $%.2f";
        viewHolder.priceText.setText(String.format(priceFormat, item.getFuelCost()));
    }
}

class ViewHolder extends RealmViewHolder {
    public TextView dateText;
    public TextView distanceText;
    public TextView amountText;
    public TextView priceText;

    public ViewHolder(LinearLayout container) {
        super(container);
        this.dateText = (TextView) container.findViewById(R.id.date_text);
        this.distanceText = (TextView) container.findViewById(R.id.distance_text);
        this.amountText = (TextView) container.findViewById(R.id.amount_text);
        this.priceText = (TextView) container.findViewById(R.id.price_text);
    }

}
