package ccomunities.alashka.com.ccommunities_dev.Network;


import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.NewPublicationActivity;
import ccomunities.alashka.com.ccommunities_dev.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPublicationAsyncTask extends AsyncTask<Object, Void, Publication> {
    private NewPublicationActivity publicationActivity;

    public NewPublicationAsyncTask(NewPublicationActivity activity) {
        publicationActivity = activity;
    }

    @Override
    protected Publication doInBackground(Object... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(publicationActivity.getResources().getString(R.string.service_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCommunitiesService service = retrofit.create(CCommunitiesService.class);

        Call<Publication> call = service.createPublication((Publication) params[0]);
        try {

            Response<Publication> response = call.execute();
            Publication publication = response.body();

            return publication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Publication publication) {
        if (publication == null) {
            Toast.makeText(publicationActivity, publicationActivity.getResources().getString(R.string.publication_not_created), Toast.LENGTH_LONG).show();
        } else {
            System.out.println("The created publication is " + publication.getTitle());
            Toast.makeText(publicationActivity, publicationActivity.getResources().getString(R.string.publication_created), Toast.LENGTH_LONG).show();
        }
    }
}
