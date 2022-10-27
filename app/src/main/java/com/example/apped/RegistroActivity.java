package com.example.apped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnRegistro, btnCancelar;
    EditText ertxtNombre, ertxtApellidoP,ertxtApellidoM,ertxtEmail,ertxtPassword;
    dUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ertxtNombre=(EditText) findViewById(R.id.ertxtNombre);
        ertxtApellidoP=(EditText) findViewById(R.id.ertxtApellidoP);
        ertxtApellidoM=(EditText) findViewById(R.id.ertxtApellidoM);
        ertxtEmail=(EditText) findViewById(R.id.ertxtEmail);
        ertxtPassword=(EditText) findViewById(R.id.ertxtPassword);
        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        dao= new dUsuario(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnRegistro:
                Usuario u = new Usuario();
                validar();
                u.setNombre(ertxtNombre.getText().toString());
                u.setApellidoP(ertxtApellidoP.getText().toString());
                u.setApellidoM(ertxtApellidoM.getText().toString());
                u.setEmail(ertxtEmail.getText().toString());
                u.setPassword(ertxtPassword.getText().toString());
                if(!u. isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(this, "Registro Exitoso!!!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya registrado!!!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnCancelar:
                Intent i2 = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(i2);
                finish();
        }
    }

    public void validar(){

        String nombre = ertxtNombre.getText().toString();
        String apellidoP = ertxtApellidoP.getText().toString();
        String apellidoM = ertxtApellidoM.getText().toString();
        String email = ertxtEmail.getText().toString();
        String psw = ertxtPassword.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            ertxtNombre.setError("Ups! ingresa tu nombre");
            ertxtNombre.requestFocus();
        }else if (TextUtils.isEmpty(apellidoP)){
            ertxtApellidoP.setError("Ups! ingresa tu apellido paterno");
            ertxtApellidoP.requestFocus();
        }else if (TextUtils.isEmpty(apellidoM)){
            ertxtApellidoM.setError("Ups! ingresa tu apellido materno");
            ertxtApellidoM.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            ertxtEmail.setError("Ups! ingresa tu Email");
            ertxtEmail.requestFocus();
        }else if (TextUtils.isEmpty(psw)){
            ertxtPassword.setError("Ups! ingresa una contraseña");
            ertxtPassword.requestFocus();
        }
    }
}