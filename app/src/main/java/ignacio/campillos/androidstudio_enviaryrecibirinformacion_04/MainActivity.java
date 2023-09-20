package ignacio.campillos.androidstudio_enviaryrecibirinformacion_04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BundleKt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textoPasswordMain;

    private EditText textoEmailMain;
    private Button buttonDesencriptarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterface();

        buttonDesencriptarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textoEmailMain.getText().toString();
                String password = buttonDesencriptarMain.getText().toString();

                if(password.isEmpty() || email.isEmpty()){
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else {
                    //CONECTAR CON LA ACTIVIDAD
                    Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class); //Crear un intent para acceder a la segunda interfaz y pasar el bundle
                    //PASAR INFORMACION
                    Bundle bundle = new Bundle(); //Crear bundle para pasar informacion
        //            bundle.putString("PASS",password); // Para añadir al bundle con la informacion a enviar ( ID + Content )
        //            bundle.putString("EMAIL",email);
                    bundle.putSerializable("USER",new Usuario(email,password));
                    intent.putExtras(bundle); // Pasar el bundle a la nueva actividad
                    startActivity(intent); // Activar la actividad
                }
            }
        });
    }

    private void initializeInterface() {
        textoEmailMain = findViewById(R.id.textEmailMain);
        textoPasswordMain = findViewById(R.id.textPasswordMain);
        buttonDesencriptarMain = findViewById(R.id.buttonDesencriptarMain);
    }
}