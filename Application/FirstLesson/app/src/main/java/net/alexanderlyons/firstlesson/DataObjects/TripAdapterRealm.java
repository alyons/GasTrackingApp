package net.alexanderlyons.firstlesson.DataObjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.R;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by PyroticBlaziken on 10/22/2015.
 */
public class TripAdapterRealm extends RealmBaseAdapter<Trip> implements ListAdapter {

    private static class MyViewHolder {
        LinearLayout tripAdapter;
    }

    public TripAdapterRealm(Context context, int resId, RealmResults<Trip> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.trip_adapter, parent, false);
        Trip item = realmResults.get(position);

        TextView dateText = (TextView)rowView.findViewById(R.id.date_text);
        String dateFormat = this.context.getResources().getString(R.string.date_display);
        dateText.setText(String.format(dateFormat, item.getDate()));

        TextView distanceText = (TextView)rowView.findViewById(R.id.distance_text);
        String distanceFormat = this.context.getResources().getString(R.string.distance_display);
        distanceText.setText(String.format(distanceFormat, item.getDistance()));

        TextView amountText = (TextView)rowView.findViewById(R.id.amount_text);
        String amountFormat = this.context.getResources().getString(R.string.amount_display);
        amountText.setText(String.format(amountFormat, item.getFuelPurchased()));

        TextView priceText = (TextView)rowView.findViewById(R.id.price_text);
        String priceFormat = this.context.getResources().getString(R.string.price_display);
        priceText.setText(String.format(priceFormat, item.getFuelCost()));

        return rowView;
    }

}
