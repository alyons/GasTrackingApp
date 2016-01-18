package net.alexanderlyons.firstlesson;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.DataObjects.CarArrayAdapter;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements TripFragment.OnFragmentInteractionListener, AddTripFragment.OnAddTripFinishListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AddCarFragment.OnAddCarFinishListener {

    public static final String ADD_TRIP_FRAGMENT_NAME = AddTripFragment.class.toString();
    public static final String CAR_LIST_FRAGMENT_NAME = CarFragment.class.toString();
    public static final String SETTINGS_FRAGMENT_NAME = SettingsFragment.class.toString();

    TripFragment tripFragment;
    AddTripFragment addTripFragment;
    CarFragment carFragment;
    AddCarFragment addCarFragment;
    CarTripsOverview tripsFragment;
    SettingsFragment settingsFragment;

    Realm realm;

    CarArrayAdapter carArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        RealmQuery<Car> query = realm.where(Car.class);
        RealmResults<Car> results = query.findAll();
        carArrayAdapter = new CarArrayAdapter(getApplicationContext(), 0, results, true);

        if (savedInstanceState == null) {

        }

        switchToCarTripsFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_car_list) {
            switchToCarFragment();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(getApplicationContext(), String.format("Opening Settings..."), Toast.LENGTH_SHORT).show();
            switchToSettingsFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Button Interactions
    public void addTrip(View view) {
        switchToAddTripFragment();
    }

    // Trip List Interactions
    public void onFragmentInteraction(int id) {
        Toast.makeText(getApplicationContext(), String.format("You selected the item at index %d!", id), Toast.LENGTH_SHORT).show();
    }

    // Add Trip Interactions
    public void onAddTripFinish() {
        switchToCarTripsFragment();
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(this.getClass().toString(),String.format("Called on Time Set: [%2d:%2d]", hourOfDay, minute));
        addTripFragment.onTimeSet(hourOfDay, minute);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d(this.getClass().toString(),String.format("Called on Date Set: %2d/%2d/%4d", month, day, year));
        addTripFragment.onDateSet(year, month, day);
    }

    public void confirmAddTrip(View view) {
        addTripFragment.onConfirmButtonPressed();
    }

    public void cancelAddTrip(View view) {
        addTripFragment.onCancelButtonPressed();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // Car Overview Interactions
    public void addCar(View view) {
        switchToAddCarFragment();
    }

    // Add Car Interactions
    @Override
    public void onAddCarFinish() {
        switchToCarTripsFragment();
    }

    public void cancelAddCar(View view) {
        addCarFragment.onCancelPressed();
    }

    public void confirmAddCar(View view) {
        addCarFragment.onConfirmPressed();
    }

    // Fragment Switch Interactions
    void switchToTripFragment() {
        tripFragment = new TripFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, tripFragment)
                .commit();
    }

    void switchToAddTripFragment() {
        addTripFragment = new AddTripFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, addTripFragment)
                .commit();
    }

    void switchToAddCarFragment() {
        addCarFragment = new AddCarFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, addCarFragment)
                .commit();
    }

    void switchToCarFragment() {
        carFragment = new CarFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, carFragment, CAR_LIST_FRAGMENT_NAME)
                .addToBackStack(CAR_LIST_FRAGMENT_NAME)
                .commit();
    }

    void switchToCarTripsFragment() {
        tripsFragment = new CarTripsOverview();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, tripsFragment)
                .commit();
    }

    void switchToSettingsFragment() {
        settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, settingsFragment, SETTINGS_FRAGMENT_NAME)
                .addToBackStack(SETTINGS_FRAGMENT_NAME)
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        public void onCreatePreferences(Bundle savedInstance, String key) {
            // Do nothing here...?
        }

        @Override
        public void onCreate(Bundle savedInstance) {
            super.onCreate(savedInstance);

            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
