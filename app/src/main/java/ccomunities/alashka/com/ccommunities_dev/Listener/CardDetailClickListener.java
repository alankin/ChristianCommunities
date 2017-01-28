package ccomunities.alashka.com.ccommunities_dev.Listener;

import android.content.Intent;
import android.view.View;

import ccomunities.alashka.com.ccommunities_dev.CardDetailActivity;
import ccomunities.alashka.com.ccommunities_dev.CommentActivity;
import ccomunities.alashka.com.ccommunities_dev.Model.Publication;

/**
 * Created by ALANKIN on 17/12/16.
 */

public class CardDetailClickListener implements View.OnClickListener {

    Publication publication;

    public CardDetailClickListener(Publication publication) {
        this.publication = publication;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), CardDetailActivity.class);
        intent.putExtra("publication", publication);
        v.getContext().startActivity(intent);
    }
}
