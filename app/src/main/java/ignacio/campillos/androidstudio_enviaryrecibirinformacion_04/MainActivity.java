package ignacio.campillos.androidstudio_enviaryrecibirinformacion_04;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ignacio.campillos.androidstudio_enviaryrecibirinformacion_04.Modelos.Direccion;
import ignacio.campillos.androidstudio_enviaryrecibirinformacion_04.Modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText textoPasswordMain;

    private EditText textoEmailMain;
    private Button buttonDesencriptarMain;
    private Button buttonCrearDireccionMain;

    private static final int DIRECCIONES = 123;
    private ActivityResultLauncher<Intent> launcherDirecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterface();
        initializeLauncher();

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

        buttonCrearDireccionMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CrearDireccionActivity.class);
                //startActivityForResult(intent, DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }

    private void initializeLauncher() {
        //1. Preparar como lanzar la actividad hijo (equivalente a startActivityForResult())
        //2. Preparar que voy a hacer cuando la hija devuelva datos (equivalente al onActivityResult())

        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, direccion.toString(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "SE HA CANCELADO", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     *
     * @param requestCode -> Identificador de la ventana cerrada
     * @param resultCode -> Modo en el que se ha cerrado
     * @param data -> Datos que estan dentro del intent
     */
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DIRECCIONES){
            if (resultCode == RESULT_OK){
                if (data != null){
                    Bundle bundle = data.getExtras();
                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, direccion.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(this, "CANCELADA", Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
    private void initializeInterface() {
        textoEmailMain = findViewById(R.id.textEmailMain);
        textoPasswordMain = findViewById(R.id.textPasswordMain);
        buttonDesencriptarMain = findViewById(R.id.buttonDesencriptarMain);
        buttonCrearDireccionMain = findViewById(R.id.buttonCrearDireccionMain);
    }
}