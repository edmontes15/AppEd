package com.example.apped.Funciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apped.MainActivity;
import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;
import com.example.apped.R;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener{

    EditText atxtNombre, atxtApellidP, atxtApellidoM, atxtEmail, atxtPassword;
    Button btnActualizar, btnCancelarA;
    int id=0;
    Usuario u;
    dUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        atxtNombre=(EditText) findViewById(R.id.atxtNombre);
        atxtApellidP=(EditText) findViewById(R.id.atxtApellidoP);
        atxtApellidoM=(EditText) findViewById(R.id.atxtApellidoM);
        atxtEmail=(EditText) findViewById(R.id.atxtEmail);
        atxtPassword=(EditText) findViewById(R.id.atxtPassword);
        btnActualizar=(Button) findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this);
        btnCancelarA=(Button) findViewById(R.id.btnCancelarA);
        btnCancelarA.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id=b.getInt("Id");
        dao = new dUsuario(this);
        u=dao.getEmailById(id);
        atxtNombre.setText(u.getNombre());
        atxtApellidP.setText(u.getApellidoP());
        atxtApellidoM.setText(u.getApellidoM());
        atxtEmail.setText(u.getEmail());
        atxtPassword.setText(u.getPassword());

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnActualizar:
                u.setNombre(atxtNombre.getText().toString());
                u.setApellidoP(atxtApellidP.getText().toString());
                u.setApellidoM(atxtApellidoM.getText().toString());
                u.setEmail(atxtEmail.getText().toString());
                u.setPassword(atxtPassword.getText().toString());
                validar();
                if(!u.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.updateUsuario(u)){
                    Toast.makeText(this, "Actualizacion Exitosa!!!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(EditarActivity.this, MainActivity.class);
                    i.putExtra("Id",u.getId());
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this,"No se puede actualizar!!!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancelarA:
                Intent i2 = new Intent(EditarActivity.this, MainActivity.class);
                i2.putExtra("Id", id);
                startActivity(i2);
                finish();
                break;
        }
    }

    public void validar(){

        String nombre = atxtNombre.getText().toString();
        String apellidoP = atxtApellidP.getText().toString();
        String apellidoM = atxtApellidoM.getText().toString();
        String email = atxtEmail.getText().toString();
        String psw = atxtPassword.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            atxtNombre.setError("Ups! ingresa tu nombre");
            atxtNombre.requestFocus();
        }else if (TextUtils.isEmpty(apellidoP)){
            atxtApellidP.setError("Ups! ingresa tu apellido paterno");
            atxtApellidP.requestFocus();
        }else if (TextUtils.isEmpty(apellidoM)){
            atxtApellidoM.setError("Ups! ingresa tu apellido materno");
            atxtApellidoM.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            atxtEmail.setError("Ups! ingresa tu Email");
            atxtEmail.requestFocus();
        }else if (TextUtils.isEmpty(psw)){
            atxtPassword.setError("Ups! ingresa una contraseña");
            atxtPassword.requestFocus();
        }
    }
}