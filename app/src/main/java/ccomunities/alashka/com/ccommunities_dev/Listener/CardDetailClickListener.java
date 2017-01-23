package ccomunities.alashka.com.ccommunities_dev.Listener;

import android.content.Intent;
import android.view.View;

import ccomunities.alashka.com.ccommunities_dev.CardDetailActivity;
import ccomunities.alashka.com.ccommunities_dev.CommentActivity;

/**
 * Created by ALANKIN on 17/12/16.
 */

public class CardDetailClickListener implements View.OnClickListener {

    Long publicationId;

    public CardDetailClickListener(Long publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), CardDetailActivity.class);
        intent.putExtra("publicationId", publicationId);
        v.getContext().startActivity(intent);
    }
}
