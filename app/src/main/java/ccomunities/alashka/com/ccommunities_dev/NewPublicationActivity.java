package ccomunities.alashka.com.ccommunities_dev;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class NewPublicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_publication);

        /*Button botonAceptar = (Button)findViewById(R.id.button_save);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validarDatos()
            }
        });*/

    }

    public void savePublication(View view) {

    }


    public void cancelPublication(View view) {
    }
}
