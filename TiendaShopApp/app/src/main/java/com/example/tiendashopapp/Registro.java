package com.example.tiendashopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    EditText inputID, inputNombreUsuario, inputApellidoUsuario, inputCorreo, inputContrasena;
    Button btnGuardar, btnCancelar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Usuarios"); //Instancia del nodo
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inputID = findViewById(R.id.input_id_usuario);
        inputNombreUsuario = findViewById(R.id.input_nombre_usuario);
        inputApellidoUsuario = findViewById(R.id.input_apellido_usuario);
        inputCorreo = findViewById(R.id.input_email_usuario);
        inputContrasena = findViewById(R.id.input_contrasena_usuario);
        btnGuardar = findViewById(R.id.buttonGuardar);
        btnCancelar = findViewById(R.id.buttonCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarRegistro();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registarUsuario();
                //Cuando se guarden los datos los input queden vacios
                inputID.setText("");
                inputNombreUsuario.setText("");
                inputApellidoUsuario.setText("");
                inputCorreo.setText("");
                inputContrasena.setText("");
            }
        });

        //Coloque la misma información en id y en contraseña
        inputID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputContrasena.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }
    public void cancelarRegistro(){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }
    //Método para registrar usuarios y se guarden en Realtime Datababe
    public void registarUsuario(){
        String usuarioId = inputID.getText().toString();
        DatabaseReference nuevoUsuario = reference.child(usuarioId);

        String idUsuario = inputID.getText().toString();
        nuevoUsuario.child("id").setValue(idUsuario);

        String nombreUsuario = inputNombreUsuario.getText().toString();
        nuevoUsuario.child("Nombre").setValue(nombreUsuario);

        String apellidoUsuario = inputApellidoUsuario.getText().toString();
        nuevoUsuario.child("Apellido").setValue(apellidoUsuario);

        String correo = inputCorreo.getText().toString();
        nuevoUsuario.child("Correo").setValue(correo);

        String contrasena = inputContrasena.getText().toString();
        nuevoUsuario.child("contrasena").setValue(contrasena);

        //Crea el registro en Autenticación de Firebase
        firebaseAuthRegistro(correo, contrasena);

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
        irInicioSesion();
    }

    //Método de autenticación con Firebase
    public void firebaseAuthRegistro(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Usuario creado con exito", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registro.this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public  void irInicioSesion(){
        Intent intent = new Intent(this, InicioSesion.class);
        startActivity(intent);
    }
}