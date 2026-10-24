package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";

    private TextView tvDatos;
    private Button btnVolver;
    private Button btnConfirmar;

    private String nombre;
    private String correo;
    private String telefono;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ConfirmacionActivity: onCreate");
        setContentView(R.layout.activity_confirmacion);

        // Controles de activity_confirmacion.xml
        tvDatos = findViewById(R.id.tvDatos);
        btnVolver = findViewById(R.id.btnVolver);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        Intent intent = getIntent();
        if (intent != null) {
            nombre = intent.getStringExtra("nombre");
            correo = intent.getStringExtra("correo");
            telefono = intent.getStringExtra("telefono");
            tipo = intent.getStringExtra("tipo");

            String textoDatos = "Nombre: " + nombre + "\n" +
                    "Correo: " + correo + "\n" +
                    "Teléfono: " + telefono + "\n" +
                    "Tipo de contacto: " + tipo;
            tvDatos.setText(textoDatos);
        }

        btnVolver.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        btnConfirmar.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("nombre", nombre);
            resultIntent.putExtra("correo", correo);
            resultIntent.putExtra("telefono", telefono);
            resultIntent.putExtra("tipo", tipo);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ConfirmacionActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ConfirmacionActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ConfirmacionActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ConfirmacionActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ConfirmacionActivity: onDestroy");
    }
}
