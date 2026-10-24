package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";

    // Controles de activity_main.xml
    private TextView tvTitulo;
    private EditText etNombre;
    private EditText etCorreo;
    private EditText etTelefono;
    private TextView tvTipo;
    private RadioGroup rgTipo;
    private RadioButton rbPersonal;
    private RadioButton rbTrabajo;
    private RadioButton rbFamilia;
    private Button btnEnviar;

    private ActivityResultLauncher<Intent> confirmacionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity: onCreate");
        setContentView(R.layout.activity_main);

        // Inicialización de controles con sus IDs
        tvTitulo = findViewById(R.id.tvTitulo);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        tvTipo = findViewById(R.id.tvTipo);
        rgTipo = findViewById(R.id.rgTipo);
        rbPersonal = findViewById(R.id.rbPersonal);
        rbTrabajo = findViewById(R.id.rbTrabajo);
        rbFamilia = findViewById(R.id.rbFamilia);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Registro del Launcher para la pantalla de confirmación
        confirmacionLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String nombre = data.getStringExtra("nombre");
                        String correo = data.getStringExtra("correo");
                        String telefono = data.getStringExtra("telefono");
                        String tipo = data.getStringExtra("tipo");

                        // Agregar el contacto a la lista en memoria
                        Contacto nuevoContacto = new Contacto(nombre, correo, telefono, tipo);
                        ListaActivity.listaContactos.add(nuevoContacto);

                        Toast.makeText(this, "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();

                        // Abrir pantalla de lista
                        Intent intentLista = new Intent(MainActivity.this, ListaActivity.class);
                        startActivity(intentLista);
                        finish();
                    }
                }
        );

        btnEnviar.setOnClickListener(v -> validarYEnviar());
    }

    private void validarYEnviar() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        boolean esValido = true;

        // Validar Nombre
        if (nombre.isEmpty()) {
            etNombre.setError("El nombre es obligatorio");
            esValido = false;
        }

        // Validar Correo
        if (correo.isEmpty()) {
            etCorreo.setError("El correo es obligatorio");
            esValido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Ingrese un formato de correo válido (ej: nombre@correo.com)");
            esValido = false;
        }

        // Validar Teléfono (exactamente 8 dígitos)
        if (telefono.isEmpty()) {
            etTelefono.setError("El teléfono es obligatorio");
            esValido = false;
        } else if (telefono.length() != 8) {
            etTelefono.setError("El teléfono debe tener exactamente 8 dígitos");
            esValido = false;
        }

        if (!esValido) {
            return;
        }

        // Obtener opción del RadioGroup
        int selectedId = rgTipo.getCheckedRadioButtonId();
        RadioButton rbSeleccionado = findViewById(selectedId);
        String tipo = rbSeleccionado != null ? rbSeleccionado.getText().toString() : "Personal";

        // Enviar datos mediante Intent a ConfirmacionActivity
        Intent intent = new Intent(MainActivity.this, ConfirmacionActivity.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("correo", correo);
        intent.putExtra("telefono", telefono);
        intent.putExtra("tipo", tipo);

        confirmacionLauncher.launch(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy");
    }
}
