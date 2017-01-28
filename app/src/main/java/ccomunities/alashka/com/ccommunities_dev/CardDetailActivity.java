package ccomunities.alashka.com.ccommunities_dev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ccomunities.alashka.com.ccommunities_dev.Model.Publication;
import ccomunities.alashka.com.ccommunities_dev.Model.User;

public class CardDetailActivity extends AppCompatActivity {

    private Publication publication;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        initialize();
    }

    private void initialize() {
        publication = (Publication) getIntent().getSerializableExtra("publication");
        System.out.println("==================================");
        System.out.println(publication.getDescription());
        if (publication.getUser() != null){
            // si tengo usuario, mostrar normal lo que necesito
            System.out.println(publication.getUser_id());
        }
        //si no tengo usuario, llamar al servicio y cargar el usuario
    }
}
