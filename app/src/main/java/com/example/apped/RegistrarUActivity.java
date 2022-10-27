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

public class RegistrarUActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnRegistroU, btnCancelarU;
    EditText rtxtNombre, rtxtApellidoP,rtxtApellidoM,rtxtEmail,rtxtPassword;
    dUsuario dao;
    int id=0;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraru);

        rtxtNombre=(EditText) findViewById(R.id.rtxtNombre);
        rtxtApellidoP=(EditText) findViewById(R.id.rtxtApellidoP);
        rtxtApellidoM=(EditText) findViewById(R.id.rtxtApellidoM);
        rtxtEmail=(EditText) findViewById(R.id.rtxtEmail);
        rtxtPassword=(EditText) findViewById(R.id.rtxtPassword);
        btnRegistroU=(Button) findViewById(R.id.btnRegistroU);
        btnRegistroU.setOnClickListener(this);
        btnCancelarU=(Button) findViewById(R.id.btnCancelarU);
        btnCancelarU.setOnClickListener(this);
        dao= new dUsuario(this);

        Bundle b = getIntent().getExtras();
        id=b.getInt("Id");
        dao = new dUsuario(this);
        u = dao.getEmailById(id);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnRegistroU:
                Usuario u = new Usuario();
                validar();
                u.setNombre(rtxtNombre.getText().toString());
                u.setApellidoP(rtxtApellidoP.getText().toString());
                u.setApellidoM(rtxtApellidoM.getText().toString());
                u.setEmail(rtxtEmail.getText().toString());
                u.setPassword(rtxtPassword.getText().toString());
                if(!u. isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(this, "Registro Exitoso!!!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistrarUActivity.this, MainActivity.class);
                    i.putExtra("Id", id);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya registrado!!!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnCancelarU:
                Intent i2 = new Intent(RegistrarUActivity.this,MainActivity.class);
                i2.putExtra("Id", id);
                startActivity(i2);
                finish();
        }
    }

    public void validar(){

        String nombre = rtxtNombre.getText().toString();
        String apellidoP = rtxtApellidoP.getText().toString();
        String apellidoM = rtxtApellidoM.getText().toString();
        String email = rtxtEmail.getText().toString();
        String psw = rtxtPassword.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            rtxtNombre.setError("Ups! ingresa tu nombre");
            rtxtNombre.requestFocus();
        }else if (TextUtils.isEmpty(apellidoP)){
            rtxtApellidoP.setError("Ups! ingresa tu apellido paterno");
            rtxtApellidoP.requestFocus();
        }else if (TextUtils.isEmpty(apellidoM)){
            rtxtApellidoM.setError("Ups! ingresa tu apellido materno");
            rtxtApellidoM.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            rtxtEmail.setError("Ups! ingresa tu Email");
            rtxtEmail.requestFocus();
        }else if (TextUtils.isEmpty(psw)){
            rtxtPassword.setError("Ups! ingresa una contraseña");
            rtxtPassword.requestFocus();
        }
    }
}