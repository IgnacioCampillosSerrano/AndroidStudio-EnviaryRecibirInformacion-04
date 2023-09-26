package ignacio.campillos.androidstudio_enviaryrecibirinformacion_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ignacio.campillos.androidstudio_enviaryrecibirinformacion_04.Modelos.Direccion;

public class CrearDireccionActivity extends AppCompatActivity {

    private EditText textCalle;
    private EditText textNumero;

    private EditText textCiudad;

    private Button buttonCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_direccion);

        inicializarVista();

        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calle = textCalle.getText().toString();
                int numero = Integer.parseInt(textNumero.getText().toString());
                String ciudad = textCiudad.getText().toString();

                Direccion direccion = new Direccion(calle, numero, ciudad);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DIR",direccion);

                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void inicializarVista() {
        textCalle = findViewById(R.id.textCalleCrear);
        textNumero = findViewById(R.id.txtNumeroCrear);
        textCiudad = findViewById(R.id.txtCiudadCrear);
        buttonCrear = findViewById(R.id.buttonCrearDireccionCrear);
    }
}