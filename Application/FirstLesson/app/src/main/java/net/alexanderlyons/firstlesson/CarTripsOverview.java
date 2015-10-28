package net.alexanderlyons.firstlesson;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.DataObjects.CarArrayAdapter;
import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.DataObjects.TripAdapterRealm;
import net.alexanderlyons.firstlesson.Helpers.MathHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarTripsOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarTripsOverview extends Fragment {

    @Bind(R.id.car_list_spinner) Spinner carSpinner;
    @Bind(R.id.car_overview_trip_list) SwipeMenuListView tripListView;

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

        setUpRealm();
    }

    void setUpRealm() {
        realm = Realm.getDefaultInstance();
        RealmQuery<Car> carQuery = realm.where(Car.class);
        RealmResults<Car> carResults = carQuery.findAll();
        carAdapter = new CarArrayAdapter(getActivity(), carResults.toArray(new Car[carResults.size()]));

        RealmQuery<Trip> tripQuery = realm.where(Trip.class);
        RealmResults<Trip> tripResults = tripQuery.findAll();
        tripAdapter = new TripAdapterRealm(getActivity(), 0, tripResults, true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_trips_overview, container, false);

        // Bind all of the views with Butter Knife
        ButterKnife.bind(this, view);

        setUpSwipeMenu();

        // Add my event listeners
        tripListView.setAdapter(tripAdapter);

        carSpinner.setAdapter(carAdapter);

        return view;
    }

    void setUpSwipeMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setWidth(MathHelper.dipToPixels(getContext(), 90));
                deleteItem.setTitle("Delete");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(18);
                menu.addMenuItem(deleteItem);
            }
        };

        tripListView.setMenuCreator(creator);

        tripListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Trip trip = tripAdapter.getItem(position);
                switch (index) {
                    case 0:
                        delete(trip);
                        tripAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    private void delete(Trip trip) {
        realm.beginTransaction();
        trip.removeFromRealm();
        realm.commitTransaction();
    }

    public interface OnCarTripsOverviewInteractionListener {
        public void onItemSelected(int position);
    }

}
