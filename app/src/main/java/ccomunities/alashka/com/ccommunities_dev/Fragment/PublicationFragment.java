package ccomunities.alashka.com.ccommunities_dev.Fragment;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import ccomunities.alashka.com.ccommunities_dev.Adapter.PublicationAdapter;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Network.PublicationAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.NewPublicationActivity;
import ccomunities.alashka.com.ccommunities_dev.R;

public class PublicationFragment extends Fragment {

    private RecyclerView recycler;
    private PublicationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public PublicationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publication, container, false);

        List items = new ArrayList();

        recycler = (RecyclerView) view.findViewById(R.id.recycler_publication);
        //setHasFixedSize() para optimizar las operaciones con los ítems
        recycler.setHasFixedSize(true);
        //indicando que el recycler tomará la forma de lista vertical similar al ListView
        layoutManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(layoutManager);

        adapter = new PublicationAdapter(getActivity());
        recycler.setAdapter(adapter);

        Boolean showingData = loadPostFromDatabase();
        if (showingData) {
            ImageView image = (ImageView) view.findViewById(R.id.image_view_no_publications);
            TextView text = (TextView) view.findViewById(R.id.text_view_no_publications);

            RecyclerView list = (RecyclerView) view.findViewById(R.id.recycler_publication);

            image.setVisibility(View.INVISIBLE);
            text.setVisibility(View.INVISIBLE);

            list.setVisibility(View.VISIBLE);
        }

        PublicationAsyncTask task = new PublicationAsyncTask(this);
        task.execute(view, showingData);

        return view;
    }

    public PublicationAdapter getAdapter() {
        return adapter;
    }

    private Boolean loadPostFromDatabase() {
        List<Publication> publications = Publication.listAll(Publication.class);
        Boolean res = false;

        if (!publications.isEmpty()) {
            res = true;
            getAdapter().clearData();
            getAdapter().addAll(publications);
        }
        return res;
    }


}
