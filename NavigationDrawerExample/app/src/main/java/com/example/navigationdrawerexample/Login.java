package com.example.navigationdrawerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //Metodo de los botones para llamar a otros Activity
    public void ingresar(View view){
        Intent ingresar = new Intent(this, MainActivity.class);
        startActivity(ingresar);

    }

    public void recordar(View view){
        Intent recordar = new Intent(this,RecuperarContrasena.class);
        startActivity(recordar);
    }
}
