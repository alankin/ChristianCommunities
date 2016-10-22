package ccomunities.alashka.com.ccommunities_dev.Network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Fragment.PublicationFragment;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ALANKIN on 21/10/16.
 */
public class PublicationAsyncTask extends AsyncTask<Void, Void, List<Publication>> {
    private PublicationFragment fragment;
    private Long user_id;

    public PublicationAsyncTask(PublicationFragment publicationFragment) {
        fragment = publicationFragment;

        SharedPreferences sharedPreferences = fragment.getActivity().getSharedPreferences(fragment.getActivity().getString(R.string.app_name), Context.MODE_PRIVATE);
        user_id = sharedPreferences.getLong("user_id", -1);
    }

    @Override
    protected List<Publication> doInBackground(Void... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ccommunitiesservice-dev.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);
        //user_id es para filtrar se debe pasar como parametro
        Call<List<Publication>> call = service.getAllPublications();

        try {
            //loadPostFromDatabase();

            Response<List<Publication>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Publication> publications) {

        fragment.getAdapter().clearData();
        fragment.getAdapter().addAll(publications);
        //savePublicationDB(publications);
    }

    private void savePublicationDB(List<Publication> publications){
        //List<Post> storagePosts = Post.listAll(Post.class);
        Publication.deleteAll(Publication.class);

        for (Publication publication : publications) {
            System.out.println("Saving on DB:" + publication.getTitle());
            //post.setUser(new User());
            publication.save();
        }
    }

    /*private void loadPostFromDatabase(){
        System.out.println("Loading post from database");
        List<Publication> posts = Publication.listAll(Publication.class);
        for (Publication post : posts) {
            System.out.println("PUBLICATION:" + post.toString());
            post.setUser(new User());
        }
        //System.out.println(posts.toString());
        fragment.getAdapter().clear();
        fragment.getAdapter().addAll(posts);
    }*/
}
