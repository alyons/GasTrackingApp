package net.alexanderlyons.firstlesson;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * Created by PyroticBlaziken on 10/22/2015.
 * Using code from: http://developer.android.com/guide/topics/ui/controls/pickers.html
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (TimePickerDialog.OnTimeSetListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTimeSetListener.");
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(this.getClass().toString(), String.format("Called on Time Set: [%2d:%2d]", hourOfDay, minute));
        if (mListener != null) mListener.onTimeSet(view, hourOfDay, minute);
    }

}
