package ccomunities.alashka.com.ccommunities_dev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Network.LoginAsyncTask;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
    }

    public void login(View view) {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        User user = new User();
        user.setUsername(name);
        user.setPassword(pass);

        Log.d("User", user.getUsername());

        LoginAsyncTask task = new LoginAsyncTask(this);
        task.execute(user);
    }
}
