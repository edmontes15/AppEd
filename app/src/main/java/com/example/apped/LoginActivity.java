package com.example.apped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView lblRegistro;
    Button btnLoginn;
    EditText etxtEmail, etxtPassword;
    dUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lblRegistro=(TextView) findViewById(R.id.lblRegistro);
        btnLoginn=(Button) findViewById(R.id.btnLoginn);
        lblRegistro.setOnClickListener((View.OnClickListener) this);
        etxtEmail=(EditText) findViewById(R.id.etxtEmail);
        etxtPassword=(EditText) findViewById(R.id.etxtPassword);
        btnLoginn.setOnClickListener(this);
        dao = new dUsuario(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnLoginn:
                String u=etxtEmail.getText().toString();
                String p=etxtPassword.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux=dao.getEmail(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(LoginActivity.this, MainActivity.class);
                    i2.putExtra("Id",ux.getId());
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this, "Correo o Password incorrectos", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.lblRegistro:
                Intent i2= new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i2);
                break;
        }
    }
}