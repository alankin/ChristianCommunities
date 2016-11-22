package ccomunities.alashka.com.ccommunities_dev.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.Network.CCommunitiesService;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ALANKIN on 13/11/16.
 */
public class UserUtil {
    public static User getUserFromDB(Long userId) {
        User user;
        long user_id = userId;

        if (user_id == -1) {
            return null;
        }

        user = User.findById(User.class, user_id);
        //user = User.find(User.class, "id = ?", Long.toString(user_id));

        return user;
    }
}
