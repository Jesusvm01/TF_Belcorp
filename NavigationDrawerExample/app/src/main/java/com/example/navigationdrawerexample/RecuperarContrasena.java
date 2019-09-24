package com.example.navigationdrawerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecuperarContrasena extends AppCompatActivity {

    //declarando las variables
    EditText txt_correo;
    Button btn_enviar, btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        ////variable para llamar la celda
        btn_enviar = (Button) findViewById(R.id.RecupeContra_btn_enviar);
        btn_cancelar = (Button) findViewById(R.id.RecupeContra_btn_cancelar);

        //Programacion para enviar y recuperar la contraseña
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //metodo para recuperar la contraseña
                Toast.makeText(getApplicationContext(),"Se envio la contraseña", Toast.LENGTH_SHORT).show();
            }
        });

        //Programacion para cancelar y regresar a Login
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });


    }
}
