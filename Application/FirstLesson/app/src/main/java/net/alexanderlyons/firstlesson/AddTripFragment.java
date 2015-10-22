package net.alexanderlyons.firstlesson;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddTripFragment.OnAddTripFinishListener} interface
 * to handle interaction events.
 * Use the {@link AddTripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTripFragment extends Fragment {
    private final static String ADDED_TRIP = "net.alexanderlyons.firstlesson.ADDED_TRIP";
    private final static String DATE_PARAM = "net.alexanderlyons.firstlesson.ADD_DATE";
    private final static String DISTANCE_PARAM = "net.alexanderlyons.firstlesson.ADD_DISTANCE";
    private final static String AMOUNT_PARAM = "net.alexanderlyons.firstlesson.ADD_AMOUNT";
    private final static String PRICE_PARAM = "net.alexanderlyons.firstlesson.ADD_PRICE";

    private Long date;
    private Double distance;
    private Double amount;
    private Double price;

    private OnAddTripFinishListener mListener;

    private EditText dateText;
    private EditText distanceText;
    private EditText amountText;
    private EditText priceText;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);

        dateText = (EditText)view.findViewById(R.id.add_date_text);
        distanceText = (EditText)view.findViewById(R.id.add_distance_text);
        amountText = (EditText)view.findViewById(R.id.add_amount_text);
        priceText = (EditText)view.findViewById(R.id.add_price_text);

        return view;
    }

    public void onConfirmButtonPressed() {

        // Check that there is text in all of the fields

        // Validate that the text is correct

        // Save the object to the cache

        if (mListener != null) {
            mListener.onAddTripFinish();
        }
    }

    public void onCancelButtonPressed() {
        if (mListener != null) {
            mListener.onAddTripFinish();
        }
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
