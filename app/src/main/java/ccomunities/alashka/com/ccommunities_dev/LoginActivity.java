package ccomunities.alashka.com.ccommunities_dev;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.PorterDuff;
import android.content.Intent;

import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Network.LoginAsyncTask;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        loading = ProgressDialog.show(this, "", getResources().getString(R.string.loading_user), true);
        task.execute(user, loading);
    }
    public void signIn(View view) {

        Intent intent = new Intent(view.getContext(), SingInActivity.class);
        startActivity(intent); new LoginAsyncTask(this);

    }
}
