package com.example.apped;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apped.Funciones.EditarActivity;
import com.example.apped.Funciones.MostrarActivity;
import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnBorrar, btnSalir, btnMostrar, btnCrear;
    TextView nombre, email;
    int id=0;

    Usuario u;
    dUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(TextView) findViewById(R.id.txtNombreI);
        email=(TextView) findViewById(R.id.txtEmailI);
        btnCrear=(Button) findViewById(R.id.btnCrear);
        btnEditar=(Button) findViewById(R.id.btnEditar);
        btnBorrar=(Button) findViewById(R.id.btnBorrar);
        btnSalir=(Button) findViewById(R.id.btnSalir);
        btnMostrar=(Button) findViewById(R.id.btnMostrar);
        btnCrear.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id=b.getInt("Id");
        dao = new dUsuario(this);
        u = dao.getEmailById(id);
        nombre.setText(u.getNombre());
        email.setText(u.getEmail());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCrear:
                Intent i = new Intent(MainActivity.this, RegistrarUActivity.class);
                i.putExtra("Id", id);
                startActivity(i);
                break;
            case R.id.btnEditar:
                Intent i2 = new Intent(MainActivity.this, EditarActivity.class);
                i2.putExtra("Id", id);
                startActivity(i2);
                break;
            case R.id.btnBorrar:
                AlertDialog.Builder b= new AlertDialog.Builder(this);
                b.setMessage("Estas Seguro de eliminar tu cuenta");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteUsuario(id)){
                            Toast.makeText(MainActivity.this, "Se elimino correctamente", Toast.LENGTH_LONG).show();
                            Intent i3 = new Intent(MainActivity.this, LoginActivity.class);
                            i3.putExtra("Id", id);
                            startActivity(i3);
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Error: No se elimino la cuenta", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btnMostrar:
                Intent i4 = new Intent(MainActivity.this, MostrarActivity.class);
                i4.putExtra("Id", id);
                startActivity(i4);
                break;
            case R.id.btnSalir:
                Toast.makeText(MainActivity.this, "Has salido de tu sesion", Toast.LENGTH_LONG).show();
                Intent i5 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i5);
                finish();
                break;


        }
    }
}