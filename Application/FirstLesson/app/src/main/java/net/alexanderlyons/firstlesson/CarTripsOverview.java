package net.alexanderlyons.firstlesson;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.DataObjects.CarArrayAdapter;
import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.DataObjects.TripArrayAdapter;
import net.alexanderlyons.firstlesson.DataObjects.TripRecyclerAdapter;
import net.alexanderlyons.firstlesson.Helpers.MathHelper;

import java.util.ArrayList;
import java.util.List;

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
public class CarTripsOverview extends Fragment implements AdapterView.OnItemSelectedListener {

    List<Trip> tripList;

    @Bind(R.id.car_list_spinner) Spinner carSpinner;
    @Bind(R.id.realm_recycler_trip_list) RealmRecyclerView tripRecyclerView;

    CarArrayAdapter carAdapter;
    TripArrayAdapter tripArrayAdapter;
    TripRecyclerAdapter tripRecyclerAdapter;
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

        setUpRealm();
    }

    void setUpRealm() {
        realm = Realm.getDefaultInstance();
        RealmQuery<Car> carQuery = realm.where(Car.class);
        RealmResults<Car> carResults = carQuery.findAll();
        carAdapter = new CarArrayAdapter(getActivity(),0, carResults, true);

        RealmQuery<Trip> tripQuery = realm.where(Trip.class);
        RealmResults<Trip> tripResults = tripQuery.findAll();
        tripRecyclerAdapter = new TripRecyclerAdapter(getActivity(), tripResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_trips_overview, container, false);

        // Bind all of the views with Butter Knife
        ButterKnife.bind(this, view);


        tripList = new ArrayList<Trip>();
        tripArrayAdapter = new TripArrayAdapter(getActivity(), tripList);

        // Add my event listeners
        tripRecyclerView.setAdapter(tripRecyclerAdapter);
        tripRecyclerView.setOnRefreshListener(new RealmRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                asyncRefreshTrips();
            }
        });

        carSpinner.setAdapter(carAdapter);
        carSpinner.setOnItemSelectedListener(this);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter().getClass() == CarArrayAdapter.class) {
            CarArrayAdapter carAdapter = (CarArrayAdapter)parent.getAdapter();
            Car car = carAdapter.getItem(position);
            tripArrayAdapter.clear();
            tripArrayAdapter.addAll(car.getTrips());
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getAdapter().getClass() == CarArrayAdapter.class) {
            tripArrayAdapter.clear();
        }
    }

    // Methods for dealing with Realm Updates
    private void asyncRefreshTrips() {
        AsyncTask<Void, Void, Void> remotemItem = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Do nothing...
                }

                Realm instance = Realm.getDefaultInstance();
                final RealmResults<Trip> trips = instance.where(Trip.class).findAll();
                tripRecyclerAdapter = new TripRecyclerAdapter(getActivity(), trips);
                //instance.close()
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                tripRecyclerView.setRefreshing(false);
            }

        };

        remotemItem.execute();
    }
}
