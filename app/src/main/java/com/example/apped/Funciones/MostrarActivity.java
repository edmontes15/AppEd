package com.example.apped.Funciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apped.Modelos.Usuario;
import com.example.apped.Modelos.dUsuario;
import com.example.apped.R;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    ListView listV;
    dUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        listV=(ListView) findViewById(R.id.listV);
        dao = new dUsuario(this);
        ArrayList<Usuario> l= dao.selectUsuario();


        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u:l) {
            list.add(u.getNombre()+" "+u.getApellidoP());
            ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
            listV.setAdapter(a);
        }
    }

}