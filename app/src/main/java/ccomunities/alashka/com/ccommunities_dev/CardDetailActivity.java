package ccomunities.alashka.com.ccommunities_dev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CardDetailActivity extends AppCompatActivity {

    private Long publicationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        initialize();
    }

    private void initialize() {
        publicationId = getIntent().getLongExtra("publicationId", -1);
    }
}
