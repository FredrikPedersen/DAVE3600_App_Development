package com.s306631.room4you.ui.fragments.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private OnPickerValueSelectedListener callback;

    public void setOnDateSelectedListener(OnPickerValueSelectedListener callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePicker =  new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        return datePicker;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        month += 1; //Counting starts at 0. Incrementing month here to not have to keep that in mind in the rest of the program
        String date = day + "-" + month + "-" + year;
        callback.onValueSelected(date);
    }
}