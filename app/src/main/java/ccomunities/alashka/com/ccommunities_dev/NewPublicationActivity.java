package ccomunities.alashka.com.ccommunities_dev;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ccomunities.alashka.com.ccommunities_dev.Fragment.DatePickerFragment;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Network.NewPublicationAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.Util.UserUtil;

public class NewPublicationActivity extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private EditText date;
    private EditText place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_publication);

        initialize();
        addEvents();
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

    }

    private void initialize() {
        title = (EditText) findViewById(R.id.edit_text_tittle_npublication);
        description = (EditText) findViewById(R.id.edit_text_description_npublication);
        date = (EditText) findViewById(R.id.edit_text_date_npublication);
        place = (EditText) findViewById(R.id.edit_text_place_npublication);
    }

    private void addEvents() {
        date.setKeyListener(null);

        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDatePicker();
            }
        });

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openDatePicker();

                    //Hide keyboard if it's open.
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    private void openDatePicker() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void savePublication(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        Long user_id = sharedPreferences.getLong("user_id", -1);

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        String dateInString = date.getText().toString();

        try {
            Date date = formatter.parse(dateInString);

            Publication publication = new Publication(title.getText().toString(), description.getText().toString(), date, place.getText().toString(), user_id);

            NewPublicationAsyncTask task = new NewPublicationAsyncTask(this);
            task.execute(publication, user_id);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();
    }

    public void cancelPublication(View view) {
        finish();
    }
}
