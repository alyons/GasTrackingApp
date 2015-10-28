package net.alexanderlyons.firstlesson.DataObjects;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import net.alexanderlyons.firstlesson.Helpers.StringHelper;
import net.alexanderlyons.firstlesson.R;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by PyroticBlaziken on 10/25/2015.
 */
public class CarArrayAdapter extends RealmBaseAdapter<Car> implements SpinnerAdapter {

    private static class MyViewHolder {
        LinearLayout carAdapter;
    }

    public CarArrayAdapter(Context context, int resID, RealmResults<Car> realmResults, boolean automaticUpdates) {
        super(context, realmResults, automaticUpdates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.car_adapter, parent, false);
        Car item = realmResults.get(position);
        Resources res = Resources.getSystem();

        TextView nicknameText = (TextView)rowView.findViewById(R.id.nickname_text);
        String usableNickname = (StringHelper.isNullOrWhitespace(item.getNickname())) ? item.getNickname() : item.getModel();
        nicknameText.setText(usableNickname);

        TextView dataText = (TextView)rowView.findViewById(R.id.data_text);
        dataText.setText(res.getString(R.string.car_data_format, item.getMake(), item.getModel(), item.getYear()));

        TextView cityText = (TextView)rowView.findViewById(R.id.cityMPG_text);
        cityText.setText(res.getString(R.string.city_mpg_display_format, item.getCityMPG()));

        TextView highwayText = (TextView)rowView.findViewById((R.id.highwayMPG_text));
        highwayText.setText(res.getString(R.string.highway_mpg_display_format, item.getHighwayMPG()));

        return rowView;
    }

}
