package com.example.tiendashopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DatosAlmacenados extends AppCompatActivity {

    TextView verIDUsuario, verNombreUsuario, verApellidoUsuario, verCorreUsuario;
    EditText consultarDatosBD;
    EditText inputEditarNombre, inputEditarApellido, inputEditarCorreo;
    Button btnVerDatosUsuario, btnVolverDashboard, btnActualizar, btnEliminar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Usuarios");

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

        inputEditarNombre = findViewById(R.id.editar_input_nombre_usuario);
        inputEditarApellido = findViewById(R.id.editar_input_apellido_usuario);
        inputEditarCorreo = findViewById(R.id.editar_input_email_usuario);

        btnActualizar = findViewById(R.id.ButtonActualizar);
        btnEliminar = findViewById(R.id.ButtonEliminar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatosUsuario();
                inputEditarNombre.setText("");
                inputEditarApellido.setText("");
                inputEditarCorreo.setText("");
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });


        btnVerDatosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperDatosUsuario();
                verIDUsuario.setText("");
                verNombreUsuario.setText("");
                verApellidoUsuario.setText("");
                verCorreUsuario.setText("");
            }
        });

        btnVolverDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboard();
            }
        });
    }

    public void recuperDatosUsuario(){
        String usuarioID = consultarDatosBD.getText().toString();
        DatabaseReference usuario = reference.child(usuarioID);

        usuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String id = snapshot.child("id").getValue(String.class);
                    verIDUsuario.setText("ID: " + id);

                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    verNombreUsuario.setText("Nombre: " + nombre);

                    String apellido = snapshot.child("Apellido").getValue(String.class);
                    verApellidoUsuario.setText("Apellido: " + apellido);

                    String correo = snapshot.child("Correo").getValue(String.class);
                    verCorreUsuario.setText("Correo: " + correo);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void dashboard(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void actualizarDatosUsuario(){

        String usuarioID = consultarDatosBD.getText().toString();

        Map<String, Object>nuevosDatos = new HashMap<>();
        nuevosDatos.put("ID", usuarioID);
        nuevosDatos.put("Nombre", inputEditarNombre.getText().toString());
        nuevosDatos.put("Apellido", inputEditarApellido.getText().toString());
        nuevosDatos.put("Correo", inputEditarCorreo.getText().toString());

        DatabaseReference usuario = reference.child(usuarioID);
        usuario.updateChildren(nuevosDatos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void eliminarUsuario(){
        String usuarioID = consultarDatosBD.getText().toString();
        DatabaseReference usuario = reference.child(usuarioID);
        usuario.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}