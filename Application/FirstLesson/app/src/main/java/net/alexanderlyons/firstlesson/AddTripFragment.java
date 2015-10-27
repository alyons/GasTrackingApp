package net.alexanderlyons.firstlesson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.alexanderlyons.firstlesson.DataObjects.Trip;
import net.alexanderlyons.firstlesson.Helpers.StringHelper;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddTripFragment.OnAddTripFinishListener} interface
 * to handle interaction events.
 * Use the {@link AddTripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTripFragment extends Fragment {
    private final static String DATE_PARAM = "net.alexanderlyons.firstlesson.ADD_DATE";
    private final static String DISTANCE_PARAM = "net.alexanderlyons.firstlesson.ADD_DISTANCE";
    private final static String AMOUNT_PARAM = "net.alexanderlyons.firstlesson.ADD_AMOUNT";
    private final static String PRICE_PARAM = "net.alexanderlyons.firstlesson.ADD_PRICE";

    private Long date;
    private Double distance;
    private Double amount;
    private Double price;

    private OnAddTripFinishListener mListener;

    private TextView dateText;
    private EditText distanceText;
    private EditText amountText;
    private EditText priceText;

    private Date usageDate;
    private DateFormat dateFormat;
    private Realm realm;

    public static AddTripFragment newInstance(Long date, Double distance, Double amount, Double price) {
        AddTripFragment fragment = new AddTripFragment();
        Bundle args = new Bundle();
        args.putLong(DATE_PARAM, date);
        args.putDouble(DISTANCE_PARAM, distance);
        args.putDouble(AMOUNT_PARAM, amount);
        args.putDouble(PRICE_PARAM, price);
        fragment.setArguments(args);
        return fragment;
    }

    public AddTripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.date = getArguments().getLong(DATE_PARAM);
            this.distance = getArguments().getDouble(DISTANCE_PARAM);
            this.amount = getArguments().getDouble(AMOUNT_PARAM);
            this.price = getArguments().getDouble(PRICE_PARAM);
        }

        dateFormat = DateFormat.getDateTimeInstance();
        //if (this.date != 0) usageDate = new Date(date);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);

        dateText = (TextView)view.findViewById(R.id.add_date_text);
        distanceText = (EditText)view.findViewById(R.id.add_distance_text);
        amountText = (EditText)view.findViewById(R.id.add_amount_text);
        priceText = (EditText)view.findViewById(R.id.add_price_text);

        return view;
    }

    public void onConfirmButtonPressed() {

        // Check that there is text in all of the fields
        // Validate that the text is correct

        if (usageDate == null) {
            Toast.makeText(getContext(), "You must have a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringHelper.isNullOrWhitespace(distanceText.getText().toString())) {
            Toast.makeText(getContext(), "You must have a distance", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String distanceString = distanceText.getText().toString();
            try {
                distance = Double.parseDouble(distanceString);
            } catch (NumberFormatException ex) {
                Toast.makeText(getContext(), "Distance must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (StringHelper.isNullOrWhitespace(amountText.getText().toString())) {
            Toast.makeText(getContext(), "You must have an amount", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String amountString = amountText.getText().toString();
            try {
                amount = Double.parseDouble(amountString);
            } catch (NumberFormatException ex) {
                Toast.makeText(getContext(), "Amount must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (StringHelper.isNullOrWhitespace(priceText.getText().toString())) {
            Toast.makeText(getContext(), "You must have a price", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String priceString = priceText.getText().toString();
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException ex) {
                Toast.makeText(getContext(), "Price must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Trip trip = new Trip(usageDate, distance, price, amount);
        realm.beginTransaction();
        realm.copyToRealm(trip);
        realm.commitTransaction();

        if (mListener != null) {
            mListener.onAddTripFinish();
        }
    }

    public void onCancelButtonPressed() {
        if (mListener != null) {
            mListener.onAddTripFinish();
        }
    }

    public void onDateSet(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        usageDate = calendar.getTime();
        date = usageDate.getTime();
        dateText.setText(dateFormat.format(usageDate));
    }

    public void onTimeSet(int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();

        if (usageDate != null) {
            calendar.setTime(usageDate);
        }

        int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, hourOfDay, minute);
        usageDate = calendar.getTime();
        date = usageDate.getTime();
        dateText.setText(dateFormat.format(usageDate));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity) {
            activity = (Activity)context;

            try {
                mListener = (OnAddTripFinishListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        } else {
            throw new ClassCastException(context.toString() + " must be an Activity.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAddTripFinishListener {
        public void onAddTripFinish();
    }
}
