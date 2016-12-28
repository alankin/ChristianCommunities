package ccomunities.alashka.com.ccommunities_dev.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Listener.CommentClickListener;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nicaela on 19/10/16.
 */
public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {

    private List<Publication> publications;

    public PublicationAdapter(Context context) {
    }

    public static class PublicationViewHolder extends RecyclerView.ViewHolder {
        public TextView tittle;
        public TextView description;
        public TextView date;
        public LinearLayout commentLayout;
        public CircleImageView profileImageView;
        private View viewAdapter;

        public PublicationViewHolder(View view) {
            super(view);

            initialize(view);
        }

        private void initialize(View view) {
            tittle = (TextView) view.findViewById(R.id.title_publication);
            description = (TextView) view.findViewById(R.id.description_publication);
            date = (TextView) view.findViewById(R.id.date_publication);
            commentLayout = (LinearLayout) view.findViewById(R.id.id_comment_layout);
            profileImageView = (CircleImageView) view.findViewById(R.id.image_user);
            viewAdapter = view;
        }
    }

    public PublicationAdapter(List<Publication> publications) {
        this.publications = publications;
    }

    @Override
    public int getItemCount() {
        return publications == null ? 0 : publications.size();
    }

    @Override
    public PublicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_publication, parent, false);

        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublicationViewHolder holder, int position) {
        holder.tittle.setText(publications.get(position).getTitle());
        holder.description.setText(publications.get(position).getDescription());
        holder.date.setText(publications.get(position).getDate());
        holder.commentLayout.setOnClickListener(new CommentClickListener(publications.get(position).getId()));
        //holder.profileImageView = Glide.with.....load(...);
        if (null != publications.get(position).getUser()) {
            Glide.with(holder.viewAdapter.getContext()).load(publications.get(position).getUser().getPathPhoto()).into(holder.profileImageView);
        }
    }

    public void clearData() {
        int size = this.publications == null ? 0 : this.publications.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.publications.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addAll(List<Publication> publicationList) {
        if (publicationList != null && !publicationList.isEmpty()) {
            this.publications = publicationList;

            this.notifyItemInserted(this.publications.size());
        }
    }

}
