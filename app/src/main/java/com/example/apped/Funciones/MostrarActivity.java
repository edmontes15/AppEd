package com.example.apped.Funciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.apped.MainActivity;
import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;
import com.example.apped.R;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    ListView listV;
    dUsuario dao;
    Button btnRegresar;

    int id=0;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        btnRegresar=(Button) findViewById(R.id.btnRegresar);
        listV=(ListView) findViewById(R.id.listV);
        dao = new dUsuario(this);
        ArrayList<Usuario> l= dao.selectUsuario();

        Bundle b = getIntent().getExtras();
        id=b.getInt("Id");
        dao = new dUsuario(this);
        u = dao.getEmailById(id);

        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u:l) {
            list.add(u.getNombre()+" "+u.getApellidoP()+" "+u.getEmail());
            ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
            listV.setAdapter(a);
        }


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MostrarActivity.this, MainActivity.class);
                i2.putExtra("Id", id);
                startActivity(i2);
                finish();
            }
        });

    }

}