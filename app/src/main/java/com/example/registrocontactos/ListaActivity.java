package com.example.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";

    public static final List<Contacto> listaContactos = new ArrayList<>();

    private RecyclerView rvContactos;
    private Button btnNuevo;
    private ContactoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ListaActivity: onCreate");
        setContentView(R.layout.activity_lista);

        rvContactos = findViewById(R.id.rvContactos);
        btnNuevo = findViewById(R.id.btnNuevo);

        adapter = new ContactoAdapter(listaContactos);
        rvContactos.setLayoutManager(new LinearLayoutManager(this));
        rvContactos.setAdapter(adapter);

        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(ListaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ListaActivity: onResume");
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ListaActivity: onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ListaActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ListaActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ListaActivity: onDestroy");
    }
}
