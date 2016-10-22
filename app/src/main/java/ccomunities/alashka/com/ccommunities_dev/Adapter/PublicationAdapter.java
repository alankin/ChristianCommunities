package ccomunities.alashka.com.ccommunities_dev.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.R;

/**
 * Created by nicaela on 19/10/16.
 */
public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {

    private List<Publication> publications;

    public PublicationAdapter(Context context) {
        //super(context, R.layout.post_item_layout);
    }

    public static class PublicationViewHolder extends RecyclerView.ViewHolder {
        public TextView tittle;
        public TextView description;

        public PublicationViewHolder(View view) {
            super(view);
            tittle = (TextView) view.findViewById(R.id.title_publication);
            description = (TextView) view.findViewById(R.id.description_publication);
        }
    }

    public PublicationAdapter(List<Publication> publications) {
        this.publications = publications;
    }


    //par que los adpaptadores sepan la cantidad de elementos que procesaran
    @Override
    public int getItemCount() {
        return publications == null ? 0 : publications.size();
    }

    // infla el contenido de un nuevo item para la lista
    @Override
    public PublicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_publication, parent, false);

        return new PublicationViewHolder(view);
    }

    //es el que realiza las modificaciones del contenido para cada item
    @Override
    public void onBindViewHolder(PublicationViewHolder holder, int position) {
        holder.tittle.setText(publications.get(position).getTitle());
        holder.description.setText(publications.get(position).getDescription());

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
