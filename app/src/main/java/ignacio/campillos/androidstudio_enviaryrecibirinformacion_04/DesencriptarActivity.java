package ignacio.campillos.androidstudio_enviaryrecibirinformacion_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ignacio.campillos.androidstudio_enviaryrecibirinformacion_04.Modelos.Usuario;

public class DesencriptarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desencriptar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
         //  String password = bundle.getString("PASS");
         //   String email = bundle.getString("EMAIL");
         //   Usuario usuario = new Usuario(email,password);
            Usuario usuario = (Usuario) bundle.getSerializable("USER");
            Toast.makeText(this, usuario.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}