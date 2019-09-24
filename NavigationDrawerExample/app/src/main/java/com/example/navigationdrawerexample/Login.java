package com.example.navigationdrawerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //creamos variables
    EditText txt_usuario, txt_contrasena;
    Button btn_ingresar, btn_recuperarcontra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creamos las variables para ubicar las herramientas
        txt_usuario = (EditText) findViewById(R.id.login_usuario);
        txt_contrasena = (EditText) findViewById(R.id.login_contrasena);
        btn_ingresar = (Button) findViewById(R.id.login_btn_ingreso);
        btn_recuperarcontra = (Button) findViewById(R.id.login_btn_recuperar_contra);

        //creamos la accion cuando demos clip en el boton de ingresar por el evento clip
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recuperamos los valores de las cajas de text
                String usuario = txt_usuario.getText().toString();
                String contrasena = txt_contrasena.getText().toString();
                String tipousuario = "administrador";

                //validar si es el usuario y contraseña esta registrado
                if (usuario.equals("jesus") && contrasena.equals("12345")){

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    //enviando los datos a otro activity en este caso se envia el valor del usuario que ingresos
                    //aqui debemos rescatar el tipo de trabajador si es administrador o supervisor
                    intent.putExtra("usuario", txt_usuario.getText().toString());
                    intent.putExtra("tipousuario", tipousuario);

                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //creamos la accion cuando demos clip en el boton Recordar Contraseña
        btn_recuperarcontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),RecuperarContrasena.class);
                startActivity(intent);
            }
        });

    }
}
