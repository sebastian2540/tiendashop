package com.example.tiendashopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    EditText inputCorreoUsuario, inputContrasenaUsuario;
    Button btnIniciarSesion, btnVolverInicioApp;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        inputCorreoUsuario = findViewById(R.id.input_email_usuario);
        inputContrasenaUsuario = findViewById(R.id.input_contrasena_usuario);
        btnIniciarSesion = findViewById(R.id.ButtonIniciarSesion);
        btnVolverInicioApp = findViewById(R.id.ButtonVolverInicioApp);



        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = inputCorreoUsuario.getText().toString();
                String password = inputContrasenaUsuario.getText().toString();
                iniciarSesion(correo, password);
            }
        });

        btnVolverInicioApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverInicio();
            }
        });
    }

    public void irDahboard(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    //Método para iniciar sesión con los datos almacenados en Autenticación
    public void iniciarSesion(String correo, String password){
        auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            irDahboard();
                            Toast.makeText(InicioSesion.this, "Inicio de sesión correcto", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(InicioSesion.this, "Valide las credenciales", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void volverInicio(){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }
}