package com.dossis.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MostrarDatos extends AppCompatActivity {

    Contacto contacto;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEdicionDatos = new Intent(view.getContext(), MainActivity.class);
                intentEdicionDatos.putExtra("nombreCompleto", contacto.nombreCompleto);
                intentEdicionDatos.putExtra("email", contacto.email);
                intentEdicionDatos.putExtra("telefono", contacto.telefono);
                intentEdicionDatos.putExtra("fechaNacimiento", contacto.fechaNacimiento);
                intentEdicionDatos.putExtra("descripcion", contacto.descripcion);
                startActivity(intentEdicionDatos);
            }
        });

        Intent intentRecibido = getIntent();
        Bundle bundle = intentRecibido.getExtras();

        String nombreCompleto = bundle.getString("nombreCompleto");
        String telefono = bundle.getString("telefono");
        String email = bundle.getString("email");
        String fechaNacimiento = bundle.getString("fechaNacimiento");
        String descripcion = bundle.getString("descripcion");

        contacto = new Contacto(nombreCompleto, fechaNacimiento, telefono, email, descripcion);
        cargarDatosContacto(contacto);
    }

    private void cargarDatosContacto(Contacto contacto) {
        TextView tvNombreCompleto = findViewById(R.id.tvNombreCompleto);
        TextView tvTelefono = findViewById(R.id.tvTelefono);
        TextView tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvDescripcion = findViewById(R.id.tvDescripcion);

        tvNombreCompleto.setText(contacto.getNombreCompleto());
        tvTelefono.setText(contacto.getTelefono());
        tvFechaNacimiento.setText(contacto.getFechaNacimiento().toString());
        tvEmail.setText(contacto.getEmail());
        tvDescripcion.setText(contacto.getDescripcion());
    }
}