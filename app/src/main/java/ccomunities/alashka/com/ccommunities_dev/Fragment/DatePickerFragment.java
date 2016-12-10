package ccomunities.alashka.com.ccommunities_dev.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ccomunities.alashka.com.ccommunities_dev.R;

/**
 * Created by ALANKIN on 10/12/16.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText dateTextView;
    private DatePickerDialog datePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initialize();

        datePicker = setDatePicker();
        return datePicker;
    }

    private void initialize() {
        changeLocale();

        dateTextView = (EditText) getActivity().findViewById(R.id.edit_text_date_npublication);
    }

    private void changeLocale() {
        Resources resources = this.getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        android.content.res.Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale("spa", "ESP");
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private DatePickerDialog setDatePicker() {
        int year, month, day;

        if (dateTextView.getText().toString().matches("")) {
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        } else {
            String[] date = dateTextView.getText().toString().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]) - 1;
            day = Integer.parseInt(date[2]);
        }

        datePicker = new DatePickerDialog(getActivity(), this, year, month, day);

        return datePicker;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        int realMonth = month + 1; // Because month index start from zero

        dateTextView.setText(year + "-" + realMonth + "-" + day);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dateTextView.getText().clear();
        super.onCancel(dialog);
    }
}