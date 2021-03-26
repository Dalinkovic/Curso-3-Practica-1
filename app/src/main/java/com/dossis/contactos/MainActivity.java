package com.dossis.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity {

    Button btnSiguiente, btnDateCancel;
    TextView etNombreCompleto, etTelefono, etEmail, etDescripcion;
    DatePicker datePicker;

    public static String getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        Date date = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnDateCancel = findViewById(R.id.btnDateCancel);
        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        etTelefono = findViewById(R.id.etTextPhone);
        etEmail = findViewById(R.id.etEmailAddress);
        etDescripcion = findViewById(R.id.etDescripcion);
        datePicker = findViewById(R.id.datePicker);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreCompleto = etNombreCompleto.getText().toString();
                String email = etEmail.getText().toString();
                String telefono = etTelefono.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String fechaNacimiento = getDateFromDatePicker(datePicker);

                Intent intentMostrarDatos = new Intent(view.getContext(), MostrarDatos.class);
                intentMostrarDatos.putExtra("nombreCompleto", nombreCompleto);
                intentMostrarDatos.putExtra("email", email);
                intentMostrarDatos.putExtra("telefono", telefono);
                intentMostrarDatos.putExtra("fechaNacimiento", fechaNacimiento);
                intentMostrarDatos.putExtra("descripcion", descripcion);
                startActivity(intentMostrarDatos);
            }
        });

        btnDateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);

                String[] split = date.toString().split("-");
                int day = Integer.valueOf(split[2]);
                int month = Integer.valueOf(split[1]) - 1; //baseIndex=0
                int year = Integer.valueOf(split[0]);

                datePicker.updateDate(year, month, day);
            }
        });


        Intent intentRecibido = getIntent();
        Bundle bundle = intentRecibido.getExtras();
        if (bundle != null) {
            String nombreCompleto = bundle.getString("nombreCompleto");
            String telefono = bundle.getString("telefono");
            String email = bundle.getString("email");
            String fechaNacimiento = bundle.getString("fechaNacimiento");
            String descripcion = bundle.getString("descripcion");

            Contacto contacto = new Contacto(nombreCompleto, fechaNacimiento, telefono, email, descripcion);
            cargarDatosContacto(contacto);
        }
    }

    private void cargarDatosContacto(Contacto contacto) {

        etNombreCompleto.setText(contacto.getNombreCompleto());
        etTelefono.setText(contacto.getTelefono());
        String[] split = contacto.fechaNacimiento.split("-");
        int day = Integer.valueOf(split[2]);
        int month = Integer.valueOf(split[1]) - 1; //baseIndex=0
        int year = Integer.valueOf(split[0]);
        //datePicker.setMinDate(System.currentTimeMillis() - 1000);
        datePicker.updateDate(year, month, day);
        etEmail.setText(contacto.getEmail());
        etDescripcion.setText(contacto.getDescripcion());
    }
}