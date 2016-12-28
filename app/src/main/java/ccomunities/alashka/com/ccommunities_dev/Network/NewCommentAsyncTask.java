package ccomunities.alashka.com.ccommunities_dev.Network;


import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import ccomunities.alashka.com.ccommunities_dev.CommentActivity;
import ccomunities.alashka.com.ccommunities_dev.Model.Comment;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.NewPublicationActivity;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewCommentAsyncTask extends AsyncTask<Object, Void, Comment> {
    private CommentActivity commentActivity;

    public NewCommentAsyncTask(CommentActivity activity) {
        commentActivity = activity;
    }

    @Override
    protected Comment doInBackground(Object... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(commentActivity.getResources().getString(R.string.service_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);

        Call<Comment> call = service.createComment((Comment) params[0]);
        try {
            Response<Comment> response = call.execute();
            Comment comment = response.body();

            return comment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Comment comment) {
        if (comment == null) {
            Toast.makeText(commentActivity, commentActivity.getResources().getString(R.string.comment_error), Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("The created comment is " + comment.getContent());
            Toast.makeText(commentActivity, commentActivity.getResources().getString(R.string.comment_success), Toast.LENGTH_SHORT).show();
        }
    }
}
