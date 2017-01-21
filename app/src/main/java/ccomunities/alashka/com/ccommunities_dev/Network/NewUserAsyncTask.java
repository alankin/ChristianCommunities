package ccomunities.alashka.com.ccommunities_dev.Network;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import ccomunities.alashka.com.ccommunities_dev.MainActivity;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.R;
import ccomunities.alashka.com.ccommunities_dev.SignInActivity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewUserAsyncTask extends AsyncTask<Object, Void, User> {
    private SignInActivity signInActivity;

    public NewUserAsyncTask(SignInActivity activity) {
        signInActivity = activity;
    }

    @Override
    protected User doInBackground(Object... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(signInActivity.getResources().getString(R.string.service_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);

        Call<User> call = service.createUser((User) params[0]);
        try {

            Response<User> response = call.execute();
            User user = response.body();

            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        if (user == null) {
            Toast.makeText(signInActivity, signInActivity.getResources().getString(R.string.user_not_created), Toast.LENGTH_LONG).show();
        } else {
            System.out.println("The created user is " + user.getUsername());
            Toast.makeText(signInActivity, signInActivity.getResources().getString(R.string.user_created), Toast.LENGTH_SHORT).show();

            //Save on shared preferences the id of the new user
            SharedPreferences sharedPreferences = signInActivity.getSharedPreferences(signInActivity.getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putLong("user_id", user.getId());
            editor.commit();

            //Opening Publications
            Intent intent = new Intent(signInActivity, MainActivity.class);
            signInActivity.startActivity(intent);
        }
    }
}
