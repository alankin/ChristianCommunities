package ccomunities.alashka.com.ccommunities_dev.Adapter;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Model.Achievement;
import ccomunities.alashka.com.ccommunities_dev.Model.UserAchievement;
import ccomunities.alashka.com.ccommunities_dev.R;

/**
 * Created by nicaela on 22/10/16.
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>{

    private List<Achievement> userAchievements;

    public AchievementAdapter(Context context) {
    }

    public static  class AchievementViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;

        public AchievementViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_achievement);
            description = (TextView) view.findViewById(R.id.description_achievement);
        }
    }

    public AchievementAdapter(List<Achievement> achievements) {
        this.userAchievements = achievements;
    }

    @Override
    public int getItemCount() {
        return userAchievements == null ? 0 : userAchievements.size();
    }

    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_achievement, parent, false);

        return new AchievementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AchievementViewHolder holder, int position) {
        holder.name.setText(userAchievements.get(position).getName());
        holder.description.setText(userAchievements.get(position).getDescription());

    }

    public void clearData(){
        int size = this.userAchievements == null ? 0 : this.userAchievements.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.userAchievements.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addAll(List<Achievement> userAchievementsList) {
        if (userAchievementsList != null && !userAchievementsList.isEmpty()) {
            this.userAchievements = userAchievementsList;

            this.notifyItemInserted(this.userAchievements.size());
        }
    }
}
