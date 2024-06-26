package com.example.tiendashopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatosAlmacenados extends AppCompatActivity {

    TextView verIDUsuario, verNombreUsuario, verApellidoUsuario, verCorreUsuario;
    EditText consultarDatosBD;

    Button btnVerDatosUsuario, btnVolverDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_almacenados);

        verIDUsuario = findViewById(R.id.TextIDUsuario);
        verNombreUsuario = findViewById(R.id.TextNombreUsuario);
        verApellidoUsuario = findViewById(R.id.TextApellidoUsuario);
        verCorreUsuario = findViewById(R.id.TextEmailUsuario);

        consultarDatosBD = findViewById(R.id.EditTextConsultaBD);

        btnVerDatosUsuario = findViewById(R.id.ButtonConsultarDatos);
        btnVolverDashboard = findViewById(R.id.ButtonDashboard);

        btnVolverDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboard();
            }
        });
    }

    public void dashboard(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}