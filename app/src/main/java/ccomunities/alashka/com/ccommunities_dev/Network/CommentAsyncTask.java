package ccomunities.alashka.com.ccommunities_dev.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccomunities.alashka.com.ccommunities_dev.CommentActivity;
import ccomunities.alashka.com.ccommunities_dev.LoginActivity;
import ccomunities.alashka.com.ccommunities_dev.MainActivity;
import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicaela on 17/12/16.
 */

public class CommentAsyncTask extends AsyncTask<Object, Void, List<Comment>> {
    private CommentActivity commentActivity;
    private Long publicationId;

    public CommentAsyncTask(CommentActivity activity) {
        commentActivity = activity;
    }

    @Override
    protected List<Comment> doInBackground(Object... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(commentActivity.getResources().getString(R.string.service_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);
        // Retrieve the publicationid on comments activity
        publicationId = (Long) params[0];

        Call<List<Comment>> call = service.getComments(publicationId);

        try {
            Response<List<Comment>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Comment> comments) {
        for (Comment comment :
                comments) {
            Log.d("COMMENT:", comment.getContent());
            Log.d("COMMENT:", comment.getUser().getName());
        }
        commentActivity.getCommentAdapter().clear();
        commentActivity.getCommentAdapter().addAll(comments);
        //que vamos a hacer con la lista de comentarios
        // los vas a mostrar? los vas a guardar en la bd?
    }
}
