package net.alexanderlyons.firstlesson;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Spinner;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.DataObjects.CarArrayAdapter;
import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.DataObjects.TripAdapterRealm;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarTripsOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarTripsOverview extends Fragment implements AbsListView.OnItemClickListener {

    @Bind(R.id.car_list_spinner) Spinner carSpinner;
    @Bind(R.id.car_overview_trip_list) AbsListView tripListView;

    CarArrayAdapter carAdapter;
    TripAdapterRealm tripAdapter;
    Realm realm;


    public static CarTripsOverview newInstance() {
        CarTripsOverview fragment = new CarTripsOverview();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CarTripsOverview() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

        realm = Realm.getDefaultInstance();
        RealmQuery<Car> carQuery = realm.where(Car.class);
        RealmResults<Car> carResults = carQuery.findAll();
        carAdapter = new CarArrayAdapter(getActivity(), carResults.toArray(new Car[carResults.size()]));

        RealmQuery<Trip> tripQuery = realm.where(Trip.class);
        RealmResults<Trip> tripResults = tripQuery.findAll();
        tripAdapter = new TripAdapterRealm(getActivity(), 0, tripResults, true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_trips_overview, container, false);

        // Bind all of the views with Butter Knife
        ButterKnife.bind(this, view);

        // Add my event listeners
        tripListView.setAdapter(tripAdapter);
        tripListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Do something here!
    }
}
