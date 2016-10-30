package ccomunities.alashka.com.ccommunities_dev.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ccomunities.alashka.com.ccommunities_dev.Adapter.AchievementAdapter;
import ccomunities.alashka.com.ccommunities_dev.Model.Achievement;
import ccomunities.alashka.com.ccommunities_dev.R;

/**
 * Created by nicaela on 29/10/16.
 */

public class AchievementFragment extends Fragment {

    private RecyclerView recycler;
    private AchievementAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public AchievementFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);


        recycler = (RecyclerView) view.findViewById(R.id.recycler_achievement);
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(layoutManager);

        adapter = new AchievementAdapter(getActivity());
        recycler.setAdapter(adapter);

//        AchievementAsyncTask task = new AchievementAsyncTask(this);
//        task.execute();

        return  view;

    }
    public AchievementAdapter getAdapter(){return  adapter;}
}
