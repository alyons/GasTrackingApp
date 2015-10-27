package net.alexanderlyons.firstlesson.DataObjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.R;

import java.util.List;

/**
 * Created by PyroticBlaziken on 10/19/2015.
 */
public class TripArrayAdapter extends ArrayAdapter<Trip> {

    private final Context context;
    private final List<Trip> values;

    public TripArrayAdapter(Context context, List<Trip> values) {
        super(context, R.layout.trip_adapter, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.trip_adapter, parent, false);

        TextView dateText = (TextView)rowView.findViewById(R.id.date_text);
        String dateFormat = this.context.getResources().getString(R.string.date_display);
        dateText.setText(String.format(dateFormat, this.values.get(position).getDate()));

        TextView distanceText = (TextView)rowView.findViewById(R.id.distance_text);
        String distanceFormat = this.context.getResources().getString(R.string.distance_display);
        distanceText.setText(String.format(distanceFormat, this.values.get(position).getDistance()));

        TextView amountText = (TextView)rowView.findViewById(R.id.amount_text);
        String amountFormat = this.context.getResources().getString(R.string.amount_display);
        amountText.setText(String.format(amountFormat, this.values.get(position).getFuelPurchased()));

        TextView priceText = (TextView)rowView.findViewById(R.id.price_text);
        String priceFormat = this.context.getResources().getString(R.string.price_display);
        priceText.setText(String.format(priceFormat, this.values.get(position).getFuelCost()));

        return rowView;
    }

}
