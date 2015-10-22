package net.alexanderlyons.firstlesson;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TripFragment.OnFragmentInteractionListener, AddTripFragment.OnAddTripFinishListener {

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

    public void confirmAddTrip(View view) {
        addTripFragment.onConfirmButtonPressed();
    }

    public void cancelAddTrip(View view) {
        addTripFragment.onCancelButtonPressed();
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
