package com.example.tiendashopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DashBoard extends AppCompatActivity {

    Button btnSalir;
    ImageButton btnDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnSalir = findViewById(R.id.ButtonSalir);
        btnDatos = (ImageButton) findViewById(R.id.ButtonVerDatosUsuario);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irDatosAlmacenados();
            }
        });
    }

    public void cerrarSesion(){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }

    public void irDatosAlmacenados(){
        Intent intent = new Intent(this, DatosAlmacenados.class);
        startActivity(intent);
    }
}