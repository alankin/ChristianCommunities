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
import java.util.List;
import java.util.ArrayList;

import ccomunities.alashka.com.ccommunities_dev.Adapter.PublicationAdapter;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Network.PublicationAsyncTask;
import ccomunities.alashka.com.ccommunities_dev.NewPublicationActivity;
import ccomunities.alashka.com.ccommunities_dev.R;

public class PublicationFragment extends Fragment {

    private FloatingActionButton fab;

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

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewPublicationActivity.class );
                startActivity(intent);

            }
        });

        List items = new ArrayList();

        /*items.add(new Publication("Title 1", "This is a description description 1 description description description description description description description description description description description description description description description description end"));
        items.add(new Publication("Title 2", "This is a description 2 description description description description description description "));
        items.add(new Publication("Title 3", "This is a description 3 description description description description description description description description "));
        items.add(new Publication("Title 4", "This is a  description 4 description description description description description description description description description description "));
        items.add(new Publication("Title 5", "This is a  description 4 description description description description description description description description description description "));
        items.add(new Publication("Title 6", "This is a  description 4 description description description description description description description description description description "));
        items.add(new Publication("Title 7", "This is a  description 4 description description description description description description description description description description "));*/

        recycler = (RecyclerView) view.findViewById(R.id.recycler_publication);
        //setHasFixedSize() para optimizar las operaciones con los ítems
        recycler.setHasFixedSize(true);
        //indicando que el recycler tomará la forma de lista vertical similar al ListView
        layoutManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(layoutManager);

        adapter = new PublicationAdapter(getActivity());
        recycler.setAdapter(adapter);

        PublicationAsyncTask task = new PublicationAsyncTask(this);
        task.execute();

        return view;
    }

    public PublicationAdapter getAdapter() {
        return adapter;
    }
}
