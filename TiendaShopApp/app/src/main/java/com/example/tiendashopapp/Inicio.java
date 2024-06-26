package com.example.tiendashopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button btnRegistro;
    Button btnIniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnRegistro = findViewById(R.id.buttonRegistrar);
        btnIniciarSesion = findViewById(R.id.ButtonIniciarSesion);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistro();
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irInicioSesion();
            }
        });
    }

    public void irRegistro(){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public  void irInicioSesion(){
        Intent intent = new Intent(this, InicioSesion.class);
        startActivity(intent);
    }
}