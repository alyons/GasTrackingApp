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
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TripFragment.OnFragmentInteractionListener, AddTripFragment.OnAddTripFinishListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TripFragment tripFragment;
    AddTripFragment addTripFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            tripFragment = new TripFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content_container, tripFragment).commit();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(int id) {
        Toast.makeText(getApplicationContext(), String.format("You selected the item at index %d!", id), Toast.LENGTH_SHORT).show();
    }

    public void onAddTripFinish() {
        switchToTripFragment();
    }

    public void addTrip(View view) {
        switchToAddTripFragment();
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
}
