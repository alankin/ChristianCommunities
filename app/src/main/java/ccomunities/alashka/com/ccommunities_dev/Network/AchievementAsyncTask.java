package ccomunities.alashka.com.ccommunities_dev.Network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Fragment.AchievementFragment;
import ccomunities.alashka.com.ccommunities_dev.Fragment.PublicationFragment;
import ccomunities.alashka.com.ccommunities_dev.Model.Achievement;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.UserAchievement;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ALANKIN on 21/10/16.
 */
public class AchievementAsyncTask extends AsyncTask<Void, Void, List<Achievement>> {
    private AchievementFragment fragment;
    private Long user_id;

    public AchievementAsyncTask(AchievementFragment achievementFragment) {
        fragment = achievementFragment;

        SharedPreferences sharedPreferences = fragment.getActivity().getSharedPreferences(fragment.getActivity().getString(R.string.app_name), Context.MODE_PRIVATE);
        user_id = sharedPreferences.getLong("user_id", -1);
    }

    @Override
    protected List<Achievement> doInBackground(Void... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fragment.getContext().getResources().getString(R.string.service_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);

        Call<List<UserAchievement>> call = service.getUserAchievements(user_id);

        try {
            //loadPostFromDatabase();
            Response<List<UserAchievement>> response = call.execute();
            List<UserAchievement> _userAchievements = response.body();

            return getAchievements(_userAchievements);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Achievement> achievements) {
        fragment.getAdapter().clearData();
        fragment.getAdapter().addAll(achievements);
    }

    private List<Achievement> getAchievements(List<UserAchievement> achievements) {
        ArrayList<Achievement> achievementsResponse = new ArrayList<>();
        for (UserAchievement userAchievement :
                achievements) {
            achievementsResponse.add(userAchievement.getAchievement());
        }
        return achievementsResponse;
    }
}
