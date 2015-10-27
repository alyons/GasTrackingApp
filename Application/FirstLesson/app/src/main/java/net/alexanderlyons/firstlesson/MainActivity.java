package net.alexanderlyons.firstlesson;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.DataObjects.CarArrayAdapter;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements TripFragment.OnFragmentInteractionListener, AddTripFragment.OnAddTripFinishListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, Spinner.OnItemSelectedListener {

    TripFragment tripFragment;
    AddTripFragment addTripFragment;
    CarFragment carFragment;
    AddCarFragment addCarFragment;

    Realm realm;

    Spinner carSpinner;
    CarArrayAdapter carArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.carSpinner = (Spinner)findViewById(R.id.main_car_spinner);
        //this.carSpinner.setOnItemSelectedListener(this);

        realm = Realm.getDefaultInstance();
        RealmQuery<Car> query = realm.where(Car.class);
        RealmResults<Car> results = query.findAll();
        Car[] cars = results.toArray(new Car[results.size()]);
        carArrayAdapter = new CarArrayAdapter(getApplicationContext(), cars);

        if (savedInstanceState == null) {
            //tripFragment = new TripFragment();
            //getSupportFragmentManager().beginTransaction().add(R.id.content_container, tripFragment).commit();
            //carFragment = new CarFragment();
            //getSupportFragmentManager().beginTransaction().add(R.id.content_container, carFragment).commit();
        }
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
            Toast.makeText(getApplicationContext(), String.format("Opening Car List..."), Toast.LENGTH_SHORT).show();
            switchToCarFragment();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), String.format("Opening Settings..."), Toast.LENGTH_SHORT).show();
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
        switchToTripFragment();
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

    // Car List Interactions
    public void onCarSelected(int id) {

    }

    // Add Car Interactions
    public void cancelAddCar(View view) {

    }

    public void confirmAddCar(View view) {

    }

    // Spinner Methods
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected so we are going to do a thing here.
        Toast.makeText(getApplicationContext(), String.format("You selected the car at position %d!", pos), Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "You didn't select a car...", Toast.LENGTH_SHORT).show();
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
                .add(R.id.content_container, addCarFragment,"add_car")
                .addToBackStack("add_car")
                .commit();
    }

    void switchToCarFragment() {
        carFragment = new CarFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, carFragment, "car_list")
                .addToBackStack("car_list")
                .commit();
    }
}
