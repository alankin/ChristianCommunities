package ccomunities.alashka.com.ccommunities_dev.Network;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccomunities.alashka.com.ccommunities_dev.LoginActivity;
import ccomunities.alashka.com.ccommunities_dev.MainActivity;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginAsyncTask extends AsyncTask<User, Void, List<User>> {
    private LoginActivity loginActivity;
    private User userToLogin;

    private final String EXIST_KEY = "exist";
    private final String USER_KEY = "user";

    public LoginAsyncTask(LoginActivity activity) {
        loginActivity = activity;
    }

    @Override
    protected List<User> doInBackground(User... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ccommunitiesservice-dev.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);
        //Todo: Implement and change by login method
        Call<List<User>> call = service.getUsers();

        userToLogin = params[0];

        try {
            Response<List<User>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        Map<String, Object> user = userExist(userToLogin, users);
        if (user.get(EXIST_KEY) == Boolean.TRUE) {
            User userValid = (User) user.get(USER_KEY);

            SharedPreferences sharedPreferences = loginActivity.getSharedPreferences(loginActivity.getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putLong("user_id", userValid.getId());
            editor.commit();

            Intent intent = new Intent(loginActivity, MainActivity.class);
            loginActivity.startActivity(intent);
        } else {
            Toast.makeText(loginActivity, "Username and Password invalid. Try again please.", Toast.LENGTH_LONG).show();
        }
    }

    private Map<String, Object> userExist(User user, List<User> users) {

        Map<String, Object> response = new HashMap<>();
        response.put(EXIST_KEY, false);

        for (User userList : users) {
            Boolean compareUsername = userList.getUsername().equals(userToLogin.getUsername());
            Boolean comparePassword = userList.getPassword().equals(userToLogin.getPassword());
            if (compareUsername && comparePassword) {
                response.put(EXIST_KEY, true);
                response.put(USER_KEY, userList);
                break;
            }
        }

        return response;
    }
}
