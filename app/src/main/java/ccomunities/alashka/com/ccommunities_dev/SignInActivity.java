package ccomunities.alashka.com.ccommunities_dev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ccomunities.alashka.com.ccommunities_dev.Model.Community;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Network.NewPublicationAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.Network.NewUserAsyncTask;

public class SignInActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText lastName;
    private EditText mail;

    private final Long communityId = 1L;
    private final String pathPhoto = "https://robohash.org/nonquoperferendis.png?size=300x300&set=set1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initToolbar();
        initialize();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_sign_in);
        toolbar.setTitle(R.string.edit_text_sign_in);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.toolbar_back);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }

        );
    }

    private void initialize() {
        username = (EditText) findViewById(R.id.edit_text_username_sign_in);
        password = (EditText) findViewById(R.id.edit_text_password_sign_in);
        name = (EditText) findViewById(R.id.edit_text_name_sign_in);
        lastName = (EditText) findViewById(R.id.edit_text_last_name_sign_in);
        mail = (EditText) findViewById(R.id.edit_email_sign_in);

    }

    public void createUser(View view) {
        //Today's date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(getString(R.string.date_format));
        String today = df.format(c.getTime());

        //TODO: complete the pathPhoto and behavior to load a photo

        User newUser = new User(username.getText().toString(), password.getText().toString(), name.getText().toString(), lastName.getText().toString(), mail.getText().toString(), pathPhoto, today, today, communityId);

        //Create the new user on service with asyncTask
        NewUserAsyncTask task = new NewUserAsyncTask(this);
        task.execute(newUser);
    }
}
