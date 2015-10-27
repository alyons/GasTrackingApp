package net.alexanderlyons.firstlesson;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import net.alexanderlyons.firstlesson.DataObjects.Car;
import net.alexanderlyons.firstlesson.Helpers.StringHelper;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCarFragment.OnAddCarFinishListener} interface
 * to handle interaction events.
 * Use the {@link AddCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCarFragment extends Fragment {
    private final static String NICKNAME_PARAM = "net.alexanderlyons.firstlesson.ADD_NICKNAME";
    private final static String MAKE_PARAM = "net.alexanderlyons.firstlesson.ADD_MAKE";
    private final static String MODEL_PARAM = "net.alexanderlyons.firstlesson.ADD_MODEL";
    private final static String YEAR_PARAM = "net.alexanderlyons.firstlesson.ADD_YEAR";
    private final static String CITY_MPG_PARAM = "net.alexanderlyons.firstlesson.ADD_CITY_MPG";
    private final static String HIGHWAY_MPG_PARAM = "net.alexanderlyons.firstlesson.ADD_HIGHWAY_MPG";

    private String nickname;
    private String make;
    private String model;
    private int year;
    private double cityMPG;
    private double highwayMPG;

    private OnAddCarFinishListener mListener;

    private EditText nicknameEditText;
    private EditText makeEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText cityMPGEditText;
    private EditText highwayMPGEditText;

    private Realm realm;

    public static AddCarFragment newInstance(String nickname, String make, String model, int year, double cityMPG, double highwayMPG) {
        AddCarFragment fragment = new AddCarFragment();
        Bundle args = new Bundle();
        args.putString(NICKNAME_PARAM, nickname);
        args.putString(MAKE_PARAM, make);
        args.putString(MODEL_PARAM, model);
        args.putInt(YEAR_PARAM, year);
        args.putDouble(CITY_MPG_PARAM, cityMPG);
        args.putDouble(HIGHWAY_MPG_PARAM, highwayMPG);
        fragment.setArguments(args);
        return fragment;
    }

    public AddCarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.nickname = getArguments().getString(NICKNAME_PARAM);
            this.make = getArguments().getString(MAKE_PARAM);
            this.model = getArguments().getString(MODEL_PARAM);
            this.year = getArguments().getInt(YEAR_PARAM);
            this.cityMPG = getArguments().getDouble(CITY_MPG_PARAM);
            this.highwayMPG = getArguments().getDouble(HIGHWAY_MPG_PARAM);
        }

        realm = Realm.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);

        this.nicknameEditText = (EditText)view.findViewById(R.id.nickname_edit_text);
        this.makeEditText = (EditText)view.findViewById(R.id.make_edit_text);
        this.modelEditText = (EditText)view.findViewById(R.id.model_edit_text);
        this.yearEditText = (EditText)view.findViewById(R.id.year_edit_text);
        this.cityMPGEditText = (EditText)view.findViewById(R.id.cityMPG_edit_text);
        this.highwayMPGEditText = (EditText)view.findViewById(R.id.highwayMPG_edit_text);

        return view;
    }

    public void onConfirmPressed() {

        nickname = nicknameEditText.getText().toString();

        make = makeEditText.getText().toString();
        if (StringHelper.isNullOrWhitespace(make)) {
            Toast.makeText(getContext(), "You must supply a make.", Toast.LENGTH_SHORT).show();
            return;
        }

        model = modelEditText.getText().toString();
        if (StringHelper.isNullOrWhitespace(model)) {
            Toast.makeText(getContext(), "You must supply a model.", Toast.LENGTH_SHORT).show();
            return;
        }

        String yearString = yearEditText.getText().toString();
        if (StringHelper.isNullOrWhitespace(yearString)) {
            Toast.makeText(getContext(), "You must supply a year.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                year = Integer.parseInt(yearString);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Year must be an integer.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String cityMPGString = cityMPGEditText.getText().toString();
        if (StringHelper.isNullOrWhitespace(cityMPGString)) {
            Toast.makeText(getContext(), "You must supply a city MPG.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                cityMPG = Double.parseDouble(cityMPGString);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "City MPG must be a number.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String highwayMPGString = highwayMPGEditText.getText().toString();
        if (StringHelper.isNullOrWhitespace(highwayMPGString)) {
            Toast.makeText(getContext(), "You must supply a highway MPG.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                highwayMPG = Double.parseDouble(highwayMPGString);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Highway MPG must be a number.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Car car;
        if (StringHelper.isNullOrWhitespace(nickname)) {
            car = new Car(make, model, year, cityMPG, highwayMPG);
        } else {
            car = new Car(nickname, make, model, year, cityMPG, highwayMPG);
        }

        realm.beginTransaction();
        realm.copyToRealm(car);
        realm.commitTransaction();

        if (mListener != null) {
            mListener.onAddCarFinish();
        }
    }

    public void onCancelPressed() {
        if (mListener != null) {
            mListener.onAddCarFinish();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnAddCarFinishListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnAddCarFinishListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddCarFinishListener {
        // TODO: Update argument type and name
        public void onAddCarFinish();
    }

}
